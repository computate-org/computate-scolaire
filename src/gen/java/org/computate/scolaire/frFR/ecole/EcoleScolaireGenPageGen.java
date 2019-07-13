package org.computate.scolaire.frFR.ecole;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleScolaireGenPageGen<DEV> extends MiseEnPage {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeEcole">Trouver l'entité listeEcole dans Solr</a>
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
	protected EcoleScolaireGenPage listeEcoleInit() {
		if(!listeEcoleCouverture.dejaInitialise) {
			_listeEcole(listeEcoleCouverture);
			if(listeEcole == null)
				setListeEcole(listeEcoleCouverture.o);
		}
		if(listeEcole != null)
			listeEcole.initLoinPourClasse(requeteSite_);
		listeEcoleCouverture.dejaInitialise(true);
		return (EcoleScolaireGenPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecole">Trouver l'entité ecole dans Solr</a>
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
	protected EcoleScolaireGenPage ecoleInit() {
		if(!ecoleCouverture.dejaInitialise) {
			_ecole(ecoleCouverture);
			if(ecole == null)
				setEcole(ecoleCouverture.o);
		}
		if(ecole != null)
			ecole.initLoinPourClasse(requeteSite_);
		ecoleCouverture.dejaInitialise(true);
		return (EcoleScolaireGenPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUriEcole">Trouver l'entité pageUriEcole dans Solr</a>
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
	protected EcoleScolaireGenPage pageUriEcoleInit() {
		if(!pageUriEcoleCouverture.dejaInitialise) {
			_pageUriEcole(pageUriEcoleCouverture);
			if(pageUriEcole == null)
				setPageUriEcole(pageUriEcoleCouverture.o);
		}
		pageUriEcoleCouverture.dejaInitialise(true);
		return (EcoleScolaireGenPage)this;
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

	protected boolean dejaInitialiseEcoleScolaireGenPage = false;

	public EcoleScolaireGenPage initLoinEcoleScolaireGenPage(RequeteSiteFrFR requeteSite_) {
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
		listeEcoleInit();
		ecoleInit();
		pageUriEcoleInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinEcoleScolaireGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleScolaireGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteMiseEnPage(requeteSite_);
		if(listeEcole != null)
			listeEcole.setRequeteSite_(requeteSite_);
		if(ecole != null)
			ecole.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteEcoleScolaireGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
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
	public Object obtenirEcoleScolaireGenPage(String var) {
		EcoleScolaireGenPage oEcoleScolaireGenPage = (EcoleScolaireGenPage)this;
		switch(var) {
			case "listeEcole":
				return oEcoleScolaireGenPage.listeEcole;
			case "ecole":
				return oEcoleScolaireGenPage.ecole;
			case "pageUriEcole":
				return oEcoleScolaireGenPage.pageUriEcole;
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
		super.htmlScripts();
	}

	public void htmlScriptsEcoleScolaireGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleScolaireGenPage();
		super.htmlScript();
	}

	public void htmlScriptEcoleScolaireGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleScolaireGenPage();
		super.htmlBody();
	}

	public void htmlBodyEcoleScolaireGenPage() {
		if(ecole != null)
			ecole.htmlBody();
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEcoleScolaireGenPage();
		super.html();
	}

	public void htmlEcoleScolaireGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEcoleScolaireGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEcoleScolaireGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEcoleScolaireGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEcoleScolaireGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEcoleScolaireGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEcoleScolaireGenPage() {
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
		if(!(o instanceof EcoleScolaireGenPage))
			return false;
		EcoleScolaireGenPage that = (EcoleScolaireGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleScolaireGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
