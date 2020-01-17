package org.computate.scolaire.frFR.pere;           

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.dad.SchoolDad
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Père
 * ApiUri.frFR: /api/pere
 * 
 * ApiTag.enUS: Dad
 * ApiUri.enUS: /api/dad
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: PerePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /pere
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: DadPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /dad
 * 
 * UnNom.frFR: un père
 * UnNom.enUS: a dad
 * Couleur: light-blue
 * IconeGroupe: regular
 * IconeNom: male
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/    
public class PereScolaire extends PereScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire du père dans la base de données. 
	 * Description.enUS: The primary key of the school dad in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */               
	protected void _pereCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: InscriptionScolaire.pereCles
	 * HtmlLigne: 6
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
		c.o(2);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _pereTri(Couverture<Integer> c) {
		c.o(2);
	}

	/**
	 * Var.enUS: enrollmentSearch
	 * r: pereCle
	 * r.enUS: dadKey
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
	 */
	protected void _inscriptionRecherche(ListeRecherche<InscriptionScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("pereCle_indexed_long:" + pk);
		l.setC(InscriptionScolaire.class);
		l.setStocker(true);
		l.setFacet(true);
		l.addFacetField("ecoleCle_indexed_long");
		l.addFacetField("anneeCle_indexed_long");
		l.addFacetField("saisonCle_indexed_long");
		l.addFacetField("sessionCle_indexed_long");
		l.addFacetField("ageCle_indexed_long");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: inscriptions
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
	 * HtmlCellule: 2
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
	 * Var.enUS: personOccupation
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: occupation
	 * NomAffichage.enUS: occupation
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 3
	 */                   
	protected void _personneOccupation(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personPhoneNumber
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: numéro de téléphone
	 * NomAffichage.enUS: phone number
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 */                   
	protected void _personneNumeroTelephone(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personEmail
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: mail
	 * NomAffichage.enUS: email
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 */                   
	protected void _personneMail(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personRelation
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: relation
	 * NomAffichage.enUS: relation
	 * r: "mère"
	 * r.enUS: "mom"
	 */                  
	protected void _personneRelation(Couverture<String> c) {
		c.o("père");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personSms
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: envoyer SMS
	 * NomAffichage.enUS: text me
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 */      
	protected void _personneSms(Couverture<Boolean> c) {
		c.o(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personReceiveEmail
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: recevoir des mails
	 * NomAffichage.enUS: receive email
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 */                   
	protected void _personneRecevoirMail(Couverture<Boolean> c) {
		c.o(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personEmergencyContact
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: contacter en cas d'urgence
	 * NomAffichage.enUS: contact in case of emergency
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 3
	 */                  
	protected void _personneContactUrgence(Couverture<Boolean> c) {
		c.o(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personPickup
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: autorisé à venir chercher
	 * NomAffichage.enUS: authorized to pickup
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 4
	 */                 
	protected void _personneChercher(Couverture<Boolean> c) {
		c.o(true);
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: dadCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * HtmlColonne: 1
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: personneNomComplet
	 * r.enUS: personCompleteName
	 */ 
	protected void _pereNomComplet(Couverture<String> c) {
		c.o(personneNomComplet);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: pereNomComplet
	 * r.enUS: dadCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(pereNomComplet);
	}
}
