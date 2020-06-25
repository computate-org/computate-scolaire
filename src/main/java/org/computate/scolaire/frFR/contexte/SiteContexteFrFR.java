package org.computate.scolaire.frFR.contexte;    

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.pgclient.PgPool;


/**   
 * InitLoin: true
 * MotCle: classeNomSimpleSiteContexte
 * NomCanonique.enUS: org.computate.scolaire.enUS.contexte.SiteContextEnUS
 */  
public class SiteContexteFrFR extends SiteContexteFrFRGen<Object> {  
	protected Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * r: ajour
	 * r.enUS: current
	 * r: nom_canonique
	 * r.enUS: canonical_name
	 * r: cree
	 * r.enUS: created
	 * r: id_utilisateur
	 * r.enUS: user_id
	 */
	public static final String SQL_selectC = "select pk, ajour, nom_canonique, cree, id_utilisateur from c where nom_canonique=$1 and id_utilisateur=$2;\n";
	/**
	 * Var.enUS: SQL_exists
	 * r: nom_canonique
	 * r.enUS: canonical_name
	 * r: id_utilisateur
	 * r.enUS: user_id
	 */
	public static final String SQL_existe = "select count(*), nom_canonique, id_utilisateur from c group by nom_canonique, id_utilisateur having c.pk=$1;\n";
	/**
	 * Var.enUS: SQL_create
	 * r: nom_canonique
	 * r.enUS: canonical_name
	 * r: id_utilisateur
	 * r.enUS: user_id
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_creer = "insert into c(nom_canonique, id_utilisateur, cree) values($1, $2, $3) returning pk;\n";

	/**
	 * r: chemin
	 * r.enUS: path
	 * r: valeur
	 * r.enUS: value
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_setD = "insert into d(pk_c, chemin, valeur, actuel, cree) values($1, $2, $3, true, now()) on conflict on constraint d_constraint do update set actuel=true, valeur=$3 returning pk, chemin, pk_c;\n";

	/**
	 * r: chemin
	 * r.enUS: path
	 * r: valeur
	 * r.enUS: value
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_removeD = "insert into d(pk_c, chemin, valeur, actuel, cree) values($1, $2, null, true, now()) on conflict on constraint d_constraint do update set actuel=false, valeur=null returning pk, chemin, pk_c;\n";

	/**
	 * Var.enUS: SQL_define
	 * r: chemin
	 * r.enUS: path
	 * r: valeur
	 * r.enUS: value
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_definir = "select chemin, valeur from d where d.pk_c=$1 and d.actuel union select 'cree', to_char(cree, 'YYYY-MM-DD\"T\"HH24:MI:SS.USOF\":00\"') from c where pk=$1;\n";
	/**
	 * Var.enUS: SQL_attribute
	 * r: entite
	 * r.enUS: entity
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_attribuer = "select pk1, pk2, entite1, entite2 from a where (a.pk1=$1 or a.pk2=$2) and a.actuel=true;\n";

	/**
	 * r: entite
	 * r.enUS: entity
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_addA = "insert into a(pk1, entite1, pk2, entite2, actuel, cree) values($1, $2, $3, $4, true, now()) on conflict on constraint a_constraint do update set actuel=true;\n";

	/**
	 * r: entite
	 * r.enUS: entity
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_removeA = "insert into a(pk1, entite1, pk2, entite2, actuel, cree) values($1, $2, $3, $4, false, now()) on conflict on constraint a_constraint do update set actuel=false;\n";

	protected void _vertx(Couverture<Vertx> c) {
	}

	/**
	 * Var.enUS: routerFactory
	 */
	protected void _usineRouteur(Couverture<OpenAPI3RouterFactory> c) {
	}

	/**
	 * Var.enUS: router
	 */
	protected void _routeur(Couverture<Router> c) {
	}

	/**
	 * Var.enUS: authHandler
	 */
	protected void _gestionnaireAuth(Couverture<OAuth2AuthHandler> c) {
	}

	/**
	 * Var.enUS: authProvider
	 */
	protected void _authFournisseur(Couverture<OAuth2Auth> c) {
	}

	/**
	 * Var.enUS: workerExecutor
	 */
	protected void _executeurTravailleur(Couverture<WorkerExecutor> c) {
	}

	/**	
	 * Var.enUS: siteConfig
	 * Le config du site. **/
	protected void _configSite(ConfigSite o) { 
	}

	/**	
	 * frFR: Le source de données du site. 
	 * **/ 
	protected void _pgPool(Couverture<PgPool> c) {
	}
//
//	/**	L'URL JNDI vers le source de courriels dans Tomcat. **/
//	protected void _urlSourceMail(Couverture<String> c) {
//		String o = "java:comp/env/mail/Session";
//		c.o(o);
//	}
//
//	/**	Le source de courriels du site. **/
//	protected void _sessionCourriel(Couverture<Session> c) {
//		try {
//			Session o = (Session)contexteInitiale.lookup(urlSourceMail);
//			c.o(o);
//		} catch(NamingException e) {
//			Properties proprietes = new Properties();
//			proprietes.setProperty("mail.smtp.host", urlSourceDonneesSimple);
//			Session o = Session.getInstance(proprietes);
//			c.o(o);
//		}
//	}

	/**	
	 * Var.enUS: solrClient
	 * r: configSite
	 * r.enUS: siteConfig
	 * Le client du moteur de recherche SOLR. 
	 **/
	protected void _clientSolr(Couverture<HttpSolrClient> c) {
		HttpSolrClient o = new HttpSolrClient.Builder(configSite.getSolrUrl()).build();
		c.o(o);
	}

	/**	
	 **/
	protected void _mailClient(Couverture<MailClient> c) {
	}

	/**	
	 * Var.enUS: solrClientComputate
	 * r: configSite
	 * r.enUS: siteConfig
	 * Le client du moteur de recherche SOLR pour le projet computate. 
	 **/
	protected void _clientSolrComputate(Couverture<HttpSolrClient> c) {
		String solrUrlComputate = configSite.getSolrUrlComputate();
		if(StringUtils.isNotEmpty(solrUrlComputate)) {
			HttpSolrClient o = new HttpSolrClient.Builder(solrUrlComputate).build();
			c.o(o);
		}
	}
}
