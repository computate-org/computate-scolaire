package org.computate.scolaire.frFR.ecole;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.ecole.EcoleFrFRGenPage;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleFrFRPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleFrFRPageGen<DEV> extends EcoleFrFRGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcoleFrFRPage = false;

	public EcoleFrFRPage initLoinEcoleFrFRPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcoleFrFRPage) {
			dejaInitialiseEcoleFrFRPage = true;
			initLoinEcoleFrFRPage();
		}
		return (EcoleFrFRPage)this;
	}

	public void initLoinEcoleFrFRPage() {
		super.initLoinEcoleFrFRGenPage(requeteSite_);
		initEcoleFrFRPage();
	}

	public void initEcoleFrFRPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinEcoleFrFRPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleFrFRPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteEcoleFrFRGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteEcoleFrFRPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcoleFrFRPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcoleFrFRPage(String var) {
		EcoleFrFRPage oEcoleFrFRPage = (EcoleFrFRPage)this;
		switch(var) {
			default:
				return super.obtenirEcoleFrFRGenPage(var);
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
				o = attribuerEcoleFrFRPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcoleFrFRPage(String var, Object val) {
		EcoleFrFRPage oEcoleFrFRPage = (EcoleFrFRPage)this;
		switch(var) {
			default:
				return super.attribuerEcoleFrFRGenPage(var, val);
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
					o = definirEcoleFrFRPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcoleFrFRPage(String var, String val) {
		switch(var) {
			default:
				return super.definirEcoleFrFRGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEcoleFrFRPage();
		super.htmlScripts();
	}

	public void htmlScriptsEcoleFrFRPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleFrFRPage();
		super.htmlScript();
	}

	public void htmlScriptEcoleFrFRPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleFrFRPage();
		super.htmlBody();
	}

	public void htmlBodyEcoleFrFRPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEcoleFrFRPage();
		super.html();
	}

	public void htmlEcoleFrFRPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEcoleFrFRPage();
		super.htmlMeta();
	}

	public void htmlMetaEcoleFrFRPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEcoleFrFRPage();
		super.htmlStyles();
	}

	public void htmlStylesEcoleFrFRPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEcoleFrFRPage();
		super.htmlStyle();
	}

	public void htmlStyleEcoleFrFRPage() {
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
		if(!(o instanceof EcoleFrFRPage))
			return false;
		EcoleFrFRPage that = (EcoleFrFRPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleFrFRPage {");
		sb.append(" }");
		return sb.toString();
	}
}
