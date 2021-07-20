package org.computate.scolaire.enUS.page.part;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.page.PageLayout;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.part.PagePart&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class PagePartGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PagePart.class);

	///////////
	// page_ //
	///////////

	/**	 The entity page_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PageLayout page_;
	@JsonIgnore
	public Wrap<PageLayout> page_Wrap = new Wrap<PageLayout>().p(this).c(PageLayout.class).var("page_").o(page_);

	/**	<br/> The entity page_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.part.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:page_">Find the entity page_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _page_(Wrap<PageLayout> c);

	public PageLayout getPage_() {
		return page_;
	}

	public void setPage_(PageLayout page_) {
		this.page_ = page_;
		this.page_Wrap.alreadyInitialized = true;
	}
	public static PageLayout staticSetPage_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected PagePart page_Init() {
		if(!page_Wrap.alreadyInitialized) {
			_page_(page_Wrap);
			if(page_ == null)
				setPage_(page_Wrap.o);
		}
		page_Wrap.alreadyInitialized(true);
		return (PagePart)this;
	}

	/////////////
	// partVar //
	/////////////

	/**	 The entity partVar
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String partVar;
	@JsonIgnore
	public Wrap<String> partVarWrap = new Wrap<String>().p(this).c(String.class).var("partVar").o(partVar);

	/**	<br/> The entity partVar
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.part.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:partVar">Find the entity partVar in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _partVar(Wrap<String> c);

	public String getPartVar() {
		return partVar;
	}
	public void setPartVar(String o) {
		this.partVar = PagePart.staticSetPartVar(siteRequest_, o);
		this.partVarWrap.alreadyInitialized = true;
	}
	public static String staticSetPartVar(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected PagePart partVarInit() {
		if(!partVarWrap.alreadyInitialized) {
			_partVar(partVarWrap);
			if(partVar == null)
				setPartVar(partVarWrap.o);
		}
		partVarWrap.alreadyInitialized(true);
		return (PagePart)this;
	}

	public static String staticSolrPartVar(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPartVar(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPartVar(SiteRequestEnUS siteRequest_, String o) {
		return PagePart.staticSolrStrPartVar(siteRequest_, PagePart.staticSolrPartVar(siteRequest_, PagePart.staticSetPartVar(siteRequest_, o)));
	}

	public String solrPartVar() {
		return PagePart.staticSolrPartVar(siteRequest_, partVar);
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
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPagePart = false;

	public PagePart initDeepPagePart(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPagePart) {
			alreadyInitializedPagePart = true;
			initDeepPagePart();
		}
		return (PagePart)this;
	}

	public void initDeepPagePart() {
		initPagePart();
		super.initDeepCluster(siteRequest_);
	}

	public void initPagePart() {
		page_Init();
		partVarInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPagePart(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPagePart(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPagePart(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPagePart(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtainPagePart(String var) {
		PagePart oPagePart = (PagePart)this;
		switch(var) {
			case "page_":
				return oPagePart.page_;
			case "partVar":
				return oPagePart.partVar;
			default:
				return super.obtainCluster(var);
		}
	}

	///////////////
	// attribute //
	///////////////

	@Override public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributePagePart(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePagePart(String var, Object val) {
		PagePart oPagePart = (PagePart)this;
		switch(var) {
			default:
				return super.attributeCluster(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetPagePart(entityVar,  siteRequest_, o);
	}
	public static Object staticSetPagePart(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "partVar":
			return PagePart.staticSetPartVar(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrPagePart(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrPagePart(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "partVar":
			return PagePart.staticSolrPartVar(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrPagePart(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrPagePart(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "partVar":
			return PagePart.staticSolrStrPartVar(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqPagePart(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqPagePart(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "partVar":
			return PagePart.staticSolrFqPartVar(siteRequest_, o);
			default:
				return Cluster.staticSolrFqCluster(entityVar,  siteRequest_, o);
		}
	}

	/////////////
	// define //
	/////////////

	@Override public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definePagePart(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePagePart(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.defineCluster(var, val);
		}
	}

	@Override public boolean defineForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definePagePart(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePagePart(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.defineCluster(var, val);
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
	// apiRequest //
	//////////////////

	public void apiRequestPagePart() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof PagePart) {
			PagePart original = (PagePart)o;
			super.apiRequestCluster();
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
