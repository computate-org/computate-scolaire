package org.computate.scolaire.frFR.recherche;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Exception;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class ResultatRechercheGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ResultatRecherche.class);

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	 L'entité requeteSite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/> L'entité requeteSite_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSiteFrFR> c) throws Exception, Exception;

	public RequeteSiteFrFR getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSiteFrFR requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
	}
	public static RequeteSiteFrFR staticSetRequeteSite_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ResultatRecherche requeteSite_Init() throws Exception {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (ResultatRecherche)this;
	}

	//////////////////
	// documentSolr //
	//////////////////

	/**	 L'entité documentSolr
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocument documentSolr;
	@JsonIgnore
	public Couverture<SolrDocument> documentSolrCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("documentSolr").o(documentSolr);

	/**	<br/> L'entité documentSolr
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:documentSolr">Trouver l'entité documentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _documentSolr(Couverture<SolrDocument> c) throws Exception, Exception;

	public SolrDocument getDocumentSolr() {
		return documentSolr;
	}

	public void setDocumentSolr(SolrDocument documentSolr) {
		this.documentSolr = documentSolr;
		this.documentSolrCouverture.dejaInitialise = true;
	}
	public static SolrDocument staticSetDocumentSolr(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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

	/**	 L'entité resultatIndice
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long resultatIndice;
	@JsonIgnore
	public Couverture<Long> resultatIndiceCouverture = new Couverture<Long>().p(this).c(Long.class).var("resultatIndice").o(resultatIndice);

	/**	<br/> L'entité resultatIndice
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recherche.ResultatRecherche&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:resultatIndice">Trouver l'entité resultatIndice dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _resultatIndice(Couverture<Long> c) throws Exception, Exception;

	public Long getResultatIndice() {
		return resultatIndice;
	}

	public void setResultatIndice(Long resultatIndice) {
		this.resultatIndice = resultatIndice;
		this.resultatIndiceCouverture.dejaInitialise = true;
	}
	public void setResultatIndice(String o) {
		this.resultatIndice = ResultatRecherche.staticSetResultatIndice(requeteSite_, o);
		this.resultatIndiceCouverture.dejaInitialise = true;
	}
	public static Long staticSetResultatIndice(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrResultatIndice(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrResultatIndice(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqResultatIndice(RequeteSiteFrFR requeteSite_, String o) {
		return ResultatRecherche.staticSolrStrResultatIndice(requeteSite_, ResultatRecherche.staticSolrResultatIndice(requeteSite_, ResultatRecherche.staticSetResultatIndice(requeteSite_, o)));
	}

	public Long solrResultatIndice() {
		return ResultatRecherche.staticSolrResultatIndice(requeteSite_, resultatIndice);
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
	// initLoin //
	//////////////

	protected boolean dejaInitialiseResultatRecherche = false;

	public ResultatRecherche initLoinResultatRecherche(RequeteSiteFrFR requeteSite_) throws Exception {
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
		requeteSite_Init();
		documentSolrInit();
		resultatIndiceInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) throws Exception {
		initLoinResultatRecherche(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteResultatRecherche(RequeteSiteFrFR requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteResultatRecherche(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
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
	public Object obtenirResultatRecherche(String var) {
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetResultatRecherche(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetResultatRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "resultatIndice":
			return ResultatRecherche.staticSetResultatIndice(requeteSite_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrResultatRecherche(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrResultatRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "resultatIndice":
			return ResultatRecherche.staticSolrResultatIndice(requeteSite_, (Long)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrResultatRecherche(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrResultatRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "resultatIndice":
			return ResultatRecherche.staticSolrStrResultatIndice(requeteSite_, (Long)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqResultatRecherche(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqResultatRecherche(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "resultatIndice":
			return ResultatRecherche.staticSolrFqResultatIndice(requeteSite_, o);
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
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
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

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiResultatRecherche() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof ResultatRecherche) {
			ResultatRecherche original = (ResultatRecherche)o;
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
