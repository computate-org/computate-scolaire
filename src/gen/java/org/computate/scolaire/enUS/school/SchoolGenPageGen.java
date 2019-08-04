package org.computate.scolaire.enUS.school;

import java.util.Objects;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.school.School;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.SchoolGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolGenPageGen<DEV> extends ClusterPage {

	////////////////
	// listSchool //
	////////////////

	/**	L'entité « listSchool »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<School> listSchool;
	public Wrap<SearchList<School>> listSchoolWrap = new Wrap<SearchList<School>>().p(this).c(SearchList.class).var("listSchool").o(listSchool);

	/**	<br/>L'entité « listSchool »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.SchoolGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchool">Trouver l'entité listSchool dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchool(Wrap<SearchList<School>> c);

	public SearchList<School> getListSchool() {
		return listSchool;
	}

	public void setListSchool(SearchList<School> listSchool) {
		this.listSchool = listSchool;
		this.listSchoolWrap.alreadyInitialized = true;
	}
	protected SchoolGenPage listSchoolInit() {
		if(!listSchoolWrap.alreadyInitialized) {
			_listSchool(listSchoolWrap);
			if(listSchool == null)
				setListSchool(listSchoolWrap.o);
		}
		if(listSchool != null)
			listSchool.initDeepForClass(siteRequest_);
		listSchoolWrap.alreadyInitialized(true);
		return (SchoolGenPage)this;
	}

	////////////
	// school //
	////////////

	/**	L'entité « school »
	 *	 is defined as null before being initialized. 
	 */
	protected School school;
	public Wrap<School> schoolWrap = new Wrap<School>().p(this).c(School.class).var("school").o(school);

	/**	<br/>L'entité « school »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.SchoolGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school">Trouver l'entité school dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _school(Wrap<School> c);

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
		this.schoolWrap.alreadyInitialized = true;
	}
	protected SchoolGenPage schoolInit() {
		if(!schoolWrap.alreadyInitialized) {
			_school(schoolWrap);
			if(school == null)
				setSchool(schoolWrap.o);
		}
		if(school != null)
			school.initDeepForClass(siteRequest_);
		schoolWrap.alreadyInitialized(true);
		return (SchoolGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolGenPage = false;

	public SchoolGenPage initDeepSchoolGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolGenPage) {
			alreadyInitializedSchoolGenPage = true;
			initDeepSchoolGenPage();
		}
		return (SchoolGenPage)this;
	}

	public void initDeepSchoolGenPage() {
		super.initDeepClusterPage(siteRequest_);
		initSchoolGenPage();
	}

	public void initSchoolGenPage() {
		listSchoolInit();
		schoolInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchool != null)
			listSchool.setSiteRequest_(siteRequest_);
		if(school != null)
			school.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolGenPage(String var) {
		SchoolGenPage oSchoolGenPage = (SchoolGenPage)this;
		switch(var) {
			case "listSchool":
				return oSchoolGenPage.listSchool;
			case "school":
				return oSchoolGenPage.school;
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
				o = attributeSchoolGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolGenPage(String var, Object val) {
		SchoolGenPage oSchoolGenPage = (SchoolGenPage)this;
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
					o = defineSchoolGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSchoolGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsSchoolGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSchoolGenPage();
		super.htmlScript();
	}

	public void htmlScriptSchoolGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySchoolGenPage();
		super.htmlBody();
	}

	public void htmlBodySchoolGenPage() {
		if(school != null)
			school.htmlBody();
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSchoolGenPage();
		super.html();
	}

	public void htmlSchoolGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSchoolGenPage();
		super.htmlMeta();
	}

	public void htmlMetaSchoolGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSchoolGenPage();
		super.htmlStyles();
	}

	public void htmlStylesSchoolGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSchoolGenPage();
		super.htmlStyle();
	}

	public void htmlStyleSchoolGenPage() {
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
		if(!(o instanceof SchoolGenPage))
			return false;
		SchoolGenPage that = (SchoolGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
