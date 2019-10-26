package org.computate.scolaire.frFR.inscription.form;

import java.text.Normalizer;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.form.part.PartForm;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.form.EnrollmentForm
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Formulaire d'Inscription
 * ApiUri.frFR: /api/form-inscription
 * 
 * ApiTag.enUS: Block
 * ApiUri.enUS: /api/enrollment-form
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: FormInscriptionPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /form-inscription
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: EnrollmentFormPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /enrollment-form
 * 
 * UnNom.frFR: un formulaire d'inscription
 * UnNom.enUS: an enrollment form
 * Couleur: indigo
 * IconeGroupe: regular
 * IconeNom: bell
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/     
public class FormInscription extends FormInscriptionGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentFormKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire du bloc dans la base de données. 
	 * Description.enUS: The primary key of the school block in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */               
	protected void _formInscriptionCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: formPartKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: PartForm.formInscriptionCle
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * NomAffichage.frFR: parts
	 * NomAffichage.enUS: parts
	 */              
	protected void _partFormCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: InscriptionScolaire.blocCles
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 * NomAffichage.frFR: inscriptions
	 * NomAffichage.enUS: enrollments
	 */              
	protected void _inscriptionCles(List<Long> o) {}

	/**
	 * Var.enUS: yearSearch
	 * r: anneeCles
	 * r.enUS: yearKeys
	 * r: AnneeScolaire
	 * r.enUS: SchoolYear
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 * r: formInscriptionCles
	 * r.enUS: enrollmentFormKeys
	 */
	protected void _anneeRecherche(ListeRecherche<AnneeScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("formInscriptionCles_indexed_longs:" + pk);
		l.setC(AnneeScolaire.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: year_
	 * r: anneeRecherche
	 * r.enUS: yearSearch
	 * Ignorer: true
	 */           
	protected void _annee_(Couverture<AnneeScolaire> c) {
		if(anneeRecherche.size() > 0) {
			c.o(anneeRecherche.get(0));
		}
	}

	/**
	 * Var.enUS: formPartSearch
	 * r: formInscriptionCles
	 * r.enUS: enrollmentFormKeys
	 * r: PartForm
	 * r.enUS: FormPart
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */
	protected void _partFormRecherche(ListeRecherche<PartForm> l) {
		l.setQuery("*:*");
		l.addFilterQuery("formInscriptionCles_indexed_longs:" + pk);
		l.setC(PartForm.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: partFormList
	 * r: partFormRecherche
	 * r.enUS: formPartSearch
	 * Ignorer: true
	 */           
	protected void _partFormListe_(Couverture<List<PartForm>> c) {
		c.o(partFormRecherche.getList());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: école
	 * NomAffichage.enUS: school
	 * r: EcoleCle
	 * r.enUS: SchoolKey
	 * r: annee
	 * r.enUS: year
	 */              
	protected void _ecoleCle(Couverture<Long> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: année
	 * NomAffichage.enUS: year
	 * r: AnneeCle
	 * r.enUS: YearKey
	 * r: annee
	 * r.enUS: year
	*/             
	protected void _anneeCle(Couverture<Long> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeCle());
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
	 * r: annee
	 * r.enUS: year
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleNomComplet());
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
	 * r: annee
	 * r.enUS: year
	 */               
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(annee_ != null)
			c.o((String)annee_.getEcoleEmplacement());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearStart
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of year
	 * r: AnneeDebut
	 * r.enUS: YearStart
	 * r: annee
	 * r.enUS: year
	 */                   
	protected void _anneeDebut(Couverture<Integer> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnd
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: AnneeFin
	 * r.enUS: YearStart
	 * r: annee
	 * r.enUS: year
	 */                    
	protected void _anneeFin(Couverture<Integer> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearShortName
	 * Indexe: true
	 * Stocke: true
	 * r: AnneeNomCourt
	 * r.enUS: YearShortName
	 * r: annee
	 * r.enUS: year
	 */                    
	protected void _anneeNomCourt(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeNomCourt());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: AnneeNomComplet
	 * r.enUS: YearCompleteName
	 * r: annee
	 * r.enUS: year
	 */                  
	protected void _anneeNomComplet(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeNomComplet());
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: enrollmentFormCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * HtmlColonne: 1
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: %s - %s %s %s par mois %s
	 * r.enUS: %s - %s %s %s per month %s
	 * r: anneeNomCourt
	 * r.enUS: yearShortName
	 * r: "formulaire d'inscription pour l'%s"
	 * r.enUS: "enrollment form for the "
	 * r: "formulaire d'inscription"
	 * r.enUS: "enrollment form"
	 */  
	protected void _formInscriptionNomComplet(Couverture<String> c) {
		String o;
		if(anneeNomCourt == null)
			o = String.format("formulaire d'inscription");
		else
			o = String.format("formulaire d'inscription pour l'%s", anneeNomCourt);
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: formInscriptionNomComplet
	 * r.enUS: enrollmentFormCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(formInscriptionNomComplet);
	}
}
