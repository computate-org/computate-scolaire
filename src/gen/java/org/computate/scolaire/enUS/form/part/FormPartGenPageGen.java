package org.computate.scolaire.enUS.form.part;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.form.part.FormPart;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPartGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class FormPartGenPageGen<DEV> extends ClusterPage {

	//////////////////
	// listFormPart //
	//////////////////

	/**	L'entité « listFormPart »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<FormPart> listFormPart;
	@JsonIgnore
	public Wrap<SearchList<FormPart>> listFormPartWrap = new Wrap<SearchList<FormPart>>().p(this).c(SearchList.class).var("listFormPart").o(listFormPart);

	/**	<br/>L'entité « listFormPart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPartGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listFormPart">Trouver l'entité listFormPart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listFormPart(Wrap<SearchList<FormPart>> c);

	public SearchList<FormPart> getListFormPart() {
		return listFormPart;
	}

	public void setListFormPart(SearchList<FormPart> listFormPart) {
		this.listFormPart = listFormPart;
		this.listFormPartWrap.alreadyInitialized = true;
	}
	protected FormPartGenPage listFormPartInit() {
		if(!listFormPartWrap.alreadyInitialized) {
			_listFormPart(listFormPartWrap);
			if(listFormPart == null)
				setListFormPart(listFormPartWrap.o);
		}
		listFormPartWrap.alreadyInitialized(true);
		return (FormPartGenPage)this;
	}

	//////////////
	// formPart //
	//////////////

	/**	L'entité « formPart »
	 *	 is defined as null before being initialized. 
	 */
	protected FormPart formPart;
	@JsonIgnore
	public Wrap<FormPart> formPartWrap = new Wrap<FormPart>().p(this).c(FormPart.class).var("formPart").o(formPart);

	/**	<br/>L'entité « formPart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPartGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:formPart">Trouver l'entité formPart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _formPart(Wrap<FormPart> c);

	public FormPart getFormPart() {
		return formPart;
	}

	public void setFormPart(FormPart formPart) {
		this.formPart = formPart;
		this.formPartWrap.alreadyInitialized = true;
	}
	protected FormPartGenPage formPartInit() {
		if(!formPartWrap.alreadyInitialized) {
			_formPart(formPartWrap);
			if(formPart == null)
				setFormPart(formPartWrap.o);
		}
		formPartWrap.alreadyInitialized(true);
		return (FormPartGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedFormPartGenPage = false;

	public FormPartGenPage initDeepFormPartGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedFormPartGenPage) {
			alreadyInitializedFormPartGenPage = true;
			initDeepFormPartGenPage();
		}
		return (FormPartGenPage)this;
	}

	public void initDeepFormPartGenPage() {
		initFormPartGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initFormPartGenPage() {
		listFormPartInit();
		formPartInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepFormPartGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestFormPartGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestFormPartGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainFormPartGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainFormPartGenPage(String var) {
		FormPartGenPage oFormPartGenPage = (FormPartGenPage)this;
		switch(var) {
			case "listFormPart":
				return oFormPartGenPage.listFormPart;
			case "formPart":
				return oFormPartGenPage.formPart;
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
				o = attributeFormPartGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeFormPartGenPage(String var, Object val) {
		FormPartGenPage oFormPartGenPage = (FormPartGenPage)this;
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
					o = defineFormPartGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineFormPartGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsFormPartGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsFormPartGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptFormPartGenPage();
		super.htmlScript();
	}

	public void htmlScriptFormPartGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyFormPartGenPage();
		super.htmlBody();
	}

	public void htmlBodyFormPartGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlFormPartGenPage();
		super.html();
	}

	public void htmlFormPartGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaFormPartGenPage();
		super.htmlMeta();
	}

	public void htmlMetaFormPartGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesFormPartGenPage();
		super.htmlStyles();
	}

	public void htmlStylesFormPartGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleFormPartGenPage();
		super.htmlStyle();
	}

	public void htmlStyleFormPartGenPage() {
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
		if(!(o instanceof FormPartGenPage))
			return false;
		FormPartGenPage that = (FormPartGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("FormPartGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
