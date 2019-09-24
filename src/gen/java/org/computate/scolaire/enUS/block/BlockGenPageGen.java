package org.computate.scolaire.enUS.block;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import org.computate.scolaire.enUS.block.SchoolBlock;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class BlockGenPageGen<DEV> extends ClusterPage {

	/////////////////////
	// listSchoolBlock //
	/////////////////////

	/**	L'entité « listSchoolBlock »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SchoolBlock> listSchoolBlock;
	@JsonIgnore
	public Wrap<SearchList<SchoolBlock>> listSchoolBlockWrap = new Wrap<SearchList<SchoolBlock>>().p(this).c(SearchList.class).var("listSchoolBlock").o(listSchoolBlock);

	/**	<br/>L'entité « listSchoolBlock »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolBlock">Trouver l'entité listSchoolBlock dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolBlock(Wrap<SearchList<SchoolBlock>> c);

	public SearchList<SchoolBlock> getListSchoolBlock() {
		return listSchoolBlock;
	}

	public void setListSchoolBlock(SearchList<SchoolBlock> listSchoolBlock) {
		this.listSchoolBlock = listSchoolBlock;
		this.listSchoolBlockWrap.alreadyInitialized = true;
	}
	protected BlockGenPage listSchoolBlockInit() {
		if(!listSchoolBlockWrap.alreadyInitialized) {
			_listSchoolBlock(listSchoolBlockWrap);
			if(listSchoolBlock == null)
				setListSchoolBlock(listSchoolBlockWrap.o);
		}
		listSchoolBlockWrap.alreadyInitialized(true);
		return (BlockGenPage)this;
	}

	/////////////////
	// schoolBlock //
	/////////////////

	/**	L'entité « schoolBlock »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolBlock schoolBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> schoolBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("schoolBlock").o(schoolBlock);

	/**	<br/>L'entité « schoolBlock »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolBlock">Trouver l'entité schoolBlock dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getSchoolBlock() {
		return schoolBlock;
	}

	public void setSchoolBlock(SchoolBlock schoolBlock) {
		this.schoolBlock = schoolBlock;
		this.schoolBlockWrap.alreadyInitialized = true;
	}
	protected BlockGenPage schoolBlockInit() {
		if(!schoolBlockWrap.alreadyInitialized) {
			_schoolBlock(schoolBlockWrap);
			if(schoolBlock == null)
				setSchoolBlock(schoolBlockWrap.o);
		}
		schoolBlockWrap.alreadyInitialized(true);
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
		super.initDeepClusterPage(siteRequest_);
		initBlockGenPage();
	}

	public void initBlockGenPage() {
		listSchoolBlockInit();
		schoolBlockInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepBlockGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBlockGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
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
			case "schoolBlock":
				return oBlockGenPage.schoolBlock;
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
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
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
