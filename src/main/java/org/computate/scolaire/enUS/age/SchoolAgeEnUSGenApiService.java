package org.computate.scolaire.enUS.age;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.age.AgeScolaireFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SchoolAgeEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-SchoolAge").register(SchoolAgeEnUSGenApiService.class, new SchoolAgeEnUSApiServiceImpl(siteContext));
	}

	static SchoolAgeEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new SchoolAgeEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SchoolAgeEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new SchoolAgeEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void putSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolAgeId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
