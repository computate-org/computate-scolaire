package org.computate.scolaire.enUS.form.part;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.form.part.PartFormFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface FormPartEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("enUSFormPart").register(FormPartEnUSGenApiService.class, new FormPartEnUSApiServiceImpl(siteContext));
	}

	static FormPartEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new FormPartEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static FormPartEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new FormPartEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postFormPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchFormPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageFormPartId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
