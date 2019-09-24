package org.computate.scolaire.frFR.gardien;

import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.gardien.GardienScolaire;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class GardienGenPageGen<DEV> extends ClusterPage {

	//////////////////////////
	// listeGardienScolaire //
	//////////////////////////

	/**	L'entité « listeGardienScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<GardienScolaire> listeGardienScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<GardienScolaire>> listeGardienScolaireCouverture = new Couverture<ListeRecherche<GardienScolaire>>().p(this).c(ListeRecherche.class).var("listeGardienScolaire").o(listeGardienScolaire);

	/**	<br/>L'entité « listeGardienScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeGardienScolaire">Trouver l'entité listeGardienScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeGardienScolaire(Couverture<ListeRecherche<GardienScolaire>> c);

	public ListeRecherche<GardienScolaire> getListeGardienScolaire() {
		return listeGardienScolaire;
	}

	public void setListeGardienScolaire(ListeRecherche<GardienScolaire> listeGardienScolaire) {
		this.listeGardienScolaire = listeGardienScolaire;
		this.listeGardienScolaireCouverture.dejaInitialise = true;
	}
	protected GardienGenPage listeGardienScolaireInit() {
		if(!listeGardienScolaireCouverture.dejaInitialise) {
			_listeGardienScolaire(listeGardienScolaireCouverture);
			if(listeGardienScolaire == null)
				setListeGardienScolaire(listeGardienScolaireCouverture.o);
		}
		if(listeGardienScolaire != null)
			listeGardienScolaire.initLoinPourClasse(requeteSite_);
		listeGardienScolaireCouverture.dejaInitialise(true);
		return (GardienGenPage)this;
	}

	/////////////////////
	// gardienScolaire //
	/////////////////////

	/**	L'entité « gardienScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected GardienScolaire gardienScolaire;
	@JsonIgnore
	public Couverture<GardienScolaire> gardienScolaireCouverture = new Couverture<GardienScolaire>().p(this).c(GardienScolaire.class).var("gardienScolaire").o(gardienScolaire);

	/**	<br/>L'entité « gardienScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienScolaire">Trouver l'entité gardienScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gardienScolaire(Couverture<GardienScolaire> c);

	public GardienScolaire getGardienScolaire() {
		return gardienScolaire;
	}

	public void setGardienScolaire(GardienScolaire gardienScolaire) {
		this.gardienScolaire = gardienScolaire;
		this.gardienScolaireCouverture.dejaInitialise = true;
	}
	protected GardienGenPage gardienScolaireInit() {
		if(!gardienScolaireCouverture.dejaInitialise) {
			_gardienScolaire(gardienScolaireCouverture);
			if(gardienScolaire == null)
				setGardienScolaire(gardienScolaireCouverture.o);
		}
		if(gardienScolaire != null)
			gardienScolaire.initLoinPourClasse(requeteSite_);
		gardienScolaireCouverture.dejaInitialise(true);
		return (GardienGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseGardienGenPage = false;

	public GardienGenPage initLoinGardienGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseGardienGenPage) {
			dejaInitialiseGardienGenPage = true;
			initLoinGardienGenPage();
		}
		return (GardienGenPage)this;
	}

	public void initLoinGardienGenPage() {
		super.initLoinClusterPage(requeteSite_);
		initGardienGenPage();
	}

	public void initGardienGenPage() {
		listeGardienScolaireInit();
		gardienScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinGardienGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteGardienGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeGardienScolaire != null)
			listeGardienScolaire.setRequeteSite_(requeteSite_);
		if(gardienScolaire != null)
			gardienScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteGardienGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirGardienGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirGardienGenPage(String var) {
		GardienGenPage oGardienGenPage = (GardienGenPage)this;
		switch(var) {
			case "listeGardienScolaire":
				return oGardienGenPage.listeGardienScolaire;
			case "gardienScolaire":
				return oGardienGenPage.gardienScolaire;
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
				o = attribuerGardienGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerGardienGenPage(String var, Object val) {
		GardienGenPage oGardienGenPage = (GardienGenPage)this;
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
					o = definirGardienGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirGardienGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsGardienGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsGardienGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptGardienGenPage();
		super.htmlScript();
	}

	public void htmlScriptGardienGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyGardienGenPage();
		super.htmlBody();
	}

	public void htmlBodyGardienGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlGardienGenPage();
		super.html();
	}

	public void htmlGardienGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaGardienGenPage();
		super.htmlMeta();
	}

	public void htmlMetaGardienGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesGardienGenPage();
		super.htmlStyles();
	}

	public void htmlStylesGardienGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleGardienGenPage();
		super.htmlStyle();
	}

	public void htmlStyleGardienGenPage() {
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
		if(!(o instanceof GardienGenPage))
			return false;
		GardienGenPage that = (GardienGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("GardienGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
