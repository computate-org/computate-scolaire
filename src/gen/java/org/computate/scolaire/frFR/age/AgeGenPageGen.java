package org.computate.scolaire.frFR.age;

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
import java.util.Map;
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
import org.computate.scolaire.frFR.age.AgeScolaire;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class AgeGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AgeGenPage.class);

	//////////////////////
	// listeAgeScolaire //
	//////////////////////

	/**	 L'entité listeAgeScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<AgeScolaire> listeAgeScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<AgeScolaire>> listeAgeScolaireCouverture = new Couverture<ListeRecherche<AgeScolaire>>().p(this).c(ListeRecherche.class).var("listeAgeScolaire").o(listeAgeScolaire);

	/**	<br/> L'entité listeAgeScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeAgeScolaire">Trouver l'entité listeAgeScolaire dans Solr</a>
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
	public static ListeRecherche<AgeScolaire> staticSetListeAgeScolaire(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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

	//////////////////
	// ageScolaire_ //
	//////////////////

	/**	 L'entité ageScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AgeScolaire ageScolaire_;
	@JsonIgnore
	public Couverture<AgeScolaire> ageScolaire_Couverture = new Couverture<AgeScolaire>().p(this).c(AgeScolaire.class).var("ageScolaire_").o(ageScolaire_);

	/**	<br/> L'entité ageScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageScolaire_">Trouver l'entité ageScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageScolaire_(Couverture<AgeScolaire> c);

	public AgeScolaire getAgeScolaire_() {
		return ageScolaire_;
	}

	public void setAgeScolaire_(AgeScolaire ageScolaire_) {
		this.ageScolaire_ = ageScolaire_;
		this.ageScolaire_Couverture.dejaInitialise = true;
	}
	public static AgeScolaire staticSetAgeScolaire_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AgeGenPage ageScolaire_Init() {
		if(!ageScolaire_Couverture.dejaInitialise) {
			_ageScolaire_(ageScolaire_Couverture);
			if(ageScolaire_ == null)
				setAgeScolaire_(ageScolaire_Couverture.o);
		}
		ageScolaire_Couverture.dejaInitialise(true);
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
		ageScolaire_Init();
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
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirAgeGenPage(String var) {
		AgeGenPage oAgeGenPage = (AgeGenPage)this;
		switch(var) {
			case "listeAgeScolaire":
				return oAgeGenPage.listeAgeScolaire;
			case "ageScolaire_":
				return oAgeGenPage.ageScolaire_;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetAgeGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetAgeGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSetClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrAgeGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrAgeGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrAgeGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrAgeGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqAgeGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqAgeGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
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
					o = definirAgeGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAgeGenPage(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	@Override public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAgeGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAgeGenPage(String var, Object val) {
		switch(var.toLowerCase()) {
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
