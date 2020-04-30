package org.computate.scolaire.frFR.config;    

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.couverture.Couverture;



/** 
 * NomCanonique.enUS: org.computate.scolaire.enUS.config.SiteConfig
 * MotCle: classeNomSimpleConfigSite
 * enUS: Loads the properties in the application config file into specific fields. 
 * frFR: Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 */    
public class ConfigSite extends ConfigSiteGen<Object> implements Serializable {  

	/**	
	 * Var.enUS: configPath
	 * frFR: Le chemin vers le fichier de config du site. 
	 * enUS: The path to the config file of the site. 
	 * r: configChemin
	 * r.enUS: configPath
	 * **/
	protected void _configChemin(Couverture<String> c) {   
		String o = System.getenv("configChemin");
		c.o(o);
	}

	/**
	/**	
	 * r: fichierConfig
	 * r.enUS: configFile
	 * enUS: The INI Configuration Object for the config file. 
	 * r: configChemin
	 * r.enUS: configPath
	 **/ 
	protected void _config(Couverture<INIConfiguration> c) {
		if(configChemin != null) {
			Configurations configurations = new Configurations();
			File fichierConfig = new File(configChemin);
			if(fichierConfig.exists()) {
				try {
					INIConfiguration o = configurations.ini(fichierConfig);
					c.o(o);
				} catch (ConfigurationException e) {
					ExceptionUtils.rethrow(e);
				}
			}
		}
	}

	/**	
	 * Var.enUS: siteIdentifier
	 * frFR: Le nom du groupe principal du config pour ce site Web. 
	 * enUS: The name of the principal group of settings of the config for this website. 
	 * r: appliNom
	 * r.enUS: appName
	 * **/
	protected void _identifiantSite(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv("appliNom");
		else
			o = config.getString("appliNom");

		c.o(o);
	}

	/**	
	 * Var.enUS: prefixEscaped
	 * frFR: Le préfixe déjà échappé pour trouver des propriétés du site. 
	 * enUS: The already escaped prefix to find the properties of the site. 
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 * **/
	protected void _prefixeEchappe(Couverture<String> c) {
		String o = StringUtils.replace(identifiantSite, ".", "..") + ".";
		c.o(o);
	}

	/**	
	 * Var.enUS: appPath
	 * frFR: Le chemin vers le projet du site cloné de git. 
	 * enUS: The path to the project of the site cloned from git. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: configChemin
	 * r.enUS: configPath
	 * **/  
	protected void _appliChemin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, StringUtils.substringBefore(configChemin, "/config/"));

		c.o(o);
	}

	/**	
	 * Var.enUS: docRoot
	 * frFR: Le chemin vers la racine de document pour le projet. 
	 * enUS: The path to the docroot for the project. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _racineDocument(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: companyName
	 * frFR: Le nom de l'entreprise. 
	 * enUS: The name of the company. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _nomEntreprise(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: domainName
	 * frFR: Le nom de domaine du site. 
	 * enUS: The domain name of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _nomDomaine(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: siteHostName
	 * frFR: Le nom d'hôte du site. 
	 * enUS: The host name of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _siteNomHote(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * frFR: Le port du site. 
	 * enUS: The port of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _sitePort(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var));
		else
			o = config.getInt(prefixeEchappe + c.var, 8080);
		c.o(o);
	}

	/**	
	 * Var.enUS: authRealm
	 * frFR: Le royaume Keycloak du site. 
	 * enUS: The Keycloak realm of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 * **/
	protected void _authRoyaume(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	
	 * Var.enUS: authResource
	 * frFR: L'ID client Keycloak du site. 
	 * enUS: The Keycloak client ID of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 * **/
	protected void _authRessource(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	
	 * frFR: Le secret client Keycloak du site. 
	 * enUS: The Keycloak client secret of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 * **/
	protected void _authSecret(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	
	 * Var.enUS: authSslRequired
	 * frFR: Si SSL est requis dans Keycloak pour le site. 
	 * enUS: Whether SSL is required in Keycloak for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _authSslRequis(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "all");
		c.o(o);
	}

	/**
	 * Var.enUS: sslJksPath
	 * frFR: Le chemin vers le keystore Java pour le site. 
	 * enUS: The path to the Java keystore for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 */
	protected void _sslJksChemin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**
	 * Var.enUS: sslJksPassword
	 * frFR: Le mot de passe pour le keystore Java pour le site. 
	 * enUS: The password for the Java keystore for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 */
	protected void _sslJksMotDePasse(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**
	 * frFR: L'URL vers le serveur Keycloak. 
	 * enUS: The URL to the Keycloak server. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 */
	protected void _authUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	
	 * Var.enUS: encryptionSalt
	 * enUS: The encryption salt to use for all database encryption. 
	 * frFR: Le sel de cryptage à utiliser pour tout cryptage de base de données. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _cryptageSel(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: encryptionPassword
	 * frFR: Le mot de passe de cryptage à utiliser pour tout cryptage de base de données. 
	 * enUS: The encryption password to use for all encryption of the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
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
	 * frFR: L'URL du domaine de base pour les URLs du site. 
	 * enUS: The base URL for the URLs of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: siteNomHote
	 * r.enUS: siteHostName
	 * **/
	protected void _siteUrlBase(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "https://" + siteNomHote);
		c.o(o);
	}

	/**	
	 * Var.enUS: siteDisplayName
	 * frFR: Le nom d'affichage du site. 
	 * enUS: The display name of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: identifiantSite
	 * r.enUS: siteIdentifier
	 * **/
	protected void _siteNomAffichage(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcDriverClass
	 * frFR: Le nom de la classe du pilote JDBC pour la base de données. 
	 * enUS: The class name of the JDBC driver class for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcClassePilote(Couverture<String> c) {
		String o;
		if(config == null)
			o = StringUtils.defaultIfEmpty(System.getenv(c.var), "org.postgresql.Driver");
		else
			o = config.getString(prefixeEchappe + c.var, "org.postgresql.Driver");
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcUsername
	 * frFR: Le nom d'utilisateur pour la base de données. 
	 * enUS: The username for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcUtilisateur(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcPassword
	 * frFR: Le mot de passe pour la base de données. 
	 * enUS: The password for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcMotDePasse(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcMaxPoolSize
	 * frFR: La taille maximale du piscine pour la base de données. 
	 * enUS: The max pool size for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcTailleMaxPiscine(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 15);
		else
			o = config.getInt(prefixeEchappe + c.var, 15);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcInitialPoolSize
	 * frFR: La taille initiale du piscine pour la base de données. 
	 * enUS: The max pool size for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcTailleInitialePiscine(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 3);
		else
			o = config.getInt(prefixeEchappe + c.var, 3);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcMinPoolSize
	 * frFR: La taille minimale du piscine pour la base de données. 
	 * enUS: The max pool size for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcTailleMinPiscine(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcMaxStatements
	 * frFR: La declarations maximale pour la base de données. 
	 * enUS: The max statements for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcMaxDeclarations(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcMaxStatementsPerConnection
	 * frFR: La declarations maximale par connexion pour la base de données. 
	 * enUS: The max statements per connection for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcMaxDeclarationsParConnexion(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	/**	
	 * Var.enUS: jdbcMaxIdleTime
	 * frFR: Le temps d'inactivité maximale pour la base de données. 
	 * enUS: The max idle time for the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _jdbcTempsInactiviteMax(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	/**	
	 * frFR: L'URL JDBC vers la base de données. 
	 * enUS: The JDBC URL to the database. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/ 
	protected void _jdbcUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * frFR: L'URL vers le moteur de recherche SOLR. 
	 * enUS: The URL to the SOLR search engine. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _solrUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * frFR: L'URL vers le moteur de recherche SOLR pour le projet computate. 
	 * enUS: The URL to the SOLR search engine for the computate project. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _solrUrlComputate(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: accountFacebook
	 * frFR: Le compte Facebook pour le site. 
	 * enUS: The Facebook account for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _compteFacebook(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: accountTwitter
	 * frFR: Le compte Twitter pour le site. 
	 * enUS: The Twitter account for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _compteTwitter(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: accountInstagram
	 * frFR: Le compte Instagram pour le site. 
	 * enUS: The Instagram account for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _compteInstagram(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: accountYoutube
	 * frFR: Le compte Youtube pour le site. 
	 * enUS: The Youtube account for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _compteYoutube(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: accountPinterest
	 * frFR: Le compte Pinterest pour le site. 
	 * enUS: The Pinterest account for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _comptePinterest(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: accountEmail
	 * frFR: Le compte mail pour le site. 
	 * enUS: The Email account for the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _compteMail(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * frFR: Le rôle OpenID Connect pour un administrateur. 
	 * enUS: The OpenID Connect role for an administrator. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _roleAdmin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: emailAdmin
	 * frFR: L'addresse mail pour l'administrateur du site pour les rapports d'erreur. 
	 * enUS: The email address for the administrator of the site for the error reports. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailAdmin(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: numberExecutors
	 * frFR: Le nombre de fils pour executer des tâches daemon dans le site. 
	 * enUS: The number of executors for executing background tasks in the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _nombreExecuteurs(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = Integer.parseInt(ObjectUtils.defaultIfNull(System.getenv(c.var), "1"));
		else
			o = config.getInt(prefixeEchappe + c.var, 1);
		c.o(o);
	}

	/**	
	 * frFR: Le version d'OpenAPI utilisé avec Vert.x qui est probablement 3.0. 
	 * enUS: The version of OpenAPI used with Vert.x which should probably be 3.0. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _openApiVersion(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "3.0.0");
		c.o(o);
	}

	/**	
	 * frFR: Le description de votre API du site. 
	 * enUS: The description of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _apiDescription(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "2.0");
		c.o(o);
	}

	/**	
	 * Var.enUS: apiTitle
	 * frFR: Le titre de votre API du site. 
	 * enUS: The title of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: siteNomAffichage
	 * r.enUS: siteDisplayName
	 * **/
	protected void _apiTitre(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, siteNomAffichage);
		c.o(o);
	}

	/**	
	 * frFR: Le terms of service de votre API du site. 
	 * enUS: The terms of service of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _apiTermsService(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "http://swagger.io/terms/");
		c.o(o);
	}

	/**	
	 * frFR: Le version de votre API du site. 
	 * enUS: The version of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _apiVersion(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "1");
		c.o(o);
	}

	/**	
	 * Var.enUS: apiContactEmail
	 * frFR: Le mail de contact de votre API du site. 
	 * enUS: The contact email of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _apiContactMail(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: apiLicenseName
	 * frFR: Le nom de licence open source de votre API du site. 
	 * enUS: The open source license name of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _apiLicenceNom(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "Apache 2.0");
		c.o(o);
	}

	/**	
	 * Var.enUS: apiLicenseUrl
	 * frFR: L'URL de licence open source de votre API du site. 
	 * enUS: The open source license URL of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _apiLicenceUrl(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "http://www.apache.org/licenses/LICENSE-2.0.html");
		c.o(o);
	}

	/**	
	 * Var.enUS: apiHostName
	 * frFR: Le nom d'hôte de votre API du site. 
	 * enUS: The host name of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * r: siteNomHote
	 * r.enUS: siteHostName
	 * **/
	protected void _apiNomHote(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, siteNomHote);
		c.o(o);
	}

	/**	
	 * Var.enUS: apiBasePath
	 * frFR: Le chemin de base de votre API du site. 
	 * enUS: The base path of your site API. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _apiCheminBase(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "/api/v" + apiVersion);
		c.o(o);
	}

	/**	
	 * Var.enUS: staticBaseUrl
	 * frFR: L'URL de base de vos fichiers static. 
	 * enUS: The base URL of your static files. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _statiqueUrlBase(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "/static");
		c.o(o);
	}

	/**	
	 * Var.enUS: emailHost
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailHote(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: emailPort
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailPort(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var));
		else
			o = config.getInt(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: emailUsername
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailUtilisateur(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: emailPassword
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailMotDePasse(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: emailFrom
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailDe(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: emailAuth
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailAuth(Couverture<Boolean> c) {
		Boolean o;
		if(config == null)
			o = BooleanUtils.toBoolean(System.getenv(c.var));
		else
			o = BooleanUtils.toBoolean(StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "false"));
		c.o(o);
	}

	/**	
	 * Var.enUS: emailSsl
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _mailSsl(Couverture<Boolean> c) {
		Boolean o;
		if(config == null)
			o = BooleanUtils.toBoolean(System.getenv(c.var));
		else
			o = BooleanUtils.toBoolean(StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "false"));
		c.o(o);
	}

	/**	
	 * Var.enUS: siteZone
	 * frFR:
	 * enUS: The default timezone of the site. 
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 * **/
	protected void _siteZone(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), ZoneId.systemDefault().getId());
		c.o(o);
	}

	/**	
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 **/
	protected void _authorizeApiLoginId(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 **/
	protected void _authorizeTransactionKey(Couverture<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	/**	
	 * Var.enUS: paymentDay
	 * r: prefixeEchappe
	 * r.enUS: prefixEscaped
	 **/
	protected void _paiementJour(Couverture<Integer> c) {
		Integer o;
		if(config == null)
			o = Integer.parseInt(StringUtils.defaultIfBlank(System.getenv(c.var), "25"));
		else
			o = config.getInteger(prefixeEchappe + c.var, 25);
		c.o(o);
	}

	/**	
	 * Var.enUS: paymentNext
	 * r: paiementJour
	 * r.enUS: paymentDay
	 **/
	protected void _paiementProchain(Couverture<LocalDate> c) {
		LocalDate now = LocalDate.now();
		LocalDate o = LocalDate.now().getDayOfMonth() < paiementJour ? now.withDayOfMonth(paiementJour) : now.plusMonths(1).withDayOfMonth(25);
		c.o(o);
	}

}
