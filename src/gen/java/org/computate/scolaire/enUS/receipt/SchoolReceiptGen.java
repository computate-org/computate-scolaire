package org.computate.scolaire.enUS.receipt;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
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
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolReceiptGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolReceipt.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolReceipt_AName = "a receipt";
	public static final String SchoolReceipt_This = "this ";
	public static final String SchoolReceipt_ThisName = "this receipt";
	public static final String SchoolReceipt_A = "a ";
	public static final String SchoolReceipt_TheName = "the receipt";
	public static final String SchoolReceipt_NameSingular = "receipt";
	public static final String SchoolReceipt_NamePlural = "receipts";
	public static final String SchoolReceipt_NameActual = "current receipt";
	public static final String SchoolReceipt_AllName = "all the receipts";
	public static final String SchoolReceipt_SearchAllNameBy = "search receipts by ";
	public static final String SchoolReceipt_Title = "receipts";
	public static final String SchoolReceipt_ThePluralName = "the receipts";
	public static final String SchoolReceipt_NoNameFound = "no receipt found";
	public static final String SchoolReceipt_NameVar = "receipt";
	public static final String SchoolReceipt_OfName = "of receipt";
	public static final String SchoolReceipt_ANameAdjective = "a receipt";
	public static final String SchoolReceipt_NameAdjectiveSingular = "receipt";
	public static final String SchoolReceipt_NameAdjectivePlural = "receipts";
	public static final String SchoolReceipt_Color = "light-green";
	public static final String SchoolReceipt_IconGroup = "solid";
	public static final String SchoolReceipt_IconName = "file-invoice-dollar";
	public static final Integer SchoolReceipt_Rows = 100;

	////////////////
	// receiptKey //
	////////////////

	/**	 The entity receiptKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long receiptKey;
	@JsonIgnore
	public Wrap<Long> receiptKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("receiptKey").o(receiptKey);

	/**	<br/> The entity receiptKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:receiptKey">Find the entity receiptKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _receiptKey(Wrap<Long> c);

	public Long getReceiptKey() {
		return receiptKey;
	}

	public void setReceiptKey(Long receiptKey) {
		this.receiptKey = receiptKey;
		this.receiptKeyWrap.alreadyInitialized = true;
	}
	public SchoolReceipt setReceiptKey(String o) {
		if(NumberUtils.isParsable(o))
			this.receiptKey = Long.parseLong(o);
		this.receiptKeyWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	protected SchoolReceipt receiptKeyInit() {
		if(!receiptKeyWrap.alreadyInitialized) {
			_receiptKey(receiptKeyWrap);
			if(receiptKey == null)
				setReceiptKey(receiptKeyWrap.o);
		}
		receiptKeyWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
	}

	public Long solrReceiptKey() {
		return receiptKey;
	}

	public String strReceiptKey() {
		return receiptKey == null ? "" : receiptKey.toString();
	}

	public String jsonReceiptKey() {
		return receiptKey == null ? "" : receiptKey.toString();
	}

	public String nomAffichageReceiptKey() {
		return "key";
	}

	public String htmTooltipReceiptKey() {
		return null;
	}

	public String htmReceiptKey() {
		return receiptKey == null ? "" : StringEscapeUtils.escapeHtml4(strReceiptKey());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
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
	public SchoolReceipt setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	protected SchoolReceipt schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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

	public void inputSchoolKey(String classApiMethodMethod) {
		SchoolReceipt s = (SchoolReceipt)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "school")
					.a("class", "valueObjectSuggest suggestSchoolKey w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setSchoolKey")
					.a("id", classApiMethodMethod, "_schoolKey")
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolReceiptSchoolKey($(this).val() ? searchSchoolFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'receiptKeys:" + pk + "'}", "], $('#listSchoolReceiptSchoolKey_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolReceipt", pk, "SchoolKey ").f().sx(htmSchoolKey()).g("span");
			}
		}
	}

	public void htmSchoolKey(String classApiMethodMethod) {
		SchoolReceipt s = (SchoolReceipt)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolReceiptSchoolKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/school?fq=receiptKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-school ").f().g("i");
								sx("school");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate a school to this receipt");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputSchoolKey(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolReceiptSchoolKey_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), School.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), School.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
											.a("id", classApiMethodMethod, "_schoolKey_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolVals({ receiptKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "schoolKey')); });")
											.f().sx("add a school")
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

	//////////////////
	// schoolSearch //
	//////////////////

	/**	 The entity schoolSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<School> schoolSearch = new SearchList<School>();
	@JsonIgnore
	public Wrap<SearchList<School>> schoolSearchWrap = new Wrap<SearchList<School>>().p(this).c(SearchList.class).var("schoolSearch").o(schoolSearch);

	/**	<br/> The entity schoolSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<School>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSearch">Find the entity schoolSearch in Solr</a>
	 * <br/>
	 * @param schoolSearch is the entity already constructed. 
	 **/
	protected abstract void _schoolSearch(SearchList<School> l);

	public SearchList<School> getSchoolSearch() {
		return schoolSearch;
	}

	public void setSchoolSearch(SearchList<School> schoolSearch) {
		this.schoolSearch = schoolSearch;
		this.schoolSearchWrap.alreadyInitialized = true;
	}
	protected SchoolReceipt schoolSearchInit() {
		if(!schoolSearchWrap.alreadyInitialized) {
			_schoolSearch(schoolSearch);
		}
		schoolSearch.initDeepForClass(siteRequest_);
		schoolSearchWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
	}

	/////////////
	// school_ //
	/////////////

	/**	 The entity school_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected School school_;
	@JsonIgnore
	public Wrap<School> school_Wrap = new Wrap<School>().p(this).c(School.class).var("school_").o(school_);

	/**	<br/> The entity school_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school_">Find the entity school_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _school_(Wrap<School> c);

	public School getSchool_() {
		return school_;
	}

	public void setSchool_(School school_) {
		this.school_ = school_;
		this.school_Wrap.alreadyInitialized = true;
	}
	protected SchoolReceipt school_Init() {
		if(!school_Wrap.alreadyInitialized) {
			_school_(school_Wrap);
			if(school_ == null)
				setSchool_(school_Wrap.o);
		}
		school_Wrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
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
	protected SchoolReceipt schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
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
	protected SchoolReceipt schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDate">Find the entity paymentDate in Solr</a>
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
	public SchoolReceipt setPaymentDate(Instant o) {
		this.paymentDate = o == null ? null : LocalDate.from(o);
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolReceipt setPaymentDate(String o) {
		this.paymentDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	public SchoolReceipt setPaymentDate(Date o) {
		this.paymentDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.paymentDateWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	protected SchoolReceipt paymentDateInit() {
		if(!paymentDateWrap.alreadyInitialized) {
			_paymentDate(paymentDateWrap);
			if(paymentDate == null)
				setPaymentDate(paymentDateWrap.o);
		}
		paymentDateWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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
		SchoolReceipt s = (SchoolReceipt)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setPaymentDate classSchoolReceipt inputSchoolReceipt", pk, "PaymentDate w3-input w3-border ")
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
				) {
				e("span").a("class", "varSchoolReceipt", pk, "PaymentDate ").f().sx(htmPaymentDate()).g("span");
			}
		}
	}

	public void htmPaymentDate(String classApiMethodMethod) {
		SchoolReceipt s = (SchoolReceipt)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolReceiptPaymentDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentDate')); $('#", classApiMethodMethod, "_paymentDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolReceiptForm :input[name=pk]').val() }], 'setPaymentDate', null, function() { addGlow($('#", classApiMethodMethod, "_paymentDate')); }, function() { addError($('#", classApiMethodMethod, "_paymentDate')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentYear">Find the entity paymentYear in Solr</a>
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
	public SchoolReceipt setPaymentYear(String o) {
		if(NumberUtils.isParsable(o))
			this.paymentYear = Integer.parseInt(o);
		this.paymentYearWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	protected SchoolReceipt paymentYearInit() {
		if(!paymentYearWrap.alreadyInitialized) {
			_paymentYear(paymentYearWrap);
			if(paymentYear == null)
				setPaymentYear(paymentYearWrap.o);
		}
		paymentYearWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
	}

	public Integer solrPaymentYear() {
		return paymentYear;
	}

	public String strPaymentYear() {
		return paymentYear == null ? "" : paymentYear.toString();
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentAmount">Find the entity paymentAmount in Solr</a>
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
	public SchoolReceipt setPaymentAmount(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	public SchoolReceipt setPaymentAmount(Double o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	public SchoolReceipt setPaymentAmount(Integer o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING);
		this.paymentAmountWrap.alreadyInitialized = true;
		return (SchoolReceipt)this;
	}
	protected SchoolReceipt paymentAmountInit() {
		if(!paymentAmountWrap.alreadyInitialized) {
			_paymentAmount(paymentAmountWrap);
			if(paymentAmount == null)
				setPaymentAmount(paymentAmountWrap.o);
		}
		paymentAmountWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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
		SchoolReceipt s = (SchoolReceipt)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "payment amount")
				.a("id", classApiMethodMethod, "_paymentAmount");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPaymentAmount classSchoolReceipt inputSchoolReceipt", pk, "PaymentAmount w3-input w3-border ");
					a("name", "setPaymentAmount");
				} else {
					a("class", "valuePaymentAmount w3-input w3-border classSchoolReceipt inputSchoolReceipt", pk, "PaymentAmount w3-input w3-border ");
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
				) {
				e("span").a("class", "varSchoolReceipt", pk, "PaymentAmount ").f().sx(htmPaymentAmount()).g("span");
			}
		}
	}

	public void htmPaymentAmount(String classApiMethodMethod) {
		SchoolReceipt s = (SchoolReceipt)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolReceiptPaymentAmount").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentAmount')); $('#", classApiMethodMethod, "_paymentAmount').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolReceiptForm :input[name=pk]').val() }], 'setPaymentAmount', null, function() { addGlow($('#", classApiMethodMethod, "_paymentAmount')); }, function() { addError($('#", classApiMethodMethod, "_paymentAmount')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDescription">Find the entity paymentDescription in Solr</a>
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
	protected SchoolReceipt paymentDescriptionInit() {
		if(!paymentDescriptionWrap.alreadyInitialized) {
			_paymentDescription(paymentDescriptionWrap);
			if(paymentDescription == null)
				setPaymentDescription(paymentDescriptionWrap.o);
		}
		paymentDescriptionWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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
		SchoolReceipt s = (SchoolReceipt)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "description")
				.a("id", classApiMethodMethod, "_paymentDescription");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPaymentDescription classSchoolReceipt inputSchoolReceipt", pk, "PaymentDescription w3-input w3-border ");
					a("name", "setPaymentDescription");
				} else {
					a("class", "valuePaymentDescription w3-input w3-border classSchoolReceipt inputSchoolReceipt", pk, "PaymentDescription w3-input w3-border ");
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
				) {
				e("span").a("class", "varSchoolReceipt", pk, "PaymentDescription ").f().sx(htmPaymentDescription()).g("span");
			}
		}
	}

	public void htmPaymentDescription(String classApiMethodMethod) {
		SchoolReceipt s = (SchoolReceipt)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolReceiptPaymentDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentDescription')); $('#", classApiMethodMethod, "_paymentDescription').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolReceiptForm :input[name=pk]').val() }], 'setPaymentDescription', null, function() { addGlow($('#", classApiMethodMethod, "_paymentDescription')); }, function() { addError($('#", classApiMethodMethod, "_paymentDescription')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentShortName">Find the entity paymentShortName in Solr</a>
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
	protected SchoolReceipt paymentShortNameInit() {
		if(!paymentShortNameWrap.alreadyInitialized) {
			_paymentShortName(paymentShortNameWrap);
			if(paymentShortName == null)
				setPaymentShortName(paymentShortNameWrap.o);
		}
		paymentShortNameWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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
		SchoolReceipt s = (SchoolReceipt)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "name")
				.a("id", classApiMethodMethod, "_paymentShortName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPaymentShortName classSchoolReceipt inputSchoolReceipt", pk, "PaymentShortName w3-input w3-border ");
					a("name", "setPaymentShortName");
				} else {
					a("class", "valuePaymentShortName w3-input w3-border classSchoolReceipt inputSchoolReceipt", pk, "PaymentShortName w3-input w3-border ");
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
				) {
				e("span").a("class", "varSchoolReceipt", pk, "PaymentShortName ").f().sx(htmPaymentShortName()).g("span");
			}
		}
	}

	public void htmPaymentShortName(String classApiMethodMethod) {
		SchoolReceipt s = (SchoolReceipt)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolReceiptPaymentShortName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_paymentShortName')); $('#", classApiMethodMethod, "_paymentShortName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolReceiptForm :input[name=pk]').val() }], 'setPaymentShortName', null, function() { addGlow($('#", classApiMethodMethod, "_paymentShortName')); }, function() { addError($('#", classApiMethodMethod, "_paymentShortName')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.receipt.SchoolReceipt&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentCompleteName">Find the entity paymentCompleteName in Solr</a>
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
	protected SchoolReceipt paymentCompleteNameInit() {
		if(!paymentCompleteNameWrap.alreadyInitialized) {
			_paymentCompleteName(paymentCompleteNameWrap);
			if(paymentCompleteName == null)
				setPaymentCompleteName(paymentCompleteNameWrap.o);
		}
		paymentCompleteNameWrap.alreadyInitialized(true);
		return (SchoolReceipt)this;
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

	protected boolean alreadyInitializedSchoolReceipt = false;

	public SchoolReceipt initDeepSchoolReceipt(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolReceipt) {
			alreadyInitializedSchoolReceipt = true;
			initDeepSchoolReceipt();
		}
		return (SchoolReceipt)this;
	}

	public void initDeepSchoolReceipt() {
		initSchoolReceipt();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolReceipt() {
		receiptKeyInit();
		schoolKeyInit();
		schoolSearchInit();
		school_Init();
		schoolAddressInit();
		schoolPhoneNumberInit();
		paymentDateInit();
		paymentYearInit();
		paymentAmountInit();
		paymentDescriptionInit();
		paymentShortNameInit();
		paymentCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolReceipt(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolReceipt(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(schoolSearch != null)
			schoolSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolReceipt(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolReceipt(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolReceipt(String var) {
		SchoolReceipt oSchoolReceipt = (SchoolReceipt)this;
		switch(var) {
			case "receiptKey":
				return oSchoolReceipt.receiptKey;
			case "schoolKey":
				return oSchoolReceipt.schoolKey;
			case "schoolSearch":
				return oSchoolReceipt.schoolSearch;
			case "school_":
				return oSchoolReceipt.school_;
			case "schoolAddress":
				return oSchoolReceipt.schoolAddress;
			case "schoolPhoneNumber":
				return oSchoolReceipt.schoolPhoneNumber;
			case "paymentDate":
				return oSchoolReceipt.paymentDate;
			case "paymentYear":
				return oSchoolReceipt.paymentYear;
			case "paymentAmount":
				return oSchoolReceipt.paymentAmount;
			case "paymentDescription":
				return oSchoolReceipt.paymentDescription;
			case "paymentShortName":
				return oSchoolReceipt.paymentShortName;
			case "paymentCompleteName":
				return oSchoolReceipt.paymentCompleteName;
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
				o = attributeSchoolReceipt(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolReceipt(String var, Object val) {
		SchoolReceipt oSchoolReceipt = (SchoolReceipt)this;
		switch(var) {
			case "schoolKey":
				if(oSchoolReceipt.getSchoolKey() == null)
					oSchoolReceipt.setSchoolKey((Long)val);
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
					o = defineSchoolReceipt(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolReceipt(String var, String val) {
		switch(var) {
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
			case "paymentDescription":
				if(val != null)
					setPaymentDescription(val);
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
		populateSchoolReceipt(solrDocument);
	}
	public void populateSchoolReceipt(SolrDocument solrDocument) {
		SchoolReceipt oSchoolReceipt = (SchoolReceipt)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("receiptKey")) {
				Long receiptKey = (Long)solrDocument.get("receiptKey_stored_long");
				if(receiptKey != null)
					oSchoolReceipt.setReceiptKey(receiptKey);
			}

			Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
			if(schoolKey != null)
				oSchoolReceipt.setSchoolKey(schoolKey);

			if(saves.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolReceipt.setSchoolAddress(schoolAddress);
			}

			if(saves.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolReceipt.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(saves.contains("paymentDate")) {
				Date paymentDate = (Date)solrDocument.get("paymentDate_stored_date");
				if(paymentDate != null)
					oSchoolReceipt.setPaymentDate(paymentDate);
			}

			if(saves.contains("paymentYear")) {
				Integer paymentYear = (Integer)solrDocument.get("paymentYear_stored_int");
				if(paymentYear != null)
					oSchoolReceipt.setPaymentYear(paymentYear);
			}

			if(saves.contains("paymentAmount")) {
				Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
				if(paymentAmount != null)
					oSchoolReceipt.setPaymentAmount(paymentAmount);
			}

			if(saves.contains("paymentDescription")) {
				String paymentDescription = (String)solrDocument.get("paymentDescription_stored_string");
				if(paymentDescription != null)
					oSchoolReceipt.setPaymentDescription(paymentDescription);
			}

			if(saves.contains("paymentShortName")) {
				String paymentShortName = (String)solrDocument.get("paymentShortName_stored_string");
				if(paymentShortName != null)
					oSchoolReceipt.setPaymentShortName(paymentShortName);
			}

			if(saves.contains("paymentCompleteName")) {
				String paymentCompleteName = (String)solrDocument.get("paymentCompleteName_stored_string");
				if(paymentCompleteName != null)
					oSchoolReceipt.setPaymentCompleteName(paymentCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.receipt.SchoolReceipt"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolReceipt o = new SchoolReceipt();
			o.siteRequestSchoolReceipt(siteRequest);
			o.initDeepSchoolReceipt(siteRequest);
			o.indexSchoolReceipt();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolReceipt();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolReceipt(document);
	}

	public void indexSchoolReceipt(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolReceipt(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolReceipt() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolReceipt(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolReceipt(SolrInputDocument document) {
		if(receiptKey != null) {
			document.addField("receiptKey_indexed_long", receiptKey);
			document.addField("receiptKey_stored_long", receiptKey);
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
		if(paymentDate != null) {
			document.addField("paymentDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paymentDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paymentYear != null) {
			document.addField("paymentYear_indexed_int", paymentYear);
			document.addField("paymentYear_stored_int", paymentYear);
		}
		if(paymentAmount != null) {
			document.addField("paymentAmount_indexed_double", paymentAmount.doubleValue());
			document.addField("paymentAmount_stored_double", paymentAmount.doubleValue());
		}
		if(paymentDescription != null) {
			document.addField("paymentDescription_indexed_string", paymentDescription);
			document.addField("paymentDescription_stored_string", paymentDescription);
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

	public void unindexSchoolReceipt() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolReceipt(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolReceipt(String entityVar) {
		switch(entityVar) {
			case "receiptKey":
				return "receiptKey_indexed_long";
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "schoolAddress":
				return "schoolAddress_indexed_string";
			case "schoolPhoneNumber":
				return "schoolPhoneNumber_indexed_string";
			case "paymentDate":
				return "paymentDate_indexed_date";
			case "paymentYear":
				return "paymentYear_indexed_int";
			case "paymentAmount":
				return "paymentAmount_indexed_double";
			case "paymentDescription":
				return "paymentDescription_indexed_string";
			case "paymentShortName":
				return "paymentShortName_indexed_string";
			case "paymentCompleteName":
				return "paymentCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolReceipt(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSchoolReceipt(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolReceipt(solrDocument);
	}
	public void storeSchoolReceipt(SolrDocument solrDocument) {
		SchoolReceipt oSchoolReceipt = (SchoolReceipt)this;

		Long receiptKey = (Long)solrDocument.get("receiptKey_stored_long");
		if(receiptKey != null)
			oSchoolReceipt.setReceiptKey(receiptKey);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolReceipt.setSchoolKey(schoolKey);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchoolReceipt.setSchoolAddress(schoolAddress);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchoolReceipt.setSchoolPhoneNumber(schoolPhoneNumber);

		Date paymentDate = (Date)solrDocument.get("paymentDate_stored_date");
		if(paymentDate != null)
			oSchoolReceipt.setPaymentDate(paymentDate);

		Integer paymentYear = (Integer)solrDocument.get("paymentYear_stored_int");
		if(paymentYear != null)
			oSchoolReceipt.setPaymentYear(paymentYear);

		Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
		if(paymentAmount != null)
			oSchoolReceipt.setPaymentAmount(paymentAmount);

		String paymentDescription = (String)solrDocument.get("paymentDescription_stored_string");
		if(paymentDescription != null)
			oSchoolReceipt.setPaymentDescription(paymentDescription);

		String paymentShortName = (String)solrDocument.get("paymentShortName_stored_string");
		if(paymentShortName != null)
			oSchoolReceipt.setPaymentShortName(paymentShortName);

		String paymentCompleteName = (String)solrDocument.get("paymentCompleteName_stored_string");
		if(paymentCompleteName != null)
			oSchoolReceipt.setPaymentCompleteName(paymentCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolReceipt() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolReceipt) {
			SchoolReceipt original = (SchoolReceipt)o;
			if(!Objects.equals(receiptKey, original.getReceiptKey()))
				apiRequest.addVars("receiptKey");
			if(!Objects.equals(schoolKey, original.getSchoolKey()))
				apiRequest.addVars("schoolKey");
			if(!Objects.equals(schoolAddress, original.getSchoolAddress()))
				apiRequest.addVars("schoolAddress");
			if(!Objects.equals(schoolPhoneNumber, original.getSchoolPhoneNumber()))
				apiRequest.addVars("schoolPhoneNumber");
			if(!Objects.equals(paymentDate, original.getPaymentDate()))
				apiRequest.addVars("paymentDate");
			if(!Objects.equals(paymentYear, original.getPaymentYear()))
				apiRequest.addVars("paymentYear");
			if(!Objects.equals(paymentAmount, original.getPaymentAmount()))
				apiRequest.addVars("paymentAmount");
			if(!Objects.equals(paymentDescription, original.getPaymentDescription()))
				apiRequest.addVars("paymentDescription");
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
		return Objects.hash(super.hashCode(), receiptKey, schoolKey, schoolAddress, schoolPhoneNumber, paymentDate, paymentYear, paymentAmount, paymentDescription, paymentShortName, paymentCompleteName);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolReceipt))
			return false;
		SchoolReceipt that = (SchoolReceipt)o;
		return super.equals(o)
				&& Objects.equals( receiptKey, that.receiptKey )
				&& Objects.equals( schoolKey, that.schoolKey )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( schoolPhoneNumber, that.schoolPhoneNumber )
				&& Objects.equals( paymentDate, that.paymentDate )
				&& Objects.equals( paymentYear, that.paymentYear )
				&& Objects.equals( paymentAmount, that.paymentAmount )
				&& Objects.equals( paymentDescription, that.paymentDescription )
				&& Objects.equals( paymentShortName, that.paymentShortName )
				&& Objects.equals( paymentCompleteName, that.paymentCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolReceipt { ");
		sb.append( "receiptKey: " ).append(receiptKey);
		sb.append( ", schoolKey: " ).append(schoolKey);
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", schoolPhoneNumber: \"" ).append(schoolPhoneNumber).append( "\"" );
		sb.append( ", paymentDate: " ).append(paymentDate);
		sb.append( ", paymentYear: " ).append(paymentYear);
		sb.append( ", paymentAmount: " ).append(paymentAmount);
		sb.append( ", paymentDescription: \"" ).append(paymentDescription).append( "\"" );
		sb.append( ", paymentShortName: \"" ).append(paymentShortName).append( "\"" );
		sb.append( ", paymentCompleteName: \"" ).append(paymentCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
