package org.computate.scolaire.frFR.annee;

import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.annee.AnneeGenPage;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AnneePageGen<DEV> extends AnneeGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAnneePage = false;

	public AnneePage initLoinAnneePage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAnneePage) {
			dejaInitialiseAnneePage = true;
			initLoinAnneePage();
		}
		return (AnneePage)this;
	}

	public void initLoinAnneePage() {
		initAnneePage();
		super.initLoinAnneeGenPage(requeteSite_);
	}

	public void initAnneePage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAnneePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAnneePage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteAnneeGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAnneePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAnneePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAnneePage(String var) {
		AnneePage oAnneePage = (AnneePage)this;
		switch(var) {
			default:
				return super.obtenirAnneeGenPage(var);
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
				o = attribuerAnneePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAnneePage(String var, Object val) {
		AnneePage oAnneePage = (AnneePage)this;
		switch(var) {
			default:
				return super.attribuerAnneeGenPage(var, val);
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
					o = definirAnneePage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAnneePage(String var, String val) {
		switch(var) {
			default:
				return super.definirAnneeGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsAnneePage();
		super.htmlScripts();
	}

	public void htmlScriptsAnneePage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptAnneePage();
		super.htmlScript();
	}

	public void htmlScriptAnneePage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyAnneePage();
		super.htmlBody();
	}

	public void htmlBodyAnneePage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlAnneePage();
		super.html();
	}

	public void htmlAnneePage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaAnneePage();
		super.htmlMeta();
	}

	public void htmlMetaAnneePage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesAnneePage();
		super.htmlStyles();
	}

	public void htmlStylesAnneePage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleAnneePage();
		super.htmlStyle();
	}

	public void htmlStyleAnneePage() {
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
		if(!(o instanceof AnneePage))
			return false;
		AnneePage that = (AnneePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AnneePage { ");
		sb.append(" }");
		return sb.toString();
	}
}
