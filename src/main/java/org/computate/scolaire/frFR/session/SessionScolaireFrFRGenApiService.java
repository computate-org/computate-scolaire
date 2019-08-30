package org.computate.scolaire.frFR.session;

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
 * classeNomCanonique.enUS: org.computate.scolaire.enUS.session.SchoolSessionEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SessionScolaireFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("frFRSessionScolaire").register(SessionScolaireFrFRGenApiService.class, new SessionScolaireFrFRApiServiceImpl(siteContexte));
	}

	static SessionScolaireFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new SessionScolaireFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SessionScolaireFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new SessionScolaireFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void postSessionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchSessionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getSessionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void deleteSessionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void rechercheSessionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheSessionScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheSessionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
