package org.computate.scolaire.frFR.recherche;  

import org.apache.solr.common.SolrDocument;

import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**
 * MotCle: classeNomSimpleResultatRecherche
 */
public class ResultatRecherche extends ResultatRechercheGen<Object> {

	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) throws Exception {
	}

	protected void _documentSolr(Couverture<SolrDocument> c) throws Exception {
	}

	protected void _resultatIndice(Couverture<Long> c) throws Exception {
	}
}
