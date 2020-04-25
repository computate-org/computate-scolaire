package org.computate.scolaire.frFR.inscription;

import java.util.Arrays;
import org.computate.scolaire.frFR.inscription.InscriptionMailGenPage;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionMailPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class InscriptionMailPageGen<DEV> extends InscriptionMailGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(InscriptionMailPage.class);

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseInscriptionMailPage = false;

	public InscriptionMailPage initLoinInscriptionMailPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseInscriptionMailPage) {
			dejaInitialiseInscriptionMailPage = true;
			initLoinInscriptionMailPage();
		}
		return (InscriptionMailPage)this;
	}

	public void initLoinInscriptionMailPage() {
		initInscriptionMailPage();
		super.initLoinInscriptionMailGenPage(requeteSite_);
	}

	public void initInscriptionMailPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinInscriptionMailPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteInscriptionMailPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteInscriptionMailGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteInscriptionMailPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirInscriptionMailPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirInscriptionMailPage(String var) {
		InscriptionMailPage oInscriptionMailPage = (InscriptionMailPage)this;
		switch(var) {
			default:
				return super.obtenirInscriptionMailGenPage(var);
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
				o = attribuerInscriptionMailPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerInscriptionMailPage(String var, Object val) {
		InscriptionMailPage oInscriptionMailPage = (InscriptionMailPage)this;
		switch(var) {
			default:
				return super.attribuerInscriptionMailGenPage(var, val);
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
					o = definirInscriptionMailPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirInscriptionMailPage(String var, String val) {
		switch(var) {
			default:
				return super.definirInscriptionMailGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsInscriptionMailPage();
		super.htmlScripts();
	}

	public void htmlScriptsInscriptionMailPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptInscriptionMailPage();
		super.htmlScript();
	}

	public void htmlScriptInscriptionMailPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyInscriptionMailPage();
		super.htmlBody();
	}

	public void htmlBodyInscriptionMailPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlInscriptionMailPage();
		super.html();
	}

	public void htmlInscriptionMailPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaInscriptionMailPage();
		super.htmlMeta();
	}

	public void htmlMetaInscriptionMailPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesInscriptionMailPage();
		super.htmlStyles();
	}

	public void htmlStylesInscriptionMailPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleInscriptionMailPage();
		super.htmlStyle();
	}

	public void htmlStyleInscriptionMailPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiInscriptionMailPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof InscriptionMailPage) {
			InscriptionMailPage original = (InscriptionMailPage)o;
			super.requeteApiInscriptionMailGenPage();
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
		if(!(o instanceof InscriptionMailPage))
			return false;
		InscriptionMailPage that = (InscriptionMailPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionMailPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
