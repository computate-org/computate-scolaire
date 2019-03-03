package org.computate.frFR.scolaire.ecole;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.ecole.EcoleScolaireGenPage;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolairePage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleScolairePageGen<DEV> extends EcoleScolaireGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcoleScolairePage = false;

	public EcoleScolairePage initLoinEcoleScolairePage(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcoleScolairePage) {
			dejaInitialiseEcoleScolairePage = true;
			initLoinEcoleScolairePage();
		}
		return (EcoleScolairePage)this;
	}

	public void initLoinEcoleScolairePage() {
		super.initLoinEcoleScolaireGenPage(requeteSite_);
		initEcoleScolairePage();
	}

	public void initEcoleScolairePage() {
	}

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinEcoleScolairePage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleScolairePage(RequeteSite requeteSite_) {
			super.requeteSiteEcoleScolaireGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteEcoleScolairePage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcoleScolairePage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcoleScolairePage(String var) throws Exception {
		EcoleScolairePage oEcoleScolairePage = (EcoleScolairePage)this;
		switch(var) {
			default:
				return super.obtenirEcoleScolaireGenPage(var);
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
				o = attribuerEcoleScolairePage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcoleScolairePage(String var, Object val) {
		EcoleScolairePage oEcoleScolairePage = (EcoleScolairePage)this;
		switch(var) {
			default:
				return super.attribuerEcoleScolaireGenPage(var, val);
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
					o = definirEcoleScolairePage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcoleScolairePage(String var, String val) {
		switch(var) {
			default:
				return super.definirEcoleScolaireGenPage(var, val);
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
		if(!(o instanceof EcoleScolairePage))
			return false;
		EcoleScolairePage that = (EcoleScolairePage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleScolairePage {");
		sb.append(" }");
		return sb.toString();
	}
}
