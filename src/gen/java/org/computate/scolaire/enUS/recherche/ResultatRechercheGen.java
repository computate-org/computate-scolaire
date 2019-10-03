package org.computate.scolaire.enUS.recherche;

import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.lang.Exception;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.lang.Object;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ResultatRechercheGen<DEV> extends Object {

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteRequestEnUS requeteSite_;
	@JsonIgnore
	public Wrap<SiteRequestEnUS> requeteSite_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Wrap<SiteRequestEnUS> c) throws Exception, Exception;

	public SiteRequestEnUS getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(SiteRequestEnUS requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Wrap.alreadyInitialized = true;
	}
	protected ResultatRecherche requeteSite_Init() throws Exception {
		if(!requeteSite_Wrap.alreadyInitialized) {
			_requeteSite_(requeteSite_Wrap);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Wrap.o);
		}
		requeteSite_Wrap.alreadyInitialized(true);
		return (ResultatRecherche)this;
	}

	//////////////////
	// documentSolr //
	//////////////////

	/**	L'entité « documentSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument documentSolr;
	@JsonIgnore
	public Wrap<SolrDocument> documentSolrWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("documentSolr").o(documentSolr);

	/**	<br/>L'entité « documentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:documentSolr">Trouver l'entité documentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _documentSolr(Wrap<SolrDocument> c) throws Exception, Exception;

	public SolrDocument getDocumentSolr() {
		return documentSolr;
	}

	public void setDocumentSolr(SolrDocument documentSolr) {
		this.documentSolr = documentSolr;
		this.documentSolrWrap.alreadyInitialized = true;
	}
	protected ResultatRecherche documentSolrInit() throws Exception {
		if(!documentSolrWrap.alreadyInitialized) {
			_documentSolr(documentSolrWrap);
			if(documentSolr == null)
				setDocumentSolr(documentSolrWrap.o);
		}
		documentSolrWrap.alreadyInitialized(true);
		return (ResultatRecherche)this;
	}

	////////////////////
	// resultatIndice //
	////////////////////

	/**	L'entité « resultatIndice »
	 *	 is defined as null before being initialized. 
	 */
	protected Long resultatIndice;
	@JsonIgnore
	public Wrap<Long> resultatIndiceWrap = new Wrap<Long>().p(this).c(Long.class).var("resultatIndice").o(resultatIndice);

	/**	<br/>L'entité « resultatIndice »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:resultatIndice">Trouver l'entité resultatIndice dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _resultatIndice(Wrap<Long> c) throws Exception, Exception;

	public Long getResultatIndice() {
		return resultatIndice;
	}

	public void setResultatIndice(Long resultatIndice) {
		this.resultatIndice = resultatIndice;
		this.resultatIndiceWrap.alreadyInitialized = true;
	}
	public ResultatRecherche setResultatIndice(String o) {
		if(NumberUtils.isParsable(o))
			this.resultatIndice = Long.parseLong(o);
		this.resultatIndiceWrap.alreadyInitialized = true;
		return (ResultatRecherche)this;
	}
	protected ResultatRecherche resultatIndiceInit() throws Exception {
		if(!resultatIndiceWrap.alreadyInitialized) {
			_resultatIndice(resultatIndiceWrap);
			if(resultatIndice == null)
				setResultatIndice(resultatIndiceWrap.o);
		}
		resultatIndiceWrap.alreadyInitialized(true);
		return (ResultatRecherche)this;
	}

	public Long solrResultatIndice() {
		return resultatIndice;
	}

	public String strResultatIndice() {
		return resultatIndice == null ? "" : resultatIndice.toString();
	}

	public String jsonResultatIndice() {
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
	// initDeep //
	//////////////

	protected boolean alreadyInitializedResultatRecherche = false;

	public ResultatRecherche initDeepResultatRecherche(SiteRequestEnUS siteRequest_) throws Exception {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedResultatRecherche) {
			alreadyInitializedResultatRecherche = true;
			initDeepResultatRecherche();
		}
		return (ResultatRecherche)this;
	}

	public void initDeepResultatRecherche() throws Exception {
		initResultatRecherche();
	}

	public void initResultatRecherche() throws Exception {
		requeteSite_Init();
		documentSolrInit();
		resultatIndiceInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) throws Exception {
		initDeepResultatRecherche(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestResultatRecherche(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestResultatRecherche(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainResultatRecherche(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainResultatRecherche(String var) {
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
	// attribute //
	///////////////

	public boolean attributeForClass(String var, Object val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributeResultatRecherche(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeResultatRecherche(String var, Object val) {
		ResultatRecherche oResultatRecherche = (ResultatRecherche)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// define //
	/////////////

	public boolean defineForClass(String var, String val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineResultatRecherche(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineResultatRecherche(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash();
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
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ResultatRecherche { ");
		sb.append(" }");
		return sb.toString();
	}
}
