package org.computate.scolaire.frFR.ecrivain;  

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.vertx.AppliSwagger2;

/**
 * NomCanonique.enUS: org.computate.scolaire.enUS.writer.ApiWriter
 */   
public class ApiEcrivain extends ApiEcrivainGen<Object> implements Comparable<ApiEcrivain> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteRequest_
	 **/
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classSolrDocument
	 */
	protected void _classeDocumentSolr(Couverture<SolrDocument> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiMethod
	 **/
	protected void _classeApiMethode(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 **/
	protected void _openApiVersion(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 **/
	protected void _appSwagger2(Couverture<AppliSwagger2> c) { 
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classUris
	 **/
	protected void _classeUris(Couverture<List<String>> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: openApiVersionNumber
	 **/
	protected void _openApiVersionNumero(Couverture<Integer> c) {
		c.o((int)Double.parseDouble(StringUtils.substringBefore(openApiVersion, ".")));
	}

	/**
	 * {@inheritDoc}
	 * r: openApiVersionNumero
	 * r.enUS: openApiVersionNumber
	 **/
	protected void _tabsSchema(Couverture<Integer> c) {
		if(openApiVersionNumero == 2)
			c.o(1);
		else
			c.o(2);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: tabsResponses
	 **/
	protected void _tabsReponses(Couverture<Integer> c) {
		if(StringUtils.equals(openApiVersion, "2.0"))
			c.o(0);
		else
			c.o(2);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wPaths
	 * r: creer
	 * r.enUS: create
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/ 
	protected void _wChemins(Couverture<ToutEcrivain> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wRequestBodies
	 * r: creer
	 * r.enUS: create
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wCorpsRequetes(Couverture<ToutEcrivain> c) {
	}

	/**
	 * {@inheritDoc}
	 * r: creer
	 * r.enUS: create
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wSchemas(Couverture<ToutEcrivain> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteContext
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _siteContexte(Couverture<SiteContexteFrFR> c) {
		c.o(requeteSite_.getSiteContexte_());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteConfig
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _configSite(Couverture<ConfigSite> c) {
		c.o(requeteSite_.getConfigSite_());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wRequestHeaders
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/ 
	protected void _wRequeteEnTete(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wRequestDescription
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wRequeteDescription(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wResponseDescription
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wReponseDescription(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wRequestBody
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wRequeteCorps(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wResponseBody
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wReponseCorps(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wRequestSchema
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wRequeteSchema(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: wResponseSchema
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 **/
	protected void _wReponseSchema(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: writers
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: creer
	 * r.enUS: create
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: TousEcrivains
	 * r.enUS: AllWriters
	 **/
	protected void _ecrivains(Couverture<TousEcrivains> c) {
		c.o(TousEcrivains.creer(requeteSite_));
	} 

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiTag
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeApiTag(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeApiTag_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classExtendsBase
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 **/
	protected void _classeEtendBase(Couverture<Boolean> c) {
		c.o((Boolean)classeDocumentSolr.get("classeEtendBase_stored_boolean"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classIsBase
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 **/
	protected void _classeEstBase(Couverture<Boolean> c) {
		c.o((Boolean)classeDocumentSolr.get("classeEstBase_stored_boolean"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classSimpleName
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeNomSimple(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeNomSimple_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classAbsolutePath
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeCheminAbsolu(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeCheminAbsolu_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiUriMethod
	 * r: classeApiMethode +
	 * r.enUS: classApiMethod +
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeApiUriMethode(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeApiUri" + classeApiMethode + "_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiMethodMethod
	 * r: classeApiMethode +
	 * r.enUS: classApiMethod +
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeApiMethodeMethode(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeApiMethode" + classeApiMethode + "_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiMediaType200Method
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/ 
	protected void _classeApiTypeMedia200Methode(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeApiTypeMedia200" + classeApiMethode + "_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiOperationIdMethod
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeApiOperationIdMethode(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeApiOperationId" + classeApiMethode + "_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiOperationIdMethodRequest
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeApiOperationIdMethodeRequete(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeApiOperationId" + classeApiMethode + "Requete_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classApiOperationIdMethodResponse
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeApiOperationIdMethodeReponse(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeApiOperationId" + classeApiMethode + "Reponse_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classSuperApiOperationIdMethodRequest
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeSuperApiOperationIdMethodeRequete(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeSuperApiOperationId" + classeApiMethode + "Requete_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classSuperApiOperationIdMethodResponse
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classeSuperApiOperationIdMethodeReponse(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classeSuperApiOperationId" + classeApiMethode + "Reponse_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classPageCanonicalNameMethod
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _classePageNomCanoniqueMethode(Couverture<String> c) {
		c.o((String)classeDocumentSolr.get("classePageNomCanonique" + classeApiMethode + "_frFR_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classKeywordsFound
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 **/
	protected void _classeMotsClesTrouves(Couverture<Boolean> c) {
		c.o((Boolean)classeDocumentSolr.get("classeMotsClesTrouves_stored_boolean"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classKeywords
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 **/
	protected void _classeMotsCles(Couverture<List<String>> c) {
		List<String> o = (List<String>)classeDocumentSolr.get("classeMotsCles_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classRolesFound
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 **/
	protected void _classeRolesTrouves(Couverture<Boolean> c) {
		c.o((Boolean)classeDocumentSolr.get("classeRolesTrouves_stored_boolean"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classRoles
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 **/
	protected void _classeRoles(Couverture<List<String>> c) {
		List<String> o = (List<String>)classeDocumentSolr.get("classeRoles_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classRolesLanguage
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 **/
	protected void _classeRolesLangue(Couverture<List<String>> c) {
		List<String> o = (List<String>)classeDocumentSolr.get("classeRolesLangue_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: languageName
	 * r: frFR
	 * r.enUS: enUS
	 **/
	protected void _langueNom(Couverture<String> c) {
		c.o("frFR");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: entitySolrDocument
	 **/
	protected void _entiteDocumentSolr(Couverture<SolrDocument> c) {
	}

	/**
	 * Var.enUS: entityVar
	 */
	String entiteVar;

	/**
	 * Var.enUS: entityCanonicalName
	 */
	String entiteNomCanonique;

	/**
	 * Var.enUS: entityCanonicalNameGeneric
	 */
	String entiteNomCanoniqueGenerique;

	/**
	 * Var.enUS: entityVarApi
	 */
	String entiteVarApi;

	/**
	 * Var.enUS: entityDescription
	 */
	String entiteDescription;

	/**
	 * Var.enUS: entityDisplayName
	 */
	String entiteNomAffichage;

	/**
	 * Var.enUS: entityMinLength
	 */
	Integer entiteLongeurMin;

	/**
	 * Var.enUS: entityMaxLength
	 */
	Integer entiteLongeurMax;

	/**
	 * Var.enUS: entityMin
	 */
	Double entiteMin;

	/**
	 * Var.enUS: entityMax
	 */
	Double entiteMax;

	/**
	 * Var.enUS: entityOptional
	 */
	Boolean entiteOptionnel;

	/**
	 * Var.enUS: entityVarCapitalized
	 */
	String entiteVarCapitalise;

	/**
	 * Var.enUS: entityJsonType
	 */
	String entiteTypeJson;

	/**
	 * Var.enUS: entityListJsonType
	 */
	String entiteListeTypeJson;

	/**
	 * Var.enUS: entityJsonFormat
	 */
	String entiteFormatJson;

	/**
	 * Var.enUS: entityPrimaryKey
	 */
	Boolean entiteClePrimaire;

	/**
	 * Var.enUS: entityStored
	 */
	Boolean entiteStocke;

	/**
	 * Var.enUS: entityIndexed
	 */
	Boolean entiteIndexe;

	/**
	 * Var.enUS: entityKeywordsFound
	 */
	Boolean entiteMotsClesTrouves;

	/**
	 * Var.enUS: entityKeywords
	 */
	List<String> entiteMotsCles;

	/**
	 * Var.enUS: entityOptionsVar
	 */
	List<String> entiteOptionsVar;

	/**
	 * Var.enUS: entityOptionsDescription
	 */
	List<String> entiteOptionsDescription;

	/**
	 * Var.enUS: initEntity
	 * Param1.var.enUS: entitySolrDocument
	 * r: EntiteDocumentSolr
	 * r.enUS: EntitySolrDocument
	 * r: entiteDocumentSolr
	 * r.enUS: entitySolrDocument
	 * 
	 * r: entiteVarApi
	 * r.enUS: entityVarApi
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteMotsClesTrouves
	 * r.enUS: entityKeywordsFound
	 * r: entiteMotsCles
	 * r.enUS: entityKeywords
	 * r: entiteNomCanoniqueGenerique
	 * r.enUS: entityCanonicalNameGeneric
	 * r: entiteNomCanonique
	 * r.enUS: entityCanonicalName
	 * r: entiteListeTypeJson
	 * r.enUS: entityListJsonType
	 * r: entiteTypeJson
	 * r.enUS: entityJsonType
	 * r: entiteFormatJson
	 * r.enUS: entityJsonFormat
	 * r: entiteOptionsVar
	 * r.enUS: entityOptionsVar
	 * r: entiteOptionsDescription
	 * r.enUS: entityOptionsDescription
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * 
	 * r: entityVarApi_
	 * r.enUS: entiteVarApi_
	 * r: entityVar_
	 * r.enUS: entiteVar_
	 * r: entityKeywordsFound_
	 * r.enUS: entiteMotsClesTrouves_
	 * r: entityKeywords_
	 * r.enUS: entiteMotsCles_
	 * r: entityCanonicalNameGeneric_
	 * r.enUS: entiteNomCanoniqueGenerique_
	 * r: entityCanonicalName_
	 * r.enUS: entiteNomCanonique_
	 * r: entityListJsonType_
	 * r.enUS: entiteListeTypeJson_
	 * r: entityJsonType_
	 * r.enUS: entiteTypeJson_
	 * r: entityJsonFormat_
	 * r.enUS: entiteFormatJson_
	 * r: entityOptionsVar_
	 * r.enUS: entiteOptionsVar_
	 * r: entityOptionsDescription_
	 * r.enUS: entiteOptionsDescription_
	 * r: entityDescription_
	 * r.enUS: entiteDescription_
	 * 
	 * r: frFR
	 * r.enUS: enUS
	 */
	public void initEntite(SolrDocument entiteDocumentSolr) {
		setEntiteDocumentSolr(entiteDocumentSolr);
		entiteVar = (String)entiteDocumentSolr.get("entiteVar_frFR_stored_string");
		entiteVarApi = StringUtils.defaultIfBlank((String)entiteDocumentSolr.get("entiteVarApi_stored_string"), entiteVar);
		entiteMotsClesTrouves = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMotsClesTrouves_stored_boolean"));
		entiteMotsCles = (List<String>)entiteDocumentSolr.get("entiteMotsCles_stored_strings");
		if(entiteMotsCles == null)
			entiteMotsCles = new ArrayList<>();
		entiteNomCanoniqueGenerique = (String)entiteDocumentSolr.get("entiteNomCanoniqueGenerique_frFR_stored_string");
		entiteNomCanonique = (String)entiteDocumentSolr.get("entiteNomCanonique_frFR_stored_string");
		entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");
		entiteTypeJson = (String)entiteDocumentSolr.get("entiteTypeJson_stored_string");
		entiteFormatJson = (String)entiteDocumentSolr.get("entiteFormatJson_stored_string");
		entiteOptionsVar = (List<String>)entiteDocumentSolr.get("entiteOptionsVar_stored_strings");
		entiteOptionsDescription = (List<String>)entiteDocumentSolr.get("entiteOptionsDescription_stored_strings");
		entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_stored_string");
	}

	/**
	 * Var.enUS: writeEntityHeaders
	 * r: entiteMotsCles
	 * r.enUS: entityKeywords
	 * r: wRequeteEnTete
	 * r.enUS: wRequestHeaders
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: entiteTypeJson
	 * r.enUS: entityJsonType
	 * r: entiteVarApi
	 * r.enUS: entityVarApi
	 */
	public void ecrireEntiteEnTete() throws Exception {

		if(entiteMotsCles.contains(classeApiMethode + ".header")) {
			wRequeteEnTete.tl(4, "- name: ", entiteVarApi);
			wRequeteEnTete.tl(5, "in: header");
			wRequeteEnTete.t(5, "description: ").yamlStr(6, entiteDescription);
			if(entiteMotsCles.contains(classeApiMethode + ".header.required"))
				wRequeteEnTete.tl(5, "required: true");
			wRequeteEnTete.tl(5, "type: ", entiteTypeJson);
		}
	}

	/**
	 * Var.enUS: writeEntityDescription
	 * Param1.var.enUS: numberTabs
	 * r: ecrireEntiteDescription
	 * r.enUS: writeEntityDescription
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: requete
	 * r.enUS: request
	 * r: reponse
	 * r.enUS: response
	 * r: wRequeteDescription
	 * r.enUS: wRequestDescription
	 * r: wReponseDescription
	 * r.enUS: wResponseDescription
	 */
	public void ecrireEntiteDescription(Integer nombreTabulations) throws Exception {
		ecrireEntiteDescription(nombreTabulations, wRequeteDescription, "requete");
		ecrireEntiteDescription(nombreTabulations, wReponseDescription, "reponse");
	}

	/**
	 * Var.enUS: writeEntityDescription
	 * Param1.var.enUS: numberTabs
	 * Param3.var.enUS: apiRequestOrResponse
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: wDescription
	 * r.enUS: wDescription
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: apiRequeteOuReponse
	 * r.enUS: apiRequestOrResponse
	 * r: entiteNomAffichage
	 * r.enUS: entityDisplayName
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: entiteOptionnel
	 * r.enUS: entityOptional
	 * r: entiteLongeurMin
	 * r.enUS: entityMinLength
	 * r: entiteLongeurMax
	 * r.enUS: entityMaxLength
	 * r: entiteMin
	 * r.enUS: entityMin
	 * r: entiteMax
	 * r.enUS: entityMax
	 * r: rechercheEntitesLignes
	 * r.enUS: searchEntitiesLines
	 * r: rechercheEntitesResultats
	 * r.enUS: searchEntitiesResults
	 * r: rechercheEntites
	 * r.enUS: searchEntities
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: ClientSolrComputate
	 * r.enUS: SolrClientComputate
	 * r: entiteDocumentSolr
	 * r.enUS: entitySolrDocument
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: AppliChemin
	 * r.enUS: AppPath
	 * r: ecrireEntiteDescription
	 * r.enUS: writeEntityDescription
	 * 
	 * r: entiteOptionsVarAncien
	 * r.enUS: entityOptionsVarOld
	 * r: entiteOptionsVar
	 * r.enUS: entityOptionsVar
	 * r: entiteOptionsDescriptionAncien
	 * r.enUS: entityOptionsDescriptionOld
	 * r: entiteOptionsDescription
	 * r.enUS: entityOptionsDescription
	 * r: entiteDescriptionAncien
	 * r.enUS: entityDescriptionOld
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: entiteTypeJsonAncien
	 * r.enUS: entityJsonTypeOld
	 * r: entiteTypeJson
	 * r.enUS: entityJsonType
	 * r: entiteFormatJsonAncien
	 * r.enUS: entityJsonFormatOld
	 * r: entiteFormatJson
	 * r.enUS: entityJsonFormat
	 * r: entiteListeTypeJsonAncien
	 * r.enUS: entityListJsonTypeOld
	 * r: entiteListeTypeJson
	 * r.enUS: entityListJsonType
	 * r: entiteNomCanoniqueGeneriqueAncien
	 * r.enUS: entityCanonicalNameGenericOld
	 * r: entiteNomCanoniqueGenerique
	 * r.enUS: entityCanonicalNameGeneric
	 * r: entiteNomCanoniqueAncien
	 * r.enUS: entityCanonicalNameOld
	 * r: entiteNomCanonique
	 * r.enUS: entityCanonicalName
	 * r: entiteMotsClesTrouvesAncien
	 * r.enUS: entityKeywordsFoundOld
	 * r: entiteMotsClesTrouves
	 * r.enUS: entityKeywordsFound
	 * r: entiteMotsCles
	 * r.enUS: entityKeywords
	 * r: entiteVarApiAncien
	 * r.enUS: entityVarApiOld
	 * r: entiteVarAncien
	 * r.enUS: entityVarOld
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: creer
	 * r.enUS: create
	 * 
	 * 
	 * r: appPath_
	 * r.enUS: appliChemin_
	 * r: classCanonicalName_
	 * r.enUS: classeNomCanonique_
	 * r: entityKeywords_
	 * r.enUS: entiteMotsCles_
	 * r: partIsEntity_
	 * r.enUS: partEstEntite_
	 * r: partNumber_
	 * r.enUS: partNumero_
	 * r: entityVarApi_
	 * r.enUS: entiteVarApi_
	 * r: entityVar_
	 * r.enUS: entiteVar_
	 * r: entityKeywordsFound_
	 * r.enUS: entiteMotsClesTrouves_
	 * r: entityKeywords_
	 * r.enUS: entiteMotsCles_
	 * r: entityCanonicalNameGeneric_
	 * r.enUS: entiteNomCanoniqueGenerique_
	 * r: entityCanonicalName_
	 * r.enUS: entiteNomCanonique_
	 * r: entityListJsonType_
	 * r.enUS: entiteListeTypeJson_
	 * r: entityJsonType_
	 * r.enUS: entiteTypeJson_
	 * r: entityJsonFormat_
	 * r.enUS: entiteFormatJson_
	 * r: entityOptionsVar_
	 * r.enUS: entiteOptionsVar_
	 * r: entityOptionsDescription_
	 * r.enUS: entiteOptionsDescription_
	 * r: entityDescription_
	 * r.enUS: entiteDescription_
	 */
	public void ecrireEntiteDescription(Integer nombreTabulations, ToutEcrivain w, String apiRequeteOuReponse) throws Exception {
		nombreTabulations = nombreTabulations == null ? 0 : nombreTabulations;
		
		if(
				entiteMotsCles.contains("apiModelEntity")
				|| entiteMotsCles.contains(classeApiMethode + "." + apiRequeteOuReponse)
				) {
			w.l();
			w.t(nombreTabulations, "- " + entiteVar);
			if(StringUtils.isNotBlank(entiteNomAffichage))
				w.s(" (" + entiteNomAffichage + ")");
			w.l(": ");
			ToutEcrivain wDescription = ToutEcrivain.creer(requeteSite_, "  ");
			if(StringUtils.isNotBlank(entiteDescription))
				wDescription.l(entiteDescription);
			if(BooleanUtils.isTrue(entiteOptionnel))
				wDescription.tl(nombreTabulations + 1, "- optional: ", entiteOptionnel);
			if(entiteLongeurMin != null)
				wDescription.tl(nombreTabulations + 1, "- min length: ", entiteLongeurMin);
			if(entiteLongeurMax != null)
				wDescription.tl(nombreTabulations + 1, "- max length: ", entiteLongeurMax);
			if(entiteMin != null)
				wDescription.tl(nombreTabulations + 1, "- min: ", entiteMin);
			if(entiteMax != null)
				wDescription.tl(nombreTabulations + 1, "- max: ", entiteMax);
			if(entiteOptionsVar != null && entiteOptionsDescription != null && entiteOptionsVar.size() > 0 && entiteOptionsDescription.size() == entiteOptionsVar.size()) {
				wDescription.tl(nombreTabulations + 1, "- enum:");
				for(int m = 0; m < entiteOptionsVar.size(); m++) {
					wDescription.tl(nombreTabulations + 2, "- " + entiteOptionsVar.get(m) + ": " + entiteOptionsDescription.get(m));
				}
			}
			if(entiteMotsCles.contains("apiModel")) {
				SolrQuery rechercheEntites = new SolrQuery();
				rechercheEntites.setQuery("*:*");
				rechercheEntites.setRows(1000000);
				rechercheEntites.addFilterQuery("appPath_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));

				if(StringUtils.isBlank(entiteNomCanoniqueGenerique))
					rechercheEntites.addFilterQuery("classCanonicalName_enUS_indexed_string:" + ClientUtils.escapeQueryChars(entiteNomCanonique));
				else
					rechercheEntites.addFilterQuery("classCanonicalName_enUS_indexed_string:" + ClientUtils.escapeQueryChars(entiteNomCanoniqueGenerique));

				rechercheEntites.addFilterQuery("entiteMotsCles_indexed_strings:" + ClientUtils.escapeQueryChars("apiModelEntity"));
				rechercheEntites.addFilterQuery("partIsEntity_indexed_boolean:true");
				rechercheEntites.addSort("partNumber_indexed_int", ORDER.asc);
				QueryResponse rechercheEntitesReponse = siteContexte.getClientSolrComputate().query(rechercheEntites);
				SolrDocumentList rechercheEntitesResultats = rechercheEntitesReponse.getResults();
				Integer rechercheEntitesLignes = rechercheEntites.getRows();

				if(rechercheEntitesResultats.size() > 0) {
					for(Long k = rechercheEntitesResultats.getStart(); k < rechercheEntitesResultats.getNumFound(); k+=rechercheEntitesLignes) {
						for(Integer l = 0; l < rechercheEntitesResultats.size(); l++) {
							SolrDocument entiteDocumentSolr = rechercheEntitesResultats.get(l);
							String entiteVarAncien = entiteVar;
							String entiteVarApiAncien = entiteVarApi;
							Boolean entiteMotsClesTrouvesAncien = entiteMotsClesTrouves;
							List<String> entiteMotsClesAncien = entiteMotsCles;
							String entiteNomCanoniqueGeneriqueAncien = entiteNomCanoniqueGenerique;
							String entiteNomCanoniqueAncien = entiteNomCanonique;
							String entiteListeTypeJsonAncien = entiteListeTypeJson;
							String entiteTypeJsonAncien = entiteTypeJson;
							String entiteFormatJsonAncien = entiteFormatJson;
							List<String> entiteOptionsVarAncien = entiteOptionsVar;
							List<String> entiteOptionsDescriptionAncien = entiteOptionsDescription;
							String entiteDescriptionAncien = entiteDescription;
							
							entiteVar = (String)entiteDocumentSolr.get("entiteVar_enUS_stored_string");
							entiteVarApi = StringUtils.defaultIfBlank((String)entiteDocumentSolr.get("entiteVarApi_stored_string"), entiteVar);
							entiteMotsClesTrouves = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMotsClesTrouves_stored_boolean"));
							entiteMotsCles = (List<String>)entiteDocumentSolr.get("entiteMotsCles_stored_strings");
							if(entiteMotsCles == null)
								entiteMotsCles = new ArrayList<>();
							entiteNomCanoniqueGenerique = (String)entiteDocumentSolr.get("entiteNomCanoniqueGenerique_enUS_stored_string");
							entiteNomCanonique = (String)entiteDocumentSolr.get("entiteNomCanonique_enUS_stored_string");
							entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");
							entiteTypeJson = (String)entiteDocumentSolr.get("entiteTypeJson_stored_string");
							entiteFormatJson = (String)entiteDocumentSolr.get("entiteFormatJson_stored_string");
							entiteOptionsVar = (List<String>)entiteDocumentSolr.get("entiteOptionsVar_stored_strings");
							entiteOptionsDescription = (List<String>)entiteDocumentSolr.get("entiteOptionsDescription_stored_strings");
							entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_stored_string");
	
							ecrireEntiteDescription(nombreTabulations + 3, w, apiRequeteOuReponse);
//							if(entiteCanonicalNameGeneric == null) {
//								writeEntityDescription(tabStep + 3, w, classeApiMethode, apiRequeteOuReponse);
//							}
//							else {
//								writeEntityDescription(tabStep + 5, w, classeApiMethode, apiRequeteOuReponse);
//							}
							
							entiteVar = entiteVarAncien;
							entiteVarApi = entiteVarApiAncien;
							entiteMotsClesTrouves = entiteMotsClesTrouvesAncien;
							entiteMotsCles = entiteMotsClesAncien;
							entiteNomCanoniqueGenerique = entiteNomCanoniqueGeneriqueAncien;
							entiteNomCanonique = entiteNomCanoniqueAncien;
							entiteListeTypeJson = entiteListeTypeJsonAncien;
							entiteTypeJson = entiteTypeJsonAncien;
							entiteFormatJson = entiteFormatJsonAncien;
							entiteOptionsVar = entiteOptionsVarAncien;
							entiteOptionsDescription = entiteOptionsDescriptionAncien;
							entiteDescription = entiteDescriptionAncien;
						}
					}
				}
			}
			w.s(StringUtils.trim(wDescription.toString()));
		}
	}

	/**
	 * Var.enUS: writeEntitySchema
	 * Param1.var.enUS: numberTabs
	 * r: ecrireEntiteSchema
	 * r.enUS: writeEntitySchema
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: requete
	 * r.enUS: request
	 * r: reponse
	 * r.enUS: response
	 * r: wRequeteSchema
	 * r.enUS: wRequestSchema
	 * r: wReponseSchema
	 * r.enUS: wResponseSchema
	 */
	public void ecrireEntiteSchema(Integer nombreTabulations) throws Exception {
		ecrireEntiteSchema(nombreTabulations, wRequeteSchema, "requete");
		ecrireEntiteSchema(nombreTabulations, wReponseSchema, "reponse");
	}

	/**
	 * Var.enUS: writeEntitySchema
	 * Param1.var.enUS: numberTabs
	 * Param3.var.enUS: apiRequestOrResponse
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: apiRequeteOuReponse
	 * r.enUS: apiRequestOrResponse
	 * r: entiteTypeJson
	 * r.enUS: entityJsonType
	 * r: entiteVarApi
	 * r.enUS: entityVarApi
	 * r: entiteOptionsVar
	 * r.enUS: entityOptionsVar
	 * r: entiteOptionsDescription
	 * r.enUS: entityOptionsDescription
	 * r: entiteListeTypeJson
	 * r.enUS: entityListJsonType
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: entiteFormatJson
	 * r.enUS: entityJsonFormat
	 * r: reponse
	 * r.enUS: response
	 * r: "Recherche"
	 * r.enUS: "Search"
	 */
	public void ecrireEntiteSchema(Integer nombreTabulations, ToutEcrivain w, String apiRequeteOuReponse) throws Exception {
		nombreTabulations = nombreTabulations == null ? (classeApiMethode.contains("Recherche") && "reponse".equals(apiRequeteOuReponse) ? 1 : 0) : nombreTabulations;
		if(entiteTypeJson != null) {
			w.tl(4 + tabsSchema + nombreTabulations, entiteVarApi, ":");
			w.tl(5 + tabsSchema + nombreTabulations, "type: ", entiteTypeJson);
			if(entiteListeTypeJson == null && entiteOptionsVar != null && entiteOptionsDescription != null && entiteOptionsVar.size() > 0 && entiteOptionsDescription.size() == entiteOptionsVar.size()) {
				w.tl(5 + tabsSchema + nombreTabulations, "enum:");
				for(String entiteOptionVar : entiteOptionsVar) {
					w.tl(6 + tabsSchema + nombreTabulations, "- ", entiteOptionVar);
					
				}
			}
			if(entiteListeTypeJson != null) {
				w.tl(5 + tabsSchema + nombreTabulations, "items:");
				w.tl(6 + tabsSchema + nombreTabulations, "type: ", entiteListeTypeJson);
				if(entiteOptionsVar != null && entiteOptionsDescription != null && entiteOptionsVar.size() > 0 && entiteOptionsDescription.size() == entiteOptionsVar.size()) {
					w.tl(6 + tabsSchema + nombreTabulations, "enum:");
					for(String entiteOptionVar : entiteOptionsVar) {
						w.tl(7 + tabsSchema + nombreTabulations, "- ", entiteOptionVar);
					}
				}
			}
			if(StringUtils.isNotBlank(entiteDescription))
				w.t(5 + tabsSchema + nombreTabulations, "description: ").yamlStr(7 + nombreTabulations, "- " + entiteDescription);
			if(entiteFormatJson != null)
				w.tl(5 + tabsSchema + nombreTabulations, "format: ", entiteFormatJson);
		}
	}

	/**
	 * Var.enUS: writeApi
	 * r: classeUris
	 * r.enUS: classUris
	 * r: classeApiMethodeMethode
	 * r.enUS: classApiMethodMethod
	 * r: classeApiUriMethode
	 * r.enUS: classApiUriMethod
	 * r: wChemins
	 * r.enUS: wPaths
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRolesLangue
	 * r.enUS: classRolesLanguage
	 * r: classeRoles
	 * r.enUS: classRoles
	 * r: classeApiTag
	 * r.enUS: classApiTag
	 * r: openApiVersionNumero
	 * r.enUS: openApiVersionNumber
	 * r: classeApiTypeMedia200Methode
	 * r.enUS: classApiMediaType200Method
	 * r: wRequeteEnTete
	 * r.enUS: wRequestHeaders
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: strRequeteDescription
	 * r.enUS: strRequestDescription
	 * r: wRequeteDescription
	 * r.enUS: wRequestDescription
	 * r: strReponseDescription
	 * r.enUS: strResponseDescription
	 * r: wReponseDescription
	 * r.enUS: wResponseDescription
	 * r: tabsReponses
	 * r.enUS: tabsResponses
	 * r: wCorpsRequetes
	 * r.enUS: wRequestBodies
	 * r: classeEtendBase
	 * r.enUS: classExtendsBase
	 * r: wRequeteSchema
	 * r.enUS: wRequestSchema
	 * r: wReponseSchema
	 * r.enUS: wResponseSchema
	 * 
	 * r: classeSuperApiOperationIdMethodeRequete
	 * r.enUS: classSuperApiOperationIdMethodRequest
	 * r: classeSuperApiOperationIdMethodeReponse
	 * r.enUS: classSuperApiOperationIdMethodResponse
	 * r: classeApiOperationIdMethodeRequete
	 * r.enUS: classApiOperationIdMethodRequest
	 * r: classeApiOperationIdMethodeReponse
	 * r.enUS: classApiOperationIdMethodResponse
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: Vide
	 * r.enUS: Empty
	 * r: ApiMethode
	 * r.enUS: ApiMethod
	 * r: "Recherche"
	 * r.enUS: "Search"
	 * r: ecrireApi
	 * r.enUS: writeApi
	 */
	public void ecrireApi(Boolean id) throws Exception {

			if(!classeUris.contains(classeApiUriMethode)) {
				wChemins.tl(1, classeApiUriMethode, (id ? "/{id}" : ""), ":");
				classeUris.add(classeApiUriMethode);
			}
	
			wChemins.tl(2, StringUtils.lowerCase(classeApiMethodeMethode), ":");
			wChemins.tl(3, "operationId: ", classeApiOperationIdMethode, (id ? "Id" : ""));
			wChemins.tl(3, "x-vertx-event-bus: ", langueNom, classeNomSimple);
	
			if(classeRolesTrouves) {
				wChemins.tl(3, "security:");
				wChemins.tl(4, "- openIdConnect:");
				for(int i = 0; i < classeRoles.size(); i++) {
					if("frFR".equals(classeRolesLangue.get(i)))
						wChemins.tl(5, "- ", classeRoles.get(i));
				}
			}
	
			wChemins.t(3, "description: ").yamlStr(4, "");
			wChemins.t(3, "summary: ").yamlStr(4, "");
			if(StringUtils.isNotBlank(classeApiTag)) {
				wChemins.tl(3, "tags:");
				wChemins.tl(4, "- ", classeApiTag);
			}
	
			if(openApiVersionNumero == 2) {
				wChemins.tl(3, "produces:");
				wChemins.tl(4, "- ", classeApiTypeMedia200Methode);
			}
	
		if(!wRequeteEnTete.getVide() || "GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode) || "PUT".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			wChemins.tl(3, "parameters:");
			wChemins.s(wRequeteEnTete);
			if("GET".equals(classeApiMethode) || "DELETE".equals(classeApiMethodeMethode) || "PUT".equals(classeApiMethodeMethode)) {
				wChemins.tl(4, "- name: id");
				wChemins.tl(5, "in: path");
				wChemins.t(5, "description: ").yamlStr(6, "");
				wChemins.tl(5, "required: true");
				wChemins.tl(5, "schema:");
				wChemins.tl(6, "type: string");
			}
			else if(classeApiMethode.contains("Recherche") || classeApiMethode.contains("PATCH")) {
				wChemins.tl(4, "- in: query");
				wChemins.tl(5, "name: q");
				wChemins.tl(5, "description: ''");
				wChemins.tl(5, "required: false");
				wChemins.tl(5, "schema:");
				wChemins.tl(6, "type: string");
				wChemins.tl(6, "default: '*:*'");
				wChemins.tl(4, "- in: query");
				wChemins.tl(5, "name: fq");
				wChemins.tl(5, "description: ''");
				wChemins.tl(5, "required: false");
				wChemins.tl(5, "schema:");
				wChemins.tl(6, "type: array");
				wChemins.tl(6, "items:");
				wChemins.tl(7, "  type: string");
				wChemins.tl(4, "- in: query");
				wChemins.tl(5, "name: fl");
				wChemins.tl(5, "description: ''");
				wChemins.tl(5, "required: false");
				wChemins.tl(5, "schema:");
				wChemins.tl(6, "type: string");
				wChemins.tl(4, "- in: query");
				wChemins.tl(5, "name: sort");
				wChemins.tl(5, "description: ''");
				wChemins.tl(5, "required: false");
				wChemins.tl(5, "schema:");
				wChemins.tl(6, "type: array");
				wChemins.tl(6, "items:");
				wChemins.tl(7, "  type: string");
				wChemins.tl(4, "- in: query");
				wChemins.tl(5, "name: start");
				wChemins.tl(5, "description: ''");
				wChemins.tl(5, "required: false");
				wChemins.tl(5, "schema:");
				wChemins.tl(6, "type: integer");
				wChemins.tl(6, "default: 0");
				wChemins.tl(6, "minimum: 0");
				wChemins.tl(4, "- in: query");
				wChemins.tl(5, "name: rows");
				wChemins.tl(5, "description: ''");
				wChemins.tl(5, "required: false");
				wChemins.tl(5, "schema:");
				wChemins.tl(6, "type: integer");
				wChemins.tl(6, "default: 10");
				wChemins.tl(6, "minimum: 1");
			}
		}

		if(openApiVersionNumero > 2) {
			if(classeApiMethodeMethode == null)
				throw new Exception("Expected a comment for " + this.toString() + " like : ApiMethode: ...");
			if(!classeApiMethodeMethode.contains("GET") && !classeApiMethodeMethode.contains("DELETE")) {
				wChemins.tl(3, "requestBody:");
				String strRequeteDescription = wRequeteDescription.toString();
				wChemins.t(4, "description: ").yamlStr(5, StringUtils.trim(strRequeteDescription));
				wChemins.tl(4, "required: true");
				wChemins.tl(4, "content:");
				wChemins.tl(5, "application/json:");
				wChemins.tl(6, "schema:");
				wChemins.tl(7, "$ref: '#/components/schemas/", classeApiOperationIdMethodeRequete, "'");
			}
		}
		else {
			wChemins.tl(4, "- name: \"", classeApiOperationIdMethodeRequete, "\"");
			wChemins.tl(5, "in: body");

			String strRequeteDescription = wRequeteDescription.toString();
//			if(StringUtils.isNotBlank(StringUtils.trim(strRequeteDescription)))
				wChemins.t(5, "description: ").yamlStr(6, StringUtils.trim(strRequeteDescription));
//			else
//				wChemins.tl(5, "description: ''");
			
			wChemins.tl(5, "schema:");
			wChemins.tl(6, "$ref: '#/components/requestBodies/", classeApiOperationIdMethodeRequete, "'");
		}

		wChemins.tl(3, "responses:");
		wChemins.tl(4, "'200':");
		if(openApiVersionNumero > 2) {
			String strReponseDescription = wReponseDescription.toString();
			wChemins.t(5, "description: ").yamlStr(6, strReponseDescription);
			wChemins.tl(5, "content:");
			wChemins.tl(6, classeApiTypeMedia200Methode, "; charset=utf-8:");
		}
		else {
	
			String strReponseDescription = wReponseDescription.toString();
	//		if(StringUtils.isNotBlank(StringUtils.trim(strReponseDescription)))
				wChemins.t(5 + tabsReponses, "description: ").yamlStr(6, strReponseDescription);
	//		else
	//			wChemins.tl(5, "description: ''");
		}

		wChemins.tl(5 + tabsReponses, "schema:");
		wChemins.tl(6 + tabsReponses, "$ref: '#/components/requestBodies/", classeApiOperationIdMethodeReponse, "'");
		wChemins.tl(4, "'400':");
		if(openApiVersionNumero > 2) {
			wChemins.t(5, "description: ").yamlStr(6 + tabsReponses, "<table><table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>invalidRequest</td><td>Missing or invalid Parameters</td></tr></table>");
			wChemins.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wChemins.t(5 + tabsReponses, "description: ").yamlStr(6 + tabsReponses, "<table><table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>invalidRequest</td><td>Missing or invalid Parameters</td></tr></table>");
		}
		wChemins.tl(5 + tabsReponses, "schema:");
		wChemins.tl(6 + tabsReponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wChemins.tl(4, "'401':");
		if(openApiVersionNumero > 2) {
			wChemins.t(5, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>unAuthorized</td><td>Authorization credentials are missing or invalid</td></tr></table>");
			wChemins.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wChemins.t(5 + tabsReponses, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>unAuthorized</td><td>Authorization credentials are missing or invalid</td></tr></table>");
		}
		wChemins.tl(5 + tabsReponses, "schema:");
		wChemins.tl(6 + tabsReponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wChemins.tl(4, "'403':");
		if(openApiVersionNumero > 2) {
			wChemins.t(5, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>accessNotConfigured</td><td>The request operation is not configured to access this resource</td><td>Channel/Country/Business provided in the request is not supported currently</td></tr></table>");
			wChemins.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wChemins.t(5 + tabsReponses, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>accessNotConfigured</td><td>The request operation is not configured to access this resource</td><td>Channel/Country/Business provided in the request is not supported currently</td></tr></table>");
		}
		wChemins.tl(5 + tabsReponses, "schema:");
		wChemins.tl(6 + tabsReponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wChemins.tl(4, "'404':");
		if(openApiVersionNumero > 2) {
			wChemins.t(5, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>resourceNotFound</td><td>The requested resource was not found</td><td>Empty resource/resource not found</td></tr></table>");
			wChemins.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wChemins.t(5 + tabsReponses, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>resourceNotFound</td><td>The requested resource was not found</td><td>Empty resource/resource not found</td></tr></table>");
		}
		wChemins.tl(5 + tabsReponses, "schema:");
		wChemins.tl(6 + tabsReponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wChemins.tl(4, "'500':");
		if(openApiVersionNumero > 2) {
			wChemins.t(5, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>fatal</td><td>serverUnavailable</td><td>The request failed due to an internal error/server unavailability</td></tr></table>");
			wChemins.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wChemins.t(5 + tabsReponses, "description: ").yamlStr(6 + tabsReponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>fatal</td><td>serverUnavailable</td><td>The request failed due to an internal error/server unavailability</td></tr></table>");
		}
		wChemins.tl(5 + tabsReponses, "schema:");
		wChemins.tl(6 + tabsReponses, "$ref: '#/components/requestBodies/ErrorResponse'");

		if(openApiVersionNumero > 2) {
			if(!"GET".equals(classeApiMethodeMethode) && !"DELETE".equals(classeApiMethodeMethode)) {
				wCorpsRequetes.tl(2, classeApiOperationIdMethodeRequete, ":");
				wCorpsRequetes.tl(3, "content:");
				wCorpsRequetes.tl(4, "application/json:");
				wCorpsRequetes.tl(5, "schema:");
				wCorpsRequetes.tl(6, "$ref: '#/components/schemas/", classeApiOperationIdMethodeRequete, "'");
			}
			wCorpsRequetes.tl(2, classeApiOperationIdMethodeReponse, ":");
			wCorpsRequetes.tl(3, "content:");
			wCorpsRequetes.tl(4, classeApiTypeMedia200Methode, "; charset=utf-8:");
			wCorpsRequetes.tl(5, "schema:");
			wCorpsRequetes.tl(6, "$ref: '#/components/schemas/", classeApiOperationIdMethodeReponse, "'");
		}

		if(!"GET".equals(classeApiMethodeMethode) && !"DELETE".equals(classeApiMethodeMethode)) {
//		if(classeMotsClesTrouves && classeMotsCles.contains(classeApiMethode + ".request")) {
			wSchemas.tl(tabsSchema, classeApiOperationIdMethodeRequete, ":");
			wSchemas.tl(tabsSchema + 1, "allOf:");
			if(BooleanUtils.isTrue(classeEtendBase)) {
				wSchemas.tl(tabsSchema + 2, "- $ref: \"#/components/schemas/", classeSuperApiOperationIdMethodeRequete, "\"");
			}
			wSchemas.tl(tabsSchema + 2, "- type: object");
			wSchemas.tl(tabsSchema + 3, "properties:");
			wSchemas.s(wRequeteSchema.toString());
		}

//		if(classeMotsClesTrouves && classeMotsCles.contains(classeApiMethode + ".response")) {
			wSchemas.tl(tabsSchema, classeApiOperationIdMethodeReponse, ":");
			wSchemas.tl(tabsSchema + 1, "allOf:");
			if("text/html".equals(classeApiTypeMedia200Methode)) {
				wSchemas.tl(tabsSchema + 2, "- type: string");
			}
			else {
				if(BooleanUtils.isTrue(classeEtendBase)) {
					wSchemas.tl(tabsSchema + 2, "- $ref: \"#/components/schemas/", classeSuperApiOperationIdMethodeReponse, "\"");
				}
	
				if(classeApiMethode.contains("Recherche")) {
					wSchemas.tl(tabsSchema + 2, "- type: array");
					wSchemas.tl(tabsSchema + 3, "items:");
					wSchemas.tl(tabsSchema + 4, "type: object");
					wSchemas.tl(tabsSchema + 4, "properties:");
				}
				else {
					wSchemas.tl(tabsSchema + 2, "- type: object");
					wSchemas.tl(tabsSchema + 3, "properties:");
				}
				wSchemas.s(wReponseSchema.toString());
			}
//		}
		if(classePageNomCanoniqueMethode != null && BooleanUtils.isFalse(id))
			ecrireApi(true);
	}

	/**
	 * r: classeApiUriMethode
	 * r.enUS: classApiUriMethod
	 * r: ClasseApiUriMethode
	 * r.enUS: ClassApiUriMethod
	 **/
	@Override public int compareTo(ApiEcrivain o) {
		return ObjectUtils.compare(classeApiUriMethode, o.getClasseApiUriMethode());
	}

	/**
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: frFR
	 * r.enUS: enUS
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 */
	@Override
	public String toString() {
		return (String)classeDocumentSolr.get("classeNomSimple_frFR_stored_string") + " " + classeApiMethode;
	}
}
