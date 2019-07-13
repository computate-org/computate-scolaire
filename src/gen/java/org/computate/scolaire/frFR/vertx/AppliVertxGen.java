package org.computate.scolaire.frFR.vertx;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import io.vertx.core.AbstractVerticle;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliVertx&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AppliVertxGen<DEV> extends AbstractVerticle {
	public static final String fermerDonneesErreur1 = "Impossible de fermer la connexion du client de base de données. ";
	public static final String fermerDonneesErreur = fermerDonneesErreur1;


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
		if(!(o instanceof AppliVertx))
			return false;
		AppliVertx that = (AppliVertx)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AppliVertx {");
		sb.append(" }");
		return sb.toString();
	}

	public static final String[] AppliVertxVals = new String[] { fermerDonneesErreur1 };
}
