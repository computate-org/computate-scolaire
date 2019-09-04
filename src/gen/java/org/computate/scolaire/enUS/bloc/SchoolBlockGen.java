package org.computate.scolaire.enUS.bloc;

import java.util.Date;
import org.computate.scolaire.enUS.session.SchoolSession;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import java.time.Instant;
import java.time.ZoneId;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolBlockGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolBlock.class);

	public static final String SchoolBlock_UnNom = "a block";
	public static final String SchoolBlock_Ce = "this ";
	public static final String SchoolBlock_CeNom = "this block";
	public static final String SchoolBlock_Un = "an ";
	public static final String SchoolBlock_LeNom = "the block";
	public static final String SchoolBlock_NomSingulier = "block";
	public static final String SchoolBlock_NomPluriel = "blocks";
	public static final String SchoolBlock_NomActuel = "current block";
	public static final String SchoolBlock_TousNom = "the blocks";
	public static final String SchoolBlock_RechercherTousNomPar = "search blocks by ";
	public static final String SchoolBlock_LesNoms = "the blocks";
	public static final String SchoolBlock_AucunNomTrouve = "no block found";
	public static final String SchoolBlock_NomVar = "block";
	public static final String SchoolBlock_DeNom = "of block";
	public static final String SchoolBlock_Couleur = "indigo";
	public static final String SchoolBlock_IconeGroupe = "duotone";
	public static final String SchoolBlock_IconeNom = "bell-o";

	///////////////
	// schoolKey //
	///////////////

	/**	L'entité « schoolKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/>L'entité « schoolKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolKey(Wrap<Long> c);

	public Long getSchoolKey() {
		return schoolKey;
	}

	public void setSchoolKey(Long schoolKey) {
		this.schoolKey = schoolKey;
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSchoolKey(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrSchoolKey() {
		return schoolKey;
	}

	public String strSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String nomAffichageSchoolKey() {
		return "school";
	}

	public String htmTooltipSchoolKey() {
		return null;
	}

	public String htmSchoolKey() {
		return schoolKey == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKey());
	}

	public void htmSchoolKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SchoolKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SchoolKey() {");
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
				r.l("				data: {\"setSchoolKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolKey\"");
							r.s(" value=\"", htmSchoolKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolKey());
			}
			r.l("</div>");
		}
	}

	//////////////
	// anneeCle //
	//////////////

	/**	L'entité « anneeCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long anneeCle;
	@JsonIgnore
	public Wrap<Long> anneeCleWrap = new Wrap<Long>().p(this).c(Long.class).var("anneeCle").o(anneeCle);

	/**	<br/>L'entité « anneeCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeCle(Wrap<Long> c);

	public Long getAnneeCle() {
		return anneeCle;
	}

	public void setAnneeCle(Long anneeCle) {
		this.anneeCle = anneeCle;
		this.anneeCleWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAnneeCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock anneeCleInit() {
		if(!anneeCleWrap.alreadyInitialized) {
			_anneeCle(anneeCleWrap);
			if(anneeCle == null)
				setAnneeCle(anneeCleWrap.o);
		}
		anneeCleWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrAnneeCle() {
		return anneeCle;
	}

	public String strAnneeCle() {
		return anneeCle == null ? "" : anneeCle.toString();
	}

	public String nomAffichageAnneeCle() {
		return null;
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
	}

	public void htmAnneeCle(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AnneeCle\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AnneeCle() {");
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
				r.l("				data: {\"setAnneeCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeCle\"");
							r.s(" value=\"", htmAnneeCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeCle());
			}
			r.l("</div>");
		}
	}

	///////////////
	// seasonKey //
	///////////////

	/**	L'entité « seasonKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long seasonKey;
	@JsonIgnore
	public Wrap<Long> seasonKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("seasonKey").o(seasonKey);

	/**	<br/>L'entité « seasonKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKey">Trouver l'entité seasonKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonKey(Wrap<Long> c);

	public Long getSeasonKey() {
		return seasonKey;
	}

	public void setSeasonKey(Long seasonKey) {
		this.seasonKey = seasonKey;
		this.seasonKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonKey(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.seasonKey = Long.parseLong(o);
		this.seasonKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonKeyInit() {
		if(!seasonKeyWrap.alreadyInitialized) {
			_seasonKey(seasonKeyWrap);
			if(seasonKey == null)
				setSeasonKey(seasonKeyWrap.o);
		}
		seasonKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrSeasonKey() {
		return seasonKey;
	}

	public String strSeasonKey() {
		return seasonKey == null ? "" : seasonKey.toString();
	}

	public String nomAffichageSeasonKey() {
		return "season";
	}

	public String htmTooltipSeasonKey() {
		return null;
	}

	public String htmSeasonKey() {
		return seasonKey == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKey());
	}

	public void htmSeasonKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonKey() {");
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
				r.l("				data: {\"setSeasonKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonKey\"");
							r.s(" value=\"", htmSeasonKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonKey());
			}
			r.l("</div>");
		}
	}

	////////////////
	// sessionKey //
	////////////////

	/**	L'entité « sessionKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long sessionKey;
	@JsonIgnore
	public Wrap<Long> sessionKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("sessionKey").o(sessionKey);

	/**	<br/>L'entité « sessionKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Trouver l'entité sessionKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionKey(Wrap<Long> c);

	public Long getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(Long sessionKey) {
		this.sessionKey = sessionKey;
		this.sessionKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionKey(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.sessionKey = Long.parseLong(o);
		this.sessionKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionKeyInit() {
		if(!sessionKeyWrap.alreadyInitialized) {
			_sessionKey(sessionKeyWrap);
			if(sessionKey == null)
				setSessionKey(sessionKeyWrap.o);
		}
		sessionKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrSessionKey() {
		return sessionKey;
	}

	public String strSessionKey() {
		return sessionKey == null ? "" : sessionKey.toString();
	}

	public String nomAffichageSessionKey() {
		return "key";
	}

	public String htmTooltipSessionKey() {
		return null;
	}

	public String htmSessionKey() {
		return sessionKey == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKey());
	}

	public void htmSessionKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SessionKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SessionKey() {");
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
				r.l("				data: {\"setSessionKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionKey\"");
							r.s(" value=\"", htmSessionKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionKey());
			}
			r.l("</div>");
		}
	}

	////////////
	// ageKey //
	////////////

	/**	L'entité « ageKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long ageKey;
	@JsonIgnore
	public Wrap<Long> ageKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("ageKey").o(ageKey);

	/**	<br/>L'entité « ageKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Trouver l'entité ageKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageKey(Wrap<Long> c);

	public Long getAgeKey() {
		return ageKey;
	}

	public void setAgeKey(Long ageKey) {
		this.ageKey = ageKey;
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeKey(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ageKey = Long.parseLong(o);
		this.ageKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageKeyInit() {
		if(!ageKeyWrap.alreadyInitialized) {
			_ageKey(ageKeyWrap);
			if(ageKey == null)
				setAgeKey(ageKeyWrap.o);
		}
		ageKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrAgeKey() {
		return ageKey;
	}

	public String strAgeKey() {
		return ageKey == null ? "" : ageKey.toString();
	}

	public String nomAffichageAgeKey() {
		return "key";
	}

	public String htmTooltipAgeKey() {
		return null;
	}

	public String htmAgeKey() {
		return ageKey == null ? "" : StringEscapeUtils.escapeHtml4(strAgeKey());
	}

	public void htmAgeKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeKey() {");
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
				r.l("				data: {\"setAgeKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageKey\"");
							r.s(" value=\"", htmAgeKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeKey());
			}
			r.l("</div>");
		}
	}

	////////////
	// ageKey //
	////////////

	/**	L'entité « ageKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long ageKey;
	@JsonIgnore
	public Wrap<Long> ageKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("ageKey").o(ageKey);

	/**	<br/>L'entité « ageKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Trouver l'entité ageKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageKey(Wrap<Long> c);

	public Long getAgeKey() {
		return ageKey;
	}

	public void setAgeKey(Long ageKey) {
		this.ageKey = ageKey;
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeKey(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ageKey = Long.parseLong(o);
		this.ageKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageKeyInit() {
		if(!ageKeyWrap.alreadyInitialized) {
			_ageKey(ageKeyWrap);
			if(ageKey == null)
				setAgeKey(ageKeyWrap.o);
		}
		ageKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrAgeKey() {
		return ageKey;
	}

	public String strAgeKey() {
		return ageKey == null ? "" : ageKey.toString();
	}

	public String nomAffichageAgeKey() {
		return "key";
	}

	public String htmTooltipAgeKey() {
		return null;
	}

	public String htmAgeKey() {
		return ageKey == null ? "" : StringEscapeUtils.escapeHtml4(strAgeKey());
	}

	public void htmAgeKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeKey() {");
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
				r.l("				data: {\"setAgeKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageKey\"");
							r.s(" value=\"", htmAgeKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeKey());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// enrollmentKeys //
	////////////////////

	/**	L'entité « enrollmentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> enrollmentKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/>L'entité « enrollmentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
	 * <br/>
	 * @param enrollmentKeys est l'entité déjà construit. 
	 **/
	protected abstract void _enrollmentKeys(List<Long> o);

	public List<Long> getEnrollmentKeys() {
		return enrollmentKeys;
	}

	public void setEnrollmentKeys(List<Long> enrollmentKeys) {
		this.enrollmentKeys = enrollmentKeys;
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public SchoolBlock addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolBlock)this;
	}
	public SchoolBlock addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolBlock)this;
	}
	public SchoolBlock setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SchoolBlock)this;
	}
	public SchoolBlock addEnrollmentKeys(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolBlock)this;
	}
	protected SchoolBlock enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public List<Long> solrEnrollmentKeys() {
		return enrollmentKeys;
	}

	public String strEnrollmentKeys() {
		return enrollmentKeys == null ? "" : enrollmentKeys.toString();
	}

	public String nomAffichageEnrollmentKeys() {
		return null;
	}

	public String htmTooltipEnrollmentKeys() {
		return null;
	}

	public String htmEnrollmentKeys() {
		return enrollmentKeys == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentKeys());
	}

	public void htmEnrollmentKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "EnrollmentKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "EnrollmentKeys() {");
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
				r.l("				data: {\"setEnrollmentKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentKeys\"");
							r.s(" value=\"", htmEnrollmentKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentKeys());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// educationSort //
	///////////////////

	/**	L'entité « educationSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer educationSort;
	@JsonIgnore
	public Wrap<Integer> educationSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("educationSort").o(educationSort);

	/**	<br/>L'entité « educationSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _educationSort(Wrap<Integer> c);

	public Integer getEducationSort() {
		return educationSort;
	}

	public void setEducationSort(Integer educationSort) {
		this.educationSort = educationSort;
		this.educationSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setEducationSort(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.educationSort = Integer.parseInt(o);
		this.educationSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock educationSortInit() {
		if(!educationSortWrap.alreadyInitialized) {
			_educationSort(educationSortWrap);
			if(educationSort == null)
				setEducationSort(educationSortWrap.o);
		}
		educationSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrEducationSort() {
		return educationSort;
	}

	public String strEducationSort() {
		return educationSort == null ? "" : educationSort.toString();
	}

	public String nomAffichageEducationSort() {
		return null;
	}

	public String htmTooltipEducationSort() {
		return null;
	}

	public String htmEducationSort() {
		return educationSort == null ? "" : StringEscapeUtils.escapeHtml4(strEducationSort());
	}

	public void htmEducationSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "EducationSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "EducationSort() {");
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
				r.l("				data: {\"setEducationSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEducationSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"educationSort\"");
							r.s(" value=\"", htmEducationSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEducationSort());
			}
			r.l("</div>");
		}
	}

	////////////////
	// schoolSort //
	////////////////

	/**	L'entité « schoolSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer schoolSort;
	@JsonIgnore
	public Wrap<Integer> schoolSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolSort").o(schoolSort);

	/**	<br/>L'entité « schoolSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolSort(Wrap<Integer> c);

	public Integer getSchoolSort() {
		return schoolSort;
	}

	public void setSchoolSort(Integer schoolSort) {
		this.schoolSort = schoolSort;
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSchoolSort(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrSchoolSort() {
		return schoolSort;
	}

	public String strSchoolSort() {
		return schoolSort == null ? "" : schoolSort.toString();
	}

	public String nomAffichageSchoolSort() {
		return null;
	}

	public String htmTooltipSchoolSort() {
		return null;
	}

	public String htmSchoolSort() {
		return schoolSort == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolSort());
	}

	public void htmSchoolSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SchoolSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SchoolSort() {");
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
				r.l("				data: {\"setSchoolSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolSort\"");
							r.s(" value=\"", htmSchoolSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolSort());
			}
			r.l("</div>");
		}
	}

	//////////////
	// yearSort //
	//////////////

	/**	L'entité « yearSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer yearSort;
	@JsonIgnore
	public Wrap<Integer> yearSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearSort").o(yearSort);

	/**	<br/>L'entité « yearSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Trouver l'entité yearSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearSort(Wrap<Integer> c);

	public Integer getYearSort() {
		return yearSort;
	}

	public void setYearSort(Integer yearSort) {
		this.yearSort = yearSort;
		this.yearSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setYearSort(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.yearSort = Integer.parseInt(o);
		this.yearSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock yearSortInit() {
		if(!yearSortWrap.alreadyInitialized) {
			_yearSort(yearSortWrap);
			if(yearSort == null)
				setYearSort(yearSortWrap.o);
		}
		yearSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrYearSort() {
		return yearSort;
	}

	public String strYearSort() {
		return yearSort == null ? "" : yearSort.toString();
	}

	public String nomAffichageYearSort() {
		return null;
	}

	public String htmTooltipYearSort() {
		return null;
	}

	public String htmYearSort() {
		return yearSort == null ? "" : StringEscapeUtils.escapeHtml4(strYearSort());
	}

	public void htmYearSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "YearSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "YearSort() {");
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
				r.l("				data: {\"setYearSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearSort\"");
							r.s(" value=\"", htmYearSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearSort());
			}
			r.l("</div>");
		}
	}

	////////////////
	// seasonSort //
	////////////////

	/**	L'entité « seasonSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer seasonSort;
	@JsonIgnore
	public Wrap<Integer> seasonSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("seasonSort").o(seasonSort);

	/**	<br/>L'entité « seasonSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Trouver l'entité seasonSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonSort(Wrap<Integer> c);

	public Integer getSeasonSort() {
		return seasonSort;
	}

	public void setSeasonSort(Integer seasonSort) {
		this.seasonSort = seasonSort;
		this.seasonSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonSort(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.seasonSort = Integer.parseInt(o);
		this.seasonSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonSortInit() {
		if(!seasonSortWrap.alreadyInitialized) {
			_seasonSort(seasonSortWrap);
			if(seasonSort == null)
				setSeasonSort(seasonSortWrap.o);
		}
		seasonSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrSeasonSort() {
		return seasonSort;
	}

	public String strSeasonSort() {
		return seasonSort == null ? "" : seasonSort.toString();
	}

	public String nomAffichageSeasonSort() {
		return null;
	}

	public String htmTooltipSeasonSort() {
		return null;
	}

	public String htmSeasonSort() {
		return seasonSort == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonSort());
	}

	public void htmSeasonSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonSort() {");
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
				r.l("				data: {\"setSeasonSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonSort\"");
							r.s(" value=\"", htmSeasonSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonSort());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// sessionSort //
	/////////////////

	/**	L'entité « sessionSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer sessionSort;
	@JsonIgnore
	public Wrap<Integer> sessionSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sessionSort").o(sessionSort);

	/**	<br/>L'entité « sessionSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSort">Trouver l'entité sessionSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionSort(Wrap<Integer> c);

	public Integer getSessionSort() {
		return sessionSort;
	}

	public void setSessionSort(Integer sessionSort) {
		this.sessionSort = sessionSort;
		this.sessionSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionSort(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.sessionSort = Integer.parseInt(o);
		this.sessionSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionSortInit() {
		if(!sessionSortWrap.alreadyInitialized) {
			_sessionSort(sessionSortWrap);
			if(sessionSort == null)
				setSessionSort(sessionSortWrap.o);
		}
		sessionSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrSessionSort() {
		return sessionSort;
	}

	public String strSessionSort() {
		return sessionSort == null ? "" : sessionSort.toString();
	}

	public String nomAffichageSessionSort() {
		return null;
	}

	public String htmTooltipSessionSort() {
		return null;
	}

	public String htmSessionSort() {
		return sessionSort == null ? "" : StringEscapeUtils.escapeHtml4(strSessionSort());
	}

	public void htmSessionSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SessionSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SessionSort() {");
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
				r.l("				data: {\"setSessionSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionSort\"");
							r.s(" value=\"", htmSessionSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionSort());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// sessionSearch //
	///////////////////

	/**	L'entité « sessionSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolSession>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolSession> sessionSearch = new SearchList<SchoolSession>();
	@JsonIgnore
	public Wrap<SearchList<SchoolSession>> sessionSearchWrap = new Wrap<SearchList<SchoolSession>>().p(this).c(SearchList.class).var("sessionSearch").o(sessionSearch);

	/**	<br/>L'entité « sessionSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolSession>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSearch">Trouver l'entité sessionSearch dans Solr</a>
	 * <br/>
	 * @param sessionSearch est l'entité déjà construit. 
	 **/
	protected abstract void _sessionSearch(SearchList<SchoolSession> l);

	public SearchList<SchoolSession> getSessionSearch() {
		return sessionSearch;
	}

	public void setSessionSearch(SearchList<SchoolSession> sessionSearch) {
		this.sessionSearch = sessionSearch;
		this.sessionSearchWrap.alreadyInitialized = true;
	}
	protected SchoolBlock sessionSearchInit() {
		if(!sessionSearchWrap.alreadyInitialized) {
			_sessionSearch(sessionSearch);
		}
		sessionSearch.initDeepForClass(siteRequest_);
		sessionSearchWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	/////////////
	// session //
	/////////////

	/**	L'entité « session »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolSession session;
	@JsonIgnore
	public Wrap<SchoolSession> sessionWrap = new Wrap<SchoolSession>().p(this).c(SchoolSession.class).var("session").o(session);

	/**	<br/>L'entité « session »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:session">Trouver l'entité session dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _session(Wrap<SchoolSession> c);

	public SchoolSession getSession() {
		return session;
	}

	public void setSession(SchoolSession session) {
		this.session = session;
		this.sessionWrap.alreadyInitialized = true;
	}
	protected SchoolBlock sessionInit() {
		if(!sessionWrap.alreadyInitialized) {
			_session(sessionWrap);
			if(session == null)
				setSession(sessionWrap.o);
		}
		if(session != null)
			session.initDeepForClass(siteRequest_);
		sessionWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	////////////////////////
	// schoolNameComplete //
	////////////////////////

	/**	L'entité « schoolNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String schoolNameComplete;
	@JsonIgnore
	public Wrap<String> schoolNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("schoolNameComplete").o(schoolNameComplete);

	/**	<br/>L'entité « schoolNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNameComplete">Trouver l'entité schoolNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolNameComplete(Wrap<String> c);

	public String getSchoolNameComplete() {
		return schoolNameComplete;
	}

	public void setSchoolNameComplete(String schoolNameComplete) {
		this.schoolNameComplete = schoolNameComplete;
		this.schoolNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolBlock schoolNameCompleteInit() {
		if(!schoolNameCompleteWrap.alreadyInitialized) {
			_schoolNameComplete(schoolNameCompleteWrap);
			if(schoolNameComplete == null)
				setSchoolNameComplete(schoolNameCompleteWrap.o);
		}
		schoolNameCompleteWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrSchoolNameComplete() {
		return schoolNameComplete;
	}

	public String strSchoolNameComplete() {
		return schoolNameComplete == null ? "" : schoolNameComplete;
	}

	public String nomAffichageSchoolNameComplete() {
		return "r: EcoleNomComplet";
	}

	public String htmTooltipSchoolNameComplete() {
		return null;
	}

	public String htmSchoolNameComplete() {
		return schoolNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolNameComplete());
	}

	public void htmSchoolNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SchoolNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SchoolNameComplete() {");
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
				r.l("				data: {\"setSchoolNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolNameComplete\"");
							r.s(" value=\"", htmSchoolNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolNameComplete());
			}
			r.l("</div>");
		}
	}

	///////////////
	// yearStart //
	///////////////

	/**	L'entité « yearStart »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate yearStart;
	@JsonIgnore
	public Wrap<LocalDate> yearStartWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearStart(Wrap<LocalDate> c);

	public LocalDate getYearStart() {
		return yearStart;
	}

	public void setYearStart(LocalDate yearStart) {
		this.yearStart = yearStart;
		this.yearStartWrap.alreadyInitialized = true;
	}
	public SchoolBlock setYearStart(Instant o) {
		this.yearStart = LocalDate.from(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setYearStart(String o) {
		this.yearStart = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setYearStart(Date o) {
		this.yearStart = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrYearStart() {
		return yearStart == null ? null : Date.from(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strYearStart() {
		return yearStart == null ? "" : yearStart.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageYearStart() {
		return "start of year";
	}

	public String htmTooltipYearStart() {
		return null;
	}

	public String htmYearStart() {
		return yearStart == null ? "" : StringEscapeUtils.escapeHtml4(strYearStart());
	}

	public void htmYearStart(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "YearStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "YearStart() {");
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
				r.l("				data: {\"setYearStart\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearStart()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearStart\"");
							r.s(" value=\"", htmYearStart(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearStart());
			}
			r.l("</div>");
		}
	}

	/////////////
	// yearEnd //
	/////////////

	/**	L'entité « yearEnd »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate yearEnd;
	@JsonIgnore
	public Wrap<LocalDate> yearEndWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearEnd(Wrap<LocalDate> c);

	public LocalDate getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(LocalDate yearEnd) {
		this.yearEnd = yearEnd;
		this.yearEndWrap.alreadyInitialized = true;
	}
	public SchoolBlock setYearEnd(Instant o) {
		this.yearEnd = LocalDate.from(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setYearEnd(String o) {
		this.yearEnd = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setYearEnd(Date o) {
		this.yearEnd = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrYearEnd() {
		return yearEnd == null ? null : Date.from(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strYearEnd() {
		return yearEnd == null ? "" : yearEnd.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageYearEnd() {
		return "end of year";
	}

	public String htmTooltipYearEnd() {
		return null;
	}

	public String htmYearEnd() {
		return yearEnd == null ? "" : StringEscapeUtils.escapeHtml4(strYearEnd());
	}

	public void htmYearEnd(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "YearEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "YearEnd() {");
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
				r.l("				data: {\"setYearEnd\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearEnd()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearEnd\"");
							r.s(" value=\"", htmYearEnd(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearEnd());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// seasonStart //
	/////////////////

	/**	L'entité « seasonStart »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate seasonStart;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStart").o(seasonStart);

	/**	<br/>L'entité « seasonStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStart">Trouver l'entité seasonStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonStart(Wrap<LocalDate> c);

	public LocalDate getSeasonStart() {
		return seasonStart;
	}

	public void setSeasonStart(LocalDate seasonStart) {
		this.seasonStart = seasonStart;
		this.seasonStartWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonStart(Instant o) {
		this.seasonStart = LocalDate.from(o);
		this.seasonStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSeasonStart(String o) {
		this.seasonStart = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonStart(Date o) {
		this.seasonStart = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.seasonStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonStartInit() {
		if(!seasonStartWrap.alreadyInitialized) {
			_seasonStart(seasonStartWrap);
			if(seasonStart == null)
				setSeasonStart(seasonStartWrap.o);
		}
		seasonStartWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrSeasonStart() {
		return seasonStart == null ? null : Date.from(seasonStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSeasonStart() {
		return seasonStart == null ? "" : seasonStart.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSeasonStart() {
		return "start of season";
	}

	public String htmTooltipSeasonStart() {
		return null;
	}

	public String htmSeasonStart() {
		return seasonStart == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStart());
	}

	public void htmSeasonStart(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonStart() {");
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
				r.l("				data: {\"setSeasonStart\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonStart()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonStart\"");
							r.s(" value=\"", htmSeasonStart(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonStart());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// seasonSummer //
	//////////////////

	/**	L'entité « seasonSummer »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean seasonSummer;
	@JsonIgnore
	public Wrap<Boolean> seasonSummerWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonSummer").o(seasonSummer);

	/**	<br/>L'entité « seasonSummer »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSummer">Trouver l'entité seasonSummer dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonSummer(Wrap<Boolean> c);

	public Boolean getSeasonSummer() {
		return seasonSummer;
	}

	public void setSeasonSummer(Boolean seasonSummer) {
		this.seasonSummer = seasonSummer;
		this.seasonSummerWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonSummer(String o) {
		this.seasonSummer = Boolean.parseBoolean(o);
		this.seasonSummerWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonSummerInit() {
		if(!seasonSummerWrap.alreadyInitialized) {
			_seasonSummer(seasonSummerWrap);
			if(seasonSummer == null)
				setSeasonSummer(seasonSummerWrap.o);
		}
		seasonSummerWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrSeasonSummer() {
		return seasonSummer;
	}

	public String strSeasonSummer() {
		return seasonSummer == null ? "" : seasonSummer.toString();
	}

	public String nomAffichageSeasonSummer() {
		return "summer";
	}

	public String htmTooltipSeasonSummer() {
		return null;
	}

	public String htmSeasonSummer() {
		return seasonSummer == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonSummer());
	}

	public void htmSeasonSummer(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonSummer\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonSummer() {");
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
				r.l("				data: {\"setSeasonSummer\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonSummer()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonSummer\"");
							r.s(" value=\"", htmSeasonSummer(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonSummer());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// seasonWinter //
	//////////////////

	/**	L'entité « seasonWinter »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean seasonWinter;
	@JsonIgnore
	public Wrap<Boolean> seasonWinterWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonWinter").o(seasonWinter);

	/**	<br/>L'entité « seasonWinter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonWinter">Trouver l'entité seasonWinter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonWinter(Wrap<Boolean> c);

	public Boolean getSeasonWinter() {
		return seasonWinter;
	}

	public void setSeasonWinter(Boolean seasonWinter) {
		this.seasonWinter = seasonWinter;
		this.seasonWinterWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonWinter(String o) {
		this.seasonWinter = Boolean.parseBoolean(o);
		this.seasonWinterWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonWinterInit() {
		if(!seasonWinterWrap.alreadyInitialized) {
			_seasonWinter(seasonWinterWrap);
			if(seasonWinter == null)
				setSeasonWinter(seasonWinterWrap.o);
		}
		seasonWinterWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrSeasonWinter() {
		return seasonWinter;
	}

	public String strSeasonWinter() {
		return seasonWinter == null ? "" : seasonWinter.toString();
	}

	public String nomAffichageSeasonWinter() {
		return "winter";
	}

	public String htmTooltipSeasonWinter() {
		return null;
	}

	public String htmSeasonWinter() {
		return seasonWinter == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonWinter());
	}

	public void htmSeasonWinter(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonWinter\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonWinter() {");
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
				r.l("				data: {\"setSeasonWinter\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonWinter()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonWinter\"");
							r.s(" value=\"", htmSeasonWinter(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonWinter());
			}
			r.l("</div>");
		}
	}

	/////////////////////////
	// seasonEnrollmentFee //
	/////////////////////////

	/**	L'entité « seasonEnrollmentFee »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal seasonEnrollmentFee;
	@JsonIgnore
	public Wrap<BigDecimal> seasonEnrollmentFeeWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("seasonEnrollmentFee").o(seasonEnrollmentFee);

	/**	<br/>L'entité « seasonEnrollmentFee »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonEnrollmentFee">Trouver l'entité seasonEnrollmentFee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonEnrollmentFee(Wrap<BigDecimal> c);

	public BigDecimal getSeasonEnrollmentFee() {
		return seasonEnrollmentFee;
	}

	public void setSeasonEnrollmentFee(BigDecimal seasonEnrollmentFee) {
		this.seasonEnrollmentFee = seasonEnrollmentFee;
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonEnrollmentFee(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.seasonEnrollmentFee = new BigDecimal(o);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonEnrollmentFee(Double o) {
			this.seasonEnrollmentFee = new BigDecimal(o);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonEnrollmentFee(Integer o) {
			this.seasonEnrollmentFee = new BigDecimal(o);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonEnrollmentFeeInit() {
		if(!seasonEnrollmentFeeWrap.alreadyInitialized) {
			_seasonEnrollmentFee(seasonEnrollmentFeeWrap);
			if(seasonEnrollmentFee == null)
				setSeasonEnrollmentFee(seasonEnrollmentFeeWrap.o);
		}
		seasonEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Double solrSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? null : seasonEnrollmentFee.doubleValue();
	}

	public String strSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? "" : seasonEnrollmentFee.toString();
	}

	public String nomAffichageSeasonEnrollmentFee() {
		return "enrollment fee";
	}

	public String htmTooltipSeasonEnrollmentFee() {
		return null;
	}

	public String htmSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonEnrollmentFee());
	}

	public void htmSeasonEnrollmentFee(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonEnrollmentFee\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonEnrollmentFee() {");
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
				r.l("				data: {\"setSeasonEnrollmentFee\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonEnrollmentFee()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonEnrollmentFee\"");
							r.s(" value=\"", htmSeasonEnrollmentFee(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonEnrollmentFee());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// seasonNameComplete //
	////////////////////////

	/**	L'entité « seasonNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String seasonNameComplete;
	@JsonIgnore
	public Wrap<String> seasonNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("seasonNameComplete").o(seasonNameComplete);

	/**	<br/>L'entité « seasonNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonNameComplete">Trouver l'entité seasonNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonNameComplete(Wrap<String> c);

	public String getSeasonNameComplete() {
		return seasonNameComplete;
	}

	public void setSeasonNameComplete(String seasonNameComplete) {
		this.seasonNameComplete = seasonNameComplete;
		this.seasonNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolBlock seasonNameCompleteInit() {
		if(!seasonNameCompleteWrap.alreadyInitialized) {
			_seasonNameComplete(seasonNameCompleteWrap);
			if(seasonNameComplete == null)
				setSeasonNameComplete(seasonNameCompleteWrap.o);
		}
		seasonNameCompleteWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrSeasonNameComplete() {
		return seasonNameComplete;
	}

	public String strSeasonNameComplete() {
		return seasonNameComplete == null ? "" : seasonNameComplete;
	}

	public String nomAffichageSeasonNameComplete() {
		return null;
	}

	public String htmTooltipSeasonNameComplete() {
		return null;
	}

	public String htmSeasonNameComplete() {
		return seasonNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonNameComplete());
	}

	public void htmSeasonNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonNameComplete() {");
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
				r.l("				data: {\"setSeasonNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonNameComplete\"");
							r.s(" value=\"", htmSeasonNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonNameComplete());
			}
			r.l("</div>");
		}
	}

	///////////////
	// seasonEnd //
	///////////////

	/**	L'entité « seasonEnd »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate seasonEnd;
	@JsonIgnore
	public Wrap<LocalDate> seasonEndWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonEnd").o(seasonEnd);

	/**	<br/>L'entité « seasonEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonEnd">Trouver l'entité seasonEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonEnd(Wrap<LocalDate> c);

	public LocalDate getSeasonEnd() {
		return seasonEnd;
	}

	public void setSeasonEnd(LocalDate seasonEnd) {
		this.seasonEnd = seasonEnd;
		this.seasonEndWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonEnd(Instant o) {
		this.seasonEnd = LocalDate.from(o);
		this.seasonEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSeasonEnd(String o) {
		this.seasonEnd = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonEnd(Date o) {
		this.seasonEnd = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.seasonEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonEndInit() {
		if(!seasonEndWrap.alreadyInitialized) {
			_seasonEnd(seasonEndWrap);
			if(seasonEnd == null)
				setSeasonEnd(seasonEndWrap.o);
		}
		seasonEndWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrSeasonEnd() {
		return seasonEnd == null ? null : Date.from(seasonEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSeasonEnd() {
		return seasonEnd == null ? "" : seasonEnd.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSeasonEnd() {
		return "end of season";
	}

	public String htmTooltipSeasonEnd() {
		return null;
	}

	public String htmSeasonEnd() {
		return seasonEnd == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonEnd());
	}

	public void htmSeasonEnd(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonEnd() {");
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
				r.l("				data: {\"setSeasonEnd\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonEnd()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonEnd\"");
							r.s(" value=\"", htmSeasonEnd(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonEnd());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// sessionStartDay //
	/////////////////////

	/**	L'entité « sessionStartDay »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate sessionStartDay;
	@JsonIgnore
	public Wrap<LocalDate> sessionStartDayWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionStartDay").o(sessionStartDay);

	/**	<br/>L'entité « sessionStartDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDay">Trouver l'entité sessionStartDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionStartDay(Wrap<LocalDate> c);

	public LocalDate getSessionStartDay() {
		return sessionStartDay;
	}

	public void setSessionStartDay(LocalDate sessionStartDay) {
		this.sessionStartDay = sessionStartDay;
		this.sessionStartDayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionStartDay(Instant o) {
		this.sessionStartDay = LocalDate.from(o);
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSessionStartDay(String o) {
		this.sessionStartDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSessionStartDay(Date o) {
		this.sessionStartDay = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionStartDayInit() {
		if(!sessionStartDayWrap.alreadyInitialized) {
			_sessionStartDay(sessionStartDayWrap);
			if(sessionStartDay == null)
				setSessionStartDay(sessionStartDayWrap.o);
		}
		sessionStartDayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrSessionStartDay() {
		return sessionStartDay == null ? null : Date.from(sessionStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionStartDay() {
		return sessionStartDay == null ? "" : sessionStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSessionStartDay() {
		return "start of the session";
	}

	public String htmTooltipSessionStartDay() {
		return null;
	}

	public String htmSessionStartDay() {
		return sessionStartDay == null ? "" : StringEscapeUtils.escapeHtml4(strSessionStartDay());
	}

	public void htmSessionStartDay(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SessionStartDay\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SessionStartDay() {");
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
				r.l("				data: {\"setSessionStartDay\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionStartDay()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionStartDay\"");
							r.s(" value=\"", htmSessionStartDay(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionStartDay());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// sessionEndDay //
	///////////////////

	/**	L'entité « sessionEndDay »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate sessionEndDay;
	@JsonIgnore
	public Wrap<LocalDate> sessionEndDayWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionEndDay").o(sessionEndDay);

	/**	<br/>L'entité « sessionEndDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDay">Trouver l'entité sessionEndDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionEndDay(Wrap<LocalDate> c);

	public LocalDate getSessionEndDay() {
		return sessionEndDay;
	}

	public void setSessionEndDay(LocalDate sessionEndDay) {
		this.sessionEndDay = sessionEndDay;
		this.sessionEndDayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionEndDay(Instant o) {
		this.sessionEndDay = LocalDate.from(o);
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSessionEndDay(String o) {
		this.sessionEndDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSessionEndDay(Date o) {
		this.sessionEndDay = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionEndDayInit() {
		if(!sessionEndDayWrap.alreadyInitialized) {
			_sessionEndDay(sessionEndDayWrap);
			if(sessionEndDay == null)
				setSessionEndDay(sessionEndDayWrap.o);
		}
		sessionEndDayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrSessionEndDay() {
		return sessionEndDay == null ? null : Date.from(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionEndDay() {
		return sessionEndDay == null ? "" : sessionEndDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSessionEndDay() {
		return "end of the session";
	}

	public String htmTooltipSessionEndDay() {
		return null;
	}

	public String htmSessionEndDay() {
		return sessionEndDay == null ? "" : StringEscapeUtils.escapeHtml4(strSessionEndDay());
	}

	public void htmSessionEndDay(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SessionEndDay\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SessionEndDay() {");
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
				r.l("				data: {\"setSessionEndDay\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionEndDay()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionEndDay\"");
							r.s(" value=\"", htmSessionEndDay(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionEndDay());
			}
			r.l("</div>");
		}
	}

	/////////////////////////
	// sessionNameComplete //
	/////////////////////////

	/**	L'entité « sessionNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String sessionNameComplete;
	@JsonIgnore
	public Wrap<String> sessionNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("sessionNameComplete").o(sessionNameComplete);

	/**	<br/>L'entité « sessionNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionNameComplete">Trouver l'entité sessionNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionNameComplete(Wrap<String> c);

	public String getSessionNameComplete() {
		return sessionNameComplete;
	}

	public void setSessionNameComplete(String sessionNameComplete) {
		this.sessionNameComplete = sessionNameComplete;
		this.sessionNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolBlock sessionNameCompleteInit() {
		if(!sessionNameCompleteWrap.alreadyInitialized) {
			_sessionNameComplete(sessionNameCompleteWrap);
			if(sessionNameComplete == null)
				setSessionNameComplete(sessionNameCompleteWrap.o);
		}
		sessionNameCompleteWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrSessionNameComplete() {
		return sessionNameComplete;
	}

	public String strSessionNameComplete() {
		return sessionNameComplete == null ? "" : sessionNameComplete;
	}

	public String nomAffichageSessionNameComplete() {
		return null;
	}

	public String htmTooltipSessionNameComplete() {
		return null;
	}

	public String htmSessionNameComplete() {
		return sessionNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strSessionNameComplete());
	}

	public void htmSessionNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SessionNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SessionNameComplete() {");
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
				r.l("				data: {\"setSessionNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionNameComplete\"");
							r.s(" value=\"", htmSessionNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionNameComplete());
			}
			r.l("</div>");
		}
	}

	//////////////
	// ageStart //
	//////////////

	/**	L'entité « ageStart »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageStart;
	@JsonIgnore
	public Wrap<Integer> ageStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageStart").o(ageStart);

	/**	<br/>L'entité « ageStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Trouver l'entité ageStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageStart(Wrap<Integer> c);

	public Integer getAgeStart() {
		return ageStart;
	}

	public void setAgeStart(Integer ageStart) {
		this.ageStart = ageStart;
		this.ageStartWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeStart(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ageStart = Integer.parseInt(o);
		this.ageStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageStartInit() {
		if(!ageStartWrap.alreadyInitialized) {
			_ageStart(ageStartWrap);
			if(ageStart == null)
				setAgeStart(ageStartWrap.o);
		}
		ageStartWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrAgeStart() {
		return ageStart;
	}

	public String strAgeStart() {
		return ageStart == null ? "" : ageStart.toString();
	}

	public String nomAffichageAgeStart() {
		return "start of the age group";
	}

	public String htmTooltipAgeStart() {
		return null;
	}

	public String htmAgeStart() {
		return ageStart == null ? "" : StringEscapeUtils.escapeHtml4(strAgeStart());
	}

	public void htmAgeStart(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeStart() {");
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
				r.l("				data: {\"setAgeStart\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeStart()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageStart\"");
							r.s(" value=\"", htmAgeStart(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeStart());
			}
			r.l("</div>");
		}
	}

	////////////
	// ageEnd //
	////////////

	/**	L'entité « ageEnd »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageEnd;
	@JsonIgnore
	public Wrap<Integer> ageEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageEnd").o(ageEnd);

	/**	<br/>L'entité « ageEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Trouver l'entité ageEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageEnd(Wrap<Integer> c);

	public Integer getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
		this.ageEndWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeEnd(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ageEnd = Integer.parseInt(o);
		this.ageEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageEndInit() {
		if(!ageEndWrap.alreadyInitialized) {
			_ageEnd(ageEndWrap);
			if(ageEnd == null)
				setAgeEnd(ageEndWrap.o);
		}
		ageEndWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrAgeEnd() {
		return ageEnd;
	}

	public String strAgeEnd() {
		return ageEnd == null ? "" : ageEnd.toString();
	}

	public String nomAffichageAgeEnd() {
		return "end of the age group";
	}

	public String htmTooltipAgeEnd() {
		return null;
	}

	public String htmAgeEnd() {
		return ageEnd == null ? "" : StringEscapeUtils.escapeHtml4(strAgeEnd());
	}

	public void htmAgeEnd(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeEnd() {");
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
				r.l("				data: {\"setAgeEnd\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeEnd()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageEnd\"");
							r.s(" value=\"", htmAgeEnd(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeEnd());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// ageNameComplete //
	/////////////////////

	/**	L'entité « ageNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String ageNameComplete;
	@JsonIgnore
	public Wrap<String> ageNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("ageNameComplete").o(ageNameComplete);

	/**	<br/>L'entité « ageNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageNameComplete">Trouver l'entité ageNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageNameComplete(Wrap<String> c);

	public String getAgeNameComplete() {
		return ageNameComplete;
	}

	public void setAgeNameComplete(String ageNameComplete) {
		this.ageNameComplete = ageNameComplete;
		this.ageNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolBlock ageNameCompleteInit() {
		if(!ageNameCompleteWrap.alreadyInitialized) {
			_ageNameComplete(ageNameCompleteWrap);
			if(ageNameComplete == null)
				setAgeNameComplete(ageNameCompleteWrap.o);
		}
		ageNameCompleteWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrAgeNameComplete() {
		return ageNameComplete;
	}

	public String strAgeNameComplete() {
		return ageNameComplete == null ? "" : ageNameComplete;
	}

	public String nomAffichageAgeNameComplete() {
		return null;
	}

	public String htmTooltipAgeNameComplete() {
		return null;
	}

	public String htmAgeNameComplete() {
		return ageNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strAgeNameComplete());
	}

	public void htmAgeNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeNameComplete() {");
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
				r.l("				data: {\"setAgeNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageNameComplete\"");
							r.s(" value=\"", htmAgeNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeNameComplete());
			}
			r.l("</div>");
		}
	}

	///////////
	// ageId //
	///////////

	/**	L'entité « ageId »
	 *	 is defined as null before being initialized. 
	 */
	protected String ageId;
	@JsonIgnore
	public Wrap<String> ageIdWrap = new Wrap<String>().p(this).c(String.class).var("ageId").o(ageId);

	/**	<br/>L'entité « ageId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageId">Trouver l'entité ageId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageId(Wrap<String> c);

	public String getAgeId() {
		return ageId;
	}

	public void setAgeId(String ageId) {
		this.ageId = ageId;
		this.ageIdWrap.alreadyInitialized = true;
	}
	protected SchoolBlock ageIdInit() {
		if(!ageIdWrap.alreadyInitialized) {
			_ageId(ageIdWrap);
			if(ageId == null)
				setAgeId(ageIdWrap.o);
		}
		ageIdWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrAgeId() {
		return ageId;
	}

	public String strAgeId() {
		return ageId == null ? "" : ageId;
	}

	public String nomAffichageAgeId() {
		return "ID";
	}

	public String htmTooltipAgeId() {
		return null;
	}

	public String htmAgeId() {
		return ageId == null ? "" : StringEscapeUtils.escapeHtml4(strAgeId());
	}

	public void htmAgeId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeId() {");
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
				r.l("				data: {\"setAgeId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeId()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageId\"");
							r.s(" value=\"", htmAgeId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeId());
			}
			r.l("</div>");
		}
	}

	/////////////
	// pageUrl //
	/////////////

	/**	L'entité « pageUrl »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUrl;
	@JsonIgnore
	public Wrap<String> pageUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageUrl").o(pageUrl);

	/**	<br/>L'entité « pageUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrl(Wrap<String> c);

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
		this.pageUrlWrap.alreadyInitialized = true;
	}
	protected SchoolBlock pageUrlInit() {
		if(!pageUrlWrap.alreadyInitialized) {
			_pageUrl(pageUrlWrap);
			if(pageUrl == null)
				setPageUrl(pageUrlWrap.o);
		}
		pageUrlWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrPageUrl() {
		return pageUrl;
	}

	public String strPageUrl() {
		return pageUrl == null ? "" : pageUrl;
	}

	public String nomAffichagePageUrl() {
		return null;
	}

	public String htmTooltipPageUrl() {
		return null;
	}

	public String htmPageUrl() {
		return pageUrl == null ? "" : StringEscapeUtils.escapeHtml4(strPageUrl());
	}

	public void htmPageUrl(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "PageUrl\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "PageUrl() {");
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
				r.l("				data: {\"setPageUrl\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePageUrl()), "</span>");
				r.s("			<input");
							r.s(" name=\"pageUrl\"");
							r.s(" value=\"", htmPageUrl(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPageUrl());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// objectSuggest //
	///////////////////

	/**	L'entité « objectSuggest »
	 *	 is defined as null before being initialized. 
	 */
	protected String objectSuggest;
	@JsonIgnore
	public Wrap<String> objectSuggestWrap = new Wrap<String>().p(this).c(String.class).var("objectSuggest").o(objectSuggest);

	/**	<br/>L'entité « objectSuggest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggest">Trouver l'entité objectSuggest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objectSuggest(Wrap<String> c);

	public String getObjectSuggest() {
		return objectSuggest;
	}

	public void setObjectSuggest(String objectSuggest) {
		this.objectSuggest = objectSuggest;
		this.objectSuggestWrap.alreadyInitialized = true;
	}
	protected SchoolBlock objectSuggestInit() {
		if(!objectSuggestWrap.alreadyInitialized) {
			_objectSuggest(objectSuggestWrap);
			if(objectSuggest == null)
				setObjectSuggest(objectSuggestWrap.o);
		}
		objectSuggestWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrObjectSuggest() {
		return objectSuggest;
	}

	public String strObjectSuggest() {
		return objectSuggest == null ? "" : objectSuggest;
	}

	public String nomAffichageObjectSuggest() {
		return null;
	}

	public String htmTooltipObjectSuggest() {
		return null;
	}

	public String htmObjectSuggest() {
		return objectSuggest == null ? "" : StringEscapeUtils.escapeHtml4(strObjectSuggest());
	}

	public void htmObjectSuggest(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "ObjectSuggest\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "ObjectSuggest() {");
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
				r.l("				data: {\"setObjectSuggest\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageObjectSuggest()), "</span>");
				r.s("			<input");
							r.s(" name=\"objectSuggest\"");
							r.s(" value=\"", htmObjectSuggest(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmObjectSuggest());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolBlock = false;

	public SchoolBlock initDeepSchoolBlock(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolBlock) {
			alreadyInitializedSchoolBlock = true;
			initDeepSchoolBlock();
		}
		return (SchoolBlock)this;
	}

	public void initDeepSchoolBlock() {
		super.initDeepCluster(siteRequest_);
		initSchoolBlock();
	}

	public void initSchoolBlock() {
		schoolKeyInit();
		anneeCleInit();
		seasonKeyInit();
		sessionKeyInit();
		ageKeyInit();
		ageKeyInit();
		enrollmentKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		sessionSortInit();
		sessionSearchInit();
		sessionInit();
		schoolNameCompleteInit();
		yearStartInit();
		yearEndInit();
		seasonStartInit();
		seasonSummerInit();
		seasonWinterInit();
		seasonEnrollmentFeeInit();
		seasonNameCompleteInit();
		seasonEndInit();
		sessionStartDayInit();
		sessionEndDayInit();
		sessionNameCompleteInit();
		ageStartInit();
		ageEndInit();
		ageNameCompleteInit();
		ageIdInit();
		pageUrlInit();
		objectSuggestInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolBlock(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolBlock(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(sessionSearch != null)
			sessionSearch.setSiteRequest_(siteRequest_);
		if(session != null)
			session.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolBlock(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolBlock(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolBlock(String var) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
		switch(var) {
			case "schoolKey":
				return oSchoolBlock.schoolKey;
			case "anneeCle":
				return oSchoolBlock.anneeCle;
			case "seasonKey":
				return oSchoolBlock.seasonKey;
			case "sessionKey":
				return oSchoolBlock.sessionKey;
			case "ageKey":
				return oSchoolBlock.ageKey;
			case "ageKey":
				return oSchoolBlock.ageKey;
			case "enrollmentKeys":
				return oSchoolBlock.enrollmentKeys;
			case "educationSort":
				return oSchoolBlock.educationSort;
			case "schoolSort":
				return oSchoolBlock.schoolSort;
			case "yearSort":
				return oSchoolBlock.yearSort;
			case "seasonSort":
				return oSchoolBlock.seasonSort;
			case "sessionSort":
				return oSchoolBlock.sessionSort;
			case "sessionSearch":
				return oSchoolBlock.sessionSearch;
			case "session":
				return oSchoolBlock.session;
			case "schoolNameComplete":
				return oSchoolBlock.schoolNameComplete;
			case "yearStart":
				return oSchoolBlock.yearStart;
			case "yearEnd":
				return oSchoolBlock.yearEnd;
			case "seasonStart":
				return oSchoolBlock.seasonStart;
			case "seasonSummer":
				return oSchoolBlock.seasonSummer;
			case "seasonWinter":
				return oSchoolBlock.seasonWinter;
			case "seasonEnrollmentFee":
				return oSchoolBlock.seasonEnrollmentFee;
			case "seasonNameComplete":
				return oSchoolBlock.seasonNameComplete;
			case "seasonEnd":
				return oSchoolBlock.seasonEnd;
			case "sessionStartDay":
				return oSchoolBlock.sessionStartDay;
			case "sessionEndDay":
				return oSchoolBlock.sessionEndDay;
			case "sessionNameComplete":
				return oSchoolBlock.sessionNameComplete;
			case "ageStart":
				return oSchoolBlock.ageStart;
			case "ageEnd":
				return oSchoolBlock.ageEnd;
			case "ageNameComplete":
				return oSchoolBlock.ageNameComplete;
			case "ageId":
				return oSchoolBlock.ageId;
			case "pageUrl":
				return oSchoolBlock.pageUrl;
			case "objectSuggest":
				return oSchoolBlock.objectSuggest;
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
				o = attributeSchoolBlock(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolBlock(String var, Object val) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
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
					o = defineSchoolBlock(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolBlock(String var, String val) {
		switch(var) {
			case "ageStart":
				setAgeStart(val);
				savesSchoolBlock.add(var);
				return val;
			case "ageEnd":
				setAgeEnd(val);
				savesSchoolBlock.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolBlock = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolBlock(solrDocument);
	}
	public void populateSchoolBlock(SolrDocument solrDocument) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
		savesSchoolBlock = (List<String>)solrDocument.get("savesSchoolBlock_stored_strings");
		if(savesSchoolBlock != null) {

			if(savesSchoolBlock.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolBlock.setSchoolKey(schoolKey);
			}

			if(savesSchoolBlock.contains("seasonKey")) {
				Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
				if(seasonKey != null)
					oSchoolBlock.setSeasonKey(seasonKey);
			}

			if(savesSchoolBlock.contains("sessionKey")) {
				Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
				if(sessionKey != null)
					oSchoolBlock.setSessionKey(sessionKey);
			}

			if(savesSchoolBlock.contains("ageKey")) {
				Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
				if(ageKey != null)
					oSchoolBlock.setAgeKey(ageKey);
			}

			if(savesSchoolBlock.contains("ageKey")) {
				Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
				if(ageKey != null)
					oSchoolBlock.setAgeKey(ageKey);
			}

			if(savesSchoolBlock.contains("enrollmentKeys")) {
				List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
				if(enrollmentKeys != null)
					oSchoolBlock.enrollmentKeys.addAll(enrollmentKeys);
			}

			if(savesSchoolBlock.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolBlock.setEducationSort(educationSort);
			}

			if(savesSchoolBlock.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolBlock.setSchoolSort(schoolSort);
			}

			if(savesSchoolBlock.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolBlock.setYearSort(yearSort);
			}

			if(savesSchoolBlock.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolBlock.setSeasonSort(seasonSort);
			}

			if(savesSchoolBlock.contains("sessionSort")) {
				Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
				if(sessionSort != null)
					oSchoolBlock.setSessionSort(sessionSort);
			}

			if(savesSchoolBlock.contains("schoolNameComplete")) {
				String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
				if(schoolNameComplete != null)
					oSchoolBlock.setSchoolNameComplete(schoolNameComplete);
			}

			if(savesSchoolBlock.contains("yearStart")) {
				Date yearStart = (Date)solrDocument.get("yearStart_stored_date");
				if(yearStart != null)
					oSchoolBlock.setYearStart(yearStart);
			}

			if(savesSchoolBlock.contains("yearEnd")) {
				Date yearEnd = (Date)solrDocument.get("yearEnd_stored_date");
				if(yearEnd != null)
					oSchoolBlock.setYearEnd(yearEnd);
			}

			if(savesSchoolBlock.contains("seasonStart")) {
				Date seasonStart = (Date)solrDocument.get("seasonStart_stored_date");
				if(seasonStart != null)
					oSchoolBlock.setSeasonStart(seasonStart);
			}

			if(savesSchoolBlock.contains("seasonSummer")) {
				Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
				if(seasonSummer != null)
					oSchoolBlock.setSeasonSummer(seasonSummer);
			}

			if(savesSchoolBlock.contains("seasonWinter")) {
				Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
				if(seasonWinter != null)
					oSchoolBlock.setSeasonWinter(seasonWinter);
			}

			if(savesSchoolBlock.contains("seasonEnrollmentFee")) {
				Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
				if(seasonEnrollmentFee != null)
					oSchoolBlock.setSeasonEnrollmentFee(seasonEnrollmentFee);
			}

			if(savesSchoolBlock.contains("seasonNameComplete")) {
				String seasonNameComplete = (String)solrDocument.get("seasonNameComplete_stored_string");
				if(seasonNameComplete != null)
					oSchoolBlock.setSeasonNameComplete(seasonNameComplete);
			}

			if(savesSchoolBlock.contains("seasonEnd")) {
				Date seasonEnd = (Date)solrDocument.get("seasonEnd_stored_date");
				if(seasonEnd != null)
					oSchoolBlock.setSeasonEnd(seasonEnd);
			}

			if(savesSchoolBlock.contains("sessionStartDay")) {
				Date sessionStartDay = (Date)solrDocument.get("sessionStartDay_stored_date");
				if(sessionStartDay != null)
					oSchoolBlock.setSessionStartDay(sessionStartDay);
			}

			if(savesSchoolBlock.contains("sessionEndDay")) {
				Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
				if(sessionEndDay != null)
					oSchoolBlock.setSessionEndDay(sessionEndDay);
			}

			if(savesSchoolBlock.contains("sessionNameComplete")) {
				String sessionNameComplete = (String)solrDocument.get("sessionNameComplete_stored_string");
				if(sessionNameComplete != null)
					oSchoolBlock.setSessionNameComplete(sessionNameComplete);
			}

			if(savesSchoolBlock.contains("ageStart")) {
				Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
				if(ageStart != null)
					oSchoolBlock.setAgeStart(ageStart);
			}

			if(savesSchoolBlock.contains("ageEnd")) {
				Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
				if(ageEnd != null)
					oSchoolBlock.setAgeEnd(ageEnd);
			}

			if(savesSchoolBlock.contains("ageNameComplete")) {
				String ageNameComplete = (String)solrDocument.get("ageNameComplete_stored_string");
				if(ageNameComplete != null)
					oSchoolBlock.setAgeNameComplete(ageNameComplete);
			}

			if(savesSchoolBlock.contains("ageId")) {
				String ageId = (String)solrDocument.get("ageId_stored_string");
				if(ageId != null)
					oSchoolBlock.setAgeId(ageId);
			}

			if(savesSchoolBlock.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oSchoolBlock.setPageUrl(pageUrl);
			}

			if(savesSchoolBlock.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_stored_string");
				if(objectSuggest != null)
					oSchoolBlock.setObjectSuggest(objectSuggest);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.bloc.SchoolBlock"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolBlock o = new SchoolBlock();
			o.siteRequestSchoolBlock(siteRequest);
			o.initDeepSchoolBlock(siteRequest);
			o.indexSchoolBlock();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolBlock();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolBlock(document);
	}

	public void indexSchoolBlock(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolBlock(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolBlock() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolBlock(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolBlock(SolrInputDocument document) {
		if(savesSchoolBlock != null)
			document.addField("savesSchoolBlock_stored_strings", savesSchoolBlock);

		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(seasonKey != null) {
			document.addField("seasonKey_indexed_long", seasonKey);
			document.addField("seasonKey_stored_long", seasonKey);
		}
		if(sessionKey != null) {
			document.addField("sessionKey_indexed_long", sessionKey);
			document.addField("sessionKey_stored_long", sessionKey);
		}
		if(ageKey != null) {
			document.addField("ageKey_indexed_long", ageKey);
			document.addField("ageKey_stored_long", ageKey);
		}
		if(ageKey != null) {
			document.addField("ageKey_indexed_long", ageKey);
			document.addField("ageKey_stored_long", ageKey);
		}
		if(enrollmentKeys != null) {
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_stored_longs", o);
			}
		}
		if(educationSort != null) {
			document.addField("educationSort_indexed_int", educationSort);
			document.addField("educationSort_stored_int", educationSort);
		}
		if(schoolSort != null) {
			document.addField("schoolSort_indexed_int", schoolSort);
			document.addField("schoolSort_stored_int", schoolSort);
		}
		if(yearSort != null) {
			document.addField("yearSort_indexed_int", yearSort);
			document.addField("yearSort_stored_int", yearSort);
		}
		if(seasonSort != null) {
			document.addField("seasonSort_indexed_int", seasonSort);
			document.addField("seasonSort_stored_int", seasonSort);
		}
		if(sessionSort != null) {
			document.addField("sessionSort_indexed_int", sessionSort);
			document.addField("sessionSort_stored_int", sessionSort);
		}
		if(schoolNameComplete != null) {
			document.addField("schoolNameComplete_indexed_string", schoolNameComplete);
			document.addField("schoolNameComplete_stored_string", schoolNameComplete);
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.of("Z"))));
			document.addField("yearStart_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.of("Z"))));
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.of("Z"))));
			document.addField("yearEnd_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.of("Z"))));
		}
		if(seasonStart != null) {
			document.addField("seasonStart_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonStart.atStartOfDay(ZoneId.of("Z"))));
			document.addField("seasonStart_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonStart.atStartOfDay(ZoneId.of("Z"))));
		}
		if(seasonSummer != null) {
			document.addField("seasonSummer_indexed_boolean", seasonSummer);
			document.addField("seasonSummer_stored_boolean", seasonSummer);
		}
		if(seasonWinter != null) {
			document.addField("seasonWinter_indexed_boolean", seasonWinter);
			document.addField("seasonWinter_stored_boolean", seasonWinter);
		}
		if(seasonEnrollmentFee != null) {
			document.addField("seasonEnrollmentFee_indexed_double", seasonEnrollmentFee);
			document.addField("seasonEnrollmentFee_stored_double", seasonEnrollmentFee);
		}
		if(seasonNameComplete != null) {
			document.addField("seasonNameComplete_indexed_string", seasonNameComplete);
			document.addField("seasonNameComplete_stored_string", seasonNameComplete);
		}
		if(seasonEnd != null) {
			document.addField("seasonEnd_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonEnd.atStartOfDay(ZoneId.of("Z"))));
			document.addField("seasonEnd_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonEnd.atStartOfDay(ZoneId.of("Z"))));
		}
		if(sessionStartDay != null) {
			document.addField("sessionStartDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionStartDay.atStartOfDay(ZoneId.of("Z"))));
			document.addField("sessionStartDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionStartDay.atStartOfDay(ZoneId.of("Z"))));
		}
		if(sessionEndDay != null) {
			document.addField("sessionEndDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionEndDay.atStartOfDay(ZoneId.of("Z"))));
			document.addField("sessionEndDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionEndDay.atStartOfDay(ZoneId.of("Z"))));
		}
		if(sessionNameComplete != null) {
			document.addField("sessionNameComplete_indexed_string", sessionNameComplete);
			document.addField("sessionNameComplete_stored_string", sessionNameComplete);
		}
		if(ageStart != null) {
			document.addField("ageStart_indexed_int", ageStart);
			document.addField("ageStart_stored_int", ageStart);
		}
		if(ageEnd != null) {
			document.addField("ageEnd_indexed_int", ageEnd);
			document.addField("ageEnd_stored_int", ageEnd);
		}
		if(ageNameComplete != null) {
			document.addField("ageNameComplete_indexed_string", ageNameComplete);
			document.addField("ageNameComplete_stored_string", ageNameComplete);
		}
		if(ageId != null) {
			document.addField("ageId_indexed_string", ageId);
			document.addField("ageId_stored_string", ageId);
		}
		if(pageUrl != null) {
			document.addField("pageUrl_indexed_string", pageUrl);
			document.addField("pageUrl_stored_string", pageUrl);
		}
		if(objectSuggest != null) {
			document.addField("objectSuggest_suggested", objectSuggest);
			document.addField("objectSuggest_indexed_string", objectSuggest);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolBlock() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolBlock(siteRequest);
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
		storeSchoolBlock(solrDocument);
	}
	public void storeSchoolBlock(SolrDocument solrDocument) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolBlock.setSchoolKey(schoolKey);

		Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
		if(seasonKey != null)
			oSchoolBlock.setSeasonKey(seasonKey);

		Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
		if(sessionKey != null)
			oSchoolBlock.setSessionKey(sessionKey);

		Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
		if(ageKey != null)
			oSchoolBlock.setAgeKey(ageKey);

		Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
		if(ageKey != null)
			oSchoolBlock.setAgeKey(ageKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolBlock.enrollmentKeys.addAll(enrollmentKeys);

		Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
		if(educationSort != null)
			oSchoolBlock.setEducationSort(educationSort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolBlock.setSchoolSort(schoolSort);

		Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
		if(yearSort != null)
			oSchoolBlock.setYearSort(yearSort);

		Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
		if(seasonSort != null)
			oSchoolBlock.setSeasonSort(seasonSort);

		Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
		if(sessionSort != null)
			oSchoolBlock.setSessionSort(sessionSort);

		String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
		if(schoolNameComplete != null)
			oSchoolBlock.setSchoolNameComplete(schoolNameComplete);

		Date yearStart = (Date)solrDocument.get("yearStart_stored_date");
		if(yearStart != null)
			oSchoolBlock.setYearStart(yearStart);

		Date yearEnd = (Date)solrDocument.get("yearEnd_stored_date");
		if(yearEnd != null)
			oSchoolBlock.setYearEnd(yearEnd);

		Date seasonStart = (Date)solrDocument.get("seasonStart_stored_date");
		if(seasonStart != null)
			oSchoolBlock.setSeasonStart(seasonStart);

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolBlock.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolBlock.setSeasonWinter(seasonWinter);

		Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
		if(seasonEnrollmentFee != null)
			oSchoolBlock.setSeasonEnrollmentFee(seasonEnrollmentFee);

		String seasonNameComplete = (String)solrDocument.get("seasonNameComplete_stored_string");
		if(seasonNameComplete != null)
			oSchoolBlock.setSeasonNameComplete(seasonNameComplete);

		Date seasonEnd = (Date)solrDocument.get("seasonEnd_stored_date");
		if(seasonEnd != null)
			oSchoolBlock.setSeasonEnd(seasonEnd);

		Date sessionStartDay = (Date)solrDocument.get("sessionStartDay_stored_date");
		if(sessionStartDay != null)
			oSchoolBlock.setSessionStartDay(sessionStartDay);

		Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
		if(sessionEndDay != null)
			oSchoolBlock.setSessionEndDay(sessionEndDay);

		String sessionNameComplete = (String)solrDocument.get("sessionNameComplete_stored_string");
		if(sessionNameComplete != null)
			oSchoolBlock.setSessionNameComplete(sessionNameComplete);

		Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
		if(ageStart != null)
			oSchoolBlock.setAgeStart(ageStart);

		Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
		if(ageEnd != null)
			oSchoolBlock.setAgeEnd(ageEnd);

		String ageNameComplete = (String)solrDocument.get("ageNameComplete_stored_string");
		if(ageNameComplete != null)
			oSchoolBlock.setAgeNameComplete(ageNameComplete);

		String ageId = (String)solrDocument.get("ageId_stored_string");
		if(ageId != null)
			oSchoolBlock.setAgeId(ageId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oSchoolBlock.setPageUrl(pageUrl);

		String objectSuggest = (String)solrDocument.get("objectSuggest_stored_string");
		if(objectSuggest != null)
			oSchoolBlock.setObjectSuggest(objectSuggest);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), ageStart, ageEnd);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolBlock))
			return false;
		SchoolBlock that = (SchoolBlock)o;
		return super.equals(o)
				&& Objects.equals( ageStart, that.ageStart )
				&& Objects.equals( ageEnd, that.ageEnd );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolBlock { ");
		sb.append( "ageStart: " ).append(ageStart);
		sb.append( ", ageEnd: " ).append(ageEnd);
		sb.append(" }");
		return sb.toString();
	}
}
