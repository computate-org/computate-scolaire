package org.computate.scolaire.frFR.inscription;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionFormGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class InscriptionFormGenPageGen<DEV> extends ClusterPage {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeInscriptionScolaire">Trouver l'entité listeInscriptionScolaire dans Solr</a>
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
	protected InscriptionFormGenPage listeInscriptionScolaireInit() {
		if(!listeInscriptionScolaireCouverture.dejaInitialise) {
			_listeInscriptionScolaire(listeInscriptionScolaireCouverture);
			if(listeInscriptionScolaire == null)
				setListeInscriptionScolaire(listeInscriptionScolaireCouverture.o);
		}
		if(listeInscriptionScolaire != null)
			listeInscriptionScolaire.initLoinPourClasse(requeteSite_);
		listeInscriptionScolaireCouverture.dejaInitialise(true);
		return (InscriptionFormGenPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionScolaire">Trouver l'entité inscriptionScolaire dans Solr</a>
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
	protected InscriptionFormGenPage inscriptionScolaireInit() {
		if(!inscriptionScolaireCouverture.dejaInitialise) {
			_inscriptionScolaire(inscriptionScolaireCouverture);
			if(inscriptionScolaire == null)
				setInscriptionScolaire(inscriptionScolaireCouverture.o);
		}
		if(inscriptionScolaire != null)
			inscriptionScolaire.initLoinPourClasse(requeteSite_);
		inscriptionScolaireCouverture.dejaInitialise(true);
		return (InscriptionFormGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseInscriptionFormGenPage = false;

	public InscriptionFormGenPage initLoinInscriptionFormGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseInscriptionFormGenPage) {
			dejaInitialiseInscriptionFormGenPage = true;
			initLoinInscriptionFormGenPage();
		}
		return (InscriptionFormGenPage)this;
	}

	public void initLoinInscriptionFormGenPage() {
		initInscriptionFormGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initInscriptionFormGenPage() {
		listeInscriptionScolaireInit();
		inscriptionScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinInscriptionFormGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteInscriptionFormGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeInscriptionScolaire != null)
			listeInscriptionScolaire.setRequeteSite_(requeteSite_);
		if(inscriptionScolaire != null)
			inscriptionScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteInscriptionFormGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirInscriptionFormGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirInscriptionFormGenPage(String var) {
		InscriptionFormGenPage oInscriptionFormGenPage = (InscriptionFormGenPage)this;
		switch(var) {
			case "listeInscriptionScolaire":
				return oInscriptionFormGenPage.listeInscriptionScolaire;
			case "inscriptionScolaire":
				return oInscriptionFormGenPage.inscriptionScolaire;
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
				o = attribuerInscriptionFormGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerInscriptionFormGenPage(String var, Object val) {
		InscriptionFormGenPage oInscriptionFormGenPage = (InscriptionFormGenPage)this;
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
					o = definirInscriptionFormGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirInscriptionFormGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsInscriptionFormGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsInscriptionFormGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptInscriptionFormGenPage();
		super.htmlScript();
	}

	public void htmlScriptInscriptionFormGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyInscriptionFormGenPage();
		super.htmlBody();
	}

	public void htmlBodyInscriptionFormGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlInscriptionFormGenPage();
		super.html();
	}

	public void htmlInscriptionFormGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaInscriptionFormGenPage();
		super.htmlMeta();
	}

	public void htmlMetaInscriptionFormGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesInscriptionFormGenPage();
		super.htmlStyles();
	}

	public void htmlStylesInscriptionFormGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleInscriptionFormGenPage();
		super.htmlStyle();
	}

	public void htmlStyleInscriptionFormGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiInscriptionFormGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		InscriptionFormGenPage original = (InscriptionFormGenPage)Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(original != null) {
			super.requeteApiClusterPage();
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
		if(!(o instanceof InscriptionFormGenPage))
			return false;
		InscriptionFormGenPage that = (InscriptionFormGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionFormGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
