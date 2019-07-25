package org.computate.scolaire.enUS.school;

import java.util.Objects;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.school.EcoleEnUSGenPage;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.EcoleEnUSPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleEnUSPageGen<DEV> extends EcoleEnUSGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEcoleEnUSPage = false;

	public EcoleEnUSPage initDeepEcoleEnUSPage(SiteRequestEnUS siteRequest_) {
		if(!alreadyInitializedEcoleEnUSPage) {
			alreadyInitializedEcoleEnUSPage = true;
			initDeepEcoleEnUSPage();
		}
		return (EcoleEnUSPage)this;
	}

	public void initDeepEcoleEnUSPage() {
		super.initDeepEcoleEnUSGenPage(siteRequest_);
		initEcoleEnUSPage();
	}

	public void initEcoleEnUSPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEcoleEnUSPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEcoleEnUSPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEcoleEnUSPage(String var) {
		EcoleEnUSPage oEcoleEnUSPage = (EcoleEnUSPage)this;
		switch(var) {
			default:
				return super.obtainEcoleEnUSGenPage(var);
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
				o = attributeEcoleEnUSPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEcoleEnUSPage(String var, Object val) {
		EcoleEnUSPage oEcoleEnUSPage = (EcoleEnUSPage)this;
		switch(var) {
			default:
				return super.attributeEcoleEnUSGenPage(var, val);
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
					o = defineEcoleEnUSPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEcoleEnUSPage(String var, String val) {
		switch(var) {
			default:
				return super.defineEcoleEnUSGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEcoleEnUSPage();
		super.htmlScripts();
	}

	public void htmlScriptsEcoleEnUSPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleEnUSPage();
		super.htmlScript();
	}

	public void htmlScriptEcoleEnUSPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleEnUSPage();
		super.htmlBody();
	}

	public void htmlBodyEcoleEnUSPage() {
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
		if(!(o instanceof EcoleEnUSPage))
			return false;
		EcoleEnUSPage that = (EcoleEnUSPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleEnUSPage {");
		sb.append(" }");
		return sb.toString();
	}
}
