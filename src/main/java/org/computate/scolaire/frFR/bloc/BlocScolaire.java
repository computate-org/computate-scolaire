package org.computate.scolaire.frFR.bloc;                    

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.age.AgeScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.block.SchoolBlock
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Bloc
 * ApiUri.frFR: /api/bloc
 * 
 * ApiTag.enUS: Block
 * ApiUri.enUS: /api/block
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: BlocPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /bloc
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: BlockPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /block
 * 
 * UnNom.frFR: un bloc
 * UnNom.enUS: a block
 * Couleur: indigo
 * IconeGroupe: regular
 * IconeNom: bell
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/         
public class BlocScolaire extends BlocScolaireGen<Cluster> {

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
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'âge dans la base de données. 
	 * Description.enUS: The primary key of the age in the database. 
	 * NomAffichage.frFR: enfant
	 * NomAffichage.enUS: child
	 */                
	protected void _enfantCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: InscriptionScolaire.blocCles
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 * NomAffichage.frFR: inscriptions
	 * NomAffichage.enUS: enrollments
	 */               
	protected void _inscriptionCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKey
	 */               
	protected void _inscriptionCle(Couverture<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentAttribute
	 */               
	protected void _inscriptionAttribuer(Couverture<Boolean> o) {}

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
	 * {@inheritDoc}
	 * Var.enUS: ageKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: AgeScolaire.blocCles
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: âge
	 * NomAffichage.enUS: age
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 * r: AgeCle
	 * r.enUS: AgeKey
	 * r: age
	 * r.enUS: age
	*/             
	protected void _ageCle(Couverture<Long> c) {
	}

	/**
	 * Var.enUS: ageSearch
	 * r: blocCles
	 * r.enUS: blockKeys
	 * r: AgeScolaire
	 * r.enUS: SchoolAge
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */
	protected void _ageRecherche(ListeRecherche<AgeScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("blocCles_indexed_longs:" + pk);
		l.setC(AgeScolaire.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: age_
	 * r: ageRecherche
	 * r.enUS: ageSearch
	 * Ignorer: true
	 */   
	protected void _age_(Couverture<AgeScolaire> c) {
		if(ageRecherche.size() > 0) {
			c.o(ageRecherche.get(0));
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
	 * r: EcoleCle
	 * r.enUS: SchoolKey
	 * r: age
	 * r.enUS: age
	 */              
	protected void _ecoleCle(Couverture<Long> c) {
		if(age_ != null)
			c.o(age_.getEcoleCle());
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
	 * r: age
	 * r.enUS: age
	*/             
	protected void _anneeCle(Couverture<Long> c) {
		if(age_ != null)
			c.o(age_.getAnneeCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: année
	 * NomAffichage.enUS: year
	 * r: SaisonCle
	 * r.enUS: SeasonKey
	 * r: age
	 * r.enUS: age
	*/             
	protected void _saisonCle(Couverture<Long> c) {
		if(age_ != null)
			c.o(age_.getSaisonCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: session
	 * NomAffichage.enUS: session
	 * r: SessionCle
	 * r.enUS: SessionKey
	 * r: age
	 * r.enUS: age
	*/             
	protected void _sessionCle(Couverture<Long> c) {
		if(age_ != null)
			c.o(age_.getSessionCle());
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
	 * r: age
	 * r.enUS: age
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(age_ != null)
			c.o(age_.getEcoleNom());
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
	 * r: age
	 * r.enUS: age
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(age_ != null)
			c.o((String)age_.getEcoleNomComplet());
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
	 * r: age
	 * r.enUS: age
	 */               
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(age_ != null)
			c.o((String)age_.getEcoleEmplacement());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolAddress
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: addresse
	 * NomAffichage.enUS: address
	 * r: EcoleAddresse
	 * r.enUS: SchoolAddress
	 * r: age
	 * r.enUS: age
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
		if(age_ != null)
			c.o((String)age_.getEcoleAddresse());
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
	 * r: age
	 * r.enUS: age
	 */
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
		if(age_ != null)
			c.o((String)age_.getEcoleNumeroTelephone());
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
	 * r: age
	 * r.enUS: age
	 */  
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
		if(age_ != null)
			c.o((String)age_.getEcoleAdministrateurNom());
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
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _anneeDebut(Couverture<Integer> c) {
		if(age_ != null)
			c.o(age_.getAnneeDebut());
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
	 * r: age
	 * r.enUS: age
	 */                     
	protected void _anneeFin(Couverture<Integer> c) {
		if(age_ != null)
			c.o(age_.getAnneeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonStartDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of season
	 * r: SaisonJourDebut
	 * r.enUS: SeasonStartDate
	 * r: age
	 * r.enUS: age
	 */                  
	protected void _saisonJourDebut(Couverture<LocalDate> c) {
		if(age_ != null)
			c.o(age_.getSaisonJourDebut());
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
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _saisonEte(Couverture<Boolean> c) {
		if(age_ != null)
			c.o(age_.getSaisonEte());
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
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _saisonHiver(Couverture<Boolean> c) {
		if(age_ != null)
			c.o(age_.getSaisonHiver());
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
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _saisonFraisInscription(Couverture<BigDecimal> c) {
		if(age_ != null)
			c.o(age_.getSaisonFraisInscription());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonShortName
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomCourt
	 * r.enUS: SeasonShortName
	 * r: age
	 * r.enUS: age
	 */
	protected void _saisonNomCourt(Couverture<String> c) {
		if(age_ != null)
			c.o(age_.getSaisonNomCourt());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomComplet
	 * r.enUS: SeasonCompleteName
	 * r: age
	 * r.enUS: age
	 */
	protected void _saisonNomComplet(Couverture<String> c) {
		if(age_ != null)
			c.o(age_.getSaisonNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionStartDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de la session
	 * NomAffichage.enUS: start of the session
	 * r: SessionJourDebut
	 * r.enUS: SessionStartDate
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _sessionJourDebut(Couverture<LocalDate> c) {
		if(age_ != null)
			c.o((LocalDate)age_.getSessionJourDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionEndDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: fin de la session
	 * NomAffichage.enUS: end of the session
	 * r: SessionJourFin
	 * r.enUS: SessionEndDate
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _sessionJourFin(Couverture<LocalDate> c) {
		if(age_ != null)
			c.o((LocalDate)age_.getSessionJourFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageShortName
	 * Indexe: true
	 * Stocke: true
	 * r: AgeNomCourt
	 * r.enUS: AgeShortName
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _ageNomCourt(Couverture<String> c) {
		if(age_ != null)
			c.o(age_.getAgeNomCourt());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: AgeNomComplet
	 * r.enUS: AgeCompleteName
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _ageNomComplet(Couverture<String> c) {
		if(age_ != null)
			c.o(age_.getAgeNomComplet());
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
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _ageDebut(Couverture<Integer> c) {
		if(age_ != null)
			c.o(age_.getAgeDebut());
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
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _ageFin(Couverture<Integer> c) {
		if(age_ != null)
			c.o(age_.getAgeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockStartTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure début
	 * NomAffichage.enUS: start time
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 */                   
	protected void _blocHeureDebut(Couverture<LocalTime> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockEndTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure fin
	 * NomAffichage.enUS: end time
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 */                   
	protected void _blocHeureFin(Couverture<LocalTime> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockPricePerMonth
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prix par mois
	 * NomAffichage.enUS: price per month
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 */                   
	protected void _blocPrixParMois(Couverture<BigDecimal> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockSunday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: dimanche
	 * NomAffichage.enUS: sunday
	 * Definir: true
	 */                   
	protected void _blocDimanche(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockMonday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: lundi
	 * NomAffichage.enUS: monday
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 */                   
	protected void _blocLundi(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockTuesday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: mardi
	 * NomAffichage.enUS: tuesday
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 3
	 */                   
	protected void _blocMardi(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockWednesday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: mercredi
	 * NomAffichage.enUS: wednesday
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 4
	 */                   
	protected void _blocMercredi(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockThursday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: jeudi
	 * NomAffichage.enUS: thursday
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 5
	 */                   
	protected void _blocJeudi(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockFriday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: vendredi
	 * NomAffichage.enUS: friday
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 6
	 */                   
	protected void _blocVendredi(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockSaturday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: samedi
	 * NomAffichage.enUS: saturday
	 * Definir: true
	 */                   
	protected void _blocSamedi(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockTotalPrice
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prix total
	 * NomAffichage.enUS: total price
	 * r: blocPrixParMois
	 * r.enUS: blockPricePerMonth
	 * r: sessionJourDebut
	 * r.enUS: sessionStartDate
	 * r: sessionJourFin
	 * r.enUS: sessionEndDate
	 */                   
	protected void _blocPrixTotal(Couverture<BigDecimal> c) {
		if(blocPrixParMois != null && sessionJourDebut != null && sessionJourFin != null) {
			c.o(blocPrixParMois.multiply(new BigDecimal(ChronoUnit.MONTHS.between(sessionJourDebut, sessionJourFin))));
		}
	}

	/**
	 * Var.enUS: sessionBlocks
	 */
	protected void _blocsSession(List<BlocScolaire> l) {}

	/**
	 * Var.enUS: ageBlocks
	 */
	protected void _blocsAge(List<BlocScolaire> l) {}

	/**
	 * Var.enUS: blockBlocks
	 */
	protected void _blocsBloc(List<BlocScolaire> l) {}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: blockShortName
	 * Indexe: true
	 * Stocke: true
	 * r: %s - %s %s à %s
	 * r.enUS: %s %s at %s
	 * r: %s - %s %s (%s€ par mois) à %s
	 * r.enUS: %s - %s %s ($%s/month) at %s
	 * r: strBlocHeureDebut
	 * r.enUS: strBlockStartTime
	 * r: strBlocHeureFin
	 * r.enUS: strBlockEndTime
	 * r: strBlocPrixParMois
	 * r.enUS: strBlockPricePerMonth
	 * r: blocPrixParMois
	 * r.enUS: blockPricePerMonth
	 * r: blocDimanche
	 * r.enUS: blockSunday
	 * r: blocLundi
	 * r.enUS: blockMonday
	 * r: blocMardi
	 * r.enUS: blockTuesday
	 * r: blocMercredi
	 * r.enUS: blockWednesday
	 * r: blocJeudi
	 * r.enUS: blockThursday
	 * r: blocVendredi
	 * r.enUS: blockFriday
	 * r: blocSamedi
	 * r.enUS: blockSaturday
	 * r: ageNomComplet
	 * r.enUS: ageCompleteName
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 * r: " Dim"
	 * r.enUS: " Su"
	 * r: " Lun"
	 * r.enUS: " Mo"
	 * r: " Mar"
	 * r.enUS: " Tu"
	 * r: " Mer"
	 * r.enUS: " We"
	 * r: " Jeu"
	 * r.enUS: " Th"
	 * r: " Ven"
	 * r.enUS: " Fr"
	 * r: " Sam"
	 * r.enUS: " Sa"
	 */   
	protected void _blocNomCourt(Couverture<String> c) {
		String o;
		String weekdays = "";
		if(BooleanUtils.isTrue(blocDimanche)) weekdays += " Dim";
		if(BooleanUtils.isTrue(blocLundi)) weekdays += " Lun";
		if(BooleanUtils.isTrue(blocMardi)) weekdays += " Mar";
		if(BooleanUtils.isTrue(blocMercredi)) weekdays += " Mer";
		if(BooleanUtils.isTrue(blocJeudi)) weekdays += " Jeu";
		if(BooleanUtils.isTrue(blocVendredi)) weekdays += " Ven";
		if(BooleanUtils.isTrue(blocSamedi)) weekdays += " Sam";
		weekdays = StringUtils.replace(StringUtils.trim(weekdays), " ", "/");
		if(blocPrixParMois == null)
			o = String.format("%s - %s %s à %s", strBlocHeureDebut(), strBlocHeureFin(), weekdays, ecoleNomComplet);
		else
			o = String.format("%s - %s %s (%s€ par mois) à %s", strBlocHeureDebut(), strBlocHeureFin(), weekdays, strBlocPrixParMois(), ecoleNomComplet);
		c.o(o);
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: blockAdminName
	 * Indexe: true
	 * Stocke: true
	 * r: %s - %s %s à %s
	 * r.enUS: %s %s at %s
	 * r: %s - %s %s (%s€ par mois) à %s
	 * r.enUS: %s - %s %s ($%s/month) at %s
	 * r: strBlocHeureDebut
	 * r.enUS: strBlockStartTime
	 * r: strBlocHeureFin
	 * r.enUS: strBlockEndTime
	 * r: strBlocPrixParMois
	 * r.enUS: strBlockPricePerMonth
	 * r: blocPrixParMois
	 * r.enUS: blockPricePerMonth
	 * r: blocDimanche
	 * r.enUS: blockSunday
	 * r: blocLundi
	 * r.enUS: blockMonday
	 * r: blocMardi
	 * r.enUS: blockTuesday
	 * r: blocMercredi
	 * r.enUS: blockWednesday
	 * r: blocJeudi
	 * r.enUS: blockThursday
	 * r: blocVendredi
	 * r.enUS: blockFriday
	 * r: blocSamedi
	 * r.enUS: blockSaturday
	 * r: ageNomComplet
	 * r.enUS: ageCompleteName
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 * r: " Di"
	 * r.enUS: " Su"
	 * r: " Lu"
	 * r.enUS: " M"
	 * r: " Ma"
	 * r.enUS: " Tu"
	 * r: " Me"
	 * r.enUS: " W"
	 * r: " Je"
	 * r.enUS: " Th"
	 * r: " Ve"
	 * r.enUS: " F"
	 * r: " Sa"
	 * r.enUS: " S"
	 */   
	protected void _blocNomAdmin(Couverture<String> c) {
		String o;
		String weekdays = "";
		if(BooleanUtils.isTrue(blocDimanche)) weekdays += " Di";
		if(BooleanUtils.isTrue(blocLundi)) weekdays += " Lu";
		if(BooleanUtils.isTrue(blocMardi)) weekdays += " Ma";
		if(BooleanUtils.isTrue(blocMercredi)) weekdays += " Me";
		if(BooleanUtils.isTrue(blocJeudi)) weekdays += " Je";
		if(BooleanUtils.isTrue(blocVendredi)) weekdays += " Ve";
		if(BooleanUtils.isTrue(blocSamedi)) weekdays += " Sa";
		weekdays = StringUtils.trim(weekdays);
			o = String.format("%s %s - %s", weekdays, strBlocHeureDebut(), strBlocHeureFin());
		c.o(o);
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: blockCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * HtmlColonne: 1
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: %s - %s %s (%s€ par mois) %s
	 * r.enUS: %s - %s %s ($%s/month) %s
	 * r: strBlocHeureDebut
	 * r.enUS: strBlockStartTime
	 * r: strBlocHeureFin
	 * r.enUS: strBlockEndTime
	 * r: strBlocPrixParMois
	 * r.enUS: strBlockPricePerMonth
	 * r: blocPrixParMois
	 * r.enUS: blockPricePerMonth
	 * r: blocDimanche
	 * r.enUS: blockSunday
	 * r: blocLundi
	 * r.enUS: blockMonday
	 * r: blocMardi
	 * r.enUS: blockTuesday
	 * r: blocMercredi
	 * r.enUS: blockWednesday
	 * r: blocJeudi
	 * r.enUS: blockThursday
	 * r: blocVendredi
	 * r.enUS: blockFriday
	 * r: blocSamedi
	 * r.enUS: blockSaturday
	 * r: ageNomComplet
	 * r.enUS: ageCompleteName
	 * r: " Dim"
	 * r.enUS: " Su"
	 * r: " Lun"
	 * r.enUS: " Mo"
	 * r: " Mar"
	 * r.enUS: " Tu"
	 * r: " Mer"
	 * r.enUS: " We"
	 * r: " Jeu"
	 * r.enUS: " Th"
	 * r: " Ven"
	 * r.enUS: " Fr"
	 * r: " Sam"
	 * r.enUS: " Sa"
	 */  
	protected void _blocNomComplet(Couverture<String> c) {
		String o;
		String weekdays = "";
		if(BooleanUtils.isTrue(blocDimanche)) weekdays += " Dim";
		if(BooleanUtils.isTrue(blocLundi)) weekdays += " Lun";
		if(BooleanUtils.isTrue(blocMardi)) weekdays += " Mar";
		if(BooleanUtils.isTrue(blocMercredi)) weekdays += " Mer";
		if(BooleanUtils.isTrue(blocJeudi)) weekdays += " Jeu";
		if(BooleanUtils.isTrue(blocVendredi)) weekdays += " Ven";
		if(BooleanUtils.isTrue(blocSamedi)) weekdays += " Sam";
		weekdays = StringUtils.replace(StringUtils.trim(weekdays), " ", "/");
		if(blocPrixParMois == null)
			o = String.format("%s - %s %s %s", strBlocHeureDebut(), strBlocHeureFin(), weekdays, ageNomComplet);
		else
			o = String.format("%s - %s %s (%s€ par mois) %s", strBlocHeureDebut(), strBlocHeureFin(), weekdays, strBlocPrixParMois(), ageNomComplet);
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: blocNomComplet
	 * r.enUS: blockCompleteName
	 */
	@Override protected void _objetTitre(Couverture<String> c) {
		c.o(blocNomComplet);
	}

	/**
	 * Var.enUS: strBlockPricePerMonth
	 * r: blocPrixParMois
	 * r.enUS: blockPricePerMonth
	 */
	@Override public String strBlocPrixParMois() {
		return blocPrixParMois == null ? "" : blocPrixParMois.setScale(0).toString();
	}

	/**
	 * Var.enUS: inputEnrollmentAttribute
	 * r: patchInscriptionScolaireVals
	 * r.enUS: patchSchoolEnrollmentVals
	 * r: inscriptionCle
	 * r.enUS: enrollmentKey
	 * r: BlocCles
	 * r.enUS: BlockKeys
	 * r: inscriptionAttribuer
	 * r.enUS: enrollmentAttribute
	 */
	public void inputInscriptionAttribuer(String classeApiMethodeMethode) {
		e("input")
			.a("type", "checkbox")
//			.a("id", classeApiMethodeMethode, "_inscriptionApprouve")
			.a("class", "setInscriptionApprouve")
			.a("name", "setInscriptionApprouve")
			.a("onchange", "patchInscriptionScolaireVals([{ name: 'fq', value: 'pk:", inscriptionCle, "' }], { [($(this).prop('checked') ? 'add' : 'remove') + 'BlocCles']: '", pk, "' } ); ");
			if(inscriptionAttribuer)
				a("checked", "checked");
		fg();

	}
}
