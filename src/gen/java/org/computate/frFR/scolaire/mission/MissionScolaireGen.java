package org.computate.frFR.scolaire.mission;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.util.Date;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import org.apache.solr.client.solrj.SolrClient;
import java.util.ArrayList;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.computate.frFR.scolaire.couverture.Couverture;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.core.json.JsonObject;
import java.lang.String;
import io.vertx.core.logging.Logger;
import io.vertx.ext.sql.SQLClient;
import org.computate.frFR.scolaire.requete.RequeteSite;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.mission.MissionScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class MissionScolaireGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(MissionScolaire.class);

	public static final String MissionScolaire_UnNom = "une mission";
	public static final String MissionScolaire_Ce = "cette ";
	public static final String MissionScolaire_CeNom = "cette mission";
	public static final String MissionScolaire_Un = "une ";
	public static final String MissionScolaire_LeNom = "la mission";
	public static final String MissionScolaire_NomSingulier = "mission";
	public static final String MissionScolaire_NomPluriel = "missions";
	public static final String MissionScolaire_NomActuel = "mission actuelle";
	public static final String MissionScolaire_TousNom = "les missions";
	public static final String MissionScolaire_LesNoms = "les missions";
	public static final String MissionScolaire_AucunNomTrouve = "aucune mission trouvée";
	public static final String MissionScolaire_NomVar = "mission";
	public static final String MissionScolaire_DeNom = "de mission";
	public static final String MissionScolaire_Couleur = "pink";
	public static final String MissionScolaire_IconeGroupe = "regular";
	public static final String MissionScolaire_IconeNom = "fort-awesome";

	////////////////
	// missionNom //
	////////////////

	/**	L'entité « missionNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String missionNom;
	public Couverture<String> missionNomCouverture = new Couverture<String>().p(this).c(String.class).var("missionNom").o(missionNom);

	/**	<br/>L'entité « missionNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.mission.MissionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:missionNom">Trouver l'entité missionNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _missionNom(Couverture<String> c);

	public String getMissionNom() {
		return missionNom;
	}

	public void setMissionNom(String missionNom) {
		this.missionNom = missionNom;
		this.missionNomCouverture.dejaInitialise = true;
	}
	protected MissionScolaire missionNomInit() {
		if(!missionNomCouverture.dejaInitialise) {
			_missionNom(missionNomCouverture);
			if(missionNom == null)
				setMissionNom(missionNomCouverture.o);
		}
		missionNomCouverture.dejaInitialise(true);
		return (MissionScolaire)this;
	}

	public String solrMissionNom() {
		return missionNom;
	}

	public String strMissionNom() {
		return missionNom == null ? "" : missionNom;
	}

	public String nomAffichageMissionNom() {
		return "nom de la mission";
	}

	public String htmTooltipMissionNom() {
		return null;
	}

	public String htmMissionNom() {
		return missionNom == null ? "" : StringEscapeUtils.escapeHtml4(strMissionNom());
	}

	public void htmMissionNom(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchMissionScolaire", strPk(), "MissionNom\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMissionScolaire", strPk(), "MissionNom() {");
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
	public Couverture<String> ecoleNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/>L'entité « ecoleNumeroTelephone »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.mission.MissionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Couverture<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public void setEcoleNumeroTelephone(String ecoleNumeroTelephone) {
		this.ecoleNumeroTelephone = ecoleNumeroTelephone;
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	protected MissionScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (MissionScolaire)this;
	}

	public String solrEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public String strEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String nomAffichageEcoleNumeroTelephone() {
		return "description de la mission";
	}

	public String htmTooltipEcoleNumeroTelephone() {
		return null;
	}

	public String htmEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumeroTelephone());
	}

	public void htmEcoleNumeroTelephone(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchMissionScolaire", strPk(), "EcoleNumeroTelephone\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMissionScolaire", strPk(), "EcoleNumeroTelephone() {");
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
	public Couverture<String> missionIdCouverture = new Couverture<String>().p(this).c(String.class).var("missionId").o(missionId);

	/**	<br/>L'entité « missionId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.mission.MissionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:missionId">Trouver l'entité missionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _missionId(Couverture<String> c);

	public String getMissionId() {
		return missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
		this.missionIdCouverture.dejaInitialise = true;
	}
	protected MissionScolaire missionIdInit() {
		if(!missionIdCouverture.dejaInitialise) {
			_missionId(missionIdCouverture);
			if(missionId == null)
				setMissionId(missionIdCouverture.o);
		}
		missionIdCouverture.dejaInitialise(true);
		return (MissionScolaire)this;
	}

	public String solrMissionId() {
		return missionId;
	}

	public String strMissionId() {
		return missionId == null ? "" : missionId;
	}

	public String nomAffichageMissionId() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipMissionId() {
		return null;
	}

	public String htmMissionId() {
		return missionId == null ? "" : StringEscapeUtils.escapeHtml4(strMissionId());
	}

	public void htmMissionId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchMissionScolaire", strPk(), "MissionId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMissionScolaire", strPk(), "MissionId() {");
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
	public Couverture<String> pageUriCouverture = new Couverture<String>().p(this).c(String.class).var("pageUri").o(pageUri);

	/**	<br/>L'entité « pageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.mission.MissionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUri">Trouver l'entité pageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUri(Couverture<String> c);

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
		this.pageUriCouverture.dejaInitialise = true;
	}
	protected MissionScolaire pageUriInit() {
		if(!pageUriCouverture.dejaInitialise) {
			_pageUri(pageUriCouverture);
			if(pageUri == null)
				setPageUri(pageUriCouverture.o);
		}
		pageUriCouverture.dejaInitialise(true);
		return (MissionScolaire)this;
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

	public void htmPageUri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchMissionScolaire", strPk(), "PageUri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchMissionScolaire", strPk(), "PageUri() {");
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
	// initLoin //
	//////////////

	protected boolean dejaInitialiseMissionScolaire = false;

	public MissionScolaire initLoinMissionScolaire(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseMissionScolaire) {
			dejaInitialiseMissionScolaire = true;
			initLoinMissionScolaire();
		}
		return (MissionScolaire)this;
	}

	public void initLoinMissionScolaire() {
		super.initLoinCluster(requeteSite_);
		initMissionScolaire();
	}

	public void initMissionScolaire() {
		missionNomInit();
		ecoleNumeroTelephoneInit();
		missionIdInit();
		pageUriInit();
	}

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinMissionScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteMissionScolaire(RequeteSite requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteMissionScolaire(requeteSite_);
	}

	/////////////
	// indexer //
	/////////////

	public static void indexer() {
		try {
			RequeteSite requeteSite = new RequeteSite();
			requeteSite.initLoinRequeteSite();
			SiteContexte siteContexte = new SiteContexte();
			siteContexte.getConfigSite().setConfigChemin("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContexte.initLoinSiteContexte();
			siteContexte.setRequeteSite_(requeteSite);
			requeteSite.setSiteContexte_(siteContexte);
			MissionScolaire o = new MissionScolaire();
			o.requeteSiteMissionScolaire(requeteSite);
			o.initLoinMissionScolaire(requeteSite);
			o.indexerMissionScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() throws Exception {
		indexerMissionScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) throws Exception {
		indexerMissionScolaire(document);
	}

	public void indexerMissionScolaire(SolrClient clientSolr) throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerMissionScolaire(document);
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerMissionScolaire() throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerMissionScolaire(document);
		SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerMissionScolaire(SolrInputDocument document) throws Exception {
		if(sauvegardesMissionScolaire != null)
			document.addField("sauvegardesMissionScolaire_stored_strings", sauvegardesMissionScolaire);

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
		super.indexerCluster(document);

	}

	public void desindexerMissionScolaire() throws Exception {
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.initLoinRequeteSite();
		SiteContexte siteContexte = new SiteContexte();
		siteContexte.initLoinSiteContexte();
		siteContexte.setRequeteSite_(requeteSite);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		initLoinMissionScolaire(siteContexte.getRequeteSite_());
		SolrClient clientSolr = siteContexte.getClientSolr();
		clientSolr.deleteById(id.toString());
		clientSolr.commit();
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirMissionScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirMissionScolaire(String var) throws Exception {
		MissionScolaire oMissionScolaire = (MissionScolaire)this;
		switch(var) {
			case "missionNom":
				return oMissionScolaire.missionNom;
			case "ecoleNumeroTelephone":
				return oMissionScolaire.ecoleNumeroTelephone;
			case "missionId":
				return oMissionScolaire.missionId;
			case "pageUri":
				return oMissionScolaire.pageUri;
			default:
				return super.obtenirCluster(var);
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
				o = attribuerMissionScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerMissionScolaire(String var, Object val) {
		MissionScolaire oMissionScolaire = (MissionScolaire)this;
		switch(var) {
			default:
				return super.attribuerCluster(var, val);
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
					o = definirMissionScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirMissionScolaire(String var, String val) {
		switch(var) {
			case "missionNom":
				setMissionNom(val);
				sauvegardesMissionScolaire.add(var);
				return val;
			case "ecoleNumeroTelephone":
				setEcoleNumeroTelephone(val);
				sauvegardesMissionScolaire.add(var);
				return val;
			case "missionId":
				setMissionId(val);
				sauvegardesMissionScolaire.add(var);
				return val;
			case "pageUri":
				setPageUri(val);
				sauvegardesMissionScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesMissionScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerMissionScolaire(solrDocument);
	}
	public void peuplerMissionScolaire(SolrDocument solrDocument) {
		MissionScolaire oMissionScolaire = (MissionScolaire)this;
		sauvegardesMissionScolaire = (List<String>)solrDocument.get("sauvegardesMissionScolaire_stored_strings");
		if(sauvegardesMissionScolaire != null) {

			if(sauvegardesMissionScolaire.contains("missionNom")) {
				String missionNom = (String)solrDocument.get("missionNom_stored_string");
				if(missionNom != null)
					oMissionScolaire.setMissionNom(missionNom);
			}

			if(sauvegardesMissionScolaire.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oMissionScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardesMissionScolaire.contains("missionId")) {
				String missionId = (String)solrDocument.get("missionId_stored_string");
				if(missionId != null)
					oMissionScolaire.setMissionId(missionId);
			}

			if(sauvegardesMissionScolaire.contains("pageUri")) {
				String pageUri = (String)solrDocument.get("pageUri_stored_string");
				if(pageUri != null)
					oMissionScolaire.setPageUri(pageUri);
			}
		}

		super.peuplerCluster(solrDocument);
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerMissionScolaire(solrDocument);
	}
	public void stockerMissionScolaire(SolrDocument solrDocument) {
		MissionScolaire oMissionScolaire = (MissionScolaire)this;

		String missionNom = (String)solrDocument.get("missionNom_stored_string");
		if(missionNom != null)
			oMissionScolaire.setMissionNom(missionNom);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oMissionScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String missionId = (String)solrDocument.get("missionId_stored_string");
		if(missionId != null)
			oMissionScolaire.setMissionId(missionId);

		String pageUri = (String)solrDocument.get("pageUri_stored_string");
		if(pageUri != null)
			oMissionScolaire.setPageUri(pageUri);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyMissionScolaire();
	}

	public void htmlBodyMissionScolaire() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), missionNom, ecoleNumeroTelephone, missionId, pageUri);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof MissionScolaire))
			return false;
		MissionScolaire that = (MissionScolaire)o;
		return super.equals(o)
				&& Objects.equals( missionNom, that.missionNom )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( missionId, that.missionId )
				&& Objects.equals( pageUri, that.pageUri );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("MissionScolaire {");
		sb.append( "missionNom: \"" ).append(missionNom).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", missionId: \"" ).append(missionId).append( "\"" );
		sb.append( ", pageUri: \"" ).append(pageUri).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
