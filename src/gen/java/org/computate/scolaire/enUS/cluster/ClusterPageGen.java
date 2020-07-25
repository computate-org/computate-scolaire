package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.cluster.ClusterGenPage;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class ClusterPageGen<DEV> extends ClusterGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ClusterPage.class);

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedClusterPage = false;

	public ClusterPage initDeepClusterPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedClusterPage) {
			alreadyInitializedClusterPage = true;
			initDeepClusterPage();
		}
		return (ClusterPage)this;
	}

	public void initDeepClusterPage() {
		initClusterPage();
		super.initDeepClusterGenPage(siteRequest_);
	}

	public void initClusterPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepClusterPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestClusterPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainClusterPage(String var) {
		ClusterPage oClusterPage = (ClusterPage)this;
		switch(var) {
			default:
				return super.obtainClusterGenPage(var);
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
				o = attributeClusterPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeClusterPage(String var, Object val) {
		ClusterPage oClusterPage = (ClusterPage)this;
		switch(var) {
			default:
				return super.attributeClusterGenPage(var, val);
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
					o = defineClusterPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineClusterPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterGenPage(var, val);
		}
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptClusterPage();
		super.htmlScript();
	}

	public void htmlScriptClusterPage() {
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsClusterPage();
		super.htmlScripts();
	}

	public void htmlScriptsClusterPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyClusterPage();
		super.htmlBody();
	}

	public void htmlBodyClusterPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlClusterPage();
		super.html();
	}

	public void htmlClusterPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaClusterPage();
		super.htmlMeta();
	}

	public void htmlMetaClusterPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesClusterPage();
		super.htmlStyles();
	}

	public void htmlStylesClusterPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleClusterPage();
		super.htmlStyle();
	}

	public void htmlStyleClusterPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestClusterPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ClusterPage) {
			ClusterPage original = (ClusterPage)o;
			super.apiRequestClusterGenPage();
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
		if(!(o instanceof ClusterPage))
			return false;
		ClusterPage that = (ClusterPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ClusterPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
