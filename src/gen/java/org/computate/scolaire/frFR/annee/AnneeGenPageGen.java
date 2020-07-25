package org.computate.scolaire.frFR.annee;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import java.math.RoundingMode;
import org.computate.scolaire.frFR.cluster.ClusterPage;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class AnneeGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AnneeGenPage.class);

	////////////////////////
	// listeAnneeScolaire //
	////////////////////////

	/**	 L'entité listeAnneeScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<AnneeScolaire> listeAnneeScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<AnneeScolaire>> listeAnneeScolaireCouverture = new Couverture<ListeRecherche<AnneeScolaire>>().p(this).c(ListeRecherche.class).var("listeAnneeScolaire").o(listeAnneeScolaire);

	/**	<br/> L'entité listeAnneeScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeAnneeScolaire">Trouver l'entité listeAnneeScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeAnneeScolaire(Couverture<ListeRecherche<AnneeScolaire>> c);

	public ListeRecherche<AnneeScolaire> getListeAnneeScolaire() {
		return listeAnneeScolaire;
	}

	public void setListeAnneeScolaire(ListeRecherche<AnneeScolaire> listeAnneeScolaire) {
		this.listeAnneeScolaire = listeAnneeScolaire;
		this.listeAnneeScolaireCouverture.dejaInitialise = true;
	}
	protected AnneeGenPage listeAnneeScolaireInit() {
		if(!listeAnneeScolaireCouverture.dejaInitialise) {
			_listeAnneeScolaire(listeAnneeScolaireCouverture);
			if(listeAnneeScolaire == null)
				setListeAnneeScolaire(listeAnneeScolaireCouverture.o);
		}
		if(listeAnneeScolaire != null)
			listeAnneeScolaire.initLoinPourClasse(requeteSite_);
		listeAnneeScolaireCouverture.dejaInitialise(true);
		return (AnneeGenPage)this;
	}

	///////////////////
	// anneeScolaire //
	///////////////////

	/**	 L'entité anneeScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AnneeScolaire anneeScolaire;
	@JsonIgnore
	public Couverture<AnneeScolaire> anneeScolaireCouverture = new Couverture<AnneeScolaire>().p(this).c(AnneeScolaire.class).var("anneeScolaire").o(anneeScolaire);

	/**	<br/> L'entité anneeScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeScolaire">Trouver l'entité anneeScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeScolaire(Couverture<AnneeScolaire> c);

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
		this.anneeScolaireCouverture.dejaInitialise = true;
	}
	protected AnneeGenPage anneeScolaireInit() {
		if(!anneeScolaireCouverture.dejaInitialise) {
			_anneeScolaire(anneeScolaireCouverture);
			if(anneeScolaire == null)
				setAnneeScolaire(anneeScolaireCouverture.o);
		}
		if(anneeScolaire != null)
			anneeScolaire.initLoinPourClasse(requeteSite_);
		anneeScolaireCouverture.dejaInitialise(true);
		return (AnneeGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAnneeGenPage = false;

	public AnneeGenPage initLoinAnneeGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAnneeGenPage) {
			dejaInitialiseAnneeGenPage = true;
			initLoinAnneeGenPage();
		}
		return (AnneeGenPage)this;
	}

	public void initLoinAnneeGenPage() {
		initAnneeGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initAnneeGenPage() {
		listeAnneeScolaireInit();
		anneeScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAnneeGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAnneeGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeAnneeScolaire != null)
			listeAnneeScolaire.setRequeteSite_(requeteSite_);
		if(anneeScolaire != null)
			anneeScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAnneeGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAnneeGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAnneeGenPage(String var) {
		AnneeGenPage oAnneeGenPage = (AnneeGenPage)this;
		switch(var) {
			case "listeAnneeScolaire":
				return oAnneeGenPage.listeAnneeScolaire;
			case "anneeScolaire":
				return oAnneeGenPage.anneeScolaire;
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
				o = attribuerAnneeGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAnneeGenPage(String var, Object val) {
		AnneeGenPage oAnneeGenPage = (AnneeGenPage)this;
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
					o = definirAnneeGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAnneeGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsAnneeGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsAnneeGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptAnneeGenPage();
		super.htmlScript();
	}

	public void htmlScriptAnneeGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyAnneeGenPage();
		super.htmlBody();
	}

	public void htmlBodyAnneeGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlAnneeGenPage();
		super.html();
	}

	public void htmlAnneeGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaAnneeGenPage();
		super.htmlMeta();
	}

	public void htmlMetaAnneeGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesAnneeGenPage();
		super.htmlStyles();
	}

	public void htmlStylesAnneeGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleAnneeGenPage();
		super.htmlStyle();
	}

	public void htmlStyleAnneeGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiAnneeGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof AnneeGenPage) {
			AnneeGenPage original = (AnneeGenPage)o;
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
		if(!(o instanceof AnneeGenPage))
			return false;
		AnneeGenPage that = (AnneeGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AnneeGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
