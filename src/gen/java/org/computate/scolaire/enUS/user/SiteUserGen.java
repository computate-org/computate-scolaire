package org.computate.scolaire.enUS.user;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
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
	public void setUserKeys(String o) {
		Long l = SiteUser.staticSetUserKeys(siteRequest_, o);
		if(l != null)
			addUserKeys(l);
		this.userKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetUserKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
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

	public static Long staticSolrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserKeys(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserKeys(siteRequest_, SiteUser.staticSolrUserKeys(siteRequest_, SiteUser.staticSetUserKeys(siteRequest_, o)));
	}

	public List<Long> solrUserKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : userKeys) {
			l.add(SiteUser.staticSolrUserKeys(siteRequest_, o));
		}
		return l;
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
	public void setEnrollmentKeys(String o) {
		Long l = SiteUser.staticSetEnrollmentKeys(siteRequest_, o);
		if(l != null)
			addEnrollmentKeys(l);
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
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

	public static Long staticSolrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrEnrollmentKeys(siteRequest_, SiteUser.staticSolrEnrollmentKeys(siteRequest_, SiteUser.staticSetEnrollmentKeys(siteRequest_, o)));
	}

	public List<Long> solrEnrollmentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : enrollmentKeys) {
			l.add(SiteUser.staticSolrEnrollmentKeys(siteRequest_, o));
		}
		return l;
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
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_enrollmentKeys_clear")
						.a("class", "enrollmentKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_enrollmentKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "enrollments")
				.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEnrollmentKeys")
				.a("id", classApiMethodMethod, "_enrollmentKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSiteUserEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'userKeys:" + pk + "'}", "], $('#listSiteUserEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

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
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classApiMethodMethod, "_enrollmentKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ userKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
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
	public static SchoolEnrollment staticSetEnrollments_(SiteRequestEnUS siteRequest_, String o) {
		return null;
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
	public void setPaymentKeys(String o) {
		Long l = SiteUser.staticSetPaymentKeys(siteRequest_, o);
		if(l != null)
			addPaymentKeys(l);
		this.paymentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetPaymentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setPaymentKeys(JsonArray objets) {
		paymentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaymentKeys(o);
		}
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

	public static Long staticSolrPaymentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrPaymentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrPaymentKeys(siteRequest_, SiteUser.staticSolrPaymentKeys(siteRequest_, SiteUser.staticSetPaymentKeys(siteRequest_, o)));
	}

	public List<Long> solrPaymentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : paymentKeys) {
			l.add(SiteUser.staticSolrPaymentKeys(siteRequest_, o));
		}
		return l;
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
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_paymentKeys_clear")
						.a("class", "paymentKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_paymentKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "payments")
				.a("class", "valueObjectSuggest suggestPaymentKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPaymentKeys")
				.a("id", classApiMethodMethod, "_paymentKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSiteUserPaymentKeys($(this).val() ? searchSchoolPaymentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'userKeys:" + pk + "'}", "], $('#listSiteUserPaymentKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

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
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
												.a("id", classApiMethodMethod, "_paymentKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolPaymentVals({ userKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "paymentKeys')); });")
												.f().sx("add a payment")
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
	public void setUserName(String o) {
		this.userName = SiteUser.staticSetUserName(siteRequest_, o);
		this.userNameWrap.alreadyInitialized = true;
	}
	public static String staticSetUserName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrUserName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrUserName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserName(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserName(siteRequest_, SiteUser.staticSolrUserName(siteRequest_, SiteUser.staticSetUserName(siteRequest_, o)));
	}

	public String solrUserName() {
		return SiteUser.staticSolrUserName(siteRequest_, userName);
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
	public void setUserEmail(String o) {
		this.userEmail = SiteUser.staticSetUserEmail(siteRequest_, o);
		this.userEmailWrap.alreadyInitialized = true;
	}
	public static String staticSetUserEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrUserEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrUserEmail(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserEmail(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserEmail(siteRequest_, SiteUser.staticSolrUserEmail(siteRequest_, SiteUser.staticSetUserEmail(siteRequest_, o)));
	}

	public String solrUserEmail() {
		return SiteUser.staticSolrUserEmail(siteRequest_, userEmail);
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
	public void setUserFirstName(String o) {
		this.userFirstName = SiteUser.staticSetUserFirstName(siteRequest_, o);
		this.userFirstNameWrap.alreadyInitialized = true;
	}
	public static String staticSetUserFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrUserFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrUserFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserFirstName(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserFirstName(siteRequest_, SiteUser.staticSolrUserFirstName(siteRequest_, SiteUser.staticSetUserFirstName(siteRequest_, o)));
	}

	public String solrUserFirstName() {
		return SiteUser.staticSolrUserFirstName(siteRequest_, userFirstName);
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
	public void setUserLastName(String o) {
		this.userLastName = SiteUser.staticSetUserLastName(siteRequest_, o);
		this.userLastNameWrap.alreadyInitialized = true;
	}
	public static String staticSetUserLastName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrUserLastName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrUserLastName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserLastName(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserLastName(siteRequest_, SiteUser.staticSolrUserLastName(siteRequest_, SiteUser.staticSetUserLastName(siteRequest_, o)));
	}

	public String solrUserLastName() {
		return SiteUser.staticSolrUserLastName(siteRequest_, userLastName);
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
	public void setUserFullName(String o) {
		this.userFullName = SiteUser.staticSetUserFullName(siteRequest_, o);
		this.userFullNameWrap.alreadyInitialized = true;
	}
	public static String staticSetUserFullName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrUserFullName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrUserFullName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserFullName(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserFullName(siteRequest_, SiteUser.staticSolrUserFullName(siteRequest_, SiteUser.staticSetUserFullName(siteRequest_, o)));
	}

	public String solrUserFullName() {
		return SiteUser.staticSolrUserFullName(siteRequest_, userFullName);
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
	public void setUserSite(String o) {
		this.userSite = SiteUser.staticSetUserSite(siteRequest_, o);
		this.userSiteWrap.alreadyInitialized = true;
	}
	public static String staticSetUserSite(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrUserSite(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrUserSite(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserSite(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserSite(siteRequest_, SiteUser.staticSolrUserSite(siteRequest_, SiteUser.staticSetUserSite(siteRequest_, o)));
	}

	public String solrUserSite() {
		return SiteUser.staticSolrUserSite(siteRequest_, userSite);
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
	public void setCustomerProfileId1(String o) {
		this.customerProfileId1 = SiteUser.staticSetCustomerProfileId1(siteRequest_, o);
		this.customerProfileId1Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId1(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrCustomerProfileId1(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId1(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId1(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId1(siteRequest_, SiteUser.staticSolrCustomerProfileId1(siteRequest_, SiteUser.staticSetCustomerProfileId1(siteRequest_, o)));
	}

	public String solrCustomerProfileId1() {
		return SiteUser.staticSolrCustomerProfileId1(siteRequest_, customerProfileId1);
	}

	public String strCustomerProfileId1() {
		return customerProfileId1 == null ? "" : customerProfileId1;
	}

	public String jsonCustomerProfileId1() {
		return customerProfileId1 == null ? "" : customerProfileId1;
	}

	public String nomAffichageCustomerProfileId1() {
		return null;
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
	public void setCustomerProfileId2(String o) {
		this.customerProfileId2 = SiteUser.staticSetCustomerProfileId2(siteRequest_, o);
		this.customerProfileId2Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId2(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrCustomerProfileId2(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId2(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId2(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId2(siteRequest_, SiteUser.staticSolrCustomerProfileId2(siteRequest_, SiteUser.staticSetCustomerProfileId2(siteRequest_, o)));
	}

	public String solrCustomerProfileId2() {
		return SiteUser.staticSolrCustomerProfileId2(siteRequest_, customerProfileId2);
	}

	public String strCustomerProfileId2() {
		return customerProfileId2 == null ? "" : customerProfileId2;
	}

	public String jsonCustomerProfileId2() {
		return customerProfileId2 == null ? "" : customerProfileId2;
	}

	public String nomAffichageCustomerProfileId2() {
		return null;
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

	////////////////////////
	// customerProfileId3 //
	////////////////////////

	/**	 The entity customerProfileId3
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId3;
	@JsonIgnore
	public Wrap<String> customerProfileId3Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId3").o(customerProfileId3);

	/**	<br/> The entity customerProfileId3
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId3">Find the entity customerProfileId3 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId3(Wrap<String> c);

	public String getCustomerProfileId3() {
		return customerProfileId3;
	}
	public void setCustomerProfileId3(String o) {
		this.customerProfileId3 = SiteUser.staticSetCustomerProfileId3(siteRequest_, o);
		this.customerProfileId3Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId3(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId3Init() {
		if(!customerProfileId3Wrap.alreadyInitialized) {
			_customerProfileId3(customerProfileId3Wrap);
			if(customerProfileId3 == null)
				setCustomerProfileId3(customerProfileId3Wrap.o);
		}
		customerProfileId3Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId3(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId3(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId3(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId3(siteRequest_, SiteUser.staticSolrCustomerProfileId3(siteRequest_, SiteUser.staticSetCustomerProfileId3(siteRequest_, o)));
	}

	public String solrCustomerProfileId3() {
		return SiteUser.staticSolrCustomerProfileId3(siteRequest_, customerProfileId3);
	}

	public String strCustomerProfileId3() {
		return customerProfileId3 == null ? "" : customerProfileId3;
	}

	public String jsonCustomerProfileId3() {
		return customerProfileId3 == null ? "" : customerProfileId3;
	}

	public String nomAffichageCustomerProfileId3() {
		return null;
	}

	public String htmTooltipCustomerProfileId3() {
		return null;
	}

	public String htmCustomerProfileId3() {
		return customerProfileId3 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId3());
	}

	public void inputCustomerProfileId3(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId3");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId3 classSiteUser inputSiteUser", pk, "CustomerProfileId3 w3-input w3-border ");
					a("name", "setCustomerProfileId3");
				} else {
					a("class", "valueCustomerProfileId3 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId3 w3-input w3-border ");
					a("name", "customerProfileId3");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId3', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId3')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId3')); }); ");
				}
				a("value", strCustomerProfileId3())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId3 ").f().sx(htmCustomerProfileId3()).g("span");
			}
		}
	}

	public void htmCustomerProfileId3(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId3(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId3')); $('#", classApiMethodMethod, "_customerProfileId3').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId3', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId3')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId3')); }); ")
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
	// customerProfileId4 //
	////////////////////////

	/**	 The entity customerProfileId4
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId4;
	@JsonIgnore
	public Wrap<String> customerProfileId4Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId4").o(customerProfileId4);

	/**	<br/> The entity customerProfileId4
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId4">Find the entity customerProfileId4 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId4(Wrap<String> c);

	public String getCustomerProfileId4() {
		return customerProfileId4;
	}
	public void setCustomerProfileId4(String o) {
		this.customerProfileId4 = SiteUser.staticSetCustomerProfileId4(siteRequest_, o);
		this.customerProfileId4Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId4(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId4Init() {
		if(!customerProfileId4Wrap.alreadyInitialized) {
			_customerProfileId4(customerProfileId4Wrap);
			if(customerProfileId4 == null)
				setCustomerProfileId4(customerProfileId4Wrap.o);
		}
		customerProfileId4Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId4(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId4(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId4(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId4(siteRequest_, SiteUser.staticSolrCustomerProfileId4(siteRequest_, SiteUser.staticSetCustomerProfileId4(siteRequest_, o)));
	}

	public String solrCustomerProfileId4() {
		return SiteUser.staticSolrCustomerProfileId4(siteRequest_, customerProfileId4);
	}

	public String strCustomerProfileId4() {
		return customerProfileId4 == null ? "" : customerProfileId4;
	}

	public String jsonCustomerProfileId4() {
		return customerProfileId4 == null ? "" : customerProfileId4;
	}

	public String nomAffichageCustomerProfileId4() {
		return null;
	}

	public String htmTooltipCustomerProfileId4() {
		return null;
	}

	public String htmCustomerProfileId4() {
		return customerProfileId4 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId4());
	}

	public void inputCustomerProfileId4(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId4");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId4 classSiteUser inputSiteUser", pk, "CustomerProfileId4 w3-input w3-border ");
					a("name", "setCustomerProfileId4");
				} else {
					a("class", "valueCustomerProfileId4 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId4 w3-input w3-border ");
					a("name", "customerProfileId4");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId4', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId4')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId4')); }); ");
				}
				a("value", strCustomerProfileId4())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId4 ").f().sx(htmCustomerProfileId4()).g("span");
			}
		}
	}

	public void htmCustomerProfileId4(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId4(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId4')); $('#", classApiMethodMethod, "_customerProfileId4').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId4', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId4')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId4')); }); ")
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
	// customerProfileId5 //
	////////////////////////

	/**	 The entity customerProfileId5
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId5;
	@JsonIgnore
	public Wrap<String> customerProfileId5Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId5").o(customerProfileId5);

	/**	<br/> The entity customerProfileId5
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId5">Find the entity customerProfileId5 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId5(Wrap<String> c);

	public String getCustomerProfileId5() {
		return customerProfileId5;
	}
	public void setCustomerProfileId5(String o) {
		this.customerProfileId5 = SiteUser.staticSetCustomerProfileId5(siteRequest_, o);
		this.customerProfileId5Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId5(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId5Init() {
		if(!customerProfileId5Wrap.alreadyInitialized) {
			_customerProfileId5(customerProfileId5Wrap);
			if(customerProfileId5 == null)
				setCustomerProfileId5(customerProfileId5Wrap.o);
		}
		customerProfileId5Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId5(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId5(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId5(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId5(siteRequest_, SiteUser.staticSolrCustomerProfileId5(siteRequest_, SiteUser.staticSetCustomerProfileId5(siteRequest_, o)));
	}

	public String solrCustomerProfileId5() {
		return SiteUser.staticSolrCustomerProfileId5(siteRequest_, customerProfileId5);
	}

	public String strCustomerProfileId5() {
		return customerProfileId5 == null ? "" : customerProfileId5;
	}

	public String jsonCustomerProfileId5() {
		return customerProfileId5 == null ? "" : customerProfileId5;
	}

	public String nomAffichageCustomerProfileId5() {
		return null;
	}

	public String htmTooltipCustomerProfileId5() {
		return null;
	}

	public String htmCustomerProfileId5() {
		return customerProfileId5 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId5());
	}

	public void inputCustomerProfileId5(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId5");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId5 classSiteUser inputSiteUser", pk, "CustomerProfileId5 w3-input w3-border ");
					a("name", "setCustomerProfileId5");
				} else {
					a("class", "valueCustomerProfileId5 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId5 w3-input w3-border ");
					a("name", "customerProfileId5");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId5', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId5')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId5')); }); ");
				}
				a("value", strCustomerProfileId5())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId5 ").f().sx(htmCustomerProfileId5()).g("span");
			}
		}
	}

	public void htmCustomerProfileId5(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId5(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId5')); $('#", classApiMethodMethod, "_customerProfileId5').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId5', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId5')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId5')); }); ")
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
	// customerProfileId6 //
	////////////////////////

	/**	 The entity customerProfileId6
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId6;
	@JsonIgnore
	public Wrap<String> customerProfileId6Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId6").o(customerProfileId6);

	/**	<br/> The entity customerProfileId6
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId6">Find the entity customerProfileId6 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId6(Wrap<String> c);

	public String getCustomerProfileId6() {
		return customerProfileId6;
	}
	public void setCustomerProfileId6(String o) {
		this.customerProfileId6 = SiteUser.staticSetCustomerProfileId6(siteRequest_, o);
		this.customerProfileId6Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId6(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId6Init() {
		if(!customerProfileId6Wrap.alreadyInitialized) {
			_customerProfileId6(customerProfileId6Wrap);
			if(customerProfileId6 == null)
				setCustomerProfileId6(customerProfileId6Wrap.o);
		}
		customerProfileId6Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId6(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId6(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId6(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId6(siteRequest_, SiteUser.staticSolrCustomerProfileId6(siteRequest_, SiteUser.staticSetCustomerProfileId6(siteRequest_, o)));
	}

	public String solrCustomerProfileId6() {
		return SiteUser.staticSolrCustomerProfileId6(siteRequest_, customerProfileId6);
	}

	public String strCustomerProfileId6() {
		return customerProfileId6 == null ? "" : customerProfileId6;
	}

	public String jsonCustomerProfileId6() {
		return customerProfileId6 == null ? "" : customerProfileId6;
	}

	public String nomAffichageCustomerProfileId6() {
		return null;
	}

	public String htmTooltipCustomerProfileId6() {
		return null;
	}

	public String htmCustomerProfileId6() {
		return customerProfileId6 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId6());
	}

	public void inputCustomerProfileId6(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId6");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId6 classSiteUser inputSiteUser", pk, "CustomerProfileId6 w3-input w3-border ");
					a("name", "setCustomerProfileId6");
				} else {
					a("class", "valueCustomerProfileId6 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId6 w3-input w3-border ");
					a("name", "customerProfileId6");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId6', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId6')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId6')); }); ");
				}
				a("value", strCustomerProfileId6())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId6 ").f().sx(htmCustomerProfileId6()).g("span");
			}
		}
	}

	public void htmCustomerProfileId6(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId6(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId6')); $('#", classApiMethodMethod, "_customerProfileId6').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId6', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId6')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId6')); }); ")
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
	// customerProfileId7 //
	////////////////////////

	/**	 The entity customerProfileId7
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId7;
	@JsonIgnore
	public Wrap<String> customerProfileId7Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId7").o(customerProfileId7);

	/**	<br/> The entity customerProfileId7
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId7">Find the entity customerProfileId7 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId7(Wrap<String> c);

	public String getCustomerProfileId7() {
		return customerProfileId7;
	}
	public void setCustomerProfileId7(String o) {
		this.customerProfileId7 = SiteUser.staticSetCustomerProfileId7(siteRequest_, o);
		this.customerProfileId7Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId7(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId7Init() {
		if(!customerProfileId7Wrap.alreadyInitialized) {
			_customerProfileId7(customerProfileId7Wrap);
			if(customerProfileId7 == null)
				setCustomerProfileId7(customerProfileId7Wrap.o);
		}
		customerProfileId7Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId7(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId7(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId7(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId7(siteRequest_, SiteUser.staticSolrCustomerProfileId7(siteRequest_, SiteUser.staticSetCustomerProfileId7(siteRequest_, o)));
	}

	public String solrCustomerProfileId7() {
		return SiteUser.staticSolrCustomerProfileId7(siteRequest_, customerProfileId7);
	}

	public String strCustomerProfileId7() {
		return customerProfileId7 == null ? "" : customerProfileId7;
	}

	public String jsonCustomerProfileId7() {
		return customerProfileId7 == null ? "" : customerProfileId7;
	}

	public String nomAffichageCustomerProfileId7() {
		return null;
	}

	public String htmTooltipCustomerProfileId7() {
		return null;
	}

	public String htmCustomerProfileId7() {
		return customerProfileId7 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId7());
	}

	public void inputCustomerProfileId7(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId7");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId7 classSiteUser inputSiteUser", pk, "CustomerProfileId7 w3-input w3-border ");
					a("name", "setCustomerProfileId7");
				} else {
					a("class", "valueCustomerProfileId7 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId7 w3-input w3-border ");
					a("name", "customerProfileId7");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId7', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId7')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId7')); }); ");
				}
				a("value", strCustomerProfileId7())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId7 ").f().sx(htmCustomerProfileId7()).g("span");
			}
		}
	}

	public void htmCustomerProfileId7(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId7(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId7')); $('#", classApiMethodMethod, "_customerProfileId7').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId7', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId7')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId7')); }); ")
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
	// customerProfileId8 //
	////////////////////////

	/**	 The entity customerProfileId8
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId8;
	@JsonIgnore
	public Wrap<String> customerProfileId8Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId8").o(customerProfileId8);

	/**	<br/> The entity customerProfileId8
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId8">Find the entity customerProfileId8 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId8(Wrap<String> c);

	public String getCustomerProfileId8() {
		return customerProfileId8;
	}
	public void setCustomerProfileId8(String o) {
		this.customerProfileId8 = SiteUser.staticSetCustomerProfileId8(siteRequest_, o);
		this.customerProfileId8Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId8(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId8Init() {
		if(!customerProfileId8Wrap.alreadyInitialized) {
			_customerProfileId8(customerProfileId8Wrap);
			if(customerProfileId8 == null)
				setCustomerProfileId8(customerProfileId8Wrap.o);
		}
		customerProfileId8Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId8(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId8(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId8(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId8(siteRequest_, SiteUser.staticSolrCustomerProfileId8(siteRequest_, SiteUser.staticSetCustomerProfileId8(siteRequest_, o)));
	}

	public String solrCustomerProfileId8() {
		return SiteUser.staticSolrCustomerProfileId8(siteRequest_, customerProfileId8);
	}

	public String strCustomerProfileId8() {
		return customerProfileId8 == null ? "" : customerProfileId8;
	}

	public String jsonCustomerProfileId8() {
		return customerProfileId8 == null ? "" : customerProfileId8;
	}

	public String nomAffichageCustomerProfileId8() {
		return null;
	}

	public String htmTooltipCustomerProfileId8() {
		return null;
	}

	public String htmCustomerProfileId8() {
		return customerProfileId8 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId8());
	}

	public void inputCustomerProfileId8(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId8");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId8 classSiteUser inputSiteUser", pk, "CustomerProfileId8 w3-input w3-border ");
					a("name", "setCustomerProfileId8");
				} else {
					a("class", "valueCustomerProfileId8 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId8 w3-input w3-border ");
					a("name", "customerProfileId8");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId8', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId8')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId8')); }); ");
				}
				a("value", strCustomerProfileId8())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId8 ").f().sx(htmCustomerProfileId8()).g("span");
			}
		}
	}

	public void htmCustomerProfileId8(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId8(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId8')); $('#", classApiMethodMethod, "_customerProfileId8').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId8', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId8')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId8')); }); ")
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
	// customerProfileId9 //
	////////////////////////

	/**	 The entity customerProfileId9
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId9;
	@JsonIgnore
	public Wrap<String> customerProfileId9Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId9").o(customerProfileId9);

	/**	<br/> The entity customerProfileId9
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId9">Find the entity customerProfileId9 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId9(Wrap<String> c);

	public String getCustomerProfileId9() {
		return customerProfileId9;
	}
	public void setCustomerProfileId9(String o) {
		this.customerProfileId9 = SiteUser.staticSetCustomerProfileId9(siteRequest_, o);
		this.customerProfileId9Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId9(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId9Init() {
		if(!customerProfileId9Wrap.alreadyInitialized) {
			_customerProfileId9(customerProfileId9Wrap);
			if(customerProfileId9 == null)
				setCustomerProfileId9(customerProfileId9Wrap.o);
		}
		customerProfileId9Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId9(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId9(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId9(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId9(siteRequest_, SiteUser.staticSolrCustomerProfileId9(siteRequest_, SiteUser.staticSetCustomerProfileId9(siteRequest_, o)));
	}

	public String solrCustomerProfileId9() {
		return SiteUser.staticSolrCustomerProfileId9(siteRequest_, customerProfileId9);
	}

	public String strCustomerProfileId9() {
		return customerProfileId9 == null ? "" : customerProfileId9;
	}

	public String jsonCustomerProfileId9() {
		return customerProfileId9 == null ? "" : customerProfileId9;
	}

	public String nomAffichageCustomerProfileId9() {
		return null;
	}

	public String htmTooltipCustomerProfileId9() {
		return null;
	}

	public String htmCustomerProfileId9() {
		return customerProfileId9 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId9());
	}

	public void inputCustomerProfileId9(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId9");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId9 classSiteUser inputSiteUser", pk, "CustomerProfileId9 w3-input w3-border ");
					a("name", "setCustomerProfileId9");
				} else {
					a("class", "valueCustomerProfileId9 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId9 w3-input w3-border ");
					a("name", "customerProfileId9");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId9', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId9')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId9')); }); ");
				}
				a("value", strCustomerProfileId9())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId9 ").f().sx(htmCustomerProfileId9()).g("span");
			}
		}
	}

	public void htmCustomerProfileId9(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId9(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId9')); $('#", classApiMethodMethod, "_customerProfileId9').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId9', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId9')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId9')); }); ")
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
	// customerProfileId10 //
	/////////////////////////

	/**	 The entity customerProfileId10
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId10;
	@JsonIgnore
	public Wrap<String> customerProfileId10Wrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId10").o(customerProfileId10);

	/**	<br/> The entity customerProfileId10
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUser&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId10">Find the entity customerProfileId10 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId10(Wrap<String> c);

	public String getCustomerProfileId10() {
		return customerProfileId10;
	}
	public void setCustomerProfileId10(String o) {
		this.customerProfileId10 = SiteUser.staticSetCustomerProfileId10(siteRequest_, o);
		this.customerProfileId10Wrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId10(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SiteUser customerProfileId10Init() {
		if(!customerProfileId10Wrap.alreadyInitialized) {
			_customerProfileId10(customerProfileId10Wrap);
			if(customerProfileId10 == null)
				setCustomerProfileId10(customerProfileId10Wrap.o);
		}
		customerProfileId10Wrap.alreadyInitialized(true);
		return (SiteUser)this;
	}

	public static String staticSolrCustomerProfileId10(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId10(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId10(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrCustomerProfileId10(siteRequest_, SiteUser.staticSolrCustomerProfileId10(siteRequest_, SiteUser.staticSetCustomerProfileId10(siteRequest_, o)));
	}

	public String solrCustomerProfileId10() {
		return SiteUser.staticSolrCustomerProfileId10(siteRequest_, customerProfileId10);
	}

	public String strCustomerProfileId10() {
		return customerProfileId10 == null ? "" : customerProfileId10;
	}

	public String jsonCustomerProfileId10() {
		return customerProfileId10 == null ? "" : customerProfileId10;
	}

	public String nomAffichageCustomerProfileId10() {
		return null;
	}

	public String htmTooltipCustomerProfileId10() {
		return null;
	}

	public String htmCustomerProfileId10() {
		return customerProfileId10 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId10());
	}

	public void inputCustomerProfileId10(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_customerProfileId10");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId10 classSiteUser inputSiteUser", pk, "CustomerProfileId10 w3-input w3-border ");
					a("name", "setCustomerProfileId10");
				} else {
					a("class", "valueCustomerProfileId10 w3-input w3-border classSiteUser inputSiteUser", pk, "CustomerProfileId10 w3-input w3-border ");
					a("name", "customerProfileId10");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId10', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId10')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId10')); }); ");
				}
				a("value", strCustomerProfileId10())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				e("span").a("class", "varSiteUser", pk, "CustomerProfileId10 ").f().sx(htmCustomerProfileId10()).g("span");
			}
		}
	}

	public void htmCustomerProfileId10(String classApiMethodMethod) {
		SiteUser s = (SiteUser)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SiteUserCustomerProfileId10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId10(classApiMethodMethod);
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId10')); $('#", classApiMethodMethod, "_customerProfileId10').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=pk]').val() }], 'setCustomerProfileId10', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId10')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId10')); }); ")
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
	public void setUserReceiveEmails(String o) {
		this.userReceiveEmails = SiteUser.staticSetUserReceiveEmails(siteRequest_, o);
		this.userReceiveEmailsWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetUserReceiveEmails(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrUserReceiveEmails(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrUserReceiveEmails(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserReceiveEmails(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrUserReceiveEmails(siteRequest_, SiteUser.staticSolrUserReceiveEmails(siteRequest_, SiteUser.staticSetUserReceiveEmails(siteRequest_, o)));
	}

	public Boolean solrUserReceiveEmails() {
		return SiteUser.staticSolrUserReceiveEmails(siteRequest_, userReceiveEmails);
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
	public void setSeeArchived(String o) {
		this.seeArchived = SiteUser.staticSetSeeArchived(siteRequest_, o);
		this.seeArchivedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetSeeArchived(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrSeeArchived(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSeeArchived(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeeArchived(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrSeeArchived(siteRequest_, SiteUser.staticSolrSeeArchived(siteRequest_, SiteUser.staticSetSeeArchived(siteRequest_, o)));
	}

	public Boolean solrSeeArchived() {
		return SiteUser.staticSolrSeeArchived(siteRequest_, seeArchived);
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
	public void setSeeDeleted(String o) {
		this.seeDeleted = SiteUser.staticSetSeeDeleted(siteRequest_, o);
		this.seeDeletedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetSeeDeleted(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrSeeDeleted(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSeeDeleted(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeeDeleted(SiteRequestEnUS siteRequest_, String o) {
		return SiteUser.staticSolrStrSeeDeleted(siteRequest_, SiteUser.staticSolrSeeDeleted(siteRequest_, SiteUser.staticSetSeeDeleted(siteRequest_, o)));
	}

	public Boolean solrSeeDeleted() {
		return SiteUser.staticSolrSeeDeleted(siteRequest_, seeDeleted);
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
		customerProfileId3Init();
		customerProfileId4Init();
		customerProfileId5Init();
		customerProfileId6Init();
		customerProfileId7Init();
		customerProfileId8Init();
		customerProfileId9Init();
		customerProfileId10Init();
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
			case "customerProfileId3":
				return oSiteUser.customerProfileId3;
			case "customerProfileId4":
				return oSiteUser.customerProfileId4;
			case "customerProfileId5":
				return oSiteUser.customerProfileId5;
			case "customerProfileId6":
				return oSiteUser.customerProfileId6;
			case "customerProfileId7":
				return oSiteUser.customerProfileId7;
			case "customerProfileId8":
				return oSiteUser.customerProfileId8;
			case "customerProfileId9":
				return oSiteUser.customerProfileId9;
			case "customerProfileId10":
				return oSiteUser.customerProfileId10;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSiteUser(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSiteUser(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSetUserKeys(siteRequest_, o);
		case "enrollmentKeys":
			return SiteUser.staticSetEnrollmentKeys(siteRequest_, o);
		case "paymentKeys":
			return SiteUser.staticSetPaymentKeys(siteRequest_, o);
		case "userName":
			return SiteUser.staticSetUserName(siteRequest_, o);
		case "userEmail":
			return SiteUser.staticSetUserEmail(siteRequest_, o);
		case "userFirstName":
			return SiteUser.staticSetUserFirstName(siteRequest_, o);
		case "userLastName":
			return SiteUser.staticSetUserLastName(siteRequest_, o);
		case "userFullName":
			return SiteUser.staticSetUserFullName(siteRequest_, o);
		case "userSite":
			return SiteUser.staticSetUserSite(siteRequest_, o);
		case "customerProfileId1":
			return SiteUser.staticSetCustomerProfileId1(siteRequest_, o);
		case "customerProfileId2":
			return SiteUser.staticSetCustomerProfileId2(siteRequest_, o);
		case "customerProfileId3":
			return SiteUser.staticSetCustomerProfileId3(siteRequest_, o);
		case "customerProfileId4":
			return SiteUser.staticSetCustomerProfileId4(siteRequest_, o);
		case "customerProfileId5":
			return SiteUser.staticSetCustomerProfileId5(siteRequest_, o);
		case "customerProfileId6":
			return SiteUser.staticSetCustomerProfileId6(siteRequest_, o);
		case "customerProfileId7":
			return SiteUser.staticSetCustomerProfileId7(siteRequest_, o);
		case "customerProfileId8":
			return SiteUser.staticSetCustomerProfileId8(siteRequest_, o);
		case "customerProfileId9":
			return SiteUser.staticSetCustomerProfileId9(siteRequest_, o);
		case "customerProfileId10":
			return SiteUser.staticSetCustomerProfileId10(siteRequest_, o);
		case "userReceiveEmails":
			return SiteUser.staticSetUserReceiveEmails(siteRequest_, o);
		case "seeArchived":
			return SiteUser.staticSetSeeArchived(siteRequest_, o);
		case "seeDeleted":
			return SiteUser.staticSetSeeDeleted(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSiteUser(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSiteUser(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSolrUserKeys(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SiteUser.staticSolrEnrollmentKeys(siteRequest_, (Long)o);
		case "paymentKeys":
			return SiteUser.staticSolrPaymentKeys(siteRequest_, (Long)o);
		case "userName":
			return SiteUser.staticSolrUserName(siteRequest_, (String)o);
		case "userEmail":
			return SiteUser.staticSolrUserEmail(siteRequest_, (String)o);
		case "userFirstName":
			return SiteUser.staticSolrUserFirstName(siteRequest_, (String)o);
		case "userLastName":
			return SiteUser.staticSolrUserLastName(siteRequest_, (String)o);
		case "userFullName":
			return SiteUser.staticSolrUserFullName(siteRequest_, (String)o);
		case "userSite":
			return SiteUser.staticSolrUserSite(siteRequest_, (String)o);
		case "customerProfileId1":
			return SiteUser.staticSolrCustomerProfileId1(siteRequest_, (String)o);
		case "customerProfileId2":
			return SiteUser.staticSolrCustomerProfileId2(siteRequest_, (String)o);
		case "customerProfileId3":
			return SiteUser.staticSolrCustomerProfileId3(siteRequest_, (String)o);
		case "customerProfileId4":
			return SiteUser.staticSolrCustomerProfileId4(siteRequest_, (String)o);
		case "customerProfileId5":
			return SiteUser.staticSolrCustomerProfileId5(siteRequest_, (String)o);
		case "customerProfileId6":
			return SiteUser.staticSolrCustomerProfileId6(siteRequest_, (String)o);
		case "customerProfileId7":
			return SiteUser.staticSolrCustomerProfileId7(siteRequest_, (String)o);
		case "customerProfileId8":
			return SiteUser.staticSolrCustomerProfileId8(siteRequest_, (String)o);
		case "customerProfileId9":
			return SiteUser.staticSolrCustomerProfileId9(siteRequest_, (String)o);
		case "customerProfileId10":
			return SiteUser.staticSolrCustomerProfileId10(siteRequest_, (String)o);
		case "userReceiveEmails":
			return SiteUser.staticSolrUserReceiveEmails(siteRequest_, (Boolean)o);
		case "seeArchived":
			return SiteUser.staticSolrSeeArchived(siteRequest_, (Boolean)o);
		case "seeDeleted":
			return SiteUser.staticSolrSeeDeleted(siteRequest_, (Boolean)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSiteUser(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSiteUser(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSolrStrUserKeys(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SiteUser.staticSolrStrEnrollmentKeys(siteRequest_, (Long)o);
		case "paymentKeys":
			return SiteUser.staticSolrStrPaymentKeys(siteRequest_, (Long)o);
		case "userName":
			return SiteUser.staticSolrStrUserName(siteRequest_, (String)o);
		case "userEmail":
			return SiteUser.staticSolrStrUserEmail(siteRequest_, (String)o);
		case "userFirstName":
			return SiteUser.staticSolrStrUserFirstName(siteRequest_, (String)o);
		case "userLastName":
			return SiteUser.staticSolrStrUserLastName(siteRequest_, (String)o);
		case "userFullName":
			return SiteUser.staticSolrStrUserFullName(siteRequest_, (String)o);
		case "userSite":
			return SiteUser.staticSolrStrUserSite(siteRequest_, (String)o);
		case "customerProfileId1":
			return SiteUser.staticSolrStrCustomerProfileId1(siteRequest_, (String)o);
		case "customerProfileId2":
			return SiteUser.staticSolrStrCustomerProfileId2(siteRequest_, (String)o);
		case "customerProfileId3":
			return SiteUser.staticSolrStrCustomerProfileId3(siteRequest_, (String)o);
		case "customerProfileId4":
			return SiteUser.staticSolrStrCustomerProfileId4(siteRequest_, (String)o);
		case "customerProfileId5":
			return SiteUser.staticSolrStrCustomerProfileId5(siteRequest_, (String)o);
		case "customerProfileId6":
			return SiteUser.staticSolrStrCustomerProfileId6(siteRequest_, (String)o);
		case "customerProfileId7":
			return SiteUser.staticSolrStrCustomerProfileId7(siteRequest_, (String)o);
		case "customerProfileId8":
			return SiteUser.staticSolrStrCustomerProfileId8(siteRequest_, (String)o);
		case "customerProfileId9":
			return SiteUser.staticSolrStrCustomerProfileId9(siteRequest_, (String)o);
		case "customerProfileId10":
			return SiteUser.staticSolrStrCustomerProfileId10(siteRequest_, (String)o);
		case "userReceiveEmails":
			return SiteUser.staticSolrStrUserReceiveEmails(siteRequest_, (Boolean)o);
		case "seeArchived":
			return SiteUser.staticSolrStrSeeArchived(siteRequest_, (Boolean)o);
		case "seeDeleted":
			return SiteUser.staticSolrStrSeeDeleted(siteRequest_, (Boolean)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSiteUser(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSiteUser(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "userKeys":
			return SiteUser.staticSolrFqUserKeys(siteRequest_, o);
		case "enrollmentKeys":
			return SiteUser.staticSolrFqEnrollmentKeys(siteRequest_, o);
		case "paymentKeys":
			return SiteUser.staticSolrFqPaymentKeys(siteRequest_, o);
		case "userName":
			return SiteUser.staticSolrFqUserName(siteRequest_, o);
		case "userEmail":
			return SiteUser.staticSolrFqUserEmail(siteRequest_, o);
		case "userFirstName":
			return SiteUser.staticSolrFqUserFirstName(siteRequest_, o);
		case "userLastName":
			return SiteUser.staticSolrFqUserLastName(siteRequest_, o);
		case "userFullName":
			return SiteUser.staticSolrFqUserFullName(siteRequest_, o);
		case "userSite":
			return SiteUser.staticSolrFqUserSite(siteRequest_, o);
		case "customerProfileId1":
			return SiteUser.staticSolrFqCustomerProfileId1(siteRequest_, o);
		case "customerProfileId2":
			return SiteUser.staticSolrFqCustomerProfileId2(siteRequest_, o);
		case "customerProfileId3":
			return SiteUser.staticSolrFqCustomerProfileId3(siteRequest_, o);
		case "customerProfileId4":
			return SiteUser.staticSolrFqCustomerProfileId4(siteRequest_, o);
		case "customerProfileId5":
			return SiteUser.staticSolrFqCustomerProfileId5(siteRequest_, o);
		case "customerProfileId6":
			return SiteUser.staticSolrFqCustomerProfileId6(siteRequest_, o);
		case "customerProfileId7":
			return SiteUser.staticSolrFqCustomerProfileId7(siteRequest_, o);
		case "customerProfileId8":
			return SiteUser.staticSolrFqCustomerProfileId8(siteRequest_, o);
		case "customerProfileId9":
			return SiteUser.staticSolrFqCustomerProfileId9(siteRequest_, o);
		case "customerProfileId10":
			return SiteUser.staticSolrFqCustomerProfileId10(siteRequest_, o);
		case "userReceiveEmails":
			return SiteUser.staticSolrFqUserReceiveEmails(siteRequest_, o);
		case "seeArchived":
			return SiteUser.staticSolrFqSeeArchived(siteRequest_, o);
		case "seeDeleted":
			return SiteUser.staticSolrFqSeeDeleted(siteRequest_, o);
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
					o = defineSiteUser(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
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
			case "customerProfileId3":
				if(val != null)
					setCustomerProfileId3(val);
				saves.add(var);
				return val;
			case "customerProfileId4":
				if(val != null)
					setCustomerProfileId4(val);
				saves.add(var);
				return val;
			case "customerProfileId5":
				if(val != null)
					setCustomerProfileId5(val);
				saves.add(var);
				return val;
			case "customerProfileId6":
				if(val != null)
					setCustomerProfileId6(val);
				saves.add(var);
				return val;
			case "customerProfileId7":
				if(val != null)
					setCustomerProfileId7(val);
				saves.add(var);
				return val;
			case "customerProfileId8":
				if(val != null)
					setCustomerProfileId8(val);
				saves.add(var);
				return val;
			case "customerProfileId9":
				if(val != null)
					setCustomerProfileId9(val);
				saves.add(var);
				return val;
			case "customerProfileId10":
				if(val != null)
					setCustomerProfileId10(val);
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

			if(saves.contains("customerProfileId3")) {
				String customerProfileId3 = (String)solrDocument.get("customerProfileId3_stored_string");
				if(customerProfileId3 != null)
					oSiteUser.setCustomerProfileId3(customerProfileId3);
			}

			if(saves.contains("customerProfileId4")) {
				String customerProfileId4 = (String)solrDocument.get("customerProfileId4_stored_string");
				if(customerProfileId4 != null)
					oSiteUser.setCustomerProfileId4(customerProfileId4);
			}

			if(saves.contains("customerProfileId5")) {
				String customerProfileId5 = (String)solrDocument.get("customerProfileId5_stored_string");
				if(customerProfileId5 != null)
					oSiteUser.setCustomerProfileId5(customerProfileId5);
			}

			if(saves.contains("customerProfileId6")) {
				String customerProfileId6 = (String)solrDocument.get("customerProfileId6_stored_string");
				if(customerProfileId6 != null)
					oSiteUser.setCustomerProfileId6(customerProfileId6);
			}

			if(saves.contains("customerProfileId7")) {
				String customerProfileId7 = (String)solrDocument.get("customerProfileId7_stored_string");
				if(customerProfileId7 != null)
					oSiteUser.setCustomerProfileId7(customerProfileId7);
			}

			if(saves.contains("customerProfileId8")) {
				String customerProfileId8 = (String)solrDocument.get("customerProfileId8_stored_string");
				if(customerProfileId8 != null)
					oSiteUser.setCustomerProfileId8(customerProfileId8);
			}

			if(saves.contains("customerProfileId9")) {
				String customerProfileId9 = (String)solrDocument.get("customerProfileId9_stored_string");
				if(customerProfileId9 != null)
					oSiteUser.setCustomerProfileId9(customerProfileId9);
			}

			if(saves.contains("customerProfileId10")) {
				String customerProfileId10 = (String)solrDocument.get("customerProfileId10_stored_string");
				if(customerProfileId10 != null)
					oSiteUser.setCustomerProfileId10(customerProfileId10);
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
		if(customerProfileId3 != null) {
			document.addField("customerProfileId3_indexed_string", customerProfileId3);
			document.addField("customerProfileId3_stored_string", customerProfileId3);
		}
		if(customerProfileId4 != null) {
			document.addField("customerProfileId4_indexed_string", customerProfileId4);
			document.addField("customerProfileId4_stored_string", customerProfileId4);
		}
		if(customerProfileId5 != null) {
			document.addField("customerProfileId5_indexed_string", customerProfileId5);
			document.addField("customerProfileId5_stored_string", customerProfileId5);
		}
		if(customerProfileId6 != null) {
			document.addField("customerProfileId6_indexed_string", customerProfileId6);
			document.addField("customerProfileId6_stored_string", customerProfileId6);
		}
		if(customerProfileId7 != null) {
			document.addField("customerProfileId7_indexed_string", customerProfileId7);
			document.addField("customerProfileId7_stored_string", customerProfileId7);
		}
		if(customerProfileId8 != null) {
			document.addField("customerProfileId8_indexed_string", customerProfileId8);
			document.addField("customerProfileId8_stored_string", customerProfileId8);
		}
		if(customerProfileId9 != null) {
			document.addField("customerProfileId9_indexed_string", customerProfileId9);
			document.addField("customerProfileId9_stored_string", customerProfileId9);
		}
		if(customerProfileId10 != null) {
			document.addField("customerProfileId10_indexed_string", customerProfileId10);
			document.addField("customerProfileId10_stored_string", customerProfileId10);
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
			case "customerProfileId3":
				return "customerProfileId3_indexed_string";
			case "customerProfileId4":
				return "customerProfileId4_indexed_string";
			case "customerProfileId5":
				return "customerProfileId5_indexed_string";
			case "customerProfileId6":
				return "customerProfileId6_indexed_string";
			case "customerProfileId7":
				return "customerProfileId7_indexed_string";
			case "customerProfileId8":
				return "customerProfileId8_indexed_string";
			case "customerProfileId9":
				return "customerProfileId9_indexed_string";
			case "customerProfileId10":
				return "customerProfileId10_indexed_string";
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

		String customerProfileId3 = (String)solrDocument.get("customerProfileId3_stored_string");
		if(customerProfileId3 != null)
			oSiteUser.setCustomerProfileId3(customerProfileId3);

		String customerProfileId4 = (String)solrDocument.get("customerProfileId4_stored_string");
		if(customerProfileId4 != null)
			oSiteUser.setCustomerProfileId4(customerProfileId4);

		String customerProfileId5 = (String)solrDocument.get("customerProfileId5_stored_string");
		if(customerProfileId5 != null)
			oSiteUser.setCustomerProfileId5(customerProfileId5);

		String customerProfileId6 = (String)solrDocument.get("customerProfileId6_stored_string");
		if(customerProfileId6 != null)
			oSiteUser.setCustomerProfileId6(customerProfileId6);

		String customerProfileId7 = (String)solrDocument.get("customerProfileId7_stored_string");
		if(customerProfileId7 != null)
			oSiteUser.setCustomerProfileId7(customerProfileId7);

		String customerProfileId8 = (String)solrDocument.get("customerProfileId8_stored_string");
		if(customerProfileId8 != null)
			oSiteUser.setCustomerProfileId8(customerProfileId8);

		String customerProfileId9 = (String)solrDocument.get("customerProfileId9_stored_string");
		if(customerProfileId9 != null)
			oSiteUser.setCustomerProfileId9(customerProfileId9);

		String customerProfileId10 = (String)solrDocument.get("customerProfileId10_stored_string");
		if(customerProfileId10 != null)
			oSiteUser.setCustomerProfileId10(customerProfileId10);

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
			if(!Objects.equals(customerProfileId3, original.getCustomerProfileId3()))
				apiRequest.addVars("customerProfileId3");
			if(!Objects.equals(customerProfileId4, original.getCustomerProfileId4()))
				apiRequest.addVars("customerProfileId4");
			if(!Objects.equals(customerProfileId5, original.getCustomerProfileId5()))
				apiRequest.addVars("customerProfileId5");
			if(!Objects.equals(customerProfileId6, original.getCustomerProfileId6()))
				apiRequest.addVars("customerProfileId6");
			if(!Objects.equals(customerProfileId7, original.getCustomerProfileId7()))
				apiRequest.addVars("customerProfileId7");
			if(!Objects.equals(customerProfileId8, original.getCustomerProfileId8()))
				apiRequest.addVars("customerProfileId8");
			if(!Objects.equals(customerProfileId9, original.getCustomerProfileId9()))
				apiRequest.addVars("customerProfileId9");
			if(!Objects.equals(customerProfileId10, original.getCustomerProfileId10()))
				apiRequest.addVars("customerProfileId10");
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
		return Objects.hash(super.hashCode(), userKeys, enrollmentKeys, paymentKeys, userName, userEmail, userFirstName, userLastName, userFullName, userSite, customerProfileId1, customerProfileId2, customerProfileId3, customerProfileId4, customerProfileId5, customerProfileId6, customerProfileId7, customerProfileId8, customerProfileId9, customerProfileId10, userReceiveEmails, seeArchived, seeDeleted);
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
				&& Objects.equals( customerProfileId3, that.customerProfileId3 )
				&& Objects.equals( customerProfileId4, that.customerProfileId4 )
				&& Objects.equals( customerProfileId5, that.customerProfileId5 )
				&& Objects.equals( customerProfileId6, that.customerProfileId6 )
				&& Objects.equals( customerProfileId7, that.customerProfileId7 )
				&& Objects.equals( customerProfileId8, that.customerProfileId8 )
				&& Objects.equals( customerProfileId9, that.customerProfileId9 )
				&& Objects.equals( customerProfileId10, that.customerProfileId10 )
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
		sb.append( ", customerProfileId3: \"" ).append(customerProfileId3).append( "\"" );
		sb.append( ", customerProfileId4: \"" ).append(customerProfileId4).append( "\"" );
		sb.append( ", customerProfileId5: \"" ).append(customerProfileId5).append( "\"" );
		sb.append( ", customerProfileId6: \"" ).append(customerProfileId6).append( "\"" );
		sb.append( ", customerProfileId7: \"" ).append(customerProfileId7).append( "\"" );
		sb.append( ", customerProfileId8: \"" ).append(customerProfileId8).append( "\"" );
		sb.append( ", customerProfileId9: \"" ).append(customerProfileId9).append( "\"" );
		sb.append( ", customerProfileId10: \"" ).append(customerProfileId10).append( "\"" );
		sb.append( ", userReceiveEmails: " ).append(userReceiveEmails);
		sb.append( ", seeArchived: " ).append(seeArchived);
		sb.append( ", seeDeleted: " ).append(seeDeleted);
		sb.append(" }");
		return sb.toString();
	}
}
