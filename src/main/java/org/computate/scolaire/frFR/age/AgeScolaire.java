package org.computate.scolaire.frFR.age;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.saison.SaisonScolaire;
import org.computate.scolaire.frFR.session.SessionScolaire;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.age.SchoolAge
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Age
 * ApiUri.frFR: /frFR/api/age
 * 
 * ApiTag.enUS: Age
 * ApiUri.enUS: /enUS/api/age
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
 * ApiUriPageRecherche.frFR: /frFR/age
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: AgePage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /enUS/age
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
	 * Description.frFR: L'année scolaire de l'âge scolaire. 
	 * Description.enUS: The school year of the school age. 
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
	 * Description.frFR: La saison scolaire de l'âge scolaire. 
	 * Description.enUS: The school season of the school age. 
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
	 * HtmlLigne: 3
	 * HtmlColonne: 4
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the session in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
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
	 * r: saisonCles
	 * r.enUS: seasonKeys
	 * r: SessionScolaire
	 * r.enUS: SchoolSession
	 * Ignorer: true
	 */
	protected void _sessionRecherche(ListeRecherche<SessionScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageCles_indexed_longs:" + pk);
		l.setC(SessionScolaire.class);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: session
	 * r: sessionRecherche
	 * r.enUS: sessionSearch
	 * Ignorer: true
	 */   
	protected void _session(Couverture<SessionScolaire> c) {
		if(sessionRecherche.size() > 0) {
			c.o(sessionRecherche.get(0));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolNameComplete
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: EcoleNomComplet
	 * r.enUS: SchoolNameComplete
	 * r: session
	 * r.enUS: session
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(session != null)
			c.o((String)session.getEcoleNomComplet());
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
	protected void _anneeDebut(Couverture<LocalDate> c) {
		if(session != null)
			c.o((LocalDate)session.getAnneeDebut());
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
	protected void _anneeFin(Couverture<LocalDate> c) {
		if(session != null)
			c.o(session.getAnneeFin());
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
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _saisonJourDebut(Couverture<LocalDate> c) {
		if(session != null)
			c.o(session.getSaisonJourDebut());
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
		if(session != null)
			c.o(session.getSaisonEte());
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
		if(session != null)
			c.o(session.getSaisonHiver());
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
		if(session != null)
			c.o(session.getSaisonFraisInscription());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonNameComplete
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomComplet
	 * r.enUS: SeasonNameComplete
	 * r: session
	 * r.enUS: session
	 */
	protected void _saisonNomComplet(Couverture<String> c) {
		if(session != null)
			c.o(session.getSaisonNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonEnd
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of season
	 * r: AnneeFin
	 * r.enUS: YearStart
	 * r: session
	 * r.enUS: session
	 */                      
	protected void _saisonFin(Couverture<LocalDate> c) {
		if(session != null)
			c.o((LocalDate)session.getAnneeFin());
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
		if(session != null)
			c.o((LocalDate)session.getSessionJourDebut());
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
		if(session != null)
			c.o((LocalDate)session.getSessionJourFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionNameComplete
	 * Indexe: true
	 * Stocke: true
	 * r: SessionNomComplet
	 * r.enUS: SessionNameComplete
	 * r: session
	 * r.enUS: session
	 */                   
	protected void _sessionNomComplet(Couverture<String> c) {
		if(session != null)
			c.o(session.getSessionNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageStart
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
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
	 * HtmlColonne: 1
	 * NomAffichage.frFR: fin du groupe d'âge
	 * NomAffichage.enUS: end of the age group
	 */                   
	protected void _ageFin(Couverture<Integer> c) {
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: ageNameComplete
	 * Indexe: true
	 * Stocke: true
	 * VarTitre: true
	 * r: ageEte
	 * r.enUS: ageSummer
	 * r: âges %s - %s pendant %s. 
	 * r.enUS: ages %s - %s during %s. 
	 * r: strAgeDebut
	 * r.enUS: strAgeStart
	 * r: strAgeFin
	 * r.enUS: strAgeEnd
	 * r: sessionNomComplet
	 * r.enUS: sessionNameComplete
	 */
	protected void _ageNomComplet(Couverture<String> c) {
		String o;
		o = String.format("âges %s - %s pendant %s. ", strAgeDebut(), strAgeFin(), sessionNomComplet);
		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: ageId
	 * Indexe: true
	 * Stocke: true
	 * VarId: true
	 * HtmlLigne: 1
	 * HtmlColonne: 4
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: ID
	 * NomAffichage.enUS: ID
	 * r: ageNomComplet
	 * r.enUS: ageNameComplete
	 */            
	protected void _ageId(Couverture<String> c) {
		if(ageNomComplet != null) {
			String s = Normalizer.normalize(ageNomComplet, Normalizer.Form.NFD);
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
	 * r: ageId
	 * r.enUS: ageId
	 * r: /frFR/age/
	 * r.enUS: /enUS/age/
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * **/   
	protected void _pageUrl(Couverture<String> c)  {
		if(ageId != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/frFR/age/" + ageId;
			c.o(o);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * r: ageNomComplet
	 * r.enUS: ageNameComplete
	 */         
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(ageNomComplet);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _classCanonicalNames
	 * Indexe: true
	 * Stocke: true
	 * r: AgeScolaire
	 * r.enUS: SchoolAge
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 **/      
	@Override protected void _classeNomsCanoniques(List<String> l) {
		l.add(AgeScolaire.class.getCanonicalName());
		super._classeNomsCanoniques(l);
	}
}
