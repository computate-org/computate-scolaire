package org.computate.scolaire.enUS.dad;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.pere.PereScolaireFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SchoolDadEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-SchoolDad").register(SchoolDadEnUSGenApiService.class, new SchoolDadEnUSApiServiceImpl(siteContext));
	}

	static SchoolDadEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new SchoolDadEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SchoolDadEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new SchoolDadEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postSchoolDad(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void putSchoolDad(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchSchoolDad(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getSchoolDad(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteSchoolDad(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchSchoolDad(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolDadId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolDad(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
