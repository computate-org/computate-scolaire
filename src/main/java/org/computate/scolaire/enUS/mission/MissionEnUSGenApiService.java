package org.computate.scolaire.enUS.mission;

import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
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
 * Translate: false
 * classCanonicalName.frFR: org.computate.scolaire.frFR.mission.MissionScolaireFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface MissionEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("enUSMission").register(MissionEnUSGenApiService.class, new MissionEnUSApiServiceImpl(siteContext));
	}

	static MissionEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new MissionEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static MissionEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new MissionEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void rechercheMission(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void postMission(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchMission(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getMission(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteMission(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
