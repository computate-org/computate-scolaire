package org.computate.frFR.scolaire.utilisateur;

import org.computate.frFR.scolaire.contexte.SiteContexte;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.Vertx;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

@WebApiServiceGen
@ProxyGen
public interface UtilisateurSiteGenApiService {
	// Une méthode d'usine pour créer une instance et un proxy. 
	static UtilisateurSiteGenApiService creer(SiteContexte siteContexte, Vertx vertx) {
		return new UtilisateurSiteApiServiceImpl(siteContexte);
	}

	// Une méthode d'usine pour créer une instance et un proxy. 
	static UtilisateurSiteGenApiService creerProxy(Vertx vertx, String addresse) {
		return new UtilisateurSiteGenApiServiceVertxEBProxy(vertx, addresse);
	}

}
