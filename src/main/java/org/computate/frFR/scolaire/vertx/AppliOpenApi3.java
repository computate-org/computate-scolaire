package org.computate.frFR.scolaire.vertx;

import org.computate.frFR.scolaire.contexte.SiteContexte;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.requete.RequeteSite;
/**
 * NomCanonique.enUS: org.computate.enUS.school.vertx.AppOpenApi3
 */
public class AppliOpenApi3 extends AppliOpenApi3Gen<AppliSwagger2> {  

	@Override protected void _apiVersion(Couverture<String> c) {
		c.o("3.0.0");
	}

	/**
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: ecrire
	 * r.enUS: write
	 */
	public static void main(String[] args) {
		AppliOpenApi3 api = new AppliOpenApi3();
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.initLoinRequeteSite();
		SiteContexte siteContexte = new SiteContexte();
		siteContexte.initLoinSiteContexte(requeteSite);
		api.initLoinAppliOpenApi3(requeteSite);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		api.ecrireOpenApi();
	}
//
//	/**
//	 * Var.enUS: requeteSite_
//	 */
//	protected void _requeteSite_(Couverture<RequeteSite> c) throws Exception {
//	}
//
//	/**
//	 * Var.enUS: siteContext
//	 */
//	protected void _siteContexte(SiteContexte o) throws Exception {
//	}
//
//	/**
//	 * Var.enUS: siteConfig
//	 * r: siteContexte.getConfigSite
//	 * r.enUS: siteContext.getSiteConfig
//	 */
//	protected void _configSite(Couverture<ConfigSite> c) throws Exception {
//		c.o(siteContexte.getConfigSite());
//	}
//
//	/**
//	 * r: initLoin
//	 * r.enUS: initDeep
//	 * r: generer
//	 * r.enUS: generate
//	 */
//	public static void main(String[] args) throws Exception {
//		AppOpenApi3 api = new AppOpenApi3();
//		api.initLoinAppOpenApi3();
//		api.genererOpenApi();
//	}
//
//	/**
//	 * Var.enUS: generateOpenApi
//	 * r: configSite
//	 * r.enUS: siteConfig
//	 * r: ConfigSite
//	 * r.enUS: SiteConfig
//	 * r: appliChemin
//	 * r.enUS: appChemin
//	 * r: siteContexte
//	 * r.enUS: siteContext
//	 * r: openapiYamlChemin
//	 * r.enUS: openapiYamlChemin
//	 * r: genererChemins
//	 * r.enUS: generateChemins
//	 * r: genererCorpsRequetes
//	 * r.enUS: generateRequeteBodies
//	 * r: genererSchemas
//	 * r.enUS: generateSchemas
//	 * r: ToutEcrivain
//	 * r.enUS: AllWriter
//	 * r: .creer(
//	 * r.enUS: .create(
//	 */
//	public void genererOpenApi() throws Exception {
//		ConfigSite configSite = siteContexte.getConfigSite();
//		String appliChemin = configSite.getAppliChemin();
//		String openapiYamlChemin = appliChemin + "/src/main/resources/openapi3.yaml";
//		File openapiYamlFichier = new File(openapiYamlChemin);
//		ToutEcrivain w = ToutEcrivain.creer(requeteSite_, openapiYamlFichier);
//
//		genererChemins(w);
//		genererCorpsRequetes(w);
//		genererSchemas(w);
//
//		w.flushClose();
//	}
//
//	/**
//	 * Var.enUS: generateChemins
//	 * r: configSite
//	 * r.enUS: siteConfig
//	 * r: rechercheClasses
//	 * r.enUS: searchClasses
//	 * r: getSiteNomAffichage
//	 * r.enUS: getSiteDisplayName
//	 * r: getApiContractMail
//	 * r.enUS: getApiContractEmail
//	 * r: getApiLicenceNom
//	 * r.enUS: getApiLicenceName
//	 * r: getUrlDomaineBase
//	 * r.enUS: getUrlDomainBase
//	 */
//	public void genererChemins(ToutEcrivain w) throws Exception {
//
//		w.s("openapi: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getOpenApiVersion()));
//		w.l("\"");
//
//		w.l("info:");
//
//		w.s("  description: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getApiDescription()));
//		w.l("\"");
//
//		w.s("  version: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getApiVersion()));
//		w.l("\"");
//
//		w.s("  title: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getSiteNomAffichage()));
//		w.l("\"");
//
//		w.s("  termsOfService: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getApiTermsService()));
//		w.l("\"");
//
//		w.l("  contact:");
//
//		w.s("    email: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getApiContactMail()));
//		w.l("\"");
//
//		w.l("  license:");
//
//		w.s("    name: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getApiLicenceNom()));
//		w.l("\"");
//
//		w.s("    url: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getApiLicenceUrl()));
//		w.l("\"");
//
//		w.l("servers:");
//
//		w.s("  - url: \"");
//		w.s(StringEscapeUtils.escapeJava(configSite.getUrlDomaineBase()));
//		w.l("\"");
//		w.s("    description: \"");
//		w.l("\"");
//
//		w.l("paths:");
//
//		SolrQuery rechercheClasses = new SolrQuery();
//		rechercheClasses.setQuery("*:*");
//		rechercheClasses.setRows(1000000);
//		rechercheClasses.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));
//		rechercheClasses.addFilterQuery("classeApi_indexed_boolean:true");
//		rechercheClasses.addFilterQuery("partEstClasse_indexed_boolean:true");
//		rechercheClasses.addSort("partNumero_indexed_int", ORDER.asc);
//		QueryResponse rechercheClassesReponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
//		SolrDocumentList rechercheClassesResultats = rechercheClassesReponse.getResults();
//		Integer rechercheClassesLignes = rechercheClasses.getRows();
//		for(Long i = rechercheClassesResultats.getStart(); i < rechercheClassesResultats.getNumFound(); i+=rechercheClassesLignes) {
//			for(Integer j = 0; j < rechercheClassesResultats.size(); j++) {
//				SolrDocument classeSolrDocument = rechercheClassesResultats.get(j);
//
//				String classeApiUri = (String)classeSolrDocument.get("classeApiUri_frFR_stored_string");
//				String classeApiUriRecherche = (String)classeSolrDocument.get("classeApiUri_frFR_stored_string");
//				String classeApiUriGET = (String)classeSolrDocument.get("classeApiUriGET_frFR_stored_string");
//				String classeApiUriPOST = (String)classeSolrDocument.get("classeApiUriPOST_frFR_stored_string");
//				String classeApiUriPATCH = (String)classeSolrDocument.get("classeApiUriPATCH_frFR_stored_string");
//				String classeApiUriPUT = (String)classeSolrDocument.get("classeApiUriPUT_frFR_stored_string");
//				String classeApiUriDELETE = (String)classeSolrDocument.get("classeApiUriDELETE_frFR_stored_string");
//
//				String classeNomSimple = (String)classeSolrDocument.get("classeNomSimple_frFR_stored_string");
//				String classeCheminAbsolu = (String)classeSolrDocument.get("classeCheminAbsolu_stored_string");
//
//				String classeApiMethodeRecherche = (String)classeSolrDocument.get("classeApiMethode_frFR_stored_string");
//				String classeApiMethodeGET = (String)classeSolrDocument.get("classeApiMethodeGET_frFR_stored_string");
//				String classeApiMethodePOST = (String)classeSolrDocument.get("classeApiMethodePOST_frFR_stored_string");
//				String classeApiMethodePATCH = (String)classeSolrDocument.get("classeApiMethodePATCH_frFR_stored_string");
//				String classeApiMethodePUT = (String)classeSolrDocument.get("classeApiMethodePUT_frFR_stored_string");
//				String classeApiMethodeDELETE = (String)classeSolrDocument.get("classeApiMethodeDELETE_frFR_stored_string");
//
//				String operationIdRecherche = "recherche" + classeNomSimple;
//				String operationIdRechercheRequete = "rechercheRequete" + classeNomSimple;
//				String operationIdRechercheReponse = "rechercheReponse" + classeNomSimple;
//
//				String operationIdGET = "getDetails" + classeNomSimple;
//				String operationIdGETRequete = "getDetailsRequete" + classeNomSimple;
//				String operationIdGETReponse = "getDetailsReponse" + classeNomSimple;
//
//				String operationIdPOSTRequete = "submitRequete" + classeNomSimple;
//				String operationIdPOSTReponse = "submitReponse" + classeNomSimple;
//
//				String operationIdPutRequete = "putRequete" + classeNomSimple;
//				String operationIdPATCHRequete = "patchRequete" + classeNomSimple;
//
//				Boolean classeMotClesTrouves = BooleanUtils.isTrue((Boolean)classeSolrDocument.get("classeMotClesTrouves_stored_boolean"));
//				List<String> classeMotCles = (List<String>)classeSolrDocument.get("classeMotCles_stored_strings");
//
//				ToutEcrivain wRechercheRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wGETRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPOSTRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPutRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPATCHRequete = ToutEcrivain.creer(requeteSite_);
//
//				ToutEcrivain wRechercheReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wGETReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPOSTReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPutReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPATCHReponse = ToutEcrivain.creer(requeteSite_);
//
//				SolrQuery rechercheEntites = new SolrQuery();
//				rechercheEntites.setQuery("*:*");
//				rechercheEntites.setRows(1000000);
//				rechercheEntites.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));
//				rechercheEntites.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
//				rechercheEntites.addFilterQuery("partEstEntite_indexed_boolean:true");
//				rechercheEntites.addSort("partNumero_indexed_int", ORDER.asc);
//				QueryResponse rechercheEntitesReponse = siteContexte.getClientSolrComputate().query(rechercheEntites);
//				SolrDocumentList rechercheEntitesResultats = rechercheEntitesReponse.getResults();
//				Integer rechercheEntitesLignes = rechercheEntites.getRows();
//
//				if(classeApiUri != null) {
//					for(Long k = rechercheEntitesResultats.getStart(); k < rechercheEntitesResultats.getNumFound(); k+=rechercheEntitesLignes) {
//						for(Integer l = 0; l < rechercheEntitesResultats.size(); l++) {
//							SolrDocument documentSolrEntite = rechercheEntitesResultats.get(l);
//							String entiteVar = (String)documentSolrEntite.get("entiteVar_enUS_stored_string");
//							String entiteDescription = (String)documentSolrEntite.get("entiteDescription_enUS_stored_string");
//							String entiteDisplayName = (String)documentSolrEntite.get("entiteDisplayName_enUS_stored_string");
//							Integer entiteMinLength = (Integer)documentSolrEntite.get("entiteMinLength_stored_int");
//							Integer entiteMaxLength = (Integer)documentSolrEntite.get("entiteMaxLength_stored_int");
//							Double entiteMin = (Double)documentSolrEntite.get("entiteMin_stored_double");
//							Double entiteMax = (Double)documentSolrEntite.get("entiteMax_stored_double");
//							Boolean entiteOptional = (Boolean)documentSolrEntite.get("entiteOptional_stored_boolean");
//							String entiteVarCapitalized = (String)documentSolrEntite.get("entiteVarCapitalized_enUS_stored_string");
//							String entiteJsonType = (String)documentSolrEntite.get("entiteJsonType_stored_string");
//							String entiteListJsonType = (String)documentSolrEntite.get("entiteListJsonType_stored_string");
//							String entiteJsonFormat = (String)documentSolrEntite.get("entiteJsonFormat_stored_string");
//							Boolean entitePrimaryKey = BooleanUtils.isTrue((Boolean)documentSolrEntite.get("entitePrimaryKey_stored_boolean"));
//							Boolean entiteStored = BooleanUtils.isTrue((Boolean)documentSolrEntite.get("entiteStored_stored_boolean"));
//							Boolean entiteIndexed = BooleanUtils.isTrue((Boolean)documentSolrEntite.get("entiteIndexed_stored_boolean"));
//							Boolean entiteMotClesTrouves = BooleanUtils.isTrue((Boolean)documentSolrEntite.get("entiteMotClesTrouves_stored_boolean"));
//							List<String> entiteMotCles = (List<String>)documentSolrEntite.get("entiteMotCles_stored_strings");
//							List<String> entiteOptionsVar = (List<String>)documentSolrEntite.get("entiteOptionsVar_stored_strings");
//							List<String> entiteOptionsValue = (List<String>)documentSolrEntite.get("entiteOptionsValue_stored_strings");
//	
//							if(entiteJsonType != null) {
//		
//								//////////////////////
//								// RechercheRequete //
//								//////////////////////
//		
//								if(classMotClesTrouves && entiteMotClesTrouves && entiteMotCles.contains("search.request")) {
//									wRechercheRequete.write("- " + entiteVar);
//									if(StringUtils.isNotBlank(entiteDisplayName))
//										wRechercheRequete.write(" (" + entiteDisplayName + ")");
//									wRechercheRequete.write(": ");
//									if(StringUtils.isNotBlank(entiteDescription))
//										wRechercheRequete.write(entiteDescription.replace("\"", "\\\""));
//									wRechercheRequete.write("\\n");
//									if(BooleanUtils.isTrue(entiteOptional))
//										wRechercheRequete.write("  - optional: " + entiteOptional + "\\n");
//									if(entiteMinLength != null)
//										wRechercheRequete.write("  - min length: " + entiteMinLength + "\\n");
//									if(entiteMaxLength != null)
//										wRechercheRequete.write("  - max length: " + entiteMaxLength + "\\n");
//									if(entiteMin != null)
//										wRechercheRequete.write("  - min: " + entiteMin + "\\n");
//									if(entiteMax != null)
//										wRechercheRequete.write("  - max: " + entiteMax + "\\n");
//									if(entiteOptionsVar != null && entiteOptionsValue != null && entiteOptionsVar.size() > 0 && entiteOptionsValue.size() == entiteOptionsVar.size()) {
//										wRechercheRequete.write("  - enum:\\n");
//										for(int m = 0; m < entiteOptionsVar.size(); m++) {
//											wRechercheRequete.write("    - " + entiteOptionsVar.get(m) + ": " + entiteOptionsValue.get(m) + "\\n");
//										}
//									}
//								}
//		
//								if(classMotClesTrouves && entiteMotClesTrouves && entiteMotCles.contains("search.response")) {
//									wRechercheReponse.write("- " + entiteVar);
//									if(StringUtils.isNotBlank(entiteDisplayName))
//										wRechercheReponse.write(" (" + entiteDisplayName + ")");
//									wRechercheReponse.write(": ");
//									if(StringUtils.isNotBlank(entiteDescription))
//										wRechercheReponse.write(entiteDescription.replace("\"", "\\\""));
//									wRechercheReponse.write("\\n");
//									if(BooleanUtils.isTrue(entiteOptional))
//										wRechercheReponse.write("  - optional: " + entiteOptional + "\\n");
//									if(entiteMinLength != null)
//										wRechercheReponse.write("  - min length: " + entiteMinLength + "\\n");
//									if(entiteMaxLength != null)
//										wRechercheReponse.write("  - max length: " + entiteMaxLength + "\\n");
//									if(entiteMin != null)
//										wRechercheReponse.write("  - min: " + entiteMin + "\\n");
//									if(entiteMax != null)
//										wRechercheReponse.write("  - max: " + entiteMax + "\\n");
//									if(entiteOptionsVar != null && entiteOptionsValue != null && entiteOptionsVar.size() > 0 && entiteOptionsValue.size() == entiteOptionsVar.size()) {
//										wRechercheReponse.write("  - enum:\\n");
//										for(int m = 0; m < entiteOptionsVar.size(); m++) {
//											wRechercheReponse.write("    - " + entiteOptionsVar.get(m) + ": " + entiteOptionsValue.get(m) + "\\n");
//										}
//									}
//								}
//		
//								/////////////////
//								// GET Requete //
//								/////////////////
//		
//								if(classMotClesTrouves && entiteMotClesTrouves && entiteMotCles.contains("details.request")) {
//									wGetPathRequete.write("- " + entiteVar);
//									if(StringUtils.isNotBlank(entiteDisplayName))
//										wGetPathRequete.write(" (" + entiteDisplayName + ")");
//									wGetPathRequete.write(": ");
//									if(StringUtils.isNotBlank(entiteDescription))
//										wGetPathRequete.write(entiteDescription.replace("\"", "\\\""));
//									wGetPathRequete.write("\\n");
//									if(BooleanUtils.isTrue(entiteOptional))
//										wGetPathRequete.write("  - optional: " + entiteOptional + "\\n");
//									if(entiteMinLength != null)
//										wGetPathRequete.write("  - min length: " + entiteMinLength + "\\n");
//									if(entiteMaxLength != null)
//										wGetPathRequete.write("  - max length: " + entiteMaxLength + "\\n");
//									if(entiteMin != null)
//										wGetPathRequete.write("  - min: " + entiteMin + "\\n");
//									if(entiteMax != null)
//										wGetPathRequete.write("  - max: " + entiteMax + "\\n");
//									if(entiteOptionsVar != null && entiteOptionsValue != null && entiteOptionsVar.size() > 0 && entiteOptionsValue.size() == entiteOptionsVar.size()) {
//										wGetPathRequete.write("  - enum:\\n");
//										for(int m = 0; m < entiteOptionsVar.size(); m++) {
//											wGetPathRequete.write("    - " + entiteOptionsVar.get(m) + ": " + entiteOptionsValue.get(m) + "\\n");
//										}
//									}
//								}
//		
//								if(classMotClesTrouves && entiteMotClesTrouves && entiteMotCles.contains("details.response")) {
//									wGetPathReponse.write("- " + entiteVar);
//									if(StringUtils.isNotBlank(entiteDisplayName))
//										wGetPathReponse.write(" (" + entiteDisplayName + ")");
//									wGetPathReponse.write(": ");
//									if(StringUtils.isNotBlank(entiteDescription))
//										wGetPathReponse.write(entiteDescription.replace("\"", "\\\""));
//									wGetPathReponse.write("\\n");
//									if(BooleanUtils.isTrue(entiteOptional))
//										wGetPathReponse.write("  - optional: " + entiteOptional + "\\n");
//									if(entiteMinLength != null)
//										wGetPathReponse.write("  - min length: " + entiteMinLength + "\\n");
//									if(entiteMaxLength != null)
//										wGetPathReponse.write("  - max length: " + entiteMaxLength + "\\n");
//									if(entiteMin != null)
//										wGetPathReponse.write("  - min: " + entiteMin + "\\n");
//									if(entiteMax != null)
//										wGetPathReponse.write("  - max: " + entiteMax + "\\n");
//									if(entiteOptionsVar != null && entiteOptionsValue != null && entiteOptionsVar.size() > 0 && entiteOptionsValue.size() == entiteOptionsVar.size()) {
//										wGetPathReponse.write("  - enum:\\n");
//										for(int m = 0; m < entiteOptionsVar.size(); m++) {
//											wGetPathReponse.write("    - " + entiteOptionsVar.get(m) + ": " + entiteOptionsValue.get(m) + "\\n");
//										}
//									}
//								}
//		
//								//////////////////
//								// POST Requete //
//								//////////////////
//
//								if(classMotClesTrouves && entiteMotClesTrouves && entiteMotCles.contains("submit.request")) {
//									wPostPathRequete.write("- " + entiteVar);
//									if(StringUtils.isNotBlank(entiteDisplayName))
//										wPostPathRequete.write(" (" + entiteDisplayName + ")");
//									wPostPathRequete.write(": ");
//									if(StringUtils.isNotBlank(entiteDescription))
//										wPostPathRequete.write(entiteDescription.replace("\"", "\\\""));
//									wPostPathRequete.write("\\n");
//									if(BooleanUtils.isTrue(entiteOptional))
//										wPostPathRequete.write("  - optional: " + entiteOptional + "\\n");
//									if(entiteMinLength != null)
//										wPostPathRequete.write("  - min length: " + entiteMinLength + "\\n");
//									if(entiteMaxLength != null)
//										wPostPathRequete.write("  - max length: " + entiteMaxLength + "\\n");
//									if(entiteMin != null)
//										wPostPathRequete.write("  - min: " + entiteMin + "\\n");
//									if(entiteMax != null)
//										wPostPathRequete.write("  - max: " + entiteMax + "\\n");
//									if(entiteOptionsVar != null && entiteOptionsValue != null && entiteOptionsVar.size() > 0 && entiteOptionsValue.size() == entiteOptionsVar.size()) {
//										wPostPathRequete.write("  - enum:\\n");
//										for(int m = 0; m < entiteOptionsVar.size(); m++) {
//											wPostPathRequete.write("    - " + entiteOptionsVar.get(m) + ": " + entiteOptionsValue.get(m) + "\\n");
//										}
//									}
//								}
//
//								if(classMotClesTrouves && entiteMotClesTrouves && entiteMotCles.contains("submit.response")) {
//									wPostPathReponse.write("- " + entiteVar);
//									if(StringUtils.isNotBlank(entiteDisplayName))
//										wPostPathReponse.write(" (" + entiteDisplayName + ")");
//									wPostPathReponse.write(": ");
//									if(StringUtils.isNotBlank(entiteDescription))
//										wPostPathReponse.write(entiteDescription.replace("\"", "\\\""));
//									wPostPathReponse.write("\\n");
//									if(BooleanUtils.isTrue(entiteOptional))
//										wPostPathReponse.write("  - optional: " + entiteOptional + "\\n");
//									if(entiteMinLength != null)
//										wPostPathReponse.write("  - min length: " + entiteMinLength + "\\n");
//									if(entiteMaxLength != null)
//										wPostPathReponse.write("  - max length: " + entiteMaxLength + "\\n");
//									if(entiteMin != null)
//										wPostPathReponse.write("  - min: " + entiteMin + "\\n");
//									if(entiteMax != null)
//										wPostPathReponse.write("  - max: " + entiteMax + "\\n");
//									if(entiteOptionsVar != null && entiteOptionsValue != null && entiteOptionsVar.size() > 0 && entiteOptionsValue.size() == entiteOptionsVar.size()) {
//										wPostPathReponse.write("  - enum:\\n");
//										for(int m = 0; m < entiteOptionsVar.size(); m++) {
//											wPostPathReponse.write("    - " + entiteOptionsVar.get(m) + ": " + entiteOptionsValue.get(m) + "\\n");
//										}
//									}
//								}
//							}
//						}
//					}
//
//					///////////////
//					// Recherche //
//					///////////////
//
//					if(classeMotClesTrouves && (classeMotCles.contains("recherche.requete") || classeMotCles.contains("recherche.reponse"))) {
//
//						//////////////////
//						// SEARCH BEGIN //
//						//////////////////
//						wRechercheRequete.l("  ", classeApiUriRecherche, ":");
//						wRechercheRequete.l("    ", StringUtils.lowerCase(classeApiMethodeRecherche), ":");
//						wRechercheRequete.l("      operationId: \"", operationIdRecherche, "\"");
//						wRechercheRequete.l("      summary: \"", "\"");
//						wRechercheRequete.l("      tags:");
//						wRechercheRequete.l("        - \"", classeNomSimple, " modele", "\"");
//						wRechercheRequete.l("      description: \"", "\"");
//						wRechercheRequete.l("      produces:");
//						wRechercheRequete.l("      - \"application/json\"");
//						wRechercheRequete.l("      parameters:");
//						wRechercheRequete.l("      - in: \"query\"");
//						wRechercheRequete.l("        name: \"q\"");
//						wRechercheRequete.l("        description: \"\"");
//						wRechercheRequete.l("        required: false");
//						wRechercheRequete.l("        type: string");
//						wRechercheRequete.l("        default: \"**:**\"");
//						wRechercheRequete.l("      - in: \"query\"");
//						wRechercheRequete.l("        name: \"fq\"");
//						wRechercheRequete.l("        description: \"\"");
//						wRechercheRequete.l("        required: false");
//						wRechercheRequete.l("        type: array");
//						wRechercheRequete.l("        items:");
//						wRechercheRequete.l("          type: string");
//						wRechercheRequete.l("      - in: \"query\"");
//						wRechercheRequete.l("        name: \"fl\"");
//						wRechercheRequete.l("        description: \"\"");
//						wRechercheRequete.l("        required: false");
//						wRechercheRequete.l("        type: string");
//						wRechercheRequete.l("      - in: \"query\"");
//						wRechercheRequete.l("        name: \"sort\"");
//						wRechercheRequete.l("        description: \"\"");
//						wRechercheRequete.l("        required: false");
//						wRechercheRequete.l("        type: array");
//						wRechercheRequete.l("        items:");
//						wRechercheRequete.l("          type: string");
//						wRechercheRequete.l("      - in: \"query\"");
//						wRechercheRequete.l("        name: \"start\"");
//						wRechercheRequete.l("        description: \"\"");
//						wRechercheRequete.l("        required: false");
//						wRechercheRequete.l("        type: integer");
//						wRechercheRequete.l("        default: 0");
//						wRechercheRequete.l("        minimum: 0");
//						wRechercheRequete.l("      - in: \"query\"");
//						wRechercheRequete.l("        name: \"rows\"");
//						wRechercheRequete.l("        description: \"\"");
//						wRechercheRequete.l("        required: false");
//						wRechercheRequete.l("        type: integer");
//						wRechercheRequete.l("        default: 10");
//						wRechercheRequete.l("        minimum: 1");
////	swagger2
////						if(!classeMotClesTrouves || classeMotClesTrouves && classeMotCles.contains("recherche.requete")) {
////							wRechercheCheminRequete.l("      - in: body");
////							wRechercheCheminRequete.l("        name: \"body\"");
////							wRechercheCheminRequete.s("        description: \"");
////						}
//	
//						wRechercheReponse.l("      responses:");
//						wRechercheReponse.l("        '200':");
//						wRechercheReponse.s("          description: \"");
//					}
//				w.l("      responses:");
//				w.l("        '200':");
//				w.s("          description: \"");
//				w.l("\"");
//				w.l("          content:");
//				w.l("            application/json:");
//				w.l("              schema:");
//
//				w.s("                $ref: '#/components/schemas/");
//				w.s(operationIdGET);
//				w.l("'");
//
//				//////////
//				// POST //
//				//////////
//				w.l("    post:");
//				w.s("      operationId: \"");
//				w.s(operationIdPOST);
//				w.l("\"");
//
//				w.s("      summary: \"");
//				w.l("\"");
//
//				w.s("      description: \"");
//				w.l("\"");
//
//				w.l("      requestBody:");
//				w.l("        description: \"\"");
//				w.l("        required: true");
//				w.l("        content:");
//				w.l("          application/json:");
//				w.l("            schema:");
//
//				w.s("              $ref: '#/components/schemas/");
//				w.s(operationIdPOST);
//				w.l("'");
//
//				w.l("      responses:");
//				w.l("        '201':");
//				w.s("          description: \"");
//				w.l("\"");
//				w.l("          content:");
//				w.l("            application/json:");
//				w.l("              schema:");
//
//				w.s("                $ref: '#/components/schemas/");
//				w.s(operationIdGET);
//				w.l("'");
//
//				///////////
//				// PATCH //
//				///////////
//				w.l("    patch:");
//				w.s("      operationId: \"");
//				w.s(operationIdPATCH);
//				w.l("\"");
//
//				w.s("      summary: \"");
//				w.l("\"");
//
//				w.s("      description: \"");
//				w.l("\"");
//
//				w.l("      parameters:");
//				w.l("      - in: \"query\"");
//				w.l("        name: \"q\"");
//				w.l("        description: \"\"");
//				w.l("        required: false");
//				w.l("        schema:");
//				w.l("          type: string");
//				w.l("        examples:");
//				w.l("          default:");
//				w.l("            value: \"*:*\"");
//				w.l("            summary: \"This is the default solr query. \"");
//				w.l("      - in: \"query\"");
//				w.l("        name: \"fq\"");
//				w.l("        description: \"\"");
//				w.l("        required: false");
//				w.l("        schema:");
//				w.l("          type: string");
//				w.l("      - in: \"query\"");
//				w.l("        name: \"fl\"");
//				w.l("        description: \"\"");
//				w.l("        required: false");
//				w.l("        schema:");
//				w.l("          type: string");
//				w.l("      - in: \"query\"");
//				w.l("        name: \"start\"");
//				w.l("        description: \"\"");
//				w.l("        required: false");
//				w.l("        schema:");
//				w.l("          type: integer");
//				w.l("          minimum: 0");
//				w.l("      - in: \"query\"");
//				w.l("        name: \"rows\"");
//				w.l("        description: \"\"");
//				w.l("        required: false");
//				w.l("        schema:");
//				w.l("          type: integer");
//				w.l("          minimum: 1");
//
//				w.l("      requestBody:");
//				w.l("        description: \"\"");
//				w.l("        required: true");
//				w.l("        content:");
//				w.l("          application/json:");
//				w.l("            schema:");
//
//				w.s("              $ref: '#/components/schemas/");
//				w.s(operationIdPATCH);
//				w.l("'");
//
//				w.l("      responses:");
//				w.l("        '200':");
//				w.s("          description: \"");
//				w.l("\"");
//				w.l("          content:");
//				w.l("            application/json:");
//				w.l("              schema:");
//
//				w.s("                $ref: '#/components/schemas/");
//				w.s(operationIdGET);
//				w.l("'");
//			}
//			rechercheClasses.setStart(i.intValue() + rechercheClassesLignes);
//			rechercheClassesReponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
//			rechercheClassesResultats = rechercheClassesReponse.getResults();
//			rechercheClassesLignes = rechercheClasses.getRows();
//		}
//	}
//
//	public void genererCorpsRequetes(ToutEcrivain w) throws Exception {
//
//		w.l("components:");
//
//		w.l("  requestBodies:");
//
//		SolrQuery rechercheClasses = new SolrQuery();
//		rechercheClasses.setQuery("*:*");
//		rechercheClasses.setRows(1000000);
//		rechercheClasses.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));
//		rechercheClasses.addFilterQuery("classeApi_indexed_boolean:true");
//		rechercheClasses.addFilterQuery("partEstClasse_indexed_boolean:true");
//		rechercheClasses.addSort("partNumero_indexed_int", ORDER.asc);
//		QueryResponse rechercheClassesReponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
//		SolrDocumentList rechercheClassesResultats = rechercheClassesReponse.getResults();
//		Integer rechercheClassesLignes = rechercheClasses.getRows();
//		for(Long i = rechercheClassesResultats.getStart(); i < rechercheClassesResultats.getNumFound(); i+=rechercheClassesLignes) {
//			for(Integer j = 0; j < rechercheClassesResultats.size(); j++) {
//				Long resultatIndice = i + j;
//				SolrDocument classeSolrDocument = rechercheClassesResultats.get(j);
//				String classeApiUri = (String)classeSolrDocument.get("classeApiUri_frFR_stored_string");
//				String classeNomSimple = (String)classeSolrDocument.get("classeNomSimple_frFR_stored_string");
//				String classeCheminAbsolu = (String)classeSolrDocument.get("classAbsoluteChemin_stored_string");
//
//				String operationIdRecherche = "getPending" + classeNomSimple;
//				String operationIdRechercheRequete = "getPendingRequete" + classeNomSimple;
//				String operationIdRechercheReponse = "getPendingReponse" + classeNomSimple;
//
//				String operationIdGET = "getDetails" + classeNomSimple;
//				String operationIdGETRequete = "getDetailsRequete" + classeNomSimple;
//				String operationIdGETReponse = "getDetailsReponse" + classeNomSimple;
//
//				String operationIdPOSTRequete = "submitRequete" + classeNomSimple;
//				String operationIdPOSTReponse = "submitReponse" + classeNomSimple;
//
//				String operationIdPutRequete = "putRequete" + classeNomSimple;
//				String operationIdPATCHRequete = "patchRequete" + classeNomSimple;
//
//				Boolean classMotClesTrouves = BooleanUtils.isTrue((Boolean)classeSolrDocument.get("classMotClesTrouves_stored_boolean"));
//				List<String> classMotCles = (List<String>)classeSolrDocument.get("classMotCles_stored_strings");
//
//				ToutEcrivain wRechercheCheminRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wGETCheminRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPOSTCheminRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPutCheminRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPATCHCheminRequete = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wRechercheCheminReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wGETCheminReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPOSTCheminReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPutCheminReponse = ToutEcrivain.creer(requeteSite_);
//				ToutEcrivain wPATCHCheminReponse = ToutEcrivain.creer(requeteSite_);
//
//				/////////
//				// GET //
//				/////////
//				w.s("    ");
//				w.s(operationIdGET);
//				w.l(":");
//
//				w.l("      required: true");
//
//				w.s("      description: \"");
//				w.l("\"");
//
//				w.l("      content:");
//				w.l("        application/json:");
//				w.l("          schema:");
//
//				w.s("            $ref: '#/components/schemas/");
//				w.s(operationIdGET);
//				w.l("'");
//
//				//////////
//				// POST //
//				//////////
//				w.s("    ");
//				w.s(operationIdPOST);
//				w.l(":");
//
//				w.l("      required: true");
//
//				w.s("      description: \"");
//				w.l("\"");
//
//				w.l("      content:");
//				w.l("        application/json:");
//				w.l("          schema:");
//
//				w.s("            $ref: '#/components/schemas/");
//				w.s(operationIdPOST);
//				w.l("'");
//
//				///////////
//				// PATCH //
//				///////////
//				w.s("    ");
//				w.s(operationIdPATCH);
//				w.l(":");
//
//				w.l("      required: true");
//
//				w.s("      description: \"");
//				w.l("\"");
//
//				w.l("      content:");
//				w.l("        application/json:");
//				w.l("          schema:");
//
//				w.s("            $ref: '#/components/schemas/");
//				w.s(operationIdPATCH);
//				w.l("'");
//			}
//			rechercheClasses.setStart(i.intValue() + rechercheClassesLignes);
//			rechercheClassesReponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
//			rechercheClassesResultats = rechercheClassesReponse.getResults();
//			rechercheClassesLignes = rechercheClasses.getRows();
//		}
//	}
//
//	public void genererSchemas(ToutEcrivain w) throws Exception {
//
//		w.l("  schemas:");
//
//		SolrQuery rechercheClasses = new SolrQuery();
//		rechercheClasses.setQuery("*:*");
//		rechercheClasses.setRows(1000000);
//		rechercheClasses.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));
//		rechercheClasses.addFilterQuery("classeApi_indexed_boolean:true");
//		rechercheClasses.addFilterQuery("partEstClasse_indexed_boolean:true");
//		rechercheClasses.addSort("partNumero_indexed_int", ORDER.asc);
//		QueryResponse rechercheClassesReponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
//		SolrDocumentList rechercheClassesResultats = rechercheClassesReponse.getResults();
//		Integer rechercheClassesLignes = rechercheClasses.getRows();
//		for(Long i = rechercheClassesResultats.getStart(); i < rechercheClassesResultats.getNumFound(); i+=rechercheClassesLignes) {
//			for(Integer j = 0; j < rechercheClassesResultats.size(); j++) {
//				Long resultatIndice = i + j;
//				SolrDocument documentSolr = rechercheClassesResultats.get(j);
//				String classeApiUri = (String)documentSolr.get("classeApiUri_frFR_stored_string");
//				String classeCheminAbsolu = (String)documentSolr.get("classeCheminAbsolu_stored_string");
//				String classeNomSimple = (String)documentSolr.get("classeNomSimple_frFR_stored_string");
//				String classeNomSimpleSuperGenerique = (String)documentSolr.get("classeNomSimpleSuperGenerique_frFR_stored_string");
//				Boolean classeEstBase = (Boolean)documentSolr.get("classeEstBase_stored_boolean");
//				String operationIdGET = "get" + classeNomSimple;
//				String operationIdPOST = "post" + classeNomSimple;
//				String operationIdPATCH = "patch" + classeNomSimple;
//
//				SolrQuery rechercheEntites = new SolrQuery();
//				rechercheEntites.setQuery("*:*");
//				rechercheEntites.setRows(1000000);
//				rechercheEntites.addFilterQuery("appliChemin_indexed_string:" + ClientUtils.escapeQueryChars(configSite.getAppliChemin()));
//				rechercheEntites.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
//				rechercheEntites.addFilterQuery("partEstEntite_indexed_boolean:true");
//				rechercheEntites.addSort("partNumero_indexed_int", ORDER.asc);
//				QueryResponse rechercheEntitesReponse = siteContexte.getClientSolrComputate().query(rechercheEntites);
//				SolrDocumentList rechercheEntitesResultats = rechercheEntitesReponse.getResults();
//				Integer rechercheEntitesLignes = rechercheEntites.getRows();
//
//				/////////
//				// GET //
//				/////////
//
//				StringWriter sGET = new StringWriter();
//				ToutEcrivain wGET = ToutEcrivain.creer(sGET);
//
//				StringWriter sPOST = new StringWriter();
//				ToutEcrivain wPOST = ToutEcrivain.creer(sPOST);
//
//				StringWriter sPATCH = new StringWriter();
//				ToutEcrivain wPATCH = ToutEcrivain.creer(sPATCH);
//
//				wGET.write("    ");
//				wGET.write(operationIdGET);
//				wGET.write(":\n");
//				wGET.write("      allOf:\n");
//				if(BooleanUtils.isFalse(classeEstBase)) {
//					wGET.write("      - $ref: \"#/components/schemas/get");
//					wGET.write(classeNomSimpleSuperGenerique);
//					wGET.write("\"\n");
//				}
//				wGET.write("      - type: object\n");
//				wGET.write("        properties:\n");
//				wGET.write("          numFound:\n");
//				wGET.write("            type: number\n");
//				wGET.write("            default: 1\n");
//				wGET.write("          start:\n");
//				wGET.write("            type: number\n");
//				wGET.write("            default: 0\n");
//				wGET.write("          rows:\n");
//				wGET.write("            type: number\n");
//				wGET.write("            default: 10\n");
//				wGET.write("          docs:\n");
//				wGET.write("            type: array\n");
//				wGET.write("            items:\n");
//				wGET.write("              type: object\n");
//				wGET.write("              properties:\n");
//
//				wPOST.write("    ");
//				wPOST.write(operationIdPOST);
//				wPOST.write(":\n");
//				wPOST.write("      allOf:\n");
//				if(BooleanUtils.isFalse(classeEstBase)) {
//					wPOST.write("      - $ref: \"#/components/schemas/post");
//					wPOST.write(classeNomSimpleSuperGenerique);
//					wPOST.write("\"\n");
//				}
//				wPOST.write("      - type: object\n");
//				wPOST.write("        properties:\n");
//
//				wPATCH.write("    ");
//				wPATCH.write(operationIdPATCH);
//				wPATCH.write(":\n");
//				wPATCH.write("      allOf:\n");
//				if(BooleanUtils.isFalse(classeEstBase)) {
//					wPATCH.write("      - $ref: \"#/components/schemas/patch");
//					wPATCH.write(classeNomSimpleSuperGenerique);
//					wPATCH.write("\"\n");
//				}
//				wPATCH.write("      - type: object\n");
//				wPATCH.write("        properties:\n");
//
//				for(Long k = rechercheEntitesResultats.getStart(); k < rechercheEntitesResultats.getNumFound(); k+=rechercheEntitesLignes) {
//					for(Integer l = 0; l < rechercheEntitesResultats.size(); l++) {
//						SolrDocument documentSolrEntite = rechercheEntitesResultats.get(l);
//						String entiteVar = (String)documentSolrEntite.get("entiteVar_frFR_stored_string");
//						String entiteVarCapitalise = (String)documentSolrEntite.get("entiteVarCapitalise_frFR_stored_string");
//						String entiteTypeJson = (String)documentSolrEntite.get("entiteTypeJson_stored_string");
//						String entiteListeTypeJson = (String)documentSolrEntite.get("entiteListeTypeJson_stored_string");
//						String entiteFormatJson = (String)documentSolrEntite.get("entiteFormatJson_stored_string");
//
//						if(entiteTypeJson != null) {
//	
//							/////////
//							// GET //
//							/////////
//	
//							wGET.write("                ");
//							wGET.write(entiteVar);
//							wGET.write(":\n");
//	
//							wGET.write("                  type: ");
//							wGET.write(entiteTypeJson);
//							wGET.write("\n");
//	
//							if(entiteFormatJson != null) {
//								wGET.write("                  format: ");
//								wGET.write(entiteFormatJson);
//								wGET.write("\n");
//							}
//	
//							if(entiteListeTypeJson != null) {
//		
//								wGET.write("                items:\n");
//		
//								wGET.write("                  type: ");
//								wGET.write(entiteListeTypeJson);
//								wGET.write("\n");
//							}
//	
//							if(!StringUtils.equals(entiteVar, "pk")) {
//								//////////
//								// POST //
//								//////////
//		
//								wPOST.write("          ");
//								wPOST.write(entiteVar);
//								wPOST.write(":\n");
//		
//								wPOST.write("            type: ");
//								wPOST.write(entiteTypeJson);
//								wPOST.write("\n");
//		
//								if(entiteFormatJson != null) {
//									wPOST.write("            format: ");
//									wPOST.write(entiteFormatJson);
//									wPOST.write("\n");
//								}
//		
//								if(entiteListeTypeJson != null) {
//			
//									wPOST.write("            items:\n");
//			
//									wPOST.write("              type: ");
//									wPOST.write(entiteListeTypeJson);
//									wPOST.write("\n");
//								}
//		
//								///////////
//								// PATCH //
//								///////////
//		
//								wPATCH.write("          ");
//								wPATCH.write("set");
//								wPATCH.write(entiteVarCapitalise);
//								wPATCH.write(":\n");
//		
//								wPATCH.write("            type: ");
//								wPATCH.write(entiteTypeJson);
//								wPATCH.write("\n");
//
//								wPATCH.write("            nullable: true\n");
//		
//								if(entiteFormatJson != null) {
//									wPATCH.write("            format: ");
//									wPATCH.write(entiteFormatJson);
//									wPATCH.write("\n");
//								}
//		
//								if(entiteListeTypeJson != null) {
//			
//									wPATCH.write("            items:\n");
//			
//									wPATCH.write("              type: ");
//									wPATCH.write(entiteListeTypeJson);
//									wPATCH.write("\n");
//								}
//							}
//						}
//					}
//				}
//
//				wGET.flush();
//				sGET.flush();
//				wGET.close();
//				sGET.close();
//
//				wPOST.flush();
//				sPOST.flush();
//				wPOST.close();
//				sPOST.close();
//
//				wPATCH.flush();
//				sPATCH.flush();
//				wPATCH.close();
//				sPATCH.close();
//
//				w.s(sGET.toString());
//				w.s(sPOST.toString());
//				w.s(sPATCH.toString());
//			}
//			rechercheClasses.setStart(i.intValue() + rechercheClassesLignes);
//			rechercheClassesReponse = siteContexte.getClientSolrComputate().query(rechercheClasses);
//			rechercheClassesResultats = rechercheClassesReponse.getResults();
//			rechercheClassesLignes = rechercheClasses.getRows();
//		}
//	}
}
