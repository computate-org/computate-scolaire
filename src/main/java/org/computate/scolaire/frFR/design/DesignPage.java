package org.computate.scolaire.frFR.design;   

import java.util.List;

import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.html.part.PartHtml;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**   
 * NomCanonique.enUS: org.computate.scolaire.enUS.design.PageDesign
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Design de page
 * ApiUri.frFR: /api/design-page
 * 
 * ApiTag.enUS: Page Design
 * ApiUri.enUS: /api/page-design
 * 
 * ApiMethode: POST
 * 
 * ApiMethode.frFR: PUTImport
 * ApiMethode.frFR: PUTFusion
 * ApiMethode.frFR: PUTCopie
 * ApiMethode.enUS: PUTImport
 * ApiMethode.enUS: PUTMerge
 * ApiMethode.enUS: PUTCopy

 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: RechercheAdmin
 * ApiUriRechercheAdmin.frFR: /api/admin/design-page
 * RoleUtilisateurRechercheAdmin.frFR: true
 * 
 * ApiMethode.enUS: AdminSearch
 * ApiUriAdminSearch.enUS: /api/admin/page-design
 * RoleUtilisateurAdminSearch.enUS: true
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: DesignPagePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /design-page
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: PageDesignPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /page-design
 * 
 * ApiMethode.frFR: DesignAffichagePageRecherche
 * PageDesignAffichagePageRecherche.frFR: DesignPageAffichage
 * PageSuperDesignAffichagePageRecherche.frFR: ClusterPage
 * ApiUriDesignAffichagePageRecherche.frFR: /page
 * 
 * ApiMethode.enUS: DesignDisplaySearchPage
 * PageDesignDisplaySearchPage.enUS: DesignDisplayPage
 * PageSuperDesignDisplaySearchPage.enUS: ClusterPage
 * ApiUriDesignDisplaySearchPage.enUS: /page
 * 
 * ApiMethode.frFR: DesignPdfPageRecherche
 * PageDesignPdfPageRecherche.frFR: DesignPdfPage
 * PageSuperDesignPdfPageRecherche.frFR: ClusterPage
 * ApiUriDesignPdfPageRecherche.frFR: /pdf
 * ApiTypeMedia200DesignPdfPageRecherche: application/pdf
 * 
 * ApiMethode.enUS: DesignPdfSearchPage
 * PageDesignPdfSearchPage.enUS: DesignPdfPage
 * PageSuperDesignPdfSearchPage.enUS: ClusterPage
 * ApiUriDesignPdfSearchPage.enUS: /pdf
 * ApiTypeMedia200DesignPdfSearchPage: application/pdf
 * 
 * ApiMethode.frFR: DesignMailPageRecherche
 * PageDesignMailPageRecherche.frFR: DesignMailPage
 * PageSuperDesignMailPageRecherche.frFR: ClusterPage
 * ApiUriDesignMailPageRecherche.frFR: /mail
 * ApiTypeMedia200DesignMailPageRecherche: application/pdf
 * 
 * ApiMethode.enUS: DesignEmailSearchPage
 * PageDesignEmailSearchPage.enUS: DesignEmailPage
 * PageSuperDesignEmailSearchPage.enUS: ClusterPage
 * ApiUriDesignEmailSearchPage.enUS: /email
 * 
 * ApiMethode.frFR: PageAccueilRecherchePage
 * PagePageAccueilRecherchePage.frFR: DesignPageAffichage
 * PageSuperPageAccueilRecherchePage.frFR: ClusterPage
 * ApiUriPageAccueilRecherchePage.frFR: /
 * 
 * ApiMethode.enUS: HomePageSearchPage
 * PageHomePageSearchPage.enUS: DesignDisplayPage
 * PageSuperHomePageSearchPage.enUS: ClusterPage
 * ApiUriHomePageSearchPage.enUS: /
 * 
 * UnNom.frFR: un design de page
 * UnNom.enUS: a page design
 * Couleur: khaki
 * IconeGroupe: regular
 * IconeNom: drafting-compass
 * NomVar.frFR: design-page
 * NomVar.enUS: page-design
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
 * PublicLire: true
 * 
 * Tri.asc: designPageNomComplet
 * 
 * Lignes: 100
*/      
public class DesignPage extends DesignPageGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: pageDesignKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire du design dans la base de données. 
	 * Description.enUS: The primary key of the design in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */          
	protected void _designPageCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childDesignKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: DesignPage.designParentCles
	 * NomAffichage.frFR: designs d'enfant
	 * NomAffichage.enUS: child designs
	*/  
	protected void _designEnfantCles(List<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: parentDesignKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: DesignPage.designEnfantCles
	 * HtmlLigne: 6
	 * HtmlCellule: 1
	 * NomAffichage.frFR: designs parent
	 * NomAffichage.enUS: parent designs
	*/  
	protected void _designParentCles(List<Long> c) {
	}

	/**  
	 * {@inheritDoc}
	 * Var.enUS: htmlPartKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: PartHtml.designPageCles
	 * HtmlLigne: 7
	 * HtmlCellule: 1
	 * NomAffichage.frFR: parts
	 * NomAffichage.enUS: parts
	 */          
	protected void _partHtmlCles(List<Long> o) {}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: pageDesignCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: %s - %s %s %s par mois %s
	 * r.enUS: %s - %s %s %s per month %s
	 * r: anneeNomComplet
	 * r.enUS: yearCompleteName
	 * r: "design d'inscription pour l'%s"
	 * r.enUS: "enrollment design for the %s"
	 * r: "design d'inscription"
	 * r.enUS: "enrollment design"
	 */
	protected void _designPageNomComplet(Couverture<String> c) {
		c.o("design d'inscription");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designHidden
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 * NomAffichage.frFR: caché
	 * NomAffichage.enUS: hidden
	 * Indexe: true
	 * Stocke: true
	 */                 
	protected void _designCache(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designAdmin
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 * NomAffichage.frFR: pour admin
	 * NomAffichage.enUS: for admin
	 * Indexe: true
	 * Stocke: true
	 */                 
	protected void _designAdmin(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designIgnoreEmptyChildName
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 4
	 * NomAffichage.frFR: ignorer nom d'enfant vide
	 * NomAffichage.enUS: ignore empty child name
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _designIgnorerNomEnfantVide(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designIgnorePaymentsNotPastDue
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * NomAffichage.frFR: ignorer inscriptions pas en souffrance
	 * NomAffichage.enUS: ignore enrollments not past due
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _designIgnorerPaiementsPasEnSouffrance(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designIgnorePaymentsPastDue
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 * NomAffichage.frFR: ignorer inscriptions en souffrance
	 * NomAffichage.enUS: ignore enrollments past due
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _designIgnorerPaiementsEnSouffrance(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designFilterEnrollmentKey
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 3
	 * NomAffichage.frFR: filtrer inscription clé
	 * NomAffichage.enUS: filter enrollment key
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _designFiltrerInscriptionCle(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designEnrollmentSortMonthDayOfBirth
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 4
	 * NomAffichage.frFR: inscription tri mois jour de naissance
	 * NomAffichage.enUS: enrollment sort month day of birth
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _designInscriptionTriMoisJourDeNaissance(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designEnrollmentSortGroupName
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 5
	 * NomAffichage.frFR: inscription tri nom de groupe
	 * NomAffichage.enUS: enrollment sort group name
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _designInscriptionTriNomGroupe(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: designEnrollmentSortChildName
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 6
	 * NomAffichage.frFR: inscription tri nom d'enfant
	 * NomAffichage.enUS: enrollment sort child name
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _designInscriptionTriNomEnfant(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: searchYears
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 * NomAffichage.frFR: rechercher années
	 * NomAffichage.enUS: search years
	 * Indexe: true
	 * Stocke: true
	 */                 
	protected void _rechercherAnnees(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: searchPayments
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 * NomAffichage.frFR: rechercher paiements
	 * NomAffichage.enUS: search payments
	 * Indexe: true
	 * Stocke: true
	 */                  
	protected void _rechercherPaiements(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: searchCurrentPayments
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 3
	 * NomAffichage.frFR: rechercher paiements actuel
	 * NomAffichage.enUS: search charges due
	 * Indexe: true
	 * Stocke: true
	 */                 
	protected void _rechercherPaiementsActuel(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: designPageNomComplet
	 * r.enUS: pageDesignCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(designPageNomComplet);
	}
}
