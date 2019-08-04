package org.computate.scolaire.frFR.utilisateur;

import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
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
public interface UtilisateurSiteFrFRGenApiService {
	static void enregistrerService(SiteContexteFrFR siteContexte, Vertx vertx) {
		new ServiceBinder(vertx).setAddress("frFRUtilisateurSite").register(UtilisateurSiteFrFRGenApiService.class, new UtilisateurSiteFrFRApiServiceImpl(siteContexte));
	}

	static UtilisateurSiteFrFRGenApiService creer(SiteContexteFrFR siteContexte, Vertx vertx) {
		return new UtilisateurSiteFrFRApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static UtilisateurSiteFrFRGenApiService creerProxy(Vertx vertx, String addresse) {
		return new UtilisateurSiteFrFRGenApiServiceVertxEBProxy(vertx, addresse);
	}

	public void patchUtilisateurSite(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheUtilisateurSiteId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
	public void pagerechercheUtilisateurSite(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);
}
