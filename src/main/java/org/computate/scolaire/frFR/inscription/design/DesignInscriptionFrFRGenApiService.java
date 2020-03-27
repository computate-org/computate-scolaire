package org.computate.scolaire.frFR.inscription.design;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface DesignInscriptionFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-frFR-DesignInscription").register(DesignInscriptionFrFRGenApiService.class, new DesignInscriptionFrFRApiServiceImpl(siteContexte));
	}

	static DesignInscriptionFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new DesignInscriptionFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static DesignInscriptionFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new DesignInscriptionFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void postDesignInscription(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putDesignInscription(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchDesignInscription(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getDesignInscription(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void rechercheDesignInscription(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheDesignInscriptionId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheDesignInscription(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
