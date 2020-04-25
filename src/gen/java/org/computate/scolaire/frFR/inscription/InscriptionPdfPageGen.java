package org.computate.scolaire.frFR.inscription;

import java.util.Arrays;
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
import org.computate.scolaire.frFR.inscription.InscriptionPdfGenPage;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionPdfPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class InscriptionPdfPageGen<DEV> extends InscriptionPdfGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(InscriptionPdfPage.class);

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseInscriptionPdfPage = false;

	public InscriptionPdfPage initLoinInscriptionPdfPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseInscriptionPdfPage) {
			dejaInitialiseInscriptionPdfPage = true;
			initLoinInscriptionPdfPage();
		}
		return (InscriptionPdfPage)this;
	}

	public void initLoinInscriptionPdfPage() {
		initInscriptionPdfPage();
		super.initLoinInscriptionPdfGenPage(requeteSite_);
	}

	public void initInscriptionPdfPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinInscriptionPdfPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteInscriptionPdfPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteInscriptionPdfGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteInscriptionPdfPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirInscriptionPdfPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirInscriptionPdfPage(String var) {
		InscriptionPdfPage oInscriptionPdfPage = (InscriptionPdfPage)this;
		switch(var) {
			default:
				return super.obtenirInscriptionPdfGenPage(var);
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
				o = attribuerInscriptionPdfPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerInscriptionPdfPage(String var, Object val) {
		InscriptionPdfPage oInscriptionPdfPage = (InscriptionPdfPage)this;
		switch(var) {
			default:
				return super.attribuerInscriptionPdfGenPage(var, val);
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
					o = definirInscriptionPdfPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirInscriptionPdfPage(String var, String val) {
		switch(var) {
			default:
				return super.definirInscriptionPdfGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsInscriptionPdfPage();
		super.htmlScripts();
	}

	public void htmlScriptsInscriptionPdfPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptInscriptionPdfPage();
		super.htmlScript();
	}

	public void htmlScriptInscriptionPdfPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyInscriptionPdfPage();
		super.htmlBody();
	}

	public void htmlBodyInscriptionPdfPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlInscriptionPdfPage();
		super.html();
	}

	public void htmlInscriptionPdfPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaInscriptionPdfPage();
		super.htmlMeta();
	}

	public void htmlMetaInscriptionPdfPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesInscriptionPdfPage();
		super.htmlStyles();
	}

	public void htmlStylesInscriptionPdfPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleInscriptionPdfPage();
		super.htmlStyle();
	}

	public void htmlStyleInscriptionPdfPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiInscriptionPdfPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof InscriptionPdfPage) {
			InscriptionPdfPage original = (InscriptionPdfPage)o;
			super.requeteApiInscriptionPdfGenPage();
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
		if(!(o instanceof InscriptionPdfPage))
			return false;
		InscriptionPdfPage that = (InscriptionPdfPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionPdfPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
