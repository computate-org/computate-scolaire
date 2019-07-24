package org.computate.enUS.school.writer;

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

public class ApiWriter extends ApiWriterGen<Object> implements Comparable<ApiWriter> {

	protected void _requeteSite_(Wrap<SiteRequestEnUS> c) {
	}

	protected void _classeDocumentSolr(Wrap<SolrDocument> c) {
	}

	protected void _classApiMethod(Wrap<String> c) {
	}

	protected void _openApiVersion(Wrap<String> c) {
	}

	protected void _appSwagger2(Wrap<AppliSwagger2> c) {
	}

	protected void _classeUris(Wrap<List<String>> c) {
	}

	protected void _openApiVersionNumber(Wrap<Integer> c) {
		c.o((int)Double.parseDouble(StringUtils.substringBefore(openApiVersion, ".")));
	}

	protected void _tabsSchema(Wrap<Integer> c) {
		if(openApiVersionNumero == 2)
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
		c.o(requeteSite_.getSiteContext_());
	}

	protected void _siteConfig(Wrap<SiteConfig> c) {
		c.o(requeteSite_.getSiteConfig_());
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

	protected void _ecrivains(Wrap<TousEcrivains> c) {
		c.o(TousEcrivains.create(siteRequest_));
	}

	protected void _classApiTag(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classApiTag_stored_string"));
	}

	protected void _classExtendsBase(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classExtendsBase_stored_boolean"));
	}

	protected void _classIsBase(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classIsBase_stored_boolean"));
	}

	protected void _classSimpleName(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classSimpleName_enUS_stored_string"));
	}

	protected void _classAbsolutePath(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classCheminAbsolu_enUS_stored_string"));
	}

	protected void _classApiUriMethod(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classApiUri" + classApiMethod + "_stored_string"));
	}

	protected void _classApiUriMethodMethod(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classApiMethod" + classApiMethod + "_stored_string"));
	}

	protected void _classApiMediaType200Method(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classApiMediaType200" + classApiMethod + "_stored_string"));
	}

	protected void _classApiOperationIdMethod(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classApiOperationId" + classApiMethod + "_stored_string"));
	}

	protected void _classApiOperationIdMethodRequest(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classApiOperationId" + classApiMethod + "Request_stored_string"));
	}

	protected void _classApiOperationIdMethodResponse(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classApiOperationId" + classApiMethod + "Response_stored_string"));
	}

	protected void _classSuperApiOperationIdMethodRequest(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classSuperApiOperationId" + classApiMethod + "Request_stored_string"));
	}

	protected void _classSuperApiOperationIdMethodResponse(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classSuperApiOperationId" + classApiMethod + "Response_stored_string"));
	}

	protected void _classKeywordsFound(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classKeywordsFound_stored_boolean"));
	}

	protected void _classKeywords(Wrap<List<String>> c) {
		List<String> o = (List<String>)classSolrDocument.get("classeMotsCles_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	protected void _classRolesFound(Wrap<Boolean> c) {
		c.o((Boolean)classSolrDocument.get("classRolesFound_stored_boolean"));
	}

	protected void _classRoles(Wrap<List<String>> c) {
		List<String> o = (List<String>)classSolrDocument.get("classeRoles_stored_strings");
		if(o == null)
			o = new ArrayList<>();
		c.o(o);
	}

	protected void _classPageLanguageName(Wrap<String> c) {
		c.o((String)classSolrDocument.get("classPageLanguageName" + classApiMethod + "_stored_string"));
	}

	protected void _classPageSimpleName(Wrap<String> c) {
		c.o((String)classeDocumentSolr.get("classSolrDocument" + classeApiMethode + "_stored_string"));
	}

	protected void _langueNom(Wrap<String> c) {
		String o = "enUS";
		if(classPageLanguageName != null) {
			o = classPageLanguageName;
		}
		c.o(o);
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

	public void  writeEntityHeaders() throws Exception, Exception {

		if(entiteMotsCles.contains(classeApiMethode + ".header")) {
			wRequeteEnTete.tl(4, "- name: ", entiteVarApi);
			wRequeteEnTete.tl(5, "in: header");
			wRequeteEnTete.t(5, "description: ").yamlStr(6, entiteDescription);
			if(entiteMotsCles.contains(classeApiMethode + ".header.required"))
				wRequeteEnTete.tl(5, "required: true");
			wRequeteEnTete.tl(5, "type: ", entiteTypeJson);
		}
	}

	public void  ecrireEntiteDescription(Integer nombreTabulations) throws Exception, Exception {
		ecrireEntiteDescription(nombreTabulations, wRequeteDescription, "requete");
		ecrireEntiteDescription(nombreTabulations, wReponseDescription, "reponse");
	}

	public void  ecrireEntiteDescription(Integer nombreTabulations, AllWriter w, String apiRequeteOuReponse) throws Exception, Exception {
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

	public void  ecrireEntiteSchema(Integer nombreTabulations) throws Exception, Exception {
		ecrireEntiteSchema(nombreTabulations, wRequeteSchema, "requete");
		ecrireEntiteSchema(nombreTabulations, wReponseSchema, "reponse");
	}

	public void  ecrireEntiteSchema(Integer nombreTabulations, AllWriter w, String apiRequeteOuReponse) throws Exception, Exception {
		nombreTabulations = nombreTabulations == null ? (classeApiMethode.contains("Recherche") && "reponse".equals(apiRequeteOuReponse) ? 1 : 0) : nombreTabulations;
		if(
				entiteTypeJson != null
//				entiteMotsCles.contains("apiModeleEntite")
//				|| entiteMotsCles.contains(classeApiMethode + "." + apiRequeteOuReponse)
				) {
//		if(classeMotsClesTrouves && entiteMotsClesTrouves && (
//				entiteMotsCles.contains("apiModeleEntite")
//				|| entiteMotsCles.contains(classeApiMethode + "." + apiRequeteOuReponse)
//				)) {
//			if(entiteMotsClesTrouves && entiteMotsCles.contains("apiModel")) {
//				SolrQuery searchEntities = new SolrQuery();
//				searchEntities.setQuery("*:*");
//				searchEntities.setRows(1000000);
//				searchEntities.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));
//
//				if(StringUtils.isBlank(entiteNomCanoniqueGenerique))
//					searchEntities.addFilterQuery("classeNomCanonique_frFR_indexed_string:" + ClientUtils.escapeQueryChars(entiteNomCanonique));
//				else
//					searchEntities.addFilterQuery("classNomCanonique_frFR_indexed_string:" + ClientUtils.escapeQueryChars(entiteNomCanoniqueGenerique));
//
//				searchEntities.addFilterQuery("entiteMotsCles_indexed_strings:" + ClientUtils.escapeQueryChars("apiModelEntity"));
//				searchEntities.addFilterQuery("partEstEntite_indexed_boolean:true");
//				searchEntities.addSort("partNumero_indexed_int", ORDER.asc);
//				QueryResponse searchEntitiesResponse = siteContexte.getClientSolrComputate().query(searchEntities);
//				SolrDocumentList searchEntitiesResults = searchEntitiesResponse.getResults();
//				Integer searchEntitiesLignes = searchEntities.getRows();
//
//				if(searchEntitiesResults.size() > 0) {
//					if(entiteNomCanoniqueGenerique == null) {
//						w.tl(6 + nombreTabulations, "type: object");
//						w.tl(6 + nombreTabulations, "properties:");
//					}
//					else {
//						w.tl(6 + nombreTabulations, "type: array");
//						w.tl(6 + nombreTabulations, "items:");
//						w.tl(7 + nombreTabulations, "type: object");
//						w.tl(7 + nombreTabulations, "properties:");
//					}
//					for(Long k = searchEntitiesResults.getStart(); k < searchEntitiesResults.getNumFound(); k+=searchEntitiesLignes) {
//						for(Integer l = 0; l < searchEntitiesResults.size(); l++) {
//							SolrDocument entiteDocumentSolr = searchEntitiesResults.get(l);
//							String entiteVarAncien = entiteVar;
//							String entiteVarApiAncien = entiteVarApi;
//							Boolean entiteMotsClesTrouvesAncien = entiteMotsClesTrouves;
//							List<String> entiteMotsClesAncien = entiteMotsCles;
//							String entiteCanonicalNameGenericAncien = entiteNomCanoniqueGenerique;
//							String entiteCanonicalNameAncien = entiteNomCanonique;
//							String entiteListeJsonTypeAncien = entiteListeTypeJson;
//							String entiteJsonTypeAncien = entiteTypeJson;
//							String entiteJsonFormatAncien = entiteFormatJson;
//							List<String> entiteOptionsVarAncien = entiteOptionsVar;
//							List<String> entiteOptionsValueurAncien = entiteOptionsDescription;
//							String entiteDescriptionAncien = entiteDescription;
//
//							entiteVar = (String)entiteDocumentSolr.get("entiteVar_enUS_stored_string");
//							entiteVarApi = StringUtils.defaultIfBlank((String)entiteDocumentSolr.get("entiteVarApi_stored_string"), entiteVar);
//							entiteMotsClesTrouves = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMotsClesTrouves_stored_boolean"));
//							entiteMotsCles = (List<String>)entiteDocumentSolr.get("entiteMotsCles_stored_strings");
//							entiteNomCanoniqueGenerique = (String)entiteDocumentSolr.get("entiteNomCanoniqueGenerique_enUS_stored_string");
//							entiteNomCanonique = (String)entiteDocumentSolr.get("entiteNomCanonique_enUS_stored_string");
//							entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");
//							entiteTypeJson = (String)entiteDocumentSolr.get("entiteTypeJson_stored_string");
//							entiteFormatJson = (String)entiteDocumentSolr.get("entiteFormatJson_stored_string");
//							entiteOptionsVar = (List<String>)entiteDocumentSolr.get("entiteOptionsVar_stored_strings");
//							entiteOptionsDescription = (List<String>)entiteDocumentSolr.get("entiteOptionsDescription_stored_strings");
//							entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_stored_string");
//
//							if(entiteNomCanoniqueGenerique == null) {
//								ecrireEntiteSchema(nombreTabulations + 3, w, apiRequeteOuReponse);
//							}
//							else {
//								ecrireEntiteSchema(nombreTabulations + 5, w, apiRequeteOuReponse);
//							}
//
//							entiteVar = entiteVarAncien;
//							entiteVarApi = entiteVarApiAncien;
//							entiteMotsClesTrouves = entiteMotsClesTrouvesAncien;
//							entiteMotsCles = entiteMotsClesAncien;
//							entiteNomCanoniqueGenerique = entiteCanonicalNameGenericAncien;
//							entiteNomCanonique = entiteCanonicalNameAncien;
//							entiteListeTypeJson = entiteListeJsonTypeAncien;
//							entiteTypeJson = entiteJsonTypeAncien;
//							entiteFormatJson = entiteJsonFormatAncien;
//							entiteOptionsVar = entiteOptionsVarAncien;
//							entiteOptionsDescription = entiteOptionsValueurAncien;
//							entiteDescription = entiteDescriptionAncien;
//						}
//					}
//				}
//			}
//			else {

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
//			}
		}
	}

	public void  ecrireApi() throws Exception, Exception {

			if(!classeUris.contains(classeApiUriMethode)) {
				wChemins.tl(1, classeApiUriMethode, ":");
				classeUris.add(classeApiUriMethode);
			}
	
			wChemins.tl(2, StringUtils.lowerCase(classeApiMethodeMethode), ":");
			wChemins.tl(3, "operationId: ", classeApiOperationIdMethode);
			wChemins.tl(3, "x-vertx-event-bus: ", langueNom, classeNomSimple);
	
			if(classeRolesTrouves) {
				wChemins.tl(3, "security:");
				wChemins.tl(4, "- openIdConnect:");
				for(String classeRole : classeRoles)
					wChemins.tl(5, "- ", classeRole);
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
	
		if(!wRequeteEnTete.getVide() || "GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode) || "PUT".equals(classeApiMethodeMethode)) {
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
			else if(classeApiMethode.contains("Recherche")) {
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
				wChemins.tl(7, "$ref: '#/components/requestBodies/", classeApiOperationIdMethodeRequete, "'");
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
	}

	@Override()
	public int compareTo(ApiWriter o) {
		return ObjectUtils.compare(classeApiUriMethode, o.getClasseApiUriMethode());
	}

	@Override()
	public String toString() {
		return (String)classeDocumentSolr.get("classeNomSimple_frFR_stored_string") + " " + classeApiMethode;
	}
}
