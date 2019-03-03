package org.computate.frFR.scolaire.utilisateur;   

import java.time.ZonedDateTime;
import java.util.List;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**
 * Modele: true
 * ApiUri.enUS: /api/v1/site/user
 * ApiUri.frFR: /api/v1/site/utilisateur
 */                
public class UtilisateurSite extends UtilisateurSiteGen<Cluster> { 

	/**
	 * Indexe: true
	 * Stocke: true
	 * attribuer: CalculInr.utilisateurPk
	 */ 
	protected void _calculInrPks(List<Long> l) {
	}

	/**
	 * Var.enUS: _siteRequest
	 */
	protected void _requeteSite_(Couverture<RequeteSite> c) {}

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
	 * Var.enUS: _userId
	 * description.frFR: L'identifiant Keycloak pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _utilisateurId(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurId();
		c.o(o);
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
	 * Var.enUS: _modelDeleted
	 * description.frFR: Marquer le modèle comme supprimé. 
	 * On ne devrait jamais actuellement supprimer de la base de données. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _modeleSupprime(Couverture<Boolean> c) {
		c.o(false);
	}

	/**	
	 * Var.enUS: _modelCreated
	 * description.frFR: La date créée pour le modèle. 
	 * Indexe: true
	 * Stocke: true
	 */         
	protected void _modeleCree(Couverture<ZonedDateTime> c) {
		ZonedDateTime o = ZonedDateTime.now();
		c.o(o);
	}

	/**	
	 * Var.enUS: _modelModified
	 * description.frFR: La date modifiée pour le modele. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _modeleModifie(Couverture<ZonedDateTime> c) {
		ZonedDateTime o = ZonedDateTime.now();
		c.o(o);
	}

	/**	
	 * Var.enUS: _modelClassCanonicalName
	 * description.frFR: Le nom canonique de cette classe Java pour le modèle. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _modeleClasseNomCanonique(Couverture<String> c) {
		c.o(getClass().getCanonicalName());
	}

	/**	
	 * Var.enUS: _modelKey
	 * description.frFR: Le clé primaire pour ce modèle dans la base de données. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _modeleCle(Couverture<Long> c) {
	}

	/**	
	 * Var.enUS: _modelSuggestionStored
	 * description.frFR: Le texte de suggérée qui est affichée mais pas indexée dans le moteur de recherche. 
	 * Indexe: true
	 * Stocke: true
	 * r: utilisateurPrenom
	 * r.enUS: userFirstName
	 * r: utilisateurNomFamille
	 * r.enUS: userLastName
	 * r: utilisateurNom
	 * r.enUS: userName
	 */
	protected void _modeleSuggestionStocke(Couverture<String> c) {
		c.o(utilisateurPrenom + " " + utilisateurNomFamille + " " + utilisateurNom);
	}

	/**	
	 * Var.enUS: _modelSuggestionIndexed
	 * description.frFR: Le texte de suggérée qui est indexée dans le moteur de recherche. 
	 * Ça peut contenir plus qui est affiché dans le suggestion. 
	 * Indexe: true
	 * Stocke: true
	 * r: utilisateurPrenom
	 * r.enUS: userFirstName
	 * r: utilisateurNomFamille
	 * r.enUS: userLastName
	 * r: utilisateurNom
	 * r.enUS: userName
	 */
	protected void _modeleSuggestionIndexe(Couverture<String> c) {
		c.o(utilisateurPrenom + " " + utilisateurNomFamille + " " + utilisateurNom);
	}
}
