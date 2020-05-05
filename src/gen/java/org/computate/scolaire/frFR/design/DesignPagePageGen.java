package org.computate.scolaire.frFR.design;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
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
import org.computate.scolaire.frFR.design.DesignPageGenPage;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPagePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class DesignPagePageGen<DEV> extends DesignPageGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignPagePage.class);

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseDesignPagePage = false;

	public DesignPagePage initLoinDesignPagePage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseDesignPagePage) {
			dejaInitialiseDesignPagePage = true;
			initLoinDesignPagePage();
		}
		return (DesignPagePage)this;
	}

	public void initLoinDesignPagePage() {
		initDesignPagePage();
		super.initLoinDesignPageGenPage(requeteSite_);
	}

	public void initDesignPagePage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinDesignPagePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteDesignPagePage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteDesignPageGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteDesignPagePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirDesignPagePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirDesignPagePage(String var) {
		DesignPagePage oDesignPagePage = (DesignPagePage)this;
		switch(var) {
			default:
				return super.obtenirDesignPageGenPage(var);
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
				o = attribuerDesignPagePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerDesignPagePage(String var, Object val) {
		DesignPagePage oDesignPagePage = (DesignPagePage)this;
		switch(var) {
			default:
				return super.attribuerDesignPageGenPage(var, val);
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
					o = definirDesignPagePage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignPagePage(String var, String val) {
		switch(var) {
			default:
				return super.definirDesignPageGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignPagePage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignPagePage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignPagePage();
		super.htmlScript();
	}

	public void htmlScriptDesignPagePage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignPagePage();
		super.htmlBody();
	}

	public void htmlBodyDesignPagePage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignPagePage();
		super.html();
	}

	public void htmlDesignPagePage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignPagePage();
		super.htmlMeta();
	}

	public void htmlMetaDesignPagePage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignPagePage();
		super.htmlStyles();
	}

	public void htmlStylesDesignPagePage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignPagePage();
		super.htmlStyle();
	}

	public void htmlStyleDesignPagePage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiDesignPagePage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof DesignPagePage) {
			DesignPagePage original = (DesignPagePage)o;
			super.requeteApiDesignPageGenPage();
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
		if(!(o instanceof DesignPagePage))
			return false;
		DesignPagePage that = (DesignPagePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignPagePage { ");
		sb.append(" }");
		return sb.toString();
	}
}
