package org.computate.scolaire.frFR.session;

import java.math.MathContext;
import org.computate.scolaire.frFR.session.SessionScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SessionGenPageGen<DEV> extends ClusterPage {

	//////////////////////////
	// listeSessionScolaire //
	//////////////////////////

	/**	L'entité « listeSessionScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<SessionScolaire> listeSessionScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<SessionScolaire>> listeSessionScolaireCouverture = new Couverture<ListeRecherche<SessionScolaire>>().p(this).c(ListeRecherche.class).var("listeSessionScolaire").o(listeSessionScolaire);

	/**	<br/>L'entité « listeSessionScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeSessionScolaire">Trouver l'entité listeSessionScolaire dans Solr</a>
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

	/////////////////////
	// sessionScolaire //
	/////////////////////

	/**	L'entité « sessionScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected SessionScolaire sessionScolaire;
	@JsonIgnore
	public Couverture<SessionScolaire> sessionScolaireCouverture = new Couverture<SessionScolaire>().p(this).c(SessionScolaire.class).var("sessionScolaire").o(sessionScolaire);

	/**	<br/>L'entité « sessionScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionScolaire">Trouver l'entité sessionScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionScolaire(Couverture<SessionScolaire> c);

	public SessionScolaire getSessionScolaire() {
		return sessionScolaire;
	}

	public void setSessionScolaire(SessionScolaire sessionScolaire) {
		this.sessionScolaire = sessionScolaire;
		this.sessionScolaireCouverture.dejaInitialise = true;
	}
	protected SessionGenPage sessionScolaireInit() {
		if(!sessionScolaireCouverture.dejaInitialise) {
			_sessionScolaire(sessionScolaireCouverture);
			if(sessionScolaire == null)
				setSessionScolaire(sessionScolaireCouverture.o);
		}
		if(sessionScolaire != null)
			sessionScolaire.initLoinPourClasse(requeteSite_);
		sessionScolaireCouverture.dejaInitialise(true);
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
		super.initLoinClusterPage(requeteSite_);
		initSessionGenPage();
	}

	public void initSessionGenPage() {
		listeSessionScolaireInit();
		sessionScolaireInit();
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
		if(sessionScolaire != null)
			sessionScolaire.setRequeteSite_(requeteSite_);
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
		}
		return o;
	}
	public Object obtenirSessionGenPage(String var) {
		SessionGenPage oSessionGenPage = (SessionGenPage)this;
		switch(var) {
			case "listeSessionScolaire":
				return oSessionGenPage.listeSessionScolaire;
			case "sessionScolaire":
				return oSessionGenPage.sessionScolaire;
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
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSessionGenPage(String var, String val) {
		switch(var) {
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
