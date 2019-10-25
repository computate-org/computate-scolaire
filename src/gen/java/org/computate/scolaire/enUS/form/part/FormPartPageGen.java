package org.computate.scolaire.enUS.form.part;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.form.part.FormPartGenPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPartPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class FormPartPageGen<DEV> extends FormPartGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedFormPartPage = false;

	public FormPartPage initDeepFormPartPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedFormPartPage) {
			alreadyInitializedFormPartPage = true;
			initDeepFormPartPage();
		}
		return (FormPartPage)this;
	}

	public void initDeepFormPartPage() {
		super.initDeepFormPartGenPage(siteRequest_);
		initFormPartPage();
	}

	public void initFormPartPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepFormPartPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestFormPartPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestFormPartGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestFormPartPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainFormPartPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainFormPartPage(String var) {
		FormPartPage oFormPartPage = (FormPartPage)this;
		switch(var) {
			default:
				return super.obtainFormPartGenPage(var);
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
				o = attributeFormPartPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeFormPartPage(String var, Object val) {
		FormPartPage oFormPartPage = (FormPartPage)this;
		switch(var) {
			default:
				return super.attributeFormPartGenPage(var, val);
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
					o = defineFormPartPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineFormPartPage(String var, String val) {
		switch(var) {
			default:
				return super.defineFormPartGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsFormPartPage();
		super.htmlScripts();
	}

	public void htmlScriptsFormPartPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptFormPartPage();
		super.htmlScript();
	}

	public void htmlScriptFormPartPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyFormPartPage();
		super.htmlBody();
	}

	public void htmlBodyFormPartPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlFormPartPage();
		super.html();
	}

	public void htmlFormPartPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaFormPartPage();
		super.htmlMeta();
	}

	public void htmlMetaFormPartPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesFormPartPage();
		super.htmlStyles();
	}

	public void htmlStylesFormPartPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleFormPartPage();
		super.htmlStyle();
	}

	public void htmlStyleFormPartPage() {
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
		if(!(o instanceof FormPartPage))
			return false;
		FormPartPage that = (FormPartPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("FormPartPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
