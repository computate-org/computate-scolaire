package org.computate.scolaire.enUS.user;

import java.util.Date;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteUserGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SiteUser.class);

	public static final String SiteUser_UnNom = "a site user";
	public static final String SiteUser_Ce = "this ";
	public static final String SiteUser_CeNom = "this site user";
	public static final String SiteUser_Un = "an ";
	public static final String SiteUser_LeNom = "the site user";
	public static final String SiteUser_NomSingulier = "site user";
	public static final String SiteUser_NomPluriel = "site users";
	public static final String SiteUser_NomActuel = "current site user";
	public static final String SiteUser_TousNom = "the site users";
	public static final String SiteUser_RechercherTousNomPar = "search site users by ";
	public static final String SiteUser_LesNoms = "the site users";
	public static final String SiteUser_AucunNomTrouve = "no site user found";
	public static final String SiteUser_NomVar = "siteUser";
	public static final String SiteUser_DeNom = "of site user";
	public static final String SiteUser_Couleur = "green";
	public static final String SiteUser_IconeGroupe = "regular";
	public static final String SiteUser_IconeNom = "book";

	////////////
	// userId //
	////////////

	/**	L'entité « userId »
	 *	 is defined as null before being initialized. 
	 */
	protected String userId;
	@JsonIgnore
	public Wrap<String> userIdWrap = new Wrap<String>().p(this).c(String.class).var("userId").o(userId);

	/**	<br/>L'entité « userId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userId">Trouver l'entité userId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userId(Wrap<String> c);

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		this.userIdWrap.alreadyInitialized = true;
	}
	protected SiteUser userIdInit() {
		if(!userIdWrap.alreadyInitialized) {
			_userId(userIdWrap);
			if(userId == null)
				setUserId(userIdWrap.o);
		}
		userIdWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrUserId() {
		return userId;
	}

	public String strUserId() {
		return userId == null ? "" : userId;
	}

	public String nomAffichageUserId() {
		return "user ID";
	}

	public String htmTooltipUserId() {
		return null;
	}

	public String htmUserId() {
		return userId == null ? "" : StringEscapeUtils.escapeHtml4(strUserId());
	}

	public void htmUserId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserId() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserId()), "</span>");
				r.s("			<input");
							r.s(" name=\"userId\"");
							r.s(" value=\"", htmUserId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserId());
			}
			r.l("</div>");
		}
	}

	//////////////
	// userName //
	//////////////

	/**	L'entité « userName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userName;
	@JsonIgnore
	public Wrap<String> userNameWrap = new Wrap<String>().p(this).c(String.class).var("userName").o(userName);

	/**	<br/>L'entité « userName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userName">Trouver l'entité userName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userName(Wrap<String> c);

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		this.userNameWrap.alreadyInitialized = true;
	}
	protected SiteUser userNameInit() {
		if(!userNameWrap.alreadyInitialized) {
			_userName(userNameWrap);
			if(userName == null)
				setUserName(userNameWrap.o);
		}
		userNameWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrUserName() {
		return userName;
	}

	public String strUserName() {
		return userName == null ? "" : userName;
	}

	public String nomAffichageUserName() {
		return null;
	}

	public String htmTooltipUserName() {
		return null;
	}

	public String htmUserName() {
		return userName == null ? "" : StringEscapeUtils.escapeHtml4(strUserName());
	}

	public void htmUserName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserName() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserName()), "</span>");
				r.s("			<input");
							r.s(" name=\"userName\"");
							r.s(" value=\"", htmUserName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserName());
			}
			r.l("</div>");
		}
	}

	///////////////
	// userEmail //
	///////////////

	/**	L'entité « userEmail »
	 *	 is defined as null before being initialized. 
	 */
	protected String userEmail;
	@JsonIgnore
	public Wrap<String> userEmailWrap = new Wrap<String>().p(this).c(String.class).var("userEmail").o(userEmail);

	/**	<br/>L'entité « userEmail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userEmail">Trouver l'entité userEmail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userEmail(Wrap<String> c);

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
		this.userEmailWrap.alreadyInitialized = true;
	}
	protected SiteUser userEmailInit() {
		if(!userEmailWrap.alreadyInitialized) {
			_userEmail(userEmailWrap);
			if(userEmail == null)
				setUserEmail(userEmailWrap.o);
		}
		userEmailWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrUserEmail() {
		return userEmail;
	}

	public String strUserEmail() {
		return userEmail == null ? "" : userEmail;
	}

	public String nomAffichageUserEmail() {
		return null;
	}

	public String htmTooltipUserEmail() {
		return null;
	}

	public String htmUserEmail() {
		return userEmail == null ? "" : StringEscapeUtils.escapeHtml4(strUserEmail());
	}

	public void htmUserEmail(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserEmail\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserEmail() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserEmail\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserEmail()), "</span>");
				r.s("			<input");
							r.s(" name=\"userEmail\"");
							r.s(" value=\"", htmUserEmail(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserEmail());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// userFirstName //
	///////////////////

	/**	L'entité « userFirstName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userFirstName;
	@JsonIgnore
	public Wrap<String> userFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("userFirstName").o(userFirstName);

	/**	<br/>L'entité « userFirstName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userFirstName">Trouver l'entité userFirstName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userFirstName(Wrap<String> c);

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
		this.userFirstNameWrap.alreadyInitialized = true;
	}
	protected SiteUser userFirstNameInit() {
		if(!userFirstNameWrap.alreadyInitialized) {
			_userFirstName(userFirstNameWrap);
			if(userFirstName == null)
				setUserFirstName(userFirstNameWrap.o);
		}
		userFirstNameWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrUserFirstName() {
		return userFirstName;
	}

	public String strUserFirstName() {
		return userFirstName == null ? "" : userFirstName;
	}

	public String nomAffichageUserFirstName() {
		return null;
	}

	public String htmTooltipUserFirstName() {
		return null;
	}

	public String htmUserFirstName() {
		return userFirstName == null ? "" : StringEscapeUtils.escapeHtml4(strUserFirstName());
	}

	public void htmUserFirstName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserFirstName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserFirstName() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserFirstName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserFirstName()), "</span>");
				r.s("			<input");
							r.s(" name=\"userFirstName\"");
							r.s(" value=\"", htmUserFirstName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserFirstName());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// userLastName //
	//////////////////

	/**	L'entité « userLastName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userLastName;
	@JsonIgnore
	public Wrap<String> userLastNameWrap = new Wrap<String>().p(this).c(String.class).var("userLastName").o(userLastName);

	/**	<br/>L'entité « userLastName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userLastName">Trouver l'entité userLastName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userLastName(Wrap<String> c);

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
		this.userLastNameWrap.alreadyInitialized = true;
	}
	protected SiteUser userLastNameInit() {
		if(!userLastNameWrap.alreadyInitialized) {
			_userLastName(userLastNameWrap);
			if(userLastName == null)
				setUserLastName(userLastNameWrap.o);
		}
		userLastNameWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrUserLastName() {
		return userLastName;
	}

	public String strUserLastName() {
		return userLastName == null ? "" : userLastName;
	}

	public String nomAffichageUserLastName() {
		return null;
	}

	public String htmTooltipUserLastName() {
		return null;
	}

	public String htmUserLastName() {
		return userLastName == null ? "" : StringEscapeUtils.escapeHtml4(strUserLastName());
	}

	public void htmUserLastName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserLastName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserLastName() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserLastName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserLastName()), "</span>");
				r.s("			<input");
							r.s(" name=\"userLastName\"");
							r.s(" value=\"", htmUserLastName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserLastName());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// userFullName //
	//////////////////

	/**	L'entité « userFullName »
	 *	 is defined as null before being initialized. 
	 */
	protected String userFullName;
	@JsonIgnore
	public Wrap<String> userFullNameWrap = new Wrap<String>().p(this).c(String.class).var("userFullName").o(userFullName);

	/**	<br/>L'entité « userFullName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userFullName">Trouver l'entité userFullName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userFullName(Wrap<String> c);

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
		this.userFullNameWrap.alreadyInitialized = true;
	}
	protected SiteUser userFullNameInit() {
		if(!userFullNameWrap.alreadyInitialized) {
			_userFullName(userFullNameWrap);
			if(userFullName == null)
				setUserFullName(userFullNameWrap.o);
		}
		userFullNameWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrUserFullName() {
		return userFullName;
	}

	public String strUserFullName() {
		return userFullName == null ? "" : userFullName;
	}

	public String nomAffichageUserFullName() {
		return null;
	}

	public String htmTooltipUserFullName() {
		return null;
	}

	public String htmUserFullName() {
		return userFullName == null ? "" : StringEscapeUtils.escapeHtml4(strUserFullName());
	}

	public void htmUserFullName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserFullName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserFullName() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserFullName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserFullName()), "</span>");
				r.s("			<input");
							r.s(" name=\"userFullName\"");
							r.s(" value=\"", htmUserFullName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserFullName());
			}
			r.l("</div>");
		}
	}

	//////////////
	// userSite //
	//////////////

	/**	L'entité « userSite »
	 *	 is defined as null before being initialized. 
	 */
	protected String userSite;
	@JsonIgnore
	public Wrap<String> userSiteWrap = new Wrap<String>().p(this).c(String.class).var("userSite").o(userSite);

	/**	<br/>L'entité « userSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userSite">Trouver l'entité userSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userSite(Wrap<String> c);

	public String getUserSite() {
		return userSite;
	}

	public void setUserSite(String userSite) {
		this.userSite = userSite;
		this.userSiteWrap.alreadyInitialized = true;
	}
	protected SiteUser userSiteInit() {
		if(!userSiteWrap.alreadyInitialized) {
			_userSite(userSiteWrap);
			if(userSite == null)
				setUserSite(userSiteWrap.o);
		}
		userSiteWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrUserSite() {
		return userSite;
	}

	public String strUserSite() {
		return userSite == null ? "" : userSite;
	}

	public String nomAffichageUserSite() {
		return null;
	}

	public String htmTooltipUserSite() {
		return null;
	}

	public String htmUserSite() {
		return userSite == null ? "" : StringEscapeUtils.escapeHtml4(strUserSite());
	}

	public void htmUserSite(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserSite\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserSite() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserSite\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserSite()), "</span>");
				r.s("			<input");
							r.s(" name=\"userSite\"");
							r.s(" value=\"", htmUserSite(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserSite());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// userReceiveEmails //
	///////////////////////

	/**	L'entité « userReceiveEmails »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean userReceiveEmails;
	@JsonIgnore
	public Wrap<Boolean> userReceiveEmailsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("userReceiveEmails").o(userReceiveEmails);

	/**	<br/>L'entité « userReceiveEmails »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userReceiveEmails">Trouver l'entité userReceiveEmails dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _userReceiveEmails(Wrap<Boolean> c);

	public Boolean getUserReceiveEmails() {
		return userReceiveEmails;
	}

	public void setUserReceiveEmails(Boolean userReceiveEmails) {
		this.userReceiveEmails = userReceiveEmails;
		this.userReceiveEmailsWrap.alreadyInitialized = true;
	}
	public SiteUser setUserReceiveEmails(String o) {
		this.userReceiveEmails = Boolean.parseBoolean(o);
		this.userReceiveEmailsWrap.alreadyInitialized = true;
		return (SiteUser)this;
	}
	protected SiteUser userReceiveEmailsInit() {
		if(!userReceiveEmailsWrap.alreadyInitialized) {
			_userReceiveEmails(userReceiveEmailsWrap);
			if(userReceiveEmails == null)
				setUserReceiveEmails(userReceiveEmailsWrap.o);
		}
		userReceiveEmailsWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public Boolean solrUserReceiveEmails() {
		return userReceiveEmails;
	}

	public String strUserReceiveEmails() {
		return userReceiveEmails == null ? "" : userReceiveEmails.toString();
	}

	public String nomAffichageUserReceiveEmails() {
		return null;
	}

	public String htmTooltipUserReceiveEmails() {
		return null;
	}

	public String htmUserReceiveEmails() {
		return userReceiveEmails == null ? "" : StringEscapeUtils.escapeHtml4(strUserReceiveEmails());
	}

	public void htmUserReceiveEmails(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "UserReceiveEmails\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "UserReceiveEmails() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUserReceiveEmails\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUserReceiveEmails()), "</span>");
				r.s("			<input");
							r.s(" name=\"userReceiveEmails\"");
							r.s(" value=\"", htmUserReceiveEmails(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUserReceiveEmails());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// seeArchived //
	/////////////////

	/**	L'entité « seeArchived »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean seeArchived;
	@JsonIgnore
	public Wrap<Boolean> seeArchivedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seeArchived").o(seeArchived);

	/**	<br/>L'entité « seeArchived »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seeArchived">Trouver l'entité seeArchived dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seeArchived(Wrap<Boolean> c);

	public Boolean getSeeArchived() {
		return seeArchived;
	}

	public void setSeeArchived(Boolean seeArchived) {
		this.seeArchived = seeArchived;
		this.seeArchivedWrap.alreadyInitialized = true;
	}
	public SiteUser setSeeArchived(String o) {
		this.seeArchived = Boolean.parseBoolean(o);
		this.seeArchivedWrap.alreadyInitialized = true;
		return (SiteUser)this;
	}
	protected SiteUser seeArchivedInit() {
		if(!seeArchivedWrap.alreadyInitialized) {
			_seeArchived(seeArchivedWrap);
			if(seeArchived == null)
				setSeeArchived(seeArchivedWrap.o);
		}
		seeArchivedWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public Boolean solrSeeArchived() {
		return seeArchived;
	}

	public String strSeeArchived() {
		return seeArchived == null ? "" : seeArchived.toString();
	}

	public String nomAffichageSeeArchived() {
		return "see archived";
	}

	public String htmTooltipSeeArchived() {
		return null;
	}

	public String htmSeeArchived() {
		return seeArchived == null ? "" : StringEscapeUtils.escapeHtml4(strSeeArchived());
	}

	public void htmSeeArchived(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "SeeArchived\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "SeeArchived() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setSeeArchived\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeeArchived()), "</span>");
				r.s("			<input");
							r.s(" name=\"seeArchived\"");
							r.s(" value=\"", htmSeeArchived(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeeArchived());
			}
			r.l("</div>");
		}
	}

	////////////////
	// seeDeleted //
	////////////////

	/**	L'entité « seeDeleted »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean seeDeleted;
	@JsonIgnore
	public Wrap<Boolean> seeDeletedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seeDeleted").o(seeDeleted);

	/**	<br/>L'entité « seeDeleted »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seeDeleted">Trouver l'entité seeDeleted dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seeDeleted(Wrap<Boolean> c);

	public Boolean getSeeDeleted() {
		return seeDeleted;
	}

	public void setSeeDeleted(Boolean seeDeleted) {
		this.seeDeleted = seeDeleted;
		this.seeDeletedWrap.alreadyInitialized = true;
	}
	public SiteUser setSeeDeleted(String o) {
		this.seeDeleted = Boolean.parseBoolean(o);
		this.seeDeletedWrap.alreadyInitialized = true;
		return (SiteUser)this;
	}
	protected SiteUser seeDeletedInit() {
		if(!seeDeletedWrap.alreadyInitialized) {
			_seeDeleted(seeDeletedWrap);
			if(seeDeleted == null)
				setSeeDeleted(seeDeletedWrap.o);
		}
		seeDeletedWrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public Boolean solrSeeDeleted() {
		return seeDeleted;
	}

	public String strSeeDeleted() {
		return seeDeleted == null ? "" : seeDeleted.toString();
	}

	public String nomAffichageSeeDeleted() {
		return "see deleted";
	}

	public String htmTooltipSeeDeleted() {
		return null;
	}

	public String htmSeeDeleted() {
		return seeDeleted == null ? "" : StringEscapeUtils.escapeHtml4(strSeeDeleted());
	}

	public void htmSeeDeleted(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSiteUser", strPk(), "SeeDeleted\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSiteUser", strPk(), "SeeDeleted() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setSeeDeleted\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeeDeleted()), "</span>");
				r.s("			<input");
							r.s(" name=\"seeDeleted\"");
							r.s(" value=\"", htmSeeDeleted(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeeDeleted());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteUser = false;

	public SiteUser initDeepSiteUser(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSiteUser) {
			alreadyInitializedSiteUser = true;
			initDeepSiteUser();
		}
		return (SiteUser)this;
	}

	public void initDeepSiteUser() {
		super.initDeepCluster(siteRequest_);
		initSiteUser();
	}

	public void initSiteUser() {
		userIdInit();
		userNameInit();
		userEmailInit();
		userFirstNameInit();
		userLastNameInit();
		userFullNameInit();
		userSiteInit();
		userReceiveEmailsInit();
		seeArchivedInit();
		seeDeletedInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteUser(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteUser(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSiteUser(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteUser(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSiteUser(String var) {
		SiteUser oSiteUser = (SiteUser)this;
		switch(var) {
			case "userId":
				return oSiteUser.userId;
			case "userName":
				return oSiteUser.userName;
			case "userEmail":
				return oSiteUser.userEmail;
			case "userFirstName":
				return oSiteUser.userFirstName;
			case "userLastName":
				return oSiteUser.userLastName;
			case "userFullName":
				return oSiteUser.userFullName;
			case "userSite":
				return oSiteUser.userSite;
			case "userReceiveEmails":
				return oSiteUser.userReceiveEmails;
			case "seeArchived":
				return oSiteUser.seeArchived;
			case "seeDeleted":
				return oSiteUser.seeDeleted;
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
				o = attributeSiteUser(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSiteUser(String var, Object val) {
		SiteUser oSiteUser = (SiteUser)this;
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
					o = defineSiteUser(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSiteUser(String var, String val) {
		switch(var) {
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSiteUser = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSiteUser(solrDocument);
	}
	public void populateSiteUser(SolrDocument solrDocument) {
		SiteUser oSiteUser = (SiteUser)this;
		savesSiteUser = (List<String>)solrDocument.get("savesSiteUser_stored_strings");
		if(savesSiteUser != null) {

			if(savesSiteUser.contains("userId")) {
				String userId = (String)solrDocument.get("userId_stored_string");
				if(userId != null)
					oSiteUser.setUserId(userId);
			}

			if(savesSiteUser.contains("userName")) {
				String userName = (String)solrDocument.get("userName_stored_string");
				if(userName != null)
					oSiteUser.setUserName(userName);
			}

			if(savesSiteUser.contains("userEmail")) {
				String userEmail = (String)solrDocument.get("userEmail_stored_string");
				if(userEmail != null)
					oSiteUser.setUserEmail(userEmail);
			}

			if(savesSiteUser.contains("userFirstName")) {
				String userFirstName = (String)solrDocument.get("userFirstName_stored_string");
				if(userFirstName != null)
					oSiteUser.setUserFirstName(userFirstName);
			}

			if(savesSiteUser.contains("userLastName")) {
				String userLastName = (String)solrDocument.get("userLastName_stored_string");
				if(userLastName != null)
					oSiteUser.setUserLastName(userLastName);
			}

			if(savesSiteUser.contains("userFullName")) {
				String userFullName = (String)solrDocument.get("userFullName_stored_string");
				if(userFullName != null)
					oSiteUser.setUserFullName(userFullName);
			}

			if(savesSiteUser.contains("userSite")) {
				String userSite = (String)solrDocument.get("userSite_stored_string");
				if(userSite != null)
					oSiteUser.setUserSite(userSite);
			}

			if(savesSiteUser.contains("userReceiveEmails")) {
				Boolean userReceiveEmails = (Boolean)solrDocument.get("userReceiveEmails_stored_boolean");
				if(userReceiveEmails != null)
					oSiteUser.setUserReceiveEmails(userReceiveEmails);
			}

			if(savesSiteUser.contains("seeArchived")) {
				Boolean seeArchived = (Boolean)solrDocument.get("seeArchived_stored_boolean");
				if(seeArchived != null)
					oSiteUser.setSeeArchived(seeArchived);
			}

			if(savesSiteUser.contains("seeDeleted")) {
				Boolean seeDeleted = (Boolean)solrDocument.get("seeDeleted_stored_boolean");
				if(seeDeleted != null)
					oSiteUser.setSeeDeleted(seeDeleted);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.user.SiteUser"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SiteUser o = new SiteUser();
			o.siteRequestSiteUser(siteRequest);
			o.initDeepSiteUser(siteRequest);
			o.indexSiteUser();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSiteUser();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSiteUser(document);
	}

	public void indexSiteUser(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSiteUser(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSiteUser() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSiteUser(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSiteUser(SolrInputDocument document) {
		if(savesSiteUser != null)
			document.addField("savesSiteUser_stored_strings", savesSiteUser);

		if(userId != null) {
			document.addField("userId_indexed_string", userId);
			document.addField("userId_stored_string", userId);
		}
		if(userName != null) {
			document.addField("userName_indexed_string", userName);
			document.addField("userName_stored_string", userName);
		}
		if(userEmail != null) {
			document.addField("userEmail_indexed_string", userEmail);
			document.addField("userEmail_stored_string", userEmail);
		}
		if(userFirstName != null) {
			document.addField("userFirstName_indexed_string", userFirstName);
			document.addField("userFirstName_stored_string", userFirstName);
		}
		if(userLastName != null) {
			document.addField("userLastName_indexed_string", userLastName);
			document.addField("userLastName_stored_string", userLastName);
		}
		if(userFullName != null) {
			document.addField("userFullName_indexed_string", userFullName);
			document.addField("userFullName_stored_string", userFullName);
		}
		if(userSite != null) {
			document.addField("userSite_indexed_string", userSite);
			document.addField("userSite_stored_string", userSite);
		}
		if(userReceiveEmails != null) {
			document.addField("userReceiveEmails_indexed_boolean", userReceiveEmails);
			document.addField("userReceiveEmails_stored_boolean", userReceiveEmails);
		}
		if(seeArchived != null) {
			document.addField("seeArchived_indexed_boolean", seeArchived);
			document.addField("seeArchived_stored_boolean", seeArchived);
		}
		if(seeDeleted != null) {
			document.addField("seeDeleted_indexed_boolean", seeDeleted);
			document.addField("seeDeleted_stored_boolean", seeDeleted);
		}
		super.indexCluster(document);

	}

	public void unindexSiteUser() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSiteUser(siteRequest);
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
		storeSiteUser(solrDocument);
	}
	public void storeSiteUser(SolrDocument solrDocument) {
		SiteUser oSiteUser = (SiteUser)this;

		String userId = (String)solrDocument.get("userId_stored_string");
		if(userId != null)
			oSiteUser.setUserId(userId);

		String userName = (String)solrDocument.get("userName_stored_string");
		if(userName != null)
			oSiteUser.setUserName(userName);

		String userEmail = (String)solrDocument.get("userEmail_stored_string");
		if(userEmail != null)
			oSiteUser.setUserEmail(userEmail);

		String userFirstName = (String)solrDocument.get("userFirstName_stored_string");
		if(userFirstName != null)
			oSiteUser.setUserFirstName(userFirstName);

		String userLastName = (String)solrDocument.get("userLastName_stored_string");
		if(userLastName != null)
			oSiteUser.setUserLastName(userLastName);

		String userFullName = (String)solrDocument.get("userFullName_stored_string");
		if(userFullName != null)
			oSiteUser.setUserFullName(userFullName);

		String userSite = (String)solrDocument.get("userSite_stored_string");
		if(userSite != null)
			oSiteUser.setUserSite(userSite);

		Boolean userReceiveEmails = (Boolean)solrDocument.get("userReceiveEmails_stored_boolean");
		if(userReceiveEmails != null)
			oSiteUser.setUserReceiveEmails(userReceiveEmails);

		Boolean seeArchived = (Boolean)solrDocument.get("seeArchived_stored_boolean");
		if(seeArchived != null)
			oSiteUser.setSeeArchived(seeArchived);

		Boolean seeDeleted = (Boolean)solrDocument.get("seeDeleted_stored_boolean");
		if(seeDeleted != null)
			oSiteUser.setSeeDeleted(seeDeleted);

		super.storeCluster(solrDocument);
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodySiteUser();
	}

	public void htmlBodySiteUser() {
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
		if(!(o instanceof SiteUser))
			return false;
		SiteUser that = (SiteUser)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SiteUser { ");
		sb.append(" }");
		return sb.toString();
	}
}
