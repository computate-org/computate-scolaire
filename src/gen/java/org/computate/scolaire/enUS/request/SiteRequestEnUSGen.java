package org.computate.scolaire.enUS.request;

import org.apache.solr.common.SolrDocumentList;
import java.security.MessageDigest;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import javax.crypto.spec.SecretKeySpec;
import org.computate.enUS.school.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import java.util.Stack;
import java.security.SecureRandom;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import java.lang.Long;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
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

	//////////////////
	// siteContext_ //
	//////////////////

	/**	L'entité « siteContext_ »
The site context with global site information. 
	 *	 is defined as null before being initialized. 
	 */
	protected SiteContextEnUS siteContext_;
	public Wrap<SiteContextEnUS> siteContext_Wrap = new Wrap<SiteContextEnUS>().p(this).c(SiteContextEnUS.class).var("siteContext_").o(siteContext_);

	/**	<br/>L'entité « siteContext_ »
The site context with global site information. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteContext_">Trouver l'entité siteContext_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteContext_(Wrap<SiteContextEnUS> c);

	public SiteContextEnUS getSiteContext_() {
		return siteContext_;
	}

	public void setSiteContext_(SiteContextEnUS siteContext_) {
		this.siteContext_ = siteContext_;
		this.siteContext_Wrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS siteContext_Init() {
		if(!siteContext_Wrap.alreadyInitialized) {
			_siteContext_(siteContext_Wrap);
			if(siteContext_ == null)
				setSiteContext_(siteContext_Wrap.o);
		}
		siteContext_Wrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	/////////////////
	// siteConfig_ //
	/////////////////

	/**	L'entité « siteConfig_ »
The site configuration. 
	 *	 is defined as null before being initialized. 
	 */
	protected SiteConfig siteConfig_;
	public Wrap<SiteConfig> siteConfig_Wrap = new Wrap<SiteConfig>().p(this).c(SiteConfig.class).var("siteConfig_").o(siteConfig_);

	/**	<br/>L'entité « siteConfig_ »
The site configuration. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteConfig_">Trouver l'entité siteConfig_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteConfig_(Wrap<SiteConfig> c);

	public SiteConfig getSiteConfig_() {
		return siteConfig_;
	}

	public void setSiteConfig_(SiteConfig siteConfig_) {
		this.siteConfig_ = siteConfig_;
		this.siteConfig_Wrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS siteConfig_Init() {
		if(!siteConfig_Wrap.alreadyInitialized) {
			_siteConfig_(siteConfig_Wrap);
			if(siteConfig_ == null)
				setSiteConfig_(siteConfig_Wrap.o);
		}
		siteConfig_Wrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	L'entité « siteRequest_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteRequestEnUS siteRequest_;
	public Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("siteRequest_").o(siteRequest_);

	/**	<br/>L'entité « siteRequest_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequestEnUS> c);

	public SiteRequestEnUS getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequestEnUS siteRequest_) {
		this.siteRequest_ = siteRequest_;
		this.siteRequest_Wrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
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

	////////////////
	// jsonObject //
	////////////////

	/**	L'entité « jsonObject »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject jsonObject;
	public Wrap<JsonObject> jsonObjectWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("jsonObject").o(jsonObject);

	/**	<br/>L'entité « jsonObject »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jsonObject">Trouver l'entité jsonObject dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jsonObject(Wrap<JsonObject> c);

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
		this.jsonObjectWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS jsonObjectInit() {
		if(!jsonObjectWrap.alreadyInitialized) {
			_jsonObject(jsonObjectWrap);
			if(jsonObject == null)
				setJsonObject(jsonObjectWrap.o);
		}
		jsonObjectWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////
	// solrQuery //
	///////////////

	/**	L'entité « solrQuery »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrQuery solrQuery;
	public Wrap<SolrQuery> solrQueryWrap = new Wrap<SolrQuery>().p(this).c(SolrQuery.class).var("solrQuery").o(solrQuery);

	/**	<br/>L'entité « solrQuery »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrQuery">Trouver l'entité solrQuery dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrQuery(Wrap<SolrQuery> c);

	public SolrQuery getSolrQuery() {
		return solrQuery;
	}

	public void setSolrQuery(SolrQuery solrQuery) {
		this.solrQuery = solrQuery;
		this.solrQueryWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS solrQueryInit() {
		if(!solrQueryWrap.alreadyInitialized) {
			_solrQuery(solrQueryWrap);
			if(solrQuery == null)
				setSolrQuery(solrQueryWrap.o);
		}
		solrQueryWrap.alreadyInitialized(true);
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

	///////////////////
	// queryResponse //
	///////////////////

	/**	L'entité « queryResponse »
	 *	 is defined as null before being initialized. 
	 */
	protected QueryResponse queryResponse;
	public Wrap<QueryResponse> queryResponseWrap = new Wrap<QueryResponse>().p(this).c(QueryResponse.class).var("queryResponse").o(queryResponse);

	/**	<br/>L'entité « queryResponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:queryResponse">Trouver l'entité queryResponse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _queryResponse(Wrap<QueryResponse> c);

	public QueryResponse getQueryResponse() {
		return queryResponse;
	}

	public void setQueryResponse(QueryResponse queryResponse) {
		this.queryResponse = queryResponse;
		this.queryResponseWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS queryResponseInit() {
		if(!queryResponseWrap.alreadyInitialized) {
			_queryResponse(queryResponseWrap);
			if(queryResponse == null)
				setQueryResponse(queryResponseWrap.o);
		}
		queryResponseWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////
	// searchResults //
	///////////////////

	/**	L'entité « searchResults »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocumentList searchResults;
	public Wrap<SolrDocumentList> searchResultsWrap = new Wrap<SolrDocumentList>().p(this).c(SolrDocumentList.class).var("searchResults").o(searchResults);

	/**	<br/>L'entité « searchResults »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:searchResults">Trouver l'entité searchResults dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _searchResults(Wrap<SolrDocumentList> c);

	public SolrDocumentList getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(SolrDocumentList searchResults) {
		this.searchResults = searchResults;
		this.searchResultsWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS searchResultsInit() {
		if(!searchResultsWrap.alreadyInitialized) {
			_searchResults(searchResultsWrap);
			if(searchResults == null)
				setSearchResults(searchResultsWrap.o);
		}
		searchResultsWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////
	// w //
	///////

	/**	L'entité « w »
	 *	 is defined as null before being initialized. 
	 */
	protected AllWriter w;
	public Wrap<AllWriter> wWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("w").o(w);

	/**	<br/>L'entité « w »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:w">Trouver l'entité w dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _w(Wrap<AllWriter> c);

	public AllWriter getW() {
		return w;
	}

	public void setW(AllWriter w) {
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
			w.initDeepForClass(siteRequest_);
		wWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////
	// userVertx //
	///////////////

	/**	L'entité « userVertx »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject userVertx;
	public Wrap<JsonObject> userVertxWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("userVertx").o(userVertx);

	/**	<br/>L'entité « userVertx »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userVertx">Trouver l'entité userVertx dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userVertx(Wrap<JsonObject> c);

	public JsonObject getUserVertx() {
		return userVertx;
	}

	public void setUserVertx(JsonObject userVertx) {
		this.userVertx = userVertx;
		this.userVertxWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS userVertxInit() {
		if(!userVertxWrap.alreadyInitialized) {
			_userVertx(userVertxWrap);
			if(userVertx == null)
				setUserVertx(userVertxWrap.o);
		}
		userVertxWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////
	// jsonPrincipal //
	///////////////////

	/**	L'entité « jsonPrincipal »
	 *	 is defined as null before being initialized. 
	 */
	protected JsonObject jsonPrincipal;
	public Wrap<JsonObject> jsonPrincipalWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("jsonPrincipal").o(jsonPrincipal);

	/**	<br/>L'entité « jsonPrincipal »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jsonPrincipal">Trouver l'entité jsonPrincipal dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jsonPrincipal(Wrap<JsonObject> c);

	public JsonObject getJsonPrincipal() {
		return jsonPrincipal;
	}

	public void setJsonPrincipal(JsonObject jsonPrincipal) {
		this.jsonPrincipal = jsonPrincipal;
		this.jsonPrincipalWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS jsonPrincipalInit() {
		if(!jsonPrincipalWrap.alreadyInitialized) {
			_jsonPrincipal(jsonPrincipalWrap);
			if(jsonPrincipal == null)
				setJsonPrincipal(jsonPrincipalWrap.o);
		}
		jsonPrincipalWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	////////////
	// userId //
	////////////

	/**	L'entité « userId »
	 *	 is defined as null before being initialized. 
	 */
	protected String userId;
	public Wrap<String> userIdWrap = new Wrap<String>().p(this).c(String.class).var("userId").o(userId);

	/**	<br/>L'entité « userId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userId">Trouver l'entité userId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userId(Wrap<String> c);

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		this.userIdWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS userIdInit() {
		if(!userIdWrap.alreadyInitialized) {
			_userId(userIdWrap);
			if(userId == null)
				setUserId(userIdWrap.o);
		}
		userIdWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUserId() {
		return userId;
	}

	public String strUserId() {
		return userId == null ? "" : userId;
	}

	public String nomAffichageUserId() {
		return null;
	}

	public String htmTooltipUserId() {
		return null;
	}

	public String htmUserId() {
		return userId == null ? "" : StringEscapeUtils.escapeHtml4(strUserId());
	}

	//////////////
	// userName //
	//////////////

	/**	L'entité « userName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userName;
	public Wrap<String> userNameWrap = new Wrap<String>().p(this).c(String.class).var("userName").o(userName);

	/**	<br/>L'entité « userName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userName">Trouver l'entité userName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userName(Wrap<String> c);

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		this.userNameWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS userNameInit() {
		if(!userNameWrap.alreadyInitialized) {
			_userName(userNameWrap);
			if(userName == null)
				setUserName(userNameWrap.o);
		}
		userNameWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUserName() {
		return userName;
	}

	public String strUserName() {
		return userName == null ? "" : userName;
	}

	public String nomAffichageUserName() {
		return null;
	}

	public String htmTooltipUserName() {
		return null;
	}

	public String htmUserName() {
		return userName == null ? "" : StringEscapeUtils.escapeHtml4(strUserName());
	}

	//////////////////
	// userLastName //
	//////////////////

	/**	L'entité « userLastName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userLastName;
	public Wrap<String> userLastNameWrap = new Wrap<String>().p(this).c(String.class).var("userLastName").o(userLastName);

	/**	<br/>L'entité « userLastName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userLastName">Trouver l'entité userLastName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userLastName(Wrap<String> c);

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
		this.userLastNameWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS userLastNameInit() {
		if(!userLastNameWrap.alreadyInitialized) {
			_userLastName(userLastNameWrap);
			if(userLastName == null)
				setUserLastName(userLastNameWrap.o);
		}
		userLastNameWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUserLastName() {
		return userLastName;
	}

	public String strUserLastName() {
		return userLastName == null ? "" : userLastName;
	}

	public String nomAffichageUserLastName() {
		return null;
	}

	public String htmTooltipUserLastName() {
		return null;
	}

	public String htmUserLastName() {
		return userLastName == null ? "" : StringEscapeUtils.escapeHtml4(strUserLastName());
	}

	///////////////////
	// userFirstName //
	///////////////////

	/**	L'entité « userFirstName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userFirstName;
	public Wrap<String> userFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("userFirstName").o(userFirstName);

	/**	<br/>L'entité « userFirstName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userFirstName">Trouver l'entité userFirstName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userFirstName(Wrap<String> c);

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
		this.userFirstNameWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS userFirstNameInit() {
		if(!userFirstNameWrap.alreadyInitialized) {
			_userFirstName(userFirstNameWrap);
			if(userFirstName == null)
				setUserFirstName(userFirstNameWrap.o);
		}
		userFirstNameWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUserFirstName() {
		return userFirstName;
	}

	public String strUserFirstName() {
		return userFirstName == null ? "" : userFirstName;
	}

	public String nomAffichageUserFirstName() {
		return null;
	}

	public String htmTooltipUserFirstName() {
		return null;
	}

	public String htmUserFirstName() {
		return userFirstName == null ? "" : StringEscapeUtils.escapeHtml4(strUserFirstName());
	}

	//////////////////
	// userFullName //
	//////////////////

	/**	L'entité « userFullName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userFullName;
	public Wrap<String> userFullNameWrap = new Wrap<String>().p(this).c(String.class).var("userFullName").o(userFullName);

	/**	<br/>L'entité « userFullName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userFullName">Trouver l'entité userFullName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userFullName(Wrap<String> c);

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
		this.userFullNameWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS userFullNameInit() {
		if(!userFullNameWrap.alreadyInitialized) {
			_userFullName(userFullNameWrap);
			if(userFullName == null)
				setUserFullName(userFullNameWrap.o);
		}
		userFullNameWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrUserFullName() {
		return userFullName;
	}

	public String strUserFullName() {
		return userFullName == null ? "" : userFullName;
	}

	public String nomAffichageUserFullName() {
		return null;
	}

	public String htmTooltipUserFullName() {
		return null;
	}

	public String htmUserFullName() {
		return userFullName == null ? "" : StringEscapeUtils.escapeHtml4(strUserFullName());
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
			utilisateurSite.initDeepForClass(siteRequest_);
		utilisateurSiteWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////
	// xmlStack //
	//////////////

	/**	L'entité « xmlStack »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 */
	protected Stack<String> xmlStack = new Stack<String>();
	public Wrap<Stack<String>> xmlStackWrap = new Wrap<Stack<String>>().p(this).c(Stack.class).var("xmlStack").o(xmlStack);

	/**	<br/>L'entité « xmlStack »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:xmlStack">Trouver l'entité xmlStack dans Solr</a>
	 * <br/>
	 * @param xmlStack est l'entité déjà construit. 
	 **/
	protected abstract void _xmlStack(Stack<String> o);

	public Stack<String> getXmlStack() {
		return xmlStack;
	}

	public void setXmlStack(Stack<String> xmlStack) {
		this.xmlStack = xmlStack;
		this.xmlStackWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS xmlStackInit() {
		if(!xmlStackWrap.alreadyInitialized) {
			_xmlStack(xmlStack);
		}
		xmlStackWrap.alreadyInitialized(true);
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
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteRequestEnUS = false;

	public SiteRequestEnUS initDeepSiteRequestEnUS(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSiteRequestEnUS) {
			alreadyInitializedSiteRequestEnUS = true;
			initDeepSiteRequestEnUS();
		}
		return (SiteRequestEnUS)this;
	}

	public void initDeepSiteRequestEnUS() {
		initSiteRequestEnUS();
	}

	public void initSiteRequestEnUS() {
		siteContext_Init();
		siteConfig_Init();
		siteRequest_Init();
		vertxInit();
		jsonObjectInit();
		solrQueryInit();
		operationRequestInit();
		queryResponseInit();
		searchResultsInit();
		wInit();
		userVertxInit();
		jsonPrincipalInit();
		userIdInit();
		userNameInit();
		userLastNameInit();
		userFirstNameInit();
		userFullNameInit();
		utilisateurRolesRoyaumeInit();
		utilisateurRessourceInit();
		utilisateurSiteInit();
		xmlStackInit();
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

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteRequestEnUS(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteRequestEnUS(SiteRequestEnUS siteRequest_) {
		if(w != null)
			w.setSiteRequest_(siteRequest_);
		if(utilisateurSite != null)
			utilisateurSite.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSiteRequestEnUS(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteRequestEnUS(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSiteRequestEnUS(String var) {
		SiteRequestEnUS oSiteRequestEnUS = (SiteRequestEnUS)this;
		switch(var) {
			case "siteContext_":
				return oSiteRequestEnUS.siteContext_;
			case "siteConfig_":
				return oSiteRequestEnUS.siteConfig_;
			case "siteRequest_":
				return oSiteRequestEnUS.siteRequest_;
			case "vertx":
				return oSiteRequestEnUS.vertx;
			case "jsonObject":
				return oSiteRequestEnUS.jsonObject;
			case "solrQuery":
				return oSiteRequestEnUS.solrQuery;
			case "operationRequest":
				return oSiteRequestEnUS.operationRequest;
			case "queryResponse":
				return oSiteRequestEnUS.queryResponse;
			case "searchResults":
				return oSiteRequestEnUS.searchResults;
			case "w":
				return oSiteRequestEnUS.w;
			case "userVertx":
				return oSiteRequestEnUS.userVertx;
			case "jsonPrincipal":
				return oSiteRequestEnUS.jsonPrincipal;
			case "userId":
				return oSiteRequestEnUS.userId;
			case "userName":
				return oSiteRequestEnUS.userName;
			case "userLastName":
				return oSiteRequestEnUS.userLastName;
			case "userFirstName":
				return oSiteRequestEnUS.userFirstName;
			case "userFullName":
				return oSiteRequestEnUS.userFullName;
			case "utilisateurRolesRoyaume":
				return oSiteRequestEnUS.utilisateurRolesRoyaume;
			case "utilisateurRessource":
				return oSiteRequestEnUS.utilisateurRessource;
			case "utilisateurSite":
				return oSiteRequestEnUS.utilisateurSite;
			case "xmlStack":
				return oSiteRequestEnUS.xmlStack;
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
	// attribute //
	///////////////

	public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributeSiteRequestEnUS(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSiteRequestEnUS(String var, Object val) {
		SiteRequestEnUS oSiteRequestEnUS = (SiteRequestEnUS)this;
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
					o = definirSiteRequestEnUS(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
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
