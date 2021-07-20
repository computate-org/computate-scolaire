package org.computate.scolaire.frFR.recu;

import org.computate.scolaire.frFR.recu.RecuGenPage;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class RecuPageGen<DEV> extends RecuGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(RecuPage.class);

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseRecuPage = false;

	public RecuPage initLoinRecuPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseRecuPage) {
			dejaInitialiseRecuPage = true;
			initLoinRecuPage();
		}
		return (RecuPage)this;
	}

	public void initLoinRecuPage() {
		initRecuPage();
		super.initLoinRecuGenPage(requeteSite_);
	}

	public void initRecuPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinRecuPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteRecuPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteRecuGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteRecuPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirRecuPage(v);
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
	public Object obtenirRecuPage(String var) {
		RecuPage oRecuPage = (RecuPage)this;
		switch(var) {
			default:
				return super.obtenirRecuGenPage(var);
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
				o = attribuerRecuPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerRecuPage(String var, Object val) {
		RecuPage oRecuPage = (RecuPage)this;
		switch(var) {
			default:
				return super.attribuerRecuGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetRecuPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetRecuPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return RecuGenPage.staticSetRecuGenPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrRecuPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrRecuPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return RecuGenPage.staticSolrRecuGenPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrRecuPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrRecuPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return RecuGenPage.staticSolrStrRecuGenPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqRecuPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqRecuPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return RecuGenPage.staticSolrFqRecuGenPage(entiteVar,  requeteSite_, o);
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
					o = definirRecuPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRecuPage(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirRecuGenPage(var, val);
		}
	}

	@Override public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirRecuPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRecuPage(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirRecuGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsRecuPage();
		super.htmlScripts();
	}

	public void htmlScriptsRecuPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptRecuPage();
		super.htmlScript();
	}

	public void htmlScriptRecuPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyRecuPage();
		super.htmlBody();
	}

	public void htmlBodyRecuPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlRecuPage();
		super.html();
	}

	public void htmlRecuPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaRecuPage();
		super.htmlMeta();
	}

	public void htmlMetaRecuPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesRecuPage();
		super.htmlStyles();
	}

	public void htmlStylesRecuPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleRecuPage();
		super.htmlStyle();
	}

	public void htmlStyleRecuPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiRecuPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof RecuPage) {
			RecuPage original = (RecuPage)o;
			super.requeteApiRecuGenPage();
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
		if(!(o instanceof RecuPage))
			return false;
		RecuPage that = (RecuPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("RecuPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
