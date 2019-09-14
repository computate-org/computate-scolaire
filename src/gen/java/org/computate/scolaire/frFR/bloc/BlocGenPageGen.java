package org.computate.scolaire.frFR.bloc;

import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;
import org.computate.scolaire.frFR.bloc.BlocScolaire;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class BlocGenPageGen<DEV> extends ClusterPage {

	///////////////////////
	// listeBlocScolaire //
	///////////////////////

	/**	L'entité « listeBlocScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<BlocScolaire> listeBlocScolaire;
	@JsonIgnore
	public Couverture<ListeRecherche<BlocScolaire>> listeBlocScolaireCouverture = new Couverture<ListeRecherche<BlocScolaire>>().p(this).c(ListeRecherche.class).var("listeBlocScolaire").o(listeBlocScolaire);

	/**	<br/>L'entité « listeBlocScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeBlocScolaire">Trouver l'entité listeBlocScolaire dans Solr</a>
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

	//////////////////
	// blocScolaire //
	//////////////////

	/**	L'entité « blocScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected BlocScolaire blocScolaire;
	@JsonIgnore
	public Couverture<BlocScolaire> blocScolaireCouverture = new Couverture<BlocScolaire>().p(this).c(BlocScolaire.class).var("blocScolaire").o(blocScolaire);

	/**	<br/>L'entité « blocScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocScolaire">Trouver l'entité blocScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocScolaire(Couverture<BlocScolaire> c);

	public BlocScolaire getBlocScolaire() {
		return blocScolaire;
	}

	public void setBlocScolaire(BlocScolaire blocScolaire) {
		this.blocScolaire = blocScolaire;
		this.blocScolaireCouverture.dejaInitialise = true;
	}
	protected BlocGenPage blocScolaireInit() {
		if(!blocScolaireCouverture.dejaInitialise) {
			_blocScolaire(blocScolaireCouverture);
			if(blocScolaire == null)
				setBlocScolaire(blocScolaireCouverture.o);
		}
		if(blocScolaire != null)
			blocScolaire.initLoinPourClasse(requeteSite_);
		blocScolaireCouverture.dejaInitialise(true);
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
		super.initLoinClusterPage(requeteSite_);
		initBlocGenPage();
	}

	public void initBlocGenPage() {
		listeBlocScolaireInit();
		blocScolaireInit();
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
		if(blocScolaire != null)
			blocScolaire.setRequeteSite_(requeteSite_);
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
		}
		return o;
	}
	public Object obtenirBlocGenPage(String var) {
		BlocGenPage oBlocGenPage = (BlocGenPage)this;
		switch(var) {
			case "listeBlocScolaire":
				return oBlocGenPage.listeBlocScolaire;
			case "blocScolaire":
				return oBlocGenPage.blocScolaire;
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
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirBlocGenPage(String var, String val) {
		switch(var) {
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
