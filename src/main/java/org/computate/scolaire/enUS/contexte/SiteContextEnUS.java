package org.computate.scolaire.enUS.contexte;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;

public class SiteContextEnUS extends SiteContextEnUSGen<Object> {

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

	protected void _vertx(Wrap<Vertx> c) {
	}

	protected void _routerFactory(Wrap<OpenAPI3RouterFactory> c) {
	}

	protected void _authHandler(Wrap<OAuth2AuthHandler> c) {
	}

	protected void _authProvider(Wrap<OAuth2Auth> c) {
	}

	protected void _workerExecutor(Wrap<WorkerExecutor> c) {
	}

	protected void _siteConfig(SiteConfig o) { 
	}

	protected void _clientSql(Wrap<SQLClient> c) {
	}

	protected void _solrClient(Wrap<HttpSolrClient> c) {
		HttpSolrClient o = new HttpSolrClient.Builder(siteConfig.getSolrUrl()).build();
		c.o(o);
	}
}
