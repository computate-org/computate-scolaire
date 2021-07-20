package org.computate.scolaire.frFR.session;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.session.SessionScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
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
import org.computate.scolaire.frFR.cluster.ClusterPage;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class SessionGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SessionGenPage.class);

	//////////////////////////
	// listeSessionScolaire //
	//////////////////////////

	/**	 L'entité listeSessionScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<SessionScolaire> listeSessionScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<SessionScolaire>> listeSessionScolaireCouverture = new Couverture<ListeRecherche<SessionScolaire>>().p(this).c(ListeRecherche.class).var("listeSessionScolaire").o(listeSessionScolaire);

	/**	<br/> L'entité listeSessionScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeSessionScolaire">Trouver l'entité listeSessionScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeSessionScolaire(Couverture<ListeRecherche<SessionScolaire>> c);

	public ListeRecherche<SessionScolaire> getListeSessionScolaire() {
		return listeSessionScolaire;
	}

	public void setListeSessionScolaire(ListeRecherche<SessionScolaire> listeSessionScolaire) {
		this.listeSessionScolaire = listeSessionScolaire;
		this.listeSessionScolaireCouverture.dejaInitialise = true;
	}
	public static ListeRecherche<SessionScolaire> staticSetListeSessionScolaire(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SessionGenPage listeSessionScolaireInit() {
		if(!listeSessionScolaireCouverture.dejaInitialise) {
			_listeSessionScolaire(listeSessionScolaireCouverture);
			if(listeSessionScolaire == null)
				setListeSessionScolaire(listeSessionScolaireCouverture.o);
		}
		if(listeSessionScolaire != null)
			listeSessionScolaire.initLoinPourClasse(requeteSite_);
		listeSessionScolaireCouverture.dejaInitialise(true);
		return (SessionGenPage)this;
	}

	//////////////////////
	// sessionScolaire_ //
	//////////////////////

	/**	 L'entité sessionScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SessionScolaire sessionScolaire_;
	@JsonIgnore
	public Couverture<SessionScolaire> sessionScolaire_Couverture = new Couverture<SessionScolaire>().p(this).c(SessionScolaire.class).var("sessionScolaire_").o(sessionScolaire_);

	/**	<br/> L'entité sessionScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionScolaire_">Trouver l'entité sessionScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionScolaire_(Couverture<SessionScolaire> c);

	public SessionScolaire getSessionScolaire_() {
		return sessionScolaire_;
	}

	public void setSessionScolaire_(SessionScolaire sessionScolaire_) {
		this.sessionScolaire_ = sessionScolaire_;
		this.sessionScolaire_Couverture.dejaInitialise = true;
	}
	public static SessionScolaire staticSetSessionScolaire_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SessionGenPage sessionScolaire_Init() {
		if(!sessionScolaire_Couverture.dejaInitialise) {
			_sessionScolaire_(sessionScolaire_Couverture);
			if(sessionScolaire_ == null)
				setSessionScolaire_(sessionScolaire_Couverture.o);
		}
		sessionScolaire_Couverture.dejaInitialise(true);
		return (SessionGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSessionGenPage = false;

	public SessionGenPage initLoinSessionGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSessionGenPage) {
			dejaInitialiseSessionGenPage = true;
			initLoinSessionGenPage();
		}
		return (SessionGenPage)this;
	}

	public void initLoinSessionGenPage() {
		initSessionGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initSessionGenPage() {
		listeSessionScolaireInit();
		sessionScolaire_Init();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSessionGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSessionGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeSessionScolaire != null)
			listeSessionScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteSessionGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSessionGenPage(v);
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
	public Object obtenirSessionGenPage(String var) {
		SessionGenPage oSessionGenPage = (SessionGenPage)this;
		switch(var) {
			case "listeSessionScolaire":
				return oSessionGenPage.listeSessionScolaire;
			case "sessionScolaire_":
				return oSessionGenPage.sessionScolaire_;
			default:
				return super.obtenirClusterPage(var);
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
				o = attribuerSessionGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSessionGenPage(String var, Object val) {
		SessionGenPage oSessionGenPage = (SessionGenPage)this;
		switch(var) {
			default:
				return super.attribuerClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetSessionGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetSessionGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSetClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrSessionGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrSessionGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrSessionGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrSessionGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqSessionGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqSessionGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrFqClusterPage(entiteVar,  requeteSite_, o);
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
					o = definirSessionGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSessionGenPage(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	@Override public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirSessionGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSessionGenPage(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSessionGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsSessionGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSessionGenPage();
		super.htmlScript();
	}

	public void htmlScriptSessionGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySessionGenPage();
		super.htmlBody();
	}

	public void htmlBodySessionGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSessionGenPage();
		super.html();
	}

	public void htmlSessionGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSessionGenPage();
		super.htmlMeta();
	}

	public void htmlMetaSessionGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSessionGenPage();
		super.htmlStyles();
	}

	public void htmlStylesSessionGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSessionGenPage();
		super.htmlStyle();
	}

	public void htmlStyleSessionGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiSessionGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof SessionGenPage) {
			SessionGenPage original = (SessionGenPage)o;
			super.requeteApiClusterPage();
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
		if(!(o instanceof SessionGenPage))
			return false;
		SessionGenPage that = (SessionGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SessionGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
