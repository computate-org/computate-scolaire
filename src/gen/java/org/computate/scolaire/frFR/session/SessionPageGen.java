package org.computate.scolaire.frFR.session;

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
import org.computate.scolaire.frFR.session.SessionGenPage;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class SessionPageGen<DEV> extends SessionGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SessionPage.class);

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
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetSessionPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetSessionPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return SessionGenPage.staticSetSessionGenPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrSessionPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrSessionPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return SessionGenPage.staticSolrSessionGenPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrSessionPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrSessionPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return SessionGenPage.staticSolrStrSessionGenPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqSessionPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqSessionPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return SessionGenPage.staticSolrFqSessionGenPage(entiteVar,  requeteSite_, o);
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
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSessionPage(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirSessionGenPage(var, val);
		}
	}

	@Override public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirSessionPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSessionPage(String var, Object val) {
		switch(var.toLowerCase()) {
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
	// requeteApi //
	//////////////////

	public void requeteApiSessionPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof SessionPage) {
			SessionPage original = (SessionPage)o;
			super.requeteApiSessionGenPage();
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
