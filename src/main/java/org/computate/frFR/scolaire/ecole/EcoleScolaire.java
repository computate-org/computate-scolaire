package org.computate.frFR.scolaire.ecole;   

import java.util.List;

import org.computate.frFR.scolaire.chaine.Chaine;
import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.couverture.Couverture;



/**    
 * Modele: true
 * NomCanonique.enUS: org.computate.enUS.education.school.SchoolEducation
 * ApiUri.enUS: /api/v1/school
 * ApiUri.frFR: /api/v1/ecole
 * PageUri.enUS: /school
 * PageUri.frFR: /ecole
 * ApiMethode: Recherche
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: PUT
 * ApiMethode: DELETE
 * ApiMethode: RecherchePage
 * ApiMotCleRecherchePage.frFR: page
 * ApiMotCleRecherchePage.enUS: page
 * ApiUriRecherchePage.frFR: /ecole
 * ApiUriRecherchePage.enUS: /school
 * ApiTypeMedia200GETPage: text/html
 * PageRecherchePage: EcoleScolairePage
 * UnNomMinuscule.frFR: une école
 * UnNomMinuscule.enUS: a school
 * Couleur: pink
 * IconeGroupe: regular
 * IconeNom: fort-awesome
 * Page: true
 * 
 * Role.frFR: SiteAdmin
 * Map.this.Integer: 1
 * ApiTag.enUS: AcademicSchool
 * ApiTag.frFR: EcoleScolaire
 */                 
public class EcoleScolaire extends EcoleScolaireGen<Cluster> {  

	/**
	 * Var.enUS: contactInfo
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _ecoleCle(Couverture<Long> c) {
		c.o(pk);
	}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _enfantCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _blocCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _groupeAgeCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _sessionCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _saisonCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _anneeCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _supprime(Couverture<Boolean> c) {
		c.o(false);
	}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _archive(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _scolaireTri(Couverture<Integer> c) {
		c.o(1);
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(1);
	}

	/**
	 * Stocke: true
	 * NomAffichage.frFR: Nom de l'école
	 * NomAffichage.enUS: Name of the school
	 * Description.frFR: 
	 * Description.enUS: 
	 */
	protected void _ecoleNom(Chaine o) {
	}

	/**
	 * Stocke: true
	 * NomAffichage.frFR: Numéro de téléphone
	 * NomAffichage.enUS: Phone number
	 * Description.frFR: 
	 * Description.enUS: 
	 */
	protected void _ecoleNumeroTelephone(Chaine o) {
	}
	/**
	 * Stocke: true
	 * NomAffichage.frFR: Addresse
	 * NomAffichage.enUS: Address
	 * Description.frFR: 
	 * Description.enUS: 
	 */
	protected void _ecoleAddresse(Chaine o) {
	}

	/**
	 * Stocke: true
	 * NomAffichage.enUS: Administrator of the school
	 * NomAffichage.frFR: Administrateur de l'école
	 * Description.frFR: 
	 * Description.enUS: 
	 */  
	protected void _ecoleAdministrateurNom(Chaine o) {
	}
	
	/**
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _objetSuggerePoids(Couverture<Double> c) {
		c.o(1D);
	}
	
	/**  
	 * suggere: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _objetSuggere(Chaine o) { 
		o.s(ecoleNom);
	}

	/**
	 * Stocke: true
	 * HtmlLigne: 7
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */ 
	protected void _ecoleNomCourt(Chaine o) {
		o.s(ecoleNom);
	}

	public void htmlBody() {
		super.htmlBody();
	}
}

