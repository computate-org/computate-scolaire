package org.computate.scolaire.enUS.enrollment;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.enrollment.EnrollmentPdfGenPage;
import java.util.List;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.dad.SchoolDad;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentPdfPageGen<DEV> extends EnrollmentPdfGenPage {

	////////
	// w1 //
	////////

	/**	L'entité « w1 »
	 *	 is defined as null before being initialized. 
	 */
	protected AllWriter w1;
	@JsonIgnore
	public Wrap<AllWriter> w1Wrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("w1").o(w1);

	/**	<br/>L'entité « w1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:w1">Trouver l'entité w1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _w1(Wrap<AllWriter> c);

	public AllWriter getW1() {
		return w1;
	}

	public void setW1(AllWriter w1) {
		this.w1 = w1;
		this.w1Wrap.alreadyInitialized = true;
	}
	protected EnrollmentPdfPage w1Init() {
		if(!w1Wrap.alreadyInitialized) {
			_w1(w1Wrap);
			if(w1 == null)
				setW1(w1Wrap.o);
		}
		if(w1 != null)
			w1.initDeepForClass(siteRequest_);
		w1Wrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
	}

	////////
	// w2 //
	////////

	/**	L'entité « w2 »
	 *	 is defined as null before being initialized. 
	 */
	protected AllWriter w2;
	@JsonIgnore
	public Wrap<AllWriter> w2Wrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("w2").o(w2);

	/**	<br/>L'entité « w2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:w2">Trouver l'entité w2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _w2(Wrap<AllWriter> c);

	public AllWriter getW2() {
		return w2;
	}

	public void setW2(AllWriter w2) {
		this.w2 = w2;
		this.w2Wrap.alreadyInitialized = true;
	}
	protected EnrollmentPdfPage w2Init() {
		if(!w2Wrap.alreadyInitialized) {
			_w2(w2Wrap);
			if(w2 == null)
				setW2(w2Wrap.o);
		}
		if(w2 != null)
			w2.initDeepForClass(siteRequest_);
		w2Wrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
	}

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listEnrollmentDesign">Trouver l'entité listEnrollmentDesign dans Solr</a>
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
	protected EnrollmentPdfPage listEnrollmentDesignInit() {
		if(!listEnrollmentDesignWrap.alreadyInitialized) {
			_listEnrollmentDesign(listEnrollmentDesign);
		}
		listEnrollmentDesign.initDeepForClass(siteRequest_);
		listEnrollmentDesignWrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDesign">Trouver l'entité enrollmentDesign dans Solr</a>
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
	protected EnrollmentPdfPage enrollmentDesignInit() {
		if(!enrollmentDesignWrap.alreadyInitialized) {
			_enrollmentDesign(enrollmentDesignWrap);
			if(enrollmentDesign == null)
				setEnrollmentDesign(enrollmentDesignWrap.o);
		}
		if(enrollmentDesign != null)
			enrollmentDesign.initDeepForClass(siteRequest_);
		enrollmentDesignWrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:mom_">Trouver l'entité mom_ dans Solr</a>
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
	protected EnrollmentPdfPage mom_Init() {
		if(!mom_Wrap.alreadyInitialized) {
			_mom_(mom_Wrap);
			if(mom_ == null)
				setMom_(mom_Wrap.o);
		}
		mom_Wrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dad_">Trouver l'entité dad_ dans Solr</a>
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
	protected EnrollmentPdfPage dad_Init() {
		if(!dad_Wrap.alreadyInitialized) {
			_dad_(dad_Wrap);
			if(dad_ == null)
				setDad_(dad_Wrap.o);
		}
		dad_Wrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardian_">Trouver l'entité guardian_ dans Solr</a>
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
	protected EnrollmentPdfPage guardian_Init() {
		if(!guardian_Wrap.alreadyInitialized) {
			_guardian_(guardian_Wrap);
			if(guardian_ == null)
				setGuardian_(guardian_Wrap.o);
		}
		guardian_Wrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartSearch">Trouver l'entité htmlPartSearch dans Solr</a>
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
	protected EnrollmentPdfPage htmlPartSearchInit() {
		if(!htmlPartSearchWrap.alreadyInitialized) {
			_htmlPartSearch(htmlPartSearch);
		}
		htmlPartSearch.initDeepForClass(siteRequest_);
		htmlPartSearchWrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentPdfPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartList">Trouver l'entité htmlPartList dans Solr</a>
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
	public EnrollmentPdfPage addHtmlPartList(HtmlPart...objets) {
		for(HtmlPart o : objets) {
			addHtmlPartList(o);
		}
		return (EnrollmentPdfPage)this;
	}
	public EnrollmentPdfPage addHtmlPartList(HtmlPart o) {
		if(o != null && !htmlPartList.contains(o))
			this.htmlPartList.add(o);
		return (EnrollmentPdfPage)this;
	}
	protected EnrollmentPdfPage htmlPartListInit() {
		if(!htmlPartListWrap.alreadyInitialized) {
			_htmlPartList(htmlPartListWrap);
			if(htmlPartList == null)
				setHtmlPartList(htmlPartListWrap.o);
		}
		htmlPartListWrap.alreadyInitialized(true);
		return (EnrollmentPdfPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentPdfPage = false;

	public EnrollmentPdfPage initDeepEnrollmentPdfPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentPdfPage) {
			alreadyInitializedEnrollmentPdfPage = true;
			initDeepEnrollmentPdfPage();
		}
		return (EnrollmentPdfPage)this;
	}

	public void initDeepEnrollmentPdfPage() {
		initEnrollmentPdfPage();
		super.initDeepEnrollmentPdfGenPage(siteRequest_);
	}

	public void initEnrollmentPdfPage() {
		w1Init();
		w2Init();
		listEnrollmentDesignInit();
		enrollmentDesignInit();
		mom_Init();
		dad_Init();
		guardian_Init();
		htmlPartSearchInit();
		htmlPartListInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentPdfPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentPdfPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestEnrollmentPdfGenPage(siteRequest_);
		if(w1 != null)
			w1.setSiteRequest_(siteRequest_);
		if(w2 != null)
			w2.setSiteRequest_(siteRequest_);
		if(listEnrollmentDesign != null)
			listEnrollmentDesign.setSiteRequest_(siteRequest_);
		if(enrollmentDesign != null)
			enrollmentDesign.setSiteRequest_(siteRequest_);
		if(htmlPartSearch != null)
			htmlPartSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentPdfPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentPdfPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentPdfPage(String var) {
		EnrollmentPdfPage oEnrollmentPdfPage = (EnrollmentPdfPage)this;
		switch(var) {
			case "w1":
				return oEnrollmentPdfPage.w1;
			case "w2":
				return oEnrollmentPdfPage.w2;
			case "listEnrollmentDesign":
				return oEnrollmentPdfPage.listEnrollmentDesign;
			case "enrollmentDesign":
				return oEnrollmentPdfPage.enrollmentDesign;
			case "mom_":
				return oEnrollmentPdfPage.mom_;
			case "dad_":
				return oEnrollmentPdfPage.dad_;
			case "guardian_":
				return oEnrollmentPdfPage.guardian_;
			case "htmlPartSearch":
				return oEnrollmentPdfPage.htmlPartSearch;
			case "htmlPartList":
				return oEnrollmentPdfPage.htmlPartList;
			default:
				return super.obtainEnrollmentPdfGenPage(var);
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
				o = attributeEnrollmentPdfPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentPdfPage(String var, Object val) {
		EnrollmentPdfPage oEnrollmentPdfPage = (EnrollmentPdfPage)this;
		switch(var) {
			default:
				return super.attributeEnrollmentPdfGenPage(var, val);
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
					o = defineEnrollmentPdfPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentPdfPage(String var, String val) {
		switch(var) {
			default:
				return super.defineEnrollmentPdfGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentPdfPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentPdfPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentPdfPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentPdfPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentPdfPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentPdfPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentPdfPage();
		super.html();
	}

	public void htmlEnrollmentPdfPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentPdfPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentPdfPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentPdfPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentPdfPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentPdfPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentPdfPage() {
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
		if(!(o instanceof EnrollmentPdfPage))
			return false;
		EnrollmentPdfPage that = (EnrollmentPdfPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentPdfPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
