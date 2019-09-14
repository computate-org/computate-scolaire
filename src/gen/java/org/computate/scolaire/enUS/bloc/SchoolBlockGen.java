package org.computate.scolaire.enUS.bloc;

import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
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
import org.computate.scolaire.enUS.age.SchoolAge;
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
	public static final String SchoolBlock_Un = "a ";
	public static final String SchoolBlock_LeNom = "the block";
	public static final String SchoolBlock_NomSingulier = "block";
	public static final String SchoolBlock_NomPluriel = "blocks";
	public static final String SchoolBlock_NomActuel = "current block";
	public static final String SchoolBlock_TousNom = "all the blocks";
	public static final String SchoolBlock_RechercherTousNomPar = "search blocks by ";
	public static final String SchoolBlock_LesNoms = "the blocks";
	public static final String SchoolBlock_AucunNomTrouve = "no block found";
	public static final String SchoolBlock_NomVar = "block";
	public static final String SchoolBlock_DeNom = "of block";
	public static final String SchoolBlock_UnNomAdjectif = "a block";
	public static final String SchoolBlock_NomAdjectifSingulier = "block";
	public static final String SchoolBlock_NomAdjectifPluriel = "blocks";
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
		if(NumberUtils.isParsable(o))
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

	public String jsonSchoolKey() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonAnneeCle() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonSeasonKey() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonSessionKey() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonAgeKey() {
		return ageKey == null ? "" : ageKey.toString();
	}

	public String nomAffichageAgeKey() {
		return "age";
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

	//////////////
	// blockKey //
	//////////////

	/**	L'entité « blockKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long blockKey;
	@JsonIgnore
	public Wrap<Long> blockKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("blockKey").o(blockKey);

	/**	<br/>L'entité « blockKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Trouver l'entité blockKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockKey(Wrap<Long> c);

	public Long getBlockKey() {
		return blockKey;
	}

	public void setBlockKey(Long blockKey) {
		this.blockKey = blockKey;
		this.blockKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockKey(String o) {
		if(NumberUtils.isParsable(o))
			this.blockKey = Long.parseLong(o);
		this.blockKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockKeyInit() {
		if(!blockKeyWrap.alreadyInitialized) {
			_blockKey(blockKeyWrap);
			if(blockKey == null)
				setBlockKey(blockKeyWrap.o);
		}
		blockKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrBlockKey() {
		return blockKey;
	}

	public String strBlockKey() {
		return blockKey == null ? "" : blockKey.toString();
	}

	public String jsonBlockKey() {
		return blockKey == null ? "" : blockKey.toString();
	}

	public String nomAffichageBlockKey() {
		return "key";
	}

	public String htmTooltipBlockKey() {
		return null;
	}

	public String htmBlockKey() {
		return blockKey == null ? "" : StringEscapeUtils.escapeHtml4(strBlockKey());
	}

	public void htmBlockKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockKey() {");
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
				r.l("				data: {\"setBlockKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockKey\"");
							r.s(" value=\"", htmBlockKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockKey());
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
		if(NumberUtils.isParsable(o)) {
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

	public String jsonEnrollmentKeys() {
		return enrollmentKeys == null ? "" : enrollmentKeys.toString();
	}

	public String nomAffichageEnrollmentKeys() {
		return "enrollments";
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
		if(NumberUtils.isParsable(o))
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

	public String jsonEducationSort() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonSchoolSort() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonYearSort() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonSeasonSort() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonSessionSort() {
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

	/////////////
	// ageSort //
	/////////////

	/**	L'entité « ageSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageSort;
	@JsonIgnore
	public Wrap<Integer> ageSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageSort").o(ageSort);

	/**	<br/>L'entité « ageSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSort">Trouver l'entité ageSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageSort(Wrap<Integer> c);

	public Integer getAgeSort() {
		return ageSort;
	}

	public void setAgeSort(Integer ageSort) {
		this.ageSort = ageSort;
		this.ageSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeSort(String o) {
		if(NumberUtils.isParsable(o))
			this.ageSort = Integer.parseInt(o);
		this.ageSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageSortInit() {
		if(!ageSortWrap.alreadyInitialized) {
			_ageSort(ageSortWrap);
			if(ageSort == null)
				setAgeSort(ageSortWrap.o);
		}
		ageSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrAgeSort() {
		return ageSort;
	}

	public String strAgeSort() {
		return ageSort == null ? "" : ageSort.toString();
	}

	public String jsonAgeSort() {
		return ageSort == null ? "" : ageSort.toString();
	}

	public String nomAffichageAgeSort() {
		return null;
	}

	public String htmTooltipAgeSort() {
		return null;
	}

	public String htmAgeSort() {
		return ageSort == null ? "" : StringEscapeUtils.escapeHtml4(strAgeSort());
	}

	public void htmAgeSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeSort() {");
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
				r.l("				data: {\"setAgeSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageSort\"");
							r.s(" value=\"", htmAgeSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeSort());
			}
			r.l("</div>");
		}
	}

	///////////////
	// ageSearch //
	///////////////

	/**	L'entité « ageSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolAge>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolAge> ageSearch = new SearchList<SchoolAge>();
	@JsonIgnore
	public Wrap<SearchList<SchoolAge>> ageSearchWrap = new Wrap<SearchList<SchoolAge>>().p(this).c(SearchList.class).var("ageSearch").o(ageSearch);

	/**	<br/>L'entité « ageSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolAge>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSearch">Trouver l'entité ageSearch dans Solr</a>
	 * <br/>
	 * @param ageSearch est l'entité déjà construit. 
	 **/
	protected abstract void _ageSearch(SearchList<SchoolAge> l);

	public SearchList<SchoolAge> getAgeSearch() {
		return ageSearch;
	}

	public void setAgeSearch(SearchList<SchoolAge> ageSearch) {
		this.ageSearch = ageSearch;
		this.ageSearchWrap.alreadyInitialized = true;
	}
	protected SchoolBlock ageSearchInit() {
		if(!ageSearchWrap.alreadyInitialized) {
			_ageSearch(ageSearch);
		}
		ageSearch.initDeepForClass(siteRequest_);
		ageSearchWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	/////////
	// age //
	/////////

	/**	L'entité « age »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolAge age;
	@JsonIgnore
	public Wrap<SchoolAge> ageWrap = new Wrap<SchoolAge>().p(this).c(SchoolAge.class).var("age").o(age);

	/**	<br/>L'entité « age »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:age">Trouver l'entité age dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _age(Wrap<SchoolAge> c);

	public SchoolAge getAge() {
		return age;
	}

	public void setAge(SchoolAge age) {
		this.age = age;
		this.ageWrap.alreadyInitialized = true;
	}
	protected SchoolBlock ageInit() {
		if(!ageWrap.alreadyInitialized) {
			_age(ageWrap);
			if(age == null)
				setAge(ageWrap.o);
		}
		if(age != null)
			age.initDeepForClass(siteRequest_);
		ageWrap.alreadyInitialized(true);
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

	public String jsonSchoolNameComplete() {
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
		this.yearStart = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
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
		return yearStart == null ? "" : yearStart.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonYearStart() {
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
		this.yearEnd = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
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
		return yearEnd == null ? "" : yearEnd.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonYearEnd() {
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

	////////////////////
	// seasonStartDay //
	////////////////////

	/**	L'entité « seasonStartDay »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate seasonStartDay;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDayWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDay").o(seasonStartDay);

	/**	<br/>L'entité « seasonStartDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDay">Trouver l'entité seasonStartDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonStartDay(Wrap<LocalDate> c);

	public LocalDate getSeasonStartDay() {
		return seasonStartDay;
	}

	public void setSeasonStartDay(LocalDate seasonStartDay) {
		this.seasonStartDay = seasonStartDay;
		this.seasonStartDayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSeasonStartDay(Instant o) {
		this.seasonStartDay = LocalDate.from(o);
		this.seasonStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSeasonStartDay(String o) {
		this.seasonStartDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonStartDay(Date o) {
		this.seasonStartDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonStartDayInit() {
		if(!seasonStartDayWrap.alreadyInitialized) {
			_seasonStartDay(seasonStartDayWrap);
			if(seasonStartDay == null)
				setSeasonStartDay(seasonStartDayWrap.o);
		}
		seasonStartDayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrSeasonStartDay() {
		return seasonStartDay == null ? null : Date.from(seasonStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSeasonStartDay() {
		return seasonStartDay == null ? "" : seasonStartDay.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSeasonStartDay() {
		return seasonStartDay == null ? "" : seasonStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSeasonStartDay() {
		return "start of season";
	}

	public String htmTooltipSeasonStartDay() {
		return null;
	}

	public String htmSeasonStartDay() {
		return seasonStartDay == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStartDay());
	}

	public void htmSeasonStartDay(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "SeasonStartDay\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "SeasonStartDay() {");
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
				r.l("				data: {\"setSeasonStartDay\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonStartDay()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonStartDay\"");
							r.s(" value=\"", htmSeasonStartDay(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonStartDay());
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

	public String jsonSeasonSummer() {
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

	public String jsonSeasonWinter() {
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
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonEnrollmentFee(Double o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonEnrollmentFee(Integer o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
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

	public String jsonSeasonEnrollmentFee() {
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

	public String jsonSeasonNameComplete() {
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

	/////////////////
	// ageStartDay //
	/////////////////

	/**	L'entité « ageStartDay »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate ageStartDay;
	@JsonIgnore
	public Wrap<LocalDate> ageStartDayWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("ageStartDay").o(ageStartDay);

	/**	<br/>L'entité « ageStartDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStartDay">Trouver l'entité ageStartDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageStartDay(Wrap<LocalDate> c);

	public LocalDate getAgeStartDay() {
		return ageStartDay;
	}

	public void setAgeStartDay(LocalDate ageStartDay) {
		this.ageStartDay = ageStartDay;
		this.ageStartDayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeStartDay(Instant o) {
		this.ageStartDay = LocalDate.from(o);
		this.ageStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setAgeStartDay(String o) {
		this.ageStartDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.ageStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setAgeStartDay(Date o) {
		this.ageStartDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.ageStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageStartDayInit() {
		if(!ageStartDayWrap.alreadyInitialized) {
			_ageStartDay(ageStartDayWrap);
			if(ageStartDay == null)
				setAgeStartDay(ageStartDayWrap.o);
		}
		ageStartDayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrAgeStartDay() {
		return ageStartDay == null ? null : Date.from(ageStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strAgeStartDay() {
		return ageStartDay == null ? "" : ageStartDay.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonAgeStartDay() {
		return ageStartDay == null ? "" : ageStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageAgeStartDay() {
		return "start of the session";
	}

	public String htmTooltipAgeStartDay() {
		return null;
	}

	public String htmAgeStartDay() {
		return ageStartDay == null ? "" : StringEscapeUtils.escapeHtml4(strAgeStartDay());
	}

	public void htmAgeStartDay(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "AgeStartDay\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "AgeStartDay() {");
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
				r.l("				data: {\"setAgeStartDay\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeStartDay()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageStartDay\"");
							r.s(" value=\"", htmAgeStartDay(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeStartDay());
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
		this.sessionEndDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
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
		return sessionEndDay == null ? "" : sessionEndDay.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSessionEndDay() {
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

	public String jsonAgeNameComplete() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonAgeStart() {
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
		if(NumberUtils.isParsable(o))
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

	public String jsonAgeEnd() {
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

	////////////////////
	// blockTimeStart //
	////////////////////

	/**	L'entité « blockTimeStart »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blockTimeStart;
	@JsonIgnore
	public Wrap<LocalTime> blockTimeStartWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockTimeStart").o(blockTimeStart);

	/**	<br/>L'entité « blockTimeStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTimeStart">Trouver l'entité blockTimeStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockTimeStart(Wrap<LocalTime> c);

	public LocalTime getBlockTimeStart() {
		return blockTimeStart;
	}

	public void setBlockTimeStart(LocalTime blockTimeStart) {
		this.blockTimeStart = blockTimeStart;
		this.blockTimeStartWrap.alreadyInitialized = true;
	}
	/** Example: 01:00 **/
	public SchoolBlock setBlockTimeStart(String o) {
		try {
			this.blockTimeStart = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockTimeStartWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockTimeStartInit() {
		if(!blockTimeStartWrap.alreadyInitialized) {
			_blockTimeStart(blockTimeStartWrap);
			if(blockTimeStart == null)
				setBlockTimeStart(blockTimeStartWrap.o);
		}
		blockTimeStartWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrBlockTimeStart() {
		return blockTimeStart == null ? null : blockTimeStart.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockTimeStart() {
		return blockTimeStart == null ? "" : blockTimeStart.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
	}

	public String jsonBlockTimeStart() {
		return blockTimeStart == null ? "" : blockTimeStart.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlockTimeStart() {
		return "start time";
	}

	public String htmTooltipBlockTimeStart() {
		return null;
	}

	public String htmBlockTimeStart() {
		return blockTimeStart == null ? "" : StringEscapeUtils.escapeHtml4(strBlockTimeStart());
	}

	public void htmBlockTimeStart(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockTimeStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockTimeStart() {");
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
				r.l("				data: {\"setBlockTimeStart\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockTimeStart()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockTimeStart\"");
							r.s(" value=\"", htmBlockTimeStart(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockTimeStart());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// blockTimeEnd //
	//////////////////

	/**	L'entité « blockTimeEnd »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blockTimeEnd;
	@JsonIgnore
	public Wrap<LocalTime> blockTimeEndWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockTimeEnd").o(blockTimeEnd);

	/**	<br/>L'entité « blockTimeEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTimeEnd">Trouver l'entité blockTimeEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockTimeEnd(Wrap<LocalTime> c);

	public LocalTime getBlockTimeEnd() {
		return blockTimeEnd;
	}

	public void setBlockTimeEnd(LocalTime blockTimeEnd) {
		this.blockTimeEnd = blockTimeEnd;
		this.blockTimeEndWrap.alreadyInitialized = true;
	}
	/** Example: 01:00 **/
	public SchoolBlock setBlockTimeEnd(String o) {
		try {
			this.blockTimeEnd = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockTimeEndWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockTimeEndInit() {
		if(!blockTimeEndWrap.alreadyInitialized) {
			_blockTimeEnd(blockTimeEndWrap);
			if(blockTimeEnd == null)
				setBlockTimeEnd(blockTimeEndWrap.o);
		}
		blockTimeEndWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrBlockTimeEnd() {
		return blockTimeEnd == null ? null : blockTimeEnd.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockTimeEnd() {
		return blockTimeEnd == null ? "" : blockTimeEnd.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
	}

	public String jsonBlockTimeEnd() {
		return blockTimeEnd == null ? "" : blockTimeEnd.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlockTimeEnd() {
		return "end time";
	}

	public String htmTooltipBlockTimeEnd() {
		return null;
	}

	public String htmBlockTimeEnd() {
		return blockTimeEnd == null ? "" : StringEscapeUtils.escapeHtml4(strBlockTimeEnd());
	}

	public void htmBlockTimeEnd(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockTimeEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockTimeEnd() {");
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
				r.l("				data: {\"setBlockTimeEnd\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockTimeEnd()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockTimeEnd\"");
							r.s(" value=\"", htmBlockTimeEnd(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockTimeEnd());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// blockPricePerMonth //
	////////////////////////

	/**	L'entité « blockPricePerMonth »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal blockPricePerMonth;
	@JsonIgnore
	public Wrap<BigDecimal> blockPricePerMonthWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockPricePerMonth").o(blockPricePerMonth);

	/**	<br/>L'entité « blockPricePerMonth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Trouver l'entité blockPricePerMonth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockPricePerMonth(Wrap<BigDecimal> c);

	public BigDecimal getBlockPricePerMonth() {
		return blockPricePerMonth;
	}

	public void setBlockPricePerMonth(BigDecimal blockPricePerMonth) {
		this.blockPricePerMonth = blockPricePerMonth;
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockPricePerMonth(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockPricePerMonth(Double o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockPricePerMonth(Integer o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockPricePerMonthInit() {
		if(!blockPricePerMonthWrap.alreadyInitialized) {
			_blockPricePerMonth(blockPricePerMonthWrap);
			if(blockPricePerMonth == null)
				setBlockPricePerMonth(blockPricePerMonthWrap.o);
		}
		blockPricePerMonthWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Double solrBlockPricePerMonth() {
		return blockPricePerMonth == null ? null : blockPricePerMonth.doubleValue();
	}

	public String strBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.toString();
	}

	public String jsonBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.toString();
	}

	public String nomAffichageBlockPricePerMonth() {
		return "price per month";
	}

	public String htmTooltipBlockPricePerMonth() {
		return null;
	}

	public String htmBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : StringEscapeUtils.escapeHtml4(strBlockPricePerMonth());
	}

	public void htmBlockPricePerMonth(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockPricePerMonth\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockPricePerMonth() {");
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
				r.l("				data: {\"setBlockPricePerMonth\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockPricePerMonth()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockPricePerMonth\"");
							r.s(" value=\"", htmBlockPricePerMonth(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockPricePerMonth());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// blockSunday //
	/////////////////

	/**	L'entité « blockSunday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockSunday;
	@JsonIgnore
	public Wrap<Boolean> blockSundayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSunday").o(blockSunday);

	/**	<br/>L'entité « blockSunday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSunday">Trouver l'entité blockSunday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockSunday(Wrap<Boolean> c);

	public Boolean getBlockSunday() {
		return blockSunday;
	}

	public void setBlockSunday(Boolean blockSunday) {
		this.blockSunday = blockSunday;
		this.blockSundayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockSunday(String o) {
		this.blockSunday = Boolean.parseBoolean(o);
		this.blockSundayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockSundayInit() {
		if(!blockSundayWrap.alreadyInitialized) {
			_blockSunday(blockSundayWrap);
			if(blockSunday == null)
				setBlockSunday(blockSundayWrap.o);
		}
		blockSundayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockSunday() {
		return blockSunday;
	}

	public String strBlockSunday() {
		return blockSunday == null ? "" : blockSunday.toString();
	}

	public String jsonBlockSunday() {
		return blockSunday == null ? "" : blockSunday.toString();
	}

	public String nomAffichageBlockSunday() {
		return "sunday";
	}

	public String htmTooltipBlockSunday() {
		return null;
	}

	public String htmBlockSunday() {
		return blockSunday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockSunday());
	}

	public void htmBlockSunday(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockSunday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockSunday() {");
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
				r.l("				data: {\"setBlockSunday\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockSunday()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockSunday\"");
							r.s(" value=\"", htmBlockSunday(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockSunday());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// blockMonday //
	/////////////////

	/**	L'entité « blockMonday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockMonday;
	@JsonIgnore
	public Wrap<Boolean> blockMondayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockMonday").o(blockMonday);

	/**	<br/>L'entité « blockMonday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockMonday">Trouver l'entité blockMonday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockMonday(Wrap<Boolean> c);

	public Boolean getBlockMonday() {
		return blockMonday;
	}

	public void setBlockMonday(Boolean blockMonday) {
		this.blockMonday = blockMonday;
		this.blockMondayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockMonday(String o) {
		this.blockMonday = Boolean.parseBoolean(o);
		this.blockMondayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockMondayInit() {
		if(!blockMondayWrap.alreadyInitialized) {
			_blockMonday(blockMondayWrap);
			if(blockMonday == null)
				setBlockMonday(blockMondayWrap.o);
		}
		blockMondayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockMonday() {
		return blockMonday;
	}

	public String strBlockMonday() {
		return blockMonday == null ? "" : blockMonday.toString();
	}

	public String jsonBlockMonday() {
		return blockMonday == null ? "" : blockMonday.toString();
	}

	public String nomAffichageBlockMonday() {
		return "monday";
	}

	public String htmTooltipBlockMonday() {
		return null;
	}

	public String htmBlockMonday() {
		return blockMonday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockMonday());
	}

	public void htmBlockMonday(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockMonday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockMonday() {");
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
				r.l("				data: {\"setBlockMonday\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockMonday()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockMonday\"");
							r.s(" value=\"", htmBlockMonday(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockMonday());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// blockTuesday //
	//////////////////

	/**	L'entité « blockTuesday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockTuesday;
	@JsonIgnore
	public Wrap<Boolean> blockTuesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockTuesday").o(blockTuesday);

	/**	<br/>L'entité « blockTuesday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTuesday">Trouver l'entité blockTuesday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockTuesday(Wrap<Boolean> c);

	public Boolean getBlockTuesday() {
		return blockTuesday;
	}

	public void setBlockTuesday(Boolean blockTuesday) {
		this.blockTuesday = blockTuesday;
		this.blockTuesdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockTuesday(String o) {
		this.blockTuesday = Boolean.parseBoolean(o);
		this.blockTuesdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockTuesdayInit() {
		if(!blockTuesdayWrap.alreadyInitialized) {
			_blockTuesday(blockTuesdayWrap);
			if(blockTuesday == null)
				setBlockTuesday(blockTuesdayWrap.o);
		}
		blockTuesdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockTuesday() {
		return blockTuesday;
	}

	public String strBlockTuesday() {
		return blockTuesday == null ? "" : blockTuesday.toString();
	}

	public String jsonBlockTuesday() {
		return blockTuesday == null ? "" : blockTuesday.toString();
	}

	public String nomAffichageBlockTuesday() {
		return "tuesday";
	}

	public String htmTooltipBlockTuesday() {
		return null;
	}

	public String htmBlockTuesday() {
		return blockTuesday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockTuesday());
	}

	public void htmBlockTuesday(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockTuesday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockTuesday() {");
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
				r.l("				data: {\"setBlockTuesday\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockTuesday()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockTuesday\"");
							r.s(" value=\"", htmBlockTuesday(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockTuesday());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// blockWednesday //
	////////////////////

	/**	L'entité « blockWednesday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockWednesday;
	@JsonIgnore
	public Wrap<Boolean> blockWednesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockWednesday").o(blockWednesday);

	/**	<br/>L'entité « blockWednesday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockWednesday">Trouver l'entité blockWednesday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockWednesday(Wrap<Boolean> c);

	public Boolean getBlockWednesday() {
		return blockWednesday;
	}

	public void setBlockWednesday(Boolean blockWednesday) {
		this.blockWednesday = blockWednesday;
		this.blockWednesdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockWednesday(String o) {
		this.blockWednesday = Boolean.parseBoolean(o);
		this.blockWednesdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockWednesdayInit() {
		if(!blockWednesdayWrap.alreadyInitialized) {
			_blockWednesday(blockWednesdayWrap);
			if(blockWednesday == null)
				setBlockWednesday(blockWednesdayWrap.o);
		}
		blockWednesdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockWednesday() {
		return blockWednesday;
	}

	public String strBlockWednesday() {
		return blockWednesday == null ? "" : blockWednesday.toString();
	}

	public String jsonBlockWednesday() {
		return blockWednesday == null ? "" : blockWednesday.toString();
	}

	public String nomAffichageBlockWednesday() {
		return "wednesday";
	}

	public String htmTooltipBlockWednesday() {
		return null;
	}

	public String htmBlockWednesday() {
		return blockWednesday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockWednesday());
	}

	public void htmBlockWednesday(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockWednesday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockWednesday() {");
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
				r.l("				data: {\"setBlockWednesday\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockWednesday()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockWednesday\"");
							r.s(" value=\"", htmBlockWednesday(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockWednesday());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// blockThursday //
	///////////////////

	/**	L'entité « blockThursday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockThursday;
	@JsonIgnore
	public Wrap<Boolean> blockThursdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockThursday").o(blockThursday);

	/**	<br/>L'entité « blockThursday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockThursday">Trouver l'entité blockThursday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockThursday(Wrap<Boolean> c);

	public Boolean getBlockThursday() {
		return blockThursday;
	}

	public void setBlockThursday(Boolean blockThursday) {
		this.blockThursday = blockThursday;
		this.blockThursdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockThursday(String o) {
		this.blockThursday = Boolean.parseBoolean(o);
		this.blockThursdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockThursdayInit() {
		if(!blockThursdayWrap.alreadyInitialized) {
			_blockThursday(blockThursdayWrap);
			if(blockThursday == null)
				setBlockThursday(blockThursdayWrap.o);
		}
		blockThursdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockThursday() {
		return blockThursday;
	}

	public String strBlockThursday() {
		return blockThursday == null ? "" : blockThursday.toString();
	}

	public String jsonBlockThursday() {
		return blockThursday == null ? "" : blockThursday.toString();
	}

	public String nomAffichageBlockThursday() {
		return "thursday";
	}

	public String htmTooltipBlockThursday() {
		return null;
	}

	public String htmBlockThursday() {
		return blockThursday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockThursday());
	}

	public void htmBlockThursday(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockThursday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockThursday() {");
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
				r.l("				data: {\"setBlockThursday\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockThursday()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockThursday\"");
							r.s(" value=\"", htmBlockThursday(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockThursday());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// blockFriday //
	/////////////////

	/**	L'entité « blockFriday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockFriday;
	@JsonIgnore
	public Wrap<Boolean> blockFridayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockFriday").o(blockFriday);

	/**	<br/>L'entité « blockFriday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockFriday">Trouver l'entité blockFriday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockFriday(Wrap<Boolean> c);

	public Boolean getBlockFriday() {
		return blockFriday;
	}

	public void setBlockFriday(Boolean blockFriday) {
		this.blockFriday = blockFriday;
		this.blockFridayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockFriday(String o) {
		this.blockFriday = Boolean.parseBoolean(o);
		this.blockFridayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockFridayInit() {
		if(!blockFridayWrap.alreadyInitialized) {
			_blockFriday(blockFridayWrap);
			if(blockFriday == null)
				setBlockFriday(blockFridayWrap.o);
		}
		blockFridayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockFriday() {
		return blockFriday;
	}

	public String strBlockFriday() {
		return blockFriday == null ? "" : blockFriday.toString();
	}

	public String jsonBlockFriday() {
		return blockFriday == null ? "" : blockFriday.toString();
	}

	public String nomAffichageBlockFriday() {
		return "friday";
	}

	public String htmTooltipBlockFriday() {
		return null;
	}

	public String htmBlockFriday() {
		return blockFriday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockFriday());
	}

	public void htmBlockFriday(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockFriday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockFriday() {");
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
				r.l("				data: {\"setBlockFriday\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockFriday()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockFriday\"");
							r.s(" value=\"", htmBlockFriday(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockFriday());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// blockSaturday //
	///////////////////

	/**	L'entité « blockSaturday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockSaturday;
	@JsonIgnore
	public Wrap<Boolean> blockSaturdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSaturday").o(blockSaturday);

	/**	<br/>L'entité « blockSaturday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSaturday">Trouver l'entité blockSaturday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockSaturday(Wrap<Boolean> c);

	public Boolean getBlockSaturday() {
		return blockSaturday;
	}

	public void setBlockSaturday(Boolean blockSaturday) {
		this.blockSaturday = blockSaturday;
		this.blockSaturdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockSaturday(String o) {
		this.blockSaturday = Boolean.parseBoolean(o);
		this.blockSaturdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockSaturdayInit() {
		if(!blockSaturdayWrap.alreadyInitialized) {
			_blockSaturday(blockSaturdayWrap);
			if(blockSaturday == null)
				setBlockSaturday(blockSaturdayWrap.o);
		}
		blockSaturdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockSaturday() {
		return blockSaturday;
	}

	public String strBlockSaturday() {
		return blockSaturday == null ? "" : blockSaturday.toString();
	}

	public String jsonBlockSaturday() {
		return blockSaturday == null ? "" : blockSaturday.toString();
	}

	public String nomAffichageBlockSaturday() {
		return "saturday";
	}

	public String htmTooltipBlockSaturday() {
		return null;
	}

	public String htmBlockSaturday() {
		return blockSaturday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockSaturday());
	}

	public void htmBlockSaturday(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlockSaturday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlockSaturday() {");
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
				r.l("				data: {\"setBlockSaturday\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockSaturday()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockSaturday\"");
							r.s(" value=\"", htmBlockSaturday(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockSaturday());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// blocNameComplete //
	//////////////////////

	/**	L'entité « blocNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String blocNameComplete;
	@JsonIgnore
	public Wrap<String> blocNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("blocNameComplete").o(blocNameComplete);

	/**	<br/>L'entité « blocNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blocNameComplete">Trouver l'entité blocNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocNameComplete(Wrap<String> c);

	public String getBlocNameComplete() {
		return blocNameComplete;
	}

	public void setBlocNameComplete(String blocNameComplete) {
		this.blocNameComplete = blocNameComplete;
		this.blocNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolBlock blocNameCompleteInit() {
		if(!blocNameCompleteWrap.alreadyInitialized) {
			_blocNameComplete(blocNameCompleteWrap);
			if(blocNameComplete == null)
				setBlocNameComplete(blocNameCompleteWrap.o);
		}
		blocNameCompleteWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrBlocNameComplete() {
		return blocNameComplete;
	}

	public String strBlocNameComplete() {
		return blocNameComplete == null ? "" : blocNameComplete;
	}

	public String jsonBlocNameComplete() {
		return blocNameComplete == null ? "" : blocNameComplete;
	}

	public String nomAffichageBlocNameComplete() {
		return null;
	}

	public String htmTooltipBlocNameComplete() {
		return null;
	}

	public String htmBlocNameComplete() {
		return blocNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strBlocNameComplete());
	}

	public void htmBlocNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlocNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlocNameComplete() {");
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
				r.l("				data: {\"setBlocNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocNameComplete\"");
							r.s(" value=\"", htmBlocNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocNameComplete());
			}
			r.l("</div>");
		}
	}

	////////////
	// blocId //
	////////////

	/**	L'entité « blocId »
	 *	 is defined as null before being initialized. 
	 */
	protected String blocId;
	@JsonIgnore
	public Wrap<String> blocIdWrap = new Wrap<String>().p(this).c(String.class).var("blocId").o(blocId);

	/**	<br/>L'entité « blocId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.bloc.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blocId">Trouver l'entité blocId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocId(Wrap<String> c);

	public String getBlocId() {
		return blocId;
	}

	public void setBlocId(String blocId) {
		this.blocId = blocId;
		this.blocIdWrap.alreadyInitialized = true;
	}
	protected SchoolBlock blocIdInit() {
		if(!blocIdWrap.alreadyInitialized) {
			_blocId(blocIdWrap);
			if(blocId == null)
				setBlocId(blocIdWrap.o);
		}
		blocIdWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrBlocId() {
		return blocId;
	}

	public String strBlocId() {
		return blocId == null ? "" : blocId;
	}

	public String jsonBlocId() {
		return blocId == null ? "" : blocId;
	}

	public String nomAffichageBlocId() {
		return "ID";
	}

	public String htmTooltipBlocId() {
		return null;
	}

	public String htmBlocId() {
		return blocId == null ? "" : StringEscapeUtils.escapeHtml4(strBlocId());
	}

	public void htmBlocId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolBlock", strPk(), "BlocId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolBlock", strPk(), "BlocId() {");
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
				r.l("				data: {\"setBlocId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocId()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocId\"");
							r.s(" value=\"", htmBlocId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocId());
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

	public String jsonPageUrl() {
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

	public String jsonObjectSuggest() {
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
		blockKeyInit();
		enrollmentKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		sessionSortInit();
		ageSortInit();
		ageSearchInit();
		ageInit();
		schoolNameCompleteInit();
		yearStartInit();
		yearEndInit();
		seasonStartDayInit();
		seasonSummerInit();
		seasonWinterInit();
		seasonEnrollmentFeeInit();
		seasonNameCompleteInit();
		ageStartDayInit();
		sessionEndDayInit();
		ageNameCompleteInit();
		ageStartInit();
		ageEndInit();
		blockTimeStartInit();
		blockTimeEndInit();
		blockPricePerMonthInit();
		blockSundayInit();
		blockMondayInit();
		blockTuesdayInit();
		blockWednesdayInit();
		blockThursdayInit();
		blockFridayInit();
		blockSaturdayInit();
		blocNameCompleteInit();
		blocIdInit();
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
		if(ageSearch != null)
			ageSearch.setSiteRequest_(siteRequest_);
		if(age != null)
			age.setSiteRequest_(siteRequest_);
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
			case "blockKey":
				return oSchoolBlock.blockKey;
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
			case "ageSort":
				return oSchoolBlock.ageSort;
			case "ageSearch":
				return oSchoolBlock.ageSearch;
			case "age":
				return oSchoolBlock.age;
			case "schoolNameComplete":
				return oSchoolBlock.schoolNameComplete;
			case "yearStart":
				return oSchoolBlock.yearStart;
			case "yearEnd":
				return oSchoolBlock.yearEnd;
			case "seasonStartDay":
				return oSchoolBlock.seasonStartDay;
			case "seasonSummer":
				return oSchoolBlock.seasonSummer;
			case "seasonWinter":
				return oSchoolBlock.seasonWinter;
			case "seasonEnrollmentFee":
				return oSchoolBlock.seasonEnrollmentFee;
			case "seasonNameComplete":
				return oSchoolBlock.seasonNameComplete;
			case "ageStartDay":
				return oSchoolBlock.ageStartDay;
			case "sessionEndDay":
				return oSchoolBlock.sessionEndDay;
			case "ageNameComplete":
				return oSchoolBlock.ageNameComplete;
			case "ageStart":
				return oSchoolBlock.ageStart;
			case "ageEnd":
				return oSchoolBlock.ageEnd;
			case "blockTimeStart":
				return oSchoolBlock.blockTimeStart;
			case "blockTimeEnd":
				return oSchoolBlock.blockTimeEnd;
			case "blockPricePerMonth":
				return oSchoolBlock.blockPricePerMonth;
			case "blockSunday":
				return oSchoolBlock.blockSunday;
			case "blockMonday":
				return oSchoolBlock.blockMonday;
			case "blockTuesday":
				return oSchoolBlock.blockTuesday;
			case "blockWednesday":
				return oSchoolBlock.blockWednesday;
			case "blockThursday":
				return oSchoolBlock.blockThursday;
			case "blockFriday":
				return oSchoolBlock.blockFriday;
			case "blockSaturday":
				return oSchoolBlock.blockSaturday;
			case "blocNameComplete":
				return oSchoolBlock.blocNameComplete;
			case "blocId":
				return oSchoolBlock.blocId;
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
			case "ageKey":
				oSchoolBlock.setAgeKey((Long)val);
				return val;
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
			case "blockTimeStart":
				setBlockTimeStart(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockTimeEnd":
				setBlockTimeEnd(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockPricePerMonth":
				setBlockPricePerMonth(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockSunday":
				setBlockSunday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockMonday":
				setBlockMonday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockTuesday":
				setBlockTuesday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockWednesday":
				setBlockWednesday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockThursday":
				setBlockThursday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockFriday":
				setBlockFriday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockSaturday":
				setBlockSaturday(val);
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

			Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
			if(ageKey != null)
				oSchoolBlock.setAgeKey(ageKey);

			if(savesSchoolBlock.contains("blockKey")) {
				Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
				if(blockKey != null)
					oSchoolBlock.setBlockKey(blockKey);
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

			if(savesSchoolBlock.contains("ageSort")) {
				Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
				if(ageSort != null)
					oSchoolBlock.setAgeSort(ageSort);
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

			if(savesSchoolBlock.contains("seasonStartDay")) {
				Date seasonStartDay = (Date)solrDocument.get("seasonStartDay_stored_date");
				if(seasonStartDay != null)
					oSchoolBlock.setSeasonStartDay(seasonStartDay);
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

			if(savesSchoolBlock.contains("ageStartDay")) {
				Date ageStartDay = (Date)solrDocument.get("ageStartDay_stored_date");
				if(ageStartDay != null)
					oSchoolBlock.setAgeStartDay(ageStartDay);
			}

			if(savesSchoolBlock.contains("sessionEndDay")) {
				Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
				if(sessionEndDay != null)
					oSchoolBlock.setSessionEndDay(sessionEndDay);
			}

			if(savesSchoolBlock.contains("ageNameComplete")) {
				String ageNameComplete = (String)solrDocument.get("ageNameComplete_stored_string");
				if(ageNameComplete != null)
					oSchoolBlock.setAgeNameComplete(ageNameComplete);
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

			if(savesSchoolBlock.contains("blockTimeStart")) {
				String blockTimeStart = (String)solrDocument.get("blockTimeStart_stored_string");
				if(blockTimeStart != null)
					oSchoolBlock.setBlockTimeStart(blockTimeStart);
			}

			if(savesSchoolBlock.contains("blockTimeEnd")) {
				String blockTimeEnd = (String)solrDocument.get("blockTimeEnd_stored_string");
				if(blockTimeEnd != null)
					oSchoolBlock.setBlockTimeEnd(blockTimeEnd);
			}

			if(savesSchoolBlock.contains("blockPricePerMonth")) {
				Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
				if(blockPricePerMonth != null)
					oSchoolBlock.setBlockPricePerMonth(blockPricePerMonth);
			}

			if(savesSchoolBlock.contains("blockSunday")) {
				Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
				if(blockSunday != null)
					oSchoolBlock.setBlockSunday(blockSunday);
			}

			if(savesSchoolBlock.contains("blockMonday")) {
				Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
				if(blockMonday != null)
					oSchoolBlock.setBlockMonday(blockMonday);
			}

			if(savesSchoolBlock.contains("blockTuesday")) {
				Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
				if(blockTuesday != null)
					oSchoolBlock.setBlockTuesday(blockTuesday);
			}

			if(savesSchoolBlock.contains("blockWednesday")) {
				Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
				if(blockWednesday != null)
					oSchoolBlock.setBlockWednesday(blockWednesday);
			}

			if(savesSchoolBlock.contains("blockThursday")) {
				Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
				if(blockThursday != null)
					oSchoolBlock.setBlockThursday(blockThursday);
			}

			if(savesSchoolBlock.contains("blockFriday")) {
				Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
				if(blockFriday != null)
					oSchoolBlock.setBlockFriday(blockFriday);
			}

			if(savesSchoolBlock.contains("blockSaturday")) {
				Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
				if(blockSaturday != null)
					oSchoolBlock.setBlockSaturday(blockSaturday);
			}

			if(savesSchoolBlock.contains("blocNameComplete")) {
				String blocNameComplete = (String)solrDocument.get("blocNameComplete_stored_string");
				if(blocNameComplete != null)
					oSchoolBlock.setBlocNameComplete(blocNameComplete);
			}

			if(savesSchoolBlock.contains("blocId")) {
				String blocId = (String)solrDocument.get("blocId_stored_string");
				if(blocId != null)
					oSchoolBlock.setBlocId(blocId);
			}

			if(savesSchoolBlock.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oSchoolBlock.setPageUrl(pageUrl);
			}

			if(savesSchoolBlock.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
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
		if(blockKey != null) {
			document.addField("blockKey_indexed_long", blockKey);
			document.addField("blockKey_stored_long", blockKey);
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
		if(ageSort != null) {
			document.addField("ageSort_indexed_int", ageSort);
			document.addField("ageSort_stored_int", ageSort);
		}
		if(schoolNameComplete != null) {
			document.addField("schoolNameComplete_indexed_string", schoolNameComplete);
			document.addField("schoolNameComplete_stored_string", schoolNameComplete);
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("yearStart_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("yearEnd_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(seasonStartDay != null) {
			document.addField("seasonStartDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
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
			document.addField("seasonEnrollmentFee_indexed_double", seasonEnrollmentFee.doubleValue());
			document.addField("seasonEnrollmentFee_stored_double", seasonEnrollmentFee.doubleValue());
		}
		if(seasonNameComplete != null) {
			document.addField("seasonNameComplete_indexed_string", seasonNameComplete);
			document.addField("seasonNameComplete_stored_string", seasonNameComplete);
		}
		if(ageStartDay != null) {
			document.addField("ageStartDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(ageStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("ageStartDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(ageStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionEndDay != null) {
			document.addField("sessionEndDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionEndDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(ageNameComplete != null) {
			document.addField("ageNameComplete_indexed_string", ageNameComplete);
			document.addField("ageNameComplete_stored_string", ageNameComplete);
		}
		if(ageStart != null) {
			document.addField("ageStart_indexed_int", ageStart);
			document.addField("ageStart_stored_int", ageStart);
		}
		if(ageEnd != null) {
			document.addField("ageEnd_indexed_int", ageEnd);
			document.addField("ageEnd_stored_int", ageEnd);
		}
		if(blockTimeStart != null) {
			document.addField("blockTimeStart_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blockTimeStart.atOffset(ZoneOffset.UTC)));
			document.addField("blockTimeStart_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blockTimeStart.atOffset(ZoneOffset.UTC)));
		}
		if(blockTimeEnd != null) {
			document.addField("blockTimeEnd_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blockTimeEnd.atOffset(ZoneOffset.UTC)));
			document.addField("blockTimeEnd_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blockTimeEnd.atOffset(ZoneOffset.UTC)));
		}
		if(blockPricePerMonth != null) {
			document.addField("blockPricePerMonth_indexed_double", blockPricePerMonth.doubleValue());
			document.addField("blockPricePerMonth_stored_double", blockPricePerMonth.doubleValue());
		}
		if(blockSunday != null) {
			document.addField("blockSunday_indexed_boolean", blockSunday);
			document.addField("blockSunday_stored_boolean", blockSunday);
		}
		if(blockMonday != null) {
			document.addField("blockMonday_indexed_boolean", blockMonday);
			document.addField("blockMonday_stored_boolean", blockMonday);
		}
		if(blockTuesday != null) {
			document.addField("blockTuesday_indexed_boolean", blockTuesday);
			document.addField("blockTuesday_stored_boolean", blockTuesday);
		}
		if(blockWednesday != null) {
			document.addField("blockWednesday_indexed_boolean", blockWednesday);
			document.addField("blockWednesday_stored_boolean", blockWednesday);
		}
		if(blockThursday != null) {
			document.addField("blockThursday_indexed_boolean", blockThursday);
			document.addField("blockThursday_stored_boolean", blockThursday);
		}
		if(blockFriday != null) {
			document.addField("blockFriday_indexed_boolean", blockFriday);
			document.addField("blockFriday_stored_boolean", blockFriday);
		}
		if(blockSaturday != null) {
			document.addField("blockSaturday_indexed_boolean", blockSaturday);
			document.addField("blockSaturday_stored_boolean", blockSaturday);
		}
		if(blocNameComplete != null) {
			document.addField("blocNameComplete_indexed_string", blocNameComplete);
			document.addField("blocNameComplete_stored_string", blocNameComplete);
		}
		if(blocId != null) {
			document.addField("blocId_indexed_string", blocId);
			document.addField("blocId_stored_string", blocId);
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

		Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
		if(blockKey != null)
			oSchoolBlock.setBlockKey(blockKey);

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

		Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
		if(ageSort != null)
			oSchoolBlock.setAgeSort(ageSort);

		String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
		if(schoolNameComplete != null)
			oSchoolBlock.setSchoolNameComplete(schoolNameComplete);

		Date yearStart = (Date)solrDocument.get("yearStart_stored_date");
		if(yearStart != null)
			oSchoolBlock.setYearStart(yearStart);

		Date yearEnd = (Date)solrDocument.get("yearEnd_stored_date");
		if(yearEnd != null)
			oSchoolBlock.setYearEnd(yearEnd);

		Date seasonStartDay = (Date)solrDocument.get("seasonStartDay_stored_date");
		if(seasonStartDay != null)
			oSchoolBlock.setSeasonStartDay(seasonStartDay);

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

		Date ageStartDay = (Date)solrDocument.get("ageStartDay_stored_date");
		if(ageStartDay != null)
			oSchoolBlock.setAgeStartDay(ageStartDay);

		Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
		if(sessionEndDay != null)
			oSchoolBlock.setSessionEndDay(sessionEndDay);

		String ageNameComplete = (String)solrDocument.get("ageNameComplete_stored_string");
		if(ageNameComplete != null)
			oSchoolBlock.setAgeNameComplete(ageNameComplete);

		Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
		if(ageStart != null)
			oSchoolBlock.setAgeStart(ageStart);

		Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
		if(ageEnd != null)
			oSchoolBlock.setAgeEnd(ageEnd);

		String blockTimeStart = (String)solrDocument.get("blockTimeStart_stored_string");
		if(blockTimeStart != null)
			oSchoolBlock.setBlockTimeStart(blockTimeStart);

		String blockTimeEnd = (String)solrDocument.get("blockTimeEnd_stored_string");
		if(blockTimeEnd != null)
			oSchoolBlock.setBlockTimeEnd(blockTimeEnd);

		Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
		if(blockPricePerMonth != null)
			oSchoolBlock.setBlockPricePerMonth(blockPricePerMonth);

		Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
		if(blockSunday != null)
			oSchoolBlock.setBlockSunday(blockSunday);

		Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
		if(blockMonday != null)
			oSchoolBlock.setBlockMonday(blockMonday);

		Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
		if(blockTuesday != null)
			oSchoolBlock.setBlockTuesday(blockTuesday);

		Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
		if(blockWednesday != null)
			oSchoolBlock.setBlockWednesday(blockWednesday);

		Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
		if(blockThursday != null)
			oSchoolBlock.setBlockThursday(blockThursday);

		Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
		if(blockFriday != null)
			oSchoolBlock.setBlockFriday(blockFriday);

		Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
		if(blockSaturday != null)
			oSchoolBlock.setBlockSaturday(blockSaturday);

		String blocNameComplete = (String)solrDocument.get("blocNameComplete_stored_string");
		if(blocNameComplete != null)
			oSchoolBlock.setBlocNameComplete(blocNameComplete);

		String blocId = (String)solrDocument.get("blocId_stored_string");
		if(blocId != null)
			oSchoolBlock.setBlocId(blocId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oSchoolBlock.setPageUrl(pageUrl);

		String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
		oSchoolBlock.setObjectSuggest(objectSuggest);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), ageKey, blockTimeStart, blockTimeEnd, blockPricePerMonth, blockSunday, blockMonday, blockTuesday, blockWednesday, blockThursday, blockFriday, blockSaturday);
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
				&& Objects.equals( ageKey, that.ageKey )
				&& Objects.equals( blockTimeStart, that.blockTimeStart )
				&& Objects.equals( blockTimeEnd, that.blockTimeEnd )
				&& Objects.equals( blockPricePerMonth, that.blockPricePerMonth )
				&& Objects.equals( blockSunday, that.blockSunday )
				&& Objects.equals( blockMonday, that.blockMonday )
				&& Objects.equals( blockTuesday, that.blockTuesday )
				&& Objects.equals( blockWednesday, that.blockWednesday )
				&& Objects.equals( blockThursday, that.blockThursday )
				&& Objects.equals( blockFriday, that.blockFriday )
				&& Objects.equals( blockSaturday, that.blockSaturday );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolBlock { ");
		sb.append( "ageKey: " ).append(ageKey);
		sb.append( ", blockTimeStart: " ).append(blockTimeStart);
		sb.append( ", blockTimeEnd: " ).append(blockTimeEnd);
		sb.append( ", blockPricePerMonth: " ).append(blockPricePerMonth);
		sb.append( ", blockSunday: " ).append(blockSunday);
		sb.append( ", blockMonday: " ).append(blockMonday);
		sb.append( ", blockTuesday: " ).append(blockTuesday);
		sb.append( ", blockWednesday: " ).append(blockWednesday);
		sb.append( ", blockThursday: " ).append(blockThursday);
		sb.append( ", blockFriday: " ).append(blockFriday);
		sb.append( ", blockSaturday: " ).append(blockSaturday);
		sb.append(" }");
		return sb.toString();
	}
}