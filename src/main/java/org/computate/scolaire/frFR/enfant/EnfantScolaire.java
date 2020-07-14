package org.computate.scolaire.frFR.enfant;     

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.child.SchoolChild
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Enfant
 * ApiUri.frFR: /api/enfant
 * 
 * ApiTag.enUS: Child
 * ApiUri.enUS: /api/child
 * 
 * ApiMethode: POST
 * 
 * ApiMethode.frFR: PUTImport
 * RoleUtilisateurPUTImport.frFR: true
 * ApiMethode.frFR: PUTFusion
 * ApiMethode.frFR: PUTCopie
 * ApiMethode.enUS: PUTImport
 * RoleUtilisateurPUTImport.enUS: true
 * ApiMethode.enUS: PUTMerge
 * ApiMethode.enUS: PUTCopy

 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: RechercheAdmin
 * ApiUriRechercheAdmin.frFR: /api/admin/enfant
 * RoleUtilisateurRechercheAdmin.frFR: true
 * 
 * ApiMethode.enUS: AdminSearch
 * ApiUriAdminSearch.enUS: /api/admin/child
 * RoleUtilisateurAdminSearch.enUS: true
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: EnfantPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /enfant
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: ChildPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /child
 * 
 * UnNom.frFR: un enfant
 * UnNom.enUS: a child
 * NomPluriel.enUS: children
 * Couleur: orange
 * IconeGroupe: regular
 * IconeNom: child
 * 
 * Role.frFR: SiteManager
 * Role.enUS: SiteManager
 * RoleSession: true
 * RoleUtilisateur: true
*/          
public class EnfantScolaire extends EnfantScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: childKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire du inscription dans la base de données. 
	 * Description.enUS: The primary key of the school enrollment in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */               
	protected void _enfantCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: InscriptionScolaire.enfantCle
	 * HtmlLigne: 8
	 * HtmlCellule: 1
	 * NomAffichage.frFR: inscriptions
	 * NomAffichage.enUS: enrollments
	 */              
	protected void _inscriptionCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familySort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _familleTri(Couverture<Integer> c) {
		c.o(1);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _enfantTri(Couverture<Integer> c) {
		c.o(1);
	}

	/**
	 * Var.enUS: enrollmentSearch
	 * r: enfantCle
	 * r.enUS: childKey
	 * r: InscriptionScolaire
	 * r.enUS: SchoolEnrollment
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 * r: ecoleCle
	 * r.enUS: schoolKey
	 * r: anneeCle
	 * r.enUS: yearKey
	 * r: saisonCle
	 * r.enUS: seasonKey
	 * r: sessionCle
	 * r.enUS: sessionKey
	 * r: ageCle
	 * r.enUS: ageKey
	 * r: utilisateurCles
	 * r.enUS: userKeys
	 */
	protected void _inscriptionRecherche(ListeRecherche<InscriptionScolaire> l) { 
		l.setQuery("*:*");
		l.addFilterQuery("enfantCle_indexed_long:" + pk);
		l.setC(InscriptionScolaire.class);
		l.setStocker(true);
		l.setFacet(true);
		l.addFacetField("ecoleCle_indexed_long");
		l.addFacetField("anneeCle_indexed_long");
		l.addFacetField("saisonCle_indexed_long");
		l.addFacetField("sessionCle_indexed_long");
		l.addFacetField("ageCle_indexed_long");
		l.addFacetField("utilisateurCles_indexed_longs");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollments
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 * r: inscriptions
	 * r.enUS: enrollments
	 * Ignorer: true
	 */ 
	protected void _inscriptions(List<InscriptionScolaire> l) {
		l.addAll(inscriptionRecherche.getList());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: userKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des utlisateurs dans la base de données. 
	 * Description.enUS: The primary key of the users in the database. 
	 * r: utilisateurCles
	 * r.enUS: userKeys
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 */                  
	protected void _utilisateurCles(List<Long> l) {
		l.addAll(inscriptionRecherche.getQueryResponse().getFacetField("utilisateurCles_indexed_longs").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: écoles
	 * NomAffichage.enUS: schools
	 * r: ecoleCle
	 * r.enUS: schoolKey
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 */                  
	protected void _ecoleCles(List<Long> l) {
		l.addAll(inscriptionRecherche.getQueryResponse().getFacetField("ecoleCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire du inscription scolaire. 
	 * Description.enUS: The school year of the school enrollment. 
	 * NomAffichage.frFR: années
	 * NomAffichage.enUS: years
	 * r: anneeCle
	 * r.enUS: yearKey
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 */
	protected void _anneeCles(List<Long> l) {
		l.addAll(inscriptionRecherche.getQueryResponse().getFacetField("anneeCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La saison scolaire du inscription scolaire. 
	 * Description.enUS: The school season of the school enrollment. 
	 * NomAffichage.frFR: saisons
	 * NomAffichage.enUS: seasons
	 * r: saisonCle
	 * r.enUS: seasonKey
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 */          
	protected void _saisonCles(List<Long> l) {
		l.addAll(inscriptionRecherche.getQueryResponse().getFacetField("saisonCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the school enrollment in the database. 
	 * NomAffichage.frFR: sessions
	 * NomAffichage.enUS: sessions
	 * r: sessionCle
	 * r.enUS: sessionKey
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 */          
	protected void _sessionCles(List<Long> l) {
		l.addAll(inscriptionRecherche.getQueryResponse().getFacetField("sessionCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'âge dans la base de données. 
	 * Description.enUS: The primary key of the age in the database. 
	 * NomAffichage.frFR: âges
	 * NomAffichage.enUS: ages
	 * r: ageCle
	 * r.enUS: ageKey
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 */                  
	protected void _ageCles(List<Long> l) {
		l.addAll(inscriptionRecherche.getQueryResponse().getFacetField("ageCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personFirstName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prénom
	 * NomAffichage.enUS: first name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 */                   
	protected void _personnePrenom(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personFirstNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prénom préferé
	 * NomAffichage.enUS: preferred first name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 * r: personnePrenom
	 * r.enUS: personFirstName
	 */                   
	protected void _personnePrenomPrefere(Couverture<String> c) {
		c.o(personnePrenom);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom de famille
	 * NomAffichage.enUS: last name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 */                   
	protected void _familleNom(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personCompleteName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom complèt
	 * NomAffichage.enUS: complete name
	 * r: personnePrenomPrefere
	 * r.enUS: personFirstNamePreferred
	 * r: familleNom
	 * r.enUS: familyName
	 */                   
	protected void _personneNomComplet(Couverture<String> c) {
		if(personnePrenomPrefere != null && familleNom != null)
			c.o(String.format("%s %s", personnePrenomPrefere, familleNom));
		else if(personnePrenomPrefere != null)
			c.o(personnePrenomPrefere);
		else if(familleNom != null)
			c.o(familleNom);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom complèt préferé
	 * NomAffichage.enUS: complete name preferred
	 * r: personneNomComplet
	 * r.enUS: personCompleteName
	 */                   
	protected void _personneNomCompletPrefere(Couverture<String> c) {
		c.o(personneNomComplet);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personFormalName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom formel
	 * NomAffichage.enUS: formal name
	 * r: personnePrenom
	 * r.enUS: personFirstName
	 * r: familleNom
	 * r.enUS: familyName
	 */                   
	protected void _personneNomFormel(Couverture<String> c) {
		c.o(String.format("%s %s", personnePrenom, familleNom));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personBirthDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * NomAffichage.frFR: date de naissance
	 * NomAffichage.enUS: birth date
	 */                   
	protected void _personneDateNaissance(Couverture<LocalDate> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personBirthDateYear
	 * Indexe: true
	 * Stocke: true
	 * r: personneDateNaissance
	 * r.enUS: personBirthDate
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _personneDateNaissanceDAnnee(Couverture<Integer> c) {
		if(personneDateNaissance != null)
			c.o(personneDateNaissance.getYear());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personBirthDateMonthOfYear
	 * Indexe: true
	 * Stocke: true
	 * r: personneDateNaissance
	 * r.enUS: personBirthDate
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _personneDateNaissanceMoisDAnnee(Couverture<String> c) {
		if(personneDateNaissance != null)
			c.o(personneDateNaissance.format(DateTimeFormatter.ofPattern("MMMM", Locale.FRANCE)));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personBirthDateDayOfWeek
	 * Indexe: true
	 * Stocke: true
	 * r: personneDateNaissance
	 * r.enUS: personBirthDate
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _personneDateNaissanceJourDeSemaine(Couverture<String> c) {
		if(personneDateNaissance != null)
			c.o(personneDateNaissance.format(DateTimeFormatter.ofPattern("EEEE", Locale.FRANCE)));
	}

	/**
	 * Var.enUS: strPersonBirthDate
	 * r: "d MMMM yyyy"
	 * r.enUS: "MMMM d, yyyy"
	 * r: FRANCE
	 * r.enUS: US
	 * r: personneDateNaissance
	 * r.enUS: personBirthDate
	 */
	@Override public String strPersonneDateNaissance() {
		return personneDateNaissance == null ? "" : personneDateNaissance.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRANCE));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personAgeInSeptember
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 * NomAffichage.frFR: âge
	 * NomAffichage.enUS: age
	 * r: "âge %s le 1 septembre %s"
	 * r.enUS: "age %s on September 1 %s"
	 * r: personneDateNaissance
	 * r.enUS: personBirthDate
	 * r: premierSeptembre
	 * r.enUS: septemberFirst
	 * r: annee
	 * r.enUS: year
	 */             
	protected void _personneAgeEnSeptembre(Couverture<String> c) {
		if(personneDateNaissance != null) {
			Integer annee = LocalDate.now().getYear();
			LocalDate premierSeptembre = LocalDate.of(annee, 9, 1);
			long age = ChronoUnit.YEARS.between(personneDateNaissance, premierSeptembre);
			c.o(String.format("âge %s le 1 septembre %s", age, annee));
		}
	}

	/**
	 * {@inheritDoc}
	 * Stocke: true
	 * NomAffichage.frFR: photo
	 * NomAffichage.enUS: photo
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 3
	 * ImageBase64Url.frFR: /photo
	 * ImageBase64Url.enUS: /photo
	 */               
	protected void _photo(Couverture<String> c) {
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: childCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: personneNomComplet
	 * r.enUS: personCompleteName
	 */  
	protected void _enfantNomComplet(Couverture<String> c) {
		c.o(personneNomComplet);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: enfantNomComplet
	 * r.enUS: childCompleteName
	 */ 
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(enfantNomComplet);
	}
}
