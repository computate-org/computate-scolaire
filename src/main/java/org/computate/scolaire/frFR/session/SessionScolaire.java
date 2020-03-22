package org.computate.scolaire.frFR.session;                   

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.saison.SaisonScolaire;

/**   
 * NomCanonique.enUS: org.computate.scolaire.enUS.session.SchoolSession
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Session
 * ApiUri.frFR: /api/session
 * 
 * ApiTag.enUS: Session
 * ApiUri.enUS: /api/session
 * 
 * ApiMethode: POST
 * ApiMethode: PUT
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: SessionPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /session
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: SessionPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /session
 * 
 * UnNom.frFR: une session
 * UnNom.enUS: a session
 * Couleur: green
 * IconeGroupe: duotone
 * IconeNom: graduation-cap
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/                                             
public class SessionScolaire extends SessionScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the session in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */          
	protected void _sessionCle(Couverture<Long> c) {
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
	 * Var.enUS: ageKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: AgeScolaire.sessionCle
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 * Description.frFR: Les âges scolaires de la session scolaire. 
	 * Description.enUS: The school age of the school session. 
	 * NomAffichage.frFR: âges
	 * NomAffichage.enUS: ages
	 */  
	protected void _ageCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: educationSort
	 * Indexe: true
	 * Stocke: true
	 */ 
	protected void _scolaireTri(Couverture<Integer> c) {
		c.o(4);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(4);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _anneeTri(Couverture<Integer> c) {
		c.o(4);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _saisonTri(Couverture<Integer> c) {
		c.o(4);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _sessionTri(Couverture<Integer> c) {
		c.o(4);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: SaisonScolaire.sessionCles
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: saison
	 * NomAffichage.enUS: season
	*/             
	protected void _saisonCle(Couverture<Long> c) {
	}

	/**
	 * Var.enUS: seasonSearch
	 * r: sessionCles
	 * r.enUS: sessionKeys
	 * r: SaisonScolaire
	 * r.enUS: SchoolSeason
	 * r: Stocker
	 * r.enUS: Store
	 * Ignorer: true
	 */
	protected void _saisonRecherche(ListeRecherche<SaisonScolaire> l) {
		l.setQuery("*:*");
		if(pk != null)
			l.addFilterQuery("sessionCles_indexed_longs:" + pk);
		l.setC(SaisonScolaire.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: season_
	 * r: saisonRecherche
	 * r.enUS: seasonSearch
	 * Ignorer: true
	 */   
	protected void _saison_(Couverture<SaisonScolaire> c) {
		if(saisonRecherche.size() > 0) {
			c.o(saisonRecherche.get(0));
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
	 * r: saison
	 * r.enUS: season
	 */              
	protected void _ecoleCle(Couverture<Long> c) {
		if(saison_ != null)
			c.o(saison_.getEcoleCle());
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
	 * r: saison
	 * r.enUS: season
	*/             
	protected void _anneeCle(Couverture<Long> c) {
		if(saison_ != null)
			c.o(saison_.getAnneeCle());
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
	 * r: saison
	 * r.enUS: season
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(saison_ != null)
			c.o(saison_.getEcoleNom());
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
	 * r: saison
	 * r.enUS: season
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(saison_ != null)
			c.o((String)saison_.getEcoleNomComplet());
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
	 * r: saison
	 * r.enUS: season
	 */               
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(saison_ != null)
			c.o((String)saison_.getEcoleEmplacement());
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
	 * r: saison
	 * r.enUS: season
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
		if(saison_ != null)
			c.o((String)saison_.getEcoleAddresse());
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
	 * r: saison
	 * r.enUS: season
	 */
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
		if(saison_ != null)
			c.o((String)saison_.getEcoleNumeroTelephone());
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
	 * r: saison
	 * r.enUS: season
	 */  
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
		if(saison_ != null)
			c.o((String)saison_.getEcoleAdministrateurNom());
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
	 * r: saison
	 * r.enUS: season
	 */                   
	protected void _anneeDebut(Couverture<Integer> c) {
		if(saison_ != null)
			c.o(saison_.getAnneeDebut());
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
	 * r: saison
	 * r.enUS: season
	 */                      
	protected void _anneeFin(Couverture<Integer> c) {
		if(saison_ != null)
			c.o(saison_.getAnneeFin());
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
	 * r: saison
	 * r.enUS: season
	 */                   
	protected void _saisonJourDebut(Couverture<LocalDate> c) {
		if(saison_ != null)
			c.o(saison_.getSaisonJourDebut());
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
	 * r: saison
	 * r.enUS: season
	 */                   
	protected void _saisonEte(Couverture<Boolean> c) {
		if(saison_ != null)
			c.o(saison_.getSaisonEte());
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
	 * r: saison
	 * r.enUS: season
	 */                   
	protected void _saisonHiver(Couverture<Boolean> c) {
		if(saison_ != null)
			c.o(saison_.getSaisonHiver());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnrollmentFee
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: AnneeFraisInscription
	 * r.enUS: YearEnrollmentFee
	 * r: saison
	 * r.enUS: season
	 */                    
	protected void _anneeFraisInscription(Couverture<BigDecimal> c) {
		if(saison_ != null)
			c.o(saison_.getAnneeFraisInscription());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonShortName
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomCourt
	 * r.enUS: SeasonShortName
	 * r: saison
	 * r.enUS: season
	 */
	protected void _saisonNomCourt(Couverture<String> c) {
		if(saison_ != null)
			c.o(saison_.getSaisonNomCourt());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomComplet
	 * r.enUS: SeasonCompleteName
	 * r: saison
	 * r.enUS: season
	 */
	protected void _saisonNomComplet(Couverture<String> c) {
		if(saison_ != null)
			c.o(saison_.getSaisonNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionStartDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: début de la session
	 * NomAffichage.enUS: start of the session
	 */                   
	protected void _sessionJourDebut(Couverture<LocalDate> c) {}

	/**
	 * Var.enUS: setSessionStartDate
	 * r: SessionJourDebut
	 * r.enUS: SessionStartDate
	 */
	@Override public SessionScolaire setSessionJourDebut(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionJourDebut(o);
		} catch (Exception e) {
			setSessionJourDebut(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	/**
	 * Var.enUS: setSessionEndDate
	 * r: SessionJourFin
	 * r.enUS: SessionEndDate
	 */
	@Override public SessionScolaire setSessionJourFin(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionJourFin(o);
		} catch (Exception e) {
			setSessionJourFin(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionEndDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 * NomAffichage.frFR: fin de la session
	 * NomAffichage.enUS: end of the session
	 */                   
	protected void _sessionJourFin(Couverture<LocalDate> c) {}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: sessionShortName
	 * Indexe: true
	 * Stocke: true
	 * r: sessionEte
	 * r.enUS: sessionSummer
	 * r: strSessionJourDebut
	 * r.enUS: strSessionStartDate
	 * r: strSessionJourFin
	 * r.enUS: strSessionEndDate
	 * r: saisonNomComplet
	 * r.enUS: seasonCompleteName
	 * r: "%s - %s session d'été à %s"
	 * r.enUS: "%s - %s summer session at %s"
	 * r: "%s - %s session scolaire à %s"
	 * r.enUS: "%s - %s school session at %s"
	 * r: "%s - %s session à %s"
	 * r.enUS: "%s - %s session at %s"
	 * r: saisonEte
	 * r.enUS: seasonSummer
	 * r: saisonHiver
	 * r.enUS: seasonWinter
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 */
	protected void _sessionNomCourt(Couverture<String> c) {
		String o;

		if(BooleanUtils.isTrue(saisonEte))
			o = String.format("%s - %s session d'été à %s", strSessionJourDebut(), strSessionJourFin(), ecoleNomComplet);
		if(BooleanUtils.isTrue(saisonHiver))
			o = String.format("%s - %s session scolaire à %s", strSessionJourDebut(), strSessionJourFin(), ecoleNomComplet);
		else
			o = String.format("%s - %s session à %s", strSessionJourDebut(), strSessionJourFin(), ecoleNomComplet);

		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: sessionCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: sessionEte
	 * r.enUS: sessionSummer
	 * r: strSessionJourDebut
	 * r.enUS: strSessionStartDate
	 * r: strSessionJourFin
	 * r.enUS: strSessionEndDate
	 * r: saisonNomComplet
	 * r.enUS: seasonCompleteName
	 * r: "%s - %s session d'été à %s"
	 * r.enUS: "%s - %s summer session at %s"
	 * r: "%s - %s session scolaire à %s"
	 * r.enUS: "%s - %s school session at %s"
	 * r: "%s - %s session à %s"
	 * r.enUS: "%s - %s session at %s"
	 * r: saisonEte
	 * r.enUS: seasonSummer
	 * r: saisonHiver
	 * r.enUS: seasonWinter
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 */
	protected void _sessionNomComplet(Couverture<String> c) {
		String o;

		if(BooleanUtils.isTrue(saisonEte))
			o = String.format("%s - %s session d'été à %s", strSessionJourDebut(), strSessionJourFin(), ecoleNomComplet);
		if(BooleanUtils.isTrue(saisonHiver))
			o = String.format("%s - %s session scolaire à %s", strSessionJourDebut(), strSessionJourFin(), ecoleNomComplet);
		else
			o = String.format("%s - %s session à %s", strSessionJourDebut(), strSessionJourFin(), ecoleNomComplet);

		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: sessionNomComplet
	 * r.enUS: sessionCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(sessionNomComplet);
	}
}
