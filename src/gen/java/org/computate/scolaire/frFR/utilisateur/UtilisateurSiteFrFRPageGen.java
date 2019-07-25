package org.computate.scolaire.frFR.utilisateur;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenPage;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSiteFrFRPageGen<DEV> extends UtilisateurSiteFrFRGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseUtilisateurSiteFrFRPage = false;

	public UtilisateurSiteFrFRPage initLoinUtilisateurSiteFrFRPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseUtilisateurSiteFrFRPage) {
			dejaInitialiseUtilisateurSiteFrFRPage = true;
			initLoinUtilisateurSiteFrFRPage();
		}
		return (UtilisateurSiteFrFRPage)this;
	}

	public void initLoinUtilisateurSiteFrFRPage() {
		super.initLoinUtilisateurSiteFrFRGenPage(requeteSite_);
		initUtilisateurSiteFrFRPage();
	}

	public void initUtilisateurSiteFrFRPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinUtilisateurSiteFrFRPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteUtilisateurSiteFrFRPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteUtilisateurSiteFrFRGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteUtilisateurSiteFrFRPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirUtilisateurSiteFrFRPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirUtilisateurSiteFrFRPage(String var) {
		UtilisateurSiteFrFRPage oUtilisateurSiteFrFRPage = (UtilisateurSiteFrFRPage)this;
		switch(var) {
			default:
				return super.obtenirUtilisateurSiteFrFRGenPage(var);
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
				o = attribuerUtilisateurSiteFrFRPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerUtilisateurSiteFrFRPage(String var, Object val) {
		UtilisateurSiteFrFRPage oUtilisateurSiteFrFRPage = (UtilisateurSiteFrFRPage)this;
		switch(var) {
			default:
				return super.attribuerUtilisateurSiteFrFRGenPage(var, val);
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
					o = definirUtilisateurSiteFrFRPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirUtilisateurSiteFrFRPage(String var, String val) {
		switch(var) {
			default:
				return super.definirUtilisateurSiteFrFRGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsUtilisateurSiteFrFRPage();
		super.htmlScripts();
	}

	public void htmlScriptsUtilisateurSiteFrFRPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptUtilisateurSiteFrFRPage();
		super.htmlScript();
	}

	public void htmlScriptUtilisateurSiteFrFRPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyUtilisateurSiteFrFRPage();
		super.htmlBody();
	}

	public void htmlBodyUtilisateurSiteFrFRPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlUtilisateurSiteFrFRPage();
		super.html();
	}

	public void htmlUtilisateurSiteFrFRPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaUtilisateurSiteFrFRPage();
		super.htmlMeta();
	}

	public void htmlMetaUtilisateurSiteFrFRPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesUtilisateurSiteFrFRPage();
		super.htmlStyles();
	}

	public void htmlStylesUtilisateurSiteFrFRPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleUtilisateurSiteFrFRPage();
		super.htmlStyle();
	}

	public void htmlStyleUtilisateurSiteFrFRPage() {
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
		if(!(o instanceof UtilisateurSiteFrFRPage))
			return false;
		UtilisateurSiteFrFRPage that = (UtilisateurSiteFrFRPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSiteFrFRPage {");
		sb.append(" }");
		return sb.toString();
	}
}
