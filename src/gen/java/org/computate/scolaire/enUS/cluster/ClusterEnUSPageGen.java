package org.computate.scolaire.enUS.cluster;

import java.util.Objects;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.cluster.ClusterEnUSGenPage;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterEnUSPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ClusterEnUSPageGen<DEV> extends ClusterEnUSGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedClusterEnUSPage = false;

	public ClusterEnUSPage initDeepClusterEnUSPage(SiteRequestEnUS siteRequest_) {
		if(!alreadyInitializedClusterEnUSPage) {
			alreadyInitializedClusterEnUSPage = true;
			initDeepClusterEnUSPage();
		}
		return (ClusterEnUSPage)this;
	}

	public void initDeepClusterEnUSPage() {
		super.initDeepClusterEnUSGenPage(siteRequest_);
		initClusterEnUSPage();
	}

	public void initClusterEnUSPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepClusterEnUSPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterEnUSPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainClusterEnUSPage(String var) {
		ClusterEnUSPage oClusterEnUSPage = (ClusterEnUSPage)this;
		switch(var) {
			default:
				return super.obtainClusterEnUSGenPage(var);
		}
	}

	///////////////
	// attribute //
	///////////////

	@Override public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributeClusterEnUSPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeClusterEnUSPage(String var, Object val) {
		ClusterEnUSPage oClusterEnUSPage = (ClusterEnUSPage)this;
		switch(var) {
			default:
				return super.attributeClusterEnUSGenPage(var, val);
		}
	}

	/////////////
	// define //
	/////////////

	@Override public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineClusterEnUSPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineClusterEnUSPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterEnUSGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsClusterEnUSPage();
		super.htmlScripts();
	}

	public void htmlScriptsClusterEnUSPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptClusterEnUSPage();
		super.htmlScript();
	}

	public void htmlScriptClusterEnUSPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyClusterEnUSPage();
		super.htmlBody();
	}

	public void htmlBodyClusterEnUSPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlClusterEnUSPage();
		super.html();
	}

	public void htmlClusterEnUSPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaClusterEnUSPage();
		super.htmlMeta();
	}

	public void htmlMetaClusterEnUSPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesClusterEnUSPage();
		super.htmlStyles();
	}

	public void htmlStylesClusterEnUSPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleClusterEnUSPage();
		super.htmlStyle();
	}

	public void htmlStyleClusterEnUSPage() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode());
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof ClusterEnUSPage))
			return false;
		ClusterEnUSPage that = (ClusterEnUSPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ClusterEnUSPage {");
		sb.append(" }");
		return sb.toString();
	}
}
