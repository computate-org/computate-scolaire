package org.computate.scolaire.enUS.enrollment.form;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.inscription.form.FormInscriptionFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface EnrollmentFormEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("enUSEnrollmentForm").register(EnrollmentFormEnUSGenApiService.class, new EnrollmentFormEnUSApiServiceImpl(siteContext));
	}

	static EnrollmentFormEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new EnrollmentFormEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static EnrollmentFormEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new EnrollmentFormEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postEnrollmentForm(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchEnrollmentForm(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageEnrollmentFormId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
