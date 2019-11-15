package org.computate.scolaire.enUS.html.part;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.html.part.PartHtmlFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface HtmlPartEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-HtmlPart").register(HtmlPartEnUSGenApiService.class, new HtmlPartEnUSApiServiceImpl(siteContext));
	}

	static HtmlPartEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new HtmlPartEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static HtmlPartEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new HtmlPartEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postHtmlPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchHtmlPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getHtmlPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteHtmlPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchHtmlPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageHtmlPartId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageHtmlPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
