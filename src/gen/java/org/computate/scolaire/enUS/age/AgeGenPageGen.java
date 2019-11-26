package org.computate.scolaire.enUS.age;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.age.SchoolAge;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AgeGenPageGen<DEV> extends ClusterPage {

	///////////////////
	// listSchoolAge //
	///////////////////

	/**	L'entité « listSchoolAge »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SchoolAge> listSchoolAge;
	@JsonIgnore
	public Wrap<SearchList<SchoolAge>> listSchoolAgeWrap = new Wrap<SearchList<SchoolAge>>().p(this).c(SearchList.class).var("listSchoolAge").o(listSchoolAge);

	/**	<br/>L'entité « listSchoolAge »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolAge">Trouver l'entité listSchoolAge dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolAge(Wrap<SearchList<SchoolAge>> c);

	public SearchList<SchoolAge> getListSchoolAge() {
		return listSchoolAge;
	}

	public void setListSchoolAge(SearchList<SchoolAge> listSchoolAge) {
		this.listSchoolAge = listSchoolAge;
		this.listSchoolAgeWrap.alreadyInitialized = true;
	}
	protected AgeGenPage listSchoolAgeInit() {
		if(!listSchoolAgeWrap.alreadyInitialized) {
			_listSchoolAge(listSchoolAgeWrap);
			if(listSchoolAge == null)
				setListSchoolAge(listSchoolAgeWrap.o);
		}
		if(listSchoolAge != null)
			listSchoolAge.initDeepForClass(siteRequest_);
		listSchoolAgeWrap.alreadyInitialized(true);
		return (AgeGenPage)this;
	}

	///////////////
	// schoolAge //
	///////////////

	/**	L'entité « schoolAge »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolAge schoolAge;
	@JsonIgnore
	public Wrap<SchoolAge> schoolAgeWrap = new Wrap<SchoolAge>().p(this).c(SchoolAge.class).var("schoolAge").o(schoolAge);

	/**	<br/>L'entité « schoolAge »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAge">Trouver l'entité schoolAge dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolAge(Wrap<SchoolAge> c);

	public SchoolAge getSchoolAge() {
		return schoolAge;
	}

	public void setSchoolAge(SchoolAge schoolAge) {
		this.schoolAge = schoolAge;
		this.schoolAgeWrap.alreadyInitialized = true;
	}
	protected AgeGenPage schoolAgeInit() {
		if(!schoolAgeWrap.alreadyInitialized) {
			_schoolAge(schoolAgeWrap);
			if(schoolAge == null)
				setSchoolAge(schoolAgeWrap.o);
		}
		if(schoolAge != null)
			schoolAge.initDeepForClass(siteRequest_);
		schoolAgeWrap.alreadyInitialized(true);
		return (AgeGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedAgeGenPage = false;

	public AgeGenPage initDeepAgeGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedAgeGenPage) {
			alreadyInitializedAgeGenPage = true;
			initDeepAgeGenPage();
		}
		return (AgeGenPage)this;
	}

	public void initDeepAgeGenPage() {
		initAgeGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initAgeGenPage() {
		listSchoolAgeInit();
		schoolAgeInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepAgeGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAgeGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolAge != null)
			listSchoolAge.setSiteRequest_(siteRequest_);
		if(schoolAge != null)
			schoolAge.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestAgeGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAgeGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainAgeGenPage(String var) {
		AgeGenPage oAgeGenPage = (AgeGenPage)this;
		switch(var) {
			case "listSchoolAge":
				return oAgeGenPage.listSchoolAge;
			case "schoolAge":
				return oAgeGenPage.schoolAge;
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
				o = attributeAgeGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeAgeGenPage(String var, Object val) {
		AgeGenPage oAgeGenPage = (AgeGenPage)this;
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
					o = defineAgeGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineAgeGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsAgeGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsAgeGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptAgeGenPage();
		super.htmlScript();
	}

	public void htmlScriptAgeGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyAgeGenPage();
		super.htmlBody();
	}

	public void htmlBodyAgeGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlAgeGenPage();
		super.html();
	}

	public void htmlAgeGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaAgeGenPage();
		super.htmlMeta();
	}

	public void htmlMetaAgeGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesAgeGenPage();
		super.htmlStyles();
	}

	public void htmlStylesAgeGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleAgeGenPage();
		super.htmlStyle();
	}

	public void htmlStyleAgeGenPage() {
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
		if(!(o instanceof AgeGenPage))
			return false;
		AgeGenPage that = (AgeGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AgeGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
