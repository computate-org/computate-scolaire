package org.computate.scolaire.enUS.writer;

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
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.vertx.AppSwagger2;

public class ApiWriter extends ApiWriterGen<Object> implements Comparable<ApiWriter> {

	protected void _siteRequest_(Wrap<SiteRequestEnUS> c) {
	}

	protected void _classSolrDocument(Wrap<SolrDocument> c) {
	}

	protected void _classApiMethod(Wrap<String> c) {
	}

	protected void _openApiVersion(Wrap<String> c) {
	}

	protected void _appSwagger2(Wrap<AppSwagger2> c) { 
	}

	protected void _classUris(Wrap<List<String>> c) {
	}

	protected void _openApiVersionNumber(Wrap<Integer> c) {
		c.o((int)Double.parseDouble(StringUtils.substringBefore(openApiVersion, ".")));
	}

	protected void _tabsSchema(Wrap<Integer> c) {
		if(openApiVersionNumber == 2)
			c.o(1);
		else
			c.o(2);
	}

	protected void _tabsResponses(Wrap<Integer> c) {
		if(StringUtils.equals(openApiVersion, "2.0"))
			c.o(0);
		else
			c.o(2);
	}

	protected void _wPaths(Wrap<AllWriter> c) {
	}

	protected void _wRequestBodies(Wrap<AllWriter> c) {
	}

	protected void _wSchemas(Wrap<AllWriter> c) {
	}

	protected void _siteContext(Wrap<SiteContextEnUS> c) {
		c.o(siteRequest_.getSiteContext_());
	}

	protected void _siteConfig(Wrap<SiteConfig> c) {
		c.o(siteRequest_.getSiteConfig_());
	}

	protected void _wRequestHeaders(Wrap<AllWriter> c) {
		c.o(AllWriter.create(siteRequest_, "  "));
	}

	protected void _wRequestDescription(Wrap<AllWriter> c) {
		c.o(AllWriter.create(siteRequest_, "  "));
	}

	protected void _wResponseDescription(Wrap<AllWriter> c) {
		c.o(AllWriter.create(siteRequest_, "  "));
	}

	protected void _wRequestBody(Wrap<AllWriter> c) {
		c.o(AllWriter.create(siteRequest_, "  "));
	}

	protected void _wResponseBody(Wrap<AllWriter> c) {
		c.o(AllWriter.create(siteRequest_, "  "));
	}

	protected void _wRequestSchema(Wrap<AllWriter> c) {
		c.o(AllWriter.create(siteRequest_, "  "));
	}

	protected void _wResponseSchema(Wrap<AllWriter> c) {
		c.o(AllWriter.create(siteRequest_, "  "));
	}

	protected void _writers(Wrap<AllWriters> c) {
		c.o(AllWriters.create(siteRequest_));
	}

	protected void _classApiTag(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeApiTag_enUS_stored_string"));
	}

	protected void _classExtendsBase(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classeEtendBase_stored_boolean"));
	}

	protected void _classIsBase(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classeEstBase_stored_boolean"));
	}

	protected void _classSimpleName(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeNomSimple_enUS_stored_string"));
	}

	protected void _classAbsolutePath(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeCheminAbsolu_enUS_stored_string"));
	}

	protected void _classApiUriMethod(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeApiUri" + classApiMethod + "_enUS_stored_string"));
	}

	protected void _classApiMethodMethod(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeApiMethode" + classApiMethod + "_enUS_stored_string"));
	}

	protected void _classApiMediaType200Method(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeApiTypeMedia200" + classApiMethod + "_enUS_stored_string"));
	}

	protected void _classApiOperationIdMethod(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeApiOperationId" + classApiMethod + "_enUS_stored_string"));
	}

	protected void _classApiOperationIdMethodRequest(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeApiOperationId" + classApiMethod + "Requete_enUS_stored_string"));
	}

	protected void _classApiOperationIdMethodResponse(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeApiOperationId" + classApiMethod + "Reponse_enUS_stored_string"));
	}

	protected void _classSuperApiOperationIdMethodRequest(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeSuperApiOperationId" + classApiMethod + "Requete_enUS_stored_string"));
	}

	protected void _classSuperApiOperationIdMethodResponse(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classeSuperApiOperationId" + classApiMethod + "Reponse_enUS_stored_string"));
	}

	protected void _classKeywordsFound(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classeMotsClesTrouves_stored_boolean"));
	}

	protected void _classKeywords(Wrap<List<String>> c) {
		List<String> o = (List<String>)classSolrDocument.get("classeMotsCles_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	protected void _classRolesFound(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classeRolesTrouves_stored_boolean"));
	}

	protected void _classRoles(Wrap<List<String>> c) {
		List<String> o = (List<String>)classSolrDocument.get("classeRoles_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	protected void _classRolesLanguage(Wrap<List<String>> c) {
		List<String> o = (List<String>)classSolrDocument.get("classeRolesLangue_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	protected void _languageName(Wrap<String> c) {
		c.o("enUS");
	}

	protected void _entitySolrDocument(Wrap<SolrDocument> c) {
	}

	String entityVar;

	String entityCanonicalName;

	String entityCanonicalNameGeneric;

	String entityVarApi;

	String entityDescription;

	String entityDisplayName;

	Integer entityMinLength;

	Integer entityMaxLength;

	Double entityMin;

	Double entityMax;

	Boolean entityOptional;

	String entityVarCapitalized;

	String entityJsonType;

	String entityListJsonType;

	String entityJsonFormat;

	Boolean entityPrimaryKey;

	Boolean entityStored;

	Boolean entityIndexed;

	Boolean entityKeywordsFound;

	List<String> entityKeywords;

	List<String> entityOptionsVar;

	List<String> entityOptionsDescription;

	public void  initEntity(SolrDocument entitySolrDocument) {
		setEntitySolrDocument(entitySolrDocument);
		entityVar = (String)entitySolrDocument.get("entiteVar_enUS_stored_string");
		entityVarApi = StringUtils.defaultIfBlank((String)entitySolrDocument.get("entiteVarApi_stored_string"), entityVar);
		entityKeywordsFound = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entiteMotsClesTrouves_stored_boolean"));
		entityKeywords = (List<String>)entitySolrDocument.get("entiteMotsCles_stored_strings");
		if(entityKeywords == null)
			entityKeywords = new ArrayList<>();
		entityCanonicalNameGeneric = (String)entitySolrDocument.get("entiteNomCanoniqueGenerique_enUS_stored_string");
		entityCanonicalName = (String)entitySolrDocument.get("entiteNomCanonique_enUS_stored_string");
		entityListJsonType = (String)entitySolrDocument.get("entiteListeTypeJson_stored_string");
		entityJsonType = (String)entitySolrDocument.get("entiteTypeJson_stored_string");
		entityJsonFormat = (String)entitySolrDocument.get("entiteFormatJson_stored_string");
		entityOptionsVar = (List<String>)entitySolrDocument.get("entiteOptionsVar_stored_strings");
		entityOptionsDescription = (List<String>)entitySolrDocument.get("entiteOptionsDescription_stored_strings");
		entityDescription = (String)entitySolrDocument.get("entiteDescription_stored_string");
	}

	public void  writeEntityHeaders() throws Exception, Exception {

		if(entityKeywords.contains(classApiMethod + ".header")) {
			wRequestHeaders.tl(4, "- name: ", entityVarApi);
			wRequestHeaders.tl(5, "in: header");
			wRequestHeaders.t(5, "description: ").yamlStr(6, entityDescription);
			if(entityKeywords.contains(classApiMethod + ".header.required"))
				wRequestHeaders.tl(5, "required: true");
			wRequestHeaders.tl(5, "type: ", entityJsonType);
		}
	}

	public void  writeEntityDescription(Integer numberTabs) throws Exception, Exception {
		writeEntityDescription(numberTabs, wRequestDescription, "request");
		writeEntityDescription(numberTabs, wResponseDescription, "response");
	}

	public void  writeEntityDescription(Integer numberTabs, AllWriter w, String apiRequestOrResponse) throws Exception, Exception {
		numberTabs = numberTabs == null ? 0 : numberTabs;
		
		if(
				entityKeywords.contains("apiModelEntity")
				|| entityKeywords.contains(classApiMethod + "." + apiRequestOrResponse)
				) {
			w.l();
			w.t(numberTabs, "- " + entityVar);
			if(StringUtils.isNotBlank(entityDisplayName))
				w.s(" (" + entityDisplayName + ")");
			w.l(": ");
			AllWriter wDescription = AllWriter.create(siteRequest_, "  ");
			if(StringUtils.isNotBlank(entityDescription))
				wDescription.l(entityDescription);
			if(BooleanUtils.isTrue(entityOptional))
				wDescription.tl(numberTabs + 1, "- optional: ", entityOptional);
			if(entityMinLength != null)
				wDescription.tl(numberTabs + 1, "- min length: ", entityMinLength);
			if(entityMaxLength != null)
				wDescription.tl(numberTabs + 1, "- max length: ", entityMaxLength);
			if(entityMin != null)
				wDescription.tl(numberTabs + 1, "- min: ", entityMin);
			if(entityMax != null)
				wDescription.tl(numberTabs + 1, "- max: ", entityMax);
			if(entityOptionsVar != null && entityOptionsDescription != null && entityOptionsVar.size() > 0 && entityOptionsDescription.size() == entityOptionsVar.size()) {
				wDescription.tl(numberTabs + 1, "- enum:");
				for(int m = 0; m < entityOptionsVar.size(); m++) {
					wDescription.tl(numberTabs + 2, "- " + entityOptionsVar.get(m) + ": " + entityOptionsDescription.get(m));
				}
			}
			if(entityKeywords.contains("apiModel")) {
				SolrQuery searchEntities = new SolrQuery();
				searchEntities.setQuery("*:*");
				searchEntities.setRows(1000000);
				searchEntities.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(siteConfig.getAppPath()));

				if(StringUtils.isBlank(entityCanonicalNameGeneric))
					searchEntities.addFilterQuery("classeNomCanonique_enUS_indexed_string:" + ClientUtils.escapeQueryChars(entityCanonicalName));
				else
					searchEntities.addFilterQuery("classeNomCanonique_enUS_indexed_string:" + ClientUtils.escapeQueryChars(entityCanonicalNameGeneric));

				searchEntities.addFilterQuery("entiteMotsCles_indexed_strings:" + ClientUtils.escapeQueryChars("apiModelEntity"));
				searchEntities.addFilterQuery("partEstEntite_indexed_boolean:true");
				searchEntities.addSort("partNumero_indexed_int", ORDER.asc);
				QueryResponse searchEntitiesReponse = siteContext.getSolrClientComputate().query(searchEntities);
				SolrDocumentList searchEntitiesResults = searchEntitiesReponse.getResults();
				Integer searchEntitiesLines = searchEntities.getRows();

				if(searchEntitiesResults.size() > 0) {
					for(Long k = searchEntitiesResults.getStart(); k < searchEntitiesResults.getNumFound(); k+=searchEntitiesLines) {
						for(Integer l = 0; l < searchEntitiesResults.size(); l++) {
							SolrDocument entitySolrDocument = searchEntitiesResults.get(l);
							String entityVarOld = entityVar;
							String entityVarApiOld = entityVarApi;
							Boolean entityKeywordsFoundOld = entityKeywordsFound;
							List<String> entityKeywordsAncien = entityKeywords;
							String entityCanonicalNameGenericOld = entityCanonicalNameGeneric;
							String entityCanonicalNameOld = entityCanonicalName;
							String entityListJsonTypeOld = entityListJsonType;
							String entityJsonTypeOld = entityJsonType;
							String entityJsonFormatOld = entityJsonFormat;
							List<String> entityOptionsVarOld = entityOptionsVar;
							List<String> entityOptionsDescriptionOld = entityOptionsDescription;
							String entityDescriptionOld = entityDescription;
							
							entityVar = (String)entitySolrDocument.get("entiteVar_enUS_stored_string");
							entityVarApi = StringUtils.defaultIfBlank((String)entitySolrDocument.get("entiteVarApi_stored_string"), entityVar);
							entityKeywordsFound = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entiteMotsClesTrouves_stored_boolean"));
							entityKeywords = (List<String>)entitySolrDocument.get("entiteMotsCles_stored_strings");
							if(entityKeywords == null)
								entityKeywords = new ArrayList<>();
							entityCanonicalNameGeneric = (String)entitySolrDocument.get("entiteNomCanoniqueGenerique_enUS_stored_string");
							entityCanonicalName = (String)entitySolrDocument.get("entiteNomCanonique_enUS_stored_string");
							entityListJsonType = (String)entitySolrDocument.get("entiteListeTypeJson_stored_string");
							entityJsonType = (String)entitySolrDocument.get("entiteTypeJson_stored_string");
							entityJsonFormat = (String)entitySolrDocument.get("entiteFormatJson_stored_string");
							entityOptionsVar = (List<String>)entitySolrDocument.get("entiteOptionsVar_stored_strings");
							entityOptionsDescription = (List<String>)entitySolrDocument.get("entiteOptionsDescription_stored_strings");
							entityDescription = (String)entitySolrDocument.get("entiteDescription_stored_string");
	
							writeEntityDescription(numberTabs + 3, w, apiRequestOrResponse);
//							if(entiteCanonicalNameGeneric == null) {
//								writeEntityDescription(tabStep + 3, w, classApiMethod, apiRequestOrResponse);
//							}
//							else {
//								writeEntityDescription(tabStep + 5, w, classApiMethod, apiRequestOrResponse);
//							}
							
							entityVar = entityVarOld;
							entityVarApi = entityVarApiOld;
							entityKeywordsFound = entityKeywordsFoundOld;
							entityKeywords = entityKeywordsAncien;
							entityCanonicalNameGeneric = entityCanonicalNameGenericOld;
							entityCanonicalName = entityCanonicalNameOld;
							entityListJsonType = entityListJsonTypeOld;
							entityJsonType = entityJsonTypeOld;
							entityJsonFormat = entityJsonFormatOld;
							entityOptionsVar = entityOptionsVarOld;
							entityOptionsDescription = entityOptionsDescriptionOld;
							entityDescription = entityDescriptionOld;
						}
					}
				}
			}
			w.s(StringUtils.trim(wDescription.toString()));
		}
	}

	public void  writeEntitySchema(Integer numberTabs) throws Exception, Exception {
		writeEntitySchema(numberTabs, wRequestSchema, "request");
		writeEntitySchema(numberTabs, wResponseSchema, "response");
	}

	public void  writeEntitySchema(Integer numberTabs, AllWriter w, String apiRequestOrResponse) throws Exception, Exception {
		numberTabs = numberTabs == null ? (classApiMethod.contains("Search") && "response".equals(apiRequestOrResponse) ? 1 : 0) : numberTabs;
		if(entityJsonType != null) {
			w.tl(4 + tabsSchema + numberTabs, entityVarApi, ":");
			w.tl(5 + tabsSchema + numberTabs, "type: ", entityJsonType);
			if(entityListJsonType == null && entityOptionsVar != null && entityOptionsDescription != null && entityOptionsVar.size() > 0 && entityOptionsDescription.size() == entityOptionsVar.size()) {
				w.tl(5 + tabsSchema + numberTabs, "enum:");
				for(String entiteOptionVar : entityOptionsVar) {
					w.tl(6 + tabsSchema + numberTabs, "- ", entiteOptionVar);
					
				}
			}
			if(entityListJsonType != null) {
				w.tl(5 + tabsSchema + numberTabs, "items:");
				w.tl(6 + tabsSchema + numberTabs, "type: ", entityListJsonType);
				if(entityOptionsVar != null && entityOptionsDescription != null && entityOptionsVar.size() > 0 && entityOptionsDescription.size() == entityOptionsVar.size()) {
					w.tl(6 + tabsSchema + numberTabs, "enum:");
					for(String entiteOptionVar : entityOptionsVar) {
						w.tl(7 + tabsSchema + numberTabs, "- ", entiteOptionVar);
					}
				}
			}
			if(StringUtils.isNotBlank(entityDescription))
				w.t(5 + tabsSchema + numberTabs, "description: ").yamlStr(7 + numberTabs, "- " + entityDescription);
			if(entityJsonFormat != null)
				w.tl(5 + tabsSchema + numberTabs, "format: ", entityJsonFormat);
		}
	}

	public void  writeApi() throws Exception, Exception {

			if(!classUris.contains(classApiUriMethod)) {
				wPaths.tl(1, classApiUriMethod, ":");
				classUris.add(classApiUriMethod);
			}
	
			wPaths.tl(2, StringUtils.lowerCase(classApiMethodMethod), ":");
			wPaths.tl(3, "operationId: ", classApiOperationIdMethod);
			wPaths.tl(3, "x-vertx-event-bus: ", languageName, classSimpleName);
	
			if(classRolesFound) {
				wPaths.tl(3, "security:");
				wPaths.tl(4, "- openIdConnect:");
				for(int i = 0; i < classRoles.size(); i++) {
					if("frFR".equals(classRolesLanguage.get(i)))
						wPaths.tl(5, "- ", classRoles.get(i));
				}
			}
	
			wPaths.t(3, "description: ").yamlStr(4, "");
			wPaths.t(3, "summary: ").yamlStr(4, "");
			if(StringUtils.isNotBlank(classApiTag)) {
				wPaths.tl(3, "tags:");
				wPaths.tl(4, "- ", classApiTag);
			}
	
			if(openApiVersionNumber == 2) {
				wPaths.tl(3, "produces:");
				wPaths.tl(4, "- ", classApiMediaType200Method);
			}
	
		if(!wRequestHeaders.getEmpty() || "GET".equals(classApiMethodMethod) || "DELETE".equals(classApiMethodMethod) || "PUT".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			wPaths.tl(3, "parameters:");
			wPaths.s(wRequestHeaders);
			if("GET".equals(classApiMethod) || "DELETE".equals(classApiMethodMethod) || "PUT".equals(classApiMethodMethod)) {
				wPaths.tl(4, "- name: id");
				wPaths.tl(5, "in: path");
				wPaths.t(5, "description: ").yamlStr(6, "");
				wPaths.tl(5, "required: true");
				wPaths.tl(5, "schema:");
				wPaths.tl(6, "type: string");
			}
			else if(classApiMethod.contains("Search") || classApiMethod.contains("PATCH")) {
				wPaths.tl(4, "- in: query");
				wPaths.tl(5, "name: q");
				wPaths.tl(5, "description: ''");
				wPaths.tl(5, "required: false");
				wPaths.tl(5, "schema:");
				wPaths.tl(6, "type: string");
				wPaths.tl(6, "default: '*:*'");
				wPaths.tl(4, "- in: query");
				wPaths.tl(5, "name: fq");
				wPaths.tl(5, "description: ''");
				wPaths.tl(5, "required: false");
				wPaths.tl(5, "schema:");
				wPaths.tl(6, "type: array");
				wPaths.tl(6, "items:");
				wPaths.tl(7, "  type: string");
				wPaths.tl(4, "- in: query");
				wPaths.tl(5, "name: fl");
				wPaths.tl(5, "description: ''");
				wPaths.tl(5, "required: false");
				wPaths.tl(5, "schema:");
				wPaths.tl(6, "type: string");
				wPaths.tl(4, "- in: query");
				wPaths.tl(5, "name: sort");
				wPaths.tl(5, "description: ''");
				wPaths.tl(5, "required: false");
				wPaths.tl(5, "schema:");
				wPaths.tl(6, "type: array");
				wPaths.tl(6, "items:");
				wPaths.tl(7, "  type: string");
				wPaths.tl(4, "- in: query");
				wPaths.tl(5, "name: start");
				wPaths.tl(5, "description: ''");
				wPaths.tl(5, "required: false");
				wPaths.tl(5, "schema:");
				wPaths.tl(6, "type: integer");
				wPaths.tl(6, "default: 0");
				wPaths.tl(6, "minimum: 0");
				wPaths.tl(4, "- in: query");
				wPaths.tl(5, "name: rows");
				wPaths.tl(5, "description: ''");
				wPaths.tl(5, "required: false");
				wPaths.tl(5, "schema:");
				wPaths.tl(6, "type: integer");
				wPaths.tl(6, "default: 10");
				wPaths.tl(6, "minimum: 1");
			}
		}

		if(openApiVersionNumber > 2) {
			if(classApiMethodMethod == null)
				throw new Exception("Expected a comment for " + this.toString() + " like : ApiMethod: ...");
			if(!classApiMethodMethod.contains("GET") && !classApiMethodMethod.contains("DELETE")) {
				wPaths.tl(3, "requestBody:");
				String strRequestDescription = wRequestDescription.toString();
				wPaths.t(4, "description: ").yamlStr(5, StringUtils.trim(strRequestDescription));
				wPaths.tl(4, "required: true");
				wPaths.tl(4, "content:");
				wPaths.tl(5, "application/json:");
				wPaths.tl(6, "schema:");
				wPaths.tl(7, "$ref: '#/components/schemas/", classApiOperationIdMethodRequest, "'");
			}
		}
		else {
			wPaths.tl(4, "- name: \"", classApiOperationIdMethodRequest, "\"");
			wPaths.tl(5, "in: body");

			String strRequestDescription = wRequestDescription.toString();
//			if(StringUtils.isNotBlank(StringUtils.trim(strRequestDescription)))
				wPaths.t(5, "description: ").yamlStr(6, StringUtils.trim(strRequestDescription));
//			else
//				wPaths.tl(5, "description: ''");
			
			wPaths.tl(5, "schema:");
			wPaths.tl(6, "$ref: '#/components/requestBodies/", classApiOperationIdMethodRequest, "'");
		}

		wPaths.tl(3, "responses:");
		wPaths.tl(4, "'200':");
		if(openApiVersionNumber > 2) {
			String strResponseDescription = wResponseDescription.toString();
			wPaths.t(5, "description: ").yamlStr(6, strResponseDescription);
			wPaths.tl(5, "content:");
			wPaths.tl(6, classApiMediaType200Method, "; charset=utf-8:");
		}
		else {
	
			String strResponseDescription = wResponseDescription.toString();
	//		if(StringUtils.isNotBlank(StringUtils.trim(strResponseDescription)))
				wPaths.t(5 + tabsResponses, "description: ").yamlStr(6, strResponseDescription);
	//		else
	//			wPaths.tl(5, "description: ''");
		}

		wPaths.tl(5 + tabsResponses, "schema:");
		wPaths.tl(6 + tabsResponses, "$ref: '#/components/requestBodies/", classApiOperationIdMethodResponse, "'");
		wPaths.tl(4, "'400':");
		if(openApiVersionNumber > 2) {
			wPaths.t(5, "description: ").yamlStr(6 + tabsResponses, "<table><table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>invalidRequest</td><td>Missing or invalid Parameters</td></tr></table>");
			wPaths.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wPaths.t(5 + tabsResponses, "description: ").yamlStr(6 + tabsResponses, "<table><table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>invalidRequest</td><td>Missing or invalid Parameters</td></tr></table>");
		}
		wPaths.tl(5 + tabsResponses, "schema:");
		wPaths.tl(6 + tabsResponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wPaths.tl(4, "'401':");
		if(openApiVersionNumber > 2) {
			wPaths.t(5, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>unAuthorized</td><td>Authorization credentials are missing or invalid</td></tr></table>");
			wPaths.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wPaths.t(5 + tabsResponses, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>error</td><td>unAuthorized</td><td>Authorization credentials are missing or invalid</td></tr></table>");
		}
		wPaths.tl(5 + tabsResponses, "schema:");
		wPaths.tl(6 + tabsResponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wPaths.tl(4, "'403':");
		if(openApiVersionNumber > 2) {
			wPaths.t(5, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>accessNotConfigured</td><td>The request operation is not configured to access this resource</td><td>Channel/Country/Business provided in the request is not supported currently</td></tr></table>");
			wPaths.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wPaths.t(5 + tabsResponses, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>accessNotConfigured</td><td>The request operation is not configured to access this resource</td><td>Channel/Country/Business provided in the request is not supported currently</td></tr></table>");
		}
		wPaths.tl(5 + tabsResponses, "schema:");
		wPaths.tl(6 + tabsResponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wPaths.tl(4, "'404':");
		if(openApiVersionNumber > 2) {
			wPaths.t(5, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>resourceNotFound</td><td>The requested resource was not found</td><td>Empty resource/resource not found</td></tr></table>");
			wPaths.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wPaths.t(5 + tabsResponses, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td><td>More Info</td></tr><tr><td>error</td><td>resourceNotFound</td><td>The requested resource was not found</td><td>Empty resource/resource not found</td></tr></table>");
		}
		wPaths.tl(5 + tabsResponses, "schema:");
		wPaths.tl(6 + tabsResponses, "$ref: '#/components/requestBodies/ErrorResponse'");
		wPaths.tl(4, "'500':");
		if(openApiVersionNumber > 2) {
			wPaths.t(5, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>fatal</td><td>serverUnavailable</td><td>The request failed due to an internal error/server unavailability</td></tr></table>");
			wPaths.tl(5, "content:").tl(6, "application/json:");
		}
		else {
			wPaths.t(5 + tabsResponses, "description: ").yamlStr(6 + tabsResponses, "<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>fatal</td><td>serverUnavailable</td><td>The request failed due to an internal error/server unavailability</td></tr></table>");
		}
		wPaths.tl(5 + tabsResponses, "schema:");
		wPaths.tl(6 + tabsResponses, "$ref: '#/components/requestBodies/ErrorResponse'");

		if(openApiVersionNumber > 2) {
			if(!"GET".equals(classApiMethodMethod) && !"DELETE".equals(classApiMethodMethod)) {
				wRequestBodies.tl(2, classApiOperationIdMethodRequest, ":");
				wRequestBodies.tl(3, "content:");
				wRequestBodies.tl(4, "application/json:");
				wRequestBodies.tl(5, "schema:");
				wRequestBodies.tl(6, "$ref: '#/components/schemas/", classApiOperationIdMethodRequest, "'");
			}
			wRequestBodies.tl(2, classApiOperationIdMethodResponse, ":");
			wRequestBodies.tl(3, "content:");
			wRequestBodies.tl(4, classApiMediaType200Method, "; charset=utf-8:");
			wRequestBodies.tl(5, "schema:");
			wRequestBodies.tl(6, "$ref: '#/components/schemas/", classApiOperationIdMethodResponse, "'");
		}

		if(!"GET".equals(classApiMethodMethod) && !"DELETE".equals(classApiMethodMethod)) {
//		if(classeMotsClesTrouves && classeMotsCles.contains(classApiMethod + ".request")) {
			wSchemas.tl(tabsSchema, classApiOperationIdMethodRequest, ":");
			wSchemas.tl(tabsSchema + 1, "allOf:");
			if(BooleanUtils.isTrue(classExtendsBase)) {
				wSchemas.tl(tabsSchema + 2, "- $ref: \"#/components/schemas/", classSuperApiOperationIdMethodRequest, "\"");
			}
			wSchemas.tl(tabsSchema + 2, "- type: object");
			wSchemas.tl(tabsSchema + 3, "properties:");
			wSchemas.s(wRequestSchema.toString());
		}

//		if(classeMotsClesTrouves && classeMotsCles.contains(classApiMethod + ".response")) {
			wSchemas.tl(tabsSchema, classApiOperationIdMethodResponse, ":");
			wSchemas.tl(tabsSchema + 1, "allOf:");
			if("text/html".equals(classApiMediaType200Method)) {
				wSchemas.tl(tabsSchema + 2, "- type: string");
			}
			else {
				if(BooleanUtils.isTrue(classExtendsBase)) {
					wSchemas.tl(tabsSchema + 2, "- $ref: \"#/components/schemas/", classSuperApiOperationIdMethodResponse, "\"");
				}
	
				if(classApiMethod.contains("Search")) {
					wSchemas.tl(tabsSchema + 2, "- type: array");
					wSchemas.tl(tabsSchema + 3, "items:");
					wSchemas.tl(tabsSchema + 4, "type: object");
					wSchemas.tl(tabsSchema + 4, "properties:");
				}
				else {
					wSchemas.tl(tabsSchema + 2, "- type: object");
					wSchemas.tl(tabsSchema + 3, "properties:");
				}
				wSchemas.s(wResponseSchema.toString());
			}
//		}
	}

	@Override()
	public int compareTo(ApiWriter o) {
		return ObjectUtils.compare(classApiUriMethod, o.getClassApiUriMethod());
	}

	@Override()
	public String toString() {
		return (String)classSolrDocument.get("classSimpleName_enUS_stored_string") + " " + classApiMethod;
	}
}
