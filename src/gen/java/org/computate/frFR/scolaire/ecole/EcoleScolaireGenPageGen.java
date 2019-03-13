package org.computate.frFR.scolaire.ecole;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.page.MiseEnPage;
import org.computate.frFR.scolaire.recherche.ListeRecherche;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleScolaireGenPageGen<DEV> extends MiseEnPage {

	////////////////////////
	// listeEcoleScolaire //
	////////////////////////

	/**	L'entité « listeEcoleScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<EcoleScolaire> listeEcoleScolaire;
	public Couverture<ListeRecherche<EcoleScolaire>> listeEcoleScolaireCouverture = new Couverture<ListeRecherche<EcoleScolaire>>().p(this).c(ListeRecherche.class).var("listeEcoleScolaire").o(listeEcoleScolaire);

	/**	<br/>L'entité « listeEcoleScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeEcoleScolaire">Trouver l'entité listeEcoleScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeEcoleScolaire(Couverture<ListeRecherche<EcoleScolaire>> c);

	public ListeRecherche<EcoleScolaire> getListeEcoleScolaire() {
		return listeEcoleScolaire;
	}

	public void setListeEcoleScolaire(ListeRecherche<EcoleScolaire> listeEcoleScolaire) {
		this.listeEcoleScolaire = listeEcoleScolaire;
		this.listeEcoleScolaireCouverture.dejaInitialise = true;
	}
	protected EcoleScolaireGenPage listeEcoleScolaireInit() {
		if(!listeEcoleScolaireCouverture.dejaInitialise) {
			_listeEcoleScolaire(listeEcoleScolaireCouverture);
			if(listeEcoleScolaire == null)
				setListeEcoleScolaire(listeEcoleScolaireCouverture.o);
		}
		if(listeEcoleScolaire != null)
			listeEcoleScolaire.initLoinPourClasse(requeteSite_);
		listeEcoleScolaireCouverture.dejaInitialise(true);
		return (EcoleScolaireGenPage)this;
	}

	///////////////////
	// ecoleScolaire //
	///////////////////

	/**	L'entité « ecoleScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected EcoleScolaire ecoleScolaire;
	public Couverture<EcoleScolaire> ecoleScolaireCouverture = new Couverture<EcoleScolaire>().p(this).c(EcoleScolaire.class).var("ecoleScolaire").o(ecoleScolaire);

	/**	<br/>L'entité « ecoleScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleScolaire">Trouver l'entité ecoleScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleScolaire(Couverture<EcoleScolaire> c);

	public EcoleScolaire getEcoleScolaire() {
		return ecoleScolaire;
	}

	public void setEcoleScolaire(EcoleScolaire ecoleScolaire) {
		this.ecoleScolaire = ecoleScolaire;
		this.ecoleScolaireCouverture.dejaInitialise = true;
	}
	protected EcoleScolaireGenPage ecoleScolaireInit() {
		if(!ecoleScolaireCouverture.dejaInitialise) {
			_ecoleScolaire(ecoleScolaireCouverture);
			if(ecoleScolaire == null)
				setEcoleScolaire(ecoleScolaireCouverture.o);
		}
		if(ecoleScolaire != null)
			ecoleScolaire.initLoinPourClasse(requeteSite_);
		ecoleScolaireCouverture.dejaInitialise(true);
		return (EcoleScolaireGenPage)this;
	}

	//////////////////////////
	// pageUriEcoleScolaire //
	//////////////////////////

	/**	L'entité « pageUriEcoleScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriEcoleScolaire;
	public Couverture<String> pageUriEcoleScolaireCouverture = new Couverture<String>().p(this).c(String.class).var("pageUriEcoleScolaire").o(pageUriEcoleScolaire);

	/**	<br/>L'entité « pageUriEcoleScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUriEcoleScolaire">Trouver l'entité pageUriEcoleScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriEcoleScolaire(Couverture<String> c);

	public String getPageUriEcoleScolaire() {
		return pageUriEcoleScolaire;
	}

	public void setPageUriEcoleScolaire(String pageUriEcoleScolaire) {
		this.pageUriEcoleScolaire = pageUriEcoleScolaire;
		this.pageUriEcoleScolaireCouverture.dejaInitialise = true;
	}
	protected EcoleScolaireGenPage pageUriEcoleScolaireInit() {
		if(!pageUriEcoleScolaireCouverture.dejaInitialise) {
			_pageUriEcoleScolaire(pageUriEcoleScolaireCouverture);
			if(pageUriEcoleScolaire == null)
				setPageUriEcoleScolaire(pageUriEcoleScolaireCouverture.o);
		}
		pageUriEcoleScolaireCouverture.dejaInitialise(true);
		return (EcoleScolaireGenPage)this;
	}

	public String solrPageUriEcoleScolaire() {
		return pageUriEcoleScolaire;
	}

	public String strPageUriEcoleScolaire() {
		return pageUriEcoleScolaire == null ? "" : pageUriEcoleScolaire;
	}

	public String nomAffichagePageUriEcoleScolaire() {
		return null;
	}

	public String htmTooltipPageUriEcoleScolaire() {
		return null;
	}

	public String htmPageUriEcoleScolaire() {
		return pageUriEcoleScolaire == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriEcoleScolaire());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcoleScolaireGenPage = false;

	public EcoleScolaireGenPage initLoinEcoleScolaireGenPage(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcoleScolaireGenPage) {
			dejaInitialiseEcoleScolaireGenPage = true;
			initLoinEcoleScolaireGenPage();
		}
		return (EcoleScolaireGenPage)this;
	}

	public void initLoinEcoleScolaireGenPage() {
		super.initLoinMiseEnPage(requeteSite_);
		initEcoleScolaireGenPage();
	}

	public void initEcoleScolaireGenPage() {
		listeEcoleScolaireInit();
		ecoleScolaireInit();
		pageUriEcoleScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinEcoleScolaireGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleScolaireGenPage(RequeteSite requeteSite_) {
			super.requeteSiteMiseEnPage(requeteSite_);
		listeEcoleScolaire.setRequeteSite_(requeteSite_);
		ecoleScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteEcoleScolaireGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcoleScolaireGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcoleScolaireGenPage(String var) throws Exception {
		EcoleScolaireGenPage oEcoleScolaireGenPage = (EcoleScolaireGenPage)this;
		switch(var) {
			case "listeEcoleScolaire":
				return oEcoleScolaireGenPage.listeEcoleScolaire;
			case "ecoleScolaire":
				return oEcoleScolaireGenPage.ecoleScolaire;
			case "pageUriEcoleScolaire":
				return oEcoleScolaireGenPage.pageUriEcoleScolaire;
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
				o = attribuerEcoleScolaireGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcoleScolaireGenPage(String var, Object val) {
		EcoleScolaireGenPage oEcoleScolaireGenPage = (EcoleScolaireGenPage)this;
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
					o = definirEcoleScolaireGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcoleScolaireGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirMiseEnPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEcoleScolaireGenPage();
		super.htmlScriptsMiseEnPage();
	}

	public void htmlScriptsEcoleScolaireGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleScolaireGenPage();
		super.htmlScriptMiseEnPage();
	}

	public void htmlScriptEcoleScolaireGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleScolaireGenPage();
		super.htmlBodyMiseEnPage();
	}

	public void htmlBodyEcoleScolaireGenPage() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), pageUriEcoleScolaire);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof EcoleScolaireGenPage))
			return false;
		EcoleScolaireGenPage that = (EcoleScolaireGenPage)o;
		return super.equals(o)
				&& Objects.equals( pageUriEcoleScolaire, that.pageUriEcoleScolaire );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleScolaireGenPage {");
		sb.append( "pageUriEcoleScolaire: \"" ).append(pageUriEcoleScolaire).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
