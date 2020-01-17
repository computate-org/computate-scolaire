package org.computate.scolaire.frFR.inscription;

import org.computate.scolaire.frFR.requete.patch.RequetePatch;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class InscriptionGenPageGen<DEV> extends ClusterPage {

	//////////////////////////////
	// listeInscriptionScolaire //
	//////////////////////////////

	/**	L'entité « listeInscriptionScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<InscriptionScolaire> listeInscriptionScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<InscriptionScolaire>> listeInscriptionScolaireCouverture = new Couverture<ListeRecherche<InscriptionScolaire>>().p(this).c(ListeRecherche.class).var("listeInscriptionScolaire").o(listeInscriptionScolaire);

	/**	<br/>L'entité « listeInscriptionScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeInscriptionScolaire">Trouver l'entité listeInscriptionScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeInscriptionScolaire(Couverture<ListeRecherche<InscriptionScolaire>> c);

	public ListeRecherche<InscriptionScolaire> getListeInscriptionScolaire() {
		return listeInscriptionScolaire;
	}

	public void setListeInscriptionScolaire(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire) {
		this.listeInscriptionScolaire = listeInscriptionScolaire;
		this.listeInscriptionScolaireCouverture.dejaInitialise = true;
	}
	protected InscriptionGenPage listeInscriptionScolaireInit() {
		if(!listeInscriptionScolaireCouverture.dejaInitialise) {
			_listeInscriptionScolaire(listeInscriptionScolaireCouverture);
			if(listeInscriptionScolaire == null)
				setListeInscriptionScolaire(listeInscriptionScolaireCouverture.o);
		}
		if(listeInscriptionScolaire != null)
			listeInscriptionScolaire.initLoinPourClasse(requeteSite_);
		listeInscriptionScolaireCouverture.dejaInitialise(true);
		return (InscriptionGenPage)this;
	}

	/////////////////////////
	// inscriptionScolaire //
	/////////////////////////

	/**	L'entité « inscriptionScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected InscriptionScolaire inscriptionScolaire;
	@JsonIgnore
	public Couverture<InscriptionScolaire> inscriptionScolaireCouverture = new Couverture<InscriptionScolaire>().p(this).c(InscriptionScolaire.class).var("inscriptionScolaire").o(inscriptionScolaire);

	/**	<br/>L'entité « inscriptionScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionScolaire">Trouver l'entité inscriptionScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionScolaire(Couverture<InscriptionScolaire> c);

	public InscriptionScolaire getInscriptionScolaire() {
		return inscriptionScolaire;
	}

	public void setInscriptionScolaire(InscriptionScolaire inscriptionScolaire) {
		this.inscriptionScolaire = inscriptionScolaire;
		this.inscriptionScolaireCouverture.dejaInitialise = true;
	}
	protected InscriptionGenPage inscriptionScolaireInit() {
		if(!inscriptionScolaireCouverture.dejaInitialise) {
			_inscriptionScolaire(inscriptionScolaireCouverture);
			if(inscriptionScolaire == null)
				setInscriptionScolaire(inscriptionScolaireCouverture.o);
		}
		if(inscriptionScolaire != null)
			inscriptionScolaire.initLoinPourClasse(requeteSite_);
		inscriptionScolaireCouverture.dejaInitialise(true);
		return (InscriptionGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseInscriptionGenPage = false;

	public InscriptionGenPage initLoinInscriptionGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseInscriptionGenPage) {
			dejaInitialiseInscriptionGenPage = true;
			initLoinInscriptionGenPage();
		}
		return (InscriptionGenPage)this;
	}

	public void initLoinInscriptionGenPage() {
		initInscriptionGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initInscriptionGenPage() {
		listeInscriptionScolaireInit();
		inscriptionScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinInscriptionGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteInscriptionGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeInscriptionScolaire != null)
			listeInscriptionScolaire.setRequeteSite_(requeteSite_);
		if(inscriptionScolaire != null)
			inscriptionScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteInscriptionGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirInscriptionGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirInscriptionGenPage(String var) {
		InscriptionGenPage oInscriptionGenPage = (InscriptionGenPage)this;
		switch(var) {
			case "listeInscriptionScolaire":
				return oInscriptionGenPage.listeInscriptionScolaire;
			case "inscriptionScolaire":
				return oInscriptionGenPage.inscriptionScolaire;
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
				o = attribuerInscriptionGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerInscriptionGenPage(String var, Object val) {
		InscriptionGenPage oInscriptionGenPage = (InscriptionGenPage)this;
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
					o = definirInscriptionGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirInscriptionGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsInscriptionGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsInscriptionGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptInscriptionGenPage();
		super.htmlScript();
	}

	public void htmlScriptInscriptionGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyInscriptionGenPage();
		super.htmlBody();
	}

	public void htmlBodyInscriptionGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlInscriptionGenPage();
		super.html();
	}

	public void htmlInscriptionGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaInscriptionGenPage();
		super.htmlMeta();
	}

	public void htmlMetaInscriptionGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesInscriptionGenPage();
		super.htmlStyles();
	}

	public void htmlStylesInscriptionGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleInscriptionGenPage();
		super.htmlStyle();
	}

	public void htmlStyleInscriptionGenPage() {
	}

	//////////////////
	// requetePatch //
	//////////////////

	public void requetePatchInscriptionGenPage() {
		RequetePatch requetePatch = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequetePatch_).orElse(null);
		InscriptionGenPage original = (InscriptionGenPage)Optional.ofNullable(requetePatch).map(RequetePatch::getOriginal).orElse(null);
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
		if(!(o instanceof InscriptionGenPage))
			return false;
		InscriptionGenPage that = (InscriptionGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
