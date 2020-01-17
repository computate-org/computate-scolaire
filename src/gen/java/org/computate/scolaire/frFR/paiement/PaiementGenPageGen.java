package org.computate.scolaire.frFR.paiement;

import org.computate.scolaire.frFR.requete.patch.RequetePatch;
import java.math.MathContext;
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
import java.util.Optional;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PaiementGenPageGen<DEV> extends ClusterPage {

	///////////////////////////
	// listePaiementScolaire //
	///////////////////////////

	/**	L'entité « listePaiementScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<PaiementScolaire> listePaiementScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<PaiementScolaire>> listePaiementScolaireCouverture = new Couverture<ListeRecherche<PaiementScolaire>>().p(this).c(ListeRecherche.class).var("listePaiementScolaire").o(listePaiementScolaire);

	/**	<br/>L'entité « listePaiementScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listePaiementScolaire">Trouver l'entité listePaiementScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listePaiementScolaire(Couverture<ListeRecherche<PaiementScolaire>> c);

	public ListeRecherche<PaiementScolaire> getListePaiementScolaire() {
		return listePaiementScolaire;
	}

	public void setListePaiementScolaire(ListeRecherche<PaiementScolaire> listePaiementScolaire) {
		this.listePaiementScolaire = listePaiementScolaire;
		this.listePaiementScolaireCouverture.dejaInitialise = true;
	}
	protected PaiementGenPage listePaiementScolaireInit() {
		if(!listePaiementScolaireCouverture.dejaInitialise) {
			_listePaiementScolaire(listePaiementScolaireCouverture);
			if(listePaiementScolaire == null)
				setListePaiementScolaire(listePaiementScolaireCouverture.o);
		}
		if(listePaiementScolaire != null)
			listePaiementScolaire.initLoinPourClasse(requeteSite_);
		listePaiementScolaireCouverture.dejaInitialise(true);
		return (PaiementGenPage)this;
	}

	//////////////////////
	// paiementScolaire //
	//////////////////////

	/**	L'entité « paiementScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected PaiementScolaire paiementScolaire;
	@JsonIgnore
	public Couverture<PaiementScolaire> paiementScolaireCouverture = new Couverture<PaiementScolaire>().p(this).c(PaiementScolaire.class).var("paiementScolaire").o(paiementScolaire);

	/**	<br/>L'entité « paiementScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementScolaire">Trouver l'entité paiementScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementScolaire(Couverture<PaiementScolaire> c);

	public PaiementScolaire getPaiementScolaire() {
		return paiementScolaire;
	}

	public void setPaiementScolaire(PaiementScolaire paiementScolaire) {
		this.paiementScolaire = paiementScolaire;
		this.paiementScolaireCouverture.dejaInitialise = true;
	}
	protected PaiementGenPage paiementScolaireInit() {
		if(!paiementScolaireCouverture.dejaInitialise) {
			_paiementScolaire(paiementScolaireCouverture);
			if(paiementScolaire == null)
				setPaiementScolaire(paiementScolaireCouverture.o);
		}
		if(paiementScolaire != null)
			paiementScolaire.initLoinPourClasse(requeteSite_);
		paiementScolaireCouverture.dejaInitialise(true);
		return (PaiementGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePaiementGenPage = false;

	public PaiementGenPage initLoinPaiementGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePaiementGenPage) {
			dejaInitialisePaiementGenPage = true;
			initLoinPaiementGenPage();
		}
		return (PaiementGenPage)this;
	}

	public void initLoinPaiementGenPage() {
		initPaiementGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initPaiementGenPage() {
		listePaiementScolaireInit();
		paiementScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPaiementGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePaiementGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listePaiementScolaire != null)
			listePaiementScolaire.setRequeteSite_(requeteSite_);
		if(paiementScolaire != null)
			paiementScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePaiementGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPaiementGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPaiementGenPage(String var) {
		PaiementGenPage oPaiementGenPage = (PaiementGenPage)this;
		switch(var) {
			case "listePaiementScolaire":
				return oPaiementGenPage.listePaiementScolaire;
			case "paiementScolaire":
				return oPaiementGenPage.paiementScolaire;
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
				o = attribuerPaiementGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPaiementGenPage(String var, Object val) {
		PaiementGenPage oPaiementGenPage = (PaiementGenPage)this;
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
					o = definirPaiementGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPaiementGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPaiementGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsPaiementGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPaiementGenPage();
		super.htmlScript();
	}

	public void htmlScriptPaiementGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPaiementGenPage();
		super.htmlBody();
	}

	public void htmlBodyPaiementGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPaiementGenPage();
		super.html();
	}

	public void htmlPaiementGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPaiementGenPage();
		super.htmlMeta();
	}

	public void htmlMetaPaiementGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPaiementGenPage();
		super.htmlStyles();
	}

	public void htmlStylesPaiementGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePaiementGenPage();
		super.htmlStyle();
	}

	public void htmlStylePaiementGenPage() {
	}

	//////////////////
	// requetePatch //
	//////////////////

	public void requetePatchPaiementGenPage() {
		RequetePatch requetePatch = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequetePatch_).orElse(null);
		PaiementGenPage original = (PaiementGenPage)Optional.ofNullable(requetePatch).map(RequetePatch::getOriginal).orElse(null);
		if(original != null) {
			super.requetePatchClusterPage();
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
		if(!(o instanceof PaiementGenPage))
			return false;
		PaiementGenPage that = (PaiementGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PaiementGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
