package org.computate.scolaire.frFR.utilisateur;       

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;

/** 
 * NomCanonique.enUS: org.computate.scolaire.enUS.user.SiteUser
 * Api: true
 * MotCle: classeNomSimpleUtilisateurSite
 * ApiMethode: RechercheFrFRPage
 * ApiMethode: RechercheEnUSPage
 * ApiMethode: PATCH
 * ApiUriRechercheFrFRPage: /frFR/utilisateur
 * ApiUriRechercheEnUSPage: /enUS/user
 * PageRechercheFrFRPage: UtilisateurSiteFrFRPage
 * PageRechercheEnUSPage: UtilisateurSiteEnUSPage
 * PageSuperRechercheFrFRPage: ClusterFrFRPage
 * PageSuperRechercheEnUSPage: ClusterEnUSPage
 * ApiUri: /api/site/utilisateur
 * Role: SiteAdmin
 * Filtre: utilisateurId
 * Sauvegarde: true
 * 
 * UnNom.frFR: un utilisateur du site
 * UnNom.enUS: a site user
 * NomPluriel.frFR: utilisateurs du site
 * Couleur: green
 * IconeGroupe: regular
 * IconeNom: book
 * Indexe: true
 */    
public class UtilisateurSite extends UtilisateurSiteGen<Cluster> {    

	/**  
	 * Var.enUS: userId
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 2
	 * Description.frFR: L'identifiant Keycloak pour cet utilisateur. 
	 * NomAffichage.frFR: utilisateur ID
	 * NomAffichage.enUS: user ID
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: UtilisateurId
	 * r.enUS: UserId
	 */  
	protected void _utilisateurId(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurId();
		c.o(o);
	}

	/**	
	 * Var.enUS: userName
	 * description.frFR: Le nom d'utilisateur pour se connecter au site. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 */
	protected void _utilisateurNom(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurNom();
		c.o(o);
	}

	/**	
	 * Var.enUS: userEmail
	 * description.frFR: Le mail pour recevoir des courriels. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: UtilisateurMail
	 * r.enUS: UserEmail
	 */ 
	protected void _utilisateurMail(Couverture<String> c) {
	}

	/**	
	 * Var.enUS: userFirstName
	 * description.frFR: Le prénom pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: UtilisateurPrenom
	 * r.enUS: UserFirstName
	 */
	protected void _utilisateurPrenom(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurPrenom();
		c.o(o);
	}

	/**	
	 * Var.enUS: userLastName
	 * description.frFR: Le nom de famille pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: UtilisateurNomFamille
	 * r.enUS: UserLastName
	 */ 
	protected void _utilisateurNomFamille(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurNomFamille();
		c.o(o);
	}

	/**	
	 * Var.enUS: userFullName
	 * description.frFR: Le prénom et nom de famille pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: UtilisateurNomComplet
	 * r.enUS: UserFullName
	 */ 
	protected void _utilisateurNomComplet(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurNomComplet();
		c.o(o);
	}

	/**	
	 * Var.enUS: userSite
	 * description.frFR: L'URL du site Web pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _utilisateurSite(Couverture<String> c) {
	}

	/**	
	 * Var.enUS: userReceiveEmails
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
