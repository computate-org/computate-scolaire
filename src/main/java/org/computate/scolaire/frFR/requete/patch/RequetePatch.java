package org.computate.scolaire.frFR.requete.patch;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.request.patch.PatchRequest
 * MotCle: classeNomSimpleRequetePatch
*/   
public class RequetePatch extends RequetePatchGen<Object> {

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteRequest_
	 * Ignorer: true
	 */        
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: created
	 * Indexe: true
	 * Stocke: true
	 * VarCree: true
	 * NomAffichage.frFR: crée
	 * NomAffichage.enUS: created
	 */   
	protected void _cree(Couverture<ZonedDateTime> c) {
		c.o(ZonedDateTime.now());
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 */   
	protected void _rows(Couverture<Integer> c) {
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 */   
	protected void _numFound(Couverture<Long> c) {
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 */   
	protected void _numPATCH(Couverture<Long> c) {
		c.o(0L);
	}

	/**
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: UUID
	 * NomAffichage.enUS: UUID
	 */                 
	protected void _uuid(Couverture<String> c) {
		c.o(UUID.randomUUID().toString());
	}

	protected void _id(Couverture<String> c) {
		c.o("PATCH-" + uuid);
	}

//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: _objectTitle
//	 * r: anneeNomComplet
//	 * r.enUS: yearCompleteName
//	 */
//	@Override
//	protected void _objetTitre(Couverture<String> c) {
//	}
}
