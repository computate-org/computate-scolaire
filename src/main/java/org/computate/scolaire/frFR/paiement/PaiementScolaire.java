package org.computate.scolaire.frFR.paiement;           

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.payment.SchoolPayment
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Paiement
 * ApiUri.frFR: /api/paiement
 * 
 * ApiTag.enUS: Payment
 * ApiUri.enUS: /api/payment
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: PaiementPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /paiement
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: PaymentPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /payment
 * 
 * UnNom.frFR: un paiement
 * UnNom.enUS: a payment
 * Couleur: green
 * IconeGroupe: solid
 * IconeNom: search-dollar
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/    
public class PaiementScolaire extends PaiementScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: écoles
	 * NomAffichage.enUS: schools
	 */
	protected void _ecoleCles(List<Long> l) {
	}

	/*
	 * {@inheritDoc}
	 * Var.enUS: yearKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de l'paiement scolaire. 
	 * Description.enUS: The school year of the school child. 
	 * NomAffichage.frFR: années
	 * NomAffichage.enUS: years
	 */          
	protected void _anneeCles(List<Long> l) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La saison scolaire de l'paiement scolaire. 
	 * Description.enUS: The school season of the school child. 
	 * NomAffichage.frFR: saisons
	 * NomAffichage.enUS: seasons
	 */          
	protected void _saisonCle(List<Long> l) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the school child in the database. 
	 * NomAffichage.frFR: sessions
	 * NomAffichage.enUS: sessions
	 */          
	protected void _sessionCles(List<Long> l) {
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
	 */                  
	protected void _ageCles(List<Long> l) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the school child in the database. 
	 * NomAffichage.frFR: sessions
	 * NomAffichage.enUS: sessions
	 */          
	protected void _blocCles(List<Long> l) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: InscriptionScolaire.paiementCles
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des enfants dans la base de données. 
	 * Description.enUS: The primary key of the school children in the database. 
	 * NomAffichage.frFR: inscriptions
	 * NomAffichage.enUS: enrollments
	 */              
	protected void _inscriptionCles(List<Long> l) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'paiement dans la base de données. 
	 * Description.enUS: The primary key of the school payment in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */               
	protected void _paiementCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des enfants dans la base de données. 
	 * Description.enUS: The primary key of the school childs in the database. 
	 * NomAffichage.frFR: enfants
	 * NomAffichage.enUS: childs
	 */               
	protected void _enfantCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des mères dans la base de données. 
	 * Description.enUS: The primary key of the school moms in the database. 
	 * NomAffichage.frFR: mères
	 * NomAffichage.enUS: moms
	 */               
	protected void _mereCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des pères dans la base de données. 
	 * Description.enUS: The primary key of the school dads in the database. 
	 * NomAffichage.frFR: pères
	 * NomAffichage.enUS: dads
	 */               
	protected void _pereCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: guardianKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des gardiens dans la base de données. 
	 * Description.enUS: The primary key of the school gardians in the database. 
	 * NomAffichage.frFR: gardiens
	 * NomAffichage.enUS: guardians
	 */               
	protected void _gardienCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: contactKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des contacts d'urgence dans la base de données. 
	 * Description.enUS: The primary key of the school emergency contacts in the database. 
	 * NomAffichage.frFR: contacts d'urgence
	 * NomAffichage.enUS: emergency contacts
	 */               
	protected void _contactCles(List<Long> o) {}

	/**
	 * Var.enUS: enrollmentSearch
	 * r: paiementCles
	 * r.enUS: paymentKeys
	 * r: InscriptionScolaire
	 * r.enUS: SchoolEnrollment
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */
	protected void _inscriptionRecherche(ListeRecherche<InscriptionScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("paiementCles_indexed_longs:" + pk);
		l.setC(InscriptionScolaire.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollment_
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 * Ignorer: true
	 */   
	protected void _inscription_(Couverture<InscriptionScolaire> c) {
		if(inscriptionRecherche.size() > 0) {
			c.o(inscriptionRecherche.get(0));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentDescription
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: description
	 * NomAffichage.enUS: description
	 */   
	protected void _paiementDescription(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: date de paiement
	 * NomAffichage.enUS: payment date
	 */                   
	protected void _paiementDate(Couverture<LocalDate> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentAmount
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 * NomAffichage.frFR: montant
	 * NomAffichage.enUS: amount
	 */                     
	protected void _paiementMontant(Couverture<BigDecimal> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentCash
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 * NomAffichage.frFR: espèces
	 * NomAffichage.enUS: cash
	 */                     
	protected void _paiementEspeces(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentCheck
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 4
	 * NomAffichage.frFR: chèque
	 * NomAffichage.enUS: check
	 */                     
	protected void _paiementCheque(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentSystem
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: authorisé
	 * NomAffichage.enUS: authorized
	 */                     
	protected void _paiementSysteme(Couverture<Boolean> c) {
		c.o(false);
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: paymentCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * HtmlColonne: 1
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: EnfantNomComplet
	 * r.enUS: ChildCompleteName
	 * r: paiementDate
	 * r.enUS: paymentDate
	 * r: PaiementDate
	 * r.enUS: PaymentDate
	 * r: paiementMontant
	 * r.enUS: paymentAmount
	 * r: paiementCheque
	 * r.enUS: paymentCheck
	 * r: paiementEspeces
	 * r.enUS: paymentCash
	 * r: paiementSysteme
	 * r.enUS: paymentSystem
	 * r: paiementDescription
	 * r.enUS: paymentDescription
	 * r: paiementValeur
	 * r.enUS: paymentValue
	 * r: " par chèque"
	 * r.enUS: " by check"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: FRANCE
	 * r.enUS: US
	 */    
	protected void _paiementNomComplet(Couverture<String> c) {
		NumberFormat f = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		f.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(inscription_ != null)
			o.append(inscription_.getEnfantNomComplet());
		if(paiementDate != null)
			o.append(" ").append(strPaiementDate());
		if(paiementMontant != null)
			o.append(" ").append(f.format(paiementMontant));
		if(BooleanUtils.isTrue(paiementCheque))
			o.append(" by check");
		if(BooleanUtils.isTrue(paiementEspeces))
			o.append(" by cash");
		if(BooleanUtils.isTrue(paiementSysteme))
			o.append(" by authorize.net");
		if(!StringUtils.isEmpty(paiementDescription))
			o.append(" ").append(paiementDescription);
		c.o(o.toString());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: paiementNomComplet
	 * r.enUS: paymentCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(paiementNomComplet);
	}
}
