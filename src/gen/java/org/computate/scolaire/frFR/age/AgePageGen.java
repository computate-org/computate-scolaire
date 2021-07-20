package org.computate.scolaire.frFR.age;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.age.AgeGenPage;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class AgePageGen<DEV> extends AgeGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AgePage.class);

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAgePage = false;

	public AgePage initLoinAgePage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAgePage) {
			dejaInitialiseAgePage = true;
			initLoinAgePage();
		}
		return (AgePage)this;
	}

	public void initLoinAgePage() {
		initAgePage();
		super.initLoinAgeGenPage(requeteSite_);
	}

	public void initAgePage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAgePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAgePage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteAgeGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAgePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAgePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirAgePage(String var) {
		AgePage oAgePage = (AgePage)this;
		switch(var) {
			default:
				return super.obtenirAgeGenPage(var);
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
				o = attribuerAgePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAgePage(String var, Object val) {
		AgePage oAgePage = (AgePage)this;
		switch(var) {
			default:
				return super.attribuerAgeGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetAgePage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetAgePage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return AgeGenPage.staticSetAgeGenPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrAgePage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrAgePage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return AgeGenPage.staticSolrAgeGenPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrAgePage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrAgePage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return AgeGenPage.staticSolrStrAgeGenPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqAgePage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqAgePage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return AgeGenPage.staticSolrFqAgeGenPage(entiteVar,  requeteSite_, o);
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
					o = definirAgePage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAgePage(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirAgeGenPage(var, val);
		}
	}

	@Override public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAgePage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAgePage(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirAgeGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsAgePage();
		super.htmlScripts();
	}

	public void htmlScriptsAgePage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptAgePage();
		super.htmlScript();
	}

	public void htmlScriptAgePage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyAgePage();
		super.htmlBody();
	}

	public void htmlBodyAgePage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlAgePage();
		super.html();
	}

	public void htmlAgePage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaAgePage();
		super.htmlMeta();
	}

	public void htmlMetaAgePage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesAgePage();
		super.htmlStyles();
	}

	public void htmlStylesAgePage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleAgePage();
		super.htmlStyle();
	}

	public void htmlStyleAgePage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiAgePage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof AgePage) {
			AgePage original = (AgePage)o;
			super.requeteApiAgeGenPage();
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
		if(!(o instanceof AgePage))
			return false;
		AgePage that = (AgePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AgePage { ");
		sb.append(" }");
		return sb.toString();
	}
}
