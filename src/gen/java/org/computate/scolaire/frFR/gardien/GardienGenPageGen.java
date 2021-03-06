package org.computate.scolaire.frFR.gardien;

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
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.frFR.gardien.GardienScolaire;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class GardienGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GardienGenPage.class);

	//////////////////////////
	// listeGardienScolaire //
	//////////////////////////

	/**	 L'entité listeGardienScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<GardienScolaire> listeGardienScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<GardienScolaire>> listeGardienScolaireCouverture = new Couverture<ListeRecherche<GardienScolaire>>().p(this).c(ListeRecherche.class).var("listeGardienScolaire").o(listeGardienScolaire);

	/**	<br/> L'entité listeGardienScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeGardienScolaire">Trouver l'entité listeGardienScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeGardienScolaire(Couverture<ListeRecherche<GardienScolaire>> c);

	public ListeRecherche<GardienScolaire> getListeGardienScolaire() {
		return listeGardienScolaire;
	}

	public void setListeGardienScolaire(ListeRecherche<GardienScolaire> listeGardienScolaire) {
		this.listeGardienScolaire = listeGardienScolaire;
		this.listeGardienScolaireCouverture.dejaInitialise = true;
	}
	protected GardienGenPage listeGardienScolaireInit() {
		if(!listeGardienScolaireCouverture.dejaInitialise) {
			_listeGardienScolaire(listeGardienScolaireCouverture);
			if(listeGardienScolaire == null)
				setListeGardienScolaire(listeGardienScolaireCouverture.o);
		}
		if(listeGardienScolaire != null)
			listeGardienScolaire.initLoinPourClasse(requeteSite_);
		listeGardienScolaireCouverture.dejaInitialise(true);
		return (GardienGenPage)this;
	}

	//////////////////////
	// gardienScolaire_ //
	//////////////////////

	/**	 L'entité gardienScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected GardienScolaire gardienScolaire_;
	@JsonIgnore
	public Couverture<GardienScolaire> gardienScolaire_Couverture = new Couverture<GardienScolaire>().p(this).c(GardienScolaire.class).var("gardienScolaire_").o(gardienScolaire_);

	/**	<br/> L'entité gardienScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienScolaire_">Trouver l'entité gardienScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gardienScolaire_(Couverture<GardienScolaire> c);

	public GardienScolaire getGardienScolaire_() {
		return gardienScolaire_;
	}

	public void setGardienScolaire_(GardienScolaire gardienScolaire_) {
		this.gardienScolaire_ = gardienScolaire_;
		this.gardienScolaire_Couverture.dejaInitialise = true;
	}
	protected GardienGenPage gardienScolaire_Init() {
		if(!gardienScolaire_Couverture.dejaInitialise) {
			_gardienScolaire_(gardienScolaire_Couverture);
			if(gardienScolaire_ == null)
				setGardienScolaire_(gardienScolaire_Couverture.o);
		}
		gardienScolaire_Couverture.dejaInitialise(true);
		return (GardienGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseGardienGenPage = false;

	public GardienGenPage initLoinGardienGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseGardienGenPage) {
			dejaInitialiseGardienGenPage = true;
			initLoinGardienGenPage();
		}
		return (GardienGenPage)this;
	}

	public void initLoinGardienGenPage() {
		initGardienGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initGardienGenPage() {
		listeGardienScolaireInit();
		gardienScolaire_Init();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinGardienGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteGardienGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeGardienScolaire != null)
			listeGardienScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteGardienGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirGardienGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirGardienGenPage(String var) {
		GardienGenPage oGardienGenPage = (GardienGenPage)this;
		switch(var) {
			case "listeGardienScolaire":
				return oGardienGenPage.listeGardienScolaire;
			case "gardienScolaire_":
				return oGardienGenPage.gardienScolaire_;
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
				o = attribuerGardienGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerGardienGenPage(String var, Object val) {
		GardienGenPage oGardienGenPage = (GardienGenPage)this;
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
					o = definirGardienGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirGardienGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsGardienGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsGardienGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptGardienGenPage();
		super.htmlScript();
	}

	public void htmlScriptGardienGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyGardienGenPage();
		super.htmlBody();
	}

	public void htmlBodyGardienGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlGardienGenPage();
		super.html();
	}

	public void htmlGardienGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaGardienGenPage();
		super.htmlMeta();
	}

	public void htmlMetaGardienGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesGardienGenPage();
		super.htmlStyles();
	}

	public void htmlStylesGardienGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleGardienGenPage();
		super.htmlStyle();
	}

	public void htmlStyleGardienGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiGardienGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof GardienGenPage) {
			GardienGenPage original = (GardienGenPage)o;
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
		if(!(o instanceof GardienGenPage))
			return false;
		GardienGenPage that = (GardienGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("GardienGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
