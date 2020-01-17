package org.computate.scolaire.frFR.session;

import org.computate.scolaire.frFR.requete.patch.RequetePatch;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.session.SessionGenPage;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SessionPageGen<DEV> extends SessionGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSessionPage = false;

	public SessionPage initLoinSessionPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSessionPage) {
			dejaInitialiseSessionPage = true;
			initLoinSessionPage();
		}
		return (SessionPage)this;
	}

	public void initLoinSessionPage() {
		initSessionPage();
		super.initLoinSessionGenPage(requeteSite_);
	}

	public void initSessionPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSessionPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSessionPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteSessionGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteSessionPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSessionPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSessionPage(String var) {
		SessionPage oSessionPage = (SessionPage)this;
		switch(var) {
			default:
				return super.obtenirSessionGenPage(var);
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
				o = attribuerSessionPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSessionPage(String var, Object val) {
		SessionPage oSessionPage = (SessionPage)this;
		switch(var) {
			default:
				return super.attribuerSessionGenPage(var, val);
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
					o = definirSessionPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSessionPage(String var, String val) {
		switch(var) {
			default:
				return super.definirSessionGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSessionPage();
		super.htmlScripts();
	}

	public void htmlScriptsSessionPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSessionPage();
		super.htmlScript();
	}

	public void htmlScriptSessionPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySessionPage();
		super.htmlBody();
	}

	public void htmlBodySessionPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSessionPage();
		super.html();
	}

	public void htmlSessionPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSessionPage();
		super.htmlMeta();
	}

	public void htmlMetaSessionPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSessionPage();
		super.htmlStyles();
	}

	public void htmlStylesSessionPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSessionPage();
		super.htmlStyle();
	}

	public void htmlStyleSessionPage() {
	}

	//////////////////
	// requetePatch //
	//////////////////

	public void requetePatchSessionPage() {
		RequetePatch requetePatch = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequetePatch_).orElse(null);
		SessionPage original = (SessionPage)Optional.ofNullable(requetePatch).map(RequetePatch::getOriginal).orElse(null);
		if(original != null) {
			super.requetePatchSessionGenPage();
		}
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
		if(!(o instanceof SessionPage))
			return false;
		SessionPage that = (SessionPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SessionPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
