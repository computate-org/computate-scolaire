package org.computate.scolaire.enUS.enrollment;

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
 * classCanonicalName.frFR: org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SchoolEnrollmentEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-SchoolEnrollment").register(SchoolEnrollmentEnUSGenApiService.class, new SchoolEnrollmentEnUSApiServiceImpl(siteContext));
	}

	static SchoolEnrollmentEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new SchoolEnrollmentEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SchoolEnrollmentEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new SchoolEnrollmentEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void postSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolEnrollmentId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void formsearchpageSchoolEnrollmentId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void formsearchpageSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void pdfsearchpageSchoolEnrollmentId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void pdfsearchpageSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
