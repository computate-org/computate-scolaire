package org.computate.frFR.scolaire.utilisateur;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.frFR.scolaire.recherche.ListeRecherche;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.apache.commons.text.StringEscapeUtils;
import java.lang.String;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSite;
import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.scolaire.requete.RequeteSite;
import org.computate.frFR.scolaire.page.MiseEnPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSiteGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSiteGenPageGen<DEV> extends MiseEnPage {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSiteGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeUtilisateurSite">Trouver l'entité listeUtilisateurSite dans Solr</a>
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
	protected UtilisateurSiteGenPage listeUtilisateurSiteInit() {
		if(!listeUtilisateurSiteCouverture.dejaInitialise) {
			_listeUtilisateurSite(listeUtilisateurSiteCouverture);
			if(listeUtilisateurSite == null)
				setListeUtilisateurSite(listeUtilisateurSiteCouverture.o);
		}
		if(listeUtilisateurSite != null)
			listeUtilisateurSite.initLoinPourClasse(requeteSite_);
		listeUtilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSiteGenPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSiteGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurSite">Trouver l'entité utilisateurSite dans Solr</a>
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
	protected UtilisateurSiteGenPage utilisateurSiteInit() {
		if(!utilisateurSiteCouverture.dejaInitialise) {
			_utilisateurSite(utilisateurSiteCouverture);
			if(utilisateurSite == null)
				setUtilisateurSite(utilisateurSiteCouverture.o);
		}
		if(utilisateurSite != null)
			utilisateurSite.initLoinPourClasse(requeteSite_);
		utilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSiteGenPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSiteGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUriUtilisateurSite">Trouver l'entité pageUriUtilisateurSite dans Solr</a>
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
	protected UtilisateurSiteGenPage pageUriUtilisateurSiteInit() {
		if(!pageUriUtilisateurSiteCouverture.dejaInitialise) {
			_pageUriUtilisateurSite(pageUriUtilisateurSiteCouverture);
			if(pageUriUtilisateurSite == null)
				setPageUriUtilisateurSite(pageUriUtilisateurSiteCouverture.o);
		}
		pageUriUtilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSiteGenPage)this;
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

	protected boolean dejaInitialiseUtilisateurSiteGenPage = false;

	public UtilisateurSiteGenPage initLoinUtilisateurSiteGenPage(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseUtilisateurSiteGenPage) {
			dejaInitialiseUtilisateurSiteGenPage = true;
			initLoinUtilisateurSiteGenPage();
		}
		return (UtilisateurSiteGenPage)this;
	}

	public void initLoinUtilisateurSiteGenPage() {
		super.initLoinMiseEnPage(requeteSite_);
		initUtilisateurSiteGenPage();
	}

	public void initUtilisateurSiteGenPage() {
		listeUtilisateurSiteInit();
		utilisateurSiteInit();
		pageUriUtilisateurSiteInit();
	}

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinUtilisateurSiteGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteUtilisateurSiteGenPage(RequeteSite requeteSite_) {
			super.requeteSiteMiseEnPage(requeteSite_);
		listeUtilisateurSite.setRequeteSite_(requeteSite_);
		utilisateurSite.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteUtilisateurSiteGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirUtilisateurSiteGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirUtilisateurSiteGenPage(String var) throws Exception {
		UtilisateurSiteGenPage oUtilisateurSiteGenPage = (UtilisateurSiteGenPage)this;
		switch(var) {
			case "listeUtilisateurSite":
				return oUtilisateurSiteGenPage.listeUtilisateurSite;
			case "utilisateurSite":
				return oUtilisateurSiteGenPage.utilisateurSite;
			case "pageUriUtilisateurSite":
				return oUtilisateurSiteGenPage.pageUriUtilisateurSite;
			default:
				return super.obtenirMiseEnPage(var);
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
				o = attribuerUtilisateurSiteGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerUtilisateurSiteGenPage(String var, Object val) {
		UtilisateurSiteGenPage oUtilisateurSiteGenPage = (UtilisateurSiteGenPage)this;
		switch(var) {
			default:
				return super.attribuerMiseEnPage(var, val);
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
					o = definirUtilisateurSiteGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirUtilisateurSiteGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirMiseEnPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsUtilisateurSiteGenPage();
		super.htmlScriptsMiseEnPage();
	}

	public void htmlScriptsUtilisateurSiteGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptUtilisateurSiteGenPage();
		super.htmlScriptMiseEnPage();
	}

	public void htmlScriptUtilisateurSiteGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyUtilisateurSiteGenPage();
		super.htmlBodyMiseEnPage();
	}

	public void htmlBodyUtilisateurSiteGenPage() {
		utilisateurSite.htmlBody();
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), pageUriUtilisateurSite);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof UtilisateurSiteGenPage))
			return false;
		UtilisateurSiteGenPage that = (UtilisateurSiteGenPage)o;
		return super.equals(o)
				&& Objects.equals( pageUriUtilisateurSite, that.pageUriUtilisateurSite );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSiteGenPage {");
		sb.append( "pageUriUtilisateurSite: \"" ).append(pageUriUtilisateurSite).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
