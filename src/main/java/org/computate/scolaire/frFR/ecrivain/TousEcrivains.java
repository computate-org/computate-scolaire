package org.computate.scolaire.frFR.ecrivain;    

import java.io.IOException;
import java.util.List;


import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;


/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.writer.AllWriters
 **/
public class TousEcrivains extends TousEcrivainsGen<Object> {    

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteRequest_
	 **/
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {
	}

	/**
	 * Param1.var.enUS: siteRequest_
	 * Param2.var.enUS: writers
	 * Var.enUS: create
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
	 * r: TousEcrivains
	 * r.enUS: AllWriters
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ecrivains
	 * r.enUS: writers
	 * r: Ecrivains
	 * r.enUS: Writers
	 */
	public static TousEcrivains creer(RequeteSiteFrFR requeteSite_, ToutEcrivain...ecrivains) {
		TousEcrivains o = new TousEcrivains();
		o.initLoinPourClasse(requeteSite_);
		o.addEcrivains(ecrivains);
		return o;
	}

	/**
	 * Var.enUS: writers
	 */
	protected void _ecrivains(List<ToutEcrivain> c) {
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: ecrivain
	 * r.enUS: writer
	 */
	public TousEcrivains t(int nombreTabulations, Object...objets) {
		for(ToutEcrivain ecrivain : ecrivains) {
			ecrivain.t(nombreTabulations, objets);
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
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: ecrivain
	 * r.enUS: writer
	 */
	public TousEcrivains tl(int nombreTabulations, Object...objets) {
		for(ToutEcrivain ecrivain : ecrivains) {
			ecrivain.tl(nombreTabulations, objets);
		}
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: ecrivain
	 * r.enUS: writer
	 */
	public TousEcrivains l(Object...objets) {
		for(ToutEcrivain ecrivain : ecrivains) {
			ecrivain.l(objets);
		}
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: ecrivain
	 * r.enUS: writer
	 */
	public TousEcrivains s(Object...objets) { 
		for(ToutEcrivain ecrivain : ecrivains) {
			ecrivain.s(objets);
		}
		return this;
	}

	/**
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: ecrivain
	 * r.enUS: writer
	 */
	public void flushClose() throws IOException {
		for(ToutEcrivain ecrivain : ecrivains) {
			ecrivain.flushClose();
		}
	}

	/**
	 * r: ecrivain
	 * r.enUS: writer
	 */
	@Override public String toString() {
		return ecrivains.get(0).toString();
	}
}
