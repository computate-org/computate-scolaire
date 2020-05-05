package org.computate.scolaire.frFR.inscription.design;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import org.computate.scolaire.frFR.inscription.design.DesignInscriptionGenPage;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscriptionPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class DesignInscriptionPageGen<DEV> extends DesignInscriptionGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignInscriptionPage.class);

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseDesignInscriptionPage = false;

	public DesignInscriptionPage initLoinDesignInscriptionPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseDesignInscriptionPage) {
			dejaInitialiseDesignInscriptionPage = true;
			initLoinDesignInscriptionPage();
		}
		return (DesignInscriptionPage)this;
	}

	public void initLoinDesignInscriptionPage() {
		initDesignInscriptionPage();
		super.initLoinDesignInscriptionGenPage(requeteSite_);
	}

	public void initDesignInscriptionPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinDesignInscriptionPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteDesignInscriptionPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteDesignInscriptionGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteDesignInscriptionPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirDesignInscriptionPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirDesignInscriptionPage(String var) {
		DesignInscriptionPage oDesignInscriptionPage = (DesignInscriptionPage)this;
		switch(var) {
			default:
				return super.obtenirDesignInscriptionGenPage(var);
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
				o = attribuerDesignInscriptionPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerDesignInscriptionPage(String var, Object val) {
		DesignInscriptionPage oDesignInscriptionPage = (DesignInscriptionPage)this;
		switch(var) {
			default:
				return super.attribuerDesignInscriptionGenPage(var, val);
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
					o = definirDesignInscriptionPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignInscriptionPage(String var, String val) {
		switch(var) {
			default:
				return super.definirDesignInscriptionGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignInscriptionPage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignInscriptionPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignInscriptionPage();
		super.htmlScript();
	}

	public void htmlScriptDesignInscriptionPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignInscriptionPage();
		super.htmlBody();
	}

	public void htmlBodyDesignInscriptionPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignInscriptionPage();
		super.html();
	}

	public void htmlDesignInscriptionPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignInscriptionPage();
		super.htmlMeta();
	}

	public void htmlMetaDesignInscriptionPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignInscriptionPage();
		super.htmlStyles();
	}

	public void htmlStylesDesignInscriptionPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignInscriptionPage();
		super.htmlStyle();
	}

	public void htmlStyleDesignInscriptionPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiDesignInscriptionPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof DesignInscriptionPage) {
			DesignInscriptionPage original = (DesignInscriptionPage)o;
			super.requeteApiDesignInscriptionGenPage();
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
		if(!(o instanceof DesignInscriptionPage))
			return false;
		DesignInscriptionPage that = (DesignInscriptionPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignInscriptionPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
