package org.computate.scolaire.frFR.bloc;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.age.AgeScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.bloc.SchoolBlock
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Bloc
 * ApiUri.frFR: /frFR/api/bloc
 * 
 * ApiTag.enUS: Block
 * ApiUri.enUS: /enUS/api/block
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
 * ApiUriPageRecherche.frFR: /frFR/bloc
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: BlockPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /enUS/block
 * 
 * UnNom.frFR: un bloc
 * UnNom.enUS: a block
 * Couleur: indigo
 * IconeGroupe: duotone
 * IconeNom: bell-o
*/
public class BlocScolaire extends BlocScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlColonne: 3
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
	 * HtmlLigne: 3
	 * HtmlColonne: 4
	 * Description.frFR: L'année scolaire du bloc scolaire. 
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
	 * Description.frFR: La saison scolaire du bloc scolaire. 
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
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKey
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlColonne: 4
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
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlColonne: 4
	 * NomAffichage.frFR: inscriptions
	 * NomAffichage.enUS: enrollments
	 */
	protected void _inscriptionCles(List<Long> o) {}

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
	 * Var.enUS: ageSearch
	 * r: saisonCles
	 * r.enUS: seasonKeys
	 * r: AgeScolaire
	 * r.enUS: SchoolAge
	 * Ignorer: true
	 */
	protected void _ageRecherche(ListeRecherche<AgeScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageCles_indexed_longs:" + pk);
		l.setC(AgeScolaire.class);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: age
	 * r: ageRecherche
	 * r.enUS: ageSearch
	 * Ignorer: true
	 */   
	protected void _age(Couverture<AgeScolaire> c) {
		if(ageRecherche.size() > 0) {
			c.o(ageRecherche.get(0));
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
	 * r: age
	 * r.enUS: age
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(age != null)
			c.o((String)age.getEcoleNomComplet());
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
	protected void _anneeDebut(Couverture<LocalDate> c) {
		if(age != null)
			c.o((LocalDate)age.getAnneeDebut());
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
	 * r: age
	 * r.enUS: age
	 */                      
	protected void _anneeFin(Couverture<LocalDate> c) {
		if(age != null)
			c.o(age.getAnneeFin());
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
	 * r: age
	 * r.enUS: age
	 */                  
	protected void _saisonJourDebut(Couverture<LocalDate> c) {
		if(age != null)
			c.o(age.getSaisonJourDebut());
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
		if(age != null)
			c.o(age.getSaisonEte());
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
		if(age != null)
			c.o(age.getSaisonHiver());
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
		if(age != null)
			c.o(age.getSaisonFraisInscription());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonNameComplete
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomComplet
	 * r.enUS: SeasonNameComplete
	 * r: age
	 * r.enUS: age
	 */
	protected void _saisonNomComplet(Couverture<String> c) {
		if(age != null)
			c.o(age.getSaisonNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageStartDay
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de la session
	 * NomAffichage.enUS: start of the session
	 * r: SessionJourDebut
	 * r.enUS: SessionStartDay
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _sessionJourDebut(Couverture<LocalDate> c) {
		if(age != null)
			c.o((LocalDate)age.getSessionJourDebut());
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
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _sessionJourFin(Couverture<LocalDate> c) {
		if(age != null)
			c.o((LocalDate)age.getSessionJourFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionNameComplete
	 * Indexe: true
	 * Stocke: true
	 * r: SessionNomComplet
	 * r.enUS: SessionNameComplete
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _sessionNomComplet(Couverture<String> c) {
		if(age != null)
			c.o(age.getSessionNomComplet());
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
		if(age != null)
			c.o(age.getAgeDebut());
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
		if(age != null)
			c.o(age.getAgeFin());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: blocNameComplete
	 * Indexe: true
	 * Stocke: true
	 * VarTitre: true
	 * r: bloc %s - %s pendant %s. 
	 * r.enUS: block %s - %s during %s. 
	 * r: strAgeDebut
	 * r.enUS: strAgeStart
	 * r: strAgeFin
	 * r.enUS: strAgeEnd
	 * r: sessionNomComplet
	 * r.enUS: sessionNameComplete
	 */
	protected void _blocNomComplet(Couverture<String> c) {
		String o;
		o = String.format("âges %s - %s pendant %s. ", strAgeDebut(), strAgeFin(), sessionNomComplet);
		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: blocId
	 * Indexe: true
	 * Stocke: true
	 * VarId: true
	 * HtmlLigne: 1
	 * HtmlColonne: 4
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: ID
	 * NomAffichage.enUS: ID
	 * r: blocNomComplet
	 * r.enUS: blocNameComplete
	 */            
	protected void _blocId(Couverture<String> c) {
		if(blocNomComplet != null) {
			String s = Normalizer.normalize(blocNomComplet, Normalizer.Form.NFD);
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
	 * r: blocId
	 * r.enUS: blocId
	 * r: /frFR/bloc/
	 * r.enUS: /enUS/bloc/
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * **/   
	protected void _pageUrl(Couverture<String> c)  {
		if(blocId != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/frFR/bloc/" + blocId;
			c.o(o);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * r: blocNomComplet
	 * r.enUS: blocNameComplete
	 */         
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(blocNomComplet);
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
		l.add(BlocScolaire.class.getCanonicalName());
		super._classeNomsCanoniques(l);
	}
}
