package org.computate.scolaire.enUS.child;

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
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class ChildGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ChildGenPage.class);

	/////////////////////
	// listSchoolChild //
	/////////////////////

	/**	 The entity listSchoolChild
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolChild> listSchoolChild;
	@JsonIgnore
	public Wrap<SearchList<SchoolChild>> listSchoolChildWrap = new Wrap<SearchList<SchoolChild>>().p(this).c(SearchList.class).var("listSchoolChild").o(listSchoolChild);

	/**	<br/> The entity listSchoolChild
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolChild">Find the entity listSchoolChild in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolChild(Wrap<SearchList<SchoolChild>> c);

	public SearchList<SchoolChild> getListSchoolChild() {
		return listSchoolChild;
	}

	public void setListSchoolChild(SearchList<SchoolChild> listSchoolChild) {
		this.listSchoolChild = listSchoolChild;
		this.listSchoolChildWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolChild> staticSetListSchoolChild(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	//////////////////
	// schoolChild_ //
	//////////////////

	/**	 The entity schoolChild_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolChild schoolChild_;
	@JsonIgnore
	public Wrap<SchoolChild> schoolChild_Wrap = new Wrap<SchoolChild>().p(this).c(SchoolChild.class).var("schoolChild_").o(schoolChild_);

	/**	<br/> The entity schoolChild_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolChild_">Find the entity schoolChild_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolChild_(Wrap<SchoolChild> c);

	public SchoolChild getSchoolChild_() {
		return schoolChild_;
	}

	public void setSchoolChild_(SchoolChild schoolChild_) {
		this.schoolChild_ = schoolChild_;
		this.schoolChild_Wrap.alreadyInitialized = true;
	}
	public static SchoolChild staticSetSchoolChild_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected ChildGenPage schoolChild_Init() {
		if(!schoolChild_Wrap.alreadyInitialized) {
			_schoolChild_(schoolChild_Wrap);
			if(schoolChild_ == null)
				setSchoolChild_(schoolChild_Wrap.o);
		}
		schoolChild_Wrap.alreadyInitialized(true);
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
		schoolChild_Init();
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
			case "schoolChild_":
				return oChildGenPage.schoolChild_;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetChildGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetChildGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrChildGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrChildGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrChildGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrChildGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqChildGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqChildGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrFqClusterPage(entityVar,  siteRequest_, o);
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
