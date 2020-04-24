package org.computate.scolaire.enUS.request;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.solr.common.SolrDocumentList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.Stack;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.user.SiteUser;
import io.vertx.core.http.CaseInsensitiveHeaders;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import io.vertx.core.Vertx;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import io.vertx.ext.web.api.OperationRequest;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLConnection;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteRequestEnUSGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SiteRequestEnUS.class);

	//////////////////
	// siteContext_ //
	//////////////////

	/**	L'entité « siteContext_ »
The site context with global site information. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteContextEnUS siteContext_;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected SiteConfig siteConfig_;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected SiteRequestEnUS siteRequest_;
	@JsonIgnore
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

	/////////////////
	// apiRequest_ //
	/////////////////

	/**	L'entité « apiRequest_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ApiRequest apiRequest_;
	@JsonIgnore
	public Wrap<ApiRequest> apiRequest_Wrap = new Wrap<ApiRequest>().p(this).c(ApiRequest.class).var("apiRequest_").o(apiRequest_);

	/**	<br/>L'entité « apiRequest_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiRequest_">Trouver l'entité apiRequest_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiRequest_(Wrap<ApiRequest> c);

	public ApiRequest getApiRequest_() {
		return apiRequest_;
	}

	public void setApiRequest_(ApiRequest apiRequest_) {
		this.apiRequest_ = apiRequest_;
		this.apiRequest_Wrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS apiRequest_Init() {
		if(!apiRequest_Wrap.alreadyInitialized) {
			_apiRequest_(apiRequest_Wrap);
			if(apiRequest_ == null)
				setApiRequest_(apiRequest_Wrap.o);
		}
		apiRequest_Wrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////
	// vertx //
	///////////

	/**	L'entité « vertx »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Vertx vertx;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected JsonObject jsonObject;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected SolrQuery solrQuery;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected OperationRequest operationRequest;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected QueryResponse queryResponse;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected SolrDocumentList searchResults;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected AllWriter w;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected JsonObject userVertx;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected JsonObject jsonPrincipal;
	@JsonIgnore
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
	@JsonInclude(Include.NON_NULL)
	protected String userId;
	@JsonIgnore
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

	public String jsonUserId() {
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

	/////////////
	// userKey //
	/////////////

	/**	L'entité « userKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long userKey;
	@JsonIgnore
	public Wrap<Long> userKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("userKey").o(userKey);

	/**	<br/>L'entité « userKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKey">Trouver l'entité userKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userKey(Wrap<Long> c);

	public Long getUserKey() {
		return userKey;
	}

	public void setUserKey(Long userKey) {
		this.userKey = userKey;
		this.userKeyWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS setUserKey(String o) {
		if(NumberUtils.isParsable(o))
			this.userKey = Long.parseLong(o);
		this.userKeyWrap.alreadyInitialized = true;
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS userKeyInit() {
		if(!userKeyWrap.alreadyInitialized) {
			_userKey(userKeyWrap);
			if(userKey == null)
				setUserKey(userKeyWrap.o);
		}
		userKeyWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public Long solrUserKey() {
		return userKey;
	}

	public String strUserKey() {
		return userKey == null ? "" : userKey.toString();
	}

	public String jsonUserKey() {
		return userKey == null ? "" : userKey.toString();
	}

	public String nomAffichageUserKey() {
		return null;
	}

	public String htmTooltipUserKey() {
		return null;
	}

	public String htmUserKey() {
		return userKey == null ? "" : StringEscapeUtils.escapeHtml4(strUserKey());
	}

	///////////////
	// sessionId //
	///////////////

	/**	L'entité « sessionId »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sessionId;
	@JsonIgnore
	public Wrap<String> sessionIdWrap = new Wrap<String>().p(this).c(String.class).var("sessionId").o(sessionId);

	/**	<br/>L'entité « sessionId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionId">Trouver l'entité sessionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionId(Wrap<String> c);

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
		this.sessionIdWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS sessionIdInit() {
		if(!sessionIdWrap.alreadyInitialized) {
			_sessionId(sessionIdWrap);
			if(sessionId == null)
				setSessionId(sessionIdWrap.o);
		}
		sessionIdWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public String solrSessionId() {
		return sessionId;
	}

	public String strSessionId() {
		return sessionId == null ? "" : sessionId;
	}

	public String jsonSessionId() {
		return sessionId == null ? "" : sessionId;
	}

	public String nomAffichageSessionId() {
		return null;
	}

	public String htmTooltipSessionId() {
		return null;
	}

	public String htmSessionId() {
		return sessionId == null ? "" : StringEscapeUtils.escapeHtml4(strSessionId());
	}

	//////////////
	// userName //
	//////////////

	/**	L'entité « userName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String userName;
	@JsonIgnore
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

	public String jsonUserName() {
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
	@JsonInclude(Include.NON_NULL)
	protected String userLastName;
	@JsonIgnore
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

	public String jsonUserLastName() {
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
	@JsonInclude(Include.NON_NULL)
	protected String userFirstName;
	@JsonIgnore
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

	public String jsonUserFirstName() {
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
	@JsonInclude(Include.NON_NULL)
	protected String userFullName;
	@JsonIgnore
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

	public String jsonUserFullName() {
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

	////////////////////
	// userRealmRoles //
	////////////////////

	/**	L'entité « userRealmRoles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> userRealmRoles = new java.util.ArrayList<java.lang.String>();
	@JsonIgnore
	public Wrap<List<String>> userRealmRolesWrap = new Wrap<List<String>>().p(this).c(List.class).var("userRealmRoles").o(userRealmRoles);

	/**	<br/>L'entité « userRealmRoles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userRealmRoles">Trouver l'entité userRealmRoles dans Solr</a>
	 * <br/>
	 * @param userRealmRoles est l'entité déjà construit. 
	 **/
	protected abstract void _userRealmRoles(List<String> o);

	public List<String> getUserRealmRoles() {
		return userRealmRoles;
	}

	public void setUserRealmRoles(List<String> userRealmRoles) {
		this.userRealmRoles = userRealmRoles;
		this.userRealmRolesWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS addUserRealmRoles(String...objets) {
		for(String o : objets) {
			addUserRealmRoles(o);
		}
		return (SiteRequestEnUS)this;
	}
	public SiteRequestEnUS addUserRealmRoles(String o) {
		if(o != null && !userRealmRoles.contains(o))
			this.userRealmRoles.add(o);
		return (SiteRequestEnUS)this;
	}
	public SiteRequestEnUS setUserRealmRoles(JsonArray objets) {
		userRealmRoles.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addUserRealmRoles(o);
		}
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS userRealmRolesInit() {
		if(!userRealmRolesWrap.alreadyInitialized) {
			_userRealmRoles(userRealmRoles);
		}
		userRealmRolesWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public List<String> solrUserRealmRoles() {
		return userRealmRoles;
	}

	public String strUserRealmRoles() {
		return userRealmRoles == null ? "" : userRealmRoles.toString();
	}

	public String jsonUserRealmRoles() {
		return userRealmRoles == null ? "" : userRealmRoles.toString();
	}

	public String nomAffichageUserRealmRoles() {
		return null;
	}

	public String htmTooltipUserRealmRoles() {
		return null;
	}

	public String htmUserRealmRoles() {
		return userRealmRoles == null ? "" : StringEscapeUtils.escapeHtml4(strUserRealmRoles());
	}

	//////////////////
	// userResource //
	//////////////////

	/**	L'entité « userResource »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected JsonObject userResource;
	@JsonIgnore
	public Wrap<JsonObject> userResourceWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("userResource").o(userResource);

	/**	<br/>L'entité « userResource »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userResource">Trouver l'entité userResource dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userResource(Wrap<JsonObject> c);

	public JsonObject getUserResource() {
		return userResource;
	}

	public void setUserResource(JsonObject userResource) {
		this.userResource = userResource;
		this.userResourceWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS userResourceInit() {
		if(!userResourceWrap.alreadyInitialized) {
			_userResource(userResourceWrap);
			if(userResource == null)
				setUserResource(userResourceWrap.o);
		}
		userResourceWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////////////
	// userResourceRoles //
	///////////////////////

	/**	L'entité « userResourceRoles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> userResourceRoles = new java.util.ArrayList<java.lang.String>();
	@JsonIgnore
	public Wrap<List<String>> userResourceRolesWrap = new Wrap<List<String>>().p(this).c(List.class).var("userResourceRoles").o(userResourceRoles);

	/**	<br/>L'entité « userResourceRoles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userResourceRoles">Trouver l'entité userResourceRoles dans Solr</a>
	 * <br/>
	 * @param userResourceRoles est l'entité déjà construit. 
	 **/
	protected abstract void _userResourceRoles(List<String> o);

	public List<String> getUserResourceRoles() {
		return userResourceRoles;
	}

	public void setUserResourceRoles(List<String> userResourceRoles) {
		this.userResourceRoles = userResourceRoles;
		this.userResourceRolesWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS addUserResourceRoles(String...objets) {
		for(String o : objets) {
			addUserResourceRoles(o);
		}
		return (SiteRequestEnUS)this;
	}
	public SiteRequestEnUS addUserResourceRoles(String o) {
		if(o != null && !userResourceRoles.contains(o))
			this.userResourceRoles.add(o);
		return (SiteRequestEnUS)this;
	}
	public SiteRequestEnUS setUserResourceRoles(JsonArray objets) {
		userResourceRoles.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addUserResourceRoles(o);
		}
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS userResourceRolesInit() {
		if(!userResourceRolesWrap.alreadyInitialized) {
			_userResourceRoles(userResourceRoles);
		}
		userResourceRolesWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public List<String> solrUserResourceRoles() {
		return userResourceRoles;
	}

	public String strUserResourceRoles() {
		return userResourceRoles == null ? "" : userResourceRoles.toString();
	}

	public String jsonUserResourceRoles() {
		return userResourceRoles == null ? "" : userResourceRoles.toString();
	}

	public String nomAffichageUserResourceRoles() {
		return null;
	}

	public String htmTooltipUserResourceRoles() {
		return null;
	}

	public String htmUserResourceRoles() {
		return userResourceRoles == null ? "" : StringEscapeUtils.escapeHtml4(strUserResourceRoles());
	}

	//////////////
	// siteUser //
	//////////////

	/**	L'entité « siteUser »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteUser siteUser;
	@JsonIgnore
	public Wrap<SiteUser> siteUserWrap = new Wrap<SiteUser>().p(this).c(SiteUser.class).var("siteUser").o(siteUser);

	/**	<br/>L'entité « siteUser »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteUser">Trouver l'entité siteUser dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteUser(Wrap<SiteUser> c);

	public SiteUser getSiteUser() {
		return siteUser;
	}

	public void setSiteUser(SiteUser siteUser) {
		this.siteUser = siteUser;
		this.siteUserWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS siteUserInit() {
		if(!siteUserWrap.alreadyInitialized) {
			_siteUser(siteUserWrap);
			if(siteUser == null)
				setSiteUser(siteUserWrap.o);
		}
		if(siteUser != null)
			siteUser.initDeepForClass(siteRequest_);
		siteUserWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	//////////////
	// xmlStack //
	//////////////

	/**	L'entité « xmlStack »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut Stack<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Stack<String> xmlStack = new Stack<String>();
	@JsonIgnore
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
	// solrDocument //
	//////////////////

	/**	L'entité « solrDocument »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocument solrDocument;
	@JsonIgnore
	public Wrap<SolrDocument> solrDocumentWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("solrDocument").o(solrDocument);

	/**	<br/>L'entité « solrDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrDocument">Trouver l'entité solrDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrDocument(Wrap<SolrDocument> c);

	public SolrDocument getSolrDocument() {
		return solrDocument;
	}

	public void setSolrDocument(SolrDocument solrDocument) {
		this.solrDocument = solrDocument;
		this.solrDocumentWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS solrDocumentInit() {
		if(!solrDocumentWrap.alreadyInitialized) {
			_solrDocument(solrDocumentWrap);
			if(solrDocument == null)
				setSolrDocument(solrDocumentWrap.o);
		}
		solrDocumentWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	///////////////
	// pageAdmin //
	///////////////

	/**	L'entité « pageAdmin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean pageAdmin;
	@JsonIgnore
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
	// requestPk //
	///////////////

	/**	L'entité « requestPk »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long requestPk;
	@JsonIgnore
	public Wrap<Long> requestPkWrap = new Wrap<Long>().p(this).c(Long.class).var("requestPk").o(requestPk);

	/**	<br/>L'entité « requestPk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:requestPk">Trouver l'entité requestPk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requestPk(Wrap<Long> c);

	public Long getRequestPk() {
		return requestPk;
	}

	public void setRequestPk(Long requestPk) {
		this.requestPk = requestPk;
		this.requestPkWrap.alreadyInitialized = true;
	}
	public SiteRequestEnUS setRequestPk(String o) {
		if(NumberUtils.isParsable(o))
			this.requestPk = Long.parseLong(o);
		this.requestPkWrap.alreadyInitialized = true;
		return (SiteRequestEnUS)this;
	}
	protected SiteRequestEnUS requestPkInit() {
		if(!requestPkWrap.alreadyInitialized) {
			_requestPk(requestPkWrap);
			if(requestPk == null)
				setRequestPk(requestPkWrap.o);
		}
		requestPkWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	public Long solrRequestPk() {
		return requestPk;
	}

	public String strRequestPk() {
		return requestPk == null ? "" : requestPk.toString();
	}

	public String jsonRequestPk() {
		return requestPk == null ? "" : requestPk.toString();
	}

	public String nomAffichageRequestPk() {
		return null;
	}

	public String htmTooltipRequestPk() {
		return null;
	}

	public String htmRequestPk() {
		return requestPk == null ? "" : StringEscapeUtils.escapeHtml4(strRequestPk());
	}

	///////////////////
	// sqlConnection //
	///////////////////

	/**	L'entité « sqlConnection »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SQLConnection sqlConnection;
	@JsonIgnore
	public Wrap<SQLConnection> sqlConnectionWrap = new Wrap<SQLConnection>().p(this).c(SQLConnection.class).var("sqlConnection").o(sqlConnection);

	/**	<br/>L'entité « sqlConnection »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sqlConnection">Trouver l'entité sqlConnection dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sqlConnection(Wrap<SQLConnection> c);

	public SQLConnection getSqlConnection() {
		return sqlConnection;
	}

	public void setSqlConnection(SQLConnection sqlConnection) {
		this.sqlConnection = sqlConnection;
		this.sqlConnectionWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS sqlConnectionInit() {
		if(!sqlConnectionWrap.alreadyInitialized) {
			_sqlConnection(sqlConnectionWrap);
			if(sqlConnection == null)
				setSqlConnection(sqlConnectionWrap.o);
		}
		sqlConnectionWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	////////////////////
	// requestHeaders //
	////////////////////

	/**	L'entité « requestHeaders »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected CaseInsensitiveHeaders requestHeaders;
	@JsonIgnore
	public Wrap<CaseInsensitiveHeaders> requestHeadersWrap = new Wrap<CaseInsensitiveHeaders>().p(this).c(CaseInsensitiveHeaders.class).var("requestHeaders").o(requestHeaders);

	/**	<br/>L'entité « requestHeaders »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:requestHeaders">Trouver l'entité requestHeaders dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requestHeaders(Wrap<CaseInsensitiveHeaders> c);

	public CaseInsensitiveHeaders getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(CaseInsensitiveHeaders requestHeaders) {
		this.requestHeaders = requestHeaders;
		this.requestHeadersWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS requestHeadersInit() {
		if(!requestHeadersWrap.alreadyInitialized) {
			_requestHeaders(requestHeadersWrap);
			if(requestHeaders == null)
				setRequestHeaders(requestHeadersWrap.o);
		}
		requestHeadersWrap.alreadyInitialized(true);
		return (SiteRequestEnUS)this;
	}

	/////////////////
	// requestVars //
	/////////////////

	/**	L'entité « requestVars »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut Map<String, String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Map<String, String> requestVars = new java.util.HashMap<java.lang.String, java.lang.String>();
	@JsonIgnore
	public Wrap<Map<String, String>> requestVarsWrap = new Wrap<Map<String, String>>().p(this).c(Map.class).var("requestVars").o(requestVars);

	/**	<br/>L'entité « requestVars »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut Map<String, String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.SiteRequestEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:requestVars">Trouver l'entité requestVars dans Solr</a>
	 * <br/>
	 * @param requestVars est l'entité déjà construit. 
	 **/
	protected abstract void _requestVars(Map<String, String> m);

	public Map<String, String> getRequestVars() {
		return requestVars;
	}

	public void setRequestVars(Map<String, String> requestVars) {
		this.requestVars = requestVars;
		this.requestVarsWrap.alreadyInitialized = true;
	}
	protected SiteRequestEnUS requestVarsInit() {
		if(!requestVarsWrap.alreadyInitialized) {
			_requestVars(requestVars);
		}
		requestVarsWrap.alreadyInitialized(true);
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
		apiRequest_Init();
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
		userKeyInit();
		sessionIdInit();
		userNameInit();
		userLastNameInit();
		userFirstNameInit();
		userFullNameInit();
		userRealmRolesInit();
		userResourceInit();
		userResourceRolesInit();
		siteUserInit();
		xmlStackInit();
		solrDocumentInit();
		pageAdminInit();
		requestPkInit();
		sqlConnectionInit();
		requestHeadersInit();
		requestVarsInit();
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
		if(siteUser != null)
			siteUser.setSiteRequest_(siteRequest_);
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
			case "apiRequest_":
				return oSiteRequestEnUS.apiRequest_;
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
			case "userKey":
				return oSiteRequestEnUS.userKey;
			case "sessionId":
				return oSiteRequestEnUS.sessionId;
			case "userName":
				return oSiteRequestEnUS.userName;
			case "userLastName":
				return oSiteRequestEnUS.userLastName;
			case "userFirstName":
				return oSiteRequestEnUS.userFirstName;
			case "userFullName":
				return oSiteRequestEnUS.userFullName;
			case "userRealmRoles":
				return oSiteRequestEnUS.userRealmRoles;
			case "userResource":
				return oSiteRequestEnUS.userResource;
			case "userResourceRoles":
				return oSiteRequestEnUS.userResourceRoles;
			case "siteUser":
				return oSiteRequestEnUS.siteUser;
			case "xmlStack":
				return oSiteRequestEnUS.xmlStack;
			case "solrDocument":
				return oSiteRequestEnUS.solrDocument;
			case "pageAdmin":
				return oSiteRequestEnUS.pageAdmin;
			case "requestPk":
				return oSiteRequestEnUS.requestPk;
			case "sqlConnection":
				return oSiteRequestEnUS.sqlConnection;
			case "requestHeaders":
				return oSiteRequestEnUS.requestHeaders;
			case "requestVars":
				return oSiteRequestEnUS.requestVars;
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
	// define //
	/////////////

	public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineSiteRequestEnUS(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSiteRequestEnUS(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSiteRequestEnUS() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SiteRequestEnUS) {
			SiteRequestEnUS original = (SiteRequestEnUS)o;
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
		sb.append("SiteRequestEnUS { ");
		sb.append(" }");
		return sb.toString();
	}
}
