package org.computate.scolaire.frFR.mission;

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
 * classeNomCanonique.enUS: org.computate.scolaire.enUS.mission.MissionEnUSGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface MissionScolaireFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("frFRMissionScolaire").register(MissionScolaireFrFRGenApiService.class, new MissionScolaireFrFRApiServiceImpl(siteContexte));
	}

	static MissionScolaireFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new MissionScolaireFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static MissionScolaireFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new MissionScolaireFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void rechercheMissionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void postMissionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void patchMissionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void getMissionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void deleteMissionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
