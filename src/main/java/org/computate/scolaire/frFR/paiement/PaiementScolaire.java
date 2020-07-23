package org.computate.scolaire.frFR.paiement;                     

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.config.ConfigSite;
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
 * RoleLire.frFR: User
 * RoleLire.enUS: User
 * 
 * Tri.desc: paiementDate
 * Tri.desc: paiementPar
 * 
 * Lignes: 50
*/    
public class PaiementScolaire extends PaiementScolaireGen<Cluster> {

	private LocalDate now;

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
	 * Var.enUS: enrollmentKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: InscriptionScolaire.paiementCles
	 * HtmlLigne: 7
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des enfants dans la base de données. 
	 * Description.enUS: The primary key of the school children in the database. 
	 * NomAffichage.frFR: inscription
	 * NomAffichage.enUS: enrollment
	 */   
	protected void _inscriptionCle(Couverture<Long> c) {}

	/**
	 * Var.enUS: enrollmentSearch
	 * r: paiementCles
	 * r.enUS: paymentKeys
	 * r: InscriptionScolaire
	 * r.enUS: SchoolEnrollment
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 * r: utilisateurCles
	 * r.enUS: userKeys
	 */   
	protected void _inscriptionRecherche(ListeRecherche<InscriptionScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("paiementCles_indexed_longs:" + pk);
		l.setC(InscriptionScolaire.class);
		l.setStocker(true);
		l.addFacetField("utilisateurCles_indexed_longs");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollment_
	 * r: inscriptionRecherche
	 * r.enUS: enrollmentSearch
	 * Ignorer: true
	 */   
	protected void _inscription_(Couverture<InscriptionScolaire> c) {
		if(inscriptionRecherche.size() == 1) {
			c.o(inscriptionRecherche.get(0));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolNumber
	 * Indexe: true
	 * Stocke: true
	 * r: inscription
	 * r.enUS: enrollment
	 * r: EcoleNumero
	 * r.enUS: SchoolNumber
	 */
	protected void _ecoleNumero(Couverture<Integer> c) {
		if(inscription_ != null)
			c.o(inscription_.getEcoleNumero());
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
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: écoles
	 * NomAffichage.enUS: schools
	 * r: inscription
	 * r.enUS: enrollment
	 * r: EcoleCle
	 * r.enUS: SchoolKey
	 */
	protected void _ecoleCle(Couverture<Long> c) {
		if(inscription_ != null)
			c.o(inscription_.getEcoleCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de l'paiement scolaire. 
	 * Description.enUS: The school year of the school child. 
	 * NomAffichage.frFR: années
	 * NomAffichage.enUS: years
	 * r: inscription
	 * r.enUS: enrollment
	 * r: AnneeCle
	 * r.enUS: YearKey
	 */         
	protected void _anneeCle(Couverture<Long> c) {
		if(inscription_ != null)
			c.o(inscription_.getAnneeCle());
	}
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: seasonKey
//	 * Indexe: true
//	 * Stocke: true
//	 * Description.frFR: La saison scolaire de l'paiement scolaire. 
//	 * Description.enUS: The school season of the school child. 
//	 * NomAffichage.frFR: saisons
//	 * NomAffichage.enUS: seasons
//	 * r: inscription
//	 * r.enUS: enrollment
//	 * r: SaisonCle
//	 * r.enUS: SeasonKey
//	 */          
//	protected void _saisonCle(Couverture<Long> c) {
//		if(inscription_ != null)
//			c.o(inscription_.getSaisonCle());
//	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the school child in the database. 
	 * NomAffichage.frFR: sessions
	 * NomAffichage.enUS: sessions
	 * r: inscription
	 * r.enUS: enrollment
	 * r: SessionCle
	 * r.enUS: SessionKey
	 */          
	protected void _sessionCle(Couverture<Long> c) {
		if(inscription_ != null)
			c.o(inscription_.getSessionCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'âge dans la base de données. 
	 * Description.enUS: The primary key of the age in the database. 
	 * NomAffichage.frFR: âges
	 * NomAffichage.enUS: ages
	 * r: inscription
	 * r.enUS: enrollment
	 * r: AgeCle
	 * r.enUS: AgeKey
	 */                  
	protected void _ageCle(Couverture<Long> c) {
		if(inscription_ != null)
			c.o(inscription_.getAgeCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the school child in the database. 
	 * NomAffichage.frFR: sessions
	 * NomAffichage.enUS: sessions
	 * r: inscription
	 * r.enUS: enrollment
	 * r: BlocCle
	 * r.enUS: BlockKey
	 */          
	protected void _blocCle(Couverture<Long> c) {
		if(inscription_ != null)
			c.o(inscription_.getBlocCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des enfants dans la base de données. 
	 * Description.enUS: The primary key of the school childs in the database. 
	 * NomAffichage.frFR: enfants
	 * NomAffichage.enUS: childs
	 * r: inscription
	 * r.enUS: enrollment
	 * r: EnfantCle
	 * r.enUS: ChildKey
	 */               
	protected void _enfantCle(Couverture<Long> c) {
		if(inscription_ != null)
			c.o(inscription_.getEnfantCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des mères dans la base de données. 
	 * Description.enUS: The primary key of the school moms in the database. 
	 * NomAffichage.frFR: mères
	 * NomAffichage.enUS: moms
	 * r: inscription
	 * r.enUS: enrollment
	 * r: MereCles
	 * r.enUS: MomKeys
	 */               
	protected void _mereCles(List<Long> l) {
		if(inscription_ != null)
			l.addAll(inscription_.getMereCles());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des pères dans la base de données. 
	 * Description.enUS: The primary key of the school dads in the database. 
	 * NomAffichage.frFR: pères
	 * NomAffichage.enUS: dads
	 * r: inscription
	 * r.enUS: enrollment
	 * r: PereCles
	 * r.enUS: DadKeys
	 */               
	protected void _pereCles(List<Long> l) {
		if(inscription_ != null)
			l.addAll(inscription_.getPereCles());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: guardianKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire des gardiens dans la base de données. 
	 * Description.enUS: The primary key of the school gardians in the database. 
	 * NomAffichage.frFR: gardiens
	 * NomAffichage.enUS: guardians
	 * r: inscription
	 * r.enUS: enrollment
	 * r: GardienCles
	 * r.enUS: GuardianKeys
	 */               
	protected void _gardienCles(List<Long> l) {
		if(inscription_ != null)
			l.addAll(inscription_.getGardienCles());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * Facet: terms
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: EnfantNomCompletPrefere
	 * r.enUS: ChildCompleteNamePreferred
	 * 
	 */         
	protected void _enfantNomCompletPrefere(Couverture<String> c) {
		if(inscription_ != null)
			c.o(inscription_.getEnfantNomCompletPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childBirthDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: EnfantDateNaissance
	 * r.enUS: ChildBirthDate
	 */    
	protected void _enfantDateNaissance(Couverture<LocalDate> c) {
		if(inscription_ != null)
			c.o(inscription_.getEnfantDateNaissance());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: MereNomCompletPrefere
	 * r.enUS: MomCompleteNamePreferred
	 */  
	protected void _mereNomCompletPrefere(Couverture<String> c) {
		if(inscription_ != null)
			c.o(inscription_.getMereNomCompletPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: inscription_
	 * r.enUS: enrollment_
	 * r: PereNomCompletPrefere
	 * r.enUS: DadCompleteNamePreferred
	 */  
	protected void _pereNomCompletPrefere(Couverture<String> c) {
		if(inscription_ != null)
			c.o(inscription_.getPereNomCompletPrefere());
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
	 * r: inscription
	 * r.enUS: enrollment
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(inscription_ != null)
			c.o(inscription_.getEcoleNom());
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
	 * r: inscription
	 * r.enUS: enrollment
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(inscription_ != null)
			c.o(inscription_.getEcoleNomComplet());
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
	 * r: inscription
	 * r.enUS: enrollment
	 */           
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(inscription_ != null)
			c.o(inscription_.getEcoleEmplacement());
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
	 * r: inscription
	 * r.enUS: enrollment
	 */               
	protected void _anneeDebut(Couverture<Integer> c) {
		if(inscription_ != null)
			c.o(inscription_.getAnneeDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnd
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: AnneeFin
	 * r.enUS: YearEnd
	 * r: inscription
	 * r.enUS: enrollment
	 */                     
	protected void _anneeFin(Couverture<Integer> c) {
		if(inscription_ != null)
			c.o(inscription_.getAnneeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonStartDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of season
	 * r: SaisonDateDebut
	 * r.enUS: SeasonStartDate
	 * r: inscription
	 * r.enUS: enrollment
	 */                  
	protected void _saisonDateDebut(Couverture<LocalDate> c) {
		if(inscription_ != null)
			c.o(inscription_.getSaisonDateDebut());
	}
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: seasonSummer
//	 * Indexe: true
//	 * Stocke: true
//	 * NomAffichage.frFR: été
//	 * NomAffichage.enUS: summer
//	 * r: SaisonEte
//	 * r.enUS: SeasonSummer
//	 * r: inscription
//	 * r.enUS: enrollment
//	 */                   
//	protected void _saisonEte(Couverture<Boolean> c) {
//		if(inscription_ != null)
//			c.o(inscription_.getSaisonEte());
//	}
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: seasonWinter
//	 * Indexe: true
//	 * Stocke: true
//	 * NomAffichage.frFR: hiver
//	 * NomAffichage.enUS: winter
//	 * r: SaisonHiver
//	 * r.enUS: SeasonWinter
//	 * r: inscription
//	 * r.enUS: enrollment
//	 */                   
//	protected void _saisonHiver(Couverture<Boolean> c) {
//		if(inscription_ != null)
//			c.o(inscription_.getSaisonHiver());
//	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnrollmentFee
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: AnneeFraisInscription
	 * r.enUS: YearEnrollmentFee
	 * r: inscription
	 * r.enUS: enrollment
	 */                
	protected void _anneeFraisInscription(Couverture<BigDecimal> c) {
		if(inscription_ != null)
			c.o(inscription_.getAnneeFraisInscription());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionStartDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de la session
	 * NomAffichage.enUS: start of the session
	 * r: SessionDateDebut
	 * r.enUS: SessionStartDate
	 * r: inscription
	 * r.enUS: enrollment
	 */                   
	protected void _sessionDateDebut(Couverture<LocalDate> c) {
		if(inscription_ != null)
			c.o((LocalDate)inscription_.getSessionDateDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionEndDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: fin de la session
	 * NomAffichage.enUS: end of the session
	 * r: SessionDateFin
	 * r.enUS: SessionEndDate
	 * r: inscription
	 * r.enUS: enrollment
	 */                   
	protected void _sessionDateFin(Couverture<LocalDate> c) {
		if(inscription_ != null)
			c.o((LocalDate)inscription_.getSessionDateFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageStart
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début du groupe d'âge
	 * NomAffichage.enUS: start of the age group
	 * r: AgeDebut
	 * r.enUS: AgeStart
	 * r: inscription
	 * r.enUS: enrollment
	 */                   
	protected void _ageDebut(Couverture<Integer> c) {
		if(inscription_ != null)
			c.o(inscription_.getAgeDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageEnd
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: fin du groupe d'âge
	 * NomAffichage.enUS: end of the age group
	 * r: AgeFin
	 * r.enUS: AgeEnd
	 * r: inscription
	 * r.enUS: enrollment
	 */                   
	protected void _ageFin(Couverture<Integer> c) {
		if(inscription_ != null)
			c.o(inscription_.getAgeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockStartTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure début
	 * NomAffichage.enUS: start time
	 * r: inscription
	 * r.enUS: enrollment
	 * r: BlocHeureDebut
	 * r.enUS: BlockStartTime
	 */                   
	protected void _blocHeureDebut(Couverture<LocalTime> c) {
		if(inscription_ != null)
			c.o(inscription_.getBlocHeureDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockEndTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure fin
	 * NomAffichage.enUS: end time
	 * r: inscription
	 * r.enUS: enrollment
	 * r: BlocHeureFin
	 * r.enUS: BlockEndTime
	 */                   
	protected void _blocHeureFin(Couverture<LocalTime> c) {
		if(inscription_ != null)
			c.o(inscription_.getBlocHeureFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockPricePerMonth
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prix par mois
	 * NomAffichage.enUS: price per month
	 * r: inscription
	 * r.enUS: enrollment
	 * r: BlocPrixParMois
	 * r.enUS: BlockPricePerMonth
	 */                   
	protected void _blocPrixParMois(Couverture<BigDecimal> c) {
		if(inscription_ != null)
			c.o(inscription_.getBlocPrixParMois());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockTotalPrice
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prix total
	 * NomAffichage.enUS: total price
	 * r: inscription
	 * r.enUS: enrollment
	 * r: BlocPrixTotal
	 * r.enUS: BlockTotalPrice
	 */                   
	protected void _blocPrixTotal(Couverture<BigDecimal> c) {
		if(inscription_ != null)
			c.o(inscription_.getBlocPrixTotal());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentPaymentEachMonth
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: paiement chaque mois
	 * NomAffichage.enUS: payment each month
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 4
	 */                   
	protected void _inscriptionPaimentChaqueMois(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentPaymentComplete
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: paiement complet
	 * NomAffichage.enUS: complete payment
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 3
	 */                       
	protected void _inscriptionPaimentComplet(Couverture<Boolean> c) {
		c.o(false);
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
	 * HtmlColonne: 3
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
	 * HtmlColonne: 4
	 * Facet: sum
	 * NomAffichage.frFR: paiement montant
	 * NomAffichage.enUS: payment history
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
	 * Var.enUS: paymentECheck
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 5
	 * NomAffichage.frFR: e-check
	 * NomAffichage.enUS: e-check
	 */                     
	protected void _paiementECheck(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentSystem
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 6
	 * NomAffichage.frFR: authorize.net
	 * NomAffichage.enUS: authorize.net
	 */                     
	protected void _paiementSysteme(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentBy
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 * NomAffichage.frFR: paiement par/pour
	 * NomAffichage.enUS: payment by/for
	 */  
	protected void _paiementPar(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: transactionId
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 * NomAffichage.frFR: transaction ID
	 * NomAffichage.enUS: transaction ID
	 */                     
	protected void _transactionId(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: customerProfileId
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 * NomAffichage.frFR: customer profile ID
	 * NomAffichage.enUS: customer profile ID
	 */                
	protected void _customerProfileId(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: transactionStatus
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 3
	 * NomAffichage.frFR: état de transaction
	 * NomAffichage.enUS: transaction status
	 */                  
	protected void _transactionStatus(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentRecieved
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 5
	 * NomAffichage.frFR: paiement récu
	 * NomAffichage.enUS: payment received
	 */                  
	protected void _paiementRecu(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: chargeAmount
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Facet: sum
	 * HtmlLigne: 6
	 * HtmlCellule: 1
	 * HtmlColonne: 5
	 * NomAffichage.frFR: frais montant
	 * NomAffichage.enUS: charge amount
	 */                   
	protected void _fraisMontant(Couverture<BigDecimal> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: chargeFirstLast
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 2
	 * NomAffichage.frFR: frais mois premier et dernier
	 * NomAffichage.enUS: first and last month charge
	 */                    
	protected void _fraisPremierDernier(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: chargeEnrollment
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 3
	 * NomAffichage.frFR: frais d'inscription
	 * NomAffichage.enUS: enrollment fee
	 */                 
	protected void _fraisInscription(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: chargeMonth
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 4
	 * NomAffichage.frFR: frais du mois
	 * NomAffichage.enUS: monthly fee
	 */                    
	protected void _fraisMois(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: chargeLateFee
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 5
	 * NomAffichage.frFR: frais de retard
	 * NomAffichage.enUS: late fee
	 */                    
	protected void _fraisRetard(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentNext
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: date de paiement prochaîne
	 * NomAffichage.enUS: next payment date
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: paiementDate
	 * r.enUS: paymentDate
	 * r: PaiementJour
	 * r.enUS: PaymentDay
	 * r: paiementJour
	 * r.enUS: paymentDay
	 * r: paiementProchain
	 * r.enUS: paymentNext
	 * r: PaiementProchain
	 * r.enUS: PaymentNext
	 * r: fraisPremierDernier
	 * r.enUS: chargeFirstLast
	 * r: fraisInscription
	 * r.enUS: chargeEnrollment
	 * r: PaiementJour
	 * r.enUS: PaymentDay
	 * r: paiementJour
	 * r.enUS: paymentDay
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 */                   
	protected void _paiementProchain(Couverture<LocalDate> c) {
		ConfigSite configSite = requeteSite_.getConfigSite_();
		LocalDate now = LocalDate.now();
		Integer paiementJour = configSite.getPaiementJour();
		c.o(LocalDate.now().getDayOfMonth() < paiementJour ? now.withDayOfMonth(paiementJour) : now.plusMonths(1).withDayOfMonth(paiementJour));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: chargeAmountDue
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Facet: sum
	 * Modifier: false
	 * NomAffichage.frFR: frais montant dû
	 * NomAffichage.enUS: charge amount due
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: paiementDate
	 * r.enUS: paymentDate
	 * r: PaiementJour
	 * r.enUS: PaymentDay
	 * r: paiementJour
	 * r.enUS: paymentDay
	 * r: paiementProchain
	 * r.enUS: paymentNext
	 * r: PaiementProchain
	 * r.enUS: PaymentNext
	 * r: fraisPremierDernier
	 * r.enUS: chargeFirstLast
	 * r: fraisInscription
	 * r.enUS: chargeEnrollment
	 * r: PaiementJour
	 * r.enUS: PaymentDay
	 * r: paiementJour
	 * r.enUS: paymentDay
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 */                   
	protected void _fraisMontantDu(Couverture<BigDecimal> c) {
		if(fraisMontant != null && (fraisInscription || paiementDate != null && paiementDate.compareTo(paiementProchain.minusMonths(1)) > 0 && paiementDate.compareTo(paiementProchain) <= 0))
			c.o(fraisMontant);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: chargeAmountFuture
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Facet: sum
	 * Modifier: false
	 * NomAffichage.frFR: frais montant future
	 * NomAffichage.enUS: future charge amount
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: paiementDate
	 * r.enUS: paymentDate
	 * r: paiementProchain
	 * r.enUS: paymentNext
	 * r: PaiementProchain
	 * r.enUS: PaymentNext
	 * r: fraisPremierDernier
	 * r.enUS: chargeFirstLast
	 * r: fraisInscription
	 * r.enUS: chargeEnrollment
	 */                   
	protected void _fraisMontantFuture(Couverture<BigDecimal> c) {
		if(fraisMontant != null && paiementDate != null && !fraisInscription && paiementDate.compareTo(paiementProchain) > 0)
			c.o(fraisMontant);
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
	 * r: "Paiement"
	 * r.enUS: "Payment"
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "nouveau"
	 * r.enUS: "new"
	 */  
	protected void _paiementNomCourt(Couverture<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMMM", Locale.FRANCE);
		fn.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(fraisMontant != null) {
			if(inscription_ != null && fraisPremierDernier && inscription_.getSessionDateDebut() != null && inscription_.getSessionDateFin() != null)
				o.append(String.format("Frais de %s + %s", fd.format(inscription_.getSessionDateDebut().plusWeeks(1)), fd.format(inscription_.getSessionDateFin())));
			else if(inscription_ != null && fraisInscription && inscription_.getSessionDateDebut() != null && inscription_.getSessionDateFin() != null)
				o.append(String.format("Frais d'inscription %s-%s", inscription_.getSessionDateDebut().getYear(), inscription_.getSessionDateFin().getYear()));
			else if(inscription_ != null && fraisRetard && fraisMontant != null)
				o.append(String.format("%s frais de retard", fn.format(fraisMontant)));
			else if(paiementDate != null)
				o.append(String.format("Frais de %s", fd.format(paiementDate.plusMonths(1))));
		}
		if(paiementMontant != null) {
			o.append("Paiement");
			if(BooleanUtils.isTrue(paiementCheque))
				o.append(" by check");
			if(BooleanUtils.isTrue(paiementEspeces))
				o.append(" by cash");
			if(BooleanUtils.isTrue(paiementSysteme))
				o.append(" by authorize.net");
			if(BooleanUtils.isTrue(paiementECheck))
				o.append(" by e-check");
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
	 */                              
	protected void _paiementNomComplet(Couverture<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.FRANCE);
		fn.setMaximumFractionDigits(0);
		StringBuilder o = new StringBuilder();

		if(fraisMontant != null) {
			if(inscription_ != null && fraisPremierDernier && inscription_.getSessionDateDebut() != null && inscription_.getSessionDateFin() != null)
				o.append(String.format("%s frais de %s + %s", fn.format(fraisMontant), fd.format(inscription_.getSessionDateDebut()), fd.format(inscription_.getSessionDateFin())));
			else if(inscription_ != null && fraisInscription && inscription_.getSessionDateDebut() != null && inscription_.getSessionDateFin() != null)
				o.append(String.format("%s frais d'inscription %s-%s", fn.format(fraisMontant), fd.format(inscription_.getSessionDateDebut()), fd.format(inscription_.getSessionDateFin())));
			else if(inscription_ != null && fraisRetard && fraisMontant != null)
				o.append(String.format("%s frais de retard", fn.format(fraisMontant)));
			else if(fraisMontant != null && paiementDate != null)
				o.append(String.format("%s frais de %s", fn.format(fraisMontant), fd.format(paiementDate.plusMonths(1))));

			if(enfantNomCompletPrefere != null)
				o.append(String.format(" pour %s", enfantNomCompletPrefere));
		}
		if(paiementMontant != null) {
			o.append(fn.format(paiementMontant));
			if(paiementDate != null)
				o.append(" ").append(strPaiementDate());
			o.append(" paiement");
			if(enfantNomCompletPrefere != null)
				o.append(String.format(" pour %s", enfantNomCompletPrefere));
			if(BooleanUtils.isTrue(paiementCheque))
				o.append(" by check");
			if(BooleanUtils.isTrue(paiementEspeces))
				o.append(" by cash");
			if(BooleanUtils.isTrue(paiementSysteme))
				o.append(" by authorize.net");
			if(BooleanUtils.isTrue(paiementECheck))
				o.append(" by e-check");
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
	 * r: "Paiement"
	 * r.enUS: "Payment"
	 * r: " pour %s"
	 * r.enUS: " for %s"
	 * r: "nouveau"
	 * r.enUS: "new"
	 */            
	@Override
	protected void _objetTexte(Couverture<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.FRANCE);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("EEEE EEE MMMM MMM d yyyy", Locale.FRANCE);
		fn.setMaximumFractionDigits(0);
		StringBuilder o = new StringBuilder();

		if(fraisMontant != null) {
			if(paiementDate != null)
				o.append(" ").append(paiementDate.format(fd2));
			if(inscription_ != null && fraisPremierDernier && inscription_.getSessionDateDebut() != null && inscription_.getSessionDateFin() != null)
				o.append(String.format("%s frais de %s + %s", fn.format(fraisMontant), fd.format(inscription_.getSessionDateDebut()), fd.format(inscription_.getSessionDateFin())));
			else if(inscription_ != null && fraisInscription && inscription_.getSessionDateDebut() != null && inscription_.getSessionDateFin() != null)
				o.append(String.format("%s frais d'inscription %s-%s", fn.format(fraisMontant), fd.format(inscription_.getSessionDateDebut()), fd.format(inscription_.getSessionDateFin())));
			else if(inscription_ != null && fraisRetard && fraisMontant != null)
				o.append(String.format("%s frais de retard", fn.format(fraisMontant)));
			else if(fraisMontant != null && paiementDate != null)
				o.append(String.format("%s frais de %s", fn.format(fraisMontant), fd.format(paiementDate.plusMonths(1))));

			if(enfantNomCompletPrefere != null)
				o.append(String.format(" pour %s", enfantNomCompletPrefere));
		}
		if(paiementMontant != null) {
			o.append(fn.format(paiementMontant));
			if(paiementDate != null)
				o.append(" ").append(paiementDate.format(fd2));
			o.append(" paiement");
			if(enfantNomCompletPrefere != null)
				o.append(String.format(" pour %s", enfantNomCompletPrefere));
			if(BooleanUtils.isTrue(paiementCheque))
				o.append(" by check");
			if(BooleanUtils.isTrue(paiementEspeces))
				o.append(" by cash");
			if(BooleanUtils.isTrue(paiementSysteme))
				o.append(" by authorize.net");
			if(BooleanUtils.isTrue(paiementECheck))
				o.append(" by e-check");
		}
		if(!StringUtils.isEmpty(paiementDescription))
			o.append(" ").append(paiementDescription);
		if(o.length() == 0)
			o.append("nouveau");
		c.o(o.toString());
	} 
}
