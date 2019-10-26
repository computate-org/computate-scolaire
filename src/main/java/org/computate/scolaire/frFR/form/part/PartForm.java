package org.computate.scolaire.frFR.form.part;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;

/**   
 * NomCanonique.enUS: org.computate.scolaire.enUS.form.part.FormPart
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Part de Formulaire
 * ApiUri.frFR: /api/part-form
 * 
 * ApiTag.enUS: Form Part
 * ApiUri.enUS: /api/form-part
 * 
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: PartFormPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /part-form
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: FormPartPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /form-part
 * 
 * UnNom.frFR:un part de formulaire
 * UnNom.enUS:a form part
 * Couleur: yellow
 * IconeGroupe: regular
 * IconeNom: sun
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
*/                              
public class PartForm extends PartFormGen<Cluster> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: formPartKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de la saison dans la base de données. 
	 * Description.enUS: The primary key of the season in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */          
	protected void _partFormCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentFormKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: FormInscription.partFormCles
	 * HtmlLigne: 3
	 * HtmlCelulle: 1
	 * NomAffichage.frFR: formulaire d'inscription
	 * NomAffichage.enUS: enrollment form
	*/             
	protected void _formInscriptionCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlOrder
	 * Stocke: true
	 * NomAffichage.enUS: ordre
	 * NomAffichage.frFR: order
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCelulle: 2
	 */               
	protected void _htmlOrdre(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlLink
	 * Stocke: true
	 * NomAffichage.enUS: HTML après
	 * NomAffichage.frFR: HTML after
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
	  NomAffichage.enUS: HTML element
	 * NomAffichage.frFR: HTML élément
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
	 * NomAffichage.enUS: HTML avant
	 * NomAffichage.frFR: HTML before
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
	 * Var.enUS: htmlText
	 * Stocke: true
	 * NomAffichage.enUS: texte
	 * NomAffichage.frFR: text
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCelulle: 2
	 */               
	protected void _htmlTexte(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlAfter
	 * Stocke: true
	 * NomAffichage.enUS: HTML après
	 * NomAffichage.frFR: HTML after
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCelulle: 3
	 */               
	protected void _htmlApres(Couverture<String> c) {
		if(htmlElement != null) {
			c.o(String.format("<%s>", htmlElement));
		}
	}
}
