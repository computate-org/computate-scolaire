package org.computate.scolaire.frFR.utilisateur;

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
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteGenPage;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSitePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSitePageGen<DEV> extends UtilisateurSiteGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseUtilisateurSitePage = false;

	public UtilisateurSitePage initLoinUtilisateurSitePage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseUtilisateurSitePage) {
			dejaInitialiseUtilisateurSitePage = true;
			initLoinUtilisateurSitePage();
		}
		return (UtilisateurSitePage)this;
	}

	public void initLoinUtilisateurSitePage() {
		super.initLoinUtilisateurSiteGenPage(requeteSite_);
		initUtilisateurSitePage();
	}

	public void initUtilisateurSitePage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinUtilisateurSitePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteUtilisateurSitePage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteUtilisateurSiteGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteUtilisateurSitePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirUtilisateurSitePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirUtilisateurSitePage(String var) {
		UtilisateurSitePage oUtilisateurSitePage = (UtilisateurSitePage)this;
		switch(var) {
			default:
				return super.obtenirUtilisateurSiteGenPage(var);
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
				o = attribuerUtilisateurSitePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerUtilisateurSitePage(String var, Object val) {
		UtilisateurSitePage oUtilisateurSitePage = (UtilisateurSitePage)this;
		switch(var) {
			default:
				return super.attribuerUtilisateurSiteGenPage(var, val);
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
					o = definirUtilisateurSitePage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirUtilisateurSitePage(String var, String val) {
		switch(var) {
			default:
				return super.definirUtilisateurSiteGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsUtilisateurSitePage();
		super.htmlScripts();
	}

	public void htmlScriptsUtilisateurSitePage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptUtilisateurSitePage();
		super.htmlScript();
	}

	public void htmlScriptUtilisateurSitePage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyUtilisateurSitePage();
		super.htmlBody();
	}

	public void htmlBodyUtilisateurSitePage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlUtilisateurSitePage();
		super.html();
	}

	public void htmlUtilisateurSitePage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaUtilisateurSitePage();
		super.htmlMeta();
	}

	public void htmlMetaUtilisateurSitePage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesUtilisateurSitePage();
		super.htmlStyles();
	}

	public void htmlStylesUtilisateurSitePage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleUtilisateurSitePage();
		super.htmlStyle();
	}

	public void htmlStyleUtilisateurSitePage() {
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
		if(!(o instanceof UtilisateurSitePage))
			return false;
		UtilisateurSitePage that = (UtilisateurSitePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSitePage { ");
		sb.append(" }");
		return sb.toString();
	}
}
