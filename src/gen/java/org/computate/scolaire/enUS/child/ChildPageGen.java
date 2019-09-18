package org.computate.scolaire.enUS.child;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.child.ChildGenPage;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.child.ChildPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ChildPageGen<DEV> extends ChildGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseChildPage = false;

	public ChildPage initLoinChildPage(SiteRequestEnUS requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseChildPage) {
			dejaInitialiseChildPage = true;
			initLoinChildPage();
		}
		return (ChildPage)this;
	}

	public void initLoinChildPage() {
		super.initLoinChildGenPage(requeteSite_);
		initChildPage();
	}

	public void initChildPage() {
	}

	@Override public void initLoinPourClasse(SiteRequestEnUS requeteSite_) {
		initLoinChildPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteChildPage(SiteRequestEnUS requeteSite_) {
			super.requeteSiteChildGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(SiteRequestEnUS requeteSite_) {
		requeteSiteChildPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirChildPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirChildPage(String var) {
		ChildPage oChildPage = (ChildPage)this;
		switch(var) {
			default:
				return super.obtenirChildGenPage(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerChildPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerChildPage(String var, Object val) {
		ChildPage oChildPage = (ChildPage)this;
		switch(var) {
			default:
				return super.attribuerChildGenPage(var, val);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirChildPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirChildPage(String var, String val) {
		switch(var) {
			default:
				return super.definirChildGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsChildPage();
		super.htmlScripts();
	}

	public void htmlScriptsChildPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptChildPage();
		super.htmlScript();
	}

	public void htmlScriptChildPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyChildPage();
		super.htmlBody();
	}

	public void htmlBodyChildPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlChildPage();
		super.html();
	}

	public void htmlChildPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaChildPage();
		super.htmlMeta();
	}

	public void htmlMetaChildPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesChildPage();
		super.htmlStyles();
	}

	public void htmlStylesChildPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleChildPage();
		super.htmlStyle();
	}

	public void htmlStyleChildPage() {
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
		if(!(o instanceof ChildPage))
			return false;
		ChildPage that = (ChildPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ChildPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
