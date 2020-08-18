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
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.pgclient.PgPool;

/**
 * CanonicalName: org.computate.scolaire.frFR.contexte.SiteContexteFrFR
 **/
public class SiteContextEnUS extends SiteContextEnUSGen<Object> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	public static final String SQL_selectC = "select pk, current, canonical_name, created, user_id from c where canonical_name=$1 and user_id=$2;\n";

	public static final String SQL_exists = "select count(*), canonical_name, user_id from c group by canonical_name, user_id having c.pk=$1;\n";

	public static final String SQL_create = "insert into c(canonical_name, user_id, created) values($1, $2, $3) returning pk;\n";

	public static final String SQL_setD = "insert into d(pk_c, path, value, created) values($1, $2, $3, now()) on conflict on constraint d_constraint do update set value=$3 returning pk, path, pk_c;\n";

	public static final String SQL_removeD = "delete from d where pk_c=$1 and path=$2;\n";

	public static final String SQL_define = "select path, value from d where d.pk_c=$1 union select 'created', to_char(created, 'YYYY-MM-DD\"T\"HH24:MI:SS.USOF\":00\"') from c where pk=$1;\n";

	public static final String SQL_attribute = "select pk1, pk2, entity1, entity2 from a where (a.pk1=$1 or a.pk2=$2) order by created;\n";

	public static final String SQL_addA = "insert into a(pk1, entity1, pk2, entity2, created) values($1, $2, $3, $4, now()) on conflict on constraint a_constraint do nothing;\n";

	public static final String SQL_removeA = "delete from a where pk1=$1 and entity1=$2 and pk2=$3 and entity2=$4;\n";

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

	protected void _pgPool(Wrap<PgPool> c) {
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
