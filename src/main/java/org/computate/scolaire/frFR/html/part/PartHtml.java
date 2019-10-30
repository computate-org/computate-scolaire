package org.computate.scolaire.frFR.html.part;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;

/**   
 * NomCanonique.enUS: org.computate.scolaire.enUS.html.part.HtmlPart
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Part de Html
 * ApiUri.frFR: /api/part-html
 * 
 * ApiTag.enUS: Html Part
 * ApiUri.enUS: /api/html-part
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: PartHtmlPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /part-html
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: HtmlPartPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /html-part
 * 
 * UnNom.frFR:un part de HTML
 * UnNom.enUS:an HTML part
 * Couleur: yellow
 * IconeGroupe: regular
 * IconeNom: sun
 * NomVar.frFR: part-html
 * NomVar.enUS: html-part
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/                         
public class PartHtml extends PartHtmlGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlPartKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la saison dans la base de données. 
	 * Description.enUS: The primary key of the season in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */          
	protected void _partHtmlCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDesignKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: DesignInscription.partHtmlCles
	 * HtmlLigne: 3
	 * HtmlCelulle: 1
	 * NomAffichage.frFR: design d'inscription
	 * NomAffichage.enUS: enrollment design
	*/           
	protected void _designInscriptionCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlLink
	 * Stocke: true
	 * NomAffichage.enUS: link
	 * NomAffichage.frFR: lien
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 3
	 */               
	protected void _htmlLien(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlElement
	 * Stocke: true
	  NomAffichage.enUS: HTML element
	 * NomAffichage.frFR: HTML élément
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCelulle: 1
	 * r: htmlLien
	 * r.enUS: htmlLink
	 */               
	protected void _htmlElement(Couverture<String> c) {
		if(htmlLien != null)
			c.o("a");
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlId
	 * Stocke: true
	  NomAffichage.enUS: HTML ID
	 * NomAffichage.frFR: HTML ID
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCelulle: 2
	 */               
	protected void _htmlId(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlClasses
	 * Stocke: true
	  NomAffichage.enUS: HTML classes
	 * NomAffichage.frFR: HTML classes
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCelulle: 3
	 */               
	protected void _htmlClasses(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlStyle
	 * Stocke: true
	  NomAffichage.enUS: HTML style
	 * NomAffichage.frFR: HTML style
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCelulle: 4
	 */              
	protected void _htmlStyle(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlBefore
	 * Stocke: true
	 * NomAffichage.frFR: HTML avant
	 * NomAffichage.enUS: HTML before
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCelulle: 1
	 */               
	protected void _htmlAvant(Couverture<String> c) {
		if(htmlElement != null) {
			String id = htmlId == null ? "" : String.format(" id=\"%s\"", htmlId);
			String classes = htmlClasses == null ? "" : String.format(" class=\"%s\"", htmlClasses);
			String style = htmlStyle == null ? "" : String.format(" style=\"%s\"", htmlStyle);
			c.o(String.format("<%s%s%s%s>", htmlElement, id, classes, style));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlVar
	 * Stocke: true
	 * NomAffichage.enUS: var
	 * NomAffichage.frFR: var
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCelulle: 2
	 */               
	protected void _htmlVar(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlAfter
	 * Stocke: true
	 * NomAffichage.frFR: HTML après
	 * NomAffichage.enUS: HTML after
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCelulle: 3
	 */               
	protected void _htmlApres(Couverture<String> c) {
		if(htmlElement != null) {
			c.o(String.format("<%s>", htmlElement));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlText
	 * Stocke: true
	 * NomAffichage.frFR: texte
	 * NomAffichage.enUS: text
	 * Definir: true
	 * Multiligne: true
	 * HtmlLigne: 6
	 * HtmlCelulle: 1
	 */               
	protected void _htmlTexte(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort1
	 * Stocke: true
	 * NomAffichage.enUS: sort1
	 * NomAffichage.frFR: tri1
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCelulle: 1
	 */               
	protected void _tri1(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort2
	 * Stocke: true
	 * NomAffichage.enUS: sort2
	 * NomAffichage.frFR: tri2
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCelulle: 2
	 */               
	protected void _tri2(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort3
	 * Stocke: true
	 * NomAffichage.enUS: sort3
	 * NomAffichage.frFR: tri3
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCelulle: 3
	 */               
	protected void _tri3(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort4
	 * Stocke: true
	 * NomAffichage.enUS: sort4
	 * NomAffichage.frFR: tri4
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCelulle: 4
	 */               
	protected void _tri4(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort5
	 * Stocke: true
	 * NomAffichage.enUS: sort5
	 * NomAffichage.frFR: tri5
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCelulle: 5
	 */               
	protected void _tri5(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort6
	 * Stocke: true
	 * NomAffichage.enUS: sort6
	 * NomAffichage.frFR: tri6
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCelulle: 6
	 */               
	protected void _tri6(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort7
	 * Stocke: true
	 * NomAffichage.enUS: sort7
	 * NomAffichage.frFR: tri7
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCelulle: 7
	 */               
	protected void _tri7(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort8
	 * Stocke: true
	 * NomAffichage.enUS: sort8
	 * NomAffichage.frFR: tri8
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCelulle: 8
	 */               
	protected void _tri8(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort9
	 * Stocke: true
	 * NomAffichage.enUS: sort9
	 * NomAffichage.frFR: tri9
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCelulle: 9
	 */               
	protected void _tri9(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort10
	 * Stocke: true
	 * NomAffichage.enUS: sort10
	 * NomAffichage.frFR: tri10
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCelulle: 10
	 */               
	protected void _tri10(Couverture<Double> c) {
	}
}
