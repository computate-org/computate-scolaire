package org.computate.scolaire.enUS.dad;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
import org.computate.scolaire.enUS.dad.SchoolDad;
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
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.DadGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class DadGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DadGenPage.class);

	///////////////////
	// listSchoolDad //
	///////////////////

	/**	 The entity listSchoolDad
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolDad> listSchoolDad;
	@JsonIgnore
	public Wrap<SearchList<SchoolDad>> listSchoolDadWrap = new Wrap<SearchList<SchoolDad>>().p(this).c(SearchList.class).var("listSchoolDad").o(listSchoolDad);

	/**	<br/> The entity listSchoolDad
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.DadGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolDad">Find the entity listSchoolDad in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolDad(Wrap<SearchList<SchoolDad>> c);

	public SearchList<SchoolDad> getListSchoolDad() {
		return listSchoolDad;
	}

	public void setListSchoolDad(SearchList<SchoolDad> listSchoolDad) {
		this.listSchoolDad = listSchoolDad;
		this.listSchoolDadWrap.alreadyInitialized = true;
	}
	protected DadGenPage listSchoolDadInit() {
		if(!listSchoolDadWrap.alreadyInitialized) {
			_listSchoolDad(listSchoolDadWrap);
			if(listSchoolDad == null)
				setListSchoolDad(listSchoolDadWrap.o);
		}
		if(listSchoolDad != null)
			listSchoolDad.initDeepForClass(siteRequest_);
		listSchoolDadWrap.alreadyInitialized(true);
		return (DadGenPage)this;
	}

	///////////////
	// schoolDad //
	///////////////

	/**	 The entity schoolDad
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolDad schoolDad;
	@JsonIgnore
	public Wrap<SchoolDad> schoolDadWrap = new Wrap<SchoolDad>().p(this).c(SchoolDad.class).var("schoolDad").o(schoolDad);

	/**	<br/> The entity schoolDad
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.DadGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolDad">Find the entity schoolDad in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolDad(Wrap<SchoolDad> c);

	public SchoolDad getSchoolDad() {
		return schoolDad;
	}

	public void setSchoolDad(SchoolDad schoolDad) {
		this.schoolDad = schoolDad;
		this.schoolDadWrap.alreadyInitialized = true;
	}
	protected DadGenPage schoolDadInit() {
		if(!schoolDadWrap.alreadyInitialized) {
			_schoolDad(schoolDadWrap);
			if(schoolDad == null)
				setSchoolDad(schoolDadWrap.o);
		}
		if(schoolDad != null)
			schoolDad.initDeepForClass(siteRequest_);
		schoolDadWrap.alreadyInitialized(true);
		return (DadGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedDadGenPage = false;

	public DadGenPage initDeepDadGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedDadGenPage) {
			alreadyInitializedDadGenPage = true;
			initDeepDadGenPage();
		}
		return (DadGenPage)this;
	}

	public void initDeepDadGenPage() {
		initDadGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initDadGenPage() {
		listSchoolDadInit();
		schoolDadInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepDadGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestDadGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolDad != null)
			listSchoolDad.setSiteRequest_(siteRequest_);
		if(schoolDad != null)
			schoolDad.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestDadGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainDadGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainDadGenPage(String var) {
		DadGenPage oDadGenPage = (DadGenPage)this;
		switch(var) {
			case "listSchoolDad":
				return oDadGenPage.listSchoolDad;
			case "schoolDad":
				return oDadGenPage.schoolDad;
			default:
				return super.obtainClusterPage(var);
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
				o = attributeDadGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeDadGenPage(String var, Object val) {
		DadGenPage oDadGenPage = (DadGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
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
					o = defineDadGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineDadGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDadGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsDadGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDadGenPage();
		super.htmlScript();
	}

	public void htmlScriptDadGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDadGenPage();
		super.htmlBody();
	}

	public void htmlBodyDadGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDadGenPage();
		super.html();
	}

	public void htmlDadGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDadGenPage();
		super.htmlMeta();
	}

	public void htmlMetaDadGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDadGenPage();
		super.htmlStyles();
	}

	public void htmlStylesDadGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDadGenPage();
		super.htmlStyle();
	}

	public void htmlStyleDadGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestDadGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof DadGenPage) {
			DadGenPage original = (DadGenPage)o;
			super.apiRequestClusterPage();
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
		if(!(o instanceof DadGenPage))
			return false;
		DadGenPage that = (DadGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DadGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
