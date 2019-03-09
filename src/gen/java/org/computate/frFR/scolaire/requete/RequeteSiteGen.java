package org.computate.frFR.scolaire.requete;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.apache.solr.common.SolrDocumentList;
import java.security.MessageDigest;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import java.util.Stack;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.security.SecureRandom;
import org.computate.frFR.scolaire.couverture.Couverture;
import java.lang.Long;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import java.lang.String;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSite;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.frFR.scolaire.config.ConfigSite;
import io.vertx.core.Vertx;
import org.apache.commons.text.StringEscapeUtils;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import io.vertx.ext.web.api.OperationRequest;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import java.lang.Object;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class RequeteSiteGen<DEV> extends Object {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte_">Trouver l'entité siteContexte_ dans Solr</a>
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

	/////////////////
	// configSite_ //
	/////////////////

	/**	L'entité « configSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected ConfigSite configSite_;
	public Couverture<ConfigSite> configSite_Couverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite_").o(configSite_);

	/**	<br/>L'entité « configSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite_">Trouver l'entité configSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configSite_(Couverture<ConfigSite> c);

	public ConfigSite getConfigSite_() {
		return configSite_;
	}

	public void setConfigSite_(ConfigSite configSite_) {
		this.configSite_ = configSite_;
		this.configSite_Couverture.dejaInitialise = true;
	}

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSite requeteSite_;
	public Couverture<RequeteSite> requeteSite_Couverture = new Couverture<RequeteSite>().p(this).c(RequeteSite.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSite> c);

	public RequeteSite getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSite requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
	}

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vertx">Trouver l'entité vertx dans Solr</a>
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
	protected RequeteSite vertxInit() {
		if(!vertxCouverture.dejaInitialise) {
			_vertx(vertxCouverture);
			if(vertx == null)
				setVertx(vertxCouverture.o);
		}
		vertxCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	///////////////
	// objetJson //
	///////////////

	/**	L'entité « objetJson »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject objetJson;
	public Couverture<JsonObject> objetJsonCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("objetJson").o(objetJson);

	/**	<br/>L'entité « objetJson »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetJson">Trouver l'entité objetJson dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetJson(Couverture<JsonObject> c);

	public JsonObject getObjetJson() {
		return objetJson;
	}

	public void setObjetJson(JsonObject objetJson) {
		this.objetJson = objetJson;
		this.objetJsonCouverture.dejaInitialise = true;
	}
	protected RequeteSite objetJsonInit() {
		if(!objetJsonCouverture.dejaInitialise) {
			_objetJson(objetJsonCouverture);
			if(objetJson == null)
				setObjetJson(objetJsonCouverture.o);
		}
		objetJsonCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	///////////////////
	// rechercheSolr //
	///////////////////

	/**	L'entité « rechercheSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrQuery rechercheSolr;
	public Couverture<SolrQuery> rechercheSolrCouverture = new Couverture<SolrQuery>().p(this).c(SolrQuery.class).var("rechercheSolr").o(rechercheSolr);

	/**	<br/>L'entité « rechercheSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:rechercheSolr">Trouver l'entité rechercheSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _rechercheSolr(Couverture<SolrQuery> c);

	public SolrQuery getRechercheSolr() {
		return rechercheSolr;
	}

	public void setRechercheSolr(SolrQuery rechercheSolr) {
		this.rechercheSolr = rechercheSolr;
		this.rechercheSolrCouverture.dejaInitialise = true;
	}
	protected RequeteSite rechercheSolrInit() {
		if(!rechercheSolrCouverture.dejaInitialise) {
			_rechercheSolr(rechercheSolrCouverture);
			if(rechercheSolr == null)
				setRechercheSolr(rechercheSolrCouverture.o);
		}
		rechercheSolrCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	//////////////////////
	// operationRequete //
	//////////////////////

	/**	L'entité « operationRequete »
	 *	 is defined as null before being initialized. 
	 */
	protected OperationRequest operationRequete;
	public Couverture<OperationRequest> operationRequeteCouverture = new Couverture<OperationRequest>().p(this).c(OperationRequest.class).var("operationRequete").o(operationRequete);

	/**	<br/>L'entité « operationRequete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:operationRequete">Trouver l'entité operationRequete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _operationRequete(Couverture<OperationRequest> c);

	public OperationRequest getOperationRequete() {
		return operationRequete;
	}

	public void setOperationRequete(OperationRequest operationRequete) {
		this.operationRequete = operationRequete;
		this.operationRequeteCouverture.dejaInitialise = true;
	}
	protected RequeteSite operationRequeteInit() {
		if(!operationRequeteCouverture.dejaInitialise) {
			_operationRequete(operationRequeteCouverture);
			if(operationRequete == null)
				setOperationRequete(operationRequeteCouverture.o);
		}
		operationRequeteCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	//////////////////////
	// reponseRecherche //
	//////////////////////

	/**	L'entité « reponseRecherche »
	 *	 is defined as null before being initialized. 
	 */
	protected QueryResponse reponseRecherche;
	public Couverture<QueryResponse> reponseRechercheCouverture = new Couverture<QueryResponse>().p(this).c(QueryResponse.class).var("reponseRecherche").o(reponseRecherche);

	/**	<br/>L'entité « reponseRecherche »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:reponseRecherche">Trouver l'entité reponseRecherche dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _reponseRecherche(Couverture<QueryResponse> c);

	public QueryResponse getReponseRecherche() {
		return reponseRecherche;
	}

	public void setReponseRecherche(QueryResponse reponseRecherche) {
		this.reponseRecherche = reponseRecherche;
		this.reponseRechercheCouverture.dejaInitialise = true;
	}
	protected RequeteSite reponseRechercheInit() {
		if(!reponseRechercheCouverture.dejaInitialise) {
			_reponseRecherche(reponseRechercheCouverture);
			if(reponseRecherche == null)
				setReponseRecherche(reponseRechercheCouverture.o);
		}
		reponseRechercheCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	////////////////////////
	// resultatsRecherche //
	////////////////////////

	/**	L'entité « resultatsRecherche »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocumentList resultatsRecherche;
	public Couverture<SolrDocumentList> resultatsRechercheCouverture = new Couverture<SolrDocumentList>().p(this).c(SolrDocumentList.class).var("resultatsRecherche").o(resultatsRecherche);

	/**	<br/>L'entité « resultatsRecherche »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:resultatsRecherche">Trouver l'entité resultatsRecherche dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _resultatsRecherche(Couverture<SolrDocumentList> c);

	public SolrDocumentList getResultatsRecherche() {
		return resultatsRecherche;
	}

	public void setResultatsRecherche(SolrDocumentList resultatsRecherche) {
		this.resultatsRecherche = resultatsRecherche;
		this.resultatsRechercheCouverture.dejaInitialise = true;
	}
	protected RequeteSite resultatsRechercheInit() {
		if(!resultatsRechercheCouverture.dejaInitialise) {
			_resultatsRecherche(resultatsRechercheCouverture);
			if(resultatsRecherche == null)
				setResultatsRecherche(resultatsRechercheCouverture.o);
		}
		resultatsRechercheCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:w">Trouver l'entité w dans Solr</a>
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
	protected RequeteSite wInit() {
		if(!wCouverture.dejaInitialise) {
			_w(wCouverture);
			if(w == null)
				setW(wCouverture.o);
		}
		if(w != null)
			w.initLoinPourClasse(requeteSite_);
		wCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	//////////////////////
	// utilisateurVertx //
	//////////////////////

	/**	L'entité « utilisateurVertx »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject utilisateurVertx;
	public Couverture<JsonObject> utilisateurVertxCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("utilisateurVertx").o(utilisateurVertx);

	/**	<br/>L'entité « utilisateurVertx »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurVertx">Trouver l'entité utilisateurVertx dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurVertx(Couverture<JsonObject> c);

	public JsonObject getUtilisateurVertx() {
		return utilisateurVertx;
	}

	public void setUtilisateurVertx(JsonObject utilisateurVertx) {
		this.utilisateurVertx = utilisateurVertx;
		this.utilisateurVertxCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurVertxInit() {
		if(!utilisateurVertxCouverture.dejaInitialise) {
			_utilisateurVertx(utilisateurVertxCouverture);
			if(utilisateurVertx == null)
				setUtilisateurVertx(utilisateurVertxCouverture.o);
		}
		utilisateurVertxCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	///////////////////
	// principalJson //
	///////////////////

	/**	L'entité « principalJson »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject principalJson;
	public Couverture<JsonObject> principalJsonCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("principalJson").o(principalJson);

	/**	<br/>L'entité « principalJson »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:principalJson">Trouver l'entité principalJson dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _principalJson(Couverture<JsonObject> c);

	public JsonObject getPrincipalJson() {
		return principalJson;
	}

	public void setPrincipalJson(JsonObject principalJson) {
		this.principalJson = principalJson;
		this.principalJsonCouverture.dejaInitialise = true;
	}
	protected RequeteSite principalJsonInit() {
		if(!principalJsonCouverture.dejaInitialise) {
			_principalJson(principalJsonCouverture);
			if(principalJson == null)
				setPrincipalJson(principalJsonCouverture.o);
		}
		principalJsonCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	///////////////////////////
	// utilisateurNomDomaine //
	///////////////////////////

	/**	L'entité « utilisateurNomDomaine »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNomDomaine;
	public Couverture<String> utilisateurNomDomaineCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomDomaine").o(utilisateurNomDomaine);

	/**	<br/>L'entité « utilisateurNomDomaine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomDomaine">Trouver l'entité utilisateurNomDomaine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomDomaine(Couverture<String> c);

	public String getUtilisateurNomDomaine() {
		return utilisateurNomDomaine;
	}

	public void setUtilisateurNomDomaine(String utilisateurNomDomaine) {
		this.utilisateurNomDomaine = utilisateurNomDomaine;
		this.utilisateurNomDomaineCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurNomDomaineInit() {
		if(!utilisateurNomDomaineCouverture.dejaInitialise) {
			_utilisateurNomDomaine(utilisateurNomDomaineCouverture);
			if(utilisateurNomDomaine == null)
				setUtilisateurNomDomaine(utilisateurNomDomaineCouverture.o);
		}
		utilisateurNomDomaineCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	public String solrUtilisateurNomDomaine() {
		return utilisateurNomDomaine;
	}

	public String strUtilisateurNomDomaine() {
		return utilisateurNomDomaine == null ? "" : utilisateurNomDomaine;
	}

	public String nomAffichageUtilisateurNomDomaine() {
		return null;
	}

	public String htmTooltipUtilisateurNomDomaine() {
		return null;
	}

	public String htmUtilisateurNomDomaine() {
		return utilisateurNomDomaine == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomDomaine());
	}

	////////////////////////////
	// utilisateurNomEnsemble //
	////////////////////////////

	/**	L'entité « utilisateurNomEnsemble »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNomEnsemble;
	public Couverture<String> utilisateurNomEnsembleCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomEnsemble").o(utilisateurNomEnsemble);

	/**	<br/>L'entité « utilisateurNomEnsemble »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomEnsemble">Trouver l'entité utilisateurNomEnsemble dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomEnsemble(Couverture<String> c);

	public String getUtilisateurNomEnsemble() {
		return utilisateurNomEnsemble;
	}

	public void setUtilisateurNomEnsemble(String utilisateurNomEnsemble) {
		this.utilisateurNomEnsemble = utilisateurNomEnsemble;
		this.utilisateurNomEnsembleCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurNomEnsembleInit() {
		if(!utilisateurNomEnsembleCouverture.dejaInitialise) {
			_utilisateurNomEnsemble(utilisateurNomEnsembleCouverture);
			if(utilisateurNomEnsemble == null)
				setUtilisateurNomEnsemble(utilisateurNomEnsembleCouverture.o);
		}
		utilisateurNomEnsembleCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	public String solrUtilisateurNomEnsemble() {
		return utilisateurNomEnsemble;
	}

	public String strUtilisateurNomEnsemble() {
		return utilisateurNomEnsemble == null ? "" : utilisateurNomEnsemble;
	}

	public String nomAffichageUtilisateurNomEnsemble() {
		return null;
	}

	public String htmTooltipUtilisateurNomEnsemble() {
		return null;
	}

	public String htmUtilisateurNomEnsemble() {
		return utilisateurNomEnsemble == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomEnsemble());
	}

	///////////////////
	// utilisateurId //
	///////////////////

	/**	L'entité « utilisateurId »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurId;
	public Couverture<String> utilisateurIdCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurId").o(utilisateurId);

	/**	<br/>L'entité « utilisateurId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurId">Trouver l'entité utilisateurId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurId(Couverture<String> c);

	public String getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(String utilisateurId) {
		this.utilisateurId = utilisateurId;
		this.utilisateurIdCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurIdInit() {
		if(!utilisateurIdCouverture.dejaInitialise) {
			_utilisateurId(utilisateurIdCouverture);
			if(utilisateurId == null)
				setUtilisateurId(utilisateurIdCouverture.o);
		}
		utilisateurIdCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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
	public Couverture<String> utilisateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNom").o(utilisateurNom);

	/**	<br/>L'entité « utilisateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNom">Trouver l'entité utilisateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNom(Couverture<String> c);

	public String getUtilisateurNom() {
		return utilisateurNom;
	}

	public void setUtilisateurNom(String utilisateurNom) {
		this.utilisateurNom = utilisateurNom;
		this.utilisateurNomCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurNomInit() {
		if(!utilisateurNomCouverture.dejaInitialise) {
			_utilisateurNom(utilisateurNomCouverture);
			if(utilisateurNom == null)
				setUtilisateurNom(utilisateurNomCouverture.o);
		}
		utilisateurNomCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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
	public Couverture<String> utilisateurNomFamilleCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomFamille").o(utilisateurNomFamille);

	/**	<br/>L'entité « utilisateurNomFamille »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomFamille">Trouver l'entité utilisateurNomFamille dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomFamille(Couverture<String> c);

	public String getUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public void setUtilisateurNomFamille(String utilisateurNomFamille) {
		this.utilisateurNomFamille = utilisateurNomFamille;
		this.utilisateurNomFamilleCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurNomFamilleInit() {
		if(!utilisateurNomFamilleCouverture.dejaInitialise) {
			_utilisateurNomFamille(utilisateurNomFamilleCouverture);
			if(utilisateurNomFamille == null)
				setUtilisateurNomFamille(utilisateurNomFamilleCouverture.o);
		}
		utilisateurNomFamilleCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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
	public Couverture<String> utilisateurPrenomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurPrenom").o(utilisateurPrenom);

	/**	<br/>L'entité « utilisateurPrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurPrenom">Trouver l'entité utilisateurPrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurPrenom(Couverture<String> c);

	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
		this.utilisateurPrenomCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurPrenomInit() {
		if(!utilisateurPrenomCouverture.dejaInitialise) {
			_utilisateurPrenom(utilisateurPrenomCouverture);
			if(utilisateurPrenom == null)
				setUtilisateurPrenom(utilisateurPrenomCouverture.o);
		}
		utilisateurPrenomCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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
	public Couverture<String> utilisateurNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomComplet").o(utilisateurNomComplet);

	/**	<br/>L'entité « utilisateurNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomComplet">Trouver l'entité utilisateurNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomComplet(Couverture<String> c);

	public String getUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public void setUtilisateurNomComplet(String utilisateurNomComplet) {
		this.utilisateurNomComplet = utilisateurNomComplet;
		this.utilisateurNomCompletCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurNomCompletInit() {
		if(!utilisateurNomCompletCouverture.dejaInitialise) {
			_utilisateurNomComplet(utilisateurNomCompletCouverture);
			if(utilisateurNomComplet == null)
				setUtilisateurNomComplet(utilisateurNomCompletCouverture.o);
		}
		utilisateurNomCompletCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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

	/////////////////////
	// utilisateurSite //
	/////////////////////

	/**	L'entité « utilisateurSite »
	 *	 is defined as null before being initialized. 
	 */
	protected UtilisateurSite utilisateurSite;
	public Couverture<UtilisateurSite> utilisateurSiteCouverture = new Couverture<UtilisateurSite>().p(this).c(UtilisateurSite.class).var("utilisateurSite").o(utilisateurSite);

	/**	<br/>L'entité « utilisateurSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurSite">Trouver l'entité utilisateurSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurSite(Couverture<UtilisateurSite> c);

	public UtilisateurSite getUtilisateurSite() {
		return utilisateurSite;
	}

	public void setUtilisateurSite(UtilisateurSite utilisateurSite) {
		this.utilisateurSite = utilisateurSite;
		this.utilisateurSiteCouverture.dejaInitialise = true;
	}
	protected RequeteSite utilisateurSiteInit() {
		if(!utilisateurSiteCouverture.dejaInitialise) {
			_utilisateurSite(utilisateurSiteCouverture);
			if(utilisateurSite == null)
				setUtilisateurSite(utilisateurSiteCouverture.o);
		}
		if(utilisateurSite != null)
			utilisateurSite.initLoinPourClasse(requeteSite_);
		utilisateurSiteCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	/////////////
	// xmlPile //
	/////////////

	/**	L'entité « xmlPile »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 */
	protected Stack<String> xmlPile = new Stack<String>();
	public Couverture<Stack<String>> xmlPileCouverture = new Couverture<Stack<String>>().p(this).c(Stack.class).var("xmlPile").o(xmlPile);

	/**	<br/>L'entité « xmlPile »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:xmlPile">Trouver l'entité xmlPile dans Solr</a>
	 * <br/>
	 * @param xmlPile est l'entité déjà construit. 
	 **/
	protected abstract void _xmlPile(Stack<String> o);

	public Stack<String> getXmlPile() {
		return xmlPile;
	}

	public void setXmlPile(Stack<String> xmlPile) {
		this.xmlPile = xmlPile;
		this.xmlPileCouverture.dejaInitialise = true;
	}
	protected RequeteSite xmlPileInit() {
		if(!xmlPileCouverture.dejaInitialise) {
			_xmlPile(xmlPile);
		}
		xmlPileCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	//////////////////
	// solrDocument //
	//////////////////

	/**	L'entité « solrDocument »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument solrDocument;
	public Couverture<SolrDocument> solrDocumentCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("solrDocument").o(solrDocument);

	/**	<br/>L'entité « solrDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrDocument">Trouver l'entité solrDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrDocument(Couverture<SolrDocument> c);

	public SolrDocument getSolrDocument() {
		return solrDocument;
	}

	public void setSolrDocument(SolrDocument solrDocument) {
		this.solrDocument = solrDocument;
		this.solrDocumentCouverture.dejaInitialise = true;
	}
	protected RequeteSite solrDocumentInit() {
		if(!solrDocumentCouverture.dejaInitialise) {
			_solrDocument(solrDocumentCouverture);
			if(solrDocument == null)
				setSolrDocument(solrDocumentCouverture.o);
		}
		solrDocumentCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	////////////////
	// pageAchete //
	////////////////

	/**	L'entité « pageAchete »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean pageAchete;
	public Couverture<Boolean> pageAcheteCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("pageAchete").o(pageAchete);

	/**	<br/>L'entité « pageAchete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageAchete">Trouver l'entité pageAchete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageAchete(Couverture<Boolean> c);

	public Boolean getPageAchete() {
		return pageAchete;
	}

	public void setPageAchete(Boolean pageAchete) {
		this.pageAchete = pageAchete;
		this.pageAcheteCouverture.dejaInitialise = true;
	}
	public RequeteSite setPageAchete(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.pageAchete = Boolean.parseBoolean(o);
		this.pageAcheteCouverture.dejaInitialise = true;
		return (RequeteSite)this;
	}
	protected RequeteSite pageAcheteInit() {
		if(!pageAcheteCouverture.dejaInitialise) {
			_pageAchete(pageAcheteCouverture);
			if(pageAchete == null)
				setPageAchete(pageAcheteCouverture.o);
		}
		pageAcheteCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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
	public Couverture<Boolean> pageAdminCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("pageAdmin").o(pageAdmin);

	/**	<br/>L'entité « pageAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageAdmin">Trouver l'entité pageAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageAdmin(Couverture<Boolean> c);

	public Boolean getPageAdmin() {
		return pageAdmin;
	}

	public void setPageAdmin(Boolean pageAdmin) {
		this.pageAdmin = pageAdmin;
		this.pageAdminCouverture.dejaInitialise = true;
	}
	public RequeteSite setPageAdmin(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.pageAdmin = Boolean.parseBoolean(o);
		this.pageAdminCouverture.dejaInitialise = true;
		return (RequeteSite)this;
	}
	protected RequeteSite pageAdminInit() {
		if(!pageAdminCouverture.dejaInitialise) {
			_pageAdmin(pageAdminCouverture);
			if(pageAdmin == null)
				setPageAdmin(pageAdminCouverture.o);
		}
		pageAdminCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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

	///////
	// h //
	///////

	/**	L'entité « h »
	 *	 is defined as null before being initialized. 
	 */
	protected String h;
	public Couverture<String> hCouverture = new Couverture<String>().p(this).c(String.class).var("h").o(h);

	/**	<br/>L'entité « h »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:h">Trouver l'entité h dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _h(Couverture<String> c);

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
		this.hCouverture.dejaInitialise = true;
	}
	protected RequeteSite hInit() {
		if(!hCouverture.dejaInitialise) {
			_h(hCouverture);
			if(h == null)
				setH(hCouverture.o);
		}
		hCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	public String solrH() {
		return h;
	}

	public String strH() {
		return h == null ? "" : h;
	}

	public String nomAffichageH() {
		return null;
	}

	public String htmTooltipH() {
		return null;
	}

	public String htmH() {
		return h == null ? "" : StringEscapeUtils.escapeHtml4(strH());
	}

	////////////////////////
	// chiffrementCrypter //
	////////////////////////

	/**	L'entité « chiffrementCrypter »
	 *	 is defined as null before being initialized. 
	 */
	protected Cipher chiffrementCrypter;
	public Couverture<Cipher> chiffrementCrypterCouverture = new Couverture<Cipher>().p(this).c(Cipher.class).var("chiffrementCrypter").o(chiffrementCrypter);

	/**	<br/>L'entité « chiffrementCrypter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:chiffrementCrypter">Trouver l'entité chiffrementCrypter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _chiffrementCrypter(Couverture<Cipher> c);

	public Cipher getChiffrementCrypter() {
		return chiffrementCrypter;
	}

	public void setChiffrementCrypter(Cipher chiffrementCrypter) {
		this.chiffrementCrypter = chiffrementCrypter;
		this.chiffrementCrypterCouverture.dejaInitialise = true;
	}
	protected RequeteSite chiffrementCrypterInit() {
		if(!chiffrementCrypterCouverture.dejaInitialise) {
			_chiffrementCrypter(chiffrementCrypterCouverture);
			if(chiffrementCrypter == null)
				setChiffrementCrypter(chiffrementCrypterCouverture.o);
		}
		chiffrementCrypterCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	//////////////////////////
	// chiffrementDecrypter //
	//////////////////////////

	/**	L'entité « chiffrementDecrypter »
	 *	 is defined as null before being initialized. 
	 */
	protected Cipher chiffrementDecrypter;
	public Couverture<Cipher> chiffrementDecrypterCouverture = new Couverture<Cipher>().p(this).c(Cipher.class).var("chiffrementDecrypter").o(chiffrementDecrypter);

	/**	<br/>L'entité « chiffrementDecrypter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:chiffrementDecrypter">Trouver l'entité chiffrementDecrypter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _chiffrementDecrypter(Couverture<Cipher> c);

	public Cipher getChiffrementDecrypter() {
		return chiffrementDecrypter;
	}

	public void setChiffrementDecrypter(Cipher chiffrementDecrypter) {
		this.chiffrementDecrypter = chiffrementDecrypter;
		this.chiffrementDecrypterCouverture.dejaInitialise = true;
	}
	protected RequeteSite chiffrementDecrypterInit() {
		if(!chiffrementDecrypterCouverture.dejaInitialise) {
			_chiffrementDecrypter(chiffrementDecrypterCouverture);
			if(chiffrementDecrypter == null)
				setChiffrementDecrypter(chiffrementDecrypterCouverture.o);
		}
		chiffrementDecrypterCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	///////////////////
	// digestMessage //
	///////////////////

	/**	L'entité « digestMessage »
	 *	 is defined as null before being initialized. 
	 */
	protected MessageDigest digestMessage;
	public Couverture<MessageDigest> digestMessageCouverture = new Couverture<MessageDigest>().p(this).c(MessageDigest.class).var("digestMessage").o(digestMessage);

	/**	<br/>L'entité « digestMessage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:digestMessage">Trouver l'entité digestMessage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _digestMessage(Couverture<MessageDigest> c);

	public MessageDigest getDigestMessage() {
		return digestMessage;
	}

	public void setDigestMessage(MessageDigest digestMessage) {
		this.digestMessage = digestMessage;
		this.digestMessageCouverture.dejaInitialise = true;
	}
	protected RequeteSite digestMessageInit() {
		if(!digestMessageCouverture.dejaInitialise) {
			_digestMessage(digestMessageCouverture);
			if(digestMessage == null)
				setDigestMessage(digestMessageCouverture.o);
		}
		digestMessageCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	////////////////
	// crypterSel //
	////////////////

	/**	L'entité « crypterSel »
	 *	 is defined as null before being initialized. 
	 */
	protected String crypterSel;
	public Couverture<String> crypterSelCouverture = new Couverture<String>().p(this).c(String.class).var("crypterSel").o(crypterSel);

	/**	<br/>L'entité « crypterSel »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:crypterSel">Trouver l'entité crypterSel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _crypterSel(Couverture<String> c);

	public String getCrypterSel() {
		return crypterSel;
	}

	public void setCrypterSel(String crypterSel) {
		this.crypterSel = crypterSel;
		this.crypterSelCouverture.dejaInitialise = true;
	}
	protected RequeteSite crypterSelInit() {
		if(!crypterSelCouverture.dejaInitialise) {
			_crypterSel(crypterSelCouverture);
			if(crypterSel == null)
				setCrypterSel(crypterSelCouverture.o);
		}
		crypterSelCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	public String solrCrypterSel() {
		return crypterSel;
	}

	public String strCrypterSel() {
		return crypterSel == null ? "" : crypterSel;
	}

	public String nomAffichageCrypterSel() {
		return null;
	}

	public String htmTooltipCrypterSel() {
		return null;
	}

	public String htmCrypterSel() {
		return crypterSel == null ? "" : StringEscapeUtils.escapeHtml4(strCrypterSel());
	}

	///////////////
	// requetePk //
	///////////////

	/**	L'entité « requetePk »
	 *	 is defined as null before being initialized. 
	 */
	protected Long requetePk;
	public Couverture<Long> requetePkCouverture = new Couverture<Long>().p(this).c(Long.class).var("requetePk").o(requetePk);

	/**	<br/>L'entité « requetePk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requetePk">Trouver l'entité requetePk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requetePk(Couverture<Long> c);

	public Long getRequetePk() {
		return requetePk;
	}

	public void setRequetePk(Long requetePk) {
		this.requetePk = requetePk;
		this.requetePkCouverture.dejaInitialise = true;
	}
	public RequeteSite setRequetePk(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.requetePk = Long.parseLong(o);
		this.requetePkCouverture.dejaInitialise = true;
		return (RequeteSite)this;
	}
	protected RequeteSite requetePkInit() {
		if(!requetePkCouverture.dejaInitialise) {
			_requetePk(requetePkCouverture);
			if(requetePk == null)
				setRequetePk(requetePkCouverture.o);
		}
		requetePkCouverture.dejaInitialise(true);
		return (RequeteSite)this;
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
	public Couverture<SQLConnection> connexionSqlCouverture = new Couverture<SQLConnection>().p(this).c(SQLConnection.class).var("connexionSql").o(connexionSql);

	/**	<br/>L'entité « connexionSql »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:connexionSql">Trouver l'entité connexionSql dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _connexionSql(Couverture<SQLConnection> c);

	public SQLConnection getConnexionSql() {
		return connexionSql;
	}

	public void setConnexionSql(SQLConnection connexionSql) {
		this.connexionSql = connexionSql;
		this.connexionSqlCouverture.dejaInitialise = true;
	}
	protected RequeteSite connexionSqlInit() {
		if(!connexionSqlCouverture.dejaInitialise) {
			_connexionSql(connexionSqlCouverture);
			if(connexionSql == null)
				setConnexionSql(connexionSqlCouverture.o);
		}
		connexionSqlCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	////////////////
	// crypterCle //
	////////////////

	/**	L'entité « crypterCle »
	 *	 is defined as null before being initialized. 
	 */
	protected byte[] crypterCle;
	public Couverture<byte[]> crypterCleCouverture = new Couverture<byte[]>().p(this).c(byte[].class).var("crypterCle").o(crypterCle);

	/**	<br/>L'entité « crypterCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:crypterCle">Trouver l'entité crypterCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _crypterCle(Couverture<byte[]> c);

	public byte[] getCrypterCle() {
		return crypterCle;
	}

	public void setCrypterCle(byte[] crypterCle) {
		this.crypterCle = crypterCle;
		this.crypterCleCouverture.dejaInitialise = true;
	}
	protected RequeteSite crypterCleInit() {
		if(!crypterCleCouverture.dejaInitialise) {
			_crypterCle(crypterCleCouverture);
			if(crypterCle == null)
				setCrypterCle(crypterCleCouverture.o);
		}
		crypterCleCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	///////////////////////
	// aleatoireSecurise //
	///////////////////////

	/**	L'entité « aleatoireSecurise »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SecureRandom(). 
	 */
	protected SecureRandom aleatoireSecurise = new SecureRandom();
	public Couverture<SecureRandom> aleatoireSecuriseCouverture = new Couverture<SecureRandom>().p(this).c(SecureRandom.class).var("aleatoireSecurise").o(aleatoireSecurise);

	/**	<br/>L'entité « aleatoireSecurise »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SecureRandom(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:aleatoireSecurise">Trouver l'entité aleatoireSecurise dans Solr</a>
	 * <br/>
	 * @param aleatoireSecurise est l'entité déjà construit. 
	 **/
	protected abstract void _aleatoireSecurise(SecureRandom o);

	public SecureRandom getAleatoireSecurise() {
		return aleatoireSecurise;
	}

	public void setAleatoireSecurise(SecureRandom aleatoireSecurise) {
		this.aleatoireSecurise = aleatoireSecurise;
		this.aleatoireSecuriseCouverture.dejaInitialise = true;
	}
	protected RequeteSite aleatoireSecuriseInit() {
		if(!aleatoireSecuriseCouverture.dejaInitialise) {
			_aleatoireSecurise(aleatoireSecurise);
		}
		aleatoireSecuriseCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	////////////////////
	// specCleSecrete //
	////////////////////

	/**	L'entité « specCleSecrete »
	 *	 is defined as null before being initialized. 
	 */
	protected SecretKeySpec specCleSecrete;
	public Couverture<SecretKeySpec> specCleSecreteCouverture = new Couverture<SecretKeySpec>().p(this).c(SecretKeySpec.class).var("specCleSecrete").o(specCleSecrete);

	/**	<br/>L'entité « specCleSecrete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.requete.RequeteSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:specCleSecrete">Trouver l'entité specCleSecrete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _specCleSecrete(Couverture<SecretKeySpec> c);

	public SecretKeySpec getSpecCleSecrete() {
		return specCleSecrete;
	}

	public void setSpecCleSecrete(SecretKeySpec specCleSecrete) {
		this.specCleSecrete = specCleSecrete;
		this.specCleSecreteCouverture.dejaInitialise = true;
	}
	protected RequeteSite specCleSecreteInit() {
		if(!specCleSecreteCouverture.dejaInitialise) {
			_specCleSecrete(specCleSecreteCouverture);
			if(specCleSecrete == null)
				setSpecCleSecrete(specCleSecreteCouverture.o);
		}
		specCleSecreteCouverture.dejaInitialise(true);
		return (RequeteSite)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseRequeteSite = false;

	public RequeteSite initLoinRequeteSite(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseRequeteSite) {
			dejaInitialiseRequeteSite = true;
			initLoinRequeteSite();
		}
		return (RequeteSite)this;
	}

	public void initLoinRequeteSite() {
		initRequeteSite();
	}

	public void initRequeteSite() {
		vertxInit();
		objetJsonInit();
		rechercheSolrInit();
		operationRequeteInit();
		reponseRechercheInit();
		resultatsRechercheInit();
		wInit();
		utilisateurVertxInit();
		principalJsonInit();
		utilisateurNomDomaineInit();
		utilisateurNomEnsembleInit();
		utilisateurIdInit();
		utilisateurNomInit();
		utilisateurNomFamilleInit();
		utilisateurPrenomInit();
		utilisateurNomCompletInit();
		utilisateurSiteInit();
		xmlPileInit();
		solrDocumentInit();
		pageAcheteInit();
		pageAdminInit();
		hInit();
		chiffrementCrypterInit();
		chiffrementDecrypterInit();
		digestMessageInit();
		crypterSelInit();
		requetePkInit();
		connexionSqlInit();
		crypterCleInit();
		aleatoireSecuriseInit();
		specCleSecreteInit();
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinRequeteSite(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteRequeteSite(RequeteSite requeteSite_) {
		w.setRequeteSite_(requeteSite_);
		utilisateurSite.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteRequeteSite(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirRequeteSite(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirRequeteSite(String var) throws Exception {
		RequeteSite oRequeteSite = (RequeteSite)this;
		switch(var) {
			case "siteContexte_":
				return oRequeteSite.siteContexte_;
			case "configSite_":
				return oRequeteSite.configSite_;
			case "requeteSite_":
				return oRequeteSite.requeteSite_;
			case "vertx":
				return oRequeteSite.vertx;
			case "objetJson":
				return oRequeteSite.objetJson;
			case "rechercheSolr":
				return oRequeteSite.rechercheSolr;
			case "operationRequete":
				return oRequeteSite.operationRequete;
			case "reponseRecherche":
				return oRequeteSite.reponseRecherche;
			case "resultatsRecherche":
				return oRequeteSite.resultatsRecherche;
			case "w":
				return oRequeteSite.w;
			case "utilisateurVertx":
				return oRequeteSite.utilisateurVertx;
			case "principalJson":
				return oRequeteSite.principalJson;
			case "utilisateurNomDomaine":
				return oRequeteSite.utilisateurNomDomaine;
			case "utilisateurNomEnsemble":
				return oRequeteSite.utilisateurNomEnsemble;
			case "utilisateurId":
				return oRequeteSite.utilisateurId;
			case "utilisateurNom":
				return oRequeteSite.utilisateurNom;
			case "utilisateurNomFamille":
				return oRequeteSite.utilisateurNomFamille;
			case "utilisateurPrenom":
				return oRequeteSite.utilisateurPrenom;
			case "utilisateurNomComplet":
				return oRequeteSite.utilisateurNomComplet;
			case "utilisateurSite":
				return oRequeteSite.utilisateurSite;
			case "xmlPile":
				return oRequeteSite.xmlPile;
			case "solrDocument":
				return oRequeteSite.solrDocument;
			case "pageAchete":
				return oRequeteSite.pageAchete;
			case "pageAdmin":
				return oRequeteSite.pageAdmin;
			case "h":
				return oRequeteSite.h;
			case "chiffrementCrypter":
				return oRequeteSite.chiffrementCrypter;
			case "chiffrementDecrypter":
				return oRequeteSite.chiffrementDecrypter;
			case "digestMessage":
				return oRequeteSite.digestMessage;
			case "crypterSel":
				return oRequeteSite.crypterSel;
			case "requetePk":
				return oRequeteSite.requetePk;
			case "connexionSql":
				return oRequeteSite.connexionSql;
			case "crypterCle":
				return oRequeteSite.crypterCle;
			case "aleatoireSecurise":
				return oRequeteSite.aleatoireSecurise;
			case "specCleSecrete":
				return oRequeteSite.specCleSecrete;
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
				o = attribuerRequeteSite(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerRequeteSite(String var, Object val) {
		RequeteSite oRequeteSite = (RequeteSite)this;
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
					o = definirRequeteSite(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRequeteSite(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(utilisateurNomDomaine, utilisateurNomEnsemble, utilisateurId, utilisateurNom, utilisateurNomFamille, utilisateurPrenom, utilisateurNomComplet, pageAchete, pageAdmin, h, crypterSel, requetePk);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof RequeteSite))
			return false;
		RequeteSite that = (RequeteSite)o;
		return Objects.equals( utilisateurNomDomaine, that.utilisateurNomDomaine )
				&& Objects.equals( utilisateurNomEnsemble, that.utilisateurNomEnsemble )
				&& Objects.equals( utilisateurId, that.utilisateurId )
				&& Objects.equals( utilisateurNom, that.utilisateurNom )
				&& Objects.equals( utilisateurNomFamille, that.utilisateurNomFamille )
				&& Objects.equals( utilisateurPrenom, that.utilisateurPrenom )
				&& Objects.equals( utilisateurNomComplet, that.utilisateurNomComplet )
				&& Objects.equals( pageAchete, that.pageAchete )
				&& Objects.equals( pageAdmin, that.pageAdmin )
				&& Objects.equals( h, that.h )
				&& Objects.equals( crypterSel, that.crypterSel )
				&& Objects.equals( requetePk, that.requetePk );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RequeteSite {");
		sb.append( "utilisateurNomDomaine: \"" ).append(utilisateurNomDomaine).append( "\"" );
		sb.append( ", utilisateurNomEnsemble: \"" ).append(utilisateurNomEnsemble).append( "\"" );
		sb.append( ", utilisateurId: \"" ).append(utilisateurId).append( "\"" );
		sb.append( ", utilisateurNom: \"" ).append(utilisateurNom).append( "\"" );
		sb.append( ", utilisateurNomFamille: \"" ).append(utilisateurNomFamille).append( "\"" );
		sb.append( ", utilisateurPrenom: \"" ).append(utilisateurPrenom).append( "\"" );
		sb.append( ", utilisateurNomComplet: \"" ).append(utilisateurNomComplet).append( "\"" );
		sb.append( ", pageAchete: " ).append(pageAchete);
		sb.append( ", pageAdmin: " ).append(pageAdmin);
		sb.append( ", h: \"" ).append(h).append( "\"" );
		sb.append( ", crypterSel: \"" ).append(crypterSel).append( "\"" );
		sb.append( ", requetePk: " ).append(requetePk);
		sb.append(" }");
		return sb.toString();
	}
}
