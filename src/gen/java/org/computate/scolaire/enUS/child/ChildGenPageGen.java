package org.computate.scolaire.enUS.child;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.child.SchoolChild;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ChildGenPageGen<DEV> extends ClusterPage {

	/////////////////////
	// listSchoolChild //
	/////////////////////

	/**	L'entité « listSchoolChild »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolChild> listSchoolChild;
	@JsonIgnore
	public Wrap<SearchList<SchoolChild>> listSchoolChildWrap = new Wrap<SearchList<SchoolChild>>().p(this).c(SearchList.class).var("listSchoolChild").o(listSchoolChild);

	/**	<br/>L'entité « listSchoolChild »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolChild">Trouver l'entité listSchoolChild dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolChild(Wrap<SearchList<SchoolChild>> c);

	public SearchList<SchoolChild> getListSchoolChild() {
		return listSchoolChild;
	}

	public void setListSchoolChild(SearchList<SchoolChild> listSchoolChild) {
		this.listSchoolChild = listSchoolChild;
		this.listSchoolChildWrap.alreadyInitialized = true;
	}
	protected ChildGenPage listSchoolChildInit() {
		if(!listSchoolChildWrap.alreadyInitialized) {
			_listSchoolChild(listSchoolChildWrap);
			if(listSchoolChild == null)
				setListSchoolChild(listSchoolChildWrap.o);
		}
		if(listSchoolChild != null)
			listSchoolChild.initDeepForClass(siteRequest_);
		listSchoolChildWrap.alreadyInitialized(true);
		return (ChildGenPage)this;
	}

	/////////////////
	// schoolChild //
	/////////////////

	/**	L'entité « schoolChild »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolChild schoolChild;
	@JsonIgnore
	public Wrap<SchoolChild> schoolChildWrap = new Wrap<SchoolChild>().p(this).c(SchoolChild.class).var("schoolChild").o(schoolChild);

	/**	<br/>L'entité « schoolChild »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolChild">Trouver l'entité schoolChild dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolChild(Wrap<SchoolChild> c);

	public SchoolChild getSchoolChild() {
		return schoolChild;
	}

	public void setSchoolChild(SchoolChild schoolChild) {
		this.schoolChild = schoolChild;
		this.schoolChildWrap.alreadyInitialized = true;
	}
	protected ChildGenPage schoolChildInit() {
		if(!schoolChildWrap.alreadyInitialized) {
			_schoolChild(schoolChildWrap);
			if(schoolChild == null)
				setSchoolChild(schoolChildWrap.o);
		}
		if(schoolChild != null)
			schoolChild.initDeepForClass(siteRequest_);
		schoolChildWrap.alreadyInitialized(true);
		return (ChildGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedChildGenPage = false;

	public ChildGenPage initDeepChildGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedChildGenPage) {
			alreadyInitializedChildGenPage = true;
			initDeepChildGenPage();
		}
		return (ChildGenPage)this;
	}

	public void initDeepChildGenPage() {
		initChildGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initChildGenPage() {
		listSchoolChildInit();
		schoolChildInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepChildGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestChildGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolChild != null)
			listSchoolChild.setSiteRequest_(siteRequest_);
		if(schoolChild != null)
			schoolChild.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestChildGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainChildGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainChildGenPage(String var) {
		ChildGenPage oChildGenPage = (ChildGenPage)this;
		switch(var) {
			case "listSchoolChild":
				return oChildGenPage.listSchoolChild;
			case "schoolChild":
				return oChildGenPage.schoolChild;
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
				o = attributeChildGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeChildGenPage(String var, Object val) {
		ChildGenPage oChildGenPage = (ChildGenPage)this;
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
					o = defineChildGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineChildGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsChildGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsChildGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptChildGenPage();
		super.htmlScript();
	}

	public void htmlScriptChildGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyChildGenPage();
		super.htmlBody();
	}

	public void htmlBodyChildGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlChildGenPage();
		super.html();
	}

	public void htmlChildGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaChildGenPage();
		super.htmlMeta();
	}

	public void htmlMetaChildGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesChildGenPage();
		super.htmlStyles();
	}

	public void htmlStylesChildGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleChildGenPage();
		super.htmlStyle();
	}

	public void htmlStyleChildGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestChildGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ChildGenPage) {
			ChildGenPage original = (ChildGenPage)o;
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
		if(!(o instanceof ChildGenPage))
			return false;
		ChildGenPage that = (ChildGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ChildGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
