package org.computate.scolaire.enUS.school;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.school.SchoolGenPage;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.SchoolPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolPageGen<DEV> extends SchoolGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolPage = false;

	public SchoolPage initDeepSchoolPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolPage) {
			alreadyInitializedSchoolPage = true;
			initDeepSchoolPage();
		}
		return (SchoolPage)this;
	}

	public void initDeepSchoolPage() {
		initSchoolPage();
		super.initDeepSchoolGenPage(siteRequest_);
	}

	public void initSchoolPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestSchoolGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolPage(String var) {
		SchoolPage oSchoolPage = (SchoolPage)this;
		switch(var) {
			default:
				return super.obtainSchoolGenPage(var);
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
				o = attributeSchoolPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolPage(String var, Object val) {
		SchoolPage oSchoolPage = (SchoolPage)this;
		switch(var) {
			default:
				return super.attributeSchoolGenPage(var, val);
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
					o = defineSchoolPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolPage(String var, String val) {
		switch(var) {
			default:
				return super.defineSchoolGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSchoolPage();
		super.htmlScripts();
	}

	public void htmlScriptsSchoolPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSchoolPage();
		super.htmlScript();
	}

	public void htmlScriptSchoolPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySchoolPage();
		super.htmlBody();
	}

	public void htmlBodySchoolPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSchoolPage();
		super.html();
	}

	public void htmlSchoolPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSchoolPage();
		super.htmlMeta();
	}

	public void htmlMetaSchoolPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSchoolPage();
		super.htmlStyles();
	}

	public void htmlStylesSchoolPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSchoolPage();
		super.htmlStyle();
	}

	public void htmlStyleSchoolPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		SchoolPage original = (SchoolPage)Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(original != null) {
			super.apiRequestSchoolGenPage();
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
		if(!(o instanceof SchoolPage))
			return false;
		SchoolPage that = (SchoolPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
