package org.computate.scolaire.frFR.ecole;

import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecole.Ecole;
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
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleGenPageGen<DEV> extends ClusterPage {

	////////////////
	// listeEcole //
	////////////////

	/**	L'entité « listeEcole »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<Ecole> listeEcole;
	@JsonIgnore
	public Couverture<ListeRecherche<Ecole>> listeEcoleCouverture = new Couverture<ListeRecherche<Ecole>>().p(this).c(ListeRecherche.class).var("listeEcole").o(listeEcole);

	/**	<br/>L'entité « listeEcole »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeEcole">Trouver l'entité listeEcole dans Solr</a>
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
	protected EcoleGenPage listeEcoleInit() {
		if(!listeEcoleCouverture.dejaInitialise) {
			_listeEcole(listeEcoleCouverture);
			if(listeEcole == null)
				setListeEcole(listeEcoleCouverture.o);
		}
		if(listeEcole != null)
			listeEcole.initLoinPourClasse(requeteSite_);
		listeEcoleCouverture.dejaInitialise(true);
		return (EcoleGenPage)this;
	}

	///////////
	// ecole //
	///////////

	/**	L'entité « ecole »
	 *	 is defined as null before being initialized. 
	 */
	protected Ecole ecole;
	@JsonIgnore
	public Couverture<Ecole> ecoleCouverture = new Couverture<Ecole>().p(this).c(Ecole.class).var("ecole").o(ecole);

	/**	<br/>L'entité « ecole »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.EcoleGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecole">Trouver l'entité ecole dans Solr</a>
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
	protected EcoleGenPage ecoleInit() {
		if(!ecoleCouverture.dejaInitialise) {
			_ecole(ecoleCouverture);
			if(ecole == null)
				setEcole(ecoleCouverture.o);
		}
		if(ecole != null)
			ecole.initLoinPourClasse(requeteSite_);
		ecoleCouverture.dejaInitialise(true);
		return (EcoleGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcoleGenPage = false;

	public EcoleGenPage initLoinEcoleGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcoleGenPage) {
			dejaInitialiseEcoleGenPage = true;
			initLoinEcoleGenPage();
		}
		return (EcoleGenPage)this;
	}

	public void initLoinEcoleGenPage() {
		initEcoleGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initEcoleGenPage() {
		listeEcoleInit();
		ecoleInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinEcoleGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeEcole != null)
			listeEcole.setRequeteSite_(requeteSite_);
		if(ecole != null)
			ecole.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteEcoleGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcoleGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcoleGenPage(String var) {
		EcoleGenPage oEcoleGenPage = (EcoleGenPage)this;
		switch(var) {
			case "listeEcole":
				return oEcoleGenPage.listeEcole;
			case "ecole":
				return oEcoleGenPage.ecole;
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
				o = attribuerEcoleGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcoleGenPage(String var, Object val) {
		EcoleGenPage oEcoleGenPage = (EcoleGenPage)this;
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
					o = definirEcoleGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcoleGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEcoleGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsEcoleGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleGenPage();
		super.htmlScript();
	}

	public void htmlScriptEcoleGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleGenPage();
		super.htmlBody();
	}

	public void htmlBodyEcoleGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEcoleGenPage();
		super.html();
	}

	public void htmlEcoleGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEcoleGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEcoleGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEcoleGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEcoleGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEcoleGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEcoleGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiEcoleGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		EcoleGenPage original = (EcoleGenPage)Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
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
		if(!(o instanceof EcoleGenPage))
			return false;
		EcoleGenPage that = (EcoleGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
