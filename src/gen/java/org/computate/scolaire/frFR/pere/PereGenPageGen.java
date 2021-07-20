package org.computate.scolaire.frFR.pere;

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
import org.computate.scolaire.frFR.pere.PereScolaire;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.pere.PereGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class PereGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PereGenPage.class);

	///////////////////////
	// listePereScolaire //
	///////////////////////

	/**	 L'entité listePereScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<PereScolaire> listePereScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<PereScolaire>> listePereScolaireCouverture = new Couverture<ListeRecherche<PereScolaire>>().p(this).c(ListeRecherche.class).var("listePereScolaire").o(listePereScolaire);

	/**	<br/> L'entité listePereScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.pere.PereGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listePereScolaire">Trouver l'entité listePereScolaire dans Solr</a>
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
	public static ListeRecherche<PereScolaire> staticSetListePereScolaire(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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

	///////////////////
	// pereScolaire_ //
	///////////////////

	/**	 L'entité pereScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PereScolaire pereScolaire_;
	@JsonIgnore
	public Couverture<PereScolaire> pereScolaire_Couverture = new Couverture<PereScolaire>().p(this).c(PereScolaire.class).var("pereScolaire_").o(pereScolaire_);

	/**	<br/> L'entité pereScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.pere.PereGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereScolaire_">Trouver l'entité pereScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pereScolaire_(Couverture<PereScolaire> c);

	public PereScolaire getPereScolaire_() {
		return pereScolaire_;
	}

	public void setPereScolaire_(PereScolaire pereScolaire_) {
		this.pereScolaire_ = pereScolaire_;
		this.pereScolaire_Couverture.dejaInitialise = true;
	}
	public static PereScolaire staticSetPereScolaire_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected PereGenPage pereScolaire_Init() {
		if(!pereScolaire_Couverture.dejaInitialise) {
			_pereScolaire_(pereScolaire_Couverture);
			if(pereScolaire_ == null)
				setPereScolaire_(pereScolaire_Couverture.o);
		}
		pereScolaire_Couverture.dejaInitialise(true);
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
		pereScolaire_Init();
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
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirPereGenPage(String var) {
		PereGenPage oPereGenPage = (PereGenPage)this;
		switch(var) {
			case "listePereScolaire":
				return oPereGenPage.listePereScolaire;
			case "pereScolaire_":
				return oPereGenPage.pereScolaire_;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetPereGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetPereGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSetClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrPereGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrPereGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrPereGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrPereGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqPereGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqPereGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
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
					o = definirPereGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPereGenPage(String var, String val) {
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
					o = definirPereGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPereGenPage(String var, Object val) {
		switch(var.toLowerCase()) {
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
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof PereGenPage) {
			PereGenPage original = (PereGenPage)o;
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
