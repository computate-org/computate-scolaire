package org.computate.scolaire.frFR.mission;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.mission.MissionScolaireGenPage;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mission.MissionScolairePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class MissionScolairePageGen<DEV> extends MissionScolaireGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseMissionScolairePage = false;

	public MissionScolairePage initLoinMissionScolairePage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseMissionScolairePage) {
			dejaInitialiseMissionScolairePage = true;
			initLoinMissionScolairePage();
		}
		return (MissionScolairePage)this;
	}

	public void initLoinMissionScolairePage() {
		super.initLoinMissionScolaireGenPage(requeteSite_);
		initMissionScolairePage();
	}

	public void initMissionScolairePage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinMissionScolairePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteMissionScolairePage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteMissionScolaireGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteMissionScolairePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirMissionScolairePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirMissionScolairePage(String var) {
		MissionScolairePage oMissionScolairePage = (MissionScolairePage)this;
		switch(var) {
			default:
				return super.obtenirMissionScolaireGenPage(var);
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
				o = attribuerMissionScolairePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerMissionScolairePage(String var, Object val) {
		MissionScolairePage oMissionScolairePage = (MissionScolairePage)this;
		switch(var) {
			default:
				return super.attribuerMissionScolaireGenPage(var, val);
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
					o = definirMissionScolairePage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirMissionScolairePage(String var, String val) {
		switch(var) {
			default:
				return super.definirMissionScolaireGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsMissionScolairePage();
		super.htmlScripts();
	}

	public void htmlScriptsMissionScolairePage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptMissionScolairePage();
		super.htmlScript();
	}

	public void htmlScriptMissionScolairePage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyMissionScolairePage();
		super.htmlBody();
	}

	public void htmlBodyMissionScolairePage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlMissionScolairePage();
		super.html();
	}

	public void htmlMissionScolairePage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaMissionScolairePage();
		super.htmlMeta();
	}

	public void htmlMetaMissionScolairePage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesMissionScolairePage();
		super.htmlStyles();
	}

	public void htmlStylesMissionScolairePage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleMissionScolairePage();
		super.htmlStyle();
	}

	public void htmlStyleMissionScolairePage() {
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
		if(!(o instanceof MissionScolairePage))
			return false;
		MissionScolairePage that = (MissionScolairePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("MissionScolairePage {");
		sb.append(" }");
		return sb.toString();
	}
}
