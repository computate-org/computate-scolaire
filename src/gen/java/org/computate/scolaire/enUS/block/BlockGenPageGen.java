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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listSchoolBlock">Trouver l'entité listSchoolBlock dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolBlock(Wrap<SearchList<SchoolBlock>> c);

	public SearchList<SchoolBlock> getListSchoolBlock() {
		return listSchoolBlock;
	}

	public void setListSchoolBlock(SearchList<SchoolBlock> listSchoolBlock) {
		this.listSchoolBlock = listSchoolBlock;
		this.listSchoolBlockWrap.dejaInitialise = true;
	}
	protected BlockGenPage listSchoolBlockInit() {
		if(!listSchoolBlockWrap.dejaInitialise) {
			_listSchoolBlock(listSchoolBlockWrap);
			if(listSchoolBlock == null)
				setListSchoolBlock(listSchoolBlockWrap.o);
		}
		listSchoolBlockWrap.dejaInitialise(true);
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.block.BlockGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolBlock">Trouver l'entité schoolBlock dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getSchoolBlock() {
		return schoolBlock;
	}

	public void setSchoolBlock(SchoolBlock schoolBlock) {
		this.schoolBlock = schoolBlock;
		this.schoolBlockWrap.dejaInitialise = true;
	}
	protected BlockGenPage schoolBlockInit() {
		if(!schoolBlockWrap.dejaInitialise) {
			_schoolBlock(schoolBlockWrap);
			if(schoolBlock == null)
				setSchoolBlock(schoolBlockWrap.o);
		}
		schoolBlockWrap.dejaInitialise(true);
		return (BlockGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseBlockGenPage = false;

	public BlockGenPage initLoinBlockGenPage(SiteRequestEnUS requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseBlockGenPage) {
			dejaInitialiseBlockGenPage = true;
			initLoinBlockGenPage();
		}
		return (BlockGenPage)this;
	}

	public void initLoinBlockGenPage() {
		super.initLoinClusterPage(requeteSite_);
		initBlockGenPage();
	}

	public void initBlockGenPage() {
		listSchoolBlockInit();
		schoolBlockInit();
	}

	@Override public void initLoinPourClasse(SiteRequestEnUS requeteSite_) {
		initLoinBlockGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteBlockGenPage(SiteRequestEnUS requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
	}

	public void requeteSitePourClasse(SiteRequestEnUS requeteSite_) {
		requeteSiteBlockGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirBlockGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirBlockGenPage(String var) {
		BlockGenPage oBlockGenPage = (BlockGenPage)this;
		switch(var) {
			case "listSchoolBlock":
				return oBlockGenPage.listSchoolBlock;
			case "schoolBlock":
				return oBlockGenPage.schoolBlock;
			default:
				return super.obtenirClusterPage(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerBlockGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerBlockGenPage(String var, Object val) {
		BlockGenPage oBlockGenPage = (BlockGenPage)this;
		switch(var) {
			default:
				return super.attribuerClusterPage(var, val);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirBlockGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirBlockGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
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
