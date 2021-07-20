package org.computate.scolaire.frFR.page.part;

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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
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
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.page.part.PagePart&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class PagePartGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PagePart.class);

	///////////
	// page_ //
	///////////

	/**	 L'entité page_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected MiseEnPage page_;
	@JsonIgnore
	public Couverture<MiseEnPage> page_Couverture = new Couverture<MiseEnPage>().p(this).c(MiseEnPage.class).var("page_").o(page_);

	/**	<br/> L'entité page_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.page.part.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:page_">Trouver l'entité page_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _page_(Couverture<MiseEnPage> c);

	public MiseEnPage getPage_() {
		return page_;
	}

	public void setPage_(MiseEnPage page_) {
		this.page_ = page_;
		this.page_Couverture.dejaInitialise = true;
	}
	public static MiseEnPage staticSetPage_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected PagePart page_Init() {
		if(!page_Couverture.dejaInitialise) {
			_page_(page_Couverture);
			if(page_ == null)
				setPage_(page_Couverture.o);
		}
		page_Couverture.dejaInitialise(true);
		return (PagePart)this;
	}

	/////////////
	// partVar //
	/////////////

	/**	 L'entité partVar
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String partVar;
	@JsonIgnore
	public Couverture<String> partVarCouverture = new Couverture<String>().p(this).c(String.class).var("partVar").o(partVar);

	/**	<br/> L'entité partVar
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.page.part.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partVar">Trouver l'entité partVar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _partVar(Couverture<String> c);

	public String getPartVar() {
		return partVar;
	}
	public void setPartVar(String o) {
		this.partVar = PagePart.staticSetPartVar(requeteSite_, o);
		this.partVarCouverture.dejaInitialise = true;
	}
	public static String staticSetPartVar(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected PagePart partVarInit() {
		if(!partVarCouverture.dejaInitialise) {
			_partVar(partVarCouverture);
			if(partVar == null)
				setPartVar(partVarCouverture.o);
		}
		partVarCouverture.dejaInitialise(true);
		return (PagePart)this;
	}

	public static String staticSolrPartVar(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPartVar(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPartVar(RequeteSiteFrFR requeteSite_, String o) {
		return PagePart.staticSolrStrPartVar(requeteSite_, PagePart.staticSolrPartVar(requeteSite_, PagePart.staticSetPartVar(requeteSite_, o)));
	}

	public String solrPartVar() {
		return PagePart.staticSolrPartVar(requeteSite_, partVar);
	}

	public String strPartVar() {
		return partVar == null ? "" : partVar;
	}

	public String sqlPartVar() {
		return partVar;
	}

	public String jsonPartVar() {
		return partVar == null ? "" : partVar;
	}

	public String nomAffichagePartVar() {
		return null;
	}

	public String htmTooltipPartVar() {
		return null;
	}

	public String htmPartVar() {
		return partVar == null ? "" : StringEscapeUtils.escapeHtml4(strPartVar());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePagePart = false;

	public PagePart initLoinPagePart(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePagePart) {
			dejaInitialisePagePart = true;
			initLoinPagePart();
		}
		return (PagePart)this;
	}

	public void initLoinPagePart() {
		initPagePart();
		super.initLoinCluster(requeteSite_);
	}

	public void initPagePart() {
		page_Init();
		partVarInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPagePart(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePagePart(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePagePart(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPagePart(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirPagePart(String var) {
		PagePart oPagePart = (PagePart)this;
		switch(var) {
			case "page_":
				return oPagePart.page_;
			case "partVar":
				return oPagePart.partVar;
			default:
				return super.obtenirCluster(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerPagePart(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPagePart(String var, Object val) {
		PagePart oPagePart = (PagePart)this;
		switch(var) {
			default:
				return super.attribuerCluster(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetPagePart(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetPagePart(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "partVar":
			return PagePart.staticSetPartVar(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrPagePart(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrPagePart(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "partVar":
			return PagePart.staticSolrPartVar(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrPagePart(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrPagePart(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "partVar":
			return PagePart.staticSolrStrPartVar(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqPagePart(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqPagePart(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "partVar":
			return PagePart.staticSolrFqPartVar(requeteSite_, o);
			default:
				return Cluster.staticSolrFqCluster(entiteVar,  requeteSite_, o);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirPagePart(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPagePart(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirCluster(var, val);
		}
	}

	@Override public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirPagePart(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPagePart(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.definirCluster(var, val);
		}
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyPagePart();
	}

	public void htmlBodyPagePart() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiPagePart() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof PagePart) {
			PagePart original = (PagePart)o;
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode());
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PagePart))
			return false;
		PagePart that = (PagePart)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PagePart { ");
		sb.append(" }");
		return sb.toString();
	}
}
