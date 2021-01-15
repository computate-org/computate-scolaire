package org.computate.scolaire.enUS.receipt;

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
import org.computate.scolaire.enUS.receipt.SchoolReceipt;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.ReceiptGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class ReceiptGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ReceiptGenPage.class);

	///////////////////////
	// listSchoolReceipt //
	///////////////////////

	/**	 The entity listSchoolReceipt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolReceipt> listSchoolReceipt;
	@JsonIgnore
	public Wrap<SearchList<SchoolReceipt>> listSchoolReceiptWrap = new Wrap<SearchList<SchoolReceipt>>().p(this).c(SearchList.class).var("listSchoolReceipt").o(listSchoolReceipt);

	/**	<br/> The entity listSchoolReceipt
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.ReceiptGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolReceipt">Find the entity listSchoolReceipt in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolReceipt(Wrap<SearchList<SchoolReceipt>> c);

	public SearchList<SchoolReceipt> getListSchoolReceipt() {
		return listSchoolReceipt;
	}

	public void setListSchoolReceipt(SearchList<SchoolReceipt> listSchoolReceipt) {
		this.listSchoolReceipt = listSchoolReceipt;
		this.listSchoolReceiptWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolReceipt> staticSetListSchoolReceipt(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected ReceiptGenPage listSchoolReceiptInit() {
		if(!listSchoolReceiptWrap.alreadyInitialized) {
			_listSchoolReceipt(listSchoolReceiptWrap);
			if(listSchoolReceipt == null)
				setListSchoolReceipt(listSchoolReceiptWrap.o);
		}
		if(listSchoolReceipt != null)
			listSchoolReceipt.initDeepForClass(siteRequest_);
		listSchoolReceiptWrap.alreadyInitialized(true);
		return (ReceiptGenPage)this;
	}

	////////////////////
	// schoolReceipt_ //
	////////////////////

	/**	 The entity schoolReceipt_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolReceipt schoolReceipt_;
	@JsonIgnore
	public Wrap<SchoolReceipt> schoolReceipt_Wrap = new Wrap<SchoolReceipt>().p(this).c(SchoolReceipt.class).var("schoolReceipt_").o(schoolReceipt_);

	/**	<br/> The entity schoolReceipt_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.ReceiptGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolReceipt_">Find the entity schoolReceipt_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolReceipt_(Wrap<SchoolReceipt> c);

	public SchoolReceipt getSchoolReceipt_() {
		return schoolReceipt_;
	}

	public void setSchoolReceipt_(SchoolReceipt schoolReceipt_) {
		this.schoolReceipt_ = schoolReceipt_;
		this.schoolReceipt_Wrap.alreadyInitialized = true;
	}
	public static SchoolReceipt staticSetSchoolReceipt_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected ReceiptGenPage schoolReceipt_Init() {
		if(!schoolReceipt_Wrap.alreadyInitialized) {
			_schoolReceipt_(schoolReceipt_Wrap);
			if(schoolReceipt_ == null)
				setSchoolReceipt_(schoolReceipt_Wrap.o);
		}
		schoolReceipt_Wrap.alreadyInitialized(true);
		return (ReceiptGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedReceiptGenPage = false;

	public ReceiptGenPage initDeepReceiptGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedReceiptGenPage) {
			alreadyInitializedReceiptGenPage = true;
			initDeepReceiptGenPage();
		}
		return (ReceiptGenPage)this;
	}

	public void initDeepReceiptGenPage() {
		initReceiptGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initReceiptGenPage() {
		listSchoolReceiptInit();
		schoolReceipt_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepReceiptGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestReceiptGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolReceipt != null)
			listSchoolReceipt.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestReceiptGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainReceiptGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainReceiptGenPage(String var) {
		ReceiptGenPage oReceiptGenPage = (ReceiptGenPage)this;
		switch(var) {
			case "listSchoolReceipt":
				return oReceiptGenPage.listSchoolReceipt;
			case "schoolReceipt_":
				return oReceiptGenPage.schoolReceipt_;
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
				o = attributeReceiptGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeReceiptGenPage(String var, Object val) {
		ReceiptGenPage oReceiptGenPage = (ReceiptGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetReceiptGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetReceiptGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrReceiptGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrReceiptGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrReceiptGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrReceiptGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqReceiptGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqReceiptGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineReceiptGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineReceiptGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsReceiptGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsReceiptGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptReceiptGenPage();
		super.htmlScript();
	}

	public void htmlScriptReceiptGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyReceiptGenPage();
		super.htmlBody();
	}

	public void htmlBodyReceiptGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlReceiptGenPage();
		super.html();
	}

	public void htmlReceiptGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaReceiptGenPage();
		super.htmlMeta();
	}

	public void htmlMetaReceiptGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesReceiptGenPage();
		super.htmlStyles();
	}

	public void htmlStylesReceiptGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleReceiptGenPage();
		super.htmlStyle();
	}

	public void htmlStyleReceiptGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestReceiptGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ReceiptGenPage) {
			ReceiptGenPage original = (ReceiptGenPage)o;
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
		if(!(o instanceof ReceiptGenPage))
			return false;
		ReceiptGenPage that = (ReceiptGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ReceiptGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
