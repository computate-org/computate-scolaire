package org.computate.scolaire.frFR.inscription;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;

/**
 * Traduire: false
 **/
public class InscriptionFormPage extends InscriptionFormPageGen<InscriptionFormGenPage> {

//	protected void _blocRecherche(ListeRecherche<BlocScolaire> l) {
//		if(blocCles.size() > 0) {
//			l.setQuery("*:*");
//			l.addFilterQuery("pk_indexed_long:(" + StringUtils.join(blocCles, " ") + ")");
//			l.setC(BlocScolaire.class);
//			l.setStocker(true);
//			l.addSort("saisonJourDebut_indexed_date", ORDER.asc);
//			l.addSort("sessionJourFin_indexed_date", ORDER.asc);
//			l.addSort("ageDebut_indexed_int", ORDER.asc);
//			l.addSort("blocPrixParMois_indexed_double", ORDER.asc);
//			l.addSort("blocHeureDebut_indexed_string", ORDER.asc);
//		}
//		else {
//			l.setQuery(null);
//		}
//	}
}
