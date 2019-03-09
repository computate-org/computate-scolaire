package org.computate.frFR.scolaire.utilisateur;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSiteGenPage;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSitePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSitePageGen<DEV> extends UtilisateurSiteGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseUtilisateurSitePage = false;

	public UtilisateurSitePage initLoinUtilisateurSitePage(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseUtilisateurSitePage) {
			dejaInitialiseUtilisateurSitePage = true;
			initLoinUtilisateurSitePage();
		}
		return (UtilisateurSitePage)this;
	}

	public void initLoinUtilisateurSitePage() {
		initUtilisateurSitePage();
	}

	public void initUtilisateurSitePage() {
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinUtilisateurSitePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteUtilisateurSitePage(RequeteSite requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteUtilisateurSitePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
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
	public Object obtenirUtilisateurSitePage(String var) throws Exception {
		UtilisateurSitePage oUtilisateurSitePage = (UtilisateurSitePage)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) {
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
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String val) {
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
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash();
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
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UtilisateurSitePage {");
		sb.append(" }");
		return sb.toString();
	}
}
