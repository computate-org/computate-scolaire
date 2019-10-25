package org.computate.scolaire.frFR.form.part;

import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.form.part.PartFormGenPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartFormPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PartFormPageGen<DEV> extends PartFormGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePartFormPage = false;

	public PartFormPage initLoinPartFormPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePartFormPage) {
			dejaInitialisePartFormPage = true;
			initLoinPartFormPage();
		}
		return (PartFormPage)this;
	}

	public void initLoinPartFormPage() {
		super.initLoinPartFormGenPage(requeteSite_);
		initPartFormPage();
	}

	public void initPartFormPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPartFormPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePartFormPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSitePartFormGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePartFormPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPartFormPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPartFormPage(String var) {
		PartFormPage oPartFormPage = (PartFormPage)this;
		switch(var) {
			default:
				return super.obtenirPartFormGenPage(var);
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
				o = attribuerPartFormPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPartFormPage(String var, Object val) {
		PartFormPage oPartFormPage = (PartFormPage)this;
		switch(var) {
			default:
				return super.attribuerPartFormGenPage(var, val);
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
					o = definirPartFormPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPartFormPage(String var, String val) {
		switch(var) {
			default:
				return super.definirPartFormGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPartFormPage();
		super.htmlScripts();
	}

	public void htmlScriptsPartFormPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPartFormPage();
		super.htmlScript();
	}

	public void htmlScriptPartFormPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPartFormPage();
		super.htmlBody();
	}

	public void htmlBodyPartFormPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPartFormPage();
		super.html();
	}

	public void htmlPartFormPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPartFormPage();
		super.htmlMeta();
	}

	public void htmlMetaPartFormPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPartFormPage();
		super.htmlStyles();
	}

	public void htmlStylesPartFormPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePartFormPage();
		super.htmlStyle();
	}

	public void htmlStylePartFormPage() {
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
		if(!(o instanceof PartFormPage))
			return false;
		PartFormPage that = (PartFormPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PartFormPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
