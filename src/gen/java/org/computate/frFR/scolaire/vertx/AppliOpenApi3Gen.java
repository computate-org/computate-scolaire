package org.computate.frFR.scolaire.vertx;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import org.computate.frFR.scolaire.vertx.AppliSwagger2;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.vertx.AppliOpenApi3&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AppliOpenApi3Gen<DEV> extends AppliSwagger2 {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAppliOpenApi3 = false;

	public AppliOpenApi3 initLoinAppliOpenApi3(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAppliOpenApi3) {
			dejaInitialiseAppliOpenApi3 = true;
			initLoinAppliOpenApi3();
		}
		return (AppliOpenApi3)this;
	}

	public void initLoinAppliOpenApi3() {
		super.initLoinAppliSwagger2(requeteSite_);
		initAppliOpenApi3();
	}

	public void initAppliOpenApi3() {
	}

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinAppliOpenApi3(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAppliOpenApi3(RequeteSite requeteSite_) {
			super.requeteSiteAppliSwagger2(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteAppliOpenApi3(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAppliOpenApi3(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAppliOpenApi3(String var) throws Exception {
		AppliOpenApi3 oAppliOpenApi3 = (AppliOpenApi3)this;
		switch(var) {
			default:
				return super.obtenirAppliSwagger2(var);
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
				o = attribuerAppliOpenApi3(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAppliOpenApi3(String var, Object val) {
		AppliOpenApi3 oAppliOpenApi3 = (AppliOpenApi3)this;
		switch(var) {
			default:
				return super.attribuerAppliSwagger2(var, val);
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
					o = definirAppliOpenApi3(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAppliOpenApi3(String var, String val) {
		switch(var) {
			default:
				return super.definirAppliSwagger2(var, val);
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
		if(!(o instanceof AppliOpenApi3))
			return false;
		AppliOpenApi3 that = (AppliOpenApi3)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AppliOpenApi3 {");
		sb.append(" }");
		return sb.toString();
	}
}
