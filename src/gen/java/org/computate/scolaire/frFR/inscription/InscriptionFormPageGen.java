package org.computate.scolaire.frFR.inscription;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.inscription.InscriptionFormGenPage;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionFormPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class InscriptionFormPageGen<DEV> extends InscriptionFormGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseInscriptionFormPage = false;

	public InscriptionFormPage initLoinInscriptionFormPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseInscriptionFormPage) {
			dejaInitialiseInscriptionFormPage = true;
			initLoinInscriptionFormPage();
		}
		return (InscriptionFormPage)this;
	}

	public void initLoinInscriptionFormPage() {
		initInscriptionFormPage();
		super.initLoinInscriptionFormGenPage(requeteSite_);
	}

	public void initInscriptionFormPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinInscriptionFormPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteInscriptionFormPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteInscriptionFormGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteInscriptionFormPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirInscriptionFormPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirInscriptionFormPage(String var) {
		InscriptionFormPage oInscriptionFormPage = (InscriptionFormPage)this;
		switch(var) {
			default:
				return super.obtenirInscriptionFormGenPage(var);
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
				o = attribuerInscriptionFormPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerInscriptionFormPage(String var, Object val) {
		InscriptionFormPage oInscriptionFormPage = (InscriptionFormPage)this;
		switch(var) {
			default:
				return super.attribuerInscriptionFormGenPage(var, val);
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
					o = definirInscriptionFormPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirInscriptionFormPage(String var, String val) {
		switch(var) {
			default:
				return super.definirInscriptionFormGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsInscriptionFormPage();
		super.htmlScripts();
	}

	public void htmlScriptsInscriptionFormPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptInscriptionFormPage();
		super.htmlScript();
	}

	public void htmlScriptInscriptionFormPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyInscriptionFormPage();
		super.htmlBody();
	}

	public void htmlBodyInscriptionFormPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlInscriptionFormPage();
		super.html();
	}

	public void htmlInscriptionFormPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaInscriptionFormPage();
		super.htmlMeta();
	}

	public void htmlMetaInscriptionFormPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesInscriptionFormPage();
		super.htmlStyles();
	}

	public void htmlStylesInscriptionFormPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleInscriptionFormPage();
		super.htmlStyle();
	}

	public void htmlStyleInscriptionFormPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiInscriptionFormPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof InscriptionFormPage) {
			InscriptionFormPage original = (InscriptionFormPage)o;
			super.requeteApiInscriptionFormGenPage();
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
		if(!(o instanceof InscriptionFormPage))
			return false;
		InscriptionFormPage that = (InscriptionFormPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionFormPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
