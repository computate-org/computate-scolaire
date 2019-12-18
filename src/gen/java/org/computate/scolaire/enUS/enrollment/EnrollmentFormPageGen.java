package org.computate.scolaire.enUS.enrollment;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.enrollment.EnrollmentFormGenPage;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.util.List;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.dad.SchoolDad;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentFormPageGen<DEV> extends EnrollmentFormGenPage {

	//////////////////////////
	// listEnrollmentDesign //
	//////////////////////////

	/**	L'entité « listEnrollmentDesign »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<EnrollmentDesign>(). 
	 */
	protected SearchList<EnrollmentDesign> listEnrollmentDesign = new SearchList<EnrollmentDesign>();
	@JsonIgnore
	public Wrap<SearchList<EnrollmentDesign>> listEnrollmentDesignWrap = new Wrap<SearchList<EnrollmentDesign>>().p(this).c(SearchList.class).var("listEnrollmentDesign").o(listEnrollmentDesign);

	/**	<br/>L'entité « listEnrollmentDesign »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<EnrollmentDesign>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listEnrollmentDesign">Trouver l'entité listEnrollmentDesign dans Solr</a>
	 * <br/>
	 * @param listEnrollmentDesign est l'entité déjà construit. 
	 **/
	protected abstract void _listEnrollmentDesign(SearchList<EnrollmentDesign> l);

	public SearchList<EnrollmentDesign> getListEnrollmentDesign() {
		return listEnrollmentDesign;
	}

	public void setListEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign) {
		this.listEnrollmentDesign = listEnrollmentDesign;
		this.listEnrollmentDesignWrap.alreadyInitialized = true;
	}
	protected EnrollmentFormPage listEnrollmentDesignInit() {
		if(!listEnrollmentDesignWrap.alreadyInitialized) {
			_listEnrollmentDesign(listEnrollmentDesign);
		}
		listEnrollmentDesign.initDeepForClass(siteRequest_);
		listEnrollmentDesignWrap.alreadyInitialized(true);
		return (EnrollmentFormPage)this;
	}

	//////////////////////
	// enrollmentDesign //
	//////////////////////

	/**	L'entité « enrollmentDesign »
	 *	 is defined as null before being initialized. 
	 */
	protected EnrollmentDesign enrollmentDesign;
	@JsonIgnore
	public Wrap<EnrollmentDesign> enrollmentDesignWrap = new Wrap<EnrollmentDesign>().p(this).c(EnrollmentDesign.class).var("enrollmentDesign").o(enrollmentDesign);

	/**	<br/>L'entité « enrollmentDesign »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDesign">Trouver l'entité enrollmentDesign dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDesign(Wrap<EnrollmentDesign> c);

	public EnrollmentDesign getEnrollmentDesign() {
		return enrollmentDesign;
	}

	public void setEnrollmentDesign(EnrollmentDesign enrollmentDesign) {
		this.enrollmentDesign = enrollmentDesign;
		this.enrollmentDesignWrap.alreadyInitialized = true;
	}
	protected EnrollmentFormPage enrollmentDesignInit() {
		if(!enrollmentDesignWrap.alreadyInitialized) {
			_enrollmentDesign(enrollmentDesignWrap);
			if(enrollmentDesign == null)
				setEnrollmentDesign(enrollmentDesignWrap.o);
		}
		if(enrollmentDesign != null)
			enrollmentDesign.initDeepForClass(siteRequest_);
		enrollmentDesignWrap.alreadyInitialized(true);
		return (EnrollmentFormPage)this;
	}

	//////////
	// mom_ //
	//////////

	/**	L'entité « mom_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolMom mom_;
	@JsonIgnore
	public Wrap<SchoolMom> mom_Wrap = new Wrap<SchoolMom>().p(this).c(SchoolMom.class).var("mom_").o(mom_);

	/**	<br/>L'entité « mom_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:mom_">Trouver l'entité mom_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mom_(Wrap<SchoolMom> c);

	public SchoolMom getMom_() {
		return mom_;
	}

	public void setMom_(SchoolMom mom_) {
		this.mom_ = mom_;
		this.mom_Wrap.alreadyInitialized = true;
	}
	protected EnrollmentFormPage mom_Init() {
		if(!mom_Wrap.alreadyInitialized) {
			_mom_(mom_Wrap);
			if(mom_ == null)
				setMom_(mom_Wrap.o);
		}
		mom_Wrap.alreadyInitialized(true);
		return (EnrollmentFormPage)this;
	}

	//////////
	// dad_ //
	//////////

	/**	L'entité « dad_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolDad dad_;
	@JsonIgnore
	public Wrap<SchoolDad> dad_Wrap = new Wrap<SchoolDad>().p(this).c(SchoolDad.class).var("dad_").o(dad_);

	/**	<br/>L'entité « dad_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dad_">Trouver l'entité dad_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _dad_(Wrap<SchoolDad> c);

	public SchoolDad getDad_() {
		return dad_;
	}

	public void setDad_(SchoolDad dad_) {
		this.dad_ = dad_;
		this.dad_Wrap.alreadyInitialized = true;
	}
	protected EnrollmentFormPage dad_Init() {
		if(!dad_Wrap.alreadyInitialized) {
			_dad_(dad_Wrap);
			if(dad_ == null)
				setDad_(dad_Wrap.o);
		}
		dad_Wrap.alreadyInitialized(true);
		return (EnrollmentFormPage)this;
	}

	///////////////
	// guardian_ //
	///////////////

	/**	L'entité « guardian_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolGuardian guardian_;
	@JsonIgnore
	public Wrap<SchoolGuardian> guardian_Wrap = new Wrap<SchoolGuardian>().p(this).c(SchoolGuardian.class).var("guardian_").o(guardian_);

	/**	<br/>L'entité « guardian_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardian_">Trouver l'entité guardian_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _guardian_(Wrap<SchoolGuardian> c);

	public SchoolGuardian getGuardian_() {
		return guardian_;
	}

	public void setGuardian_(SchoolGuardian guardian_) {
		this.guardian_ = guardian_;
		this.guardian_Wrap.alreadyInitialized = true;
	}
	protected EnrollmentFormPage guardian_Init() {
		if(!guardian_Wrap.alreadyInitialized) {
			_guardian_(guardian_Wrap);
			if(guardian_ == null)
				setGuardian_(guardian_Wrap.o);
		}
		guardian_Wrap.alreadyInitialized(true);
		return (EnrollmentFormPage)this;
	}

	////////////////////
	// htmlPartSearch //
	////////////////////

	/**	L'entité « htmlPartSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 */
	protected SearchList<HtmlPart> htmlPartSearch = new SearchList<HtmlPart>();
	@JsonIgnore
	public Wrap<SearchList<HtmlPart>> htmlPartSearchWrap = new Wrap<SearchList<HtmlPart>>().p(this).c(SearchList.class).var("htmlPartSearch").o(htmlPartSearch);

	/**	<br/>L'entité « htmlPartSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartSearch">Trouver l'entité htmlPartSearch dans Solr</a>
	 * <br/>
	 * @param htmlPartSearch est l'entité déjà construit. 
	 **/
	protected abstract void _htmlPartSearch(SearchList<HtmlPart> l);

	public SearchList<HtmlPart> getHtmlPartSearch() {
		return htmlPartSearch;
	}

	public void setHtmlPartSearch(SearchList<HtmlPart> htmlPartSearch) {
		this.htmlPartSearch = htmlPartSearch;
		this.htmlPartSearchWrap.alreadyInitialized = true;
	}
	protected EnrollmentFormPage htmlPartSearchInit() {
		if(!htmlPartSearchWrap.alreadyInitialized) {
			_htmlPartSearch(htmlPartSearch);
		}
		htmlPartSearch.initDeepForClass(siteRequest_);
		htmlPartSearchWrap.alreadyInitialized(true);
		return (EnrollmentFormPage)this;
	}

	//////////////////
	// htmlPartList //
	//////////////////

	/**	L'entité « htmlPartList »
	 *	 is defined as null before being initialized. 
	 */
	protected List<HtmlPart> htmlPartList;
	@JsonIgnore
	public Wrap<List<HtmlPart>> htmlPartListWrap = new Wrap<List<HtmlPart>>().p(this).c(List.class).var("htmlPartList").o(htmlPartList);

	/**	<br/>L'entité « htmlPartList »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartList">Trouver l'entité htmlPartList dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlPartList(Wrap<List<HtmlPart>> c);

	public List<HtmlPart> getHtmlPartList() {
		return htmlPartList;
	}

	public void setHtmlPartList(List<HtmlPart> htmlPartList) {
		this.htmlPartList = htmlPartList;
		this.htmlPartListWrap.alreadyInitialized = true;
	}
	public EnrollmentFormPage addHtmlPartList(HtmlPart...objets) {
		for(HtmlPart o : objets) {
			addHtmlPartList(o);
		}
		return (EnrollmentFormPage)this;
	}
	public EnrollmentFormPage addHtmlPartList(HtmlPart o) {
		if(o != null && !htmlPartList.contains(o))
			this.htmlPartList.add(o);
		return (EnrollmentFormPage)this;
	}
	protected EnrollmentFormPage htmlPartListInit() {
		if(!htmlPartListWrap.alreadyInitialized) {
			_htmlPartList(htmlPartListWrap);
			if(htmlPartList == null)
				setHtmlPartList(htmlPartListWrap.o);
		}
		htmlPartListWrap.alreadyInitialized(true);
		return (EnrollmentFormPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentFormPage = false;

	public EnrollmentFormPage initDeepEnrollmentFormPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentFormPage) {
			alreadyInitializedEnrollmentFormPage = true;
			initDeepEnrollmentFormPage();
		}
		return (EnrollmentFormPage)this;
	}

	public void initDeepEnrollmentFormPage() {
		initEnrollmentFormPage();
		super.initDeepEnrollmentFormGenPage(siteRequest_);
	}

	public void initEnrollmentFormPage() {
		listEnrollmentDesignInit();
		enrollmentDesignInit();
		mom_Init();
		dad_Init();
		guardian_Init();
		htmlPartSearchInit();
		htmlPartListInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentFormPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentFormPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestEnrollmentFormGenPage(siteRequest_);
		if(listEnrollmentDesign != null)
			listEnrollmentDesign.setSiteRequest_(siteRequest_);
		if(enrollmentDesign != null)
			enrollmentDesign.setSiteRequest_(siteRequest_);
		if(htmlPartSearch != null)
			htmlPartSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentFormPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentFormPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentFormPage(String var) {
		EnrollmentFormPage oEnrollmentFormPage = (EnrollmentFormPage)this;
		switch(var) {
			case "listEnrollmentDesign":
				return oEnrollmentFormPage.listEnrollmentDesign;
			case "enrollmentDesign":
				return oEnrollmentFormPage.enrollmentDesign;
			case "mom_":
				return oEnrollmentFormPage.mom_;
			case "dad_":
				return oEnrollmentFormPage.dad_;
			case "guardian_":
				return oEnrollmentFormPage.guardian_;
			case "htmlPartSearch":
				return oEnrollmentFormPage.htmlPartSearch;
			case "htmlPartList":
				return oEnrollmentFormPage.htmlPartList;
			default:
				return super.obtainEnrollmentFormGenPage(var);
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
				o = attributeEnrollmentFormPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentFormPage(String var, Object val) {
		EnrollmentFormPage oEnrollmentFormPage = (EnrollmentFormPage)this;
		switch(var) {
			default:
				return super.attributeEnrollmentFormGenPage(var, val);
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
					o = defineEnrollmentFormPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentFormPage(String var, String val) {
		switch(var) {
			default:
				return super.defineEnrollmentFormGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentFormPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentFormPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentFormPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentFormPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentFormPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentFormPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentFormPage();
		super.html();
	}

	public void htmlEnrollmentFormPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentFormPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentFormPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentFormPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentFormPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentFormPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentFormPage() {
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
		if(!(o instanceof EnrollmentFormPage))
			return false;
		EnrollmentFormPage that = (EnrollmentFormPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentFormPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
