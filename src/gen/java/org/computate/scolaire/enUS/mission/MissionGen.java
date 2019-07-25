package org.computate.scolaire.enUS.mission;

import java.util.Date;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mission.Mission&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class MissionGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(Mission.class);

	////////////////
	// missionNom //
	////////////////

	/**	L'entité « missionNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String missionNom;
	public Wrap<String> missionNomWrap = new Wrap<String>().p(this).c(String.class).var("missionNom").o(missionNom);

	/**	<br/>L'entité « missionNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mission.Mission&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:missionNom">Trouver l'entité missionNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _missionNom(Wrap<String> c);

	public String getMissionNom() {
		return missionNom;
	}

	public void setMissionNom(String missionNom) {
		this.missionNom = missionNom;
		this.missionNomWrap.alreadyInitialized = true;
	}
	protected Mission missionNomInit() {
		if(!missionNomWrap.alreadyInitialized) {
			_missionNom(missionNomWrap);
			if(missionNom == null)
				setMissionNom(missionNomWrap.o);
		}
		missionNomWrap.alreadyInitialized(true);
		return (Mission)this;
	}

	public String solrMissionNom() {
		return missionNom;
	}

	public String strMissionNom() {
		return missionNom == null ? "" : missionNom;
	}

	public String nomAffichageMissionNom() {
		return "mission name";
	}

	public String htmTooltipMissionNom() {
		return null;
	}

	public String htmMissionNom() {
		return missionNom == null ? "" : StringEscapeUtils.escapeHtml4(strMissionNom());
	}

	public void htmMissionNom(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchMission", strPk(), "MissionNom\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMission", strPk(), "MissionNom() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/mission?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setMissionNom\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageMissionNom()), "</span>");
				r.s("			<input");
							r.s(" name=\"missionNom\"");
							r.s(" value=\"", htmMissionNom(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmMissionNom());
			}
			r.l("</div>");
		}
	}

	//////////////////////////
	// ecoleNumeroTelephone //
	//////////////////////////

	/**	L'entité « ecoleNumeroTelephone »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleNumeroTelephone;
	public Wrap<String> ecoleNumeroTelephoneWrap = new Wrap<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/>L'entité « ecoleNumeroTelephone »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mission.Mission&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Wrap<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public void setEcoleNumeroTelephone(String ecoleNumeroTelephone) {
		this.ecoleNumeroTelephone = ecoleNumeroTelephone;
		this.ecoleNumeroTelephoneWrap.alreadyInitialized = true;
	}
	protected Mission ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneWrap.alreadyInitialized) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneWrap);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneWrap.o);
		}
		ecoleNumeroTelephoneWrap.alreadyInitialized(true);
		return (Mission)this;
	}

	public String solrEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public String strEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String nomAffichageEcoleNumeroTelephone() {
		return "mission description";
	}

	public String htmTooltipEcoleNumeroTelephone() {
		return null;
	}

	public String htmEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumeroTelephone());
	}

	public void htmEcoleNumeroTelephone(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchMission", strPk(), "EcoleNumeroTelephone\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMission", strPk(), "EcoleNumeroTelephone() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/mission?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setEcoleNumeroTelephone\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleNumeroTelephone()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleNumeroTelephone\"");
							r.s(" value=\"", htmEcoleNumeroTelephone(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleNumeroTelephone());
			}
			r.l("</div>");
		}
	}

	///////////////
	// missionId //
	///////////////

	/**	L'entité « missionId »
	 *	 is defined as null before being initialized. 
	 */
	protected String missionId;
	public Wrap<String> missionIdWrap = new Wrap<String>().p(this).c(String.class).var("missionId").o(missionId);

	/**	<br/>L'entité « missionId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mission.Mission&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:missionId">Trouver l'entité missionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _missionId(Wrap<String> c);

	public String getMissionId() {
		return missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
		this.missionIdWrap.alreadyInitialized = true;
	}
	protected Mission missionIdInit() {
		if(!missionIdWrap.alreadyInitialized) {
			_missionId(missionIdWrap);
			if(missionId == null)
				setMissionId(missionIdWrap.o);
		}
		missionIdWrap.alreadyInitialized(true);
		return (Mission)this;
	}

	public String solrMissionId() {
		return missionId;
	}

	public String strMissionId() {
		return missionId == null ? "" : missionId;
	}

	public String nomAffichageMissionId() {
		return "";
	}

	public String htmTooltipMissionId() {
		return null;
	}

	public String htmMissionId() {
		return missionId == null ? "" : StringEscapeUtils.escapeHtml4(strMissionId());
	}

	public void htmMissionId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchMission", strPk(), "MissionId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMission", strPk(), "MissionId() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/mission?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setMissionId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageMissionId()), "</span>");
				r.s("			<input");
							r.s(" name=\"missionId\"");
							r.s(" value=\"", htmMissionId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmMissionId());
			}
			r.l("</div>");
		}
	}

	/////////////
	// pageUri //
	/////////////

	/**	L'entité « pageUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUri;
	public Wrap<String> pageUriWrap = new Wrap<String>().p(this).c(String.class).var("pageUri").o(pageUri);

	/**	<br/>L'entité « pageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mission.Mission&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUri">Trouver l'entité pageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUri(Wrap<String> c);

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
		this.pageUriWrap.alreadyInitialized = true;
	}
	protected Mission pageUriInit() {
		if(!pageUriWrap.alreadyInitialized) {
			_pageUri(pageUriWrap);
			if(pageUri == null)
				setPageUri(pageUriWrap.o);
		}
		pageUriWrap.alreadyInitialized(true);
		return (Mission)this;
	}

	public String solrPageUri() {
		return pageUri;
	}

	public String strPageUri() {
		return pageUri == null ? "" : pageUri;
	}

	public String nomAffichagePageUri() {
		return null;
	}

	public String htmTooltipPageUri() {
		return null;
	}

	public String htmPageUri() {
		return pageUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageUri());
	}

	public void htmPageUri(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchMission", strPk(), "PageUri\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMission", strPk(), "PageUri() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/mission?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setPageUri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePageUri()), "</span>");
				r.s("			<input");
							r.s(" name=\"pageUri\"");
							r.s(" value=\"", htmPageUri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPageUri());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedMission = false;

	public Mission initDeepMission(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedMission) {
			alreadyInitializedMission = true;
			initDeepMission();
		}
		return (Mission)this;
	}

	public void initDeepMission() {
		super.initDeepCluster(siteRequest_);
		initMission();
	}

	public void initMission() {
		missionNomInit();
		ecoleNumeroTelephoneInit();
		missionIdInit();
		pageUriInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepMission(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestMission(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestMission(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainMission(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainMission(String var) {
		Mission oMission = (Mission)this;
		switch(var) {
			case "missionNom":
				return oMission.missionNom;
			case "ecoleNumeroTelephone":
				return oMission.ecoleNumeroTelephone;
			case "missionId":
				return oMission.missionId;
			case "pageUri":
				return oMission.pageUri;
			default:
				return super.obtainCluster(var);
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
				o = attributeMission(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeMission(String var, Object val) {
		Mission oMission = (Mission)this;
		switch(var) {
			default:
				return super.attributeCluster(var, val);
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
					o = defineMission(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineMission(String var, String val) {
		switch(var) {
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesMission = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateMission(solrDocument);
	}
	public void populateMission(SolrDocument solrDocument) {
		Mission oMission = (Mission)this;
		savesMission = (List<String>)solrDocument.get("savesMission_stored_strings");
		if(savesMission != null) {

			if(savesMission.contains("missionNom")) {
				String missionNom = (String)solrDocument.get("missionNom_stored_string");
				if(missionNom != null)
					oMission.setMissionNom(missionNom);
			}

			if(savesMission.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oMission.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(savesMission.contains("missionId")) {
				String missionId = (String)solrDocument.get("missionId_stored_string");
				if(missionId != null)
					oMission.setMissionId(missionId);
			}

			if(savesMission.contains("pageUri")) {
				String pageUri = (String)solrDocument.get("pageUri_stored_string");
				if(pageUri != null)
					oMission.setPageUri(pageUri);
			}
		}

		super.populateCluster(solrDocument);
	}

	/////////////
	// index //
	/////////////

	public static void index() {
		try {
			SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.getSiteConfig().setConfigPath("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery("*:*");
			solrQuery.setRows(1);
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.mission.Mission"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			Mission o = new Mission();
			o.siteRequestMission(siteRequest);
			o.initDeepMission(siteRequest);
			o.indexMission();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexMission();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexMission(document);
	}

	public void indexMission(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexMission(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexMission() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexMission(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexMission(SolrInputDocument document) {
		if(savesMission != null)
			document.addField("savesMission_stored_strings", savesMission);

		if(missionNom != null) {
			document.addField("missionNom_indexed_string", missionNom);
			document.addField("missionNom_stored_string", missionNom);
		}
		if(ecoleNumeroTelephone != null) {
			document.addField("ecoleNumeroTelephone_indexed_string", ecoleNumeroTelephone);
			document.addField("ecoleNumeroTelephone_stored_string", ecoleNumeroTelephone);
		}
		if(missionId != null) {
			document.addField("missionId_stored_string", missionId);
		}
		if(pageUri != null) {
			document.addField("pageUri_indexed_string", pageUri);
			document.addField("pageUri_stored_string", pageUri);
		}
		super.indexCluster(document);

	}

	public void unindexMission() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepMission(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeMission(solrDocument);
	}
	public void storeMission(SolrDocument solrDocument) {
		Mission oMission = (Mission)this;

		String missionNom = (String)solrDocument.get("missionNom_stored_string");
		if(missionNom != null)
			oMission.setMissionNom(missionNom);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oMission.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String missionId = (String)solrDocument.get("missionId_stored_string");
		if(missionId != null)
			oMission.setMissionId(missionId);

		String pageUri = (String)solrDocument.get("pageUri_stored_string");
		if(pageUri != null)
			oMission.setPageUri(pageUri);

		super.storeCluster(solrDocument);
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyMission();
	}

	public void htmlBodyMission() {
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
		if(!(o instanceof Mission))
			return false;
		Mission that = (Mission)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("Mission {");
		sb.append(" }");
		return sb.toString();
	}
}
