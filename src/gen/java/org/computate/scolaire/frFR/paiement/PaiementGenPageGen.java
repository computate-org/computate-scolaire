package org.computate.scolaire.frFR.paiement;

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
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class PaiementGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PaiementGenPage.class);

	///////////////////////////
	// listePaiementScolaire //
	///////////////////////////

	/**	 L'entité listePaiementScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<PaiementScolaire> listePaiementScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<PaiementScolaire>> listePaiementScolaireCouverture = new Couverture<ListeRecherche<PaiementScolaire>>().p(this).c(ListeRecherche.class).var("listePaiementScolaire").o(listePaiementScolaire);

	/**	<br/> L'entité listePaiementScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listePaiementScolaire">Trouver l'entité listePaiementScolaire dans Solr</a>
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
	public static ListeRecherche<PaiementScolaire> staticSetListePaiementScolaire(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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

	///////////////////////
	// paiementScolaire_ //
	///////////////////////

	/**	 L'entité paiementScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PaiementScolaire paiementScolaire_;
	@JsonIgnore
	public Couverture<PaiementScolaire> paiementScolaire_Couverture = new Couverture<PaiementScolaire>().p(this).c(PaiementScolaire.class).var("paiementScolaire_").o(paiementScolaire_);

	/**	<br/> L'entité paiementScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementScolaire_">Trouver l'entité paiementScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementScolaire_(Couverture<PaiementScolaire> c);

	public PaiementScolaire getPaiementScolaire_() {
		return paiementScolaire_;
	}

	public void setPaiementScolaire_(PaiementScolaire paiementScolaire_) {
		this.paiementScolaire_ = paiementScolaire_;
		this.paiementScolaire_Couverture.dejaInitialise = true;
	}
	public static PaiementScolaire staticSetPaiementScolaire_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected PaiementGenPage paiementScolaire_Init() {
		if(!paiementScolaire_Couverture.dejaInitialise) {
			_paiementScolaire_(paiementScolaire_Couverture);
			if(paiementScolaire_ == null)
				setPaiementScolaire_(paiementScolaire_Couverture.o);
		}
		paiementScolaire_Couverture.dejaInitialise(true);
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
		paiementScolaire_Init();
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
			case "paiementScolaire_":
				return oPaiementGenPage.paiementScolaire_;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetPaiementGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetPaiementGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSetClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrPaiementGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrPaiementGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrPaiementGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrPaiementGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqPaiementGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqPaiementGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
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
	// requeteApi //
	//////////////////

	public void requeteApiPaiementGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof PaiementGenPage) {
			PaiementGenPage original = (PaiementGenPage)o;
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
