package org.computate.scolaire.enUS.block;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.block.SchoolBlock;
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
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class BlockGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(BlockGenPage.class);

	/////////////////////
	// listSchoolBlock //
	/////////////////////

	/**	 The entity listSchoolBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolBlock> listSchoolBlock;
	@JsonIgnore
	public Wrap<SearchList<SchoolBlock>> listSchoolBlockWrap = new Wrap<SearchList<SchoolBlock>>().p(this).c(SearchList.class).var("listSchoolBlock").o(listSchoolBlock);

	/**	<br/> The entity listSchoolBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolBlock">Find the entity listSchoolBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolBlock(Wrap<SearchList<SchoolBlock>> c);

	public SearchList<SchoolBlock> getListSchoolBlock() {
		return listSchoolBlock;
	}

	public void setListSchoolBlock(SearchList<SchoolBlock> listSchoolBlock) {
		this.listSchoolBlock = listSchoolBlock;
		this.listSchoolBlockWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolBlock> staticSetListSchoolBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected BlockGenPage listSchoolBlockInit() {
		if(!listSchoolBlockWrap.alreadyInitialized) {
			_listSchoolBlock(listSchoolBlockWrap);
			if(listSchoolBlock == null)
				setListSchoolBlock(listSchoolBlockWrap.o);
		}
		if(listSchoolBlock != null)
			listSchoolBlock.initDeepForClass(siteRequest_);
		listSchoolBlockWrap.alreadyInitialized(true);
		return (BlockGenPage)this;
	}

	//////////////////
	// schoolBlock_ //
	//////////////////

	/**	 The entity schoolBlock_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock schoolBlock_;
	@JsonIgnore
	public Wrap<SchoolBlock> schoolBlock_Wrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("schoolBlock_").o(schoolBlock_);

	/**	<br/> The entity schoolBlock_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolBlock_">Find the entity schoolBlock_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolBlock_(Wrap<SchoolBlock> c);

	public SchoolBlock getSchoolBlock_() {
		return schoolBlock_;
	}

	public void setSchoolBlock_(SchoolBlock schoolBlock_) {
		this.schoolBlock_ = schoolBlock_;
		this.schoolBlock_Wrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetSchoolBlock_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected BlockGenPage schoolBlock_Init() {
		if(!schoolBlock_Wrap.alreadyInitialized) {
			_schoolBlock_(schoolBlock_Wrap);
			if(schoolBlock_ == null)
				setSchoolBlock_(schoolBlock_Wrap.o);
		}
		schoolBlock_Wrap.alreadyInitialized(true);
		return (BlockGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedBlockGenPage = false;

	public BlockGenPage initDeepBlockGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedBlockGenPage) {
			alreadyInitializedBlockGenPage = true;
			initDeepBlockGenPage();
		}
		return (BlockGenPage)this;
	}

	public void initDeepBlockGenPage() {
		initBlockGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initBlockGenPage() {
		listSchoolBlockInit();
		schoolBlock_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepBlockGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBlockGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolBlock != null)
			listSchoolBlock.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestBlockGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBlockGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainBlockGenPage(String var) {
		BlockGenPage oBlockGenPage = (BlockGenPage)this;
		switch(var) {
			case "listSchoolBlock":
				return oBlockGenPage.listSchoolBlock;
			case "schoolBlock_":
				return oBlockGenPage.schoolBlock_;
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
				o = attributeBlockGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeBlockGenPage(String var, Object val) {
		BlockGenPage oBlockGenPage = (BlockGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetBlockGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetBlockGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrBlockGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrBlockGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrBlockGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrBlockGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqBlockGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqBlockGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineBlockGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineBlockGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsBlockGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsBlockGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptBlockGenPage();
		super.htmlScript();
	}

	public void htmlScriptBlockGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyBlockGenPage();
		super.htmlBody();
	}

	public void htmlBodyBlockGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlBlockGenPage();
		super.html();
	}

	public void htmlBlockGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaBlockGenPage();
		super.htmlMeta();
	}

	public void htmlMetaBlockGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesBlockGenPage();
		super.htmlStyles();
	}

	public void htmlStylesBlockGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleBlockGenPage();
		super.htmlStyle();
	}

	public void htmlStyleBlockGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestBlockGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof BlockGenPage) {
			BlockGenPage original = (BlockGenPage)o;
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
		if(!(o instanceof BlockGenPage))
			return false;
		BlockGenPage that = (BlockGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("BlockGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
