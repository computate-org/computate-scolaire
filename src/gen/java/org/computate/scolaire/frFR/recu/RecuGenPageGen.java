package org.computate.scolaire.frFR.recu;

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
import java.math.RoundingMode;
import org.computate.scolaire.frFR.cluster.ClusterPage;
import java.math.MathContext;
import org.computate.scolaire.frFR.recu.RecuScolaire;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class RecuGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(RecuGenPage.class);

	///////////////////////
	// listeRecuScolaire //
	///////////////////////

	/**	 L'entité listeRecuScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<RecuScolaire> listeRecuScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<RecuScolaire>> listeRecuScolaireCouverture = new Couverture<ListeRecherche<RecuScolaire>>().p(this).c(ListeRecherche.class).var("listeRecuScolaire").o(listeRecuScolaire);

	/**	<br/> L'entité listeRecuScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeRecuScolaire">Trouver l'entité listeRecuScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeRecuScolaire(Couverture<ListeRecherche<RecuScolaire>> c);

	public ListeRecherche<RecuScolaire> getListeRecuScolaire() {
		return listeRecuScolaire;
	}

	public void setListeRecuScolaire(ListeRecherche<RecuScolaire> listeRecuScolaire) {
		this.listeRecuScolaire = listeRecuScolaire;
		this.listeRecuScolaireCouverture.dejaInitialise = true;
	}
	public static ListeRecherche<RecuScolaire> staticSetListeRecuScolaire(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected RecuGenPage listeRecuScolaireInit() {
		if(!listeRecuScolaireCouverture.dejaInitialise) {
			_listeRecuScolaire(listeRecuScolaireCouverture);
			if(listeRecuScolaire == null)
				setListeRecuScolaire(listeRecuScolaireCouverture.o);
		}
		if(listeRecuScolaire != null)
			listeRecuScolaire.initLoinPourClasse(requeteSite_);
		listeRecuScolaireCouverture.dejaInitialise(true);
		return (RecuGenPage)this;
	}

	///////////////////
	// recuScolaire_ //
	///////////////////

	/**	 L'entité recuScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected RecuScolaire recuScolaire_;
	@JsonIgnore
	public Couverture<RecuScolaire> recuScolaire_Couverture = new Couverture<RecuScolaire>().p(this).c(RecuScolaire.class).var("recuScolaire_").o(recuScolaire_);

	/**	<br/> L'entité recuScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:recuScolaire_">Trouver l'entité recuScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _recuScolaire_(Couverture<RecuScolaire> c);

	public RecuScolaire getRecuScolaire_() {
		return recuScolaire_;
	}

	public void setRecuScolaire_(RecuScolaire recuScolaire_) {
		this.recuScolaire_ = recuScolaire_;
		this.recuScolaire_Couverture.dejaInitialise = true;
	}
	public static RecuScolaire staticSetRecuScolaire_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected RecuGenPage recuScolaire_Init() {
		if(!recuScolaire_Couverture.dejaInitialise) {
			_recuScolaire_(recuScolaire_Couverture);
			if(recuScolaire_ == null)
				setRecuScolaire_(recuScolaire_Couverture.o);
		}
		recuScolaire_Couverture.dejaInitialise(true);
		return (RecuGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseRecuGenPage = false;

	public RecuGenPage initLoinRecuGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseRecuGenPage) {
			dejaInitialiseRecuGenPage = true;
			initLoinRecuGenPage();
		}
		return (RecuGenPage)this;
	}

	public void initLoinRecuGenPage() {
		initRecuGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initRecuGenPage() {
		listeRecuScolaireInit();
		recuScolaire_Init();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinRecuGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteRecuGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeRecuScolaire != null)
			listeRecuScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteRecuGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirRecuGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirRecuGenPage(String var) {
		RecuGenPage oRecuGenPage = (RecuGenPage)this;
		switch(var) {
			case "listeRecuScolaire":
				return oRecuGenPage.listeRecuScolaire;
			case "recuScolaire_":
				return oRecuGenPage.recuScolaire_;
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
				o = attribuerRecuGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerRecuGenPage(String var, Object val) {
		RecuGenPage oRecuGenPage = (RecuGenPage)this;
		switch(var) {
			default:
				return super.attribuerClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetRecuGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetRecuGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSetClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrRecuGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrRecuGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrRecuGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrRecuGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqRecuGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqRecuGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
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
					o = definirRecuGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRecuGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsRecuGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsRecuGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptRecuGenPage();
		super.htmlScript();
	}

	public void htmlScriptRecuGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyRecuGenPage();
		super.htmlBody();
	}

	public void htmlBodyRecuGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlRecuGenPage();
		super.html();
	}

	public void htmlRecuGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaRecuGenPage();
		super.htmlMeta();
	}

	public void htmlMetaRecuGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesRecuGenPage();
		super.htmlStyles();
	}

	public void htmlStylesRecuGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleRecuGenPage();
		super.htmlStyle();
	}

	public void htmlStyleRecuGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiRecuGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof RecuGenPage) {
			RecuGenPage original = (RecuGenPage)o;
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
		if(!(o instanceof RecuGenPage))
			return false;
		RecuGenPage that = (RecuGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("RecuGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
