package org.computate.scolaire.frFR.annee;        

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecole.Ecole;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.year.SchoolYear
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Année
 * ApiUri.frFR: /frFR/api/annee
 * 
 * ApiTag.enUS: School
 * ApiUri.enUS: /enUS/api/year
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: AnneePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /frFR/annee
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: YearPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /enUS/year
 * 
 * UnNom.frFR: une année
 * UnNom.enUS: a year
 * Couleur: orange
 * IconeGroupe: duotone
 * IconeNom: calendar-check-o
*/                                            
public class AnneeScolaire extends AnneeScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: Ecole.anneeCles
	 * HtmlLigne: 3
	 * HtmlColonne: 3
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: école
	 * NomAffichage.enUS: school
	 */             
	protected void _ecoleCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'année dans la base de données. 
	 * Description.enUS: The primary key of the year in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */          
	protected void _anneeCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _inscriptionCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKeys
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _saisonCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearStart
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of year
	 */                   
	protected void _anneeDebut(Couverture<LocalDate> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnd
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 2
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: anneeDebut
	 * r.enUS: yearStart
	 */                      
	protected void _anneeFin(Couverture<LocalDate> c) {
		if(anneeDebut != null)
			c.o(anneeDebut.plusYears(1));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: school
	 * r: anneeDebut
	 * r.enUS: yearStart
	 */
	protected void _ecole(Couverture<Ecole> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearShortName
	 * Indexe: true
	 * Stocke: true
	 * r: anneeDebut
	 * r.enUS: yearStart
	 * r: anneeFin
	 * r.enUS: yearEnd
	 * r: FRANCE
	 * r.enUS: US
	 * r: "année %d"
	 * r.enUS: "%d year"
	 * r: "année"
	 * r.enUS: "year"
	 */                    
	protected void _anneeNomCourt(Couverture<String> c) {
		String o = "année";

		if(anneeDebut != null && anneeFin != null)
			o = String.format("%d-%d", anneeDebut.getYear(), anneeFin.getYear());
		else if(anneeDebut != null)
			o = String.format("année %d", anneeDebut.getYear());
		else if(anneeFin != null)
			o = String.format("année %d", anneeFin.getYear());

		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: anneeDebut
	 * r.enUS: yearStart
	 * r: anneeFin
	 * r.enUS: yearEnd
	 * r: FRANCE
	 * r.enUS: US
	 * r: "année scolaire %d-%d"
	 * r.enUS: "%d-%d school year"
	 * r: "année scolaire %d"
	 * r.enUS: "%d school year"
	 * r: " à %s"
	 * r.enUS: " at %s"
	 * r: ecole
	 * r.enUS: school
	 * r: EcoleNom
	 * r.enUS: SchoolName
	 * r: "année"
	 * r.enUS: "year"
	 */             
	protected void _anneeNomComplete(Couverture<String> c) {
		String o = "année";

		if(anneeDebut != null && anneeFin != null)
			o = String.format("année scolaire %d-%d", anneeDebut.getYear(), anneeFin.getYear());
		else if(anneeDebut != null)
			o = String.format("année scolaire %d", anneeDebut.getYear());
		else if(anneeFin != null)
			o = String.format("année scolaire %d", anneeFin.getYear());

		if(ecole != null)
			o += String.format(" à %s", ecole.getEcoleNom());

		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * r: anneeNomComplete
	 * r.enUS: yearCompleteName
	 */         
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(anneeNomComplete);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _classCanonicalNames
	 * Indexe: true
	 * Stocke: true
	 * r: AnneeScolaire
	 * r.enUS: SchoolYear
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 **/      
	@Override protected void _classeNomsCanoniques(List<String> l) {
		l.add(AnneeScolaire.class.getCanonicalName());
		super._classeNomsCanoniques(l);
	}
}
