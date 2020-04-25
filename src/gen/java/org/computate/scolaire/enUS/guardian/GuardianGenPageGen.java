package org.computate.scolaire.enUS.guardian;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.GuardianGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class GuardianGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GuardianGenPage.class);

	////////////////////////
	// listSchoolGuardian //
	////////////////////////

	/**	L'entité « listSchoolGuardian »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolGuardian> listSchoolGuardian;
	@JsonIgnore
	public Wrap<SearchList<SchoolGuardian>> listSchoolGuardianWrap = new Wrap<SearchList<SchoolGuardian>>().p(this).c(SearchList.class).var("listSchoolGuardian").o(listSchoolGuardian);

	/**	<br/>L'entité « listSchoolGuardian »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.GuardianGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolGuardian">Trouver l'entité listSchoolGuardian dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolGuardian(Wrap<SearchList<SchoolGuardian>> c);

	public SearchList<SchoolGuardian> getListSchoolGuardian() {
		return listSchoolGuardian;
	}

	public void setListSchoolGuardian(SearchList<SchoolGuardian> listSchoolGuardian) {
		this.listSchoolGuardian = listSchoolGuardian;
		this.listSchoolGuardianWrap.alreadyInitialized = true;
	}
	protected GuardianGenPage listSchoolGuardianInit() {
		if(!listSchoolGuardianWrap.alreadyInitialized) {
			_listSchoolGuardian(listSchoolGuardianWrap);
			if(listSchoolGuardian == null)
				setListSchoolGuardian(listSchoolGuardianWrap.o);
		}
		if(listSchoolGuardian != null)
			listSchoolGuardian.initDeepForClass(siteRequest_);
		listSchoolGuardianWrap.alreadyInitialized(true);
		return (GuardianGenPage)this;
	}

	////////////////////
	// schoolGuardian //
	////////////////////

	/**	L'entité « schoolGuardian »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolGuardian schoolGuardian;
	@JsonIgnore
	public Wrap<SchoolGuardian> schoolGuardianWrap = new Wrap<SchoolGuardian>().p(this).c(SchoolGuardian.class).var("schoolGuardian").o(schoolGuardian);

	/**	<br/>L'entité « schoolGuardian »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.GuardianGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolGuardian">Trouver l'entité schoolGuardian dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolGuardian(Wrap<SchoolGuardian> c);

	public SchoolGuardian getSchoolGuardian() {
		return schoolGuardian;
	}

	public void setSchoolGuardian(SchoolGuardian schoolGuardian) {
		this.schoolGuardian = schoolGuardian;
		this.schoolGuardianWrap.alreadyInitialized = true;
	}
	protected GuardianGenPage schoolGuardianInit() {
		if(!schoolGuardianWrap.alreadyInitialized) {
			_schoolGuardian(schoolGuardianWrap);
			if(schoolGuardian == null)
				setSchoolGuardian(schoolGuardianWrap.o);
		}
		if(schoolGuardian != null)
			schoolGuardian.initDeepForClass(siteRequest_);
		schoolGuardianWrap.alreadyInitialized(true);
		return (GuardianGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedGuardianGenPage = false;

	public GuardianGenPage initDeepGuardianGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedGuardianGenPage) {
			alreadyInitializedGuardianGenPage = true;
			initDeepGuardianGenPage();
		}
		return (GuardianGenPage)this;
	}

	public void initDeepGuardianGenPage() {
		initGuardianGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initGuardianGenPage() {
		listSchoolGuardianInit();
		schoolGuardianInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepGuardianGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGuardianGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolGuardian != null)
			listSchoolGuardian.setSiteRequest_(siteRequest_);
		if(schoolGuardian != null)
			schoolGuardian.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestGuardianGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainGuardianGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainGuardianGenPage(String var) {
		GuardianGenPage oGuardianGenPage = (GuardianGenPage)this;
		switch(var) {
			case "listSchoolGuardian":
				return oGuardianGenPage.listSchoolGuardian;
			case "schoolGuardian":
				return oGuardianGenPage.schoolGuardian;
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
				o = attributeGuardianGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeGuardianGenPage(String var, Object val) {
		GuardianGenPage oGuardianGenPage = (GuardianGenPage)this;
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
					o = defineGuardianGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineGuardianGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsGuardianGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsGuardianGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptGuardianGenPage();
		super.htmlScript();
	}

	public void htmlScriptGuardianGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyGuardianGenPage();
		super.htmlBody();
	}

	public void htmlBodyGuardianGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlGuardianGenPage();
		super.html();
	}

	public void htmlGuardianGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaGuardianGenPage();
		super.htmlMeta();
	}

	public void htmlMetaGuardianGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesGuardianGenPage();
		super.htmlStyles();
	}

	public void htmlStylesGuardianGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleGuardianGenPage();
		super.htmlStyle();
	}

	public void htmlStyleGuardianGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestGuardianGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof GuardianGenPage) {
			GuardianGenPage original = (GuardianGenPage)o;
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
		if(!(o instanceof GuardianGenPage))
			return false;
		GuardianGenPage that = (GuardianGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("GuardianGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
