package org.computate.scolaire.frFR.recu;                    

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.receipt.SchoolReceipt
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Reçu
 * ApiUri.frFR: /api/recu
 * 
 * ApiTag.enUS: Receipt
 * ApiUri.enUS: /api/receipt
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
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: RecuPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /paiement
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: ReceiptPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /receipt
 * 
 * UnNom.frFR: un reçu
 * UnNom.enUS: a receipt
 * Couleur: light-green
 * IconeGroupe: solid
 * IconeNom: file-invoice-dollar
 * 
 * Role.frFR: SiteManager
 * Role.enUS: SiteManager
 * 
 * Tri.desc: paiementDate
 * 
 * Lignes: 100
*/   
public class RecuScolaire extends RecuScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: receiptKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de le reçu dans la base de données. 
	 * Description.enUS: The primary key of the school receipt in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */         
	protected void _recuCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: Ecole.recuCles
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: école
	 * NomAffichage.enUS: school
	 */
	protected void _ecoleCle(Couverture<Long> c) {
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
	 * r: ecoleCle
	 * r.enUS: schoolKey
	 */  
	protected void _ecoleRecherche(ListeRecherche<Ecole> l) {
		l.setQuery("*:*");
		l.addFilterQuery("pk_indexed_long:" + ecoleCle);
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
	 * Var.enUS: schoolAddress
	 * Indexe: true
	 * Stocke: true
	 * r: inscription
	 * r.enUS: enrollment
	 * r: EcoleAddresse
	 * r.enUS: SchoolAddress
	 * r: ecole
	 * r.enUS: school
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
		if(ecole_ != null)
			c.o(ecole_.getEcoleAddresse());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolPhoneNumber
	 * Indexe: true
	 * Stocke: true
	 * r: inscription
	 * r.enUS: enrollment
	 * r: EcoleNumeroTelephone
	 * r.enUS: SchoolPhoneNumber
	 * r: ecole
	 * r.enUS: school
	 */
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
		if(ecole_ != null)
			c.o(ecole_.getEcoleNumeroTelephone());
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
	 * HtmlColonne: 3
	 */                
	protected void _paiementDate(Couverture<LocalDate> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentYear
	 * Indexe: true
	 * Stocke: true
	 * r: paiementDate
	 * r.enUS: paymentDate
	 */                
	protected void _paiementAnnee(Couverture<Integer> c) {
		if(paiementDate != null)
			c.o(paiementDate.getYear());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentAmount
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 * HtmlColonne: 4
	 * Facet: sum
	 * NomAffichage.frFR: paiement montant
	 * NomAffichage.enUS: payment amount
	 */              
	protected void _paiementMontant(Couverture<BigDecimal> c) {
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
	 * Var.enUS: paymentShortName
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * HtmlColonne: 2
	 * r: enfantNomCompletPrefere
	 * r.enUS: childCompleteNamePreferred
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
	 * r: paiementECheck
	 * r.enUS: paymentECheck
	 * r: paiementDescription
	 * r.enUS: paymentDescription
	 * r: paiementValeur
	 * r.enUS: paymentValue
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: fraisRetard
	 * r.enUS: chargeLateFee
	 * r: " par chèque"
	 * r.enUS: " by check"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: fraisPremierDernier
	 * r.enUS: chargeFirstLast
	 * r: fraisInscription
	 * r.enUS: chargeEnrollment
	 * r: SessionDateDebut
	 * r.enUS: SessionStartDate
	 * r: SessionDateFin
	 * r.enUS: SessionEndDate
	 * r: FRANCE
	 * r.enUS: US
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "Frais de %s"
	 * r.enUS: "%s tuition"
	 * r: "Frais de %s + %s"
	 * r.enUS: "%s + %s tuition"
	 * r: "Frais d'inscription %s-%s"
	 * r.enUS: "%s-%s enrollment fee"
	 * r: "%s frais de retard"
	 * r.enUS: "%s late fee"
	 * r: " paiement"
	 * r.enUS: " payment"
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "nouveau"
	 * r.enUS: "new"
	 * r: " et "
	 * r.enUS: " and "
	 */  
	protected void _paiementNomCourt(Couverture<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.FRANCE);
		fn.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(paiementDate != null)
			o.append(paiementDate.format(fd2));
		if(paiementMontant != null) {
			o.append(" ").append(fn.format(paiementMontant));
			o.append(" paiement");
		}
		if(!StringUtils.isEmpty(paiementDescription))
			o.append(" ").append(paiementDescription);
		if(o.length() == 0)
			o.append("nouveau");
		c.o(o.toString());
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: paymentCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: enfantNomCompletPrefere
	 * r.enUS: childCompleteNamePreferred
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
	 * r: paiementECheck
	 * r.enUS: paymentECheck
	 * r: paiementDescription
	 * r.enUS: paymentDescription
	 * r: paiementValeur
	 * r.enUS: paymentValue
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: fraisRetard
	 * r.enUS: chargeLateFee
	 * r: " par chèque"
	 * r.enUS: " by check"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: fraisPremierDernier
	 * r.enUS: chargeFirstLast
	 * r: fraisInscription
	 * r.enUS: chargeEnrollment
	 * r: SessionDateDebut
	 * r.enUS: SessionStartDate
	 * r: SessionDateFin
	 * r.enUS: SessionEndDate
	 * r: FRANCE
	 * r.enUS: US
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "%s frais de %s"
	 * r.enUS: "%s %s tuition"
	 * r: "%s frais de %s + %s"
	 * r.enUS: "%s %s + %s tuition"
	 * r: "%s frais d'inscription %s-%s"
	 * r.enUS: "%s %s-%s enrollment fee"
	 * r: "%s frais de retard"
	 * r.enUS: "%s late fee"
	 * r: " paiement"
	 * r.enUS: " payment"
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "nouveau"
	 * r.enUS: "new"
	 * r: " et "
	 * r.enUS: " and "
	 */                              
	protected void _paiementNomComplet(Couverture<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.FRANCE);
		fn.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(paiementDate != null)
			o.append(paiementDate.format(fd2));
		if(paiementMontant != null) {
			o.append(" ").append(fn.format(paiementMontant));
			o.append(" paiement");
		}
		if(!StringUtils.isEmpty(paiementDescription))
			o.append(" ").append(paiementDescription);
		if(o.length() == 0)
			o.append("nouveau");
		c.o(o.toString());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectText
	 * r: paiementNomComplet
	 * r.enUS: paymentCompleteName
	 * r: enfantNomCompletPrefere
	 * r.enUS: childCompleteNamePreferred
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
	 * r: paiementECheck
	 * r.enUS: paymentECheck
	 * r: paiementDescription
	 * r.enUS: paymentDescription
	 * r: paiementValeur
	 * r.enUS: paymentValue
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: fraisRetard
	 * r.enUS: chargeLateFee
	 * r: " par chèque"
	 * r.enUS: " by check"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: " par espèces"
	 * r.enUS: " by cash"
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: fraisPremierDernier
	 * r.enUS: chargeFirstLast
	 * r: fraisInscription
	 * r.enUS: chargeEnrollment
	 * r: SessionDateDebut
	 * r.enUS: SessionStartDate
	 * r: SessionDateFin
	 * r.enUS: SessionEndDate
	 * r: FRANCE
	 * r.enUS: US
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "Frais de %s"
	 * r.enUS: "%s tuition"
	 * r: "Frais de %s + %s"
	 * r.enUS: "%s + %s tuition"
	 * r: "Frais d'inscription %s-%s"
	 * r.enUS: "%s-%s enrollment fee"
	 * r: "%s frais de retard"
	 * r.enUS: "%s late fee"
	 * r: " paiement"
	 * r.enUS: " payment"
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "nouveau"
	 * r.enUS: "new"
	 * r: " et "
	 * r.enUS: " and "
	 */            
	@Override
	protected void _objetTexte(Couverture<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.FRANCE);
		fn.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(paiementDate != null)
			o.append(paiementDate.format(fd2));
		if(paiementMontant != null) {
			o.append(" ").append(fn.format(paiementMontant));
			o.append(" paiement");
		}
		if(!StringUtils.isEmpty(paiementDescription))
			o.append(" ").append(paiementDescription);
		if(o.length() == 0)
			o.append("nouveau");
		c.o(o.toString());
	} 
}

