package org.computate.scolaire.enUS.user;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import org.computate.scolaire.enUS.user.SiteUser;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.logging.Logger;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteUserGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SiteUserGenPage.class);

	//////////////////
	// listSiteUser //
	//////////////////

	/**	L'entité « listSiteUser »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SiteUser> listSiteUser;
	@JsonIgnore
	public Wrap<SearchList<SiteUser>> listSiteUserWrap = new Wrap<SearchList<SiteUser>>().p(this).c(SearchList.class).var("listSiteUser").o(listSiteUser);

	/**	<br/>L'entité « listSiteUser »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSiteUser">Trouver l'entité listSiteUser dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSiteUser(Wrap<SearchList<SiteUser>> c);

	public SearchList<SiteUser> getListSiteUser() {
		return listSiteUser;
	}

	public void setListSiteUser(SearchList<SiteUser> listSiteUser) {
		this.listSiteUser = listSiteUser;
		this.listSiteUserWrap.alreadyInitialized = true;
	}
	protected SiteUserGenPage listSiteUserInit() {
		if(!listSiteUserWrap.alreadyInitialized) {
			_listSiteUser(listSiteUserWrap);
			if(listSiteUser == null)
				setListSiteUser(listSiteUserWrap.o);
		}
		if(listSiteUser != null)
			listSiteUser.initDeepForClass(siteRequest_);
		listSiteUserWrap.alreadyInitialized(true);
		return (SiteUserGenPage)this;
	}

	//////////////
	// siteUser //
	//////////////

	/**	L'entité « siteUser »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteUser siteUser;
	@JsonIgnore
	public Wrap<SiteUser> siteUserWrap = new Wrap<SiteUser>().p(this).c(SiteUser.class).var("siteUser").o(siteUser);

	/**	<br/>L'entité « siteUser »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteUser">Trouver l'entité siteUser dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteUser(Wrap<SiteUser> c);

	public SiteUser getSiteUser() {
		return siteUser;
	}

	public void setSiteUser(SiteUser siteUser) {
		this.siteUser = siteUser;
		this.siteUserWrap.alreadyInitialized = true;
	}
	protected SiteUserGenPage siteUserInit() {
		if(!siteUserWrap.alreadyInitialized) {
			_siteUser(siteUserWrap);
			if(siteUser == null)
				setSiteUser(siteUserWrap.o);
		}
		if(siteUser != null)
			siteUser.initDeepForClass(siteRequest_);
		siteUserWrap.alreadyInitialized(true);
		return (SiteUserGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteUserGenPage = false;

	public SiteUserGenPage initDeepSiteUserGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSiteUserGenPage) {
			alreadyInitializedSiteUserGenPage = true;
			initDeepSiteUserGenPage();
		}
		return (SiteUserGenPage)this;
	}

	public void initDeepSiteUserGenPage() {
		initSiteUserGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initSiteUserGenPage() {
		listSiteUserInit();
		siteUserInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteUserGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteUserGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSiteUser != null)
			listSiteUser.setSiteRequest_(siteRequest_);
		if(siteUser != null)
			siteUser.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSiteUserGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteUserGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSiteUserGenPage(String var) {
		SiteUserGenPage oSiteUserGenPage = (SiteUserGenPage)this;
		switch(var) {
			case "listSiteUser":
				return oSiteUserGenPage.listSiteUser;
			case "siteUser":
				return oSiteUserGenPage.siteUser;
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
				o = attributeSiteUserGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSiteUserGenPage(String var, Object val) {
		SiteUserGenPage oSiteUserGenPage = (SiteUserGenPage)this;
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
					o = defineSiteUserGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSiteUserGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSiteUserGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsSiteUserGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSiteUserGenPage();
		super.htmlScript();
	}

	public void htmlScriptSiteUserGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySiteUserGenPage();
		super.htmlBody();
	}

	public void htmlBodySiteUserGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSiteUserGenPage();
		super.html();
	}

	public void htmlSiteUserGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSiteUserGenPage();
		super.htmlMeta();
	}

	public void htmlMetaSiteUserGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSiteUserGenPage();
		super.htmlStyles();
	}

	public void htmlStylesSiteUserGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSiteUserGenPage();
		super.htmlStyle();
	}

	public void htmlStyleSiteUserGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSiteUserGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SiteUserGenPage) {
			SiteUserGenPage original = (SiteUserGenPage)o;
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
		if(!(o instanceof SiteUserGenPage))
			return false;
		SiteUserGenPage that = (SiteUserGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SiteUserGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
