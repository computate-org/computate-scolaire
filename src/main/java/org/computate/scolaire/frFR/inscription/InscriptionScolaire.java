package org.computate.scolaire.frFR.inscription; 

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.age.AgeScolaire;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.enfant.EnfantScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.SchoolEnrollment
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Inscription
 * ApiUri.frFR: /api/inscription
 * 
 * ApiTag.enUS: Enrollment
 * ApiUri.enUS: /api/enrollment
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: InscriptionPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /inscription
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: EnrollmentPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /enrollment
 * 
 * UnNom.frFR: une inscription
 * UnNom.enUS: an enrollment
 * Couleur: purple
 * IconeGroupe: solid
 * IconeNom: edit
*/ 
public class InscriptionScolaire extends InscriptionScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'inscription dans la base de données. 
	 * Description.enUS: The primary key of the school enrollment in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */                  
	protected void _inscriptionCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: BlocScolaire.inscriptionCles
	 * HtmlLigne: 7
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire des blocs dans la base de données. 
	 * Description.enUS: The primary key of the school blocks in the database. 
	 * NomAffichage.frFR: blocs
	 * NomAffichage.enUS: blocks
	 */   
	protected void _blocCles(List<Long> o) {}

	/**
	 * Var.enUS: blockSearch
	 * r: inscriptionCles
	 * r.enUS: enrollmentKeys
	 * r: BlocScolaire
	 * r.enUS: SchoolBlock
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 * r: blocCles
	 * r.enUS: blockKeys
	 */
	protected void _blocRecherche(ListeRecherche<BlocScolaire> l) {
		if(blocCles.size() > 0) {
			l.setQuery("*:*");
			l.addFilterQuery("pk_indexed_long:(" + StringUtils.join(blocCles, " ") + ")");
			l.setC(BlocScolaire.class);
			l.setStocker(true);
		}
		else {
			l.setQuery(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: block
	 * r: blocRecherche
	 * r.enUS: blockSearch
	 * Ignorer: true
	 */   
	protected void _bloc(Couverture<BlocScolaire> c) {
		if(blocRecherche.size() > 0) {
			c.o(blocRecherche.get(0));
		}
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
	 */                  
	protected void _ecoleCle(Couverture<Long> c) {
	}

	/*
	 * {@inheritDoc}
	 * Var.enUS: yearKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de l'inscription scolaire. 
	 * Description.enUS: The school year of the school block. 
	 * NomAffichage.frFR: année
	 * NomAffichage.enUS: year
	 */          
	protected void _anneeCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La saison scolaire de l'inscription scolaire. 
	 * Description.enUS: The school season of the school block. 
	 * NomAffichage.frFR: saison
	 * NomAffichage.enUS: season
	 */           
	protected void _saisonCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the school block in the database. 
	 * NomAffichage.frFR: session
	 * NomAffichage.enUS: session
	 */          
	protected void _sessionCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'âge dans la base de données. 
	 * Description.enUS: The primary key of the age in the database. 
	 * NomAffichage.frFR: âge
	 * NomAffichage.enUS: age
	 */                  
	protected void _ageCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire du bloc dans la base de données. 
	 * Description.enUS: The primary key of the school block in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */              
	protected void _blocCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: EnfantScolaire.inscriptionCles
	 * HtmlLigne: 7
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des enfants dans la base de données. 
	 * Description.enUS: The primary key of the school children in the database. 
	 * NomAffichage.frFR: enfants
	 * NomAffichage.enUS: children
	 */               
	protected void _enfantCle(Couverture<Long> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: MereScolaire.inscriptionCles
	 * HtmlLigne: 8
	 * HtmlCellule: 1
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
	 * Attribuer: PereScolaire.inscriptionCles
	 * HtmlLigne: 8
	 * HtmlCellule: 2
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
	 * Attribuer: GardienScolaire.inscriptionCles
	 * HtmlLigne: 9
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire des gardiens dans la base de données. 
	 * Description.enUS: The primary key of the school gardians in the database. 
	 * NomAffichage.frFR: gardiens
	 * NomAffichage.enUS: guardians
	 */               
	protected void _gardienCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: PaiementScolaire.inscriptionCles
	 * HtmlLigne: 9
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des contacts d'urgence dans la base de données. 
	 * Description.enUS: The primary key of the school emergency contacts in the database. 
	 * NomAffichage.frFR: paiements
	 * NomAffichage.enUS: payments
	 */               
	protected void _paiementCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: educationSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _scolaireTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _anneeTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _saisonTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _sessionTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ageTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * Var.enUS: childSearch
	 * r: inscriptionCles
	 * r.enUS: enrollmentKeys
	 * r: EnfantScolaire
	 * r.enUS: SchoolChild
	 * r: setStocker
	 * r.enUS: setStore
	 * r: enfantCle
	 * r.enUS: childKey
	 * Ignorer: true
	 */
	protected void _enfantRecherche(ListeRecherche<EnfantScolaire> l) {
		if(enfantCle == null) {
			l.setQuery(null);
		}
		else {
			l.setQuery("*:*");
			l.addFilterQuery("inscriptionCles_indexed_longs:" + pk);
			l.addFilterQuery("pk_indexed_long:" + enfantCle);
			l.setC(EnfantScolaire.class);
			l.setStocker(true);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: child_
	 * r: enfantRecherche
	 * r.enUS: childSearch
	 * Ignorer: true
	 */   
	protected void _enfant_(Couverture<EnfantScolaire> c) {
		if(enfantRecherche.size() > 0) {
			c.o(enfantRecherche.get(0));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childCompleteName
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: EnfantNomComplet
	 * r.enUS: ChildCompleteName
	 * r: enfant_
	 * r.enUS: child_
	 * r: PersonneNomComplet
	 * r.enUS: PersonCompleteName
	 */   
	protected void _enfantNomComplet(Couverture<String> c) {
		if(enfant_ != null)
			c.o((String)enfant_.getPersonneNomComplet());
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
	 * r: bloc
	 * r.enUS: block
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(bloc != null)
			c.o((String)bloc.getEcoleNomComplet());
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
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _anneeDebut(Couverture<Integer> c) {
		if(bloc != null)
			c.o(bloc.getAnneeDebut());
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
	 * r: bloc
	 * r.enUS: block
	 */                     
	protected void _anneeFin(Couverture<Integer> c) {
		if(bloc != null)
			c.o(bloc.getAnneeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonStartDay
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of season
	 * r: SaisonJourDebut
	 * r.enUS: SeasonStartDay
	 * r: bloc
	 * r.enUS: block
	 */                  
	protected void _saisonJourDebut(Couverture<LocalDate> c) {
		if(bloc != null)
			c.o(bloc.getSaisonJourDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonSummer
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: été
	 * NomAffichage.enUS: summer
	 * r: SaisonEte
	 * r.enUS: SeasonSummer
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _saisonEte(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getSaisonEte());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonWinter
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: hiver
	 * NomAffichage.enUS: winter
	 * r: SaisonHiver
	 * r.enUS: SeasonWinter
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _saisonHiver(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getSaisonHiver());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonEnrollmentFee
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: frais d'inscription
	 * NomAffichage.enUS: enrollment fee
	 * r: SaisonFraisInscription
	 * r.enUS: SeasonEnrollmentFee
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _saisonFraisInscription(Couverture<BigDecimal> c) {
		if(bloc != null)
			c.o(bloc.getSaisonFraisInscription());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomComplet
	 * r.enUS: SeasonCompleteName
	 * r: bloc
	 * r.enUS: block
	 */
	protected void _saisonNomComplet(Couverture<String> c) {
		if(bloc != null)
			c.o(bloc.getSaisonNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionStartDay
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de la session
	 * NomAffichage.enUS: start of the session
	 * r: SessionJourDebut
	 * r.enUS: SessionStartDay
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _sessionJourDebut(Couverture<LocalDate> c) {
		if(bloc != null)
			c.o((LocalDate)bloc.getSessionJourDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionEndDay
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: fin de la session
	 * NomAffichage.enUS: end of the session
	 * r: SessionJourFin
	 * r.enUS: SessionEndDay
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _sessionJourFin(Couverture<LocalDate> c) {
		if(bloc != null)
			c.o((LocalDate)bloc.getSessionJourFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: AgeNomComplet
	 * r.enUS: AgeCompleteName
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _ageNomComplet(Couverture<String> c) {
		if(bloc != null)
			c.o(bloc.getAgeNomComplet());
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
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _ageDebut(Couverture<Integer> c) {
		if(bloc != null)
			c.o(bloc.getAgeDebut());
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
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _ageFin(Couverture<Integer> c) {
		if(bloc != null)
			c.o(bloc.getAgeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockStartTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure début
	 * NomAffichage.enUS: start time
	 * r: bloc
	 * r.enUS: block
	 * r: BlocHeureDebut
	 * r.enUS: BlockStartTime
	 */                   
	protected void _blocHeureDebut(Couverture<LocalTime> c) {
		if(bloc != null)
			c.o(bloc.getBlocHeureDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockEndTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure fin
	 * NomAffichage.enUS: end time
	 * r: bloc
	 * r.enUS: block
	 * r: BlocHeureFin
	 * r.enUS: BlockEndTime
	 */                   
	protected void _blocHeureFin(Couverture<LocalTime> c) {
		if(bloc != null)
			c.o(bloc.getBlocHeureFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockPricePerMonth
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prix par mois
	 * NomAffichage.enUS: price per month
	 * r: bloc
	 * r.enUS: block
	 * r: BlocPrixParMois
	 * r.enUS: BlockPricePerMonth
	 */                   
	protected void _blocPrixParMois(Couverture<BigDecimal> c) {
		if(bloc != null)
			c.o(bloc.getBlocPrixParMois());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockSunday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: dimanche
	 * NomAffichage.enUS: sunday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocDimanche
	 * r.enUS: BlockSunday
	 */                   
	protected void _blocDimanche(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getBlocDimanche());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockMonday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: lundi
	 * NomAffichage.enUS: monday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocLundi
	 * r.enUS: BlockMonday
	 */                   
	protected void _blocLundi(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getBlocLundi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockTuesday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: mardi
	 * NomAffichage.enUS: tuesday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocMardi
	 * r.enUS: BlockTuesday
	 */                   
	protected void _blocMardi(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getBlocMardi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockWednesday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: mercredi
	 * NomAffichage.enUS: wednesday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocMercredi
	 * r.enUS: BlockWednesday
	 */                   
	protected void _blocMercredi(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getBlocMercredi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockThursday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: jeudi
	 * NomAffichage.enUS: thursday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocJeudi
	 * r.enUS: BlockThursday
	 */                   
	protected void _blocJeudi(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getBlocJeudi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockFriday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: vendredi
	 * NomAffichage.enUS: friday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocVendredi
	 * r.enUS: BlockFriday
	 */                   
	protected void _blocVendredi(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getBlocVendredi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockSaturday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: samedi
	 * NomAffichage.enUS: saturday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocSamedi
	 * r.enUS: BlockSaturday
	 */                   
	protected void _blocSamedi(Couverture<Boolean> c) {
		if(bloc != null)
			c.o(bloc.getBlocSamedi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentApproved
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: approuvé
	 * NomAffichage.enUS: approved
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 */                   
	protected void _inscriptionApprouve(Couverture<Boolean> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentImmunizations
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: vacciné
	 * NomAffichage.enUS: immunized
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 */                   
	protected void _inscriptionImmunisations(Couverture<Boolean> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyMarried
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: marié
	 * NomAffichage.enUS: married
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 1
	 */                   
	protected void _familleMarie(Couverture<Boolean> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familySeparated
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: séparé
	 * NomAffichage.enUS: separated
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 2
	 */                   
	protected void _familleSepare(Couverture<Boolean> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyDivorced
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: divorcé
	 * NomAffichage.enUS: divorced
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 3
	 */                   
	protected void _familleDivorce(Couverture<Boolean> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyAddress
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: addresse de la famille
	 * NomAffichage.enUS: family address
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCellule: 1
	 */                   
	protected void _familleAddresse(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyHowDoYouKnowTheSchool
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: comment vous connaissez l'école ? 
	 * NomAffichage.enUS: how do you know the school? 
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCellule: 2
	 */                   
	protected void _familleCommentVousConnaissezEcole(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSpecialConsiderations
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: considérations spéciale
	 * NomAffichage.enUS: special considerations
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 */                   
	protected void _inscriptionConsiderationsSpeciales(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentGroupName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom du groupe
	 * NomAffichage.enUS: group name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 */                   
	protected void _inscriptionNomGroupe(Couverture<String> c) {
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
	 * HtmlCellule: 3
	 */                   
	protected void _inscriptionPaimentChaqueMois(Couverture<Boolean> c) {
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
	 * HtmlCellule: 2
	 */                   
	protected void _inscriptionPaimentComplet(Couverture<Boolean> c) {
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: enrollmentCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * HtmlColonne: 1
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: "inscription pour l'enfant %s"
	 * r.enUS: "enrollment for the child %s"
	 * r: "inscription %s"
	 * r.enUS: "enrollment %s"
	 * r: getPersonneNomCompletPrefere
	 * r.enUS: getPersonCompleteNamePreferred
	 * r: enfant_
	 * r.enUS: child_
	 */  
	protected void _inscriptionNomComplet(Couverture<String> c) {
		String o;
		if(enfant_ != null)
			o = String.format("inscription pour l'enfant %s", enfant_.getPersonneNomCompletPrefere());
		else
			o = String.format("inscription %s", pk);
		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: enrollmentId
	 * Indexe: true
	 * Stocke: true
	 * VarId: true
	 * HtmlLigne: 1
	 * HtmlCellule: 4
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: ID
	 * NomAffichage.enUS: ID
	 * r: inscriptionNomComplet
	 * r.enUS: enrollmentCompleteName
	 */            
	protected void _inscriptionId(Couverture<String> c) {
		if(inscriptionNomComplet != null) {
			String s = Normalizer.normalize(inscriptionNomComplet, Normalizer.Form.NFD);
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
	 * r: inscriptionId
	 * r.enUS: enrollmentId
	 * r: /inscription/
	 * r.enUS: /enrollment/
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * **/  
	protected void _pageUrl(Couverture<String> c)  {
		if(inscriptionId != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/inscription/" + inscriptionId;
			c.o(o);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * r: inscriptionNomComplet
	 * r.enUS: enrollmentCompleteName
	 */         
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(inscriptionNomComplet);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _classCanonicalNames
	 * Indexe: true
	 * Stocke: true
	 * r: InscriptionScolaire
	 * r.enUS: SchoolEnrollment
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 **/      
	@Override protected void _classeNomsCanoniques(List<String> l) {
		l.add(InscriptionScolaire.class.getCanonicalName());
		super._classeNomsCanoniques(l);
	}
}
