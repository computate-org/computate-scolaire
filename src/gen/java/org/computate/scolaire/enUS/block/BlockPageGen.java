package org.computate.scolaire.enUS.block;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import org.computate.scolaire.enUS.block.BlockGenPage;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.block.BlockPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class BlockPageGen<DEV> extends BlockGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseBlockPage = false;

	public BlockPage initLoinBlockPage(SiteRequestEnUS requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseBlockPage) {
			dejaInitialiseBlockPage = true;
			initLoinBlockPage();
		}
		return (BlockPage)this;
	}

	public void initLoinBlockPage() {
		super.initLoinBlockGenPage(requeteSite_);
		initBlockPage();
	}

	public void initBlockPage() {
	}

	@Override public void initLoinPourClasse(SiteRequestEnUS requeteSite_) {
		initLoinBlockPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteBlockPage(SiteRequestEnUS requeteSite_) {
			super.requeteSiteBlockGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(SiteRequestEnUS requeteSite_) {
		requeteSiteBlockPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirBlockPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirBlockPage(String var) {
		BlockPage oBlockPage = (BlockPage)this;
		switch(var) {
			default:
				return super.obtenirBlockGenPage(var);
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
				o = attribuerBlockPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerBlockPage(String var, Object val) {
		BlockPage oBlockPage = (BlockPage)this;
		switch(var) {
			default:
				return super.attribuerBlockGenPage(var, val);
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
					o = definirBlockPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirBlockPage(String var, String val) {
		switch(var) {
			default:
				return super.definirBlockGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsBlockPage();
		super.htmlScripts();
	}

	public void htmlScriptsBlockPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptBlockPage();
		super.htmlScript();
	}

	public void htmlScriptBlockPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyBlockPage();
		super.htmlBody();
	}

	public void htmlBodyBlockPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlBlockPage();
		super.html();
	}

	public void htmlBlockPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaBlockPage();
		super.htmlMeta();
	}

	public void htmlMetaBlockPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesBlockPage();
		super.htmlStyles();
	}

	public void htmlStylesBlockPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleBlockPage();
		super.htmlStyle();
	}

	public void htmlStyleBlockPage() {
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
		if(!(o instanceof BlockPage))
			return false;
		BlockPage that = (BlockPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("BlockPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
