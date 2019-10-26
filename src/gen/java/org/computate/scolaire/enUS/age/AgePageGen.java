package org.computate.scolaire.enUS.age;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.age.AgeGenPage;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.age.AgePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AgePageGen<DEV> extends AgeGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedAgePage = false;

	public AgePage initDeepAgePage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedAgePage) {
			alreadyInitializedAgePage = true;
			initDeepAgePage();
		}
		return (AgePage)this;
	}

	public void initDeepAgePage() {
		initAgePage();
		super.initDeepAgeGenPage(siteRequest_);
	}

	public void initAgePage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepAgePage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAgePage(SiteRequestEnUS siteRequest_) {
			super.siteRequestAgeGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestAgePage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAgePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainAgePage(String var) {
		AgePage oAgePage = (AgePage)this;
		switch(var) {
			default:
				return super.obtainAgeGenPage(var);
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
				o = attributeAgePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeAgePage(String var, Object val) {
		AgePage oAgePage = (AgePage)this;
		switch(var) {
			default:
				return super.attributeAgeGenPage(var, val);
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
					o = defineAgePage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineAgePage(String var, String val) {
		switch(var) {
			default:
				return super.defineAgeGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsAgePage();
		super.htmlScripts();
	}

	public void htmlScriptsAgePage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptAgePage();
		super.htmlScript();
	}

	public void htmlScriptAgePage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyAgePage();
		super.htmlBody();
	}

	public void htmlBodyAgePage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlAgePage();
		super.html();
	}

	public void htmlAgePage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaAgePage();
		super.htmlMeta();
	}

	public void htmlMetaAgePage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesAgePage();
		super.htmlStyles();
	}

	public void htmlStylesAgePage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleAgePage();
		super.htmlStyle();
	}

	public void htmlStyleAgePage() {
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
		if(!(o instanceof AgePage))
			return false;
		AgePage that = (AgePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AgePage { ");
		sb.append(" }");
		return sb.toString();
	}
}
