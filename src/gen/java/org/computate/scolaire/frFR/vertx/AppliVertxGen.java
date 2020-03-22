package org.computate.scolaire.frFR.vertx;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.AbstractVerticle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliVertx&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AppliVertxGen<DEV> extends AbstractVerticle {
	public static final String configurerDonneesErreurConnexion1 = "Impossible d'ouvrir la connexion du client de base de données. ";
	public static final String configurerDonneesErreurConnexion = configurerDonneesErreurConnexion1;
	public static final String configurerDonneesSuccesConnexion1 = "La connexion du client de base de données a réussi. ";
	public static final String configurerDonneesSuccesConnexion = configurerDonneesSuccesConnexion1;
	public static final String configurerDonneesErreurInit1 = "Impossible d'initialiser les tables de la base de données. ";
	public static final String configurerDonneesErreurInit = configurerDonneesErreurInit1;
	public static final String configurerDonneesSuccesInit1 = "Les tables de base de données ont été créées avec succès. ";
	public static final String configurerDonneesSuccesInit = configurerDonneesSuccesInit1;

	public static final String configurerClusterErreurDonnees1 = "Impossible de configurer les données du cluster partagé.";
	public static final String configurerClusterErreurDonnees = configurerClusterErreurDonnees1;
	public static final String configurerClusterSuccesDonnees1 = "Les données du cluster partagé ont été configurées avec succès. ";
	public static final String configurerClusterSuccesDonnees = configurerClusterSuccesDonnees1;

	public static final String configurerOpenApiErreur1 = "Impossible de configurer le serveur auth et le API.";
	public static final String configurerOpenApiErreur = configurerOpenApiErreur1;
	public static final String configurerOpenApiSucces1 = "Le serveur auth et le API ont été configurées avec succès. ";
	public static final String configurerOpenApiSucces = configurerOpenApiSucces1;

	public static final String configurerControlesSanteErreurBaseDeDonnees1 = "La base de données n'est pas configurée correctement. ";
	public static final String configurerControlesSanteErreurBaseDeDonnees = configurerControlesSanteErreurBaseDeDonnees1;
	public static final String configurerControlesSanteVideSolr1 = "Le moteur de recherche Solr est vide. ";
	public static final String configurerControlesSanteVideSolr = configurerControlesSanteVideSolr1;
	public static final String configurerControlesSanteErreurSolr1 = "Le moteur de recherche Solr n'est pas configuré correctement. ";
	public static final String configurerControlesSanteErreurSolr = configurerControlesSanteErreurSolr1;
	public static final String configurerControlesSanteErreurVertx1 = "L'application Vert.x n'est pas configuré correctement. ";
	public static final String configurerControlesSanteErreurVertx = configurerControlesSanteErreurVertx1;

	public static final String demarrerServeurErreurServeur1 = "Le serveur n'est pas configurée correctement. ";
	public static final String demarrerServeurErreurServeur = demarrerServeurErreurServeur1;
	public static final String demarrerServeurSuccesServeur1 = "Le serveur HTTP est démarré : %s:%s";
	public static final String demarrerServeurSuccesServeur = demarrerServeurSuccesServeur1;
	public static final String demarrerServeurAvantServeur1 = "Le serveur HTTP est démarré : %s:%s";
	public static final String demarrerServeurAvantServeur = demarrerServeurAvantServeur1;

	public static final String fermerDonneesErreur1 = "Impossible de fermer la connexion du client de base de données. ";
	public static final String fermerDonneesErreur = fermerDonneesErreur1;
	public static final String fermerDonneesSucces1 = "La connexion client de la base de données a été fermée.";
	public static final String fermerDonneesSucces = fermerDonneesSucces1;


	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAppliVertx = false;

	public AppliVertx initLoinAppliVertx(RequeteSiteFrFR requeteSite_) {
		if(!dejaInitialiseAppliVertx) {
			dejaInitialiseAppliVertx = true;
			initLoinAppliVertx();
		}
		return (AppliVertx)this;
	}

	public void initLoinAppliVertx() {
		initAppliVertx();
	}

	public void initAppliVertx() {
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAppliVertx(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAppliVertx(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAppliVertx(String var) {
		AppliVertx oAppliVertx = (AppliVertx)this;
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
				o = attribuerAppliVertx(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAppliVertx(String var, Object val) {
		AppliVertx oAppliVertx = (AppliVertx)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAppliVertx(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAppliVertx(String var, String val) {
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
		sb.append("AppliVertx { ");
		sb.append(" }");
		return sb.toString();
	}

	public static final String[] AppliVertxVals = new String[] { configurerDonneesErreurConnexion1, configurerDonneesSuccesConnexion1, configurerDonneesErreurInit1, configurerDonneesSuccesInit1, configurerClusterErreurDonnees1, configurerClusterSuccesDonnees1, configurerOpenApiErreur1, configurerOpenApiSucces1, configurerControlesSanteErreurBaseDeDonnees1, configurerControlesSanteVideSolr1, configurerControlesSanteErreurSolr1, configurerControlesSanteErreurVertx1, demarrerServeurErreurServeur1, demarrerServeurSuccesServeur1, demarrerServeurAvantServeur1, fermerDonneesErreur1, fermerDonneesSucces1 };
}
