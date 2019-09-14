package org.computate.scolaire.enUS.season;

import java.util.Objects;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.season.SeasonGenPage;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SeasonPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SeasonPageGen<DEV> extends SeasonGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSeasonPage = false;

	public SeasonPage initDeepSeasonPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSeasonPage) {
			alreadyInitializedSeasonPage = true;
			initDeepSeasonPage();
		}
		return (SeasonPage)this;
	}

	public void initDeepSeasonPage() {
		super.initDeepSeasonGenPage(siteRequest_);
		initSeasonPage();
	}

	public void initSeasonPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSeasonPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSeasonPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestSeasonGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSeasonPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSeasonPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSeasonPage(String var) {
		SeasonPage oSeasonPage = (SeasonPage)this;
		switch(var) {
			default:
				return super.obtainSeasonGenPage(var);
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
				o = attributeSeasonPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSeasonPage(String var, Object val) {
		SeasonPage oSeasonPage = (SeasonPage)this;
		switch(var) {
			default:
				return super.attributeSeasonGenPage(var, val);
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
					o = defineSeasonPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSeasonPage(String var, String val) {
		switch(var) {
			default:
				return super.defineSeasonGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSeasonPage();
		super.htmlScripts();
	}

	public void htmlScriptsSeasonPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSeasonPage();
		super.htmlScript();
	}

	public void htmlScriptSeasonPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySeasonPage();
		super.htmlBody();
	}

	public void htmlBodySeasonPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSeasonPage();
		super.html();
	}

	public void htmlSeasonPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSeasonPage();
		super.htmlMeta();
	}

	public void htmlMetaSeasonPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSeasonPage();
		super.htmlStyles();
	}

	public void htmlStylesSeasonPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSeasonPage();
		super.htmlStyle();
	}

	public void htmlStyleSeasonPage() {
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
		if(!(o instanceof SeasonPage))
			return false;
		SeasonPage that = (SeasonPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SeasonPage { ");
		sb.append(" }");
		return sb.toString();
	}
}