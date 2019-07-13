package org.computate.scolaire.enUS.vertx;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import io.vertx.core.AbstractVerticle;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;

/**	
 *	A Java class to start the Vert.x application as a main method. 
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppVertx&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AppVertxGen<DEV> extends AbstractVerticle {
	public static final String closeDataError1 = "Could not close the database client connection. ";
	public static final String closeDataError = closeDataError1;


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

	public static final String[] AppVertxVals = new String[] { closeDataError1 };
}
