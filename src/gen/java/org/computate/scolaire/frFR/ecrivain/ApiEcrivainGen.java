package org.computate.scolaire.frFR.ecrivain;

import org.computate.scolaire.frFR.ecrivain.TousEcrivains;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.vertx.AppliSwagger2;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ApiEcrivainGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ApiEcrivain.class);

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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
	protected ApiEcrivain requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////////////
	// classeDocumentSolr //
	////////////////////////

	/**	L'entité « classeDocumentSolr »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocument classeDocumentSolr;
	@JsonIgnore
	public Couverture<SolrDocument> classeDocumentSolrCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("classeDocumentSolr").o(classeDocumentSolr);

	/**	<br/>L'entité « classeDocumentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeDocumentSolr">Trouver l'entité classeDocumentSolr dans Solr</a>
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

	//////////////////
	// contexteRows //
	//////////////////

	/**	L'entité « contexteRows »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer contexteRows;
	@JsonIgnore
	public Couverture<Integer> contexteRowsCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("contexteRows").o(contexteRows);

	/**	<br/>L'entité « contexteRows »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:contexteRows">Trouver l'entité contexteRows dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contexteRows(Couverture<Integer> c);

	public Integer getContexteRows() {
		return contexteRows;
	}

	public void setContexteRows(Integer contexteRows) {
		this.contexteRows = contexteRows;
		this.contexteRowsCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setContexteRows(String o) {
		if(NumberUtils.isParsable(o))
			this.contexteRows = Integer.parseInt(o);
		this.contexteRowsCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain contexteRowsInit() {
		if(!contexteRowsCouverture.dejaInitialise) {
			_contexteRows(contexteRowsCouverture);
			if(contexteRows == null)
				setContexteRows(contexteRowsCouverture.o);
		}
		contexteRowsCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Integer solrContexteRows() {
		return contexteRows;
	}

	public String strContexteRows() {
		return contexteRows == null ? "" : contexteRows.toString();
	}

	public String jsonContexteRows() {
		return contexteRows == null ? "" : contexteRows.toString();
	}

	public String nomAffichageContexteRows() {
		return null;
	}

	public String htmTooltipContexteRows() {
		return null;
	}

	public String htmContexteRows() {
		return contexteRows == null ? "" : StringEscapeUtils.escapeHtml4(strContexteRows());
	}

	//////////////////////
	// classeApiMethode //
	//////////////////////

	/**	L'entité « classeApiMethode »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classeApiMethode;
	@JsonIgnore
	public Couverture<String> classeApiMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiMethode").o(classeApiMethode);

	/**	<br/>L'entité « classeApiMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiMethode">Trouver l'entité classeApiMethode dans Solr</a>
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

	public String jsonClasseApiMethode() {
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
	@JsonInclude(Include.NON_NULL)
	protected String openApiVersion;
	@JsonIgnore
	public Couverture<String> openApiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/>L'entité « openApiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
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

	public String jsonOpenApiVersion() {
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
	@JsonInclude(Include.NON_NULL)
	protected AppliSwagger2 appSwagger2;
	@JsonIgnore
	public Couverture<AppliSwagger2> appSwagger2Couverture = new Couverture<AppliSwagger2>().p(this).c(AppliSwagger2.class).var("appSwagger2").o(appSwagger2);

	/**	<br/>L'entité « appSwagger2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appSwagger2">Trouver l'entité appSwagger2 dans Solr</a>
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
		if(appSwagger2 != null)
			appSwagger2.initLoinPourClasse(requeteSite_);
		appSwagger2Couverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	////////////////
	// classeUris //
	////////////////

	/**	L'entité « classeUris »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classeUris;
	@JsonIgnore
	public Couverture<List<String>> classeUrisCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeUris").o(classeUris);

	/**	<br/>L'entité « classeUris »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeUris">Trouver l'entité classeUris dans Solr</a>
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
	public ApiEcrivain setClasseUris(JsonArray objets) {
		classeUris.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasseUris(o);
		}
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

	public String jsonClasseUris() {
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
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer openApiVersionNumero;
	@JsonIgnore
	public Couverture<Integer> openApiVersionNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("openApiVersionNumero").o(openApiVersionNumero);

	/**	<br/>L'entité « openApiVersionNumero »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersionNumero">Trouver l'entité openApiVersionNumero dans Solr</a>
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
		if(NumberUtils.isParsable(o))
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

	public String jsonOpenApiVersionNumero() {
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
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer tabsSchema;
	@JsonIgnore
	public Couverture<Integer> tabsSchemaCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("tabsSchema").o(tabsSchema);

	/**	<br/>L'entité « tabsSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tabsSchema">Trouver l'entité tabsSchema dans Solr</a>
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
		if(NumberUtils.isParsable(o))
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

	public String jsonTabsSchema() {
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
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer tabsReponses;
	@JsonIgnore
	public Couverture<Integer> tabsReponsesCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("tabsReponses").o(tabsReponses);

	/**	<br/>L'entité « tabsReponses »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tabsReponses">Trouver l'entité tabsReponses dans Solr</a>
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
		if(NumberUtils.isParsable(o))
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

	public String jsonTabsReponses() {
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wChemins;
	@JsonIgnore
	public Couverture<ToutEcrivain> wCheminsCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wChemins").o(wChemins);

	/**	<br/>L'entité « wChemins »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wChemins">Trouver l'entité wChemins dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wCorpsRequetes;
	@JsonIgnore
	public Couverture<ToutEcrivain> wCorpsRequetesCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wCorpsRequetes").o(wCorpsRequetes);

	/**	<br/>L'entité « wCorpsRequetes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wCorpsRequetes">Trouver l'entité wCorpsRequetes dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wSchemas;
	@JsonIgnore
	public Couverture<ToutEcrivain> wSchemasCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wSchemas").o(wSchemas);

	/**	<br/>L'entité « wSchemas »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wSchemas">Trouver l'entité wSchemas dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected SiteContexteFrFR siteContexte;
	@JsonIgnore
	public Couverture<SiteContexteFrFR> siteContexteCouverture = new Couverture<SiteContexteFrFR>().p(this).c(SiteContexteFrFR.class).var("siteContexte").o(siteContexte);

	/**	<br/>L'entité « siteContexte »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte">Trouver l'entité siteContexte dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteContexte(Couverture<SiteContexteFrFR> c);

	public SiteContexteFrFR getSiteContexte() {
		return siteContexte;
	}

	public void setSiteContexte(SiteContexteFrFR siteContexte) {
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
	@JsonInclude(Include.NON_NULL)
	protected ConfigSite configSite;
	@JsonIgnore
	public Couverture<ConfigSite> configSiteCouverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite").o(configSite);

	/**	<br/>L'entité « configSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wRequeteEnTete;
	@JsonIgnore
	public Couverture<ToutEcrivain> wRequeteEnTeteCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteEnTete").o(wRequeteEnTete);

	/**	<br/>L'entité « wRequeteEnTete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteEnTete">Trouver l'entité wRequeteEnTete dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wRequeteDescription;
	@JsonIgnore
	public Couverture<ToutEcrivain> wRequeteDescriptionCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteDescription").o(wRequeteDescription);

	/**	<br/>L'entité « wRequeteDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteDescription">Trouver l'entité wRequeteDescription dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wReponseDescription;
	@JsonIgnore
	public Couverture<ToutEcrivain> wReponseDescriptionCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wReponseDescription").o(wReponseDescription);

	/**	<br/>L'entité « wReponseDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wReponseDescription">Trouver l'entité wReponseDescription dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wRequeteCorps;
	@JsonIgnore
	public Couverture<ToutEcrivain> wRequeteCorpsCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteCorps").o(wRequeteCorps);

	/**	<br/>L'entité « wRequeteCorps »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteCorps">Trouver l'entité wRequeteCorps dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wReponseCorps;
	@JsonIgnore
	public Couverture<ToutEcrivain> wReponseCorpsCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wReponseCorps").o(wReponseCorps);

	/**	<br/>L'entité « wReponseCorps »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wReponseCorps">Trouver l'entité wReponseCorps dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wRequeteSchema;
	@JsonIgnore
	public Couverture<ToutEcrivain> wRequeteSchemaCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wRequeteSchema").o(wRequeteSchema);

	/**	<br/>L'entité « wRequeteSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wRequeteSchema">Trouver l'entité wRequeteSchema dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wReponseSchema;
	@JsonIgnore
	public Couverture<ToutEcrivain> wReponseSchemaCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wReponseSchema").o(wReponseSchema);

	/**	<br/>L'entité « wReponseSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wReponseSchema">Trouver l'entité wReponseSchema dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected TousEcrivains ecrivains;
	@JsonIgnore
	public Couverture<TousEcrivains> ecrivainsCouverture = new Couverture<TousEcrivains>().p(this).c(TousEcrivains.class).var("ecrivains").o(ecrivains);

	/**	<br/>L'entité « ecrivains »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecrivains">Trouver l'entité ecrivains dans Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected String classeApiTag;
	@JsonIgnore
	public Couverture<String> classeApiTagCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiTag").o(classeApiTag);

	/**	<br/>L'entité « classeApiTag »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiTag">Trouver l'entité classeApiTag dans Solr</a>
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

	public String jsonClasseApiTag() {
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

	/////////////////////
	// classeEtendBase //
	/////////////////////

	/**	L'entité « classeEtendBase »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classeEtendBase;
	@JsonIgnore
	public Couverture<Boolean> classeEtendBaseCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeEtendBase").o(classeEtendBase);

	/**	<br/>L'entité « classeEtendBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeEtendBase">Trouver l'entité classeEtendBase dans Solr</a>
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

	public String jsonClasseEtendBase() {
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
	@JsonInclude(Include.NON_NULL)
	protected Boolean classeEstBase;
	@JsonIgnore
	public Couverture<Boolean> classeEstBaseCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeEstBase").o(classeEstBase);

	/**	<br/>L'entité « classeEstBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeEstBase">Trouver l'entité classeEstBase dans Solr</a>
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

	public String jsonClasseEstBase() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeNomSimple;
	@JsonIgnore
	public Couverture<String> classeNomSimpleCouverture = new Couverture<String>().p(this).c(String.class).var("classeNomSimple").o(classeNomSimple);

	/**	<br/>L'entité « classeNomSimple »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeNomSimple">Trouver l'entité classeNomSimple dans Solr</a>
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

	public String jsonClasseNomSimple() {
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

	//////////////
	// appliNom //
	//////////////

	/**	L'entité « appliNom »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appliNom;
	@JsonIgnore
	public Couverture<String> appliNomCouverture = new Couverture<String>().p(this).c(String.class).var("appliNom").o(appliNom);

	/**	<br/>L'entité « appliNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appliNom">Trouver l'entité appliNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appliNom(Couverture<String> c);

	public String getAppliNom() {
		return appliNom;
	}

	public void setAppliNom(String appliNom) {
		this.appliNom = appliNom;
		this.appliNomCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain appliNomInit() {
		if(!appliNomCouverture.dejaInitialise) {
			_appliNom(appliNomCouverture);
			if(appliNom == null)
				setAppliNom(appliNomCouverture.o);
		}
		appliNomCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrAppliNom() {
		return appliNom;
	}

	public String strAppliNom() {
		return appliNom == null ? "" : appliNom;
	}

	public String jsonAppliNom() {
		return appliNom == null ? "" : appliNom;
	}

	public String nomAffichageAppliNom() {
		return null;
	}

	public String htmTooltipAppliNom() {
		return null;
	}

	public String htmAppliNom() {
		return appliNom == null ? "" : StringEscapeUtils.escapeHtml4(strAppliNom());
	}

	////////////////////////
	// classeCheminAbsolu //
	////////////////////////

	/**	L'entité « classeCheminAbsolu »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classeCheminAbsolu;
	@JsonIgnore
	public Couverture<String> classeCheminAbsoluCouverture = new Couverture<String>().p(this).c(String.class).var("classeCheminAbsolu").o(classeCheminAbsolu);

	/**	<br/>L'entité « classeCheminAbsolu »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeCheminAbsolu">Trouver l'entité classeCheminAbsolu dans Solr</a>
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

	public String jsonClasseCheminAbsolu() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeApiUriMethode;
	@JsonIgnore
	public Couverture<String> classeApiUriMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiUriMethode").o(classeApiUriMethode);

	/**	<br/>L'entité « classeApiUriMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiUriMethode">Trouver l'entité classeApiUriMethode dans Solr</a>
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

	public String jsonClasseApiUriMethode() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeApiMethodeMethode;
	@JsonIgnore
	public Couverture<String> classeApiMethodeMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiMethodeMethode").o(classeApiMethodeMethode);

	/**	<br/>L'entité « classeApiMethodeMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiMethodeMethode">Trouver l'entité classeApiMethodeMethode dans Solr</a>
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

	public String jsonClasseApiMethodeMethode() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeApiTypeMedia200Methode;
	@JsonIgnore
	public Couverture<String> classeApiTypeMedia200MethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiTypeMedia200Methode").o(classeApiTypeMedia200Methode);

	/**	<br/>L'entité « classeApiTypeMedia200Methode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiTypeMedia200Methode">Trouver l'entité classeApiTypeMedia200Methode dans Solr</a>
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

	public String jsonClasseApiTypeMedia200Methode() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeApiOperationIdMethode;
	@JsonIgnore
	public Couverture<String> classeApiOperationIdMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiOperationIdMethode").o(classeApiOperationIdMethode);

	/**	<br/>L'entité « classeApiOperationIdMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiOperationIdMethode">Trouver l'entité classeApiOperationIdMethode dans Solr</a>
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

	public String jsonClasseApiOperationIdMethode() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeApiOperationIdMethodeRequete;
	@JsonIgnore
	public Couverture<String> classeApiOperationIdMethodeRequeteCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiOperationIdMethodeRequete").o(classeApiOperationIdMethodeRequete);

	/**	<br/>L'entité « classeApiOperationIdMethodeRequete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiOperationIdMethodeRequete">Trouver l'entité classeApiOperationIdMethodeRequete dans Solr</a>
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

	public String jsonClasseApiOperationIdMethodeRequete() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeApiOperationIdMethodeReponse;
	@JsonIgnore
	public Couverture<String> classeApiOperationIdMethodeReponseCouverture = new Couverture<String>().p(this).c(String.class).var("classeApiOperationIdMethodeReponse").o(classeApiOperationIdMethodeReponse);

	/**	<br/>L'entité « classeApiOperationIdMethodeReponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeApiOperationIdMethodeReponse">Trouver l'entité classeApiOperationIdMethodeReponse dans Solr</a>
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

	public String jsonClasseApiOperationIdMethodeReponse() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeSuperApiOperationIdMethodeRequete;
	@JsonIgnore
	public Couverture<String> classeSuperApiOperationIdMethodeRequeteCouverture = new Couverture<String>().p(this).c(String.class).var("classeSuperApiOperationIdMethodeRequete").o(classeSuperApiOperationIdMethodeRequete);

	/**	<br/>L'entité « classeSuperApiOperationIdMethodeRequete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeSuperApiOperationIdMethodeRequete">Trouver l'entité classeSuperApiOperationIdMethodeRequete dans Solr</a>
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

	public String jsonClasseSuperApiOperationIdMethodeRequete() {
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
	@JsonInclude(Include.NON_NULL)
	protected String classeSuperApiOperationIdMethodeReponse;
	@JsonIgnore
	public Couverture<String> classeSuperApiOperationIdMethodeReponseCouverture = new Couverture<String>().p(this).c(String.class).var("classeSuperApiOperationIdMethodeReponse").o(classeSuperApiOperationIdMethodeReponse);

	/**	<br/>L'entité « classeSuperApiOperationIdMethodeReponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeSuperApiOperationIdMethodeReponse">Trouver l'entité classeSuperApiOperationIdMethodeReponse dans Solr</a>
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

	public String jsonClasseSuperApiOperationIdMethodeReponse() {
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

	///////////////////////////////////
	// classePageNomCanoniqueMethode //
	///////////////////////////////////

	/**	L'entité « classePageNomCanoniqueMethode »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classePageNomCanoniqueMethode;
	@JsonIgnore
	public Couverture<String> classePageNomCanoniqueMethodeCouverture = new Couverture<String>().p(this).c(String.class).var("classePageNomCanoniqueMethode").o(classePageNomCanoniqueMethode);

	/**	<br/>L'entité « classePageNomCanoniqueMethode »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classePageNomCanoniqueMethode">Trouver l'entité classePageNomCanoniqueMethode dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classePageNomCanoniqueMethode(Couverture<String> c);

	public String getClassePageNomCanoniqueMethode() {
		return classePageNomCanoniqueMethode;
	}

	public void setClassePageNomCanoniqueMethode(String classePageNomCanoniqueMethode) {
		this.classePageNomCanoniqueMethode = classePageNomCanoniqueMethode;
		this.classePageNomCanoniqueMethodeCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain classePageNomCanoniqueMethodeInit() {
		if(!classePageNomCanoniqueMethodeCouverture.dejaInitialise) {
			_classePageNomCanoniqueMethode(classePageNomCanoniqueMethodeCouverture);
			if(classePageNomCanoniqueMethode == null)
				setClassePageNomCanoniqueMethode(classePageNomCanoniqueMethodeCouverture.o);
		}
		classePageNomCanoniqueMethodeCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrClassePageNomCanoniqueMethode() {
		return classePageNomCanoniqueMethode;
	}

	public String strClassePageNomCanoniqueMethode() {
		return classePageNomCanoniqueMethode == null ? "" : classePageNomCanoniqueMethode;
	}

	public String jsonClassePageNomCanoniqueMethode() {
		return classePageNomCanoniqueMethode == null ? "" : classePageNomCanoniqueMethode;
	}

	public String nomAffichageClassePageNomCanoniqueMethode() {
		return null;
	}

	public String htmTooltipClassePageNomCanoniqueMethode() {
		return null;
	}

	public String htmClassePageNomCanoniqueMethode() {
		return classePageNomCanoniqueMethode == null ? "" : StringEscapeUtils.escapeHtml4(strClassePageNomCanoniqueMethode());
	}

	///////////////////////////
	// classeMotsClesTrouves //
	///////////////////////////

	/**	L'entité « classeMotsClesTrouves »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classeMotsClesTrouves;
	@JsonIgnore
	public Couverture<Boolean> classeMotsClesTrouvesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeMotsClesTrouves").o(classeMotsClesTrouves);

	/**	<br/>L'entité « classeMotsClesTrouves »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeMotsClesTrouves">Trouver l'entité classeMotsClesTrouves dans Solr</a>
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

	public String jsonClasseMotsClesTrouves() {
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
	@JsonInclude(Include.NON_NULL)
	protected List<String> classeMotsCles;
	@JsonIgnore
	public Couverture<List<String>> classeMotsClesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeMotsCles").o(classeMotsCles);

	/**	<br/>L'entité « classeMotsCles »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeMotsCles">Trouver l'entité classeMotsCles dans Solr</a>
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
	public ApiEcrivain setClasseMotsCles(JsonArray objets) {
		classeMotsCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasseMotsCles(o);
		}
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

	public String jsonClasseMotsCles() {
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

	//////////////////////
	// classePublicLire //
	//////////////////////

	/**	L'entité « classePublicLire »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classePublicLire;
	@JsonIgnore
	public Couverture<Boolean> classePublicLireCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classePublicLire").o(classePublicLire);

	/**	<br/>L'entité « classePublicLire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classePublicLire">Trouver l'entité classePublicLire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classePublicLire(Couverture<Boolean> c);

	public Boolean getClassePublicLire() {
		return classePublicLire;
	}

	public void setClassePublicLire(Boolean classePublicLire) {
		this.classePublicLire = classePublicLire;
		this.classePublicLireCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setClassePublicLire(String o) {
		this.classePublicLire = Boolean.parseBoolean(o);
		this.classePublicLireCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classePublicLireInit() {
		if(!classePublicLireCouverture.dejaInitialise) {
			_classePublicLire(classePublicLireCouverture);
			if(classePublicLire == null)
				setClassePublicLire(classePublicLireCouverture.o);
		}
		classePublicLireCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Boolean solrClassePublicLire() {
		return classePublicLire;
	}

	public String strClassePublicLire() {
		return classePublicLire == null ? "" : classePublicLire.toString();
	}

	public String jsonClassePublicLire() {
		return classePublicLire == null ? "" : classePublicLire.toString();
	}

	public String nomAffichageClassePublicLire() {
		return null;
	}

	public String htmTooltipClassePublicLire() {
		return null;
	}

	public String htmClassePublicLire() {
		return classePublicLire == null ? "" : StringEscapeUtils.escapeHtml4(strClassePublicLire());
	}

	///////////////////////
	// classeRoleSession //
	///////////////////////

	/**	L'entité « classeRoleSession »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classeRoleSession;
	@JsonIgnore
	public Couverture<Boolean> classeRoleSessionCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeRoleSession").o(classeRoleSession);

	/**	<br/>L'entité « classeRoleSession »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeRoleSession">Trouver l'entité classeRoleSession dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeRoleSession(Couverture<Boolean> c);

	public Boolean getClasseRoleSession() {
		return classeRoleSession;
	}

	public void setClasseRoleSession(Boolean classeRoleSession) {
		this.classeRoleSession = classeRoleSession;
		this.classeRoleSessionCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setClasseRoleSession(String o) {
		this.classeRoleSession = Boolean.parseBoolean(o);
		this.classeRoleSessionCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeRoleSessionInit() {
		if(!classeRoleSessionCouverture.dejaInitialise) {
			_classeRoleSession(classeRoleSessionCouverture);
			if(classeRoleSession == null)
				setClasseRoleSession(classeRoleSessionCouverture.o);
		}
		classeRoleSessionCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Boolean solrClasseRoleSession() {
		return classeRoleSession;
	}

	public String strClasseRoleSession() {
		return classeRoleSession == null ? "" : classeRoleSession.toString();
	}

	public String jsonClasseRoleSession() {
		return classeRoleSession == null ? "" : classeRoleSession.toString();
	}

	public String nomAffichageClasseRoleSession() {
		return null;
	}

	public String htmTooltipClasseRoleSession() {
		return null;
	}

	public String htmClasseRoleSession() {
		return classeRoleSession == null ? "" : StringEscapeUtils.escapeHtml4(strClasseRoleSession());
	}

	///////////////////////////
	// classeRoleUtilisateur //
	///////////////////////////

	/**	L'entité « classeRoleUtilisateur »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classeRoleUtilisateur;
	@JsonIgnore
	public Couverture<Boolean> classeRoleUtilisateurCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeRoleUtilisateur").o(classeRoleUtilisateur);

	/**	<br/>L'entité « classeRoleUtilisateur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeRoleUtilisateur">Trouver l'entité classeRoleUtilisateur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeRoleUtilisateur(Couverture<Boolean> c);

	public Boolean getClasseRoleUtilisateur() {
		return classeRoleUtilisateur;
	}

	public void setClasseRoleUtilisateur(Boolean classeRoleUtilisateur) {
		this.classeRoleUtilisateur = classeRoleUtilisateur;
		this.classeRoleUtilisateurCouverture.dejaInitialise = true;
	}
	public ApiEcrivain setClasseRoleUtilisateur(String o) {
		this.classeRoleUtilisateur = Boolean.parseBoolean(o);
		this.classeRoleUtilisateurCouverture.dejaInitialise = true;
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeRoleUtilisateurInit() {
		if(!classeRoleUtilisateurCouverture.dejaInitialise) {
			_classeRoleUtilisateur(classeRoleUtilisateurCouverture);
			if(classeRoleUtilisateur == null)
				setClasseRoleUtilisateur(classeRoleUtilisateurCouverture.o);
		}
		classeRoleUtilisateurCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public Boolean solrClasseRoleUtilisateur() {
		return classeRoleUtilisateur;
	}

	public String strClasseRoleUtilisateur() {
		return classeRoleUtilisateur == null ? "" : classeRoleUtilisateur.toString();
	}

	public String jsonClasseRoleUtilisateur() {
		return classeRoleUtilisateur == null ? "" : classeRoleUtilisateur.toString();
	}

	public String nomAffichageClasseRoleUtilisateur() {
		return null;
	}

	public String htmTooltipClasseRoleUtilisateur() {
		return null;
	}

	public String htmClasseRoleUtilisateur() {
		return classeRoleUtilisateur == null ? "" : StringEscapeUtils.escapeHtml4(strClasseRoleUtilisateur());
	}

	////////////////////////
	// classeRolesTrouves //
	////////////////////////

	/**	L'entité « classeRolesTrouves »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classeRolesTrouves;
	@JsonIgnore
	public Couverture<Boolean> classeRolesTrouvesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("classeRolesTrouves").o(classeRolesTrouves);

	/**	<br/>L'entité « classeRolesTrouves »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeRolesTrouves">Trouver l'entité classeRolesTrouves dans Solr</a>
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

	public String jsonClasseRolesTrouves() {
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
	@JsonInclude(Include.NON_NULL)
	protected List<String> classeRoles;
	@JsonIgnore
	public Couverture<List<String>> classeRolesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeRoles").o(classeRoles);

	/**	<br/>L'entité « classeRoles »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeRoles">Trouver l'entité classeRoles dans Solr</a>
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
	public ApiEcrivain setClasseRoles(JsonArray objets) {
		classeRoles.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasseRoles(o);
		}
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

	public String jsonClasseRoles() {
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

	///////////////////////
	// classeRolesLangue //
	///////////////////////

	/**	L'entité « classeRolesLangue »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classeRolesLangue;
	@JsonIgnore
	public Couverture<List<String>> classeRolesLangueCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeRolesLangue").o(classeRolesLangue);

	/**	<br/>L'entité « classeRolesLangue »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeRolesLangue">Trouver l'entité classeRolesLangue dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeRolesLangue(Couverture<List<String>> c);

	public List<String> getClasseRolesLangue() {
		return classeRolesLangue;
	}

	public void setClasseRolesLangue(List<String> classeRolesLangue) {
		this.classeRolesLangue = classeRolesLangue;
		this.classeRolesLangueCouverture.dejaInitialise = true;
	}
	public ApiEcrivain addClasseRolesLangue(String...objets) {
		for(String o : objets) {
			addClasseRolesLangue(o);
		}
		return (ApiEcrivain)this;
	}
	public ApiEcrivain addClasseRolesLangue(String o) {
		if(o != null && !classeRolesLangue.contains(o))
			this.classeRolesLangue.add(o);
		return (ApiEcrivain)this;
	}
	public ApiEcrivain setClasseRolesLangue(JsonArray objets) {
		classeRolesLangue.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasseRolesLangue(o);
		}
		return (ApiEcrivain)this;
	}
	protected ApiEcrivain classeRolesLangueInit() {
		if(!classeRolesLangueCouverture.dejaInitialise) {
			_classeRolesLangue(classeRolesLangueCouverture);
			if(classeRolesLangue == null)
				setClasseRolesLangue(classeRolesLangueCouverture.o);
		}
		classeRolesLangueCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public List<String> solrClasseRolesLangue() {
		return classeRolesLangue;
	}

	public String strClasseRolesLangue() {
		return classeRolesLangue == null ? "" : classeRolesLangue.toString();
	}

	public String jsonClasseRolesLangue() {
		return classeRolesLangue == null ? "" : classeRolesLangue.toString();
	}

	public String nomAffichageClasseRolesLangue() {
		return null;
	}

	public String htmTooltipClasseRolesLangue() {
		return null;
	}

	public String htmClasseRolesLangue() {
		return classeRolesLangue == null ? "" : StringEscapeUtils.escapeHtml4(strClasseRolesLangue());
	}

	///////////////
	// langueNom //
	///////////////

	/**	L'entité « langueNom »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String langueNom;
	@JsonIgnore
	public Couverture<String> langueNomCouverture = new Couverture<String>().p(this).c(String.class).var("langueNom").o(langueNom);

	/**	<br/>L'entité « langueNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:langueNom">Trouver l'entité langueNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _langueNom(Couverture<String> c);

	public String getLangueNom() {
		return langueNom;
	}

	public void setLangueNom(String langueNom) {
		this.langueNom = langueNom;
		this.langueNomCouverture.dejaInitialise = true;
	}
	protected ApiEcrivain langueNomInit() {
		if(!langueNomCouverture.dejaInitialise) {
			_langueNom(langueNomCouverture);
			if(langueNom == null)
				setLangueNom(langueNomCouverture.o);
		}
		langueNomCouverture.dejaInitialise(true);
		return (ApiEcrivain)this;
	}

	public String solrLangueNom() {
		return langueNom;
	}

	public String strLangueNom() {
		return langueNom == null ? "" : langueNom;
	}

	public String jsonLangueNom() {
		return langueNom == null ? "" : langueNom;
	}

	public String nomAffichageLangueNom() {
		return null;
	}

	public String htmTooltipLangueNom() {
		return null;
	}

	public String htmLangueNom() {
		return langueNom == null ? "" : StringEscapeUtils.escapeHtml4(strLangueNom());
	}

	////////////////////////
	// entiteDocumentSolr //
	////////////////////////

	/**	L'entité « entiteDocumentSolr »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocument entiteDocumentSolr;
	@JsonIgnore
	public Couverture<SolrDocument> entiteDocumentSolrCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("entiteDocumentSolr").o(entiteDocumentSolr);

	/**	<br/>L'entité « entiteDocumentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ApiEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:entiteDocumentSolr">Trouver l'entité entiteDocumentSolr dans Solr</a>
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

	public ApiEcrivain initLoinApiEcrivain(RequeteSiteFrFR requeteSite_) {
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
		requeteSite_Init();
		classeDocumentSolrInit();
		contexteRowsInit();
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
		classeEtendBaseInit();
		classeEstBaseInit();
		classeNomSimpleInit();
		appliNomInit();
		classeCheminAbsoluInit();
		classeApiUriMethodeInit();
		classeApiMethodeMethodeInit();
		classeApiTypeMedia200MethodeInit();
		classeApiOperationIdMethodeInit();
		classeApiOperationIdMethodeRequeteInit();
		classeApiOperationIdMethodeReponseInit();
		classeSuperApiOperationIdMethodeRequeteInit();
		classeSuperApiOperationIdMethodeReponseInit();
		classePageNomCanoniqueMethodeInit();
		classeMotsClesTrouvesInit();
		classeMotsClesInit();
		classePublicLireInit();
		classeRoleSessionInit();
		classeRoleUtilisateurInit();
		classeRolesTrouvesInit();
		classeRolesInit();
		classeRolesLangueInit();
		langueNomInit();
		entiteDocumentSolrInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinApiEcrivain(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteApiEcrivain(RequeteSiteFrFR requeteSite_) {
		if(appSwagger2 != null)
			appSwagger2.setRequeteSite_(requeteSite_);
		if(wChemins != null)
			wChemins.setRequeteSite_(requeteSite_);
		if(wCorpsRequetes != null)
			wCorpsRequetes.setRequeteSite_(requeteSite_);
		if(wSchemas != null)
			wSchemas.setRequeteSite_(requeteSite_);
		if(wRequeteEnTete != null)
			wRequeteEnTete.setRequeteSite_(requeteSite_);
		if(wRequeteDescription != null)
			wRequeteDescription.setRequeteSite_(requeteSite_);
		if(wReponseDescription != null)
			wReponseDescription.setRequeteSite_(requeteSite_);
		if(wRequeteCorps != null)
			wRequeteCorps.setRequeteSite_(requeteSite_);
		if(wReponseCorps != null)
			wReponseCorps.setRequeteSite_(requeteSite_);
		if(wRequeteSchema != null)
			wRequeteSchema.setRequeteSite_(requeteSite_);
		if(wReponseSchema != null)
			wReponseSchema.setRequeteSite_(requeteSite_);
		if(ecrivains != null)
			ecrivains.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteApiEcrivain(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
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
	public Object obtenirApiEcrivain(String var) {
		ApiEcrivain oApiEcrivain = (ApiEcrivain)this;
		switch(var) {
			case "requeteSite_":
				return oApiEcrivain.requeteSite_;
			case "classeDocumentSolr":
				return oApiEcrivain.classeDocumentSolr;
			case "contexteRows":
				return oApiEcrivain.contexteRows;
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
			case "classeEtendBase":
				return oApiEcrivain.classeEtendBase;
			case "classeEstBase":
				return oApiEcrivain.classeEstBase;
			case "classeNomSimple":
				return oApiEcrivain.classeNomSimple;
			case "appliNom":
				return oApiEcrivain.appliNom;
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
			case "classePageNomCanoniqueMethode":
				return oApiEcrivain.classePageNomCanoniqueMethode;
			case "classeMotsClesTrouves":
				return oApiEcrivain.classeMotsClesTrouves;
			case "classeMotsCles":
				return oApiEcrivain.classeMotsCles;
			case "classePublicLire":
				return oApiEcrivain.classePublicLire;
			case "classeRoleSession":
				return oApiEcrivain.classeRoleSession;
			case "classeRoleUtilisateur":
				return oApiEcrivain.classeRoleUtilisateur;
			case "classeRolesTrouves":
				return oApiEcrivain.classeRolesTrouves;
			case "classeRoles":
				return oApiEcrivain.classeRoles;
			case "classeRolesLangue":
				return oApiEcrivain.classeRolesLangue;
			case "langueNom":
				return oApiEcrivain.langueNom;
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

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiApiEcrivain() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof ApiEcrivain) {
			ApiEcrivain original = (ApiEcrivain)o;
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
		if(!(o instanceof ApiEcrivain))
			return false;
		ApiEcrivain that = (ApiEcrivain)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ApiEcrivain { ");
		sb.append(" }");
		return sb.toString();
	}
}
