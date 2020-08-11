package org.computate.scolaire.frFR.saison;

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
import org.computate.scolaire.frFR.saison.SaisonScolaire;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class SaisonGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SaisonGenPage.class);

	/////////////////////////
	// listeSaisonScolaire //
	/////////////////////////

	/**	 L'entité listeSaisonScolaire
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<SaisonScolaire> listeSaisonScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<SaisonScolaire>> listeSaisonScolaireCouverture = new Couverture<ListeRecherche<SaisonScolaire>>().p(this).c(ListeRecherche.class).var("listeSaisonScolaire").o(listeSaisonScolaire);

	/**	<br/> L'entité listeSaisonScolaire
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeSaisonScolaire">Trouver l'entité listeSaisonScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeSaisonScolaire(Couverture<ListeRecherche<SaisonScolaire>> c);

	public ListeRecherche<SaisonScolaire> getListeSaisonScolaire() {
		return listeSaisonScolaire;
	}

	public void setListeSaisonScolaire(ListeRecherche<SaisonScolaire> listeSaisonScolaire) {
		this.listeSaisonScolaire = listeSaisonScolaire;
		this.listeSaisonScolaireCouverture.dejaInitialise = true;
	}
	protected SaisonGenPage listeSaisonScolaireInit() {
		if(!listeSaisonScolaireCouverture.dejaInitialise) {
			_listeSaisonScolaire(listeSaisonScolaireCouverture);
			if(listeSaisonScolaire == null)
				setListeSaisonScolaire(listeSaisonScolaireCouverture.o);
		}
		if(listeSaisonScolaire != null)
			listeSaisonScolaire.initLoinPourClasse(requeteSite_);
		listeSaisonScolaireCouverture.dejaInitialise(true);
		return (SaisonGenPage)this;
	}

	/////////////////////
	// saisonScolaire_ //
	/////////////////////

	/**	 L'entité saisonScolaire_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SaisonScolaire saisonScolaire_;
	@JsonIgnore
	public Couverture<SaisonScolaire> saisonScolaire_Couverture = new Couverture<SaisonScolaire>().p(this).c(SaisonScolaire.class).var("saisonScolaire_").o(saisonScolaire_);

	/**	<br/> L'entité saisonScolaire_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonScolaire_">Trouver l'entité saisonScolaire_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonScolaire_(Couverture<SaisonScolaire> c);

	public SaisonScolaire getSaisonScolaire_() {
		return saisonScolaire_;
	}

	public void setSaisonScolaire_(SaisonScolaire saisonScolaire_) {
		this.saisonScolaire_ = saisonScolaire_;
		this.saisonScolaire_Couverture.dejaInitialise = true;
	}
	protected SaisonGenPage saisonScolaire_Init() {
		if(!saisonScolaire_Couverture.dejaInitialise) {
			_saisonScolaire_(saisonScolaire_Couverture);
			if(saisonScolaire_ == null)
				setSaisonScolaire_(saisonScolaire_Couverture.o);
		}
		saisonScolaire_Couverture.dejaInitialise(true);
		return (SaisonGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSaisonGenPage = false;

	public SaisonGenPage initLoinSaisonGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSaisonGenPage) {
			dejaInitialiseSaisonGenPage = true;
			initLoinSaisonGenPage();
		}
		return (SaisonGenPage)this;
	}

	public void initLoinSaisonGenPage() {
		initSaisonGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initSaisonGenPage() {
		listeSaisonScolaireInit();
		saisonScolaire_Init();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSaisonGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSaisonGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeSaisonScolaire != null)
			listeSaisonScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteSaisonGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSaisonGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSaisonGenPage(String var) {
		SaisonGenPage oSaisonGenPage = (SaisonGenPage)this;
		switch(var) {
			case "listeSaisonScolaire":
				return oSaisonGenPage.listeSaisonScolaire;
			case "saisonScolaire_":
				return oSaisonGenPage.saisonScolaire_;
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
				o = attribuerSaisonGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSaisonGenPage(String var, Object val) {
		SaisonGenPage oSaisonGenPage = (SaisonGenPage)this;
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
					o = definirSaisonGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSaisonGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSaisonGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsSaisonGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSaisonGenPage();
		super.htmlScript();
	}

	public void htmlScriptSaisonGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySaisonGenPage();
		super.htmlBody();
	}

	public void htmlBodySaisonGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSaisonGenPage();
		super.html();
	}

	public void htmlSaisonGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSaisonGenPage();
		super.htmlMeta();
	}

	public void htmlMetaSaisonGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSaisonGenPage();
		super.htmlStyles();
	}

	public void htmlStylesSaisonGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSaisonGenPage();
		super.htmlStyle();
	}

	public void htmlStyleSaisonGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiSaisonGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof SaisonGenPage) {
			SaisonGenPage original = (SaisonGenPage)o;
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
		if(!(o instanceof SaisonGenPage))
			return false;
		SaisonGenPage that = (SaisonGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SaisonGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
