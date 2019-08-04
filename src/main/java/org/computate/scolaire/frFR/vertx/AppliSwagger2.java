package org.computate.scolaire.frFR.vertx;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecrivain.ApiEcrivain;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**
 * NomCanonique.enUS: org.computate.scolaire.enUS.vertx.AppSwagger2
 */
public class AppliSwagger2 extends AppliSwagger2Gen<Object> {       

	/**
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: ecrire
	 * r.enUS: write
	 * r: AppliSwagger2
	 * r.enUS: AppSwagger2
	 */
	public static void main(String[] args) {
		AppliSwagger2 api = new AppliSwagger2();
		api.initLoinAppliSwagger2();
		api.ecrireOpenApi();
	}

	/**
	 * Var.enUS: siteRequest_
	 */
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteContext
	 */
	protected void _siteContexte(SiteContexteFrFR o) { 
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteConfig
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 */   
	protected void _configSite(Couverture<ConfigSite> c) {
		c.o(siteContexte.getConfigSite());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: appPath
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: AppliChemin
	 * r.enUS: AppPath
	 * r: configSite
	 * r.enUS: siteConfig
	 **/ 
	protected void _appliChemin(Couverture<String> c) {
		c.o(configSite.getAppliChemin());
	}

	/**
	 * {@inheritDoc}
	 * r: configSite
	 * r.enUS: siteConfig
	 **/
	protected void _openApiVersion(Couverture<String> c) {
		c.o(configSite.getOpenApiVersion());
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
	 * r: configSite
	 * r.enUS: siteConfig
	 **/
	protected void _apiVersion(Couverture<String> c) {
		c.o(configSite.getApiVersion());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: openApiYamlPath
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: -frFR.yaml
	 * r.enUS: -enUS.yaml
	 **/
	protected void _openApiYamlChemin(Couverture<String> c) {
		c.o(appliChemin + "/src/main/resources/" + ("2.0".equals(apiVersion) ? "swagger2" : "openapi3") + "-frFR.yaml");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: openApiYamlFile
	 * r: openApiYamlChemin
	 * r.enUS: openApiYamlPath
	 **/
	protected void _openApiYamlFichier(Couverture<File> c) {
		c.o(new File(openApiYamlChemin));
	}

	/**
	 * {@inheritDoc}
	 * r: creer
	 * r.enUS: create
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: openApiYamlFichier
	 * r.enUS: openApiYamlFile
	 **/
	protected void _w(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, openApiYamlFichier, "  "));
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
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
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
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
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
		c.o(ToutEcrivain.creer(requeteSite_, "  "));
	}

	/**
	 * Var.enUS: classApiMethods
	 */
	List<String> classeApiMethodes;

	/**
	 * Var.enUS: classUris
	 */
	List<String> classeUris;

	/**
	 * Var.enUS: apiWriters
	 */
	List<ApiEcrivain> apiEcrivains;

	/**
	 * Var.enUS: classApiTag
	 */
	String classeApiTag;

	/**
	 * Var.enUS: classApiUri
	 */
	String classeApiUri;

	/**
	 * Var.enUS: classSimpleName
	 */
	String classeNomSimple;

	/**
	 * Var.enUS: classAbsolutePath
	 */
	String classeCheminAbsolu;

	/**
	 * Var.enUS: classIsBase
	 */
	Boolean classeEstBase;

	/**
	 * Var.enUS: classKeywordsFound
	 */
	Boolean classeMotsClesTrouves;

	/**
	 * Var.enUS: classKeywords
	 */
	List<String> classeMotsCles;

	/**
	 * Var.enUS: writeOpenApi
	 * r: ecrireInfo
	 * r.enUS: writeInfo
	 * r: ecrireApi
	 * r.enUS: writeApi
	 * r: wChemins
	 * r.enUS: wPaths
	 * r: wCorpsRequetes
	 * r.enUS: wRequestBodies
	 */
	public void ecrireOpenApi() {

		ecrireInfo();
		ecrireApi();

		w.s(wChemins.toString());
		w.s(wCorpsRequetes.toString());
		w.s(wSchemas.toString());

		w.flushClose();
	}

	/**
	 * Var.enUS: writeInfo
	 * r: openApiVersionNumero
	 * r.enUS: openApiVersionNumber
	 * r: wChemins
	 * r.enUS: wPaths
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * r: ApiNomHote
	 * r.enUS: ApiHostName
	 * r: ApiTitre
	 * r.enUS: ApiTitle
	 */
	public void ecrireInfo() {

		if(openApiVersionNumero == 2)
			wChemins.l("swagger: \"", apiVersion, "\"");
		else
			wChemins.l("openapi: ", apiVersion);

		wChemins.l("info:");

		wChemins.t(1, "title: ").string(configSite.getApiTitre()).l();
//		wChemins.t(1, "description: ").yamlStr(2, configSite.getApiDescription());
		if(openApiVersionNumero == 2) {
			wChemins.t(1, "version: ").string(apiVersion).l();
			wChemins.t(0, "host: ").l(wChemins.js(configSite.getApiNomHote()));
			wChemins.tl(0, "schemes:");
			wChemins.tl(1, "- \"https\"");
		}
		else if(openApiVersionNumero > 2) {
			wChemins.tl(1, "version: ", apiVersion);
			wChemins.tl(0, "servers:");
			wChemins.tl(1, "- url: ", configSite.getSiteUrlBase());
		}
	}

	/**
	 * Var.enUS: writeApi
	 * r: openApiVersionNumero
	 * r.enUS: openApiVersionNumber
	 * r: wChemins
	 * r.enUS: wPaths
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: wCorpsRequetes
	 * r.enUS: wRequestBodies
	 * r: rechercheClassesLignes
	 * r.enUS: searchClassesLines
	 * r: rechercheClasses
	 * r.enUS: searchClasses
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: ClientSolrComputate
	 * r.enUS: SolrClientComputate
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: classeApiTag
	 * r.enUS: classApiTag
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: classeEstBase
	 * r.enUS: classIsBase
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeUris
	 * r.enUS: classUris
	 * r: apiEcrivains
	 * r.enUS: apiWriters
	 * r: ApiEcrivain
	 * r.enUS: ApiWriter
	 * r: apiEcrivain
	 * r.enUS: apiWriter
	 * r: AuthRoyaume
	 * r.enUS: AuthRealm
	 * r: classeDocumentSolr
	 * r.enUS: classSolrDocument
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: ClasseDocumentSolr
	 * r.enUS: ClassSolrDocument
	 * r: ClasseApiMethode
	 * r.enUS: ClassApiMethod
	 * r: WChemins
	 * r.enUS: WPaths
	 * r: WCorpsRequetes
	 * r.enUS: WRequestBodies
	 * r: ClasseUris
	 * r.enUS: ClassUris
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: classeMotsClesTrouves
	 * r.enUS: classKeywordsFound
	 * r: classeMotsCles
	 * r.enUS: classKeywords
	 * r: rechercheEntitesResultats
	 * r.enUS: searchEntitesResults
	 * r: rechercheEntitesReponse
	 * r.enUS: searchEntitesResponse
	 * r: rechercheEntitesLignes
	 * r.enUS: searchEntitesLines
	 * r: rechercheEntites
	 * r.enUS: searchEntites
	 * r: initEntite
	 * r.enUS: initEntity
	 * r: ecrireEntiteEnTete
	 * r.enUS: writeEntityHeaders
	 * r: ecrireEntiteSchema
	 * r.enUS: writeEntitySchema
	 * r: WReponseDescription
	 * r.enUS: WResponseDescription
	 * r: getEcrivains
	 * r.enUS: getWriters
	 * r: ecrireApi
	 * r.enUS: writeApi
	 * r: AppliChemin
	 * r.enUS: AppPath
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: partNumero
	 * r.enUS: partNumber
	 * 
	 * r: classeApi
	 * r.enUS: classApi
	 * 
	 * r: appPath_
	 * r.enUS: appliChemin_
	 * r: classApiTag_
	 * r.enUS: classeApiTag_
	 * r: classApi_
	 * r.enUS: classeApi_
	 * r: partIsClass_
	 * r.enUS: partEstClasse_
	 * r: partNumber_
	 * r.enUS: partNumero_
	 * r: classApiUri_
	 * r.enUS: classeApiUri_
	 * r: classIsBase_
	 * r.enUS: classeEstBase_
	 * r: classApiMethods_
	 * r.enUS: classeApiMethodes_
	 * r: classeSimpleName_
	 * r.enUS: classeNomSimple_
	 * r: classAbsolutePath_
	 * r.enUS: classeCheminAbsolu_
	 * r: classKeywordsFound_
	 * r.enUS: classeMotsClesTrouves_
	 * r: classKeywords_
	 * r.enUS: classeMotsCles_
	 * r: _frFR_
	 * r.enUS: _enUS_
	 */
	public void ecrireApi() {
		try {
			wChemins.tl(0, "paths:");

			if(openApiVersionNumero == 2) {
				wSchemas.tl(0, "definitions:");
			}
			else {
				wCorpsRequetes.tl(0, "components:");
				if(configSite.getAuthUrl() != null) {
					wCorpsRequetes.tl(1, "securitySchemes:");
						wCorpsRequetes.tl(2, "openIdConnect:");
						wCorpsRequetes.tl(3, "type: openIdConnect");
						wCorpsRequetes.tl(3, "openIdConnectUrl: ", configSite.getAuthUrl(), "/realms/", configSite.getAuthRoyaume(), "/.well-known/openid-configuration");
				}
				wCorpsRequetes.tl(1, "requestBodies:");
				wCorpsRequetes.tl(2, "ErrorResponse:");
				wCorpsRequetes.tl(3, "content:");
				wCorpsRequetes.tl(4, "application/json:");
				wCorpsRequetes.tl(5, "schema:");
				wCorpsRequetes.tl(6, "$ref: '#/components/schemas/ErrorResponse'");

				wSchemas.tl(1, "schemas:");
			}

			SolrQuery rechercheClasses = new SolrQuery();
			rechercheClasses.setQuery("*:*");
			rechercheClasses.setRows(1000000);
			rechercheClasses.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(appliChemin));
			rechercheClasses.addFilterQuery("classeApi_indexed_boolean:true");
			rechercheClasses.addFilterQuery("partEstClasse_indexed_boolean:true");
			rechercheClasses.addSort("partNumero_indexed_int", ORDER.asc);
			QueryResponse searchClassesResponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
			SolrDocumentList rechercheClassesResultats = searchClassesResponse.getResults();
			Integer rechercheClassesLignes = rechercheClasses.getRows();
			for(Long i = rechercheClassesResultats.getStart(); i < rechercheClassesResultats.getNumFound(); i+=rechercheClassesLignes) {
				for(Integer j = 0; j < rechercheClassesResultats.size(); j++) {
					SolrDocument classeDocumentSolr = rechercheClassesResultats.get(j);

					classeApiTag = StringUtils.defaultIfBlank((String)classeDocumentSolr.get("classApiTag_frFR_stored_string"), classeNomSimple + " API");
					classeApiUri = (String)classeDocumentSolr.get("classeApiUri_frFR_stored_string");
					classeEstBase = (Boolean)classeDocumentSolr.get("classeEstBase_stored_boolean");

					classeApiMethodes = (List<String>)classeDocumentSolr.get("classeApiMethodes_frFR_stored_strings");
					classeUris = new ArrayList<>();
					if(classeApiMethodes == null)
						classeApiMethodes = new ArrayList<>();
					apiEcrivains = new ArrayList<>();

					for(String classeApiMethode : classeApiMethodes) {
						ApiEcrivain apiEcrivain = new ApiEcrivain();
						apiEcrivain.setClasseDocumentSolr(classeDocumentSolr);
						apiEcrivain.setClasseApiMethode(classeApiMethode);
						apiEcrivain.setWChemins(wChemins);
						apiEcrivain.setWCorpsRequetes(wCorpsRequetes);
						apiEcrivain.setWSchemas(wSchemas);
						apiEcrivain.setOpenApiVersion(openApiVersion);
						apiEcrivain.setAppSwagger2(this);
						apiEcrivain.setClasseUris(classeUris);
						apiEcrivain.initLoinApiEcrivain(requeteSite_);
						apiEcrivains.add(apiEcrivain);
					}
					Collections.sort(apiEcrivains);

					classeNomSimple = (String)classeDocumentSolr.get("classeNomSimple_enUS_stored_string");
					classeCheminAbsolu = (String)classeDocumentSolr.get("classeCheminAbsolu_stored_string");

					classeMotsClesTrouves = BooleanUtils.isTrue((Boolean)classeDocumentSolr.get("classeMotsClesTrouves_stored_boolean"));
					classeMotsCles = (List<String>)classeDocumentSolr.get("classeMotsCles_stored_strings");

					SolrQuery rechercheEntites = new SolrQuery();
					rechercheEntites.setQuery("*:*");
					rechercheEntites.setRows(1000000);
					rechercheEntites.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));
					rechercheEntites.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
					rechercheEntites.addFilterQuery("partEstEntite_indexed_boolean:true");
					rechercheEntites.addSort("partNumero_indexed_int", ORDER.asc);
					QueryResponse rechercheEntitesReponse = siteContexte.getClientSolrComputate().query(rechercheEntites);
					SolrDocumentList rechercheEntitesResultats = rechercheEntitesReponse.getResults();
					Integer rechercheEntitesLignes = rechercheEntites.getRows();

					for(Long k = rechercheEntitesResultats.getStart(); k < rechercheEntitesResultats.getNumFound(); k+=rechercheEntitesLignes) {
						for(Integer l = 0; l < rechercheEntitesResultats.size(); l++) {
							for(ApiEcrivain apiEcrivain : apiEcrivains) {
								SolrDocument entiteDocumentSolr = rechercheEntitesResultats.get(l);

								apiEcrivain.initEntite(entiteDocumentSolr);
								apiEcrivain.ecrireEntiteEnTete();
								apiEcrivain.ecrireEntiteSchema(null);
							}
						}
						rechercheEntites.setStart(i.intValue() + rechercheEntitesLignes);
						rechercheEntitesReponse = siteContexte.getClientSolrComputate().query(rechercheEntites);
						rechercheEntitesResultats = rechercheEntitesReponse.getResults();
						rechercheEntitesLignes = rechercheEntites.getRows();
					}
					for(ApiEcrivain apiEcrivain : apiEcrivains) {
						apiEcrivain.getEcrivains().flushClose();
						apiEcrivain.ecrireApi();
					}

					for(ApiEcrivain apiEcrivain : apiEcrivains) {
						apiEcrivain.getWReponseDescription().flushClose();
					}
				}
				rechercheClasses.setStart(i.intValue() + rechercheClassesLignes);
				searchClassesResponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
				rechercheClassesResultats = searchClassesResponse.getResults();
				rechercheClassesLignes = rechercheClasses.getRows();
			}
			wSchemas.tl(tabsSchema, "ErrorResponse:");
			wSchemas.tl(tabsSchema + 1, "required:");
			wSchemas.tl(tabsSchema + 2, "- type");
			wSchemas.tl(tabsSchema + 2, "- code");
			wSchemas.tl(tabsSchema + 1, "properties:");
			wSchemas.tl(tabsSchema + 2, "type:");
			wSchemas.tl(tabsSchema + 3, "type: string");
			wSchemas.tl(tabsSchema + 3, "enum:");
			wSchemas.tl(tabsSchema + 4, "- ERROR");
			wSchemas.tl(tabsSchema + 4, "- WARN");
			wSchemas.tl(tabsSchema + 4, "- INVALID");
			wSchemas.tl(tabsSchema + 4, "- FATAL");
			wSchemas.t(tabsSchema + 3, "description: ").yamlStr(tabsSchema + 4, "<br>invalid - Request did not confirm to the specification and was unprocessed & rejected. Please fix the value and try again</br>                         <br>warn - Request was partially processed.  E.g. some of the fields are missing in response to the system issues,  request was accepted successfully but will be processed asynchronously</br>                                                          <br>error - The request was accepted but could not be processed successfully</br>            <br>fatal - There was an internal system error while processing the request. These are technical errors and will be resolved by Citi, and the consumer should retry after some time. Business errors will not be categorized as fatal </br>");
			wSchemas.tl(tabsSchema + 2, "code:");
			wSchemas.tl(tabsSchema + 3, "type: string");
			wSchemas.tl(tabsSchema + 3, "description: Error code which qualifies the error. ");
			wSchemas.tl(tabsSchema + 2, "details:");
			wSchemas.tl(tabsSchema + 3, "type: string");
			wSchemas.tl(tabsSchema + 3, "description: Human readable explanation specific to the occurrence of the problem. ");
			wSchemas.tl(tabsSchema + 2, "location:");
			wSchemas.tl(tabsSchema + 3, "type: string");
			wSchemas.tl(tabsSchema + 3, "description: The name of the field that resulted in the error. ");
			wSchemas.tl(tabsSchema + 2, "moreInfo:");
			wSchemas.tl(tabsSchema + 3, "type: string");
			wSchemas.tl(tabsSchema + 3, "description: URI to human readable documentation of the error. ");
		} catch (Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}
}
