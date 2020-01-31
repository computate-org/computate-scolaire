package org.computate.scolaire.frFR.pere;

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
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.frFR.pere.PereScolaire;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.pere.PereGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PereGenPageGen<DEV> extends ClusterPage {

	///////////////////////
	// listePereScolaire //
	///////////////////////

	/**	L'entité « listePereScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<PereScolaire> listePereScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<PereScolaire>> listePereScolaireCouverture = new Couverture<ListeRecherche<PereScolaire>>().p(this).c(ListeRecherche.class).var("listePereScolaire").o(listePereScolaire);

	/**	<br/>L'entité « listePereScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.pere.PereGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listePereScolaire">Trouver l'entité listePereScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listePereScolaire(Couverture<ListeRecherche<PereScolaire>> c);

	public ListeRecherche<PereScolaire> getListePereScolaire() {
		return listePereScolaire;
	}

	public void setListePereScolaire(ListeRecherche<PereScolaire> listePereScolaire) {
		this.listePereScolaire = listePereScolaire;
		this.listePereScolaireCouverture.dejaInitialise = true;
	}
	protected PereGenPage listePereScolaireInit() {
		if(!listePereScolaireCouverture.dejaInitialise) {
			_listePereScolaire(listePereScolaireCouverture);
			if(listePereScolaire == null)
				setListePereScolaire(listePereScolaireCouverture.o);
		}
		if(listePereScolaire != null)
			listePereScolaire.initLoinPourClasse(requeteSite_);
		listePereScolaireCouverture.dejaInitialise(true);
		return (PereGenPage)this;
	}

	//////////////////
	// pereScolaire //
	//////////////////

	/**	L'entité « pereScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected PereScolaire pereScolaire;
	@JsonIgnore
	public Couverture<PereScolaire> pereScolaireCouverture = new Couverture<PereScolaire>().p(this).c(PereScolaire.class).var("pereScolaire").o(pereScolaire);

	/**	<br/>L'entité « pereScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.pere.PereGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereScolaire">Trouver l'entité pereScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pereScolaire(Couverture<PereScolaire> c);

	public PereScolaire getPereScolaire() {
		return pereScolaire;
	}

	public void setPereScolaire(PereScolaire pereScolaire) {
		this.pereScolaire = pereScolaire;
		this.pereScolaireCouverture.dejaInitialise = true;
	}
	protected PereGenPage pereScolaireInit() {
		if(!pereScolaireCouverture.dejaInitialise) {
			_pereScolaire(pereScolaireCouverture);
			if(pereScolaire == null)
				setPereScolaire(pereScolaireCouverture.o);
		}
		if(pereScolaire != null)
			pereScolaire.initLoinPourClasse(requeteSite_);
		pereScolaireCouverture.dejaInitialise(true);
		return (PereGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePereGenPage = false;

	public PereGenPage initLoinPereGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePereGenPage) {
			dejaInitialisePereGenPage = true;
			initLoinPereGenPage();
		}
		return (PereGenPage)this;
	}

	public void initLoinPereGenPage() {
		initPereGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initPereGenPage() {
		listePereScolaireInit();
		pereScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPereGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePereGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listePereScolaire != null)
			listePereScolaire.setRequeteSite_(requeteSite_);
		if(pereScolaire != null)
			pereScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePereGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPereGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPereGenPage(String var) {
		PereGenPage oPereGenPage = (PereGenPage)this;
		switch(var) {
			case "listePereScolaire":
				return oPereGenPage.listePereScolaire;
			case "pereScolaire":
				return oPereGenPage.pereScolaire;
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
				o = attribuerPereGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPereGenPage(String var, Object val) {
		PereGenPage oPereGenPage = (PereGenPage)this;
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
					o = definirPereGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPereGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPereGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsPereGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPereGenPage();
		super.htmlScript();
	}

	public void htmlScriptPereGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPereGenPage();
		super.htmlBody();
	}

	public void htmlBodyPereGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPereGenPage();
		super.html();
	}

	public void htmlPereGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPereGenPage();
		super.htmlMeta();
	}

	public void htmlMetaPereGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPereGenPage();
		super.htmlStyles();
	}

	public void htmlStylesPereGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePereGenPage();
		super.htmlStyle();
	}

	public void htmlStylePereGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiPereGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		PereGenPage original = (PereGenPage)Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
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
		if(!(o instanceof PereGenPage))
			return false;
		PereGenPage that = (PereGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PereGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
