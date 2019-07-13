package org.computate.scolaire.frFR.ecole;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecole.EcoleScolaireGenPage;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleScolairePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleScolairePageGen<DEV> extends EcoleScolaireGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcoleScolairePage = false;

	public EcoleScolairePage initLoinEcoleScolairePage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcoleScolairePage) {
			dejaInitialiseEcoleScolairePage = true;
			initLoinEcoleScolairePage();
		}
		return (EcoleScolairePage)this;
	}

	public void initLoinEcoleScolairePage() {
		super.initLoinEcoleScolaireGenPage(requeteSite_);
		initEcoleScolairePage();
	}

	public void initEcoleScolairePage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinEcoleScolairePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleScolairePage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteEcoleScolaireGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteEcoleScolairePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcoleScolairePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcoleScolairePage(String var) {
		EcoleScolairePage oEcoleScolairePage = (EcoleScolairePage)this;
		switch(var) {
			default:
				return super.obtenirEcoleScolaireGenPage(var);
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
				o = attribuerEcoleScolairePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcoleScolairePage(String var, Object val) {
		EcoleScolairePage oEcoleScolairePage = (EcoleScolairePage)this;
		switch(var) {
			default:
				return super.attribuerEcoleScolaireGenPage(var, val);
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
					o = definirEcoleScolairePage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcoleScolairePage(String var, String val) {
		switch(var) {
			default:
				return super.definirEcoleScolaireGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEcoleScolairePage();
		super.htmlScripts();
	}

	public void htmlScriptsEcoleScolairePage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleScolairePage();
		super.htmlScript();
	}

	public void htmlScriptEcoleScolairePage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleScolairePage();
		super.htmlBody();
	}

	public void htmlBodyEcoleScolairePage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEcoleScolairePage();
		super.html();
	}

	public void htmlEcoleScolairePage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEcoleScolairePage();
		super.htmlMeta();
	}

	public void htmlMetaEcoleScolairePage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEcoleScolairePage();
		super.htmlStyles();
	}

	public void htmlStylesEcoleScolairePage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEcoleScolairePage();
		super.htmlStyle();
	}

	public void htmlStyleEcoleScolairePage() {
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
		if(!(o instanceof EcoleScolairePage))
			return false;
		EcoleScolairePage that = (EcoleScolairePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleScolairePage {");
		sb.append(" }");
		return sb.toString();
	}
}
