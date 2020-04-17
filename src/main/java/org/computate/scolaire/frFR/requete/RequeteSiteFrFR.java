package org.computate.scolaire.frFR.requete;  

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;

import io.vertx.core.Vertx;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.KeycloakHelper;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.api.OperationRequest; 

/**
 * MotCle: classeNomSimpleRequeteSite
 * NomCanonique.enUS: org.computate.scolaire.enUS.request.SiteRequestEnUS
 */   
public class RequeteSiteFrFR extends RequeteSiteFrFRGen<Object> implements Serializable {  

	private static final long serialVersionUID = -6737494107881513257L;

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteContext_
	 * r: SiteContexteFrFR
	 * r.enUS: SiteContextEnUS
	 * 
	 * enUS: The site context with global site information. 
	 * frFR: Le contexte du site pour obtenir des objets globals du contexte. 
	 **/    
	protected void _siteContexte_(Couverture<SiteContexteFrFR> c) {
	}

	private static final Pattern PATTERN_SESSION = Pattern.compile(".*vertx-web.session=(\\w+)");

	/**	
	 * Var.enUS: siteConfig_
	 * 
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: siteContexte_
	 * r.enUS: siteContext_
	 * 
	 * enUS: The site configuration. 
	 * frFR: L'objet de configuration du site. 
	 * **/
	protected void _configSite_(Couverture<ConfigSite> c) {
		ConfigSite o = siteContexte_.getConfigSite();
		c.o(o);
	}

	/**
	 * Var.enUS: siteRequest_
	 */
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) { 
		c.o(this);
	}

	/**
	 * Var.enUS: apiRequest_
	 */
	protected void _requeteApi_(Couverture<RequeteApi> c) { 
	}

	/**
	 * r: siteContexte_
	 * r.enUS: siteContext_
	 */
	protected void _vertx(Couverture<Vertx> c) {
		if(siteContexte_ != null)
			c.o(siteContexte_.getVertx());
	}

	/**
	 * Var.enUS: jsonObject
	 */
	protected void _objetJson(Couverture<JsonObject> c) {
	}

	/**
	 * Var.enUS: solrQuery
	 */
	protected void _rechercheSolr(Couverture<SolrQuery> c) {
	}

	/**
	 * Var.enUS: operationRequest
	 */
	protected void _operationRequete(Couverture<OperationRequest> c) {
	}

	/**
	 * Var.enUS: queryResponse
	 * r: rechercheSolr
	 * r.enUS: solrQuery
	 * r: siteContexte_
	 * r.enUS: siteContext_
	 * r: getClientSolr
	 * r.enUS: getSolrClient
	 */
	protected void _reponseRecherche(Couverture<QueryResponse> c) {
		if(rechercheSolr != null) {
			try {
				QueryResponse o = siteContexte_.getClientSolr().query(rechercheSolr);
				c.o(o);
			} catch (SolrServerException | IOException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	/**
	 * Var.enUS: searchResults
	 * r: reponseRecherche
	 * r.enUS: queryResponse
	 */
	protected void _resultatsRecherche(Couverture<SolrDocumentList> c) {
		if(reponseRecherche != null) {
			SolrDocumentList o = reponseRecherche.getResults();
			c.o(o);
		}
	} 

	/**
	 * frFR: L'écrivain pour écrirer le résultat du réponse. 
	 * r.enUS: requeteSite_
	 * siteRequest
	 * r.enUS: ecrivain
	 * writer
	 */  
	protected void _w(Couverture<ToutEcrivain> c) {
	}

	/**
	 * Var.enUS: userVertx
	 * r: operationRequete
	 * r.enUS: operationRequest
	 */
	protected void _utilisateurVertx(Couverture<JsonObject> c) {
		if(operationRequete != null) {
			JsonObject o = operationRequete.getUser();
			c.o(o);
		}

	}

	/**
	 * Var.enUS: jsonPrincipal
	 * r: utilisateurVertx
	 * r.enUS: userVertx
	 */
	protected void _principalJson(Couverture<JsonObject> c) {
		if(utilisateurVertx != null) {
			JsonObject o = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userId
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le sujet d'acces Keycloak pour l'utilisateur. 
	 * Aussi l'ID d'utilisateur cle primaire dans la base de donnees Keycloak. 
	 ***/
	protected void _utilisateurId(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("sub");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userKey
	 ***/
	protected void _utilisateurCle(Couverture<Long> c) {
	}

	/**   
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * r: operationRequete
	 * r.enUS: operationRequest
	 */                   
	protected void _sessionId(Couverture<String> c) {
		if(operationRequete != null) {
			String cookie = operationRequete.getHeaders().get("Cookie");
			if(StringUtils.isNotBlank(cookie)) {
				Matcher m = PATTERN_SESSION.matcher(cookie);
				if(m.matches()) {
					c.o(m.group(1));
				}
			}
		}
	}

	/**	
	 * Var.enUS: userName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le nom d'utilisateur prefere de l'utilisateur. 
	 * **/
	protected void _utilisateurNom(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("preferred_username");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userLastName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le nom de famille de l'utilisateur. 
	 * **/
	protected void _utilisateurNomFamille(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("family_name");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userFirstName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le prenom de l'utilisateur. 
	 * **/
	protected void _utilisateurPrenom(Couverture<String> c) { 
		if(principalJson != null) {
			String o = principalJson.getString("given_name");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userFullName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le nom complet de l'utilisateur. 
	 * **/
	protected void _utilisateurNomComplet(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("name");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userRealmRoles
	 * r: configSite_
	 * r.enUS: siteConfig_
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * r: addUtilisateurRolesRoyaume
	 * r.enUS: addUserRealmRoles
	 * frFR: Les rôles de la ressource de l'utilisateur. 
	 * **/
	protected void _utilisateurRolesRoyaume(List<String> o) {
		JsonArray roles = Optional.ofNullable(principalJson).map(o1 -> o1.getJsonObject("realm_access")).map(o2 -> o2.getJsonArray("roles")).orElse(new JsonArray());
		roles.stream().forEach(r -> {
			addUtilisateurRolesRoyaume((String)r);
		});
	}

	/**	
	 * Var.enUS: userResource
	 * r: configSite_
	 * r.enUS: siteConfig_
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: AuthRessource
	 * r.enUS: AuthResource
	 * frFR: Les rôles de la ressource de l'utilisateur. 
	 * **/
	protected void _utilisateurRessource(Couverture<JsonObject> c) {
		JsonObject o = Optional.ofNullable(principalJson).map(p -> p.getJsonObject("resource_access")).map(o1 -> o1.getJsonObject(
				Optional.ofNullable(requeteSite_).map(r -> r.getConfigSite_()).map(c1 -> c1.getAuthRessource()).orElse("")
				)).orElse(new JsonObject());
		c.o(o);
	}

	/**	
	 * Var.enUS: userResourceRoles
	 * r: configSite_
	 * r.enUS: siteConfig_
	 * r: utilisateurRessource
	 * r.enUS: userResource
	 * r: addUtilisateurRolesRessource
	 * r.enUS: addUserResourceRoles
	 * frFR: Les rôles de la ressource de l'utilisateur. 
	 * **/
	protected void _utilisateurRolesRessource(List<String> o) {
		JsonArray roles = Optional.ofNullable(utilisateurRessource).map(o2 -> o2.getJsonArray("roles")).orElse(new JsonArray());
		roles.stream().forEach(r -> {
			addUtilisateurRolesRessource((String)r);
		});
	}

	/**	
	 * Var.enUS: siteUser
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: utilisateurId
	 * r.enUS: userId
	 * r: utilisateurPrenom
	 * r.enUS: userFirstName
	 * r: utilisateurNomFamille
	 * r.enUS: userLastName
	 * r: utilisateurNom
	 * r.enUS: userName
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: UtilisateurPrenom
	 * r.enUS: UserFirstName
	 * r: UtilisateurNomFamille
	 * r.enUS: UserLastName
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 * L'utilisateur de la requête. 
	 * **/ 
	protected void _utilisateurSite(Couverture<UtilisateurSite> c) { 
		if(utilisateurId != null) {
			UtilisateurSite o = new UtilisateurSite();
			o.setUtilisateurNom(utilisateurNom);
			o.setUtilisateurPrenom(utilisateurPrenom);
			o.setUtilisateurNomFamille(utilisateurNomFamille);
			o.setUtilisateurId(utilisateurId);
			c.o(o);
		}
	}

	/**
	 * Var.enUS: xmlStack
	 */
	protected void _xmlPile(Stack<String> o) {}

	/**
	 * Var.enUS: solrDocument
	 */
	protected void _documentSolr(Couverture<SolrDocument> c) {  
	}

	/**	
	 * frFR: Si la page vu etait achete par l'utilisateur. 
	 * **/
	protected void _pageAdmin(Couverture<Boolean> c) { 
		c.o(false);
	} 
	
	/**
	 * Var.enUS: requestPk
	 * r: operationRequete
	 * r.enUS: operationRequest
	 */
	protected void _requetePk(Couverture<Long> c) {
		if(operationRequete != null)
			c.o(operationRequete.getParams().getLong("pk"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sqlConnection
	 **/
	protected void _connexionSql(Couverture<SQLConnection> c) {
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: requestHeaders
	 **/
	protected void _requeteEnTetes(Couverture<CaseInsensitiveHeaders> c) {
	}
	
	/**
	 * Var.enUS: requestVars
	 */
	protected void _requeteVars(Map<String, String> m) {
	}

	/**
	 * r: RequeteSiteFrFR
	 * r.enUS: SiteRequestEnUS
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: RequeteApi
	 * r.enUS: ApiRequest
	 * r: requeteApi
	 * r.enUS: apiRequest
	 * r: ObjetJson
	 * r.enUS: JsonObject
	 * r: objetJson
	 * r.enUS: jsonObject
	 * r: RechercheSolr
	 * r.enUS: SolrQuery
	 * r: rechercheSolr
	 * r.enUS: solrQuery
	 * r: OperationRequete
	 * r.enUS: OperationRequest
	 * r: operationRequete
	 * r.enUS: operationRequest
	 * r: UtilisateurCle
	 * r.enUS: UserKey
	 * r: utilisateurCle
	 * r.enUS: userKey
	 * r: DocumentSolr
	 * r.enUS: SolrDocument
	 * r: documentSolr
	 * r.enUS: solrDocument
	 * r: ConnexionSql
	 * r.enUS: SqlConnection
	 * r: connexionSql
	 * r.enUS: sqlConnection
	 * r: RequeteEnTetes
	 * r.enUS: RequestHeaders
	 * r: requeteEnTetes
	 * r.enUS: requestHeaders
	 * r: RequeteVars
	 * r.enUS: RequestVars
	 * r: requeteVars
	 * r.enUS: requestVars
	 */
	public RequeteSiteFrFR copy() {
		RequeteSiteFrFR o = new RequeteSiteFrFR();
		o.setSiteContexte_(siteContexte_);
		o.setRequeteApi_(requeteApi_);
		o.setObjetJson(objetJson);
		o.setRechercheSolr(rechercheSolr);
		o.setOperationRequete(operationRequete);
		o.setUtilisateurCle(utilisateurCle);
		o.setDocumentSolr(documentSolr);
		o.setPageAdmin(pageAdmin);
		o.setConnexionSql(connexionSql);
		o.setRequeteEnTetes(requeteEnTetes);
		o.setRequeteVars(requeteVars);
		return o;
	}
}
