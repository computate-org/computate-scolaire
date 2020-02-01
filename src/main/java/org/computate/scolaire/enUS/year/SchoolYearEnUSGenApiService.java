package org.computate.scolaire.enUS.year;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.annee.AnneeScolaireFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SchoolYearEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-SchoolYear").register(SchoolYearEnUSGenApiService.class, new SchoolYearEnUSApiServiceImpl(siteContext));
	}

	static SchoolYearEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new SchoolYearEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SchoolYearEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new SchoolYearEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postSchoolYear(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void putSchoolYear(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchSchoolYear(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getSchoolYear(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteSchoolYear(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchSchoolYear(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolYearId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolYear(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
