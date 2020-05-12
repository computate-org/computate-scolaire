package org.computate.scolaire.frFR.html.part;          

import java.util.List;
import java.util.stream.Collectors;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.design.DesignPage;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

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
 * Tri.asc: tri1
 * Tri.asc: tri2
 * Tri.asc: tri3
 * Tri.asc: tri4
 * Tri.asc: tri5
 * Tri.asc: tri6
 * Tri.asc: tri7
 * Tri.asc: tri8
 * Tri.asc: tri9
 * Tri.asc: tri10
 * Lignes: 300
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
 * 
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
	 * Var.enUS: pageDesignKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: DesignPage.partHtmlCles
	 * HtmlLigne: 3
	 * HtmlCelulle: 1
	 * NomAffichage.frFR: designs de page
	 * NomAffichage.enUS: page designs
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: DesignPage
	 * r.enUS: PageDesign
	 * r: designParentCles
	 * r.enUS: parentDesignKeys
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: Stocker
	 * r.enUS: Store
	 * r: designPageCles
	 * r.enUS: pageDesignKeys
	*/                                      
	protected void _designPageCles(List<Long> l) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlLink
	 * Indexe: true
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
	 * Indexe: true
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
	 * Indexe: true
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
	 * Indexe: true
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
	 * Indexe: true
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
	 * Multiligne: true
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
	 * Var.enUS: htmlAfter
	 * Stocke: true
	 * NomAffichage.frFR: HTML après
	 * NomAffichage.enUS: HTML after
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCelulle: 1
	 * Multiligne: true
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
	 * HtmlLigne: 7
	 * HtmlCelulle: 2
	 */             
	protected void _htmlTexte(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlVar
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: var
	 * NomAffichage.frFR: var
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCelulle: 1
	 */               
	protected void _htmlVar(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlVarSpan
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: var span
	 * NomAffichage.frFR: var span
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCelulle: 2
	 */               
	protected void _htmlVarSpan(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlVarForm
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: var form
	 * NomAffichage.frFR: var form
	 * Definir: true
	 * HtmlLigne: 9
	 * HtmlCelulle: 1
	 */            
	protected void _htmlVarForm(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlVarInput
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: var input
	 * NomAffichage.frFR: var input
	 * Definir: true
	 * HtmlLigne: 9
	 * HtmlCelulle: 2
	 */            
	protected void _htmlVarInput(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlVarForEach
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: var for each
	 * NomAffichage.frFR: var for each
	 * Definir: true
	 * HtmlLigne: 10
	 * HtmlCelulle: 1
	 */             
	protected void _htmlVarForEach(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: htmlExclude
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: HTML exclude
	 * NomAffichage.frFR: HTML exclure
	 * Definir: true
	 * HtmlLigne: 10
	 * HtmlCelulle: 2
	 */         
	protected void _htmlExclure(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: pdfExclude
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: PDF exclude
	 * NomAffichage.frFR: PDF exclure
	 * Definir: true
	 * HtmlLigne: 10
	 * HtmlCelulle: 3
	 */            
	protected void _pdfExclure(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: loginLogout
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: login/logout
	 * NomAffichage.frFR: se connecter / se deconnecter
	 * Definir: true
	 * HtmlLigne: 10
	 * HtmlCelulle: 4
	 */            
	protected void _connecterDeconnecter(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort1
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort1
	 * NomAffichage.frFR: tri1
	 * Definir: true
	 * HtmlLigne: 11
	 * HtmlCelulle: 1
	 */               
	protected void _tri1(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort2
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort2
	 * NomAffichage.frFR: tri2
	 * Definir: true
	 * HtmlLigne: 11
	 * HtmlCelulle: 2
	 */               
	protected void _tri2(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort3
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort3
	 * NomAffichage.frFR: tri3
	 * Definir: true
	 * HtmlLigne: 11
	 * HtmlCelulle: 3
	 */               
	protected void _tri3(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort4
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort4
	 * NomAffichage.frFR: tri4
	 * Definir: true
	 * HtmlLigne: 11
	 * HtmlCelulle: 4
	 */               
	protected void _tri4(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort5
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort5
	 * NomAffichage.frFR: tri5
	 * Definir: true
	 * HtmlLigne: 11
	 * HtmlCelulle: 5
	 */               
	protected void _tri5(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort6
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort6
	 * NomAffichage.frFR: tri6
	 * Definir: true
	 * HtmlLigne: 12
	 * HtmlCelulle: 6
	 */              
	protected void _tri6(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort7
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort7
	 * NomAffichage.frFR: tri7
	 * Definir: true
	 * HtmlLigne: 12
	 * HtmlCelulle: 7
	 */               
	protected void _tri7(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort8
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort8
	 * NomAffichage.frFR: tri8
	 * Definir: true
	 * HtmlLigne: 12
	 * HtmlCelulle: 8
	 */               
	protected void _tri8(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort9
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort9
	 * NomAffichage.frFR: tri9
	 * Definir: true
	 * HtmlLigne: 12
	 * HtmlCelulle: 9
	 */               
	protected void _tri9(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sort10
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: sort10
	 * NomAffichage.frFR: tri10
	 * Definir: true
	 * HtmlLigne: 12
	 * HtmlCelulle: 10
	 */              
	protected void _tri10(Couverture<Double> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: inscriptionNomComplet
	 * r.enUS: enrollmentCompleteName
	 * r: htmlAvant
	 * r.enUS: htmlBefore
	 * r: htmlTexte
	 * r.enUS: htmlText
	 * r: htmlApres
	 * r.enUS: htmlAfter
	 * r: connecterDeconnecter
	 * r.enUS: loginLogout
	 * r: deconnecter
	 * r.enUS: logout
	 * r: connecter
	 * r.enUS: login
	 */ 
	@Override
	protected void _objetTitre(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		if(htmlAvant != null)
			b.append(htmlAvant);

		if(htmlVarForEach != null)
			b.append("for each {").append(htmlVarForEach).append("}").append(" into {").append(htmlVar).append("}");
		else {
			if(htmlVarSpan != null)
				b.append("<span>").append(htmlVarSpan).append("</span>");
			else if(htmlVar != null)
				b.append("{").append(htmlVar).append("}");
		}

		if(htmlVarInput != null)
			b.append("[").append(htmlVarInput).append("]");
		if(htmlVarForm != null)
			b.append("[[").append(htmlVarForm).append("]]");
		if(connecterDeconnecter)
			b.append("[ connecter ] / [ deconnecter ]");
		if(htmlTexte != null)
			b.append(htmlTexte);
		if(htmlApres != null)
			b.append(htmlApres);
		if(b.length() == 0)
			b.append(pk);
		c.o(b.toString());
	}

	@Override
	/**
	 * Var.enUS: _objectId
	 */   
	protected void _objetId(Couverture<String> c) {
		if(pk != null){
			c.o(pk.toString());
		}
	}
}
