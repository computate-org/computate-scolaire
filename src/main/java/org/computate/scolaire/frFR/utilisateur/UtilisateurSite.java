package org.computate.scolaire.frFR.utilisateur;        

import java.util.List;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;

/**  
 * NomCanonique.enUS: org.computate.scolaire.enUS.user.SiteUser
 * 
 * Modele: true
 * Api: true
 * Indexe: true
 * 
 * ApiTag.frFR: Utilisateur
 * ApiUri.frFR: /api/utilisateur
 * 
 * ApiTag.enUS: User
 * ApiUri.enUS: /api/user
 * 
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * ApiMethode: PATCH
 * ApiMethode: POST
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: UtilisateurSitePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /utilisateur
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: SiteUserPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /user
 * 
 * MotCle: classeNomSimpleUtilisateurSite
 * Filtre: utilisateurId
 * Sauvegarde: true
 * 
 * UnNom.frFR: un utilisateur du site
 * UnNom.enUS: a site user
 * NomPluriel.frFR: utilisateurs du site
 * Couleur: gray
 * IconeGroupe: regular
 * IconeNom: user-cog
 * 
 * NomVar.frFR: utilisateur
 * NomVar.enUS: user
 * 
 * RoleUtilisateur: true
 * Role.frFR: SiteManager
 * Role.enUS: SiteManager
 */     
public class UtilisateurSite extends UtilisateurSiteGen<Cluster> {  

	/**
	 * {@inheritDoc}
	 * Var.enUS: userKeys
	 * Indexe: true
	 * Stocke: true
	 */              
	protected void _utilisateurCles(List<Long> l) {
		l.add(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: InscriptionScolaire.utilisateurCles
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 * NomAffichage.frFR: inscriptions
	 * NomAffichage.enUS: enrollments
	 */              
	protected void _inscriptionCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollments_
	 * Ignorer: true
	 */            
	protected void _inscriptions_(Couverture<List<InscriptionScolaire>> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: PaiementScolaire.utilisateurCles
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des contacts d'urgence dans la base de données. 
	 * Description.enUS: The primary key of the school emergency contacts in the database. 
	 * NomAffichage.frFR: paiements
	 * NomAffichage.enUS: payments
	 */              
	protected void _paiementCles(List<Long> o) {}

	/**
	 * Var.enUS: userName
	 * description.frFR: Le nom d'utilisateur pour se connecter au site. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 */  
	protected void _utilisateurNom(Couverture<String> c) {
	}

	/**	
	 * Var.enUS: userEmail
	 * description.frFR: Le mail pour recevoir des courriels. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
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
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId1(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId2(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId3(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId4(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId5(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId6(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId7(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId8(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId9(Couverture<String> c) {
	}

	/**	
	 * description.frFR: ID authorize.net pour l'utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */       
	protected void _customerProfileId10(Couverture<String> c) {
	}

	/**	
	 * Var.enUS: userReceiveEmails
	 * description.frFR: Le nom de famille pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: recevoir des courriels
	 * NomAffichage.enUS: receive email
	 */  
	protected void _utilisateurRecevoirCourriels(Couverture<Boolean> c) {
		c.o(false);
	}

	/**  
	 * {@inheritDoc}
	 * Var.enUS: seeArchived
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
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
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 * NomAffichage.frFR: voir supprimé
	 * NomAffichage.enUS: see deleted
	 * Description.frFR: Filter the search results in the application to show deleted records. 
	 * Description.enUS: Filtrer les résultats de recherche dans l'application à voir les données supprimées. 
	 **/
	protected void _voirSupprime(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * Var.enUS: _objectTitle
	 * r: utilisateurNomComplet
	 * r.enUS: userFullName
	 * r: utilisateurMail
	 * r.enUS: userEmail
	 * r: utilisateurNom
	 * r.enUS: userName
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: utilisateurNomComplet
	 * r.enUS: userFullName
	 */   
	@Override 
	protected void _objetTitre(Couverture<String> c) {
		c.o(utilisateurNomComplet + " " + utilisateurMail + " " + utilisateurNom);
	}
}
