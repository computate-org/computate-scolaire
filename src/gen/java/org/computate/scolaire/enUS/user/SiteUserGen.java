package org.computate.scolaire.enUS.user;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SiteUserGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SiteUser.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin", "SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SiteUser_AName = "a site user";
	public static final String SiteUser_This = "this ";
	public static final String SiteUser_ThisName = "this site user";
	public static final String SiteUser_A = "a ";
	public static final String SiteUser_TheName = "the site user";
	public static final String SiteUser_NameSingular = "site user";
	public static final String SiteUser_NamePlural = "site users";
	public static final String SiteUser_NameActual = "current site user";
	public static final String SiteUser_AllName = "all the site users";
	public static final String SiteUser_SearchAllNameBy = "search site users by ";
	public static final String SiteUser_Title = "site users";
	public static final String SiteUser_ThePluralName = "the site users";
	public static final String SiteUser_NoNameFound = "no site user found";
	public static final String SiteUser_NameVar = "user";
	public static final String SiteUser_OfName = "of site user";
	public static final String SiteUser_ANameAdjective = "a site user";
	public static final String SiteUser_NameAdjectiveSingular = "site user";
	public static final String SiteUser_NameAdjectivePlural = "site users";
	public static final String SiteUser_Color = "gray";
	public static final String SiteUser_IconGroup = "regular";
	public static final String SiteUser_IconName = "user-cog";

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKeys">Find the entity userKeys in Solr</a>
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
	public SiteUser addUserKeys(Long...objets) {
		for(Long o : objets) {
			addUserKeys(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addUserKeys(Long o) {
		if(o != null && !userKeys.contains(o))
			this.userKeys.add(o);
		return (SiteUser)this;
	}
	public SiteUser setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addUserKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUserKeys(p);
		}
		return (SiteUser)this;
	}
	protected SiteUser userKeysInit() {
		if(!userKeysWrap.alreadyInitialized) {
			_userKeys(userKeys);
		}
		userKeysWrap.alreadyInitialized(true);
		return (SiteUser)this;
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

	////////////////////
	// enrollmentKeys //
	////////////////////

	/**	 The entity enrollmentKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> enrollmentKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/> The entity enrollmentKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Find the entity enrollmentKeys in Solr</a>
	 * <br/>
	 * @param enrollmentKeys is the entity already constructed. 
	 **/
	protected abstract void _enrollmentKeys(List<Long> o);

	public List<Long> getEnrollmentKeys() {
		return enrollmentKeys;
	}

	public void setEnrollmentKeys(List<Long> enrollmentKeys) {
		this.enrollmentKeys = enrollmentKeys;
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public SiteUser addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SiteUser)this;
	}
	public SiteUser setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SiteUser)this;
	}
	protected SiteUser enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SiteUser)this;
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
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "enrollments")
					.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setEnrollmentKeys")
					.a("id", classApiMethodMethod, "_enrollmentKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestSiteUserEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'userKeys:" + pk + "'}", "], $('#listSiteUserEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "EnrollmentKeys ").f().sx(htmEnrollmentKeys()).g("span");
			}
		}
	}

	public void htmEnrollmentKeys(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserEnrollmentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enrollment?fq=userKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("enrollments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate enrollments to this site user");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSiteUserEnrollmentKeys_", classApiMethodMethod).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("id", classApiMethodMethod, "_enrollmentKeys_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ userKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
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

	//////////////////
	// enrollments_ //
	//////////////////

	/**	 The entity enrollments_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollments_;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollments_Wrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollments_").o(enrollments_);

	/**	<br/> The entity enrollments_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments_">Find the entity enrollments_ in Solr</a>
	 * <br/>
	 * @param o is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollments_(Wrap<List<SchoolEnrollment>> o);

	public List<SchoolEnrollment> getEnrollments_() {
		return enrollments_;
	}

	public void setEnrollments_(List<SchoolEnrollment> enrollments_) {
		this.enrollments_ = enrollments_;
		this.enrollments_Wrap.alreadyInitialized = true;
	}
	public SiteUser addEnrollments_(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments_(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addEnrollments_(SchoolEnrollment o) {
		if(o != null && !enrollments_.contains(o))
			this.enrollments_.add(o);
		return (SiteUser)this;
	}
	protected SiteUser enrollments_Init() {
		if(!enrollments_Wrap.alreadyInitialized) {
			_enrollments_(enrollments_Wrap);
			if(enrollments_ == null)
				setEnrollments_(enrollments_Wrap.o);
		}
		enrollments_Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	/////////////////
	// paymentKeys //
	/////////////////

	/**	 The entity paymentKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> paymentKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> paymentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("paymentKeys").o(paymentKeys);

	/**	<br/> The entity paymentKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentKeys">Find the entity paymentKeys in Solr</a>
	 * <br/>
	 * @param paymentKeys is the entity already constructed. 
	 **/
	protected abstract void _paymentKeys(List<Long> o);

	public List<Long> getPaymentKeys() {
		return paymentKeys;
	}

	public void setPaymentKeys(List<Long> paymentKeys) {
		this.paymentKeys = paymentKeys;
		this.paymentKeysWrap.alreadyInitialized = true;
	}
	public SiteUser addPaymentKeys(Long...objets) {
		for(Long o : objets) {
			addPaymentKeys(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addPaymentKeys(Long o) {
		if(o != null && !paymentKeys.contains(o))
			this.paymentKeys.add(o);
		return (SiteUser)this;
	}
	public SiteUser setPaymentKeys(JsonArray objets) {
		paymentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaymentKeys(o);
		}
		return (SiteUser)this;
	}
	public SiteUser addPaymentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPaymentKeys(p);
		}
		return (SiteUser)this;
	}
	protected SiteUser paymentKeysInit() {
		if(!paymentKeysWrap.alreadyInitialized) {
			_paymentKeys(paymentKeys);
		}
		paymentKeysWrap.alreadyInitialized(true);
		return (SiteUser)this;
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

	public void inputPaymentKeys(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "payments")
					.a("class", "valueObjectSuggest suggestPaymentKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setPaymentKeys")
					.a("id", classApiMethodMethod, "_paymentKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestSiteUserPaymentKeys($(this).val() ? searchSchoolPaymentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'userKeys:" + pk + "'}", "], $('#listSiteUserPaymentKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "PaymentKeys ").f().sx(htmPaymentKeys()).g("span");
			}
		}
	}

	public void htmPaymentKeys(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserPaymentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/payment?fq=userKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fas fa-search-dollar ").f().g("i");
								sx("payments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate payments to this site user");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputPaymentKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSiteUserPaymentKeys_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolPayment.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolPayment.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
											.a("id", classApiMethodMethod, "_paymentKeys_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolPaymentVals({ userKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "paymentKeys')); });")
											.f().sx("add a payment")
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

	//////////////
	// userName //
	//////////////

	/**	 The entity userName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String userName;
	@JsonIgnore
	public Wrap<String> userNameWrap = new Wrap<String>().p(this).c(String.class).var("userName").o(userName);

	/**	<br/> The entity userName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userName">Find the entity userName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonUserName() {
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

	public void inputUserName(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_userName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setUserName classSiteUser inputSiteUser", pk, "UserName w3-input w3-border ");
					a("name", "setUserName");
				} else {
					a("class", "valueUserName w3-input w3-border classSiteUser inputSiteUser", pk, "UserName w3-input w3-border ");
					a("name", "userName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setUserName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_userName')); }, function() { addError($('#", classApiMethodMethod, "_userName')); }); ");
				}
				a("value", strUserName())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "UserName ").f().sx(htmUserName()).g("span");
			}
		}
	}

	public void htmUserName(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserUserName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputUserName(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_userName')); $('#", classApiMethodMethod, "_userName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setUserName', null, function() { addGlow($('#", classApiMethodMethod, "_userName')); }, function() { addError($('#", classApiMethodMethod, "_userName')); }); ")
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

	///////////////
	// userEmail //
	///////////////

	/**	 The entity userEmail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String userEmail;
	@JsonIgnore
	public Wrap<String> userEmailWrap = new Wrap<String>().p(this).c(String.class).var("userEmail").o(userEmail);

	/**	<br/> The entity userEmail
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userEmail">Find the entity userEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonUserEmail() {
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

	public void inputUserEmail(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_userEmail");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setUserEmail classSiteUser inputSiteUser", pk, "UserEmail w3-input w3-border ");
					a("name", "setUserEmail");
				} else {
					a("class", "valueUserEmail w3-input w3-border classSiteUser inputSiteUser", pk, "UserEmail w3-input w3-border ");
					a("name", "userEmail");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setUserEmail', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_userEmail')); }, function() { addError($('#", classApiMethodMethod, "_userEmail')); }); ");
				}
				a("value", strUserEmail())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "UserEmail ").f().sx(htmUserEmail()).g("span");
			}
		}
	}

	public void htmUserEmail(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserUserEmail").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputUserEmail(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_userEmail')); $('#", classApiMethodMethod, "_userEmail').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setUserEmail', null, function() { addGlow($('#", classApiMethodMethod, "_userEmail')); }, function() { addError($('#", classApiMethodMethod, "_userEmail')); }); ")
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
	// userFirstName //
	///////////////////

	/**	 The entity userFirstName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String userFirstName;
	@JsonIgnore
	public Wrap<String> userFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("userFirstName").o(userFirstName);

	/**	<br/> The entity userFirstName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userFirstName">Find the entity userFirstName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonUserFirstName() {
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

	//////////////////
	// userLastName //
	//////////////////

	/**	 The entity userLastName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String userLastName;
	@JsonIgnore
	public Wrap<String> userLastNameWrap = new Wrap<String>().p(this).c(String.class).var("userLastName").o(userLastName);

	/**	<br/> The entity userLastName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userLastName">Find the entity userLastName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonUserLastName() {
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

	//////////////////
	// userFullName //
	//////////////////

	/**	 The entity userFullName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String userFullName;
	@JsonIgnore
	public Wrap<String> userFullNameWrap = new Wrap<String>().p(this).c(String.class).var("userFullName").o(userFullName);

	/**	<br/> The entity userFullName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userFullName">Find the entity userFullName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonUserFullName() {
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

	//////////////
	// userSite //
	//////////////

	/**	 The entity userSite
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String userSite;
	@JsonIgnore
	public Wrap<String> userSiteWrap = new Wrap<String>().p(this).c(String.class).var("userSite").o(userSite);

	/**	<br/> The entity userSite
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userSite">Find the entity userSite in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonUserSite() {
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

	////////////////////////
	// customerProfileId1 //
	////////////////////////

	/**	 The entity customerProfileId1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId1;
	@JsonIgnore
	public Wrap<String> customerProfileId1Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId1").o(customerProfileId1);

	/**	<br/> The entity customerProfileId1
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId1">Find the entity customerProfileId1 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId1(Wrap<String> c);

	public String getCustomerProfileId1() {
		return customerProfileId1;
	}

	public void setCustomerProfileId1(String customerProfileId1) {
		this.customerProfileId1 = customerProfileId1;
		this.customerProfileId1Wrap.alreadyInitialized = true;
	}
	protected SiteUser customerProfileId1Init() {
		if(!customerProfileId1Wrap.alreadyInitialized) {
			_customerProfileId1(customerProfileId1Wrap);
			if(customerProfileId1 == null)
				setCustomerProfileId1(customerProfileId1Wrap.o);
		}
		customerProfileId1Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrCustomerProfileId1() {
		return customerProfileId1;
	}

	public String strCustomerProfileId1() {
		return customerProfileId1 == null ? "" : customerProfileId1;
	}

	public String jsonCustomerProfileId1() {
		return customerProfileId1 == null ? "" : customerProfileId1;
	}

	public String nomAffichageCustomerProfileId1() {
		return "customer profile ID 1";
	}

	public String htmTooltipCustomerProfileId1() {
		return null;
	}

	public String htmCustomerProfileId1() {
		return customerProfileId1 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId1());
	}

	public void inputCustomerProfileId1(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID 1")
				.a("id", classApiMethodMethod, "_customerProfileId1");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId1 classSiteUser inputSiteUser", pk, "CustomerProfileId1 w3-input w3-border ");
					a("name", "setCustomerProfileId1");
				} else {
					a("class", "valueCustomerProfileId1 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId1 w3-input w3-border ");
					a("name", "customerProfileId1");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId1', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId1')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId1')); }); ");
				}
				a("value", strCustomerProfileId1())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId1 ").f().sx(htmCustomerProfileId1()).g("span");
			}
		}
	}

	public void htmCustomerProfileId1(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classApiMethodMethod, "_customerProfileId1").a("class", "").f().sx("customer profile ID 1").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId1(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId1')); $('#", classApiMethodMethod, "_customerProfileId1').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId1', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId1')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId1')); }); ")
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
	// customerProfileId2 //
	////////////////////////

	/**	 The entity customerProfileId2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId2;
	@JsonIgnore
	public Wrap<String> customerProfileId2Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId2").o(customerProfileId2);

	/**	<br/> The entity customerProfileId2
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId2">Find the entity customerProfileId2 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId2(Wrap<String> c);

	public String getCustomerProfileId2() {
		return customerProfileId2;
	}

	public void setCustomerProfileId2(String customerProfileId2) {
		this.customerProfileId2 = customerProfileId2;
		this.customerProfileId2Wrap.alreadyInitialized = true;
	}
	protected SiteUser customerProfileId2Init() {
		if(!customerProfileId2Wrap.alreadyInitialized) {
			_customerProfileId2(customerProfileId2Wrap);
			if(customerProfileId2 == null)
				setCustomerProfileId2(customerProfileId2Wrap.o);
		}
		customerProfileId2Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public String solrCustomerProfileId2() {
		return customerProfileId2;
	}

	public String strCustomerProfileId2() {
		return customerProfileId2 == null ? "" : customerProfileId2;
	}

	public String jsonCustomerProfileId2() {
		return customerProfileId2 == null ? "" : customerProfileId2;
	}

	public String nomAffichageCustomerProfileId2() {
		return "customer profile ID 2";
	}

	public String htmTooltipCustomerProfileId2() {
		return null;
	}

	public String htmCustomerProfileId2() {
		return customerProfileId2 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId2());
	}

	public void inputCustomerProfileId2(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID 2")
				.a("id", classApiMethodMethod, "_customerProfileId2");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId2 classSiteUser inputSiteUser", pk, "CustomerProfileId2 w3-input w3-border ");
					a("name", "setCustomerProfileId2");
				} else {
					a("class", "valueCustomerProfileId2 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId2 w3-input w3-border ");
					a("name", "customerProfileId2");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId2', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId2')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId2')); }); ");
				}
				a("value", strCustomerProfileId2())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId2 ").f().sx(htmCustomerProfileId2()).g("span");
			}
		}
	}

	public void htmCustomerProfileId2(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classApiMethodMethod, "_customerProfileId2").a("class", "").f().sx("customer profile ID 2").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId2(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId2')); $('#", classApiMethodMethod, "_customerProfileId2').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId2', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId2')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId2')); }); ")
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
	// userReceiveEmails //
	///////////////////////

	/**	 The entity userReceiveEmails
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean userReceiveEmails;
	@JsonIgnore
	public Wrap<Boolean> userReceiveEmailsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("userReceiveEmails").o(userReceiveEmails);

	/**	<br/> The entity userReceiveEmails
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userReceiveEmails">Find the entity userReceiveEmails in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonUserReceiveEmails() {
		return userReceiveEmails == null ? "" : userReceiveEmails.toString();
	}

	public String nomAffichageUserReceiveEmails() {
		return "receive email";
	}

	public String htmTooltipUserReceiveEmails() {
		return null;
	}

	public String htmUserReceiveEmails() {
		return userReceiveEmails == null ? "" : StringEscapeUtils.escapeHtml4(strUserReceiveEmails());
	}

	public void inputUserReceiveEmails(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_userReceiveEmails")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_userReceiveEmails");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setUserReceiveEmails classSiteUser inputSiteUser", pk, "UserReceiveEmails w3-input w3-border ");
				a("name", "setUserReceiveEmails");
			} else {
				a("class", "valueUserReceiveEmails classSiteUser inputSiteUser", pk, "UserReceiveEmails w3-input w3-border ");
				a("name", "userReceiveEmails");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setUserReceiveEmails', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_userReceiveEmails')); }, function() { addError($('#", classApiMethodMethod, "_userReceiveEmails')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getUserReceiveEmails() != null && getUserReceiveEmails())
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
					) {
				e("span").a("class", "varSiteUser", pk, "UserReceiveEmails ").f().sx(htmUserReceiveEmails()).g("span");
			}
		}
	}

	public void htmUserReceiveEmails(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserUserReceiveEmails").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classApiMethodMethod, "_userReceiveEmails").a("class", "").f().sx("receive email").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputUserReceiveEmails(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// seeArchived //
	/////////////////

	/**	 The entity seeArchived
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean seeArchived;
	@JsonIgnore
	public Wrap<Boolean> seeArchivedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seeArchived").o(seeArchived);

	/**	<br/> The entity seeArchived
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seeArchived">Find the entity seeArchived in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonSeeArchived() {
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

	public void inputSeeArchived(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_seeArchived")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_seeArchived");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeeArchived classSiteUser inputSiteUser", pk, "SeeArchived w3-input w3-border ");
				a("name", "setSeeArchived");
			} else {
				a("class", "valueSeeArchived classSiteUser inputSiteUser", pk, "SeeArchived w3-input w3-border ");
				a("name", "seeArchived");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeeArchived', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seeArchived')); }, function() { addError($('#", classApiMethodMethod, "_seeArchived')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getSeeArchived() != null && getSeeArchived())
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
					) {
				e("span").a("class", "varSiteUser", pk, "SeeArchived ").f().sx(htmSeeArchived()).g("span");
			}
		}
	}

	public void htmSeeArchived(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserSeeArchived").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classApiMethodMethod, "_seeArchived").a("class", "").f().sx("see archived").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSeeArchived(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// seeDeleted //
	////////////////

	/**	 The entity seeDeleted
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean seeDeleted;
	@JsonIgnore
	public Wrap<Boolean> seeDeletedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seeDeleted").o(seeDeleted);

	/**	<br/> The entity seeDeleted
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seeDeleted">Find the entity seeDeleted in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	public String jsonSeeDeleted() {
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

	public void inputSeeDeleted(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_seeDeleted")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_seeDeleted");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeeDeleted classSiteUser inputSiteUser", pk, "SeeDeleted w3-input w3-border ");
				a("name", "setSeeDeleted");
			} else {
				a("class", "valueSeeDeleted classSiteUser inputSiteUser", pk, "SeeDeleted w3-input w3-border ");
				a("name", "seeDeleted");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeeDeleted', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seeDeleted')); }, function() { addError($('#", classApiMethodMethod, "_seeDeleted')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getSeeDeleted() != null && getSeeDeleted())
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
					) {
				e("span").a("class", "varSiteUser", pk, "SeeDeleted ").f().sx(htmSeeDeleted()).g("span");
			}
		}
	}

	public void htmSeeDeleted(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserSeeDeleted").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classApiMethodMethod, "_seeDeleted").a("class", "").f().sx("see deleted").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSeeDeleted(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
		initSiteUser();
		super.initDeepCluster(siteRequest_);
	}

	public void initSiteUser() {
		userKeysInit();
		enrollmentKeysInit();
		enrollments_Init();
		paymentKeysInit();
		userNameInit();
		userEmailInit();
		userFirstNameInit();
		userLastNameInit();
		userFullNameInit();
		userSiteInit();
		customerProfileId1Init();
		customerProfileId2Init();
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
			case "userKeys":
				return oSiteUser.userKeys;
			case "enrollmentKeys":
				return oSiteUser.enrollmentKeys;
			case "enrollments_":
				return oSiteUser.enrollments_;
			case "paymentKeys":
				return oSiteUser.paymentKeys;
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
			case "customerProfileId1":
				return oSiteUser.customerProfileId1;
			case "customerProfileId2":
				return oSiteUser.customerProfileId2;
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
			case "enrollmentKeys":
				oSiteUser.addEnrollmentKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "paymentKeys":
				oSiteUser.addPaymentKeys((Long)val);
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
			case "userName":
				if(val != null)
					setUserName(val);
				saves.add(var);
				return val;
			case "userEmail":
				if(val != null)
					setUserEmail(val);
				saves.add(var);
				return val;
			case "customerProfileId1":
				if(val != null)
					setCustomerProfileId1(val);
				saves.add(var);
				return val;
			case "customerProfileId2":
				if(val != null)
					setCustomerProfileId2(val);
				saves.add(var);
				return val;
			case "userReceiveEmails":
				if(val != null)
					setUserReceiveEmails(val);
				saves.add(var);
				return val;
			case "seeArchived":
				if(val != null)
					setSeeArchived(val);
				saves.add(var);
				return val;
			case "seeDeleted":
				if(val != null)
					setSeeDeleted(val);
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
		populateSiteUser(solrDocument);
	}
	public void populateSiteUser(SolrDocument solrDocument) {
		SiteUser oSiteUser = (SiteUser)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("userKeys")) {
				List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
				if(userKeys != null)
					oSiteUser.userKeys.addAll(userKeys);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSiteUser.enrollmentKeys.addAll(enrollmentKeys);

			List<Long> paymentKeys = (List<Long>)solrDocument.get("paymentKeys_stored_longs");
			if(paymentKeys != null)
				oSiteUser.paymentKeys.addAll(paymentKeys);

			if(saves.contains("userName")) {
				String userName = (String)solrDocument.get("userName_stored_string");
				if(userName != null)
					oSiteUser.setUserName(userName);
			}

			if(saves.contains("userEmail")) {
				String userEmail = (String)solrDocument.get("userEmail_stored_string");
				if(userEmail != null)
					oSiteUser.setUserEmail(userEmail);
			}

			if(saves.contains("userFirstName")) {
				String userFirstName = (String)solrDocument.get("userFirstName_stored_string");
				if(userFirstName != null)
					oSiteUser.setUserFirstName(userFirstName);
			}

			if(saves.contains("userLastName")) {
				String userLastName = (String)solrDocument.get("userLastName_stored_string");
				if(userLastName != null)
					oSiteUser.setUserLastName(userLastName);
			}

			if(saves.contains("userFullName")) {
				String userFullName = (String)solrDocument.get("userFullName_stored_string");
				if(userFullName != null)
					oSiteUser.setUserFullName(userFullName);
			}

			if(saves.contains("userSite")) {
				String userSite = (String)solrDocument.get("userSite_stored_string");
				if(userSite != null)
					oSiteUser.setUserSite(userSite);
			}

			if(saves.contains("customerProfileId1")) {
				String customerProfileId1 = (String)solrDocument.get("customerProfileId1_stored_string");
				if(customerProfileId1 != null)
					oSiteUser.setCustomerProfileId1(customerProfileId1);
			}

			if(saves.contains("customerProfileId2")) {
				String customerProfileId2 = (String)solrDocument.get("customerProfileId2_stored_string");
				if(customerProfileId2 != null)
					oSiteUser.setCustomerProfileId2(customerProfileId2);
			}

			if(saves.contains("userReceiveEmails")) {
				Boolean userReceiveEmails = (Boolean)solrDocument.get("userReceiveEmails_stored_boolean");
				if(userReceiveEmails != null)
					oSiteUser.setUserReceiveEmails(userReceiveEmails);
			}

			if(saves.contains("seeArchived")) {
				Boolean seeArchived = (Boolean)solrDocument.get("seeArchived_stored_boolean");
				if(seeArchived != null)
					oSiteUser.setSeeArchived(seeArchived);
			}

			if(saves.contains("seeDeleted")) {
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
			clientSolr.commit(false, false, true);
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
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSiteUser(SolrInputDocument document) {
		if(userKeys != null) {
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_indexed_longs", o);
			}
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_stored_longs", o);
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
		if(paymentKeys != null) {
			for(java.lang.Long o : paymentKeys) {
				document.addField("paymentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : paymentKeys) {
				document.addField("paymentKeys_stored_longs", o);
			}
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
		if(customerProfileId1 != null) {
			document.addField("customerProfileId1_indexed_string", customerProfileId1);
			document.addField("customerProfileId1_stored_string", customerProfileId1);
		}
		if(customerProfileId2 != null) {
			document.addField("customerProfileId2_indexed_string", customerProfileId2);
			document.addField("customerProfileId2_stored_string", customerProfileId2);
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
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSiteUser(String entityVar) {
		switch(entityVar) {
			case "userKeys":
				return "userKeys_indexed_longs";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "paymentKeys":
				return "paymentKeys_indexed_longs";
			case "userName":
				return "userName_indexed_string";
			case "userEmail":
				return "userEmail_indexed_string";
			case "userFirstName":
				return "userFirstName_indexed_string";
			case "userLastName":
				return "userLastName_indexed_string";
			case "userFullName":
				return "userFullName_indexed_string";
			case "userSite":
				return "userSite_indexed_string";
			case "customerProfileId1":
				return "customerProfileId1_indexed_string";
			case "customerProfileId2":
				return "customerProfileId2_indexed_string";
			case "userReceiveEmails":
				return "userReceiveEmails_indexed_boolean";
			case "seeArchived":
				return "seeArchived_indexed_boolean";
			case "seeDeleted":
				return "seeDeleted_indexed_boolean";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSiteUser(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSiteUser(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
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

		List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
		if(userKeys != null)
			oSiteUser.userKeys.addAll(userKeys);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSiteUser.enrollmentKeys.addAll(enrollmentKeys);

		List<Long> paymentKeys = (List<Long>)solrDocument.get("paymentKeys_stored_longs");
		if(paymentKeys != null)
			oSiteUser.paymentKeys.addAll(paymentKeys);

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

		String customerProfileId1 = (String)solrDocument.get("customerProfileId1_stored_string");
		if(customerProfileId1 != null)
			oSiteUser.setCustomerProfileId1(customerProfileId1);

		String customerProfileId2 = (String)solrDocument.get("customerProfileId2_stored_string");
		if(customerProfileId2 != null)
			oSiteUser.setCustomerProfileId2(customerProfileId2);

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

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSiteUser() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SiteUser) {
			SiteUser original = (SiteUser)o;
			if(!Objects.equals(userKeys, original.getUserKeys()))
				apiRequest.addVars("userKeys");
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(paymentKeys, original.getPaymentKeys()))
				apiRequest.addVars("paymentKeys");
			if(!Objects.equals(userName, original.getUserName()))
				apiRequest.addVars("userName");
			if(!Objects.equals(userEmail, original.getUserEmail()))
				apiRequest.addVars("userEmail");
			if(!Objects.equals(userFirstName, original.getUserFirstName()))
				apiRequest.addVars("userFirstName");
			if(!Objects.equals(userLastName, original.getUserLastName()))
				apiRequest.addVars("userLastName");
			if(!Objects.equals(userFullName, original.getUserFullName()))
				apiRequest.addVars("userFullName");
			if(!Objects.equals(userSite, original.getUserSite()))
				apiRequest.addVars("userSite");
			if(!Objects.equals(customerProfileId1, original.getCustomerProfileId1()))
				apiRequest.addVars("customerProfileId1");
			if(!Objects.equals(customerProfileId2, original.getCustomerProfileId2()))
				apiRequest.addVars("customerProfileId2");
			if(!Objects.equals(userReceiveEmails, original.getUserReceiveEmails()))
				apiRequest.addVars("userReceiveEmails");
			if(!Objects.equals(seeArchived, original.getSeeArchived()))
				apiRequest.addVars("seeArchived");
			if(!Objects.equals(seeDeleted, original.getSeeDeleted()))
				apiRequest.addVars("seeDeleted");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), userKeys, enrollmentKeys, paymentKeys, userName, userEmail, userFirstName, userLastName, userFullName, userSite, customerProfileId1, customerProfileId2, userReceiveEmails, seeArchived, seeDeleted);
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
		return super.equals(o)
				&& Objects.equals( userKeys, that.userKeys )
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( paymentKeys, that.paymentKeys )
				&& Objects.equals( userName, that.userName )
				&& Objects.equals( userEmail, that.userEmail )
				&& Objects.equals( userFirstName, that.userFirstName )
				&& Objects.equals( userLastName, that.userLastName )
				&& Objects.equals( userFullName, that.userFullName )
				&& Objects.equals( userSite, that.userSite )
				&& Objects.equals( customerProfileId1, that.customerProfileId1 )
				&& Objects.equals( customerProfileId2, that.customerProfileId2 )
				&& Objects.equals( userReceiveEmails, that.userReceiveEmails )
				&& Objects.equals( seeArchived, that.seeArchived )
				&& Objects.equals( seeDeleted, that.seeDeleted );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SiteUser { ");
		sb.append( "userKeys: " ).append(userKeys);
		sb.append( ", enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", paymentKeys: " ).append(paymentKeys);
		sb.append( ", userName: \"" ).append(userName).append( "\"" );
		sb.append( ", userEmail: \"" ).append(userEmail).append( "\"" );
		sb.append( ", userFirstName: \"" ).append(userFirstName).append( "\"" );
		sb.append( ", userLastName: \"" ).append(userLastName).append( "\"" );
		sb.append( ", userFullName: \"" ).append(userFullName).append( "\"" );
		sb.append( ", userSite: \"" ).append(userSite).append( "\"" );
		sb.append( ", customerProfileId1: \"" ).append(customerProfileId1).append( "\"" );
		sb.append( ", customerProfileId2: \"" ).append(customerProfileId2).append( "\"" );
		sb.append( ", userReceiveEmails: " ).append(userReceiveEmails);
		sb.append( ", seeArchived: " ).append(seeArchived);
		sb.append( ", seeDeleted: " ).append(seeDeleted);
		sb.append(" }");
		return sb.toString();
	}
}
