package org.computate.scolaire.frFR.annee;                     

import java.util.List;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.inscription.design.DesignInscription;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.year.SchoolYear
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Année
 * ApiUri.frFR: /api/annee
 * 
 * ApiTag.enUS: Year
 * ApiUri.enUS: /api/year
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
 * ApiUriPageRecherche.frFR: /annee
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: YearPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /year
 * 
 * UnNom.frFR: une année
 * UnNom.enUS: a year
 * Couleur: orange
 * IconeGroupe: regular
 * IconeNom: calendar-check
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/   
public class AnneeScolaire extends AnneeScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: Ecole.anneeCles
	 * HtmlLigne: 4
	 * HtmlCelulle: 1
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: école
	 * NomAffichage.enUS: school
	 */               
	protected void _ecoleCle(Couverture<Long> c) {
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
	 * Attribuer: SaisonScolaire.anneeCle
	 * HtmlLigne: 4
	 * HtmlCelulle: 2
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: saisons
	 * NomAffichage.enUS: seasons
	 */    
	protected void _saisonCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: educationSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _scolaireTri(Couverture<Integer> c) {
		c.o(2);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(2);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _anneeTri(Couverture<Integer> c) {
		c.o(2);
	}

	/**
	 * Var.enUS: schoolSearch
	 * r: anneeCles
	 * r.enUS: yearKeys
	 * r: Ecole
	 * r.enUS: School
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */  
	protected void _ecoleRecherche(ListeRecherche<Ecole> l) {
		l.setQuery("*:*");
		l.addFilterQuery("anneeCles_indexed_longs:" + pk);
		l.setC(Ecole.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: school_
	 * r: ecoleRecherche
	 * r.enUS: schoolSearch
	 * Ignorer: true
	 */    
	protected void _ecole_(Couverture<Ecole> c) {
		if(ecoleRecherche.size() > 0) {
			c.o(ecoleRecherche.get(0));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolName
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: EcoleNom
	 * r.enUS: SchoolName
	 * r: ecole
	 * r.enUS: school
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(ecole_ != null)
			c.o(ecole_.getEcoleNom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolCompleteName
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: EcoleNomComplet
	 * r.enUS: SchoolCompleteName
	 * r: ecole
	 * r.enUS: school
	 */  
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolLocation
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: location
	 * NomAffichage.frFR: l'emplacement
	 * r: EcoleEmplacement
	 * r.enUS: SchoolLocation
	 * r: ecole
	 * r.enUS: school
	 */               
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleEmplacement());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolAddress
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: addresse
	 * NomAffichage.enUS: address
	 * r: EcoleAddresse
	 * r.enUS: SchoolAddress
	 * r: ecole
	 * r.enUS: school
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleAddresse());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolPhoneNumber
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: numéro de téléphone
	 * NomAffichage.enUS: phone number
	 * r: EcoleNumeroTelephone
	 * r.enUS: SchoolPhoneNumber
	 * r: ecole
	 * r.enUS: school
	 */
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleNumeroTelephone());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolAdministratorName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: administrator of the school
	 * NomAffichage.frFR: administrateur de l'école
	 * r: EcoleAdministrateurNom
	 * r.enUS: SchoolAdministratorName
	 * r: ecole
	 * r.enUS: school
	 */  
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleAdministrateurNom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentFormKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: FormInscription.anneeCle
	 * HtmlLigne: 4
	 * HtmlCelulle: 2
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: formulaire d'inscription
	 * NomAffichage.enUS: enrollment form
	 */               
	protected void _formInscriptionCle(Couverture<Long> c) {
	}

	/**
	 * Var.enUS: enrollmentFormSearch
	 * r: formInscriptionCles
	 * r.enUS: enrollmentFormKeys
	 * r: FormInscription
	 * r.enUS: EnrollmentForm
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 * r: DesignInscription
	 * r.enUS: EnrollmentDesign
	 */   
	protected void _formInscriptionRecherche(ListeRecherche<DesignInscription> l) {
		l.setQuery("*:*");
		l.addFilterQuery("formInscriptionCles_indexed_longs:" + pk);
		l.setC(DesignInscription.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearStart
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 1
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of year
	 */                   
	protected void _anneeDebut(Couverture<Integer> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnd
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 2
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: anneeDebut
	 * r.enUS: yearStart
	 */                       
	protected void _anneeFin(Couverture<Integer> c) {
		if(anneeDebut != null)
			c.o(anneeDebut + 1);
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
			o = String.format("%d-%d", anneeDebut, anneeFin);
		else if(anneeDebut != null)
			o = String.format("année %d", anneeDebut);
		else if(anneeFin != null)
			o = String.format("année %d", anneeFin);

		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: yearCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * HtmlColonne: 1
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
	 * r: EcoleNom
	 * r.enUS: SchoolName
	 * r: "année"
	 * r.enUS: "year"
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 */                 
	protected void _anneeNomComplet(Couverture<String> c) {
		String o = "année";

		if(anneeDebut != null && anneeFin != null)
			o = String.format("année scolaire %d-%d", anneeDebut, anneeFin);
		else if(anneeDebut != null)
			o = String.format("année scolaire %d", anneeDebut);
		else if(anneeFin != null)
			o = String.format("année scolaire %d", anneeFin);

		if(ecoleNomComplet != null)
			o += String.format(" à %s", ecoleNomComplet);

		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: anneeNomComplet
	 * r.enUS: yearCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(anneeNomComplet);
	}
}
