package org.computate.scolaire.frFR.age;      

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.session.SessionScolaire;

/**     
 * NomCanonique.enUS: org.computate.scolaire.enUS.age.SchoolAge
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Age
 * ApiUri.frFR: /api/age
 * 
 * ApiTag.enUS: Age
 * ApiUri.enUS: /api/age
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: AgePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /age
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: AgePage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /age
 * 
 * UnNom.frFR: un âge
 * UnNom.enUS: an age
 * Couleur: blue
 * IconeGroupe: duotone
 * IconeNom: birthday-cake
*/                                                   
public class AgeScolaire extends AgeScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'âge dans la base de données. 
	 * Description.enUS: The primary key of the age in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */ 
	protected void _ageCle(Couverture<Long> c) {
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
	 * Var.enUS: blockKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: BlocScolaire.ageCle
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * Description.frFR: Les blocs scolaires de l'âge scolaire. 
	 * Description.enUS: The school blocks of the school age. 
	 * NomAffichage.frFR: blocs
	 * NomAffichage.enUS: blocks
	 */   
	protected void _blocCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: educationSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _scolaireTri(Couverture<Integer> c) {
		c.o(5);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(5);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _anneeTri(Couverture<Integer> c) {
		c.o(5);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _saisonTri(Couverture<Integer> c) {
		c.o(5);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _sessionTri(Couverture<Integer> c) {
		c.o(5);
	}

	/**
	 * Var.enUS: sessionSearch
	 * r: ageCles
	 * r.enUS: ageKeys
	 * r: SessionScolaire
	 * r.enUS: SchoolSession
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */
	protected void _sessionRecherche(ListeRecherche<SessionScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageCles_indexed_longs:" + pk);
		l.setC(SessionScolaire.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: session_
	 * r: sessionRecherche
	 * r.enUS: sessionSearch
	 * Ignorer: true
	 */   
	protected void _session_(Couverture<SessionScolaire> c) {
		if(sessionRecherche.size() > 0) {
			c.o(sessionRecherche.get(0));
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
	 * r: session
	 * r.enUS: session
	 */              
	protected void _ecoleCle(Couverture<Long> c) {
		if(session_ != null)
			c.o(session_.getEcoleCle());
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
	 * r: session
	 * r.enUS: session
	*/             
	protected void _anneeCle(Couverture<Long> c) {
		if(session_ != null)
			c.o(session_.getAnneeCle());
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
	 * r: session
	 * r.enUS: session
	*/             
	protected void _saisonCle(Couverture<Long> c) {
		if(session_ != null)
			c.o(session_.getSaisonCle());
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
	 * r: session
	 * r.enUS: session
	*/             
	protected void _sessionCle(Couverture<Long> c) {
		if(session_ != null)
			c.o(session_.getSessionCle());
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
	 * r: session
	 * r.enUS: session
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(session_ != null)
			c.o(session_.getEcoleNom());
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
	 * r: session
	 * r.enUS: session
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(session_ != null)
			c.o((String)session_.getEcoleNomComplet());
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
	 * r: session
	 * r.enUS: session
	 */               
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(session_ != null)
			c.o((String)session_.getEcoleEmplacement());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _anneeDebut(Couverture<Integer> c) {
		if(session_ != null)
			c.o(session_.getAnneeDebut());
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
	 * r: session
	 * r.enUS: session
	 */                      
	protected void _anneeFin(Couverture<Integer> c) {
		if(session_ != null)
			c.o(session_.getAnneeFin());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _saisonJourDebut(Couverture<LocalDate> c) {
		if(session_ != null)
			c.o(session_.getSaisonJourDebut());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _saisonEte(Couverture<Boolean> c) {
		if(session_ != null)
			c.o(session_.getSaisonEte());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _saisonHiver(Couverture<Boolean> c) {
		if(session_ != null)
			c.o(session_.getSaisonHiver());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _saisonFraisInscription(Couverture<BigDecimal> c) {
		if(session_ != null)
			c.o(session_.getSaisonFraisInscription());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomComplet
	 * r.enUS: SeasonCompleteName
	 * r: session
	 * r.enUS: session
	 */
	protected void _saisonNomComplet(Couverture<String> c) {
		if(session_ != null)
			c.o(session_.getSaisonNomComplet());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _sessionJourDebut(Couverture<LocalDate> c) {
		if(session_ != null)
			c.o((LocalDate)session_.getSessionJourDebut());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _sessionJourFin(Couverture<LocalDate> c) {
		if(session_ != null)
			c.o((LocalDate)session_.getSessionJourFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: SessionNomComplet
	 * r.enUS: SessionCompleteName
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _sessionNomComplet(Couverture<String> c) {
		if(session_ != null)
			c.o(session_.getSessionNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageStart
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: début du groupe d'âge
	 * NomAffichage.enUS: start of the age group
	 */                   
	protected void _ageDebut(Couverture<Integer> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageEnd
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: fin du groupe d'âge
	 * NomAffichage.enUS: end of the age group
	 */                  
	protected void _ageFin(Couverture<Integer> c) {
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: ageCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * HtmlColonne: 1
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: ageEte
	 * r.enUS: ageSummer
	 * r: âges %s-%s pendant la %s
	 * r.enUS: ages %s-%s during the %s
	 * r: strAgeDebut
	 * r.enUS: strAgeStart
	 * r: strAgeFin
	 * r.enUS: strAgeEnd
	 * r: sessionNomComplet
	 * r.enUS: sessionCompleteName
	 */
	protected void _ageNomComplet(Couverture<String> c) {
		String o;
		o = String.format("âges %s-%s pendant la %s", strAgeDebut(), strAgeFin(), sessionNomComplet);
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: ageNomComplet
	 * r.enUS: ageCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(ageNomComplet);
	}
}
