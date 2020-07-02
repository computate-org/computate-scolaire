package org.computate.scolaire.frFR.annee;                                   

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.year.SchoolYear
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Année
 * ApiUri.frFR: /api/annee
 * 
 * ApiTag.enUS: Year
 * ApiUri.enUS: /api/year
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
 * PagePageRecherche.frFR: AnneePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /annee
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: YearPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /year
 * 
 * UnNom.frFR: une année
 * UnNom.enUS: a year
 * Couleur: orange
 * IconeGroupe: regular
 * IconeNom: calendar-check
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/   
public class AnneeScolaire extends AnneeScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: Ecole.anneeCles
	 * HtmlLigne: 4
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: école
	 * NomAffichage.enUS: school
	 */              
	protected void _ecoleCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'année dans la base de données. 
	 * Description.enUS: The primary key of the year in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */           
	protected void _anneeCle(Couverture<Long> c) {
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
	 * Var.enUS: seasonKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: SaisonScolaire.anneeCle
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: saisons
	 * NomAffichage.enUS: seasons
	 */    
	protected void _saisonCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: AgeScolaire.anneeCle
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
		c.o(2);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(2);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _anneeTri(Couverture<Integer> c) {
		c.o(2);
	}

	/**
	 * Var.enUS: schoolSearch
	 * r: anneeCles
	 * r.enUS: yearKeys
	 * r: Ecole
	 * r.enUS: School
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */  
	protected void _ecoleRecherche(ListeRecherche<Ecole> l) {
		l.setQuery("*:*");
		l.addFilterQuery("anneeCles_indexed_longs:" + pk);
		l.setC(Ecole.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: school_
	 * r: ecoleRecherche
	 * r.enUS: schoolSearch
	 * Ignorer: true
	 */    
	protected void _ecole_(Couverture<Ecole> c) {
		if(ecoleRecherche.size() > 0) {
			c.o(ecoleRecherche.get(0));
		}
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
	 * r: ecole
	 * r.enUS: school
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(ecole_ != null)
			c.o(ecole_.getEcoleNom());
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
	 * r: ecole
	 * r.enUS: school
	 */  
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleNomComplet());
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
	 * r: ecole
	 * r.enUS: school
	 */               
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleEmplacement());
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
	 * r: ecole
	 * r.enUS: school
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleAddresse());
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
	 * r: ecole
	 * r.enUS: school
	 */
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleNumeroTelephone());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolNumber
	 * Indexe: true
	 * Stocke: true
	 * r: EcoleNumero
	 * r.enUS: SchoolNumber
	 * r: ecole
	 * r.enUS: school
	 */
	protected void _ecoleNumero(Couverture<Integer> c) {
		if(ecole_ != null)
			c.o((Integer)ecole_.getEcoleNumero());
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
	 * r: ecole
	 * r.enUS: school
	 */  
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
		if(ecole_ != null)
			c.o((String)ecole_.getEcoleAdministrateurNom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentFormKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: FormInscription.anneeCle
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: formulaire d'inscription
	 * NomAffichage.enUS: enrollment form
	 */               
	protected void _formInscriptionCle(Couverture<Long> c) {
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
	protected void _sessionDateDebut(Couverture<LocalDate> c) {}

	/**
	 * Var.enUS: setSessionStartDate
	 * r: SessionDateDebut
	 * r.enUS: SessionStartDate
	 */
	@Override public AnneeScolaire setSessionDateDebut(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionDateDebut(o);
		} catch (Exception e) {
			setSessionDateDebut(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
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
	protected void _sessionDateFin(Couverture<LocalDate> c) {}

	/**
	 * Var.enUS: setSessionEndDate
	 * r: SessionDateFin
	 * r.enUS: SessionEndDate
	 */
	@Override public AnneeScolaire setSessionDateFin(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionDateFin(o);
		} catch (Exception e) {
			setSessionDateFin(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearStart
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 1
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of year
	 * r: sessionDateDebut
	 * r.enUS: sessionStartDate
	 */                   
	protected void _anneeDebut(Couverture<Integer> c) {
		if(sessionDateDebut != null)
			c.o(sessionDateDebut.getYear());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnd
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 2
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: sessionDateFin
	 * r.enUS: sessionEndDate
	 */                       
	protected void _anneeFin(Couverture<Integer> c) {
		if(sessionDateFin != null)
			c.o(sessionDateFin.getYear());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnrollmentFee
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 3
	 * NomAffichage.frFR: frais d'inscription
	 * NomAffichage.enUS: enrollment fee
	 */         
	protected void _anneeFraisInscription(Couverture<BigDecimal> c) {}

	/**
	 * Var.enUS: yearYears
	 */ 
	protected void _anneesAnnee(List<AnneeScolaire> l) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearShortName
	 * Indexe: true
	 * Stocke: true
	 * r: anneeDebut
	 * r.enUS: yearStart
	 * r: anneeFin
	 * r.enUS: yearEnd
	 * r: FRANCE
	 * r.enUS: US
	 * r: "année %d"
	 * r.enUS: "%d year"
	 * r: "année"
	 * r.enUS: "year"
	 */                               
	protected void _anneeNomCourt(Couverture<String> c) {
		String o = "année";

		if(anneeDebut != null && anneeFin != null)
			o = String.format("%d-%d", anneeDebut, anneeFin);
		else if(anneeDebut != null)
			o = String.format("année %d", anneeDebut);
		else if(anneeFin != null)
			o = String.format("année %d", anneeFin);

		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: yearCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * r: anneeDebut
	 * r.enUS: yearStart
	 * r: anneeFin
	 * r.enUS: yearEnd
	 * r: FRANCE
	 * r.enUS: US
	 * r: "année scolaire %d-%d"
	 * r.enUS: "%d-%d school year"
	 * r: "année scolaire %d"
	 * r.enUS: "%d school year"
	 * r: " à %s"
	 * r.enUS: " at %s"
	 * r: EcoleNom
	 * r.enUS: SchoolName
	 * r: "année"
	 * r.enUS: "year"
	 * r: ecoleNomComplet
	 * r.enUS: schoolCompleteName
	 */                 
	protected void _anneeNomComplet(Couverture<String> c) {
		String o = "année";

		if(anneeDebut != null && anneeFin != null)
			o = String.format("année scolaire %d-%d", anneeDebut, anneeFin);
		else if(anneeDebut != null)
			o = String.format("année scolaire %d", anneeDebut);
		else if(anneeFin != null)
			o = String.format("année scolaire %d", anneeFin);

		if(ecoleNomComplet != null)
			o += String.format(" à %s", ecoleNomComplet);

		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: anneeNomComplet
	 * r.enUS: yearCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(anneeNomComplet);
	}
}
