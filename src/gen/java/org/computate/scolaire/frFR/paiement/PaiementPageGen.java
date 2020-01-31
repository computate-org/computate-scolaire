package org.computate.scolaire.frFR.paiement;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
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
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.paiement.PaiementGenPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PaiementPageGen<DEV> extends PaiementGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePaiementPage = false;

	public PaiementPage initLoinPaiementPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePaiementPage) {
			dejaInitialisePaiementPage = true;
			initLoinPaiementPage();
		}
		return (PaiementPage)this;
	}

	public void initLoinPaiementPage() {
		initPaiementPage();
		super.initLoinPaiementGenPage(requeteSite_);
	}

	public void initPaiementPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPaiementPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePaiementPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSitePaiementGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePaiementPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPaiementPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPaiementPage(String var) {
		PaiementPage oPaiementPage = (PaiementPage)this;
		switch(var) {
			default:
				return super.obtenirPaiementGenPage(var);
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
				o = attribuerPaiementPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPaiementPage(String var, Object val) {
		PaiementPage oPaiementPage = (PaiementPage)this;
		switch(var) {
			default:
				return super.attribuerPaiementGenPage(var, val);
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
					o = definirPaiementPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPaiementPage(String var, String val) {
		switch(var) {
			default:
				return super.definirPaiementGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPaiementPage();
		super.htmlScripts();
	}

	public void htmlScriptsPaiementPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPaiementPage();
		super.htmlScript();
	}

	public void htmlScriptPaiementPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPaiementPage();
		super.htmlBody();
	}

	public void htmlBodyPaiementPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPaiementPage();
		super.html();
	}

	public void htmlPaiementPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPaiementPage();
		super.htmlMeta();
	}

	public void htmlMetaPaiementPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPaiementPage();
		super.htmlStyles();
	}

	public void htmlStylesPaiementPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePaiementPage();
		super.htmlStyle();
	}

	public void htmlStylePaiementPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiPaiementPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		PaiementPage original = (PaiementPage)Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(original != null) {
			super.requeteApiPaiementGenPage();
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
		if(!(o instanceof PaiementPage))
			return false;
		PaiementPage that = (PaiementPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PaiementPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
