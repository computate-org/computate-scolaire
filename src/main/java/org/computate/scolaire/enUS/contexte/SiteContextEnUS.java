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
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class SiteContextEnUS extends SiteContextEnUSGen<Object> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	public static final String SQL_selectC = "select pk, current, canonical_name, created, modified, user_id from c where canonical_name=? and user_id=?;\n";

	public static final String SQL_exists = "select count(*), canonical_name, user_id from c group by canonical_name, user_id having c.pk=?;\n";

	public static final String SQL_create = "insert into c(canonical_name, user_id) values(?, ?) returning pk;\n";

	public static final String SQL_modify = "update c set modified=now() where pk=? and canonical_name=? returning created;\n";

	public static final String SQL_setD = "with d1 as (insert into d(path, value, current, pk_c) values(?, ?, true, ?) returning pk, path, pk_c) update d set current=false, modified=now() where d.pk_c=(select pk_c from d1) and d.path=(select path from d1) and d.current=true and d.pk != (select pk from d1);\n";

	public static final String SQL_removeD = "update d set current=false, modified=now() where d.pk_c=? and d.path=? and d.current=true;\n";

	public static final String SQL_define = "select path, value from d where d.pk_c=? and d.current union select 'created', to_char(created, 'YYYY-MM-DD\"T\"HH24:MI:SS.USOF\":00\"') from c where pk=? union select 'modified', to_char(modified, 'YYYY-MM-DD\"T\"HH24:MI:SS.USOF\":00\"') from c where pk=?;\n";

	public static final String SQL_attribute = "select pk1, pk2, entity1, entity2 from a where (a.pk1=? or a.pk2=?) and a.current=true;\n";

	public static final String SQL_setA1 = "with a1 as (insert into a(entity1, pk1, entity2, pk2, current) values(?, ?, ?, ?, true) returning pk, entity1, pk1, entity2, pk2) update a set current=false, modified=now() where a.entity1=(select entity1 from a1) and a.pk1=(select pk1 from a1) and a.entity2=(select entity2 from a1) and a.current=true and a.pk != (select pk from a1);\n";

	public static final String SQL_setA2 = "with a1 as (insert into a(entity1, pk1, entity2, pk2, current) values(?, ?, ?, ?, true) returning pk, entity1, pk1, entity2, pk2) update a set current=false, modified=now() where a.entity1=(select entity1 from a1) and a.entity2=(select entity2 from a1) and a.pk2=(select pk2 from a1) and a.current=true and a.pk != (select pk from a1);\n";

	public static final String SQL_addA = "with a1 as (insert into a(entity1, pk1, entity2, pk2, current) values(?, ?, ?, ?, true) returning pk, entity1, pk1, entity2, pk2) update a set current=false, modified=now() where a.entity1=(select entity1 from a1) and a.pk1=(select pk1 from a1) and a.entity2=(select entity2 from a1) and a.pk2=(select pk2 from a1) and a.current=true and a.pk != (select pk from a1);\n";

	public static final String SQL_clearA1 = "update a set current=false, modified=now() where a.entity1=? and a.pk1=? and a.entity2=? and a.current=true;\n";

	public static final String SQL_clearA2 = "update a set current=false, modified=now() where a.entity1=? and a.entity2=? and a.pk2=? and a.current=true;\n";

	public static final String SQL_removeA = "update a set current=false, modified=now() where a.entity1=? and a.pk1=? and a.entity2=? and a.pk2=? and a.current=true;\n";

	public static final String SQL_clear = "update c set modified=now() where objet.pk=? and objet.canonical_name=? returning created;\nupdate a set actuel=false, modified=now() where a.pk1=? or a.pk2=? and a.actuel=true;\nupdate p set actuel=false, modified=now() where p.pk_c=? and p.actuel=true;\n";

	public static final String SQL_delete = "update c set modified=now() where objet.pk=? and objet.canonical_name=? returning created;\nupdate a set actuel=false, modified=now() where a.pk1=? or a.pk2=? and a.actuel=true;\nupdate p set actuel=false, modified=now() where p.pk_c=? and p.actuel=true;\nwith p1 as (insert into p(chemin, valeur, actuel, pk_c) values('supprime', true, true, ?) returning pk, chemin, pk_c) update p set actuel=false, modified=now() where p.pk_c=(select pk_c from p1) and p.chemin=(select chemin from p1) and p.actuel=true and p.pk != (select pk from p1);\n";

	protected void _vertx(Wrap<Vertx> c) {
	}

	protected void _routerFactory(Wrap<OpenAPI3RouterFactory> c) {
	}

	protected void _router(Wrap<Router> c) {
	}

	protected void _authHandler(Wrap<OAuth2AuthHandler> c) {
	}

	protected void _authProvider(Wrap<OAuth2Auth> c) {
	}

	protected void _workerExecutor(Wrap<WorkerExecutor> c) {
	}

	protected void _siteConfig(SiteConfig o) { 
	}

	protected void _sqlClient(Wrap<SQLClient> c) {
	}

	protected void _solrClient(Wrap<HttpSolrClient> c) {
		HttpSolrClient o = new HttpSolrClient.Builder(siteConfig.getSolrUrl()).build();
		c.o(o);
	}

	protected void _solrClientComputate(Wrap<HttpSolrClient> c) {
		String solrUrlComputate = siteConfig.getSolrUrlComputate();
		if(StringUtils.isNotEmpty(solrUrlComputate)) {
			HttpSolrClient o = new HttpSolrClient.Builder(solrUrlComputate).build();
			c.o(o);
		}
	}
}
