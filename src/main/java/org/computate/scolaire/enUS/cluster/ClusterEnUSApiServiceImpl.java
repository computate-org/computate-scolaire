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
 * CanonicalName.frFR: org.computate.scolaire.frFR.cluster.ClusterFrFRApiServiceImpl
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
}
