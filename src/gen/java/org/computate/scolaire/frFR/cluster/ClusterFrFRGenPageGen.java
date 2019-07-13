package org.computate.scolaire.frFR.cluster;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.ClusterFrFRGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ClusterFrFRGenPageGen<DEV> extends MiseEnPage {

	//////////////////
	// listeCluster //
	//////////////////

	/**	L'entité « listeCluster »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<Cluster> listeCluster;
	public Couverture<ListeRecherche<Cluster>> listeClusterCouverture = new Couverture<ListeRecherche<Cluster>>().p(this).c(ListeRecherche.class).var("listeCluster").o(listeCluster);

	/**	<br/>L'entité « listeCluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.ClusterFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeCluster">Trouver l'entité listeCluster dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeCluster(Couverture<ListeRecherche<Cluster>> c);

	public ListeRecherche<Cluster> getListeCluster() {
		return listeCluster;
	}

	public void setListeCluster(ListeRecherche<Cluster> listeCluster) {
		this.listeCluster = listeCluster;
		this.listeClusterCouverture.dejaInitialise = true;
	}
	protected ClusterFrFRGenPage listeClusterInit() {
		if(!listeClusterCouverture.dejaInitialise) {
			_listeCluster(listeClusterCouverture);
			if(listeCluster == null)
				setListeCluster(listeClusterCouverture.o);
		}
		if(listeCluster != null)
			listeCluster.initLoinPourClasse(requeteSite_);
		listeClusterCouverture.dejaInitialise(true);
		return (ClusterFrFRGenPage)this;
	}

	/////////////
	// cluster //
	/////////////

	/**	L'entité « cluster »
	 *	 is defined as null before being initialized. 
	 */
	protected Cluster cluster;
	public Couverture<Cluster> clusterCouverture = new Couverture<Cluster>().p(this).c(Cluster.class).var("cluster").o(cluster);

	/**	<br/>L'entité « cluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.ClusterFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cluster">Trouver l'entité cluster dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cluster(Couverture<Cluster> c);

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
		this.clusterCouverture.dejaInitialise = true;
	}
	protected ClusterFrFRGenPage clusterInit() {
		if(!clusterCouverture.dejaInitialise) {
			_cluster(clusterCouverture);
			if(cluster == null)
				setCluster(clusterCouverture.o);
		}
		if(cluster != null)
			cluster.initLoinPourClasse(requeteSite_);
		clusterCouverture.dejaInitialise(true);
		return (ClusterFrFRGenPage)this;
	}

	////////////////////
	// pageUriCluster //
	////////////////////

	/**	L'entité « pageUriCluster »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriCluster;
	public Couverture<String> pageUriClusterCouverture = new Couverture<String>().p(this).c(String.class).var("pageUriCluster").o(pageUriCluster);

	/**	<br/>L'entité « pageUriCluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.ClusterFrFRGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUriCluster">Trouver l'entité pageUriCluster dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriCluster(Couverture<String> c);

	public String getPageUriCluster() {
		return pageUriCluster;
	}

	public void setPageUriCluster(String pageUriCluster) {
		this.pageUriCluster = pageUriCluster;
		this.pageUriClusterCouverture.dejaInitialise = true;
	}
	protected ClusterFrFRGenPage pageUriClusterInit() {
		if(!pageUriClusterCouverture.dejaInitialise) {
			_pageUriCluster(pageUriClusterCouverture);
			if(pageUriCluster == null)
				setPageUriCluster(pageUriClusterCouverture.o);
		}
		pageUriClusterCouverture.dejaInitialise(true);
		return (ClusterFrFRGenPage)this;
	}

	public String solrPageUriCluster() {
		return pageUriCluster;
	}

	public String strPageUriCluster() {
		return pageUriCluster == null ? "" : pageUriCluster;
	}

	public String nomAffichagePageUriCluster() {
		return null;
	}

	public String htmTooltipPageUriCluster() {
		return null;
	}

	public String htmPageUriCluster() {
		return pageUriCluster == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriCluster());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseClusterFrFRGenPage = false;

	public ClusterFrFRGenPage initLoinClusterFrFRGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseClusterFrFRGenPage) {
			dejaInitialiseClusterFrFRGenPage = true;
			initLoinClusterFrFRGenPage();
		}
		return (ClusterFrFRGenPage)this;
	}

	public void initLoinClusterFrFRGenPage() {
		super.initLoinMiseEnPage(requeteSite_);
		initClusterFrFRGenPage();
	}

	public void initClusterFrFRGenPage() {
		listeClusterInit();
		clusterInit();
		pageUriClusterInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinClusterFrFRGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteClusterFrFRGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteMiseEnPage(requeteSite_);
		if(listeCluster != null)
			listeCluster.setRequeteSite_(requeteSite_);
		if(cluster != null)
			cluster.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteClusterFrFRGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirClusterFrFRGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirClusterFrFRGenPage(String var) {
		ClusterFrFRGenPage oClusterFrFRGenPage = (ClusterFrFRGenPage)this;
		switch(var) {
			case "listeCluster":
				return oClusterFrFRGenPage.listeCluster;
			case "cluster":
				return oClusterFrFRGenPage.cluster;
			case "pageUriCluster":
				return oClusterFrFRGenPage.pageUriCluster;
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
				o = attribuerClusterFrFRGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerClusterFrFRGenPage(String var, Object val) {
		ClusterFrFRGenPage oClusterFrFRGenPage = (ClusterFrFRGenPage)this;
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
					o = definirClusterFrFRGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirClusterFrFRGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirMiseEnPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsClusterFrFRGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsClusterFrFRGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptClusterFrFRGenPage();
		super.htmlScript();
	}

	public void htmlScriptClusterFrFRGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyClusterFrFRGenPage();
		super.htmlBody();
	}

	public void htmlBodyClusterFrFRGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlClusterFrFRGenPage();
		super.html();
	}

	public void htmlClusterFrFRGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaClusterFrFRGenPage();
		super.htmlMeta();
	}

	public void htmlMetaClusterFrFRGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesClusterFrFRGenPage();
		super.htmlStyles();
	}

	public void htmlStylesClusterFrFRGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleClusterFrFRGenPage();
		super.htmlStyle();
	}

	public void htmlStyleClusterFrFRGenPage() {
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
		if(!(o instanceof ClusterFrFRGenPage))
			return false;
		ClusterFrFRGenPage that = (ClusterFrFRGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ClusterFrFRGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
