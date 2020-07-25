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
import java.time.LocalTime;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
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
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
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
	public static final List<String> ROLE_READS = Arrays.asList("User");

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
	public SchoolPayment setEnrollmentKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentKey = Long.parseLong(o);
		this.enrollmentKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
				e("input")
					.a("type", "text")
					.a("placeholder", "enrollment")
					.a("class", "valueObjectSuggest suggestEnrollmentKey w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setEnrollmentKey")
					.a("id", classApiMethodMethod, "_enrollmentKey")
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolPaymentEnrollmentKey($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'paymentKeys:" + pk + "'}", "], $('#listSchoolPaymentEnrollmentKey_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmEnrollmentKey());
			}
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
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("id", classApiMethodMethod, "_enrollmentKey_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ paymentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKey')); });")
											.f().sx("add an enrollment")
										.g("button");
									} g("div");
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
	public SchoolPayment setSchoolNumber(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolNumber = Integer.parseInt(o);
		this.schoolNumberWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Integer solrSchoolNumber() {
		return schoolNumber;
	}

	public String strSchoolNumber() {
		return schoolNumber == null ? "" : schoolNumber.toString();
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
	public SchoolPayment setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
		return (SchoolPayment)this;
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

	public List<Long> solrUserKeys() {
		return userKeys;
	}

	public String strUserKeys() {
		return userKeys == null ? "" : userKeys.toString();
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
	public SchoolPayment setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
		this.schoolAddressWrap.alreadyInitialized = true;
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

	public String solrSchoolAddress() {
		return schoolAddress;
	}

	public String strSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
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

	public void setSchoolPhoneNumber(String schoolPhoneNumber) {
		this.schoolPhoneNumber = schoolPhoneNumber;
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
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

	public String solrSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}

	public String strSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
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
	public SchoolPayment setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Long solrYearKey() {
		return yearKey;
	}

	public String strYearKey() {
		return yearKey == null ? "" : yearKey.toString();
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
	public SchoolPayment setSessionKey(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionKey = Long.parseLong(o);
		this.sessionKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
	public SchoolPayment setAgeKey(String o) {
		if(NumberUtils.isParsable(o))
			this.ageKey = Long.parseLong(o);
		this.ageKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
	public SchoolPayment setBlockKey(String o) {
		if(NumberUtils.isParsable(o))
			this.blockKey = Long.parseLong(o);
		this.blockKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
	public SchoolPayment setChildKey(String o) {
		if(NumberUtils.isParsable(o))
			this.childKey = Long.parseLong(o);
		this.childKeyWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public void setChildCompleteNamePreferred(String childCompleteNamePreferred) {
		this.childCompleteNamePreferred = childCompleteNamePreferred;
		this.childCompleteNamePreferredWrap.alreadyInitialized = true;
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

	public String solrChildCompleteNamePreferred() {
		return childCompleteNamePreferred;
	}

	public String strChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : childCompleteNamePreferred;
	}

	public String jsonChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : childCompleteNamePreferred;
	}

	public String nomAffichageChildCompleteNamePreferred() {
		return "Facet: terms";
	}

	public String htmTooltipChildCompleteNamePreferred() {
		return null;
	}

	public String htmChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strChildCompleteNamePreferred());
	}

	public void inputChildCompleteNamePreferred(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "Facet: terms")
				.a("id", classApiMethodMethod, "_childCompleteNamePreferred");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChildCompleteNamePreferred classSchoolPayment inputSchoolPayment", pk, "ChildCompleteNamePreferred w3-input w3-border ");
					a("name", "setChildCompleteNamePreferred");
				} else {
					a("class", "valueChildCompleteNamePreferred w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "ChildCompleteNamePreferred w3-input w3-border ");
					a("name", "childCompleteNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildCompleteNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }); ");
				}
				a("value", strChildCompleteNamePreferred())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmChildCompleteNamePreferred());
			}
		}
	}

	public void htmChildCompleteNamePreferred(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChildCompleteNamePreferred").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_childCompleteNamePreferred").a("class", "").f().sx("Facet: terms").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildCompleteNamePreferred(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childCompleteNamePreferred')); $('#", classApiMethodMethod, "_childCompleteNamePreferred').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setChildCompleteNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }); ")
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
	public SchoolPayment setChildBirthDate(Instant o) {
		this.childBirthDate = o == null ? null : LocalDate.from(o);
		this.childBirthDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolPayment setChildBirthDate(String o) {
		this.childBirthDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.childBirthDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setChildBirthDate(Date o) {
		this.childBirthDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.childBirthDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Date solrChildBirthDate() {
		return childBirthDate == null ? null : Date.from(childBirthDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageChildBirthDate() {
		return "r: inscription_";
	}

	public String htmTooltipChildBirthDate() {
		return null;
	}

	public String htmChildBirthDate() {
		return childBirthDate == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthDate());
	}

	public void inputChildBirthDate(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setChildBirthDate classSchoolPayment inputSchoolPayment", pk, "ChildBirthDate w3-input w3-border ")
				.a("placeholder", "MM/DD/YYYY")
				.a("data-timeformat", "MM/dd/yyyy")
				.a("id", classApiMethodMethod, "_childBirthDate")
				.a("onclick", "removeGlow($(this)); ")
				.a("value", childBirthDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(childBirthDate))
				.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildBirthDate', s, function() { addGlow($('#", classApiMethodMethod, "_childBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_childBirthDate')); }); } ")
				.fg();
		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmChildBirthDate());
			}
		}
	}

	public void htmChildBirthDate(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChildBirthDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_childBirthDate").a("class", "").f().sx("r: inscription_").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputChildBirthDate(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childBirthDate')); $('#", classApiMethodMethod, "_childBirthDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setChildBirthDate', null, function() { addGlow($('#", classApiMethodMethod, "_childBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_childBirthDate')); }); ")
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

	public void setMomCompleteNamePreferred(String momCompleteNamePreferred) {
		this.momCompleteNamePreferred = momCompleteNamePreferred;
		this.momCompleteNamePreferredWrap.alreadyInitialized = true;
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

	public String solrMomCompleteNamePreferred() {
		return momCompleteNamePreferred;
	}

	public String strMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : momCompleteNamePreferred;
	}

	public String jsonMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : momCompleteNamePreferred;
	}

	public String nomAffichageMomCompleteNamePreferred() {
		return "r: inscription_";
	}

	public String htmTooltipMomCompleteNamePreferred() {
		return null;
	}

	public String htmMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strMomCompleteNamePreferred());
	}

	public void inputMomCompleteNamePreferred(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "r: inscription_")
				.a("id", classApiMethodMethod, "_momCompleteNamePreferred");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setMomCompleteNamePreferred classSchoolPayment inputSchoolPayment", pk, "MomCompleteNamePreferred w3-input w3-border ");
					a("name", "setMomCompleteNamePreferred");
				} else {
					a("class", "valueMomCompleteNamePreferred w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "MomCompleteNamePreferred w3-input w3-border ");
					a("name", "momCompleteNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setMomCompleteNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_momCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_momCompleteNamePreferred')); }); ");
				}
				a("value", strMomCompleteNamePreferred())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmMomCompleteNamePreferred());
			}
		}
	}

	public void htmMomCompleteNamePreferred(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentMomCompleteNamePreferred").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_momCompleteNamePreferred").a("class", "").f().sx("r: inscription_").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputMomCompleteNamePreferred(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_momCompleteNamePreferred')); $('#", classApiMethodMethod, "_momCompleteNamePreferred').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setMomCompleteNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_momCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_momCompleteNamePreferred')); }); ")
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

	public void setDadCompleteNamePreferred(String dadCompleteNamePreferred) {
		this.dadCompleteNamePreferred = dadCompleteNamePreferred;
		this.dadCompleteNamePreferredWrap.alreadyInitialized = true;
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

	public String solrDadCompleteNamePreferred() {
		return dadCompleteNamePreferred;
	}

	public String strDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : dadCompleteNamePreferred;
	}

	public String jsonDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : dadCompleteNamePreferred;
	}

	public String nomAffichageDadCompleteNamePreferred() {
		return "r: inscription_";
	}

	public String htmTooltipDadCompleteNamePreferred() {
		return null;
	}

	public String htmDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strDadCompleteNamePreferred());
	}

	public void inputDadCompleteNamePreferred(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "r: inscription_")
				.a("id", classApiMethodMethod, "_dadCompleteNamePreferred");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setDadCompleteNamePreferred classSchoolPayment inputSchoolPayment", pk, "DadCompleteNamePreferred w3-input w3-border ");
					a("name", "setDadCompleteNamePreferred");
				} else {
					a("class", "valueDadCompleteNamePreferred w3-input w3-border classSchoolPayment inputSchoolPayment", pk, "DadCompleteNamePreferred w3-input w3-border ");
					a("name", "dadCompleteNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDadCompleteNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_dadCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_dadCompleteNamePreferred')); }); ");
				}
				a("value", strDadCompleteNamePreferred())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmDadCompleteNamePreferred());
			}
		}
	}

	public void htmDadCompleteNamePreferred(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentDadCompleteNamePreferred").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_dadCompleteNamePreferred").a("class", "").f().sx("r: inscription_").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDadCompleteNamePreferred(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_dadCompleteNamePreferred')); $('#", classApiMethodMethod, "_dadCompleteNamePreferred').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolPaymentForm :input[name=pk]').val() }], 'setDadCompleteNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_dadCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_dadCompleteNamePreferred')); }); ")
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

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
		this.schoolNameWrap.alreadyInitialized = true;
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

	public String solrSchoolName() {
		return schoolName;
	}

	public String strSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String jsonSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String nomAffichageSchoolName() {
		return "r: EcoleNom";
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

	public void setSchoolCompleteName(String schoolCompleteName) {
		this.schoolCompleteName = schoolCompleteName;
		this.schoolCompleteNameWrap.alreadyInitialized = true;
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

	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
		this.schoolLocationWrap.alreadyInitialized = true;
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

	public String solrSchoolLocation() {
		return schoolLocation;
	}

	public String strSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
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
	public SchoolPayment setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
	public SchoolPayment setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
	public SchoolPayment setSeasonStartDate(Instant o) {
		this.seasonStartDate = o == null ? null : LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolPayment setSeasonStartDate(String o) {
		this.seasonStartDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Date solrSeasonStartDate() {
		return seasonStartDate == null ? null : Date.from(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
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
	public SchoolPayment setYearEnrollmentFee(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setYearEnrollmentFee(Double o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setYearEnrollmentFee(Integer o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Double solrYearEnrollmentFee() {
		return yearEnrollmentFee == null ? null : yearEnrollmentFee.doubleValue();
	}

	public String strYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.setScale(2, RoundingMode.CEILING).toString();
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
	public SchoolPayment setSessionStartDate(Instant o) {
		this.sessionStartDate = o == null ? null : LocalDate.from(o);
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolPayment setSessionStartDate(String o) {
		this.sessionStartDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setSessionStartDate(Date o) {
		this.sessionStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Date solrSessionStartDate() {
		return sessionStartDate == null ? null : Date.from(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
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
	public SchoolPayment setSessionEndDate(Instant o) {
		this.sessionEndDate = o == null ? null : LocalDate.from(o);
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolPayment setSessionEndDate(String o) {
		this.sessionEndDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setSessionEndDate(Date o) {
		this.sessionEndDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Date solrSessionEndDate() {
		return sessionEndDate == null ? null : Date.from(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
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
	public SchoolPayment setAgeStart(String o) {
		if(NumberUtils.isParsable(o))
			this.ageStart = Integer.parseInt(o);
		this.ageStartWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
	public SchoolPayment setAgeEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.ageEnd = Integer.parseInt(o);
		this.ageEndWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
	public SchoolPayment setBlockStartTime(String o) {
		try {
			this.blockStartTime = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
			this.blockStartTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolPayment)this;
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

	public String solrBlockStartTime() {
		return blockStartTime == null ? null : blockStartTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")));
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
	public SchoolPayment setBlockEndTime(String o) {
		try {
			this.blockEndTime = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
			this.blockEndTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolPayment)this;
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

	public String solrBlockEndTime() {
		return blockEndTime == null ? null : blockEndTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")));
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
	public SchoolPayment setBlockPricePerMonth(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setBlockPricePerMonth(Double o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setBlockPricePerMonth(Integer o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Double solrBlockPricePerMonth() {
		return blockPricePerMonth == null ? null : blockPricePerMonth.doubleValue();
	}

	public String strBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.setScale(2, RoundingMode.CEILING).toString();
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
	public SchoolPayment setBlockTotalPrice(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setBlockTotalPrice(Double o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setBlockTotalPrice(Integer o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Double solrBlockTotalPrice() {
		return blockTotalPrice == null ? null : blockTotalPrice.doubleValue();
	}

	public String strBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.setScale(2, RoundingMode.CEILING).toString();
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
	public SchoolPayment setEnrollmentPaymentEachMonth(String o) {
		this.enrollmentPaymentEachMonth = Boolean.parseBoolean(o);
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmEnrollmentPaymentEachMonth());
			}
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentDescription());
			}
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
	public SchoolPayment setPaymentDate(Instant o) {
		this.paymentDate = o == null ? null : LocalDate.from(o);
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolPayment setPaymentDate(String o) {
		this.paymentDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setPaymentDate(Date o) {
		this.paymentDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
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
		return paymentDate == null ? null : Date.from(paymentDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strPaymentDate() {
		return paymentDate == null ? "" : paymentDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
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
				.a("onclick", "removeGlow($(this)); ")
				.a("value", paymentDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(paymentDate))
				.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaymentDate', s, function() { addGlow($('#", classApiMethodMethod, "_paymentDate')); }, function() { addError($('#", classApiMethodMethod, "_paymentDate')); }); } ")
				.fg();
		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentDate());
			}
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
	public SchoolPayment setPaymentAmount(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setPaymentAmount(Double o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setPaymentAmount(Integer o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
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
		return paymentAmount == null ? "" : paymentAmount.setScale(2, RoundingMode.CEILING).toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentAmount());
			}
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentCash());
			}
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentCheck());
			}
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
	public SchoolPayment setPaymentECheck(String o) {
		this.paymentECheck = Boolean.parseBoolean(o);
		this.paymentECheckWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Boolean solrPaymentECheck() {
		return paymentECheck;
	}

	public String strPaymentECheck() {
		return paymentECheck == null ? "" : paymentECheck.toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentECheck());
			}
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentSystem());
			}
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

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
		this.paymentTypeWrap.alreadyInitialized = true;
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

	public String solrPaymentType() {
		return paymentType;
	}

	public String strPaymentType() {
		return paymentType == null ? "" : paymentType;
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

	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
		this.paymentByWrap.alreadyInitialized = true;
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

	public String solrPaymentBy() {
		return paymentBy;
	}

	public String strPaymentBy() {
		return paymentBy == null ? "" : paymentBy;
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentBy());
			}
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

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		this.transactionIdWrap.alreadyInitialized = true;
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

	public String solrTransactionId() {
		return transactionId;
	}

	public String strTransactionId() {
		return transactionId == null ? "" : transactionId;
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmTransactionId());
			}
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

	public void setCustomerProfileId(String customerProfileId) {
		this.customerProfileId = customerProfileId;
		this.customerProfileIdWrap.alreadyInitialized = true;
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

	public String solrCustomerProfileId() {
		return customerProfileId;
	}

	public String strCustomerProfileId() {
		return customerProfileId == null ? "" : customerProfileId;
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmCustomerProfileId());
			}
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

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		this.transactionStatusWrap.alreadyInitialized = true;
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

	public String solrTransactionStatus() {
		return transactionStatus;
	}

	public String strTransactionStatus() {
		return transactionStatus == null ? "" : transactionStatus;
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmTransactionStatus());
			}
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
	public SchoolPayment setPaymentRecieved(String o) {
		this.paymentRecieved = Boolean.parseBoolean(o);
		this.paymentRecievedWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Boolean solrPaymentRecieved() {
		return paymentRecieved;
	}

	public String strPaymentRecieved() {
		return paymentRecieved == null ? "" : paymentRecieved.toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentRecieved());
			}
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
	public SchoolPayment setChargeAmount(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setChargeAmount(Double o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setChargeAmount(Integer o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Double solrChargeAmount() {
		return chargeAmount == null ? null : chargeAmount.doubleValue();
	}

	public String strChargeAmount() {
		return chargeAmount == null ? "" : chargeAmount.setScale(2, RoundingMode.CEILING).toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmChargeAmount());
			}
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
	public SchoolPayment setChargeFirstLast(String o) {
		this.chargeFirstLast = Boolean.parseBoolean(o);
		this.chargeFirstLastWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Boolean solrChargeFirstLast() {
		return chargeFirstLast;
	}

	public String strChargeFirstLast() {
		return chargeFirstLast == null ? "" : chargeFirstLast.toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmChargeFirstLast());
			}
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
	public SchoolPayment setChargeEnrollment(String o) {
		this.chargeEnrollment = Boolean.parseBoolean(o);
		this.chargeEnrollmentWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Boolean solrChargeEnrollment() {
		return chargeEnrollment;
	}

	public String strChargeEnrollment() {
		return chargeEnrollment == null ? "" : chargeEnrollment.toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmChargeEnrollment());
			}
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
	public SchoolPayment setChargeMonth(String o) {
		this.chargeMonth = Boolean.parseBoolean(o);
		this.chargeMonthWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Boolean solrChargeMonth() {
		return chargeMonth;
	}

	public String strChargeMonth() {
		return chargeMonth == null ? "" : chargeMonth.toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmChargeMonth());
			}
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
	public SchoolPayment setChargeLateFee(String o) {
		this.chargeLateFee = Boolean.parseBoolean(o);
		this.chargeLateFeeWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Boolean solrChargeLateFee() {
		return chargeLateFee;
	}

	public String strChargeLateFee() {
		return chargeLateFee == null ? "" : chargeLateFee.toString();
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmChargeLateFee());
			}
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
	public SchoolPayment setPaymentNext(Instant o) {
		this.paymentNext = o == null ? null : LocalDate.from(o);
		this.paymentNextWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolPayment setPaymentNext(String o) {
		this.paymentNext = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.paymentNextWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setPaymentNext(Date o) {
		this.paymentNext = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.paymentNextWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Date solrPaymentNext() {
		return paymentNext == null ? null : Date.from(paymentNext.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strPaymentNext() {
		return paymentNext == null ? "" : paymentNext.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
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
	public SchoolPayment setChargeAmountDue(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountDueWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setChargeAmountDue(Double o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountDueWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setChargeAmountDue(Integer o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountDueWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Double solrChargeAmountDue() {
		return chargeAmountDue == null ? null : chargeAmountDue.doubleValue();
	}

	public String strChargeAmountDue() {
		return chargeAmountDue == null ? "" : chargeAmountDue.setScale(2, RoundingMode.CEILING).toString();
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

	public void inputChargeAmountDue(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		sx(htmChargeAmountDue());
	}

	public void htmChargeAmountDue(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChargeAmountDue").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_chargeAmountDue").a("class", "").f().sx("charge amount due").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChargeAmountDue(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	public SchoolPayment setChargeAmountFuture(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setChargeAmountFuture(Double o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
	}
	public SchoolPayment setChargeAmountFuture(Integer o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
		return (SchoolPayment)this;
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

	public Double solrChargeAmountFuture() {
		return chargeAmountFuture == null ? null : chargeAmountFuture.doubleValue();
	}

	public String strChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : chargeAmountFuture.setScale(2, RoundingMode.CEILING).toString();
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

	public void inputChargeAmountFuture(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		sx(htmChargeAmountFuture());
	}

	public void htmChargeAmountFuture(String classApiMethodMethod) {
		SchoolPayment s = (SchoolPayment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolPaymentChargeAmountFuture").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classApiMethodMethod, "_chargeAmountFuture").a("class", "").f().sx("future charge amount").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChargeAmountFuture(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void setPaymentShortName(String paymentShortName) {
		this.paymentShortName = paymentShortName;
		this.paymentShortNameWrap.alreadyInitialized = true;
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

	public String solrPaymentShortName() {
		return paymentShortName;
	}

	public String strPaymentShortName() {
		return paymentShortName == null ? "" : paymentShortName;
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLE_READS)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLE_READS)
				) {
				sx(htmPaymentShortName());
			}
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
		blockStartTimeInit();
		blockEndTimeInit();
		blockPricePerMonthInit();
		blockTotalPriceInit();
		enrollmentPaymentEachMonthInit();
		paymentDescriptionInit();
		paymentDateInit();
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
		paymentNextInit();
		chargeAmountDueInit();
		chargeAmountFutureInit();
		paymentShortNameInit();
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
			case "blockStartTime":
				return oSchoolPayment.blockStartTime;
			case "blockEndTime":
				return oSchoolPayment.blockEndTime;
			case "blockPricePerMonth":
				return oSchoolPayment.blockPricePerMonth;
			case "blockTotalPrice":
				return oSchoolPayment.blockTotalPrice;
			case "enrollmentPaymentEachMonth":
				return oSchoolPayment.enrollmentPaymentEachMonth;
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
			case "paymentNext":
				return oSchoolPayment.paymentNext;
			case "chargeAmountDue":
				return oSchoolPayment.chargeAmountDue;
			case "chargeAmountFuture":
				return oSchoolPayment.chargeAmountFuture;
			case "paymentShortName":
				return oSchoolPayment.paymentShortName;
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
			case "enrollmentKey":
				oSchoolPayment.setEnrollmentKey((Long)val);
				if(!saves.contains(var))
					saves.add(var);
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
			case "childCompleteNamePreferred":
				if(val != null)
					setChildCompleteNamePreferred(val);
				saves.add(var);
				return val;
			case "childBirthDate":
				if(val != null)
					setChildBirthDate(val);
				saves.add(var);
				return val;
			case "momCompleteNamePreferred":
				if(val != null)
					setMomCompleteNamePreferred(val);
				saves.add(var);
				return val;
			case "dadCompleteNamePreferred":
				if(val != null)
					setDadCompleteNamePreferred(val);
				saves.add(var);
				return val;
			case "enrollmentPaymentEachMonth":
				if(val != null)
					setEnrollmentPaymentEachMonth(val);
				saves.add(var);
				return val;
			case "paymentDescription":
				if(val != null)
					setPaymentDescription(val);
				saves.add(var);
				return val;
			case "paymentDate":
				if(val != null)
					setPaymentDate(val);
				saves.add(var);
				return val;
			case "paymentAmount":
				if(val != null)
					setPaymentAmount(val);
				saves.add(var);
				return val;
			case "paymentCash":
				if(val != null)
					setPaymentCash(val);
				saves.add(var);
				return val;
			case "paymentCheck":
				if(val != null)
					setPaymentCheck(val);
				saves.add(var);
				return val;
			case "paymentECheck":
				if(val != null)
					setPaymentECheck(val);
				saves.add(var);
				return val;
			case "paymentSystem":
				if(val != null)
					setPaymentSystem(val);
				saves.add(var);
				return val;
			case "paymentBy":
				if(val != null)
					setPaymentBy(val);
				saves.add(var);
				return val;
			case "transactionId":
				if(val != null)
					setTransactionId(val);
				saves.add(var);
				return val;
			case "customerProfileId":
				if(val != null)
					setCustomerProfileId(val);
				saves.add(var);
				return val;
			case "transactionStatus":
				if(val != null)
					setTransactionStatus(val);
				saves.add(var);
				return val;
			case "paymentRecieved":
				if(val != null)
					setPaymentRecieved(val);
				saves.add(var);
				return val;
			case "chargeAmount":
				if(val != null)
					setChargeAmount(val);
				saves.add(var);
				return val;
			case "chargeFirstLast":
				if(val != null)
					setChargeFirstLast(val);
				saves.add(var);
				return val;
			case "chargeEnrollment":
				if(val != null)
					setChargeEnrollment(val);
				saves.add(var);
				return val;
			case "chargeMonth":
				if(val != null)
					setChargeMonth(val);
				saves.add(var);
				return val;
			case "chargeLateFee":
				if(val != null)
					setChargeLateFee(val);
				saves.add(var);
				return val;
			case "chargeAmountDue":
				if(val != null)
					setChargeAmountDue(val);
				saves.add(var);
				return val;
			case "chargeAmountFuture":
				if(val != null)
					setChargeAmountFuture(val);
				saves.add(var);
				return val;
			case "paymentShortName":
				if(val != null)
					setPaymentShortName(val);
				saves.add(var);
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
			case "blockTotalPrice":
				return "blockTotalPrice_indexed_double";
			case "enrollmentPaymentEachMonth":
				return "enrollmentPaymentEachMonth_indexed_boolean";
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

		String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
		if(blockStartTime != null)
			oSchoolPayment.setBlockStartTime(blockStartTime);

		String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
		if(blockEndTime != null)
			oSchoolPayment.setBlockEndTime(blockEndTime);

		Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
		if(blockPricePerMonth != null)
			oSchoolPayment.setBlockPricePerMonth(blockPricePerMonth);

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
			if(!Objects.equals(enrollmentKey, original.getEnrollmentKey()))
				apiRequest.addVars("enrollmentKey");
			if(!Objects.equals(childCompleteNamePreferred, original.getChildCompleteNamePreferred()))
				apiRequest.addVars("childCompleteNamePreferred");
			if(!Objects.equals(childBirthDate, original.getChildBirthDate()))
				apiRequest.addVars("childBirthDate");
			if(!Objects.equals(momCompleteNamePreferred, original.getMomCompleteNamePreferred()))
				apiRequest.addVars("momCompleteNamePreferred");
			if(!Objects.equals(dadCompleteNamePreferred, original.getDadCompleteNamePreferred()))
				apiRequest.addVars("dadCompleteNamePreferred");
			if(!Objects.equals(enrollmentPaymentEachMonth, original.getEnrollmentPaymentEachMonth()))
				apiRequest.addVars("enrollmentPaymentEachMonth");
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
			if(!Objects.equals(paymentECheck, original.getPaymentECheck()))
				apiRequest.addVars("paymentECheck");
			if(!Objects.equals(paymentSystem, original.getPaymentSystem()))
				apiRequest.addVars("paymentSystem");
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
			if(!Objects.equals(chargeAmountDue, original.getChargeAmountDue()))
				apiRequest.addVars("chargeAmountDue");
			if(!Objects.equals(chargeAmountFuture, original.getChargeAmountFuture()))
				apiRequest.addVars("chargeAmountFuture");
			if(!Objects.equals(paymentShortName, original.getPaymentShortName()))
				apiRequest.addVars("paymentShortName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentKey, childCompleteNamePreferred, childBirthDate, momCompleteNamePreferred, dadCompleteNamePreferred, enrollmentPaymentEachMonth, paymentDescription, paymentDate, paymentAmount, paymentCash, paymentCheck, paymentECheck, paymentSystem, paymentBy, transactionId, customerProfileId, transactionStatus, paymentRecieved, chargeAmount, chargeFirstLast, chargeEnrollment, chargeMonth, chargeLateFee, chargeAmountDue, chargeAmountFuture, paymentShortName);
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
				&& Objects.equals( enrollmentKey, that.enrollmentKey )
				&& Objects.equals( childCompleteNamePreferred, that.childCompleteNamePreferred )
				&& Objects.equals( childBirthDate, that.childBirthDate )
				&& Objects.equals( momCompleteNamePreferred, that.momCompleteNamePreferred )
				&& Objects.equals( dadCompleteNamePreferred, that.dadCompleteNamePreferred )
				&& Objects.equals( enrollmentPaymentEachMonth, that.enrollmentPaymentEachMonth )
				&& Objects.equals( paymentDescription, that.paymentDescription )
				&& Objects.equals( paymentDate, that.paymentDate )
				&& Objects.equals( paymentAmount, that.paymentAmount )
				&& Objects.equals( paymentCash, that.paymentCash )
				&& Objects.equals( paymentCheck, that.paymentCheck )
				&& Objects.equals( paymentECheck, that.paymentECheck )
				&& Objects.equals( paymentSystem, that.paymentSystem )
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
				&& Objects.equals( chargeAmountDue, that.chargeAmountDue )
				&& Objects.equals( chargeAmountFuture, that.chargeAmountFuture )
				&& Objects.equals( paymentShortName, that.paymentShortName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolPayment { ");
		sb.append( "enrollmentKey: " ).append(enrollmentKey);
		sb.append( ", childCompleteNamePreferred: \"" ).append(childCompleteNamePreferred).append( "\"" );
		sb.append( ", childBirthDate: " ).append(childBirthDate);
		sb.append( ", momCompleteNamePreferred: \"" ).append(momCompleteNamePreferred).append( "\"" );
		sb.append( ", dadCompleteNamePreferred: \"" ).append(dadCompleteNamePreferred).append( "\"" );
		sb.append( ", enrollmentPaymentEachMonth: " ).append(enrollmentPaymentEachMonth);
		sb.append( ", paymentDescription: \"" ).append(paymentDescription).append( "\"" );
		sb.append( ", paymentDate: " ).append(paymentDate);
		sb.append( ", paymentAmount: " ).append(paymentAmount);
		sb.append( ", paymentCash: " ).append(paymentCash);
		sb.append( ", paymentCheck: " ).append(paymentCheck);
		sb.append( ", paymentECheck: " ).append(paymentECheck);
		sb.append( ", paymentSystem: " ).append(paymentSystem);
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
		sb.append( ", chargeAmountDue: " ).append(chargeAmountDue);
		sb.append( ", chargeAmountFuture: " ).append(chargeAmountFuture);
		sb.append( ", paymentShortName: \"" ).append(paymentShortName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
