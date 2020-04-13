package org.computate.scolaire.frFR.paiement;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface PaiementScolaireFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-frFR-PaiementScolaire").register(PaiementScolaireFrFRGenApiService.class, new PaiementScolaireFrFRApiServiceImpl(siteContexte));
	}

	static PaiementScolaireFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new PaiementScolaireFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static PaiementScolaireFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new PaiementScolaireFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void postPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putimportPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putfusionPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void putcopiePaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getPaiementScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void recherchePaiementScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerecherchePaiementScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerecherchePaiementScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
