package org.computate.scolaire.frFR.bloc;

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
 * classeNomCanonique.enUS: org.computate.scolaire.enUS.bloc.SchoolBlockEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface BlocScolaireFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("frFRBlocScolaire").register(BlocScolaireFrFRGenApiService.class, new BlocScolaireFrFRApiServiceImpl(siteContexte));
	}

	static BlocScolaireFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new BlocScolaireFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static BlocScolaireFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new BlocScolaireFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void postBlocScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchBlocScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void deleteBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void rechercheBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheBlocScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
