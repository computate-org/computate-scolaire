package org.computate.scolaire.frFR.openshift;

import java.io.File;


import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

public class OpenshiftTemplate extends OpenshiftTemplateGen<Object> {

	/**
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: ecrire
	 * r.enUS: write
	 */
	public static void main(String[] args) {
		OpenshiftTemplate api = new OpenshiftTemplate();
		api.initLoinOpenshiftTemplate();
		api.ecrireOpenshiftTemplate();
	}

	/**
	 * Var.enUS: siteRequest_
	 */
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {
	}

	/**
	 * Var.enUS: siteContext
	 */
	protected void _siteContexte(SiteContexteFrFR o) {
	}

	/**
	 * Var.enUS: siteConfig
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 */
	protected void _configSite(Couverture<ConfigSite> c) {
		c.o(siteContexte.getConfigSite());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: appPath
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: AppliChemin
	 * r.enUS: AppPath
	 **/
	protected void _appliChemin(Couverture<String> c) {
		c.o(configSite.getAppliChemin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: openApiYamlPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/
	protected void _openApiYamlChemin(Couverture<String> c) {
		c.o(appliChemin + "/.yaml");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: openApiYamlFile
	 * r: openApiYamlChemin
	 * r.enUS: openApiYamlPath
	 **/
	protected void _openApiYamlFichier(Couverture<File> c) {
		c.o(new File(openApiYamlChemin));
	}

	/**
	 * {@inheritDoc}
	 * r: creer
	 * r.enUS: create
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: openApiYamlFichier
	 * r.enUS: openApiYamlFile
	 **/
	protected void _w(Couverture<ToutEcrivain> c) {
		c.o(ToutEcrivain.creer(requeteSite_, openApiYamlFichier, "  "));
	}

	private void ecrireOpenshiftTemplate() {
		// TODO Auto-generated method stub
		
	}
}
