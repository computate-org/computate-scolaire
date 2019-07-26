package org.computate.scolaire.enUS.school;

import java.util.Objects;
import org.computate.scolaire.enUS.cluster.ClusterEnUSPage;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.school.School;
import org.apache.commons.text.StringEscapeUtils;
import java.lang.String;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.EcoleEnUSGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleEnUSGenPageGen<DEV> extends ClusterEnUSPage {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.EcoleEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchool">Trouver l'entité listSchool dans Solr</a>
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
	protected EcoleEnUSGenPage listSchoolInit() {
		if(!listSchoolWrap.alreadyInitialized) {
			_listSchool(listSchoolWrap);
			if(listSchool == null)
				setListSchool(listSchoolWrap.o);
		}
		if(listSchool != null)
			listSchool.initDeepForClass(siteRequest_);
		listSchoolWrap.alreadyInitialized(true);
		return (EcoleEnUSGenPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.EcoleEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school">Trouver l'entité school dans Solr</a>
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
	protected EcoleEnUSGenPage schoolInit() {
		if(!schoolWrap.alreadyInitialized) {
			_school(schoolWrap);
			if(school == null)
				setSchool(schoolWrap.o);
		}
		if(school != null)
			school.initDeepForClass(siteRequest_);
		schoolWrap.alreadyInitialized(true);
		return (EcoleEnUSGenPage)this;
	}

	///////////////////
	// pageUriSchool //
	///////////////////

	/**	L'entité « pageUriSchool »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriSchool;
	public Wrap<String> pageUriSchoolWrap = new Wrap<String>().p(this).c(String.class).var("pageUriSchool").o(pageUriSchool);

	/**	<br/>L'entité « pageUriSchool »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.EcoleEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUriSchool">Trouver l'entité pageUriSchool dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriSchool(Wrap<String> c);

	public String getPageUriSchool() {
		return pageUriSchool;
	}

	public void setPageUriSchool(String pageUriSchool) {
		this.pageUriSchool = pageUriSchool;
		this.pageUriSchoolWrap.alreadyInitialized = true;
	}
	protected EcoleEnUSGenPage pageUriSchoolInit() {
		if(!pageUriSchoolWrap.alreadyInitialized) {
			_pageUriSchool(pageUriSchoolWrap);
			if(pageUriSchool == null)
				setPageUriSchool(pageUriSchoolWrap.o);
		}
		pageUriSchoolWrap.alreadyInitialized(true);
		return (EcoleEnUSGenPage)this;
	}

	public String solrPageUriSchool() {
		return pageUriSchool;
	}

	public String strPageUriSchool() {
		return pageUriSchool == null ? "" : pageUriSchool;
	}

	public String nomAffichagePageUriSchool() {
		return null;
	}

	public String htmTooltipPageUriSchool() {
		return null;
	}

	public String htmPageUriSchool() {
		return pageUriSchool == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriSchool());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEcoleEnUSGenPage = false;

	public EcoleEnUSGenPage initDeepEcoleEnUSGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEcoleEnUSGenPage) {
			alreadyInitializedEcoleEnUSGenPage = true;
			initDeepEcoleEnUSGenPage();
		}
		return (EcoleEnUSGenPage)this;
	}

	public void initDeepEcoleEnUSGenPage() {
		super.initDeepClusterEnUSPage(siteRequest_);
		initEcoleEnUSGenPage();
	}

	public void initEcoleEnUSGenPage() {
		listSchoolInit();
		schoolInit();
		pageUriSchoolInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEcoleEnUSGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEcoleEnUSGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterEnUSPage(siteRequest_);
		if(listSchool != null)
			listSchool.setSiteRequest_(siteRequest_);
		if(school != null)
			school.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEcoleEnUSGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEcoleEnUSGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEcoleEnUSGenPage(String var) {
		EcoleEnUSGenPage oEcoleEnUSGenPage = (EcoleEnUSGenPage)this;
		switch(var) {
			case "listSchool":
				return oEcoleEnUSGenPage.listSchool;
			case "school":
				return oEcoleEnUSGenPage.school;
			case "pageUriSchool":
				return oEcoleEnUSGenPage.pageUriSchool;
			default:
				return super.obtainClusterEnUSPage(var);
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
				o = attributeEcoleEnUSGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEcoleEnUSGenPage(String var, Object val) {
		EcoleEnUSGenPage oEcoleEnUSGenPage = (EcoleEnUSGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterEnUSPage(var, val);
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
					o = defineEcoleEnUSGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEcoleEnUSGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterEnUSPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEcoleEnUSGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsEcoleEnUSGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEcoleEnUSGenPage();
		super.htmlScript();
	}

	public void htmlScriptEcoleEnUSGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEcoleEnUSGenPage();
		super.htmlBody();
	}

	public void htmlBodyEcoleEnUSGenPage() {
		if(school != null)
			school.htmlBody();
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEcoleEnUSGenPage();
		super.html();
	}

	public void htmlEcoleEnUSGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEcoleEnUSGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEcoleEnUSGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEcoleEnUSGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEcoleEnUSGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEcoleEnUSGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEcoleEnUSGenPage() {
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
		if(!(o instanceof EcoleEnUSGenPage))
			return false;
		EcoleEnUSGenPage that = (EcoleEnUSGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleEnUSGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
