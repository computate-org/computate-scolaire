package org.computate.scolaire.enUS.user;

import java.util.Objects;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.user.UtilisateurSiteEnUSGenPage;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.UtilisateurSiteEnUSPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSiteEnUSPageGen<DEV> extends UtilisateurSiteEnUSGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedUtilisateurSiteEnUSPage = false;

	public UtilisateurSiteEnUSPage initDeepUtilisateurSiteEnUSPage(SiteRequestEnUS siteRequest_) {
		if(!alreadyInitializedUtilisateurSiteEnUSPage) {
			alreadyInitializedUtilisateurSiteEnUSPage = true;
			initDeepUtilisateurSiteEnUSPage();
		}
		return (UtilisateurSiteEnUSPage)this;
	}

	public void initDeepUtilisateurSiteEnUSPage() {
		super.initDeepUtilisateurSiteEnUSGenPage(siteRequest_);
		initUtilisateurSiteEnUSPage();
	}

	public void initUtilisateurSiteEnUSPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepUtilisateurSiteEnUSPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainUtilisateurSiteEnUSPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainUtilisateurSiteEnUSPage(String var) {
		UtilisateurSiteEnUSPage oUtilisateurSiteEnUSPage = (UtilisateurSiteEnUSPage)this;
		switch(var) {
			default:
				return super.obtainUtilisateurSiteEnUSGenPage(var);
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
				o = attributeUtilisateurSiteEnUSPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeUtilisateurSiteEnUSPage(String var, Object val) {
		UtilisateurSiteEnUSPage oUtilisateurSiteEnUSPage = (UtilisateurSiteEnUSPage)this;
		switch(var) {
			default:
				return super.attributeUtilisateurSiteEnUSGenPage(var, val);
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
					o = defineUtilisateurSiteEnUSPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineUtilisateurSiteEnUSPage(String var, String val) {
		switch(var) {
			default:
				return super.defineUtilisateurSiteEnUSGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsUtilisateurSiteEnUSPage();
		super.htmlScripts();
	}

	public void htmlScriptsUtilisateurSiteEnUSPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptUtilisateurSiteEnUSPage();
		super.htmlScript();
	}

	public void htmlScriptUtilisateurSiteEnUSPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyUtilisateurSiteEnUSPage();
		super.htmlBody();
	}

	public void htmlBodyUtilisateurSiteEnUSPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlUtilisateurSiteEnUSPage();
		super.html();
	}

	public void htmlUtilisateurSiteEnUSPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaUtilisateurSiteEnUSPage();
		super.htmlMeta();
	}

	public void htmlMetaUtilisateurSiteEnUSPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesUtilisateurSiteEnUSPage();
		super.htmlStyles();
	}

	public void htmlStylesUtilisateurSiteEnUSPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleUtilisateurSiteEnUSPage();
		super.htmlStyle();
	}

	public void htmlStyleUtilisateurSiteEnUSPage() {
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
		if(!(o instanceof UtilisateurSiteEnUSPage))
			return false;
		UtilisateurSiteEnUSPage that = (UtilisateurSiteEnUSPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSiteEnUSPage {");
		sb.append(" }");
		return sb.toString();
	}
}
