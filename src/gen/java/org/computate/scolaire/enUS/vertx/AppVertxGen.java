package org.computate.scolaire.enUS.vertx;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import io.vertx.core.AbstractVerticle;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;

/**	
 *	A Java class to start the Vert.x application as a main method. 
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppVertx&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AppVertxGen<DEV> extends AbstractVerticle {
	public static final String configureDataConnectionError1 = "Could not open the database client connection. ";
	public static final String configureDataConnectionError = configureDataConnectionError1;
	public static final String configureDataConnectionSuccess1 = "The database client connection was successful. ";
	public static final String configureDataConnectionSuccess = configureDataConnectionSuccess1;
	public static final String configureDataInitError1 = "Could not initialize the database tables. ";
	public static final String configureDataInitError = configureDataInitError1;
	public static final String configureDataInitSuccess1 = "The database tables were created successfully. ";
	public static final String configureDataInitSuccess = configureDataInitSuccess1;

	public static final String configureClusterDataError1 = "Could not configure the shared cluster data. ";
	public static final String configureClusterDataError = configureClusterDataError1;
	public static final String configureClusterDataSuccess1 = "The shared cluster data was configured successfully. ";
	public static final String configureClusterDataSuccess = configureClusterDataSuccess1;

	public static final String closeDataError1 = "Could not close the database client connection. ";
	public static final String closeDataError = closeDataError1;
	public static final String closeDataSuccess1 = "The database client connextion was closed. ";
	public static final String closeDataSuccess = closeDataSuccess1;


	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedAppVertx = false;

	public AppVertx initDeepAppVertx(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedAppVertx) {
			alreadyInitializedAppVertx = true;
			initDeepAppVertx();
		}
		return (AppVertx)this;
	}

	public void initDeepAppVertx() {
		initAppVertx();
	}

	public void initAppVertx() {
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepAppVertx(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAppVertx(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestAppVertx(siteRequest_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAppVertx(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAppVertx(String var) {
		AppVertx oAppVertx = (AppVertx)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerAppVertx(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAppVertx(String var, Object val) {
		AppVertx oAppVertx = (AppVertx)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAppVertx(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAppVertx(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash();
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof AppVertx))
			return false;
		AppVertx that = (AppVertx)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AppVertx {");
		sb.append(" }");
		return sb.toString();
	}

	public static final String[] AppVertxVals = new String[] { configureDataConnectionError1, configureDataConnectionSuccess1, configureDataInitError1, configureDataInitSuccess1, configureClusterDataError1, configureClusterDataSuccess1, closeDataError1, closeDataSuccess1 };
}
