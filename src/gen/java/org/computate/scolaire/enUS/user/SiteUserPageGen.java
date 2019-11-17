package org.computate.scolaire.enUS.user;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import org.computate.scolaire.enUS.user.SiteUserGenPage;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteUserPageGen<DEV> extends SiteUserGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteUserPage = false;

	public SiteUserPage initDeepSiteUserPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSiteUserPage) {
			alreadyInitializedSiteUserPage = true;
			initDeepSiteUserPage();
		}
		return (SiteUserPage)this;
	}

	public void initDeepSiteUserPage() {
		initSiteUserPage();
		super.initDeepSiteUserGenPage(siteRequest_);
	}

	public void initSiteUserPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteUserPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteUserPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestSiteUserGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSiteUserPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteUserPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSiteUserPage(String var) {
		SiteUserPage oSiteUserPage = (SiteUserPage)this;
		switch(var) {
			default:
				return super.obtainSiteUserGenPage(var);
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
				o = attributeSiteUserPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSiteUserPage(String var, Object val) {
		SiteUserPage oSiteUserPage = (SiteUserPage)this;
		switch(var) {
			default:
				return super.attributeSiteUserGenPage(var, val);
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
					o = defineSiteUserPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSiteUserPage(String var, String val) {
		switch(var) {
			default:
				return super.defineSiteUserGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSiteUserPage();
		super.htmlScripts();
	}

	public void htmlScriptsSiteUserPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSiteUserPage();
		super.htmlScript();
	}

	public void htmlScriptSiteUserPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySiteUserPage();
		super.htmlBody();
	}

	public void htmlBodySiteUserPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSiteUserPage();
		super.html();
	}

	public void htmlSiteUserPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSiteUserPage();
		super.htmlMeta();
	}

	public void htmlMetaSiteUserPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSiteUserPage();
		super.htmlStyles();
	}

	public void htmlStylesSiteUserPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSiteUserPage();
		super.htmlStyle();
	}

	public void htmlStyleSiteUserPage() {
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
		if(!(o instanceof SiteUserPage))
			return false;
		SiteUserPage that = (SiteUserPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SiteUserPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
