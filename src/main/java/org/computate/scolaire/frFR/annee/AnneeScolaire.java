package org.computate.scolaire.frFR.annee;                                

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
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
 * ApiUri.frFR: /frFR/api/annee
 * 
 * ApiTag.enUS: School
 * ApiUri.enUS: /enUS/api/year
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: AnneePage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /frFR/annee
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: YearPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /enUS/year
 * 
 * UnNom.frFR: une année
 * UnNom.enUS: a year
 * Couleur: orange
 * IconeGroupe: duotone
 * IconeNom: calendar-check-o
*/                                                
public class AnneeScolaire extends AnneeScolaireGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: Ecole.anneeCles
	 * HtmlLigne: 3
	 * HtmlColonne: 3
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
	 */
	protected void _saisonCles(List<Long> o) {}

	/**
	 * Var.enUS: schoolSearch
	 * r: anneeCles
	 * r.enUS: yearKeys
	 * r: Ecole
	 * r.enUS: School
	 * Ignorer: true
	 */
	protected void _ecoleRecherche(ListeRecherche<Ecole> l) {
		l.setQuery("*:*");
		l.addFilterQuery("anneeCles_indexed_longs:" + pk);
		l.setC(Ecole.class);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolDocument
	 * r: ecoleRecherche
	 * r.enUS: schoolSearch
	 * Ignorer: true
	 */   
	protected void _ecoleDocument(Couverture<SolrDocument> c) {
		if(ecoleRecherche.getSolrDocumentList().size() > 0) {
			c.o(ecoleRecherche.getSolrDocumentList().get(0));
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
	 * r: ecoleNomComplet
	 * r.enUS: schoolNameComplete
	 * r: ecoleDocument
	 * r.enUS: schoolDocument
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(ecoleDocument != null)
			c.o((String)ecoleDocument.get("ecoleNomComplet_stored_string"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearStart
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 1
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of year
	 */                   
	protected void _anneeDebut(Couverture<LocalDate> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnd
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlColonne: 2
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: anneeDebut
	 * r.enUS: yearStart
	 */                      
	protected void _anneeFin(Couverture<LocalDate> c) {
		if(anneeDebut != null)
			c.o(anneeDebut.plusYears(1));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearNameShort
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
			o = String.format("%d-%d", anneeDebut.getYear(), anneeFin.getYear());
		else if(anneeDebut != null)
			o = String.format("année %d", anneeDebut.getYear());
		else if(anneeFin != null)
			o = String.format("année %d", anneeFin.getYear());

		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: yearNameComplete
	 * Indexe: true
	 * Stocke: true
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
	 * r.enUS: schoolNameComplete
	 */                
	protected void _anneeNomComplet(Couverture<String> c) {
		String o = "année";

		if(anneeDebut != null && anneeFin != null)
			o = String.format("année scolaire %d-%d", anneeDebut.getYear(), anneeFin.getYear());
		else if(anneeDebut != null)
			o = String.format("année scolaire %d", anneeDebut.getYear());
		else if(anneeFin != null)
			o = String.format("année scolaire %d", anneeFin.getYear());

		if(ecoleNomComplet != null)
			o += String.format(" à %s", ecoleNomComplet);

		c.o(o);
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: yearId
	 * Indexe: true
	 * Stocke: true
	 * VarId: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: anneeNomComplet
	 * r.enUS: yearNameComplete
	 */         
	protected void _anneeId(Couverture<String> c) {
		if(anneeNomComplet != null) {
			String s = Normalizer.normalize(anneeNomComplet, Normalizer.Form.NFD);
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
	 * r: anneeId
	 * r.enUS: yearId
	 * r: /frFR/annee/
	 * r.enUS: /enUS/year/
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * **/   
	protected void _pageUrl(Couverture<String> c)  {
		if(anneeId != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/frFR/annee/" + anneeId;
			c.o(o);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * r: anneeNomComplet
	 * r.enUS: yearNameComplete
	 */         
	protected void _objetSuggere(Couverture<String> c) { 
		c.o(anneeNomComplet);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _classCanonicalNames
	 * Indexe: true
	 * Stocke: true
	 * r: AnneeScolaire
	 * r.enUS: SchoolYear
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 **/      
	@Override protected void _classeNomsCanoniques(List<String> l) {
		l.add(AnneeScolaire.class.getCanonicalName());
		super._classeNomsCanoniques(l);
	}
}
