package org.computate.frFR.scolaire.recherche;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ResultatRechercheGen<DEV> extends Object {

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSite requeteSite_;
	public Couverture<RequeteSite> requeteSite_Couverture = new Couverture<RequeteSite>().p(this).c(RequeteSite.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSite> c) throws Exception;

	public RequeteSite getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSite requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
	}

	//////////////////
	// documentSolr //
	//////////////////

	/**	L'entité « documentSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument documentSolr;
	public Couverture<SolrDocument> documentSolrCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("documentSolr").o(documentSolr);

	/**	<br/>L'entité « documentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:documentSolr">Trouver l'entité documentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _documentSolr(Couverture<SolrDocument> c) throws Exception;

	public SolrDocument getDocumentSolr() {
		return documentSolr;
	}

	public void setDocumentSolr(SolrDocument documentSolr) {
		this.documentSolr = documentSolr;
		this.documentSolrCouverture.dejaInitialise = true;
	}
	protected ResultatRecherche documentSolrInit() throws Exception {
		if(!documentSolrCouverture.dejaInitialise) {
			_documentSolr(documentSolrCouverture);
			if(documentSolr == null)
				setDocumentSolr(documentSolrCouverture.o);
		}
		documentSolrCouverture.dejaInitialise(true);
		return (ResultatRecherche)this;
	}

	////////////////////
	// resultatIndice //
	////////////////////

	/**	L'entité « resultatIndice »
	 *	 is defined as null before being initialized. 
	 */
	protected Long resultatIndice;
	public Couverture<Long> resultatIndiceCouverture = new Couverture<Long>().p(this).c(Long.class).var("resultatIndice").o(resultatIndice);

	/**	<br/>L'entité « resultatIndice »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:resultatIndice">Trouver l'entité resultatIndice dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _resultatIndice(Couverture<Long> c) throws Exception;

	public Long getResultatIndice() {
		return resultatIndice;
	}

	public void setResultatIndice(Long resultatIndice) {
		this.resultatIndice = resultatIndice;
		this.resultatIndiceCouverture.dejaInitialise = true;
	}
	public ResultatRecherche setResultatIndice(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.resultatIndice = Long.parseLong(o);
		this.resultatIndiceCouverture.dejaInitialise = true;
		return (ResultatRecherche)this;
	}
	protected ResultatRecherche resultatIndiceInit() throws Exception {
		if(!resultatIndiceCouverture.dejaInitialise) {
			_resultatIndice(resultatIndiceCouverture);
			if(resultatIndice == null)
				setResultatIndice(resultatIndiceCouverture.o);
		}
		resultatIndiceCouverture.dejaInitialise(true);
		return (ResultatRecherche)this;
	}

	public Long solrResultatIndice() {
		return resultatIndice;
	}

	public String strResultatIndice() {
		return resultatIndice == null ? "" : resultatIndice.toString();
	}

	public String nomAffichageResultatIndice() {
		return null;
	}

	public String htmTooltipResultatIndice() {
		return null;
	}

	public String htmResultatIndice() {
		return resultatIndice == null ? "" : StringEscapeUtils.escapeHtml4(strResultatIndice());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseResultatRecherche = false;

	public ResultatRecherche initLoinResultatRecherche(RequeteSite requeteSite_) throws Exception {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseResultatRecherche) {
			dejaInitialiseResultatRecherche = true;
			initLoinResultatRecherche();
		}
		return (ResultatRecherche)this;
	}

	public void initLoinResultatRecherche() throws Exception {
		initResultatRecherche();
	}

	public void initResultatRecherche() throws Exception {
		documentSolrInit();
		resultatIndiceInit();
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) throws Exception {
		initLoinResultatRecherche(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteResultatRecherche(RequeteSite requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteResultatRecherche(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirResultatRecherche(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirResultatRecherche(String var) throws Exception {
		ResultatRecherche oResultatRecherche = (ResultatRecherche)this;
		switch(var) {
			case "requeteSite_":
				return oResultatRecherche.requeteSite_;
			case "documentSolr":
				return oResultatRecherche.documentSolr;
			case "resultatIndice":
				return oResultatRecherche.resultatIndice;
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerResultatRecherche(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerResultatRecherche(String var, Object val) {
		ResultatRecherche oResultatRecherche = (ResultatRecherche)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirResultatRecherche(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirResultatRecherche(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(resultatIndice);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof ResultatRecherche))
			return false;
		ResultatRecherche that = (ResultatRecherche)o;
		return Objects.equals( resultatIndice, that.resultatIndice );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ResultatRecherche {");
		sb.append( "resultatIndice: " ).append(resultatIndice);
		sb.append(" }");
		return sb.toString();
	}
}
