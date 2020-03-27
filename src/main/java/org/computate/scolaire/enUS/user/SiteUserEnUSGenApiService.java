package org.computate.scolaire.enUS.user;

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
 * CanonicalName.frFR: org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenApiService
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SiteUserEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("computate-scolaire-enUS-SiteUser").register(SiteUserEnUSGenApiService.class, new SiteUserEnUSApiServiceImpl(siteContext));
	}

	static SiteUserEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new SiteUserEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SiteUserEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new SiteUserEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void searchSiteUser(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchSiteUser(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void postSiteUser(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSiteUserId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void searchpageSiteUser(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
