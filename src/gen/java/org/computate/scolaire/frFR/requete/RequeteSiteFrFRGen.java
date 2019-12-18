package org.computate.scolaire.frFR.requete;

import org.apache.solr.common.SolrDocumentList;
import java.security.MessageDigest;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import javax.crypto.spec.SecretKeySpec;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import java.text.NumberFormat;
import java.util.Stack;
import java.security.SecureRandom;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.config.ConfigSite;
import java.lang.Long;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import io.vertx.core.http.CaseInsensitiveHeaders;
import org.computate.scolaire.frFR.requete.patch.RequetePatch;
import java.math.MathContext;
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
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLConnection;
import java.lang.Object;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class RequeteSiteFrFRGen<DEV> extends Object {

	///////////////////
	// siteContexte_ //
	///////////////////

	/**	L'entité « siteContexte_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteContexteFrFR siteContexte_;
	@JsonIgnore
	public Couverture<SiteContexteFrFR> siteContexte_Couverture = new Couverture<SiteContexteFrFR>().p(this).c(SiteContexteFrFR.class).var("siteContexte_").o(siteContexte_);

	/**	<br/>L'entité « siteContexte_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte_">Trouver l'entité siteContexte_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteContexte_(Couverture<SiteContexteFrFR> c);

	public SiteContexteFrFR getSiteContexte_() {
		return siteContexte_;
	}

	public void setSiteContexte_(SiteContexteFrFR siteContexte_) {
		this.siteContexte_ = siteContexte_;
		this.siteContexte_Couverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR siteContexte_Init() {
		if(!siteContexte_Couverture.dejaInitialise) {
			_siteContexte_(siteContexte_Couverture);
			if(siteContexte_ == null)
				setSiteContexte_(siteContexte_Couverture.o);
		}
		siteContexte_Couverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	/////////////////
	// configSite_ //
	/////////////////

	/**	L'entité « configSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected ConfigSite configSite_;
	@JsonIgnore
	public Couverture<ConfigSite> configSite_Couverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite_").o(configSite_);

	/**	<br/>L'entité « configSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite_">Trouver l'entité configSite_ dans Solr</a>
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
	protected RequeteSiteFrFR configSite_Init() {
		if(!configSite_Couverture.dejaInitialise) {
			_configSite_(configSite_Couverture);
			if(configSite_ == null)
				setConfigSite_(configSite_Couverture.o);
		}
		configSite_Couverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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
	protected RequeteSiteFrFR requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////////
	// requetePatch_ //
	///////////////////

	/**	L'entité « requetePatch_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequetePatch requetePatch_;
	@JsonIgnore
	public Couverture<RequetePatch> requetePatch_Couverture = new Couverture<RequetePatch>().p(this).c(RequetePatch.class).var("requetePatch_").o(requetePatch_);

	/**	<br/>L'entité « requetePatch_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requetePatch_">Trouver l'entité requetePatch_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requetePatch_(Couverture<RequetePatch> c);

	public RequetePatch getRequetePatch_() {
		return requetePatch_;
	}

	public void setRequetePatch_(RequetePatch requetePatch_) {
		this.requetePatch_ = requetePatch_;
		this.requetePatch_Couverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR requetePatch_Init() {
		if(!requetePatch_Couverture.dejaInitialise) {
			_requetePatch_(requetePatch_Couverture);
			if(requetePatch_ == null)
				setRequetePatch_(requetePatch_Couverture.o);
		}
		requetePatch_Couverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vertx">Trouver l'entité vertx dans Solr</a>
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
	protected RequeteSiteFrFR vertxInit() {
		if(!vertxCouverture.dejaInitialise) {
			_vertx(vertxCouverture);
			if(vertx == null)
				setVertx(vertxCouverture.o);
		}
		vertxCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////
	// objetJson //
	///////////////

	/**	L'entité « objetJson »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject objetJson;
	@JsonIgnore
	public Couverture<JsonObject> objetJsonCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("objetJson").o(objetJson);

	/**	<br/>L'entité « objetJson »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetJson">Trouver l'entité objetJson dans Solr</a>
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
	protected RequeteSiteFrFR objetJsonInit() {
		if(!objetJsonCouverture.dejaInitialise) {
			_objetJson(objetJsonCouverture);
			if(objetJson == null)
				setObjetJson(objetJsonCouverture.o);
		}
		objetJsonCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////////
	// rechercheSolr //
	///////////////////

	/**	L'entité « rechercheSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrQuery rechercheSolr;
	@JsonIgnore
	public Couverture<SolrQuery> rechercheSolrCouverture = new Couverture<SolrQuery>().p(this).c(SolrQuery.class).var("rechercheSolr").o(rechercheSolr);

	/**	<br/>L'entité « rechercheSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:rechercheSolr">Trouver l'entité rechercheSolr dans Solr</a>
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
	protected RequeteSiteFrFR rechercheSolrInit() {
		if(!rechercheSolrCouverture.dejaInitialise) {
			_rechercheSolr(rechercheSolrCouverture);
			if(rechercheSolr == null)
				setRechercheSolr(rechercheSolrCouverture.o);
		}
		rechercheSolrCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	//////////////////////
	// operationRequete //
	//////////////////////

	/**	L'entité « operationRequete »
	 *	 is defined as null before being initialized. 
	 */
	protected OperationRequest operationRequete;
	@JsonIgnore
	public Couverture<OperationRequest> operationRequeteCouverture = new Couverture<OperationRequest>().p(this).c(OperationRequest.class).var("operationRequete").o(operationRequete);

	/**	<br/>L'entité « operationRequete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:operationRequete">Trouver l'entité operationRequete dans Solr</a>
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
	protected RequeteSiteFrFR operationRequeteInit() {
		if(!operationRequeteCouverture.dejaInitialise) {
			_operationRequete(operationRequeteCouverture);
			if(operationRequete == null)
				setOperationRequete(operationRequeteCouverture.o);
		}
		operationRequeteCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	//////////////////////
	// reponseRecherche //
	//////////////////////

	/**	L'entité « reponseRecherche »
	 *	 is defined as null before being initialized. 
	 */
	protected QueryResponse reponseRecherche;
	@JsonIgnore
	public Couverture<QueryResponse> reponseRechercheCouverture = new Couverture<QueryResponse>().p(this).c(QueryResponse.class).var("reponseRecherche").o(reponseRecherche);

	/**	<br/>L'entité « reponseRecherche »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:reponseRecherche">Trouver l'entité reponseRecherche dans Solr</a>
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
	protected RequeteSiteFrFR reponseRechercheInit() {
		if(!reponseRechercheCouverture.dejaInitialise) {
			_reponseRecherche(reponseRechercheCouverture);
			if(reponseRecherche == null)
				setReponseRecherche(reponseRechercheCouverture.o);
		}
		reponseRechercheCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	////////////////////////
	// resultatsRecherche //
	////////////////////////

	/**	L'entité « resultatsRecherche »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocumentList resultatsRecherche;
	@JsonIgnore
	public Couverture<SolrDocumentList> resultatsRechercheCouverture = new Couverture<SolrDocumentList>().p(this).c(SolrDocumentList.class).var("resultatsRecherche").o(resultatsRecherche);

	/**	<br/>L'entité « resultatsRecherche »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:resultatsRecherche">Trouver l'entité resultatsRecherche dans Solr</a>
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
	protected RequeteSiteFrFR resultatsRechercheInit() {
		if(!resultatsRechercheCouverture.dejaInitialise) {
			_resultatsRecherche(resultatsRechercheCouverture);
			if(resultatsRecherche == null)
				setResultatsRecherche(resultatsRechercheCouverture.o);
		}
		resultatsRechercheCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////
	// w //
	///////

	/**	L'entité « w »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain w;
	@JsonIgnore
	public Couverture<ToutEcrivain> wCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("w").o(w);

	/**	<br/>L'entité « w »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:w">Trouver l'entité w dans Solr</a>
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
	protected RequeteSiteFrFR wInit() {
		if(!wCouverture.dejaInitialise) {
			_w(wCouverture);
			if(w == null)
				setW(wCouverture.o);
		}
		if(w != null)
			w.initLoinPourClasse(requeteSite_);
		wCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	//////////////////////
	// utilisateurVertx //
	//////////////////////

	/**	L'entité « utilisateurVertx »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject utilisateurVertx;
	@JsonIgnore
	public Couverture<JsonObject> utilisateurVertxCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("utilisateurVertx").o(utilisateurVertx);

	/**	<br/>L'entité « utilisateurVertx »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurVertx">Trouver l'entité utilisateurVertx dans Solr</a>
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
	protected RequeteSiteFrFR utilisateurVertxInit() {
		if(!utilisateurVertxCouverture.dejaInitialise) {
			_utilisateurVertx(utilisateurVertxCouverture);
			if(utilisateurVertx == null)
				setUtilisateurVertx(utilisateurVertxCouverture.o);
		}
		utilisateurVertxCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////////
	// principalJson //
	///////////////////

	/**	L'entité « principalJson »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject principalJson;
	@JsonIgnore
	public Couverture<JsonObject> principalJsonCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("principalJson").o(principalJson);

	/**	<br/>L'entité « principalJson »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:principalJson">Trouver l'entité principalJson dans Solr</a>
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
	protected RequeteSiteFrFR principalJsonInit() {
		if(!principalJsonCouverture.dejaInitialise) {
			_principalJson(principalJsonCouverture);
			if(principalJson == null)
				setPrincipalJson(principalJsonCouverture.o);
		}
		principalJsonCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////////
	// utilisateurId //
	///////////////////

	/**	L'entité « utilisateurId »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurId;
	@JsonIgnore
	public Couverture<String> utilisateurIdCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurId").o(utilisateurId);

	/**	<br/>L'entité « utilisateurId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurId">Trouver l'entité utilisateurId dans Solr</a>
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
	protected RequeteSiteFrFR utilisateurIdInit() {
		if(!utilisateurIdCouverture.dejaInitialise) {
			_utilisateurId(utilisateurIdCouverture);
			if(utilisateurId == null)
				setUtilisateurId(utilisateurIdCouverture.o);
		}
		utilisateurIdCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public String solrUtilisateurId() {
		return utilisateurId;
	}

	public String strUtilisateurId() {
		return utilisateurId == null ? "" : utilisateurId;
	}

	public String jsonUtilisateurId() {
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
	@JsonIgnore
	public Couverture<String> utilisateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNom").o(utilisateurNom);

	/**	<br/>L'entité « utilisateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNom">Trouver l'entité utilisateurNom dans Solr</a>
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
	protected RequeteSiteFrFR utilisateurNomInit() {
		if(!utilisateurNomCouverture.dejaInitialise) {
			_utilisateurNom(utilisateurNomCouverture);
			if(utilisateurNom == null)
				setUtilisateurNom(utilisateurNomCouverture.o);
		}
		utilisateurNomCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public String solrUtilisateurNom() {
		return utilisateurNom;
	}

	public String strUtilisateurNom() {
		return utilisateurNom == null ? "" : utilisateurNom;
	}

	public String jsonUtilisateurNom() {
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
	@JsonIgnore
	public Couverture<String> utilisateurNomFamilleCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomFamille").o(utilisateurNomFamille);

	/**	<br/>L'entité « utilisateurNomFamille »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomFamille">Trouver l'entité utilisateurNomFamille dans Solr</a>
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
	protected RequeteSiteFrFR utilisateurNomFamilleInit() {
		if(!utilisateurNomFamilleCouverture.dejaInitialise) {
			_utilisateurNomFamille(utilisateurNomFamilleCouverture);
			if(utilisateurNomFamille == null)
				setUtilisateurNomFamille(utilisateurNomFamilleCouverture.o);
		}
		utilisateurNomFamilleCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public String solrUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public String strUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : utilisateurNomFamille;
	}

	public String jsonUtilisateurNomFamille() {
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
	@JsonIgnore
	public Couverture<String> utilisateurPrenomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurPrenom").o(utilisateurPrenom);

	/**	<br/>L'entité « utilisateurPrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurPrenom">Trouver l'entité utilisateurPrenom dans Solr</a>
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
	protected RequeteSiteFrFR utilisateurPrenomInit() {
		if(!utilisateurPrenomCouverture.dejaInitialise) {
			_utilisateurPrenom(utilisateurPrenomCouverture);
			if(utilisateurPrenom == null)
				setUtilisateurPrenom(utilisateurPrenomCouverture.o);
		}
		utilisateurPrenomCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public String solrUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public String strUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : utilisateurPrenom;
	}

	public String jsonUtilisateurPrenom() {
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
	@JsonIgnore
	public Couverture<String> utilisateurNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomComplet").o(utilisateurNomComplet);

	/**	<br/>L'entité « utilisateurNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomComplet">Trouver l'entité utilisateurNomComplet dans Solr</a>
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
	protected RequeteSiteFrFR utilisateurNomCompletInit() {
		if(!utilisateurNomCompletCouverture.dejaInitialise) {
			_utilisateurNomComplet(utilisateurNomCompletCouverture);
			if(utilisateurNomComplet == null)
				setUtilisateurNomComplet(utilisateurNomCompletCouverture.o);
		}
		utilisateurNomCompletCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public String solrUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public String strUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : utilisateurNomComplet;
	}

	public String jsonUtilisateurNomComplet() {
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
	@JsonIgnore
	public Couverture<List<String>> utilisateurRolesRoyaumeCouverture = new Couverture<List<String>>().p(this).c(List.class).var("utilisateurRolesRoyaume").o(utilisateurRolesRoyaume);

	/**	<br/>L'entité « utilisateurRolesRoyaume »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurRolesRoyaume">Trouver l'entité utilisateurRolesRoyaume dans Solr</a>
	 * <br/>
	 * @param utilisateurRolesRoyaume est l'entité déjà construit. 
	 **/
	protected abstract void _utilisateurRolesRoyaume(List<String> o);

	public List<String> getUtilisateurRolesRoyaume() {
		return utilisateurRolesRoyaume;
	}

	public void setUtilisateurRolesRoyaume(List<String> utilisateurRolesRoyaume) {
		this.utilisateurRolesRoyaume = utilisateurRolesRoyaume;
		this.utilisateurRolesRoyaumeCouverture.dejaInitialise = true;
	}
	public RequeteSiteFrFR addUtilisateurRolesRoyaume(String...objets) {
		for(String o : objets) {
			addUtilisateurRolesRoyaume(o);
		}
		return (RequeteSiteFrFR)this;
	}
	public RequeteSiteFrFR addUtilisateurRolesRoyaume(String o) {
		if(o != null && !utilisateurRolesRoyaume.contains(o))
			this.utilisateurRolesRoyaume.add(o);
		return (RequeteSiteFrFR)this;
	}
	public RequeteSiteFrFR setUtilisateurRolesRoyaume(JsonArray objets) {
		utilisateurRolesRoyaume.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addUtilisateurRolesRoyaume(o);
		}
		return (RequeteSiteFrFR)this;
	}
	protected RequeteSiteFrFR utilisateurRolesRoyaumeInit() {
		if(!utilisateurRolesRoyaumeCouverture.dejaInitialise) {
			_utilisateurRolesRoyaume(utilisateurRolesRoyaume);
		}
		utilisateurRolesRoyaumeCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public List<String> solrUtilisateurRolesRoyaume() {
		return utilisateurRolesRoyaume;
	}

	public String strUtilisateurRolesRoyaume() {
		return utilisateurRolesRoyaume == null ? "" : utilisateurRolesRoyaume.toString();
	}

	public String jsonUtilisateurRolesRoyaume() {
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
	@JsonIgnore
	public Couverture<JsonObject> utilisateurRessourceCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("utilisateurRessource").o(utilisateurRessource);

	/**	<br/>L'entité « utilisateurRessource »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurRessource">Trouver l'entité utilisateurRessource dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurRessource(Couverture<JsonObject> c);

	public JsonObject getUtilisateurRessource() {
		return utilisateurRessource;
	}

	public void setUtilisateurRessource(JsonObject utilisateurRessource) {
		this.utilisateurRessource = utilisateurRessource;
		this.utilisateurRessourceCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR utilisateurRessourceInit() {
		if(!utilisateurRessourceCouverture.dejaInitialise) {
			_utilisateurRessource(utilisateurRessourceCouverture);
			if(utilisateurRessource == null)
				setUtilisateurRessource(utilisateurRessourceCouverture.o);
		}
		utilisateurRessourceCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////////////////////
	// utilisateurRolesRessource //
	///////////////////////////////

	/**	L'entité « utilisateurRolesRessource »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	protected List<String> utilisateurRolesRessource = new java.util.ArrayList<java.lang.String>();
	@JsonIgnore
	public Couverture<List<String>> utilisateurRolesRessourceCouverture = new Couverture<List<String>>().p(this).c(List.class).var("utilisateurRolesRessource").o(utilisateurRolesRessource);

	/**	<br/>L'entité « utilisateurRolesRessource »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurRolesRessource">Trouver l'entité utilisateurRolesRessource dans Solr</a>
	 * <br/>
	 * @param utilisateurRolesRessource est l'entité déjà construit. 
	 **/
	protected abstract void _utilisateurRolesRessource(List<String> o);

	public List<String> getUtilisateurRolesRessource() {
		return utilisateurRolesRessource;
	}

	public void setUtilisateurRolesRessource(List<String> utilisateurRolesRessource) {
		this.utilisateurRolesRessource = utilisateurRolesRessource;
		this.utilisateurRolesRessourceCouverture.dejaInitialise = true;
	}
	public RequeteSiteFrFR addUtilisateurRolesRessource(String...objets) {
		for(String o : objets) {
			addUtilisateurRolesRessource(o);
		}
		return (RequeteSiteFrFR)this;
	}
	public RequeteSiteFrFR addUtilisateurRolesRessource(String o) {
		if(o != null && !utilisateurRolesRessource.contains(o))
			this.utilisateurRolesRessource.add(o);
		return (RequeteSiteFrFR)this;
	}
	public RequeteSiteFrFR setUtilisateurRolesRessource(JsonArray objets) {
		utilisateurRolesRessource.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addUtilisateurRolesRessource(o);
		}
		return (RequeteSiteFrFR)this;
	}
	protected RequeteSiteFrFR utilisateurRolesRessourceInit() {
		if(!utilisateurRolesRessourceCouverture.dejaInitialise) {
			_utilisateurRolesRessource(utilisateurRolesRessource);
		}
		utilisateurRolesRessourceCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public List<String> solrUtilisateurRolesRessource() {
		return utilisateurRolesRessource;
	}

	public String strUtilisateurRolesRessource() {
		return utilisateurRolesRessource == null ? "" : utilisateurRolesRessource.toString();
	}

	public String jsonUtilisateurRolesRessource() {
		return utilisateurRolesRessource == null ? "" : utilisateurRolesRessource.toString();
	}

	public String nomAffichageUtilisateurRolesRessource() {
		return null;
	}

	public String htmTooltipUtilisateurRolesRessource() {
		return null;
	}

	public String htmUtilisateurRolesRessource() {
		return utilisateurRolesRessource == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurRolesRessource());
	}

	/////////////////////
	// utilisateurSite //
	/////////////////////

	/**	L'entité « utilisateurSite »
	 *	 is defined as null before being initialized. 
	 */
	protected UtilisateurSite utilisateurSite;
	@JsonIgnore
	public Couverture<UtilisateurSite> utilisateurSiteCouverture = new Couverture<UtilisateurSite>().p(this).c(UtilisateurSite.class).var("utilisateurSite").o(utilisateurSite);

	/**	<br/>L'entité « utilisateurSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurSite">Trouver l'entité utilisateurSite dans Solr</a>
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
	protected RequeteSiteFrFR utilisateurSiteInit() {
		if(!utilisateurSiteCouverture.dejaInitialise) {
			_utilisateurSite(utilisateurSiteCouverture);
			if(utilisateurSite == null)
				setUtilisateurSite(utilisateurSiteCouverture.o);
		}
		if(utilisateurSite != null)
			utilisateurSite.initLoinPourClasse(requeteSite_);
		utilisateurSiteCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	/////////////
	// xmlPile //
	/////////////

	/**	L'entité « xmlPile »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 */
	protected Stack<String> xmlPile = new Stack<String>();
	@JsonIgnore
	public Couverture<Stack<String>> xmlPileCouverture = new Couverture<Stack<String>>().p(this).c(Stack.class).var("xmlPile").o(xmlPile);

	/**	<br/>L'entité « xmlPile »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:xmlPile">Trouver l'entité xmlPile dans Solr</a>
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
	protected RequeteSiteFrFR xmlPileInit() {
		if(!xmlPileCouverture.dejaInitialise) {
			_xmlPile(xmlPile);
		}
		xmlPileCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	//////////////////
	// documentSolr //
	//////////////////

	/**	L'entité « documentSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument documentSolr;
	@JsonIgnore
	public Couverture<SolrDocument> documentSolrCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("documentSolr").o(documentSolr);

	/**	<br/>L'entité « documentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:documentSolr">Trouver l'entité documentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _documentSolr(Couverture<SolrDocument> c);

	public SolrDocument getDocumentSolr() {
		return documentSolr;
	}

	public void setDocumentSolr(SolrDocument documentSolr) {
		this.documentSolr = documentSolr;
		this.documentSolrCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR documentSolrInit() {
		if(!documentSolrCouverture.dejaInitialise) {
			_documentSolr(documentSolrCouverture);
			if(documentSolr == null)
				setDocumentSolr(documentSolrCouverture.o);
		}
		documentSolrCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////
	// pageAdmin //
	///////////////

	/**	L'entité « pageAdmin »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean pageAdmin;
	@JsonIgnore
	public Couverture<Boolean> pageAdminCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("pageAdmin").o(pageAdmin);

	/**	<br/>L'entité « pageAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageAdmin">Trouver l'entité pageAdmin dans Solr</a>
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
	public RequeteSiteFrFR setPageAdmin(String o) {
		this.pageAdmin = Boolean.parseBoolean(o);
		this.pageAdminCouverture.dejaInitialise = true;
		return (RequeteSiteFrFR)this;
	}
	protected RequeteSiteFrFR pageAdminInit() {
		if(!pageAdminCouverture.dejaInitialise) {
			_pageAdmin(pageAdminCouverture);
			if(pageAdmin == null)
				setPageAdmin(pageAdminCouverture.o);
		}
		pageAdminCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public Boolean solrPageAdmin() {
		return pageAdmin;
	}

	public String strPageAdmin() {
		return pageAdmin == null ? "" : pageAdmin.toString();
	}

	public String jsonPageAdmin() {
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
	@JsonIgnore
	public Couverture<Long> requetePkCouverture = new Couverture<Long>().p(this).c(Long.class).var("requetePk").o(requetePk);

	/**	<br/>L'entité « requetePk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requetePk">Trouver l'entité requetePk dans Solr</a>
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
	public RequeteSiteFrFR setRequetePk(String o) {
		if(NumberUtils.isParsable(o))
			this.requetePk = Long.parseLong(o);
		this.requetePkCouverture.dejaInitialise = true;
		return (RequeteSiteFrFR)this;
	}
	protected RequeteSiteFrFR requetePkInit() {
		if(!requetePkCouverture.dejaInitialise) {
			_requetePk(requetePkCouverture);
			if(requetePk == null)
				setRequetePk(requetePkCouverture.o);
		}
		requetePkCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public Long solrRequetePk() {
		return requetePk;
	}

	public String strRequetePk() {
		return requetePk == null ? "" : requetePk.toString();
	}

	public String jsonRequetePk() {
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
	@JsonIgnore
	public Couverture<SQLConnection> connexionSqlCouverture = new Couverture<SQLConnection>().p(this).c(SQLConnection.class).var("connexionSql").o(connexionSql);

	/**	<br/>L'entité « connexionSql »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:connexionSql">Trouver l'entité connexionSql dans Solr</a>
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
	protected RequeteSiteFrFR connexionSqlInit() {
		if(!connexionSqlCouverture.dejaInitialise) {
			_connexionSql(connexionSqlCouverture);
			if(connexionSql == null)
				setConnexionSql(connexionSqlCouverture.o);
		}
		connexionSqlCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	////////////////////
	// requeteEnTetes //
	////////////////////

	/**	L'entité « requeteEnTetes »
	 *	 is defined as null before being initialized. 
	 */
	protected CaseInsensitiveHeaders requeteEnTetes;
	@JsonIgnore
	public Couverture<CaseInsensitiveHeaders> requeteEnTetesCouverture = new Couverture<CaseInsensitiveHeaders>().p(this).c(CaseInsensitiveHeaders.class).var("requeteEnTetes").o(requeteEnTetes);

	/**	<br/>L'entité « requeteEnTetes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteEnTetes">Trouver l'entité requeteEnTetes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteEnTetes(Couverture<CaseInsensitiveHeaders> c);

	public CaseInsensitiveHeaders getRequeteEnTetes() {
		return requeteEnTetes;
	}

	public void setRequeteEnTetes(CaseInsensitiveHeaders requeteEnTetes) {
		this.requeteEnTetes = requeteEnTetes;
		this.requeteEnTetesCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR requeteEnTetesInit() {
		if(!requeteEnTetesCouverture.dejaInitialise) {
			_requeteEnTetes(requeteEnTetesCouverture);
			if(requeteEnTetes == null)
				setRequeteEnTetes(requeteEnTetesCouverture.o);
		}
		requeteEnTetesCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	////////////////////////
	// cryptageMotDePasse //
	////////////////////////

	/**	L'entité « cryptageMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	protected String cryptageMotDePasse;
	@JsonIgnore
	public Couverture<String> cryptageMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("cryptageMotDePasse").o(cryptageMotDePasse);

	/**	<br/>L'entité « cryptageMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageMotDePasse">Trouver l'entité cryptageMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageMotDePasse(Couverture<String> c);

	public String getCryptageMotDePasse() {
		return cryptageMotDePasse;
	}

	public void setCryptageMotDePasse(String cryptageMotDePasse) {
		this.cryptageMotDePasse = cryptageMotDePasse;
		this.cryptageMotDePasseCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR cryptageMotDePasseInit() {
		if(!cryptageMotDePasseCouverture.dejaInitialise) {
			_cryptageMotDePasse(cryptageMotDePasseCouverture);
			if(cryptageMotDePasse == null)
				setCryptageMotDePasse(cryptageMotDePasseCouverture.o);
		}
		cryptageMotDePasseCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	public String solrCryptageMotDePasse() {
		return cryptageMotDePasse;
	}

	public String strCryptageMotDePasse() {
		return cryptageMotDePasse == null ? "" : cryptageMotDePasse;
	}

	public String jsonCryptageMotDePasse() {
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
	@JsonIgnore
	public Couverture<Cipher> cryptageChiffreCrypterCouverture = new Couverture<Cipher>().p(this).c(Cipher.class).var("cryptageChiffreCrypter").o(cryptageChiffreCrypter);

	/**	<br/>L'entité « cryptageChiffreCrypter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageChiffreCrypter">Trouver l'entité cryptageChiffreCrypter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageChiffreCrypter(Couverture<Cipher> c);

	public Cipher getCryptageChiffreCrypter() {
		return cryptageChiffreCrypter;
	}

	public void setCryptageChiffreCrypter(Cipher cryptageChiffreCrypter) {
		this.cryptageChiffreCrypter = cryptageChiffreCrypter;
		this.cryptageChiffreCrypterCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR cryptageChiffreCrypterInit() {
		if(!cryptageChiffreCrypterCouverture.dejaInitialise) {
			_cryptageChiffreCrypter(cryptageChiffreCrypterCouverture);
			if(cryptageChiffreCrypter == null)
				setCryptageChiffreCrypter(cryptageChiffreCrypterCouverture.o);
		}
		cryptageChiffreCrypterCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	//////////////////////////////
	// cryptageChiffreDecrypter //
	//////////////////////////////

	/**	L'entité « cryptageChiffreDecrypter »
	 *	 is defined as null before being initialized. 
	 */
	protected Cipher cryptageChiffreDecrypter;
	@JsonIgnore
	public Couverture<Cipher> cryptageChiffreDecrypterCouverture = new Couverture<Cipher>().p(this).c(Cipher.class).var("cryptageChiffreDecrypter").o(cryptageChiffreDecrypter);

	/**	<br/>L'entité « cryptageChiffreDecrypter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageChiffreDecrypter">Trouver l'entité cryptageChiffreDecrypter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageChiffreDecrypter(Couverture<Cipher> c);

	public Cipher getCryptageChiffreDecrypter() {
		return cryptageChiffreDecrypter;
	}

	public void setCryptageChiffreDecrypter(Cipher cryptageChiffreDecrypter) {
		this.cryptageChiffreDecrypter = cryptageChiffreDecrypter;
		this.cryptageChiffreDecrypterCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR cryptageChiffreDecrypterInit() {
		if(!cryptageChiffreDecrypterCouverture.dejaInitialise) {
			_cryptageChiffreDecrypter(cryptageChiffreDecrypterCouverture);
			if(cryptageChiffreDecrypter == null)
				setCryptageChiffreDecrypter(cryptageChiffreDecrypterCouverture.o);
		}
		cryptageChiffreDecrypterCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////////////////
	// cryptageDigestMessage //
	///////////////////////////

	/**	L'entité « cryptageDigestMessage »
	 *	 is defined as null before being initialized. 
	 */
	protected MessageDigest cryptageDigestMessage;
	@JsonIgnore
	public Couverture<MessageDigest> cryptageDigestMessageCouverture = new Couverture<MessageDigest>().p(this).c(MessageDigest.class).var("cryptageDigestMessage").o(cryptageDigestMessage);

	/**	<br/>L'entité « cryptageDigestMessage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageDigestMessage">Trouver l'entité cryptageDigestMessage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageDigestMessage(Couverture<MessageDigest> c);

	public MessageDigest getCryptageDigestMessage() {
		return cryptageDigestMessage;
	}

	public void setCryptageDigestMessage(MessageDigest cryptageDigestMessage) {
		this.cryptageDigestMessage = cryptageDigestMessage;
		this.cryptageDigestMessageCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR cryptageDigestMessageInit() {
		if(!cryptageDigestMessageCouverture.dejaInitialise) {
			_cryptageDigestMessage(cryptageDigestMessageCouverture);
			if(cryptageDigestMessage == null)
				setCryptageDigestMessage(cryptageDigestMessageCouverture.o);
		}
		cryptageDigestMessageCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	/////////////////
	// cryptageCle //
	/////////////////

	/**	L'entité « cryptageCle »
	 *	 is defined as null before being initialized. 
	 */
	protected byte[] cryptageCle;
	@JsonIgnore
	public Couverture<byte[]> cryptageCleCouverture = new Couverture<byte[]>().p(this).c(byte[].class).var("cryptageCle").o(cryptageCle);

	/**	<br/>L'entité « cryptageCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageCle">Trouver l'entité cryptageCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageCle(Couverture<byte[]> c);

	public byte[] getCryptageCle() {
		return cryptageCle;
	}

	public void setCryptageCle(byte[] cryptageCle) {
		this.cryptageCle = cryptageCle;
		this.cryptageCleCouverture.dejaInitialise = true;
	}
	protected RequeteSiteFrFR cryptageCleInit() {
		if(!cryptageCleCouverture.dejaInitialise) {
			_cryptageCle(cryptageCleCouverture);
			if(cryptageCle == null)
				setCryptageCle(cryptageCleCouverture.o);
		}
		cryptageCleCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	///////////////////////
	// aleatoireSecurise //
	///////////////////////

	/**	L'entité « aleatoireSecurise »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SecureRandom(). 
	 */
	protected SecureRandom aleatoireSecurise = new SecureRandom();
	@JsonIgnore
	public Couverture<SecureRandom> aleatoireSecuriseCouverture = new Couverture<SecureRandom>().p(this).c(SecureRandom.class).var("aleatoireSecurise").o(aleatoireSecurise);

	/**	<br/>L'entité « aleatoireSecurise »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SecureRandom(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:aleatoireSecurise">Trouver l'entité aleatoireSecurise dans Solr</a>
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
	protected RequeteSiteFrFR aleatoireSecuriseInit() {
		if(!aleatoireSecuriseCouverture.dejaInitialise) {
			_aleatoireSecurise(aleatoireSecurise);
		}
		aleatoireSecuriseCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	////////////////////
	// specCleSecrete //
	////////////////////

	/**	L'entité « specCleSecrete »
	 *	 is defined as null before being initialized. 
	 */
	protected SecretKeySpec specCleSecrete;
	@JsonIgnore
	public Couverture<SecretKeySpec> specCleSecreteCouverture = new Couverture<SecretKeySpec>().p(this).c(SecretKeySpec.class).var("specCleSecrete").o(specCleSecrete);

	/**	<br/>L'entité « specCleSecrete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.RequeteSiteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:specCleSecrete">Trouver l'entité specCleSecrete dans Solr</a>
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
	protected RequeteSiteFrFR specCleSecreteInit() {
		if(!specCleSecreteCouverture.dejaInitialise) {
			_specCleSecrete(specCleSecreteCouverture);
			if(specCleSecrete == null)
				setSpecCleSecrete(specCleSecreteCouverture.o);
		}
		specCleSecreteCouverture.dejaInitialise(true);
		return (RequeteSiteFrFR)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseRequeteSiteFrFR = false;

	public RequeteSiteFrFR initLoinRequeteSiteFrFR(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseRequeteSiteFrFR) {
			dejaInitialiseRequeteSiteFrFR = true;
			initLoinRequeteSiteFrFR();
		}
		return (RequeteSiteFrFR)this;
	}

	public void initLoinRequeteSiteFrFR() {
		initRequeteSiteFrFR();
	}

	public void initRequeteSiteFrFR() {
		siteContexte_Init();
		configSite_Init();
		requeteSite_Init();
		requetePatch_Init();
		vertxInit();
		objetJsonInit();
		rechercheSolrInit();
		operationRequeteInit();
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
		utilisateurRolesRessourceInit();
		utilisateurSiteInit();
		xmlPileInit();
		documentSolrInit();
		pageAdminInit();
		requetePkInit();
		connexionSqlInit();
		requeteEnTetesInit();
		cryptageMotDePasseInit();
		cryptageChiffreCrypterInit();
		cryptageChiffreDecrypterInit();
		cryptageDigestMessageInit();
		cryptageCleInit();
		aleatoireSecuriseInit();
		specCleSecreteInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinRequeteSiteFrFR(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteRequeteSiteFrFR(RequeteSiteFrFR requeteSite_) {
		if(w != null)
			w.setRequeteSite_(requeteSite_);
		if(utilisateurSite != null)
			utilisateurSite.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteRequeteSiteFrFR(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirRequeteSiteFrFR(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirRequeteSiteFrFR(String var) {
		RequeteSiteFrFR oRequeteSiteFrFR = (RequeteSiteFrFR)this;
		switch(var) {
			case "siteContexte_":
				return oRequeteSiteFrFR.siteContexte_;
			case "configSite_":
				return oRequeteSiteFrFR.configSite_;
			case "requeteSite_":
				return oRequeteSiteFrFR.requeteSite_;
			case "requetePatch_":
				return oRequeteSiteFrFR.requetePatch_;
			case "vertx":
				return oRequeteSiteFrFR.vertx;
			case "objetJson":
				return oRequeteSiteFrFR.objetJson;
			case "rechercheSolr":
				return oRequeteSiteFrFR.rechercheSolr;
			case "operationRequete":
				return oRequeteSiteFrFR.operationRequete;
			case "reponseRecherche":
				return oRequeteSiteFrFR.reponseRecherche;
			case "resultatsRecherche":
				return oRequeteSiteFrFR.resultatsRecherche;
			case "w":
				return oRequeteSiteFrFR.w;
			case "utilisateurVertx":
				return oRequeteSiteFrFR.utilisateurVertx;
			case "principalJson":
				return oRequeteSiteFrFR.principalJson;
			case "utilisateurId":
				return oRequeteSiteFrFR.utilisateurId;
			case "utilisateurNom":
				return oRequeteSiteFrFR.utilisateurNom;
			case "utilisateurNomFamille":
				return oRequeteSiteFrFR.utilisateurNomFamille;
			case "utilisateurPrenom":
				return oRequeteSiteFrFR.utilisateurPrenom;
			case "utilisateurNomComplet":
				return oRequeteSiteFrFR.utilisateurNomComplet;
			case "utilisateurRolesRoyaume":
				return oRequeteSiteFrFR.utilisateurRolesRoyaume;
			case "utilisateurRessource":
				return oRequeteSiteFrFR.utilisateurRessource;
			case "utilisateurRolesRessource":
				return oRequeteSiteFrFR.utilisateurRolesRessource;
			case "utilisateurSite":
				return oRequeteSiteFrFR.utilisateurSite;
			case "xmlPile":
				return oRequeteSiteFrFR.xmlPile;
			case "documentSolr":
				return oRequeteSiteFrFR.documentSolr;
			case "pageAdmin":
				return oRequeteSiteFrFR.pageAdmin;
			case "requetePk":
				return oRequeteSiteFrFR.requetePk;
			case "connexionSql":
				return oRequeteSiteFrFR.connexionSql;
			case "requeteEnTetes":
				return oRequeteSiteFrFR.requeteEnTetes;
			case "cryptageMotDePasse":
				return oRequeteSiteFrFR.cryptageMotDePasse;
			case "cryptageChiffreCrypter":
				return oRequeteSiteFrFR.cryptageChiffreCrypter;
			case "cryptageChiffreDecrypter":
				return oRequeteSiteFrFR.cryptageChiffreDecrypter;
			case "cryptageDigestMessage":
				return oRequeteSiteFrFR.cryptageDigestMessage;
			case "cryptageCle":
				return oRequeteSiteFrFR.cryptageCle;
			case "aleatoireSecurise":
				return oRequeteSiteFrFR.aleatoireSecurise;
			case "specCleSecrete":
				return oRequeteSiteFrFR.specCleSecrete;
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
				o = attribuerRequeteSiteFrFR(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerRequeteSiteFrFR(String var, Object val) {
		RequeteSiteFrFR oRequeteSiteFrFR = (RequeteSiteFrFR)this;
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
					o = definirRequeteSiteFrFR(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRequeteSiteFrFR(String var, String val) {
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
		if(!(o instanceof RequeteSiteFrFR))
			return false;
		RequeteSiteFrFR that = (RequeteSiteFrFR)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RequeteSiteFrFR { ");
		sb.append(" }");
		return sb.toString();
	}
}
