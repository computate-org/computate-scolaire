package org.computate.scolaire.enUS.payment;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import java.math.BigDecimal;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolPaymentGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolPayment.class);

	public static final String SchoolPayment_UnNom = "a payment";
	public static final String SchoolPayment_Ce = "this ";
	public static final String SchoolPayment_CeNom = "this payment";
	public static final String SchoolPayment_Un = "a ";
	public static final String SchoolPayment_LeNom = "the payment";
	public static final String SchoolPayment_NomSingulier = "payment";
	public static final String SchoolPayment_NomPluriel = "payments";
	public static final String SchoolPayment_NomActuel = "current payment";
	public static final String SchoolPayment_TousNom = "all the payments";
	public static final String SchoolPayment_RechercherTousNomPar = "search payments by ";
	public static final String SchoolPayment_LesNoms = "the payments";
	public static final String SchoolPayment_AucunNomTrouve = "no payment found";
	public static final String SchoolPayment_NomVar = "payment";
	public static final String SchoolPayment_DeNom = "of payment";
	public static final String SchoolPayment_UnNomAdjectif = "a payment";
	public static final String SchoolPayment_NomAdjectifSingulier = "payment";
	public static final String SchoolPayment_NomAdjectifPluriel = "payments";
	public static final String SchoolPayment_Couleur = "green";
	public static final String SchoolPayment_IconeGroupe = "solid";
	public static final String SchoolPayment_IconeNom = "search-dollar";

	////////////////
	// schoolKeys //
	////////////////

	/**	L'entité « schoolKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> schoolKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> schoolKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("schoolKeys").o(schoolKeys);

	/**	<br/>L'entité « schoolKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKeys">Trouver l'entité schoolKeys dans Solr</a>
	 * <br/>
	 * @param schoolKeys est l'entité déjà construit. 
	 **/
	protected abstract void _schoolKeys(List<Long> l);

	public List<Long> getSchoolKeys() {
		return schoolKeys;
	}

	public void setSchoolKeys(List<Long> schoolKeys) {
		this.schoolKeys = schoolKeys;
		this.schoolKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addSchoolKeys(Long...objets) {
		for(Long o : objets) {
			addSchoolKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addSchoolKeys(Long o) {
		if(o != null && !schoolKeys.contains(o))
			this.schoolKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setSchoolKeys(JsonArray objets) {
		schoolKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSchoolKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addSchoolKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSchoolKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment schoolKeysInit() {
		if(!schoolKeysWrap.alreadyInitialized) {
			_schoolKeys(schoolKeys);
		}
		schoolKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public List<Long> solrSchoolKeys() {
		return schoolKeys;
	}

	public String strSchoolKeys() {
		return schoolKeys == null ? "" : schoolKeys.toString();
	}

	public String jsonSchoolKeys() {
		return schoolKeys == null ? "" : schoolKeys.toString();
	}

	public String nomAffichageSchoolKeys() {
		return "schools";
	}

	public String htmTooltipSchoolKeys() {
		return null;
	}

	public String htmSchoolKeys() {
		return schoolKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKeys());
	}

	///////////////
	// anneeCles //
	///////////////

	/**	L'entité « anneeCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> anneeCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> anneeClesWrap = new Wrap<List<Long>>().p(this).c(List.class).var("anneeCles").o(anneeCles);

	/**	<br/>L'entité « anneeCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
	 * <br/>
	 * @param anneeCles est l'entité déjà construit. 
	 **/
	protected abstract void _anneeCles(List<Long> l);

	public List<Long> getAnneeCles() {
		return anneeCles;
	}

	public void setAnneeCles(List<Long> anneeCles) {
		this.anneeCles = anneeCles;
		this.anneeClesWrap.alreadyInitialized = true;
	}
	public SchoolPayment addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addAnneeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment anneeClesInit() {
		if(!anneeClesWrap.alreadyInitialized) {
			_anneeCles(anneeCles);
		}
		anneeClesWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public List<Long> solrAnneeCles() {
		return anneeCles;
	}

	public String strAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String jsonAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String nomAffichageAnneeCles() {
		return null;
	}

	public String htmTooltipAnneeCles() {
		return null;
	}

	public String htmAnneeCles() {
		return anneeCles == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCles());
	}

	////////////////
	// seasonKeys //
	////////////////

	/**	L'entité « seasonKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> seasonKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> seasonKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("seasonKeys").o(seasonKeys);

	/**	<br/>L'entité « seasonKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Trouver l'entité seasonKeys dans Solr</a>
	 * <br/>
	 * @param seasonKeys est l'entité déjà construit. 
	 **/
	protected abstract void _seasonKeys(List<Long> l);

	public List<Long> getSeasonKeys() {
		return seasonKeys;
	}

	public void setSeasonKeys(List<Long> seasonKeys) {
		this.seasonKeys = seasonKeys;
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addSeasonKeys(Long...objets) {
		for(Long o : objets) {
			addSeasonKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addSeasonKeys(Long o) {
		if(o != null && !seasonKeys.contains(o))
			this.seasonKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addSeasonKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSeasonKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment seasonKeysInit() {
		if(!seasonKeysWrap.alreadyInitialized) {
			_seasonKeys(seasonKeys);
		}
		seasonKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public List<Long> solrSeasonKeys() {
		return seasonKeys;
	}

	public String strSeasonKeys() {
		return seasonKeys == null ? "" : seasonKeys.toString();
	}

	public String jsonSeasonKeys() {
		return seasonKeys == null ? "" : seasonKeys.toString();
	}

	public String nomAffichageSeasonKeys() {
		return "seasons";
	}

	public String htmTooltipSeasonKeys() {
		return null;
	}

	public String htmSeasonKeys() {
		return seasonKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKeys());
	}

	/////////////////
	// sessionKeys //
	/////////////////

	/**	L'entité « sessionKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> sessionKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> sessionKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("sessionKeys").o(sessionKeys);

	/**	<br/>L'entité « sessionKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Trouver l'entité sessionKeys dans Solr</a>
	 * <br/>
	 * @param sessionKeys est l'entité déjà construit. 
	 **/
	protected abstract void _sessionKeys(List<Long> l);

	public List<Long> getSessionKeys() {
		return sessionKeys;
	}

	public void setSessionKeys(List<Long> sessionKeys) {
		this.sessionKeys = sessionKeys;
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addSessionKeys(Long...objets) {
		for(Long o : objets) {
			addSessionKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addSessionKeys(Long o) {
		if(o != null && !sessionKeys.contains(o))
			this.sessionKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addSessionKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment sessionKeysInit() {
		if(!sessionKeysWrap.alreadyInitialized) {
			_sessionKeys(sessionKeys);
		}
		sessionKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public List<Long> solrSessionKeys() {
		return sessionKeys;
	}

	public String strSessionKeys() {
		return sessionKeys == null ? "" : sessionKeys.toString();
	}

	public String jsonSessionKeys() {
		return sessionKeys == null ? "" : sessionKeys.toString();
	}

	public String nomAffichageSessionKeys() {
		return "sessions";
	}

	public String htmTooltipSessionKeys() {
		return null;
	}

	public String htmSessionKeys() {
		return sessionKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKeys());
	}

	/////////////
	// ageKeys //
	/////////////

	/**	L'entité « ageKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> ageKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> ageKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("ageKeys").o(ageKeys);

	/**	<br/>L'entité « ageKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Trouver l'entité ageKeys dans Solr</a>
	 * <br/>
	 * @param ageKeys est l'entité déjà construit. 
	 **/
	protected abstract void _ageKeys(List<Long> l);

	public List<Long> getAgeKeys() {
		return ageKeys;
	}

	public void setAgeKeys(List<Long> ageKeys) {
		this.ageKeys = ageKeys;
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addAgeKeys(Long...objets) {
		for(Long o : objets) {
			addAgeKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addAgeKeys(Long o) {
		if(o != null && !ageKeys.contains(o))
			this.ageKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addAgeKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment ageKeysInit() {
		if(!ageKeysWrap.alreadyInitialized) {
			_ageKeys(ageKeys);
		}
		ageKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public List<Long> solrAgeKeys() {
		return ageKeys;
	}

	public String strAgeKeys() {
		return ageKeys == null ? "" : ageKeys.toString();
	}

	public String jsonAgeKeys() {
		return ageKeys == null ? "" : ageKeys.toString();
	}

	public String nomAffichageAgeKeys() {
		return "ages";
	}

	public String htmTooltipAgeKeys() {
		return null;
	}

	public String htmAgeKeys() {
		return ageKeys == null ? "" : StringEscapeUtils.escapeHtml4(strAgeKeys());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKeys">Trouver l'entité blockKeys dans Solr</a>
	 * <br/>
	 * @param blockKeys est l'entité déjà construit. 
	 **/
	protected abstract void _blockKeys(List<Long> l);

	public List<Long> getBlockKeys() {
		return blockKeys;
	}

	public void setBlockKeys(List<Long> blockKeys) {
		this.blockKeys = blockKeys;
		this.blockKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addBlockKeys(Long...objets) {
		for(Long o : objets) {
			addBlockKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addBlockKeys(Long o) {
		if(o != null && !blockKeys.contains(o))
			this.blockKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setBlockKeys(JsonArray objets) {
		blockKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlockKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addBlockKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addBlockKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment blockKeysInit() {
		if(!blockKeysWrap.alreadyInitialized) {
			_blockKeys(blockKeys);
		}
		blockKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
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
		return "sessions";
	}

	public String htmTooltipBlockKeys() {
		return null;
	}

	public String htmBlockKeys() {
		return blockKeys == null ? "" : StringEscapeUtils.escapeHtml4(strBlockKeys());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
	 * <br/>
	 * @param enrollmentKeys est l'entité déjà construit. 
	 **/
	protected abstract void _enrollmentKeys(List<Long> l);

	public List<Long> getEnrollmentKeys() {
		return enrollmentKeys;
	}

	public void setEnrollmentKeys(List<Long> enrollmentKeys) {
		this.enrollmentKeys = enrollmentKeys;
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
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

	public void inputEnrollmentKeys(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "enrollments")
				.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEnrollmentKeys")
				.a("id", classApiMethodMethod, "_enrollmentKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolPaymentEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($('#suggestSchoolPaymentEnrollmentKeys')) : [{'name':'fq','value':'paymentKeys:", pk, "'}], $('#listSchoolPaymentEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmEnrollmentKeys(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolPaymentEnrollmentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enrollment?fq=paymentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("enrollments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate enrollments to this payment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputEnrollmentKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolPaymentEnrollmentKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
										.a("onclick", "postSchoolEnrollmentVals({ paymentKeys: [ \"", pk, "\" ] }, function() { patchSchoolPaymentVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
										.f().sx("add an enrollment")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// paymentKey //
	////////////////

	/**	L'entité « paymentKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long paymentKey;
	@JsonIgnore
	public Wrap<Long> paymentKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("paymentKey").o(paymentKey);

	/**	<br/>L'entité « paymentKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentKey">Trouver l'entité paymentKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentKey(Wrap<Long> c);

	public Long getPaymentKey() {
		return paymentKey;
	}

	public void setPaymentKey(Long paymentKey) {
		this.paymentKey = paymentKey;
		this.paymentKeyWrap.alreadyInitialized = true;
	}
	public SchoolPayment setPaymentKey(String o) {
		if(NumberUtils.isParsable(o))
			this.paymentKey = Long.parseLong(o);
		this.paymentKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentKeyInit() {
		if(!paymentKeyWrap.alreadyInitialized) {
			_paymentKey(paymentKeyWrap);
			if(paymentKey == null)
				setPaymentKey(paymentKeyWrap.o);
		}
		paymentKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public Long solrPaymentKey() {
		return paymentKey;
	}

	public String strPaymentKey() {
		return paymentKey == null ? "" : paymentKey.toString();
	}

	public String jsonPaymentKey() {
		return paymentKey == null ? "" : paymentKey.toString();
	}

	public String nomAffichagePaymentKey() {
		return "key";
	}

	public String htmTooltipPaymentKey() {
		return null;
	}

	public String htmPaymentKey() {
		return paymentKey == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentKey());
	}

	///////////////
	// childKeys //
	///////////////

	/**	L'entité « childKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> childKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> childKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("childKeys").o(childKeys);

	/**	<br/>L'entité « childKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKeys">Trouver l'entité childKeys dans Solr</a>
	 * <br/>
	 * @param childKeys est l'entité déjà construit. 
	 **/
	protected abstract void _childKeys(List<Long> o);

	public List<Long> getChildKeys() {
		return childKeys;
	}

	public void setChildKeys(List<Long> childKeys) {
		this.childKeys = childKeys;
		this.childKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addChildKeys(Long...objets) {
		for(Long o : objets) {
			addChildKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addChildKeys(Long o) {
		if(o != null && !childKeys.contains(o))
			this.childKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setChildKeys(JsonArray objets) {
		childKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addChildKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addChildKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addChildKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment childKeysInit() {
		if(!childKeysWrap.alreadyInitialized) {
			_childKeys(childKeys);
		}
		childKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public List<Long> solrChildKeys() {
		return childKeys;
	}

	public String strChildKeys() {
		return childKeys == null ? "" : childKeys.toString();
	}

	public String jsonChildKeys() {
		return childKeys == null ? "" : childKeys.toString();
	}

	public String nomAffichageChildKeys() {
		return "childs";
	}

	public String htmTooltipChildKeys() {
		return null;
	}

	public String htmChildKeys() {
		return childKeys == null ? "" : StringEscapeUtils.escapeHtml4(strChildKeys());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momKeys">Trouver l'entité momKeys dans Solr</a>
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
	public SchoolPayment addMomKeys(Long...objets) {
		for(Long o : objets) {
			addMomKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addMomKeys(Long o) {
		if(o != null && !momKeys.contains(o))
			this.momKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setMomKeys(JsonArray objets) {
		momKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMomKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addMomKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addMomKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment momKeysInit() {
		if(!momKeysWrap.alreadyInitialized) {
			_momKeys(momKeys);
		}
		momKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadKeys">Trouver l'entité dadKeys dans Solr</a>
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
	public SchoolPayment addDadKeys(Long...objets) {
		for(Long o : objets) {
			addDadKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addDadKeys(Long o) {
		if(o != null && !dadKeys.contains(o))
			this.dadKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setDadKeys(JsonArray objets) {
		dadKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDadKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addDadKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addDadKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment dadKeysInit() {
		if(!dadKeysWrap.alreadyInitialized) {
			_dadKeys(dadKeys);
		}
		dadKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianKeys">Trouver l'entité guardianKeys dans Solr</a>
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
	public SchoolPayment addGuardianKeys(Long...objets) {
		for(Long o : objets) {
			addGuardianKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addGuardianKeys(Long o) {
		if(o != null && !guardianKeys.contains(o))
			this.guardianKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setGuardianKeys(JsonArray objets) {
		guardianKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGuardianKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addGuardianKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addGuardianKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment guardianKeysInit() {
		if(!guardianKeysWrap.alreadyInitialized) {
			_guardianKeys(guardianKeys);
		}
		guardianKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
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

	/////////////////
	// contactKeys //
	/////////////////

	/**	L'entité « contactKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> contactKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> contactKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("contactKeys").o(contactKeys);

	/**	<br/>L'entité « contactKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contactKeys">Trouver l'entité contactKeys dans Solr</a>
	 * <br/>
	 * @param contactKeys est l'entité déjà construit. 
	 **/
	protected abstract void _contactKeys(List<Long> o);

	public List<Long> getContactKeys() {
		return contactKeys;
	}

	public void setContactKeys(List<Long> contactKeys) {
		this.contactKeys = contactKeys;
		this.contactKeysWrap.alreadyInitialized = true;
	}
	public SchoolPayment addContactKeys(Long...objets) {
		for(Long o : objets) {
			addContactKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addContactKeys(Long o) {
		if(o != null && !contactKeys.contains(o))
			this.contactKeys.add(o);
		return (SchoolPayment)this;
	}
	public SchoolPayment setContactKeys(JsonArray objets) {
		contactKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addContactKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addContactKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addContactKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment contactKeysInit() {
		if(!contactKeysWrap.alreadyInitialized) {
			_contactKeys(contactKeys);
		}
		contactKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public List<Long> solrContactKeys() {
		return contactKeys;
	}

	public String strContactKeys() {
		return contactKeys == null ? "" : contactKeys.toString();
	}

	public String jsonContactKeys() {
		return contactKeys == null ? "" : contactKeys.toString();
	}

	public String nomAffichageContactKeys() {
		return "emergency contacts";
	}

	public String htmTooltipContactKeys() {
		return null;
	}

	public String htmContactKeys() {
		return contactKeys == null ? "" : StringEscapeUtils.escapeHtml4(strContactKeys());
	}

	//////////////////////
	// enrollmentSearch //
	//////////////////////

	/**	L'entité « enrollmentSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> enrollmentSearchWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("enrollmentSearch").o(enrollmentSearch);

	/**	<br/>L'entité « enrollmentSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Trouver l'entité enrollmentSearch dans Solr</a>
	 * <br/>
	 * @param enrollmentSearch est l'entité déjà construit. 
	 **/
	protected abstract void _enrollmentSearch(SearchList<SchoolEnrollment> l);

	public SearchList<SchoolEnrollment> getEnrollmentSearch() {
		return enrollmentSearch;
	}

	public void setEnrollmentSearch(SearchList<SchoolEnrollment> enrollmentSearch) {
		this.enrollmentSearch = enrollmentSearch;
		this.enrollmentSearchWrap.alreadyInitialized = true;
	}
	protected SchoolPayment enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	/////////////////
	// enrollment_ //
	/////////////////

	/**	L'entité « enrollment_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolEnrollment enrollment_;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollment_Wrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollment_").o(enrollment_);

	/**	<br/>L'entité « enrollment_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollment_">Trouver l'entité enrollment_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollment_(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollment_() {
		return enrollment_;
	}

	public void setEnrollment_(SchoolEnrollment enrollment_) {
		this.enrollment_ = enrollment_;
		this.enrollment_Wrap.alreadyInitialized = true;
	}
	protected SchoolPayment enrollment_Init() {
		if(!enrollment_Wrap.alreadyInitialized) {
			_enrollment_(enrollment_Wrap);
			if(enrollment_ == null)
				setEnrollment_(enrollment_Wrap.o);
		}
		enrollment_Wrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	////////////////////////
	// paymentDescription //
	////////////////////////

	/**	L'entité « paymentDescription »
	 *	 is defined as null before being initialized. 
	 */
	protected String paymentDescription;
	@JsonIgnore
	public Wrap<String> paymentDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("paymentDescription").o(paymentDescription);

	/**	<br/>L'entité « paymentDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDescription">Trouver l'entité paymentDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentDescription(Wrap<String> c);

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
		this.paymentDescriptionWrap.alreadyInitialized = true;
	}
	protected SchoolPayment paymentDescriptionInit() {
		if(!paymentDescriptionWrap.alreadyInitialized) {
			_paymentDescription(paymentDescriptionWrap);
			if(paymentDescription == null)
				setPaymentDescription(paymentDescriptionWrap.o);
		}
		paymentDescriptionWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public String solrPaymentDescription() {
		return paymentDescription;
	}

	public String strPaymentDescription() {
		return paymentDescription == null ? "" : paymentDescription;
	}

	public String jsonPaymentDescription() {
		return paymentDescription == null ? "" : paymentDescription;
	}

	public String nomAffichagePaymentDescription() {
		return "description";
	}

	public String htmTooltipPaymentDescription() {
		return null;
	}

	public String htmPaymentDescription() {
		return paymentDescription == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentDescription());
	}

	public void inputPaymentDescription(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "description")
			.a("id", classApiMethodMethod, "_paymentDescription");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPaymentDescription inputSchoolPayment", pk, "PaymentDescription w3-input w3-border ");
				a("name", "setPaymentDescription");
			} else {
				a("class", "valuePaymentDescription w3-input w3-border inputSchoolPayment", pk, "PaymentDescription w3-input w3-border ");
				a("name", "paymentDescription");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolPaymentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentDescription', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_paymentDescription')); }, function() { addError($('#", classApiMethodMethod, "_paymentDescription')); }); ");
			}
			a("value", strPaymentDescription())
		.fg();

	}

	public void htmPaymentDescription(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolPaymentPaymentDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentDescription(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentDescription')); $('#", classApiMethodMethod, "_paymentDescription').val(null); patchSchoolPaymentVal([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentDescription', null, function() { addGlow($('#", classApiMethodMethod, "_paymentDescription')); }, function() { addError($('#", classApiMethodMethod, "_paymentDescription')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// paymentDate //
	/////////////////

	/**	L'entité « paymentDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate paymentDate;
	@JsonIgnore
	public Wrap<LocalDate> paymentDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("paymentDate").o(paymentDate);

	/**	<br/>L'entité « paymentDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDate">Trouver l'entité paymentDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentDate(Wrap<LocalDate> c);

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
		this.paymentDateWrap.alreadyInitialized = true;
	}
	public SchoolPayment setPaymentDate(Instant o) {
		this.paymentDate = LocalDate.from(o);
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolPayment setPaymentDate(String o) {
		this.paymentDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setPaymentDate(Date o) {
		this.paymentDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentDateInit() {
		if(!paymentDateWrap.alreadyInitialized) {
			_paymentDate(paymentDateWrap);
			if(paymentDate == null)
				setPaymentDate(paymentDateWrap.o);
		}
		paymentDateWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public Date solrPaymentDate() {
		return paymentDate == null ? null : Date.from(paymentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strPaymentDate() {
		return paymentDate == null ? "" : paymentDate.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonPaymentDate() {
		return paymentDate == null ? "" : paymentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichagePaymentDate() {
		return "payment date";
	}

	public String htmTooltipPaymentDate() {
		return null;
	}

	public String htmPaymentDate() {
		return paymentDate == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentDate());
	}

	public void inputPaymentDate(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setPaymentDate inputSchoolPayment", pk, "PaymentDate w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_paymentDate")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", paymentDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(paymentDate))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolPaymentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentDate', s, function() { addGlow($('#", classApiMethodMethod, "_paymentDate')); }, function() { addError($('#", classApiMethodMethod, "_paymentDate')); }); } ")
			.fg();
	}

	public void htmPaymentDate(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolPaymentPaymentDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentDate").a("class", "").f().sx("payment date").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputPaymentDate(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentDate')); $('#", classApiMethodMethod, "_paymentDate').val(null); patchSchoolPaymentVal([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentDate', null, function() { addGlow($('#", classApiMethodMethod, "_paymentDate')); }, function() { addError($('#", classApiMethodMethod, "_paymentDate')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// paymentAmount //
	///////////////////

	/**	L'entité « paymentAmount »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected BigDecimal paymentAmount;
	@JsonIgnore
	public Wrap<BigDecimal> paymentAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("paymentAmount").o(paymentAmount);

	/**	<br/>L'entité « paymentAmount »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentAmount">Trouver l'entité paymentAmount dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentAmount(Wrap<BigDecimal> c);

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public SchoolPayment setPaymentAmount(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setPaymentAmount(Double o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setPaymentAmount(Integer o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentAmountInit() {
		if(!paymentAmountWrap.alreadyInitialized) {
			_paymentAmount(paymentAmountWrap);
			if(paymentAmount == null)
				setPaymentAmount(paymentAmountWrap.o);
		}
		paymentAmountWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public Double solrPaymentAmount() {
		return paymentAmount == null ? null : paymentAmount.doubleValue();
	}

	public String strPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.setScale(2).toString();
	}

	public String jsonPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.toString();
	}

	public String nomAffichagePaymentAmount() {
		return "amount";
	}

	public String htmTooltipPaymentAmount() {
		return null;
	}

	public String htmPaymentAmount() {
		return paymentAmount == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentAmount());
	}

	public void inputPaymentAmount(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "amount")
			.a("id", classApiMethodMethod, "_paymentAmount");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPaymentAmount inputSchoolPayment", pk, "PaymentAmount w3-input w3-border ");
				a("name", "setPaymentAmount");
			} else {
				a("class", "valuePaymentAmount w3-input w3-border inputSchoolPayment", pk, "PaymentAmount w3-input w3-border ");
				a("name", "paymentAmount");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolPaymentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentAmount', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_paymentAmount')); }, function() { addError($('#", classApiMethodMethod, "_paymentAmount')); }); ");
			}
			a("value", strPaymentAmount())
		.fg();

	}

	public void htmPaymentAmount(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolPaymentPaymentAmount").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentAmount").a("class", "").f().sx("amount").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentAmount(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentAmount')); $('#", classApiMethodMethod, "_paymentAmount').val(null); patchSchoolPaymentVal([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentAmount', null, function() { addGlow($('#", classApiMethodMethod, "_paymentAmount')); }, function() { addError($('#", classApiMethodMethod, "_paymentAmount')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// paymentCash //
	/////////////////

	/**	L'entité « paymentCash »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean paymentCash;
	@JsonIgnore
	public Wrap<Boolean> paymentCashWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentCash").o(paymentCash);

	/**	<br/>L'entité « paymentCash »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentCash">Trouver l'entité paymentCash dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentCash(Wrap<Boolean> c);

	public Boolean getPaymentCash() {
		return paymentCash;
	}

	public void setPaymentCash(Boolean paymentCash) {
		this.paymentCash = paymentCash;
		this.paymentCashWrap.alreadyInitialized = true;
	}
	public SchoolPayment setPaymentCash(String o) {
		this.paymentCash = Boolean.parseBoolean(o);
		this.paymentCashWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentCashInit() {
		if(!paymentCashWrap.alreadyInitialized) {
			_paymentCash(paymentCashWrap);
			if(paymentCash == null)
				setPaymentCash(paymentCashWrap.o);
		}
		paymentCashWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public Boolean solrPaymentCash() {
		return paymentCash;
	}

	public String strPaymentCash() {
		return paymentCash == null ? "" : paymentCash.toString();
	}

	public String jsonPaymentCash() {
		return paymentCash == null ? "" : paymentCash.toString();
	}

	public String nomAffichagePaymentCash() {
		return "cash";
	}

	public String htmTooltipPaymentCash() {
		return null;
	}

	public String htmPaymentCash() {
		return paymentCash == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentCash());
	}

	public void inputPaymentCash(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_paymentCash")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_paymentCash");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setPaymentCash inputSchoolPayment", pk, "PaymentCash w3-input w3-border ");
			a("name", "setPaymentCash");
		} else {
			a("class", "valuePaymentCash inputSchoolPayment", pk, "PaymentCash w3-input w3-border ");
			a("name", "paymentCash");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolPaymentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentCash', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_paymentCash')); }, function() { addError($('#", classApiMethodMethod, "_paymentCash')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getPaymentCash() != null && getPaymentCash())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmPaymentCash(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolPaymentPaymentCash").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentCash").a("class", "").f().sx("cash").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentCash(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// paymentCheck //
	//////////////////

	/**	L'entité « paymentCheck »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean paymentCheck;
	@JsonIgnore
	public Wrap<Boolean> paymentCheckWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentCheck").o(paymentCheck);

	/**	<br/>L'entité « paymentCheck »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentCheck">Trouver l'entité paymentCheck dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentCheck(Wrap<Boolean> c);

	public Boolean getPaymentCheck() {
		return paymentCheck;
	}

	public void setPaymentCheck(Boolean paymentCheck) {
		this.paymentCheck = paymentCheck;
		this.paymentCheckWrap.alreadyInitialized = true;
	}
	public SchoolPayment setPaymentCheck(String o) {
		this.paymentCheck = Boolean.parseBoolean(o);
		this.paymentCheckWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentCheckInit() {
		if(!paymentCheckWrap.alreadyInitialized) {
			_paymentCheck(paymentCheckWrap);
			if(paymentCheck == null)
				setPaymentCheck(paymentCheckWrap.o);
		}
		paymentCheckWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public Boolean solrPaymentCheck() {
		return paymentCheck;
	}

	public String strPaymentCheck() {
		return paymentCheck == null ? "" : paymentCheck.toString();
	}

	public String jsonPaymentCheck() {
		return paymentCheck == null ? "" : paymentCheck.toString();
	}

	public String nomAffichagePaymentCheck() {
		return "check";
	}

	public String htmTooltipPaymentCheck() {
		return null;
	}

	public String htmPaymentCheck() {
		return paymentCheck == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentCheck());
	}

	public void inputPaymentCheck(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_paymentCheck")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_paymentCheck");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setPaymentCheck inputSchoolPayment", pk, "PaymentCheck w3-input w3-border ");
			a("name", "setPaymentCheck");
		} else {
			a("class", "valuePaymentCheck inputSchoolPayment", pk, "PaymentCheck w3-input w3-border ");
			a("name", "paymentCheck");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolPaymentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentCheck', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_paymentCheck')); }, function() { addError($('#", classApiMethodMethod, "_paymentCheck')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getPaymentCheck() != null && getPaymentCheck())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmPaymentCheck(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolPaymentPaymentCheck").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentCheck").a("class", "").f().sx("check").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentCheck(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// paymentSystem //
	///////////////////

	/**	L'entité « paymentSystem »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean paymentSystem;
	@JsonIgnore
	public Wrap<Boolean> paymentSystemWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentSystem").o(paymentSystem);

	/**	<br/>L'entité « paymentSystem »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentSystem">Trouver l'entité paymentSystem dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentSystem(Wrap<Boolean> c);

	public Boolean getPaymentSystem() {
		return paymentSystem;
	}

	public void setPaymentSystem(Boolean paymentSystem) {
		this.paymentSystem = paymentSystem;
		this.paymentSystemWrap.alreadyInitialized = true;
	}
	public SchoolPayment setPaymentSystem(String o) {
		this.paymentSystem = Boolean.parseBoolean(o);
		this.paymentSystemWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentSystemInit() {
		if(!paymentSystemWrap.alreadyInitialized) {
			_paymentSystem(paymentSystemWrap);
			if(paymentSystem == null)
				setPaymentSystem(paymentSystemWrap.o);
		}
		paymentSystemWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public Boolean solrPaymentSystem() {
		return paymentSystem;
	}

	public String strPaymentSystem() {
		return paymentSystem == null ? "" : paymentSystem.toString();
	}

	public String jsonPaymentSystem() {
		return paymentSystem == null ? "" : paymentSystem.toString();
	}

	public String nomAffichagePaymentSystem() {
		return "authorized";
	}

	public String htmTooltipPaymentSystem() {
		return null;
	}

	public String htmPaymentSystem() {
		return paymentSystem == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentSystem());
	}

	/////////////////////////
	// paymentCompleteName //
	/////////////////////////

	/**	L'entité « paymentCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String paymentCompleteName;
	@JsonIgnore
	public Wrap<String> paymentCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("paymentCompleteName").o(paymentCompleteName);

	/**	<br/>L'entité « paymentCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentCompleteName">Trouver l'entité paymentCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentCompleteName(Wrap<String> c);

	public String getPaymentCompleteName() {
		return paymentCompleteName;
	}

	public void setPaymentCompleteName(String paymentCompleteName) {
		this.paymentCompleteName = paymentCompleteName;
		this.paymentCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolPayment paymentCompleteNameInit() {
		if(!paymentCompleteNameWrap.alreadyInitialized) {
			_paymentCompleteName(paymentCompleteNameWrap);
			if(paymentCompleteName == null)
				setPaymentCompleteName(paymentCompleteNameWrap.o);
		}
		paymentCompleteNameWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public String solrPaymentCompleteName() {
		return paymentCompleteName;
	}

	public String strPaymentCompleteName() {
		return paymentCompleteName == null ? "" : paymentCompleteName;
	}

	public String jsonPaymentCompleteName() {
		return paymentCompleteName == null ? "" : paymentCompleteName;
	}

	public String nomAffichagePaymentCompleteName() {
		return "name";
	}

	public String htmTooltipPaymentCompleteName() {
		return null;
	}

	public String htmPaymentCompleteName() {
		return paymentCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentCompleteName());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolPayment = false;

	public SchoolPayment initDeepSchoolPayment(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolPayment) {
			alreadyInitializedSchoolPayment = true;
			initDeepSchoolPayment();
		}
		return (SchoolPayment)this;
	}

	public void initDeepSchoolPayment() {
		initSchoolPayment();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolPayment() {
		schoolKeysInit();
		anneeClesInit();
		seasonKeysInit();
		sessionKeysInit();
		ageKeysInit();
		blockKeysInit();
		enrollmentKeysInit();
		paymentKeyInit();
		childKeysInit();
		momKeysInit();
		dadKeysInit();
		guardianKeysInit();
		contactKeysInit();
		enrollmentSearchInit();
		enrollment_Init();
		paymentDescriptionInit();
		paymentDateInit();
		paymentAmountInit();
		paymentCashInit();
		paymentCheckInit();
		paymentSystemInit();
		paymentCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolPayment(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolPayment(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolPayment(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolPayment(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolPayment(String var) {
		SchoolPayment oSchoolPayment = (SchoolPayment)this;
		switch(var) {
			case "schoolKeys":
				return oSchoolPayment.schoolKeys;
			case "anneeCles":
				return oSchoolPayment.anneeCles;
			case "seasonKeys":
				return oSchoolPayment.seasonKeys;
			case "sessionKeys":
				return oSchoolPayment.sessionKeys;
			case "ageKeys":
				return oSchoolPayment.ageKeys;
			case "blockKeys":
				return oSchoolPayment.blockKeys;
			case "enrollmentKeys":
				return oSchoolPayment.enrollmentKeys;
			case "paymentKey":
				return oSchoolPayment.paymentKey;
			case "childKeys":
				return oSchoolPayment.childKeys;
			case "momKeys":
				return oSchoolPayment.momKeys;
			case "dadKeys":
				return oSchoolPayment.dadKeys;
			case "guardianKeys":
				return oSchoolPayment.guardianKeys;
			case "contactKeys":
				return oSchoolPayment.contactKeys;
			case "enrollmentSearch":
				return oSchoolPayment.enrollmentSearch;
			case "enrollment_":
				return oSchoolPayment.enrollment_;
			case "paymentDescription":
				return oSchoolPayment.paymentDescription;
			case "paymentDate":
				return oSchoolPayment.paymentDate;
			case "paymentAmount":
				return oSchoolPayment.paymentAmount;
			case "paymentCash":
				return oSchoolPayment.paymentCash;
			case "paymentCheck":
				return oSchoolPayment.paymentCheck;
			case "paymentSystem":
				return oSchoolPayment.paymentSystem;
			case "paymentCompleteName":
				return oSchoolPayment.paymentCompleteName;
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
				o = attributeSchoolPayment(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolPayment(String var, Object val) {
		SchoolPayment oSchoolPayment = (SchoolPayment)this;
		switch(var) {
			case "enrollmentKeys":
				oSchoolPayment.addEnrollmentKeys((Long)val);
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
					o = defineSchoolPayment(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolPayment(String var, String val) {
		switch(var) {
			case "paymentDescription":
				setPaymentDescription(val);
				savesSchoolPayment.add(var);
				return val;
			case "paymentDate":
				setPaymentDate(val);
				savesSchoolPayment.add(var);
				return val;
			case "paymentAmount":
				setPaymentAmount(val);
				savesSchoolPayment.add(var);
				return val;
			case "paymentCash":
				setPaymentCash(val);
				savesSchoolPayment.add(var);
				return val;
			case "paymentCheck":
				setPaymentCheck(val);
				savesSchoolPayment.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolPayment = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolPayment(solrDocument);
	}
	public void populateSchoolPayment(SolrDocument solrDocument) {
		SchoolPayment oSchoolPayment = (SchoolPayment)this;
		savesSchoolPayment = (List<String>)solrDocument.get("savesSchoolPayment_stored_strings");
		if(savesSchoolPayment != null) {

			if(savesSchoolPayment.contains("schoolKeys")) {
				List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
				if(schoolKeys != null)
					oSchoolPayment.schoolKeys.addAll(schoolKeys);
			}

			if(savesSchoolPayment.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolPayment.seasonKeys.addAll(seasonKeys);
			}

			if(savesSchoolPayment.contains("sessionKeys")) {
				List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
				if(sessionKeys != null)
					oSchoolPayment.sessionKeys.addAll(sessionKeys);
			}

			if(savesSchoolPayment.contains("ageKeys")) {
				List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
				if(ageKeys != null)
					oSchoolPayment.ageKeys.addAll(ageKeys);
			}

			if(savesSchoolPayment.contains("blockKeys")) {
				List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
				if(blockKeys != null)
					oSchoolPayment.blockKeys.addAll(blockKeys);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolPayment.enrollmentKeys.addAll(enrollmentKeys);

			if(savesSchoolPayment.contains("paymentKey")) {
				Long paymentKey = (Long)solrDocument.get("paymentKey_stored_long");
				if(paymentKey != null)
					oSchoolPayment.setPaymentKey(paymentKey);
			}

			if(savesSchoolPayment.contains("childKeys")) {
				List<Long> childKeys = (List<Long>)solrDocument.get("childKeys_stored_longs");
				if(childKeys != null)
					oSchoolPayment.childKeys.addAll(childKeys);
			}

			if(savesSchoolPayment.contains("momKeys")) {
				List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
				if(momKeys != null)
					oSchoolPayment.momKeys.addAll(momKeys);
			}

			if(savesSchoolPayment.contains("dadKeys")) {
				List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
				if(dadKeys != null)
					oSchoolPayment.dadKeys.addAll(dadKeys);
			}

			if(savesSchoolPayment.contains("guardianKeys")) {
				List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
				if(guardianKeys != null)
					oSchoolPayment.guardianKeys.addAll(guardianKeys);
			}

			if(savesSchoolPayment.contains("contactKeys")) {
				List<Long> contactKeys = (List<Long>)solrDocument.get("contactKeys_stored_longs");
				if(contactKeys != null)
					oSchoolPayment.contactKeys.addAll(contactKeys);
			}

			if(savesSchoolPayment.contains("paymentDescription")) {
				String paymentDescription = (String)solrDocument.get("paymentDescription_stored_string");
				if(paymentDescription != null)
					oSchoolPayment.setPaymentDescription(paymentDescription);
			}

			if(savesSchoolPayment.contains("paymentDate")) {
				Date paymentDate = (Date)solrDocument.get("paymentDate_stored_date");
				if(paymentDate != null)
					oSchoolPayment.setPaymentDate(paymentDate);
			}

			if(savesSchoolPayment.contains("paymentAmount")) {
				Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
				if(paymentAmount != null)
					oSchoolPayment.setPaymentAmount(paymentAmount);
			}

			if(savesSchoolPayment.contains("paymentCash")) {
				Boolean paymentCash = (Boolean)solrDocument.get("paymentCash_stored_boolean");
				if(paymentCash != null)
					oSchoolPayment.setPaymentCash(paymentCash);
			}

			if(savesSchoolPayment.contains("paymentCheck")) {
				Boolean paymentCheck = (Boolean)solrDocument.get("paymentCheck_stored_boolean");
				if(paymentCheck != null)
					oSchoolPayment.setPaymentCheck(paymentCheck);
			}

			if(savesSchoolPayment.contains("paymentSystem")) {
				Boolean paymentSystem = (Boolean)solrDocument.get("paymentSystem_stored_boolean");
				if(paymentSystem != null)
					oSchoolPayment.setPaymentSystem(paymentSystem);
			}

			if(savesSchoolPayment.contains("paymentCompleteName")) {
				String paymentCompleteName = (String)solrDocument.get("paymentCompleteName_stored_string");
				if(paymentCompleteName != null)
					oSchoolPayment.setPaymentCompleteName(paymentCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.payment.SchoolPayment"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolPayment o = new SchoolPayment();
			o.siteRequestSchoolPayment(siteRequest);
			o.initDeepSchoolPayment(siteRequest);
			o.indexSchoolPayment();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolPayment();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolPayment(document);
	}

	public void indexSchoolPayment(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolPayment(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolPayment() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolPayment(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolPayment(SolrInputDocument document) {
		if(savesSchoolPayment != null)
			document.addField("savesSchoolPayment_stored_strings", savesSchoolPayment);

		if(schoolKeys != null) {
			for(java.lang.Long o : schoolKeys) {
				document.addField("schoolKeys_indexed_longs", o);
			}
			for(java.lang.Long o : schoolKeys) {
				document.addField("schoolKeys_stored_longs", o);
			}
		}
		if(seasonKeys != null) {
			for(java.lang.Long o : seasonKeys) {
				document.addField("seasonKeys_indexed_longs", o);
			}
			for(java.lang.Long o : seasonKeys) {
				document.addField("seasonKeys_stored_longs", o);
			}
		}
		if(sessionKeys != null) {
			for(java.lang.Long o : sessionKeys) {
				document.addField("sessionKeys_indexed_longs", o);
			}
			for(java.lang.Long o : sessionKeys) {
				document.addField("sessionKeys_stored_longs", o);
			}
		}
		if(ageKeys != null) {
			for(java.lang.Long o : ageKeys) {
				document.addField("ageKeys_indexed_longs", o);
			}
			for(java.lang.Long o : ageKeys) {
				document.addField("ageKeys_stored_longs", o);
			}
		}
		if(blockKeys != null) {
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_indexed_longs", o);
			}
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_stored_longs", o);
			}
		}
		if(enrollmentKeys != null) {
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_stored_longs", o);
			}
		}
		if(paymentKey != null) {
			document.addField("paymentKey_indexed_long", paymentKey);
			document.addField("paymentKey_stored_long", paymentKey);
		}
		if(childKeys != null) {
			for(java.lang.Long o : childKeys) {
				document.addField("childKeys_indexed_longs", o);
			}
			for(java.lang.Long o : childKeys) {
				document.addField("childKeys_stored_longs", o);
			}
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
		if(contactKeys != null) {
			for(java.lang.Long o : contactKeys) {
				document.addField("contactKeys_indexed_longs", o);
			}
			for(java.lang.Long o : contactKeys) {
				document.addField("contactKeys_stored_longs", o);
			}
		}
		if(paymentDescription != null) {
			document.addField("paymentDescription_indexed_string", paymentDescription);
			document.addField("paymentDescription_stored_string", paymentDescription);
		}
		if(paymentDate != null) {
			document.addField("paymentDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paymentDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paymentAmount != null) {
			document.addField("paymentAmount_indexed_double", paymentAmount.doubleValue());
			document.addField("paymentAmount_stored_double", paymentAmount.doubleValue());
		}
		if(paymentCash != null) {
			document.addField("paymentCash_indexed_boolean", paymentCash);
			document.addField("paymentCash_stored_boolean", paymentCash);
		}
		if(paymentCheck != null) {
			document.addField("paymentCheck_indexed_boolean", paymentCheck);
			document.addField("paymentCheck_stored_boolean", paymentCheck);
		}
		if(paymentSystem != null) {
			document.addField("paymentSystem_indexed_boolean", paymentSystem);
			document.addField("paymentSystem_stored_boolean", paymentSystem);
		}
		if(paymentCompleteName != null) {
			document.addField("paymentCompleteName_indexed_string", paymentCompleteName);
			document.addField("paymentCompleteName_stored_string", paymentCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolPayment() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolPayment(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolPayment(String entityVar) {
		switch(entityVar) {
			case "schoolKeys":
				return "schoolKeys_indexed_longs";
			case "seasonKeys":
				return "seasonKeys_indexed_longs";
			case "sessionKeys":
				return "sessionKeys_indexed_longs";
			case "ageKeys":
				return "ageKeys_indexed_longs";
			case "blockKeys":
				return "blockKeys_indexed_longs";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "paymentKey":
				return "paymentKey_indexed_long";
			case "childKeys":
				return "childKeys_indexed_longs";
			case "momKeys":
				return "momKeys_indexed_longs";
			case "dadKeys":
				return "dadKeys_indexed_longs";
			case "guardianKeys":
				return "guardianKeys_indexed_longs";
			case "contactKeys":
				return "contactKeys_indexed_longs";
			case "paymentDescription":
				return "paymentDescription_indexed_string";
			case "paymentDate":
				return "paymentDate_indexed_date";
			case "paymentAmount":
				return "paymentAmount_indexed_double";
			case "paymentCash":
				return "paymentCash_indexed_boolean";
			case "paymentCheck":
				return "paymentCheck_indexed_boolean";
			case "paymentSystem":
				return "paymentSystem_indexed_boolean";
			case "paymentCompleteName":
				return "paymentCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolPayment(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestSchoolPayment(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolPayment(solrDocument);
	}
	public void storeSchoolPayment(SolrDocument solrDocument) {
		SchoolPayment oSchoolPayment = (SchoolPayment)this;

		List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
		if(schoolKeys != null)
			oSchoolPayment.schoolKeys.addAll(schoolKeys);

		List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
		if(seasonKeys != null)
			oSchoolPayment.seasonKeys.addAll(seasonKeys);

		List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
		if(sessionKeys != null)
			oSchoolPayment.sessionKeys.addAll(sessionKeys);

		List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
		if(ageKeys != null)
			oSchoolPayment.ageKeys.addAll(ageKeys);

		List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
		if(blockKeys != null)
			oSchoolPayment.blockKeys.addAll(blockKeys);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolPayment.enrollmentKeys.addAll(enrollmentKeys);

		Long paymentKey = (Long)solrDocument.get("paymentKey_stored_long");
		if(paymentKey != null)
			oSchoolPayment.setPaymentKey(paymentKey);

		List<Long> childKeys = (List<Long>)solrDocument.get("childKeys_stored_longs");
		if(childKeys != null)
			oSchoolPayment.childKeys.addAll(childKeys);

		List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
		if(momKeys != null)
			oSchoolPayment.momKeys.addAll(momKeys);

		List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
		if(dadKeys != null)
			oSchoolPayment.dadKeys.addAll(dadKeys);

		List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
		if(guardianKeys != null)
			oSchoolPayment.guardianKeys.addAll(guardianKeys);

		List<Long> contactKeys = (List<Long>)solrDocument.get("contactKeys_stored_longs");
		if(contactKeys != null)
			oSchoolPayment.contactKeys.addAll(contactKeys);

		String paymentDescription = (String)solrDocument.get("paymentDescription_stored_string");
		if(paymentDescription != null)
			oSchoolPayment.setPaymentDescription(paymentDescription);

		Date paymentDate = (Date)solrDocument.get("paymentDate_stored_date");
		if(paymentDate != null)
			oSchoolPayment.setPaymentDate(paymentDate);

		Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
		if(paymentAmount != null)
			oSchoolPayment.setPaymentAmount(paymentAmount);

		Boolean paymentCash = (Boolean)solrDocument.get("paymentCash_stored_boolean");
		if(paymentCash != null)
			oSchoolPayment.setPaymentCash(paymentCash);

		Boolean paymentCheck = (Boolean)solrDocument.get("paymentCheck_stored_boolean");
		if(paymentCheck != null)
			oSchoolPayment.setPaymentCheck(paymentCheck);

		Boolean paymentSystem = (Boolean)solrDocument.get("paymentSystem_stored_boolean");
		if(paymentSystem != null)
			oSchoolPayment.setPaymentSystem(paymentSystem);

		String paymentCompleteName = (String)solrDocument.get("paymentCompleteName_stored_string");
		if(paymentCompleteName != null)
			oSchoolPayment.setPaymentCompleteName(paymentCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolPayment() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		SchoolPayment original = (SchoolPayment)Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(original != null) {
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(paymentDescription, original.getPaymentDescription()))
				apiRequest.addVars("paymentDescription");
			if(!Objects.equals(paymentDate, original.getPaymentDate()))
				apiRequest.addVars("paymentDate");
			if(!Objects.equals(paymentAmount, original.getPaymentAmount()))
				apiRequest.addVars("paymentAmount");
			if(!Objects.equals(paymentCash, original.getPaymentCash()))
				apiRequest.addVars("paymentCash");
			if(!Objects.equals(paymentCheck, original.getPaymentCheck()))
				apiRequest.addVars("paymentCheck");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentKeys, paymentDescription, paymentDate, paymentAmount, paymentCash, paymentCheck);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolPayment))
			return false;
		SchoolPayment that = (SchoolPayment)o;
		return super.equals(o)
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( paymentDescription, that.paymentDescription )
				&& Objects.equals( paymentDate, that.paymentDate )
				&& Objects.equals( paymentAmount, that.paymentAmount )
				&& Objects.equals( paymentCash, that.paymentCash )
				&& Objects.equals( paymentCheck, that.paymentCheck );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolPayment { ");
		sb.append( "enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", paymentDescription: \"" ).append(paymentDescription).append( "\"" );
		sb.append( ", paymentDate: " ).append(paymentDate);
		sb.append( ", paymentAmount: " ).append(paymentAmount);
		sb.append( ", paymentCash: " ).append(paymentCash);
		sb.append( ", paymentCheck: " ).append(paymentCheck);
		sb.append(" }");
		return sb.toString();
	}
}
