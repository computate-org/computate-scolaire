package org.computate.frFR.scolaire.recherche;  

import org.apache.solr.common.SolrDocument;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.requete.RequeteSite;

public class ResultatRecherche extends ResultatRechercheGen<Object> {

	protected void _requeteSite_(Couverture<RequeteSite> c) throws Exception {
	}

	protected void _documentSolr(Couverture<SolrDocument> c) throws Exception {
	}

	protected void _resultatIndice(Couverture<Long> c) throws Exception {
	}
}
