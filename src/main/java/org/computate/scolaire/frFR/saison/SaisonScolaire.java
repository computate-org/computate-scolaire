package org.computate.scolaire.frFR.saison;                 

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.session.SessionScolaire;

/**   
 * NomCanonique.enUS: org.computate.scolaire.enUS.season.SchoolSeason
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Saison
 * ApiUri.frFR: /api/saison
 * 
 * ApiTag.enUS: Season
 * ApiUri.enUS: /api/season
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
 * PagePageRecherche.frFR: SaisonPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /saison
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: SeasonPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /season
 * 
 * UnNom.frFR:une saison
 * UnNom.enUS:a season
 * Adjectif.frFR:scolaire
 * Adjectif.enUS:school
 * Couleur: yellow
 * IconeGroupe: regular
 * IconeNom: sun
 * 
 * Role.frFR: SiteManager
 * Role.enUS: SiteManager
*/                             
public class SaisonScolaire extends SaisonScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la saison dans la base de données. 
	 * Description.enUS: The primary key of the season in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */          
	protected void _saisonCle(Couverture<Long> c) {
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
	 * Var.enUS: yearKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: AnneeScolaire.saisonCles
	 * HtmlLigne: 4
	 * HtmlCelulle: 1
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: année
	 * NomAffichage.enUS: year
	*/            
	protected void _anneeCle(Couverture<Long> c) {
	}

	/** 
	 * {@inheritDoc}
	 * Var.enUS: sessionKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: SessionScolaire.saisonCle
	 * HtmlLigne: 4
	 * HtmlCelulle: 2
	 * Description.frFR: Les sessions scolaires de la saison scolaire. 
	 * Description.enUS: The school sessions of the school season. 
	 * NomAffichage.frFR: sessions
	 * NomAffichage.enUS: sessions
	 */   
	protected void _sessionCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: educationSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _scolaireTri(Couverture<Integer> c) {
		c.o(3);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(3);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _anneeTri(Couverture<Integer> c) {
		c.o(3);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _saisonTri(Couverture<Integer> c) {
		c.o(3);
	}

	/**
	 * Var.enUS: yearSearch
	 * r: saisonCles
	 * r.enUS: seasonKeys
	 * r: AnneeScolaire
	 * r.enUS: SchoolYear
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */                 
	protected void _anneeRecherche(ListeRecherche<AnneeScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("saisonCles_indexed_longs:" + pk);
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
			c.o(annee_.getEcoleNomComplet());
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
	 * Var.enUS: seasonStartDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 1
	 * NomAffichage.frFR: début de la saison
	 * NomAffichage.enUS: start of the season
	 */                  
	protected void _saisonDateDebut(Couverture<LocalDate> c) {}

	/**
	 * Var.enUS: setSeasonStartDate
	 * r: SaisonDateDebut
	 * r.enUS: SeasonStartDate
	 */
	@Override public SaisonScolaire setSaisonDateDebut(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSaisonDateDebut(o);
		} catch (Exception e) {
			setSaisonDateDebut(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonSummer
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * NomAffichage.frFR: été
	 * NomAffichage.enUS: summer
	 */                   
	protected void _saisonEte(Couverture<Boolean> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonWinter
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * NomAffichage.frFR: hiver
	 * NomAffichage.enUS: winter
	 */                   
	protected void _saisonHiver(Couverture<Boolean> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonFuture
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 2
	 * NomAffichage.frFR: saison future
	 * NomAffichage.enUS: future season
	 */                 
	protected void _saisonFuture(Couverture<Boolean> c) {}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonShortName
	 * Indexe: true
	 * Stocke: true
	 * r: saisonEte
	 * r.enUS: seasonSummer
	 * r: saisonHiver
	 * r.enUS: seasonWinter
	 * r: "Classes de la saison d'été (frais d'inscription singulaire $%s)"
	 * r.enUS: "Summer season classes (one time registration fee $%s)"
	 * r: "Classes de la saison scolaire (frais d'inscription singulaire $%s)"
	 * r.enUS: "Regular school year classes (one time registration fee $%s)"
	 * r: "Classes supplimentaires pendant l'année scolaire %s-%s à %s"
	 * r.enUS: "Additional classes coming during the %s-%s school year at %s"
	 * r: "%s saison à %s"
	 * r.enUS: "%s season at %s"
	 * r: strSaisonDateDebut
	 * r.enUS: strSeasonStartDate
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 * r: ecoleNom
	 * r.enUS: schoolName
	 * r: anneeDebut
	 * r.enUS: yearStart
	 * r: anneeFin
	 * r.enUS: yearEnd
	 * r: saisonFuture
	 * r.enUS: seasonFuture
	 * r: AnneeFraisInscription
	 * r.enUS: YearEnrollmentFee
	 */           
	protected void _saisonNomCourt(Couverture<String> c) {
		String o;
		
		if(BooleanUtils.isTrue(saisonFuture))
			o = String.format("Classes supplimentaires pendant l'année scolaire %s-%s à %s", anneeDebut, anneeFin, ecoleNom);
		else if(BooleanUtils.isTrue(saisonEte))
			o = String.format("Classes de la saison d'été (frais d'inscription singulaire $%s)", strAnneeFraisInscription());
		else if(BooleanUtils.isTrue(saisonHiver))
			o = String.format("Classes de la saison scolaire (frais d'inscription singulaire $%s)", strAnneeFraisInscription());
		else
			o = String.format("%s saison à %s", strSaisonDateDebut(), ecoleNomComplet);
		
		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: seasonCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: saisonEte
	 * r.enUS: seasonSummer
	 * r: saisonHiver
	 * r.enUS: seasonWinter
	 * r: "%s saison d'été à %s"
	 * r.enUS: "%s summer season at %s"
	 * r: "%s-%s saison scolaire à %s"
	 * r.enUS: "%s-%s school season at %s"
	 * r: "%s saison à %s"
	 * r.enUS: "%s season at %s"
	 * r: "Classes supplimentaires pendant l'année scolaire %s-%s à %s"
	 * r.enUS: "Additional classes coming during the %s-%s school year at %s"
	 * r: saisonFuture
	 * r.enUS: seasonFuture
	 * r: strSaisonDateDebut
	 * r.enUS: strSeasonStartDate
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 * r: anneeDebut
	 * r.enUS: yearStart
	 * r: anneeFin
	 * r.enUS: yearEnd
	 */              
	protected void _saisonNomComplet(Couverture<String> c) {
		String o;
		
		if(BooleanUtils.isTrue(saisonFuture))
			o = String.format("Classes supplimentaires pendant l'année scolaire %s-%s à %s", anneeDebut, anneeFin, ecoleNomComplet);
		else if(BooleanUtils.isTrue(saisonEte))
			o = String.format("%s saison d'été à %s", anneeFin, ecoleNomComplet);
		else if(BooleanUtils.isTrue(saisonHiver))
			o = String.format("%s-%s saison scolaire à %s", anneeDebut, anneeFin, ecoleNomComplet);
		else
			o = String.format("%s saison à %s", strSaisonDateDebut(), ecoleNomComplet);
		
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: saisonNomComplet
	 * r.enUS: seasonCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(saisonNomComplet);
	}

	/**
	 * Var.enUS: strYearEnrollmentFee
	 * r: anneeFraisInscription
	 * r.enUS: yearEnrollmentFee
	 */
	@Override public String strAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : anneeFraisInscription.setScale(0).toString();
	}
}
