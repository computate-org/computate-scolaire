package org.computate.enUS.academic.vertx;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.apache.commons.text.StringEscapeUtils;
import java.lang.Object;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Exception;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.academic.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AppPopulateGen<DEV> extends Object {

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	L'entité « siteRequest_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteRequestEnUS siteRequest_;
	public Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("siteRequest_").o(siteRequest_);

	/**	<br/>L'entité « siteRequest_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.academic.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequestEnUS> c) throws Exception, Exception;

	public SiteRequestEnUS getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequestEnUS siteRequest_) {
		this.siteRequest_ = siteRequest_;
		this.siteRequest_Wrap.alreadyInitialized = true;
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

	/**	L'entité « siteContext »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SiteContextEnUS(). 
	 */
	protected SiteContextEnUS siteContext = new SiteContextEnUS();
	public Wrap<SiteContextEnUS> siteContextWrap = new Wrap<SiteContextEnUS>().p(this).c(SiteContextEnUS.class).var("siteContext").o(siteContext);

	/**	<br/>L'entité « siteContext »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SiteContextEnUS(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.academic.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteContext">Trouver l'entité siteContext dans Solr</a>
	 * <br/>
	 * @param siteContext est l'entité déjà construit. 
	 **/
	protected abstract void _siteContext(SiteContextEnUS o) throws Exception, Exception;

	public SiteContextEnUS getSiteContext() {
		return siteContext;
	}

	public void setSiteContext(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		this.siteContextWrap.alreadyInitialized = true;
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

	/**	L'entité « siteConfig »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteConfig siteConfig;
	public Wrap<SiteConfig> siteConfigWrap = new Wrap<SiteConfig>().p(this).c(SiteConfig.class).var("siteConfig").o(siteConfig);

	/**	<br/>L'entité « siteConfig »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.academic.vertx.AppPopulate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteConfig">Trouver l'entité siteConfig dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteConfig(Wrap<SiteConfig> c) throws Exception, Exception;

	public SiteConfig getSiteConfig() {
		return siteConfig;
	}

	public void setSiteConfig(SiteConfig siteConfig) {
		this.siteConfig = siteConfig;
		this.siteConfigWrap.alreadyInitialized = true;
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
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineAppPopulate(String var, String val) {
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
		sb.append("AppPopulate {");
		sb.append(" }");
		return sb.toString();
	}
}
