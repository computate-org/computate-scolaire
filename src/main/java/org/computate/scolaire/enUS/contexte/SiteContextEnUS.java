package org.computate.scolaire.enUS.contexte;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;

public class SiteContextEnUS extends SiteContextEnUSGen<Object> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	public static final String SQL_selectC = "select pk, current, canonical_name, created, user_id from c where canonical_name=? and user_id=?;\n";

	public static final String SQL_exists = "select count(*), canonical_name, user_id from c group by canonical_name, user_id having c.pk=?;\n";

	public static final String SQL_create = "insert into c(canonical_name, user_id, created) values(?, ?, now()) returning pk;\n";

	public static final String SQL_setD = "insert into d(pk_c, path, value, current, created) values(?, ?, ?, true, now()) on conflict on constraint d_constraint do update set current=true, value=d.value returning pk, path, pk_c;\n";

	public static final String SQL_removeD = "insert into d(pk_c, path, value, current, created) values(?, ?, null, true, now()) on conflict on constraint d_constraint do update set current=false, value=null returning pk, path, pk_c;\n";

	public static final String SQL_define = "select path, value from d where d.pk_c=? and d.current union select 'created', to_char(created, 'YYYY-MM-DD\"T\"HH24:MI:SS.USOF\":00\"') from c where pk=?;\n";

	public static final String SQL_attribute = "select pk1, pk2, entity1, entity2 from a where (a.pk1=? or a.pk2=?) and a.current=true;\n";

	public static final String SQL_addA = "insert into a(pk1, entity1, pk2, entity2, current, created) values(?, ?, ?, ?, true, now()) on conflict on constraint a_constraint do update set current=true;\n";

	public static final String SQL_removeA = "insert into a(pk1, entity1, pk2, entity2, current, created) values(?, ?, ?, ?, false, now()) on conflict on constraint a_constraint do update set current=false;\n";

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

	protected void _mailClient(Wrap<MailClient> c) {
	}

	protected void _solrClientComputate(Wrap<HttpSolrClient> c) {
		String solrUrlComputate = siteConfig.getSolrUrlComputate();
		if(StringUtils.isNotEmpty(solrUrlComputate)) {
			HttpSolrClient o = new HttpSolrClient.Builder(solrUrlComputate).build();
			c.o(o);
		}
	}
}
