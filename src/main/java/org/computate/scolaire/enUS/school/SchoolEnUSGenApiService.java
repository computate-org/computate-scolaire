package org.computate.scolaire.enUS.school;

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
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SchoolEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("enUSSchool").register(SchoolEnUSGenApiService.class, new SchoolEnUSApiServiceImpl(siteContext));
	}

	static SchoolEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new SchoolEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SchoolEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new SchoolEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void rechercheenuspageEcoleId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void rechercheenuspageEcole(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchEcole(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void postEcole(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getEcole(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteEcole(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
