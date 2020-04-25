package org.computate.scolaire.enUS.enrollment.design;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignGenPage;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentDesignPageGen<DEV> extends EnrollmentDesignGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentDesignPage.class);

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentDesignPage = false;

	public EnrollmentDesignPage initDeepEnrollmentDesignPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentDesignPage) {
			alreadyInitializedEnrollmentDesignPage = true;
			initDeepEnrollmentDesignPage();
		}
		return (EnrollmentDesignPage)this;
	}

	public void initDeepEnrollmentDesignPage() {
		initEnrollmentDesignPage();
		super.initDeepEnrollmentDesignGenPage(siteRequest_);
	}

	public void initEnrollmentDesignPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentDesignPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentDesignPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestEnrollmentDesignGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentDesignPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentDesignPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentDesignPage(String var) {
		EnrollmentDesignPage oEnrollmentDesignPage = (EnrollmentDesignPage)this;
		switch(var) {
			default:
				return super.obtainEnrollmentDesignGenPage(var);
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
				o = attributeEnrollmentDesignPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentDesignPage(String var, Object val) {
		EnrollmentDesignPage oEnrollmentDesignPage = (EnrollmentDesignPage)this;
		switch(var) {
			default:
				return super.attributeEnrollmentDesignGenPage(var, val);
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
					o = defineEnrollmentDesignPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentDesignPage(String var, String val) {
		switch(var) {
			default:
				return super.defineEnrollmentDesignGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentDesignPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentDesignPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentDesignPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentDesignPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentDesignPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentDesignPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentDesignPage();
		super.html();
	}

	public void htmlEnrollmentDesignPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentDesignPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentDesignPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentDesignPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentDesignPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentDesignPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentDesignPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestEnrollmentDesignPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof EnrollmentDesignPage) {
			EnrollmentDesignPage original = (EnrollmentDesignPage)o;
			super.apiRequestEnrollmentDesignGenPage();
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
		if(!(o instanceof EnrollmentDesignPage))
			return false;
		EnrollmentDesignPage that = (EnrollmentDesignPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentDesignPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
