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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.GenPageInscription&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class GenPageInscriptionGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GenPageInscription.class);

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.GenPageInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeInscriptionScolaire">Trouver l'entité listeInscriptionScolaire dans Solr</a>
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
	public static ListeRecherche<InscriptionScolaire> staticSetListeInscriptionScolaire(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected GenPageInscription listeInscriptionScolaireInit() {
		if(!listeInscriptionScolaireCouverture.dejaInitialise) {
			_listeInscriptionScolaire(listeInscriptionScolaireCouverture);
			if(listeInscriptionScolaire == null)
				setListeInscriptionScolaire(listeInscriptionScolaireCouverture.o);
		}
		if(listeInscriptionScolaire != null)
			listeInscriptionScolaire.initLoinPourClasse(requeteSite_);
		listeInscriptionScolaireCouverture.dejaInitialise(true);
		return (GenPageInscription)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.GenPageInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionScolaire_">Trouver l'entité inscriptionScolaire_ dans Solr</a>
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
	public static InscriptionScolaire staticSetInscriptionScolaire_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected GenPageInscription inscriptionScolaire_Init() {
		if(!inscriptionScolaire_Couverture.dejaInitialise) {
			_inscriptionScolaire_(inscriptionScolaire_Couverture);
			if(inscriptionScolaire_ == null)
				setInscriptionScolaire_(inscriptionScolaire_Couverture.o);
		}
		inscriptionScolaire_Couverture.dejaInitialise(true);
		return (GenPageInscription)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseGenPageInscription = false;

	public GenPageInscription initLoinGenPageInscription(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseGenPageInscription) {
			dejaInitialiseGenPageInscription = true;
			initLoinGenPageInscription();
		}
		return (GenPageInscription)this;
	}

	public void initLoinGenPageInscription() {
		initGenPageInscription();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initGenPageInscription() {
		listeInscriptionScolaireInit();
		inscriptionScolaire_Init();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinGenPageInscription(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteGenPageInscription(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeInscriptionScolaire != null)
			listeInscriptionScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteGenPageInscription(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirGenPageInscription(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirGenPageInscription(String var) {
		GenPageInscription oGenPageInscription = (GenPageInscription)this;
		switch(var) {
			case "listeInscriptionScolaire":
				return oGenPageInscription.listeInscriptionScolaire;
			case "inscriptionScolaire_":
				return oGenPageInscription.inscriptionScolaire_;
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
				o = attribuerGenPageInscription(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerGenPageInscription(String var, Object val) {
		GenPageInscription oGenPageInscription = (GenPageInscription)this;
		switch(var) {
			default:
				return super.attribuerClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetGenPageInscription(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetGenPageInscription(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSetClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrGenPageInscription(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrGenPageInscription(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrGenPageInscription(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrGenPageInscription(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqGenPageInscription(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqGenPageInscription(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrFqClusterPage(entiteVar,  requeteSite_, o);
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
					o = definirGenPageInscription(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirGenPageInscription(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsGenPageInscription();
		super.htmlScripts();
	}

	public void htmlScriptsGenPageInscription() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptGenPageInscription();
		super.htmlScript();
	}

	public void htmlScriptGenPageInscription() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyGenPageInscription();
		super.htmlBody();
	}

	public void htmlBodyGenPageInscription() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlGenPageInscription();
		super.html();
	}

	public void htmlGenPageInscription() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaGenPageInscription();
		super.htmlMeta();
	}

	public void htmlMetaGenPageInscription() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesGenPageInscription();
		super.htmlStyles();
	}

	public void htmlStylesGenPageInscription() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleGenPageInscription();
		super.htmlStyle();
	}

	public void htmlStyleGenPageInscription() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiGenPageInscription() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof GenPageInscription) {
			GenPageInscription original = (GenPageInscription)o;
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
		if(!(o instanceof GenPageInscription))
			return false;
		GenPageInscription that = (GenPageInscription)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("GenPageInscription { ");
		sb.append(" }");
		return sb.toString();
	}
}
