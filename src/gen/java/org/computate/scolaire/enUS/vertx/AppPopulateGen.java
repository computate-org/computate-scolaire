package org.computate.scolaire.enUS.vertx;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Exception;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class AppPopulateGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AppPopulate.class);

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	 The entity siteRequest_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteRequestEnUS siteRequest_;
	@JsonIgnore
	public Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("siteRequest_").o(siteRequest_);

	/**	<br/> The entity siteRequest_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Find the entity siteRequest_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequestEnUS> c) throws Exception, Exception;

	public SiteRequestEnUS getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequestEnUS siteRequest_) {
		this.siteRequest_ = siteRequest_;
		this.siteRequest_Wrap.alreadyInitialized = true;
	}
	public static SiteRequestEnUS staticSetSiteRequest_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected AppPopulate siteRequest_Init() throws Exception {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (AppPopulate)this;
	}

	/////////////////
	// siteContext //
	/////////////////

	/**	 The entity siteContext
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SiteContextEnUS(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteContextEnUS siteContext = new SiteContextEnUS();
	@JsonIgnore
	public Wrap<SiteContextEnUS> siteContextWrap = new Wrap<SiteContextEnUS>().p(this).c(SiteContextEnUS.class).var("siteContext").o(siteContext);

	/**	<br/> The entity siteContext
	 *  It is constructed before being initialized with the constructor by default SiteContextEnUS(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteContext">Find the entity siteContext in Solr</a>
	 * <br/>
	 * @param siteContext is the entity already constructed. 
	 **/
	protected abstract void _siteContext(SiteContextEnUS o) throws Exception, Exception;

	public SiteContextEnUS getSiteContext() {
		return siteContext;
	}

	public void setSiteContext(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		this.siteContextWrap.alreadyInitialized = true;
	}
	public static SiteContextEnUS staticSetSiteContext(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected AppPopulate siteContextInit() throws Exception {
		if(!siteContextWrap.alreadyInitialized) {
			_siteContext(siteContext);
		}
		siteContext.initDeepForClass(siteRequest_);
		siteContextWrap.alreadyInitialized(true);
		return (AppPopulate)this;
	}

	////////////////
	// siteConfig //
	////////////////

	/**	 The entity siteConfig
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteConfig siteConfig;
	@JsonIgnore
	public Wrap<SiteConfig> siteConfigWrap = new Wrap<SiteConfig>().p(this).c(SiteConfig.class).var("siteConfig").o(siteConfig);

	/**	<br/> The entity siteConfig
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteConfig">Find the entity siteConfig in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteConfig(Wrap<SiteConfig> c) throws Exception, Exception;

	public SiteConfig getSiteConfig() {
		return siteConfig;
	}

	public void setSiteConfig(SiteConfig siteConfig) {
		this.siteConfig = siteConfig;
		this.siteConfigWrap.alreadyInitialized = true;
	}
	public static SiteConfig staticSetSiteConfig(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected AppPopulate siteConfigInit() throws Exception {
		if(!siteConfigWrap.alreadyInitialized) {
			_siteConfig(siteConfigWrap);
			if(siteConfig == null)
				setSiteConfig(siteConfigWrap.o);
		}
		if(siteConfig != null)
			siteConfig.initDeepForClass(siteRequest_);
		siteConfigWrap.alreadyInitialized(true);
		return (AppPopulate)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedAppPopulate = false;

	public AppPopulate initDeepAppPopulate(SiteRequestEnUS siteRequest_) throws Exception {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedAppPopulate) {
			alreadyInitializedAppPopulate = true;
			initDeepAppPopulate();
		}
		return (AppPopulate)this;
	}

	public void initDeepAppPopulate() throws Exception {
		initAppPopulate();
	}

	public void initAppPopulate() throws Exception {
		siteRequest_Init();
		siteContextInit();
		siteConfigInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) throws Exception {
		initDeepAppPopulate(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAppPopulate(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestAppPopulate(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAppPopulate(v);
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
	public Object obtainAppPopulate(String var) {
		AppPopulate oAppPopulate = (AppPopulate)this;
		switch(var) {
			case "siteRequest_":
				return oAppPopulate.siteRequest_;
			case "siteContext":
				return oAppPopulate.siteContext;
			case "siteConfig":
				return oAppPopulate.siteConfig;
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
				o = attributeAppPopulate(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeAppPopulate(String var, Object val) {
		AppPopulate oAppPopulate = (AppPopulate)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetAppPopulate(entityVar,  siteRequest_, o);
	}
	public static Object staticSetAppPopulate(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrAppPopulate(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrAppPopulate(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrAppPopulate(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrAppPopulate(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqAppPopulate(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqAppPopulate(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
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
					o = defineAppPopulate(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineAppPopulate(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	public boolean defineForClass(String var, Object val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineAppPopulate(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineAppPopulate(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestAppPopulate() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof AppPopulate) {
			AppPopulate original = (AppPopulate)o;
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
		if(!(o instanceof AppPopulate))
			return false;
		AppPopulate that = (AppPopulate)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AppPopulate { ");
		sb.append(" }");
		return sb.toString();
	}
}
