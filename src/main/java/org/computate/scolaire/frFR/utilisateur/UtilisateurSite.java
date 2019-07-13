package org.computate.scolaire.frFR.utilisateur;     

import java.time.ZonedDateTime;
import java.util.List;


import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/** 
 * MotCle: classeNomSimpleUtilisateurSite
 * Modele: true
 * NomCanonique.enUS: org.computate.enUS.education.school.School
 * ApiUri: /api/utilisateur
 * ApiMethode: PATCH
 * ApiMethode: RecherchePage
 * ApiMotCleRecherchePage: page
 * ApiMotCleRecherchePage: page
 * ApiUriRecherchePage: /utilisateur
 * ApiUriRecherchePage: /user
 * PageRecherchePage: UtilisateurSitePage
 * UnNomMinuscule.frFR: un utilisateur
 * UnNomMinuscule.enUS: a user
 * Couleur: deep-purple
 * IconeGroupe: regular
 * IconeNom: user
 * 
 * Role.frFR: SiteAdmin
 * Filtre: utilisateurId
 * ApiTag.enUS: SiteUser
 * ApiTag.frFR: UtilisateurSite
 */     
public class UtilisateurSite extends UtilisateurSiteGen<Cluster> {  

	/**  
	 * Var.enUS: _userId
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 2
	 * Description.frFR: L'identifiant Keycloak pour cet utilisateur. 
	 * NomAffichage.frFR: utilisateur ID
	 * NomAffichage.enUS: user ID
	 */  
	protected void _utilisateurId(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurId();
		c.o(o);
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: CalculInr.utilisateurPk
	 */   
	protected void _calculInrPks(List<Long> l) {
	}

	/**	
	 * Var.enUS: _userName
	 * description.frFR: Le nom d'utilisateur pour se connecter au site. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _utilisateurNom(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurNom();
		c.o(o);
	}

	/**	
	 * Var.enUS: _userEmail
	 * description.frFR: Le mail pour recevoir des courriels. 
	 * Indexe: true
	 * Stocke: true
	 */ 
	protected void _utilisateurMail(Couverture<String> c) {
	}

	/**	
	 * Var.enUS: _userFirstName
	 * description.frFR: Le prénom pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _utilisateurPrenom(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurPrenom();
		c.o(o);
	}

	/**	
	 * Var.enUS: _userLastName
	 * description.frFR: Le nom de famille pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 */ 
	protected void _utilisateurNomFamille(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurNomFamille();
		c.o(o);
	}

	/**	
	 * Var.enUS: _userFullName
	 * description.frFR: Le prénom et nom de famille pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * r: utilisateurPrenom
	 * r.enUS: userFirstName
	 * r: utilisateurNomFamille
	 * r.enUS: userLastName
	 */
	protected void _utilisateurNomComplet(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurNomComplet();
		c.o(o);
	}

	/**	
	 * Var.enUS: _userSite
	 * description.frFR: L'URL du site Web pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _utilisateurSite(Couverture<String> c) {
	}

	/**	
	 * Var.enUS: _userReceiveEmails
	 * description.frFR: Le nom de famille pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 */  
	protected void _utilisateurRecevoirCourriels(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seeArchived
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 2
	 * NomAffichage.frFR: voir archivé
	 * NomAffichage.enUS: see archived
	 * Description.frFR: Filter the search results in the application to show archived records. 
	 * Description.enUS: Filtrer les résultats de recherche dans l'application à voir les données archivées. 
	**/ 
	protected void _voirArchive(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seeDeleted
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 2
	 * NomAffichage.frFR: voir supprimé
	 * NomAffichage.enUS: see deleted
	 * Description.frFR: Filter the search results in the application to show deleted records. 
	 * Description.enUS: Filtrer les résultats de recherche dans l'application à voir les données supprimées. 
	 **/
	protected void _voirSupprime(Couverture<Boolean> c) {
		c.o(false);
	}

	public void htmlBody() {
		super.htmlBody();
	}  
}
