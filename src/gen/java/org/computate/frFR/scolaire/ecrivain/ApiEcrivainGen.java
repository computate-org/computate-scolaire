package org.computate.frFR.scolaire.ecrivain;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.vertx.AppliSwagger2;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import org.computate.frFR.scolaire.config.ConfigSite;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.frFR.scolaire.ecrivain.TousEcrivains;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.computate.frFR.scolaire.couverture.Couverture;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ApiEcrivainGen<DEV> extends Object {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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

	////////////////////////
	// classeDocumentSolr //
	////////////////////////

	/**	L'entité « classeDocumentSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument classeDocumentSolr;
	public Couverture<SolrDocument> classeDocumentSolrCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("classeDocumentSolr").o(classeDocumentSolr);

	/**	<br/>L'entité « classeDocumentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeDocumentSolr">Trouver l'entité classeDocumentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeDocumentSolr(Couverture<SolrDocument> c);

	public SolrDocument getClasseDocumentSolr() {
		return classeDocumentSolr;
	}

	public void setClasseDocumentSolr(SolrDocument classeDocumentSolr) {
		this.classeDocumentSolr = classeDocumentSolr;
		this.classeDocumentSolrCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeDocumentSolrInit() {
		if(!classeDocumentSolrCouverture.dejaInitialise) {
			_classeDocumentSolr(classeDocumentSolrCouverture);
			if(classeDocumentSolr == null)
				setClasseDocumentSolr(classeDocumentSolrCouverture.o);
		}
		classeDocumentSolrCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	//////////////////////
	// classeApiMethode //
	//////////////////////

	/**	L'entité « classeApiMethode »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiMethode;
	public Couverture<String> classeApiMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiMethode").o(classeApiMethode);

	/**	<br/>L'entité « classeApiMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiMethode">Trouver l'entité classeApiMethode dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiMethode(Couverture<String> c);

	public String getClasseApiMethode() {
		return classeApiMethode;
	}

	public void setClasseApiMethode(String classeApiMethode) {
		this.classeApiMethode = classeApiMethode;
		this.classeApiMethodeCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiMethodeInit() {
		if(!classeApiMethodeCouverture.dejaInitialise) {
			_classeApiMethode(classeApiMethodeCouverture);
			if(classeApiMethode == null)
				setClasseApiMethode(classeApiMethodeCouverture.o);
		}
		classeApiMethodeCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiMethode() {
		return classeApiMethode;
	}

	public String strClasseApiMethode() {
		return classeApiMethode == null ? "" : classeApiMethode;
	}

	public String nomAffichageClasseApiMethode() {
		return null;
	}

	public String htmTooltipClasseApiMethode() {
		return null;
	}

	public String htmClasseApiMethode() {
		return classeApiMethode == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiMethode());
	}

	////////////////////
	// openApiVersion //
	////////////////////

	/**	L'entité « openApiVersion »
	 *	 is defined as null before being initialized. 
	 */
	protected String openApiVersion;
	public Couverture<String> openApiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/>L'entité « openApiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersion(Couverture<String> c);

	public String getOpenApiVersion() {
		return openApiVersion;
	}

	public void setOpenApiVersion(String openApiVersion) {
		this.openApiVersion = openApiVersion;
		this.openApiVersionCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain openApiVersionInit() {
		if(!openApiVersionCouverture.dejaInitialise) {
			_openApiVersion(openApiVersionCouverture);
			if(openApiVersion == null)
				setOpenApiVersion(openApiVersionCouverture.o);
		}
		openApiVersionCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrOpenApiVersion() {
		return openApiVersion;
	}

	public String strOpenApiVersion() {
		return openApiVersion == null ? "" : openApiVersion;
	}

	public String nomAffichageOpenApiVersion() {
		return null;
	}

	public String htmTooltipOpenApiVersion() {
		return null;
	}

	public String htmOpenApiVersion() {
		return openApiVersion == null ? "" : StringEscapeUtils.escapeHtml4(strOpenApiVersion());
	}

	/////////////////
	// appSwagger2 //
	/////////////////

	/**	L'entité « appSwagger2 »
	 *	 is defined as null before being initialized. 
	 */
	protected AppliSwagger2 appSwagger2;
	public Couverture<AppliSwagger2> appSwagger2Couverture = new Couverture<AppliSwagger2>().p(this).c(AppliSwagger2.class).var("appSwagger2").o(appSwagger2);

	/**	<br/>L'entité « appSwagger2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appSwagger2">Trouver l'entité appSwagger2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appSwagger2(Couverture<AppliSwagger2> c);

	public AppliSwagger2 getAppSwagger2() {
		return appSwagger2;
	}

	public void setAppSwagger2(AppliSwagger2 appSwagger2) {
		this.appSwagger2 = appSwagger2;
		this.appSwagger2Couverture.dejaInitialise = true;
	}
	protected ApiEcrivain appSwagger2Init() {
		if(!appSwagger2Couverture.dejaInitialise) {
			_appSwagger2(appSwagger2Couverture);
			if(appSwagger2 == null)
				setAppSwagger2(appSwagger2Couverture.o);
		}
		appSwagger2Couverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////
	// classeUris //
	////////////////

	/**	L'entité « classeUris »
	 *	 is defined as null before being initialized. 
	 */
	protected List<String> classeUris;
	public Couverture<List<String>> classeUrisCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeUris").o(classeUris);

	/**	<br/>L'entité « classeUris »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeUris">Trouver l'entité classeUris dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeUris(Couverture<List<String>> c);

	public List<String> getClasseUris() {
		return classeUris;
	}

	public void setClasseUris(List<String> classeUris) {
		this.classeUris = classeUris;
		this.classeUrisCouverture.dejaInitialise = true;
	}
	public ApiEcrivain addClasseUris(String...objets) {
		for(String o : objets) {
			addClasseUris(o);
		}
		return (ApiEcrivain)this;
	}
	public ApiEcrivain addClasseUris(String o) {
		if(o != null && !classeUris.contains(o))
			this.classeUris.add(o);
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeUrisInit() {
		if(!classeUrisCouverture.dejaInitialise) {
			_classeUris(classeUrisCouverture);
			if(classeUris == null)
				setClasseUris(classeUrisCouverture.o);
		}
		classeUrisCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public List<String> solrClasseUris() {
		return classeUris;
	}

	public String strClasseUris() {
		return classeUris == null ? "" : classeUris.toString();
	}

	public String nomAffichageClasseUris() {
		return null;
	}

	public String htmTooltipClasseUris() {
		return null;
	}

	public String htmClasseUris() {
		return classeUris == null ? "" : StringEscapeUtils.escapeHtml4(strClasseUris());
	}

	//////////////////////////
	// openApiVersionNumero //
	//////////////////////////

	/**	L'entité « openApiVersionNumero »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer openApiVersionNumero;
	public Couverture<Integer> openApiVersionNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("openApiVersionNumero").o(openApiVersionNumero);

	/**	<br/>L'entité « openApiVersionNumero »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersionNumero">Trouver l'entité openApiVersionNumero dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersionNumero(Couverture<Integer> c);

	public Integer getOpenApiVersionNumero() {
		return openApiVersionNumero;
	}

	public void setOpenApiVersionNumero(Integer openApiVersionNumero) {
		this.openApiVersionNumero = openApiVersionNumero;
		this.openApiVersionNumeroCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setOpenApiVersionNumero(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.openApiVersionNumero = Integer.parseInt(o);
		this.openApiVersionNumeroCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain openApiVersionNumeroInit() {
		if(!openApiVersionNumeroCouverture.dejaInitialise) {
			_openApiVersionNumero(openApiVersionNumeroCouverture);
			if(openApiVersionNumero == null)
				setOpenApiVersionNumero(openApiVersionNumeroCouverture.o);
		}
		openApiVersionNumeroCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Integer solrOpenApiVersionNumero() {
		return openApiVersionNumero;
	}

	public String strOpenApiVersionNumero() {
		return openApiVersionNumero == null ? "" : openApiVersionNumero.toString();
	}

	public String nomAffichageOpenApiVersionNumero() {
		return null;
	}

	public String htmTooltipOpenApiVersionNumero() {
		return null;
	}

	public String htmOpenApiVersionNumero() {
		return openApiVersionNumero == null ? "" : StringEscapeUtils.escapeHtml4(strOpenApiVersionNumero());
	}

	////////////////
	// tabsSchema //
	////////////////

	/**	L'entité « tabsSchema »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer tabsSchema;
	public Couverture<Integer> tabsSchemaCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("tabsSchema").o(tabsSchema);

	/**	<br/>L'entité « tabsSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tabsSchema">Trouver l'entité tabsSchema dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabsSchema(Couverture<Integer> c);

	public Integer getTabsSchema() {
		return tabsSchema;
	}

	public void setTabsSchema(Integer tabsSchema) {
		this.tabsSchema = tabsSchema;
		this.tabsSchemaCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setTabsSchema(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.tabsSchema = Integer.parseInt(o);
		this.tabsSchemaCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain tabsSchemaInit() {
		if(!tabsSchemaCouverture.dejaInitialise) {
			_tabsSchema(tabsSchemaCouverture);
			if(tabsSchema == null)
				setTabsSchema(tabsSchemaCouverture.o);
		}
		tabsSchemaCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Integer solrTabsSchema() {
		return tabsSchema;
	}

	public String strTabsSchema() {
		return tabsSchema == null ? "" : tabsSchema.toString();
	}

	public String nomAffichageTabsSchema() {
		return null;
	}

	public String htmTooltipTabsSchema() {
		return null;
	}

	public String htmTabsSchema() {
		return tabsSchema == null ? "" : StringEscapeUtils.escapeHtml4(strTabsSchema());
	}

	//////////////////
	// tabsReponses //
	//////////////////

	/**	L'entité « tabsReponses »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer tabsReponses;
	public Couverture<Integer> tabsReponsesCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("tabsReponses").o(tabsReponses);

	/**	<br/>L'entité « tabsReponses »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tabsReponses">Trouver l'entité tabsReponses dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabsReponses(Couverture<Integer> c);

	public Integer getTabsReponses() {
		return tabsReponses;
	}

	public void setTabsReponses(Integer tabsReponses) {
		this.tabsReponses = tabsReponses;
		this.tabsReponsesCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setTabsReponses(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.tabsReponses = Integer.parseInt(o);
		this.tabsReponsesCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain tabsReponsesInit() {
		if(!tabsReponsesCouverture.dejaInitialise) {
			_tabsReponses(tabsReponsesCouverture);
			if(tabsReponses == null)
				setTabsReponses(tabsReponsesCouverture.o);
		}
		tabsReponsesCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Integer solrTabsReponses() {
		return tabsReponses;
	}

	public String strTabsReponses() {
		return tabsReponses == null ? "" : tabsReponses.toString();
	}

	public String nomAffichageTabsReponses() {
		return null;
	}

	public String htmTooltipTabsReponses() {
		return null;
	}

	public String htmTabsReponses() {
		return tabsReponses == null ? "" : StringEscapeUtils.escapeHtml4(strTabsReponses());
	}

	//////////////
	// wChemins //
	//////////////

	/**	L'entité « wChemins »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wChemins;
	public Couverture<ToutEcrivain> wCheminsCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wChemins").o(wChemins);

	/**	<br/>L'entité « wChemins »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wChemins">Trouver l'entité wChemins dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wChemins(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWChemins() {
		return wChemins;
	}

	public void setWChemins(ToutEcrivain wChemins) {
		this.wChemins = wChemins;
		this.wCheminsCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wCheminsInit() {
		if(!wCheminsCouverture.dejaInitialise) {
			_wChemins(wCheminsCouverture);
			if(wChemins == null)
				setWChemins(wCheminsCouverture.o);
		}
		if(wChemins != null)
			wChemins.initLoinPourClasse(requeteSite_);
		wCheminsCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////////
	// wCorpsRequetes //
	////////////////////

	/**	L'entité « wCorpsRequetes »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wCorpsRequetes;
	public Couverture<ToutEcrivain> wCorpsRequetesCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wCorpsRequetes").o(wCorpsRequetes);

	/**	<br/>L'entité « wCorpsRequetes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wCorpsRequetes">Trouver l'entité wCorpsRequetes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wCorpsRequetes(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWCorpsRequetes() {
		return wCorpsRequetes;
	}

	public void setWCorpsRequetes(ToutEcrivain wCorpsRequetes) {
		this.wCorpsRequetes = wCorpsRequetes;
		this.wCorpsRequetesCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wCorpsRequetesInit() {
		if(!wCorpsRequetesCouverture.dejaInitialise) {
			_wCorpsRequetes(wCorpsRequetesCouverture);
			if(wCorpsRequetes == null)
				setWCorpsRequetes(wCorpsRequetesCouverture.o);
		}
		if(wCorpsRequetes != null)
			wCorpsRequetes.initLoinPourClasse(requeteSite_);
		wCorpsRequetesCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	//////////////
	// wSchemas //
	//////////////

	/**	L'entité « wSchemas »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wSchemas;
	public Couverture<ToutEcrivain> wSchemasCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wSchemas").o(wSchemas);

	/**	<br/>L'entité « wSchemas »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wSchemas">Trouver l'entité wSchemas dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wSchemas(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWSchemas() {
		return wSchemas;
	}

	public void setWSchemas(ToutEcrivain wSchemas) {
		this.wSchemas = wSchemas;
		this.wSchemasCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wSchemasInit() {
		if(!wSchemasCouverture.dejaInitialise) {
			_wSchemas(wSchemasCouverture);
			if(wSchemas == null)
				setWSchemas(wSchemasCouverture.o);
		}
		if(wSchemas != null)
			wSchemas.initLoinPourClasse(requeteSite_);
		wSchemasCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	//////////////////
	// siteContexte //
	//////////////////

	/**	L'entité « siteContexte »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteContexte siteContexte;
	public Couverture<SiteContexte> siteContexteCouverture = new Couverture<SiteContexte>().p(this).c(SiteContexte.class).var("siteContexte").o(siteContexte);

	/**	<br/>L'entité « siteContexte »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte">Trouver l'entité siteContexte dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteContexte(Couverture<SiteContexte> c);

	public SiteContexte getSiteContexte() {
		return siteContexte;
	}

	public void setSiteContexte(SiteContexte siteContexte) {
		this.siteContexte = siteContexte;
		this.siteContexteCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain siteContexteInit() {
		if(!siteContexteCouverture.dejaInitialise) {
			_siteContexte(siteContexteCouverture);
			if(siteContexte == null)
				setSiteContexte(siteContexteCouverture.o);
		}
		if(siteContexte != null)
			siteContexte.initLoinPourClasse(requeteSite_);
		siteContexteCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////
	// configSite //
	////////////////

	/**	L'entité « configSite »
	 *	 is defined as null before being initialized. 
	 */
	protected ConfigSite configSite;
	public Couverture<ConfigSite> configSiteCouverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite").o(configSite);

	/**	<br/>L'entité « configSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configSite(Couverture<ConfigSite> c);

	public ConfigSite getConfigSite() {
		return configSite;
	}

	public void setConfigSite(ConfigSite configSite) {
		this.configSite = configSite;
		this.configSiteCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain configSiteInit() {
		if(!configSiteCouverture.dejaInitialise) {
			_configSite(configSiteCouverture);
			if(configSite == null)
				setConfigSite(configSiteCouverture.o);
		}
		if(configSite != null)
			configSite.initLoinPourClasse(requeteSite_);
		configSiteCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////////
	// wRequeteEnTete //
	////////////////////

	/**	L'entité « wRequeteEnTete »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wRequeteEnTete;
	public Couverture<ToutEcrivain> wRequeteEnTeteCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteEnTete").o(wRequeteEnTete);

	/**	<br/>L'entité « wRequeteEnTete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteEnTete">Trouver l'entité wRequeteEnTete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequeteEnTete(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWRequeteEnTete() {
		return wRequeteEnTete;
	}

	public void setWRequeteEnTete(ToutEcrivain wRequeteEnTete) {
		this.wRequeteEnTete = wRequeteEnTete;
		this.wRequeteEnTeteCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wRequeteEnTeteInit() {
		if(!wRequeteEnTeteCouverture.dejaInitialise) {
			_wRequeteEnTete(wRequeteEnTeteCouverture);
			if(wRequeteEnTete == null)
				setWRequeteEnTete(wRequeteEnTeteCouverture.o);
		}
		if(wRequeteEnTete != null)
			wRequeteEnTete.initLoinPourClasse(requeteSite_);
		wRequeteEnTeteCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	/////////////////////////
	// wRequeteDescription //
	/////////////////////////

	/**	L'entité « wRequeteDescription »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wRequeteDescription;
	public Couverture<ToutEcrivain> wRequeteDescriptionCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteDescription").o(wRequeteDescription);

	/**	<br/>L'entité « wRequeteDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteDescription">Trouver l'entité wRequeteDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequeteDescription(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWRequeteDescription() {
		return wRequeteDescription;
	}

	public void setWRequeteDescription(ToutEcrivain wRequeteDescription) {
		this.wRequeteDescription = wRequeteDescription;
		this.wRequeteDescriptionCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wRequeteDescriptionInit() {
		if(!wRequeteDescriptionCouverture.dejaInitialise) {
			_wRequeteDescription(wRequeteDescriptionCouverture);
			if(wRequeteDescription == null)
				setWRequeteDescription(wRequeteDescriptionCouverture.o);
		}
		if(wRequeteDescription != null)
			wRequeteDescription.initLoinPourClasse(requeteSite_);
		wRequeteDescriptionCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	/////////////////////////
	// wReponseDescription //
	/////////////////////////

	/**	L'entité « wReponseDescription »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wReponseDescription;
	public Couverture<ToutEcrivain> wReponseDescriptionCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wReponseDescription").o(wReponseDescription);

	/**	<br/>L'entité « wReponseDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wReponseDescription">Trouver l'entité wReponseDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wReponseDescription(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWReponseDescription() {
		return wReponseDescription;
	}

	public void setWReponseDescription(ToutEcrivain wReponseDescription) {
		this.wReponseDescription = wReponseDescription;
		this.wReponseDescriptionCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wReponseDescriptionInit() {
		if(!wReponseDescriptionCouverture.dejaInitialise) {
			_wReponseDescription(wReponseDescriptionCouverture);
			if(wReponseDescription == null)
				setWReponseDescription(wReponseDescriptionCouverture.o);
		}
		if(wReponseDescription != null)
			wReponseDescription.initLoinPourClasse(requeteSite_);
		wReponseDescriptionCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	///////////////////
	// wRequeteCorps //
	///////////////////

	/**	L'entité « wRequeteCorps »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wRequeteCorps;
	public Couverture<ToutEcrivain> wRequeteCorpsCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteCorps").o(wRequeteCorps);

	/**	<br/>L'entité « wRequeteCorps »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteCorps">Trouver l'entité wRequeteCorps dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequeteCorps(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWRequeteCorps() {
		return wRequeteCorps;
	}

	public void setWRequeteCorps(ToutEcrivain wRequeteCorps) {
		this.wRequeteCorps = wRequeteCorps;
		this.wRequeteCorpsCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wRequeteCorpsInit() {
		if(!wRequeteCorpsCouverture.dejaInitialise) {
			_wRequeteCorps(wRequeteCorpsCouverture);
			if(wRequeteCorps == null)
				setWRequeteCorps(wRequeteCorpsCouverture.o);
		}
		if(wRequeteCorps != null)
			wRequeteCorps.initLoinPourClasse(requeteSite_);
		wRequeteCorpsCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	///////////////////
	// wReponseCorps //
	///////////////////

	/**	L'entité « wReponseCorps »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wReponseCorps;
	public Couverture<ToutEcrivain> wReponseCorpsCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wReponseCorps").o(wReponseCorps);

	/**	<br/>L'entité « wReponseCorps »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wReponseCorps">Trouver l'entité wReponseCorps dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wReponseCorps(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWReponseCorps() {
		return wReponseCorps;
	}

	public void setWReponseCorps(ToutEcrivain wReponseCorps) {
		this.wReponseCorps = wReponseCorps;
		this.wReponseCorpsCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wReponseCorpsInit() {
		if(!wReponseCorpsCouverture.dejaInitialise) {
			_wReponseCorps(wReponseCorpsCouverture);
			if(wReponseCorps == null)
				setWReponseCorps(wReponseCorpsCouverture.o);
		}
		if(wReponseCorps != null)
			wReponseCorps.initLoinPourClasse(requeteSite_);
		wReponseCorpsCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////////
	// wRequeteSchema //
	////////////////////

	/**	L'entité « wRequeteSchema »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wRequeteSchema;
	public Couverture<ToutEcrivain> wRequeteSchemaCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteSchema").o(wRequeteSchema);

	/**	<br/>L'entité « wRequeteSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteSchema">Trouver l'entité wRequeteSchema dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequeteSchema(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWRequeteSchema() {
		return wRequeteSchema;
	}

	public void setWRequeteSchema(ToutEcrivain wRequeteSchema) {
		this.wRequeteSchema = wRequeteSchema;
		this.wRequeteSchemaCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wRequeteSchemaInit() {
		if(!wRequeteSchemaCouverture.dejaInitialise) {
			_wRequeteSchema(wRequeteSchemaCouverture);
			if(wRequeteSchema == null)
				setWRequeteSchema(wRequeteSchemaCouverture.o);
		}
		if(wRequeteSchema != null)
			wRequeteSchema.initLoinPourClasse(requeteSite_);
		wRequeteSchemaCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////////
	// wReponseSchema //
	////////////////////

	/**	L'entité « wReponseSchema »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain wReponseSchema;
	public Couverture<ToutEcrivain> wReponseSchemaCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wReponseSchema").o(wReponseSchema);

	/**	<br/>L'entité « wReponseSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wReponseSchema">Trouver l'entité wReponseSchema dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wReponseSchema(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWReponseSchema() {
		return wReponseSchema;
	}

	public void setWReponseSchema(ToutEcrivain wReponseSchema) {
		this.wReponseSchema = wReponseSchema;
		this.wReponseSchemaCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain wReponseSchemaInit() {
		if(!wReponseSchemaCouverture.dejaInitialise) {
			_wReponseSchema(wReponseSchemaCouverture);
			if(wReponseSchema == null)
				setWReponseSchema(wReponseSchemaCouverture.o);
		}
		if(wReponseSchema != null)
			wReponseSchema.initLoinPourClasse(requeteSite_);
		wReponseSchemaCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	///////////////
	// ecrivains //
	///////////////

	/**	L'entité « ecrivains »
	 *	 is defined as null before being initialized. 
	 */
	protected TousEcrivains ecrivains;
	public Couverture<TousEcrivains> ecrivainsCouverture = new Couverture<TousEcrivains>().p(this).c(TousEcrivains.class).var("ecrivains").o(ecrivains);

	/**	<br/>L'entité « ecrivains »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecrivains">Trouver l'entité ecrivains dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecrivains(Couverture<TousEcrivains> c);

	public TousEcrivains getEcrivains() {
		return ecrivains;
	}

	public void setEcrivains(TousEcrivains ecrivains) {
		this.ecrivains = ecrivains;
		this.ecrivainsCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain ecrivainsInit() {
		if(!ecrivainsCouverture.dejaInitialise) {
			_ecrivains(ecrivainsCouverture);
			if(ecrivains == null)
				setEcrivains(ecrivainsCouverture.o);
		}
		if(ecrivains != null)
			ecrivains.initLoinPourClasse(requeteSite_);
		ecrivainsCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	//////////////////
	// classeApiTag //
	//////////////////

	/**	L'entité « classeApiTag »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiTag;
	public Couverture<String> classeApiTagCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiTag").o(classeApiTag);

	/**	<br/>L'entité « classeApiTag »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiTag">Trouver l'entité classeApiTag dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiTag(Couverture<String> c);

	public String getClasseApiTag() {
		return classeApiTag;
	}

	public void setClasseApiTag(String classeApiTag) {
		this.classeApiTag = classeApiTag;
		this.classeApiTagCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiTagInit() {
		if(!classeApiTagCouverture.dejaInitialise) {
			_classeApiTag(classeApiTagCouverture);
			if(classeApiTag == null)
				setClasseApiTag(classeApiTagCouverture.o);
		}
		classeApiTagCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiTag() {
		return classeApiTag;
	}

	public String strClasseApiTag() {
		return classeApiTag == null ? "" : classeApiTag;
	}

	public String nomAffichageClasseApiTag() {
		return null;
	}

	public String htmTooltipClasseApiTag() {
		return null;
	}

	public String htmClasseApiTag() {
		return classeApiTag == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiTag());
	}

	//////////////////////////
	// vertxServiceAddresse //
	//////////////////////////

	/**	L'entité « vertxServiceAddresse »
	 *	 is defined as null before being initialized. 
	 */
	protected String vertxServiceAddresse;
	public Couverture<String> vertxServiceAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("vertxServiceAddresse").o(vertxServiceAddresse);

	/**	<br/>L'entité « vertxServiceAddresse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vertxServiceAddresse">Trouver l'entité vertxServiceAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _vertxServiceAddresse(Couverture<String> c);

	public String getVertxServiceAddresse() {
		return vertxServiceAddresse;
	}

	public void setVertxServiceAddresse(String vertxServiceAddresse) {
		this.vertxServiceAddresse = vertxServiceAddresse;
		this.vertxServiceAddresseCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain vertxServiceAddresseInit() {
		if(!vertxServiceAddresseCouverture.dejaInitialise) {
			_vertxServiceAddresse(vertxServiceAddresseCouverture);
			if(vertxServiceAddresse == null)
				setVertxServiceAddresse(vertxServiceAddresseCouverture.o);
		}
		vertxServiceAddresseCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrVertxServiceAddresse() {
		return vertxServiceAddresse;
	}

	public String strVertxServiceAddresse() {
		return vertxServiceAddresse == null ? "" : vertxServiceAddresse;
	}

	public String nomAffichageVertxServiceAddresse() {
		return null;
	}

	public String htmTooltipVertxServiceAddresse() {
		return null;
	}

	public String htmVertxServiceAddresse() {
		return vertxServiceAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strVertxServiceAddresse());
	}

	/////////////////////
	// classeEtendBase //
	/////////////////////

	/**	L'entité « classeEtendBase »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean classeEtendBase;
	public Couverture<Boolean> classeEtendBaseCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeEtendBase").o(classeEtendBase);

	/**	<br/>L'entité « classeEtendBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeEtendBase">Trouver l'entité classeEtendBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeEtendBase(Couverture<Boolean> c);

	public Boolean getClasseEtendBase() {
		return classeEtendBase;
	}

	public void setClasseEtendBase(Boolean classeEtendBase) {
		this.classeEtendBase = classeEtendBase;
		this.classeEtendBaseCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setClasseEtendBase(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.classeEtendBase = Boolean.parseBoolean(o);
		this.classeEtendBaseCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeEtendBaseInit() {
		if(!classeEtendBaseCouverture.dejaInitialise) {
			_classeEtendBase(classeEtendBaseCouverture);
			if(classeEtendBase == null)
				setClasseEtendBase(classeEtendBaseCouverture.o);
		}
		classeEtendBaseCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Boolean solrClasseEtendBase() {
		return classeEtendBase;
	}

	public String strClasseEtendBase() {
		return classeEtendBase == null ? "" : classeEtendBase.toString();
	}

	public String nomAffichageClasseEtendBase() {
		return null;
	}

	public String htmTooltipClasseEtendBase() {
		return null;
	}

	public String htmClasseEtendBase() {
		return classeEtendBase == null ? "" : StringEscapeUtils.escapeHtml4(strClasseEtendBase());
	}

	///////////////////
	// classeEstBase //
	///////////////////

	/**	L'entité « classeEstBase »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean classeEstBase;
	public Couverture<Boolean> classeEstBaseCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeEstBase").o(classeEstBase);

	/**	<br/>L'entité « classeEstBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeEstBase">Trouver l'entité classeEstBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeEstBase(Couverture<Boolean> c);

	public Boolean getClasseEstBase() {
		return classeEstBase;
	}

	public void setClasseEstBase(Boolean classeEstBase) {
		this.classeEstBase = classeEstBase;
		this.classeEstBaseCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setClasseEstBase(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.classeEstBase = Boolean.parseBoolean(o);
		this.classeEstBaseCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeEstBaseInit() {
		if(!classeEstBaseCouverture.dejaInitialise) {
			_classeEstBase(classeEstBaseCouverture);
			if(classeEstBase == null)
				setClasseEstBase(classeEstBaseCouverture.o);
		}
		classeEstBaseCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Boolean solrClasseEstBase() {
		return classeEstBase;
	}

	public String strClasseEstBase() {
		return classeEstBase == null ? "" : classeEstBase.toString();
	}

	public String nomAffichageClasseEstBase() {
		return null;
	}

	public String htmTooltipClasseEstBase() {
		return null;
	}

	public String htmClasseEstBase() {
		return classeEstBase == null ? "" : StringEscapeUtils.escapeHtml4(strClasseEstBase());
	}

	/////////////////////
	// classeNomSimple //
	/////////////////////

	/**	L'entité « classeNomSimple »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeNomSimple;
	public Couverture<String> classeNomSimpleCouverture = new Couverture<String>().p(this).c(String.class).var("classeNomSimple").o(classeNomSimple);

	/**	<br/>L'entité « classeNomSimple »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeNomSimple">Trouver l'entité classeNomSimple dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeNomSimple(Couverture<String> c);

	public String getClasseNomSimple() {
		return classeNomSimple;
	}

	public void setClasseNomSimple(String classeNomSimple) {
		this.classeNomSimple = classeNomSimple;
		this.classeNomSimpleCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeNomSimpleInit() {
		if(!classeNomSimpleCouverture.dejaInitialise) {
			_classeNomSimple(classeNomSimpleCouverture);
			if(classeNomSimple == null)
				setClasseNomSimple(classeNomSimpleCouverture.o);
		}
		classeNomSimpleCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseNomSimple() {
		return classeNomSimple;
	}

	public String strClasseNomSimple() {
		return classeNomSimple == null ? "" : classeNomSimple;
	}

	public String nomAffichageClasseNomSimple() {
		return null;
	}

	public String htmTooltipClasseNomSimple() {
		return null;
	}

	public String htmClasseNomSimple() {
		return classeNomSimple == null ? "" : StringEscapeUtils.escapeHtml4(strClasseNomSimple());
	}

	////////////////////////
	// classeCheminAbsolu //
	////////////////////////

	/**	L'entité « classeCheminAbsolu »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeCheminAbsolu;
	public Couverture<String> classeCheminAbsoluCouverture = new Couverture<String>().p(this).c(String.class).var("classeCheminAbsolu").o(classeCheminAbsolu);

	/**	<br/>L'entité « classeCheminAbsolu »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeCheminAbsolu">Trouver l'entité classeCheminAbsolu dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeCheminAbsolu(Couverture<String> c);

	public String getClasseCheminAbsolu() {
		return classeCheminAbsolu;
	}

	public void setClasseCheminAbsolu(String classeCheminAbsolu) {
		this.classeCheminAbsolu = classeCheminAbsolu;
		this.classeCheminAbsoluCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeCheminAbsoluInit() {
		if(!classeCheminAbsoluCouverture.dejaInitialise) {
			_classeCheminAbsolu(classeCheminAbsoluCouverture);
			if(classeCheminAbsolu == null)
				setClasseCheminAbsolu(classeCheminAbsoluCouverture.o);
		}
		classeCheminAbsoluCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseCheminAbsolu() {
		return classeCheminAbsolu;
	}

	public String strClasseCheminAbsolu() {
		return classeCheminAbsolu == null ? "" : classeCheminAbsolu;
	}

	public String nomAffichageClasseCheminAbsolu() {
		return null;
	}

	public String htmTooltipClasseCheminAbsolu() {
		return null;
	}

	public String htmClasseCheminAbsolu() {
		return classeCheminAbsolu == null ? "" : StringEscapeUtils.escapeHtml4(strClasseCheminAbsolu());
	}

	/////////////////////////
	// classeApiUriMethode //
	/////////////////////////

	/**	L'entité « classeApiUriMethode »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiUriMethode;
	public Couverture<String> classeApiUriMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiUriMethode").o(classeApiUriMethode);

	/**	<br/>L'entité « classeApiUriMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiUriMethode">Trouver l'entité classeApiUriMethode dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiUriMethode(Couverture<String> c);

	public String getClasseApiUriMethode() {
		return classeApiUriMethode;
	}

	public void setClasseApiUriMethode(String classeApiUriMethode) {
		this.classeApiUriMethode = classeApiUriMethode;
		this.classeApiUriMethodeCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiUriMethodeInit() {
		if(!classeApiUriMethodeCouverture.dejaInitialise) {
			_classeApiUriMethode(classeApiUriMethodeCouverture);
			if(classeApiUriMethode == null)
				setClasseApiUriMethode(classeApiUriMethodeCouverture.o);
		}
		classeApiUriMethodeCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiUriMethode() {
		return classeApiUriMethode;
	}

	public String strClasseApiUriMethode() {
		return classeApiUriMethode == null ? "" : classeApiUriMethode;
	}

	public String nomAffichageClasseApiUriMethode() {
		return null;
	}

	public String htmTooltipClasseApiUriMethode() {
		return null;
	}

	public String htmClasseApiUriMethode() {
		return classeApiUriMethode == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiUriMethode());
	}

	/////////////////////////////
	// classeApiMethodeMethode //
	/////////////////////////////

	/**	L'entité « classeApiMethodeMethode »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiMethodeMethode;
	public Couverture<String> classeApiMethodeMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiMethodeMethode").o(classeApiMethodeMethode);

	/**	<br/>L'entité « classeApiMethodeMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiMethodeMethode">Trouver l'entité classeApiMethodeMethode dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiMethodeMethode(Couverture<String> c);

	public String getClasseApiMethodeMethode() {
		return classeApiMethodeMethode;
	}

	public void setClasseApiMethodeMethode(String classeApiMethodeMethode) {
		this.classeApiMethodeMethode = classeApiMethodeMethode;
		this.classeApiMethodeMethodeCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiMethodeMethodeInit() {
		if(!classeApiMethodeMethodeCouverture.dejaInitialise) {
			_classeApiMethodeMethode(classeApiMethodeMethodeCouverture);
			if(classeApiMethodeMethode == null)
				setClasseApiMethodeMethode(classeApiMethodeMethodeCouverture.o);
		}
		classeApiMethodeMethodeCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiMethodeMethode() {
		return classeApiMethodeMethode;
	}

	public String strClasseApiMethodeMethode() {
		return classeApiMethodeMethode == null ? "" : classeApiMethodeMethode;
	}

	public String nomAffichageClasseApiMethodeMethode() {
		return null;
	}

	public String htmTooltipClasseApiMethodeMethode() {
		return null;
	}

	public String htmClasseApiMethodeMethode() {
		return classeApiMethodeMethode == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiMethodeMethode());
	}

	//////////////////////////////////
	// classeApiTypeMedia200Methode //
	//////////////////////////////////

	/**	L'entité « classeApiTypeMedia200Methode »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiTypeMedia200Methode;
	public Couverture<String> classeApiTypeMedia200MethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiTypeMedia200Methode").o(classeApiTypeMedia200Methode);

	/**	<br/>L'entité « classeApiTypeMedia200Methode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiTypeMedia200Methode">Trouver l'entité classeApiTypeMedia200Methode dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiTypeMedia200Methode(Couverture<String> c);

	public String getClasseApiTypeMedia200Methode() {
		return classeApiTypeMedia200Methode;
	}

	public void setClasseApiTypeMedia200Methode(String classeApiTypeMedia200Methode) {
		this.classeApiTypeMedia200Methode = classeApiTypeMedia200Methode;
		this.classeApiTypeMedia200MethodeCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiTypeMedia200MethodeInit() {
		if(!classeApiTypeMedia200MethodeCouverture.dejaInitialise) {
			_classeApiTypeMedia200Methode(classeApiTypeMedia200MethodeCouverture);
			if(classeApiTypeMedia200Methode == null)
				setClasseApiTypeMedia200Methode(classeApiTypeMedia200MethodeCouverture.o);
		}
		classeApiTypeMedia200MethodeCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiTypeMedia200Methode() {
		return classeApiTypeMedia200Methode;
	}

	public String strClasseApiTypeMedia200Methode() {
		return classeApiTypeMedia200Methode == null ? "" : classeApiTypeMedia200Methode;
	}

	public String nomAffichageClasseApiTypeMedia200Methode() {
		return null;
	}

	public String htmTooltipClasseApiTypeMedia200Methode() {
		return null;
	}

	public String htmClasseApiTypeMedia200Methode() {
		return classeApiTypeMedia200Methode == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiTypeMedia200Methode());
	}

	/////////////////////////////////
	// classeApiOperationIdMethode //
	/////////////////////////////////

	/**	L'entité « classeApiOperationIdMethode »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiOperationIdMethode;
	public Couverture<String> classeApiOperationIdMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiOperationIdMethode").o(classeApiOperationIdMethode);

	/**	<br/>L'entité « classeApiOperationIdMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiOperationIdMethode">Trouver l'entité classeApiOperationIdMethode dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiOperationIdMethode(Couverture<String> c);

	public String getClasseApiOperationIdMethode() {
		return classeApiOperationIdMethode;
	}

	public void setClasseApiOperationIdMethode(String classeApiOperationIdMethode) {
		this.classeApiOperationIdMethode = classeApiOperationIdMethode;
		this.classeApiOperationIdMethodeCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiOperationIdMethodeInit() {
		if(!classeApiOperationIdMethodeCouverture.dejaInitialise) {
			_classeApiOperationIdMethode(classeApiOperationIdMethodeCouverture);
			if(classeApiOperationIdMethode == null)
				setClasseApiOperationIdMethode(classeApiOperationIdMethodeCouverture.o);
		}
		classeApiOperationIdMethodeCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiOperationIdMethode() {
		return classeApiOperationIdMethode;
	}

	public String strClasseApiOperationIdMethode() {
		return classeApiOperationIdMethode == null ? "" : classeApiOperationIdMethode;
	}

	public String nomAffichageClasseApiOperationIdMethode() {
		return null;
	}

	public String htmTooltipClasseApiOperationIdMethode() {
		return null;
	}

	public String htmClasseApiOperationIdMethode() {
		return classeApiOperationIdMethode == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiOperationIdMethode());
	}

	////////////////////////////////////////
	// classeApiOperationIdMethodeRequete //
	////////////////////////////////////////

	/**	L'entité « classeApiOperationIdMethodeRequete »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiOperationIdMethodeRequete;
	public Couverture<String> classeApiOperationIdMethodeRequeteCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiOperationIdMethodeRequete").o(classeApiOperationIdMethodeRequete);

	/**	<br/>L'entité « classeApiOperationIdMethodeRequete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiOperationIdMethodeRequete">Trouver l'entité classeApiOperationIdMethodeRequete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiOperationIdMethodeRequete(Couverture<String> c);

	public String getClasseApiOperationIdMethodeRequete() {
		return classeApiOperationIdMethodeRequete;
	}

	public void setClasseApiOperationIdMethodeRequete(String classeApiOperationIdMethodeRequete) {
		this.classeApiOperationIdMethodeRequete = classeApiOperationIdMethodeRequete;
		this.classeApiOperationIdMethodeRequeteCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiOperationIdMethodeRequeteInit() {
		if(!classeApiOperationIdMethodeRequeteCouverture.dejaInitialise) {
			_classeApiOperationIdMethodeRequete(classeApiOperationIdMethodeRequeteCouverture);
			if(classeApiOperationIdMethodeRequete == null)
				setClasseApiOperationIdMethodeRequete(classeApiOperationIdMethodeRequeteCouverture.o);
		}
		classeApiOperationIdMethodeRequeteCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiOperationIdMethodeRequete() {
		return classeApiOperationIdMethodeRequete;
	}

	public String strClasseApiOperationIdMethodeRequete() {
		return classeApiOperationIdMethodeRequete == null ? "" : classeApiOperationIdMethodeRequete;
	}

	public String nomAffichageClasseApiOperationIdMethodeRequete() {
		return null;
	}

	public String htmTooltipClasseApiOperationIdMethodeRequete() {
		return null;
	}

	public String htmClasseApiOperationIdMethodeRequete() {
		return classeApiOperationIdMethodeRequete == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiOperationIdMethodeRequete());
	}

	////////////////////////////////////////
	// classeApiOperationIdMethodeReponse //
	////////////////////////////////////////

	/**	L'entité « classeApiOperationIdMethodeReponse »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeApiOperationIdMethodeReponse;
	public Couverture<String> classeApiOperationIdMethodeReponseCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiOperationIdMethodeReponse").o(classeApiOperationIdMethodeReponse);

	/**	<br/>L'entité « classeApiOperationIdMethodeReponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiOperationIdMethodeReponse">Trouver l'entité classeApiOperationIdMethodeReponse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeApiOperationIdMethodeReponse(Couverture<String> c);

	public String getClasseApiOperationIdMethodeReponse() {
		return classeApiOperationIdMethodeReponse;
	}

	public void setClasseApiOperationIdMethodeReponse(String classeApiOperationIdMethodeReponse) {
		this.classeApiOperationIdMethodeReponse = classeApiOperationIdMethodeReponse;
		this.classeApiOperationIdMethodeReponseCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeApiOperationIdMethodeReponseInit() {
		if(!classeApiOperationIdMethodeReponseCouverture.dejaInitialise) {
			_classeApiOperationIdMethodeReponse(classeApiOperationIdMethodeReponseCouverture);
			if(classeApiOperationIdMethodeReponse == null)
				setClasseApiOperationIdMethodeReponse(classeApiOperationIdMethodeReponseCouverture.o);
		}
		classeApiOperationIdMethodeReponseCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseApiOperationIdMethodeReponse() {
		return classeApiOperationIdMethodeReponse;
	}

	public String strClasseApiOperationIdMethodeReponse() {
		return classeApiOperationIdMethodeReponse == null ? "" : classeApiOperationIdMethodeReponse;
	}

	public String nomAffichageClasseApiOperationIdMethodeReponse() {
		return null;
	}

	public String htmTooltipClasseApiOperationIdMethodeReponse() {
		return null;
	}

	public String htmClasseApiOperationIdMethodeReponse() {
		return classeApiOperationIdMethodeReponse == null ? "" : StringEscapeUtils.escapeHtml4(strClasseApiOperationIdMethodeReponse());
	}

	/////////////////////////////////////////////
	// classeSuperApiOperationIdMethodeRequete //
	/////////////////////////////////////////////

	/**	L'entité « classeSuperApiOperationIdMethodeRequete »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeSuperApiOperationIdMethodeRequete;
	public Couverture<String> classeSuperApiOperationIdMethodeRequeteCouverture = new Couverture<String>().p(this).c(String.class).var("classeSuperApiOperationIdMethodeRequete").o(classeSuperApiOperationIdMethodeRequete);

	/**	<br/>L'entité « classeSuperApiOperationIdMethodeRequete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeSuperApiOperationIdMethodeRequete">Trouver l'entité classeSuperApiOperationIdMethodeRequete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeSuperApiOperationIdMethodeRequete(Couverture<String> c);

	public String getClasseSuperApiOperationIdMethodeRequete() {
		return classeSuperApiOperationIdMethodeRequete;
	}

	public void setClasseSuperApiOperationIdMethodeRequete(String classeSuperApiOperationIdMethodeRequete) {
		this.classeSuperApiOperationIdMethodeRequete = classeSuperApiOperationIdMethodeRequete;
		this.classeSuperApiOperationIdMethodeRequeteCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeSuperApiOperationIdMethodeRequeteInit() {
		if(!classeSuperApiOperationIdMethodeRequeteCouverture.dejaInitialise) {
			_classeSuperApiOperationIdMethodeRequete(classeSuperApiOperationIdMethodeRequeteCouverture);
			if(classeSuperApiOperationIdMethodeRequete == null)
				setClasseSuperApiOperationIdMethodeRequete(classeSuperApiOperationIdMethodeRequeteCouverture.o);
		}
		classeSuperApiOperationIdMethodeRequeteCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseSuperApiOperationIdMethodeRequete() {
		return classeSuperApiOperationIdMethodeRequete;
	}

	public String strClasseSuperApiOperationIdMethodeRequete() {
		return classeSuperApiOperationIdMethodeRequete == null ? "" : classeSuperApiOperationIdMethodeRequete;
	}

	public String nomAffichageClasseSuperApiOperationIdMethodeRequete() {
		return null;
	}

	public String htmTooltipClasseSuperApiOperationIdMethodeRequete() {
		return null;
	}

	public String htmClasseSuperApiOperationIdMethodeRequete() {
		return classeSuperApiOperationIdMethodeRequete == null ? "" : StringEscapeUtils.escapeHtml4(strClasseSuperApiOperationIdMethodeRequete());
	}

	/////////////////////////////////////////////
	// classeSuperApiOperationIdMethodeReponse //
	/////////////////////////////////////////////

	/**	L'entité « classeSuperApiOperationIdMethodeReponse »
	 *	 is defined as null before being initialized. 
	 */
	protected String classeSuperApiOperationIdMethodeReponse;
	public Couverture<String> classeSuperApiOperationIdMethodeReponseCouverture = new Couverture<String>().p(this).c(String.class).var("classeSuperApiOperationIdMethodeReponse").o(classeSuperApiOperationIdMethodeReponse);

	/**	<br/>L'entité « classeSuperApiOperationIdMethodeReponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeSuperApiOperationIdMethodeReponse">Trouver l'entité classeSuperApiOperationIdMethodeReponse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeSuperApiOperationIdMethodeReponse(Couverture<String> c);

	public String getClasseSuperApiOperationIdMethodeReponse() {
		return classeSuperApiOperationIdMethodeReponse;
	}

	public void setClasseSuperApiOperationIdMethodeReponse(String classeSuperApiOperationIdMethodeReponse) {
		this.classeSuperApiOperationIdMethodeReponse = classeSuperApiOperationIdMethodeReponse;
		this.classeSuperApiOperationIdMethodeReponseCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classeSuperApiOperationIdMethodeReponseInit() {
		if(!classeSuperApiOperationIdMethodeReponseCouverture.dejaInitialise) {
			_classeSuperApiOperationIdMethodeReponse(classeSuperApiOperationIdMethodeReponseCouverture);
			if(classeSuperApiOperationIdMethodeReponse == null)
				setClasseSuperApiOperationIdMethodeReponse(classeSuperApiOperationIdMethodeReponseCouverture.o);
		}
		classeSuperApiOperationIdMethodeReponseCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClasseSuperApiOperationIdMethodeReponse() {
		return classeSuperApiOperationIdMethodeReponse;
	}

	public String strClasseSuperApiOperationIdMethodeReponse() {
		return classeSuperApiOperationIdMethodeReponse == null ? "" : classeSuperApiOperationIdMethodeReponse;
	}

	public String nomAffichageClasseSuperApiOperationIdMethodeReponse() {
		return null;
	}

	public String htmTooltipClasseSuperApiOperationIdMethodeReponse() {
		return null;
	}

	public String htmClasseSuperApiOperationIdMethodeReponse() {
		return classeSuperApiOperationIdMethodeReponse == null ? "" : StringEscapeUtils.escapeHtml4(strClasseSuperApiOperationIdMethodeReponse());
	}

	///////////////////////////
	// classeMotsClesTrouves //
	///////////////////////////

	/**	L'entité « classeMotsClesTrouves »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean classeMotsClesTrouves;
	public Couverture<Boolean> classeMotsClesTrouvesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeMotsClesTrouves").o(classeMotsClesTrouves);

	/**	<br/>L'entité « classeMotsClesTrouves »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeMotsClesTrouves">Trouver l'entité classeMotsClesTrouves dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeMotsClesTrouves(Couverture<Boolean> c);

	public Boolean getClasseMotsClesTrouves() {
		return classeMotsClesTrouves;
	}

	public void setClasseMotsClesTrouves(Boolean classeMotsClesTrouves) {
		this.classeMotsClesTrouves = classeMotsClesTrouves;
		this.classeMotsClesTrouvesCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setClasseMotsClesTrouves(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.classeMotsClesTrouves = Boolean.parseBoolean(o);
		this.classeMotsClesTrouvesCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeMotsClesTrouvesInit() {
		if(!classeMotsClesTrouvesCouverture.dejaInitialise) {
			_classeMotsClesTrouves(classeMotsClesTrouvesCouverture);
			if(classeMotsClesTrouves == null)
				setClasseMotsClesTrouves(classeMotsClesTrouvesCouverture.o);
		}
		classeMotsClesTrouvesCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Boolean solrClasseMotsClesTrouves() {
		return classeMotsClesTrouves;
	}

	public String strClasseMotsClesTrouves() {
		return classeMotsClesTrouves == null ? "" : classeMotsClesTrouves.toString();
	}

	public String nomAffichageClasseMotsClesTrouves() {
		return null;
	}

	public String htmTooltipClasseMotsClesTrouves() {
		return null;
	}

	public String htmClasseMotsClesTrouves() {
		return classeMotsClesTrouves == null ? "" : StringEscapeUtils.escapeHtml4(strClasseMotsClesTrouves());
	}

	////////////////////
	// classeMotsCles //
	////////////////////

	/**	L'entité « classeMotsCles »
	 *	 is defined as null before being initialized. 
	 */
	protected List<String> classeMotsCles;
	public Couverture<List<String>> classeMotsClesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeMotsCles").o(classeMotsCles);

	/**	<br/>L'entité « classeMotsCles »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeMotsCles">Trouver l'entité classeMotsCles dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeMotsCles(Couverture<List<String>> c);

	public List<String> getClasseMotsCles() {
		return classeMotsCles;
	}

	public void setClasseMotsCles(List<String> classeMotsCles) {
		this.classeMotsCles = classeMotsCles;
		this.classeMotsClesCouverture.dejaInitialise = true;
	}
	public ApiEcrivain addClasseMotsCles(String...objets) {
		for(String o : objets) {
			addClasseMotsCles(o);
		}
		return (ApiEcrivain)this;
	}
	public ApiEcrivain addClasseMotsCles(String o) {
		if(o != null && !classeMotsCles.contains(o))
			this.classeMotsCles.add(o);
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeMotsClesInit() {
		if(!classeMotsClesCouverture.dejaInitialise) {
			_classeMotsCles(classeMotsClesCouverture);
			if(classeMotsCles == null)
				setClasseMotsCles(classeMotsClesCouverture.o);
		}
		classeMotsClesCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public List<String> solrClasseMotsCles() {
		return classeMotsCles;
	}

	public String strClasseMotsCles() {
		return classeMotsCles == null ? "" : classeMotsCles.toString();
	}

	public String nomAffichageClasseMotsCles() {
		return null;
	}

	public String htmTooltipClasseMotsCles() {
		return null;
	}

	public String htmClasseMotsCles() {
		return classeMotsCles == null ? "" : StringEscapeUtils.escapeHtml4(strClasseMotsCles());
	}

	////////////////////////
	// classeRolesTrouves //
	////////////////////////

	/**	L'entité « classeRolesTrouves »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean classeRolesTrouves;
	public Couverture<Boolean> classeRolesTrouvesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeRolesTrouves").o(classeRolesTrouves);

	/**	<br/>L'entité « classeRolesTrouves »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeRolesTrouves">Trouver l'entité classeRolesTrouves dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeRolesTrouves(Couverture<Boolean> c);

	public Boolean getClasseRolesTrouves() {
		return classeRolesTrouves;
	}

	public void setClasseRolesTrouves(Boolean classeRolesTrouves) {
		this.classeRolesTrouves = classeRolesTrouves;
		this.classeRolesTrouvesCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setClasseRolesTrouves(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.classeRolesTrouves = Boolean.parseBoolean(o);
		this.classeRolesTrouvesCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeRolesTrouvesInit() {
		if(!classeRolesTrouvesCouverture.dejaInitialise) {
			_classeRolesTrouves(classeRolesTrouvesCouverture);
			if(classeRolesTrouves == null)
				setClasseRolesTrouves(classeRolesTrouvesCouverture.o);
		}
		classeRolesTrouvesCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Boolean solrClasseRolesTrouves() {
		return classeRolesTrouves;
	}

	public String strClasseRolesTrouves() {
		return classeRolesTrouves == null ? "" : classeRolesTrouves.toString();
	}

	public String nomAffichageClasseRolesTrouves() {
		return null;
	}

	public String htmTooltipClasseRolesTrouves() {
		return null;
	}

	public String htmClasseRolesTrouves() {
		return classeRolesTrouves == null ? "" : StringEscapeUtils.escapeHtml4(strClasseRolesTrouves());
	}

	/////////////////
	// classeRoles //
	/////////////////

	/**	L'entité « classeRoles »
	 *	 is defined as null before being initialized. 
	 */
	protected List<String> classeRoles;
	public Couverture<List<String>> classeRolesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeRoles").o(classeRoles);

	/**	<br/>L'entité « classeRoles »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeRoles">Trouver l'entité classeRoles dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeRoles(Couverture<List<String>> c);

	public List<String> getClasseRoles() {
		return classeRoles;
	}

	public void setClasseRoles(List<String> classeRoles) {
		this.classeRoles = classeRoles;
		this.classeRolesCouverture.dejaInitialise = true;
	}
	public ApiEcrivain addClasseRoles(String...objets) {
		for(String o : objets) {
			addClasseRoles(o);
		}
		return (ApiEcrivain)this;
	}
	public ApiEcrivain addClasseRoles(String o) {
		if(o != null && !classeRoles.contains(o))
			this.classeRoles.add(o);
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeRolesInit() {
		if(!classeRolesCouverture.dejaInitialise) {
			_classeRoles(classeRolesCouverture);
			if(classeRoles == null)
				setClasseRoles(classeRolesCouverture.o);
		}
		classeRolesCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public List<String> solrClasseRoles() {
		return classeRoles;
	}

	public String strClasseRoles() {
		return classeRoles == null ? "" : classeRoles.toString();
	}

	public String nomAffichageClasseRoles() {
		return null;
	}

	public String htmTooltipClasseRoles() {
		return null;
	}

	public String htmClasseRoles() {
		return classeRoles == null ? "" : StringEscapeUtils.escapeHtml4(strClasseRoles());
	}

	////////////////////////
	// entiteDocumentSolr //
	////////////////////////

	/**	L'entité « entiteDocumentSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument entiteDocumentSolr;
	public Couverture<SolrDocument> entiteDocumentSolrCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("entiteDocumentSolr").o(entiteDocumentSolr);

	/**	<br/>L'entité « entiteDocumentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:entiteDocumentSolr">Trouver l'entité entiteDocumentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _entiteDocumentSolr(Couverture<SolrDocument> c);

	public SolrDocument getEntiteDocumentSolr() {
		return entiteDocumentSolr;
	}

	public void setEntiteDocumentSolr(SolrDocument entiteDocumentSolr) {
		this.entiteDocumentSolr = entiteDocumentSolr;
		this.entiteDocumentSolrCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain entiteDocumentSolrInit() {
		if(!entiteDocumentSolrCouverture.dejaInitialise) {
			_entiteDocumentSolr(entiteDocumentSolrCouverture);
			if(entiteDocumentSolr == null)
				setEntiteDocumentSolr(entiteDocumentSolrCouverture.o);
		}
		entiteDocumentSolrCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseApiEcrivain = false;

	public ApiEcrivain initLoinApiEcrivain(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseApiEcrivain) {
			dejaInitialiseApiEcrivain = true;
			initLoinApiEcrivain();
		}
		return (ApiEcrivain)this;
	}

	public void initLoinApiEcrivain() {
		initApiEcrivain();
	}

	public void initApiEcrivain() {
		classeDocumentSolrInit();
		classeApiMethodeInit();
		openApiVersionInit();
		appSwagger2Init();
		classeUrisInit();
		openApiVersionNumeroInit();
		tabsSchemaInit();
		tabsReponsesInit();
		wCheminsInit();
		wCorpsRequetesInit();
		wSchemasInit();
		siteContexteInit();
		configSiteInit();
		wRequeteEnTeteInit();
		wRequeteDescriptionInit();
		wReponseDescriptionInit();
		wRequeteCorpsInit();
		wReponseCorpsInit();
		wRequeteSchemaInit();
		wReponseSchemaInit();
		ecrivainsInit();
		classeApiTagInit();
		vertxServiceAddresseInit();
		classeEtendBaseInit();
		classeEstBaseInit();
		classeNomSimpleInit();
		classeCheminAbsoluInit();
		classeApiUriMethodeInit();
		classeApiMethodeMethodeInit();
		classeApiTypeMedia200MethodeInit();
		classeApiOperationIdMethodeInit();
		classeApiOperationIdMethodeRequeteInit();
		classeApiOperationIdMethodeReponseInit();
		classeSuperApiOperationIdMethodeRequeteInit();
		classeSuperApiOperationIdMethodeReponseInit();
		classeMotsClesTrouvesInit();
		classeMotsClesInit();
		classeRolesTrouvesInit();
		classeRolesInit();
		entiteDocumentSolrInit();
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinApiEcrivain(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteApiEcrivain(RequeteSite requeteSite_) {
		wChemins.setRequeteSite_(requeteSite_);
		wCorpsRequetes.setRequeteSite_(requeteSite_);
		wSchemas.setRequeteSite_(requeteSite_);
		siteContexte.setRequeteSite_(requeteSite_);
		configSite.setRequeteSite_(requeteSite_);
		wRequeteEnTete.setRequeteSite_(requeteSite_);
		wRequeteDescription.setRequeteSite_(requeteSite_);
		wReponseDescription.setRequeteSite_(requeteSite_);
		wRequeteCorps.setRequeteSite_(requeteSite_);
		wReponseCorps.setRequeteSite_(requeteSite_);
		wRequeteSchema.setRequeteSite_(requeteSite_);
		wReponseSchema.setRequeteSite_(requeteSite_);
		ecrivains.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteApiEcrivain(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirApiEcrivain(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirApiEcrivain(String var) throws Exception {
		ApiEcrivain oApiEcrivain = (ApiEcrivain)this;
		switch(var) {
			case "requeteSite_":
				return oApiEcrivain.requeteSite_;
			case "classeDocumentSolr":
				return oApiEcrivain.classeDocumentSolr;
			case "classeApiMethode":
				return oApiEcrivain.classeApiMethode;
			case "openApiVersion":
				return oApiEcrivain.openApiVersion;
			case "appSwagger2":
				return oApiEcrivain.appSwagger2;
			case "classeUris":
				return oApiEcrivain.classeUris;
			case "openApiVersionNumero":
				return oApiEcrivain.openApiVersionNumero;
			case "tabsSchema":
				return oApiEcrivain.tabsSchema;
			case "tabsReponses":
				return oApiEcrivain.tabsReponses;
			case "wChemins":
				return oApiEcrivain.wChemins;
			case "wCorpsRequetes":
				return oApiEcrivain.wCorpsRequetes;
			case "wSchemas":
				return oApiEcrivain.wSchemas;
			case "siteContexte":
				return oApiEcrivain.siteContexte;
			case "configSite":
				return oApiEcrivain.configSite;
			case "wRequeteEnTete":
				return oApiEcrivain.wRequeteEnTete;
			case "wRequeteDescription":
				return oApiEcrivain.wRequeteDescription;
			case "wReponseDescription":
				return oApiEcrivain.wReponseDescription;
			case "wRequeteCorps":
				return oApiEcrivain.wRequeteCorps;
			case "wReponseCorps":
				return oApiEcrivain.wReponseCorps;
			case "wRequeteSchema":
				return oApiEcrivain.wRequeteSchema;
			case "wReponseSchema":
				return oApiEcrivain.wReponseSchema;
			case "ecrivains":
				return oApiEcrivain.ecrivains;
			case "classeApiTag":
				return oApiEcrivain.classeApiTag;
			case "vertxServiceAddresse":
				return oApiEcrivain.vertxServiceAddresse;
			case "classeEtendBase":
				return oApiEcrivain.classeEtendBase;
			case "classeEstBase":
				return oApiEcrivain.classeEstBase;
			case "classeNomSimple":
				return oApiEcrivain.classeNomSimple;
			case "classeCheminAbsolu":
				return oApiEcrivain.classeCheminAbsolu;
			case "classeApiUriMethode":
				return oApiEcrivain.classeApiUriMethode;
			case "classeApiMethodeMethode":
				return oApiEcrivain.classeApiMethodeMethode;
			case "classeApiTypeMedia200Methode":
				return oApiEcrivain.classeApiTypeMedia200Methode;
			case "classeApiOperationIdMethode":
				return oApiEcrivain.classeApiOperationIdMethode;
			case "classeApiOperationIdMethodeRequete":
				return oApiEcrivain.classeApiOperationIdMethodeRequete;
			case "classeApiOperationIdMethodeReponse":
				return oApiEcrivain.classeApiOperationIdMethodeReponse;
			case "classeSuperApiOperationIdMethodeRequete":
				return oApiEcrivain.classeSuperApiOperationIdMethodeRequete;
			case "classeSuperApiOperationIdMethodeReponse":
				return oApiEcrivain.classeSuperApiOperationIdMethodeReponse;
			case "classeMotsClesTrouves":
				return oApiEcrivain.classeMotsClesTrouves;
			case "classeMotsCles":
				return oApiEcrivain.classeMotsCles;
			case "classeRolesTrouves":
				return oApiEcrivain.classeRolesTrouves;
			case "classeRoles":
				return oApiEcrivain.classeRoles;
			case "entiteDocumentSolr":
				return oApiEcrivain.entiteDocumentSolr;
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
				o = attribuerApiEcrivain(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerApiEcrivain(String var, Object val) {
		ApiEcrivain oApiEcrivain = (ApiEcrivain)this;
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
					o = definirApiEcrivain(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirApiEcrivain(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(classeApiMethode, openApiVersion, classeUris, openApiVersionNumero, tabsSchema, tabsReponses, classeApiTag, vertxServiceAddresse, classeEtendBase, classeEstBase, classeNomSimple, classeCheminAbsolu, classeApiUriMethode, classeApiMethodeMethode, classeApiTypeMedia200Methode, classeApiOperationIdMethode, classeApiOperationIdMethodeRequete, classeApiOperationIdMethodeReponse, classeSuperApiOperationIdMethodeRequete, classeSuperApiOperationIdMethodeReponse, classeMotsClesTrouves, classeMotsCles, classeRolesTrouves, classeRoles);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof ApiEcrivain))
			return false;
		ApiEcrivain that = (ApiEcrivain)o;
		return Objects.equals( classeApiMethode, that.classeApiMethode )
				&& Objects.equals( openApiVersion, that.openApiVersion )
				&& Objects.equals( classeUris, that.classeUris )
				&& Objects.equals( openApiVersionNumero, that.openApiVersionNumero )
				&& Objects.equals( tabsSchema, that.tabsSchema )
				&& Objects.equals( tabsReponses, that.tabsReponses )
				&& Objects.equals( classeApiTag, that.classeApiTag )
				&& Objects.equals( vertxServiceAddresse, that.vertxServiceAddresse )
				&& Objects.equals( classeEtendBase, that.classeEtendBase )
				&& Objects.equals( classeEstBase, that.classeEstBase )
				&& Objects.equals( classeNomSimple, that.classeNomSimple )
				&& Objects.equals( classeCheminAbsolu, that.classeCheminAbsolu )
				&& Objects.equals( classeApiUriMethode, that.classeApiUriMethode )
				&& Objects.equals( classeApiMethodeMethode, that.classeApiMethodeMethode )
				&& Objects.equals( classeApiTypeMedia200Methode, that.classeApiTypeMedia200Methode )
				&& Objects.equals( classeApiOperationIdMethode, that.classeApiOperationIdMethode )
				&& Objects.equals( classeApiOperationIdMethodeRequete, that.classeApiOperationIdMethodeRequete )
				&& Objects.equals( classeApiOperationIdMethodeReponse, that.classeApiOperationIdMethodeReponse )
				&& Objects.equals( classeSuperApiOperationIdMethodeRequete, that.classeSuperApiOperationIdMethodeRequete )
				&& Objects.equals( classeSuperApiOperationIdMethodeReponse, that.classeSuperApiOperationIdMethodeReponse )
				&& Objects.equals( classeMotsClesTrouves, that.classeMotsClesTrouves )
				&& Objects.equals( classeMotsCles, that.classeMotsCles )
				&& Objects.equals( classeRolesTrouves, that.classeRolesTrouves )
				&& Objects.equals( classeRoles, that.classeRoles );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ApiEcrivain {");
		sb.append( "classeApiMethode: \"" ).append(classeApiMethode).append( "\"" );
		sb.append( ", openApiVersion: \"" ).append(openApiVersion).append( "\"" );
		sb.append( ", classeUris: " ).append(classeUris);
		sb.append( ", openApiVersionNumero: " ).append(openApiVersionNumero);
		sb.append( ", tabsSchema: " ).append(tabsSchema);
		sb.append( ", tabsReponses: " ).append(tabsReponses);
		sb.append( ", classeApiTag: \"" ).append(classeApiTag).append( "\"" );
		sb.append( ", vertxServiceAddresse: \"" ).append(vertxServiceAddresse).append( "\"" );
		sb.append( ", classeEtendBase: " ).append(classeEtendBase);
		sb.append( ", classeEstBase: " ).append(classeEstBase);
		sb.append( ", classeNomSimple: \"" ).append(classeNomSimple).append( "\"" );
		sb.append( ", classeCheminAbsolu: \"" ).append(classeCheminAbsolu).append( "\"" );
		sb.append( ", classeApiUriMethode: \"" ).append(classeApiUriMethode).append( "\"" );
		sb.append( ", classeApiMethodeMethode: \"" ).append(classeApiMethodeMethode).append( "\"" );
		sb.append( ", classeApiTypeMedia200Methode: \"" ).append(classeApiTypeMedia200Methode).append( "\"" );
		sb.append( ", classeApiOperationIdMethode: \"" ).append(classeApiOperationIdMethode).append( "\"" );
		sb.append( ", classeApiOperationIdMethodeRequete: \"" ).append(classeApiOperationIdMethodeRequete).append( "\"" );
		sb.append( ", classeApiOperationIdMethodeReponse: \"" ).append(classeApiOperationIdMethodeReponse).append( "\"" );
		sb.append( ", classeSuperApiOperationIdMethodeRequete: \"" ).append(classeSuperApiOperationIdMethodeRequete).append( "\"" );
		sb.append( ", classeSuperApiOperationIdMethodeReponse: \"" ).append(classeSuperApiOperationIdMethodeReponse).append( "\"" );
		sb.append( ", classeMotsClesTrouves: " ).append(classeMotsClesTrouves);
		sb.append( ", classeMotsCles: " ).append(classeMotsCles);
		sb.append( ", classeRolesTrouves: " ).append(classeRolesTrouves);
		sb.append( ", classeRoles: " ).append(classeRoles);
		sb.append(" }");
		return sb.toString();
	}
}
