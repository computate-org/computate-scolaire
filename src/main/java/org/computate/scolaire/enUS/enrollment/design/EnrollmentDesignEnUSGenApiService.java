package org.computate.scolaire.enUS.enrollment.design;

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
 * CanonicalName.frFR: org.computate.scolaire.frFR.inscription.design.DesignInscriptionFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface EnrollmentDesignEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-EnrollmentDesign").register(EnrollmentDesignEnUSGenApiService.class, new EnrollmentDesignEnUSApiServiceImpl(siteContext));
	}

	static EnrollmentDesignEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new EnrollmentDesignEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static EnrollmentDesignEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new EnrollmentDesignEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void putEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageEnrollmentDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
