package org.computate.scolaire.enUS.enrollment;

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
import org.computate.scolaire.enUS.enrollment.EnrollmentGenPage;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentPageGen<DEV> extends EnrollmentGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEnrollmentPage = false;

	public EnrollmentPage initLoinEnrollmentPage(SiteRequestEnUS requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEnrollmentPage) {
			dejaInitialiseEnrollmentPage = true;
			initLoinEnrollmentPage();
		}
		return (EnrollmentPage)this;
	}

	public void initLoinEnrollmentPage() {
		super.initLoinEnrollmentGenPage(requeteSite_);
		initEnrollmentPage();
	}

	public void initEnrollmentPage() {
	}

	@Override public void initLoinPourClasse(SiteRequestEnUS requeteSite_) {
		initLoinEnrollmentPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEnrollmentPage(SiteRequestEnUS requeteSite_) {
			super.requeteSiteEnrollmentGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(SiteRequestEnUS requeteSite_) {
		requeteSiteEnrollmentPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEnrollmentPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEnrollmentPage(String var) {
		EnrollmentPage oEnrollmentPage = (EnrollmentPage)this;
		switch(var) {
			default:
				return super.obtenirEnrollmentGenPage(var);
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
				o = attribuerEnrollmentPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEnrollmentPage(String var, Object val) {
		EnrollmentPage oEnrollmentPage = (EnrollmentPage)this;
		switch(var) {
			default:
				return super.attribuerEnrollmentGenPage(var, val);
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
					o = definirEnrollmentPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEnrollmentPage(String var, String val) {
		switch(var) {
			default:
				return super.definirEnrollmentGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentPage();
		super.html();
	}

	public void htmlEnrollmentPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentPage() {
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
		if(!(o instanceof EnrollmentPage))
			return false;
		EnrollmentPage that = (EnrollmentPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
