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
 * Traduire: false
 * Gen: false
 **/
@WebApiServiceGen
@ProxyGen
public interface SiteUserEnUSGenApiService {
	// Une méthode d'usine pour créer une instance et un proxy. 
	static void enregistrerService(SiteContextEnUS siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("enUSSiteUser").register(SiteUserEnUSGenApiService.class, new SiteUserEnUSApiServiceImpl(siteContexte));
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SiteUserEnUSGenApiService creer(SiteContextEnUS siteContexte, Vertx vertx) {
		return new SiteUserEnUSApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static SiteUserEnUSGenApiService creerProxy(Vertx vertx, String addresse) {
		return new SiteUserEnUSGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void patchUtilisateurSite(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
