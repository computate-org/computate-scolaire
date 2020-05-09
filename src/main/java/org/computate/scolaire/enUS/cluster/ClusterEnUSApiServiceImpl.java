package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.vertx.AppRestore;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

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
		Promise<Void> promise = Promise.promise();
		AppRestore appRestore = new AppRestore();
		appRestore.init(siteRequest.getVertx(), siteRequest.getVertx().getOrCreateContext());
		try {
			appRestore.start(siteRequest, promise);
//			if(promise.succeeded())
//				eventHandler.handle(Future.succeededFuture());
//			else
//				eventHandler.handle(Future.failedFuture(promise.cause()));
		} catch (Exception e) {
			promise.fail(e);
		}
		return promise.future();
	}
}
