package org.computate.scolaire.frFR.age;            

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;
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
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
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
	 * HtmlCellule: 2
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
	 * {@inheritDoc}
	 * Var.enUS: sessionKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: SessionScolaire.ageCles
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: session
	 * NomAffichage.enUS: session
	*/            
	protected void _sessionCle(Couverture<Long> c) {
	}

	/**
	 * Var.enUS: yearSearch
	 * r: ageCles
	 * r.enUS: ageKeys
	 * r: AnneeScolaire
	 * r.enUS: SchoolYear
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */
	protected void _anneeRecherche(ListeRecherche<AnneeScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageCles_indexed_longs:" + pk);
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
	 * Attribuer: AnneeScolaire.ageCles
	 * HtmlLigne: 4
	 * HtmlCellule: 1
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
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: seasonKey
//	 * Indexe: true
//	 * Stocke: true
//	 * Description.frFR: L'année scolaire de la saison scolaire. 
//	 * Description.enUS: The school year of the school season. 
//	 * NomAffichage.frFR: année
//	 * NomAffichage.enUS: year
//	 * r: SaisonCle
//	 * r.enUS: SeasonKey
//	 * r: annee
//	 * r.enUS: year
//	*/             
//	protected void _saisonCle(Couverture<Long> c) {
//		if(annee_ != null)
//			c.o(annee_.getSaisonCle());
//	}

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
	 * r: annee
	 * r.enUS: year
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleNom());
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
			c.o((String)annee_.getEcoleNomComplet());
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
	 * Var.enUS: schoolAddress
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: addresse
	 * NomAffichage.enUS: address
	 * r: EcoleAddresse
	 * r.enUS: SchoolAddress
	 * r: annee
	 * r.enUS: year
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
		if(annee_ != null)
			c.o((String)annee_.getEcoleAddresse());
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
	 * r: annee
	 * r.enUS: year
	 */
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
		if(annee_ != null)
			c.o((String)annee_.getEcoleNumeroTelephone());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolNumber
	 * Indexe: true
	 * Stocke: true
	 * r: EcoleNumero
	 * r.enUS: SchoolNumber
	 * r: annee
	 * r.enUS: year
	 */
	protected void _ecoleNumero(Couverture<Integer> c) {
		if(annee_ != null)
			c.o((Integer)annee_.getEcoleNumero());
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
	 * r: annee
	 * r.enUS: year
	 */  
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
		if(annee_ != null)
			c.o((String)annee_.getEcoleAdministrateurNom());
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
	 * r.enUS: YearEnd
	 * r: annee
	 * r.enUS: year
	 */                      
	protected void _anneeFin(Couverture<Integer> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeFin());
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
	 * r: annee
	 * r.enUS: year
	 */                   
	protected void _saisonDateDebut(Couverture<LocalDate> c) {
		if(annee_ != null)
			c.o(annee_.getSaisonDateDebut());
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
//	 * r: session
//	 * r.enUS: session
//	 */                   
//	protected void _saisonEte(Couverture<Boolean> c) {
//		if(annee_ != null)
//			c.o(annee_.getSaisonEte());
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
//	 * r: session
//	 * r.enUS: session
//	 */                   
//	protected void _saisonHiver(Couverture<Boolean> c) {
//		if(annee_ != null)
//			c.o(annee_.getSaisonHiver());
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
	 * r: annee
	 * r.enUS: year
	 */                    
	protected void _anneeFraisInscription(Couverture<BigDecimal> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeFraisInscription());
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
	 * Var.enUS: sessionStartDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de la session
	 * NomAffichage.enUS: start of the session
	 * r: SessionDateDebut
	 * r.enUS: SessionStartDate
	 * r: annee
	 * r.enUS: year
	 */                   
	protected void _sessionDateDebut(Couverture<LocalDate> c) {
		if(annee_ != null)
			c.o((LocalDate)annee_.getSessionDateDebut());
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
	 * r: annee
	 * r.enUS: year
	 */                   
	protected void _sessionDateFin(Couverture<LocalDate> c) {
		if(annee_ != null)
			c.o((LocalDate)annee_.getSessionDateFin());
	}
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: sessionCompleteName
//	 * Indexe: true
//	 * Stocke: true
//	 * r: SessionNomComplet
//	 * r.enUS: SessionCompleteName
//	 * r: session
//	 * r.enUS: session
//	 */                   
//	protected void _sessionNomComplet(Couverture<String> c) {
//		if(annee_ != null)
//			c.o(annee_.getSessionNomComplet());
//	}

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
	 * Var.enUS: ageShortName
	 * Indexe: true
	 * Stocke: true
	 * r: ageEte
	 * r.enUS: ageSummer
	 * r: âges %s-%s %s - %s
	 * r.enUS: %s-%s year olds (%s - %s)
	 * r: strAgeDebut
	 * r.enUS: strAgeStart
	 * r: strAgeFin
	 * r.enUS: strAgeEnd
	 * r: sessionNomComplet
	 * r.enUS: sessionCompleteName
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: sessionDateDebut
	 * r.enUS: sessionStartDate
	 * r: sessionDateFin
	 * r.enUS: sessionEndDate
	 * r: FORMATMoisAnnee
	 * r.enUS: FORMATMonthYear
	 */ 
	protected void _ageNomCourt(Couverture<String> c) {
		String o;
		o = String.format("âges %s-%s %s - %s", strAgeDebut(), strAgeFin(), sessionDateDebut == null ? "" : MiseEnPage.FORMATMoisAnnee.format(sessionDateDebut), sessionDateFin == null ? "" : MiseEnPage.FORMATMoisAnnee.format(sessionDateFin));
		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: ageCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
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
	 * r: anneeNomComplet
	 * r.enUS: yearCompleteName
	 */
	protected void _ageNomComplet(Couverture<String> c) {
		String o;
		o = String.format("âges %s-%s pendant la %s", strAgeDebut(), strAgeFin(), anneeNomComplet);
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
