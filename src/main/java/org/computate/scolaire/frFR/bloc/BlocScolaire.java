package org.computate.scolaire.frFR.bloc;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
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
 * IconeGroupe: duotone
 * IconeNom: bell-o
*/    
public class BlocScolaire extends BlocScolaireGen<Cluster> {

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
	 * Attribuer: AgeScolaire.blocCles
	 * HtmlLigne: 5
	 * HtmlCellule: 1
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
	 * Var.enUS: seasonCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: SaisonNomComplet
	 * r.enUS: SeasonCompleteName
	 * r: age
	 * r.enUS: age
	 */
	protected void _saisonNomComplet(Couverture<String> c) {
		if(age != null)
			c.o(age.getSaisonNomComplet());
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
	 * Var.enUS: ageCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: AgeNomComplet
	 * r.enUS: AgeCompleteName
	 * r: age
	 * r.enUS: age
	 */                   
	protected void _ageNomComplet(Couverture<String> c) {
		if(age != null)
			c.o(age.getAgeNomComplet());
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
	 * Var.enUS: blocCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarTitre: true
	 * HtmlColonne: 1
	 * r: %s - %s %s %s/mois %s
	 * r.enUS: %s - %s %s %s/month %s
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
			o = String.format("%s - %s %s %s/mois %s", strBlocHeureDebut(), strBlocHeureFin(), weekdays, strBlocPrixParMois(), ageNomComplet);
		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: blocId
	 * Indexe: true
	 * Stocke: true
	 * VarId: true
	 * HtmlLigne: 1
	 * HtmlCellule: 4
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: ID
	 * NomAffichage.enUS: ID
	 * r: blocNomComplet
	 * r.enUS: blocCompleteName
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
	 * r: /bloc/
	 * r.enUS: /block/
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * **/   
	protected void _pageUrl(Couverture<String> c)  {
		if(blocId != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/bloc/" + blocId;
			c.o(o);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * r: blocNomComplet
	 * r.enUS: blocCompleteName
	 */         
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(blocNomComplet);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _classCanonicalNames
	 * Indexe: true
	 * Stocke: true
	 * r: BlocScolaire
	 * r.enUS: SchoolBlock
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 **/      
	@Override protected void _classeNomsCanoniques(List<String> l) {
		l.add(BlocScolaire.class.getCanonicalName());
		super._classeNomsCanoniques(l);
	}
}
