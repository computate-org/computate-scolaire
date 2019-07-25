package org.computate.scolaire.frFR.utilisateur;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.cluster.ClusterFrFRPage;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSiteFrFRGenPageGen<DEV> extends ClusterFrFRPage {

	//////////////////////////
	// listeUtilisateurSite //
	//////////////////////////

	/**	L'entité « listeUtilisateurSite »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<UtilisateurSite> listeUtilisateurSite;
	public Couverture<ListeRecherche<UtilisateurSite>> listeUtilisateurSiteCouverture = new Couverture<ListeRecherche<UtilisateurSite>>().p(this).c(ListeRecherche.class).var("listeUtilisateurSite").o(listeUtilisateurSite);

	/**	<br/>L'entité « listeUtilisateurSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeUtilisateurSite">Trouver l'entité listeUtilisateurSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeUtilisateurSite(Couverture<ListeRecherche<UtilisateurSite>> c);

	public ListeRecherche<UtilisateurSite> getListeUtilisateurSite() {
		return listeUtilisateurSite;
	}

	public void setListeUtilisateurSite(ListeRecherche<UtilisateurSite> listeUtilisateurSite) {
		this.listeUtilisateurSite = listeUtilisateurSite;
		this.listeUtilisateurSiteCouverture.dejaInitialise = true;
	}
	protected UtilisateurSiteFrFRGenPage listeUtilisateurSiteInit() {
		if(!listeUtilisateurSiteCouverture.dejaInitialise) {
			_listeUtilisateurSite(listeUtilisateurSiteCouverture);
			if(listeUtilisateurSite == null)
				setListeUtilisateurSite(listeUtilisateurSiteCouverture.o);
		}
		if(listeUtilisateurSite != null)
			listeUtilisateurSite.initLoinPourClasse(requeteSite_);
		listeUtilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSiteFrFRGenPage)this;
	}

	/////////////////////
	// utilisateurSite //
	/////////////////////

	/**	L'entité « utilisateurSite »
	 *	 is defined as null before being initialized. 
	 */
	protected UtilisateurSite utilisateurSite;
	public Couverture<UtilisateurSite> utilisateurSiteCouverture = new Couverture<UtilisateurSite>().p(this).c(UtilisateurSite.class).var("utilisateurSite").o(utilisateurSite);

	/**	<br/>L'entité « utilisateurSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurSite">Trouver l'entité utilisateurSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurSite(Couverture<UtilisateurSite> c);

	public UtilisateurSite getUtilisateurSite() {
		return utilisateurSite;
	}

	public void setUtilisateurSite(UtilisateurSite utilisateurSite) {
		this.utilisateurSite = utilisateurSite;
		this.utilisateurSiteCouverture.dejaInitialise = true;
	}
	protected UtilisateurSiteFrFRGenPage utilisateurSiteInit() {
		if(!utilisateurSiteCouverture.dejaInitialise) {
			_utilisateurSite(utilisateurSiteCouverture);
			if(utilisateurSite == null)
				setUtilisateurSite(utilisateurSiteCouverture.o);
		}
		if(utilisateurSite != null)
			utilisateurSite.initLoinPourClasse(requeteSite_);
		utilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSiteFrFRGenPage)this;
	}

	////////////////////////////
	// pageUriUtilisateurSite //
	////////////////////////////

	/**	L'entité « pageUriUtilisateurSite »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriUtilisateurSite;
	public Couverture<String> pageUriUtilisateurSiteCouverture = new Couverture<String>().p(this).c(String.class).var("pageUriUtilisateurSite").o(pageUriUtilisateurSite);

	/**	<br/>L'entité « pageUriUtilisateurSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUriUtilisateurSite">Trouver l'entité pageUriUtilisateurSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriUtilisateurSite(Couverture<String> c);

	public String getPageUriUtilisateurSite() {
		return pageUriUtilisateurSite;
	}

	public void setPageUriUtilisateurSite(String pageUriUtilisateurSite) {
		this.pageUriUtilisateurSite = pageUriUtilisateurSite;
		this.pageUriUtilisateurSiteCouverture.dejaInitialise = true;
	}
	protected UtilisateurSiteFrFRGenPage pageUriUtilisateurSiteInit() {
		if(!pageUriUtilisateurSiteCouverture.dejaInitialise) {
			_pageUriUtilisateurSite(pageUriUtilisateurSiteCouverture);
			if(pageUriUtilisateurSite == null)
				setPageUriUtilisateurSite(pageUriUtilisateurSiteCouverture.o);
		}
		pageUriUtilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSiteFrFRGenPage)this;
	}

	public String solrPageUriUtilisateurSite() {
		return pageUriUtilisateurSite;
	}

	public String strPageUriUtilisateurSite() {
		return pageUriUtilisateurSite == null ? "" : pageUriUtilisateurSite;
	}

	public String nomAffichagePageUriUtilisateurSite() {
		return null;
	}

	public String htmTooltipPageUriUtilisateurSite() {
		return null;
	}

	public String htmPageUriUtilisateurSite() {
		return pageUriUtilisateurSite == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriUtilisateurSite());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseUtilisateurSiteFrFRGenPage = false;

	public UtilisateurSiteFrFRGenPage initLoinUtilisateurSiteFrFRGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseUtilisateurSiteFrFRGenPage) {
			dejaInitialiseUtilisateurSiteFrFRGenPage = true;
			initLoinUtilisateurSiteFrFRGenPage();
		}
		return (UtilisateurSiteFrFRGenPage)this;
	}

	public void initLoinUtilisateurSiteFrFRGenPage() {
		super.initLoinClusterFrFRPage(requeteSite_);
		initUtilisateurSiteFrFRGenPage();
	}

	public void initUtilisateurSiteFrFRGenPage() {
		listeUtilisateurSiteInit();
		utilisateurSiteInit();
		pageUriUtilisateurSiteInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinUtilisateurSiteFrFRGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteUtilisateurSiteFrFRGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterFrFRPage(requeteSite_);
		if(listeUtilisateurSite != null)
			listeUtilisateurSite.setRequeteSite_(requeteSite_);
		if(utilisateurSite != null)
			utilisateurSite.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteUtilisateurSiteFrFRGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirUtilisateurSiteFrFRGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirUtilisateurSiteFrFRGenPage(String var) {
		UtilisateurSiteFrFRGenPage oUtilisateurSiteFrFRGenPage = (UtilisateurSiteFrFRGenPage)this;
		switch(var) {
			case "listeUtilisateurSite":
				return oUtilisateurSiteFrFRGenPage.listeUtilisateurSite;
			case "utilisateurSite":
				return oUtilisateurSiteFrFRGenPage.utilisateurSite;
			case "pageUriUtilisateurSite":
				return oUtilisateurSiteFrFRGenPage.pageUriUtilisateurSite;
			default:
				return super.obtenirClusterFrFRPage(var);
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
				o = attribuerUtilisateurSiteFrFRGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerUtilisateurSiteFrFRGenPage(String var, Object val) {
		UtilisateurSiteFrFRGenPage oUtilisateurSiteFrFRGenPage = (UtilisateurSiteFrFRGenPage)this;
		switch(var) {
			default:
				return super.attribuerClusterFrFRPage(var, val);
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
					o = definirUtilisateurSiteFrFRGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirUtilisateurSiteFrFRGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterFrFRPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsUtilisateurSiteFrFRGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsUtilisateurSiteFrFRGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptUtilisateurSiteFrFRGenPage();
		super.htmlScript();
	}

	public void htmlScriptUtilisateurSiteFrFRGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyUtilisateurSiteFrFRGenPage();
		super.htmlBody();
	}

	public void htmlBodyUtilisateurSiteFrFRGenPage() {
		if(utilisateurSite != null)
			utilisateurSite.htmlBody();
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlUtilisateurSiteFrFRGenPage();
		super.html();
	}

	public void htmlUtilisateurSiteFrFRGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaUtilisateurSiteFrFRGenPage();
		super.htmlMeta();
	}

	public void htmlMetaUtilisateurSiteFrFRGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesUtilisateurSiteFrFRGenPage();
		super.htmlStyles();
	}

	public void htmlStylesUtilisateurSiteFrFRGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleUtilisateurSiteFrFRGenPage();
		super.htmlStyle();
	}

	public void htmlStyleUtilisateurSiteFrFRGenPage() {
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
		if(!(o instanceof UtilisateurSiteFrFRGenPage))
			return false;
		UtilisateurSiteFrFRGenPage that = (UtilisateurSiteFrFRGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSiteFrFRGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
