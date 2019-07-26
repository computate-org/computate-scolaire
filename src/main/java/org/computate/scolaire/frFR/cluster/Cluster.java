package org.computate.scolaire.frFR.cluster;            

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.page.part.PagePart;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.xml.OutilXml;



/**       
 * NomCanonique.enUS: org.computate.scolaire.enUS.cluster.Cluster
 * Api: true
 * ApiUri: /api/cluster
 * Role: SiteAdmin
 * ApiMethode: RechercheFrFRPage
 * ApiMethode: RechercheEnUSPage
 * ApiMethode: Recherche
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiUriRechercheFrFRPage: /frFR/cluster
 * ApiUriRechercheEnUSPage: /enUS/cluster
 * PageRechercheFrFRPage: ClusterFrFRPage
 * PageRechercheEnUSPage: ClusterEnUSPage
 * MotCle: classeNomSimpleCluster
 * Modele: true
 */      
public class Cluster extends ClusterGen<Object> {   

	/**
	 * Var.enUS: siteRequest_
	 * {@inheritDoc}
	 */         
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {}

	protected void _pageParts(List<PagePart> l) {
	}

	/** 
	 * Var.enUS: beforePagePart
	 */  
	public void avantPagePart(PagePart o, String var) {
	}  

	/** 
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * ClePrimaire: true
	 * Description.frFR: La clé primaire dans la base de données. 
	 * Description.enUS: The primary key in the database. 
	 * NomAffichage.frFR: clé primaire
	 * NomAffichage.enUS: primary key
	 */                                        
	protected void _pk(Couverture<Long> c) {}

	/**
	 * {@inheritDoc}
	 * CleUnique: true
	 */
	protected void _id(Couverture<String> c) {
		if(pk != null)
			c.o(pk.toString());
	} 

	/**
	 * {@inheritDoc}
	 * Var.enUS: created
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Modifier: false
	 * HtmlLigne: 1
	 * Description.frFR: La date et l'heure créées. 
	 * Description.enUS: The date and time created. 
	 * NomAffichage.frFR: crée
	 * NomAffichage.enUS: created
	 */    
	protected void _cree(Couverture<ZonedDateTime> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: modified
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Modifier: false
	 * HtmlLigne: 1
	 * Description.frFR: La date et l'heure modifiéés. 
	 * Description.enUS: The date and time modified. 
	 * NomAffichage.frFR: modifié
	 * NomAffichage.enUS: modified
	 */
	protected void _modifie(Couverture<ZonedDateTime> c) {}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: archived
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: archivé. 
	 * Description.enUS: archived. 
	 * NomAffichage.frFR: archivé
	 * NomAffichage.enUS: archived
	 */
	protected void _archive(Couverture<Boolean> c) {
		c.o(false);
	}          
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: deleted
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: supprimé. 
	 * Description.enUS: deleted. 
	 * NomAffichage.frFR: supprimé
	 * NomAffichage.enUS: deleted
	 */    
	protected void _supprime(Couverture<Boolean> c) {
		c.o(false);
	}

	/**  
	 * Var.enUS: classCanonicalName
	 * Indexe: true
	 * Stocke: true
	 */       
	protected void _classeNomCanonique(Couverture<String> c) {
		String o = getClass().getCanonicalName();
		c.o(o);
	}

	/**
	 * Var.enUS: classSimpleName
	 * Indexe: true
	 * Stocke: true
	 */              
	protected void _classeNomSimple(Couverture<String> c) {
		String o = getClass().getSimpleName();
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: classCanonicalNames
	 * Indexe: true
	 * Stocke: true
	 **/      
	protected void _classeNomsCanoniques(List<String> l) { 
		l.add(Cluster.class.getCanonicalName());
	}

	/**
	 * Param1.var.enUS: localName
	 * r: nomLocal
	 * r.enUS: localName
	 * r: xmlPile
	 * r.enUS: xmlStack
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: tabulation
	 * r.enUS: tab
	 * r: Echappes
	 * r.enUS: Escaped
	 * r: ecrivain
	 * r.enUS: writer
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: getXmlPile
	 * r.enUS: getXmlStack
	 */
	public Cluster e(String nomLocal) {
		ToutEcrivain w = requeteSite_.getW();
		String nomLocalParent = requeteSite_.getXmlPile().isEmpty() ? null : requeteSite_.getXmlPile().peek();

		boolean eNoWrapParent = nomLocalParent == null || MiseEnPage.HTML_ELEMENTS_NO_WRAP.contains(nomLocalParent);
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\t"));
		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\\t"));

		requeteSite_.getXmlPile().push(nomLocal);
		if(StringUtils.equals(nomLocal, "html"))
			w.s("<!DOCTYPE html>\n");
		if(!eNoWrapParent && !tabulationsEchappes.isEmpty()) {
			w.l();
			w.s(tabulations);
		}
		w.s("<");
		w.s(nomLocal);
		
		return this;
	}

	/**
	 * Param1.var.enUS: attributeName
	 * Param2.var.enUS: objects
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: OutilXml
	 * r.enUS: UtilXml
	 * r: echapperDansCitatations
	 * r.enUS: escapeInQuotes
	 * r: nomAttribut
	 * r.enUS: attributeName
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster a1(String nomAttribut, Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		w.s(" ");
		w.s(nomAttribut);
		w.s("=\"");
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapperDansCitatations(s));
			}
		}
		
		return this;
	}

	/**  
	 * Param1.var.enUS: attributeName
	 * Param2.var.enUS: objects
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: OutilXml
	 * r.enUS: UtilXml
	 * r: objet
	 * r.enUS: object
	 * r: nomAttribut
	 * r.enUS: attributeName
	 * r: echapperDansCitatations
	 * r.enUS: escapeInQuotes
	 */
	public Cluster a(String nomAttribut, Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		w.s(" ");
		w.s(nomAttribut);
		w.s("=\"");
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapperDansCitatations(s));
			}
		}
		w.s("\"");
		
		return this;
	}

	/**  
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 */
	public Cluster a2() {
		ToutEcrivain w = requeteSite_.getW();
		w.s("\"");
		
		return this;
	}

	/** 
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 */
	public Cluster f() {
		ToutEcrivain w = requeteSite_.getW();
		w.s(">");
		
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster s(Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(s);
			}
		}
		
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		s("\n");
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster l(Object...objets) {
		s(objets);
		s("\n");
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster lx(Object...objets) {
		s(objets);
		sx("\n");
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: objet
	 * r.enUS: object
	 * r: OutilXml
	 * r.enUS: UtilXml
	 * r: echapper
	 * r.enUS: escape
	 */
	public Cluster sx(Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapper(s));
			}
		}
		
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster tx(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("\t");
		sx(objets);
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public Cluster tlx(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("\t");
		sx(objets);
		sx("\n");
		return this;
	}

	/**
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: getXmlPile
	 * r.enUS: getXmlStack
	 */
	public Cluster fg() {
		ToutEcrivain w = requeteSite_.getW();
		w.s("/>");
		requeteSite_.getXmlPile().pop();
		
		return this;
	}

	/**     
	 * Param1.var.enUS: localName
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: getXmlPile
	 * r.enUS: getXmlStack
	 * r: nomLocalParent
	 * r.enUS: localNameParent
	 * r: tabulationsEchappes
	 * r.enUS: tabsEscaped
	 * r: tabulations
	 * r.enUS: tabs
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: nomLocal
	 * r.enUS: localName
	 */ 
	public Cluster g(String nomLocal) {
		ToutEcrivain w = requeteSite_.getW();
		String nomLocalParent = requeteSite_.getXmlPile().peek();
		boolean eNoWrap = nomLocalParent == null || MiseEnPage.HTML_ELEMENTS_NO_WRAP.contains(nomLocal);

		requeteSite_.getXmlPile().pop();
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\t"));
		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\\t"));

		if(!eNoWrap && !tabulationsEchappes.isEmpty()) {
			w.l();
			w.s(tabulations);
		}
		w.s("</");
		w.s(nomLocal);
		w.s(">");
		
		return this;
	}
}
