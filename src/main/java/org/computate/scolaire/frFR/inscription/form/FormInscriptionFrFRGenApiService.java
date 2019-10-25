package org.computate.scolaire.frFR.inscription.form;

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
 * classeNomCanonique.enUS: org.computate.scolaire.enUS.enrollment.form.EnrollmentFormEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface FormInscriptionFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("frFRFormInscription").register(FormInscriptionFrFRGenApiService.class, new FormInscriptionFrFRApiServiceImpl(siteContexte));
	}

	static FormInscriptionFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new FormInscriptionFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static FormInscriptionFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new FormInscriptionFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void postFormInscription(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchFormInscription(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getFormInscription(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void deleteFormInscription(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void rechercheFormInscription(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheFormInscriptionId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheFormInscription(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
