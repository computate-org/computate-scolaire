package org.computate.scolaire.frFR.vertx;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import io.vertx.core.AbstractVerticle;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.OuvrierVertx&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class OuvrierVertxGen<DEV> extends AbstractVerticle {
	protected static final Logger LOGGER = LoggerFactory.getLogger(OuvrierVertx.class);
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

	public static final String fermerDonneesErreur1 = "Impossible de fermer la connexion du client de base de données. ";
	public static final String fermerDonneesErreur = fermerDonneesErreur1;
	public static final String fermerDonneesSucces1 = "La connexion client de la base de données a été fermée.";
	public static final String fermerDonneesSucces = fermerDonneesSucces1;


	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseOuvrierVertx = false;

	public OuvrierVertx initLoinOuvrierVertx(RequeteSiteFrFR requeteSite_) {
		if(!dejaInitialiseOuvrierVertx) {
			dejaInitialiseOuvrierVertx = true;
			initLoinOuvrierVertx();
		}
		return (OuvrierVertx)this;
	}

	public void initLoinOuvrierVertx() {
		initOuvrierVertx();
	}

	public void initOuvrierVertx() {
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinOuvrierVertx(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirOuvrierVertx(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirOuvrierVertx(String var) {
		OuvrierVertx oOuvrierVertx = (OuvrierVertx)this;
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
				o = attribuerOuvrierVertx(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerOuvrierVertx(String var, Object val) {
		OuvrierVertx oOuvrierVertx = (OuvrierVertx)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetOuvrierVertx(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetOuvrierVertx(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrOuvrierVertx(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrOuvrierVertx(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrOuvrierVertx(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrOuvrierVertx(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqOuvrierVertx(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqOuvrierVertx(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
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
					o = definirOuvrierVertx(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirOuvrierVertx(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirOuvrierVertx(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirOuvrierVertx(String var, Object val) {
		switch(var.toLowerCase()) {
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
		if(!(o instanceof OuvrierVertx))
			return false;
		OuvrierVertx that = (OuvrierVertx)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("OuvrierVertx { ");
		sb.append(" }");
		return sb.toString();
	}

	public static final String[] OuvrierVertxVals = new String[] { configurerDonneesErreurConnexion1, configurerDonneesSuccesConnexion1, configurerDonneesErreurInit1, configurerDonneesSuccesInit1, configurerClusterErreurDonnees1, configurerClusterSuccesDonnees1, fermerDonneesErreur1, fermerDonneesSucces1 };
}
