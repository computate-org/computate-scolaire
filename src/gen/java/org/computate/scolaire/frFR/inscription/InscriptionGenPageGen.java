package org.computate.scolaire.frFR.inscription;

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
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class InscriptionGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(InscriptionGenPage.class);

	//////////////////////////////
	// listeInscriptionScolaire //
	//////////////////////////////

	/**	 L'entité listeInscriptionScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<InscriptionScolaire> listeInscriptionScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<InscriptionScolaire>> listeInscriptionScolaireCouverture = new Couverture<ListeRecherche<InscriptionScolaire>>().p(this).c(ListeRecherche.class).var("listeInscriptionScolaire").o(listeInscriptionScolaire);

	/**	<br/> L'entité listeInscriptionScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeInscriptionScolaire">Trouver l'entité listeInscriptionScolaire dans Solr</a>
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

	//////////////////////////
	// inscriptionScolaire_ //
	//////////////////////////

	/**	 L'entité inscriptionScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected InscriptionScolaire inscriptionScolaire_;
	@JsonIgnore
	public Couverture<InscriptionScolaire> inscriptionScolaire_Couverture = new Couverture<InscriptionScolaire>().p(this).c(InscriptionScolaire.class).var("inscriptionScolaire_").o(inscriptionScolaire_);

	/**	<br/> L'entité inscriptionScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionScolaire_">Trouver l'entité inscriptionScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionScolaire_(Couverture<InscriptionScolaire> c);

	public InscriptionScolaire getInscriptionScolaire_() {
		return inscriptionScolaire_;
	}

	public void setInscriptionScolaire_(InscriptionScolaire inscriptionScolaire_) {
		this.inscriptionScolaire_ = inscriptionScolaire_;
		this.inscriptionScolaire_Couverture.dejaInitialise = true;
	}
	protected InscriptionGenPage inscriptionScolaire_Init() {
		if(!inscriptionScolaire_Couverture.dejaInitialise) {
			_inscriptionScolaire_(inscriptionScolaire_Couverture);
			if(inscriptionScolaire_ == null)
				setInscriptionScolaire_(inscriptionScolaire_Couverture.o);
		}
		inscriptionScolaire_Couverture.dejaInitialise(true);
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
		inscriptionScolaire_Init();
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
			case "inscriptionScolaire_":
				return oInscriptionGenPage.inscriptionScolaire_;
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
	// requeteApi //
	//////////////////

	public void requeteApiInscriptionGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof InscriptionGenPage) {
			InscriptionGenPage original = (InscriptionGenPage)o;
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
