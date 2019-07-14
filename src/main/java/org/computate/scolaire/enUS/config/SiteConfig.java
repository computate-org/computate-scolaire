package org.computate.scolaire.enUS.config;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.wrap.Wrap;

/**	
 *	Loads the properties in the application config file into specific fields. 
 **/
public class SiteConfig extends SiteConfigGen<Object> implements Serializable {

	protected void _configChemin(Wrap<String> c) {   
		String o = System.getenv("configChemin");
		c.o(o);
	}

	/**	
	 *	The INI Configuration Object for the config file. 
	 **/
	protected void _config(Wrap<INIConfiguration> c) {
		Configurations configurations = new Configurations();
		File configFile = new File(configChemin);
		if(configChemin != null && configFile.exists()) {
			try {
				INIConfiguration o = configurations.ini(configFile);
				c.o(o);
			} catch (ConfigurationException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _identifiantSite(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv("appliNom");
		else
			o = config.getString("appliNom");

		c.o(o);
	}

	protected void _prefixeEchappe(Wrap<String> c) {
		String o = StringUtils.replace(identifiantSite, ".", "..") + ".";
		c.o(o);
	}

	protected void _appliChemin(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, StringUtils.substringBefore(configChemin, "/config/"));

		c.o(o);
	}

	protected void _racineDocument(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _nomEntreprise(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _nomDomaine(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _siteNomHote(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _sitePort(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var));
		else
			o = config.getInt(prefixeEchappe + c.var, 8080);
		c.o(o);
	}

	protected void _authRoyaume(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _authRessource(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _authSecret(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _authSslRequis(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "all");
		c.o(o);
	}

	protected void _sslJksChemin(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _sslJksMotDePasse(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _authUrl(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _cryptageSel(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _cryptageMotDePasse(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _siteBaseUrl(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "https://" + siteNomHote);
		c.o(o);
	}

	protected void _siteNomAffichage(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), identifiantSite);
		c.o(o);
	}

	protected void _jdbcClassePilote(Wrap<String> c) {
		String o;
		if(config == null)
			o = StringUtils.defaultIfEmpty(System.getenv(c.var), "org.postgresql.Driver");
		else
			o = config.getString(prefixeEchappe + c.var, "org.postgresql.Driver");
		c.o(o);
	}

	protected void _jdbcUtilisateur(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _jdbcMotDePasse(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _jdbcTailleMaxPiscine(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 15);
		else
			o = config.getInt(prefixeEchappe + c.var, 15);
		c.o(o);
	}

	protected void _jdbcTailleInitialePiscine(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 3);
		else
			o = config.getInt(prefixeEchappe + c.var, 3);
		c.o(o);
	}

	protected void _jdbcTailleMinPiscine(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	protected void _jdbcMaxDeclarations(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	protected void _jdbcMaxDeclarationsParConnexion(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	protected void _jdbcTempsInactiviteMax(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = NumberUtils.toInt(System.getenv(c.var), 0);
		else
			o = config.getInt(prefixeEchappe + c.var, 0);
		c.o(o);
	}

	protected void _jdbcUrl(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _solrUrl(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _solrUrlComputate(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _jetonIdentitePaypal(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _compteFacebook(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _compteTwitter(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _compteGooglePlus(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _compteInstagram(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _compteYoutube(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _identifiantCanalYoutube(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _comptePinterest(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _compteOpenclipart(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _compteMail(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _roleAdmin(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _mailAdmin(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _nombreExecuteurs(Wrap<Integer> c) {
		Integer o;
		if(config == null)
			o = Integer.parseInt(System.getenv(c.var), 1);
		else
			o = config.getInt(prefixeEchappe + c.var, 1);
		c.o(o);
	}

	protected void _openApiVersion(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "3.0.0");
		c.o(o);
	}

	protected void _apiDescription(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "2.0");
		c.o(o);
	}

	protected void _apiTitre(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, siteNomAffichage);
		c.o(o);
	}

	protected void _apiTermsService(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "http://swagger.io/terms/");
		c.o(o);
	}

	protected void _apiVersion(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "1");
		c.o(o);
	}

	protected void _apiContactMail(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var);
		c.o(o);
	}

	protected void _apiLicenceNom(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "Apache 2.0");
		c.o(o);
	}

	protected void _apiLicenceUrl(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "http://www.apache.org/licenses/LICENSE-2.0.html");
		c.o(o);
	}

	protected void _apiNomHote(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, siteNomHote);
		c.o(o);
	}

	protected void _apiCheminBase(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "/api/v" + apiVersion);
		c.o(o);
	}

	protected void _vertxServiceAddress(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = config.getString(prefixeEchappe + c.var, "address");
		c.o(o);
	}

	protected void _statiqueUrlBase(Wrap<String> c) {
		String o;
		if(config == null)
			o = System.getenv(c.var);
		else
			o = StringUtils.defaultIfBlank(config.getString(prefixeEchappe + c.var), "/static");
		c.o(o);
	}
}
