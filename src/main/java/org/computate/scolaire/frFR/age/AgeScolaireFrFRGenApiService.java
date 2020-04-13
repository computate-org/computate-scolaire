package org.computate.scolaire.frFR.age;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.age.SchoolAgeEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface AgeScolaireFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-frFR-AgeScolaire").register(AgeScolaireFrFRGenApiService.class, new AgeScolaireFrFRApiServiceImpl(siteContexte));
	}

	static AgeScolaireFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new AgeScolaireFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static AgeScolaireFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new AgeScolaireFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void postAgeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putimportAgeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putfusionAgeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putcopieAgeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchAgeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getAgeScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void rechercheAgeScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheAgeScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheAgeScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
