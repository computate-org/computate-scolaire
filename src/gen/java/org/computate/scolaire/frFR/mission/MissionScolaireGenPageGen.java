package org.computate.scolaire.frFR.mission;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.mission.MissionScolaire;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mission.MissionScolaireGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class MissionScolaireGenPageGen<DEV> extends MiseEnPage {

	//////////////////////////
	// listeMissionScolaire //
	//////////////////////////

	/**	L'entité « listeMissionScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<MissionScolaire> listeMissionScolaire;
	public Couverture<ListeRecherche<MissionScolaire>> listeMissionScolaireCouverture = new Couverture<ListeRecherche<MissionScolaire>>().p(this).c(ListeRecherche.class).var("listeMissionScolaire").o(listeMissionScolaire);

	/**	<br/>L'entité « listeMissionScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mission.MissionScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeMissionScolaire">Trouver l'entité listeMissionScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeMissionScolaire(Couverture<ListeRecherche<MissionScolaire>> c);

	public ListeRecherche<MissionScolaire> getListeMissionScolaire() {
		return listeMissionScolaire;
	}

	public void setListeMissionScolaire(ListeRecherche<MissionScolaire> listeMissionScolaire) {
		this.listeMissionScolaire = listeMissionScolaire;
		this.listeMissionScolaireCouverture.dejaInitialise = true;
	}
	protected MissionScolaireGenPage listeMissionScolaireInit() {
		if(!listeMissionScolaireCouverture.dejaInitialise) {
			_listeMissionScolaire(listeMissionScolaireCouverture);
			if(listeMissionScolaire == null)
				setListeMissionScolaire(listeMissionScolaireCouverture.o);
		}
		if(listeMissionScolaire != null)
			listeMissionScolaire.initLoinPourClasse(requeteSite_);
		listeMissionScolaireCouverture.dejaInitialise(true);
		return (MissionScolaireGenPage)this;
	}

	/////////////////////
	// missionScolaire //
	/////////////////////

	/**	L'entité « missionScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected MissionScolaire missionScolaire;
	public Couverture<MissionScolaire> missionScolaireCouverture = new Couverture<MissionScolaire>().p(this).c(MissionScolaire.class).var("missionScolaire").o(missionScolaire);

	/**	<br/>L'entité « missionScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mission.MissionScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:missionScolaire">Trouver l'entité missionScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _missionScolaire(Couverture<MissionScolaire> c);

	public MissionScolaire getMissionScolaire() {
		return missionScolaire;
	}

	public void setMissionScolaire(MissionScolaire missionScolaire) {
		this.missionScolaire = missionScolaire;
		this.missionScolaireCouverture.dejaInitialise = true;
	}
	protected MissionScolaireGenPage missionScolaireInit() {
		if(!missionScolaireCouverture.dejaInitialise) {
			_missionScolaire(missionScolaireCouverture);
			if(missionScolaire == null)
				setMissionScolaire(missionScolaireCouverture.o);
		}
		if(missionScolaire != null)
			missionScolaire.initLoinPourClasse(requeteSite_);
		missionScolaireCouverture.dejaInitialise(true);
		return (MissionScolaireGenPage)this;
	}

	////////////////////////////
	// pageUriMissionScolaire //
	////////////////////////////

	/**	L'entité « pageUriMissionScolaire »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriMissionScolaire;
	public Couverture<String> pageUriMissionScolaireCouverture = new Couverture<String>().p(this).c(String.class).var("pageUriMissionScolaire").o(pageUriMissionScolaire);

	/**	<br/>L'entité « pageUriMissionScolaire »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mission.MissionScolaireGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUriMissionScolaire">Trouver l'entité pageUriMissionScolaire dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriMissionScolaire(Couverture<String> c);

	public String getPageUriMissionScolaire() {
		return pageUriMissionScolaire;
	}

	public void setPageUriMissionScolaire(String pageUriMissionScolaire) {
		this.pageUriMissionScolaire = pageUriMissionScolaire;
		this.pageUriMissionScolaireCouverture.dejaInitialise = true;
	}
	protected MissionScolaireGenPage pageUriMissionScolaireInit() {
		if(!pageUriMissionScolaireCouverture.dejaInitialise) {
			_pageUriMissionScolaire(pageUriMissionScolaireCouverture);
			if(pageUriMissionScolaire == null)
				setPageUriMissionScolaire(pageUriMissionScolaireCouverture.o);
		}
		pageUriMissionScolaireCouverture.dejaInitialise(true);
		return (MissionScolaireGenPage)this;
	}

	public String solrPageUriMissionScolaire() {
		return pageUriMissionScolaire;
	}

	public String strPageUriMissionScolaire() {
		return pageUriMissionScolaire == null ? "" : pageUriMissionScolaire;
	}

	public String nomAffichagePageUriMissionScolaire() {
		return null;
	}

	public String htmTooltipPageUriMissionScolaire() {
		return null;
	}

	public String htmPageUriMissionScolaire() {
		return pageUriMissionScolaire == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriMissionScolaire());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseMissionScolaireGenPage = false;

	public MissionScolaireGenPage initLoinMissionScolaireGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseMissionScolaireGenPage) {
			dejaInitialiseMissionScolaireGenPage = true;
			initLoinMissionScolaireGenPage();
		}
		return (MissionScolaireGenPage)this;
	}

	public void initLoinMissionScolaireGenPage() {
		super.initLoinMiseEnPage(requeteSite_);
		initMissionScolaireGenPage();
	}

	public void initMissionScolaireGenPage() {
		listeMissionScolaireInit();
		missionScolaireInit();
		pageUriMissionScolaireInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinMissionScolaireGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteMissionScolaireGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteMiseEnPage(requeteSite_);
		if(listeMissionScolaire != null)
			listeMissionScolaire.setRequeteSite_(requeteSite_);
		if(missionScolaire != null)
			missionScolaire.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteMissionScolaireGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirMissionScolaireGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirMissionScolaireGenPage(String var) {
		MissionScolaireGenPage oMissionScolaireGenPage = (MissionScolaireGenPage)this;
		switch(var) {
			case "listeMissionScolaire":
				return oMissionScolaireGenPage.listeMissionScolaire;
			case "missionScolaire":
				return oMissionScolaireGenPage.missionScolaire;
			case "pageUriMissionScolaire":
				return oMissionScolaireGenPage.pageUriMissionScolaire;
			default:
				return super.obtenirMiseEnPage(var);
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
				o = attribuerMissionScolaireGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerMissionScolaireGenPage(String var, Object val) {
		MissionScolaireGenPage oMissionScolaireGenPage = (MissionScolaireGenPage)this;
		switch(var) {
			default:
				return super.attribuerMiseEnPage(var, val);
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
					o = definirMissionScolaireGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirMissionScolaireGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirMiseEnPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsMissionScolaireGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsMissionScolaireGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptMissionScolaireGenPage();
		super.htmlScript();
	}

	public void htmlScriptMissionScolaireGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyMissionScolaireGenPage();
		super.htmlBody();
	}

	public void htmlBodyMissionScolaireGenPage() {
		if(missionScolaire != null)
			missionScolaire.htmlBody();
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlMissionScolaireGenPage();
		super.html();
	}

	public void htmlMissionScolaireGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaMissionScolaireGenPage();
		super.htmlMeta();
	}

	public void htmlMetaMissionScolaireGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesMissionScolaireGenPage();
		super.htmlStyles();
	}

	public void htmlStylesMissionScolaireGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleMissionScolaireGenPage();
		super.htmlStyle();
	}

	public void htmlStyleMissionScolaireGenPage() {
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
		if(!(o instanceof MissionScolaireGenPage))
			return false;
		MissionScolaireGenPage that = (MissionScolaireGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("MissionScolaireGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
