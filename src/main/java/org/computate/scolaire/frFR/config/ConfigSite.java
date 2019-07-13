package org.computate.scolaire.frFR.config;   

import java.io.File;
import java.io.Serializable;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;



/**
 * NomCanonique.enUS: org.computate.enUS.java.SiteConfig
 * MotCle: classeNomSimpleConfigSite
 * enUS: Loads the properties in the application config file into specific fields. 
 * frFR: Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 */    
public class ConfigSite extends ConfigSiteGen<Object> implements Serializable {   

	/**	Tous les infos importants à propos de la requête actuelle. **/
	protected void _requeteSite_(RequeteSiteFrFR o) {
	}

	/**	L'écouteur de contexte du site pour obtenir des objets globals du contexte. **/
	protected void _siteContexte_(Couverture<SiteContexteFrFR> c) {
	}

	/**	Le chemin vers le fichier de config du site. **/
	protected void _configChemin(Couverture<String> c) {   
		String o = System.getenv("configChemin");
		c.o(o);
	}

	/**
	/**	
	 * r: fichierConfig
	 * r.enUS: configFile
	 * enUS: The INI Configuration Object for the config file. 
	 **/ 
	protected void _config(Couverture<INIConfiguration> c) {
		Configurations configurations = new Configurations();
		File fichierConfig = new File(configChemin);
		if(configChemin != null && fichierConfig.exists()) {
			try {
				INIConfiguration o = configurations.ini(fichierConfig);
				c.o(o);
			} catch (ConfigurationException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	/**	Le nom du groupe principal du config pour ce site Web. **/
	protected void _identifiantSite(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv("appliNom");
		else
			o = config.getString("appliNom");

		c.o(o);
	}

	/**	Le préfixe déjà échappé pour trouver des propriétés du site. **/
	protected void _prefixeEchappe(Couverture<String> c) {
		String o = StringUtils.replace(identifiantSite, ".", "..") + ".";
		c.o(o);
	}

	/**	Le chemin vers le projet du site cloné de git. **/  
	protected void _appliChemin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, StringUtils.substringBefore(configChemin, "/config/"));

		c.o(o);
	}

	/**	Le chemin vers la racine de document pour le projet. **/
	protected void _racineDocument(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le nom de l'entreprise. **/
	protected void _nomEntreprise(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le nom de domaine du site. **/
	protected void _nomDomaine(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le nom d'hôte du site. **/
	protected void _siteNomHote(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le port du site. **/
	protected void _sitePort(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var));
		else
			o = config.getInt(prefixeEchappe + c.var, 8080);
		c.o(o);
	}

	/**	L'ID client Keycloak du site. **/
	protected void _authRoyaume(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	L'ID client Keycloak du site. **/
	protected void _authRessource(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	L'ID client Keycloak du site. **/
	protected void _authSecret(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	L'ID client Keycloak du site. **/
	protected void _authSslRequis(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "all");
		c.o(o);
	}

	protected void _sslJksChemin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _sslJksMotDePasse(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	L'ID client Keycloak du site. **/
	protected void _authUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	Le sel de cryptage à utiliser pour tout cryptage. **/
	protected void _cryptageSel(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le mot de passe de cryptage à utiliser pour tout cryptage. **/
	protected void _cryptageMotDePasse(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**
	 * Var.enUS: siteBaseUrl
	 * 	L'URL du domaine de base pour les URLs du site. **/
	protected void _siteUrlBase(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "https://" + siteNomHote);
		c.o(o);
	}

	/**	Le nom d'affichage du site. **/
	protected void _siteNomAffichage(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _jdbcClassePilote(Couverture<String> c) {
		String o;
		if(config == null)
			o = StringUtils.defaultIfEmpty(System.getenv(c.var), "org.postgresql.Driver");
		else
			o = config.getString(prefixeEchappe + c.var, "org.postgresql.Driver");
		c.o(o);
	}

	protected void _jdbcUtilisateur(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _jdbcMotDePasse(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _jdbcTailleMaxPiscine(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 15);
		else
			o = config.getInt(prefixeEchappe + c.var, 15);
		c.o(o);
	}

	protected void _jdbcTailleInitialePiscine(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 3);
		else
			o = config.getInt(prefixeEchappe + c.var, 3);
		c.o(o);
	}

	protected void _jdbcTailleMinPiscine(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	protected void _jdbcMaxDeclarations(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	protected void _jdbcMaxDeclarationsParConnexion(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	protected void _jdbcTempsInactiviteMax(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	/**	L'URL JDBC vers le source de données. **/ 
	protected void _jdbcUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	L'URL vers le moteur de recherche SOLR. **/
	protected void _solrUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	L'URL vers le moteur de recherche SOLR. **/
	protected void _solrUrlComputate(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le jeton d'identité Paypal pour valider des transactions Paypal. **/
	protected void _jetonIdentitePaypal(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte Facebook pour le site. **/
	protected void _compteFacebook(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte Twitter pour le site. **/
	protected void _compteTwitter(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte Google Plus pour le site. **/
	protected void _compteGooglePlus(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte Instagram pour le site. **/
	protected void _compteInstagram(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte Youtube pour le site. **/
	protected void _compteYoutube(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	L'identifiant du canal Youtube pour le site. **/
	protected void _identifiantCanalYoutube(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte Pinterest pour le site. **/
	protected void _comptePinterest(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte Open Clipart pour le site. **/
	protected void _compteOpenclipart(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le compte mail pour le site. **/
	protected void _compteMail(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le rôle OpenID Connect pour un administrateur. **/
	protected void _roleAdmin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	L'addresse mail pour l'administrateur du site pour les rapports d'erreur. **/
	protected void _mailAdmin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	Le nombre de fils pour executer des tâches daemon dans le site. **/
	protected void _nombreExecuteurs(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = Integer.parseInt(System.getenv(c.var), 1);
		else
			o = config.getInt(prefixeEchappe + c.var, 1);
		c.o(o);
	}

	protected void _openApiVersion(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "3.0.0");
		c.o(o);
	}

	protected void _apiDescription(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "2.0");
		c.o(o);
	}

	protected void _apiTitre(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, siteNomAffichage);
		c.o(o);
	}

	protected void _apiTermsService(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "http://swagger.io/terms/");
		c.o(o);
	}

	protected void _apiVersion(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "1");
		c.o(o);
	}

	protected void _apiContactMail(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _apiLicenceNom(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "Apache 2.0");
		c.o(o);
	}

	protected void _apiLicenceUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "http://www.apache.org/licenses/LICENSE-2.0.html");
		c.o(o);
	}

	protected void _apiNomHote(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, siteNomHote);
		c.o(o);
	}

	protected void _apiCheminBase(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "/api/v" + apiVersion);
		c.o(o);
	}

	/**
	 * Var.enUS: vertxServiceAddress
	 * r: addresse
	 * r.enUS: address
	 */
	protected void _vertxServiceAddresse(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "addresse");
		c.o(o);
	}

	protected void _statiqueUrlBase(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "/static");
		c.o(o);
	}
}
