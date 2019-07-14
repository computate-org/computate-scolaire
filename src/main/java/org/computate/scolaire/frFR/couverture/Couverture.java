package org.computate.scolaire.frFR.couverture;     

import java.io.Serializable;
import java.util.ArrayList;

/** 
 * 
 * NomCanonique.enUS: org.computate.scolaire.enUS.wrap.Wrap
 * MotCle: classeNomSimpleCouverture
 */   
public class Couverture<DEV> implements Serializable { 
	private static final long serialVersionUID = 4171725370071929571L;

	/**	
	 * frFR: Le nom du champ qui est couvert par cette couverture. 
	 **/  
	public String var;
	public Couverture<DEV> var(String o) {
		var = o;
		return this;
	}

	/**
	 * frFR: L'objet qui est couvert par cette couverture. 
	 **/
	public DEV o;
	public Couverture<DEV> o(DEV o) {
		this.o = o;
		return this;
	}

	/**
	 * frFR: La classe de l'objet qui est couvert par cette couverture. 
	 **/
	public Class<?> c;
	public Couverture<DEV> c(Class<?> o) {
		c = o;
		return this;
	}

	/**
	 * frFR: L'objet parent de l'objet qui est couvert par cette couverture. 
	 **/
	public Object p;
	public Couverture<DEV> p(Object o) {
		p = o;
		return this;
	}

	/**
	 * Var.enUS: cChild
	 */
	public Class<?> cEnfant;

	/**
	 * Var.enUS: cChild
	 * r: cEnfant
	 * r.enUS: cChild
	 */
	public Couverture<DEV> cEnfant(Class<?> o) {
		cEnfant = o;
		return this;
	}

	/**
	 * Var.enUS: alreadyInitialized
	 * frFR: Vrai si cette couverture a était déjà initialisée. 
	 */
	public Boolean dejaInitialise = false;
	/**
	 * Var.enUS: alreadyInitialized
	 * r: dejaInitialise
	 * r.enUS: alreadyInitialized
	 * frFR: Vrai si cette couverture a était déjà initialisée. 
	 */
	public Couverture<DEV> dejaInitialise(Boolean o) {
		dejaInitialise = o;
		return this;
	}

	/**
	 * Var.enUS: siteRequestObject
	 * **/
	public Object requeteSiteObjet;
	/**
	 * Var.enUS: siteRequestObject
	 * r: requeteSiteObjet
	 * r.enUS: siteRequestObject
	 * frFR: Tous les infos importants à propos de la requête actuelle. 
	 */
	public Couverture<DEV> requeteSiteObjet(Object o) {
		requeteSiteObjet = o;
		return this;
	}

	/**  
	 * **/
	public ArrayList<String> pageVars = new ArrayList<String>();

	/** 
	 * Var.enUS: pageVarsAdd
	 */
	public void pageVarsAjouter(String...pageVars) {
		for(String pageVar : pageVars)
			this.pageVars.add(pageVar);
	}
}
