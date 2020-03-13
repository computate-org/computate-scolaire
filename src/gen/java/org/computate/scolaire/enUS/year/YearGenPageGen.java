package org.computate.scolaire.enUS.year;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.YearGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class YearGenPageGen<DEV> extends ClusterPage {

	////////////////////
	// listSchoolYear //
	////////////////////

	/**	L'entité « listSchoolYear »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolYear> listSchoolYear;
	@JsonIgnore
	public Wrap<SearchList<SchoolYear>> listSchoolYearWrap = new Wrap<SearchList<SchoolYear>>().p(this).c(SearchList.class).var("listSchoolYear").o(listSchoolYear);

	/**	<br/>L'entité « listSchoolYear »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.YearGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolYear">Trouver l'entité listSchoolYear dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolYear(Wrap<SearchList<SchoolYear>> c);

	public SearchList<SchoolYear> getListSchoolYear() {
		return listSchoolYear;
	}

	public void setListSchoolYear(SearchList<SchoolYear> listSchoolYear) {
		this.listSchoolYear = listSchoolYear;
		this.listSchoolYearWrap.alreadyInitialized = true;
	}
	protected YearGenPage listSchoolYearInit() {
		if(!listSchoolYearWrap.alreadyInitialized) {
			_listSchoolYear(listSchoolYearWrap);
			if(listSchoolYear == null)
				setListSchoolYear(listSchoolYearWrap.o);
		}
		if(listSchoolYear != null)
			listSchoolYear.initDeepForClass(siteRequest_);
		listSchoolYearWrap.alreadyInitialized(true);
		return (YearGenPage)this;
	}

	////////////////
	// schoolYear //
	////////////////

	/**	L'entité « schoolYear »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolYear schoolYear;
	@JsonIgnore
	public Wrap<SchoolYear> schoolYearWrap = new Wrap<SchoolYear>().p(this).c(SchoolYear.class).var("schoolYear").o(schoolYear);

	/**	<br/>L'entité « schoolYear »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.YearGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolYear">Trouver l'entité schoolYear dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolYear(Wrap<SchoolYear> c);

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
		this.schoolYearWrap.alreadyInitialized = true;
	}
	protected YearGenPage schoolYearInit() {
		if(!schoolYearWrap.alreadyInitialized) {
			_schoolYear(schoolYearWrap);
			if(schoolYear == null)
				setSchoolYear(schoolYearWrap.o);
		}
		if(schoolYear != null)
			schoolYear.initDeepForClass(siteRequest_);
		schoolYearWrap.alreadyInitialized(true);
		return (YearGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedYearGenPage = false;

	public YearGenPage initDeepYearGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedYearGenPage) {
			alreadyInitializedYearGenPage = true;
			initDeepYearGenPage();
		}
		return (YearGenPage)this;
	}

	public void initDeepYearGenPage() {
		initYearGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initYearGenPage() {
		listSchoolYearInit();
		schoolYearInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepYearGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestYearGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolYear != null)
			listSchoolYear.setSiteRequest_(siteRequest_);
		if(schoolYear != null)
			schoolYear.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestYearGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainYearGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainYearGenPage(String var) {
		YearGenPage oYearGenPage = (YearGenPage)this;
		switch(var) {
			case "listSchoolYear":
				return oYearGenPage.listSchoolYear;
			case "schoolYear":
				return oYearGenPage.schoolYear;
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
				o = attributeYearGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeYearGenPage(String var, Object val) {
		YearGenPage oYearGenPage = (YearGenPage)this;
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
					o = defineYearGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineYearGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsYearGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsYearGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptYearGenPage();
		super.htmlScript();
	}

	public void htmlScriptYearGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyYearGenPage();
		super.htmlBody();
	}

	public void htmlBodyYearGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlYearGenPage();
		super.html();
	}

	public void htmlYearGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaYearGenPage();
		super.htmlMeta();
	}

	public void htmlMetaYearGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesYearGenPage();
		super.htmlStyles();
	}

	public void htmlStylesYearGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleYearGenPage();
		super.htmlStyle();
	}

	public void htmlStyleYearGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestYearGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof YearGenPage) {
			YearGenPage original = (YearGenPage)o;
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
		if(!(o instanceof YearGenPage))
			return false;
		YearGenPage that = (YearGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("YearGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
