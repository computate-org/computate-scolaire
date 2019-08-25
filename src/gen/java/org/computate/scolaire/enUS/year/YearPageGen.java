package org.computate.scolaire.enUS.year;

import java.util.Objects;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.enUS.year.YearGenPage;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.YearPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class YearPageGen<DEV> extends YearGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedYearPage = false;

	public YearPage initDeepYearPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedYearPage) {
			alreadyInitializedYearPage = true;
			initDeepYearPage();
		}
		return (YearPage)this;
	}

	public void initDeepYearPage() {
		super.initDeepYearGenPage(siteRequest_);
		initYearPage();
	}

	public void initYearPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepYearPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestYearPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestYearGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestYearPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainYearPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainYearPage(String var) {
		YearPage oYearPage = (YearPage)this;
		switch(var) {
			default:
				return super.obtainYearGenPage(var);
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
				o = attributeYearPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeYearPage(String var, Object val) {
		YearPage oYearPage = (YearPage)this;
		switch(var) {
			default:
				return super.attributeYearGenPage(var, val);
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
					o = defineYearPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineYearPage(String var, String val) {
		switch(var) {
			default:
				return super.defineYearGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsYearPage();
		super.htmlScripts();
	}

	public void htmlScriptsYearPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptYearPage();
		super.htmlScript();
	}

	public void htmlScriptYearPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyYearPage();
		super.htmlBody();
	}

	public void htmlBodyYearPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlYearPage();
		super.html();
	}

	public void htmlYearPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaYearPage();
		super.htmlMeta();
	}

	public void htmlMetaYearPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesYearPage();
		super.htmlStyles();
	}

	public void htmlStylesYearPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleYearPage();
		super.htmlStyle();
	}

	public void htmlStyleYearPage() {
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
		if(!(o instanceof YearPage))
			return false;
		YearPage that = (YearPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("YearPage {");
		sb.append(" }");
		return sb.toString();
	}
}