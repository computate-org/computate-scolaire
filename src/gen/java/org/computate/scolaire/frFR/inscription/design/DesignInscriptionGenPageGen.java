package org.computate.scolaire.frFR.inscription.design;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.cluster.ClusterPage;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.frFR.inscription.design.DesignInscription;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscriptionGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class DesignInscriptionGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignInscriptionGenPage.class);

	////////////////////////////
	// listeDesignInscription //
	////////////////////////////

	/**	L'entité « listeDesignInscription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<DesignInscription> listeDesignInscription;
	@JsonIgnore
	public Couverture<ListeRecherche<DesignInscription>> listeDesignInscriptionCouverture = new Couverture<ListeRecherche<DesignInscription>>().p(this).c(ListeRecherche.class).var("listeDesignInscription").o(listeDesignInscription);

	/**	<br/>L'entité « listeDesignInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeDesignInscription">Trouver l'entité listeDesignInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeDesignInscription(Couverture<ListeRecherche<DesignInscription>> c);

	public ListeRecherche<DesignInscription> getListeDesignInscription() {
		return listeDesignInscription;
	}

	public void setListeDesignInscription(ListeRecherche<DesignInscription> listeDesignInscription) {
		this.listeDesignInscription = listeDesignInscription;
		this.listeDesignInscriptionCouverture.dejaInitialise = true;
	}
	protected DesignInscriptionGenPage listeDesignInscriptionInit() {
		if(!listeDesignInscriptionCouverture.dejaInitialise) {
			_listeDesignInscription(listeDesignInscriptionCouverture);
			if(listeDesignInscription == null)
				setListeDesignInscription(listeDesignInscriptionCouverture.o);
		}
		if(listeDesignInscription != null)
			listeDesignInscription.initLoinPourClasse(requeteSite_);
		listeDesignInscriptionCouverture.dejaInitialise(true);
		return (DesignInscriptionGenPage)this;
	}

	///////////////////////
	// designInscription //
	///////////////////////

	/**	L'entité « designInscription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected DesignInscription designInscription;
	@JsonIgnore
	public Couverture<DesignInscription> designInscriptionCouverture = new Couverture<DesignInscription>().p(this).c(DesignInscription.class).var("designInscription").o(designInscription);

	/**	<br/>L'entité « designInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designInscription">Trouver l'entité designInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designInscription(Couverture<DesignInscription> c);

	public DesignInscription getDesignInscription() {
		return designInscription;
	}

	public void setDesignInscription(DesignInscription designInscription) {
		this.designInscription = designInscription;
		this.designInscriptionCouverture.dejaInitialise = true;
	}
	protected DesignInscriptionGenPage designInscriptionInit() {
		if(!designInscriptionCouverture.dejaInitialise) {
			_designInscription(designInscriptionCouverture);
			if(designInscription == null)
				setDesignInscription(designInscriptionCouverture.o);
		}
		if(designInscription != null)
			designInscription.initLoinPourClasse(requeteSite_);
		designInscriptionCouverture.dejaInitialise(true);
		return (DesignInscriptionGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseDesignInscriptionGenPage = false;

	public DesignInscriptionGenPage initLoinDesignInscriptionGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseDesignInscriptionGenPage) {
			dejaInitialiseDesignInscriptionGenPage = true;
			initLoinDesignInscriptionGenPage();
		}
		return (DesignInscriptionGenPage)this;
	}

	public void initLoinDesignInscriptionGenPage() {
		initDesignInscriptionGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initDesignInscriptionGenPage() {
		listeDesignInscriptionInit();
		designInscriptionInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinDesignInscriptionGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteDesignInscriptionGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeDesignInscription != null)
			listeDesignInscription.setRequeteSite_(requeteSite_);
		if(designInscription != null)
			designInscription.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteDesignInscriptionGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirDesignInscriptionGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirDesignInscriptionGenPage(String var) {
		DesignInscriptionGenPage oDesignInscriptionGenPage = (DesignInscriptionGenPage)this;
		switch(var) {
			case "listeDesignInscription":
				return oDesignInscriptionGenPage.listeDesignInscription;
			case "designInscription":
				return oDesignInscriptionGenPage.designInscription;
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
				o = attribuerDesignInscriptionGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerDesignInscriptionGenPage(String var, Object val) {
		DesignInscriptionGenPage oDesignInscriptionGenPage = (DesignInscriptionGenPage)this;
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
					o = definirDesignInscriptionGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignInscriptionGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignInscriptionGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignInscriptionGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignInscriptionGenPage();
		super.htmlScript();
	}

	public void htmlScriptDesignInscriptionGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignInscriptionGenPage();
		super.htmlBody();
	}

	public void htmlBodyDesignInscriptionGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignInscriptionGenPage();
		super.html();
	}

	public void htmlDesignInscriptionGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignInscriptionGenPage();
		super.htmlMeta();
	}

	public void htmlMetaDesignInscriptionGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignInscriptionGenPage();
		super.htmlStyles();
	}

	public void htmlStylesDesignInscriptionGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignInscriptionGenPage();
		super.htmlStyle();
	}

	public void htmlStyleDesignInscriptionGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiDesignInscriptionGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof DesignInscriptionGenPage) {
			DesignInscriptionGenPage original = (DesignInscriptionGenPage)o;
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
		if(!(o instanceof DesignInscriptionGenPage))
			return false;
		DesignInscriptionGenPage that = (DesignInscriptionGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignInscriptionGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
