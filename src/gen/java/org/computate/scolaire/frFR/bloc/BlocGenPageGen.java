package org.computate.scolaire.frFR.bloc;

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
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.computate.scolaire.frFR.bloc.BlocScolaire;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class BlocGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(BlocGenPage.class);

	///////////////////////
	// listeBlocScolaire //
	///////////////////////

	/**	 L'entité listeBlocScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<BlocScolaire> listeBlocScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<BlocScolaire>> listeBlocScolaireCouverture = new Couverture<ListeRecherche<BlocScolaire>>().p(this).c(ListeRecherche.class).var("listeBlocScolaire").o(listeBlocScolaire);

	/**	<br/> L'entité listeBlocScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeBlocScolaire">Trouver l'entité listeBlocScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeBlocScolaire(Couverture<ListeRecherche<BlocScolaire>> c);

	public ListeRecherche<BlocScolaire> getListeBlocScolaire() {
		return listeBlocScolaire;
	}

	public void setListeBlocScolaire(ListeRecherche<BlocScolaire> listeBlocScolaire) {
		this.listeBlocScolaire = listeBlocScolaire;
		this.listeBlocScolaireCouverture.dejaInitialise = true;
	}
	public static ListeRecherche<BlocScolaire> staticSetListeBlocScolaire(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected BlocGenPage listeBlocScolaireInit() {
		if(!listeBlocScolaireCouverture.dejaInitialise) {
			_listeBlocScolaire(listeBlocScolaireCouverture);
			if(listeBlocScolaire == null)
				setListeBlocScolaire(listeBlocScolaireCouverture.o);
		}
		if(listeBlocScolaire != null)
			listeBlocScolaire.initLoinPourClasse(requeteSite_);
		listeBlocScolaireCouverture.dejaInitialise(true);
		return (BlocGenPage)this;
	}

	///////////////////
	// blocScolaire_ //
	///////////////////

	/**	 L'entité blocScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected BlocScolaire blocScolaire_;
	@JsonIgnore
	public Couverture<BlocScolaire> blocScolaire_Couverture = new Couverture<BlocScolaire>().p(this).c(BlocScolaire.class).var("blocScolaire_").o(blocScolaire_);

	/**	<br/> L'entité blocScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocScolaire_">Trouver l'entité blocScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocScolaire_(Couverture<BlocScolaire> c);

	public BlocScolaire getBlocScolaire_() {
		return blocScolaire_;
	}

	public void setBlocScolaire_(BlocScolaire blocScolaire_) {
		this.blocScolaire_ = blocScolaire_;
		this.blocScolaire_Couverture.dejaInitialise = true;
	}
	public static BlocScolaire staticSetBlocScolaire_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected BlocGenPage blocScolaire_Init() {
		if(!blocScolaire_Couverture.dejaInitialise) {
			_blocScolaire_(blocScolaire_Couverture);
			if(blocScolaire_ == null)
				setBlocScolaire_(blocScolaire_Couverture.o);
		}
		blocScolaire_Couverture.dejaInitialise(true);
		return (BlocGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseBlocGenPage = false;

	public BlocGenPage initLoinBlocGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseBlocGenPage) {
			dejaInitialiseBlocGenPage = true;
			initLoinBlocGenPage();
		}
		return (BlocGenPage)this;
	}

	public void initLoinBlocGenPage() {
		initBlocGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initBlocGenPage() {
		listeBlocScolaireInit();
		blocScolaire_Init();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinBlocGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteBlocGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeBlocScolaire != null)
			listeBlocScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteBlocGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirBlocGenPage(v);
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
	public Object obtenirBlocGenPage(String var) {
		BlocGenPage oBlocGenPage = (BlocGenPage)this;
		switch(var) {
			case "listeBlocScolaire":
				return oBlocGenPage.listeBlocScolaire;
			case "blocScolaire_":
				return oBlocGenPage.blocScolaire_;
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
				o = attribuerBlocGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerBlocGenPage(String var, Object val) {
		BlocGenPage oBlocGenPage = (BlocGenPage)this;
		switch(var) {
			default:
				return super.attribuerClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetBlocGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetBlocGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSetClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrBlocGenPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrBlocGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrBlocGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrBlocGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqBlocGenPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqBlocGenPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
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
					o = definirBlocGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirBlocGenPage(String var, String val) {
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
					o = definirBlocGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirBlocGenPage(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsBlocGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsBlocGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptBlocGenPage();
		super.htmlScript();
	}

	public void htmlScriptBlocGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyBlocGenPage();
		super.htmlBody();
	}

	public void htmlBodyBlocGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlBlocGenPage();
		super.html();
	}

	public void htmlBlocGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaBlocGenPage();
		super.htmlMeta();
	}

	public void htmlMetaBlocGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesBlocGenPage();
		super.htmlStyles();
	}

	public void htmlStylesBlocGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleBlocGenPage();
		super.htmlStyle();
	}

	public void htmlStyleBlocGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiBlocGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof BlocGenPage) {
			BlocGenPage original = (BlocGenPage)o;
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
		if(!(o instanceof BlocGenPage))
			return false;
		BlocGenPage that = (BlocGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("BlocGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
