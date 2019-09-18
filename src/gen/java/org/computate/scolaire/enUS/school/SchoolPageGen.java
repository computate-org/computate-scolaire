package org.computate.scolaire.enUS.school;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.school.SchoolGenPage;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.school.SchoolPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolPageGen<DEV> extends SchoolGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSchoolPage = false;

	public SchoolPage initLoinSchoolPage(SiteRequestEnUS requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSchoolPage) {
			dejaInitialiseSchoolPage = true;
			initLoinSchoolPage();
		}
		return (SchoolPage)this;
	}

	public void initLoinSchoolPage() {
		super.initLoinSchoolGenPage(requeteSite_);
		initSchoolPage();
	}

	public void initSchoolPage() {
	}

	@Override public void initLoinPourClasse(SiteRequestEnUS requeteSite_) {
		initLoinSchoolPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSchoolPage(SiteRequestEnUS requeteSite_) {
			super.requeteSiteSchoolGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(SiteRequestEnUS requeteSite_) {
		requeteSiteSchoolPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSchoolPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSchoolPage(String var) {
		SchoolPage oSchoolPage = (SchoolPage)this;
		switch(var) {
			default:
				return super.obtenirSchoolGenPage(var);
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
				o = attribuerSchoolPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSchoolPage(String var, Object val) {
		SchoolPage oSchoolPage = (SchoolPage)this;
		switch(var) {
			default:
				return super.attribuerSchoolGenPage(var, val);
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
					o = definirSchoolPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSchoolPage(String var, String val) {
		switch(var) {
			default:
				return super.definirSchoolGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSchoolPage();
		super.htmlScripts();
	}

	public void htmlScriptsSchoolPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSchoolPage();
		super.htmlScript();
	}

	public void htmlScriptSchoolPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySchoolPage();
		super.htmlBody();
	}

	public void htmlBodySchoolPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSchoolPage();
		super.html();
	}

	public void htmlSchoolPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSchoolPage();
		super.htmlMeta();
	}

	public void htmlMetaSchoolPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSchoolPage();
		super.htmlStyles();
	}

	public void htmlStylesSchoolPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSchoolPage();
		super.htmlStyle();
	}

	public void htmlStyleSchoolPage() {
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
		if(!(o instanceof SchoolPage))
			return false;
		SchoolPage that = (SchoolPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
