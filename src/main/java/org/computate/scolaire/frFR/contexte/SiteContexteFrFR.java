package org.computate.scolaire.frFR.contexte;    

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;


/**   
 * InitLoin: true
 * MotCle: classeNomSimpleSiteContexte
 * NomCanonique.enUS: org.computate.scolaire.enUS.contexte.SiteContextEnUS
 */  
public class SiteContexteFrFR extends SiteContexteFrFRGen<Object> { 
	protected Logger log = LoggerFactory.getLogger(getClass());

	public static final String SQL_selectC = "select pk, ajour, nom_canonique, cree, modifie, id_utilisateur from c where nom_canonique=? and id_utilisateur=?;\n";
	public static final String SQL_existe = "select count(*), nom_canonique, id_utilisateur from c group by nom_canonique, id_utilisateur having c.pk=?;\n";
	public static final String SQL_creer = "insert into c(nom_canonique, id_utilisateur) values(?, ?) returning pk;\n";
	public static final String SQL_modifier = "update c set modifie=now() where pk=? and nom_canonique=? returning cree;\n";
	public static final String SQL_setD = "with d1 as (insert into d(chemin, valeur, actuel, pk_c) values(?, ?, true, ?) returning pk, chemin, pk_c) update d set actuel=false, modifie=now() where d.pk_c=(select pk_c from d1) and d.chemin=(select chemin from d1) and d.actuel=true and d.pk != (select pk from d1);\n";
	public static final String SQL_removeD = "update d set actuel=false, modifie=now() where d.pk_c=? and d.chemin=? and d.actuel=true;\n";
	public static final String SQL_definir = "select chemin, valeur from d where d.pk_c=? and d.actuel union select 'cree', to_char(cree, 'YYYY-MM-DD\"T\"HH24:MI:SS.USOF\":00\"') from c where pk=? union select 'modifie', to_char(modifie, 'YYYY-MM-DD\"T\"HH24:MI:SS.USOF\":00\"') from c where pk=?;\n";
	public static final String SQL_attribuer = "select pk1, pk2, entite1, entite2 from a where (a.pk1=? or a.pk2=?) and a.actuel=true;\n";
	public static final String SQL_setA1 = "with a1 as (insert into a(entite1, pk1, entite2, pk2, actuel) values(?, ?, ?, ?, true) returning pk, entite1, pk1, entite2, pk2) update a set actuel=false, modifie=now() where a.entite1=(select entite1 from a1) and a.pk1=(select pk1 from a1) and a.entite2=(select entite2 from a1) and a.actuel=true and a.pk != (select pk from a1);\n";
	public static final String SQL_setA2 = "with a1 as (insert into a(entite1, pk1, entite2, pk2, actuel) values(?, ?, ?, ?, true) returning pk, entite1, pk1, entite2, pk2) update a set actuel=false, modifie=now() where a.entite1=(select entite1 from a1) and a.entite2=(select entite2 from a1) and a.pk2=(select pk2 from a1) and a.actuel=true and a.pk != (select pk from a1);\n";
	public static final String SQL_addA = "with a1 as (insert into a(entite1, pk1, entite2, pk2, actuel) values(?, ?, ?, ?, true) returning pk, entite1, pk1, entite2, pk2) update a set actuel=false, modifie=now() where a.entite1=(select entite1 from a1) and a.pk1=(select pk1 from a1) and a.entite2=(select entite2 from a1) and a.pk2=(select pk2 from a1) and a.actuel=true and a.pk != (select pk from a1);\n";
	public static final String SQL_clearA1 = "update a set actuel=false, modifie=now() where a.entite1=? and a.pk1=? and a.entite2=? and a.actuel=true;\n";
	public static final String SQL_clearA2 = "update a set actuel=false, modifie=now() where a.entite1=? and a.entite2=? and a.pk2=? and a.actuel=true;\n";
	public static final String SQL_removeA = "update a set actuel=false, modifie=now() where a.entite1=? and a.pk1=? and a.entite2=? and a.pk2=? and a.actuel=true;\n";
	public static final String SQL_vider = "update c set modifie=now() where objet.pk=? and objet.nom_canonique=? returning cree;\nupdate a set actuel=false, modifie=now() where a.pk1=? or a.pk2=? and a.actuel=true;\nupdate p set actuel=false, modifie=now() where p.pk_c=? and p.actuel=true;\n";
	public static final String SQL_supprimer = "update c set modifie=now() where objet.pk=? and objet.nom_canonique=? returning cree;\nupdate a set actuel=false, modifie=now() where a.pk1=? or a.pk2=? and a.actuel=true;\nupdate p set actuel=false, modifie=now() where p.pk_c=? and p.actuel=true;\nwith p1 as (insert into p(chemin, valeur, actuel, pk_c) values('supprime', true, true, ?) returning pk, chemin, pk_c) update p set actuel=false, modifie=now() where p.pk_c=(select pk_c from p1) and p.chemin=(select chemin from p1) and p.actuel=true and p.pk != (select pk from p1);\n";

	protected void _vertx(Couverture<Vertx> c) {
	}

	/**
	 * Var.enUS: routerFactory
	 */
	protected void _usineRouteur(Couverture<OpenAPI3RouterFactory> c) {
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

	/**	Le source de données du site. **/
	protected void _clientSql(Couverture<SQLClient> c) {
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
}
