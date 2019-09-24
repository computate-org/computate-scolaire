package org.computate.scolaire.frFR.ecole;             

import java.text.Normalizer;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;



/**                                    
 * NomCanonique.enUS: org.computate.scolaire.enUS.school.School
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Ecole
 * ApiUri.frFR: /api/ecole
 * 
 * ApiTag.enUS: School
 * ApiUri.enUS: /api/school
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: EcolePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /ecole
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: SchoolPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /school
 * 
 * UnNom.frFR: une école
 * UnNom.enUS: a school
 * Couleur: pink
 * IconeGroupe: duotone
 * IconeNom: school
 */                  
public class Ecole extends EcoleGen<Cluster> {   

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */         
	protected void _ecoleCle(Couverture<Long> c) {
		c.o(pk);
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: childKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _enfantCles(List<Long> o) {}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _blocCles(List<Long> o) {}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: ageGroupKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _groupeAgeCles(List<Long> o) {}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */
	protected void _sessionCles(List<Long> o) {}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */        
	protected void _saisonCles(List<Long> o) {}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: yearKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: AnneeScolaire.ecoleCle
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: années
	 * NomAffichage.enUS: years
	 */   
	protected void _anneeCles(List<Long> o) {}

	/**      
	 * {@inheritDoc}
	 * Var.enUS: educationSort
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
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
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
	 * {@inheritDoc}
	 * Var.enUS: schoolName
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: nom de l'école
	 * NomAffichage.enUS: name of the school
	 * Description.frFR: Nom de l'école. 
	 * Description.enUS: Name of the school. 
	 */   
	protected void _ecoleNom(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolPhoneNumber
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * NomAffichage.frFR: numéro de téléphone
	 * NomAffichage.enUS: phone number
	 * Description.frFR: Numéro de téléphone de l'école. 
	 * Description.enUS: Telephone number of the school. 
	 */         
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolAdministratorName
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 * NomAffichage.enUS: administrator of the school
	 * NomAffichage.frFR: administrateur de l'école
	 * Description.frFR: 
	 * Description.enUS: 
	 */               
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolLocation
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 * NomAffichage.enUS: location
	 * NomAffichage.frFR: l'emplacement
	 * Description.frFR: 
	 * Description.enUS: 
	 */               
	protected void _ecoleEmplacement(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolAddress
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 * Multiligne: true
	 * NomAffichage.frFR: addresse
	 * NomAffichage.enUS: address
	 * Description.frFR: 
	 * Description.enUS: 
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggestWeight
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */    
	protected void _objetSuggerePoids(Couverture<Double> c) {
		c.o(1D);
	}
	
	/**  
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: ecoleNom
	 * r.enUS: schoolName
	 */
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(ecoleNom);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolShortName
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: ecoleNom
	 * r.enUS: schoolName
	 * r: ecoleEmplacement
	 * r.enUS: schoolLocation
	 */   
	protected void _ecoleNomCourt(Couverture<String> c) {
		if(ecoleEmplacement != null)
			c.o(ecoleEmplacement);
		else 
			c.o(ecoleNom);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolCompleteName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * HtmlColonne: 1
	 * r: ecoleNom
	 * r.enUS: schoolName
	 * VarTitre: true
	 * r: ecoleEmplacement
	 * r.enUS: schoolLocation
	 * r: "%s à %s"
	 * r.enUS: "%s at %s"
	 */        
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(ecoleEmplacement != null)
			c.o(String.format("%s à %s", ecoleNom, ecoleEmplacement));
		else 
			c.o(ecoleNom);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: schoolId
	 * Indexe: true
	 * Stocke: true
	 * VarId: true
	 * HtmlLigne: 1
	 * HtmlCellule: 4
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: ID
	 * NomAffichage.enUS: ID
	 * r: ecoleNom
	 * r.enUS: schoolName
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
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * VarUrl: true
	 * r: ecoleId
	 * r.enUS: schoolId
	 * r: /frFR/ecole/
	 * r.enUS: /school/
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * **/  
	protected void _pageUrl(Couverture<String> c)  {
		if(ecoleId != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/frFR/ecole/" + ecoleId;
			c.o(o);
		}
	}

	/**
	 * r: ecoleNom
	 * r.enUS: schoolName
	 * r: École : 
	 * r.enUS: School: 
	 */ 
	protected void _pageH1(Couverture<String> c)  {
		c.o("École : " + ecoleNom);
	}

	public void htmlBody() {
		super.htmlBody();
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _classCanonicalNames
	 * Indexe: true
	 * Stocke: true
	 * r: Ecole
	 * r.enUS: School
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 **/      
	@Override protected void _classeNomsCanoniques(List<String> l) {
		l.add(Ecole.class.getCanonicalName());
		super._classeNomsCanoniques(l);
	}
}

