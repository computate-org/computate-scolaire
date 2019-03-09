package org.computate.frFR.scolaire.utilisateur;

import org.computate.frFR.scolaire.contexte.SiteContexte;
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

@WebApiServiceGen
@ProxyGen
public interface UtilisateurSiteGenApiService {
	// Une méthode d'usine pour créer une instance et un proxy. 
	static void enregistrerService(SiteContexte siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("UtilisateurSite").register(UtilisateurSiteGenApiService.class, new UtilisateurSiteApiServiceImpl(siteContexte));
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static UtilisateurSiteGenApiService creer(SiteContexte siteContexte, Vertx vertx) {
		return new UtilisateurSiteApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static UtilisateurSiteGenApiService creerProxy(Vertx vertx, String addresse) {
		return new UtilisateurSiteGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void patchUtilisateurSite(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void recherchepageUtilisateurSiteId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void recherchepageUtilisateurSite(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
