package org.computate.scolaire.frFR.age;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.age.AgeScolaire;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AgeGenPageGen<DEV> extends ClusterPage {

	//////////////////////
	// listeAgeScolaire //
	//////////////////////

	/**	L'entité « listeAgeScolaire »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<AgeScolaire> listeAgeScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<AgeScolaire>> listeAgeScolaireCouverture = new Couverture<ListeRecherche<AgeScolaire>>().p(this).c(ListeRecherche.class).var("listeAgeScolaire").o(listeAgeScolaire);

	/**	<br/>L'entité « listeAgeScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeAgeScolaire">Trouver l'entité listeAgeScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeAgeScolaire(Couverture<ListeRecherche<AgeScolaire>> c);

	public ListeRecherche<AgeScolaire> getListeAgeScolaire() {
		return listeAgeScolaire;
	}

	public void setListeAgeScolaire(ListeRecherche<AgeScolaire> listeAgeScolaire) {
		this.listeAgeScolaire = listeAgeScolaire;
		this.listeAgeScolaireCouverture.dejaInitialise = true;
	}
	protected AgeGenPage listeAgeScolaireInit() {
		if(!listeAgeScolaireCouverture.dejaInitialise) {
			_listeAgeScolaire(listeAgeScolaireCouverture);
			if(listeAgeScolaire == null)
				setListeAgeScolaire(listeAgeScolaireCouverture.o);
		}
		if(listeAgeScolaire != null)
			listeAgeScolaire.initLoinPourClasse(requeteSite_);
		listeAgeScolaireCouverture.dejaInitialise(true);
		return (AgeGenPage)this;
	}

	/////////////////
	// ageScolaire //
	/////////////////

	/**	L'entité « ageScolaire »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AgeScolaire ageScolaire;
	@JsonIgnore
	public Couverture<AgeScolaire> ageScolaireCouverture = new Couverture<AgeScolaire>().p(this).c(AgeScolaire.class).var("ageScolaire").o(ageScolaire);

	/**	<br/>L'entité « ageScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageScolaire">Trouver l'entité ageScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageScolaire(Couverture<AgeScolaire> c);

	public AgeScolaire getAgeScolaire() {
		return ageScolaire;
	}

	public void setAgeScolaire(AgeScolaire ageScolaire) {
		this.ageScolaire = ageScolaire;
		this.ageScolaireCouverture.dejaInitialise = true;
	}
	protected AgeGenPage ageScolaireInit() {
		if(!ageScolaireCouverture.dejaInitialise) {
			_ageScolaire(ageScolaireCouverture);
			if(ageScolaire == null)
				setAgeScolaire(ageScolaireCouverture.o);
		}
		if(ageScolaire != null)
			ageScolaire.initLoinPourClasse(requeteSite_);
		ageScolaireCouverture.dejaInitialise(true);
		return (AgeGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAgeGenPage = false;

	public AgeGenPage initLoinAgeGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAgeGenPage) {
			dejaInitialiseAgeGenPage = true;
			initLoinAgeGenPage();
		}
		return (AgeGenPage)this;
	}

	public void initLoinAgeGenPage() {
		initAgeGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initAgeGenPage() {
		listeAgeScolaireInit();
		ageScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAgeGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAgeGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeAgeScolaire != null)
			listeAgeScolaire.setRequeteSite_(requeteSite_);
		if(ageScolaire != null)
			ageScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAgeGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAgeGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAgeGenPage(String var) {
		AgeGenPage oAgeGenPage = (AgeGenPage)this;
		switch(var) {
			case "listeAgeScolaire":
				return oAgeGenPage.listeAgeScolaire;
			case "ageScolaire":
				return oAgeGenPage.ageScolaire;
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
				o = attribuerAgeGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAgeGenPage(String var, Object val) {
		AgeGenPage oAgeGenPage = (AgeGenPage)this;
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
					o = definirAgeGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAgeGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsAgeGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsAgeGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptAgeGenPage();
		super.htmlScript();
	}

	public void htmlScriptAgeGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyAgeGenPage();
		super.htmlBody();
	}

	public void htmlBodyAgeGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlAgeGenPage();
		super.html();
	}

	public void htmlAgeGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaAgeGenPage();
		super.htmlMeta();
	}

	public void htmlMetaAgeGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesAgeGenPage();
		super.htmlStyles();
	}

	public void htmlStylesAgeGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleAgeGenPage();
		super.htmlStyle();
	}

	public void htmlStyleAgeGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiAgeGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof AgeGenPage) {
			AgeGenPage original = (AgeGenPage)o;
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
		if(!(o instanceof AgeGenPage))
			return false;
		AgeGenPage that = (AgeGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AgeGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
