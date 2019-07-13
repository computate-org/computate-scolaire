package org.computate.scolaire.frFR.cluster;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.cluster.ClusterFrFRGenPage;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.ClusterFrFRPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ClusterFrFRPageGen<DEV> extends ClusterFrFRGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseClusterFrFRPage = false;

	public ClusterFrFRPage initLoinClusterFrFRPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseClusterFrFRPage) {
			dejaInitialiseClusterFrFRPage = true;
			initLoinClusterFrFRPage();
		}
		return (ClusterFrFRPage)this;
	}

	public void initLoinClusterFrFRPage() {
		super.initLoinClusterFrFRGenPage(requeteSite_);
		initClusterFrFRPage();
	}

	public void initClusterFrFRPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinClusterFrFRPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteClusterFrFRPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterFrFRGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteClusterFrFRPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirClusterFrFRPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirClusterFrFRPage(String var) {
		ClusterFrFRPage oClusterFrFRPage = (ClusterFrFRPage)this;
		switch(var) {
			default:
				return super.obtenirClusterFrFRGenPage(var);
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
				o = attribuerClusterFrFRPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerClusterFrFRPage(String var, Object val) {
		ClusterFrFRPage oClusterFrFRPage = (ClusterFrFRPage)this;
		switch(var) {
			default:
				return super.attribuerClusterFrFRGenPage(var, val);
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
					o = definirClusterFrFRPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirClusterFrFRPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterFrFRGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsClusterFrFRPage();
		super.htmlScripts();
	}

	public void htmlScriptsClusterFrFRPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptClusterFrFRPage();
		super.htmlScript();
	}

	public void htmlScriptClusterFrFRPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyClusterFrFRPage();
		super.htmlBody();
	}

	public void htmlBodyClusterFrFRPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlClusterFrFRPage();
		super.html();
	}

	public void htmlClusterFrFRPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaClusterFrFRPage();
		super.htmlMeta();
	}

	public void htmlMetaClusterFrFRPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesClusterFrFRPage();
		super.htmlStyles();
	}

	public void htmlStylesClusterFrFRPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleClusterFrFRPage();
		super.htmlStyle();
	}

	public void htmlStyleClusterFrFRPage() {
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
		if(!(o instanceof ClusterFrFRPage))
			return false;
		ClusterFrFRPage that = (ClusterFrFRPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ClusterFrFRPage {");
		sb.append(" }");
		return sb.toString();
	}
}
