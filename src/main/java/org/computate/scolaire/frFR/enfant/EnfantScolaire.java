package org.computate.scolaire.frFR.enfant;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.age.AgeScolaire;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.child.SchoolChild
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Enfant
 * ApiUri.frFR: /api/enfant
 * 
 * ApiTag.enUS: Child
 * ApiUri.enUS: /api/child
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: EnfantPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /enfant
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: ChildPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /child
 * 
 * UnNom.frFR: un enfant
 * UnNom.enUS: a child
 * Couleur: green
 * IconeGroupe: regular
 * IconeNom: child
*/    
public class EnfantScolaire extends EnfantScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: AgeScolaire.blocCles
	 * HtmlLigne: 5
	 * HtmlColonne: 1
	 * Description.frFR: La clé primaire du bloc dans la base de données. 
	 * Description.enUS: The primary key of the school block in the database. 
	 * NomAffichage.frFR: blocs
	 * NomAffichage.enUS: blocks
	 */               
	protected void _blocCles(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire du bloc dans la base de données. 
	 * Description.enUS: The primary key of the school block in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */               
	protected void _enfantCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKeys
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: inscriptions
	 * NomAffichage.enUS: enrollments
	 */               
	protected void _inscriptionCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familySort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _familleTri(Couverture<Integer> c) {
		c.o(1);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _enfantTri(Couverture<Integer> c) {
		c.o(1);
	}

	/**
	 * Var.enUS: blockSearch
	 * r: enfantCle
	 * r.enUS: childKey
	 * r: BlocScolaire
	 * r.enUS: SchoolBlock
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */
	protected void _blocRecherche(ListeRecherche<BlocScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enfantCle_indexed_long:" + pk);
		l.setC(BlocScolaire.class);
		l.setStocker(true);
		l.setFacet(true);
		l.addFacetField("ecoleCle_indexed_long");
		l.addFacetField("anneeCle_indexed_long");
		l.addFacetField("saisonCle_indexed_long");
		l.addFacetField("sessionCle_indexed_long");
		l.addFacetField("ageCle_indexed_long");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blocs
	 * r: blocRecherche
	 * r.enUS: blockSearch
	 * r: blocs
	 * r.enUS: blocks
	 * Ignorer: true
	 */   
	protected void _blocs(List<BlocScolaire> c) {
		blocs.addAll(blocRecherche.getList());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: écoles
	 * NomAffichage.enUS: schools
	 * r: ecoleCle
	 * r.enUS: schoolKey
	 */                  
	protected void _ecoleCles(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("ecoleCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/*
	 * {@inheritDoc}
	 * Var.enUS: yearKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire du bloc scolaire. 
	 * Description.enUS: The school year of the school block. 
	 * NomAffichage.frFR: années
	 * NomAffichage.enUS: years
	 * r: anneeCle
	 * r.enUS: yearKey
	 */          
	protected void _anneeCles(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("anneeCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La saison scolaire du bloc scolaire. 
	 * Description.enUS: The school season of the school block. 
	 * NomAffichage.frFR: saisons
	 * NomAffichage.enUS: seasons
	 * r: saisonCle
	 * r.enUS: seasonKey
	 */          
	protected void _saisonCles(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("saisonCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la session dans la base de données. 
	 * Description.enUS: The primary key of the school block in the database. 
	 * NomAffichage.frFR: sessions
	 * NomAffichage.enUS: sessions
	 * r: sessionCle
	 * r.enUS: sessionKey
	 */          
	protected void _sessionCles(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("sessionCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageKeys
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'âge dans la base de données. 
	 * Description.enUS: The primary key of the age in the database. 
	 * NomAffichage.frFR: âges
	 * NomAffichage.enUS: ages
	 * r: ageCle
	 * r.enUS: ageKey
	 */                  
	protected void _ageCles(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("ageCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personFirstName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prénom
	 * NomAffichage.enUS: first name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 */                   
	protected void _personnePrenom(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom de famille
	 * NomAffichage.enUS: last name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 */                   
	protected void _familleNom(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personCompleteName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom complèt
	 * NomAffichage.enUS: complete name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 * r: personnePrenom
	 * r.enUS: personFirstName
	 * r: familleNom
	 * r.enUS: familyName
	 */                   
	protected void _personneNomComplet(Couverture<String> c) {
		c.o(String.format("%s %s", personnePrenom, familleNom));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom complèt préferé
	 * NomAffichage.enUS: complete name preferred
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 * r: personnePrenom
	 * r.enUS: personFirstName
	 * r: familleNom
	 * r.enUS: familyName
	 */                   
	protected void _personneNomCompletPrefere(Couverture<String> c) {
		c.o(String.format("%s %s", personnePrenom, familleNom));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personFormalName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom formel
	 * NomAffichage.enUS: formal name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 * r: personnePrenom
	 * r.enUS: personFirstName
	 * r: familleNom
	 * r.enUS: familyName
	 */                   
	protected void _personneNomFormel(Couverture<String> c) {
		c.o(String.format("%s %s", personnePrenom, familleNom));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: personBirthdate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: date de naissance
	 * NomAffichage.enUS: birth date
	 */                   
	protected void _personneDateNaissance(Couverture<LocalDate> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childMedicalConditions
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: conditions médicales
	 * NomAffichage.enUS: medical conditions
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 */                   
	protected void _enfantConditionsMedicales(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childPreviousSchoolsAttended
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: écoles précedemment fréqentées
	 * NomAffichage.enUS: schools previously attended
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 2
	 */                   
	protected void _enfantEcolesPrecedemmentFrequentees(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childDescription
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: description
	 * NomAffichage.enUS: description
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 3
	 */                   
	protected void _enfantDescription(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childObjectives
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: objectifs
	 * NomAffichage.enUS: objectives
	 * Definir: true
	 */                   
	protected void _enfantObjectifs(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enfantVaccinsAJour
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: vaccins à jour
	 * NomAffichage.enUS: current vaccines
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlColonne: 2
	 */                   
	protected void _enfantVaccinsAJour(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childPottyTrained
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: propre
	 * NomAffichage.enUS: potty trained
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlColonne: 3
	 */                   
	protected void _enfantPropre(Couverture<Boolean> c) {
		c.o(false);
	}

	/**    
	 * {@inheritDoc}
	 * Var.enUS: blocNameComplete
	 * Indexe: true
	 * Stocke: true
	 * VarTitre: true
	 * r: %s - %s %s %s/mois %s
	 * r.enUS: %s - %s %s %s/month %s
	 * r: strBlocHeureDebut
	 * r.enUS: strBlockTimeStart
	 * r: strBlocHeureFin
	 * r.enUS: strBlockTimeEnd
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
	 * r.enUS: ageNameComplete
	 */  
	protected void _blocNomComplet(Couverture<String> c) {
		String o;
		String weekdays = "";
		if(blocLundi) weekdays += " Mo";
		if(blocMardi) weekdays += " Tu";
		if(blocMercredi) weekdays += " We";
		if(blocJeudi) weekdays += " Th";
		if(blocVendredi) weekdays += " Fr";
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
