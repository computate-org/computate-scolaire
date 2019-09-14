package org.computate.scolaire.frFR.saison;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.cluster.Cluster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.saison.SaisonScolaire;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SaisonGenPageGen<DEV> extends ClusterPage {

	/////////////////////////
	// listeSaisonScolaire //
	/////////////////////////

	/**	L'entité « listeSaisonScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<SaisonScolaire> listeSaisonScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<SaisonScolaire>> listeSaisonScolaireCouverture = new Couverture<ListeRecherche<SaisonScolaire>>().p(this).c(ListeRecherche.class).var("listeSaisonScolaire").o(listeSaisonScolaire);

	/**	<br/>L'entité « listeSaisonScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeSaisonScolaire">Trouver l'entité listeSaisonScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeSaisonScolaire(Couverture<ListeRecherche<SaisonScolaire>> c);

	public ListeRecherche<SaisonScolaire> getListeSaisonScolaire() {
		return listeSaisonScolaire;
	}

	public void setListeSaisonScolaire(ListeRecherche<SaisonScolaire> listeSaisonScolaire) {
		this.listeSaisonScolaire = listeSaisonScolaire;
		this.listeSaisonScolaireCouverture.dejaInitialise = true;
	}
	protected SaisonGenPage listeSaisonScolaireInit() {
		if(!listeSaisonScolaireCouverture.dejaInitialise) {
			_listeSaisonScolaire(listeSaisonScolaireCouverture);
			if(listeSaisonScolaire == null)
				setListeSaisonScolaire(listeSaisonScolaireCouverture.o);
		}
		if(listeSaisonScolaire != null)
			listeSaisonScolaire.initLoinPourClasse(requeteSite_);
		listeSaisonScolaireCouverture.dejaInitialise(true);
		return (SaisonGenPage)this;
	}

	////////////////////
	// saisonScolaire //
	////////////////////

	/**	L'entité « saisonScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected SaisonScolaire saisonScolaire;
	@JsonIgnore
	public Couverture<SaisonScolaire> saisonScolaireCouverture = new Couverture<SaisonScolaire>().p(this).c(SaisonScolaire.class).var("saisonScolaire").o(saisonScolaire);

	/**	<br/>L'entité « saisonScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonScolaire">Trouver l'entité saisonScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonScolaire(Couverture<SaisonScolaire> c);

	public SaisonScolaire getSaisonScolaire() {
		return saisonScolaire;
	}

	public void setSaisonScolaire(SaisonScolaire saisonScolaire) {
		this.saisonScolaire = saisonScolaire;
		this.saisonScolaireCouverture.dejaInitialise = true;
	}
	protected SaisonGenPage saisonScolaireInit() {
		if(!saisonScolaireCouverture.dejaInitialise) {
			_saisonScolaire(saisonScolaireCouverture);
			if(saisonScolaire == null)
				setSaisonScolaire(saisonScolaireCouverture.o);
		}
		if(saisonScolaire != null)
			saisonScolaire.initLoinPourClasse(requeteSite_);
		saisonScolaireCouverture.dejaInitialise(true);
		return (SaisonGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSaisonGenPage = false;

	public SaisonGenPage initLoinSaisonGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSaisonGenPage) {
			dejaInitialiseSaisonGenPage = true;
			initLoinSaisonGenPage();
		}
		return (SaisonGenPage)this;
	}

	public void initLoinSaisonGenPage() {
		super.initLoinClusterPage(requeteSite_);
		initSaisonGenPage();
	}

	public void initSaisonGenPage() {
		listeSaisonScolaireInit();
		saisonScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSaisonGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSaisonGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeSaisonScolaire != null)
			listeSaisonScolaire.setRequeteSite_(requeteSite_);
		if(saisonScolaire != null)
			saisonScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteSaisonGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSaisonGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSaisonGenPage(String var) {
		SaisonGenPage oSaisonGenPage = (SaisonGenPage)this;
		switch(var) {
			case "listeSaisonScolaire":
				return oSaisonGenPage.listeSaisonScolaire;
			case "saisonScolaire":
				return oSaisonGenPage.saisonScolaire;
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
				o = attribuerSaisonGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSaisonGenPage(String var, Object val) {
		SaisonGenPage oSaisonGenPage = (SaisonGenPage)this;
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
					o = definirSaisonGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSaisonGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSaisonGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsSaisonGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSaisonGenPage();
		super.htmlScript();
	}

	public void htmlScriptSaisonGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySaisonGenPage();
		super.htmlBody();
	}

	public void htmlBodySaisonGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSaisonGenPage();
		super.html();
	}

	public void htmlSaisonGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSaisonGenPage();
		super.htmlMeta();
	}

	public void htmlMetaSaisonGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSaisonGenPage();
		super.htmlStyles();
	}

	public void htmlStylesSaisonGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSaisonGenPage();
		super.htmlStyle();
	}

	public void htmlStyleSaisonGenPage() {
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
		if(!(o instanceof SaisonGenPage))
			return false;
		SaisonGenPage that = (SaisonGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SaisonGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}