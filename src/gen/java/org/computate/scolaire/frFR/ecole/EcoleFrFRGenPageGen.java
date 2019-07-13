package org.computate.scolaire.frFR.ecole;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.cluster.ClusterFrFRPage;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleFrFRGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleFrFRGenPageGen<DEV> extends ClusterFrFRPage {

	////////////////
	// listeEcole //
	////////////////

	/**	L'entité « listeEcole »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<Ecole> listeEcole;
	public Couverture<ListeRecherche<Ecole>> listeEcoleCouverture = new Couverture<ListeRecherche<Ecole>>().p(this).c(ListeRecherche.class).var("listeEcole").o(listeEcole);

	/**	<br/>L'entité « listeEcole »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeEcole">Trouver l'entité listeEcole dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeEcole(Couverture<ListeRecherche<Ecole>> c);

	public ListeRecherche<Ecole> getListeEcole() {
		return listeEcole;
	}

	public void setListeEcole(ListeRecherche<Ecole> listeEcole) {
		this.listeEcole = listeEcole;
		this.listeEcoleCouverture.dejaInitialise = true;
	}
	protected EcoleFrFRGenPage listeEcoleInit() {
		if(!listeEcoleCouverture.dejaInitialise) {
			_listeEcole(listeEcoleCouverture);
			if(listeEcole == null)
				setListeEcole(listeEcoleCouverture.o);
		}
		if(listeEcole != null)
			listeEcole.initLoinPourClasse(requeteSite_);
		listeEcoleCouverture.dejaInitialise(true);
		return (EcoleFrFRGenPage)this;
	}

	///////////
	// ecole //
	///////////

	/**	L'entité « ecole »
	 *	 is defined as null before being initialized. 
	 */
	protected Ecole ecole;
	public Couverture<Ecole> ecoleCouverture = new Couverture<Ecole>().p(this).c(Ecole.class).var("ecole").o(ecole);

	/**	<br/>L'entité « ecole »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecole">Trouver l'entité ecole dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecole(Couverture<Ecole> c);

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
		this.ecoleCouverture.dejaInitialise = true;
	}
	protected EcoleFrFRGenPage ecoleInit() {
		if(!ecoleCouverture.dejaInitialise) {
			_ecole(ecoleCouverture);
			if(ecole == null)
				setEcole(ecoleCouverture.o);
		}
		if(ecole != null)
			ecole.initLoinPourClasse(requeteSite_);
		ecoleCouverture.dejaInitialise(true);
		return (EcoleFrFRGenPage)this;
	}

	//////////////////
	// pageUriEcole //
	//////////////////

	/**	L'entité « pageUriEcole »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriEcole;
	public Couverture<String> pageUriEcoleCouverture = new Couverture<String>().p(this).c(String.class).var("pageUriEcole").o(pageUriEcole);

	/**	<br/>L'entité « pageUriEcole »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUriEcole">Trouver l'entité pageUriEcole dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriEcole(Couverture<String> c);

	public String getPageUriEcole() {
		return pageUriEcole;
	}

	public void setPageUriEcole(String pageUriEcole) {
		this.pageUriEcole = pageUriEcole;
		this.pageUriEcoleCouverture.dejaInitialise = true;
	}
	protected EcoleFrFRGenPage pageUriEcoleInit() {
		if(!pageUriEcoleCouverture.dejaInitialise) {
			_pageUriEcole(pageUriEcoleCouverture);
			if(pageUriEcole == null)
				setPageUriEcole(pageUriEcoleCouverture.o);
		}
		pageUriEcoleCouverture.dejaInitialise(true);
		return (EcoleFrFRGenPage)this;
	}

	public String solrPageUriEcole() {
		return pageUriEcole;
	}

	public String strPageUriEcole() {
		return pageUriEcole == null ? "" : pageUriEcole;
	}

	public String nomAffichagePageUriEcole() {
		return null;
	}

	public String htmTooltipPageUriEcole() {
		return null;
	}

	public String htmPageUriEcole() {
		return pageUriEcole == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriEcole());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcoleFrFRGenPage = false;

	public EcoleFrFRGenPage initLoinEcoleFrFRGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcoleFrFRGenPage) {
			dejaInitialiseEcoleFrFRGenPage = true;
			initLoinEcoleFrFRGenPage();
		}
		return (EcoleFrFRGenPage)this;
	}

	public void initLoinEcoleFrFRGenPage() {
		super.initLoinClusterFrFRPage(requeteSite_);
		initEcoleFrFRGenPage();
	}

	public void initEcoleFrFRGenPage() {
		listeEcoleInit();
		ecoleInit();
		pageUriEcoleInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinEcoleFrFRGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleFrFRGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterFrFRPage(requeteSite_);
		if(listeEcole != null)
			listeEcole.setRequeteSite_(requeteSite_);
		if(ecole != null)
			ecole.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteEcoleFrFRGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcoleFrFRGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcoleFrFRGenPage(String var) {
		EcoleFrFRGenPage oEcoleFrFRGenPage = (EcoleFrFRGenPage)this;
		switch(var) {
			case "listeEcole":
				return oEcoleFrFRGenPage.listeEcole;
			case "ecole":
				return oEcoleFrFRGenPage.ecole;
			case "pageUriEcole":
				return oEcoleFrFRGenPage.pageUriEcole;
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
				o = attribuerEcoleFrFRGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcoleFrFRGenPage(String var, Object val) {
		EcoleFrFRGenPage oEcoleFrFRGenPage = (EcoleFrFRGenPage)this;
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
					o = definirEcoleFrFRGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcoleFrFRGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterFrFRPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEcoleFrFRGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsEcoleFrFRGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleFrFRGenPage();
		super.htmlScript();
	}

	public void htmlScriptEcoleFrFRGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleFrFRGenPage();
		super.htmlBody();
	}

	public void htmlBodyEcoleFrFRGenPage() {
		if(ecole != null)
			ecole.htmlBody();
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEcoleFrFRGenPage();
		super.html();
	}

	public void htmlEcoleFrFRGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEcoleFrFRGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEcoleFrFRGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEcoleFrFRGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEcoleFrFRGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEcoleFrFRGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEcoleFrFRGenPage() {
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
		if(!(o instanceof EcoleFrFRGenPage))
			return false;
		EcoleFrFRGenPage that = (EcoleFrFRGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleFrFRGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
