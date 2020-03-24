package org.computate.scolaire.enUS.design;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.design.DesignPageFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface PageDesignEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-PageDesign").register(PageDesignEnUSGenApiService.class, new PageDesignEnUSApiServiceImpl(siteContext));
	}

	static PageDesignEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new PageDesignEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static PageDesignEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new PageDesignEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postPageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void putPageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchPageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getPageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchPageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpagePageDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpagePageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
