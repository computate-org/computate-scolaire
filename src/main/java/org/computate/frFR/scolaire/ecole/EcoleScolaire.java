package org.computate.frFR.scolaire.ecole;    

import java.text.Normalizer;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
 * ApiTag.enUS: School
 * ApiTag.frFR: EcoleScolaire
 */                              
public class EcoleScolaire extends EcoleScolaireGen<Cluster> {   

	/**
	 * Var.enUS: contactInfo
	 * Indexe: true
	 * Stocke: true
	 * HtmlColonne: 1
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: Clé
	 * NomAffichage.enUS: Key
	 */         
	protected void _ecoleCle(Couverture<Long> c) {
		c.o(pk);
	}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _enfantCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _blocCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _groupeAgeCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _sessionCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _saisonCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _anneeCles(List<Long> o) {}
	
	/**
	 * Indexe: true
	 * Stocke: true
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
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(1);
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 1
	 * HtmlColonne: 2
	 * NomAffichage.frFR: Nom de l'école
	 * NomAffichage.enUS: Name of the school
	 * Description.frFR: Nom de l'école. 
	 * Description.enUS: Name of the school. 
	 */ 
	protected void _ecoleNom(Couverture<String> c) {
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 1
	 * HtmlColonne: 3
	 * NomAffichage.frFR: Numéro de téléphone
	 * NomAffichage.enUS: Phone number
	 * Description.frFR: Numéro de téléphone de l'école. 
	 * Description.enUS: Telephone number of the school. 
	 */    
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
	}
	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 2
	 * HtmlColonne: 4
	 * Multiligne: true
	 * NomAffichage.frFR: Addresse
	 * NomAffichage.enUS: Address
	 * Description.frFR: 
	 * Description.enUS: 
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 2
	 * NomAffichage.enUS: Administrator of the school
	 * NomAffichage.frFR: Administrateur de l'école
	 * Description.frFR: 
	 * Description.enUS: 
	 */  
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
	}
	
	/**
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
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(ecoleNom);
	}

	/**
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */ 
	protected void _ecoleNomCourt(Couverture<String> c) {
		c.o(ecoleNom);
	}

	/**
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */       
	protected void _ecoleId(Couverture<String> c) {
		if(ecoleNom != null) {
			String s = Normalizer.normalize(ecoleNom, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
			c.o(s);
		}
		else if(pk != null){
			c.o(pk.toString());
		}
	}

	/**	la version plus courte de l'URL qui commence avec « / » 
	 * Indexe: true
	 * Stocke: true
	 * r: frFR
	 * r.enUS: enUS
	 * **/
	protected void _pageUri(Couverture<String> c)  {
		if(ecoleId != null) {
			String o = "/ecole/" + ecoleId;
			c.o(o);
		}
	}

	public void htmlBody() {
		super.htmlBody();
	}
}

