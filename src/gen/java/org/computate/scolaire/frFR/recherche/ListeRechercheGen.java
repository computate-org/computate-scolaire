package org.computate.scolaire.frFR.recherche;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.solr.common.SolrDocumentList;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Class;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class ListeRechercheGen<DEV> {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ListeRecherche.class);

	///////
	// c //
	///////

	/**	 L'entité c
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Class<DEV> c;
	@JsonIgnore
	public Couverture<Class<DEV>> cCouverture = new Couverture<Class<DEV>>().p(this).c(Class.class).var("c").o(c);

	/**	<br/> L'entité c
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:c">Trouver l'entité c dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _c(Couverture<Class<DEV>> c);

	public Class<DEV> getC() {
		return c;
	}

	public void setC(Class<DEV> c) {
		this.c = c;
		this.cCouverture.dejaInitialise = true;
	}
	protected ListeRecherche cInit() {
		if(!cCouverture.dejaInitialise) {
			_c(cCouverture);
			if(c == null)
				setC(cCouverture.o);
		}
		cCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	 L'entité requeteSite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/> L'entité requeteSite_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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
	public static RequeteSiteFrFR staticSetRequeteSite_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ListeRecherche requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	/////////////
	// stocker //
	/////////////

	/**	 L'entité stocker
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean stocker;
	@JsonIgnore
	public Couverture<Boolean> stockerCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("stocker").o(stocker);

	/**	<br/> L'entité stocker
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:stocker">Trouver l'entité stocker dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _stocker(Couverture<Boolean> c);

	public Boolean getStocker() {
		return stocker;
	}

	public void setStocker(Boolean stocker) {
		this.stocker = stocker;
		this.stockerCouverture.dejaInitialise = true;
	}
	public void setStocker(String o) {
		this.stocker = ListeRecherche.staticSetStocker(requeteSite_, o);
		this.stockerCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetStocker(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected ListeRecherche stockerInit() {
		if(!stockerCouverture.dejaInitialise) {
			_stocker(stockerCouverture);
			if(stocker == null)
				setStocker(stockerCouverture.o);
		}
		stockerCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	public static Boolean staticSolrStocker(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrStocker(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqStocker(RequeteSiteFrFR requeteSite_, String o) {
		return ListeRecherche.staticSolrStrStocker(requeteSite_, ListeRecherche.staticSolrStocker(requeteSite_, ListeRecherche.staticSetStocker(requeteSite_, o)));
	}

	public Boolean solrStocker() {
		return ListeRecherche.staticSolrStocker(requeteSite_, stocker);
	}

	public String strStocker() {
		return stocker == null ? "" : stocker.toString();
	}

	public String jsonStocker() {
		return stocker == null ? "" : stocker.toString();
	}

	public String nomAffichageStocker() {
		return null;
	}

	public String htmTooltipStocker() {
		return null;
	}

	public String htmStocker() {
		return stocker == null ? "" : StringEscapeUtils.escapeHtml4(strStocker());
	}

	/////////////
	// peupler //
	/////////////

	/**	 L'entité peupler
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean peupler;
	@JsonIgnore
	public Couverture<Boolean> peuplerCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("peupler").o(peupler);

	/**	<br/> L'entité peupler
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:peupler">Trouver l'entité peupler dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _peupler(Couverture<Boolean> c);

	public Boolean getPeupler() {
		return peupler;
	}

	public void setPeupler(Boolean peupler) {
		this.peupler = peupler;
		this.peuplerCouverture.dejaInitialise = true;
	}
	public void setPeupler(String o) {
		this.peupler = ListeRecherche.staticSetPeupler(requeteSite_, o);
		this.peuplerCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPeupler(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected ListeRecherche peuplerInit() {
		if(!peuplerCouverture.dejaInitialise) {
			_peupler(peuplerCouverture);
			if(peupler == null)
				setPeupler(peuplerCouverture.o);
		}
		peuplerCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	public static Boolean staticSolrPeupler(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPeupler(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPeupler(RequeteSiteFrFR requeteSite_, String o) {
		return ListeRecherche.staticSolrStrPeupler(requeteSite_, ListeRecherche.staticSolrPeupler(requeteSite_, ListeRecherche.staticSetPeupler(requeteSite_, o)));
	}

	public Boolean solrPeupler() {
		return ListeRecherche.staticSolrPeupler(requeteSite_, peupler);
	}

	public String strPeupler() {
		return peupler == null ? "" : peupler.toString();
	}

	public String jsonPeupler() {
		return peupler == null ? "" : peupler.toString();
	}

	public String nomAffichagePeupler() {
		return null;
	}

	public String htmTooltipPeupler() {
		return null;
	}

	public String htmPeupler() {
		return peupler == null ? "" : StringEscapeUtils.escapeHtml4(strPeupler());
	}

	////////////
	// fields //
	////////////

	/**	 L'entité fields
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> fields = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> fieldsCouverture = new Couverture<List<String>>().p(this).c(List.class).var("fields").o(fields);

	/**	<br/> L'entité fields
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fields">Trouver l'entité fields dans Solr</a>
	 * <br/>
	 * @param fields est l'entité déjà construit. 
	 **/
	protected abstract void _fields(List<String> c);

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
		this.fieldsCouverture.dejaInitialise = true;
	}
	public static String staticSetFields(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	public ListeRecherche addFields(String...objets) {
		for(String o : objets) {
			addFields(o);
		}
		return (ListeRecherche)this;
	}
	public ListeRecherche addFields(String o) {
		if(o != null && !fields.contains(o))
			this.fields.add(o);
		return (ListeRecherche)this;
	}
	public void setFields(JsonArray objets) {
		fields.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addFields(o);
		}
	}
	protected ListeRecherche fieldsInit() {
		if(!fieldsCouverture.dejaInitialise) {
			_fields(fields);
		}
		fieldsCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	public static String staticSolrFields(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrFields(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFields(RequeteSiteFrFR requeteSite_, String o) {
		return ListeRecherche.staticSolrStrFields(requeteSite_, ListeRecherche.staticSolrFields(requeteSite_, ListeRecherche.staticSetFields(requeteSite_, o)));
	}

	public List<String> solrFields() {
		List<String> l = new ArrayList<String>();
		for(String o : fields) {
			l.add(ListeRecherche.staticSolrFields(requeteSite_, o));
		}
		return l;
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

	/**	 L'entité solrQuery
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SolrQuery(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrQuery solrQuery = new SolrQuery();
	@JsonIgnore
	public Couverture<SolrQuery> solrQueryCouverture = new Couverture<SolrQuery>().p(this).c(SolrQuery.class).var("solrQuery").o(solrQuery);

	/**	<br/> L'entité solrQuery
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SolrQuery(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrQuery">Trouver l'entité solrQuery dans Solr</a>
	 * <br/>
	 * @param solrQuery est l'entité déjà construit. 
	 **/
	protected abstract void _solrQuery(SolrQuery o);

	public SolrQuery getSolrQuery() {
		return solrQuery;
	}

	public void setSolrQuery(SolrQuery solrQuery) {
		this.solrQuery = solrQuery;
		this.solrQueryCouverture.dejaInitialise = true;
	}
	public static SolrQuery staticSetSolrQuery(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ListeRecherche solrQueryInit() {
		if(!solrQueryCouverture.dejaInitialise) {
			_solrQuery(solrQuery);
		}
		solrQueryCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	///////////////////
	// queryResponse //
	///////////////////

	/**	 L'entité queryResponse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected QueryResponse queryResponse;
	@JsonIgnore
	public Couverture<QueryResponse> queryResponseCouverture = new Couverture<QueryResponse>().p(this).c(QueryResponse.class).var("queryResponse").o(queryResponse);

	/**	<br/> L'entité queryResponse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:queryResponse">Trouver l'entité queryResponse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _queryResponse(Couverture<QueryResponse> c);

	public QueryResponse getQueryResponse() {
		return queryResponse;
	}

	public void setQueryResponse(QueryResponse queryResponse) {
		this.queryResponse = queryResponse;
		this.queryResponseCouverture.dejaInitialise = true;
	}
	public static QueryResponse staticSetQueryResponse(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ListeRecherche queryResponseInit() {
		if(!queryResponseCouverture.dejaInitialise) {
			_queryResponse(queryResponseCouverture);
			if(queryResponse == null)
				setQueryResponse(queryResponseCouverture.o);
		}
		queryResponseCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	//////////////////////
	// solrDocumentList //
	//////////////////////

	/**	 L'entité solrDocumentList
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocumentList solrDocumentList;
	@JsonIgnore
	public Couverture<SolrDocumentList> solrDocumentListCouverture = new Couverture<SolrDocumentList>().p(this).c(SolrDocumentList.class).var("solrDocumentList").o(solrDocumentList);

	/**	<br/> L'entité solrDocumentList
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrDocumentList">Trouver l'entité solrDocumentList dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrDocumentList(Couverture<SolrDocumentList> c);

	public SolrDocumentList getSolrDocumentList() {
		return solrDocumentList;
	}

	public void setSolrDocumentList(SolrDocumentList solrDocumentList) {
		this.solrDocumentList = solrDocumentList;
		this.solrDocumentListCouverture.dejaInitialise = true;
	}
	public static SolrDocumentList staticSetSolrDocumentList(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ListeRecherche solrDocumentListInit() {
		if(!solrDocumentListCouverture.dejaInitialise) {
			_solrDocumentList(solrDocumentListCouverture);
			if(solrDocumentList == null)
				setSolrDocumentList(solrDocumentListCouverture.o);
		}
		solrDocumentListCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	//////////
	// list //
	//////////

	/**	 L'entité list
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<DEV>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<DEV> list = new ArrayList<DEV>();
	@JsonIgnore
	public Couverture<List<DEV>> listCouverture = new Couverture<List<DEV>>().p(this).c(List.class).var("list").o(list);

	/**	<br/> L'entité list
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<DEV>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:list">Trouver l'entité list dans Solr</a>
	 * <br/>
	 * @param list est l'entité déjà construit. 
	 **/
	protected abstract void _list(List<DEV> l);

	public List<DEV> getList() {
		return list;
	}

	public void setList(List<DEV> list) {
		this.list = list;
		this.listCouverture.dejaInitialise = true;
	}
	public ListeRecherche addList(DEV...objets) {
		for(DEV o : objets) {
			addList(o);
		}
		return (ListeRecherche)this;
	}
	public ListeRecherche addList(DEV o) {
		if(o != null && !list.contains(o))
			this.list.add(o);
		return (ListeRecherche)this;
	}
	protected ListeRecherche listInit() {
		if(!listCouverture.dejaInitialise) {
			_list(list);
		}
		listCouverture.dejaInitialise(true);
		return (ListeRecherche)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseListeRecherche = false;

	public ListeRecherche initLoinListeRecherche(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseListeRecherche) {
			dejaInitialiseListeRecherche = true;
			initLoinListeRecherche();
		}
		return (ListeRecherche)this;
	}

	public void initLoinListeRecherche() {
		initListeRecherche();
	}

	public void initListeRecherche() {
		cInit();
		requeteSite_Init();
		stockerInit();
		peuplerInit();
		fieldsInit();
		solrQueryInit();
		queryResponseInit();
		solrDocumentListInit();
		listInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinListeRecherche(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteListeRecherche(RequeteSiteFrFR requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteListeRecherche(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirListeRecherche(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirListeRecherche(String var) {
		ListeRecherche oListeRecherche = (ListeRecherche)this;
		switch(var) {
			case "c":
				return oListeRecherche.c;
			case "requeteSite_":
				return oListeRecherche.requeteSite_;
			case "stocker":
				return oListeRecherche.stocker;
			case "peupler":
				return oListeRecherche.peupler;
			case "fields":
				return oListeRecherche.fields;
			case "solrQuery":
				return oListeRecherche.solrQuery;
			case "queryResponse":
				return oListeRecherche.queryResponse;
			case "solrDocumentList":
				return oListeRecherche.solrDocumentList;
			case "list":
				return oListeRecherche.list;
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
				o = attribuerListeRecherche(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerListeRecherche(String var, Object val) {
		ListeRecherche oListeRecherche = (ListeRecherche)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetListeRecherche(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetListeRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "stocker":
			return ListeRecherche.staticSetStocker(requeteSite_, o);
		case "peupler":
			return ListeRecherche.staticSetPeupler(requeteSite_, o);
		case "fields":
			return ListeRecherche.staticSetFields(requeteSite_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrListeRecherche(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrListeRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "stocker":
			return ListeRecherche.staticSolrStocker(requeteSite_, (Boolean)o);
		case "peupler":
			return ListeRecherche.staticSolrPeupler(requeteSite_, (Boolean)o);
		case "fields":
			return ListeRecherche.staticSolrFields(requeteSite_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrListeRecherche(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrListeRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "stocker":
			return ListeRecherche.staticSolrStrStocker(requeteSite_, (Boolean)o);
		case "peupler":
			return ListeRecherche.staticSolrStrPeupler(requeteSite_, (Boolean)o);
		case "fields":
			return ListeRecherche.staticSolrStrFields(requeteSite_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqListeRecherche(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqListeRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "stocker":
			return ListeRecherche.staticSolrFqStocker(requeteSite_, o);
		case "peupler":
			return ListeRecherche.staticSolrFqPeupler(requeteSite_, o);
		case "fields":
			return ListeRecherche.staticSolrFqFields(requeteSite_, o);
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
					o = definirListeRecherche(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirListeRecherche(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiListeRecherche() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof ListeRecherche) {
			ListeRecherche original = (ListeRecherche)o;
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
		if(!(o instanceof ListeRecherche))
			return false;
		ListeRecherche that = (ListeRecherche)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ListeRecherche { ");
		sb.append(" }");
		return sb.toString();
	}
}
