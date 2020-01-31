package org.computate.scolaire.frFR.mere;

import org.computate.scolaire.frFR.mere.MereScolaire;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class MereGenPageGen<DEV> extends ClusterPage {

	///////////////////////
	// listeMereScolaire //
	///////////////////////

	/**	L'entité « listeMereScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<MereScolaire> listeMereScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<MereScolaire>> listeMereScolaireCouverture = new Couverture<ListeRecherche<MereScolaire>>().p(this).c(ListeRecherche.class).var("listeMereScolaire").o(listeMereScolaire);

	/**	<br/>L'entité « listeMereScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeMereScolaire">Trouver l'entité listeMereScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeMereScolaire(Couverture<ListeRecherche<MereScolaire>> c);

	public ListeRecherche<MereScolaire> getListeMereScolaire() {
		return listeMereScolaire;
	}

	public void setListeMereScolaire(ListeRecherche<MereScolaire> listeMereScolaire) {
		this.listeMereScolaire = listeMereScolaire;
		this.listeMereScolaireCouverture.dejaInitialise = true;
	}
	protected MereGenPage listeMereScolaireInit() {
		if(!listeMereScolaireCouverture.dejaInitialise) {
			_listeMereScolaire(listeMereScolaireCouverture);
			if(listeMereScolaire == null)
				setListeMereScolaire(listeMereScolaireCouverture.o);
		}
		if(listeMereScolaire != null)
			listeMereScolaire.initLoinPourClasse(requeteSite_);
		listeMereScolaireCouverture.dejaInitialise(true);
		return (MereGenPage)this;
	}

	//////////////////
	// mereScolaire //
	//////////////////

	/**	L'entité « mereScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected MereScolaire mereScolaire;
	@JsonIgnore
	public Couverture<MereScolaire> mereScolaireCouverture = new Couverture<MereScolaire>().p(this).c(MereScolaire.class).var("mereScolaire").o(mereScolaire);

	/**	<br/>L'entité « mereScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereScolaire">Trouver l'entité mereScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mereScolaire(Couverture<MereScolaire> c);

	public MereScolaire getMereScolaire() {
		return mereScolaire;
	}

	public void setMereScolaire(MereScolaire mereScolaire) {
		this.mereScolaire = mereScolaire;
		this.mereScolaireCouverture.dejaInitialise = true;
	}
	protected MereGenPage mereScolaireInit() {
		if(!mereScolaireCouverture.dejaInitialise) {
			_mereScolaire(mereScolaireCouverture);
			if(mereScolaire == null)
				setMereScolaire(mereScolaireCouverture.o);
		}
		if(mereScolaire != null)
			mereScolaire.initLoinPourClasse(requeteSite_);
		mereScolaireCouverture.dejaInitialise(true);
		return (MereGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseMereGenPage = false;

	public MereGenPage initLoinMereGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseMereGenPage) {
			dejaInitialiseMereGenPage = true;
			initLoinMereGenPage();
		}
		return (MereGenPage)this;
	}

	public void initLoinMereGenPage() {
		initMereGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initMereGenPage() {
		listeMereScolaireInit();
		mereScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinMereGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteMereGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeMereScolaire != null)
			listeMereScolaire.setRequeteSite_(requeteSite_);
		if(mereScolaire != null)
			mereScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteMereGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirMereGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirMereGenPage(String var) {
		MereGenPage oMereGenPage = (MereGenPage)this;
		switch(var) {
			case "listeMereScolaire":
				return oMereGenPage.listeMereScolaire;
			case "mereScolaire":
				return oMereGenPage.mereScolaire;
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
				o = attribuerMereGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerMereGenPage(String var, Object val) {
		MereGenPage oMereGenPage = (MereGenPage)this;
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
					o = definirMereGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirMereGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsMereGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsMereGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptMereGenPage();
		super.htmlScript();
	}

	public void htmlScriptMereGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyMereGenPage();
		super.htmlBody();
	}

	public void htmlBodyMereGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlMereGenPage();
		super.html();
	}

	public void htmlMereGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaMereGenPage();
		super.htmlMeta();
	}

	public void htmlMetaMereGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesMereGenPage();
		super.htmlStyles();
	}

	public void htmlStylesMereGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleMereGenPage();
		super.htmlStyle();
	}

	public void htmlStyleMereGenPage() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiMereGenPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		MereGenPage original = (MereGenPage)Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(original != null) {
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
		if(!(o instanceof MereGenPage))
			return false;
		MereGenPage that = (MereGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("MereGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
