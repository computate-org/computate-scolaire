package org.computate.frFR.scolaire.recherche;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import java.util.List;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.apache.solr.client.solrj.SolrQuery;
import java.lang.Boolean;
import java.lang.Class;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ListeRechercheGen<DEV> {

	///////
	// c //
	///////

	/**	L'entité « c »
	 *	 is defined as null before being initialized. 
	 */
	protected Class<DEV> c;
	public Couverture<Class<DEV>> cCouverture = new Couverture<Class<DEV>>().p(this).c(Class.class).var("c").o(c);

	/**	<br/>L'entité « c »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:c">Trouver l'entité c dans Solr</a>
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

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSite requeteSite_;
	public Couverture<RequeteSite> requeteSite_Couverture = new Couverture<RequeteSite>().p(this).c(RequeteSite.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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

	/////////////
	// stocker //
	/////////////

	/**	L'entité « stocker »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean stocker;
	public Couverture<Boolean> stockerCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("stocker").o(stocker);

	/**	<br/>L'entité « stocker »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:stocker">Trouver l'entité stocker dans Solr</a>
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
	public ListeRecherche setStocker(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.stocker = Boolean.parseBoolean(o);
		this.stockerCouverture.dejaInitialise = true;
		return (ListeRecherche)this;
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

	public Boolean solrStocker() {
		return stocker;
	}

	public String strStocker() {
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

	/**	L'entité « peupler »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean peupler;
	public Couverture<Boolean> peuplerCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("peupler").o(peupler);

	/**	<br/>L'entité « peupler »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:peupler">Trouver l'entité peupler dans Solr</a>
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
	public ListeRecherche setPeupler(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.peupler = Boolean.parseBoolean(o);
		this.peuplerCouverture.dejaInitialise = true;
		return (ListeRecherche)this;
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

	public Boolean solrPeupler() {
		return peupler;
	}

	public String strPeupler() {
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

	///////////////
	// solrQuery //
	///////////////

	/**	L'entité « solrQuery »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SolrQuery(). 
	 */
	protected SolrQuery solrQuery = new SolrQuery();
	public Couverture<SolrQuery> solrQueryCouverture = new Couverture<SolrQuery>().p(this).c(SolrQuery.class).var("solrQuery").o(solrQuery);

	/**	<br/>L'entité « solrQuery »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SolrQuery(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrQuery">Trouver l'entité solrQuery dans Solr</a>
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

	/**	L'entité « queryResponse »
	 *	 is defined as null before being initialized. 
	 */
	protected QueryResponse queryResponse;
	public Couverture<QueryResponse> queryResponseCouverture = new Couverture<QueryResponse>().p(this).c(QueryResponse.class).var("queryResponse").o(queryResponse);

	/**	<br/>L'entité « queryResponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:queryResponse">Trouver l'entité queryResponse dans Solr</a>
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

	/**	L'entité « solrDocumentList »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocumentList solrDocumentList;
	public Couverture<SolrDocumentList> solrDocumentListCouverture = new Couverture<SolrDocumentList>().p(this).c(SolrDocumentList.class).var("solrDocumentList").o(solrDocumentList);

	/**	<br/>L'entité « solrDocumentList »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrDocumentList">Trouver l'entité solrDocumentList dans Solr</a>
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

	/**	L'entité « list »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<DEV>(). 
	 */
	protected List<DEV> list = new java.util.ArrayList<DEV>();
	public Couverture<List<DEV>> listCouverture = new Couverture<List<DEV>>().p(this).c(List.class).var("list").o(list);

	/**	<br/>L'entité « list »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<DEV>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ListeRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:list">Trouver l'entité list dans Solr</a>
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

	public ListeRecherche initLoinListeRecherche(RequeteSite requeteSite_) {
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
		stockerInit();
		peuplerInit();
		solrQueryInit();
		queryResponseInit();
		solrDocumentListInit();
		listInit();
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinListeRecherche(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteListeRecherche(RequeteSite requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteListeRecherche(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
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
	public Object obtenirListeRecherche(String var) throws Exception {
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
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
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

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(stocker, peupler);
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
		return Objects.equals( stocker, that.stocker )
				&& Objects.equals( peupler, that.peupler );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ListeRecherche {");
		sb.append( "stocker: " ).append(stocker);
		sb.append( ", peupler: " ).append(peupler);
		sb.append(" }");
		return sb.toString();
	}
}
