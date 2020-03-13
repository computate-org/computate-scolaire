package org.computate.scolaire.frFR.saison;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.saison.SaisonGenPage;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SaisonPageGen<DEV> extends SaisonGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSaisonPage = false;

	public SaisonPage initLoinSaisonPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSaisonPage) {
			dejaInitialiseSaisonPage = true;
			initLoinSaisonPage();
		}
		return (SaisonPage)this;
	}

	public void initLoinSaisonPage() {
		initSaisonPage();
		super.initLoinSaisonGenPage(requeteSite_);
	}

	public void initSaisonPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSaisonPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSaisonPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteSaisonGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteSaisonPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSaisonPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSaisonPage(String var) {
		SaisonPage oSaisonPage = (SaisonPage)this;
		switch(var) {
			default:
				return super.obtenirSaisonGenPage(var);
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
				o = attribuerSaisonPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSaisonPage(String var, Object val) {
		SaisonPage oSaisonPage = (SaisonPage)this;
		switch(var) {
			default:
				return super.attribuerSaisonGenPage(var, val);
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
					o = definirSaisonPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSaisonPage(String var, String val) {
		switch(var) {
			default:
				return super.definirSaisonGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSaisonPage();
		super.htmlScripts();
	}

	public void htmlScriptsSaisonPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSaisonPage();
		super.htmlScript();
	}

	public void htmlScriptSaisonPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySaisonPage();
		super.htmlBody();
	}

	public void htmlBodySaisonPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSaisonPage();
		super.html();
	}

	public void htmlSaisonPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSaisonPage();
		super.htmlMeta();
	}

	public void htmlMetaSaisonPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSaisonPage();
		super.htmlStyles();
	}

	public void htmlStylesSaisonPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSaisonPage();
		super.htmlStyle();
	}

	public void htmlStyleSaisonPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiSaisonPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof SaisonPage) {
			SaisonPage original = (SaisonPage)o;
			super.requeteApiSaisonGenPage();
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
		if(!(o instanceof SaisonPage))
			return false;
		SaisonPage that = (SaisonPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SaisonPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
