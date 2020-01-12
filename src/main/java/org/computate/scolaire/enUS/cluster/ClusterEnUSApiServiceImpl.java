package org.computate.scolaire.enUS.cluster;

import java.util.ArrayList;
import java.util.List;

import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.vertx.AppRestore;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

/**
 * Translate: false
 **/
public class ClusterEnUSApiServiceImpl extends ClusterEnUSGenApiServiceImpl {

	public ClusterEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}
//
//	public Handler<Future<Object>> start1() {
//		AppRestore appRestore = new AppRestore();
//		appRestore.start( future );
//	}
//
	public Future<Void>  appRestore(SiteRequestEnUS siteRequest, Handler<Future<Void>> eventHandler) throws Exception, Exception {
		Future<Void> future = Future.future();
		AppRestore appRestore = new AppRestore();
		appRestore.init(siteRequest.getVertx(), siteRequest.getVertx().getOrCreateContext());
		try {
			appRestore.start(siteRequest, future);
			if(future.succeeded())
				eventHandler.handle(Future.succeededFuture());
			else
				eventHandler.handle(Future.failedFuture(future.cause()));
		} catch (Exception e) {
			future.fail(e);
		}
		return future;
	}

	@Override
	public void patchCluster(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		if(operationRequest.getParams().getJsonObject("query").getJsonArray("fq").size() > 0) {
			super.patchCluster(body, operationRequest, eventHandler);
		} else {
			try {
				SiteRequestEnUS siteRequest = generateSiteRequestEnUSForCluster(siteContext, operationRequest, body);
				WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
				workerExecutor.executeBlocking(
					future -> {
						try {
							appRestore(siteRequest, a -> {
								if(a.succeeded())
									a.complete();
								else
									errorCluster(siteRequest, eventHandler, a);
							});
						} catch (Exception e) {
							errorCluster(null, eventHandler, Future.failedFuture(e));
						}
					}
					, res -> {
						LOGGER.info("AppRestore complete. ");
						Buffer buffer = Buffer.buffer();
						buffer.appendString("{}");
						eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
					}
				);
			} catch(Exception e) {
				errorCluster(null, eventHandler, Future.failedFuture(e));
			}
		}
	}
}
