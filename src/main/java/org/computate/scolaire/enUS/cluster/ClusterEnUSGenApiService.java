package org.computate.scolaire.enUS.cluster;

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
public interface ClusterEnUSGenApiService {
	static void registerService(SiteContextEnUS siteContext, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("enUSCluster").register(ClusterEnUSGenApiService.class, new ClusterEnUSApiServiceImpl(siteContext));
	}

	static ClusterEnUSGenApiService create(SiteContextEnUS siteContext, Vertx vertx) {
		return new ClusterEnUSApiServiceImpl(siteContext);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static ClusterEnUSGenApiService createProxy(Vertx vertx, String address) {
		return new ClusterEnUSGenApiServiceVertxEBProxy(vertx, address);
	}

	public void rechercheenuspageClusterId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void rechercheenuspageCluster(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void rechercheCluster(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void postCluster(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void patchCluster(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void getCluster(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
	public void deleteCluster(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);
}
