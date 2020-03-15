package org.computate.scolaire.frFR.cluster;             

import java.text.Normalizer;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.page.part.PagePart;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.xml.OutilXml;



/**          
 * NomCanonique.enUS: org.computate.scolaire.enUS.cluster.Cluster
 * Modele: true
 * Api: true
 * Indexe: true
 * 
 * ApiTag.frFR: Cluster
 * ApiUri.frFR: /api/cluster
 * 
 * ApiTag.enUS: Cluster
 * ApiUri.enUS: /api/cluster
 * 
 * ApiMethode: POST
 * ApiMethode: PUT
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /cluster
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /cluster
 * 
 * UnNom.frFR: un cluster
 * UnNom.enUS: a cluster
 * Couleur: gray
 * IconeGroupe: regular
 * IconeNom: fort-awesome
 * MotCle: classeNomSimpleCluster
 * 
 * RoleUtilisateur: true
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
 * RoleRead.frFR: User
 * RoleRead.enUS: User
 */               
public class Cluster extends ClusterGen<Object> {   

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteRequest_
	 * Ignorer: true
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
	 * Modifier: false
	 * HtmlLigne: 1
	 * HtmlCellule: 3
	 * Description.frFR: La clé primaire dans la base de données. 
	 * Description.enUS: The primary key in the database. 
	 * NomAffichage.frFR: clé primaire
	 * NomAffichage.enUS: primary key
	 */                                       
	protected void _pk(Couverture<Long> c) {}

	/** 
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * InheritClePrimaire: true
	 */                                       
	protected void _inheritPk(Couverture<Long> c) {}

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
	 * VarCree: true
	 * HtmlLigne: 1
	 * HtmlCellule: 3
	 * Description.frFR: La date et l'heure créées. 
	 * Description.enUS: The date and time created. 
	 * NomAffichage.frFR: crée
	 * NomAffichage.enUS: created
	 * HtmlColonne: 2
	 */   
	protected void _cree(Couverture<ZonedDateTime> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: modified
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Modifier: false
	 * VarModifie: true
	 * HtmlLigne: 1
	 * HtmlCellule: 4
	 * Description.frFR: La date et l'heure modifiéés. 
	 * Description.enUS: The date and time modified. 
	 * NomAffichage.frFR: modifié
	 * NomAffichage.enUS: modified
	 */
	protected void _modifie(Couverture<ZonedDateTime> c) {
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: archived
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 2
	 * HtmlCellule: 1
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
	 * HtmlLigne: 2
	 * HtmlCellule: 2
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
		Class<?> cl = getClass();
		if(!cl.equals(Cluster.class))
			l.add(cl.getCanonicalName());
		l.add(Cluster.class.getCanonicalName());
	}

	/**   
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite
	 * r.enUS: siteRequest
	 */                  
	protected void _sessionId(Couverture<String> c) {
		c.o(requeteSite_.getSessionId());
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: saves
	 * Indexe: true
	 * Stocke: true
	 * Sauvegardes: true
	 * r: sauvegardes
	 * r.enUS: saves
	 */               
	protected void _sauvegardes(Couverture<List<String>> c) {
		try {
			c.o((List<String>)FieldUtils.getField(getClass(), "sauvegardes" + getClass().getSimpleName(), true).get(this));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectTitle
	 * Indexe: true
	 * Stocke: true
	 * VarTitre: true
	 * HtmlColonne: 2
	 */        
	protected void _objetTitre(Couverture<String> c) {
	}

	/**   
	 * {@inheritDoc}
	 * Var.enUS: objectId
	 * Indexe: true
	 * Stocke: true
	 * VarId: true
	 * HtmlLigne: 1
	 * HtmlCellule: 4
	 * NomAffichage.frFR: ID
	 * NomAffichage.enUS: ID
	 * r: objetTitre
	 * r.enUS: objectTitle
	 */                   
	protected void _objetId(Couverture<String> c) {
		if(objetTitre != null) {
			c.o(toId(objetTitre));
		}
		else if(pk != null){
			c.o(pk.toString());
		}
	}

	/**
	 * r: objetTitre
	 * r.enUS: objectTitle
	 */
	public String toId(String s) {
		if(s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
		}

		return s;
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectNameVar
	 * r: objetId
	 * r.enUS: objectId
	 * **/   
	protected void _objetNomVar(Couverture<String> c)  {
		if(objetId != null) {
			Class<?> cl = getClass();

			try {
				String o = toId((String)FieldUtils.getField(cl, cl.getSimpleName() + "_NomVar").get(this));
				c.o(o);
			} catch (Exception e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: objectSuggest
	 * Suggere: true
	 * r: objetNomVar
	 * r.enUS: objectNameVar
	 * r: objetId
	 * r.enUS: objectId
	 * r: objetTitre
	 * r.enUS: objectTitle
	 */     
	protected void _objetSuggere(Couverture<String> c) { 
		StringBuilder b = new StringBuilder();
		if(pk != null)
			b.append(" ").append(pk);
		if(objetNomVar != null)
			b.append(" ").append(objetNomVar);
		if(objetId != null)
			b.append(" ").append(objetId);
		if(objetTitre != null)
			b.append(" ").append(objetTitre);
		c.o(b.toString());
	}

	/**	la version plus courte de l'URL qui commence avec « / » 
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * VarUrlId: true
	 * r: objetId
	 * r.enUS: objectId
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * r: objetNomVar
	 * r.enUS: objectNameVar
	 * **/   
	protected void _pageUrlId(Couverture<String> c)  {
		if(objetId != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/" + objetNomVar + "/" + objetId;
			c.o(o);
		}
	}

	/**	la version plus courte de l'URL qui commence avec « / » 
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * VarUrlPk: true
	 * r: objetId
	 * r.enUS: objectId
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * r: objetNomVar
	 * r.enUS: objectNameVar
	 * **/   
	protected void _pageUrlPk(Couverture<String> c)  {
		if(pk != null) {
			String o = requeteSite_.getConfigSite_().getSiteUrlBase() + "/" + objetNomVar + "/" + pk;
			c.o(o);
		}
	}

	/**
	 * H1: true
	 * r: ecoleNom
	 * r.enUS: schoolName
	 * r: " : "
	 * r.enUS: ": "
	 * r: objetTitre
	 * r.enUS: objectTitle
	 */ 
	protected void _pageH1(Couverture<String> c)  {
		try {
			Class<?> cl = getClass();
			c.o((String)FieldUtils.getField(cl, cl.getSimpleName() + "_NomSingulier").get(this) + " : " + objetTitre);
		} catch (Exception e) {
			ExceptionUtils.rethrow(e);
		}
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

		if(!eNoWrap || nomLocalParent == null)
			w.l();
		if(!eNoWrap && !tabulationsEchappes.isEmpty())
			w.s(tabulations);
		w.s("</");
		w.s(nomLocal);
		w.s(">");
		
		return this;
	}
}
