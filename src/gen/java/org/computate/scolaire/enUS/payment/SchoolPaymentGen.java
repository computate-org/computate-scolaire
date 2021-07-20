package org.computate.scolaire.enUS.payment;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import java.util.Map;
import java.time.LocalTime;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import java.time.LocalDate;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.solr.client.solrj.SolrClient;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolPaymentGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolPayment.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("SiteManager");

	public static final String SchoolPayment_AName = "a payment";
	public static final String SchoolPayment_This = "this ";
	public static final String SchoolPayment_ThisName = "this payment";
	public static final String SchoolPayment_A = "a ";
	public static final String SchoolPayment_TheName = "the payment";
	public static final String SchoolPayment_NameSingular = "payment";
	public static final String SchoolPayment_NamePlural = "payments";
	public static final String SchoolPayment_NameActual = "current payment";
	public static final String SchoolPayment_AllName = "all the payments";
	public static final String SchoolPayment_SearchAllNameBy = "search payments by ";
	public static final String SchoolPayment_Title = "payments";
	public static final String SchoolPayment_ThePluralName = "the payments";
	public static final String SchoolPayment_NoNameFound = "no payment found";
	public static final String SchoolPayment_NameVar = "payment";
	public static final String SchoolPayment_OfName = "of payment";
	public static final String SchoolPayment_ANameAdjective = "a payment";
	public static final String SchoolPayment_NameAdjectiveSingular = "payment";
	public static final String SchoolPayment_NameAdjectivePlural = "payments";
	public static final String SchoolPayment_Color = "green";
	public static final String SchoolPayment_IconGroup = "solid";
	public static final String SchoolPayment_IconName = "search-dollar";
	public static final Integer SchoolPayment_Rows = 50;

	////////////////
	// paymentKey //
	////////////////

	/**	 The entity paymentKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long paymentKey;
	@JsonIgnore
	public Wrap<Long> paymentKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("paymentKey").o(paymentKey);

	/**	<br/> The entity paymentKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentKey">Find the entity paymentKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentKey(Wrap<Long> c);

	public Long getPaymentKey() {
		return paymentKey;
	}

	public void setPaymentKey(Long paymentKey) {
		this.paymentKey = paymentKey;
		this.paymentKeyWrap.alreadyInitialized = true;
	}
	public void setPaymentKey(String o) {
		this.paymentKey = SchoolPayment.staticSetPaymentKey(siteRequest_, o);
		this.paymentKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetPaymentKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrPaymentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrPaymentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentKey(siteRequest_, SchoolPayment.staticSolrPaymentKey(siteRequest_, SchoolPayment.staticSetPaymentKey(siteRequest_, o)));
	}

	public Long solrPaymentKey() {
		return SchoolPayment.staticSolrPaymentKey(siteRequest_, paymentKey);
	}

	public String strPaymentKey() {
		return paymentKey == null ? "" : paymentKey.toString();
	}

	public Long sqlPaymentKey() {
		return paymentKey;
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

	///////////////////
	// enrollmentKey //
	///////////////////

	/**	 The entity enrollmentKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long enrollmentKey;
	@JsonIgnore
	public Wrap<Long> enrollmentKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentKey").o(enrollmentKey);

	/**	<br/> The entity enrollmentKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKey">Find the entity enrollmentKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentKey(Wrap<Long> c);

	public Long getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(Long enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public void setEnrollmentKey(String o) {
		this.enrollmentKey = SchoolPayment.staticSetEnrollmentKey(siteRequest_, o);
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolPayment enrollmentKeyInit() {
		if(!enrollmentKeyWrap.alreadyInitialized) {
			_enrollmentKey(enrollmentKeyWrap);
			if(enrollmentKey == null)
				setEnrollmentKey(enrollmentKeyWrap.o);
		}
		enrollmentKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrEnrollmentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrEnrollmentKey(siteRequest_, SchoolPayment.staticSolrEnrollmentKey(siteRequest_, SchoolPayment.staticSetEnrollmentKey(siteRequest_, o)));
	}

	public Long solrEnrollmentKey() {
		return SchoolPayment.staticSolrEnrollmentKey(siteRequest_, enrollmentKey);
	}

	public String strEnrollmentKey() {
		return enrollmentKey == null ? "" : enrollmentKey.toString();
	}

	public Long sqlEnrollmentKey() {
		return enrollmentKey;
	}

	public String jsonEnrollmentKey() {
		return enrollmentKey == null ? "" : enrollmentKey.toString();
	}

	public String nomAffichageEnrollmentKey() {
		return "enrollment";
	}

	public String htmTooltipEnrollmentKey() {
		return null;
	}

	public String htmEnrollmentKey() {
		return enrollmentKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentKey());
	}

	public void inputEnrollmentKey(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_enrollmentKey_clear")
						.a("class", "enrollmentKey_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_enrollmentKey_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "enrollment")
				.a("class", "valueObjectSuggest suggestEnrollmentKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEnrollmentKey")
				.a("id", classApiMethodMethod, "_enrollmentKey")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolPaymentEnrollmentKey($(this).val() ? [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,enrollmentCompleteName' } ] : [", pk == null ? "" : "{'name':'fq','value':'paymentKeys:" + pk + "'}", "], $('#listSchoolPaymentEnrollmentKey_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
		}
	}

	public void htmEnrollmentKey(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentEnrollmentKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enrollment?fq=paymentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("enrollment");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate an enrollment to this payment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputEnrollmentKey(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolPaymentEnrollmentKey_", classApiMethodMethod).f();
								} g("ul");
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classApiMethodMethod, "_enrollmentKey_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ paymentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKey')); });")
												.f().sx("add an enrollment")
											.g("button");
										} g("div");
									}
								}
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// enrollmentSearch //
	//////////////////////

	/**	 The entity enrollmentSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> enrollmentSearchWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("enrollmentSearch").o(enrollmentSearch);

	/**	<br/> The entity enrollmentSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Find the entity enrollmentSearch in Solr</a>
	 * <br/>
	 * @param enrollmentSearch is the entity already constructed. 
	 **/
	protected abstract void _enrollmentSearch(SearchList<SchoolEnrollment> l);

	public SearchList<SchoolEnrollment> getEnrollmentSearch() {
		return enrollmentSearch;
	}

	public void setEnrollmentSearch(SearchList<SchoolEnrollment> enrollmentSearch) {
		this.enrollmentSearch = enrollmentSearch;
		this.enrollmentSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolEnrollment> staticSetEnrollmentSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity enrollment_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollment_;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollment_Wrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollment_").o(enrollment_);

	/**	<br/> The entity enrollment_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollment_">Find the entity enrollment_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollment_(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollment_() {
		return enrollment_;
	}

	public void setEnrollment_(SchoolEnrollment enrollment_) {
		this.enrollment_ = enrollment_;
		this.enrollment_Wrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollment_(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	//////////////////
	// schoolNumber //
	//////////////////

	/**	 The entity schoolNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer schoolNumber;
	@JsonIgnore
	public Wrap<Integer> schoolNumberWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolNumber").o(schoolNumber);

	/**	<br/> The entity schoolNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNumber">Find the entity schoolNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolNumber(Wrap<Integer> c);

	public Integer getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(Integer schoolNumber) {
		this.schoolNumber = schoolNumber;
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public void setSchoolNumber(String o) {
		this.schoolNumber = SchoolPayment.staticSetSchoolNumber(siteRequest_, o);
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment schoolNumberInit() {
		if(!schoolNumberWrap.alreadyInitialized) {
			_schoolNumber(schoolNumberWrap);
			if(schoolNumber == null)
				setSchoolNumber(schoolNumberWrap.o);
		}
		schoolNumberWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSchoolNumber(siteRequest_, SchoolPayment.staticSolrSchoolNumber(siteRequest_, SchoolPayment.staticSetSchoolNumber(siteRequest_, o)));
	}

	public Integer solrSchoolNumber() {
		return SchoolPayment.staticSolrSchoolNumber(siteRequest_, schoolNumber);
	}

	public String strSchoolNumber() {
		return schoolNumber == null ? "" : schoolNumber.toString();
	}

	public Integer sqlSchoolNumber() {
		return schoolNumber;
	}

	public String jsonSchoolNumber() {
		return schoolNumber == null ? "" : schoolNumber.toString();
	}

	public String nomAffichageSchoolNumber() {
		return null;
	}

	public String htmTooltipSchoolNumber() {
		return null;
	}

	public String htmSchoolNumber() {
		return schoolNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolNumber());
	}

	//////////////
	// userKeys //
	//////////////

	/**	 The entity userKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> userKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> userKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("userKeys").o(userKeys);

	/**	<br/> The entity userKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKeys">Find the entity userKeys in Solr</a>
	 * <br/>
	 * @param userKeys is the entity already constructed. 
	 **/
	protected abstract void _userKeys(List<Long> l);

	public List<Long> getUserKeys() {
		return userKeys;
	}

	public void setUserKeys(List<Long> userKeys) {
		this.userKeys = userKeys;
		this.userKeysWrap.alreadyInitialized = true;
	}
	public void setUserKeys(String o) {
		Long l = SchoolPayment.staticSetUserKeys(siteRequest_, o);
		if(l != null)
			addUserKeys(l);
		this.userKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetUserKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolPayment addUserKeys(Long...objets) {
		for(Long o : objets) {
			addUserKeys(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addUserKeys(Long o) {
		if(o != null && !userKeys.contains(o))
			this.userKeys.add(o);
		return (SchoolPayment)this;
	}
	public void setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
	}
	public SchoolPayment addUserKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUserKeys(p);
		}
		return (SchoolPayment)this;
	}
	protected SchoolPayment userKeysInit() {
		if(!userKeysWrap.alreadyInitialized) {
			_userKeys(userKeys);
		}
		userKeysWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrUserKeys(siteRequest_, SchoolPayment.staticSolrUserKeys(siteRequest_, SchoolPayment.staticSetUserKeys(siteRequest_, o)));
	}

	public List<Long> solrUserKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : userKeys) {
			l.add(SchoolPayment.staticSolrUserKeys(siteRequest_, o));
		}
		return l;
	}

	public String strUserKeys() {
		return userKeys == null ? "" : userKeys.toString();
	}

	public List<Long> sqlUserKeys() {
		return userKeys;
	}

	public String jsonUserKeys() {
		return userKeys == null ? "" : userKeys.toString();
	}

	public String nomAffichageUserKeys() {
		return null;
	}

	public String htmTooltipUserKeys() {
		return null;
	}

	public String htmUserKeys() {
		return userKeys == null ? "" : StringEscapeUtils.escapeHtml4(strUserKeys());
	}

	///////////////
	// schoolKey //
	///////////////

	/**	 The entity schoolKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/> The entity schoolKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolKey(Wrap<Long> c);

	public Long getSchoolKey() {
		return schoolKey;
	}

	public void setSchoolKey(Long schoolKey) {
		this.schoolKey = schoolKey;
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public void setSchoolKey(String o) {
		this.schoolKey = SchoolPayment.staticSetSchoolKey(siteRequest_, o);
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolPayment schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSchoolKey(siteRequest_, SchoolPayment.staticSolrSchoolKey(siteRequest_, SchoolPayment.staticSetSchoolKey(siteRequest_, o)));
	}

	public Long solrSchoolKey() {
		return SchoolPayment.staticSolrSchoolKey(siteRequest_, schoolKey);
	}

	public String strSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public Long sqlSchoolKey() {
		return schoolKey;
	}

	public String jsonSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String nomAffichageSchoolKey() {
		return null;
	}

	public String htmTooltipSchoolKey() {
		return null;
	}

	public String htmSchoolKey() {
		return schoolKey == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKey());
	}

	///////////////////
	// schoolAddress //
	///////////////////

	/**	 The entity schoolAddress
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAddress;
	@JsonIgnore
	public Wrap<String> schoolAddressWrap = new Wrap<String>().p(this).c(String.class).var("schoolAddress").o(schoolAddress);

	/**	<br/> The entity schoolAddress
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String o) {
		this.schoolAddress = SchoolPayment.staticSetSchoolAddress(siteRequest_, o);
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSchoolAddress(siteRequest_, SchoolPayment.staticSolrSchoolAddress(siteRequest_, SchoolPayment.staticSetSchoolAddress(siteRequest_, o)));
	}

	public String solrSchoolAddress() {
		return SchoolPayment.staticSolrSchoolAddress(siteRequest_, schoolAddress);
	}

	public String strSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String sqlSchoolAddress() {
		return schoolAddress;
	}

	public String jsonSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String nomAffichageSchoolAddress() {
		return null;
	}

	public String htmTooltipSchoolAddress() {
		return null;
	}

	public String htmSchoolAddress() {
		return schoolAddress == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAddress());
	}

	///////////////////////
	// schoolPhoneNumber //
	///////////////////////

	/**	 The entity schoolPhoneNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolPhoneNumber;
	@JsonIgnore
	public Wrap<String> schoolPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("schoolPhoneNumber").o(schoolPhoneNumber);

	/**	<br/> The entity schoolPhoneNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String o) {
		this.schoolPhoneNumber = SchoolPayment.staticSetSchoolPhoneNumber(siteRequest_, o);
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSchoolPhoneNumber(siteRequest_, SchoolPayment.staticSolrSchoolPhoneNumber(siteRequest_, SchoolPayment.staticSetSchoolPhoneNumber(siteRequest_, o)));
	}

	public String solrSchoolPhoneNumber() {
		return SchoolPayment.staticSolrSchoolPhoneNumber(siteRequest_, schoolPhoneNumber);
	}

	public String strSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String sqlSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}

	public String jsonSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String nomAffichageSchoolPhoneNumber() {
		return null;
	}

	public String htmTooltipSchoolPhoneNumber() {
		return null;
	}

	public String htmSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolPhoneNumber());
	}

	/////////////
	// yearKey //
	/////////////

	/**	 The entity yearKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long yearKey;
	@JsonIgnore
	public Wrap<Long> yearKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("yearKey").o(yearKey);

	/**	<br/> The entity yearKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Find the entity yearKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearKey(Wrap<Long> c);

	public Long getYearKey() {
		return yearKey;
	}

	public void setYearKey(Long yearKey) {
		this.yearKey = yearKey;
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public void setYearKey(String o) {
		this.yearKey = SchoolPayment.staticSetYearKey(siteRequest_, o);
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolPayment yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrYearKey(siteRequest_, SchoolPayment.staticSolrYearKey(siteRequest_, SchoolPayment.staticSetYearKey(siteRequest_, o)));
	}

	public Long solrYearKey() {
		return SchoolPayment.staticSolrYearKey(siteRequest_, yearKey);
	}

	public String strYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public Long sqlYearKey() {
		return yearKey;
	}

	public String jsonYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public String nomAffichageYearKey() {
		return "years";
	}

	public String htmTooltipYearKey() {
		return null;
	}

	public String htmYearKey() {
		return yearKey == null ? "" : StringEscapeUtils.escapeHtml4(strYearKey());
	}

	////////////////
	// sessionKey //
	////////////////

	/**	 The entity sessionKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long sessionKey;
	@JsonIgnore
	public Wrap<Long> sessionKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("sessionKey").o(sessionKey);

	/**	<br/> The entity sessionKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Find the entity sessionKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionKey(Wrap<Long> c);

	public Long getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(Long sessionKey) {
		this.sessionKey = sessionKey;
		this.sessionKeyWrap.alreadyInitialized = true;
	}
	public void setSessionKey(String o) {
		this.sessionKey = SchoolPayment.staticSetSessionKey(siteRequest_, o);
		this.sessionKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSessionKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolPayment sessionKeyInit() {
		if(!sessionKeyWrap.alreadyInitialized) {
			_sessionKey(sessionKeyWrap);
			if(sessionKey == null)
				setSessionKey(sessionKeyWrap.o);
		}
		sessionKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrSessionKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSessionKey(siteRequest_, SchoolPayment.staticSolrSessionKey(siteRequest_, SchoolPayment.staticSetSessionKey(siteRequest_, o)));
	}

	public Long solrSessionKey() {
		return SchoolPayment.staticSolrSessionKey(siteRequest_, sessionKey);
	}

	public String strSessionKey() {
		return sessionKey == null ? "" : sessionKey.toString();
	}

	public Long sqlSessionKey() {
		return sessionKey;
	}

	public String jsonSessionKey() {
		return sessionKey == null ? "" : sessionKey.toString();
	}

	public String nomAffichageSessionKey() {
		return "sessions";
	}

	public String htmTooltipSessionKey() {
		return null;
	}

	public String htmSessionKey() {
		return sessionKey == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKey());
	}

	////////////
	// ageKey //
	////////////

	/**	 The entity ageKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long ageKey;
	@JsonIgnore
	public Wrap<Long> ageKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("ageKey").o(ageKey);

	/**	<br/> The entity ageKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Find the entity ageKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageKey(Wrap<Long> c);

	public Long getAgeKey() {
		return ageKey;
	}

	public void setAgeKey(Long ageKey) {
		this.ageKey = ageKey;
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public void setAgeKey(String o) {
		this.ageKey = SchoolPayment.staticSetAgeKey(siteRequest_, o);
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetAgeKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolPayment ageKeyInit() {
		if(!ageKeyWrap.alreadyInitialized) {
			_ageKey(ageKeyWrap);
			if(ageKey == null)
				setAgeKey(ageKeyWrap.o);
		}
		ageKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrAgeKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrAgeKey(siteRequest_, SchoolPayment.staticSolrAgeKey(siteRequest_, SchoolPayment.staticSetAgeKey(siteRequest_, o)));
	}

	public Long solrAgeKey() {
		return SchoolPayment.staticSolrAgeKey(siteRequest_, ageKey);
	}

	public String strAgeKey() {
		return ageKey == null ? "" : ageKey.toString();
	}

	public Long sqlAgeKey() {
		return ageKey;
	}

	public String jsonAgeKey() {
		return ageKey == null ? "" : ageKey.toString();
	}

	public String nomAffichageAgeKey() {
		return "ages";
	}

	public String htmTooltipAgeKey() {
		return null;
	}

	public String htmAgeKey() {
		return ageKey == null ? "" : StringEscapeUtils.escapeHtml4(strAgeKey());
	}

	//////////////
	// blockKey //
	//////////////

	/**	 The entity blockKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long blockKey;
	@JsonIgnore
	public Wrap<Long> blockKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("blockKey").o(blockKey);

	/**	<br/> The entity blockKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Find the entity blockKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockKey(Wrap<Long> c);

	public Long getBlockKey() {
		return blockKey;
	}

	public void setBlockKey(Long blockKey) {
		this.blockKey = blockKey;
		this.blockKeyWrap.alreadyInitialized = true;
	}
	public void setBlockKey(String o) {
		this.blockKey = SchoolPayment.staticSetBlockKey(siteRequest_, o);
		this.blockKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetBlockKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolPayment blockKeyInit() {
		if(!blockKeyWrap.alreadyInitialized) {
			_blockKey(blockKeyWrap);
			if(blockKey == null)
				setBlockKey(blockKeyWrap.o);
		}
		blockKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrBlockKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrBlockKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrBlockKey(siteRequest_, SchoolPayment.staticSolrBlockKey(siteRequest_, SchoolPayment.staticSetBlockKey(siteRequest_, o)));
	}

	public Long solrBlockKey() {
		return SchoolPayment.staticSolrBlockKey(siteRequest_, blockKey);
	}

	public String strBlockKey() {
		return blockKey == null ? "" : blockKey.toString();
	}

	public Long sqlBlockKey() {
		return blockKey;
	}

	public String jsonBlockKey() {
		return blockKey == null ? "" : blockKey.toString();
	}

	public String nomAffichageBlockKey() {
		return "sessions";
	}

	public String htmTooltipBlockKey() {
		return null;
	}

	public String htmBlockKey() {
		return blockKey == null ? "" : StringEscapeUtils.escapeHtml4(strBlockKey());
	}

	//////////////
	// childKey //
	//////////////

	/**	 The entity childKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long childKey;
	@JsonIgnore
	public Wrap<Long> childKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("childKey").o(childKey);

	/**	<br/> The entity childKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Find the entity childKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childKey(Wrap<Long> c);

	public Long getChildKey() {
		return childKey;
	}

	public void setChildKey(Long childKey) {
		this.childKey = childKey;
		this.childKeyWrap.alreadyInitialized = true;
	}
	public void setChildKey(String o) {
		this.childKey = SchoolPayment.staticSetChildKey(siteRequest_, o);
		this.childKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetChildKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolPayment childKeyInit() {
		if(!childKeyWrap.alreadyInitialized) {
			_childKey(childKeyWrap);
			if(childKey == null)
				setChildKey(childKeyWrap.o);
		}
		childKeyWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Long staticSolrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChildKey(siteRequest_, SchoolPayment.staticSolrChildKey(siteRequest_, SchoolPayment.staticSetChildKey(siteRequest_, o)));
	}

	public Long solrChildKey() {
		return SchoolPayment.staticSolrChildKey(siteRequest_, childKey);
	}

	public String strChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public Long sqlChildKey() {
		return childKey;
	}

	public String jsonChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public String nomAffichageChildKey() {
		return "childs";
	}

	public String htmTooltipChildKey() {
		return null;
	}

	public String htmChildKey() {
		return childKey == null ? "" : StringEscapeUtils.escapeHtml4(strChildKey());
	}

	/////////////
	// momKeys //
	/////////////

	/**	 The entity momKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> momKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> momKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("momKeys").o(momKeys);

	/**	<br/> The entity momKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momKeys">Find the entity momKeys in Solr</a>
	 * <br/>
	 * @param momKeys is the entity already constructed. 
	 **/
	protected abstract void _momKeys(List<Long> l);

	public List<Long> getMomKeys() {
		return momKeys;
	}

	public void setMomKeys(List<Long> momKeys) {
		this.momKeys = momKeys;
		this.momKeysWrap.alreadyInitialized = true;
	}
	public void setMomKeys(String o) {
		Long l = SchoolPayment.staticSetMomKeys(siteRequest_, o);
		if(l != null)
			addMomKeys(l);
		this.momKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetMomKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setMomKeys(JsonArray objets) {
		momKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMomKeys(o);
		}
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

	public static Long staticSolrMomKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrMomKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMomKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrMomKeys(siteRequest_, SchoolPayment.staticSolrMomKeys(siteRequest_, SchoolPayment.staticSetMomKeys(siteRequest_, o)));
	}

	public List<Long> solrMomKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : momKeys) {
			l.add(SchoolPayment.staticSolrMomKeys(siteRequest_, o));
		}
		return l;
	}

	public String strMomKeys() {
		return momKeys == null ? "" : momKeys.toString();
	}

	public List<Long> sqlMomKeys() {
		return momKeys;
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

	/**	 The entity dadKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> dadKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> dadKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("dadKeys").o(dadKeys);

	/**	<br/> The entity dadKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadKeys">Find the entity dadKeys in Solr</a>
	 * <br/>
	 * @param dadKeys is the entity already constructed. 
	 **/
	protected abstract void _dadKeys(List<Long> l);

	public List<Long> getDadKeys() {
		return dadKeys;
	}

	public void setDadKeys(List<Long> dadKeys) {
		this.dadKeys = dadKeys;
		this.dadKeysWrap.alreadyInitialized = true;
	}
	public void setDadKeys(String o) {
		Long l = SchoolPayment.staticSetDadKeys(siteRequest_, o);
		if(l != null)
			addDadKeys(l);
		this.dadKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetDadKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setDadKeys(JsonArray objets) {
		dadKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDadKeys(o);
		}
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

	public static Long staticSolrDadKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrDadKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrDadKeys(siteRequest_, SchoolPayment.staticSolrDadKeys(siteRequest_, SchoolPayment.staticSetDadKeys(siteRequest_, o)));
	}

	public List<Long> solrDadKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : dadKeys) {
			l.add(SchoolPayment.staticSolrDadKeys(siteRequest_, o));
		}
		return l;
	}

	public String strDadKeys() {
		return dadKeys == null ? "" : dadKeys.toString();
	}

	public List<Long> sqlDadKeys() {
		return dadKeys;
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

	/**	 The entity guardianKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> guardianKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> guardianKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("guardianKeys").o(guardianKeys);

	/**	<br/> The entity guardianKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianKeys">Find the entity guardianKeys in Solr</a>
	 * <br/>
	 * @param guardianKeys is the entity already constructed. 
	 **/
	protected abstract void _guardianKeys(List<Long> l);

	public List<Long> getGuardianKeys() {
		return guardianKeys;
	}

	public void setGuardianKeys(List<Long> guardianKeys) {
		this.guardianKeys = guardianKeys;
		this.guardianKeysWrap.alreadyInitialized = true;
	}
	public void setGuardianKeys(String o) {
		Long l = SchoolPayment.staticSetGuardianKeys(siteRequest_, o);
		if(l != null)
			addGuardianKeys(l);
		this.guardianKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetGuardianKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setGuardianKeys(JsonArray objets) {
		guardianKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGuardianKeys(o);
		}
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

	public static Long staticSolrGuardianKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrGuardianKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGuardianKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrGuardianKeys(siteRequest_, SchoolPayment.staticSolrGuardianKeys(siteRequest_, SchoolPayment.staticSetGuardianKeys(siteRequest_, o)));
	}

	public List<Long> solrGuardianKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : guardianKeys) {
			l.add(SchoolPayment.staticSolrGuardianKeys(siteRequest_, o));
		}
		return l;
	}

	public String strGuardianKeys() {
		return guardianKeys == null ? "" : guardianKeys.toString();
	}

	public List<Long> sqlGuardianKeys() {
		return guardianKeys;
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

	////////////////////////////////
	// childCompleteNamePreferred //
	////////////////////////////////

	/**	 The entity childCompleteNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> childCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("childCompleteNamePreferred").o(childCompleteNamePreferred);

	/**	<br/> The entity childCompleteNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childCompleteNamePreferred">Find the entity childCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childCompleteNamePreferred(Wrap<String> c);

	public String getChildCompleteNamePreferred() {
		return childCompleteNamePreferred;
	}
	public void setChildCompleteNamePreferred(String o) {
		this.childCompleteNamePreferred = SchoolPayment.staticSetChildCompleteNamePreferred(siteRequest_, o);
		this.childCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment childCompleteNamePreferredInit() {
		if(!childCompleteNamePreferredWrap.alreadyInitialized) {
			_childCompleteNamePreferred(childCompleteNamePreferredWrap);
			if(childCompleteNamePreferred == null)
				setChildCompleteNamePreferred(childCompleteNamePreferredWrap.o);
		}
		childCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChildCompleteNamePreferred(siteRequest_, SchoolPayment.staticSolrChildCompleteNamePreferred(siteRequest_, SchoolPayment.staticSetChildCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrChildCompleteNamePreferred() {
		return SchoolPayment.staticSolrChildCompleteNamePreferred(siteRequest_, childCompleteNamePreferred);
	}

	public String strChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : childCompleteNamePreferred;
	}

	public String sqlChildCompleteNamePreferred() {
		return childCompleteNamePreferred;
	}

	public String jsonChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : childCompleteNamePreferred;
	}

	public String nomAffichageChildCompleteNamePreferred() {
		return null;
	}

	public String htmTooltipChildCompleteNamePreferred() {
		return null;
	}

	public String htmChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strChildCompleteNamePreferred());
	}

	////////////////////
	// childBirthDate //
	////////////////////

	/**	 The entity childBirthDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate childBirthDate;
	@JsonIgnore
	public Wrap<LocalDate> childBirthDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("childBirthDate").o(childBirthDate);

	/**	<br/> The entity childBirthDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthDate">Find the entity childBirthDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childBirthDate(Wrap<LocalDate> c);

	public LocalDate getChildBirthDate() {
		return childBirthDate;
	}

	public void setChildBirthDate(LocalDate childBirthDate) {
		this.childBirthDate = childBirthDate;
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	public void setChildBirthDate(Instant o) {
		this.childBirthDate = o == null ? null : LocalDate.from(o);
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setChildBirthDate(String o) {
		this.childBirthDate = SchoolPayment.staticSetChildBirthDate(siteRequest_, o);
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetChildBirthDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setChildBirthDate(Date o) {
		this.childBirthDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	protected SchoolPayment childBirthDateInit() {
		if(!childBirthDateWrap.alreadyInitialized) {
			_childBirthDate(childBirthDateWrap);
			if(childBirthDate == null)
				setChildBirthDate(childBirthDateWrap.o);
		}
		childBirthDateWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Date staticSolrChildBirthDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrChildBirthDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqChildBirthDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChildBirthDate(siteRequest_, SchoolPayment.staticSolrChildBirthDate(siteRequest_, SchoolPayment.staticSetChildBirthDate(siteRequest_, o)));
	}

	public Date solrChildBirthDate() {
		return SchoolPayment.staticSolrChildBirthDate(siteRequest_, childBirthDate);
	}

	public String strChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlChildBirthDate() {
		return childBirthDate;
	}

	public String jsonChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageChildBirthDate() {
		return null;
	}

	public String htmTooltipChildBirthDate() {
		return null;
	}

	public String htmChildBirthDate() {
		return childBirthDate == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthDate());
	}

	//////////////////////////////
	// momCompleteNamePreferred //
	//////////////////////////////

	/**	 The entity momCompleteNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String momCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> momCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("momCompleteNamePreferred").o(momCompleteNamePreferred);

	/**	<br/> The entity momCompleteNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momCompleteNamePreferred">Find the entity momCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _momCompleteNamePreferred(Wrap<String> c);

	public String getMomCompleteNamePreferred() {
		return momCompleteNamePreferred;
	}
	public void setMomCompleteNamePreferred(String o) {
		this.momCompleteNamePreferred = SchoolPayment.staticSetMomCompleteNamePreferred(siteRequest_, o);
		this.momCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment momCompleteNamePreferredInit() {
		if(!momCompleteNamePreferredWrap.alreadyInitialized) {
			_momCompleteNamePreferred(momCompleteNamePreferredWrap);
			if(momCompleteNamePreferred == null)
				setMomCompleteNamePreferred(momCompleteNamePreferredWrap.o);
		}
		momCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrMomCompleteNamePreferred(siteRequest_, SchoolPayment.staticSolrMomCompleteNamePreferred(siteRequest_, SchoolPayment.staticSetMomCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrMomCompleteNamePreferred() {
		return SchoolPayment.staticSolrMomCompleteNamePreferred(siteRequest_, momCompleteNamePreferred);
	}

	public String strMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : momCompleteNamePreferred;
	}

	public String sqlMomCompleteNamePreferred() {
		return momCompleteNamePreferred;
	}

	public String jsonMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : momCompleteNamePreferred;
	}

	public String nomAffichageMomCompleteNamePreferred() {
		return null;
	}

	public String htmTooltipMomCompleteNamePreferred() {
		return null;
	}

	public String htmMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strMomCompleteNamePreferred());
	}

	//////////////////////////////
	// dadCompleteNamePreferred //
	//////////////////////////////

	/**	 The entity dadCompleteNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String dadCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> dadCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("dadCompleteNamePreferred").o(dadCompleteNamePreferred);

	/**	<br/> The entity dadCompleteNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadCompleteNamePreferred">Find the entity dadCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dadCompleteNamePreferred(Wrap<String> c);

	public String getDadCompleteNamePreferred() {
		return dadCompleteNamePreferred;
	}
	public void setDadCompleteNamePreferred(String o) {
		this.dadCompleteNamePreferred = SchoolPayment.staticSetDadCompleteNamePreferred(siteRequest_, o);
		this.dadCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment dadCompleteNamePreferredInit() {
		if(!dadCompleteNamePreferredWrap.alreadyInitialized) {
			_dadCompleteNamePreferred(dadCompleteNamePreferredWrap);
			if(dadCompleteNamePreferred == null)
				setDadCompleteNamePreferred(dadCompleteNamePreferredWrap.o);
		}
		dadCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrDadCompleteNamePreferred(siteRequest_, SchoolPayment.staticSolrDadCompleteNamePreferred(siteRequest_, SchoolPayment.staticSetDadCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrDadCompleteNamePreferred() {
		return SchoolPayment.staticSolrDadCompleteNamePreferred(siteRequest_, dadCompleteNamePreferred);
	}

	public String strDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : dadCompleteNamePreferred;
	}

	public String sqlDadCompleteNamePreferred() {
		return dadCompleteNamePreferred;
	}

	public String jsonDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : dadCompleteNamePreferred;
	}

	public String nomAffichageDadCompleteNamePreferred() {
		return null;
	}

	public String htmTooltipDadCompleteNamePreferred() {
		return null;
	}

	public String htmDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strDadCompleteNamePreferred());
	}

	////////////////
	// schoolName //
	////////////////

	/**	 The entity schoolName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/> The entity schoolName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Find the entity schoolName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String o) {
		this.schoolName = SchoolPayment.staticSetSchoolName(siteRequest_, o);
		this.schoolNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSchoolName(siteRequest_, SchoolPayment.staticSolrSchoolName(siteRequest_, SchoolPayment.staticSetSchoolName(siteRequest_, o)));
	}

	public String solrSchoolName() {
		return SchoolPayment.staticSolrSchoolName(siteRequest_, schoolName);
	}

	public String strSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String sqlSchoolName() {
		return schoolName;
	}

	public String jsonSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String nomAffichageSchoolName() {
		return null;
	}

	public String htmTooltipSchoolName() {
		return null;
	}

	public String htmSchoolName() {
		return schoolName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolName());
	}

	////////////////////////
	// schoolCompleteName //
	////////////////////////

	/**	 The entity schoolCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/> The entity schoolCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Find the entity schoolCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}
	public void setSchoolCompleteName(String o) {
		this.schoolCompleteName = SchoolPayment.staticSetSchoolCompleteName(siteRequest_, o);
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSchoolCompleteName(siteRequest_, SchoolPayment.staticSolrSchoolCompleteName(siteRequest_, SchoolPayment.staticSetSchoolCompleteName(siteRequest_, o)));
	}

	public String solrSchoolCompleteName() {
		return SchoolPayment.staticSolrSchoolCompleteName(siteRequest_, schoolCompleteName);
	}

	public String strSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String sqlSchoolCompleteName() {
		return schoolCompleteName;
	}

	public String jsonSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String nomAffichageSchoolCompleteName() {
		return null;
	}

	public String htmTooltipSchoolCompleteName() {
		return null;
	}

	public String htmSchoolCompleteName() {
		return schoolCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolCompleteName());
	}

	////////////////////
	// schoolLocation //
	////////////////////

	/**	 The entity schoolLocation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation;
	@JsonIgnore
	public Wrap<String> schoolLocationWrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation").o(schoolLocation);

	/**	<br/> The entity schoolLocation
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Find the entity schoolLocation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String o) {
		this.schoolLocation = SchoolPayment.staticSetSchoolLocation(siteRequest_, o);
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSchoolLocation(siteRequest_, SchoolPayment.staticSolrSchoolLocation(siteRequest_, SchoolPayment.staticSetSchoolLocation(siteRequest_, o)));
	}

	public String solrSchoolLocation() {
		return SchoolPayment.staticSolrSchoolLocation(siteRequest_, schoolLocation);
	}

	public String strSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String sqlSchoolLocation() {
		return schoolLocation;
	}

	public String jsonSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String nomAffichageSchoolLocation() {
		return "location";
	}

	public String htmTooltipSchoolLocation() {
		return null;
	}

	public String htmSchoolLocation() {
		return schoolLocation == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolLocation());
	}

	///////////////
	// yearStart //
	///////////////

	/**	 The entity yearStart
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/> The entity yearStart
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Find the entity yearStart in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearStart(Wrap<Integer> c);

	public Integer getYearStart() {
		return yearStart;
	}

	public void setYearStart(Integer yearStart) {
		this.yearStart = yearStart;
		this.yearStartWrap.alreadyInitialized = true;
	}
	public void setYearStart(String o) {
		this.yearStart = SchoolPayment.staticSetYearStart(siteRequest_, o);
		this.yearStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrYearStart(siteRequest_, SchoolPayment.staticSolrYearStart(siteRequest_, SchoolPayment.staticSetYearStart(siteRequest_, o)));
	}

	public Integer solrYearStart() {
		return SchoolPayment.staticSolrYearStart(siteRequest_, yearStart);
	}

	public String strYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public Integer sqlYearStart() {
		return yearStart;
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

	/////////////
	// yearEnd //
	/////////////

	/**	 The entity yearEnd
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/> The entity yearEnd
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Find the entity yearEnd in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearEnd(Wrap<Integer> c);

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
		this.yearEndWrap.alreadyInitialized = true;
	}
	public void setYearEnd(String o) {
		this.yearEnd = SchoolPayment.staticSetYearEnd(siteRequest_, o);
		this.yearEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrYearEnd(siteRequest_, SchoolPayment.staticSolrYearEnd(siteRequest_, SchoolPayment.staticSetYearEnd(siteRequest_, o)));
	}

	public Integer solrYearEnd() {
		return SchoolPayment.staticSolrYearEnd(siteRequest_, yearEnd);
	}

	public String strYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public Integer sqlYearEnd() {
		return yearEnd;
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

	/////////////////////
	// seasonStartDate //
	/////////////////////

	/**	 The entity seasonStartDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate seasonStartDate;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDate").o(seasonStartDate);

	/**	<br/> The entity seasonStartDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Find the entity seasonStartDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonStartDate(Wrap<LocalDate> c);

	public LocalDate getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(LocalDate seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public void setSeasonStartDate(Instant o) {
		this.seasonStartDate = o == null ? null : LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSeasonStartDate(String o) {
		this.seasonStartDate = SchoolPayment.staticSetSeasonStartDate(siteRequest_, o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	protected SchoolPayment seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Date staticSolrSeasonStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSeasonStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSeasonStartDate(siteRequest_, SchoolPayment.staticSolrSeasonStartDate(siteRequest_, SchoolPayment.staticSetSeasonStartDate(siteRequest_, o)));
	}

	public Date solrSeasonStartDate() {
		return SchoolPayment.staticSolrSeasonStartDate(siteRequest_, seasonStartDate);
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlSeasonStartDate() {
		return seasonStartDate;
	}

	public String jsonSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ISO_DATE);
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

	///////////////////////
	// yearEnrollmentFee //
	///////////////////////

	/**	 The entity yearEnrollmentFee
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal yearEnrollmentFee;
	@JsonIgnore
	public Wrap<BigDecimal> yearEnrollmentFeeWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("yearEnrollmentFee").o(yearEnrollmentFee);

	/**	<br/> The entity yearEnrollmentFee
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Find the entity yearEnrollmentFee in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearEnrollmentFee(Wrap<BigDecimal> c);

	public BigDecimal getYearEnrollmentFee() {
		return yearEnrollmentFee;
	}

	public void setYearEnrollmentFee(BigDecimal yearEnrollmentFee) {
		this.yearEnrollmentFee = yearEnrollmentFee;
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public void setYearEnrollmentFee(String o) {
		this.yearEnrollmentFee = SchoolPayment.staticSetYearEnrollmentFee(siteRequest_, o);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetYearEnrollmentFee(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setYearEnrollmentFee(Double o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public void setYearEnrollmentFee(Integer o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	protected SchoolPayment yearEnrollmentFeeInit() {
		if(!yearEnrollmentFeeWrap.alreadyInitialized) {
			_yearEnrollmentFee(yearEnrollmentFeeWrap);
			if(yearEnrollmentFee == null)
				setYearEnrollmentFee(yearEnrollmentFeeWrap.o);
		}
		yearEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrYearEnrollmentFee(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrYearEnrollmentFee(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnrollmentFee(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrYearEnrollmentFee(siteRequest_, SchoolPayment.staticSolrYearEnrollmentFee(siteRequest_, SchoolPayment.staticSetYearEnrollmentFee(siteRequest_, o)));
	}

	public Double solrYearEnrollmentFee() {
		return SchoolPayment.staticSolrYearEnrollmentFee(siteRequest_, yearEnrollmentFee);
	}

	public String strYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlYearEnrollmentFee() {
		return yearEnrollmentFee;
	}

	public String jsonYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.toString();
	}

	public String nomAffichageYearEnrollmentFee() {
		return "end of year";
	}

	public String htmTooltipYearEnrollmentFee() {
		return null;
	}

	public String htmYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : StringEscapeUtils.escapeHtml4(strYearEnrollmentFee());
	}

	//////////////////////
	// sessionStartDate //
	//////////////////////

	/**	 The entity sessionStartDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionStartDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionStartDate").o(sessionStartDate);

	/**	<br/> The entity sessionStartDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDate">Find the entity sessionStartDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionStartDate(Wrap<LocalDate> c);

	public LocalDate getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(LocalDate sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public void setSessionStartDate(Instant o) {
		this.sessionStartDate = o == null ? null : LocalDate.from(o);
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionStartDate(String o) {
		this.sessionStartDate = SchoolPayment.staticSetSessionStartDate(siteRequest_, o);
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionStartDate(Date o) {
		this.sessionStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	protected SchoolPayment sessionStartDateInit() {
		if(!sessionStartDateWrap.alreadyInitialized) {
			_sessionStartDate(sessionStartDateWrap);
			if(sessionStartDate == null)
				setSessionStartDate(sessionStartDateWrap.o);
		}
		sessionStartDateWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Date staticSolrSessionStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSessionStartDate(siteRequest_, SchoolPayment.staticSolrSessionStartDate(siteRequest_, SchoolPayment.staticSetSessionStartDate(siteRequest_, o)));
	}

	public Date solrSessionStartDate() {
		return SchoolPayment.staticSolrSessionStartDate(siteRequest_, sessionStartDate);
	}

	public String strSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlSessionStartDate() {
		return sessionStartDate;
	}

	public String jsonSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionStartDate() {
		return "start of the session";
	}

	public String htmTooltipSessionStartDate() {
		return null;
	}

	public String htmSessionStartDate() {
		return sessionStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSessionStartDate());
	}

	////////////////////
	// sessionEndDate //
	////////////////////

	/**	 The entity sessionEndDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionEndDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionEndDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionEndDate").o(sessionEndDate);

	/**	<br/> The entity sessionEndDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDate">Find the entity sessionEndDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionEndDate(Wrap<LocalDate> c);

	public LocalDate getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(LocalDate sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public void setSessionEndDate(Instant o) {
		this.sessionEndDate = o == null ? null : LocalDate.from(o);
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionEndDate(String o) {
		this.sessionEndDate = SchoolPayment.staticSetSessionEndDate(siteRequest_, o);
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionEndDate(Date o) {
		this.sessionEndDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	protected SchoolPayment sessionEndDateInit() {
		if(!sessionEndDateWrap.alreadyInitialized) {
			_sessionEndDate(sessionEndDateWrap);
			if(sessionEndDate == null)
				setSessionEndDate(sessionEndDateWrap.o);
		}
		sessionEndDateWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Date staticSolrSessionEndDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionEndDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrSessionEndDate(siteRequest_, SchoolPayment.staticSolrSessionEndDate(siteRequest_, SchoolPayment.staticSetSessionEndDate(siteRequest_, o)));
	}

	public Date solrSessionEndDate() {
		return SchoolPayment.staticSolrSessionEndDate(siteRequest_, sessionEndDate);
	}

	public String strSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlSessionEndDate() {
		return sessionEndDate;
	}

	public String jsonSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionEndDate() {
		return "end of the session";
	}

	public String htmTooltipSessionEndDate() {
		return null;
	}

	public String htmSessionEndDate() {
		return sessionEndDate == null ? "" : StringEscapeUtils.escapeHtml4(strSessionEndDate());
	}

	//////////////
	// ageStart //
	//////////////

	/**	 The entity ageStart
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageStart;
	@JsonIgnore
	public Wrap<Integer> ageStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageStart").o(ageStart);

	/**	<br/> The entity ageStart
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Find the entity ageStart in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageStart(Wrap<Integer> c);

	public Integer getAgeStart() {
		return ageStart;
	}

	public void setAgeStart(Integer ageStart) {
		this.ageStart = ageStart;
		this.ageStartWrap.alreadyInitialized = true;
	}
	public void setAgeStart(String o) {
		this.ageStart = SchoolPayment.staticSetAgeStart(siteRequest_, o);
		this.ageStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment ageStartInit() {
		if(!ageStartWrap.alreadyInitialized) {
			_ageStart(ageStartWrap);
			if(ageStart == null)
				setAgeStart(ageStartWrap.o);
		}
		ageStartWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrAgeStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrAgeStart(siteRequest_, SchoolPayment.staticSolrAgeStart(siteRequest_, SchoolPayment.staticSetAgeStart(siteRequest_, o)));
	}

	public Integer solrAgeStart() {
		return SchoolPayment.staticSolrAgeStart(siteRequest_, ageStart);
	}

	public String strAgeStart() {
		return ageStart == null ? "" : ageStart.toString();
	}

	public Integer sqlAgeStart() {
		return ageStart;
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

	////////////
	// ageEnd //
	////////////

	/**	 The entity ageEnd
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageEnd;
	@JsonIgnore
	public Wrap<Integer> ageEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageEnd").o(ageEnd);

	/**	<br/> The entity ageEnd
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Find the entity ageEnd in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageEnd(Wrap<Integer> c);

	public Integer getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
		this.ageEndWrap.alreadyInitialized = true;
	}
	public void setAgeEnd(String o) {
		this.ageEnd = SchoolPayment.staticSetAgeEnd(siteRequest_, o);
		this.ageEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment ageEndInit() {
		if(!ageEndWrap.alreadyInitialized) {
			_ageEnd(ageEndWrap);
			if(ageEnd == null)
				setAgeEnd(ageEndWrap.o);
		}
		ageEndWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrAgeEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrAgeEnd(siteRequest_, SchoolPayment.staticSolrAgeEnd(siteRequest_, SchoolPayment.staticSetAgeEnd(siteRequest_, o)));
	}

	public Integer solrAgeEnd() {
		return SchoolPayment.staticSolrAgeEnd(siteRequest_, ageEnd);
	}

	public String strAgeEnd() {
		return ageEnd == null ? "" : ageEnd.toString();
	}

	public Integer sqlAgeEnd() {
		return ageEnd;
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

	///////////////////////
	// blockCompleteName //
	///////////////////////

	/**	 The entity blockCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockCompleteName;
	@JsonIgnore
	public Wrap<String> blockCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("blockCompleteName").o(blockCompleteName);

	/**	<br/> The entity blockCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockCompleteName">Find the entity blockCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockCompleteName(Wrap<String> c);

	public String getBlockCompleteName() {
		return blockCompleteName;
	}
	public void setBlockCompleteName(String o) {
		this.blockCompleteName = SchoolPayment.staticSetBlockCompleteName(siteRequest_, o);
		this.blockCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment blockCompleteNameInit() {
		if(!blockCompleteNameWrap.alreadyInitialized) {
			_blockCompleteName(blockCompleteNameWrap);
			if(blockCompleteName == null)
				setBlockCompleteName(blockCompleteNameWrap.o);
		}
		blockCompleteNameWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrBlockCompleteName(siteRequest_, SchoolPayment.staticSolrBlockCompleteName(siteRequest_, SchoolPayment.staticSetBlockCompleteName(siteRequest_, o)));
	}

	public String solrBlockCompleteName() {
		return SchoolPayment.staticSolrBlockCompleteName(siteRequest_, blockCompleteName);
	}

	public String strBlockCompleteName() {
		return blockCompleteName == null ? "" : blockCompleteName;
	}

	public String sqlBlockCompleteName() {
		return blockCompleteName;
	}

	public String jsonBlockCompleteName() {
		return blockCompleteName == null ? "" : blockCompleteName;
	}

	public String nomAffichageBlockCompleteName() {
		return "block complete name";
	}

	public String htmTooltipBlockCompleteName() {
		return null;
	}

	public String htmBlockCompleteName() {
		return blockCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockCompleteName());
	}

	////////////////////
	// blockStartTime //
	////////////////////

	/**	 The entity blockStartTime
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blockStartTime;
	@JsonIgnore
	public Wrap<LocalTime> blockStartTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockStartTime").o(blockStartTime);

	/**	<br/> The entity blockStartTime
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockStartTime">Find the entity blockStartTime in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
	public void setBlockStartTime(String o) {
		this.blockStartTime = SchoolPayment.staticSetBlockStartTime(siteRequest_, o);
		this.blockStartTimeWrap.alreadyInitialized = true;
	}
	public static LocalTime staticSetBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
	}
	protected SchoolPayment blockStartTimeInit() {
		if(!blockStartTimeWrap.alreadyInitialized) {
			_blockStartTime(blockStartTimeWrap);
			if(blockStartTime == null)
				setBlockStartTime(blockStartTimeWrap.o);
		}
		blockStartTimeWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrBlockStartTime(SiteRequestEnUS siteRequest_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrBlockStartTime(siteRequest_, SchoolPayment.staticSolrBlockStartTime(siteRequest_, SchoolPayment.staticSetBlockStartTime(siteRequest_, o)));
	}

	public String solrBlockStartTime() {
		return SchoolPayment.staticSolrBlockStartTime(siteRequest_, blockStartTime);
	}

	public String strBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")));
	}

	public LocalTime sqlBlockStartTime() {
		return blockStartTime;
	}

	public String jsonBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ISO_TIME);
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

	//////////////////
	// blockEndTime //
	//////////////////

	/**	 The entity blockEndTime
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blockEndTime;
	@JsonIgnore
	public Wrap<LocalTime> blockEndTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockEndTime").o(blockEndTime);

	/**	<br/> The entity blockEndTime
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockEndTime">Find the entity blockEndTime in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
	public void setBlockEndTime(String o) {
		this.blockEndTime = SchoolPayment.staticSetBlockEndTime(siteRequest_, o);
		this.blockEndTimeWrap.alreadyInitialized = true;
	}
	public static LocalTime staticSetBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
	}
	protected SchoolPayment blockEndTimeInit() {
		if(!blockEndTimeWrap.alreadyInitialized) {
			_blockEndTime(blockEndTimeWrap);
			if(blockEndTime == null)
				setBlockEndTime(blockEndTimeWrap.o);
		}
		blockEndTimeWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrBlockEndTime(SiteRequestEnUS siteRequest_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrBlockEndTime(siteRequest_, SchoolPayment.staticSolrBlockEndTime(siteRequest_, SchoolPayment.staticSetBlockEndTime(siteRequest_, o)));
	}

	public String solrBlockEndTime() {
		return SchoolPayment.staticSolrBlockEndTime(siteRequest_, blockEndTime);
	}

	public String strBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")));
	}

	public LocalTime sqlBlockEndTime() {
		return blockEndTime;
	}

	public String jsonBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ISO_TIME);
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

	////////////////////////
	// blockPricePerMonth //
	////////////////////////

	/**	 The entity blockPricePerMonth
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blockPricePerMonth;
	@JsonIgnore
	public Wrap<BigDecimal> blockPricePerMonthWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockPricePerMonth").o(blockPricePerMonth);

	/**	<br/> The entity blockPricePerMonth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Find the entity blockPricePerMonth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockPricePerMonth(Wrap<BigDecimal> c);

	public BigDecimal getBlockPricePerMonth() {
		return blockPricePerMonth;
	}

	public void setBlockPricePerMonth(BigDecimal blockPricePerMonth) {
		this.blockPricePerMonth = blockPricePerMonth;
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public void setBlockPricePerMonth(String o) {
		this.blockPricePerMonth = SchoolPayment.staticSetBlockPricePerMonth(siteRequest_, o);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetBlockPricePerMonth(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setBlockPricePerMonth(Double o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public void setBlockPricePerMonth(Integer o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	protected SchoolPayment blockPricePerMonthInit() {
		if(!blockPricePerMonthWrap.alreadyInitialized) {
			_blockPricePerMonth(blockPricePerMonthWrap);
			if(blockPricePerMonth == null)
				setBlockPricePerMonth(blockPricePerMonthWrap.o);
		}
		blockPricePerMonthWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrBlockPricePerMonth(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlockPricePerMonth(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockPricePerMonth(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrBlockPricePerMonth(siteRequest_, SchoolPayment.staticSolrBlockPricePerMonth(siteRequest_, SchoolPayment.staticSetBlockPricePerMonth(siteRequest_, o)));
	}

	public Double solrBlockPricePerMonth() {
		return SchoolPayment.staticSolrBlockPricePerMonth(siteRequest_, blockPricePerMonth);
	}

	public String strBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlBlockPricePerMonth() {
		return blockPricePerMonth;
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

	/////////////////////////
	// enrollmentGroupName //
	/////////////////////////

	/**	 The entity enrollmentGroupName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentGroupName;
	@JsonIgnore
	public Wrap<String> enrollmentGroupNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentGroupName").o(enrollmentGroupName);

	/**	<br/> The entity enrollmentGroupName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroupName">Find the entity enrollmentGroupName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentGroupName(Wrap<String> c);

	public String getEnrollmentGroupName() {
		return enrollmentGroupName;
	}
	public void setEnrollmentGroupName(String o) {
		this.enrollmentGroupName = SchoolPayment.staticSetEnrollmentGroupName(siteRequest_, o);
		this.enrollmentGroupNameWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment enrollmentGroupNameInit() {
		if(!enrollmentGroupNameWrap.alreadyInitialized) {
			_enrollmentGroupName(enrollmentGroupNameWrap);
			if(enrollmentGroupName == null)
				setEnrollmentGroupName(enrollmentGroupNameWrap.o);
		}
		enrollmentGroupNameWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrEnrollmentGroupName(siteRequest_, SchoolPayment.staticSolrEnrollmentGroupName(siteRequest_, SchoolPayment.staticSetEnrollmentGroupName(siteRequest_, o)));
	}

	public String solrEnrollmentGroupName() {
		return SchoolPayment.staticSolrEnrollmentGroupName(siteRequest_, enrollmentGroupName);
	}

	public String strEnrollmentGroupName() {
		return enrollmentGroupName == null ? "" : enrollmentGroupName;
	}

	public String sqlEnrollmentGroupName() {
		return enrollmentGroupName;
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

	/////////////////////
	// blockTotalPrice //
	/////////////////////

	/**	 The entity blockTotalPrice
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blockTotalPrice;
	@JsonIgnore
	public Wrap<BigDecimal> blockTotalPriceWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockTotalPrice").o(blockTotalPrice);

	/**	<br/> The entity blockTotalPrice
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTotalPrice">Find the entity blockTotalPrice in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockTotalPrice(Wrap<BigDecimal> c);

	public BigDecimal getBlockTotalPrice() {
		return blockTotalPrice;
	}

	public void setBlockTotalPrice(BigDecimal blockTotalPrice) {
		this.blockTotalPrice = blockTotalPrice;
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public void setBlockTotalPrice(String o) {
		this.blockTotalPrice = SchoolPayment.staticSetBlockTotalPrice(siteRequest_, o);
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetBlockTotalPrice(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setBlockTotalPrice(Double o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public void setBlockTotalPrice(Integer o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	protected SchoolPayment blockTotalPriceInit() {
		if(!blockTotalPriceWrap.alreadyInitialized) {
			_blockTotalPrice(blockTotalPriceWrap);
			if(blockTotalPrice == null)
				setBlockTotalPrice(blockTotalPriceWrap.o);
		}
		blockTotalPriceWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrBlockTotalPrice(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlockTotalPrice(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockTotalPrice(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrBlockTotalPrice(siteRequest_, SchoolPayment.staticSolrBlockTotalPrice(siteRequest_, SchoolPayment.staticSetBlockTotalPrice(siteRequest_, o)));
	}

	public Double solrBlockTotalPrice() {
		return SchoolPayment.staticSolrBlockTotalPrice(siteRequest_, blockTotalPrice);
	}

	public String strBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlBlockTotalPrice() {
		return blockTotalPrice;
	}

	public String jsonBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.toString();
	}

	public String nomAffichageBlockTotalPrice() {
		return "total price";
	}

	public String htmTooltipBlockTotalPrice() {
		return null;
	}

	public String htmBlockTotalPrice() {
		return blockTotalPrice == null ? "" : StringEscapeUtils.escapeHtml4(strBlockTotalPrice());
	}

	////////////////////////////////
	// enrollmentPaymentEachMonth //
	////////////////////////////////

	/**	 The entity enrollmentPaymentEachMonth
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enrollmentPaymentEachMonth;
	@JsonIgnore
	public Wrap<Boolean> enrollmentPaymentEachMonthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentPaymentEachMonth").o(enrollmentPaymentEachMonth);

	/**	<br/> The entity enrollmentPaymentEachMonth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPaymentEachMonth">Find the entity enrollmentPaymentEachMonth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentPaymentEachMonth(Wrap<Boolean> c);

	public Boolean getEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth;
	}

	public void setEnrollmentPaymentEachMonth(Boolean enrollmentPaymentEachMonth) {
		this.enrollmentPaymentEachMonth = enrollmentPaymentEachMonth;
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
	}
	public void setEnrollmentPaymentEachMonth(String o) {
		this.enrollmentPaymentEachMonth = SchoolPayment.staticSetEnrollmentPaymentEachMonth(siteRequest_, o);
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolPayment enrollmentPaymentEachMonthInit() {
		if(!enrollmentPaymentEachMonthWrap.alreadyInitialized) {
			_enrollmentPaymentEachMonth(enrollmentPaymentEachMonthWrap);
			if(enrollmentPaymentEachMonth == null)
				setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonthWrap.o);
		}
		enrollmentPaymentEachMonthWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Boolean staticSolrEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrEnrollmentPaymentEachMonth(siteRequest_, SchoolPayment.staticSolrEnrollmentPaymentEachMonth(siteRequest_, SchoolPayment.staticSetEnrollmentPaymentEachMonth(siteRequest_, o)));
	}

	public Boolean solrEnrollmentPaymentEachMonth() {
		return SchoolPayment.staticSolrEnrollmentPaymentEachMonth(siteRequest_, enrollmentPaymentEachMonth);
	}

	public String strEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth == null ? "" : enrollmentPaymentEachMonth.toString();
	}

	public Boolean sqlEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth;
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

	public void inputEnrollmentPaymentEachMonth(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_enrollmentPaymentEachMonth")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_enrollmentPaymentEachMonth");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setEnrollmentPaymentEachMonth classSchoolPayment inputSchoolPayment", pk, "EnrollmentPaymentEachMonth w3-input w3-border ");
				a("name", "setEnrollmentPaymentEachMonth");
			} else {
				a("class", "valueEnrollmentPaymentEachMonth classSchoolPayment inputSchoolPayment", pk, "EnrollmentPaymentEachMonth w3-input w3-border ");
				a("name", "enrollmentPaymentEachMonth");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentPaymentEachMonth', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentPaymentEachMonth')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentPaymentEachMonth')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getEnrollmentPaymentEachMonth() != null && getEnrollmentPaymentEachMonth())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "EnrollmentPaymentEachMonth ").f().sx(htmEnrollmentPaymentEachMonth()).g("span");
		}
	}

	public void htmEnrollmentPaymentEachMonth(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentEnrollmentPaymentEachMonth").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentPaymentEachMonth").a("class", "").f().sx("payment each month").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentPaymentEachMonth(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////
	// paymentDescription //
	////////////////////////

	/**	 The entity paymentDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paymentDescription;
	@JsonIgnore
	public Wrap<String> paymentDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("paymentDescription").o(paymentDescription);

	/**	<br/> The entity paymentDescription
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDescription">Find the entity paymentDescription in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentDescription(Wrap<String> c);

	public String getPaymentDescription() {
		return paymentDescription;
	}
	public void setPaymentDescription(String o) {
		this.paymentDescription = SchoolPayment.staticSetPaymentDescription(siteRequest_, o);
		this.paymentDescriptionWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentDescription(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrPaymentDescription(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentDescription(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentDescription(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentDescription(siteRequest_, SchoolPayment.staticSolrPaymentDescription(siteRequest_, SchoolPayment.staticSetPaymentDescription(siteRequest_, o)));
	}

	public String solrPaymentDescription() {
		return SchoolPayment.staticSolrPaymentDescription(siteRequest_, paymentDescription);
	}

	public String strPaymentDescription() {
		return paymentDescription == null ? "" : paymentDescription;
	}

	public String sqlPaymentDescription() {
		return paymentDescription;
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "description")
				.a("id", classApiMethodMethod, "_paymentDescription");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPaymentDescription classSchoolPayment inputSchoolPayment", pk, "PaymentDescription w3-input w3-border ");
					a("name", "setPaymentDescription");
				} else {
					a("class", "valuePaymentDescription w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "PaymentDescription w3-input w3-border ");
					a("name", "paymentDescription");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentDescription', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_paymentDescription')); }, function() { addError($('#", classApiMethodMethod, "_paymentDescription')); }); ");
				}
				a("value", strPaymentDescription())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentDescription ").f().sx(htmPaymentDescription()).g("span");
		}
	}

	public void htmPaymentDescription(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentDescription(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentDescription')); $('#", classApiMethodMethod, "_paymentDescription').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentDescription', null, function() { addGlow($('#", classApiMethodMethod, "_paymentDescription')); }, function() { addError($('#", classApiMethodMethod, "_paymentDescription')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 The entity paymentDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paymentDate;
	@JsonIgnore
	public Wrap<LocalDate> paymentDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("paymentDate").o(paymentDate);

	/**	<br/> The entity paymentDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDate">Find the entity paymentDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentDate(Wrap<LocalDate> c);

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
		this.paymentDateWrap.alreadyInitialized = true;
	}
	public void setPaymentDate(Instant o) {
		this.paymentDate = o == null ? null : LocalDate.from(o);
		this.paymentDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPaymentDate(String o) {
		this.paymentDate = SchoolPayment.staticSetPaymentDate(siteRequest_, o);
		this.paymentDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetPaymentDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPaymentDate(Date o) {
		this.paymentDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.paymentDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrPaymentDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPaymentDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPaymentDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentDate(siteRequest_, SchoolPayment.staticSolrPaymentDate(siteRequest_, SchoolPayment.staticSetPaymentDate(siteRequest_, o)));
	}

	public Date solrPaymentDate() {
		return SchoolPayment.staticSolrPaymentDate(siteRequest_, paymentDate);
	}

	public String strPaymentDate() {
		return paymentDate == null ? "" : paymentDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlPaymentDate() {
		return paymentDate;
	}

	public String jsonPaymentDate() {
		return paymentDate == null ? "" : paymentDate.format(DateTimeFormatter.ISO_DATE);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setPaymentDate classSchoolPayment inputSchoolPayment", pk, "PaymentDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_paymentDate")
					.a("value", paymentDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(paymentDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentDate', s, function() { addGlow($('#", classApiMethodMethod, "_paymentDate')); }, function() { addError($('#", classApiMethodMethod, "_paymentDate')); }); } ");
			}
			fg();
		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentDate ").f().sx(htmPaymentDate()).g("span");
		}
	}

	public void htmPaymentDate(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentDate").a("class", "").f().sx("payment date").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputPaymentDate(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentDate')); $('#", classApiMethodMethod, "_paymentDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentDate', null, function() { addGlow($('#", classApiMethodMethod, "_paymentDate')); }, function() { addError($('#", classApiMethodMethod, "_paymentDate')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// lateFeeDate //
	/////////////////

	/**	 The entity lateFeeDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate lateFeeDate;
	@JsonIgnore
	public Wrap<LocalDate> lateFeeDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("lateFeeDate").o(lateFeeDate);

	/**	<br/> The entity lateFeeDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:lateFeeDate">Find the entity lateFeeDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _lateFeeDate(Wrap<LocalDate> c);

	public LocalDate getLateFeeDate() {
		return lateFeeDate;
	}

	public void setLateFeeDate(LocalDate lateFeeDate) {
		this.lateFeeDate = lateFeeDate;
		this.lateFeeDateWrap.alreadyInitialized = true;
	}
	public void setLateFeeDate(Instant o) {
		this.lateFeeDate = o == null ? null : LocalDate.from(o);
		this.lateFeeDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setLateFeeDate(String o) {
		this.lateFeeDate = SchoolPayment.staticSetLateFeeDate(siteRequest_, o);
		this.lateFeeDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetLateFeeDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setLateFeeDate(Date o) {
		this.lateFeeDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.lateFeeDateWrap.alreadyInitialized = true;
	}
	protected SchoolPayment lateFeeDateInit() {
		if(!lateFeeDateWrap.alreadyInitialized) {
			_lateFeeDate(lateFeeDateWrap);
			if(lateFeeDate == null)
				setLateFeeDate(lateFeeDateWrap.o);
		}
		lateFeeDateWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Date staticSolrLateFeeDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrLateFeeDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqLateFeeDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrLateFeeDate(siteRequest_, SchoolPayment.staticSolrLateFeeDate(siteRequest_, SchoolPayment.staticSetLateFeeDate(siteRequest_, o)));
	}

	public Date solrLateFeeDate() {
		return SchoolPayment.staticSolrLateFeeDate(siteRequest_, lateFeeDate);
	}

	public String strLateFeeDate() {
		return lateFeeDate == null ? "" : lateFeeDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlLateFeeDate() {
		return lateFeeDate;
	}

	public String jsonLateFeeDate() {
		return lateFeeDate == null ? "" : lateFeeDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageLateFeeDate() {
		return "late fee date";
	}

	public String htmTooltipLateFeeDate() {
		return null;
	}

	public String htmLateFeeDate() {
		return lateFeeDate == null ? "" : StringEscapeUtils.escapeHtml4(strLateFeeDate());
	}

	public void inputLateFeeDate(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setLateFeeDate classSchoolPayment inputSchoolPayment", pk, "LateFeeDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_lateFeeDate")
					.a("value", lateFeeDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(lateFeeDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setLateFeeDate', s, function() { addGlow($('#", classApiMethodMethod, "_lateFeeDate')); }, function() { addError($('#", classApiMethodMethod, "_lateFeeDate')); }); } ");
			}
			fg();
		} else {
				e("span").a("class", "varSchoolPayment", pk, "LateFeeDate ").f().sx(htmLateFeeDate()).g("span");
		}
	}

	public void htmLateFeeDate(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentLateFeeDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_lateFeeDate").a("class", "").f().sx("late fee date").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputLateFeeDate(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_lateFeeDate')); $('#", classApiMethodMethod, "_lateFeeDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setLateFeeDate', null, function() { addGlow($('#", classApiMethodMethod, "_lateFeeDate')); }, function() { addError($('#", classApiMethodMethod, "_lateFeeDate')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// paymentYear //
	/////////////////

	/**	 The entity paymentYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paymentYear;
	@JsonIgnore
	public Wrap<Integer> paymentYearWrap = new Wrap<Integer>().p(this).c(Integer.class).var("paymentYear").o(paymentYear);

	/**	<br/> The entity paymentYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentYear">Find the entity paymentYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentYear(Wrap<Integer> c);

	public Integer getPaymentYear() {
		return paymentYear;
	}

	public void setPaymentYear(Integer paymentYear) {
		this.paymentYear = paymentYear;
		this.paymentYearWrap.alreadyInitialized = true;
	}
	public void setPaymentYear(String o) {
		this.paymentYear = SchoolPayment.staticSetPaymentYear(siteRequest_, o);
		this.paymentYearWrap.alreadyInitialized = true;
	}
	public static Integer staticSetPaymentYear(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment paymentYearInit() {
		if(!paymentYearWrap.alreadyInitialized) {
			_paymentYear(paymentYearWrap);
			if(paymentYear == null)
				setPaymentYear(paymentYearWrap.o);
		}
		paymentYearWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrPaymentYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrPaymentYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentYear(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentYear(siteRequest_, SchoolPayment.staticSolrPaymentYear(siteRequest_, SchoolPayment.staticSetPaymentYear(siteRequest_, o)));
	}

	public Integer solrPaymentYear() {
		return SchoolPayment.staticSolrPaymentYear(siteRequest_, paymentYear);
	}

	public String strPaymentYear() {
		return paymentYear == null ? "" : paymentYear.toString();
	}

	public Integer sqlPaymentYear() {
		return paymentYear;
	}

	public String jsonPaymentYear() {
		return paymentYear == null ? "" : paymentYear.toString();
	}

	public String nomAffichagePaymentYear() {
		return null;
	}

	public String htmTooltipPaymentYear() {
		return null;
	}

	public String htmPaymentYear() {
		return paymentYear == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentYear());
	}

	///////////////////
	// paymentAmount //
	///////////////////

	/**	 The entity paymentAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paymentAmount;
	@JsonIgnore
	public Wrap<BigDecimal> paymentAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("paymentAmount").o(paymentAmount);

	/**	<br/> The entity paymentAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentAmount">Find the entity paymentAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentAmount(Wrap<BigDecimal> c);

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentAmount(String o) {
		this.paymentAmount = SchoolPayment.staticSetPaymentAmount(siteRequest_, o);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetPaymentAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaymentAmount(Double o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentAmount(Integer o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentAmountWrap.alreadyInitialized = true;
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

	public static Double staticSolrPaymentAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentAmount(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentAmount(siteRequest_, SchoolPayment.staticSolrPaymentAmount(siteRequest_, SchoolPayment.staticSetPaymentAmount(siteRequest_, o)));
	}

	public Double solrPaymentAmount() {
		return SchoolPayment.staticSolrPaymentAmount(siteRequest_, paymentAmount);
	}

	public String strPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlPaymentAmount() {
		return paymentAmount;
	}

	public String jsonPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.toString();
	}

	public String nomAffichagePaymentAmount() {
		return "payment amount";
	}

	public String htmTooltipPaymentAmount() {
		return null;
	}

	public String htmPaymentAmount() {
		return paymentAmount == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentAmount());
	}

	public void inputPaymentAmount(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "payment amount")
				.a("id", classApiMethodMethod, "_paymentAmount");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPaymentAmount classSchoolPayment inputSchoolPayment", pk, "PaymentAmount w3-input w3-border ");
					a("name", "setPaymentAmount");
				} else {
					a("class", "valuePaymentAmount w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "PaymentAmount w3-input w3-border ");
					a("name", "paymentAmount");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentAmount', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_paymentAmount')); }, function() { addError($('#", classApiMethodMethod, "_paymentAmount')); }); ");
				}
				a("value", strPaymentAmount())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentAmount ").f().sx(htmPaymentAmount()).g("span");
		}
	}

	public void htmPaymentAmount(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentAmount").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentAmount").a("class", "").f().sx("payment amount").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentAmount(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentAmount')); $('#", classApiMethodMethod, "_paymentAmount').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentAmount', null, function() { addGlow($('#", classApiMethodMethod, "_paymentAmount')); }, function() { addError($('#", classApiMethodMethod, "_paymentAmount')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 The entity paymentCash
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentCash;
	@JsonIgnore
	public Wrap<Boolean> paymentCashWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentCash").o(paymentCash);

	/**	<br/> The entity paymentCash
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentCash">Find the entity paymentCash in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentCash(Wrap<Boolean> c);

	public Boolean getPaymentCash() {
		return paymentCash;
	}

	public void setPaymentCash(Boolean paymentCash) {
		this.paymentCash = paymentCash;
		this.paymentCashWrap.alreadyInitialized = true;
	}
	public void setPaymentCash(String o) {
		this.paymentCash = SchoolPayment.staticSetPaymentCash(siteRequest_, o);
		this.paymentCashWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentCash(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaymentCash(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentCash(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentCash(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentCash(siteRequest_, SchoolPayment.staticSolrPaymentCash(siteRequest_, SchoolPayment.staticSetPaymentCash(siteRequest_, o)));
	}

	public Boolean solrPaymentCash() {
		return SchoolPayment.staticSolrPaymentCash(siteRequest_, paymentCash);
	}

	public String strPaymentCash() {
		return paymentCash == null ? "" : paymentCash.toString();
	}

	public Boolean sqlPaymentCash() {
		return paymentCash;
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
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
				a("class", "setPaymentCash classSchoolPayment inputSchoolPayment", pk, "PaymentCash w3-input w3-border ");
				a("name", "setPaymentCash");
			} else {
				a("class", "valuePaymentCash classSchoolPayment inputSchoolPayment", pk, "PaymentCash w3-input w3-border ");
				a("name", "paymentCash");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentCash', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_paymentCash')); }, function() { addError($('#", classApiMethodMethod, "_paymentCash')); }); ");
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

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentCash ").f().sx(htmPaymentCash()).g("span");
		}
	}

	public void htmPaymentCash(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentCash").f();
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

	/**	 The entity paymentCheck
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentCheck;
	@JsonIgnore
	public Wrap<Boolean> paymentCheckWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentCheck").o(paymentCheck);

	/**	<br/> The entity paymentCheck
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentCheck">Find the entity paymentCheck in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentCheck(Wrap<Boolean> c);

	public Boolean getPaymentCheck() {
		return paymentCheck;
	}

	public void setPaymentCheck(Boolean paymentCheck) {
		this.paymentCheck = paymentCheck;
		this.paymentCheckWrap.alreadyInitialized = true;
	}
	public void setPaymentCheck(String o) {
		this.paymentCheck = SchoolPayment.staticSetPaymentCheck(siteRequest_, o);
		this.paymentCheckWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentCheck(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaymentCheck(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentCheck(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentCheck(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentCheck(siteRequest_, SchoolPayment.staticSolrPaymentCheck(siteRequest_, SchoolPayment.staticSetPaymentCheck(siteRequest_, o)));
	}

	public Boolean solrPaymentCheck() {
		return SchoolPayment.staticSolrPaymentCheck(siteRequest_, paymentCheck);
	}

	public String strPaymentCheck() {
		return paymentCheck == null ? "" : paymentCheck.toString();
	}

	public Boolean sqlPaymentCheck() {
		return paymentCheck;
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
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
				a("class", "setPaymentCheck classSchoolPayment inputSchoolPayment", pk, "PaymentCheck w3-input w3-border ");
				a("name", "setPaymentCheck");
			} else {
				a("class", "valuePaymentCheck classSchoolPayment inputSchoolPayment", pk, "PaymentCheck w3-input w3-border ");
				a("name", "paymentCheck");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentCheck', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_paymentCheck')); }, function() { addError($('#", classApiMethodMethod, "_paymentCheck')); }); ");
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

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentCheck ").f().sx(htmPaymentCheck()).g("span");
		}
	}

	public void htmPaymentCheck(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentCheck").f();
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
	// paymentECheck //
	///////////////////

	/**	 The entity paymentECheck
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentECheck;
	@JsonIgnore
	public Wrap<Boolean> paymentECheckWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentECheck").o(paymentECheck);

	/**	<br/> The entity paymentECheck
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentECheck">Find the entity paymentECheck in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentECheck(Wrap<Boolean> c);

	public Boolean getPaymentECheck() {
		return paymentECheck;
	}

	public void setPaymentECheck(Boolean paymentECheck) {
		this.paymentECheck = paymentECheck;
		this.paymentECheckWrap.alreadyInitialized = true;
	}
	public void setPaymentECheck(String o) {
		this.paymentECheck = SchoolPayment.staticSetPaymentECheck(siteRequest_, o);
		this.paymentECheckWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentECheck(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolPayment paymentECheckInit() {
		if(!paymentECheckWrap.alreadyInitialized) {
			_paymentECheck(paymentECheckWrap);
			if(paymentECheck == null)
				setPaymentECheck(paymentECheckWrap.o);
		}
		paymentECheckWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Boolean staticSolrPaymentECheck(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentECheck(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentECheck(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentECheck(siteRequest_, SchoolPayment.staticSolrPaymentECheck(siteRequest_, SchoolPayment.staticSetPaymentECheck(siteRequest_, o)));
	}

	public Boolean solrPaymentECheck() {
		return SchoolPayment.staticSolrPaymentECheck(siteRequest_, paymentECheck);
	}

	public String strPaymentECheck() {
		return paymentECheck == null ? "" : paymentECheck.toString();
	}

	public Boolean sqlPaymentECheck() {
		return paymentECheck;
	}

	public String jsonPaymentECheck() {
		return paymentECheck == null ? "" : paymentECheck.toString();
	}

	public String nomAffichagePaymentECheck() {
		return "e-check";
	}

	public String htmTooltipPaymentECheck() {
		return null;
	}

	public String htmPaymentECheck() {
		return paymentECheck == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentECheck());
	}

	public void inputPaymentECheck(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_paymentECheck")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_paymentECheck");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPaymentECheck classSchoolPayment inputSchoolPayment", pk, "PaymentECheck w3-input w3-border ");
				a("name", "setPaymentECheck");
			} else {
				a("class", "valuePaymentECheck classSchoolPayment inputSchoolPayment", pk, "PaymentECheck w3-input w3-border ");
				a("name", "paymentECheck");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentECheck', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_paymentECheck')); }, function() { addError($('#", classApiMethodMethod, "_paymentECheck')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getPaymentECheck() != null && getPaymentECheck())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentECheck ").f().sx(htmPaymentECheck()).g("span");
		}
	}

	public void htmPaymentECheck(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentECheck").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentECheck").a("class", "").f().sx("e-check").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentECheck(classApiMethodMethod);
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

	/**	 The entity paymentSystem
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentSystem;
	@JsonIgnore
	public Wrap<Boolean> paymentSystemWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentSystem").o(paymentSystem);

	/**	<br/> The entity paymentSystem
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentSystem">Find the entity paymentSystem in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentSystem(Wrap<Boolean> c);

	public Boolean getPaymentSystem() {
		return paymentSystem;
	}

	public void setPaymentSystem(Boolean paymentSystem) {
		this.paymentSystem = paymentSystem;
		this.paymentSystemWrap.alreadyInitialized = true;
	}
	public void setPaymentSystem(String o) {
		this.paymentSystem = SchoolPayment.staticSetPaymentSystem(siteRequest_, o);
		this.paymentSystemWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentSystem(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaymentSystem(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentSystem(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentSystem(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentSystem(siteRequest_, SchoolPayment.staticSolrPaymentSystem(siteRequest_, SchoolPayment.staticSetPaymentSystem(siteRequest_, o)));
	}

	public Boolean solrPaymentSystem() {
		return SchoolPayment.staticSolrPaymentSystem(siteRequest_, paymentSystem);
	}

	public String strPaymentSystem() {
		return paymentSystem == null ? "" : paymentSystem.toString();
	}

	public Boolean sqlPaymentSystem() {
		return paymentSystem;
	}

	public String jsonPaymentSystem() {
		return paymentSystem == null ? "" : paymentSystem.toString();
	}

	public String nomAffichagePaymentSystem() {
		return "authorize.net";
	}

	public String htmTooltipPaymentSystem() {
		return null;
	}

	public String htmPaymentSystem() {
		return paymentSystem == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentSystem());
	}

	public void inputPaymentSystem(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_paymentSystem")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_paymentSystem");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPaymentSystem classSchoolPayment inputSchoolPayment", pk, "PaymentSystem w3-input w3-border ");
				a("name", "setPaymentSystem");
			} else {
				a("class", "valuePaymentSystem classSchoolPayment inputSchoolPayment", pk, "PaymentSystem w3-input w3-border ");
				a("name", "paymentSystem");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentSystem', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_paymentSystem')); }, function() { addError($('#", classApiMethodMethod, "_paymentSystem')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getPaymentSystem() != null && getPaymentSystem())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentSystem ").f().sx(htmPaymentSystem()).g("span");
		}
	}

	public void htmPaymentSystem(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentSystem").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentSystem").a("class", "").f().sx("authorize.net").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentSystem(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// paymentType //
	/////////////////

	/**	 The entity paymentType
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paymentType;
	@JsonIgnore
	public Wrap<String> paymentTypeWrap = new Wrap<String>().p(this).c(String.class).var("paymentType").o(paymentType);

	/**	<br/> The entity paymentType
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentType">Find the entity paymentType in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentType(Wrap<String> c);

	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String o) {
		this.paymentType = SchoolPayment.staticSetPaymentType(siteRequest_, o);
		this.paymentTypeWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentType(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment paymentTypeInit() {
		if(!paymentTypeWrap.alreadyInitialized) {
			_paymentType(paymentTypeWrap);
			if(paymentType == null)
				setPaymentType(paymentTypeWrap.o);
		}
		paymentTypeWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrPaymentType(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentType(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentType(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentType(siteRequest_, SchoolPayment.staticSolrPaymentType(siteRequest_, SchoolPayment.staticSetPaymentType(siteRequest_, o)));
	}

	public String solrPaymentType() {
		return SchoolPayment.staticSolrPaymentType(siteRequest_, paymentType);
	}

	public String strPaymentType() {
		return paymentType == null ? "" : paymentType;
	}

	public String sqlPaymentType() {
		return paymentType;
	}

	public String jsonPaymentType() {
		return paymentType == null ? "" : paymentType;
	}

	public String nomAffichagePaymentType() {
		return null;
	}

	public String htmTooltipPaymentType() {
		return null;
	}

	public String htmPaymentType() {
		return paymentType == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentType());
	}

	///////////////
	// paymentBy //
	///////////////

	/**	 The entity paymentBy
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paymentBy;
	@JsonIgnore
	public Wrap<String> paymentByWrap = new Wrap<String>().p(this).c(String.class).var("paymentBy").o(paymentBy);

	/**	<br/> The entity paymentBy
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentBy">Find the entity paymentBy in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentBy(Wrap<String> c);

	public String getPaymentBy() {
		return paymentBy;
	}
	public void setPaymentBy(String o) {
		this.paymentBy = SchoolPayment.staticSetPaymentBy(siteRequest_, o);
		this.paymentByWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentBy(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment paymentByInit() {
		if(!paymentByWrap.alreadyInitialized) {
			_paymentBy(paymentByWrap);
			if(paymentBy == null)
				setPaymentBy(paymentByWrap.o);
		}
		paymentByWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrPaymentBy(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentBy(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentBy(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentBy(siteRequest_, SchoolPayment.staticSolrPaymentBy(siteRequest_, SchoolPayment.staticSetPaymentBy(siteRequest_, o)));
	}

	public String solrPaymentBy() {
		return SchoolPayment.staticSolrPaymentBy(siteRequest_, paymentBy);
	}

	public String strPaymentBy() {
		return paymentBy == null ? "" : paymentBy;
	}

	public String sqlPaymentBy() {
		return paymentBy;
	}

	public String jsonPaymentBy() {
		return paymentBy == null ? "" : paymentBy;
	}

	public String nomAffichagePaymentBy() {
		return "payment by/for";
	}

	public String htmTooltipPaymentBy() {
		return null;
	}

	public String htmPaymentBy() {
		return paymentBy == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentBy());
	}

	public void inputPaymentBy(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "payment by/for")
				.a("id", classApiMethodMethod, "_paymentBy");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPaymentBy classSchoolPayment inputSchoolPayment", pk, "PaymentBy w3-input w3-border ");
					a("name", "setPaymentBy");
				} else {
					a("class", "valuePaymentBy w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "PaymentBy w3-input w3-border ");
					a("name", "paymentBy");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentBy', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_paymentBy')); }, function() { addError($('#", classApiMethodMethod, "_paymentBy')); }); ");
				}
				a("value", strPaymentBy())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentBy ").f().sx(htmPaymentBy()).g("span");
		}
	}

	public void htmPaymentBy(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentBy").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentBy").a("class", "").f().sx("payment by/for").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentBy(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentBy')); $('#", classApiMethodMethod, "_paymentBy').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentBy', null, function() { addGlow($('#", classApiMethodMethod, "_paymentBy')); }, function() { addError($('#", classApiMethodMethod, "_paymentBy')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// transactionId //
	///////////////////

	/**	 The entity transactionId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String transactionId;
	@JsonIgnore
	public Wrap<String> transactionIdWrap = new Wrap<String>().p(this).c(String.class).var("transactionId").o(transactionId);

	/**	<br/> The entity transactionId
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:transactionId">Find the entity transactionId in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _transactionId(Wrap<String> c);

	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String o) {
		this.transactionId = SchoolPayment.staticSetTransactionId(siteRequest_, o);
		this.transactionIdWrap.alreadyInitialized = true;
	}
	public static String staticSetTransactionId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment transactionIdInit() {
		if(!transactionIdWrap.alreadyInitialized) {
			_transactionId(transactionIdWrap);
			if(transactionId == null)
				setTransactionId(transactionIdWrap.o);
		}
		transactionIdWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrTransactionId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrTransactionId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqTransactionId(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrTransactionId(siteRequest_, SchoolPayment.staticSolrTransactionId(siteRequest_, SchoolPayment.staticSetTransactionId(siteRequest_, o)));
	}

	public String solrTransactionId() {
		return SchoolPayment.staticSolrTransactionId(siteRequest_, transactionId);
	}

	public String strTransactionId() {
		return transactionId == null ? "" : transactionId;
	}

	public String sqlTransactionId() {
		return transactionId;
	}

	public String jsonTransactionId() {
		return transactionId == null ? "" : transactionId;
	}

	public String nomAffichageTransactionId() {
		return "transaction ID";
	}

	public String htmTooltipTransactionId() {
		return null;
	}

	public String htmTransactionId() {
		return transactionId == null ? "" : StringEscapeUtils.escapeHtml4(strTransactionId());
	}

	public void inputTransactionId(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "transaction ID")
				.a("id", classApiMethodMethod, "_transactionId");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setTransactionId classSchoolPayment inputSchoolPayment", pk, "TransactionId w3-input w3-border ");
					a("name", "setTransactionId");
				} else {
					a("class", "valueTransactionId w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "TransactionId w3-input w3-border ");
					a("name", "transactionId");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setTransactionId', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_transactionId')); }, function() { addError($('#", classApiMethodMethod, "_transactionId')); }); ");
				}
				a("value", strTransactionId())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "TransactionId ").f().sx(htmTransactionId()).g("span");
		}
	}

	public void htmTransactionId(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentTransactionId").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_transactionId").a("class", "").f().sx("transaction ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTransactionId(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_transactionId')); $('#", classApiMethodMethod, "_transactionId').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setTransactionId', null, function() { addGlow($('#", classApiMethodMethod, "_transactionId')); }, function() { addError($('#", classApiMethodMethod, "_transactionId')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////
	// customerProfileId //
	///////////////////////

	/**	 The entity customerProfileId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId;
	@JsonIgnore
	public Wrap<String> customerProfileIdWrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId").o(customerProfileId);

	/**	<br/> The entity customerProfileId
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId">Find the entity customerProfileId in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId(Wrap<String> c);

	public String getCustomerProfileId() {
		return customerProfileId;
	}
	public void setCustomerProfileId(String o) {
		this.customerProfileId = SchoolPayment.staticSetCustomerProfileId(siteRequest_, o);
		this.customerProfileIdWrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment customerProfileIdInit() {
		if(!customerProfileIdWrap.alreadyInitialized) {
			_customerProfileId(customerProfileIdWrap);
			if(customerProfileId == null)
				setCustomerProfileId(customerProfileIdWrap.o);
		}
		customerProfileIdWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrCustomerProfileId(siteRequest_, SchoolPayment.staticSolrCustomerProfileId(siteRequest_, SchoolPayment.staticSetCustomerProfileId(siteRequest_, o)));
	}

	public String solrCustomerProfileId() {
		return SchoolPayment.staticSolrCustomerProfileId(siteRequest_, customerProfileId);
	}

	public String strCustomerProfileId() {
		return customerProfileId == null ? "" : customerProfileId;
	}

	public String sqlCustomerProfileId() {
		return customerProfileId;
	}

	public String jsonCustomerProfileId() {
		return customerProfileId == null ? "" : customerProfileId;
	}

	public String nomAffichageCustomerProfileId() {
		return "customer profile ID";
	}

	public String htmTooltipCustomerProfileId() {
		return null;
	}

	public String htmCustomerProfileId() {
		return customerProfileId == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId());
	}

	public void inputCustomerProfileId(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID")
				.a("id", classApiMethodMethod, "_customerProfileId");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId classSchoolPayment inputSchoolPayment", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "setCustomerProfileId");
				} else {
					a("class", "valueCustomerProfileId w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "customerProfileId");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId')); }); ");
				}
				a("value", strCustomerProfileId())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "CustomerProfileId ").f().sx(htmCustomerProfileId()).g("span");
		}
	}

	public void htmCustomerProfileId(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentCustomerProfileId").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_customerProfileId").a("class", "").f().sx("customer profile ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId')); $('#", classApiMethodMethod, "_customerProfileId').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setCustomerProfileId', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////
	// transactionStatus //
	///////////////////////

	/**	 The entity transactionStatus
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String transactionStatus;
	@JsonIgnore
	public Wrap<String> transactionStatusWrap = new Wrap<String>().p(this).c(String.class).var("transactionStatus").o(transactionStatus);

	/**	<br/> The entity transactionStatus
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:transactionStatus">Find the entity transactionStatus in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _transactionStatus(Wrap<String> c);

	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String o) {
		this.transactionStatus = SchoolPayment.staticSetTransactionStatus(siteRequest_, o);
		this.transactionStatusWrap.alreadyInitialized = true;
	}
	public static String staticSetTransactionStatus(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment transactionStatusInit() {
		if(!transactionStatusWrap.alreadyInitialized) {
			_transactionStatus(transactionStatusWrap);
			if(transactionStatus == null)
				setTransactionStatus(transactionStatusWrap.o);
		}
		transactionStatusWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrTransactionStatus(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrTransactionStatus(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqTransactionStatus(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrTransactionStatus(siteRequest_, SchoolPayment.staticSolrTransactionStatus(siteRequest_, SchoolPayment.staticSetTransactionStatus(siteRequest_, o)));
	}

	public String solrTransactionStatus() {
		return SchoolPayment.staticSolrTransactionStatus(siteRequest_, transactionStatus);
	}

	public String strTransactionStatus() {
		return transactionStatus == null ? "" : transactionStatus;
	}

	public String sqlTransactionStatus() {
		return transactionStatus;
	}

	public String jsonTransactionStatus() {
		return transactionStatus == null ? "" : transactionStatus;
	}

	public String nomAffichageTransactionStatus() {
		return "transaction status";
	}

	public String htmTooltipTransactionStatus() {
		return null;
	}

	public String htmTransactionStatus() {
		return transactionStatus == null ? "" : StringEscapeUtils.escapeHtml4(strTransactionStatus());
	}

	public void inputTransactionStatus(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "transaction status")
				.a("id", classApiMethodMethod, "_transactionStatus");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setTransactionStatus classSchoolPayment inputSchoolPayment", pk, "TransactionStatus w3-input w3-border ");
					a("name", "setTransactionStatus");
				} else {
					a("class", "valueTransactionStatus w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "TransactionStatus w3-input w3-border ");
					a("name", "transactionStatus");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setTransactionStatus', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_transactionStatus')); }, function() { addError($('#", classApiMethodMethod, "_transactionStatus')); }); ");
				}
				a("value", strTransactionStatus())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "TransactionStatus ").f().sx(htmTransactionStatus()).g("span");
		}
	}

	public void htmTransactionStatus(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentTransactionStatus").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_transactionStatus").a("class", "").f().sx("transaction status").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTransactionStatus(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_transactionStatus')); $('#", classApiMethodMethod, "_transactionStatus').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setTransactionStatus', null, function() { addGlow($('#", classApiMethodMethod, "_transactionStatus')); }, function() { addError($('#", classApiMethodMethod, "_transactionStatus')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// paymentRecieved //
	/////////////////////

	/**	 The entity paymentRecieved
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentRecieved;
	@JsonIgnore
	public Wrap<Boolean> paymentRecievedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentRecieved").o(paymentRecieved);

	/**	<br/> The entity paymentRecieved
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentRecieved">Find the entity paymentRecieved in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentRecieved(Wrap<Boolean> c);

	public Boolean getPaymentRecieved() {
		return paymentRecieved;
	}

	public void setPaymentRecieved(Boolean paymentRecieved) {
		this.paymentRecieved = paymentRecieved;
		this.paymentRecievedWrap.alreadyInitialized = true;
	}
	public void setPaymentRecieved(String o) {
		this.paymentRecieved = SchoolPayment.staticSetPaymentRecieved(siteRequest_, o);
		this.paymentRecievedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentRecieved(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolPayment paymentRecievedInit() {
		if(!paymentRecievedWrap.alreadyInitialized) {
			_paymentRecieved(paymentRecievedWrap);
			if(paymentRecieved == null)
				setPaymentRecieved(paymentRecievedWrap.o);
		}
		paymentRecievedWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Boolean staticSolrPaymentRecieved(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentRecieved(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentRecieved(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentRecieved(siteRequest_, SchoolPayment.staticSolrPaymentRecieved(siteRequest_, SchoolPayment.staticSetPaymentRecieved(siteRequest_, o)));
	}

	public Boolean solrPaymentRecieved() {
		return SchoolPayment.staticSolrPaymentRecieved(siteRequest_, paymentRecieved);
	}

	public String strPaymentRecieved() {
		return paymentRecieved == null ? "" : paymentRecieved.toString();
	}

	public Boolean sqlPaymentRecieved() {
		return paymentRecieved;
	}

	public String jsonPaymentRecieved() {
		return paymentRecieved == null ? "" : paymentRecieved.toString();
	}

	public String nomAffichagePaymentRecieved() {
		return "payment received";
	}

	public String htmTooltipPaymentRecieved() {
		return null;
	}

	public String htmPaymentRecieved() {
		return paymentRecieved == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentRecieved());
	}

	public void inputPaymentRecieved(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_paymentRecieved")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_paymentRecieved");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPaymentRecieved classSchoolPayment inputSchoolPayment", pk, "PaymentRecieved w3-input w3-border ");
				a("name", "setPaymentRecieved");
			} else {
				a("class", "valuePaymentRecieved classSchoolPayment inputSchoolPayment", pk, "PaymentRecieved w3-input w3-border ");
				a("name", "paymentRecieved");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentRecieved', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_paymentRecieved')); }, function() { addError($('#", classApiMethodMethod, "_paymentRecieved')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getPaymentRecieved() != null && getPaymentRecieved())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentRecieved ").f().sx(htmPaymentRecieved()).g("span");
		}
	}

	public void htmPaymentRecieved(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentRecieved").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentRecieved").a("class", "").f().sx("payment received").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentRecieved(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// chargeAmount //
	//////////////////

	/**	 The entity chargeAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmount;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmount").o(chargeAmount);

	/**	<br/> The entity chargeAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmount">Find the entity chargeAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmount(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public void setChargeAmount(String o) {
		this.chargeAmount = SchoolPayment.staticSetChargeAmount(siteRequest_, o);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmount(Double o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public void setChargeAmount(Integer o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	protected SchoolPayment chargeAmountInit() {
		if(!chargeAmountWrap.alreadyInitialized) {
			_chargeAmount(chargeAmountWrap);
			if(chargeAmount == null)
				setChargeAmount(chargeAmountWrap.o);
		}
		chargeAmountWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrChargeAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmount(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeAmount(siteRequest_, SchoolPayment.staticSolrChargeAmount(siteRequest_, SchoolPayment.staticSetChargeAmount(siteRequest_, o)));
	}

	public Double solrChargeAmount() {
		return SchoolPayment.staticSolrChargeAmount(siteRequest_, chargeAmount);
	}

	public String strChargeAmount() {
		return chargeAmount == null ? "" : chargeAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlChargeAmount() {
		return chargeAmount;
	}

	public String jsonChargeAmount() {
		return chargeAmount == null ? "" : chargeAmount.toString();
	}

	public String nomAffichageChargeAmount() {
		return "charge amount";
	}

	public String htmTooltipChargeAmount() {
		return null;
	}

	public String htmChargeAmount() {
		return chargeAmount == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmount());
	}

	public void inputChargeAmount(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "charge amount")
				.a("id", classApiMethodMethod, "_chargeAmount");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChargeAmount classSchoolPayment inputSchoolPayment", pk, "ChargeAmount w3-input w3-border ");
					a("name", "setChargeAmount");
				} else {
					a("class", "valueChargeAmount w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "ChargeAmount w3-input w3-border ");
					a("name", "chargeAmount");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChargeAmount', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_chargeAmount')); }, function() { addError($('#", classApiMethodMethod, "_chargeAmount')); }); ");
				}
				a("value", strChargeAmount())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "ChargeAmount ").f().sx(htmChargeAmount()).g("span");
		}
	}

	public void htmChargeAmount(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChargeAmount").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_chargeAmount").a("class", "").f().sx("charge amount").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChargeAmount(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_chargeAmount')); $('#", classApiMethodMethod, "_chargeAmount').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setChargeAmount', null, function() { addGlow($('#", classApiMethodMethod, "_chargeAmount')); }, function() { addError($('#", classApiMethodMethod, "_chargeAmount')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// chargeFirstLast //
	/////////////////////

	/**	 The entity chargeFirstLast
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean chargeFirstLast;
	@JsonIgnore
	public Wrap<Boolean> chargeFirstLastWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("chargeFirstLast").o(chargeFirstLast);

	/**	<br/> The entity chargeFirstLast
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeFirstLast">Find the entity chargeFirstLast in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeFirstLast(Wrap<Boolean> c);

	public Boolean getChargeFirstLast() {
		return chargeFirstLast;
	}

	public void setChargeFirstLast(Boolean chargeFirstLast) {
		this.chargeFirstLast = chargeFirstLast;
		this.chargeFirstLastWrap.alreadyInitialized = true;
	}
	public void setChargeFirstLast(String o) {
		this.chargeFirstLast = SchoolPayment.staticSetChargeFirstLast(siteRequest_, o);
		this.chargeFirstLastWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetChargeFirstLast(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolPayment chargeFirstLastInit() {
		if(!chargeFirstLastWrap.alreadyInitialized) {
			_chargeFirstLast(chargeFirstLastWrap);
			if(chargeFirstLast == null)
				setChargeFirstLast(chargeFirstLastWrap.o);
		}
		chargeFirstLastWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Boolean staticSolrChargeFirstLast(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrChargeFirstLast(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeFirstLast(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeFirstLast(siteRequest_, SchoolPayment.staticSolrChargeFirstLast(siteRequest_, SchoolPayment.staticSetChargeFirstLast(siteRequest_, o)));
	}

	public Boolean solrChargeFirstLast() {
		return SchoolPayment.staticSolrChargeFirstLast(siteRequest_, chargeFirstLast);
	}

	public String strChargeFirstLast() {
		return chargeFirstLast == null ? "" : chargeFirstLast.toString();
	}

	public Boolean sqlChargeFirstLast() {
		return chargeFirstLast;
	}

	public String jsonChargeFirstLast() {
		return chargeFirstLast == null ? "" : chargeFirstLast.toString();
	}

	public String nomAffichageChargeFirstLast() {
		return "first and last month charge";
	}

	public String htmTooltipChargeFirstLast() {
		return null;
	}

	public String htmChargeFirstLast() {
		return chargeFirstLast == null ? "" : StringEscapeUtils.escapeHtml4(strChargeFirstLast());
	}

	public void inputChargeFirstLast(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_chargeFirstLast")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_chargeFirstLast");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChargeFirstLast classSchoolPayment inputSchoolPayment", pk, "ChargeFirstLast w3-input w3-border ");
				a("name", "setChargeFirstLast");
			} else {
				a("class", "valueChargeFirstLast classSchoolPayment inputSchoolPayment", pk, "ChargeFirstLast w3-input w3-border ");
				a("name", "chargeFirstLast");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChargeFirstLast', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_chargeFirstLast')); }, function() { addError($('#", classApiMethodMethod, "_chargeFirstLast')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getChargeFirstLast() != null && getChargeFirstLast())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "ChargeFirstLast ").f().sx(htmChargeFirstLast()).g("span");
		}
	}

	public void htmChargeFirstLast(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChargeFirstLast").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_chargeFirstLast").a("class", "").f().sx("first and last month charge").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChargeFirstLast(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// chargeEnrollment //
	//////////////////////

	/**	 The entity chargeEnrollment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean chargeEnrollment;
	@JsonIgnore
	public Wrap<Boolean> chargeEnrollmentWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("chargeEnrollment").o(chargeEnrollment);

	/**	<br/> The entity chargeEnrollment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeEnrollment">Find the entity chargeEnrollment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeEnrollment(Wrap<Boolean> c);

	public Boolean getChargeEnrollment() {
		return chargeEnrollment;
	}

	public void setChargeEnrollment(Boolean chargeEnrollment) {
		this.chargeEnrollment = chargeEnrollment;
		this.chargeEnrollmentWrap.alreadyInitialized = true;
	}
	public void setChargeEnrollment(String o) {
		this.chargeEnrollment = SchoolPayment.staticSetChargeEnrollment(siteRequest_, o);
		this.chargeEnrollmentWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetChargeEnrollment(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolPayment chargeEnrollmentInit() {
		if(!chargeEnrollmentWrap.alreadyInitialized) {
			_chargeEnrollment(chargeEnrollmentWrap);
			if(chargeEnrollment == null)
				setChargeEnrollment(chargeEnrollmentWrap.o);
		}
		chargeEnrollmentWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Boolean staticSolrChargeEnrollment(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrChargeEnrollment(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeEnrollment(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeEnrollment(siteRequest_, SchoolPayment.staticSolrChargeEnrollment(siteRequest_, SchoolPayment.staticSetChargeEnrollment(siteRequest_, o)));
	}

	public Boolean solrChargeEnrollment() {
		return SchoolPayment.staticSolrChargeEnrollment(siteRequest_, chargeEnrollment);
	}

	public String strChargeEnrollment() {
		return chargeEnrollment == null ? "" : chargeEnrollment.toString();
	}

	public Boolean sqlChargeEnrollment() {
		return chargeEnrollment;
	}

	public String jsonChargeEnrollment() {
		return chargeEnrollment == null ? "" : chargeEnrollment.toString();
	}

	public String nomAffichageChargeEnrollment() {
		return "enrollment fee";
	}

	public String htmTooltipChargeEnrollment() {
		return null;
	}

	public String htmChargeEnrollment() {
		return chargeEnrollment == null ? "" : StringEscapeUtils.escapeHtml4(strChargeEnrollment());
	}

	public void inputChargeEnrollment(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_chargeEnrollment")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_chargeEnrollment");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChargeEnrollment classSchoolPayment inputSchoolPayment", pk, "ChargeEnrollment w3-input w3-border ");
				a("name", "setChargeEnrollment");
			} else {
				a("class", "valueChargeEnrollment classSchoolPayment inputSchoolPayment", pk, "ChargeEnrollment w3-input w3-border ");
				a("name", "chargeEnrollment");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChargeEnrollment', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_chargeEnrollment')); }, function() { addError($('#", classApiMethodMethod, "_chargeEnrollment')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getChargeEnrollment() != null && getChargeEnrollment())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "ChargeEnrollment ").f().sx(htmChargeEnrollment()).g("span");
		}
	}

	public void htmChargeEnrollment(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChargeEnrollment").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_chargeEnrollment").a("class", "").f().sx("enrollment fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChargeEnrollment(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// chargeMonth //
	/////////////////

	/**	 The entity chargeMonth
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean chargeMonth;
	@JsonIgnore
	public Wrap<Boolean> chargeMonthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("chargeMonth").o(chargeMonth);

	/**	<br/> The entity chargeMonth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeMonth">Find the entity chargeMonth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeMonth(Wrap<Boolean> c);

	public Boolean getChargeMonth() {
		return chargeMonth;
	}

	public void setChargeMonth(Boolean chargeMonth) {
		this.chargeMonth = chargeMonth;
		this.chargeMonthWrap.alreadyInitialized = true;
	}
	public void setChargeMonth(String o) {
		this.chargeMonth = SchoolPayment.staticSetChargeMonth(siteRequest_, o);
		this.chargeMonthWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetChargeMonth(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolPayment chargeMonthInit() {
		if(!chargeMonthWrap.alreadyInitialized) {
			_chargeMonth(chargeMonthWrap);
			if(chargeMonth == null)
				setChargeMonth(chargeMonthWrap.o);
		}
		chargeMonthWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Boolean staticSolrChargeMonth(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrChargeMonth(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeMonth(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeMonth(siteRequest_, SchoolPayment.staticSolrChargeMonth(siteRequest_, SchoolPayment.staticSetChargeMonth(siteRequest_, o)));
	}

	public Boolean solrChargeMonth() {
		return SchoolPayment.staticSolrChargeMonth(siteRequest_, chargeMonth);
	}

	public String strChargeMonth() {
		return chargeMonth == null ? "" : chargeMonth.toString();
	}

	public Boolean sqlChargeMonth() {
		return chargeMonth;
	}

	public String jsonChargeMonth() {
		return chargeMonth == null ? "" : chargeMonth.toString();
	}

	public String nomAffichageChargeMonth() {
		return "monthly fee";
	}

	public String htmTooltipChargeMonth() {
		return null;
	}

	public String htmChargeMonth() {
		return chargeMonth == null ? "" : StringEscapeUtils.escapeHtml4(strChargeMonth());
	}

	public void inputChargeMonth(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_chargeMonth")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_chargeMonth");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChargeMonth classSchoolPayment inputSchoolPayment", pk, "ChargeMonth w3-input w3-border ");
				a("name", "setChargeMonth");
			} else {
				a("class", "valueChargeMonth classSchoolPayment inputSchoolPayment", pk, "ChargeMonth w3-input w3-border ");
				a("name", "chargeMonth");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChargeMonth', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_chargeMonth')); }, function() { addError($('#", classApiMethodMethod, "_chargeMonth')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getChargeMonth() != null && getChargeMonth())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "ChargeMonth ").f().sx(htmChargeMonth()).g("span");
		}
	}

	public void htmChargeMonth(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChargeMonth").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_chargeMonth").a("class", "").f().sx("monthly fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChargeMonth(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// chargeLateFee //
	///////////////////

	/**	 The entity chargeLateFee
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean chargeLateFee;
	@JsonIgnore
	public Wrap<Boolean> chargeLateFeeWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("chargeLateFee").o(chargeLateFee);

	/**	<br/> The entity chargeLateFee
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeLateFee">Find the entity chargeLateFee in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeLateFee(Wrap<Boolean> c);

	public Boolean getChargeLateFee() {
		return chargeLateFee;
	}

	public void setChargeLateFee(Boolean chargeLateFee) {
		this.chargeLateFee = chargeLateFee;
		this.chargeLateFeeWrap.alreadyInitialized = true;
	}
	public void setChargeLateFee(String o) {
		this.chargeLateFee = SchoolPayment.staticSetChargeLateFee(siteRequest_, o);
		this.chargeLateFeeWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetChargeLateFee(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolPayment chargeLateFeeInit() {
		if(!chargeLateFeeWrap.alreadyInitialized) {
			_chargeLateFee(chargeLateFeeWrap);
			if(chargeLateFee == null)
				setChargeLateFee(chargeLateFeeWrap.o);
		}
		chargeLateFeeWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Boolean staticSolrChargeLateFee(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrChargeLateFee(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeLateFee(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeLateFee(siteRequest_, SchoolPayment.staticSolrChargeLateFee(siteRequest_, SchoolPayment.staticSetChargeLateFee(siteRequest_, o)));
	}

	public Boolean solrChargeLateFee() {
		return SchoolPayment.staticSolrChargeLateFee(siteRequest_, chargeLateFee);
	}

	public String strChargeLateFee() {
		return chargeLateFee == null ? "" : chargeLateFee.toString();
	}

	public Boolean sqlChargeLateFee() {
		return chargeLateFee;
	}

	public String jsonChargeLateFee() {
		return chargeLateFee == null ? "" : chargeLateFee.toString();
	}

	public String nomAffichageChargeLateFee() {
		return "late fee";
	}

	public String htmTooltipChargeLateFee() {
		return null;
	}

	public String htmChargeLateFee() {
		return chargeLateFee == null ? "" : StringEscapeUtils.escapeHtml4(strChargeLateFee());
	}

	public void inputChargeLateFee(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_chargeLateFee")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_chargeLateFee");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChargeLateFee classSchoolPayment inputSchoolPayment", pk, "ChargeLateFee w3-input w3-border ");
				a("name", "setChargeLateFee");
			} else {
				a("class", "valueChargeLateFee classSchoolPayment inputSchoolPayment", pk, "ChargeLateFee w3-input w3-border ");
				a("name", "chargeLateFee");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChargeLateFee', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_chargeLateFee')); }, function() { addError($('#", classApiMethodMethod, "_chargeLateFee')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getChargeLateFee() != null && getChargeLateFee())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
				e("span").a("class", "varSchoolPayment", pk, "ChargeLateFee ").f().sx(htmChargeLateFee()).g("span");
		}
	}

	public void htmChargeLateFee(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChargeLateFee").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_chargeLateFee").a("class", "").f().sx("late fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChargeLateFee(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////
	// now //
	/////////

	/**	 The entity now
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate now;
	@JsonIgnore
	public Wrap<LocalDate> nowWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("now").o(now);

	/**	<br/> The entity now
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:now">Find the entity now in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _now(Wrap<LocalDate> c);

	public LocalDate getNow() {
		return now;
	}

	public void setNow(LocalDate now) {
		this.now = now;
		this.nowWrap.alreadyInitialized = true;
	}
	public void setNow(Instant o) {
		this.now = o == null ? null : LocalDate.from(o);
		this.nowWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setNow(String o) {
		this.now = SchoolPayment.staticSetNow(siteRequest_, o);
		this.nowWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetNow(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setNow(Date o) {
		this.now = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.nowWrap.alreadyInitialized = true;
	}
	protected SchoolPayment nowInit() {
		if(!nowWrap.alreadyInitialized) {
			_now(nowWrap);
			if(now == null)
				setNow(nowWrap.o);
		}
		nowWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Date staticSolrNow(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrNow(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqNow(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrNow(siteRequest_, SchoolPayment.staticSolrNow(siteRequest_, SchoolPayment.staticSetNow(siteRequest_, o)));
	}

	public Date solrNow() {
		return SchoolPayment.staticSolrNow(siteRequest_, now);
	}

	public String strNow() {
		return now == null ? "" : now.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlNow() {
		return now;
	}

	public String jsonNow() {
		return now == null ? "" : now.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageNow() {
		return null;
	}

	public String htmTooltipNow() {
		return null;
	}

	public String htmNow() {
		return now == null ? "" : StringEscapeUtils.escapeHtml4(strNow());
	}

	////////////////
	// paymentDay //
	////////////////

	/**	 The entity paymentDay
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paymentDay;
	@JsonIgnore
	public Wrap<Integer> paymentDayWrap = new Wrap<Integer>().p(this).c(Integer.class).var("paymentDay").o(paymentDay);

	/**	<br/> The entity paymentDay
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDay">Find the entity paymentDay in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentDay(Wrap<Integer> c);

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
		this.paymentDayWrap.alreadyInitialized = true;
	}
	public void setPaymentDay(String o) {
		this.paymentDay = SchoolPayment.staticSetPaymentDay(siteRequest_, o);
		this.paymentDayWrap.alreadyInitialized = true;
	}
	public static Integer staticSetPaymentDay(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment paymentDayInit() {
		if(!paymentDayWrap.alreadyInitialized) {
			_paymentDay(paymentDayWrap);
			if(paymentDay == null)
				setPaymentDay(paymentDayWrap.o);
		}
		paymentDayWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrPaymentDay(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrPaymentDay(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentDay(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentDay(siteRequest_, SchoolPayment.staticSolrPaymentDay(siteRequest_, SchoolPayment.staticSetPaymentDay(siteRequest_, o)));
	}

	public Integer solrPaymentDay() {
		return SchoolPayment.staticSolrPaymentDay(siteRequest_, paymentDay);
	}

	public String strPaymentDay() {
		return paymentDay == null ? "" : paymentDay.toString();
	}

	public Integer sqlPaymentDay() {
		return paymentDay;
	}

	public String jsonPaymentDay() {
		return paymentDay == null ? "" : paymentDay.toString();
	}

	public String nomAffichagePaymentDay() {
		return null;
	}

	public String htmTooltipPaymentDay() {
		return null;
	}

	public String htmPaymentDay() {
		return paymentDay == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentDay());
	}

	/////////////////
	// paymentNext //
	/////////////////

	/**	 The entity paymentNext
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paymentNext;
	@JsonIgnore
	public Wrap<LocalDate> paymentNextWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("paymentNext").o(paymentNext);

	/**	<br/> The entity paymentNext
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentNext">Find the entity paymentNext in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentNext(Wrap<LocalDate> c);

	public LocalDate getPaymentNext() {
		return paymentNext;
	}

	public void setPaymentNext(LocalDate paymentNext) {
		this.paymentNext = paymentNext;
		this.paymentNextWrap.alreadyInitialized = true;
	}
	public void setPaymentNext(Instant o) {
		this.paymentNext = o == null ? null : LocalDate.from(o);
		this.paymentNextWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPaymentNext(String o) {
		this.paymentNext = SchoolPayment.staticSetPaymentNext(siteRequest_, o);
		this.paymentNextWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetPaymentNext(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPaymentNext(Date o) {
		this.paymentNext = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.paymentNextWrap.alreadyInitialized = true;
	}
	protected SchoolPayment paymentNextInit() {
		if(!paymentNextWrap.alreadyInitialized) {
			_paymentNext(paymentNextWrap);
			if(paymentNext == null)
				setPaymentNext(paymentNextWrap.o);
		}
		paymentNextWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Date staticSolrPaymentNext(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPaymentNext(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPaymentNext(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentNext(siteRequest_, SchoolPayment.staticSolrPaymentNext(siteRequest_, SchoolPayment.staticSetPaymentNext(siteRequest_, o)));
	}

	public Date solrPaymentNext() {
		return SchoolPayment.staticSolrPaymentNext(siteRequest_, paymentNext);
	}

	public String strPaymentNext() {
		return paymentNext == null ? "" : paymentNext.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public LocalDate sqlPaymentNext() {
		return paymentNext;
	}

	public String jsonPaymentNext() {
		return paymentNext == null ? "" : paymentNext.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichagePaymentNext() {
		return "next payment date";
	}

	public String htmTooltipPaymentNext() {
		return null;
	}

	public String htmPaymentNext() {
		return paymentNext == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentNext());
	}

	/////////////////////
	// chargeAmountDue //
	/////////////////////

	/**	 The entity chargeAmountDue
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountDue;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountDueWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountDue").o(chargeAmountDue);

	/**	<br/> The entity chargeAmountDue
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountDue">Find the entity chargeAmountDue in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountDue(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountDue() {
		return chargeAmountDue;
	}

	public void setChargeAmountDue(BigDecimal chargeAmountDue) {
		this.chargeAmountDue = chargeAmountDue;
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public void setChargeAmountDue(String o) {
		this.chargeAmountDue = SchoolPayment.staticSetChargeAmountDue(siteRequest_, o);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountDue(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountDue(Double o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public void setChargeAmountDue(Integer o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	protected SchoolPayment chargeAmountDueInit() {
		if(!chargeAmountDueWrap.alreadyInitialized) {
			_chargeAmountDue(chargeAmountDueWrap);
			if(chargeAmountDue == null)
				setChargeAmountDue(chargeAmountDueWrap.o);
		}
		chargeAmountDueWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrChargeAmountDue(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountDue(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountDue(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeAmountDue(siteRequest_, SchoolPayment.staticSolrChargeAmountDue(siteRequest_, SchoolPayment.staticSetChargeAmountDue(siteRequest_, o)));
	}

	public Double solrChargeAmountDue() {
		return SchoolPayment.staticSolrChargeAmountDue(siteRequest_, chargeAmountDue);
	}

	public String strChargeAmountDue() {
		return chargeAmountDue == null ? "" : chargeAmountDue.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlChargeAmountDue() {
		return chargeAmountDue;
	}

	public String jsonChargeAmountDue() {
		return chargeAmountDue == null ? "" : chargeAmountDue.toString();
	}

	public String nomAffichageChargeAmountDue() {
		return "charge amount due";
	}

	public String htmTooltipChargeAmountDue() {
		return null;
	}

	public String htmChargeAmountDue() {
		return chargeAmountDue == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountDue());
	}

	////////////////////////
	// chargeAmountPassed //
	////////////////////////

	/**	 The entity chargeAmountPassed
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountPassed;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountPassedWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountPassed").o(chargeAmountPassed);

	/**	<br/> The entity chargeAmountPassed
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountPassed">Find the entity chargeAmountPassed in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountPassed(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountPassed() {
		return chargeAmountPassed;
	}

	public void setChargeAmountPassed(BigDecimal chargeAmountPassed) {
		this.chargeAmountPassed = chargeAmountPassed;
		this.chargeAmountPassedWrap.alreadyInitialized = true;
	}
	public void setChargeAmountPassed(String o) {
		this.chargeAmountPassed = SchoolPayment.staticSetChargeAmountPassed(siteRequest_, o);
		this.chargeAmountPassedWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountPassed(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountPassed(Double o) {
			this.chargeAmountPassed = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountPassedWrap.alreadyInitialized = true;
	}
	public void setChargeAmountPassed(Integer o) {
			this.chargeAmountPassed = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountPassedWrap.alreadyInitialized = true;
	}
	protected SchoolPayment chargeAmountPassedInit() {
		if(!chargeAmountPassedWrap.alreadyInitialized) {
			_chargeAmountPassed(chargeAmountPassedWrap);
			if(chargeAmountPassed == null)
				setChargeAmountPassed(chargeAmountPassedWrap.o);
		}
		chargeAmountPassedWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrChargeAmountPassed(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountPassed(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountPassed(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeAmountPassed(siteRequest_, SchoolPayment.staticSolrChargeAmountPassed(siteRequest_, SchoolPayment.staticSetChargeAmountPassed(siteRequest_, o)));
	}

	public Double solrChargeAmountPassed() {
		return SchoolPayment.staticSolrChargeAmountPassed(siteRequest_, chargeAmountPassed);
	}

	public String strChargeAmountPassed() {
		return chargeAmountPassed == null ? "" : chargeAmountPassed.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlChargeAmountPassed() {
		return chargeAmountPassed;
	}

	public String jsonChargeAmountPassed() {
		return chargeAmountPassed == null ? "" : chargeAmountPassed.toString();
	}

	public String nomAffichageChargeAmountPassed() {
		return "charge amount passed";
	}

	public String htmTooltipChargeAmountPassed() {
		return null;
	}

	public String htmChargeAmountPassed() {
		return chargeAmountPassed == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountPassed());
	}

	///////////////////////////
	// chargeAmountNotPassed //
	///////////////////////////

	/**	 The entity chargeAmountNotPassed
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountNotPassed;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountNotPassedWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountNotPassed").o(chargeAmountNotPassed);

	/**	<br/> The entity chargeAmountNotPassed
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountNotPassed">Find the entity chargeAmountNotPassed in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountNotPassed(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountNotPassed() {
		return chargeAmountNotPassed;
	}

	public void setChargeAmountNotPassed(BigDecimal chargeAmountNotPassed) {
		this.chargeAmountNotPassed = chargeAmountNotPassed;
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	public void setChargeAmountNotPassed(String o) {
		this.chargeAmountNotPassed = SchoolPayment.staticSetChargeAmountNotPassed(siteRequest_, o);
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountNotPassed(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountNotPassed(Double o) {
			this.chargeAmountNotPassed = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	public void setChargeAmountNotPassed(Integer o) {
			this.chargeAmountNotPassed = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	protected SchoolPayment chargeAmountNotPassedInit() {
		if(!chargeAmountNotPassedWrap.alreadyInitialized) {
			_chargeAmountNotPassed(chargeAmountNotPassedWrap);
			if(chargeAmountNotPassed == null)
				setChargeAmountNotPassed(chargeAmountNotPassedWrap.o);
		}
		chargeAmountNotPassedWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrChargeAmountNotPassed(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountNotPassed(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountNotPassed(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeAmountNotPassed(siteRequest_, SchoolPayment.staticSolrChargeAmountNotPassed(siteRequest_, SchoolPayment.staticSetChargeAmountNotPassed(siteRequest_, o)));
	}

	public Double solrChargeAmountNotPassed() {
		return SchoolPayment.staticSolrChargeAmountNotPassed(siteRequest_, chargeAmountNotPassed);
	}

	public String strChargeAmountNotPassed() {
		return chargeAmountNotPassed == null ? "" : chargeAmountNotPassed.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlChargeAmountNotPassed() {
		return chargeAmountNotPassed;
	}

	public String jsonChargeAmountNotPassed() {
		return chargeAmountNotPassed == null ? "" : chargeAmountNotPassed.toString();
	}

	public String nomAffichageChargeAmountNotPassed() {
		return "charge amount not passed";
	}

	public String htmTooltipChargeAmountNotPassed() {
		return null;
	}

	public String htmChargeAmountNotPassed() {
		return chargeAmountNotPassed == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountNotPassed());
	}

	////////////////////////
	// chargeAmountFuture //
	////////////////////////

	/**	 The entity chargeAmountFuture
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountFuture;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountFutureWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountFuture").o(chargeAmountFuture);

	/**	<br/> The entity chargeAmountFuture
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountFuture">Find the entity chargeAmountFuture in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountFuture(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountFuture() {
		return chargeAmountFuture;
	}

	public void setChargeAmountFuture(BigDecimal chargeAmountFuture) {
		this.chargeAmountFuture = chargeAmountFuture;
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public void setChargeAmountFuture(String o) {
		this.chargeAmountFuture = SchoolPayment.staticSetChargeAmountFuture(siteRequest_, o);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountFuture(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountFuture(Double o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public void setChargeAmountFuture(Integer o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	protected SchoolPayment chargeAmountFutureInit() {
		if(!chargeAmountFutureWrap.alreadyInitialized) {
			_chargeAmountFuture(chargeAmountFutureWrap);
			if(chargeAmountFuture == null)
				setChargeAmountFuture(chargeAmountFutureWrap.o);
		}
		chargeAmountFutureWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Double staticSolrChargeAmountFuture(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountFuture(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountFuture(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrChargeAmountFuture(siteRequest_, SchoolPayment.staticSolrChargeAmountFuture(siteRequest_, SchoolPayment.staticSetChargeAmountFuture(siteRequest_, o)));
	}

	public Double solrChargeAmountFuture() {
		return SchoolPayment.staticSolrChargeAmountFuture(siteRequest_, chargeAmountFuture);
	}

	public String strChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : chargeAmountFuture.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public BigDecimal sqlChargeAmountFuture() {
		return chargeAmountFuture;
	}

	public String jsonChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : chargeAmountFuture.toString();
	}

	public String nomAffichageChargeAmountFuture() {
		return "future charge amount";
	}

	public String htmTooltipChargeAmountFuture() {
		return null;
	}

	public String htmChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountFuture());
	}

	//////////////////////
	// paymentShortName //
	//////////////////////

	/**	 The entity paymentShortName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paymentShortName;
	@JsonIgnore
	public Wrap<String> paymentShortNameWrap = new Wrap<String>().p(this).c(String.class).var("paymentShortName").o(paymentShortName);

	/**	<br/> The entity paymentShortName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentShortName">Find the entity paymentShortName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentShortName(Wrap<String> c);

	public String getPaymentShortName() {
		return paymentShortName;
	}
	public void setPaymentShortName(String o) {
		this.paymentShortName = SchoolPayment.staticSetPaymentShortName(siteRequest_, o);
		this.paymentShortNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolPayment paymentShortNameInit() {
		if(!paymentShortNameWrap.alreadyInitialized) {
			_paymentShortName(paymentShortNameWrap);
			if(paymentShortName == null)
				setPaymentShortName(paymentShortNameWrap.o);
		}
		paymentShortNameWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static String staticSolrPaymentShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentShortName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentShortName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentShortName(siteRequest_, SchoolPayment.staticSolrPaymentShortName(siteRequest_, SchoolPayment.staticSetPaymentShortName(siteRequest_, o)));
	}

	public String solrPaymentShortName() {
		return SchoolPayment.staticSolrPaymentShortName(siteRequest_, paymentShortName);
	}

	public String strPaymentShortName() {
		return paymentShortName == null ? "" : paymentShortName;
	}

	public String sqlPaymentShortName() {
		return paymentShortName;
	}

	public String jsonPaymentShortName() {
		return paymentShortName == null ? "" : paymentShortName;
	}

	public String nomAffichagePaymentShortName() {
		return "name";
	}

	public String htmTooltipPaymentShortName() {
		return null;
	}

	public String htmPaymentShortName() {
		return paymentShortName == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentShortName());
	}

	public void inputPaymentShortName(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "name")
				.a("id", classApiMethodMethod, "_paymentShortName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPaymentShortName classSchoolPayment inputSchoolPayment", pk, "PaymentShortName w3-input w3-border ");
					a("name", "setPaymentShortName");
				} else {
					a("class", "valuePaymentShortName w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "PaymentShortName w3-input w3-border ");
					a("name", "paymentShortName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentShortName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_paymentShortName')); }, function() { addError($('#", classApiMethodMethod, "_paymentShortName')); }); ");
				}
				a("value", strPaymentShortName())
			.fg();

		} else {
				e("span").a("class", "varSchoolPayment", pk, "PaymentShortName ").f().sx(htmPaymentShortName()).g("span");
		}
	}

	public void htmPaymentShortName(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentPaymentShortName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_paymentShortName").a("class", "").f().sx("name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaymentShortName(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentShortName')); $('#", classApiMethodMethod, "_paymentShortName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setPaymentShortName', null, function() { addGlow($('#", classApiMethodMethod, "_paymentShortName')); }, function() { addError($('#", classApiMethodMethod, "_paymentShortName')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////
	// paymentCompleteName //
	/////////////////////////

	/**	 The entity paymentCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paymentCompleteName;
	@JsonIgnore
	public Wrap<String> paymentCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("paymentCompleteName").o(paymentCompleteName);

	/**	<br/> The entity paymentCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentCompleteName">Find the entity paymentCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentCompleteName(Wrap<String> c);

	public String getPaymentCompleteName() {
		return paymentCompleteName;
	}
	public void setPaymentCompleteName(String o) {
		this.paymentCompleteName = SchoolPayment.staticSetPaymentCompleteName(siteRequest_, o);
		this.paymentCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrPaymentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentCompleteName(siteRequest_, SchoolPayment.staticSolrPaymentCompleteName(siteRequest_, SchoolPayment.staticSetPaymentCompleteName(siteRequest_, o)));
	}

	public String solrPaymentCompleteName() {
		return SchoolPayment.staticSolrPaymentCompleteName(siteRequest_, paymentCompleteName);
	}

	public String strPaymentCompleteName() {
		return paymentCompleteName == null ? "" : paymentCompleteName;
	}

	public String sqlPaymentCompleteName() {
		return paymentCompleteName;
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

	///////////////////
	// paymentGroups //
	///////////////////

	/**	 The entity paymentGroups
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolPayment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolPayment> paymentGroups = new ArrayList<SchoolPayment>();
	@JsonIgnore
	public Wrap<List<SchoolPayment>> paymentGroupsWrap = new Wrap<List<SchoolPayment>>().p(this).c(List.class).var("paymentGroups").o(paymentGroups);

	/**	<br/> The entity paymentGroups
	 *  It is constructed before being initialized with the constructor by default List<SchoolPayment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentGroups">Find the entity paymentGroups in Solr</a>
	 * <br/>
	 * @param paymentGroups is the entity already constructed. 
	 **/
	protected abstract void _paymentGroups(List<SchoolPayment> l);

	public List<SchoolPayment> getPaymentGroups() {
		return paymentGroups;
	}

	public void setPaymentGroups(List<SchoolPayment> paymentGroups) {
		this.paymentGroups = paymentGroups;
		this.paymentGroupsWrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPaymentGroups(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolPayment addPaymentGroups(SchoolPayment...objets) {
		for(SchoolPayment o : objets) {
			addPaymentGroups(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addPaymentGroups(SchoolPayment o) {
		if(o != null && !paymentGroups.contains(o))
			this.paymentGroups.add(o);
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentGroupsInit() {
		if(!paymentGroupsWrap.alreadyInitialized) {
			_paymentGroups(paymentGroups);
		}
		paymentGroupsWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	/////////////////////
	// paymentPayments //
	/////////////////////

	/**	 The entity paymentPayments
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolPayment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolPayment> paymentPayments = new ArrayList<SchoolPayment>();
	@JsonIgnore
	public Wrap<List<SchoolPayment>> paymentPaymentsWrap = new Wrap<List<SchoolPayment>>().p(this).c(List.class).var("paymentPayments").o(paymentPayments);

	/**	<br/> The entity paymentPayments
	 *  It is constructed before being initialized with the constructor by default List<SchoolPayment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentPayments">Find the entity paymentPayments in Solr</a>
	 * <br/>
	 * @param paymentPayments is the entity already constructed. 
	 **/
	protected abstract void _paymentPayments(List<SchoolPayment> l);

	public List<SchoolPayment> getPaymentPayments() {
		return paymentPayments;
	}

	public void setPaymentPayments(List<SchoolPayment> paymentPayments) {
		this.paymentPayments = paymentPayments;
		this.paymentPaymentsWrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPaymentPayments(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolPayment addPaymentPayments(SchoolPayment...objets) {
		for(SchoolPayment o : objets) {
			addPaymentPayments(o);
		}
		return (SchoolPayment)this;
	}
	public SchoolPayment addPaymentPayments(SchoolPayment o) {
		if(o != null && !paymentPayments.contains(o))
			this.paymentPayments.add(o);
		return (SchoolPayment)this;
	}
	protected SchoolPayment paymentPaymentsInit() {
		if(!paymentPaymentsWrap.alreadyInitialized) {
			_paymentPayments(paymentPayments);
		}
		paymentPaymentsWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	///////////////////
	// paymentNumber //
	///////////////////

	/**	 The entity paymentNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paymentNumber;
	@JsonIgnore
	public Wrap<Integer> paymentNumberWrap = new Wrap<Integer>().p(this).c(Integer.class).var("paymentNumber").o(paymentNumber);

	/**	<br/> The entity paymentNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.SchoolPayment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentNumber">Find the entity paymentNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentNumber(Wrap<Integer> c);

	public Integer getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
		this.paymentNumberWrap.alreadyInitialized = true;
	}
	public void setPaymentNumber(String o) {
		this.paymentNumber = SchoolPayment.staticSetPaymentNumber(siteRequest_, o);
		this.paymentNumberWrap.alreadyInitialized = true;
	}
	public static Integer staticSetPaymentNumber(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolPayment paymentNumberInit() {
		if(!paymentNumberWrap.alreadyInitialized) {
			_paymentNumber(paymentNumberWrap);
			if(paymentNumber == null)
				setPaymentNumber(paymentNumberWrap.o);
		}
		paymentNumberWrap.alreadyInitialized(true);
		return (SchoolPayment)this;
	}

	public static Integer staticSolrPaymentNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrPaymentNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolPayment.staticSolrStrPaymentNumber(siteRequest_, SchoolPayment.staticSolrPaymentNumber(siteRequest_, SchoolPayment.staticSetPaymentNumber(siteRequest_, o)));
	}

	public Integer solrPaymentNumber() {
		return SchoolPayment.staticSolrPaymentNumber(siteRequest_, paymentNumber);
	}

	public String strPaymentNumber() {
		return paymentNumber == null ? "" : paymentNumber.toString();
	}

	public Integer sqlPaymentNumber() {
		return paymentNumber;
	}

	public String jsonPaymentNumber() {
		return paymentNumber == null ? "" : paymentNumber.toString();
	}

	public String nomAffichagePaymentNumber() {
		return null;
	}

	public String htmTooltipPaymentNumber() {
		return null;
	}

	public String htmPaymentNumber() {
		return paymentNumber == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentNumber());
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
		paymentKeyInit();
		enrollmentKeyInit();
		enrollmentSearchInit();
		enrollment_Init();
		schoolNumberInit();
		userKeysInit();
		schoolKeyInit();
		schoolAddressInit();
		schoolPhoneNumberInit();
		yearKeyInit();
		sessionKeyInit();
		ageKeyInit();
		blockKeyInit();
		childKeyInit();
		momKeysInit();
		dadKeysInit();
		guardianKeysInit();
		childCompleteNamePreferredInit();
		childBirthDateInit();
		momCompleteNamePreferredInit();
		dadCompleteNamePreferredInit();
		schoolNameInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		yearStartInit();
		yearEndInit();
		seasonStartDateInit();
		yearEnrollmentFeeInit();
		sessionStartDateInit();
		sessionEndDateInit();
		ageStartInit();
		ageEndInit();
		blockCompleteNameInit();
		blockStartTimeInit();
		blockEndTimeInit();
		blockPricePerMonthInit();
		enrollmentGroupNameInit();
		blockTotalPriceInit();
		enrollmentPaymentEachMonthInit();
		paymentDescriptionInit();
		paymentDateInit();
		lateFeeDateInit();
		paymentYearInit();
		paymentAmountInit();
		paymentCashInit();
		paymentCheckInit();
		paymentECheckInit();
		paymentSystemInit();
		paymentTypeInit();
		paymentByInit();
		transactionIdInit();
		customerProfileIdInit();
		transactionStatusInit();
		paymentRecievedInit();
		chargeAmountInit();
		chargeFirstLastInit();
		chargeEnrollmentInit();
		chargeMonthInit();
		chargeLateFeeInit();
		nowInit();
		paymentDayInit();
		paymentNextInit();
		chargeAmountDueInit();
		chargeAmountPassedInit();
		chargeAmountNotPassedInit();
		chargeAmountFutureInit();
		paymentShortNameInit();
		paymentCompleteNameInit();
		paymentGroupsInit();
		paymentPaymentsInit();
		paymentNumberInit();
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
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtainSchoolPayment(String var) {
		SchoolPayment oSchoolPayment = (SchoolPayment)this;
		switch(var) {
			case "paymentKey":
				return oSchoolPayment.paymentKey;
			case "enrollmentKey":
				return oSchoolPayment.enrollmentKey;
			case "enrollmentSearch":
				return oSchoolPayment.enrollmentSearch;
			case "enrollment_":
				return oSchoolPayment.enrollment_;
			case "schoolNumber":
				return oSchoolPayment.schoolNumber;
			case "userKeys":
				return oSchoolPayment.userKeys;
			case "schoolKey":
				return oSchoolPayment.schoolKey;
			case "schoolAddress":
				return oSchoolPayment.schoolAddress;
			case "schoolPhoneNumber":
				return oSchoolPayment.schoolPhoneNumber;
			case "yearKey":
				return oSchoolPayment.yearKey;
			case "sessionKey":
				return oSchoolPayment.sessionKey;
			case "ageKey":
				return oSchoolPayment.ageKey;
			case "blockKey":
				return oSchoolPayment.blockKey;
			case "childKey":
				return oSchoolPayment.childKey;
			case "momKeys":
				return oSchoolPayment.momKeys;
			case "dadKeys":
				return oSchoolPayment.dadKeys;
			case "guardianKeys":
				return oSchoolPayment.guardianKeys;
			case "childCompleteNamePreferred":
				return oSchoolPayment.childCompleteNamePreferred;
			case "childBirthDate":
				return oSchoolPayment.childBirthDate;
			case "momCompleteNamePreferred":
				return oSchoolPayment.momCompleteNamePreferred;
			case "dadCompleteNamePreferred":
				return oSchoolPayment.dadCompleteNamePreferred;
			case "schoolName":
				return oSchoolPayment.schoolName;
			case "schoolCompleteName":
				return oSchoolPayment.schoolCompleteName;
			case "schoolLocation":
				return oSchoolPayment.schoolLocation;
			case "yearStart":
				return oSchoolPayment.yearStart;
			case "yearEnd":
				return oSchoolPayment.yearEnd;
			case "seasonStartDate":
				return oSchoolPayment.seasonStartDate;
			case "yearEnrollmentFee":
				return oSchoolPayment.yearEnrollmentFee;
			case "sessionStartDate":
				return oSchoolPayment.sessionStartDate;
			case "sessionEndDate":
				return oSchoolPayment.sessionEndDate;
			case "ageStart":
				return oSchoolPayment.ageStart;
			case "ageEnd":
				return oSchoolPayment.ageEnd;
			case "blockCompleteName":
				return oSchoolPayment.blockCompleteName;
			case "blockStartTime":
				return oSchoolPayment.blockStartTime;
			case "blockEndTime":
				return oSchoolPayment.blockEndTime;
			case "blockPricePerMonth":
				return oSchoolPayment.blockPricePerMonth;
			case "enrollmentGroupName":
				return oSchoolPayment.enrollmentGroupName;
			case "blockTotalPrice":
				return oSchoolPayment.blockTotalPrice;
			case "enrollmentPaymentEachMonth":
				return oSchoolPayment.enrollmentPaymentEachMonth;
			case "paymentDescription":
				return oSchoolPayment.paymentDescription;
			case "paymentDate":
				return oSchoolPayment.paymentDate;
			case "lateFeeDate":
				return oSchoolPayment.lateFeeDate;
			case "paymentYear":
				return oSchoolPayment.paymentYear;
			case "paymentAmount":
				return oSchoolPayment.paymentAmount;
			case "paymentCash":
				return oSchoolPayment.paymentCash;
			case "paymentCheck":
				return oSchoolPayment.paymentCheck;
			case "paymentECheck":
				return oSchoolPayment.paymentECheck;
			case "paymentSystem":
				return oSchoolPayment.paymentSystem;
			case "paymentType":
				return oSchoolPayment.paymentType;
			case "paymentBy":
				return oSchoolPayment.paymentBy;
			case "transactionId":
				return oSchoolPayment.transactionId;
			case "customerProfileId":
				return oSchoolPayment.customerProfileId;
			case "transactionStatus":
				return oSchoolPayment.transactionStatus;
			case "paymentRecieved":
				return oSchoolPayment.paymentRecieved;
			case "chargeAmount":
				return oSchoolPayment.chargeAmount;
			case "chargeFirstLast":
				return oSchoolPayment.chargeFirstLast;
			case "chargeEnrollment":
				return oSchoolPayment.chargeEnrollment;
			case "chargeMonth":
				return oSchoolPayment.chargeMonth;
			case "chargeLateFee":
				return oSchoolPayment.chargeLateFee;
			case "now":
				return oSchoolPayment.now;
			case "paymentDay":
				return oSchoolPayment.paymentDay;
			case "paymentNext":
				return oSchoolPayment.paymentNext;
			case "chargeAmountDue":
				return oSchoolPayment.chargeAmountDue;
			case "chargeAmountPassed":
				return oSchoolPayment.chargeAmountPassed;
			case "chargeAmountNotPassed":
				return oSchoolPayment.chargeAmountNotPassed;
			case "chargeAmountFuture":
				return oSchoolPayment.chargeAmountFuture;
			case "paymentShortName":
				return oSchoolPayment.paymentShortName;
			case "paymentCompleteName":
				return oSchoolPayment.paymentCompleteName;
			case "paymentGroups":
				return oSchoolPayment.paymentGroups;
			case "paymentPayments":
				return oSchoolPayment.paymentPayments;
			case "paymentNumber":
				return oSchoolPayment.paymentNumber;
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
			case "enrollmentKey":
				if(oSchoolPayment.getEnrollmentKey() == null)
					oSchoolPayment.setEnrollmentKey((Long)val);
				if(!saves.contains("enrollmentKey"))
					saves.add("enrollmentKey");
				return val;
			default:
				return super.attributeCluster(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSchoolPayment(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolPayment(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "paymentKey":
			return SchoolPayment.staticSetPaymentKey(siteRequest_, o);
		case "enrollmentKey":
			return SchoolPayment.staticSetEnrollmentKey(siteRequest_, o);
		case "schoolNumber":
			return SchoolPayment.staticSetSchoolNumber(siteRequest_, o);
		case "userKeys":
			return SchoolPayment.staticSetUserKeys(siteRequest_, o);
		case "schoolKey":
			return SchoolPayment.staticSetSchoolKey(siteRequest_, o);
		case "schoolAddress":
			return SchoolPayment.staticSetSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolPayment.staticSetSchoolPhoneNumber(siteRequest_, o);
		case "yearKey":
			return SchoolPayment.staticSetYearKey(siteRequest_, o);
		case "sessionKey":
			return SchoolPayment.staticSetSessionKey(siteRequest_, o);
		case "ageKey":
			return SchoolPayment.staticSetAgeKey(siteRequest_, o);
		case "blockKey":
			return SchoolPayment.staticSetBlockKey(siteRequest_, o);
		case "childKey":
			return SchoolPayment.staticSetChildKey(siteRequest_, o);
		case "momKeys":
			return SchoolPayment.staticSetMomKeys(siteRequest_, o);
		case "dadKeys":
			return SchoolPayment.staticSetDadKeys(siteRequest_, o);
		case "guardianKeys":
			return SchoolPayment.staticSetGuardianKeys(siteRequest_, o);
		case "childCompleteNamePreferred":
			return SchoolPayment.staticSetChildCompleteNamePreferred(siteRequest_, o);
		case "childBirthDate":
			return SchoolPayment.staticSetChildBirthDate(siteRequest_, o);
		case "momCompleteNamePreferred":
			return SchoolPayment.staticSetMomCompleteNamePreferred(siteRequest_, o);
		case "dadCompleteNamePreferred":
			return SchoolPayment.staticSetDadCompleteNamePreferred(siteRequest_, o);
		case "schoolName":
			return SchoolPayment.staticSetSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolPayment.staticSetSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolPayment.staticSetSchoolLocation(siteRequest_, o);
		case "yearStart":
			return SchoolPayment.staticSetYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolPayment.staticSetYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return SchoolPayment.staticSetSeasonStartDate(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolPayment.staticSetYearEnrollmentFee(siteRequest_, o);
		case "sessionStartDate":
			return SchoolPayment.staticSetSessionStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolPayment.staticSetSessionEndDate(siteRequest_, o);
		case "ageStart":
			return SchoolPayment.staticSetAgeStart(siteRequest_, o);
		case "ageEnd":
			return SchoolPayment.staticSetAgeEnd(siteRequest_, o);
		case "blockCompleteName":
			return SchoolPayment.staticSetBlockCompleteName(siteRequest_, o);
		case "blockStartTime":
			return SchoolPayment.staticSetBlockStartTime(siteRequest_, o);
		case "blockEndTime":
			return SchoolPayment.staticSetBlockEndTime(siteRequest_, o);
		case "blockPricePerMonth":
			return SchoolPayment.staticSetBlockPricePerMonth(siteRequest_, o);
		case "enrollmentGroupName":
			return SchoolPayment.staticSetEnrollmentGroupName(siteRequest_, o);
		case "blockTotalPrice":
			return SchoolPayment.staticSetBlockTotalPrice(siteRequest_, o);
		case "enrollmentPaymentEachMonth":
			return SchoolPayment.staticSetEnrollmentPaymentEachMonth(siteRequest_, o);
		case "paymentDescription":
			return SchoolPayment.staticSetPaymentDescription(siteRequest_, o);
		case "paymentDate":
			return SchoolPayment.staticSetPaymentDate(siteRequest_, o);
		case "lateFeeDate":
			return SchoolPayment.staticSetLateFeeDate(siteRequest_, o);
		case "paymentYear":
			return SchoolPayment.staticSetPaymentYear(siteRequest_, o);
		case "paymentAmount":
			return SchoolPayment.staticSetPaymentAmount(siteRequest_, o);
		case "paymentCash":
			return SchoolPayment.staticSetPaymentCash(siteRequest_, o);
		case "paymentCheck":
			return SchoolPayment.staticSetPaymentCheck(siteRequest_, o);
		case "paymentECheck":
			return SchoolPayment.staticSetPaymentECheck(siteRequest_, o);
		case "paymentSystem":
			return SchoolPayment.staticSetPaymentSystem(siteRequest_, o);
		case "paymentType":
			return SchoolPayment.staticSetPaymentType(siteRequest_, o);
		case "paymentBy":
			return SchoolPayment.staticSetPaymentBy(siteRequest_, o);
		case "transactionId":
			return SchoolPayment.staticSetTransactionId(siteRequest_, o);
		case "customerProfileId":
			return SchoolPayment.staticSetCustomerProfileId(siteRequest_, o);
		case "transactionStatus":
			return SchoolPayment.staticSetTransactionStatus(siteRequest_, o);
		case "paymentRecieved":
			return SchoolPayment.staticSetPaymentRecieved(siteRequest_, o);
		case "chargeAmount":
			return SchoolPayment.staticSetChargeAmount(siteRequest_, o);
		case "chargeFirstLast":
			return SchoolPayment.staticSetChargeFirstLast(siteRequest_, o);
		case "chargeEnrollment":
			return SchoolPayment.staticSetChargeEnrollment(siteRequest_, o);
		case "chargeMonth":
			return SchoolPayment.staticSetChargeMonth(siteRequest_, o);
		case "chargeLateFee":
			return SchoolPayment.staticSetChargeLateFee(siteRequest_, o);
		case "now":
			return SchoolPayment.staticSetNow(siteRequest_, o);
		case "paymentDay":
			return SchoolPayment.staticSetPaymentDay(siteRequest_, o);
		case "paymentNext":
			return SchoolPayment.staticSetPaymentNext(siteRequest_, o);
		case "chargeAmountDue":
			return SchoolPayment.staticSetChargeAmountDue(siteRequest_, o);
		case "chargeAmountPassed":
			return SchoolPayment.staticSetChargeAmountPassed(siteRequest_, o);
		case "chargeAmountNotPassed":
			return SchoolPayment.staticSetChargeAmountNotPassed(siteRequest_, o);
		case "chargeAmountFuture":
			return SchoolPayment.staticSetChargeAmountFuture(siteRequest_, o);
		case "paymentShortName":
			return SchoolPayment.staticSetPaymentShortName(siteRequest_, o);
		case "paymentCompleteName":
			return SchoolPayment.staticSetPaymentCompleteName(siteRequest_, o);
		case "paymentNumber":
			return SchoolPayment.staticSetPaymentNumber(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolPayment(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolPayment(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "paymentKey":
			return SchoolPayment.staticSolrPaymentKey(siteRequest_, (Long)o);
		case "enrollmentKey":
			return SchoolPayment.staticSolrEnrollmentKey(siteRequest_, (Long)o);
		case "schoolNumber":
			return SchoolPayment.staticSolrSchoolNumber(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolPayment.staticSolrUserKeys(siteRequest_, (Long)o);
		case "schoolKey":
			return SchoolPayment.staticSolrSchoolKey(siteRequest_, (Long)o);
		case "schoolAddress":
			return SchoolPayment.staticSolrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolPayment.staticSolrSchoolPhoneNumber(siteRequest_, (String)o);
		case "yearKey":
			return SchoolPayment.staticSolrYearKey(siteRequest_, (Long)o);
		case "sessionKey":
			return SchoolPayment.staticSolrSessionKey(siteRequest_, (Long)o);
		case "ageKey":
			return SchoolPayment.staticSolrAgeKey(siteRequest_, (Long)o);
		case "blockKey":
			return SchoolPayment.staticSolrBlockKey(siteRequest_, (Long)o);
		case "childKey":
			return SchoolPayment.staticSolrChildKey(siteRequest_, (Long)o);
		case "momKeys":
			return SchoolPayment.staticSolrMomKeys(siteRequest_, (Long)o);
		case "dadKeys":
			return SchoolPayment.staticSolrDadKeys(siteRequest_, (Long)o);
		case "guardianKeys":
			return SchoolPayment.staticSolrGuardianKeys(siteRequest_, (Long)o);
		case "childCompleteNamePreferred":
			return SchoolPayment.staticSolrChildCompleteNamePreferred(siteRequest_, (String)o);
		case "childBirthDate":
			return SchoolPayment.staticSolrChildBirthDate(siteRequest_, (LocalDate)o);
		case "momCompleteNamePreferred":
			return SchoolPayment.staticSolrMomCompleteNamePreferred(siteRequest_, (String)o);
		case "dadCompleteNamePreferred":
			return SchoolPayment.staticSolrDadCompleteNamePreferred(siteRequest_, (String)o);
		case "schoolName":
			return SchoolPayment.staticSolrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolPayment.staticSolrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolPayment.staticSolrSchoolLocation(siteRequest_, (String)o);
		case "yearStart":
			return SchoolPayment.staticSolrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolPayment.staticSolrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return SchoolPayment.staticSolrSeasonStartDate(siteRequest_, (LocalDate)o);
		case "yearEnrollmentFee":
			return SchoolPayment.staticSolrYearEnrollmentFee(siteRequest_, (BigDecimal)o);
		case "sessionStartDate":
			return SchoolPayment.staticSolrSessionStartDate(siteRequest_, (LocalDate)o);
		case "sessionEndDate":
			return SchoolPayment.staticSolrSessionEndDate(siteRequest_, (LocalDate)o);
		case "ageStart":
			return SchoolPayment.staticSolrAgeStart(siteRequest_, (Integer)o);
		case "ageEnd":
			return SchoolPayment.staticSolrAgeEnd(siteRequest_, (Integer)o);
		case "blockCompleteName":
			return SchoolPayment.staticSolrBlockCompleteName(siteRequest_, (String)o);
		case "blockStartTime":
			return SchoolPayment.staticSolrBlockStartTime(siteRequest_, (LocalTime)o);
		case "blockEndTime":
			return SchoolPayment.staticSolrBlockEndTime(siteRequest_, (LocalTime)o);
		case "blockPricePerMonth":
			return SchoolPayment.staticSolrBlockPricePerMonth(siteRequest_, (BigDecimal)o);
		case "enrollmentGroupName":
			return SchoolPayment.staticSolrEnrollmentGroupName(siteRequest_, (String)o);
		case "blockTotalPrice":
			return SchoolPayment.staticSolrBlockTotalPrice(siteRequest_, (BigDecimal)o);
		case "enrollmentPaymentEachMonth":
			return SchoolPayment.staticSolrEnrollmentPaymentEachMonth(siteRequest_, (Boolean)o);
		case "paymentDescription":
			return SchoolPayment.staticSolrPaymentDescription(siteRequest_, (String)o);
		case "paymentDate":
			return SchoolPayment.staticSolrPaymentDate(siteRequest_, (LocalDate)o);
		case "lateFeeDate":
			return SchoolPayment.staticSolrLateFeeDate(siteRequest_, (LocalDate)o);
		case "paymentYear":
			return SchoolPayment.staticSolrPaymentYear(siteRequest_, (Integer)o);
		case "paymentAmount":
			return SchoolPayment.staticSolrPaymentAmount(siteRequest_, (BigDecimal)o);
		case "paymentCash":
			return SchoolPayment.staticSolrPaymentCash(siteRequest_, (Boolean)o);
		case "paymentCheck":
			return SchoolPayment.staticSolrPaymentCheck(siteRequest_, (Boolean)o);
		case "paymentECheck":
			return SchoolPayment.staticSolrPaymentECheck(siteRequest_, (Boolean)o);
		case "paymentSystem":
			return SchoolPayment.staticSolrPaymentSystem(siteRequest_, (Boolean)o);
		case "paymentType":
			return SchoolPayment.staticSolrPaymentType(siteRequest_, (String)o);
		case "paymentBy":
			return SchoolPayment.staticSolrPaymentBy(siteRequest_, (String)o);
		case "transactionId":
			return SchoolPayment.staticSolrTransactionId(siteRequest_, (String)o);
		case "customerProfileId":
			return SchoolPayment.staticSolrCustomerProfileId(siteRequest_, (String)o);
		case "transactionStatus":
			return SchoolPayment.staticSolrTransactionStatus(siteRequest_, (String)o);
		case "paymentRecieved":
			return SchoolPayment.staticSolrPaymentRecieved(siteRequest_, (Boolean)o);
		case "chargeAmount":
			return SchoolPayment.staticSolrChargeAmount(siteRequest_, (BigDecimal)o);
		case "chargeFirstLast":
			return SchoolPayment.staticSolrChargeFirstLast(siteRequest_, (Boolean)o);
		case "chargeEnrollment":
			return SchoolPayment.staticSolrChargeEnrollment(siteRequest_, (Boolean)o);
		case "chargeMonth":
			return SchoolPayment.staticSolrChargeMonth(siteRequest_, (Boolean)o);
		case "chargeLateFee":
			return SchoolPayment.staticSolrChargeLateFee(siteRequest_, (Boolean)o);
		case "now":
			return SchoolPayment.staticSolrNow(siteRequest_, (LocalDate)o);
		case "paymentDay":
			return SchoolPayment.staticSolrPaymentDay(siteRequest_, (Integer)o);
		case "paymentNext":
			return SchoolPayment.staticSolrPaymentNext(siteRequest_, (LocalDate)o);
		case "chargeAmountDue":
			return SchoolPayment.staticSolrChargeAmountDue(siteRequest_, (BigDecimal)o);
		case "chargeAmountPassed":
			return SchoolPayment.staticSolrChargeAmountPassed(siteRequest_, (BigDecimal)o);
		case "chargeAmountNotPassed":
			return SchoolPayment.staticSolrChargeAmountNotPassed(siteRequest_, (BigDecimal)o);
		case "chargeAmountFuture":
			return SchoolPayment.staticSolrChargeAmountFuture(siteRequest_, (BigDecimal)o);
		case "paymentShortName":
			return SchoolPayment.staticSolrPaymentShortName(siteRequest_, (String)o);
		case "paymentCompleteName":
			return SchoolPayment.staticSolrPaymentCompleteName(siteRequest_, (String)o);
		case "paymentNumber":
			return SchoolPayment.staticSolrPaymentNumber(siteRequest_, (Integer)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolPayment(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolPayment(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "paymentKey":
			return SchoolPayment.staticSolrStrPaymentKey(siteRequest_, (Long)o);
		case "enrollmentKey":
			return SchoolPayment.staticSolrStrEnrollmentKey(siteRequest_, (Long)o);
		case "schoolNumber":
			return SchoolPayment.staticSolrStrSchoolNumber(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolPayment.staticSolrStrUserKeys(siteRequest_, (Long)o);
		case "schoolKey":
			return SchoolPayment.staticSolrStrSchoolKey(siteRequest_, (Long)o);
		case "schoolAddress":
			return SchoolPayment.staticSolrStrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolPayment.staticSolrStrSchoolPhoneNumber(siteRequest_, (String)o);
		case "yearKey":
			return SchoolPayment.staticSolrStrYearKey(siteRequest_, (Long)o);
		case "sessionKey":
			return SchoolPayment.staticSolrStrSessionKey(siteRequest_, (Long)o);
		case "ageKey":
			return SchoolPayment.staticSolrStrAgeKey(siteRequest_, (Long)o);
		case "blockKey":
			return SchoolPayment.staticSolrStrBlockKey(siteRequest_, (Long)o);
		case "childKey":
			return SchoolPayment.staticSolrStrChildKey(siteRequest_, (Long)o);
		case "momKeys":
			return SchoolPayment.staticSolrStrMomKeys(siteRequest_, (Long)o);
		case "dadKeys":
			return SchoolPayment.staticSolrStrDadKeys(siteRequest_, (Long)o);
		case "guardianKeys":
			return SchoolPayment.staticSolrStrGuardianKeys(siteRequest_, (Long)o);
		case "childCompleteNamePreferred":
			return SchoolPayment.staticSolrStrChildCompleteNamePreferred(siteRequest_, (String)o);
		case "childBirthDate":
			return SchoolPayment.staticSolrStrChildBirthDate(siteRequest_, (Date)o);
		case "momCompleteNamePreferred":
			return SchoolPayment.staticSolrStrMomCompleteNamePreferred(siteRequest_, (String)o);
		case "dadCompleteNamePreferred":
			return SchoolPayment.staticSolrStrDadCompleteNamePreferred(siteRequest_, (String)o);
		case "schoolName":
			return SchoolPayment.staticSolrStrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolPayment.staticSolrStrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolPayment.staticSolrStrSchoolLocation(siteRequest_, (String)o);
		case "yearStart":
			return SchoolPayment.staticSolrStrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolPayment.staticSolrStrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return SchoolPayment.staticSolrStrSeasonStartDate(siteRequest_, (Date)o);
		case "yearEnrollmentFee":
			return SchoolPayment.staticSolrStrYearEnrollmentFee(siteRequest_, (Double)o);
		case "sessionStartDate":
			return SchoolPayment.staticSolrStrSessionStartDate(siteRequest_, (Date)o);
		case "sessionEndDate":
			return SchoolPayment.staticSolrStrSessionEndDate(siteRequest_, (Date)o);
		case "ageStart":
			return SchoolPayment.staticSolrStrAgeStart(siteRequest_, (Integer)o);
		case "ageEnd":
			return SchoolPayment.staticSolrStrAgeEnd(siteRequest_, (Integer)o);
		case "blockCompleteName":
			return SchoolPayment.staticSolrStrBlockCompleteName(siteRequest_, (String)o);
		case "blockStartTime":
			return SchoolPayment.staticSolrStrBlockStartTime(siteRequest_, (String)o);
		case "blockEndTime":
			return SchoolPayment.staticSolrStrBlockEndTime(siteRequest_, (String)o);
		case "blockPricePerMonth":
			return SchoolPayment.staticSolrStrBlockPricePerMonth(siteRequest_, (Double)o);
		case "enrollmentGroupName":
			return SchoolPayment.staticSolrStrEnrollmentGroupName(siteRequest_, (String)o);
		case "blockTotalPrice":
			return SchoolPayment.staticSolrStrBlockTotalPrice(siteRequest_, (Double)o);
		case "enrollmentPaymentEachMonth":
			return SchoolPayment.staticSolrStrEnrollmentPaymentEachMonth(siteRequest_, (Boolean)o);
		case "paymentDescription":
			return SchoolPayment.staticSolrStrPaymentDescription(siteRequest_, (String)o);
		case "paymentDate":
			return SchoolPayment.staticSolrStrPaymentDate(siteRequest_, (Date)o);
		case "lateFeeDate":
			return SchoolPayment.staticSolrStrLateFeeDate(siteRequest_, (Date)o);
		case "paymentYear":
			return SchoolPayment.staticSolrStrPaymentYear(siteRequest_, (Integer)o);
		case "paymentAmount":
			return SchoolPayment.staticSolrStrPaymentAmount(siteRequest_, (Double)o);
		case "paymentCash":
			return SchoolPayment.staticSolrStrPaymentCash(siteRequest_, (Boolean)o);
		case "paymentCheck":
			return SchoolPayment.staticSolrStrPaymentCheck(siteRequest_, (Boolean)o);
		case "paymentECheck":
			return SchoolPayment.staticSolrStrPaymentECheck(siteRequest_, (Boolean)o);
		case "paymentSystem":
			return SchoolPayment.staticSolrStrPaymentSystem(siteRequest_, (Boolean)o);
		case "paymentType":
			return SchoolPayment.staticSolrStrPaymentType(siteRequest_, (String)o);
		case "paymentBy":
			return SchoolPayment.staticSolrStrPaymentBy(siteRequest_, (String)o);
		case "transactionId":
			return SchoolPayment.staticSolrStrTransactionId(siteRequest_, (String)o);
		case "customerProfileId":
			return SchoolPayment.staticSolrStrCustomerProfileId(siteRequest_, (String)o);
		case "transactionStatus":
			return SchoolPayment.staticSolrStrTransactionStatus(siteRequest_, (String)o);
		case "paymentRecieved":
			return SchoolPayment.staticSolrStrPaymentRecieved(siteRequest_, (Boolean)o);
		case "chargeAmount":
			return SchoolPayment.staticSolrStrChargeAmount(siteRequest_, (Double)o);
		case "chargeFirstLast":
			return SchoolPayment.staticSolrStrChargeFirstLast(siteRequest_, (Boolean)o);
		case "chargeEnrollment":
			return SchoolPayment.staticSolrStrChargeEnrollment(siteRequest_, (Boolean)o);
		case "chargeMonth":
			return SchoolPayment.staticSolrStrChargeMonth(siteRequest_, (Boolean)o);
		case "chargeLateFee":
			return SchoolPayment.staticSolrStrChargeLateFee(siteRequest_, (Boolean)o);
		case "now":
			return SchoolPayment.staticSolrStrNow(siteRequest_, (Date)o);
		case "paymentDay":
			return SchoolPayment.staticSolrStrPaymentDay(siteRequest_, (Integer)o);
		case "paymentNext":
			return SchoolPayment.staticSolrStrPaymentNext(siteRequest_, (Date)o);
		case "chargeAmountDue":
			return SchoolPayment.staticSolrStrChargeAmountDue(siteRequest_, (Double)o);
		case "chargeAmountPassed":
			return SchoolPayment.staticSolrStrChargeAmountPassed(siteRequest_, (Double)o);
		case "chargeAmountNotPassed":
			return SchoolPayment.staticSolrStrChargeAmountNotPassed(siteRequest_, (Double)o);
		case "chargeAmountFuture":
			return SchoolPayment.staticSolrStrChargeAmountFuture(siteRequest_, (Double)o);
		case "paymentShortName":
			return SchoolPayment.staticSolrStrPaymentShortName(siteRequest_, (String)o);
		case "paymentCompleteName":
			return SchoolPayment.staticSolrStrPaymentCompleteName(siteRequest_, (String)o);
		case "paymentNumber":
			return SchoolPayment.staticSolrStrPaymentNumber(siteRequest_, (Integer)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolPayment(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolPayment(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "paymentKey":
			return SchoolPayment.staticSolrFqPaymentKey(siteRequest_, o);
		case "enrollmentKey":
			return SchoolPayment.staticSolrFqEnrollmentKey(siteRequest_, o);
		case "schoolNumber":
			return SchoolPayment.staticSolrFqSchoolNumber(siteRequest_, o);
		case "userKeys":
			return SchoolPayment.staticSolrFqUserKeys(siteRequest_, o);
		case "schoolKey":
			return SchoolPayment.staticSolrFqSchoolKey(siteRequest_, o);
		case "schoolAddress":
			return SchoolPayment.staticSolrFqSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolPayment.staticSolrFqSchoolPhoneNumber(siteRequest_, o);
		case "yearKey":
			return SchoolPayment.staticSolrFqYearKey(siteRequest_, o);
		case "sessionKey":
			return SchoolPayment.staticSolrFqSessionKey(siteRequest_, o);
		case "ageKey":
			return SchoolPayment.staticSolrFqAgeKey(siteRequest_, o);
		case "blockKey":
			return SchoolPayment.staticSolrFqBlockKey(siteRequest_, o);
		case "childKey":
			return SchoolPayment.staticSolrFqChildKey(siteRequest_, o);
		case "momKeys":
			return SchoolPayment.staticSolrFqMomKeys(siteRequest_, o);
		case "dadKeys":
			return SchoolPayment.staticSolrFqDadKeys(siteRequest_, o);
		case "guardianKeys":
			return SchoolPayment.staticSolrFqGuardianKeys(siteRequest_, o);
		case "childCompleteNamePreferred":
			return SchoolPayment.staticSolrFqChildCompleteNamePreferred(siteRequest_, o);
		case "childBirthDate":
			return SchoolPayment.staticSolrFqChildBirthDate(siteRequest_, o);
		case "momCompleteNamePreferred":
			return SchoolPayment.staticSolrFqMomCompleteNamePreferred(siteRequest_, o);
		case "dadCompleteNamePreferred":
			return SchoolPayment.staticSolrFqDadCompleteNamePreferred(siteRequest_, o);
		case "schoolName":
			return SchoolPayment.staticSolrFqSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolPayment.staticSolrFqSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolPayment.staticSolrFqSchoolLocation(siteRequest_, o);
		case "yearStart":
			return SchoolPayment.staticSolrFqYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolPayment.staticSolrFqYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return SchoolPayment.staticSolrFqSeasonStartDate(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolPayment.staticSolrFqYearEnrollmentFee(siteRequest_, o);
		case "sessionStartDate":
			return SchoolPayment.staticSolrFqSessionStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolPayment.staticSolrFqSessionEndDate(siteRequest_, o);
		case "ageStart":
			return SchoolPayment.staticSolrFqAgeStart(siteRequest_, o);
		case "ageEnd":
			return SchoolPayment.staticSolrFqAgeEnd(siteRequest_, o);
		case "blockCompleteName":
			return SchoolPayment.staticSolrFqBlockCompleteName(siteRequest_, o);
		case "blockStartTime":
			return SchoolPayment.staticSolrFqBlockStartTime(siteRequest_, o);
		case "blockEndTime":
			return SchoolPayment.staticSolrFqBlockEndTime(siteRequest_, o);
		case "blockPricePerMonth":
			return SchoolPayment.staticSolrFqBlockPricePerMonth(siteRequest_, o);
		case "enrollmentGroupName":
			return SchoolPayment.staticSolrFqEnrollmentGroupName(siteRequest_, o);
		case "blockTotalPrice":
			return SchoolPayment.staticSolrFqBlockTotalPrice(siteRequest_, o);
		case "enrollmentPaymentEachMonth":
			return SchoolPayment.staticSolrFqEnrollmentPaymentEachMonth(siteRequest_, o);
		case "paymentDescription":
			return SchoolPayment.staticSolrFqPaymentDescription(siteRequest_, o);
		case "paymentDate":
			return SchoolPayment.staticSolrFqPaymentDate(siteRequest_, o);
		case "lateFeeDate":
			return SchoolPayment.staticSolrFqLateFeeDate(siteRequest_, o);
		case "paymentYear":
			return SchoolPayment.staticSolrFqPaymentYear(siteRequest_, o);
		case "paymentAmount":
			return SchoolPayment.staticSolrFqPaymentAmount(siteRequest_, o);
		case "paymentCash":
			return SchoolPayment.staticSolrFqPaymentCash(siteRequest_, o);
		case "paymentCheck":
			return SchoolPayment.staticSolrFqPaymentCheck(siteRequest_, o);
		case "paymentECheck":
			return SchoolPayment.staticSolrFqPaymentECheck(siteRequest_, o);
		case "paymentSystem":
			return SchoolPayment.staticSolrFqPaymentSystem(siteRequest_, o);
		case "paymentType":
			return SchoolPayment.staticSolrFqPaymentType(siteRequest_, o);
		case "paymentBy":
			return SchoolPayment.staticSolrFqPaymentBy(siteRequest_, o);
		case "transactionId":
			return SchoolPayment.staticSolrFqTransactionId(siteRequest_, o);
		case "customerProfileId":
			return SchoolPayment.staticSolrFqCustomerProfileId(siteRequest_, o);
		case "transactionStatus":
			return SchoolPayment.staticSolrFqTransactionStatus(siteRequest_, o);
		case "paymentRecieved":
			return SchoolPayment.staticSolrFqPaymentRecieved(siteRequest_, o);
		case "chargeAmount":
			return SchoolPayment.staticSolrFqChargeAmount(siteRequest_, o);
		case "chargeFirstLast":
			return SchoolPayment.staticSolrFqChargeFirstLast(siteRequest_, o);
		case "chargeEnrollment":
			return SchoolPayment.staticSolrFqChargeEnrollment(siteRequest_, o);
		case "chargeMonth":
			return SchoolPayment.staticSolrFqChargeMonth(siteRequest_, o);
		case "chargeLateFee":
			return SchoolPayment.staticSolrFqChargeLateFee(siteRequest_, o);
		case "now":
			return SchoolPayment.staticSolrFqNow(siteRequest_, o);
		case "paymentDay":
			return SchoolPayment.staticSolrFqPaymentDay(siteRequest_, o);
		case "paymentNext":
			return SchoolPayment.staticSolrFqPaymentNext(siteRequest_, o);
		case "chargeAmountDue":
			return SchoolPayment.staticSolrFqChargeAmountDue(siteRequest_, o);
		case "chargeAmountPassed":
			return SchoolPayment.staticSolrFqChargeAmountPassed(siteRequest_, o);
		case "chargeAmountNotPassed":
			return SchoolPayment.staticSolrFqChargeAmountNotPassed(siteRequest_, o);
		case "chargeAmountFuture":
			return SchoolPayment.staticSolrFqChargeAmountFuture(siteRequest_, o);
		case "paymentShortName":
			return SchoolPayment.staticSolrFqPaymentShortName(siteRequest_, o);
		case "paymentCompleteName":
			return SchoolPayment.staticSolrFqPaymentCompleteName(siteRequest_, o);
		case "paymentNumber":
			return SchoolPayment.staticSolrFqPaymentNumber(siteRequest_, o);
			default:
				return Cluster.staticSolrFqCluster(entityVar,  siteRequest_, o);
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
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolPayment(String var, String val) {
		switch(var.toLowerCase()) {
			case "enrollmentkey":
				if(val != null)
					setEnrollmentKey(val);
				saves.add("enrollmentKey");
				return val;
			case "enrollmentpaymenteachmonth":
				if(val != null)
					setEnrollmentPaymentEachMonth(val);
				saves.add("enrollmentPaymentEachMonth");
				return val;
			case "paymentdescription":
				if(val != null)
					setPaymentDescription(val);
				saves.add("paymentDescription");
				return val;
			case "paymentdate":
				if(val != null)
					setPaymentDate(val);
				saves.add("paymentDate");
				return val;
			case "latefeedate":
				if(val != null)
					setLateFeeDate(val);
				saves.add("lateFeeDate");
				return val;
			case "paymentamount":
				if(val != null)
					setPaymentAmount(val);
				saves.add("paymentAmount");
				return val;
			case "paymentcash":
				if(val != null)
					setPaymentCash(val);
				saves.add("paymentCash");
				return val;
			case "paymentcheck":
				if(val != null)
					setPaymentCheck(val);
				saves.add("paymentCheck");
				return val;
			case "paymentecheck":
				if(val != null)
					setPaymentECheck(val);
				saves.add("paymentECheck");
				return val;
			case "paymentsystem":
				if(val != null)
					setPaymentSystem(val);
				saves.add("paymentSystem");
				return val;
			case "paymentby":
				if(val != null)
					setPaymentBy(val);
				saves.add("paymentBy");
				return val;
			case "transactionid":
				if(val != null)
					setTransactionId(val);
				saves.add("transactionId");
				return val;
			case "customerprofileid":
				if(val != null)
					setCustomerProfileId(val);
				saves.add("customerProfileId");
				return val;
			case "transactionstatus":
				if(val != null)
					setTransactionStatus(val);
				saves.add("transactionStatus");
				return val;
			case "paymentrecieved":
				if(val != null)
					setPaymentRecieved(val);
				saves.add("paymentRecieved");
				return val;
			case "chargeamount":
				if(val != null)
					setChargeAmount(val);
				saves.add("chargeAmount");
				return val;
			case "chargefirstlast":
				if(val != null)
					setChargeFirstLast(val);
				saves.add("chargeFirstLast");
				return val;
			case "chargeenrollment":
				if(val != null)
					setChargeEnrollment(val);
				saves.add("chargeEnrollment");
				return val;
			case "chargemonth":
				if(val != null)
					setChargeMonth(val);
				saves.add("chargeMonth");
				return val;
			case "chargelatefee":
				if(val != null)
					setChargeLateFee(val);
				saves.add("chargeLateFee");
				return val;
			case "paymentshortname":
				if(val != null)
					setPaymentShortName(val);
				saves.add("paymentShortName");
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	@Override public boolean defineForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineSchoolPayment(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolPayment(String var, Object val) {
		switch(var.toLowerCase()) {
			case "enrollmentkey":
				if(val instanceof Long)
					setEnrollmentKey((Long)val);
				saves.add("enrollmentKey");
				return val;
			case "enrollmentpaymenteachmonth":
				if(val instanceof Boolean)
					setEnrollmentPaymentEachMonth((Boolean)val);
				saves.add("enrollmentPaymentEachMonth");
				return val;
			case "paymentdescription":
				if(val instanceof String)
					setPaymentDescription((String)val);
				saves.add("paymentDescription");
				return val;
			case "paymentdate":
				if(val instanceof LocalDate)
					setPaymentDate((LocalDate)val);
				saves.add("paymentDate");
				return val;
			case "latefeedate":
				if(val instanceof LocalDate)
					setLateFeeDate((LocalDate)val);
				saves.add("lateFeeDate");
				return val;
			case "paymentamount":
				if(val instanceof BigDecimal)
					setPaymentAmount((BigDecimal)val);
				saves.add("paymentAmount");
				return val;
			case "paymentcash":
				if(val instanceof Boolean)
					setPaymentCash((Boolean)val);
				saves.add("paymentCash");
				return val;
			case "paymentcheck":
				if(val instanceof Boolean)
					setPaymentCheck((Boolean)val);
				saves.add("paymentCheck");
				return val;
			case "paymentecheck":
				if(val instanceof Boolean)
					setPaymentECheck((Boolean)val);
				saves.add("paymentECheck");
				return val;
			case "paymentsystem":
				if(val instanceof Boolean)
					setPaymentSystem((Boolean)val);
				saves.add("paymentSystem");
				return val;
			case "paymentby":
				if(val instanceof String)
					setPaymentBy((String)val);
				saves.add("paymentBy");
				return val;
			case "transactionid":
				if(val instanceof String)
					setTransactionId((String)val);
				saves.add("transactionId");
				return val;
			case "customerprofileid":
				if(val instanceof String)
					setCustomerProfileId((String)val);
				saves.add("customerProfileId");
				return val;
			case "transactionstatus":
				if(val instanceof String)
					setTransactionStatus((String)val);
				saves.add("transactionStatus");
				return val;
			case "paymentrecieved":
				if(val instanceof Boolean)
					setPaymentRecieved((Boolean)val);
				saves.add("paymentRecieved");
				return val;
			case "chargeamount":
				if(val instanceof BigDecimal)
					setChargeAmount((BigDecimal)val);
				saves.add("chargeAmount");
				return val;
			case "chargefirstlast":
				if(val instanceof Boolean)
					setChargeFirstLast((Boolean)val);
				saves.add("chargeFirstLast");
				return val;
			case "chargeenrollment":
				if(val instanceof Boolean)
					setChargeEnrollment((Boolean)val);
				saves.add("chargeEnrollment");
				return val;
			case "chargemonth":
				if(val instanceof Boolean)
					setChargeMonth((Boolean)val);
				saves.add("chargeMonth");
				return val;
			case "chargelatefee":
				if(val instanceof Boolean)
					setChargeLateFee((Boolean)val);
				saves.add("chargeLateFee");
				return val;
			case "paymentshortname":
				if(val instanceof String)
					setPaymentShortName((String)val);
				saves.add("paymentShortName");
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolPayment(solrDocument);
	}
	public void populateSchoolPayment(SolrDocument solrDocument) {
		SchoolPayment oSchoolPayment = (SchoolPayment)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("paymentKey")) {
				Long paymentKey = (Long)solrDocument.get("paymentKey_stored_long");
				if(paymentKey != null)
					oSchoolPayment.setPaymentKey(paymentKey);
			}

			Long enrollmentKey = (Long)solrDocument.get("enrollmentKey_stored_long");
			if(enrollmentKey != null)
				oSchoolPayment.setEnrollmentKey(enrollmentKey);

			if(saves.contains("schoolNumber")) {
				Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
				if(schoolNumber != null)
					oSchoolPayment.setSchoolNumber(schoolNumber);
			}

			if(saves.contains("userKeys")) {
				List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
				if(userKeys != null)
					oSchoolPayment.userKeys.addAll(userKeys);
			}

			if(saves.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolPayment.setSchoolKey(schoolKey);
			}

			if(saves.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolPayment.setSchoolAddress(schoolAddress);
			}

			if(saves.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolPayment.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(saves.contains("yearKey")) {
				Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
				if(yearKey != null)
					oSchoolPayment.setYearKey(yearKey);
			}

			if(saves.contains("sessionKey")) {
				Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
				if(sessionKey != null)
					oSchoolPayment.setSessionKey(sessionKey);
			}

			if(saves.contains("ageKey")) {
				Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
				if(ageKey != null)
					oSchoolPayment.setAgeKey(ageKey);
			}

			if(saves.contains("blockKey")) {
				Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
				if(blockKey != null)
					oSchoolPayment.setBlockKey(blockKey);
			}

			if(saves.contains("childKey")) {
				Long childKey = (Long)solrDocument.get("childKey_stored_long");
				if(childKey != null)
					oSchoolPayment.setChildKey(childKey);
			}

			if(saves.contains("momKeys")) {
				List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
				if(momKeys != null)
					oSchoolPayment.momKeys.addAll(momKeys);
			}

			if(saves.contains("dadKeys")) {
				List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
				if(dadKeys != null)
					oSchoolPayment.dadKeys.addAll(dadKeys);
			}

			if(saves.contains("guardianKeys")) {
				List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
				if(guardianKeys != null)
					oSchoolPayment.guardianKeys.addAll(guardianKeys);
			}

			if(saves.contains("childCompleteNamePreferred")) {
				String childCompleteNamePreferred = (String)solrDocument.get("childCompleteNamePreferred_stored_string");
				if(childCompleteNamePreferred != null)
					oSchoolPayment.setChildCompleteNamePreferred(childCompleteNamePreferred);
			}

			if(saves.contains("childBirthDate")) {
				Date childBirthDate = (Date)solrDocument.get("childBirthDate_stored_date");
				if(childBirthDate != null)
					oSchoolPayment.setChildBirthDate(childBirthDate);
			}

			if(saves.contains("momCompleteNamePreferred")) {
				String momCompleteNamePreferred = (String)solrDocument.get("momCompleteNamePreferred_stored_string");
				if(momCompleteNamePreferred != null)
					oSchoolPayment.setMomCompleteNamePreferred(momCompleteNamePreferred);
			}

			if(saves.contains("dadCompleteNamePreferred")) {
				String dadCompleteNamePreferred = (String)solrDocument.get("dadCompleteNamePreferred_stored_string");
				if(dadCompleteNamePreferred != null)
					oSchoolPayment.setDadCompleteNamePreferred(dadCompleteNamePreferred);
			}

			if(saves.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolPayment.setSchoolName(schoolName);
			}

			if(saves.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolPayment.setSchoolCompleteName(schoolCompleteName);
			}

			if(saves.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolPayment.setSchoolLocation(schoolLocation);
			}

			if(saves.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolPayment.setYearStart(yearStart);
			}

			if(saves.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolPayment.setYearEnd(yearEnd);
			}

			if(saves.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolPayment.setSeasonStartDate(seasonStartDate);
			}

			if(saves.contains("yearEnrollmentFee")) {
				Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
				if(yearEnrollmentFee != null)
					oSchoolPayment.setYearEnrollmentFee(yearEnrollmentFee);
			}

			if(saves.contains("sessionStartDate")) {
				Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
				if(sessionStartDate != null)
					oSchoolPayment.setSessionStartDate(sessionStartDate);
			}

			if(saves.contains("sessionEndDate")) {
				Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
				if(sessionEndDate != null)
					oSchoolPayment.setSessionEndDate(sessionEndDate);
			}

			if(saves.contains("ageStart")) {
				Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
				if(ageStart != null)
					oSchoolPayment.setAgeStart(ageStart);
			}

			if(saves.contains("ageEnd")) {
				Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
				if(ageEnd != null)
					oSchoolPayment.setAgeEnd(ageEnd);
			}

			if(saves.contains("blockCompleteName")) {
				String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
				if(blockCompleteName != null)
					oSchoolPayment.setBlockCompleteName(blockCompleteName);
			}

			if(saves.contains("blockStartTime")) {
				String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
				if(blockStartTime != null)
					oSchoolPayment.setBlockStartTime(blockStartTime);
			}

			if(saves.contains("blockEndTime")) {
				String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
				if(blockEndTime != null)
					oSchoolPayment.setBlockEndTime(blockEndTime);
			}

			if(saves.contains("blockPricePerMonth")) {
				Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
				if(blockPricePerMonth != null)
					oSchoolPayment.setBlockPricePerMonth(blockPricePerMonth);
			}

			if(saves.contains("enrollmentGroupName")) {
				String enrollmentGroupName = (String)solrDocument.get("enrollmentGroupName_stored_string");
				if(enrollmentGroupName != null)
					oSchoolPayment.setEnrollmentGroupName(enrollmentGroupName);
			}

			if(saves.contains("blockTotalPrice")) {
				Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
				if(blockTotalPrice != null)
					oSchoolPayment.setBlockTotalPrice(blockTotalPrice);
			}

			if(saves.contains("enrollmentPaymentEachMonth")) {
				Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
				if(enrollmentPaymentEachMonth != null)
					oSchoolPayment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);
			}

			if(saves.contains("paymentDescription")) {
				String paymentDescription = (String)solrDocument.get("paymentDescription_stored_string");
				if(paymentDescription != null)
					oSchoolPayment.setPaymentDescription(paymentDescription);
			}

			if(saves.contains("paymentDate")) {
				Date paymentDate = (Date)solrDocument.get("paymentDate_stored_date");
				if(paymentDate != null)
					oSchoolPayment.setPaymentDate(paymentDate);
			}

			if(saves.contains("lateFeeDate")) {
				Date lateFeeDate = (Date)solrDocument.get("lateFeeDate_stored_date");
				if(lateFeeDate != null)
					oSchoolPayment.setLateFeeDate(lateFeeDate);
			}

			if(saves.contains("paymentYear")) {
				Integer paymentYear = (Integer)solrDocument.get("paymentYear_stored_int");
				if(paymentYear != null)
					oSchoolPayment.setPaymentYear(paymentYear);
			}

			if(saves.contains("paymentAmount")) {
				Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
				if(paymentAmount != null)
					oSchoolPayment.setPaymentAmount(paymentAmount);
			}

			if(saves.contains("paymentCash")) {
				Boolean paymentCash = (Boolean)solrDocument.get("paymentCash_stored_boolean");
				if(paymentCash != null)
					oSchoolPayment.setPaymentCash(paymentCash);
			}

			if(saves.contains("paymentCheck")) {
				Boolean paymentCheck = (Boolean)solrDocument.get("paymentCheck_stored_boolean");
				if(paymentCheck != null)
					oSchoolPayment.setPaymentCheck(paymentCheck);
			}

			if(saves.contains("paymentECheck")) {
				Boolean paymentECheck = (Boolean)solrDocument.get("paymentECheck_stored_boolean");
				if(paymentECheck != null)
					oSchoolPayment.setPaymentECheck(paymentECheck);
			}

			if(saves.contains("paymentSystem")) {
				Boolean paymentSystem = (Boolean)solrDocument.get("paymentSystem_stored_boolean");
				if(paymentSystem != null)
					oSchoolPayment.setPaymentSystem(paymentSystem);
			}

			if(saves.contains("paymentType")) {
				String paymentType = (String)solrDocument.get("paymentType_stored_string");
				if(paymentType != null)
					oSchoolPayment.setPaymentType(paymentType);
			}

			if(saves.contains("paymentBy")) {
				String paymentBy = (String)solrDocument.get("paymentBy_stored_string");
				if(paymentBy != null)
					oSchoolPayment.setPaymentBy(paymentBy);
			}

			if(saves.contains("transactionId")) {
				String transactionId = (String)solrDocument.get("transactionId_stored_string");
				if(transactionId != null)
					oSchoolPayment.setTransactionId(transactionId);
			}

			if(saves.contains("customerProfileId")) {
				String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
				if(customerProfileId != null)
					oSchoolPayment.setCustomerProfileId(customerProfileId);
			}

			if(saves.contains("transactionStatus")) {
				String transactionStatus = (String)solrDocument.get("transactionStatus_stored_string");
				if(transactionStatus != null)
					oSchoolPayment.setTransactionStatus(transactionStatus);
			}

			if(saves.contains("paymentRecieved")) {
				Boolean paymentRecieved = (Boolean)solrDocument.get("paymentRecieved_stored_boolean");
				if(paymentRecieved != null)
					oSchoolPayment.setPaymentRecieved(paymentRecieved);
			}

			if(saves.contains("chargeAmount")) {
				Double chargeAmount = (Double)solrDocument.get("chargeAmount_stored_double");
				if(chargeAmount != null)
					oSchoolPayment.setChargeAmount(chargeAmount);
			}

			if(saves.contains("chargeFirstLast")) {
				Boolean chargeFirstLast = (Boolean)solrDocument.get("chargeFirstLast_stored_boolean");
				if(chargeFirstLast != null)
					oSchoolPayment.setChargeFirstLast(chargeFirstLast);
			}

			if(saves.contains("chargeEnrollment")) {
				Boolean chargeEnrollment = (Boolean)solrDocument.get("chargeEnrollment_stored_boolean");
				if(chargeEnrollment != null)
					oSchoolPayment.setChargeEnrollment(chargeEnrollment);
			}

			if(saves.contains("chargeMonth")) {
				Boolean chargeMonth = (Boolean)solrDocument.get("chargeMonth_stored_boolean");
				if(chargeMonth != null)
					oSchoolPayment.setChargeMonth(chargeMonth);
			}

			if(saves.contains("chargeLateFee")) {
				Boolean chargeLateFee = (Boolean)solrDocument.get("chargeLateFee_stored_boolean");
				if(chargeLateFee != null)
					oSchoolPayment.setChargeLateFee(chargeLateFee);
			}

			if(saves.contains("paymentNext")) {
				Date paymentNext = (Date)solrDocument.get("paymentNext_stored_date");
				if(paymentNext != null)
					oSchoolPayment.setPaymentNext(paymentNext);
			}

			if(saves.contains("chargeAmountDue")) {
				Double chargeAmountDue = (Double)solrDocument.get("chargeAmountDue_stored_double");
				if(chargeAmountDue != null)
					oSchoolPayment.setChargeAmountDue(chargeAmountDue);
			}

			if(saves.contains("chargeAmountPassed")) {
				Double chargeAmountPassed = (Double)solrDocument.get("chargeAmountPassed_stored_double");
				if(chargeAmountPassed != null)
					oSchoolPayment.setChargeAmountPassed(chargeAmountPassed);
			}

			if(saves.contains("chargeAmountNotPassed")) {
				Double chargeAmountNotPassed = (Double)solrDocument.get("chargeAmountNotPassed_stored_double");
				if(chargeAmountNotPassed != null)
					oSchoolPayment.setChargeAmountNotPassed(chargeAmountNotPassed);
			}

			if(saves.contains("chargeAmountFuture")) {
				Double chargeAmountFuture = (Double)solrDocument.get("chargeAmountFuture_stored_double");
				if(chargeAmountFuture != null)
					oSchoolPayment.setChargeAmountFuture(chargeAmountFuture);
			}

			if(saves.contains("paymentShortName")) {
				String paymentShortName = (String)solrDocument.get("paymentShortName_stored_string");
				if(paymentShortName != null)
					oSchoolPayment.setPaymentShortName(paymentShortName);
			}

			if(saves.contains("paymentCompleteName")) {
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
		if(paymentKey != null) {
			document.addField("paymentKey_indexed_long", paymentKey);
			document.addField("paymentKey_stored_long", paymentKey);
		}
		if(enrollmentKey != null) {
			document.addField("enrollmentKey_indexed_long", enrollmentKey);
			document.addField("enrollmentKey_stored_long", enrollmentKey);
		}
		if(schoolNumber != null) {
			document.addField("schoolNumber_indexed_int", schoolNumber);
			document.addField("schoolNumber_stored_int", schoolNumber);
		}
		if(userKeys != null) {
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_indexed_longs", o);
			}
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_stored_longs", o);
			}
		}
		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(schoolAddress != null) {
			document.addField("schoolAddress_indexed_string", schoolAddress);
			document.addField("schoolAddress_stored_string", schoolAddress);
		}
		if(schoolPhoneNumber != null) {
			document.addField("schoolPhoneNumber_indexed_string", schoolPhoneNumber);
			document.addField("schoolPhoneNumber_stored_string", schoolPhoneNumber);
		}
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
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
		if(childCompleteNamePreferred != null) {
			document.addField("childCompleteNamePreferred_indexed_string", childCompleteNamePreferred);
			document.addField("childCompleteNamePreferred_stored_string", childCompleteNamePreferred);
		}
		if(childBirthDate != null) {
			document.addField("childBirthDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(childBirthDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("childBirthDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(childBirthDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(momCompleteNamePreferred != null) {
			document.addField("momCompleteNamePreferred_indexed_string", momCompleteNamePreferred);
			document.addField("momCompleteNamePreferred_stored_string", momCompleteNamePreferred);
		}
		if(dadCompleteNamePreferred != null) {
			document.addField("dadCompleteNamePreferred_indexed_string", dadCompleteNamePreferred);
			document.addField("dadCompleteNamePreferred_stored_string", dadCompleteNamePreferred);
		}
		if(schoolName != null) {
			document.addField("schoolName_indexed_string", schoolName);
			document.addField("schoolName_stored_string", schoolName);
		}
		if(schoolCompleteName != null) {
			document.addField("schoolCompleteName_indexed_string", schoolCompleteName);
			document.addField("schoolCompleteName_stored_string", schoolCompleteName);
		}
		if(schoolLocation != null) {
			document.addField("schoolLocation_indexed_string", schoolLocation);
			document.addField("schoolLocation_stored_string", schoolLocation);
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
			document.addField("seasonStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(yearEnrollmentFee != null) {
			document.addField("yearEnrollmentFee_indexed_double", yearEnrollmentFee.doubleValue());
			document.addField("yearEnrollmentFee_stored_double", yearEnrollmentFee.doubleValue());
		}
		if(sessionStartDate != null) {
			document.addField("sessionStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionEndDate != null) {
			document.addField("sessionEndDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionEndDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(ageStart != null) {
			document.addField("ageStart_indexed_int", ageStart);
			document.addField("ageStart_stored_int", ageStart);
		}
		if(ageEnd != null) {
			document.addField("ageEnd_indexed_int", ageEnd);
			document.addField("ageEnd_stored_int", ageEnd);
		}
		if(blockCompleteName != null) {
			document.addField("blockCompleteName_stored_string", blockCompleteName);
		}
		if(blockStartTime != null) {
			document.addField("blockStartTime_indexed_string", DateTimeFormatter.ofPattern("HH:mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockStartTime_stored_string", DateTimeFormatter.ofPattern("HH:mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
		}
		if(blockEndTime != null) {
			document.addField("blockEndTime_indexed_string", DateTimeFormatter.ofPattern("HH:mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockEndTime_stored_string", DateTimeFormatter.ofPattern("HH:mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
		}
		if(blockPricePerMonth != null) {
			document.addField("blockPricePerMonth_indexed_double", blockPricePerMonth.doubleValue());
			document.addField("blockPricePerMonth_stored_double", blockPricePerMonth.doubleValue());
		}
		if(enrollmentGroupName != null) {
			document.addField("enrollmentGroupName_indexed_string", enrollmentGroupName);
			document.addField("enrollmentGroupName_stored_string", enrollmentGroupName);
		}
		if(blockTotalPrice != null) {
			document.addField("blockTotalPrice_indexed_double", blockTotalPrice.doubleValue());
			document.addField("blockTotalPrice_stored_double", blockTotalPrice.doubleValue());
		}
		if(enrollmentPaymentEachMonth != null) {
			document.addField("enrollmentPaymentEachMonth_indexed_boolean", enrollmentPaymentEachMonth);
			document.addField("enrollmentPaymentEachMonth_stored_boolean", enrollmentPaymentEachMonth);
		}
		if(paymentDescription != null) {
			document.addField("paymentDescription_indexed_string", paymentDescription);
			document.addField("paymentDescription_stored_string", paymentDescription);
		}
		if(paymentDate != null) {
			document.addField("paymentDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paymentDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(lateFeeDate != null) {
			document.addField("lateFeeDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(lateFeeDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("lateFeeDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(lateFeeDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paymentYear != null) {
			document.addField("paymentYear_indexed_int", paymentYear);
			document.addField("paymentYear_stored_int", paymentYear);
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
		if(paymentECheck != null) {
			document.addField("paymentECheck_indexed_boolean", paymentECheck);
			document.addField("paymentECheck_stored_boolean", paymentECheck);
		}
		if(paymentSystem != null) {
			document.addField("paymentSystem_indexed_boolean", paymentSystem);
			document.addField("paymentSystem_stored_boolean", paymentSystem);
		}
		if(paymentType != null) {
			document.addField("paymentType_indexed_string", paymentType);
			document.addField("paymentType_stored_string", paymentType);
		}
		if(paymentBy != null) {
			document.addField("paymentBy_indexed_string", paymentBy);
			document.addField("paymentBy_stored_string", paymentBy);
		}
		if(transactionId != null) {
			document.addField("transactionId_indexed_string", transactionId);
			document.addField("transactionId_stored_string", transactionId);
		}
		if(customerProfileId != null) {
			document.addField("customerProfileId_indexed_string", customerProfileId);
			document.addField("customerProfileId_stored_string", customerProfileId);
		}
		if(transactionStatus != null) {
			document.addField("transactionStatus_indexed_string", transactionStatus);
			document.addField("transactionStatus_stored_string", transactionStatus);
		}
		if(paymentRecieved != null) {
			document.addField("paymentRecieved_indexed_boolean", paymentRecieved);
			document.addField("paymentRecieved_stored_boolean", paymentRecieved);
		}
		if(chargeAmount != null) {
			document.addField("chargeAmount_indexed_double", chargeAmount.doubleValue());
			document.addField("chargeAmount_stored_double", chargeAmount.doubleValue());
		}
		if(chargeFirstLast != null) {
			document.addField("chargeFirstLast_indexed_boolean", chargeFirstLast);
			document.addField("chargeFirstLast_stored_boolean", chargeFirstLast);
		}
		if(chargeEnrollment != null) {
			document.addField("chargeEnrollment_indexed_boolean", chargeEnrollment);
			document.addField("chargeEnrollment_stored_boolean", chargeEnrollment);
		}
		if(chargeMonth != null) {
			document.addField("chargeMonth_indexed_boolean", chargeMonth);
			document.addField("chargeMonth_stored_boolean", chargeMonth);
		}
		if(chargeLateFee != null) {
			document.addField("chargeLateFee_indexed_boolean", chargeLateFee);
			document.addField("chargeLateFee_stored_boolean", chargeLateFee);
		}
		if(paymentNext != null) {
			document.addField("paymentNext_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentNext.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paymentNext_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentNext.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(chargeAmountDue != null) {
			document.addField("chargeAmountDue_indexed_double", chargeAmountDue.doubleValue());
			document.addField("chargeAmountDue_stored_double", chargeAmountDue.doubleValue());
		}
		if(chargeAmountPassed != null) {
			document.addField("chargeAmountPassed_indexed_double", chargeAmountPassed.doubleValue());
			document.addField("chargeAmountPassed_stored_double", chargeAmountPassed.doubleValue());
		}
		if(chargeAmountNotPassed != null) {
			document.addField("chargeAmountNotPassed_indexed_double", chargeAmountNotPassed.doubleValue());
			document.addField("chargeAmountNotPassed_stored_double", chargeAmountNotPassed.doubleValue());
		}
		if(chargeAmountFuture != null) {
			document.addField("chargeAmountFuture_indexed_double", chargeAmountFuture.doubleValue());
			document.addField("chargeAmountFuture_stored_double", chargeAmountFuture.doubleValue());
		}
		if(paymentShortName != null) {
			document.addField("paymentShortName_indexed_string", paymentShortName);
			document.addField("paymentShortName_stored_string", paymentShortName);
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
			case "paymentKey":
				return "paymentKey_indexed_long";
			case "enrollmentKey":
				return "enrollmentKey_indexed_long";
			case "schoolNumber":
				return "schoolNumber_indexed_int";
			case "userKeys":
				return "userKeys_indexed_longs";
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "schoolAddress":
				return "schoolAddress_indexed_string";
			case "schoolPhoneNumber":
				return "schoolPhoneNumber_indexed_string";
			case "yearKey":
				return "yearKey_indexed_long";
			case "sessionKey":
				return "sessionKey_indexed_long";
			case "ageKey":
				return "ageKey_indexed_long";
			case "blockKey":
				return "blockKey_indexed_long";
			case "childKey":
				return "childKey_indexed_long";
			case "momKeys":
				return "momKeys_indexed_longs";
			case "dadKeys":
				return "dadKeys_indexed_longs";
			case "guardianKeys":
				return "guardianKeys_indexed_longs";
			case "childCompleteNamePreferred":
				return "childCompleteNamePreferred_indexed_string";
			case "childBirthDate":
				return "childBirthDate_indexed_date";
			case "momCompleteNamePreferred":
				return "momCompleteNamePreferred_indexed_string";
			case "dadCompleteNamePreferred":
				return "dadCompleteNamePreferred_indexed_string";
			case "schoolName":
				return "schoolName_indexed_string";
			case "schoolCompleteName":
				return "schoolCompleteName_indexed_string";
			case "schoolLocation":
				return "schoolLocation_indexed_string";
			case "yearStart":
				return "yearStart_indexed_int";
			case "yearEnd":
				return "yearEnd_indexed_int";
			case "seasonStartDate":
				return "seasonStartDate_indexed_date";
			case "yearEnrollmentFee":
				return "yearEnrollmentFee_indexed_double";
			case "sessionStartDate":
				return "sessionStartDate_indexed_date";
			case "sessionEndDate":
				return "sessionEndDate_indexed_date";
			case "ageStart":
				return "ageStart_indexed_int";
			case "ageEnd":
				return "ageEnd_indexed_int";
			case "blockStartTime":
				return "blockStartTime_indexed_string";
			case "blockEndTime":
				return "blockEndTime_indexed_string";
			case "blockPricePerMonth":
				return "blockPricePerMonth_indexed_double";
			case "enrollmentGroupName":
				return "enrollmentGroupName_indexed_string";
			case "blockTotalPrice":
				return "blockTotalPrice_indexed_double";
			case "enrollmentPaymentEachMonth":
				return "enrollmentPaymentEachMonth_indexed_boolean";
			case "paymentDescription":
				return "paymentDescription_indexed_string";
			case "paymentDate":
				return "paymentDate_indexed_date";
			case "lateFeeDate":
				return "lateFeeDate_indexed_date";
			case "paymentYear":
				return "paymentYear_indexed_int";
			case "paymentAmount":
				return "paymentAmount_indexed_double";
			case "paymentCash":
				return "paymentCash_indexed_boolean";
			case "paymentCheck":
				return "paymentCheck_indexed_boolean";
			case "paymentECheck":
				return "paymentECheck_indexed_boolean";
			case "paymentSystem":
				return "paymentSystem_indexed_boolean";
			case "paymentType":
				return "paymentType_indexed_string";
			case "paymentBy":
				return "paymentBy_indexed_string";
			case "transactionId":
				return "transactionId_indexed_string";
			case "customerProfileId":
				return "customerProfileId_indexed_string";
			case "transactionStatus":
				return "transactionStatus_indexed_string";
			case "paymentRecieved":
				return "paymentRecieved_indexed_boolean";
			case "chargeAmount":
				return "chargeAmount_indexed_double";
			case "chargeFirstLast":
				return "chargeFirstLast_indexed_boolean";
			case "chargeEnrollment":
				return "chargeEnrollment_indexed_boolean";
			case "chargeMonth":
				return "chargeMonth_indexed_boolean";
			case "chargeLateFee":
				return "chargeLateFee_indexed_boolean";
			case "paymentNext":
				return "paymentNext_indexed_date";
			case "chargeAmountDue":
				return "chargeAmountDue_indexed_double";
			case "chargeAmountPassed":
				return "chargeAmountPassed_indexed_double";
			case "chargeAmountNotPassed":
				return "chargeAmountNotPassed_indexed_double";
			case "chargeAmountFuture":
				return "chargeAmountFuture_indexed_double";
			case "paymentShortName":
				return "paymentShortName_indexed_string";
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

	public static String varSuggestedSchoolPayment(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
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

		Long paymentKey = (Long)solrDocument.get("paymentKey_stored_long");
		if(paymentKey != null)
			oSchoolPayment.setPaymentKey(paymentKey);

		Long enrollmentKey = (Long)solrDocument.get("enrollmentKey_stored_long");
		if(enrollmentKey != null)
			oSchoolPayment.setEnrollmentKey(enrollmentKey);

		Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
		if(schoolNumber != null)
			oSchoolPayment.setSchoolNumber(schoolNumber);

		List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
		if(userKeys != null)
			oSchoolPayment.userKeys.addAll(userKeys);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolPayment.setSchoolKey(schoolKey);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchoolPayment.setSchoolAddress(schoolAddress);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchoolPayment.setSchoolPhoneNumber(schoolPhoneNumber);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolPayment.setYearKey(yearKey);

		Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
		if(sessionKey != null)
			oSchoolPayment.setSessionKey(sessionKey);

		Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
		if(ageKey != null)
			oSchoolPayment.setAgeKey(ageKey);

		Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
		if(blockKey != null)
			oSchoolPayment.setBlockKey(blockKey);

		Long childKey = (Long)solrDocument.get("childKey_stored_long");
		if(childKey != null)
			oSchoolPayment.setChildKey(childKey);

		List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
		if(momKeys != null)
			oSchoolPayment.momKeys.addAll(momKeys);

		List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
		if(dadKeys != null)
			oSchoolPayment.dadKeys.addAll(dadKeys);

		List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
		if(guardianKeys != null)
			oSchoolPayment.guardianKeys.addAll(guardianKeys);

		String childCompleteNamePreferred = (String)solrDocument.get("childCompleteNamePreferred_stored_string");
		if(childCompleteNamePreferred != null)
			oSchoolPayment.setChildCompleteNamePreferred(childCompleteNamePreferred);

		Date childBirthDate = (Date)solrDocument.get("childBirthDate_stored_date");
		if(childBirthDate != null)
			oSchoolPayment.setChildBirthDate(childBirthDate);

		String momCompleteNamePreferred = (String)solrDocument.get("momCompleteNamePreferred_stored_string");
		if(momCompleteNamePreferred != null)
			oSchoolPayment.setMomCompleteNamePreferred(momCompleteNamePreferred);

		String dadCompleteNamePreferred = (String)solrDocument.get("dadCompleteNamePreferred_stored_string");
		if(dadCompleteNamePreferred != null)
			oSchoolPayment.setDadCompleteNamePreferred(dadCompleteNamePreferred);

		String schoolName = (String)solrDocument.get("schoolName_stored_string");
		if(schoolName != null)
			oSchoolPayment.setSchoolName(schoolName);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oSchoolPayment.setSchoolCompleteName(schoolCompleteName);

		String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
		if(schoolLocation != null)
			oSchoolPayment.setSchoolLocation(schoolLocation);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolPayment.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolPayment.setYearEnd(yearEnd);

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolPayment.setSeasonStartDate(seasonStartDate);

		Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
		if(yearEnrollmentFee != null)
			oSchoolPayment.setYearEnrollmentFee(yearEnrollmentFee);

		Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
		if(sessionStartDate != null)
			oSchoolPayment.setSessionStartDate(sessionStartDate);

		Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
		if(sessionEndDate != null)
			oSchoolPayment.setSessionEndDate(sessionEndDate);

		Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
		if(ageStart != null)
			oSchoolPayment.setAgeStart(ageStart);

		Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
		if(ageEnd != null)
			oSchoolPayment.setAgeEnd(ageEnd);

		String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
		if(blockCompleteName != null)
			oSchoolPayment.setBlockCompleteName(blockCompleteName);

		String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
		if(blockStartTime != null)
			oSchoolPayment.setBlockStartTime(blockStartTime);

		String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
		if(blockEndTime != null)
			oSchoolPayment.setBlockEndTime(blockEndTime);

		Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
		if(blockPricePerMonth != null)
			oSchoolPayment.setBlockPricePerMonth(blockPricePerMonth);

		String enrollmentGroupName = (String)solrDocument.get("enrollmentGroupName_stored_string");
		if(enrollmentGroupName != null)
			oSchoolPayment.setEnrollmentGroupName(enrollmentGroupName);

		Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
		if(blockTotalPrice != null)
			oSchoolPayment.setBlockTotalPrice(blockTotalPrice);

		Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
		if(enrollmentPaymentEachMonth != null)
			oSchoolPayment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);

		String paymentDescription = (String)solrDocument.get("paymentDescription_stored_string");
		if(paymentDescription != null)
			oSchoolPayment.setPaymentDescription(paymentDescription);

		Date paymentDate = (Date)solrDocument.get("paymentDate_stored_date");
		if(paymentDate != null)
			oSchoolPayment.setPaymentDate(paymentDate);

		Date lateFeeDate = (Date)solrDocument.get("lateFeeDate_stored_date");
		if(lateFeeDate != null)
			oSchoolPayment.setLateFeeDate(lateFeeDate);

		Integer paymentYear = (Integer)solrDocument.get("paymentYear_stored_int");
		if(paymentYear != null)
			oSchoolPayment.setPaymentYear(paymentYear);

		Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
		if(paymentAmount != null)
			oSchoolPayment.setPaymentAmount(paymentAmount);

		Boolean paymentCash = (Boolean)solrDocument.get("paymentCash_stored_boolean");
		if(paymentCash != null)
			oSchoolPayment.setPaymentCash(paymentCash);

		Boolean paymentCheck = (Boolean)solrDocument.get("paymentCheck_stored_boolean");
		if(paymentCheck != null)
			oSchoolPayment.setPaymentCheck(paymentCheck);

		Boolean paymentECheck = (Boolean)solrDocument.get("paymentECheck_stored_boolean");
		if(paymentECheck != null)
			oSchoolPayment.setPaymentECheck(paymentECheck);

		Boolean paymentSystem = (Boolean)solrDocument.get("paymentSystem_stored_boolean");
		if(paymentSystem != null)
			oSchoolPayment.setPaymentSystem(paymentSystem);

		String paymentType = (String)solrDocument.get("paymentType_stored_string");
		if(paymentType != null)
			oSchoolPayment.setPaymentType(paymentType);

		String paymentBy = (String)solrDocument.get("paymentBy_stored_string");
		if(paymentBy != null)
			oSchoolPayment.setPaymentBy(paymentBy);

		String transactionId = (String)solrDocument.get("transactionId_stored_string");
		if(transactionId != null)
			oSchoolPayment.setTransactionId(transactionId);

		String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
		if(customerProfileId != null)
			oSchoolPayment.setCustomerProfileId(customerProfileId);

		String transactionStatus = (String)solrDocument.get("transactionStatus_stored_string");
		if(transactionStatus != null)
			oSchoolPayment.setTransactionStatus(transactionStatus);

		Boolean paymentRecieved = (Boolean)solrDocument.get("paymentRecieved_stored_boolean");
		if(paymentRecieved != null)
			oSchoolPayment.setPaymentRecieved(paymentRecieved);

		Double chargeAmount = (Double)solrDocument.get("chargeAmount_stored_double");
		if(chargeAmount != null)
			oSchoolPayment.setChargeAmount(chargeAmount);

		Boolean chargeFirstLast = (Boolean)solrDocument.get("chargeFirstLast_stored_boolean");
		if(chargeFirstLast != null)
			oSchoolPayment.setChargeFirstLast(chargeFirstLast);

		Boolean chargeEnrollment = (Boolean)solrDocument.get("chargeEnrollment_stored_boolean");
		if(chargeEnrollment != null)
			oSchoolPayment.setChargeEnrollment(chargeEnrollment);

		Boolean chargeMonth = (Boolean)solrDocument.get("chargeMonth_stored_boolean");
		if(chargeMonth != null)
			oSchoolPayment.setChargeMonth(chargeMonth);

		Boolean chargeLateFee = (Boolean)solrDocument.get("chargeLateFee_stored_boolean");
		if(chargeLateFee != null)
			oSchoolPayment.setChargeLateFee(chargeLateFee);

		Date paymentNext = (Date)solrDocument.get("paymentNext_stored_date");
		if(paymentNext != null)
			oSchoolPayment.setPaymentNext(paymentNext);

		Double chargeAmountDue = (Double)solrDocument.get("chargeAmountDue_stored_double");
		if(chargeAmountDue != null)
			oSchoolPayment.setChargeAmountDue(chargeAmountDue);

		Double chargeAmountPassed = (Double)solrDocument.get("chargeAmountPassed_stored_double");
		if(chargeAmountPassed != null)
			oSchoolPayment.setChargeAmountPassed(chargeAmountPassed);

		Double chargeAmountNotPassed = (Double)solrDocument.get("chargeAmountNotPassed_stored_double");
		if(chargeAmountNotPassed != null)
			oSchoolPayment.setChargeAmountNotPassed(chargeAmountNotPassed);

		Double chargeAmountFuture = (Double)solrDocument.get("chargeAmountFuture_stored_double");
		if(chargeAmountFuture != null)
			oSchoolPayment.setChargeAmountFuture(chargeAmountFuture);

		String paymentShortName = (String)solrDocument.get("paymentShortName_stored_string");
		if(paymentShortName != null)
			oSchoolPayment.setPaymentShortName(paymentShortName);

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
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolPayment) {
			SchoolPayment original = (SchoolPayment)o;
			if(!Objects.equals(paymentKey, original.getPaymentKey()))
				apiRequest.addVars("paymentKey");
			if(!Objects.equals(enrollmentKey, original.getEnrollmentKey()))
				apiRequest.addVars("enrollmentKey");
			if(!Objects.equals(schoolNumber, original.getSchoolNumber()))
				apiRequest.addVars("schoolNumber");
			if(!Objects.equals(userKeys, original.getUserKeys()))
				apiRequest.addVars("userKeys");
			if(!Objects.equals(schoolKey, original.getSchoolKey()))
				apiRequest.addVars("schoolKey");
			if(!Objects.equals(schoolAddress, original.getSchoolAddress()))
				apiRequest.addVars("schoolAddress");
			if(!Objects.equals(schoolPhoneNumber, original.getSchoolPhoneNumber()))
				apiRequest.addVars("schoolPhoneNumber");
			if(!Objects.equals(yearKey, original.getYearKey()))
				apiRequest.addVars("yearKey");
			if(!Objects.equals(sessionKey, original.getSessionKey()))
				apiRequest.addVars("sessionKey");
			if(!Objects.equals(ageKey, original.getAgeKey()))
				apiRequest.addVars("ageKey");
			if(!Objects.equals(blockKey, original.getBlockKey()))
				apiRequest.addVars("blockKey");
			if(!Objects.equals(childKey, original.getChildKey()))
				apiRequest.addVars("childKey");
			if(!Objects.equals(momKeys, original.getMomKeys()))
				apiRequest.addVars("momKeys");
			if(!Objects.equals(dadKeys, original.getDadKeys()))
				apiRequest.addVars("dadKeys");
			if(!Objects.equals(guardianKeys, original.getGuardianKeys()))
				apiRequest.addVars("guardianKeys");
			if(!Objects.equals(childCompleteNamePreferred, original.getChildCompleteNamePreferred()))
				apiRequest.addVars("childCompleteNamePreferred");
			if(!Objects.equals(childBirthDate, original.getChildBirthDate()))
				apiRequest.addVars("childBirthDate");
			if(!Objects.equals(momCompleteNamePreferred, original.getMomCompleteNamePreferred()))
				apiRequest.addVars("momCompleteNamePreferred");
			if(!Objects.equals(dadCompleteNamePreferred, original.getDadCompleteNamePreferred()))
				apiRequest.addVars("dadCompleteNamePreferred");
			if(!Objects.equals(schoolName, original.getSchoolName()))
				apiRequest.addVars("schoolName");
			if(!Objects.equals(schoolCompleteName, original.getSchoolCompleteName()))
				apiRequest.addVars("schoolCompleteName");
			if(!Objects.equals(schoolLocation, original.getSchoolLocation()))
				apiRequest.addVars("schoolLocation");
			if(!Objects.equals(yearStart, original.getYearStart()))
				apiRequest.addVars("yearStart");
			if(!Objects.equals(yearEnd, original.getYearEnd()))
				apiRequest.addVars("yearEnd");
			if(!Objects.equals(seasonStartDate, original.getSeasonStartDate()))
				apiRequest.addVars("seasonStartDate");
			if(!Objects.equals(yearEnrollmentFee, original.getYearEnrollmentFee()))
				apiRequest.addVars("yearEnrollmentFee");
			if(!Objects.equals(sessionStartDate, original.getSessionStartDate()))
				apiRequest.addVars("sessionStartDate");
			if(!Objects.equals(sessionEndDate, original.getSessionEndDate()))
				apiRequest.addVars("sessionEndDate");
			if(!Objects.equals(ageStart, original.getAgeStart()))
				apiRequest.addVars("ageStart");
			if(!Objects.equals(ageEnd, original.getAgeEnd()))
				apiRequest.addVars("ageEnd");
			if(!Objects.equals(blockCompleteName, original.getBlockCompleteName()))
				apiRequest.addVars("blockCompleteName");
			if(!Objects.equals(blockStartTime, original.getBlockStartTime()))
				apiRequest.addVars("blockStartTime");
			if(!Objects.equals(blockEndTime, original.getBlockEndTime()))
				apiRequest.addVars("blockEndTime");
			if(!Objects.equals(blockPricePerMonth, original.getBlockPricePerMonth()))
				apiRequest.addVars("blockPricePerMonth");
			if(!Objects.equals(enrollmentGroupName, original.getEnrollmentGroupName()))
				apiRequest.addVars("enrollmentGroupName");
			if(!Objects.equals(blockTotalPrice, original.getBlockTotalPrice()))
				apiRequest.addVars("blockTotalPrice");
			if(!Objects.equals(enrollmentPaymentEachMonth, original.getEnrollmentPaymentEachMonth()))
				apiRequest.addVars("enrollmentPaymentEachMonth");
			if(!Objects.equals(paymentDescription, original.getPaymentDescription()))
				apiRequest.addVars("paymentDescription");
			if(!Objects.equals(paymentDate, original.getPaymentDate()))
				apiRequest.addVars("paymentDate");
			if(!Objects.equals(lateFeeDate, original.getLateFeeDate()))
				apiRequest.addVars("lateFeeDate");
			if(!Objects.equals(paymentYear, original.getPaymentYear()))
				apiRequest.addVars("paymentYear");
			if(!Objects.equals(paymentAmount, original.getPaymentAmount()))
				apiRequest.addVars("paymentAmount");
			if(!Objects.equals(paymentCash, original.getPaymentCash()))
				apiRequest.addVars("paymentCash");
			if(!Objects.equals(paymentCheck, original.getPaymentCheck()))
				apiRequest.addVars("paymentCheck");
			if(!Objects.equals(paymentECheck, original.getPaymentECheck()))
				apiRequest.addVars("paymentECheck");
			if(!Objects.equals(paymentSystem, original.getPaymentSystem()))
				apiRequest.addVars("paymentSystem");
			if(!Objects.equals(paymentType, original.getPaymentType()))
				apiRequest.addVars("paymentType");
			if(!Objects.equals(paymentBy, original.getPaymentBy()))
				apiRequest.addVars("paymentBy");
			if(!Objects.equals(transactionId, original.getTransactionId()))
				apiRequest.addVars("transactionId");
			if(!Objects.equals(customerProfileId, original.getCustomerProfileId()))
				apiRequest.addVars("customerProfileId");
			if(!Objects.equals(transactionStatus, original.getTransactionStatus()))
				apiRequest.addVars("transactionStatus");
			if(!Objects.equals(paymentRecieved, original.getPaymentRecieved()))
				apiRequest.addVars("paymentRecieved");
			if(!Objects.equals(chargeAmount, original.getChargeAmount()))
				apiRequest.addVars("chargeAmount");
			if(!Objects.equals(chargeFirstLast, original.getChargeFirstLast()))
				apiRequest.addVars("chargeFirstLast");
			if(!Objects.equals(chargeEnrollment, original.getChargeEnrollment()))
				apiRequest.addVars("chargeEnrollment");
			if(!Objects.equals(chargeMonth, original.getChargeMonth()))
				apiRequest.addVars("chargeMonth");
			if(!Objects.equals(chargeLateFee, original.getChargeLateFee()))
				apiRequest.addVars("chargeLateFee");
			if(!Objects.equals(paymentNext, original.getPaymentNext()))
				apiRequest.addVars("paymentNext");
			if(!Objects.equals(chargeAmountDue, original.getChargeAmountDue()))
				apiRequest.addVars("chargeAmountDue");
			if(!Objects.equals(chargeAmountPassed, original.getChargeAmountPassed()))
				apiRequest.addVars("chargeAmountPassed");
			if(!Objects.equals(chargeAmountNotPassed, original.getChargeAmountNotPassed()))
				apiRequest.addVars("chargeAmountNotPassed");
			if(!Objects.equals(chargeAmountFuture, original.getChargeAmountFuture()))
				apiRequest.addVars("chargeAmountFuture");
			if(!Objects.equals(paymentShortName, original.getPaymentShortName()))
				apiRequest.addVars("paymentShortName");
			if(!Objects.equals(paymentCompleteName, original.getPaymentCompleteName()))
				apiRequest.addVars("paymentCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), paymentKey, enrollmentKey, schoolNumber, userKeys, schoolKey, schoolAddress, schoolPhoneNumber, yearKey, sessionKey, ageKey, blockKey, childKey, momKeys, dadKeys, guardianKeys, childCompleteNamePreferred, childBirthDate, momCompleteNamePreferred, dadCompleteNamePreferred, schoolName, schoolCompleteName, schoolLocation, yearStart, yearEnd, seasonStartDate, yearEnrollmentFee, sessionStartDate, sessionEndDate, ageStart, ageEnd, blockCompleteName, blockStartTime, blockEndTime, blockPricePerMonth, enrollmentGroupName, blockTotalPrice, enrollmentPaymentEachMonth, paymentDescription, paymentDate, lateFeeDate, paymentYear, paymentAmount, paymentCash, paymentCheck, paymentECheck, paymentSystem, paymentType, paymentBy, transactionId, customerProfileId, transactionStatus, paymentRecieved, chargeAmount, chargeFirstLast, chargeEnrollment, chargeMonth, chargeLateFee, paymentNext, chargeAmountDue, chargeAmountPassed, chargeAmountNotPassed, chargeAmountFuture, paymentShortName, paymentCompleteName);
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
				&& Objects.equals( paymentKey, that.paymentKey )
				&& Objects.equals( enrollmentKey, that.enrollmentKey )
				&& Objects.equals( schoolNumber, that.schoolNumber )
				&& Objects.equals( userKeys, that.userKeys )
				&& Objects.equals( schoolKey, that.schoolKey )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( schoolPhoneNumber, that.schoolPhoneNumber )
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( sessionKey, that.sessionKey )
				&& Objects.equals( ageKey, that.ageKey )
				&& Objects.equals( blockKey, that.blockKey )
				&& Objects.equals( childKey, that.childKey )
				&& Objects.equals( momKeys, that.momKeys )
				&& Objects.equals( dadKeys, that.dadKeys )
				&& Objects.equals( guardianKeys, that.guardianKeys )
				&& Objects.equals( childCompleteNamePreferred, that.childCompleteNamePreferred )
				&& Objects.equals( childBirthDate, that.childBirthDate )
				&& Objects.equals( momCompleteNamePreferred, that.momCompleteNamePreferred )
				&& Objects.equals( dadCompleteNamePreferred, that.dadCompleteNamePreferred )
				&& Objects.equals( schoolName, that.schoolName )
				&& Objects.equals( schoolCompleteName, that.schoolCompleteName )
				&& Objects.equals( schoolLocation, that.schoolLocation )
				&& Objects.equals( yearStart, that.yearStart )
				&& Objects.equals( yearEnd, that.yearEnd )
				&& Objects.equals( seasonStartDate, that.seasonStartDate )
				&& Objects.equals( yearEnrollmentFee, that.yearEnrollmentFee )
				&& Objects.equals( sessionStartDate, that.sessionStartDate )
				&& Objects.equals( sessionEndDate, that.sessionEndDate )
				&& Objects.equals( ageStart, that.ageStart )
				&& Objects.equals( ageEnd, that.ageEnd )
				&& Objects.equals( blockCompleteName, that.blockCompleteName )
				&& Objects.equals( blockStartTime, that.blockStartTime )
				&& Objects.equals( blockEndTime, that.blockEndTime )
				&& Objects.equals( blockPricePerMonth, that.blockPricePerMonth )
				&& Objects.equals( enrollmentGroupName, that.enrollmentGroupName )
				&& Objects.equals( blockTotalPrice, that.blockTotalPrice )
				&& Objects.equals( enrollmentPaymentEachMonth, that.enrollmentPaymentEachMonth )
				&& Objects.equals( paymentDescription, that.paymentDescription )
				&& Objects.equals( paymentDate, that.paymentDate )
				&& Objects.equals( lateFeeDate, that.lateFeeDate )
				&& Objects.equals( paymentYear, that.paymentYear )
				&& Objects.equals( paymentAmount, that.paymentAmount )
				&& Objects.equals( paymentCash, that.paymentCash )
				&& Objects.equals( paymentCheck, that.paymentCheck )
				&& Objects.equals( paymentECheck, that.paymentECheck )
				&& Objects.equals( paymentSystem, that.paymentSystem )
				&& Objects.equals( paymentType, that.paymentType )
				&& Objects.equals( paymentBy, that.paymentBy )
				&& Objects.equals( transactionId, that.transactionId )
				&& Objects.equals( customerProfileId, that.customerProfileId )
				&& Objects.equals( transactionStatus, that.transactionStatus )
				&& Objects.equals( paymentRecieved, that.paymentRecieved )
				&& Objects.equals( chargeAmount, that.chargeAmount )
				&& Objects.equals( chargeFirstLast, that.chargeFirstLast )
				&& Objects.equals( chargeEnrollment, that.chargeEnrollment )
				&& Objects.equals( chargeMonth, that.chargeMonth )
				&& Objects.equals( chargeLateFee, that.chargeLateFee )
				&& Objects.equals( paymentNext, that.paymentNext )
				&& Objects.equals( chargeAmountDue, that.chargeAmountDue )
				&& Objects.equals( chargeAmountPassed, that.chargeAmountPassed )
				&& Objects.equals( chargeAmountNotPassed, that.chargeAmountNotPassed )
				&& Objects.equals( chargeAmountFuture, that.chargeAmountFuture )
				&& Objects.equals( paymentShortName, that.paymentShortName )
				&& Objects.equals( paymentCompleteName, that.paymentCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolPayment { ");
		sb.append( "paymentKey: " ).append(paymentKey);
		sb.append( ", enrollmentKey: " ).append(enrollmentKey);
		sb.append( ", schoolNumber: " ).append(schoolNumber);
		sb.append( ", userKeys: " ).append(userKeys);
		sb.append( ", schoolKey: " ).append(schoolKey);
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", schoolPhoneNumber: \"" ).append(schoolPhoneNumber).append( "\"" );
		sb.append( ", yearKey: " ).append(yearKey);
		sb.append( ", sessionKey: " ).append(sessionKey);
		sb.append( ", ageKey: " ).append(ageKey);
		sb.append( ", blockKey: " ).append(blockKey);
		sb.append( ", childKey: " ).append(childKey);
		sb.append( ", momKeys: " ).append(momKeys);
		sb.append( ", dadKeys: " ).append(dadKeys);
		sb.append( ", guardianKeys: " ).append(guardianKeys);
		sb.append( ", childCompleteNamePreferred: \"" ).append(childCompleteNamePreferred).append( "\"" );
		sb.append( ", childBirthDate: " ).append(childBirthDate);
		sb.append( ", momCompleteNamePreferred: \"" ).append(momCompleteNamePreferred).append( "\"" );
		sb.append( ", dadCompleteNamePreferred: \"" ).append(dadCompleteNamePreferred).append( "\"" );
		sb.append( ", schoolName: \"" ).append(schoolName).append( "\"" );
		sb.append( ", schoolCompleteName: \"" ).append(schoolCompleteName).append( "\"" );
		sb.append( ", schoolLocation: \"" ).append(schoolLocation).append( "\"" );
		sb.append( ", yearStart: " ).append(yearStart);
		sb.append( ", yearEnd: " ).append(yearEnd);
		sb.append( ", seasonStartDate: " ).append(seasonStartDate);
		sb.append( ", yearEnrollmentFee: " ).append(yearEnrollmentFee);
		sb.append( ", sessionStartDate: " ).append(sessionStartDate);
		sb.append( ", sessionEndDate: " ).append(sessionEndDate);
		sb.append( ", ageStart: " ).append(ageStart);
		sb.append( ", ageEnd: " ).append(ageEnd);
		sb.append( ", blockCompleteName: \"" ).append(blockCompleteName).append( "\"" );
		sb.append( ", blockStartTime: " ).append(blockStartTime);
		sb.append( ", blockEndTime: " ).append(blockEndTime);
		sb.append( ", blockPricePerMonth: " ).append(blockPricePerMonth);
		sb.append( ", enrollmentGroupName: \"" ).append(enrollmentGroupName).append( "\"" );
		sb.append( ", blockTotalPrice: " ).append(blockTotalPrice);
		sb.append( ", enrollmentPaymentEachMonth: " ).append(enrollmentPaymentEachMonth);
		sb.append( ", paymentDescription: \"" ).append(paymentDescription).append( "\"" );
		sb.append( ", paymentDate: " ).append(paymentDate);
		sb.append( ", lateFeeDate: " ).append(lateFeeDate);
		sb.append( ", paymentYear: " ).append(paymentYear);
		sb.append( ", paymentAmount: " ).append(paymentAmount);
		sb.append( ", paymentCash: " ).append(paymentCash);
		sb.append( ", paymentCheck: " ).append(paymentCheck);
		sb.append( ", paymentECheck: " ).append(paymentECheck);
		sb.append( ", paymentSystem: " ).append(paymentSystem);
		sb.append( ", paymentType: \"" ).append(paymentType).append( "\"" );
		sb.append( ", paymentBy: \"" ).append(paymentBy).append( "\"" );
		sb.append( ", transactionId: \"" ).append(transactionId).append( "\"" );
		sb.append( ", customerProfileId: \"" ).append(customerProfileId).append( "\"" );
		sb.append( ", transactionStatus: \"" ).append(transactionStatus).append( "\"" );
		sb.append( ", paymentRecieved: " ).append(paymentRecieved);
		sb.append( ", chargeAmount: " ).append(chargeAmount);
		sb.append( ", chargeFirstLast: " ).append(chargeFirstLast);
		sb.append( ", chargeEnrollment: " ).append(chargeEnrollment);
		sb.append( ", chargeMonth: " ).append(chargeMonth);
		sb.append( ", chargeLateFee: " ).append(chargeLateFee);
		sb.append( ", paymentNext: " ).append(paymentNext);
		sb.append( ", chargeAmountDue: " ).append(chargeAmountDue);
		sb.append( ", chargeAmountPassed: " ).append(chargeAmountPassed);
		sb.append( ", chargeAmountNotPassed: " ).append(chargeAmountNotPassed);
		sb.append( ", chargeAmountFuture: " ).append(chargeAmountFuture);
		sb.append( ", paymentShortName: \"" ).append(paymentShortName).append( "\"" );
		sb.append( ", paymentCompleteName: \"" ).append(paymentCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
