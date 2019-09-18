package org.computate.scolaire.enUS.child;

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
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ChildGenPageGen<DEV> extends ClusterPage {

	/////////////////////
	// listSchoolChild //
	/////////////////////

	/**	L'entité « listSchoolChild »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SchoolChild> listSchoolChild;
	@JsonIgnore
	public Wrap<SearchList<SchoolChild>> listSchoolChildWrap = new Wrap<SearchList<SchoolChild>>().p(this).c(SearchList.class).var("listSchoolChild").o(listSchoolChild);

	/**	<br/>L'entité « listSchoolChild »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listSchoolChild">Trouver l'entité listSchoolChild dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolChild(Wrap<SearchList<SchoolChild>> c);

	public SearchList<SchoolChild> getListSchoolChild() {
		return listSchoolChild;
	}

	public void setListSchoolChild(SearchList<SchoolChild> listSchoolChild) {
		this.listSchoolChild = listSchoolChild;
		this.listSchoolChildWrap.dejaInitialise = true;
	}
	protected ChildGenPage listSchoolChildInit() {
		if(!listSchoolChildWrap.dejaInitialise) {
			_listSchoolChild(listSchoolChildWrap);
			if(listSchoolChild == null)
				setListSchoolChild(listSchoolChildWrap.o);
		}
		listSchoolChildWrap.dejaInitialise(true);
		return (ChildGenPage)this;
	}

	/////////////////
	// schoolChild //
	/////////////////

	/**	L'entité « schoolChild »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolChild schoolChild;
	@JsonIgnore
	public Wrap<SchoolChild> schoolChildWrap = new Wrap<SchoolChild>().p(this).c(SchoolChild.class).var("schoolChild").o(schoolChild);

	/**	<br/>L'entité « schoolChild »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.child.ChildGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolChild">Trouver l'entité schoolChild dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolChild(Wrap<SchoolChild> c);

	public SchoolChild getSchoolChild() {
		return schoolChild;
	}

	public void setSchoolChild(SchoolChild schoolChild) {
		this.schoolChild = schoolChild;
		this.schoolChildWrap.dejaInitialise = true;
	}
	protected ChildGenPage schoolChildInit() {
		if(!schoolChildWrap.dejaInitialise) {
			_schoolChild(schoolChildWrap);
			if(schoolChild == null)
				setSchoolChild(schoolChildWrap.o);
		}
		schoolChildWrap.dejaInitialise(true);
		return (ChildGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseChildGenPage = false;

	public ChildGenPage initLoinChildGenPage(SiteRequestEnUS requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseChildGenPage) {
			dejaInitialiseChildGenPage = true;
			initLoinChildGenPage();
		}
		return (ChildGenPage)this;
	}

	public void initLoinChildGenPage() {
		super.initLoinClusterPage(requeteSite_);
		initChildGenPage();
	}

	public void initChildGenPage() {
		listSchoolChildInit();
		schoolChildInit();
	}

	@Override public void initLoinPourClasse(SiteRequestEnUS requeteSite_) {
		initLoinChildGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteChildGenPage(SiteRequestEnUS requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
	}

	public void requeteSitePourClasse(SiteRequestEnUS requeteSite_) {
		requeteSiteChildGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirChildGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirChildGenPage(String var) {
		ChildGenPage oChildGenPage = (ChildGenPage)this;
		switch(var) {
			case "listSchoolChild":
				return oChildGenPage.listSchoolChild;
			case "schoolChild":
				return oChildGenPage.schoolChild;
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
				o = attribuerChildGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerChildGenPage(String var, Object val) {
		ChildGenPage oChildGenPage = (ChildGenPage)this;
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
					o = definirChildGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirChildGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
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
