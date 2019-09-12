package org.computate.scolaire.enUS.search;

import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.util.List;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.solr.client.solrj.SolrQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.Class;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SearchListGen<DEV> {

	///////
	// c //
	///////

	/**	L'entité « c »
	 *	 is defined as null before being initialized. 
	 */
	protected Class<DEV> c;
	@JsonIgnore
	public Wrap<Class<DEV>> cWrap = new Wrap<Class<DEV>>().p(this).c(Class.class).var("c").o(c);

	/**	<br/>L'entité « c »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:c">Trouver l'entité c dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _c(Wrap<Class<DEV>> c);

	public Class<DEV> getC() {
		return c;
	}

	public void setC(Class<DEV> c) {
		this.c = c;
		this.cWrap.alreadyInitialized = true;
	}
	protected SearchList cInit() {
		if(!cWrap.alreadyInitialized) {
			_c(cWrap);
			if(c == null)
				setC(cWrap.o);
		}
		cWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	L'entité « siteRequest_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SiteRequestEnUS siteRequest_;
	@JsonIgnore
	public Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("siteRequest_").o(siteRequest_);

	/**	<br/>L'entité « siteRequest_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
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
	protected SearchList siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	///////////
	// store //
	///////////

	/**	L'entité « store »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean store;
	@JsonIgnore
	public Wrap<Boolean> storeWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("store").o(store);

	/**	<br/>L'entité « store »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:store">Trouver l'entité store dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _store(Wrap<Boolean> c);

	public Boolean getStore() {
		return store;
	}

	public void setStore(Boolean store) {
		this.store = store;
		this.storeWrap.alreadyInitialized = true;
	}
	public SearchList setStore(String o) {
		this.store = Boolean.parseBoolean(o);
		this.storeWrap.alreadyInitialized = true;
		return (SearchList)this;
	}
	protected SearchList storeInit() {
		if(!storeWrap.alreadyInitialized) {
			_store(storeWrap);
			if(store == null)
				setStore(storeWrap.o);
		}
		storeWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	public Boolean solrStore() {
		return store;
	}

	public String strStore() {
		return store == null ? "" : store.toString();
	}

	public String jsonStore() {
		return store == null ? "" : store.toString();
	}

	public String nomAffichageStore() {
		return null;
	}

	public String htmTooltipStore() {
		return null;
	}

	public String htmStore() {
		return store == null ? "" : StringEscapeUtils.escapeHtml4(strStore());
	}

	//////////////
	// populate //
	//////////////

	/**	L'entité « populate »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean populate;
	@JsonIgnore
	public Wrap<Boolean> populateWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("populate").o(populate);

	/**	<br/>L'entité « populate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:populate">Trouver l'entité populate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _populate(Wrap<Boolean> c);

	public Boolean getPopulate() {
		return populate;
	}

	public void setPopulate(Boolean populate) {
		this.populate = populate;
		this.populateWrap.alreadyInitialized = true;
	}
	public SearchList setPopulate(String o) {
		this.populate = Boolean.parseBoolean(o);
		this.populateWrap.alreadyInitialized = true;
		return (SearchList)this;
	}
	protected SearchList populateInit() {
		if(!populateWrap.alreadyInitialized) {
			_populate(populateWrap);
			if(populate == null)
				setPopulate(populateWrap.o);
		}
		populateWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	public Boolean solrPopulate() {
		return populate;
	}

	public String strPopulate() {
		return populate == null ? "" : populate.toString();
	}

	public String jsonPopulate() {
		return populate == null ? "" : populate.toString();
	}

	public String nomAffichagePopulate() {
		return null;
	}

	public String htmTooltipPopulate() {
		return null;
	}

	public String htmPopulate() {
		return populate == null ? "" : StringEscapeUtils.escapeHtml4(strPopulate());
	}

	////////////
	// fields //
	////////////

	/**	L'entité « fields »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	protected List<String> fields = new java.util.ArrayList<java.lang.String>();
	@JsonIgnore
	public Wrap<List<String>> fieldsWrap = new Wrap<List<String>>().p(this).c(List.class).var("fields").o(fields);

	/**	<br/>L'entité « fields »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:fields">Trouver l'entité fields dans Solr</a>
	 * <br/>
	 * @param fields est l'entité déjà construit. 
	 **/
	protected abstract void _fields(List<String> c);

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
		this.fieldsWrap.alreadyInitialized = true;
	}
	public SearchList addFields(String...objets) {
		for(String o : objets) {
			addFields(o);
		}
		return (SearchList)this;
	}
	public SearchList addFields(String o) {
		if(o != null && !fields.contains(o))
			this.fields.add(o);
		return (SearchList)this;
	}
	public SearchList setFields(JsonArray objets) {
		fields.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addFields(o);
		}
		return (SearchList)this;
	}
	protected SearchList fieldsInit() {
		if(!fieldsWrap.alreadyInitialized) {
			_fields(fields);
		}
		fieldsWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	public List<String> solrFields() {
		return fields;
	}

	public String strFields() {
		return fields == null ? "" : fields.toString();
	}

	public String jsonFields() {
		return fields == null ? "" : fields.toString();
	}

	public String nomAffichageFields() {
		return null;
	}

	public String htmTooltipFields() {
		return null;
	}

	public String htmFields() {
		return fields == null ? "" : StringEscapeUtils.escapeHtml4(strFields());
	}

	///////////////
	// solrQuery //
	///////////////

	/**	L'entité « solrQuery »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SolrQuery(). 
	 */
	protected SolrQuery solrQuery = new SolrQuery();
	@JsonIgnore
	public Wrap<SolrQuery> solrQueryWrap = new Wrap<SolrQuery>().p(this).c(SolrQuery.class).var("solrQuery").o(solrQuery);

	/**	<br/>L'entité « solrQuery »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SolrQuery(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrQuery">Trouver l'entité solrQuery dans Solr</a>
	 * <br/>
	 * @param solrQuery est l'entité déjà construit. 
	 **/
	protected abstract void _solrQuery(SolrQuery o);

	public SolrQuery getSolrQuery() {
		return solrQuery;
	}

	public void setSolrQuery(SolrQuery solrQuery) {
		this.solrQuery = solrQuery;
		this.solrQueryWrap.alreadyInitialized = true;
	}
	protected SearchList solrQueryInit() {
		if(!solrQueryWrap.alreadyInitialized) {
			_solrQuery(solrQuery);
		}
		solrQueryWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	///////////////////
	// queryResponse //
	///////////////////

	/**	L'entité « queryResponse »
	 *	 is defined as null before being initialized. 
	 */
	protected QueryResponse queryResponse;
	@JsonIgnore
	public Wrap<QueryResponse> queryResponseWrap = new Wrap<QueryResponse>().p(this).c(QueryResponse.class).var("queryResponse").o(queryResponse);

	/**	<br/>L'entité « queryResponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:queryResponse">Trouver l'entité queryResponse dans Solr</a>
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
	protected SearchList queryResponseInit() {
		if(!queryResponseWrap.alreadyInitialized) {
			_queryResponse(queryResponseWrap);
			if(queryResponse == null)
				setQueryResponse(queryResponseWrap.o);
		}
		queryResponseWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	//////////////////////
	// solrDocumentList //
	//////////////////////

	/**	L'entité « solrDocumentList »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocumentList solrDocumentList;
	@JsonIgnore
	public Wrap<SolrDocumentList> solrDocumentListWrap = new Wrap<SolrDocumentList>().p(this).c(SolrDocumentList.class).var("solrDocumentList").o(solrDocumentList);

	/**	<br/>L'entité « solrDocumentList »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrDocumentList">Trouver l'entité solrDocumentList dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrDocumentList(Wrap<SolrDocumentList> c);

	public SolrDocumentList getSolrDocumentList() {
		return solrDocumentList;
	}

	public void setSolrDocumentList(SolrDocumentList solrDocumentList) {
		this.solrDocumentList = solrDocumentList;
		this.solrDocumentListWrap.alreadyInitialized = true;
	}
	protected SearchList solrDocumentListInit() {
		if(!solrDocumentListWrap.alreadyInitialized) {
			_solrDocumentList(solrDocumentListWrap);
			if(solrDocumentList == null)
				setSolrDocumentList(solrDocumentListWrap.o);
		}
		solrDocumentListWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	//////////
	// list //
	//////////

	/**	L'entité « list »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<DEV>(). 
	 */
	protected List<DEV> list = new java.util.ArrayList<DEV>();
	@JsonIgnore
	public Wrap<List<DEV>> listWrap = new Wrap<List<DEV>>().p(this).c(List.class).var("list").o(list);

	/**	<br/>L'entité « list »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<DEV>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.search.SearchList&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:list">Trouver l'entité list dans Solr</a>
	 * <br/>
	 * @param list est l'entité déjà construit. 
	 **/
	protected abstract void _list(List<DEV> l);

	public List<DEV> getList() {
		return list;
	}

	public void setList(List<DEV> list) {
		this.list = list;
		this.listWrap.alreadyInitialized = true;
	}
	public SearchList addList(DEV...objets) {
		for(DEV o : objets) {
			addList(o);
		}
		return (SearchList)this;
	}
	public SearchList addList(DEV o) {
		if(o != null && !list.contains(o))
			this.list.add(o);
		return (SearchList)this;
	}
	protected SearchList listInit() {
		if(!listWrap.alreadyInitialized) {
			_list(list);
		}
		listWrap.alreadyInitialized(true);
		return (SearchList)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSearchList = false;

	public SearchList initDeepSearchList(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSearchList) {
			alreadyInitializedSearchList = true;
			initDeepSearchList();
		}
		return (SearchList)this;
	}

	public void initDeepSearchList() {
		initSearchList();
	}

	public void initSearchList() {
		cInit();
		siteRequest_Init();
		storeInit();
		populateInit();
		fieldsInit();
		solrQueryInit();
		queryResponseInit();
		solrDocumentListInit();
		listInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSearchList(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSearchList(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSearchList(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSearchList(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSearchList(String var) {
		SearchList oSearchList = (SearchList)this;
		switch(var) {
			case "c":
				return oSearchList.c;
			case "siteRequest_":
				return oSearchList.siteRequest_;
			case "store":
				return oSearchList.store;
			case "populate":
				return oSearchList.populate;
			case "fields":
				return oSearchList.fields;
			case "solrQuery":
				return oSearchList.solrQuery;
			case "queryResponse":
				return oSearchList.queryResponse;
			case "solrDocumentList":
				return oSearchList.solrDocumentList;
			case "list":
				return oSearchList.list;
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
				o = attributeSearchList(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSearchList(String var, Object val) {
		SearchList oSearchList = (SearchList)this;
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
					o = defineSearchList(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSearchList(String var, String val) {
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
		if(!(o instanceof SearchList))
			return false;
		SearchList that = (SearchList)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SearchList { ");
		sb.append(" }");
		return sb.toString();
	}
}
