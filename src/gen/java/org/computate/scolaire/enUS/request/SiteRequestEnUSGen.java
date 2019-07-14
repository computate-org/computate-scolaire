package org.computate.scolaire.enUS.request;

import org.apache.solr.common.SolrDocumentList;
import java.security.MessageDigest;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import javax.crypto.spec.SecretKeySpec;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import java.util.Stack;
import java.security.SecureRandom;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import java.lang.Long;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
import io.vertx.core.Vertx;
import org.apache.commons.text.StringEscapeUtils;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import io.vertx.ext.web.api.OperationRequest;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import java.lang.Object;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteRequestEnUSGen<DEV> extends Object {

	///////////////////
	// siteContexte_ //
	///////////////////

	/**	L'entité « siteContexte_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteContextEnUS siteContexte_;
	public Wrap<SiteContextEnUS> siteContexte_Wrap = new Wrap<SiteContextEnUS>().p(this).c(SiteContextEnUS.class).var("siteContexte_").o(siteContexte_);

	/**	<br/>L'entité « siteContexte_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteContexte_">Trouver l'entité siteContexte_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteContexte_(Wrap<SiteContextEnUS> c);

	public SiteContextEnUS getSiteContexte_() {
		return siteContexte_;
	}

	public void setSiteContexte_(SiteContextEnUS siteContexte_) {
		this.siteContexte_ = siteContexte_;
		this.siteContexte_Wrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS siteContexte_Init() {
		if(!siteContexte_Wrap.alreadyInitialized) {
			_siteContexte_(siteContexte_Wrap);
			if(siteContexte_ == null)
				setSiteContexte_(siteContexte_Wrap.o);
		}
		siteContexte_Wrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	/////////////////
	// configSite_ //
	/////////////////

	/**	L'entité « configSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteConfig configSite_;
	public Wrap<SiteConfig> configSite_Wrap = new Wrap<SiteConfig>().p(this).c(SiteConfig.class).var("configSite_").o(configSite_);

	/**	<br/>L'entité « configSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:configSite_">Trouver l'entité configSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configSite_(Wrap<SiteConfig> c);

	public SiteConfig getConfigSite_() {
		return configSite_;
	}

	public void setConfigSite_(SiteConfig configSite_) {
		this.configSite_ = configSite_;
		this.configSite_Wrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS configSite_Init() {
		if(!configSite_Wrap.alreadyInitialized) {
			_configSite_(configSite_Wrap);
			if(configSite_ == null)
				setConfigSite_(configSite_Wrap.o);
		}
		configSite_Wrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSiteFrFR requeteSite_;
	public Wrap<RequeteSiteFrFR> requeteSite_Wrap = new Wrap<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Wrap<RequeteSiteFrFR> c);

	public RequeteSiteFrFR getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSiteFrFR requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Wrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS requeteSite_Init() {
		if(!requeteSite_Wrap.alreadyInitialized) {
			_requeteSite_(requeteSite_Wrap);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Wrap.o);
		}
		requeteSite_Wrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:vertx">Trouver l'entité vertx dans Solr</a>
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
	protected SiteRequestEnUS vertxInit() {
		if(!vertxWrap.alreadyInitialized) {
			_vertx(vertxWrap);
			if(vertx == null)
				setVertx(vertxWrap.o);
		}
		vertxWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////
	// objetJson //
	///////////////

	/**	L'entité « objetJson »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject objetJson;
	public Wrap<JsonObject> objetJsonWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("objetJson").o(objetJson);

	/**	<br/>L'entité « objetJson »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objetJson">Trouver l'entité objetJson dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetJson(Wrap<JsonObject> c);

	public JsonObject getObjetJson() {
		return objetJson;
	}

	public void setObjetJson(JsonObject objetJson) {
		this.objetJson = objetJson;
		this.objetJsonWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS objetJsonInit() {
		if(!objetJsonWrap.alreadyInitialized) {
			_objetJson(objetJsonWrap);
			if(objetJson == null)
				setObjetJson(objetJsonWrap.o);
		}
		objetJsonWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////
	// rechercheSolr //
	///////////////////

	/**	L'entité « rechercheSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrQuery rechercheSolr;
	public Wrap<SolrQuery> rechercheSolrWrap = new Wrap<SolrQuery>().p(this).c(SolrQuery.class).var("rechercheSolr").o(rechercheSolr);

	/**	<br/>L'entité « rechercheSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:rechercheSolr">Trouver l'entité rechercheSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _rechercheSolr(Wrap<SolrQuery> c);

	public SolrQuery getRechercheSolr() {
		return rechercheSolr;
	}

	public void setRechercheSolr(SolrQuery rechercheSolr) {
		this.rechercheSolr = rechercheSolr;
		this.rechercheSolrWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS rechercheSolrInit() {
		if(!rechercheSolrWrap.alreadyInitialized) {
			_rechercheSolr(rechercheSolrWrap);
			if(rechercheSolr == null)
				setRechercheSolr(rechercheSolrWrap.o);
		}
		rechercheSolrWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////////////
	// operationRequest //
	//////////////////////

	/**	L'entité « operationRequest »
	 *	 is defined as null before being initialized. 
	 */
	protected OperationRequest operationRequest;
	public Wrap<OperationRequest> operationRequestWrap = new Wrap<OperationRequest>().p(this).c(OperationRequest.class).var("operationRequest").o(operationRequest);

	/**	<br/>L'entité « operationRequest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:operationRequest">Trouver l'entité operationRequest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _operationRequest(Wrap<OperationRequest> c);

	public OperationRequest getOperationRequest() {
		return operationRequest;
	}

	public void setOperationRequest(OperationRequest operationRequest) {
		this.operationRequest = operationRequest;
		this.operationRequestWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS operationRequestInit() {
		if(!operationRequestWrap.alreadyInitialized) {
			_operationRequest(operationRequestWrap);
			if(operationRequest == null)
				setOperationRequest(operationRequestWrap.o);
		}
		operationRequestWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////////////
	// reponseRecherche //
	//////////////////////

	/**	L'entité « reponseRecherche »
	 *	 is defined as null before being initialized. 
	 */
	protected QueryResponse reponseRecherche;
	public Wrap<QueryResponse> reponseRechercheWrap = new Wrap<QueryResponse>().p(this).c(QueryResponse.class).var("reponseRecherche").o(reponseRecherche);

	/**	<br/>L'entité « reponseRecherche »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:reponseRecherche">Trouver l'entité reponseRecherche dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _reponseRecherche(Wrap<QueryResponse> c);

	public QueryResponse getReponseRecherche() {
		return reponseRecherche;
	}

	public void setReponseRecherche(QueryResponse reponseRecherche) {
		this.reponseRecherche = reponseRecherche;
		this.reponseRechercheWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS reponseRechercheInit() {
		if(!reponseRechercheWrap.alreadyInitialized) {
			_reponseRecherche(reponseRechercheWrap);
			if(reponseRecherche == null)
				setReponseRecherche(reponseRechercheWrap.o);
		}
		reponseRechercheWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	////////////////////////
	// resultatsRecherche //
	////////////////////////

	/**	L'entité « resultatsRecherche »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocumentList resultatsRecherche;
	public Wrap<SolrDocumentList> resultatsRechercheWrap = new Wrap<SolrDocumentList>().p(this).c(SolrDocumentList.class).var("resultatsRecherche").o(resultatsRecherche);

	/**	<br/>L'entité « resultatsRecherche »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:resultatsRecherche">Trouver l'entité resultatsRecherche dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _resultatsRecherche(Wrap<SolrDocumentList> c);

	public SolrDocumentList getResultatsRecherche() {
		return resultatsRecherche;
	}

	public void setResultatsRecherche(SolrDocumentList resultatsRecherche) {
		this.resultatsRecherche = resultatsRecherche;
		this.resultatsRechercheWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS resultatsRechercheInit() {
		if(!resultatsRechercheWrap.alreadyInitialized) {
			_resultatsRecherche(resultatsRechercheWrap);
			if(resultatsRecherche == null)
				setResultatsRecherche(resultatsRechercheWrap.o);
		}
		resultatsRechercheWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////
	// w //
	///////

	/**	L'entité « w »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain w;
	public Wrap<ToutEcrivain> wWrap = new Wrap<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("w").o(w);

	/**	<br/>L'entité « w »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:w">Trouver l'entité w dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _w(Wrap<ToutEcrivain> c);

	public ToutEcrivain getW() {
		return w;
	}

	public void setW(ToutEcrivain w) {
		this.w = w;
		this.wWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS wInit() {
		if(!wWrap.alreadyInitialized) {
			_w(wWrap);
			if(w == null)
				setW(wWrap.o);
		}
		if(w != null)
			w.initLoinPourClasse(requeteSite_);
		wWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////////////
	// utilisateurVertx //
	//////////////////////

	/**	L'entité « utilisateurVertx »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject utilisateurVertx;
	public Wrap<JsonObject> utilisateurVertxWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("utilisateurVertx").o(utilisateurVertx);

	/**	<br/>L'entité « utilisateurVertx »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurVertx">Trouver l'entité utilisateurVertx dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurVertx(Wrap<JsonObject> c);

	public JsonObject getUtilisateurVertx() {
		return utilisateurVertx;
	}

	public void setUtilisateurVertx(JsonObject utilisateurVertx) {
		this.utilisateurVertx = utilisateurVertx;
		this.utilisateurVertxWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurVertxInit() {
		if(!utilisateurVertxWrap.alreadyInitialized) {
			_utilisateurVertx(utilisateurVertxWrap);
			if(utilisateurVertx == null)
				setUtilisateurVertx(utilisateurVertxWrap.o);
		}
		utilisateurVertxWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////
	// principalJson //
	///////////////////

	/**	L'entité « principalJson »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject principalJson;
	public Wrap<JsonObject> principalJsonWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("principalJson").o(principalJson);

	/**	<br/>L'entité « principalJson »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:principalJson">Trouver l'entité principalJson dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _principalJson(Wrap<JsonObject> c);

	public JsonObject getPrincipalJson() {
		return principalJson;
	}

	public void setPrincipalJson(JsonObject principalJson) {
		this.principalJson = principalJson;
		this.principalJsonWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS principalJsonInit() {
		if(!principalJsonWrap.alreadyInitialized) {
			_principalJson(principalJsonWrap);
			if(principalJson == null)
				setPrincipalJson(principalJsonWrap.o);
		}
		principalJsonWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////
	// utilisateurId //
	///////////////////

	/**	L'entité « utilisateurId »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurId;
	public Wrap<String> utilisateurIdWrap = new Wrap<String>().p(this).c(String.class).var("utilisateurId").o(utilisateurId);

	/**	<br/>L'entité « utilisateurId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurId">Trouver l'entité utilisateurId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurId(Wrap<String> c);

	public String getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(String utilisateurId) {
		this.utilisateurId = utilisateurId;
		this.utilisateurIdWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurIdInit() {
		if(!utilisateurIdWrap.alreadyInitialized) {
			_utilisateurId(utilisateurIdWrap);
			if(utilisateurId == null)
				setUtilisateurId(utilisateurIdWrap.o);
		}
		utilisateurIdWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUtilisateurId() {
		return utilisateurId;
	}

	public String strUtilisateurId() {
		return utilisateurId == null ? "" : utilisateurId;
	}

	public String nomAffichageUtilisateurId() {
		return null;
	}

	public String htmTooltipUtilisateurId() {
		return null;
	}

	public String htmUtilisateurId() {
		return utilisateurId == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurId());
	}

	////////////////////
	// utilisateurNom //
	////////////////////

	/**	L'entité « utilisateurNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNom;
	public Wrap<String> utilisateurNomWrap = new Wrap<String>().p(this).c(String.class).var("utilisateurNom").o(utilisateurNom);

	/**	<br/>L'entité « utilisateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurNom">Trouver l'entité utilisateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNom(Wrap<String> c);

	public String getUtilisateurNom() {
		return utilisateurNom;
	}

	public void setUtilisateurNom(String utilisateurNom) {
		this.utilisateurNom = utilisateurNom;
		this.utilisateurNomWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurNomInit() {
		if(!utilisateurNomWrap.alreadyInitialized) {
			_utilisateurNom(utilisateurNomWrap);
			if(utilisateurNom == null)
				setUtilisateurNom(utilisateurNomWrap.o);
		}
		utilisateurNomWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUtilisateurNom() {
		return utilisateurNom;
	}

	public String strUtilisateurNom() {
		return utilisateurNom == null ? "" : utilisateurNom;
	}

	public String nomAffichageUtilisateurNom() {
		return null;
	}

	public String htmTooltipUtilisateurNom() {
		return null;
	}

	public String htmUtilisateurNom() {
		return utilisateurNom == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNom());
	}

	///////////////////////////
	// utilisateurNomFamille //
	///////////////////////////

	/**	L'entité « utilisateurNomFamille »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNomFamille;
	public Wrap<String> utilisateurNomFamilleWrap = new Wrap<String>().p(this).c(String.class).var("utilisateurNomFamille").o(utilisateurNomFamille);

	/**	<br/>L'entité « utilisateurNomFamille »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurNomFamille">Trouver l'entité utilisateurNomFamille dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomFamille(Wrap<String> c);

	public String getUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public void setUtilisateurNomFamille(String utilisateurNomFamille) {
		this.utilisateurNomFamille = utilisateurNomFamille;
		this.utilisateurNomFamilleWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurNomFamilleInit() {
		if(!utilisateurNomFamilleWrap.alreadyInitialized) {
			_utilisateurNomFamille(utilisateurNomFamilleWrap);
			if(utilisateurNomFamille == null)
				setUtilisateurNomFamille(utilisateurNomFamilleWrap.o);
		}
		utilisateurNomFamilleWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public String strUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : utilisateurNomFamille;
	}

	public String nomAffichageUtilisateurNomFamille() {
		return null;
	}

	public String htmTooltipUtilisateurNomFamille() {
		return null;
	}

	public String htmUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomFamille());
	}

	///////////////////////
	// utilisateurPrenom //
	///////////////////////

	/**	L'entité « utilisateurPrenom »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurPrenom;
	public Wrap<String> utilisateurPrenomWrap = new Wrap<String>().p(this).c(String.class).var("utilisateurPrenom").o(utilisateurPrenom);

	/**	<br/>L'entité « utilisateurPrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurPrenom">Trouver l'entité utilisateurPrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurPrenom(Wrap<String> c);

	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
		this.utilisateurPrenomWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurPrenomInit() {
		if(!utilisateurPrenomWrap.alreadyInitialized) {
			_utilisateurPrenom(utilisateurPrenomWrap);
			if(utilisateurPrenom == null)
				setUtilisateurPrenom(utilisateurPrenomWrap.o);
		}
		utilisateurPrenomWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public String strUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : utilisateurPrenom;
	}

	public String nomAffichageUtilisateurPrenom() {
		return null;
	}

	public String htmTooltipUtilisateurPrenom() {
		return null;
	}

	public String htmUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurPrenom());
	}

	///////////////////////////
	// utilisateurNomComplet //
	///////////////////////////

	/**	L'entité « utilisateurNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNomComplet;
	public Wrap<String> utilisateurNomCompletWrap = new Wrap<String>().p(this).c(String.class).var("utilisateurNomComplet").o(utilisateurNomComplet);

	/**	<br/>L'entité « utilisateurNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurNomComplet">Trouver l'entité utilisateurNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomComplet(Wrap<String> c);

	public String getUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public void setUtilisateurNomComplet(String utilisateurNomComplet) {
		this.utilisateurNomComplet = utilisateurNomComplet;
		this.utilisateurNomCompletWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurNomCompletInit() {
		if(!utilisateurNomCompletWrap.alreadyInitialized) {
			_utilisateurNomComplet(utilisateurNomCompletWrap);
			if(utilisateurNomComplet == null)
				setUtilisateurNomComplet(utilisateurNomCompletWrap.o);
		}
		utilisateurNomCompletWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public String strUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : utilisateurNomComplet;
	}

	public String nomAffichageUtilisateurNomComplet() {
		return null;
	}

	public String htmTooltipUtilisateurNomComplet() {
		return null;
	}

	public String htmUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomComplet());
	}

	/////////////////////////////
	// utilisateurRolesRoyaume //
	/////////////////////////////

	/**	L'entité « utilisateurRolesRoyaume »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	protected List<String> utilisateurRolesRoyaume = new java.util.ArrayList<java.lang.String>();
	public Wrap<List<String>> utilisateurRolesRoyaumeWrap = new Wrap<List<String>>().p(this).c(List.class).var("utilisateurRolesRoyaume").o(utilisateurRolesRoyaume);

	/**	<br/>L'entité « utilisateurRolesRoyaume »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurRolesRoyaume">Trouver l'entité utilisateurRolesRoyaume dans Solr</a>
	 * <br/>
	 * @param utilisateurRolesRoyaume est l'entité déjà construit. 
	 **/
	protected abstract void _utilisateurRolesRoyaume(List<String> o);

	public List<String> getUtilisateurRolesRoyaume() {
		return utilisateurRolesRoyaume;
	}

	public void setUtilisateurRolesRoyaume(List<String> utilisateurRolesRoyaume) {
		this.utilisateurRolesRoyaume = utilisateurRolesRoyaume;
		this.utilisateurRolesRoyaumeWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS addUtilisateurRolesRoyaume(String...objets) {
		for(String o : objets) {
			addUtilisateurRolesRoyaume(o);
		}
		return (SiteRequestEnUS)this;
	}
	public SiteRequestEnUS addUtilisateurRolesRoyaume(String o) {
		if(o != null && !utilisateurRolesRoyaume.contains(o))
			this.utilisateurRolesRoyaume.add(o);
		return (SiteRequestEnUS)this;
	}
	public SiteRequestEnUS setUtilisateurRolesRoyaume(JsonArray objets) {
		utilisateurRolesRoyaume.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addUtilisateurRolesRoyaume(o);
		}
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS utilisateurRolesRoyaumeInit() {
		if(!utilisateurRolesRoyaumeWrap.alreadyInitialized) {
			_utilisateurRolesRoyaume(utilisateurRolesRoyaume);
		}
		utilisateurRolesRoyaumeWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public List<String> solrUtilisateurRolesRoyaume() {
		return utilisateurRolesRoyaume;
	}

	public String strUtilisateurRolesRoyaume() {
		return utilisateurRolesRoyaume == null ? "" : utilisateurRolesRoyaume.toString();
	}

	public String nomAffichageUtilisateurRolesRoyaume() {
		return null;
	}

	public String htmTooltipUtilisateurRolesRoyaume() {
		return null;
	}

	public String htmUtilisateurRolesRoyaume() {
		return utilisateurRolesRoyaume == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurRolesRoyaume());
	}

	//////////////////////////
	// utilisateurRessource //
	//////////////////////////

	/**	L'entité « utilisateurRessource »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject utilisateurRessource;
	public Wrap<JsonObject> utilisateurRessourceWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("utilisateurRessource").o(utilisateurRessource);

	/**	<br/>L'entité « utilisateurRessource »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurRessource">Trouver l'entité utilisateurRessource dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurRessource(Wrap<JsonObject> c);

	public JsonObject getUtilisateurRessource() {
		return utilisateurRessource;
	}

	public void setUtilisateurRessource(JsonObject utilisateurRessource) {
		this.utilisateurRessource = utilisateurRessource;
		this.utilisateurRessourceWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurRessourceInit() {
		if(!utilisateurRessourceWrap.alreadyInitialized) {
			_utilisateurRessource(utilisateurRessourceWrap);
			if(utilisateurRessource == null)
				setUtilisateurRessource(utilisateurRessourceWrap.o);
		}
		utilisateurRessourceWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	/////////////////////
	// utilisateurSite //
	/////////////////////

	/**	L'entité « utilisateurSite »
	 *	 is defined as null before being initialized. 
	 */
	protected UtilisateurSite utilisateurSite;
	public Wrap<UtilisateurSite> utilisateurSiteWrap = new Wrap<UtilisateurSite>().p(this).c(UtilisateurSite.class).var("utilisateurSite").o(utilisateurSite);

	/**	<br/>L'entité « utilisateurSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:utilisateurSite">Trouver l'entité utilisateurSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurSite(Wrap<UtilisateurSite> c);

	public UtilisateurSite getUtilisateurSite() {
		return utilisateurSite;
	}

	public void setUtilisateurSite(UtilisateurSite utilisateurSite) {
		this.utilisateurSite = utilisateurSite;
		this.utilisateurSiteWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS utilisateurSiteInit() {
		if(!utilisateurSiteWrap.alreadyInitialized) {
			_utilisateurSite(utilisateurSiteWrap);
			if(utilisateurSite == null)
				setUtilisateurSite(utilisateurSiteWrap.o);
		}
		if(utilisateurSite != null)
			utilisateurSite.initLoinPourClasse(requeteSite_);
		utilisateurSiteWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	/////////////
	// xmlPile //
	/////////////

	/**	L'entité « xmlPile »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 */
	protected Stack<String> xmlPile = new Stack<String>();
	public Wrap<Stack<String>> xmlPileWrap = new Wrap<Stack<String>>().p(this).c(Stack.class).var("xmlPile").o(xmlPile);

	/**	<br/>L'entité « xmlPile »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:xmlPile">Trouver l'entité xmlPile dans Solr</a>
	 * <br/>
	 * @param xmlPile est l'entité déjà construit. 
	 **/
	protected abstract void _xmlPile(Stack<String> o);

	public Stack<String> getXmlPile() {
		return xmlPile;
	}

	public void setXmlPile(Stack<String> xmlPile) {
		this.xmlPile = xmlPile;
		this.xmlPileWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS xmlPileInit() {
		if(!xmlPileWrap.alreadyInitialized) {
			_xmlPile(xmlPile);
		}
		xmlPileWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////////
	// documentSolr //
	//////////////////

	/**	L'entité « documentSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument documentSolr;
	public Wrap<SolrDocument> documentSolrWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("documentSolr").o(documentSolr);

	/**	<br/>L'entité « documentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:documentSolr">Trouver l'entité documentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _documentSolr(Wrap<SolrDocument> c);

	public SolrDocument getDocumentSolr() {
		return documentSolr;
	}

	public void setDocumentSolr(SolrDocument documentSolr) {
		this.documentSolr = documentSolr;
		this.documentSolrWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS documentSolrInit() {
		if(!documentSolrWrap.alreadyInitialized) {
			_documentSolr(documentSolrWrap);
			if(documentSolr == null)
				setDocumentSolr(documentSolrWrap.o);
		}
		documentSolrWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	////////////////
	// pageAchete //
	////////////////

	/**	L'entité « pageAchete »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean pageAchete;
	public Wrap<Boolean> pageAcheteWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("pageAchete").o(pageAchete);

	/**	<br/>L'entité « pageAchete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageAchete">Trouver l'entité pageAchete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageAchete(Wrap<Boolean> c);

	public Boolean getPageAchete() {
		return pageAchete;
	}

	public void setPageAchete(Boolean pageAchete) {
		this.pageAchete = pageAchete;
		this.pageAcheteWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS setPageAchete(String o) {
		this.pageAchete = Boolean.parseBoolean(o);
		this.pageAcheteWrap.alreadyInitialized = true;
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS pageAcheteInit() {
		if(!pageAcheteWrap.alreadyInitialized) {
			_pageAchete(pageAcheteWrap);
			if(pageAchete == null)
				setPageAchete(pageAcheteWrap.o);
		}
		pageAcheteWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public Boolean solrPageAchete() {
		return pageAchete;
	}

	public String strPageAchete() {
		return pageAchete == null ? "" : pageAchete.toString();
	}

	public String nomAffichagePageAchete() {
		return null;
	}

	public String htmTooltipPageAchete() {
		return null;
	}

	public String htmPageAchete() {
		return pageAchete == null ? "" : StringEscapeUtils.escapeHtml4(strPageAchete());
	}

	///////////////
	// pageAdmin //
	///////////////

	/**	L'entité « pageAdmin »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean pageAdmin;
	public Wrap<Boolean> pageAdminWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("pageAdmin").o(pageAdmin);

	/**	<br/>L'entité « pageAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageAdmin">Trouver l'entité pageAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageAdmin(Wrap<Boolean> c);

	public Boolean getPageAdmin() {
		return pageAdmin;
	}

	public void setPageAdmin(Boolean pageAdmin) {
		this.pageAdmin = pageAdmin;
		this.pageAdminWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS setPageAdmin(String o) {
		this.pageAdmin = Boolean.parseBoolean(o);
		this.pageAdminWrap.alreadyInitialized = true;
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS pageAdminInit() {
		if(!pageAdminWrap.alreadyInitialized) {
			_pageAdmin(pageAdminWrap);
			if(pageAdmin == null)
				setPageAdmin(pageAdminWrap.o);
		}
		pageAdminWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public Boolean solrPageAdmin() {
		return pageAdmin;
	}

	public String strPageAdmin() {
		return pageAdmin == null ? "" : pageAdmin.toString();
	}

	public String nomAffichagePageAdmin() {
		return null;
	}

	public String htmTooltipPageAdmin() {
		return null;
	}

	public String htmPageAdmin() {
		return pageAdmin == null ? "" : StringEscapeUtils.escapeHtml4(strPageAdmin());
	}

	///////////////
	// requetePk //
	///////////////

	/**	L'entité « requetePk »
	 *	 is defined as null before being initialized. 
	 */
	protected Long requetePk;
	public Wrap<Long> requetePkWrap = new Wrap<Long>().p(this).c(Long.class).var("requetePk").o(requetePk);

	/**	<br/>L'entité « requetePk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:requetePk">Trouver l'entité requetePk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requetePk(Wrap<Long> c);

	public Long getRequetePk() {
		return requetePk;
	}

	public void setRequetePk(Long requetePk) {
		this.requetePk = requetePk;
		this.requetePkWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS setRequetePk(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.requetePk = Long.parseLong(o);
		this.requetePkWrap.alreadyInitialized = true;
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS requetePkInit() {
		if(!requetePkWrap.alreadyInitialized) {
			_requetePk(requetePkWrap);
			if(requetePk == null)
				setRequetePk(requetePkWrap.o);
		}
		requetePkWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public Long solrRequetePk() {
		return requetePk;
	}

	public String strRequetePk() {
		return requetePk == null ? "" : requetePk.toString();
	}

	public String nomAffichageRequetePk() {
		return null;
	}

	public String htmTooltipRequetePk() {
		return null;
	}

	public String htmRequetePk() {
		return requetePk == null ? "" : StringEscapeUtils.escapeHtml4(strRequetePk());
	}

	//////////////////
	// connexionSql //
	//////////////////

	/**	L'entité « connexionSql »
	 *	 is defined as null before being initialized. 
	 */
	protected SQLConnection connexionSql;
	public Wrap<SQLConnection> connexionSqlWrap = new Wrap<SQLConnection>().p(this).c(SQLConnection.class).var("connexionSql").o(connexionSql);

	/**	<br/>L'entité « connexionSql »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:connexionSql">Trouver l'entité connexionSql dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _connexionSql(Wrap<SQLConnection> c);

	public SQLConnection getConnexionSql() {
		return connexionSql;
	}

	public void setConnexionSql(SQLConnection connexionSql) {
		this.connexionSql = connexionSql;
		this.connexionSqlWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS connexionSqlInit() {
		if(!connexionSqlWrap.alreadyInitialized) {
			_connexionSql(connexionSqlWrap);
			if(connexionSql == null)
				setConnexionSql(connexionSqlWrap.o);
		}
		connexionSqlWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cryptageMotDePasse">Trouver l'entité cryptageMotDePasse dans Solr</a>
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
	protected SiteRequestEnUS cryptageMotDePasseInit() {
		if(!cryptageMotDePasseWrap.alreadyInitialized) {
			_cryptageMotDePasse(cryptageMotDePasseWrap);
			if(cryptageMotDePasse == null)
				setCryptageMotDePasse(cryptageMotDePasseWrap.o);
		}
		cryptageMotDePasseWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
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

	////////////////////////////
	// cryptageChiffreCrypter //
	////////////////////////////

	/**	L'entité « cryptageChiffreCrypter »
	 *	 is defined as null before being initialized. 
	 */
	protected Cipher cryptageChiffreCrypter;
	public Wrap<Cipher> cryptageChiffreCrypterWrap = new Wrap<Cipher>().p(this).c(Cipher.class).var("cryptageChiffreCrypter").o(cryptageChiffreCrypter);

	/**	<br/>L'entité « cryptageChiffreCrypter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cryptageChiffreCrypter">Trouver l'entité cryptageChiffreCrypter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageChiffreCrypter(Wrap<Cipher> c);

	public Cipher getCryptageChiffreCrypter() {
		return cryptageChiffreCrypter;
	}

	public void setCryptageChiffreCrypter(Cipher cryptageChiffreCrypter) {
		this.cryptageChiffreCrypter = cryptageChiffreCrypter;
		this.cryptageChiffreCrypterWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS cryptageChiffreCrypterInit() {
		if(!cryptageChiffreCrypterWrap.alreadyInitialized) {
			_cryptageChiffreCrypter(cryptageChiffreCrypterWrap);
			if(cryptageChiffreCrypter == null)
				setCryptageChiffreCrypter(cryptageChiffreCrypterWrap.o);
		}
		cryptageChiffreCrypterWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////////////////////
	// cryptageChiffreDecrypter //
	//////////////////////////////

	/**	L'entité « cryptageChiffreDecrypter »
	 *	 is defined as null before being initialized. 
	 */
	protected Cipher cryptageChiffreDecrypter;
	public Wrap<Cipher> cryptageChiffreDecrypterWrap = new Wrap<Cipher>().p(this).c(Cipher.class).var("cryptageChiffreDecrypter").o(cryptageChiffreDecrypter);

	/**	<br/>L'entité « cryptageChiffreDecrypter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cryptageChiffreDecrypter">Trouver l'entité cryptageChiffreDecrypter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageChiffreDecrypter(Wrap<Cipher> c);

	public Cipher getCryptageChiffreDecrypter() {
		return cryptageChiffreDecrypter;
	}

	public void setCryptageChiffreDecrypter(Cipher cryptageChiffreDecrypter) {
		this.cryptageChiffreDecrypter = cryptageChiffreDecrypter;
		this.cryptageChiffreDecrypterWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS cryptageChiffreDecrypterInit() {
		if(!cryptageChiffreDecrypterWrap.alreadyInitialized) {
			_cryptageChiffreDecrypter(cryptageChiffreDecrypterWrap);
			if(cryptageChiffreDecrypter == null)
				setCryptageChiffreDecrypter(cryptageChiffreDecrypterWrap.o);
		}
		cryptageChiffreDecrypterWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////////////
	// cryptageDigestMessage //
	///////////////////////////

	/**	L'entité « cryptageDigestMessage »
	 *	 is defined as null before being initialized. 
	 */
	protected MessageDigest cryptageDigestMessage;
	public Wrap<MessageDigest> cryptageDigestMessageWrap = new Wrap<MessageDigest>().p(this).c(MessageDigest.class).var("cryptageDigestMessage").o(cryptageDigestMessage);

	/**	<br/>L'entité « cryptageDigestMessage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cryptageDigestMessage">Trouver l'entité cryptageDigestMessage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageDigestMessage(Wrap<MessageDigest> c);

	public MessageDigest getCryptageDigestMessage() {
		return cryptageDigestMessage;
	}

	public void setCryptageDigestMessage(MessageDigest cryptageDigestMessage) {
		this.cryptageDigestMessage = cryptageDigestMessage;
		this.cryptageDigestMessageWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS cryptageDigestMessageInit() {
		if(!cryptageDigestMessageWrap.alreadyInitialized) {
			_cryptageDigestMessage(cryptageDigestMessageWrap);
			if(cryptageDigestMessage == null)
				setCryptageDigestMessage(cryptageDigestMessageWrap.o);
		}
		cryptageDigestMessageWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	/////////////////
	// cryptageCle //
	/////////////////

	/**	L'entité « cryptageCle »
	 *	 is defined as null before being initialized. 
	 */
	protected byte[] cryptageCle;
	public Wrap<byte[]> cryptageCleWrap = new Wrap<byte[]>().p(this).c(byte[].class).var("cryptageCle").o(cryptageCle);

	/**	<br/>L'entité « cryptageCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cryptageCle">Trouver l'entité cryptageCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageCle(Wrap<byte[]> c);

	public byte[] getCryptageCle() {
		return cryptageCle;
	}

	public void setCryptageCle(byte[] cryptageCle) {
		this.cryptageCle = cryptageCle;
		this.cryptageCleWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS cryptageCleInit() {
		if(!cryptageCleWrap.alreadyInitialized) {
			_cryptageCle(cryptageCleWrap);
			if(cryptageCle == null)
				setCryptageCle(cryptageCleWrap.o);
		}
		cryptageCleWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////////
	// aleatoireSecurise //
	///////////////////////

	/**	L'entité « aleatoireSecurise »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SecureRandom(). 
	 */
	protected SecureRandom aleatoireSecurise = new SecureRandom();
	public Wrap<SecureRandom> aleatoireSecuriseWrap = new Wrap<SecureRandom>().p(this).c(SecureRandom.class).var("aleatoireSecurise").o(aleatoireSecurise);

	/**	<br/>L'entité « aleatoireSecurise »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SecureRandom(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:aleatoireSecurise">Trouver l'entité aleatoireSecurise dans Solr</a>
	 * <br/>
	 * @param aleatoireSecurise est l'entité déjà construit. 
	 **/
	protected abstract void _aleatoireSecurise(SecureRandom o);

	public SecureRandom getAleatoireSecurise() {
		return aleatoireSecurise;
	}

	public void setAleatoireSecurise(SecureRandom aleatoireSecurise) {
		this.aleatoireSecurise = aleatoireSecurise;
		this.aleatoireSecuriseWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS aleatoireSecuriseInit() {
		if(!aleatoireSecuriseWrap.alreadyInitialized) {
			_aleatoireSecurise(aleatoireSecurise);
		}
		aleatoireSecuriseWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	////////////////////
	// specCleSecrete //
	////////////////////

	/**	L'entité « specCleSecrete »
	 *	 is defined as null before being initialized. 
	 */
	protected SecretKeySpec specCleSecrete;
	public Wrap<SecretKeySpec> specCleSecreteWrap = new Wrap<SecretKeySpec>().p(this).c(SecretKeySpec.class).var("specCleSecrete").o(specCleSecrete);

	/**	<br/>L'entité « specCleSecrete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:specCleSecrete">Trouver l'entité specCleSecrete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _specCleSecrete(Wrap<SecretKeySpec> c);

	public SecretKeySpec getSpecCleSecrete() {
		return specCleSecrete;
	}

	public void setSpecCleSecrete(SecretKeySpec specCleSecrete) {
		this.specCleSecrete = specCleSecrete;
		this.specCleSecreteWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS specCleSecreteInit() {
		if(!specCleSecreteWrap.alreadyInitialized) {
			_specCleSecrete(specCleSecreteWrap);
			if(specCleSecrete == null)
				setSpecCleSecrete(specCleSecreteWrap.o);
		}
		specCleSecreteWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean alreadyInitializedSiteRequestEnUS = false;

	public SiteRequestEnUS initLoinSiteRequestEnUS( requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!alreadyInitializedSiteRequestEnUS) {
			alreadyInitializedSiteRequestEnUS = true;
			initLoinSiteRequestEnUS();
		}
		return (SiteRequestEnUS)this;
	}

	public void initLoinSiteRequestEnUS() {
		initSiteRequestEnUS();
	}

	public void initSiteRequestEnUS() {
		siteContexte_Init();
		configSite_Init();
		requeteSite_Init();
		vertxInit();
		objetJsonInit();
		rechercheSolrInit();
		operationRequestInit();
		reponseRechercheInit();
		resultatsRechercheInit();
		wInit();
		utilisateurVertxInit();
		principalJsonInit();
		utilisateurIdInit();
		utilisateurNomInit();
		utilisateurNomFamilleInit();
		utilisateurPrenomInit();
		utilisateurNomCompletInit();
		utilisateurRolesRoyaumeInit();
		utilisateurRessourceInit();
		utilisateurSiteInit();
		xmlPileInit();
		documentSolrInit();
		pageAcheteInit();
		pageAdminInit();
		requetePkInit();
		connexionSqlInit();
		cryptageMotDePasseInit();
		cryptageChiffreCrypterInit();
		cryptageChiffreDecrypterInit();
		cryptageDigestMessageInit();
		cryptageCleInit();
		aleatoireSecuriseInit();
		specCleSecreteInit();
	}

	public void initLoinPourClasse( requeteSite_) {
		initLoinSiteRequestEnUS(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSiteRequestEnUS( requeteSite_) {
		if(w != null)
			w.setRequeteSite_(requeteSite_);
		if(utilisateurSite != null)
			utilisateurSite.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse( requeteSite_) {
		requeteSiteSiteRequestEnUS(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSiteRequestEnUS(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSiteRequestEnUS(String var) {
		SiteRequestEnUS oSiteRequestEnUS = (SiteRequestEnUS)this;
		switch(var) {
			case "siteContexte_":
				return oSiteRequestEnUS.siteContexte_;
			case "configSite_":
				return oSiteRequestEnUS.configSite_;
			case "requeteSite_":
				return oSiteRequestEnUS.requeteSite_;
			case "vertx":
				return oSiteRequestEnUS.vertx;
			case "objetJson":
				return oSiteRequestEnUS.objetJson;
			case "rechercheSolr":
				return oSiteRequestEnUS.rechercheSolr;
			case "operationRequest":
				return oSiteRequestEnUS.operationRequest;
			case "reponseRecherche":
				return oSiteRequestEnUS.reponseRecherche;
			case "resultatsRecherche":
				return oSiteRequestEnUS.resultatsRecherche;
			case "w":
				return oSiteRequestEnUS.w;
			case "utilisateurVertx":
				return oSiteRequestEnUS.utilisateurVertx;
			case "principalJson":
				return oSiteRequestEnUS.principalJson;
			case "utilisateurId":
				return oSiteRequestEnUS.utilisateurId;
			case "utilisateurNom":
				return oSiteRequestEnUS.utilisateurNom;
			case "utilisateurNomFamille":
				return oSiteRequestEnUS.utilisateurNomFamille;
			case "utilisateurPrenom":
				return oSiteRequestEnUS.utilisateurPrenom;
			case "utilisateurNomComplet":
				return oSiteRequestEnUS.utilisateurNomComplet;
			case "utilisateurRolesRoyaume":
				return oSiteRequestEnUS.utilisateurRolesRoyaume;
			case "utilisateurRessource":
				return oSiteRequestEnUS.utilisateurRessource;
			case "utilisateurSite":
				return oSiteRequestEnUS.utilisateurSite;
			case "xmlPile":
				return oSiteRequestEnUS.xmlPile;
			case "documentSolr":
				return oSiteRequestEnUS.documentSolr;
			case "pageAchete":
				return oSiteRequestEnUS.pageAchete;
			case "pageAdmin":
				return oSiteRequestEnUS.pageAdmin;
			case "requetePk":
				return oSiteRequestEnUS.requetePk;
			case "connexionSql":
				return oSiteRequestEnUS.connexionSql;
			case "cryptageMotDePasse":
				return oSiteRequestEnUS.cryptageMotDePasse;
			case "cryptageChiffreCrypter":
				return oSiteRequestEnUS.cryptageChiffreCrypter;
			case "cryptageChiffreDecrypter":
				return oSiteRequestEnUS.cryptageChiffreDecrypter;
			case "cryptageDigestMessage":
				return oSiteRequestEnUS.cryptageDigestMessage;
			case "cryptageCle":
				return oSiteRequestEnUS.cryptageCle;
			case "aleatoireSecurise":
				return oSiteRequestEnUS.aleatoireSecurise;
			case "specCleSecrete":
				return oSiteRequestEnUS.specCleSecrete;
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
				o = attribuerSiteRequestEnUS(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSiteRequestEnUS(String var, Object val) {
		SiteRequestEnUS oSiteRequestEnUS = (SiteRequestEnUS)this;
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
					o = definirSiteRequestEnUS(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSiteRequestEnUS(String var, String val) {
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
		if(!(o instanceof SiteRequestEnUS))
			return false;
		SiteRequestEnUS that = (SiteRequestEnUS)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SiteRequestEnUS {");
		sb.append(" }");
		return sb.toString();
	}
}
