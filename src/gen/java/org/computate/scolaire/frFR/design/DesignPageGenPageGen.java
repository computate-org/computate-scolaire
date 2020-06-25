package org.computate.scolaire.frFR.design;

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
import org.computate.scolaire.frFR.cluster.ClusterPage;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.design.DesignPage;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPageGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class DesignPageGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignPageGenPage.class);

	/////////////////////
	// listeDesignPage //
	/////////////////////

	/**	L'entité « listeDesignPage »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<DesignPage> listeDesignPage;
	@JsonIgnore
	public Couverture<ListeRecherche<DesignPage>> listeDesignPageCouverture = new Couverture<ListeRecherche<DesignPage>>().p(this).c(ListeRecherche.class).var("listeDesignPage").o(listeDesignPage);

	/**	<br/>L'entité « listeDesignPage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPageGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeDesignPage">Trouver l'entité listeDesignPage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeDesignPage(Couverture<ListeRecherche<DesignPage>> c);

	public ListeRecherche<DesignPage> getListeDesignPage() {
		return listeDesignPage;
	}

	public void setListeDesignPage(ListeRecherche<DesignPage> listeDesignPage) {
		this.listeDesignPage = listeDesignPage;
		this.listeDesignPageCouverture.dejaInitialise = true;
	}
	protected DesignPageGenPage listeDesignPageInit() {
		if(!listeDesignPageCouverture.dejaInitialise) {
			_listeDesignPage(listeDesignPageCouverture);
			if(listeDesignPage == null)
				setListeDesignPage(listeDesignPageCouverture.o);
		}
		if(listeDesignPage != null)
			listeDesignPage.initLoinPourClasse(requeteSite_);
		listeDesignPageCouverture.dejaInitialise(true);
		return (DesignPageGenPage)this;
	}

	////////////////
	// designPage //
	////////////////

	/**	L'entité « designPage »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected DesignPage designPage;
	@JsonIgnore
	public Couverture<DesignPage> designPageCouverture = new Couverture<DesignPage>().p(this).c(DesignPage.class).var("designPage").o(designPage);

	/**	<br/>L'entité « designPage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPageGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designPage">Trouver l'entité designPage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designPage(Couverture<DesignPage> c);

	public DesignPage getDesignPage() {
		return designPage;
	}

	public void setDesignPage(DesignPage designPage) {
		this.designPage = designPage;
		this.designPageCouverture.dejaInitialise = true;
	}
	protected DesignPageGenPage designPageInit() {
		if(!designPageCouverture.dejaInitialise) {
			_designPage(designPageCouverture);
			if(designPage == null)
				setDesignPage(designPageCouverture.o);
		}
		if(designPage != null)
			designPage.initLoinPourClasse(requeteSite_);
		designPageCouverture.dejaInitialise(true);
		return (DesignPageGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseDesignPageGenPage = false;

	public DesignPageGenPage initLoinDesignPageGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseDesignPageGenPage) {
			dejaInitialiseDesignPageGenPage = true;
			initLoinDesignPageGenPage();
		}
		return (DesignPageGenPage)this;
	}

	public void initLoinDesignPageGenPage() {
		initDesignPageGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initDesignPageGenPage() {
		listeDesignPageInit();
		designPageInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinDesignPageGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteDesignPageGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeDesignPage != null)
			listeDesignPage.setRequeteSite_(requeteSite_);
		if(designPage != null)
			designPage.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteDesignPageGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirDesignPageGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirDesignPageGenPage(String var) {
		DesignPageGenPage oDesignPageGenPage = (DesignPageGenPage)this;
		switch(var) {
			case "listeDesignPage":
				return oDesignPageGenPage.listeDesignPage;
			case "designPage":
				return oDesignPageGenPage.designPage;
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
				o = attribuerDesignPageGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerDesignPageGenPage(String var, Object val) {
		DesignPageGenPage oDesignPageGenPage = (DesignPageGenPage)this;
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
					o = definirDesignPageGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignPageGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignPageGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignPageGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignPageGenPage();
		super.htmlScript();
	}

	public void htmlScriptDesignPageGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignPageGenPage();
		super.htmlBody();
	}

	public void htmlBodyDesignPageGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignPageGenPage();
		super.html();
	}

	public void htmlDesignPageGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignPageGenPage();
		super.htmlMeta();
	}

	public void htmlMetaDesignPageGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignPageGenPage();
		super.htmlStyles();
	}

	public void htmlStylesDesignPageGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignPageGenPage();
		super.htmlStyle();
	}

	public void htmlStyleDesignPageGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiDesignPageGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof DesignPageGenPage) {
			DesignPageGenPage original = (DesignPageGenPage)o;
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
		if(!(o instanceof DesignPageGenPage))
			return false;
		DesignPageGenPage that = (DesignPageGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignPageGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
