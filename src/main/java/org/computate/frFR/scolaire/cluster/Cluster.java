package org.computate.frFR.scolaire.cluster;     

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.page.MiseEnPage;
import org.computate.frFR.scolaire.requete.RequeteSite;



/**
 * NomCanonique.enUS: org.computate.site.course.c000.cluster.Cluster
 * Modele: true
 * ApiUri.enUS: /api/warfarin/cluster
 * ApiUri.frFR: /api/warfarin/cluster
 * ApiMethode: Recherche
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: PUT
 * ApiMethode: DELETE
 * ApiTag.enUS: Cluster
 * ApiTag.frFR: Cluster
 */            
public class Cluster extends ClusterGen<Object> {   

	public static DateTimeFormatter FORMAT_dateMedicalCourt = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.FRANCE);

//	/**
//	 * Ignorer: true
//	 * Var.enUS: siteRequestCluster
//	 * Param1.var.enUS: siteRequest
//	 * remplacer.enUS: requeteSite
//	 * siteRequest
//	 */
//	@Override public void requeteSiteCluster(RequeteSite requeteSite) {  
//		super.requeteSiteCluster(requeteSite);
//		requeteSite(requeteSite);
//	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _siteRequest
	 */       
	protected void _requeteSite_(Couverture<RequeteSite> c) {}
	protected void _page_(Couverture<MiseEnPage> c) {}

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
	 * Var.enUS: _deleted
	 * Description.frFR: Supprimé. 
	 * Description.enUS: Deleted. 
	 * NomAffichage.frFR: Supprimé
	 * NomAffichage.enUS: Deleted
	 */
	protected void _supprime(Couverture<Boolean> c) {
		Boolean o = false;
		c.o(o);
	}

	/**	
	 * Var.enUS: _userId
	 * description.frFR: L'identifiant Keycloak pour cet utilisateur. 
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _utilisateurId(Couverture<String> c) {
		String o = requeteSite_.getUtilisateurId();
		c.o(o);
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * Var.enUS: _created
	 * Description.frFR: La date et l'heure créées. 
	 * Description.enUS: The date and time created. 
	 * NomAffichage.frFR: Crée
	 * NomAffichage.enUS: Created
	 */
	protected void _cree(Couverture<LocalDateTime> c) {}

	/**
	 * Indexe: true
	 * Stocke: true
	 * Var.enUS: _modified
	 * Description.frFR: La date et l'heure modifiéés. 
	 * Description.enUS: The date and time modified. 
	 * NomAffichage.frFR: Modifié
	 * NomAffichage.enUS: Modified
	 */
	protected void _modifie(Couverture<LocalDateTime> c) {}

	/**   
	 * Indexe: true
	 * Stocke: true
	 * Var.enUS: _classCanonicalName
	 */       
	protected void _classeNomCanonique(Couverture<String> c) {
		String o = getClass().getCanonicalName();
		c.o(o);
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * Var.enUS: _classSimpleName
	 */          
	protected void _classeNomSimple(Couverture<String> c) {
		String o = getClass().getSimpleName();
		c.o(o);
	}

	public void htmlCluster() {}  
//
//	/**
//	 * genInclure: true
//	 */
//	public MiseEnPage e(String nomLocal) {
//		if(page_ != null)
//			page_.e(nomLocal);
//		return page_;
//	}
//
//	/**
//	 * genInclure: true
//	 * Param1.var.enUS: attributeName
//	 * Param2.var.enUS: objects
//	 * remplacer.enUS: nomAttribut
//	 * attributeName
//	 * remplacer.enUS: objets
//	 * objects
//	 */
//	public MiseEnPage a(String nomAttribut, Object...objets) {
//		if(page_ != null)
//			page_.a(nomAttribut, objets);
//		return page_;
//	}
//
//	/**
//	 * genInclure: true
//	 */
//	public MiseEnPage f() {
//		if(page_ != null)
//			page_.f();
//		return page_;
//	}
//
//	public MiseEnPage toutShApos(Object...objets) {
//		if(page_ != null)
//			page_.toutShApos(objets);
//		return page_;
//	}
//
//	public MiseEnPage toutLigne(Object...objets) {
//		if(page_ != null)
//			page_.toutLigne(objets);
//		return page_;
//	}
//
//	public MiseEnPage tout(Object...objets) {
//		if(page_ != null)
//			page_.tout(objets);
//		return page_;
//	}
//
//	public MiseEnPage enUS(Object...objets) {
//		if(page_ != null)
//			page_.enUS(objets);
//		return page_;
//	}
//
//	public MiseEnPage frFR(Object...objets) {
//		if(page_ != null)
//			page_.frFR(objets);
//		return page_;
//	}
//
//	public MiseEnPage enUSLigne(Object...objets) {
//		if(page_ != null)
//			page_.enUSLigne(objets);
//		return page_;
//	}
//
//	public MiseEnPage frFRLigne(Object...objets) {
//		if(page_ != null)
//			page_.frFRLigne(objets);
//		return page_;
//	}
//
//	public MiseEnPage toutShAposXml(Object...objets) {
//		if(page_ != null)
//			page_.toutShAposXml(objets);
//		return page_;
//	}
//
//	/**
//	 * genInclure: true
//	 * Param1.var.enUS: objects
//	 * remplacer.enUS: objets
//	 * objects
//	 * remplacer.enUS: toutXml
//	 * allXml
//	 */
//	public MiseEnPage toutXml(Object...objets) {
//		if(page_ != null)
//			page_.toutXml(objets);
//		return page_;
//	}
//
//	public MiseEnPage enUSXml(Object...objets) {
//		if(page_ != null)
//			page_.enUSXml(objets);
//		return page_;
//	}
//
//	public MiseEnPage frFRXml(Object...objets) {
//		if(page_ != null)
//			page_.frFRXml(objets);
//		return page_;
//	}
//
//	/**
//	 * genInclure: true
//	 */
//	public MiseEnPage fg() {
//		if(page_ != null)
//			page_.fg();
//		return page_;
//	}
//
//	/**
//	 * genInclure: true
//	 * Param1.var.enUS: localName
//	 * remplacer.enUS: nomLocal
//	 * localName
//	 */
//	public MiseEnPage g(String nomLocal) {
//		if(page_ != null)
//			page_.g(nomLocal);
//		return page_;
//	}
//
//	public MiseEnPage icone(String href) {
//		if(page_ != null)
//			page_.g(href);
//		return page_;
//	}
//
//	public MiseEnPage iconeLight(String nom) {
//		if(page_ != null)
//			page_.g(nom);
//		return page_;
//	}
//
//	public MiseEnPage iconeRegular(String nom) {
//		if(page_ != null)
//			page_.g(nom);
//		return page_;
//	}
//
//	public MiseEnPage iconeSolid(String nom) {
//		if(page_ != null)
//			page_.g(nom);
//		return page_;
//	}
//
//	public MiseEnPage iconeBrands(String nom) {
//		if(page_ != null)
//			page_.g(nom);
//		return page_;
//	}
//
//	public MiseEnPage iconeComputate(String nom) {
//		if(page_ != null)
//			page_.g(nom);
//		return page_;
//	}
}
