package org.computate.scolaire.enUS.enrollment;

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
import org.computate.scolaire.enUS.child.SchoolChild;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolEnrollmentGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolEnrollment.class);

	public static final String SchoolEnrollment_UnNom = "an enrollment";
	public static final String SchoolEnrollment_Ce = "this ";
	public static final String SchoolEnrollment_CeNom = "this enrollment";
	public static final String SchoolEnrollment_Un = "a ";
	public static final String SchoolEnrollment_LeNom = "theenrollment";
	public static final String SchoolEnrollment_NomSingulier = "enrollment";
	public static final String SchoolEnrollment_NomPluriel = "enrollments";
	public static final String SchoolEnrollment_NomActuel = "current enrollment";
	public static final String SchoolEnrollment_TousNom = "all the enrollments";
	public static final String SchoolEnrollment_RechercherTousNomPar = "search enrollments by ";
	public static final String SchoolEnrollment_LesNoms = "the enrollments";
	public static final String SchoolEnrollment_AucunNomTrouve = "no enrollment found";
	public static final String SchoolEnrollment_NomVar = "enrollment";
	public static final String SchoolEnrollment_DeNom = "of enrollment";
	public static final String SchoolEnrollment_UnNomAdjectif = "an enrollment";
	public static final String SchoolEnrollment_NomAdjectifSingulier = "enrollment";
	public static final String SchoolEnrollment_NomAdjectifPluriel = "enrollments";
	public static final String SchoolEnrollment_Couleur = "purple";
	public static final String SchoolEnrollment_IconeGroupe = "solid";
	public static final String SchoolEnrollment_IconeNom = "edit";

	///////////////////
	// enrollmentKey //
	///////////////////

	/**	L'entité « enrollmentKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long enrollmentKey;
	@JsonIgnore
	public Wrap<Long> enrollmentKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentKey").o(enrollmentKey);

	/**	<br/>L'entité « enrollmentKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKey">Trouver l'entité enrollmentKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentKey(Wrap<Long> c);

	public Long getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(Long enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentKey = Long.parseLong(o);
		this.enrollmentKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentKeyInit() {
		if(!enrollmentKeyWrap.alreadyInitialized) {
			_enrollmentKey(enrollmentKeyWrap);
			if(enrollmentKey == null)
				setEnrollmentKey(enrollmentKeyWrap.o);
		}
		enrollmentKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Long solrEnrollmentKey() {
		return enrollmentKey;
	}

	public String strEnrollmentKey() {
		return enrollmentKey == null ? "" : enrollmentKey.toString();
	}

	public String jsonEnrollmentKey() {
		return enrollmentKey == null ? "" : enrollmentKey.toString();
	}

	public String nomAffichageEnrollmentKey() {
		return "key";
	}

	public String htmTooltipEnrollmentKey() {
		return null;
	}

	public String htmEnrollmentKey() {
		return enrollmentKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentKey());
	}

	public void htmEnrollmentKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentKey() {");
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
				r.l("				data: {\"setEnrollmentKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentKey\"");
							r.s(" value=\"", htmEnrollmentKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentKey());
			}
			r.l("</div>");
		}
	}

	///////////////
	// blockKeys //
	///////////////

	/**	L'entité « blockKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> blockKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> blockKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("blockKeys").o(blockKeys);

	/**	<br/>L'entité « blockKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKeys">Trouver l'entité blockKeys dans Solr</a>
	 * <br/>
	 * @param blockKeys est l'entité déjà construit. 
	 **/
	protected abstract void _blockKeys(List<Long> o);

	public List<Long> getBlockKeys() {
		return blockKeys;
	}

	public void setBlockKeys(List<Long> blockKeys) {
		this.blockKeys = blockKeys;
		this.blockKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addBlockKeys(Long...objets) {
		for(Long o : objets) {
			addBlockKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addBlockKeys(Long o) {
		if(o != null && !blockKeys.contains(o))
			this.blockKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockKeys(JsonArray objets) {
		blockKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlockKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addBlockKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addBlockKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockKeysInit() {
		if(!blockKeysWrap.alreadyInitialized) {
			_blockKeys(blockKeys);
		}
		blockKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrBlockKeys() {
		return blockKeys;
	}

	public String strBlockKeys() {
		return blockKeys == null ? "" : blockKeys.toString();
	}

	public String jsonBlockKeys() {
		return blockKeys == null ? "" : blockKeys.toString();
	}

	public String nomAffichageBlockKeys() {
		return "blocks";
	}

	public String htmTooltipBlockKeys() {
		return null;
	}

	public String htmBlockKeys() {
		return blockKeys == null ? "" : StringEscapeUtils.escapeHtml4(strBlockKeys());
	}

	public void htmBlockKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockKeys() {");
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
				r.l("				data: {\"setBlockKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockKeys\"");
							r.s(" value=\"", htmBlockKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockKeys());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// blockSearch //
	/////////////////

	/**	L'entité « blockSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolBlock>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolBlock> blockSearch = new SearchList<SchoolBlock>();
	@JsonIgnore
	public Wrap<SearchList<SchoolBlock>> blockSearchWrap = new Wrap<SearchList<SchoolBlock>>().p(this).c(SearchList.class).var("blockSearch").o(blockSearch);

	/**	<br/>L'entité « blockSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolBlock>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSearch">Trouver l'entité blockSearch dans Solr</a>
	 * <br/>
	 * @param blockSearch est l'entité déjà construit. 
	 **/
	protected abstract void _blockSearch(SearchList<SchoolBlock> l);

	public SearchList<SchoolBlock> getBlockSearch() {
		return blockSearch;
	}

	public void setBlockSearch(SearchList<SchoolBlock> blockSearch) {
		this.blockSearch = blockSearch;
		this.blockSearchWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment blockSearchInit() {
		if(!blockSearchWrap.alreadyInitialized) {
			_blockSearch(blockSearch);
		}
		blockSearch.initDeepForClass(siteRequest_);
		blockSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////
	// block //
	///////////

	/**	L'entité « block »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolBlock block;
	@JsonIgnore
	public Wrap<SchoolBlock> blockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("block").o(block);

	/**	<br/>L'entité « block »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:block">Trouver l'entité block dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _block(Wrap<SchoolBlock> c);

	public SchoolBlock getBlock() {
		return block;
	}

	public void setBlock(SchoolBlock block) {
		this.block = block;
		this.blockWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment blockInit() {
		if(!blockWrap.alreadyInitialized) {
			_block(blockWrap);
			if(block == null)
				setBlock(blockWrap.o);
		}
		if(block != null)
			block.initDeepForClass(siteRequest_);
		blockWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
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
	public SchoolEnrollment setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SchoolKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SchoolKey() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public SchoolEnrollment setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment anneeCleInit() {
		if(!anneeCleWrap.alreadyInitialized) {
			_anneeCle(anneeCleWrap);
			if(anneeCle == null)
				setAnneeCle(anneeCleWrap.o);
		}
		anneeCleWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "AnneeCle\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "AnneeCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKey">Trouver l'entité seasonKey dans Solr</a>
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
	public SchoolEnrollment setSeasonKey(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonKey = Long.parseLong(o);
		this.seasonKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonKeyInit() {
		if(!seasonKeyWrap.alreadyInitialized) {
			_seasonKey(seasonKeyWrap);
			if(seasonKey == null)
				setSeasonKey(seasonKeyWrap.o);
		}
		seasonKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SeasonKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SeasonKey() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Trouver l'entité sessionKey dans Solr</a>
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
	public SchoolEnrollment setSessionKey(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionKey = Long.parseLong(o);
		this.sessionKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionKeyInit() {
		if(!sessionKeyWrap.alreadyInitialized) {
			_sessionKey(sessionKeyWrap);
			if(sessionKey == null)
				setSessionKey(sessionKeyWrap.o);
		}
		sessionKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
		return "session";
	}

	public String htmTooltipSessionKey() {
		return null;
	}

	public String htmSessionKey() {
		return sessionKey == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKey());
	}

	public void htmSessionKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SessionKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SessionKey() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Trouver l'entité ageKey dans Solr</a>
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
	public SchoolEnrollment setAgeKey(String o) {
		if(NumberUtils.isParsable(o))
			this.ageKey = Long.parseLong(o);
		this.ageKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageKeyInit() {
		if(!ageKeyWrap.alreadyInitialized) {
			_ageKey(ageKeyWrap);
			if(ageKey == null)
				setAgeKey(ageKeyWrap.o);
		}
		ageKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "AgeKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "AgeKey() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Trouver l'entité blockKey dans Solr</a>
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
	public SchoolEnrollment setBlockKey(String o) {
		if(NumberUtils.isParsable(o))
			this.blockKey = Long.parseLong(o);
		this.blockKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockKeyInit() {
		if(!blockKeyWrap.alreadyInitialized) {
			_blockKey(blockKeyWrap);
			if(blockKey == null)
				setBlockKey(blockKeyWrap.o);
		}
		blockKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockKey() {");
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

	//////////////
	// childKey //
	//////////////

	/**	L'entité « childKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long childKey;
	@JsonIgnore
	public Wrap<Long> childKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("childKey").o(childKey);

	/**	<br/>L'entité « childKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Trouver l'entité childKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childKey(Wrap<Long> c);

	public Long getChildKey() {
		return childKey;
	}

	public void setChildKey(Long childKey) {
		this.childKey = childKey;
		this.childKeyWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setChildKey(String o) {
		if(NumberUtils.isParsable(o))
			this.childKey = Long.parseLong(o);
		this.childKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment childKeyInit() {
		if(!childKeyWrap.alreadyInitialized) {
			_childKey(childKeyWrap);
			if(childKey == null)
				setChildKey(childKeyWrap.o);
		}
		childKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Long solrChildKey() {
		return childKey;
	}

	public String strChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public String jsonChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public String nomAffichageChildKey() {
		return "children";
	}

	public String htmTooltipChildKey() {
		return null;
	}

	public String htmChildKey() {
		return childKey == null ? "" : StringEscapeUtils.escapeHtml4(strChildKey());
	}

	public void htmChildKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "ChildKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "ChildKey() {");
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
				r.l("				data: {\"setChildKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageChildKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"childKey\"");
							r.s(" value=\"", htmChildKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmChildKey());
			}
			r.l("</div>");
		}
	}

	/////////////
	// momKeys //
	/////////////

	/**	L'entité « momKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> momKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> momKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("momKeys").o(momKeys);

	/**	<br/>L'entité « momKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momKeys">Trouver l'entité momKeys dans Solr</a>
	 * <br/>
	 * @param momKeys est l'entité déjà construit. 
	 **/
	protected abstract void _momKeys(List<Long> o);

	public List<Long> getMomKeys() {
		return momKeys;
	}

	public void setMomKeys(List<Long> momKeys) {
		this.momKeys = momKeys;
		this.momKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addMomKeys(Long...objets) {
		for(Long o : objets) {
			addMomKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addMomKeys(Long o) {
		if(o != null && !momKeys.contains(o))
			this.momKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setMomKeys(JsonArray objets) {
		momKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMomKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addMomKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addMomKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment momKeysInit() {
		if(!momKeysWrap.alreadyInitialized) {
			_momKeys(momKeys);
		}
		momKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrMomKeys() {
		return momKeys;
	}

	public String strMomKeys() {
		return momKeys == null ? "" : momKeys.toString();
	}

	public String jsonMomKeys() {
		return momKeys == null ? "" : momKeys.toString();
	}

	public String nomAffichageMomKeys() {
		return "moms";
	}

	public String htmTooltipMomKeys() {
		return null;
	}

	public String htmMomKeys() {
		return momKeys == null ? "" : StringEscapeUtils.escapeHtml4(strMomKeys());
	}

	public void htmMomKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "MomKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "MomKeys() {");
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
				r.l("				data: {\"setMomKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageMomKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"momKeys\"");
							r.s(" value=\"", htmMomKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmMomKeys());
			}
			r.l("</div>");
		}
	}

	/////////////
	// dadKeys //
	/////////////

	/**	L'entité « dadKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> dadKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> dadKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("dadKeys").o(dadKeys);

	/**	<br/>L'entité « dadKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadKeys">Trouver l'entité dadKeys dans Solr</a>
	 * <br/>
	 * @param dadKeys est l'entité déjà construit. 
	 **/
	protected abstract void _dadKeys(List<Long> o);

	public List<Long> getDadKeys() {
		return dadKeys;
	}

	public void setDadKeys(List<Long> dadKeys) {
		this.dadKeys = dadKeys;
		this.dadKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addDadKeys(Long...objets) {
		for(Long o : objets) {
			addDadKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addDadKeys(Long o) {
		if(o != null && !dadKeys.contains(o))
			this.dadKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setDadKeys(JsonArray objets) {
		dadKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDadKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addDadKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addDadKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment dadKeysInit() {
		if(!dadKeysWrap.alreadyInitialized) {
			_dadKeys(dadKeys);
		}
		dadKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrDadKeys() {
		return dadKeys;
	}

	public String strDadKeys() {
		return dadKeys == null ? "" : dadKeys.toString();
	}

	public String jsonDadKeys() {
		return dadKeys == null ? "" : dadKeys.toString();
	}

	public String nomAffichageDadKeys() {
		return "dads";
	}

	public String htmTooltipDadKeys() {
		return null;
	}

	public String htmDadKeys() {
		return dadKeys == null ? "" : StringEscapeUtils.escapeHtml4(strDadKeys());
	}

	public void htmDadKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "DadKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "DadKeys() {");
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
				r.l("				data: {\"setDadKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageDadKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"dadKeys\"");
							r.s(" value=\"", htmDadKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmDadKeys());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// guardianKeys //
	//////////////////

	/**	L'entité « guardianKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> guardianKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> guardianKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("guardianKeys").o(guardianKeys);

	/**	<br/>L'entité « guardianKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianKeys">Trouver l'entité guardianKeys dans Solr</a>
	 * <br/>
	 * @param guardianKeys est l'entité déjà construit. 
	 **/
	protected abstract void _guardianKeys(List<Long> o);

	public List<Long> getGuardianKeys() {
		return guardianKeys;
	}

	public void setGuardianKeys(List<Long> guardianKeys) {
		this.guardianKeys = guardianKeys;
		this.guardianKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addGuardianKeys(Long...objets) {
		for(Long o : objets) {
			addGuardianKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addGuardianKeys(Long o) {
		if(o != null && !guardianKeys.contains(o))
			this.guardianKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setGuardianKeys(JsonArray objets) {
		guardianKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGuardianKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addGuardianKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addGuardianKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment guardianKeysInit() {
		if(!guardianKeysWrap.alreadyInitialized) {
			_guardianKeys(guardianKeys);
		}
		guardianKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrGuardianKeys() {
		return guardianKeys;
	}

	public String strGuardianKeys() {
		return guardianKeys == null ? "" : guardianKeys.toString();
	}

	public String jsonGuardianKeys() {
		return guardianKeys == null ? "" : guardianKeys.toString();
	}

	public String nomAffichageGuardianKeys() {
		return "guardians";
	}

	public String htmTooltipGuardianKeys() {
		return null;
	}

	public String htmGuardianKeys() {
		return guardianKeys == null ? "" : StringEscapeUtils.escapeHtml4(strGuardianKeys());
	}

	public void htmGuardianKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "GuardianKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "GuardianKeys() {");
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
				r.l("				data: {\"setGuardianKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageGuardianKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"guardianKeys\"");
							r.s(" value=\"", htmGuardianKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmGuardianKeys());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// paymentKeys //
	/////////////////

	/**	L'entité « paymentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> paymentKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> paymentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("paymentKeys").o(paymentKeys);

	/**	<br/>L'entité « paymentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentKeys">Trouver l'entité paymentKeys dans Solr</a>
	 * <br/>
	 * @param paymentKeys est l'entité déjà construit. 
	 **/
	protected abstract void _paymentKeys(List<Long> o);

	public List<Long> getPaymentKeys() {
		return paymentKeys;
	}

	public void setPaymentKeys(List<Long> paymentKeys) {
		this.paymentKeys = paymentKeys;
		this.paymentKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addPaymentKeys(Long...objets) {
		for(Long o : objets) {
			addPaymentKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addPaymentKeys(Long o) {
		if(o != null && !paymentKeys.contains(o))
			this.paymentKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setPaymentKeys(JsonArray objets) {
		paymentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaymentKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addPaymentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPaymentKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment paymentKeysInit() {
		if(!paymentKeysWrap.alreadyInitialized) {
			_paymentKeys(paymentKeys);
		}
		paymentKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrPaymentKeys() {
		return paymentKeys;
	}

	public String strPaymentKeys() {
		return paymentKeys == null ? "" : paymentKeys.toString();
	}

	public String jsonPaymentKeys() {
		return paymentKeys == null ? "" : paymentKeys.toString();
	}

	public String nomAffichagePaymentKeys() {
		return "payments";
	}

	public String htmTooltipPaymentKeys() {
		return null;
	}

	public String htmPaymentKeys() {
		return paymentKeys == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentKeys());
	}

	public void htmPaymentKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "PaymentKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "PaymentKeys() {");
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
				r.l("				data: {\"setPaymentKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePaymentKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"paymentKeys\"");
							r.s(" value=\"", htmPaymentKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPaymentKeys());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
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
	public SchoolEnrollment setEducationSort(String o) {
		if(NumberUtils.isParsable(o))
			this.educationSort = Integer.parseInt(o);
		this.educationSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment educationSortInit() {
		if(!educationSortWrap.alreadyInitialized) {
			_educationSort(educationSortWrap);
			if(educationSort == null)
				setEducationSort(educationSortWrap.o);
		}
		educationSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EducationSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EducationSort() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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
	public SchoolEnrollment setSchoolSort(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SchoolSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SchoolSort() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Trouver l'entité yearSort dans Solr</a>
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
	public SchoolEnrollment setYearSort(String o) {
		if(NumberUtils.isParsable(o))
			this.yearSort = Integer.parseInt(o);
		this.yearSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearSortInit() {
		if(!yearSortWrap.alreadyInitialized) {
			_yearSort(yearSortWrap);
			if(yearSort == null)
				setYearSort(yearSortWrap.o);
		}
		yearSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "YearSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "YearSort() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Trouver l'entité seasonSort dans Solr</a>
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
	public SchoolEnrollment setSeasonSort(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonSort = Integer.parseInt(o);
		this.seasonSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonSortInit() {
		if(!seasonSortWrap.alreadyInitialized) {
			_seasonSort(seasonSortWrap);
			if(seasonSort == null)
				setSeasonSort(seasonSortWrap.o);
		}
		seasonSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SeasonSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SeasonSort() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSort">Trouver l'entité sessionSort dans Solr</a>
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
	public SchoolEnrollment setSessionSort(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionSort = Integer.parseInt(o);
		this.sessionSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionSortInit() {
		if(!sessionSortWrap.alreadyInitialized) {
			_sessionSort(sessionSortWrap);
			if(sessionSort == null)
				setSessionSort(sessionSortWrap.o);
		}
		sessionSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SessionSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SessionSort() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSort">Trouver l'entité ageSort dans Solr</a>
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
	public SchoolEnrollment setAgeSort(String o) {
		if(NumberUtils.isParsable(o))
			this.ageSort = Integer.parseInt(o);
		this.ageSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageSortInit() {
		if(!ageSortWrap.alreadyInitialized) {
			_ageSort(ageSortWrap);
			if(ageSort == null)
				setAgeSort(ageSortWrap.o);
		}
		ageSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "AgeSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "AgeSort() {");
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

	/////////////////
	// childSearch //
	/////////////////

	/**	L'entité « childSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolChild>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolChild> childSearch = new SearchList<SchoolChild>();
	@JsonIgnore
	public Wrap<SearchList<SchoolChild>> childSearchWrap = new Wrap<SearchList<SchoolChild>>().p(this).c(SearchList.class).var("childSearch").o(childSearch);

	/**	<br/>L'entité « childSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolChild>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childSearch">Trouver l'entité childSearch dans Solr</a>
	 * <br/>
	 * @param childSearch est l'entité déjà construit. 
	 **/
	protected abstract void _childSearch(SearchList<SchoolChild> l);

	public SearchList<SchoolChild> getChildSearch() {
		return childSearch;
	}

	public void setChildSearch(SearchList<SchoolChild> childSearch) {
		this.childSearch = childSearch;
		this.childSearchWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childSearchInit() {
		if(!childSearchWrap.alreadyInitialized) {
			_childSearch(childSearch);
		}
		childSearch.initDeepForClass(siteRequest_);
		childSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	////////////
	// child_ //
	////////////

	/**	L'entité « child_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolChild child_;
	@JsonIgnore
	public Wrap<SchoolChild> child_Wrap = new Wrap<SchoolChild>().p(this).c(SchoolChild.class).var("child_").o(child_);

	/**	<br/>L'entité « child_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:child_">Trouver l'entité child_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _child_(Wrap<SchoolChild> c);

	public SchoolChild getChild_() {
		return child_;
	}

	public void setChild_(SchoolChild child_) {
		this.child_ = child_;
		this.child_Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment child_Init() {
		if(!child_Wrap.alreadyInitialized) {
			_child_(child_Wrap);
			if(child_ == null)
				setChild_(child_Wrap.o);
		}
		child_Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////////////
	// childCompleteName //
	///////////////////////

	/**	L'entité « childCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String childCompleteName;
	@JsonIgnore
	public Wrap<String> childCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("childCompleteName").o(childCompleteName);

	/**	<br/>L'entité « childCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childCompleteName">Trouver l'entité childCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childCompleteName(Wrap<String> c);

	public String getChildCompleteName() {
		return childCompleteName;
	}

	public void setChildCompleteName(String childCompleteName) {
		this.childCompleteName = childCompleteName;
		this.childCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childCompleteNameInit() {
		if(!childCompleteNameWrap.alreadyInitialized) {
			_childCompleteName(childCompleteNameWrap);
			if(childCompleteName == null)
				setChildCompleteName(childCompleteNameWrap.o);
		}
		childCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrChildCompleteName() {
		return childCompleteName;
	}

	public String strChildCompleteName() {
		return childCompleteName == null ? "" : childCompleteName;
	}

	public String jsonChildCompleteName() {
		return childCompleteName == null ? "" : childCompleteName;
	}

	public String nomAffichageChildCompleteName() {
		return "r: EnfantNomComplet";
	}

	public String htmTooltipChildCompleteName() {
		return null;
	}

	public String htmChildCompleteName() {
		return childCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strChildCompleteName());
	}

	public void htmChildCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "ChildCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "ChildCompleteName() {");
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
				r.l("				data: {\"setChildCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageChildCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"childCompleteName\"");
							r.s(" value=\"", htmChildCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmChildCompleteName());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// schoolCompleteName //
	////////////////////////

	/**	L'entité « schoolCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/>L'entité « schoolCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Trouver l'entité schoolCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}

	public void setSchoolCompleteName(String schoolCompleteName) {
		this.schoolCompleteName = schoolCompleteName;
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrSchoolCompleteName() {
		return schoolCompleteName;
	}

	public String strSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String jsonSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String nomAffichageSchoolCompleteName() {
		return "r: EcoleNomComplet";
	}

	public String htmTooltipSchoolCompleteName() {
		return null;
	}

	public String htmSchoolCompleteName() {
		return schoolCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolCompleteName());
	}

	public void htmSchoolCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SchoolCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SchoolCompleteName() {");
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
				r.l("				data: {\"setSchoolCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolCompleteName\"");
							r.s(" value=\"", htmSchoolCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolCompleteName());
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
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearStart(Wrap<Integer> c);

	public Integer getYearStart() {
		return yearStart;
	}

	public void setYearStart(Integer yearStart) {
		this.yearStart = yearStart;
		this.yearStartWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Integer solrYearStart() {
		return yearStart;
	}

	public String strYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public String jsonYearStart() {
		return yearStart == null ? "" : yearStart.toString();
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "YearStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "YearStart() {");
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
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearEnd(Wrap<Integer> c);

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
		this.yearEndWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Integer solrYearEnd() {
		return yearEnd;
	}

	public String strYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public String jsonYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "YearEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "YearEnd() {");
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

	/////////////////////
	// seasonStartDate //
	/////////////////////

	/**	L'entité « seasonStartDate »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate seasonStartDate;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDate").o(seasonStartDate);

	/**	<br/>L'entité « seasonStartDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Trouver l'entité seasonStartDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonStartDate(Wrap<LocalDate> c);

	public LocalDate getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(LocalDate seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setSeasonStartDate(Instant o) {
		this.seasonStartDate = LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setSeasonStartDate(String o) {
		this.seasonStartDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSeasonStartDate(Date o) {
		this.seasonStartDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrSeasonStartDate() {
		return seasonStartDate == null ? null : Date.from(seasonStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSeasonStartDate() {
		return "start of season";
	}

	public String htmTooltipSeasonStartDate() {
		return null;
	}

	public String htmSeasonStartDate() {
		return seasonStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStartDate());
	}

	public void htmSeasonStartDate(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SeasonStartDate\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SeasonStartDate() {");
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
				r.l("				data: {\"setSeasonStartDate\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonStartDate()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonStartDate\"");
							r.s(" value=\"", htmSeasonStartDate(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonStartDate());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSummer">Trouver l'entité seasonSummer dans Solr</a>
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
	public SchoolEnrollment setSeasonSummer(String o) {
		this.seasonSummer = Boolean.parseBoolean(o);
		this.seasonSummerWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonSummerInit() {
		if(!seasonSummerWrap.alreadyInitialized) {
			_seasonSummer(seasonSummerWrap);
			if(seasonSummer == null)
				setSeasonSummer(seasonSummerWrap.o);
		}
		seasonSummerWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SeasonSummer\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SeasonSummer() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonWinter">Trouver l'entité seasonWinter dans Solr</a>
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
	public SchoolEnrollment setSeasonWinter(String o) {
		this.seasonWinter = Boolean.parseBoolean(o);
		this.seasonWinterWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonWinterInit() {
		if(!seasonWinterWrap.alreadyInitialized) {
			_seasonWinter(seasonWinterWrap);
			if(seasonWinter == null)
				setSeasonWinter(seasonWinterWrap.o);
		}
		seasonWinterWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SeasonWinter\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SeasonWinter() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonEnrollmentFee">Trouver l'entité seasonEnrollmentFee dans Solr</a>
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
	public SchoolEnrollment setSeasonEnrollmentFee(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSeasonEnrollmentFee(Double o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSeasonEnrollmentFee(Integer o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonEnrollmentFeeInit() {
		if(!seasonEnrollmentFeeWrap.alreadyInitialized) {
			_seasonEnrollmentFee(seasonEnrollmentFeeWrap);
			if(seasonEnrollmentFee == null)
				setSeasonEnrollmentFee(seasonEnrollmentFeeWrap.o);
		}
		seasonEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SeasonEnrollmentFee\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SeasonEnrollmentFee() {");
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
	// seasonCompleteName //
	////////////////////////

	/**	L'entité « seasonCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String seasonCompleteName;
	@JsonIgnore
	public Wrap<String> seasonCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("seasonCompleteName").o(seasonCompleteName);

	/**	<br/>L'entité « seasonCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonCompleteName">Trouver l'entité seasonCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonCompleteName(Wrap<String> c);

	public String getSeasonCompleteName() {
		return seasonCompleteName;
	}

	public void setSeasonCompleteName(String seasonCompleteName) {
		this.seasonCompleteName = seasonCompleteName;
		this.seasonCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment seasonCompleteNameInit() {
		if(!seasonCompleteNameWrap.alreadyInitialized) {
			_seasonCompleteName(seasonCompleteNameWrap);
			if(seasonCompleteName == null)
				setSeasonCompleteName(seasonCompleteNameWrap.o);
		}
		seasonCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrSeasonCompleteName() {
		return seasonCompleteName;
	}

	public String strSeasonCompleteName() {
		return seasonCompleteName == null ? "" : seasonCompleteName;
	}

	public String jsonSeasonCompleteName() {
		return seasonCompleteName == null ? "" : seasonCompleteName;
	}

	public String nomAffichageSeasonCompleteName() {
		return null;
	}

	public String htmTooltipSeasonCompleteName() {
		return null;
	}

	public String htmSeasonCompleteName() {
		return seasonCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonCompleteName());
	}

	public void htmSeasonCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SeasonCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SeasonCompleteName() {");
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
				r.l("				data: {\"setSeasonCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonCompleteName\"");
							r.s(" value=\"", htmSeasonCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonCompleteName());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDay">Trouver l'entité sessionStartDay dans Solr</a>
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
	public SchoolEnrollment setSessionStartDay(Instant o) {
		this.sessionStartDay = LocalDate.from(o);
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setSessionStartDay(String o) {
		this.sessionStartDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSessionStartDay(Date o) {
		this.sessionStartDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionStartDayInit() {
		if(!sessionStartDayWrap.alreadyInitialized) {
			_sessionStartDay(sessionStartDayWrap);
			if(sessionStartDay == null)
				setSessionStartDay(sessionStartDayWrap.o);
		}
		sessionStartDayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrSessionStartDay() {
		return sessionStartDay == null ? null : Date.from(sessionStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionStartDay() {
		return sessionStartDay == null ? "" : sessionStartDay.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSessionStartDay() {
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SessionStartDay\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SessionStartDay() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDay">Trouver l'entité sessionEndDay dans Solr</a>
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
	public SchoolEnrollment setSessionEndDay(Instant o) {
		this.sessionEndDay = LocalDate.from(o);
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setSessionEndDay(String o) {
		this.sessionEndDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSessionEndDay(Date o) {
		this.sessionEndDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionEndDayInit() {
		if(!sessionEndDayWrap.alreadyInitialized) {
			_sessionEndDay(sessionEndDayWrap);
			if(sessionEndDay == null)
				setSessionEndDay(sessionEndDayWrap.o);
		}
		sessionEndDayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "SessionEndDay\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "SessionEndDay() {");
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
	// ageCompleteName //
	/////////////////////

	/**	L'entité « ageCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String ageCompleteName;
	@JsonIgnore
	public Wrap<String> ageCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("ageCompleteName").o(ageCompleteName);

	/**	<br/>L'entité « ageCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageCompleteName">Trouver l'entité ageCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageCompleteName(Wrap<String> c);

	public String getAgeCompleteName() {
		return ageCompleteName;
	}

	public void setAgeCompleteName(String ageCompleteName) {
		this.ageCompleteName = ageCompleteName;
		this.ageCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment ageCompleteNameInit() {
		if(!ageCompleteNameWrap.alreadyInitialized) {
			_ageCompleteName(ageCompleteNameWrap);
			if(ageCompleteName == null)
				setAgeCompleteName(ageCompleteNameWrap.o);
		}
		ageCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrAgeCompleteName() {
		return ageCompleteName;
	}

	public String strAgeCompleteName() {
		return ageCompleteName == null ? "" : ageCompleteName;
	}

	public String jsonAgeCompleteName() {
		return ageCompleteName == null ? "" : ageCompleteName;
	}

	public String nomAffichageAgeCompleteName() {
		return null;
	}

	public String htmTooltipAgeCompleteName() {
		return null;
	}

	public String htmAgeCompleteName() {
		return ageCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strAgeCompleteName());
	}

	public void htmAgeCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "AgeCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "AgeCompleteName() {");
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
				r.l("				data: {\"setAgeCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageCompleteName\"");
							r.s(" value=\"", htmAgeCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeCompleteName());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Trouver l'entité ageStart dans Solr</a>
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
	public SchoolEnrollment setAgeStart(String o) {
		if(NumberUtils.isParsable(o))
			this.ageStart = Integer.parseInt(o);
		this.ageStartWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageStartInit() {
		if(!ageStartWrap.alreadyInitialized) {
			_ageStart(ageStartWrap);
			if(ageStart == null)
				setAgeStart(ageStartWrap.o);
		}
		ageStartWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "AgeStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "AgeStart() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Trouver l'entité ageEnd dans Solr</a>
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
	public SchoolEnrollment setAgeEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.ageEnd = Integer.parseInt(o);
		this.ageEndWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageEndInit() {
		if(!ageEndWrap.alreadyInitialized) {
			_ageEnd(ageEndWrap);
			if(ageEnd == null)
				setAgeEnd(ageEndWrap.o);
		}
		ageEndWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "AgeEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "AgeEnd() {");
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
	// blockStartTime //
	////////////////////

	/**	L'entité « blockStartTime »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blockStartTime;
	@JsonIgnore
	public Wrap<LocalTime> blockStartTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockStartTime").o(blockStartTime);

	/**	<br/>L'entité « blockStartTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockStartTime">Trouver l'entité blockStartTime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockStartTime(Wrap<LocalTime> c);

	public LocalTime getBlockStartTime() {
		return blockStartTime;
	}

	public void setBlockStartTime(LocalTime blockStartTime) {
		this.blockStartTime = blockStartTime;
		this.blockStartTimeWrap.alreadyInitialized = true;
	}
	/** Example: 01:00 **/
	public SchoolEnrollment setBlockStartTime(String o) {
		try {
			this.blockStartTime = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockStartTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockStartTimeInit() {
		if(!blockStartTimeWrap.alreadyInitialized) {
			_blockStartTime(blockStartTimeWrap);
			if(blockStartTime == null)
				setBlockStartTime(blockStartTimeWrap.o);
		}
		blockStartTimeWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrBlockStartTime() {
		return blockStartTime == null ? null : blockStartTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
	}

	public String jsonBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlockStartTime() {
		return "start time";
	}

	public String htmTooltipBlockStartTime() {
		return null;
	}

	public String htmBlockStartTime() {
		return blockStartTime == null ? "" : StringEscapeUtils.escapeHtml4(strBlockStartTime());
	}

	public void htmBlockStartTime(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockStartTime\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockStartTime() {");
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
				r.l("				data: {\"setBlockStartTime\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockStartTime()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockStartTime\"");
							r.s(" value=\"", htmBlockStartTime(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockStartTime());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// blockEndTime //
	//////////////////

	/**	L'entité « blockEndTime »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blockEndTime;
	@JsonIgnore
	public Wrap<LocalTime> blockEndTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockEndTime").o(blockEndTime);

	/**	<br/>L'entité « blockEndTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockEndTime">Trouver l'entité blockEndTime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockEndTime(Wrap<LocalTime> c);

	public LocalTime getBlockEndTime() {
		return blockEndTime;
	}

	public void setBlockEndTime(LocalTime blockEndTime) {
		this.blockEndTime = blockEndTime;
		this.blockEndTimeWrap.alreadyInitialized = true;
	}
	/** Example: 01:00 **/
	public SchoolEnrollment setBlockEndTime(String o) {
		try {
			this.blockEndTime = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockEndTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockEndTimeInit() {
		if(!blockEndTimeWrap.alreadyInitialized) {
			_blockEndTime(blockEndTimeWrap);
			if(blockEndTime == null)
				setBlockEndTime(blockEndTimeWrap.o);
		}
		blockEndTimeWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrBlockEndTime() {
		return blockEndTime == null ? null : blockEndTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
	}

	public String jsonBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlockEndTime() {
		return "end time";
	}

	public String htmTooltipBlockEndTime() {
		return null;
	}

	public String htmBlockEndTime() {
		return blockEndTime == null ? "" : StringEscapeUtils.escapeHtml4(strBlockEndTime());
	}

	public void htmBlockEndTime(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockEndTime\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockEndTime() {");
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
				r.l("				data: {\"setBlockEndTime\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockEndTime()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockEndTime\"");
							r.s(" value=\"", htmBlockEndTime(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockEndTime());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Trouver l'entité blockPricePerMonth dans Solr</a>
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
	public SchoolEnrollment setBlockPricePerMonth(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockPricePerMonth(Double o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockPricePerMonth(Integer o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockPricePerMonthInit() {
		if(!blockPricePerMonthWrap.alreadyInitialized) {
			_blockPricePerMonth(blockPricePerMonthWrap);
			if(blockPricePerMonth == null)
				setBlockPricePerMonth(blockPricePerMonthWrap.o);
		}
		blockPricePerMonthWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockPricePerMonth\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockPricePerMonth() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSunday">Trouver l'entité blockSunday dans Solr</a>
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
	public SchoolEnrollment setBlockSunday(String o) {
		this.blockSunday = Boolean.parseBoolean(o);
		this.blockSundayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockSundayInit() {
		if(!blockSundayWrap.alreadyInitialized) {
			_blockSunday(blockSundayWrap);
			if(blockSunday == null)
				setBlockSunday(blockSundayWrap.o);
		}
		blockSundayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockSunday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockSunday() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockMonday">Trouver l'entité blockMonday dans Solr</a>
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
	public SchoolEnrollment setBlockMonday(String o) {
		this.blockMonday = Boolean.parseBoolean(o);
		this.blockMondayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockMondayInit() {
		if(!blockMondayWrap.alreadyInitialized) {
			_blockMonday(blockMondayWrap);
			if(blockMonday == null)
				setBlockMonday(blockMondayWrap.o);
		}
		blockMondayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockMonday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockMonday() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTuesday">Trouver l'entité blockTuesday dans Solr</a>
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
	public SchoolEnrollment setBlockTuesday(String o) {
		this.blockTuesday = Boolean.parseBoolean(o);
		this.blockTuesdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockTuesdayInit() {
		if(!blockTuesdayWrap.alreadyInitialized) {
			_blockTuesday(blockTuesdayWrap);
			if(blockTuesday == null)
				setBlockTuesday(blockTuesdayWrap.o);
		}
		blockTuesdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockTuesday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockTuesday() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockWednesday">Trouver l'entité blockWednesday dans Solr</a>
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
	public SchoolEnrollment setBlockWednesday(String o) {
		this.blockWednesday = Boolean.parseBoolean(o);
		this.blockWednesdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockWednesdayInit() {
		if(!blockWednesdayWrap.alreadyInitialized) {
			_blockWednesday(blockWednesdayWrap);
			if(blockWednesday == null)
				setBlockWednesday(blockWednesdayWrap.o);
		}
		blockWednesdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockWednesday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockWednesday() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockThursday">Trouver l'entité blockThursday dans Solr</a>
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
	public SchoolEnrollment setBlockThursday(String o) {
		this.blockThursday = Boolean.parseBoolean(o);
		this.blockThursdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockThursdayInit() {
		if(!blockThursdayWrap.alreadyInitialized) {
			_blockThursday(blockThursdayWrap);
			if(blockThursday == null)
				setBlockThursday(blockThursdayWrap.o);
		}
		blockThursdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockThursday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockThursday() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockFriday">Trouver l'entité blockFriday dans Solr</a>
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
	public SchoolEnrollment setBlockFriday(String o) {
		this.blockFriday = Boolean.parseBoolean(o);
		this.blockFridayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockFridayInit() {
		if(!blockFridayWrap.alreadyInitialized) {
			_blockFriday(blockFridayWrap);
			if(blockFriday == null)
				setBlockFriday(blockFridayWrap.o);
		}
		blockFridayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockFriday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockFriday() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSaturday">Trouver l'entité blockSaturday dans Solr</a>
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
	public SchoolEnrollment setBlockSaturday(String o) {
		this.blockSaturday = Boolean.parseBoolean(o);
		this.blockSaturdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockSaturdayInit() {
		if(!blockSaturdayWrap.alreadyInitialized) {
			_blockSaturday(blockSaturdayWrap);
			if(blockSaturday == null)
				setBlockSaturday(blockSaturdayWrap.o);
		}
		blockSaturdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "BlockSaturday\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "BlockSaturday() {");
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

	////////////////////////
	// enrollmentApproved //
	////////////////////////

	/**	L'entité « enrollmentApproved »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentApproved;
	@JsonIgnore
	public Wrap<Boolean> enrollmentApprovedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentApproved").o(enrollmentApproved);

	/**	<br/>L'entité « enrollmentApproved »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentApproved">Trouver l'entité enrollmentApproved dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentApproved(Wrap<Boolean> c);

	public Boolean getEnrollmentApproved() {
		return enrollmentApproved;
	}

	public void setEnrollmentApproved(Boolean enrollmentApproved) {
		this.enrollmentApproved = enrollmentApproved;
		this.enrollmentApprovedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentApproved(String o) {
		this.enrollmentApproved = Boolean.parseBoolean(o);
		this.enrollmentApprovedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentApprovedInit() {
		if(!enrollmentApprovedWrap.alreadyInitialized) {
			_enrollmentApproved(enrollmentApprovedWrap);
			if(enrollmentApproved == null)
				setEnrollmentApproved(enrollmentApprovedWrap.o);
		}
		enrollmentApprovedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentApproved() {
		return enrollmentApproved;
	}

	public String strEnrollmentApproved() {
		return enrollmentApproved == null ? "" : enrollmentApproved.toString();
	}

	public String jsonEnrollmentApproved() {
		return enrollmentApproved == null ? "" : enrollmentApproved.toString();
	}

	public String nomAffichageEnrollmentApproved() {
		return "approved";
	}

	public String htmTooltipEnrollmentApproved() {
		return null;
	}

	public String htmEnrollmentApproved() {
		return enrollmentApproved == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentApproved());
	}

	public void htmEnrollmentApproved(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentApproved\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentApproved() {");
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
				r.l("				data: {\"setEnrollmentApproved\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentApproved()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentApproved\"");
							r.s(" value=\"", htmEnrollmentApproved(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentApproved());
			}
			r.l("</div>");
		}
	}

	/////////////////////////////
	// enrollmentImmunizations //
	/////////////////////////////

	/**	L'entité « enrollmentImmunizations »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentImmunizations;
	@JsonIgnore
	public Wrap<Boolean> enrollmentImmunizationsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentImmunizations").o(enrollmentImmunizations);

	/**	<br/>L'entité « enrollmentImmunizations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentImmunizations">Trouver l'entité enrollmentImmunizations dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentImmunizations(Wrap<Boolean> c);

	public Boolean getEnrollmentImmunizations() {
		return enrollmentImmunizations;
	}

	public void setEnrollmentImmunizations(Boolean enrollmentImmunizations) {
		this.enrollmentImmunizations = enrollmentImmunizations;
		this.enrollmentImmunizationsWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentImmunizations(String o) {
		this.enrollmentImmunizations = Boolean.parseBoolean(o);
		this.enrollmentImmunizationsWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentImmunizationsInit() {
		if(!enrollmentImmunizationsWrap.alreadyInitialized) {
			_enrollmentImmunizations(enrollmentImmunizationsWrap);
			if(enrollmentImmunizations == null)
				setEnrollmentImmunizations(enrollmentImmunizationsWrap.o);
		}
		enrollmentImmunizationsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentImmunizations() {
		return enrollmentImmunizations;
	}

	public String strEnrollmentImmunizations() {
		return enrollmentImmunizations == null ? "" : enrollmentImmunizations.toString();
	}

	public String jsonEnrollmentImmunizations() {
		return enrollmentImmunizations == null ? "" : enrollmentImmunizations.toString();
	}

	public String nomAffichageEnrollmentImmunizations() {
		return "immunized";
	}

	public String htmTooltipEnrollmentImmunizations() {
		return null;
	}

	public String htmEnrollmentImmunizations() {
		return enrollmentImmunizations == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentImmunizations());
	}

	public void htmEnrollmentImmunizations(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentImmunizations\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentImmunizations() {");
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
				r.l("				data: {\"setEnrollmentImmunizations\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentImmunizations()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentImmunizations\"");
							r.s(" value=\"", htmEnrollmentImmunizations(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentImmunizations());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// familyMarried //
	///////////////////

	/**	L'entité « familyMarried »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familyMarried;
	@JsonIgnore
	public Wrap<Boolean> familyMarriedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familyMarried").o(familyMarried);

	/**	<br/>L'entité « familyMarried »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyMarried">Trouver l'entité familyMarried dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyMarried(Wrap<Boolean> c);

	public Boolean getFamilyMarried() {
		return familyMarried;
	}

	public void setFamilyMarried(Boolean familyMarried) {
		this.familyMarried = familyMarried;
		this.familyMarriedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setFamilyMarried(String o) {
		this.familyMarried = Boolean.parseBoolean(o);
		this.familyMarriedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment familyMarriedInit() {
		if(!familyMarriedWrap.alreadyInitialized) {
			_familyMarried(familyMarriedWrap);
			if(familyMarried == null)
				setFamilyMarried(familyMarriedWrap.o);
		}
		familyMarriedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrFamilyMarried() {
		return familyMarried;
	}

	public String strFamilyMarried() {
		return familyMarried == null ? "" : familyMarried.toString();
	}

	public String jsonFamilyMarried() {
		return familyMarried == null ? "" : familyMarried.toString();
	}

	public String nomAffichageFamilyMarried() {
		return "parents married";
	}

	public String htmTooltipFamilyMarried() {
		return null;
	}

	public String htmFamilyMarried() {
		return familyMarried == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyMarried());
	}

	public void htmFamilyMarried(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "FamilyMarried\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "FamilyMarried() {");
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
				r.l("				data: {\"setFamilyMarried\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilyMarried()), "</span>");
				r.s("			<input");
							r.s(" name=\"familyMarried\"");
							r.s(" value=\"", htmFamilyMarried(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilyMarried());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// familySeparated //
	/////////////////////

	/**	L'entité « familySeparated »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familySeparated;
	@JsonIgnore
	public Wrap<Boolean> familySeparatedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familySeparated").o(familySeparated);

	/**	<br/>L'entité « familySeparated »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySeparated">Trouver l'entité familySeparated dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familySeparated(Wrap<Boolean> c);

	public Boolean getFamilySeparated() {
		return familySeparated;
	}

	public void setFamilySeparated(Boolean familySeparated) {
		this.familySeparated = familySeparated;
		this.familySeparatedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setFamilySeparated(String o) {
		this.familySeparated = Boolean.parseBoolean(o);
		this.familySeparatedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment familySeparatedInit() {
		if(!familySeparatedWrap.alreadyInitialized) {
			_familySeparated(familySeparatedWrap);
			if(familySeparated == null)
				setFamilySeparated(familySeparatedWrap.o);
		}
		familySeparatedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrFamilySeparated() {
		return familySeparated;
	}

	public String strFamilySeparated() {
		return familySeparated == null ? "" : familySeparated.toString();
	}

	public String jsonFamilySeparated() {
		return familySeparated == null ? "" : familySeparated.toString();
	}

	public String nomAffichageFamilySeparated() {
		return "parents separated";
	}

	public String htmTooltipFamilySeparated() {
		return null;
	}

	public String htmFamilySeparated() {
		return familySeparated == null ? "" : StringEscapeUtils.escapeHtml4(strFamilySeparated());
	}

	public void htmFamilySeparated(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "FamilySeparated\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "FamilySeparated() {");
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
				r.l("				data: {\"setFamilySeparated\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilySeparated()), "</span>");
				r.s("			<input");
							r.s(" name=\"familySeparated\"");
							r.s(" value=\"", htmFamilySeparated(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilySeparated());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// familyDivorced //
	////////////////////

	/**	L'entité « familyDivorced »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familyDivorced;
	@JsonIgnore
	public Wrap<Boolean> familyDivorcedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familyDivorced").o(familyDivorced);

	/**	<br/>L'entité « familyDivorced »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyDivorced">Trouver l'entité familyDivorced dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyDivorced(Wrap<Boolean> c);

	public Boolean getFamilyDivorced() {
		return familyDivorced;
	}

	public void setFamilyDivorced(Boolean familyDivorced) {
		this.familyDivorced = familyDivorced;
		this.familyDivorcedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setFamilyDivorced(String o) {
		this.familyDivorced = Boolean.parseBoolean(o);
		this.familyDivorcedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment familyDivorcedInit() {
		if(!familyDivorcedWrap.alreadyInitialized) {
			_familyDivorced(familyDivorcedWrap);
			if(familyDivorced == null)
				setFamilyDivorced(familyDivorcedWrap.o);
		}
		familyDivorcedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrFamilyDivorced() {
		return familyDivorced;
	}

	public String strFamilyDivorced() {
		return familyDivorced == null ? "" : familyDivorced.toString();
	}

	public String jsonFamilyDivorced() {
		return familyDivorced == null ? "" : familyDivorced.toString();
	}

	public String nomAffichageFamilyDivorced() {
		return "parents divorced";
	}

	public String htmTooltipFamilyDivorced() {
		return null;
	}

	public String htmFamilyDivorced() {
		return familyDivorced == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyDivorced());
	}

	public void htmFamilyDivorced(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "FamilyDivorced\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "FamilyDivorced() {");
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
				r.l("				data: {\"setFamilyDivorced\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilyDivorced()), "</span>");
				r.s("			<input");
							r.s(" name=\"familyDivorced\"");
							r.s(" value=\"", htmFamilyDivorced(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilyDivorced());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// familyAddress //
	///////////////////

	/**	L'entité « familyAddress »
	 *	 is defined as null before being initialized. 
	 */
	protected String familyAddress;
	@JsonIgnore
	public Wrap<String> familyAddressWrap = new Wrap<String>().p(this).c(String.class).var("familyAddress").o(familyAddress);

	/**	<br/>L'entité « familyAddress »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyAddress">Trouver l'entité familyAddress dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyAddress(Wrap<String> c);

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
		this.familyAddressWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment familyAddressInit() {
		if(!familyAddressWrap.alreadyInitialized) {
			_familyAddress(familyAddressWrap);
			if(familyAddress == null)
				setFamilyAddress(familyAddressWrap.o);
		}
		familyAddressWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrFamilyAddress() {
		return familyAddress;
	}

	public String strFamilyAddress() {
		return familyAddress == null ? "" : familyAddress;
	}

	public String jsonFamilyAddress() {
		return familyAddress == null ? "" : familyAddress;
	}

	public String nomAffichageFamilyAddress() {
		return "family address";
	}

	public String htmTooltipFamilyAddress() {
		return null;
	}

	public String htmFamilyAddress() {
		return familyAddress == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyAddress());
	}

	public void htmFamilyAddress(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "FamilyAddress\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "FamilyAddress() {");
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
				r.l("				data: {\"setFamilyAddress\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilyAddress()), "</span>");
				r.s("			<input");
							r.s(" name=\"familyAddress\"");
							r.s(" value=\"", htmFamilyAddress(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilyAddress());
			}
			r.l("</div>");
		}
	}

	/////////////////////////////////
	// familyHowDoYouKnowTheSchool //
	/////////////////////////////////

	/**	L'entité « familyHowDoYouKnowTheSchool »
	 *	 is defined as null before being initialized. 
	 */
	protected String familyHowDoYouKnowTheSchool;
	@JsonIgnore
	public Wrap<String> familyHowDoYouKnowTheSchoolWrap = new Wrap<String>().p(this).c(String.class).var("familyHowDoYouKnowTheSchool").o(familyHowDoYouKnowTheSchool);

	/**	<br/>L'entité « familyHowDoYouKnowTheSchool »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyHowDoYouKnowTheSchool">Trouver l'entité familyHowDoYouKnowTheSchool dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyHowDoYouKnowTheSchool(Wrap<String> c);

	public String getFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool;
	}

	public void setFamilyHowDoYouKnowTheSchool(String familyHowDoYouKnowTheSchool) {
		this.familyHowDoYouKnowTheSchool = familyHowDoYouKnowTheSchool;
		this.familyHowDoYouKnowTheSchoolWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment familyHowDoYouKnowTheSchoolInit() {
		if(!familyHowDoYouKnowTheSchoolWrap.alreadyInitialized) {
			_familyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchoolWrap);
			if(familyHowDoYouKnowTheSchool == null)
				setFamilyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchoolWrap.o);
		}
		familyHowDoYouKnowTheSchoolWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool;
	}

	public String strFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool == null ? "" : familyHowDoYouKnowTheSchool;
	}

	public String jsonFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool == null ? "" : familyHowDoYouKnowTheSchool;
	}

	public String nomAffichageFamilyHowDoYouKnowTheSchool() {
		return "how do you know the school? ";
	}

	public String htmTooltipFamilyHowDoYouKnowTheSchool() {
		return null;
	}

	public String htmFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyHowDoYouKnowTheSchool());
	}

	public void htmFamilyHowDoYouKnowTheSchool(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "FamilyHowDoYouKnowTheSchool\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "FamilyHowDoYouKnowTheSchool() {");
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
				r.l("				data: {\"setFamilyHowDoYouKnowTheSchool\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilyHowDoYouKnowTheSchool()), "</span>");
				r.s("			<input");
							r.s(" name=\"familyHowDoYouKnowTheSchool\"");
							r.s(" value=\"", htmFamilyHowDoYouKnowTheSchool(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilyHowDoYouKnowTheSchool());
			}
			r.l("</div>");
		}
	}

	/////////////////////////////////////
	// enrollmentSpecialConsiderations //
	/////////////////////////////////////

	/**	L'entité « enrollmentSpecialConsiderations »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSpecialConsiderations;
	@JsonIgnore
	public Wrap<String> enrollmentSpecialConsiderationsWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSpecialConsiderations").o(enrollmentSpecialConsiderations);

	/**	<br/>L'entité « enrollmentSpecialConsiderations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSpecialConsiderations">Trouver l'entité enrollmentSpecialConsiderations dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSpecialConsiderations(Wrap<String> c);

	public String getEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations;
	}

	public void setEnrollmentSpecialConsiderations(String enrollmentSpecialConsiderations) {
		this.enrollmentSpecialConsiderations = enrollmentSpecialConsiderations;
		this.enrollmentSpecialConsiderationsWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSpecialConsiderationsInit() {
		if(!enrollmentSpecialConsiderationsWrap.alreadyInitialized) {
			_enrollmentSpecialConsiderations(enrollmentSpecialConsiderationsWrap);
			if(enrollmentSpecialConsiderations == null)
				setEnrollmentSpecialConsiderations(enrollmentSpecialConsiderationsWrap.o);
		}
		enrollmentSpecialConsiderationsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations;
	}

	public String strEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations == null ? "" : enrollmentSpecialConsiderations;
	}

	public String jsonEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations == null ? "" : enrollmentSpecialConsiderations;
	}

	public String nomAffichageEnrollmentSpecialConsiderations() {
		return "special considerations";
	}

	public String htmTooltipEnrollmentSpecialConsiderations() {
		return null;
	}

	public String htmEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSpecialConsiderations());
	}

	public void htmEnrollmentSpecialConsiderations(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentSpecialConsiderations\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentSpecialConsiderations() {");
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
				r.l("				data: {\"setEnrollmentSpecialConsiderations\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentSpecialConsiderations()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentSpecialConsiderations\"");
							r.s(" value=\"", htmEnrollmentSpecialConsiderations(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentSpecialConsiderations());
			}
			r.l("</div>");
		}
	}

	/////////////////////////
	// enrollmentGroupName //
	/////////////////////////

	/**	L'entité « enrollmentGroupName »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentGroupName;
	@JsonIgnore
	public Wrap<String> enrollmentGroupNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentGroupName").o(enrollmentGroupName);

	/**	<br/>L'entité « enrollmentGroupName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroupName">Trouver l'entité enrollmentGroupName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentGroupName(Wrap<String> c);

	public String getEnrollmentGroupName() {
		return enrollmentGroupName;
	}

	public void setEnrollmentGroupName(String enrollmentGroupName) {
		this.enrollmentGroupName = enrollmentGroupName;
		this.enrollmentGroupNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentGroupNameInit() {
		if(!enrollmentGroupNameWrap.alreadyInitialized) {
			_enrollmentGroupName(enrollmentGroupNameWrap);
			if(enrollmentGroupName == null)
				setEnrollmentGroupName(enrollmentGroupNameWrap.o);
		}
		enrollmentGroupNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentGroupName() {
		return enrollmentGroupName;
	}

	public String strEnrollmentGroupName() {
		return enrollmentGroupName == null ? "" : enrollmentGroupName;
	}

	public String jsonEnrollmentGroupName() {
		return enrollmentGroupName == null ? "" : enrollmentGroupName;
	}

	public String nomAffichageEnrollmentGroupName() {
		return "group name";
	}

	public String htmTooltipEnrollmentGroupName() {
		return null;
	}

	public String htmEnrollmentGroupName() {
		return enrollmentGroupName == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentGroupName());
	}

	public void htmEnrollmentGroupName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentGroupName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentGroupName() {");
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
				r.l("				data: {\"setEnrollmentGroupName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentGroupName()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentGroupName\"");
							r.s(" value=\"", htmEnrollmentGroupName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentGroupName());
			}
			r.l("</div>");
		}
	}

	////////////////////////////////
	// enrollmentPaymentEachMonth //
	////////////////////////////////

	/**	L'entité « enrollmentPaymentEachMonth »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentPaymentEachMonth;
	@JsonIgnore
	public Wrap<Boolean> enrollmentPaymentEachMonthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentPaymentEachMonth").o(enrollmentPaymentEachMonth);

	/**	<br/>L'entité « enrollmentPaymentEachMonth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPaymentEachMonth">Trouver l'entité enrollmentPaymentEachMonth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentPaymentEachMonth(Wrap<Boolean> c);

	public Boolean getEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth;
	}

	public void setEnrollmentPaymentEachMonth(Boolean enrollmentPaymentEachMonth) {
		this.enrollmentPaymentEachMonth = enrollmentPaymentEachMonth;
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentPaymentEachMonth(String o) {
		this.enrollmentPaymentEachMonth = Boolean.parseBoolean(o);
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentPaymentEachMonthInit() {
		if(!enrollmentPaymentEachMonthWrap.alreadyInitialized) {
			_enrollmentPaymentEachMonth(enrollmentPaymentEachMonthWrap);
			if(enrollmentPaymentEachMonth == null)
				setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonthWrap.o);
		}
		enrollmentPaymentEachMonthWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth;
	}

	public String strEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth == null ? "" : enrollmentPaymentEachMonth.toString();
	}

	public String jsonEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth == null ? "" : enrollmentPaymentEachMonth.toString();
	}

	public String nomAffichageEnrollmentPaymentEachMonth() {
		return "payment each month";
	}

	public String htmTooltipEnrollmentPaymentEachMonth() {
		return null;
	}

	public String htmEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentPaymentEachMonth());
	}

	public void htmEnrollmentPaymentEachMonth(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentPaymentEachMonth\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentPaymentEachMonth() {");
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
				r.l("				data: {\"setEnrollmentPaymentEachMonth\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentPaymentEachMonth()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentPaymentEachMonth\"");
							r.s(" value=\"", htmEnrollmentPaymentEachMonth(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentPaymentEachMonth());
			}
			r.l("</div>");
		}
	}

	///////////////////////////////
	// enrollmentPaymentComplete //
	///////////////////////////////

	/**	L'entité « enrollmentPaymentComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentPaymentComplete;
	@JsonIgnore
	public Wrap<Boolean> enrollmentPaymentCompleteWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentPaymentComplete").o(enrollmentPaymentComplete);

	/**	<br/>L'entité « enrollmentPaymentComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPaymentComplete">Trouver l'entité enrollmentPaymentComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentPaymentComplete(Wrap<Boolean> c);

	public Boolean getEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete;
	}

	public void setEnrollmentPaymentComplete(Boolean enrollmentPaymentComplete) {
		this.enrollmentPaymentComplete = enrollmentPaymentComplete;
		this.enrollmentPaymentCompleteWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentPaymentComplete(String o) {
		this.enrollmentPaymentComplete = Boolean.parseBoolean(o);
		this.enrollmentPaymentCompleteWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentPaymentCompleteInit() {
		if(!enrollmentPaymentCompleteWrap.alreadyInitialized) {
			_enrollmentPaymentComplete(enrollmentPaymentCompleteWrap);
			if(enrollmentPaymentComplete == null)
				setEnrollmentPaymentComplete(enrollmentPaymentCompleteWrap.o);
		}
		enrollmentPaymentCompleteWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete;
	}

	public String strEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete == null ? "" : enrollmentPaymentComplete.toString();
	}

	public String jsonEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete == null ? "" : enrollmentPaymentComplete.toString();
	}

	public String nomAffichageEnrollmentPaymentComplete() {
		return "complete payment";
	}

	public String htmTooltipEnrollmentPaymentComplete() {
		return null;
	}

	public String htmEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentPaymentComplete());
	}

	public void htmEnrollmentPaymentComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentPaymentComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentPaymentComplete() {");
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
				r.l("				data: {\"setEnrollmentPaymentComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentPaymentComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentPaymentComplete\"");
							r.s(" value=\"", htmEnrollmentPaymentComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentPaymentComplete());
			}
			r.l("</div>");
		}
	}

	////////////////////////////
	// enrollmentCompleteName //
	////////////////////////////

	/**	L'entité « enrollmentCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentCompleteName;
	@JsonIgnore
	public Wrap<String> enrollmentCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentCompleteName").o(enrollmentCompleteName);

	/**	<br/>L'entité « enrollmentCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentCompleteName">Trouver l'entité enrollmentCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentCompleteName(Wrap<String> c);

	public String getEnrollmentCompleteName() {
		return enrollmentCompleteName;
	}

	public void setEnrollmentCompleteName(String enrollmentCompleteName) {
		this.enrollmentCompleteName = enrollmentCompleteName;
		this.enrollmentCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentCompleteNameInit() {
		if(!enrollmentCompleteNameWrap.alreadyInitialized) {
			_enrollmentCompleteName(enrollmentCompleteNameWrap);
			if(enrollmentCompleteName == null)
				setEnrollmentCompleteName(enrollmentCompleteNameWrap.o);
		}
		enrollmentCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentCompleteName() {
		return enrollmentCompleteName;
	}

	public String strEnrollmentCompleteName() {
		return enrollmentCompleteName == null ? "" : enrollmentCompleteName;
	}

	public String jsonEnrollmentCompleteName() {
		return enrollmentCompleteName == null ? "" : enrollmentCompleteName;
	}

	public String nomAffichageEnrollmentCompleteName() {
		return "name";
	}

	public String htmTooltipEnrollmentCompleteName() {
		return null;
	}

	public String htmEnrollmentCompleteName() {
		return enrollmentCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentCompleteName());
	}

	public void htmEnrollmentCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentCompleteName() {");
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
				r.l("				data: {\"setEnrollmentCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentCompleteName\"");
							r.s(" value=\"", htmEnrollmentCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentCompleteName());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// enrollmentId //
	//////////////////

	/**	L'entité « enrollmentId »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentId;
	@JsonIgnore
	public Wrap<String> enrollmentIdWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentId").o(enrollmentId);

	/**	<br/>L'entité « enrollmentId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentId">Trouver l'entité enrollmentId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentId(Wrap<String> c);

	public String getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(String enrollmentId) {
		this.enrollmentId = enrollmentId;
		this.enrollmentIdWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentIdInit() {
		if(!enrollmentIdWrap.alreadyInitialized) {
			_enrollmentId(enrollmentIdWrap);
			if(enrollmentId == null)
				setEnrollmentId(enrollmentIdWrap.o);
		}
		enrollmentIdWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentId() {
		return enrollmentId;
	}

	public String strEnrollmentId() {
		return enrollmentId == null ? "" : enrollmentId;
	}

	public String jsonEnrollmentId() {
		return enrollmentId == null ? "" : enrollmentId;
	}

	public String nomAffichageEnrollmentId() {
		return "ID";
	}

	public String htmTooltipEnrollmentId() {
		return null;
	}

	public String htmEnrollmentId() {
		return enrollmentId == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentId());
	}

	public void htmEnrollmentId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "EnrollmentId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "EnrollmentId() {");
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
				r.l("				data: {\"setEnrollmentId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentId()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentId\"");
							r.s(" value=\"", htmEnrollmentId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentId());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected SchoolEnrollment pageUrlInit() {
		if(!pageUrlWrap.alreadyInitialized) {
			_pageUrl(pageUrlWrap);
			if(pageUrl == null)
				setPageUrl(pageUrlWrap.o);
		}
		pageUrlWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "PageUrl\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "PageUrl() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggest">Trouver l'entité objectSuggest dans Solr</a>
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
	protected SchoolEnrollment objectSuggestInit() {
		if(!objectSuggestWrap.alreadyInitialized) {
			_objectSuggest(objectSuggestWrap);
			if(objectSuggest == null)
				setObjectSuggest(objectSuggestWrap.o);
		}
		objectSuggestWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
			r.s("<div id=\"patchSchoolEnrollment", strPk(), "ObjectSuggest\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolEnrollment", strPk(), "ObjectSuggest() {");
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

	protected boolean alreadyInitializedSchoolEnrollment = false;

	public SchoolEnrollment initDeepSchoolEnrollment(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolEnrollment) {
			alreadyInitializedSchoolEnrollment = true;
			initDeepSchoolEnrollment();
		}
		return (SchoolEnrollment)this;
	}

	public void initDeepSchoolEnrollment() {
		super.initDeepCluster(siteRequest_);
		initSchoolEnrollment();
	}

	public void initSchoolEnrollment() {
		enrollmentKeyInit();
		blockKeysInit();
		blockSearchInit();
		blockInit();
		schoolKeyInit();
		anneeCleInit();
		seasonKeyInit();
		sessionKeyInit();
		ageKeyInit();
		blockKeyInit();
		childKeyInit();
		momKeysInit();
		dadKeysInit();
		guardianKeysInit();
		paymentKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		sessionSortInit();
		ageSortInit();
		childSearchInit();
		child_Init();
		childCompleteNameInit();
		schoolCompleteNameInit();
		yearStartInit();
		yearEndInit();
		seasonStartDateInit();
		seasonSummerInit();
		seasonWinterInit();
		seasonEnrollmentFeeInit();
		seasonCompleteNameInit();
		sessionStartDayInit();
		sessionEndDayInit();
		ageCompleteNameInit();
		ageStartInit();
		ageEndInit();
		blockStartTimeInit();
		blockEndTimeInit();
		blockPricePerMonthInit();
		blockSundayInit();
		blockMondayInit();
		blockTuesdayInit();
		blockWednesdayInit();
		blockThursdayInit();
		blockFridayInit();
		blockSaturdayInit();
		enrollmentApprovedInit();
		enrollmentImmunizationsInit();
		familyMarriedInit();
		familySeparatedInit();
		familyDivorcedInit();
		familyAddressInit();
		familyHowDoYouKnowTheSchoolInit();
		enrollmentSpecialConsiderationsInit();
		enrollmentGroupNameInit();
		enrollmentPaymentEachMonthInit();
		enrollmentPaymentCompleteInit();
		enrollmentCompleteNameInit();
		enrollmentIdInit();
		pageUrlInit();
		objectSuggestInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolEnrollment(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolEnrollment(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(blockSearch != null)
			blockSearch.setSiteRequest_(siteRequest_);
		if(block != null)
			block.setSiteRequest_(siteRequest_);
		if(childSearch != null)
			childSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolEnrollment(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolEnrollment(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolEnrollment(String var) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;
		switch(var) {
			case "enrollmentKey":
				return oSchoolEnrollment.enrollmentKey;
			case "blockKeys":
				return oSchoolEnrollment.blockKeys;
			case "blockSearch":
				return oSchoolEnrollment.blockSearch;
			case "block":
				return oSchoolEnrollment.block;
			case "schoolKey":
				return oSchoolEnrollment.schoolKey;
			case "anneeCle":
				return oSchoolEnrollment.anneeCle;
			case "seasonKey":
				return oSchoolEnrollment.seasonKey;
			case "sessionKey":
				return oSchoolEnrollment.sessionKey;
			case "ageKey":
				return oSchoolEnrollment.ageKey;
			case "blockKey":
				return oSchoolEnrollment.blockKey;
			case "childKey":
				return oSchoolEnrollment.childKey;
			case "momKeys":
				return oSchoolEnrollment.momKeys;
			case "dadKeys":
				return oSchoolEnrollment.dadKeys;
			case "guardianKeys":
				return oSchoolEnrollment.guardianKeys;
			case "paymentKeys":
				return oSchoolEnrollment.paymentKeys;
			case "educationSort":
				return oSchoolEnrollment.educationSort;
			case "schoolSort":
				return oSchoolEnrollment.schoolSort;
			case "yearSort":
				return oSchoolEnrollment.yearSort;
			case "seasonSort":
				return oSchoolEnrollment.seasonSort;
			case "sessionSort":
				return oSchoolEnrollment.sessionSort;
			case "ageSort":
				return oSchoolEnrollment.ageSort;
			case "childSearch":
				return oSchoolEnrollment.childSearch;
			case "child_":
				return oSchoolEnrollment.child_;
			case "childCompleteName":
				return oSchoolEnrollment.childCompleteName;
			case "schoolCompleteName":
				return oSchoolEnrollment.schoolCompleteName;
			case "yearStart":
				return oSchoolEnrollment.yearStart;
			case "yearEnd":
				return oSchoolEnrollment.yearEnd;
			case "seasonStartDate":
				return oSchoolEnrollment.seasonStartDate;
			case "seasonSummer":
				return oSchoolEnrollment.seasonSummer;
			case "seasonWinter":
				return oSchoolEnrollment.seasonWinter;
			case "seasonEnrollmentFee":
				return oSchoolEnrollment.seasonEnrollmentFee;
			case "seasonCompleteName":
				return oSchoolEnrollment.seasonCompleteName;
			case "sessionStartDay":
				return oSchoolEnrollment.sessionStartDay;
			case "sessionEndDay":
				return oSchoolEnrollment.sessionEndDay;
			case "ageCompleteName":
				return oSchoolEnrollment.ageCompleteName;
			case "ageStart":
				return oSchoolEnrollment.ageStart;
			case "ageEnd":
				return oSchoolEnrollment.ageEnd;
			case "blockStartTime":
				return oSchoolEnrollment.blockStartTime;
			case "blockEndTime":
				return oSchoolEnrollment.blockEndTime;
			case "blockPricePerMonth":
				return oSchoolEnrollment.blockPricePerMonth;
			case "blockSunday":
				return oSchoolEnrollment.blockSunday;
			case "blockMonday":
				return oSchoolEnrollment.blockMonday;
			case "blockTuesday":
				return oSchoolEnrollment.blockTuesday;
			case "blockWednesday":
				return oSchoolEnrollment.blockWednesday;
			case "blockThursday":
				return oSchoolEnrollment.blockThursday;
			case "blockFriday":
				return oSchoolEnrollment.blockFriday;
			case "blockSaturday":
				return oSchoolEnrollment.blockSaturday;
			case "enrollmentApproved":
				return oSchoolEnrollment.enrollmentApproved;
			case "enrollmentImmunizations":
				return oSchoolEnrollment.enrollmentImmunizations;
			case "familyMarried":
				return oSchoolEnrollment.familyMarried;
			case "familySeparated":
				return oSchoolEnrollment.familySeparated;
			case "familyDivorced":
				return oSchoolEnrollment.familyDivorced;
			case "familyAddress":
				return oSchoolEnrollment.familyAddress;
			case "familyHowDoYouKnowTheSchool":
				return oSchoolEnrollment.familyHowDoYouKnowTheSchool;
			case "enrollmentSpecialConsiderations":
				return oSchoolEnrollment.enrollmentSpecialConsiderations;
			case "enrollmentGroupName":
				return oSchoolEnrollment.enrollmentGroupName;
			case "enrollmentPaymentEachMonth":
				return oSchoolEnrollment.enrollmentPaymentEachMonth;
			case "enrollmentPaymentComplete":
				return oSchoolEnrollment.enrollmentPaymentComplete;
			case "enrollmentCompleteName":
				return oSchoolEnrollment.enrollmentCompleteName;
			case "enrollmentId":
				return oSchoolEnrollment.enrollmentId;
			case "pageUrl":
				return oSchoolEnrollment.pageUrl;
			case "objectSuggest":
				return oSchoolEnrollment.objectSuggest;
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
				o = attributeSchoolEnrollment(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolEnrollment(String var, Object val) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;
		switch(var) {
			case "blockKeys":
				oSchoolEnrollment.addBlockKeys((Long)val);
				return val;
			case "childKey":
				oSchoolEnrollment.setChildKey((Long)val);
				return val;
			case "momKeys":
				oSchoolEnrollment.addMomKeys((Long)val);
				return val;
			case "dadKeys":
				oSchoolEnrollment.addDadKeys((Long)val);
				return val;
			case "guardianKeys":
				oSchoolEnrollment.addGuardianKeys((Long)val);
				return val;
			case "paymentKeys":
				oSchoolEnrollment.addPaymentKeys((Long)val);
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
					o = defineSchoolEnrollment(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolEnrollment(String var, String val) {
		switch(var) {
			case "enrollmentApproved":
				setEnrollmentApproved(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentImmunizations":
				setEnrollmentImmunizations(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyMarried":
				setFamilyMarried(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familySeparated":
				setFamilySeparated(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyDivorced":
				setFamilyDivorced(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyAddress":
				setFamilyAddress(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyHowDoYouKnowTheSchool":
				setFamilyHowDoYouKnowTheSchool(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSpecialConsiderations":
				setEnrollmentSpecialConsiderations(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentGroupName":
				setEnrollmentGroupName(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentPaymentEachMonth":
				setEnrollmentPaymentEachMonth(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentPaymentComplete":
				setEnrollmentPaymentComplete(val);
				savesSchoolEnrollment.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolEnrollment = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolEnrollment(solrDocument);
	}
	public void populateSchoolEnrollment(SolrDocument solrDocument) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;
		savesSchoolEnrollment = (List<String>)solrDocument.get("savesSchoolEnrollment_stored_strings");
		if(savesSchoolEnrollment != null) {

			if(savesSchoolEnrollment.contains("enrollmentKey")) {
				Long enrollmentKey = (Long)solrDocument.get("enrollmentKey_stored_long");
				if(enrollmentKey != null)
					oSchoolEnrollment.setEnrollmentKey(enrollmentKey);
			}

			List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
			if(blockKeys != null)
				oSchoolEnrollment.blockKeys.addAll(blockKeys);

			if(savesSchoolEnrollment.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolEnrollment.setSchoolKey(schoolKey);
			}

			if(savesSchoolEnrollment.contains("seasonKey")) {
				Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
				if(seasonKey != null)
					oSchoolEnrollment.setSeasonKey(seasonKey);
			}

			if(savesSchoolEnrollment.contains("sessionKey")) {
				Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
				if(sessionKey != null)
					oSchoolEnrollment.setSessionKey(sessionKey);
			}

			if(savesSchoolEnrollment.contains("ageKey")) {
				Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
				if(ageKey != null)
					oSchoolEnrollment.setAgeKey(ageKey);
			}

			if(savesSchoolEnrollment.contains("blockKey")) {
				Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
				if(blockKey != null)
					oSchoolEnrollment.setBlockKey(blockKey);
			}

			Long childKey = (Long)solrDocument.get("childKey_stored_long");
			if(childKey != null)
				oSchoolEnrollment.setChildKey(childKey);

			List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
			if(momKeys != null)
				oSchoolEnrollment.momKeys.addAll(momKeys);

			List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
			if(dadKeys != null)
				oSchoolEnrollment.dadKeys.addAll(dadKeys);

			List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
			if(guardianKeys != null)
				oSchoolEnrollment.guardianKeys.addAll(guardianKeys);

			List<Long> paymentKeys = (List<Long>)solrDocument.get("paymentKeys_stored_longs");
			if(paymentKeys != null)
				oSchoolEnrollment.paymentKeys.addAll(paymentKeys);

			if(savesSchoolEnrollment.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolEnrollment.setEducationSort(educationSort);
			}

			if(savesSchoolEnrollment.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolEnrollment.setSchoolSort(schoolSort);
			}

			if(savesSchoolEnrollment.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolEnrollment.setYearSort(yearSort);
			}

			if(savesSchoolEnrollment.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolEnrollment.setSeasonSort(seasonSort);
			}

			if(savesSchoolEnrollment.contains("sessionSort")) {
				Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
				if(sessionSort != null)
					oSchoolEnrollment.setSessionSort(sessionSort);
			}

			if(savesSchoolEnrollment.contains("ageSort")) {
				Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
				if(ageSort != null)
					oSchoolEnrollment.setAgeSort(ageSort);
			}

			if(savesSchoolEnrollment.contains("childCompleteName")) {
				String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
				if(childCompleteName != null)
					oSchoolEnrollment.setChildCompleteName(childCompleteName);
			}

			if(savesSchoolEnrollment.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolEnrollment.setSchoolCompleteName(schoolCompleteName);
			}

			if(savesSchoolEnrollment.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolEnrollment.setYearStart(yearStart);
			}

			if(savesSchoolEnrollment.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolEnrollment.setYearEnd(yearEnd);
			}

			if(savesSchoolEnrollment.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolEnrollment.setSeasonStartDate(seasonStartDate);
			}

			if(savesSchoolEnrollment.contains("seasonSummer")) {
				Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
				if(seasonSummer != null)
					oSchoolEnrollment.setSeasonSummer(seasonSummer);
			}

			if(savesSchoolEnrollment.contains("seasonWinter")) {
				Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
				if(seasonWinter != null)
					oSchoolEnrollment.setSeasonWinter(seasonWinter);
			}

			if(savesSchoolEnrollment.contains("seasonEnrollmentFee")) {
				Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
				if(seasonEnrollmentFee != null)
					oSchoolEnrollment.setSeasonEnrollmentFee(seasonEnrollmentFee);
			}

			if(savesSchoolEnrollment.contains("seasonCompleteName")) {
				String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
				if(seasonCompleteName != null)
					oSchoolEnrollment.setSeasonCompleteName(seasonCompleteName);
			}

			if(savesSchoolEnrollment.contains("sessionStartDay")) {
				Date sessionStartDay = (Date)solrDocument.get("sessionStartDay_stored_date");
				if(sessionStartDay != null)
					oSchoolEnrollment.setSessionStartDay(sessionStartDay);
			}

			if(savesSchoolEnrollment.contains("sessionEndDay")) {
				Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
				if(sessionEndDay != null)
					oSchoolEnrollment.setSessionEndDay(sessionEndDay);
			}

			if(savesSchoolEnrollment.contains("ageCompleteName")) {
				String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
				if(ageCompleteName != null)
					oSchoolEnrollment.setAgeCompleteName(ageCompleteName);
			}

			if(savesSchoolEnrollment.contains("ageStart")) {
				Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
				if(ageStart != null)
					oSchoolEnrollment.setAgeStart(ageStart);
			}

			if(savesSchoolEnrollment.contains("ageEnd")) {
				Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
				if(ageEnd != null)
					oSchoolEnrollment.setAgeEnd(ageEnd);
			}

			if(savesSchoolEnrollment.contains("blockStartTime")) {
				String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
				if(blockStartTime != null)
					oSchoolEnrollment.setBlockStartTime(blockStartTime);
			}

			if(savesSchoolEnrollment.contains("blockEndTime")) {
				String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
				if(blockEndTime != null)
					oSchoolEnrollment.setBlockEndTime(blockEndTime);
			}

			if(savesSchoolEnrollment.contains("blockPricePerMonth")) {
				Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
				if(blockPricePerMonth != null)
					oSchoolEnrollment.setBlockPricePerMonth(blockPricePerMonth);
			}

			if(savesSchoolEnrollment.contains("blockSunday")) {
				Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
				if(blockSunday != null)
					oSchoolEnrollment.setBlockSunday(blockSunday);
			}

			if(savesSchoolEnrollment.contains("blockMonday")) {
				Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
				if(blockMonday != null)
					oSchoolEnrollment.setBlockMonday(blockMonday);
			}

			if(savesSchoolEnrollment.contains("blockTuesday")) {
				Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
				if(blockTuesday != null)
					oSchoolEnrollment.setBlockTuesday(blockTuesday);
			}

			if(savesSchoolEnrollment.contains("blockWednesday")) {
				Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
				if(blockWednesday != null)
					oSchoolEnrollment.setBlockWednesday(blockWednesday);
			}

			if(savesSchoolEnrollment.contains("blockThursday")) {
				Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
				if(blockThursday != null)
					oSchoolEnrollment.setBlockThursday(blockThursday);
			}

			if(savesSchoolEnrollment.contains("blockFriday")) {
				Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
				if(blockFriday != null)
					oSchoolEnrollment.setBlockFriday(blockFriday);
			}

			if(savesSchoolEnrollment.contains("blockSaturday")) {
				Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
				if(blockSaturday != null)
					oSchoolEnrollment.setBlockSaturday(blockSaturday);
			}

			if(savesSchoolEnrollment.contains("enrollmentApproved")) {
				Boolean enrollmentApproved = (Boolean)solrDocument.get("enrollmentApproved_stored_boolean");
				if(enrollmentApproved != null)
					oSchoolEnrollment.setEnrollmentApproved(enrollmentApproved);
			}

			if(savesSchoolEnrollment.contains("enrollmentImmunizations")) {
				Boolean enrollmentImmunizations = (Boolean)solrDocument.get("enrollmentImmunizations_stored_boolean");
				if(enrollmentImmunizations != null)
					oSchoolEnrollment.setEnrollmentImmunizations(enrollmentImmunizations);
			}

			if(savesSchoolEnrollment.contains("familyMarried")) {
				Boolean familyMarried = (Boolean)solrDocument.get("familyMarried_stored_boolean");
				if(familyMarried != null)
					oSchoolEnrollment.setFamilyMarried(familyMarried);
			}

			if(savesSchoolEnrollment.contains("familySeparated")) {
				Boolean familySeparated = (Boolean)solrDocument.get("familySeparated_stored_boolean");
				if(familySeparated != null)
					oSchoolEnrollment.setFamilySeparated(familySeparated);
			}

			if(savesSchoolEnrollment.contains("familyDivorced")) {
				Boolean familyDivorced = (Boolean)solrDocument.get("familyDivorced_stored_boolean");
				if(familyDivorced != null)
					oSchoolEnrollment.setFamilyDivorced(familyDivorced);
			}

			if(savesSchoolEnrollment.contains("familyAddress")) {
				String familyAddress = (String)solrDocument.get("familyAddress_stored_string");
				if(familyAddress != null)
					oSchoolEnrollment.setFamilyAddress(familyAddress);
			}

			if(savesSchoolEnrollment.contains("familyHowDoYouKnowTheSchool")) {
				String familyHowDoYouKnowTheSchool = (String)solrDocument.get("familyHowDoYouKnowTheSchool_stored_string");
				if(familyHowDoYouKnowTheSchool != null)
					oSchoolEnrollment.setFamilyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchool);
			}

			if(savesSchoolEnrollment.contains("enrollmentSpecialConsiderations")) {
				String enrollmentSpecialConsiderations = (String)solrDocument.get("enrollmentSpecialConsiderations_stored_string");
				if(enrollmentSpecialConsiderations != null)
					oSchoolEnrollment.setEnrollmentSpecialConsiderations(enrollmentSpecialConsiderations);
			}

			if(savesSchoolEnrollment.contains("enrollmentGroupName")) {
				String enrollmentGroupName = (String)solrDocument.get("enrollmentGroupName_stored_string");
				if(enrollmentGroupName != null)
					oSchoolEnrollment.setEnrollmentGroupName(enrollmentGroupName);
			}

			if(savesSchoolEnrollment.contains("enrollmentPaymentEachMonth")) {
				Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
				if(enrollmentPaymentEachMonth != null)
					oSchoolEnrollment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);
			}

			if(savesSchoolEnrollment.contains("enrollmentPaymentComplete")) {
				Boolean enrollmentPaymentComplete = (Boolean)solrDocument.get("enrollmentPaymentComplete_stored_boolean");
				if(enrollmentPaymentComplete != null)
					oSchoolEnrollment.setEnrollmentPaymentComplete(enrollmentPaymentComplete);
			}

			if(savesSchoolEnrollment.contains("enrollmentCompleteName")) {
				String enrollmentCompleteName = (String)solrDocument.get("enrollmentCompleteName_stored_string");
				if(enrollmentCompleteName != null)
					oSchoolEnrollment.setEnrollmentCompleteName(enrollmentCompleteName);
			}

			if(savesSchoolEnrollment.contains("enrollmentId")) {
				String enrollmentId = (String)solrDocument.get("enrollmentId_stored_string");
				if(enrollmentId != null)
					oSchoolEnrollment.setEnrollmentId(enrollmentId);
			}

			if(savesSchoolEnrollment.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oSchoolEnrollment.setPageUrl(pageUrl);
			}

			if(savesSchoolEnrollment.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
				oSchoolEnrollment.setObjectSuggest(objectSuggest);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.enrollment.SchoolEnrollment"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolEnrollment o = new SchoolEnrollment();
			o.siteRequestSchoolEnrollment(siteRequest);
			o.initDeepSchoolEnrollment(siteRequest);
			o.indexSchoolEnrollment();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolEnrollment();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolEnrollment(document);
	}

	public void indexSchoolEnrollment(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolEnrollment(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolEnrollment() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolEnrollment(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolEnrollment(SolrInputDocument document) {
		if(savesSchoolEnrollment != null)
			document.addField("savesSchoolEnrollment_stored_strings", savesSchoolEnrollment);

		if(enrollmentKey != null) {
			document.addField("enrollmentKey_indexed_long", enrollmentKey);
			document.addField("enrollmentKey_stored_long", enrollmentKey);
		}
		if(blockKeys != null) {
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_indexed_longs", o);
			}
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_stored_longs", o);
			}
		}
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
		if(childKey != null) {
			document.addField("childKey_indexed_long", childKey);
			document.addField("childKey_stored_long", childKey);
		}
		if(momKeys != null) {
			for(java.lang.Long o : momKeys) {
				document.addField("momKeys_indexed_longs", o);
			}
			for(java.lang.Long o : momKeys) {
				document.addField("momKeys_stored_longs", o);
			}
		}
		if(dadKeys != null) {
			for(java.lang.Long o : dadKeys) {
				document.addField("dadKeys_indexed_longs", o);
			}
			for(java.lang.Long o : dadKeys) {
				document.addField("dadKeys_stored_longs", o);
			}
		}
		if(guardianKeys != null) {
			for(java.lang.Long o : guardianKeys) {
				document.addField("guardianKeys_indexed_longs", o);
			}
			for(java.lang.Long o : guardianKeys) {
				document.addField("guardianKeys_stored_longs", o);
			}
		}
		if(paymentKeys != null) {
			for(java.lang.Long o : paymentKeys) {
				document.addField("paymentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : paymentKeys) {
				document.addField("paymentKeys_stored_longs", o);
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
		if(childCompleteName != null) {
			document.addField("childCompleteName_indexed_string", childCompleteName);
			document.addField("childCompleteName_stored_string", childCompleteName);
		}
		if(schoolCompleteName != null) {
			document.addField("schoolCompleteName_indexed_string", schoolCompleteName);
			document.addField("schoolCompleteName_stored_string", schoolCompleteName);
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_int", yearStart);
			document.addField("yearStart_stored_int", yearStart);
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_int", yearEnd);
			document.addField("yearEnd_stored_int", yearEnd);
		}
		if(seasonStartDate != null) {
			document.addField("seasonStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
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
		if(seasonCompleteName != null) {
			document.addField("seasonCompleteName_indexed_string", seasonCompleteName);
			document.addField("seasonCompleteName_stored_string", seasonCompleteName);
		}
		if(sessionStartDay != null) {
			document.addField("sessionStartDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionStartDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionEndDay != null) {
			document.addField("sessionEndDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionEndDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(ageCompleteName != null) {
			document.addField("ageCompleteName_indexed_string", ageCompleteName);
			document.addField("ageCompleteName_stored_string", ageCompleteName);
		}
		if(ageStart != null) {
			document.addField("ageStart_indexed_int", ageStart);
			document.addField("ageStart_stored_int", ageStart);
		}
		if(ageEnd != null) {
			document.addField("ageEnd_indexed_int", ageEnd);
			document.addField("ageEnd_stored_int", ageEnd);
		}
		if(blockStartTime != null) {
			document.addField("blockStartTime_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockStartTime_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
		}
		if(blockEndTime != null) {
			document.addField("blockEndTime_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockEndTime_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
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
		if(enrollmentApproved != null) {
			document.addField("enrollmentApproved_indexed_boolean", enrollmentApproved);
			document.addField("enrollmentApproved_stored_boolean", enrollmentApproved);
		}
		if(enrollmentImmunizations != null) {
			document.addField("enrollmentImmunizations_indexed_boolean", enrollmentImmunizations);
			document.addField("enrollmentImmunizations_stored_boolean", enrollmentImmunizations);
		}
		if(familyMarried != null) {
			document.addField("familyMarried_indexed_boolean", familyMarried);
			document.addField("familyMarried_stored_boolean", familyMarried);
		}
		if(familySeparated != null) {
			document.addField("familySeparated_indexed_boolean", familySeparated);
			document.addField("familySeparated_stored_boolean", familySeparated);
		}
		if(familyDivorced != null) {
			document.addField("familyDivorced_indexed_boolean", familyDivorced);
			document.addField("familyDivorced_stored_boolean", familyDivorced);
		}
		if(familyAddress != null) {
			document.addField("familyAddress_indexed_string", familyAddress);
			document.addField("familyAddress_stored_string", familyAddress);
		}
		if(familyHowDoYouKnowTheSchool != null) {
			document.addField("familyHowDoYouKnowTheSchool_indexed_string", familyHowDoYouKnowTheSchool);
			document.addField("familyHowDoYouKnowTheSchool_stored_string", familyHowDoYouKnowTheSchool);
		}
		if(enrollmentSpecialConsiderations != null) {
			document.addField("enrollmentSpecialConsiderations_indexed_string", enrollmentSpecialConsiderations);
			document.addField("enrollmentSpecialConsiderations_stored_string", enrollmentSpecialConsiderations);
		}
		if(enrollmentGroupName != null) {
			document.addField("enrollmentGroupName_indexed_string", enrollmentGroupName);
			document.addField("enrollmentGroupName_stored_string", enrollmentGroupName);
		}
		if(enrollmentPaymentEachMonth != null) {
			document.addField("enrollmentPaymentEachMonth_indexed_boolean", enrollmentPaymentEachMonth);
			document.addField("enrollmentPaymentEachMonth_stored_boolean", enrollmentPaymentEachMonth);
		}
		if(enrollmentPaymentComplete != null) {
			document.addField("enrollmentPaymentComplete_indexed_boolean", enrollmentPaymentComplete);
			document.addField("enrollmentPaymentComplete_stored_boolean", enrollmentPaymentComplete);
		}
		if(enrollmentCompleteName != null) {
			document.addField("enrollmentCompleteName_indexed_string", enrollmentCompleteName);
			document.addField("enrollmentCompleteName_stored_string", enrollmentCompleteName);
		}
		if(enrollmentId != null) {
			document.addField("enrollmentId_indexed_string", enrollmentId);
			document.addField("enrollmentId_stored_string", enrollmentId);
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

	public void unindexSchoolEnrollment() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolEnrollment(siteRequest);
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
		storeSchoolEnrollment(solrDocument);
	}
	public void storeSchoolEnrollment(SolrDocument solrDocument) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;

		Long enrollmentKey = (Long)solrDocument.get("enrollmentKey_stored_long");
		if(enrollmentKey != null)
			oSchoolEnrollment.setEnrollmentKey(enrollmentKey);

		List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
		if(blockKeys != null)
			oSchoolEnrollment.blockKeys.addAll(blockKeys);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolEnrollment.setSchoolKey(schoolKey);

		Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
		if(seasonKey != null)
			oSchoolEnrollment.setSeasonKey(seasonKey);

		Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
		if(sessionKey != null)
			oSchoolEnrollment.setSessionKey(sessionKey);

		Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
		if(ageKey != null)
			oSchoolEnrollment.setAgeKey(ageKey);

		Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
		if(blockKey != null)
			oSchoolEnrollment.setBlockKey(blockKey);

		Long childKey = (Long)solrDocument.get("childKey_stored_long");
		if(childKey != null)
			oSchoolEnrollment.setChildKey(childKey);

		List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
		if(momKeys != null)
			oSchoolEnrollment.momKeys.addAll(momKeys);

		List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
		if(dadKeys != null)
			oSchoolEnrollment.dadKeys.addAll(dadKeys);

		List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
		if(guardianKeys != null)
			oSchoolEnrollment.guardianKeys.addAll(guardianKeys);

		List<Long> paymentKeys = (List<Long>)solrDocument.get("paymentKeys_stored_longs");
		if(paymentKeys != null)
			oSchoolEnrollment.paymentKeys.addAll(paymentKeys);

		Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
		if(educationSort != null)
			oSchoolEnrollment.setEducationSort(educationSort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolEnrollment.setSchoolSort(schoolSort);

		Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
		if(yearSort != null)
			oSchoolEnrollment.setYearSort(yearSort);

		Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
		if(seasonSort != null)
			oSchoolEnrollment.setSeasonSort(seasonSort);

		Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
		if(sessionSort != null)
			oSchoolEnrollment.setSessionSort(sessionSort);

		Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
		if(ageSort != null)
			oSchoolEnrollment.setAgeSort(ageSort);

		String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
		if(childCompleteName != null)
			oSchoolEnrollment.setChildCompleteName(childCompleteName);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oSchoolEnrollment.setSchoolCompleteName(schoolCompleteName);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolEnrollment.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolEnrollment.setYearEnd(yearEnd);

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolEnrollment.setSeasonStartDate(seasonStartDate);

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolEnrollment.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolEnrollment.setSeasonWinter(seasonWinter);

		Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
		if(seasonEnrollmentFee != null)
			oSchoolEnrollment.setSeasonEnrollmentFee(seasonEnrollmentFee);

		String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
		if(seasonCompleteName != null)
			oSchoolEnrollment.setSeasonCompleteName(seasonCompleteName);

		Date sessionStartDay = (Date)solrDocument.get("sessionStartDay_stored_date");
		if(sessionStartDay != null)
			oSchoolEnrollment.setSessionStartDay(sessionStartDay);

		Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
		if(sessionEndDay != null)
			oSchoolEnrollment.setSessionEndDay(sessionEndDay);

		String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
		if(ageCompleteName != null)
			oSchoolEnrollment.setAgeCompleteName(ageCompleteName);

		Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
		if(ageStart != null)
			oSchoolEnrollment.setAgeStart(ageStart);

		Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
		if(ageEnd != null)
			oSchoolEnrollment.setAgeEnd(ageEnd);

		String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
		if(blockStartTime != null)
			oSchoolEnrollment.setBlockStartTime(blockStartTime);

		String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
		if(blockEndTime != null)
			oSchoolEnrollment.setBlockEndTime(blockEndTime);

		Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
		if(blockPricePerMonth != null)
			oSchoolEnrollment.setBlockPricePerMonth(blockPricePerMonth);

		Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
		if(blockSunday != null)
			oSchoolEnrollment.setBlockSunday(blockSunday);

		Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
		if(blockMonday != null)
			oSchoolEnrollment.setBlockMonday(blockMonday);

		Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
		if(blockTuesday != null)
			oSchoolEnrollment.setBlockTuesday(blockTuesday);

		Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
		if(blockWednesday != null)
			oSchoolEnrollment.setBlockWednesday(blockWednesday);

		Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
		if(blockThursday != null)
			oSchoolEnrollment.setBlockThursday(blockThursday);

		Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
		if(blockFriday != null)
			oSchoolEnrollment.setBlockFriday(blockFriday);

		Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
		if(blockSaturday != null)
			oSchoolEnrollment.setBlockSaturday(blockSaturday);

		Boolean enrollmentApproved = (Boolean)solrDocument.get("enrollmentApproved_stored_boolean");
		if(enrollmentApproved != null)
			oSchoolEnrollment.setEnrollmentApproved(enrollmentApproved);

		Boolean enrollmentImmunizations = (Boolean)solrDocument.get("enrollmentImmunizations_stored_boolean");
		if(enrollmentImmunizations != null)
			oSchoolEnrollment.setEnrollmentImmunizations(enrollmentImmunizations);

		Boolean familyMarried = (Boolean)solrDocument.get("familyMarried_stored_boolean");
		if(familyMarried != null)
			oSchoolEnrollment.setFamilyMarried(familyMarried);

		Boolean familySeparated = (Boolean)solrDocument.get("familySeparated_stored_boolean");
		if(familySeparated != null)
			oSchoolEnrollment.setFamilySeparated(familySeparated);

		Boolean familyDivorced = (Boolean)solrDocument.get("familyDivorced_stored_boolean");
		if(familyDivorced != null)
			oSchoolEnrollment.setFamilyDivorced(familyDivorced);

		String familyAddress = (String)solrDocument.get("familyAddress_stored_string");
		if(familyAddress != null)
			oSchoolEnrollment.setFamilyAddress(familyAddress);

		String familyHowDoYouKnowTheSchool = (String)solrDocument.get("familyHowDoYouKnowTheSchool_stored_string");
		if(familyHowDoYouKnowTheSchool != null)
			oSchoolEnrollment.setFamilyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchool);

		String enrollmentSpecialConsiderations = (String)solrDocument.get("enrollmentSpecialConsiderations_stored_string");
		if(enrollmentSpecialConsiderations != null)
			oSchoolEnrollment.setEnrollmentSpecialConsiderations(enrollmentSpecialConsiderations);

		String enrollmentGroupName = (String)solrDocument.get("enrollmentGroupName_stored_string");
		if(enrollmentGroupName != null)
			oSchoolEnrollment.setEnrollmentGroupName(enrollmentGroupName);

		Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
		if(enrollmentPaymentEachMonth != null)
			oSchoolEnrollment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);

		Boolean enrollmentPaymentComplete = (Boolean)solrDocument.get("enrollmentPaymentComplete_stored_boolean");
		if(enrollmentPaymentComplete != null)
			oSchoolEnrollment.setEnrollmentPaymentComplete(enrollmentPaymentComplete);

		String enrollmentCompleteName = (String)solrDocument.get("enrollmentCompleteName_stored_string");
		if(enrollmentCompleteName != null)
			oSchoolEnrollment.setEnrollmentCompleteName(enrollmentCompleteName);

		String enrollmentId = (String)solrDocument.get("enrollmentId_stored_string");
		if(enrollmentId != null)
			oSchoolEnrollment.setEnrollmentId(enrollmentId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oSchoolEnrollment.setPageUrl(pageUrl);

		String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
		oSchoolEnrollment.setObjectSuggest(objectSuggest);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), blockKeys, childKey, momKeys, dadKeys, guardianKeys, paymentKeys, enrollmentApproved, enrollmentImmunizations, familyMarried, familySeparated, familyDivorced, familyAddress, familyHowDoYouKnowTheSchool, enrollmentSpecialConsiderations, enrollmentGroupName, enrollmentPaymentEachMonth, enrollmentPaymentComplete);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolEnrollment))
			return false;
		SchoolEnrollment that = (SchoolEnrollment)o;
		return super.equals(o)
				&& Objects.equals( blockKeys, that.blockKeys )
				&& Objects.equals( childKey, that.childKey )
				&& Objects.equals( momKeys, that.momKeys )
				&& Objects.equals( dadKeys, that.dadKeys )
				&& Objects.equals( guardianKeys, that.guardianKeys )
				&& Objects.equals( paymentKeys, that.paymentKeys )
				&& Objects.equals( enrollmentApproved, that.enrollmentApproved )
				&& Objects.equals( enrollmentImmunizations, that.enrollmentImmunizations )
				&& Objects.equals( familyMarried, that.familyMarried )
				&& Objects.equals( familySeparated, that.familySeparated )
				&& Objects.equals( familyDivorced, that.familyDivorced )
				&& Objects.equals( familyAddress, that.familyAddress )
				&& Objects.equals( familyHowDoYouKnowTheSchool, that.familyHowDoYouKnowTheSchool )
				&& Objects.equals( enrollmentSpecialConsiderations, that.enrollmentSpecialConsiderations )
				&& Objects.equals( enrollmentGroupName, that.enrollmentGroupName )
				&& Objects.equals( enrollmentPaymentEachMonth, that.enrollmentPaymentEachMonth )
				&& Objects.equals( enrollmentPaymentComplete, that.enrollmentPaymentComplete );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolEnrollment { ");
		sb.append( "blockKeys: " ).append(blockKeys);
		sb.append( ", childKey: " ).append(childKey);
		sb.append( ", momKeys: " ).append(momKeys);
		sb.append( ", dadKeys: " ).append(dadKeys);
		sb.append( ", guardianKeys: " ).append(guardianKeys);
		sb.append( ", paymentKeys: " ).append(paymentKeys);
		sb.append( ", enrollmentApproved: " ).append(enrollmentApproved);
		sb.append( ", enrollmentImmunizations: " ).append(enrollmentImmunizations);
		sb.append( ", familyMarried: " ).append(familyMarried);
		sb.append( ", familySeparated: " ).append(familySeparated);
		sb.append( ", familyDivorced: " ).append(familyDivorced);
		sb.append( ", familyAddress: \"" ).append(familyAddress).append( "\"" );
		sb.append( ", familyHowDoYouKnowTheSchool: \"" ).append(familyHowDoYouKnowTheSchool).append( "\"" );
		sb.append( ", enrollmentSpecialConsiderations: \"" ).append(enrollmentSpecialConsiderations).append( "\"" );
		sb.append( ", enrollmentGroupName: \"" ).append(enrollmentGroupName).append( "\"" );
		sb.append( ", enrollmentPaymentEachMonth: " ).append(enrollmentPaymentEachMonth);
		sb.append( ", enrollmentPaymentComplete: " ).append(enrollmentPaymentComplete);
		sb.append(" }");
		return sb.toString();
	}
}
