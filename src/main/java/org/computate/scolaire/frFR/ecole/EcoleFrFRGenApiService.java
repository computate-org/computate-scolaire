package org.computate.scolaire.frFR.ecole;

import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.ext.web.api.generator.WebApiServiceGen;
import io.vertx.serviceproxy.ServiceBinder;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.school.SchoolEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface EcoleFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-frFR-Ecole").register(EcoleFrFRGenApiService.class, new EcoleFrFRApiServiceImpl(siteContexte));
	}

	static EcoleFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new EcoleFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static EcoleFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new EcoleFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void postEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void rechercheEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putimportEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putfusionEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putcopieEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheEcoleId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
