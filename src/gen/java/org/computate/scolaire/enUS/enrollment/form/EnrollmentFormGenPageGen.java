package org.computate.scolaire.enUS.enrollment.form;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.enrollment.form.EnrollmentForm;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentFormGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentFormGenPageGen<DEV> extends ClusterPage {

	////////////////////////
	// listEnrollmentForm //
	////////////////////////

	/**	L'entité « listEnrollmentForm »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<EnrollmentForm> listEnrollmentForm;
	@JsonIgnore
	public Wrap<SearchList<EnrollmentForm>> listEnrollmentFormWrap = new Wrap<SearchList<EnrollmentForm>>().p(this).c(SearchList.class).var("listEnrollmentForm").o(listEnrollmentForm);

	/**	<br/>L'entité « listEnrollmentForm »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listEnrollmentForm">Trouver l'entité listEnrollmentForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listEnrollmentForm(Wrap<SearchList<EnrollmentForm>> c);

	public SearchList<EnrollmentForm> getListEnrollmentForm() {
		return listEnrollmentForm;
	}

	public void setListEnrollmentForm(SearchList<EnrollmentForm> listEnrollmentForm) {
		this.listEnrollmentForm = listEnrollmentForm;
		this.listEnrollmentFormWrap.alreadyInitialized = true;
	}
	protected EnrollmentFormGenPage listEnrollmentFormInit() {
		if(!listEnrollmentFormWrap.alreadyInitialized) {
			_listEnrollmentForm(listEnrollmentFormWrap);
			if(listEnrollmentForm == null)
				setListEnrollmentForm(listEnrollmentFormWrap.o);
		}
		listEnrollmentFormWrap.alreadyInitialized(true);
		return (EnrollmentFormGenPage)this;
	}

	////////////////////
	// enrollmentForm //
	////////////////////

	/**	L'entité « enrollmentForm »
	 *	 is defined as null before being initialized. 
	 */
	protected EnrollmentForm enrollmentForm;
	@JsonIgnore
	public Wrap<EnrollmentForm> enrollmentFormWrap = new Wrap<EnrollmentForm>().p(this).c(EnrollmentForm.class).var("enrollmentForm").o(enrollmentForm);

	/**	<br/>L'entité « enrollmentForm »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentForm">Trouver l'entité enrollmentForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentForm(Wrap<EnrollmentForm> c);

	public EnrollmentForm getEnrollmentForm() {
		return enrollmentForm;
	}

	public void setEnrollmentForm(EnrollmentForm enrollmentForm) {
		this.enrollmentForm = enrollmentForm;
		this.enrollmentFormWrap.alreadyInitialized = true;
	}
	protected EnrollmentFormGenPage enrollmentFormInit() {
		if(!enrollmentFormWrap.alreadyInitialized) {
			_enrollmentForm(enrollmentFormWrap);
			if(enrollmentForm == null)
				setEnrollmentForm(enrollmentFormWrap.o);
		}
		enrollmentFormWrap.alreadyInitialized(true);
		return (EnrollmentFormGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentFormGenPage = false;

	public EnrollmentFormGenPage initDeepEnrollmentFormGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentFormGenPage) {
			alreadyInitializedEnrollmentFormGenPage = true;
			initDeepEnrollmentFormGenPage();
		}
		return (EnrollmentFormGenPage)this;
	}

	public void initDeepEnrollmentFormGenPage() {
		super.initDeepClusterPage(siteRequest_);
		initEnrollmentFormGenPage();
	}

	public void initEnrollmentFormGenPage() {
		listEnrollmentFormInit();
		enrollmentFormInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentFormGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentFormGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentFormGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentFormGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentFormGenPage(String var) {
		EnrollmentFormGenPage oEnrollmentFormGenPage = (EnrollmentFormGenPage)this;
		switch(var) {
			case "listEnrollmentForm":
				return oEnrollmentFormGenPage.listEnrollmentForm;
			case "enrollmentForm":
				return oEnrollmentFormGenPage.enrollmentForm;
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
				o = attributeEnrollmentFormGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentFormGenPage(String var, Object val) {
		EnrollmentFormGenPage oEnrollmentFormGenPage = (EnrollmentFormGenPage)this;
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
					o = defineEnrollmentFormGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentFormGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentFormGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentFormGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentFormGenPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentFormGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentFormGenPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentFormGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentFormGenPage();
		super.html();
	}

	public void htmlEnrollmentFormGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentFormGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentFormGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentFormGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentFormGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentFormGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentFormGenPage() {
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
		if(!(o instanceof EnrollmentFormGenPage))
			return false;
		EnrollmentFormGenPage that = (EnrollmentFormGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentFormGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
