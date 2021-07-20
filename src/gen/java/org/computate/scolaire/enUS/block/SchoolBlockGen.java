package org.computate.scolaire.enUS.block;

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
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import org.computate.scolaire.enUS.age.SchoolAge;
import java.time.LocalDate;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.computate.scolaire.enUS.block.SchoolBlock;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolBlockGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolBlock.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolBlock_AName = "a block";
	public static final String SchoolBlock_This = "this ";
	public static final String SchoolBlock_ThisName = "this block";
	public static final String SchoolBlock_A = "a ";
	public static final String SchoolBlock_TheName = "the block";
	public static final String SchoolBlock_NameSingular = "block";
	public static final String SchoolBlock_NamePlural = "blocks";
	public static final String SchoolBlock_NameActual = "current block";
	public static final String SchoolBlock_AllName = "all the blocks";
	public static final String SchoolBlock_SearchAllNameBy = "search blocks by ";
	public static final String SchoolBlock_Title = "blocks";
	public static final String SchoolBlock_ThePluralName = "the blocks";
	public static final String SchoolBlock_NoNameFound = "no block found";
	public static final String SchoolBlock_NameVar = "block";
	public static final String SchoolBlock_OfName = "of block";
	public static final String SchoolBlock_ANameAdjective = "a block";
	public static final String SchoolBlock_NameAdjectiveSingular = "block";
	public static final String SchoolBlock_NameAdjectivePlural = "blocks";
	public static final String SchoolBlock_Color = "indigo";
	public static final String SchoolBlock_IconGroup = "regular";
	public static final String SchoolBlock_IconName = "bell";

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Find the entity blockKey in Solr</a>
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
		this.blockKey = SchoolBlock.staticSetBlockKey(siteRequest_, o);
		this.blockKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetBlockKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrBlockKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrBlockKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockKey(siteRequest_, SchoolBlock.staticSolrBlockKey(siteRequest_, SchoolBlock.staticSetBlockKey(siteRequest_, o)));
	}

	public Long solrBlockKey() {
		return SchoolBlock.staticSolrBlockKey(siteRequest_, blockKey);
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
		return "key";
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Find the entity childKey in Solr</a>
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
		this.childKey = SchoolBlock.staticSetChildKey(siteRequest_, o);
		this.childKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetChildKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolBlock childKeyInit() {
		if(!childKeyWrap.alreadyInitialized) {
			_childKey(childKeyWrap);
			if(childKey == null)
				setChildKey(childKeyWrap.o);
		}
		childKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Long staticSolrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrChildKey(siteRequest_, SchoolBlock.staticSolrChildKey(siteRequest_, SchoolBlock.staticSetChildKey(siteRequest_, o)));
	}

	public Long solrChildKey() {
		return SchoolBlock.staticSolrChildKey(siteRequest_, childKey);
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
		return "child";
	}

	public String htmTooltipChildKey() {
		return null;
	}

	public String htmChildKey() {
		return childKey == null ? "" : StringEscapeUtils.escapeHtml4(strChildKey());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Find the entity enrollmentKeys in Solr</a>
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
		Long l = SchoolBlock.staticSetEnrollmentKeys(siteRequest_, o);
		if(l != null)
			addEnrollmentKeys(l);
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
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

	public static Long staticSolrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrEnrollmentKeys(siteRequest_, SchoolBlock.staticSolrEnrollmentKeys(siteRequest_, SchoolBlock.staticSetEnrollmentKeys(siteRequest_, o)));
	}

	public List<Long> solrEnrollmentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : enrollmentKeys) {
			l.add(SchoolBlock.staticSolrEnrollmentKeys(siteRequest_, o));
		}
		return l;
	}

	public String strEnrollmentKeys() {
		return enrollmentKeys == null ? "" : enrollmentKeys.toString();
	}

	public List<Long> sqlEnrollmentKeys() {
		return enrollmentKeys;
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
		SchoolBlock s = (SchoolBlock)this;
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
				a("oninput", "suggestSchoolBlockEnrollmentKeys($(this).val() ? [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,enrollmentCompleteName' } ] : [", pk == null ? "" : "{'name':'fq','value':'blockKeys:" + pk + "'}", "], $('#listSchoolBlockEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
		}
	}

	public void htmEnrollmentKeys(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockEnrollmentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enrollment?fq=blockKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("enrollments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate enrollments to this block");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolBlockEnrollmentKeys_", classApiMethodMethod).f();
								} g("ul");
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classApiMethodMethod, "_enrollmentKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ blockKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKey">Find the entity enrollmentKey in Solr</a>
	 * <br/>
	 * @param o is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentKey(Wrap<Long> o);

	public Long getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(Long enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public void setEnrollmentKey(String o) {
		this.enrollmentKey = SchoolBlock.staticSetEnrollmentKey(siteRequest_, o);
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolBlock enrollmentKeyInit() {
		if(!enrollmentKeyWrap.alreadyInitialized) {
			_enrollmentKey(enrollmentKeyWrap);
			if(enrollmentKey == null)
				setEnrollmentKey(enrollmentKeyWrap.o);
		}
		enrollmentKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Long staticSolrEnrollmentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrEnrollmentKey(siteRequest_, SchoolBlock.staticSolrEnrollmentKey(siteRequest_, SchoolBlock.staticSetEnrollmentKey(siteRequest_, o)));
	}

	public Long solrEnrollmentKey() {
		return SchoolBlock.staticSolrEnrollmentKey(siteRequest_, enrollmentKey);
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
		return null;
	}

	public String htmTooltipEnrollmentKey() {
		return null;
	}

	public String htmEnrollmentKey() {
		return enrollmentKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentKey());
	}

	/////////////////////////
	// enrollmentAttribute //
	/////////////////////////

	/**	 The entity enrollmentAttribute
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enrollmentAttribute;
	@JsonIgnore
	public Wrap<Boolean> enrollmentAttributeWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentAttribute").o(enrollmentAttribute);

	/**	<br/> The entity enrollmentAttribute
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentAttribute">Find the entity enrollmentAttribute in Solr</a>
	 * <br/>
	 * @param o is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentAttribute(Wrap<Boolean> o);

	public Boolean getEnrollmentAttribute() {
		return enrollmentAttribute;
	}

	public void setEnrollmentAttribute(Boolean enrollmentAttribute) {
		this.enrollmentAttribute = enrollmentAttribute;
		this.enrollmentAttributeWrap.alreadyInitialized = true;
	}
	public void setEnrollmentAttribute(String o) {
		this.enrollmentAttribute = SchoolBlock.staticSetEnrollmentAttribute(siteRequest_, o);
		this.enrollmentAttributeWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetEnrollmentAttribute(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolBlock enrollmentAttributeInit() {
		if(!enrollmentAttributeWrap.alreadyInitialized) {
			_enrollmentAttribute(enrollmentAttributeWrap);
			if(enrollmentAttribute == null)
				setEnrollmentAttribute(enrollmentAttributeWrap.o);
		}
		enrollmentAttributeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Boolean staticSolrEnrollmentAttribute(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEnrollmentAttribute(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentAttribute(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrEnrollmentAttribute(siteRequest_, SchoolBlock.staticSolrEnrollmentAttribute(siteRequest_, SchoolBlock.staticSetEnrollmentAttribute(siteRequest_, o)));
	}

	public Boolean solrEnrollmentAttribute() {
		return SchoolBlock.staticSolrEnrollmentAttribute(siteRequest_, enrollmentAttribute);
	}

	public String strEnrollmentAttribute() {
		return enrollmentAttribute == null ? "" : enrollmentAttribute.toString();
	}

	public Boolean sqlEnrollmentAttribute() {
		return enrollmentAttribute;
	}

	public String jsonEnrollmentAttribute() {
		return enrollmentAttribute == null ? "" : enrollmentAttribute.toString();
	}

	public String nomAffichageEnrollmentAttribute() {
		return null;
	}

	public String htmTooltipEnrollmentAttribute() {
		return null;
	}

	public String htmEnrollmentAttribute() {
		return enrollmentAttribute == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentAttribute());
	}

	///////////////////
	// educationSort //
	///////////////////

	/**	 The entity educationSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer educationSort;
	@JsonIgnore
	public Wrap<Integer> educationSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("educationSort").o(educationSort);

	/**	<br/> The entity educationSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Find the entity educationSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _educationSort(Wrap<Integer> c);

	public Integer getEducationSort() {
		return educationSort;
	}

	public void setEducationSort(Integer educationSort) {
		this.educationSort = educationSort;
		this.educationSortWrap.alreadyInitialized = true;
	}
	public void setEducationSort(String o) {
		this.educationSort = SchoolBlock.staticSetEducationSort(siteRequest_, o);
		this.educationSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetEducationSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEducationSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrEducationSort(siteRequest_, SchoolBlock.staticSolrEducationSort(siteRequest_, SchoolBlock.staticSetEducationSort(siteRequest_, o)));
	}

	public Integer solrEducationSort() {
		return SchoolBlock.staticSolrEducationSort(siteRequest_, educationSort);
	}

	public String strEducationSort() {
		return educationSort == null ? "" : educationSort.toString();
	}

	public Integer sqlEducationSort() {
		return educationSort;
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

	////////////////
	// schoolSort //
	////////////////

	/**	 The entity schoolSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer schoolSort;
	@JsonIgnore
	public Wrap<Integer> schoolSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolSort").o(schoolSort);

	/**	<br/> The entity schoolSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Find the entity schoolSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolSort(Wrap<Integer> c);

	public Integer getSchoolSort() {
		return schoolSort;
	}

	public void setSchoolSort(Integer schoolSort) {
		this.schoolSort = schoolSort;
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public void setSchoolSort(String o) {
		this.schoolSort = SchoolBlock.staticSetSchoolSort(siteRequest_, o);
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolSort(siteRequest_, SchoolBlock.staticSolrSchoolSort(siteRequest_, SchoolBlock.staticSetSchoolSort(siteRequest_, o)));
	}

	public Integer solrSchoolSort() {
		return SchoolBlock.staticSolrSchoolSort(siteRequest_, schoolSort);
	}

	public String strSchoolSort() {
		return schoolSort == null ? "" : schoolSort.toString();
	}

	public Integer sqlSchoolSort() {
		return schoolSort;
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

	//////////////
	// yearSort //
	//////////////

	/**	 The entity yearSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearSort;
	@JsonIgnore
	public Wrap<Integer> yearSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearSort").o(yearSort);

	/**	<br/> The entity yearSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Find the entity yearSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearSort(Wrap<Integer> c);

	public Integer getYearSort() {
		return yearSort;
	}

	public void setYearSort(Integer yearSort) {
		this.yearSort = yearSort;
		this.yearSortWrap.alreadyInitialized = true;
	}
	public void setYearSort(String o) {
		this.yearSort = SchoolBlock.staticSetYearSort(siteRequest_, o);
		this.yearSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrYearSort(siteRequest_, SchoolBlock.staticSolrYearSort(siteRequest_, SchoolBlock.staticSetYearSort(siteRequest_, o)));
	}

	public Integer solrYearSort() {
		return SchoolBlock.staticSolrYearSort(siteRequest_, yearSort);
	}

	public String strYearSort() {
		return yearSort == null ? "" : yearSort.toString();
	}

	public Integer sqlYearSort() {
		return yearSort;
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

	////////////////
	// seasonSort //
	////////////////

	/**	 The entity seasonSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer seasonSort;
	@JsonIgnore
	public Wrap<Integer> seasonSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("seasonSort").o(seasonSort);

	/**	<br/> The entity seasonSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Find the entity seasonSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonSort(Wrap<Integer> c);

	public Integer getSeasonSort() {
		return seasonSort;
	}

	public void setSeasonSort(Integer seasonSort) {
		this.seasonSort = seasonSort;
		this.seasonSortWrap.alreadyInitialized = true;
	}
	public void setSeasonSort(String o) {
		this.seasonSort = SchoolBlock.staticSetSeasonSort(siteRequest_, o);
		this.seasonSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSeasonSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSeasonSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSeasonSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSeasonSort(siteRequest_, SchoolBlock.staticSolrSeasonSort(siteRequest_, SchoolBlock.staticSetSeasonSort(siteRequest_, o)));
	}

	public Integer solrSeasonSort() {
		return SchoolBlock.staticSolrSeasonSort(siteRequest_, seasonSort);
	}

	public String strSeasonSort() {
		return seasonSort == null ? "" : seasonSort.toString();
	}

	public Integer sqlSeasonSort() {
		return seasonSort;
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

	/////////////////
	// sessionSort //
	/////////////////

	/**	 The entity sessionSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sessionSort;
	@JsonIgnore
	public Wrap<Integer> sessionSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sessionSort").o(sessionSort);

	/**	<br/> The entity sessionSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSort">Find the entity sessionSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionSort(Wrap<Integer> c);

	public Integer getSessionSort() {
		return sessionSort;
	}

	public void setSessionSort(Integer sessionSort) {
		this.sessionSort = sessionSort;
		this.sessionSortWrap.alreadyInitialized = true;
	}
	public void setSessionSort(String o) {
		this.sessionSort = SchoolBlock.staticSetSessionSort(siteRequest_, o);
		this.sessionSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSessionSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSessionSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSessionSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSessionSort(siteRequest_, SchoolBlock.staticSolrSessionSort(siteRequest_, SchoolBlock.staticSetSessionSort(siteRequest_, o)));
	}

	public Integer solrSessionSort() {
		return SchoolBlock.staticSolrSessionSort(siteRequest_, sessionSort);
	}

	public String strSessionSort() {
		return sessionSort == null ? "" : sessionSort.toString();
	}

	public Integer sqlSessionSort() {
		return sessionSort;
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

	/////////////
	// ageSort //
	/////////////

	/**	 The entity ageSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageSort;
	@JsonIgnore
	public Wrap<Integer> ageSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageSort").o(ageSort);

	/**	<br/> The entity ageSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSort">Find the entity ageSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageSort(Wrap<Integer> c);

	public Integer getAgeSort() {
		return ageSort;
	}

	public void setAgeSort(Integer ageSort) {
		this.ageSort = ageSort;
		this.ageSortWrap.alreadyInitialized = true;
	}
	public void setAgeSort(String o) {
		this.ageSort = SchoolBlock.staticSetAgeSort(siteRequest_, o);
		this.ageSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrAgeSort(siteRequest_, SchoolBlock.staticSolrAgeSort(siteRequest_, SchoolBlock.staticSetAgeSort(siteRequest_, o)));
	}

	public Integer solrAgeSort() {
		return SchoolBlock.staticSolrAgeSort(siteRequest_, ageSort);
	}

	public String strAgeSort() {
		return ageSort == null ? "" : ageSort.toString();
	}

	public Integer sqlAgeSort() {
		return ageSort;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Find the entity ageKey in Solr</a>
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
		this.ageKey = SchoolBlock.staticSetAgeKey(siteRequest_, o);
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetAgeKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrAgeKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrAgeKey(siteRequest_, SchoolBlock.staticSolrAgeKey(siteRequest_, SchoolBlock.staticSetAgeKey(siteRequest_, o)));
	}

	public Long solrAgeKey() {
		return SchoolBlock.staticSolrAgeKey(siteRequest_, ageKey);
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
		return "age";
	}

	public String htmTooltipAgeKey() {
		return null;
	}

	public String htmAgeKey() {
		return ageKey == null ? "" : StringEscapeUtils.escapeHtml4(strAgeKey());
	}

	public void inputAgeKey(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_ageKey_clear")
						.a("class", "ageKey_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_ageKey_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "age")
				.a("class", "valueObjectSuggest suggestAgeKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setAgeKey")
				.a("id", classApiMethodMethod, "_ageKey")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolBlockAgeKey($(this).val() ? [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,ageCompleteName' } ] : [", pk == null ? "" : "{'name':'fq','value':'blockKeys:" + pk + "'}", "], $('#listSchoolBlockAgeKey_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
		}
	}

	public void htmAgeKey(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockAgeKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/age?fq=blockKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue w3-hover-blue ").f();
								e("i").a("class", "fad fa-birthday-cake ").f().g("i");
								sx("age");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate an age to this block");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputAgeKey(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolBlockAgeKey_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolAge.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolAge.ROLES)
										) {
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
												.a("id", classApiMethodMethod, "_ageKey_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolAgeVals({ blockKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "ageKey')); });")
												.f().sx("add an age")
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

	///////////////
	// ageSearch //
	///////////////

	/**	 The entity ageSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolAge>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolAge> ageSearch = new SearchList<SchoolAge>();
	@JsonIgnore
	public Wrap<SearchList<SchoolAge>> ageSearchWrap = new Wrap<SearchList<SchoolAge>>().p(this).c(SearchList.class).var("ageSearch").o(ageSearch);

	/**	<br/> The entity ageSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolAge>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSearch">Find the entity ageSearch in Solr</a>
	 * <br/>
	 * @param ageSearch is the entity already constructed. 
	 **/
	protected abstract void _ageSearch(SearchList<SchoolAge> l);

	public SearchList<SchoolAge> getAgeSearch() {
		return ageSearch;
	}

	public void setAgeSearch(SearchList<SchoolAge> ageSearch) {
		this.ageSearch = ageSearch;
		this.ageSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolAge> staticSetAgeSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SchoolBlock ageSearchInit() {
		if(!ageSearchWrap.alreadyInitialized) {
			_ageSearch(ageSearch);
		}
		ageSearch.initDeepForClass(siteRequest_);
		ageSearchWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	//////////
	// age_ //
	//////////

	/**	 The entity age_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SchoolAge age_;
	@JsonIgnore
	public Wrap<SchoolAge> age_Wrap = new Wrap<SchoolAge>().p(this).c(SchoolAge.class).var("age_").o(age_);

	/**	<br/> The entity age_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:age_">Find the entity age_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _age_(Wrap<SchoolAge> c);

	public SchoolAge getAge_() {
		return age_;
	}

	public void setAge_(SchoolAge age_) {
		this.age_ = age_;
		this.age_Wrap.alreadyInitialized = true;
	}
	public static SchoolAge staticSetAge_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SchoolBlock age_Init() {
		if(!age_Wrap.alreadyInitialized) {
			_age_(age_Wrap);
			if(age_ == null)
				setAge_(age_Wrap.o);
		}
		age_Wrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
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
		this.schoolKey = SchoolBlock.staticSetSchoolKey(siteRequest_, o);
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolKey(siteRequest_, SchoolBlock.staticSolrSchoolKey(siteRequest_, SchoolBlock.staticSetSchoolKey(siteRequest_, o)));
	}

	public Long solrSchoolKey() {
		return SchoolBlock.staticSolrSchoolKey(siteRequest_, schoolKey);
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
		return "school";
	}

	public String htmTooltipSchoolKey() {
		return null;
	}

	public String htmSchoolKey() {
		return schoolKey == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKey());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Find the entity yearKey in Solr</a>
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
		this.yearKey = SchoolBlock.staticSetYearKey(siteRequest_, o);
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolBlock yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Long staticSolrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrYearKey(siteRequest_, SchoolBlock.staticSolrYearKey(siteRequest_, SchoolBlock.staticSetYearKey(siteRequest_, o)));
	}

	public Long solrYearKey() {
		return SchoolBlock.staticSolrYearKey(siteRequest_, yearKey);
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
		return "year";
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Find the entity sessionKey in Solr</a>
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
		this.sessionKey = SchoolBlock.staticSetSessionKey(siteRequest_, o);
		this.sessionKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSessionKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSessionKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSessionKey(siteRequest_, SchoolBlock.staticSolrSessionKey(siteRequest_, SchoolBlock.staticSetSessionKey(siteRequest_, o)));
	}

	public Long solrSessionKey() {
		return SchoolBlock.staticSolrSessionKey(siteRequest_, sessionKey);
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
		return "session";
	}

	public String htmTooltipSessionKey() {
		return null;
	}

	public String htmSessionKey() {
		return sessionKey == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKey());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Find the entity schoolName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String o) {
		this.schoolName = SchoolBlock.staticSetSchoolName(siteRequest_, o);
		this.schoolNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolName(siteRequest_, SchoolBlock.staticSolrSchoolName(siteRequest_, SchoolBlock.staticSetSchoolName(siteRequest_, o)));
	}

	public String solrSchoolName() {
		return SchoolBlock.staticSolrSchoolName(siteRequest_, schoolName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Find the entity schoolCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}
	public void setSchoolCompleteName(String o) {
		this.schoolCompleteName = SchoolBlock.staticSetSchoolCompleteName(siteRequest_, o);
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolCompleteName(siteRequest_, SchoolBlock.staticSolrSchoolCompleteName(siteRequest_, SchoolBlock.staticSetSchoolCompleteName(siteRequest_, o)));
	}

	public String solrSchoolCompleteName() {
		return SchoolBlock.staticSolrSchoolCompleteName(siteRequest_, schoolCompleteName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Find the entity schoolLocation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String o) {
		this.schoolLocation = SchoolBlock.staticSetSchoolLocation(siteRequest_, o);
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolLocation(siteRequest_, SchoolBlock.staticSolrSchoolLocation(siteRequest_, SchoolBlock.staticSetSchoolLocation(siteRequest_, o)));
	}

	public String solrSchoolLocation() {
		return SchoolBlock.staticSolrSchoolLocation(siteRequest_, schoolLocation);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String o) {
		this.schoolAddress = SchoolBlock.staticSetSchoolAddress(siteRequest_, o);
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolAddress(siteRequest_, SchoolBlock.staticSolrSchoolAddress(siteRequest_, SchoolBlock.staticSetSchoolAddress(siteRequest_, o)));
	}

	public String solrSchoolAddress() {
		return SchoolBlock.staticSolrSchoolAddress(siteRequest_, schoolAddress);
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
		return "address";
	}

	public String htmTooltipSchoolAddress() {
		return null;
	}

	public String htmSchoolAddress() {
		return schoolAddress == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAddress());
	}

	public void inputSchoolAddress(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "address")
				.a("id", classApiMethodMethod, "_schoolAddress");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setSchoolAddress classSchoolBlock inputSchoolBlock", pk, "SchoolAddress w3-input w3-border ");
					a("name", "setSchoolAddress");
				} else {
					a("class", "valueSchoolAddress w3-input w3-border classSchoolBlock inputSchoolBlock", pk, "SchoolAddress w3-input w3-border ");
					a("name", "schoolAddress");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSchoolAddress', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ");
				}
				a("value", strSchoolAddress())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolBlock", pk, "SchoolAddress ").f().sx(htmSchoolAddress()).g("span");
			}
		}
	}

	public void htmSchoolAddress(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockSchoolAddress").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_schoolAddress").a("class", "").f().sx("address").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSchoolAddress(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_schoolAddress')); $('#", classApiMethodMethod, "_schoolAddress').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setSchoolAddress', null, function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String o) {
		this.schoolPhoneNumber = SchoolBlock.staticSetSchoolPhoneNumber(siteRequest_, o);
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolPhoneNumber(siteRequest_, SchoolBlock.staticSolrSchoolPhoneNumber(siteRequest_, SchoolBlock.staticSetSchoolPhoneNumber(siteRequest_, o)));
	}

	public String solrSchoolPhoneNumber() {
		return SchoolBlock.staticSolrSchoolPhoneNumber(siteRequest_, schoolPhoneNumber);
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
		return "phone number";
	}

	public String htmTooltipSchoolPhoneNumber() {
		return null;
	}

	public String htmSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolPhoneNumber());
	}

	////////////////
	// schoolForm //
	////////////////

	/**	 The entity schoolForm
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolForm;
	@JsonIgnore
	public Wrap<String> schoolFormWrap = new Wrap<String>().p(this).c(String.class).var("schoolForm").o(schoolForm);

	/**	<br/> The entity schoolForm
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolForm">Find the entity schoolForm in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolForm(Wrap<String> c);

	public String getSchoolForm() {
		return schoolForm;
	}
	public void setSchoolForm(String o) {
		this.schoolForm = SchoolBlock.staticSetSchoolForm(siteRequest_, o);
		this.schoolFormWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock schoolFormInit() {
		if(!schoolFormWrap.alreadyInitialized) {
			_schoolForm(schoolFormWrap);
			if(schoolForm == null)
				setSchoolForm(schoolFormWrap.o);
		}
		schoolFormWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolForm(siteRequest_, SchoolBlock.staticSolrSchoolForm(siteRequest_, SchoolBlock.staticSetSchoolForm(siteRequest_, o)));
	}

	public String solrSchoolForm() {
		return SchoolBlock.staticSolrSchoolForm(siteRequest_, schoolForm);
	}

	public String strSchoolForm() {
		return schoolForm == null ? "" : schoolForm;
	}

	public String sqlSchoolForm() {
		return schoolForm;
	}

	public String jsonSchoolForm() {
		return schoolForm == null ? "" : schoolForm;
	}

	public String nomAffichageSchoolForm() {
		return null;
	}

	public String htmTooltipSchoolForm() {
		return null;
	}

	public String htmSchoolForm() {
		return schoolForm == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolForm());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNumber">Find the entity schoolNumber in Solr</a>
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
		this.schoolNumber = SchoolBlock.staticSetSchoolNumber(siteRequest_, o);
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolBlock schoolNumberInit() {
		if(!schoolNumberWrap.alreadyInitialized) {
			_schoolNumber(schoolNumberWrap);
			if(schoolNumber == null)
				setSchoolNumber(schoolNumberWrap.o);
		}
		schoolNumberWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Integer staticSolrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolNumber(siteRequest_, SchoolBlock.staticSolrSchoolNumber(siteRequest_, SchoolBlock.staticSetSchoolNumber(siteRequest_, o)));
	}

	public Integer solrSchoolNumber() {
		return SchoolBlock.staticSolrSchoolNumber(siteRequest_, schoolNumber);
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

	/////////////////////////////
	// schoolAdministratorName //
	/////////////////////////////

	/**	 The entity schoolAdministratorName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAdministratorName;
	@JsonIgnore
	public Wrap<String> schoolAdministratorNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolAdministratorName").o(schoolAdministratorName);

	/**	<br/> The entity schoolAdministratorName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Find the entity schoolAdministratorName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}
	public void setSchoolAdministratorName(String o) {
		this.schoolAdministratorName = SchoolBlock.staticSetSchoolAdministratorName(siteRequest_, o);
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSchoolAdministratorName(siteRequest_, SchoolBlock.staticSolrSchoolAdministratorName(siteRequest_, SchoolBlock.staticSetSchoolAdministratorName(siteRequest_, o)));
	}

	public String solrSchoolAdministratorName() {
		return SchoolBlock.staticSolrSchoolAdministratorName(siteRequest_, schoolAdministratorName);
	}

	public String strSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String sqlSchoolAdministratorName() {
		return schoolAdministratorName;
	}

	public String jsonSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String nomAffichageSchoolAdministratorName() {
		return "administrator of the school";
	}

	public String htmTooltipSchoolAdministratorName() {
		return null;
	}

	public String htmSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAdministratorName());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Find the entity yearStart in Solr</a>
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
		this.yearStart = SchoolBlock.staticSetYearStart(siteRequest_, o);
		this.yearStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrYearStart(siteRequest_, SchoolBlock.staticSolrYearStart(siteRequest_, SchoolBlock.staticSetYearStart(siteRequest_, o)));
	}

	public Integer solrYearStart() {
		return SchoolBlock.staticSolrYearStart(siteRequest_, yearStart);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Find the entity yearEnd in Solr</a>
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
		this.yearEnd = SchoolBlock.staticSetYearEnd(siteRequest_, o);
		this.yearEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrYearEnd(siteRequest_, SchoolBlock.staticSolrYearEnd(siteRequest_, SchoolBlock.staticSetYearEnd(siteRequest_, o)));
	}

	public Integer solrYearEnd() {
		return SchoolBlock.staticSolrYearEnd(siteRequest_, yearEnd);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Find the entity seasonStartDate in Solr</a>
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
		this.seasonStartDate = SchoolBlock.staticSetSeasonStartDate(siteRequest_, o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	protected SchoolBlock seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Date staticSolrSeasonStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSeasonStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSeasonStartDate(siteRequest_, SchoolBlock.staticSolrSeasonStartDate(siteRequest_, SchoolBlock.staticSetSeasonStartDate(siteRequest_, o)));
	}

	public Date solrSeasonStartDate() {
		return SchoolBlock.staticSolrSeasonStartDate(siteRequest_, seasonStartDate);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Find the entity yearEnrollmentFee in Solr</a>
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
		this.yearEnrollmentFee = SchoolBlock.staticSetYearEnrollmentFee(siteRequest_, o);
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
	protected SchoolBlock yearEnrollmentFeeInit() {
		if(!yearEnrollmentFeeWrap.alreadyInitialized) {
			_yearEnrollmentFee(yearEnrollmentFeeWrap);
			if(yearEnrollmentFee == null)
				setYearEnrollmentFee(yearEnrollmentFeeWrap.o);
		}
		yearEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Double staticSolrYearEnrollmentFee(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrYearEnrollmentFee(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnrollmentFee(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrYearEnrollmentFee(siteRequest_, SchoolBlock.staticSolrYearEnrollmentFee(siteRequest_, SchoolBlock.staticSetYearEnrollmentFee(siteRequest_, o)));
	}

	public Double solrYearEnrollmentFee() {
		return SchoolBlock.staticSolrYearEnrollmentFee(siteRequest_, yearEnrollmentFee);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDate">Find the entity sessionStartDate in Solr</a>
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
		this.sessionStartDate = SchoolBlock.staticSetSessionStartDate(siteRequest_, o);
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionStartDate(Date o) {
		this.sessionStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	protected SchoolBlock sessionStartDateInit() {
		if(!sessionStartDateWrap.alreadyInitialized) {
			_sessionStartDate(sessionStartDateWrap);
			if(sessionStartDate == null)
				setSessionStartDate(sessionStartDateWrap.o);
		}
		sessionStartDateWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Date staticSolrSessionStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSessionStartDate(siteRequest_, SchoolBlock.staticSolrSessionStartDate(siteRequest_, SchoolBlock.staticSetSessionStartDate(siteRequest_, o)));
	}

	public Date solrSessionStartDate() {
		return SchoolBlock.staticSolrSessionStartDate(siteRequest_, sessionStartDate);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDate">Find the entity sessionEndDate in Solr</a>
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
		this.sessionEndDate = SchoolBlock.staticSetSessionEndDate(siteRequest_, o);
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionEndDate(Date o) {
		this.sessionEndDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	protected SchoolBlock sessionEndDateInit() {
		if(!sessionEndDateWrap.alreadyInitialized) {
			_sessionEndDate(sessionEndDateWrap);
			if(sessionEndDate == null)
				setSessionEndDate(sessionEndDateWrap.o);
		}
		sessionEndDateWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Date staticSolrSessionEndDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionEndDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrSessionEndDate(siteRequest_, SchoolBlock.staticSolrSessionEndDate(siteRequest_, SchoolBlock.staticSetSessionEndDate(siteRequest_, o)));
	}

	public Date solrSessionEndDate() {
		return SchoolBlock.staticSolrSessionEndDate(siteRequest_, sessionEndDate);
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

	//////////////////
	// ageShortName //
	//////////////////

	/**	 The entity ageShortName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ageShortName;
	@JsonIgnore
	public Wrap<String> ageShortNameWrap = new Wrap<String>().p(this).c(String.class).var("ageShortName").o(ageShortName);

	/**	<br/> The entity ageShortName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageShortName">Find the entity ageShortName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageShortName(Wrap<String> c);

	public String getAgeShortName() {
		return ageShortName;
	}
	public void setAgeShortName(String o) {
		this.ageShortName = SchoolBlock.staticSetAgeShortName(siteRequest_, o);
		this.ageShortNameWrap.alreadyInitialized = true;
	}
	public static String staticSetAgeShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock ageShortNameInit() {
		if(!ageShortNameWrap.alreadyInitialized) {
			_ageShortName(ageShortNameWrap);
			if(ageShortName == null)
				setAgeShortName(ageShortNameWrap.o);
		}
		ageShortNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrAgeShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrAgeShortName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeShortName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrAgeShortName(siteRequest_, SchoolBlock.staticSolrAgeShortName(siteRequest_, SchoolBlock.staticSetAgeShortName(siteRequest_, o)));
	}

	public String solrAgeShortName() {
		return SchoolBlock.staticSolrAgeShortName(siteRequest_, ageShortName);
	}

	public String strAgeShortName() {
		return ageShortName == null ? "" : ageShortName;
	}

	public String sqlAgeShortName() {
		return ageShortName;
	}

	public String jsonAgeShortName() {
		return ageShortName == null ? "" : ageShortName;
	}

	public String nomAffichageAgeShortName() {
		return null;
	}

	public String htmTooltipAgeShortName() {
		return null;
	}

	public String htmAgeShortName() {
		return ageShortName == null ? "" : StringEscapeUtils.escapeHtml4(strAgeShortName());
	}

	/////////////////////
	// ageCompleteName //
	/////////////////////

	/**	 The entity ageCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ageCompleteName;
	@JsonIgnore
	public Wrap<String> ageCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("ageCompleteName").o(ageCompleteName);

	/**	<br/> The entity ageCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageCompleteName">Find the entity ageCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageCompleteName(Wrap<String> c);

	public String getAgeCompleteName() {
		return ageCompleteName;
	}
	public void setAgeCompleteName(String o) {
		this.ageCompleteName = SchoolBlock.staticSetAgeCompleteName(siteRequest_, o);
		this.ageCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock ageCompleteNameInit() {
		if(!ageCompleteNameWrap.alreadyInitialized) {
			_ageCompleteName(ageCompleteNameWrap);
			if(ageCompleteName == null)
				setAgeCompleteName(ageCompleteNameWrap.o);
		}
		ageCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrAgeCompleteName(siteRequest_, SchoolBlock.staticSolrAgeCompleteName(siteRequest_, SchoolBlock.staticSetAgeCompleteName(siteRequest_, o)));
	}

	public String solrAgeCompleteName() {
		return SchoolBlock.staticSolrAgeCompleteName(siteRequest_, ageCompleteName);
	}

	public String strAgeCompleteName() {
		return ageCompleteName == null ? "" : ageCompleteName;
	}

	public String sqlAgeCompleteName() {
		return ageCompleteName;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Find the entity ageStart in Solr</a>
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
		this.ageStart = SchoolBlock.staticSetAgeStart(siteRequest_, o);
		this.ageStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrAgeStart(siteRequest_, SchoolBlock.staticSolrAgeStart(siteRequest_, SchoolBlock.staticSetAgeStart(siteRequest_, o)));
	}

	public Integer solrAgeStart() {
		return SchoolBlock.staticSolrAgeStart(siteRequest_, ageStart);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Find the entity ageEnd in Solr</a>
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
		this.ageEnd = SchoolBlock.staticSetAgeEnd(siteRequest_, o);
		this.ageEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrAgeEnd(siteRequest_, SchoolBlock.staticSolrAgeEnd(siteRequest_, SchoolBlock.staticSetAgeEnd(siteRequest_, o)));
	}

	public Integer solrAgeEnd() {
		return SchoolBlock.staticSolrAgeEnd(siteRequest_, ageEnd);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockStartTime">Find the entity blockStartTime in Solr</a>
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
		this.blockStartTime = SchoolBlock.staticSetBlockStartTime(siteRequest_, o);
		this.blockStartTimeWrap.alreadyInitialized = true;
	}
	public static LocalTime staticSetBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
	}
	protected SchoolBlock blockStartTimeInit() {
		if(!blockStartTimeWrap.alreadyInitialized) {
			_blockStartTime(blockStartTimeWrap);
			if(blockStartTime == null)
				setBlockStartTime(blockStartTimeWrap.o);
		}
		blockStartTimeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrBlockStartTime(SiteRequestEnUS siteRequest_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockStartTime(siteRequest_, SchoolBlock.staticSolrBlockStartTime(siteRequest_, SchoolBlock.staticSetBlockStartTime(siteRequest_, o)));
	}

	public String solrBlockStartTime() {
		return SchoolBlock.staticSolrBlockStartTime(siteRequest_, blockStartTime);
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

	public void inputBlockStartTime(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border timepicker setBlockStartTime classSchoolBlock inputSchoolBlock", pk, "BlockStartTime w3-input w3-border ")
					.a("placeholder", "HH:MM A")
					.a("id", classApiMethodMethod, "_blockStartTime")
					.a("value", blockStartTime == null ? "" : DateTimeFormatter.ofPattern("h:mm a").format(blockStartTime));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'h:mm a'); if(t) { var s = t.format('HH:mm'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockStartTime', s, function() { addGlow($('#", classApiMethodMethod, "_blockStartTime')); }, function() { addError($('#", classApiMethodMethod, "_blockStartTime')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolBlock", pk, "BlockStartTime ").f().sx(htmBlockStartTime()).g("span");
			}
		}
	}

	public void htmBlockStartTime(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockStartTime").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockStartTime").a("class", "").f().sx("start time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputBlockStartTime(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockStartTime')); $('#", classApiMethodMethod, "_blockStartTime').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockStartTime', null, function() { addGlow($('#", classApiMethodMethod, "_blockStartTime')); }, function() { addError($('#", classApiMethodMethod, "_blockStartTime')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockEndTime">Find the entity blockEndTime in Solr</a>
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
		this.blockEndTime = SchoolBlock.staticSetBlockEndTime(siteRequest_, o);
		this.blockEndTimeWrap.alreadyInitialized = true;
	}
	public static LocalTime staticSetBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
	}
	protected SchoolBlock blockEndTimeInit() {
		if(!blockEndTimeWrap.alreadyInitialized) {
			_blockEndTime(blockEndTimeWrap);
			if(blockEndTime == null)
				setBlockEndTime(blockEndTimeWrap.o);
		}
		blockEndTimeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrBlockEndTime(SiteRequestEnUS siteRequest_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockEndTime(siteRequest_, SchoolBlock.staticSolrBlockEndTime(siteRequest_, SchoolBlock.staticSetBlockEndTime(siteRequest_, o)));
	}

	public String solrBlockEndTime() {
		return SchoolBlock.staticSolrBlockEndTime(siteRequest_, blockEndTime);
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

	public void inputBlockEndTime(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border timepicker setBlockEndTime classSchoolBlock inputSchoolBlock", pk, "BlockEndTime w3-input w3-border ")
					.a("placeholder", "HH:MM A")
					.a("id", classApiMethodMethod, "_blockEndTime")
					.a("value", blockEndTime == null ? "" : DateTimeFormatter.ofPattern("h:mm a").format(blockEndTime));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'h:mm a'); if(t) { var s = t.format('HH:mm'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockEndTime', s, function() { addGlow($('#", classApiMethodMethod, "_blockEndTime')); }, function() { addError($('#", classApiMethodMethod, "_blockEndTime')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolBlock", pk, "BlockEndTime ").f().sx(htmBlockEndTime()).g("span");
			}
		}
	}

	public void htmBlockEndTime(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockEndTime").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockEndTime").a("class", "").f().sx("end time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputBlockEndTime(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockEndTime')); $('#", classApiMethodMethod, "_blockEndTime').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockEndTime', null, function() { addGlow($('#", classApiMethodMethod, "_blockEndTime')); }, function() { addError($('#", classApiMethodMethod, "_blockEndTime')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Find the entity blockPricePerMonth in Solr</a>
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
		this.blockPricePerMonth = SchoolBlock.staticSetBlockPricePerMonth(siteRequest_, o);
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
	protected SchoolBlock blockPricePerMonthInit() {
		if(!blockPricePerMonthWrap.alreadyInitialized) {
			_blockPricePerMonth(blockPricePerMonthWrap);
			if(blockPricePerMonth == null)
				setBlockPricePerMonth(blockPricePerMonthWrap.o);
		}
		blockPricePerMonthWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Double staticSolrBlockPricePerMonth(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlockPricePerMonth(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockPricePerMonth(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockPricePerMonth(siteRequest_, SchoolBlock.staticSolrBlockPricePerMonth(siteRequest_, SchoolBlock.staticSetBlockPricePerMonth(siteRequest_, o)));
	}

	public Double solrBlockPricePerMonth() {
		return SchoolBlock.staticSolrBlockPricePerMonth(siteRequest_, blockPricePerMonth);
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

	public void inputBlockPricePerMonth(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "price per month")
				.a("id", classApiMethodMethod, "_blockPricePerMonth");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setBlockPricePerMonth classSchoolBlock inputSchoolBlock", pk, "BlockPricePerMonth w3-input w3-border ");
					a("name", "setBlockPricePerMonth");
				} else {
					a("class", "valueBlockPricePerMonth w3-input w3-border classSchoolBlock inputSchoolBlock", pk, "BlockPricePerMonth w3-input w3-border ");
					a("name", "blockPricePerMonth");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockPricePerMonth', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); }, function() { addError($('#", classApiMethodMethod, "_blockPricePerMonth')); }); ");
				}
				a("value", strBlockPricePerMonth())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolBlock", pk, "BlockPricePerMonth ").f().sx(htmBlockPricePerMonth()).g("span");
			}
		}
	}

	public void htmBlockPricePerMonth(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockPricePerMonth").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockPricePerMonth").a("class", "").f().sx("price per month").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockPricePerMonth(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); $('#", classApiMethodMethod, "_blockPricePerMonth').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockPricePerMonth', null, function() { addGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); }, function() { addError($('#", classApiMethodMethod, "_blockPricePerMonth')); }); ")
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
	// blockSunday //
	/////////////////

	/**	 The entity blockSunday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockSunday;
	@JsonIgnore
	public Wrap<Boolean> blockSundayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSunday").o(blockSunday);

	/**	<br/> The entity blockSunday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSunday">Find the entity blockSunday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockSunday(Wrap<Boolean> c);

	public Boolean getBlockSunday() {
		return blockSunday;
	}

	public void setBlockSunday(Boolean blockSunday) {
		this.blockSunday = blockSunday;
		this.blockSundayWrap.alreadyInitialized = true;
	}
	public void setBlockSunday(String o) {
		this.blockSunday = SchoolBlock.staticSetBlockSunday(siteRequest_, o);
		this.blockSundayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockSunday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockSunday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockSunday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockSunday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockSunday(siteRequest_, SchoolBlock.staticSolrBlockSunday(siteRequest_, SchoolBlock.staticSetBlockSunday(siteRequest_, o)));
	}

	public Boolean solrBlockSunday() {
		return SchoolBlock.staticSolrBlockSunday(siteRequest_, blockSunday);
	}

	public String strBlockSunday() {
		return blockSunday == null ? "" : blockSunday.toString();
	}

	public Boolean sqlBlockSunday() {
		return blockSunday;
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

	/////////////////
	// blockMonday //
	/////////////////

	/**	 The entity blockMonday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockMonday;
	@JsonIgnore
	public Wrap<Boolean> blockMondayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockMonday").o(blockMonday);

	/**	<br/> The entity blockMonday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockMonday">Find the entity blockMonday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockMonday(Wrap<Boolean> c);

	public Boolean getBlockMonday() {
		return blockMonday;
	}

	public void setBlockMonday(Boolean blockMonday) {
		this.blockMonday = blockMonday;
		this.blockMondayWrap.alreadyInitialized = true;
	}
	public void setBlockMonday(String o) {
		this.blockMonday = SchoolBlock.staticSetBlockMonday(siteRequest_, o);
		this.blockMondayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockMonday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockMonday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockMonday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockMonday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockMonday(siteRequest_, SchoolBlock.staticSolrBlockMonday(siteRequest_, SchoolBlock.staticSetBlockMonday(siteRequest_, o)));
	}

	public Boolean solrBlockMonday() {
		return SchoolBlock.staticSolrBlockMonday(siteRequest_, blockMonday);
	}

	public String strBlockMonday() {
		return blockMonday == null ? "" : blockMonday.toString();
	}

	public Boolean sqlBlockMonday() {
		return blockMonday;
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

	public void inputBlockMonday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_blockMonday")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_blockMonday");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockMonday classSchoolBlock inputSchoolBlock", pk, "BlockMonday w3-input w3-border ");
				a("name", "setBlockMonday");
			} else {
				a("class", "valueBlockMonday classSchoolBlock inputSchoolBlock", pk, "BlockMonday w3-input w3-border ");
				a("name", "blockMonday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockMonday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockMonday')); }, function() { addError($('#", classApiMethodMethod, "_blockMonday')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getBlockMonday() != null && getBlockMonday())
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
				e("span").a("class", "varSchoolBlock", pk, "BlockMonday ").f().sx(htmBlockMonday()).g("span");
			}
		}
	}

	public void htmBlockMonday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockMonday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockMonday").a("class", "").f().sx("monday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockMonday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// blockTuesday //
	//////////////////

	/**	 The entity blockTuesday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockTuesday;
	@JsonIgnore
	public Wrap<Boolean> blockTuesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockTuesday").o(blockTuesday);

	/**	<br/> The entity blockTuesday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTuesday">Find the entity blockTuesday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockTuesday(Wrap<Boolean> c);

	public Boolean getBlockTuesday() {
		return blockTuesday;
	}

	public void setBlockTuesday(Boolean blockTuesday) {
		this.blockTuesday = blockTuesday;
		this.blockTuesdayWrap.alreadyInitialized = true;
	}
	public void setBlockTuesday(String o) {
		this.blockTuesday = SchoolBlock.staticSetBlockTuesday(siteRequest_, o);
		this.blockTuesdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockTuesday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockTuesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockTuesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockTuesday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockTuesday(siteRequest_, SchoolBlock.staticSolrBlockTuesday(siteRequest_, SchoolBlock.staticSetBlockTuesday(siteRequest_, o)));
	}

	public Boolean solrBlockTuesday() {
		return SchoolBlock.staticSolrBlockTuesday(siteRequest_, blockTuesday);
	}

	public String strBlockTuesday() {
		return blockTuesday == null ? "" : blockTuesday.toString();
	}

	public Boolean sqlBlockTuesday() {
		return blockTuesday;
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

	public void inputBlockTuesday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_blockTuesday")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_blockTuesday");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockTuesday classSchoolBlock inputSchoolBlock", pk, "BlockTuesday w3-input w3-border ");
				a("name", "setBlockTuesday");
			} else {
				a("class", "valueBlockTuesday classSchoolBlock inputSchoolBlock", pk, "BlockTuesday w3-input w3-border ");
				a("name", "blockTuesday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockTuesday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockTuesday')); }, function() { addError($('#", classApiMethodMethod, "_blockTuesday')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getBlockTuesday() != null && getBlockTuesday())
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
				e("span").a("class", "varSchoolBlock", pk, "BlockTuesday ").f().sx(htmBlockTuesday()).g("span");
			}
		}
	}

	public void htmBlockTuesday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockTuesday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockTuesday").a("class", "").f().sx("tuesday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockTuesday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// blockWednesday //
	////////////////////

	/**	 The entity blockWednesday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockWednesday;
	@JsonIgnore
	public Wrap<Boolean> blockWednesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockWednesday").o(blockWednesday);

	/**	<br/> The entity blockWednesday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockWednesday">Find the entity blockWednesday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockWednesday(Wrap<Boolean> c);

	public Boolean getBlockWednesday() {
		return blockWednesday;
	}

	public void setBlockWednesday(Boolean blockWednesday) {
		this.blockWednesday = blockWednesday;
		this.blockWednesdayWrap.alreadyInitialized = true;
	}
	public void setBlockWednesday(String o) {
		this.blockWednesday = SchoolBlock.staticSetBlockWednesday(siteRequest_, o);
		this.blockWednesdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockWednesday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockWednesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockWednesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockWednesday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockWednesday(siteRequest_, SchoolBlock.staticSolrBlockWednesday(siteRequest_, SchoolBlock.staticSetBlockWednesday(siteRequest_, o)));
	}

	public Boolean solrBlockWednesday() {
		return SchoolBlock.staticSolrBlockWednesday(siteRequest_, blockWednesday);
	}

	public String strBlockWednesday() {
		return blockWednesday == null ? "" : blockWednesday.toString();
	}

	public Boolean sqlBlockWednesday() {
		return blockWednesday;
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

	public void inputBlockWednesday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_blockWednesday")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_blockWednesday");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockWednesday classSchoolBlock inputSchoolBlock", pk, "BlockWednesday w3-input w3-border ");
				a("name", "setBlockWednesday");
			} else {
				a("class", "valueBlockWednesday classSchoolBlock inputSchoolBlock", pk, "BlockWednesday w3-input w3-border ");
				a("name", "blockWednesday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockWednesday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockWednesday')); }, function() { addError($('#", classApiMethodMethod, "_blockWednesday')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getBlockWednesday() != null && getBlockWednesday())
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
				e("span").a("class", "varSchoolBlock", pk, "BlockWednesday ").f().sx(htmBlockWednesday()).g("span");
			}
		}
	}

	public void htmBlockWednesday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockWednesday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockWednesday").a("class", "").f().sx("wednesday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockWednesday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// blockThursday //
	///////////////////

	/**	 The entity blockThursday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockThursday;
	@JsonIgnore
	public Wrap<Boolean> blockThursdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockThursday").o(blockThursday);

	/**	<br/> The entity blockThursday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockThursday">Find the entity blockThursday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockThursday(Wrap<Boolean> c);

	public Boolean getBlockThursday() {
		return blockThursday;
	}

	public void setBlockThursday(Boolean blockThursday) {
		this.blockThursday = blockThursday;
		this.blockThursdayWrap.alreadyInitialized = true;
	}
	public void setBlockThursday(String o) {
		this.blockThursday = SchoolBlock.staticSetBlockThursday(siteRequest_, o);
		this.blockThursdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockThursday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockThursday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockThursday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockThursday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockThursday(siteRequest_, SchoolBlock.staticSolrBlockThursday(siteRequest_, SchoolBlock.staticSetBlockThursday(siteRequest_, o)));
	}

	public Boolean solrBlockThursday() {
		return SchoolBlock.staticSolrBlockThursday(siteRequest_, blockThursday);
	}

	public String strBlockThursday() {
		return blockThursday == null ? "" : blockThursday.toString();
	}

	public Boolean sqlBlockThursday() {
		return blockThursday;
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

	public void inputBlockThursday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_blockThursday")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_blockThursday");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockThursday classSchoolBlock inputSchoolBlock", pk, "BlockThursday w3-input w3-border ");
				a("name", "setBlockThursday");
			} else {
				a("class", "valueBlockThursday classSchoolBlock inputSchoolBlock", pk, "BlockThursday w3-input w3-border ");
				a("name", "blockThursday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockThursday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockThursday')); }, function() { addError($('#", classApiMethodMethod, "_blockThursday')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getBlockThursday() != null && getBlockThursday())
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
				e("span").a("class", "varSchoolBlock", pk, "BlockThursday ").f().sx(htmBlockThursday()).g("span");
			}
		}
	}

	public void htmBlockThursday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockThursday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockThursday").a("class", "").f().sx("thursday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockThursday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// blockFriday //
	/////////////////

	/**	 The entity blockFriday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockFriday;
	@JsonIgnore
	public Wrap<Boolean> blockFridayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockFriday").o(blockFriday);

	/**	<br/> The entity blockFriday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockFriday">Find the entity blockFriday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockFriday(Wrap<Boolean> c);

	public Boolean getBlockFriday() {
		return blockFriday;
	}

	public void setBlockFriday(Boolean blockFriday) {
		this.blockFriday = blockFriday;
		this.blockFridayWrap.alreadyInitialized = true;
	}
	public void setBlockFriday(String o) {
		this.blockFriday = SchoolBlock.staticSetBlockFriday(siteRequest_, o);
		this.blockFridayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockFriday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockFriday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockFriday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockFriday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockFriday(siteRequest_, SchoolBlock.staticSolrBlockFriday(siteRequest_, SchoolBlock.staticSetBlockFriday(siteRequest_, o)));
	}

	public Boolean solrBlockFriday() {
		return SchoolBlock.staticSolrBlockFriday(siteRequest_, blockFriday);
	}

	public String strBlockFriday() {
		return blockFriday == null ? "" : blockFriday.toString();
	}

	public Boolean sqlBlockFriday() {
		return blockFriday;
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

	public void inputBlockFriday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_blockFriday")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_blockFriday");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockFriday classSchoolBlock inputSchoolBlock", pk, "BlockFriday w3-input w3-border ");
				a("name", "setBlockFriday");
			} else {
				a("class", "valueBlockFriday classSchoolBlock inputSchoolBlock", pk, "BlockFriday w3-input w3-border ");
				a("name", "blockFriday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockFriday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockFriday')); }, function() { addError($('#", classApiMethodMethod, "_blockFriday')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getBlockFriday() != null && getBlockFriday())
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
				e("span").a("class", "varSchoolBlock", pk, "BlockFriday ").f().sx(htmBlockFriday()).g("span");
			}
		}
	}

	public void htmBlockFriday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolBlockBlockFriday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockFriday").a("class", "").f().sx("friday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockFriday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// blockSaturday //
	///////////////////

	/**	 The entity blockSaturday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockSaturday;
	@JsonIgnore
	public Wrap<Boolean> blockSaturdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSaturday").o(blockSaturday);

	/**	<br/> The entity blockSaturday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSaturday">Find the entity blockSaturday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockSaturday(Wrap<Boolean> c);

	public Boolean getBlockSaturday() {
		return blockSaturday;
	}

	public void setBlockSaturday(Boolean blockSaturday) {
		this.blockSaturday = blockSaturday;
		this.blockSaturdayWrap.alreadyInitialized = true;
	}
	public void setBlockSaturday(String o) {
		this.blockSaturday = SchoolBlock.staticSetBlockSaturday(siteRequest_, o);
		this.blockSaturdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockSaturday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockSaturday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockSaturday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockSaturday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockSaturday(siteRequest_, SchoolBlock.staticSolrBlockSaturday(siteRequest_, SchoolBlock.staticSetBlockSaturday(siteRequest_, o)));
	}

	public Boolean solrBlockSaturday() {
		return SchoolBlock.staticSolrBlockSaturday(siteRequest_, blockSaturday);
	}

	public String strBlockSaturday() {
		return blockSaturday == null ? "" : blockSaturday.toString();
	}

	public Boolean sqlBlockSaturday() {
		return blockSaturday;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTotalPrice">Find the entity blockTotalPrice in Solr</a>
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
		this.blockTotalPrice = SchoolBlock.staticSetBlockTotalPrice(siteRequest_, o);
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
	protected SchoolBlock blockTotalPriceInit() {
		if(!blockTotalPriceWrap.alreadyInitialized) {
			_blockTotalPrice(blockTotalPriceWrap);
			if(blockTotalPrice == null)
				setBlockTotalPrice(blockTotalPriceWrap.o);
		}
		blockTotalPriceWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static Double staticSolrBlockTotalPrice(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlockTotalPrice(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockTotalPrice(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockTotalPrice(siteRequest_, SchoolBlock.staticSolrBlockTotalPrice(siteRequest_, SchoolBlock.staticSetBlockTotalPrice(siteRequest_, o)));
	}

	public Double solrBlockTotalPrice() {
		return SchoolBlock.staticSolrBlockTotalPrice(siteRequest_, blockTotalPrice);
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

	///////////////////
	// sessionBlocks //
	///////////////////

	/**	 The entity sessionBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> sessionBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> sessionBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("sessionBlocks").o(sessionBlocks);

	/**	<br/> The entity sessionBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionBlocks">Find the entity sessionBlocks in Solr</a>
	 * <br/>
	 * @param sessionBlocks is the entity already constructed. 
	 **/
	protected abstract void _sessionBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getSessionBlocks() {
		return sessionBlocks;
	}

	public void setSessionBlocks(List<SchoolBlock> sessionBlocks) {
		this.sessionBlocks = sessionBlocks;
		this.sessionBlocksWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetSessionBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolBlock addSessionBlocks(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addSessionBlocks(o);
		}
		return (SchoolBlock)this;
	}
	public SchoolBlock addSessionBlocks(SchoolBlock o) {
		if(o != null && !sessionBlocks.contains(o))
			this.sessionBlocks.add(o);
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionBlocksInit() {
		if(!sessionBlocksWrap.alreadyInitialized) {
			_sessionBlocks(sessionBlocks);
		}
		sessionBlocksWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	///////////////
	// ageBlocks //
	///////////////

	/**	 The entity ageBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> ageBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> ageBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("ageBlocks").o(ageBlocks);

	/**	<br/> The entity ageBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageBlocks">Find the entity ageBlocks in Solr</a>
	 * <br/>
	 * @param ageBlocks is the entity already constructed. 
	 **/
	protected abstract void _ageBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getAgeBlocks() {
		return ageBlocks;
	}

	public void setAgeBlocks(List<SchoolBlock> ageBlocks) {
		this.ageBlocks = ageBlocks;
		this.ageBlocksWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetAgeBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolBlock addAgeBlocks(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addAgeBlocks(o);
		}
		return (SchoolBlock)this;
	}
	public SchoolBlock addAgeBlocks(SchoolBlock o) {
		if(o != null && !ageBlocks.contains(o))
			this.ageBlocks.add(o);
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageBlocksInit() {
		if(!ageBlocksWrap.alreadyInitialized) {
			_ageBlocks(ageBlocks);
		}
		ageBlocksWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	/////////////////
	// blockBlocks //
	/////////////////

	/**	 The entity blockBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> blockBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> blockBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("blockBlocks").o(blockBlocks);

	/**	<br/> The entity blockBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockBlocks">Find the entity blockBlocks in Solr</a>
	 * <br/>
	 * @param blockBlocks is the entity already constructed. 
	 **/
	protected abstract void _blockBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getBlockBlocks() {
		return blockBlocks;
	}

	public void setBlockBlocks(List<SchoolBlock> blockBlocks) {
		this.blockBlocks = blockBlocks;
		this.blockBlocksWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetBlockBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolBlock addBlockBlocks(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addBlockBlocks(o);
		}
		return (SchoolBlock)this;
	}
	public SchoolBlock addBlockBlocks(SchoolBlock o) {
		if(o != null && !blockBlocks.contains(o))
			this.blockBlocks.add(o);
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockBlocksInit() {
		if(!blockBlocksWrap.alreadyInitialized) {
			_blockBlocks(blockBlocks);
		}
		blockBlocksWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	////////////////////
	// blockShortName //
	////////////////////

	/**	 The entity blockShortName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockShortName;
	@JsonIgnore
	public Wrap<String> blockShortNameWrap = new Wrap<String>().p(this).c(String.class).var("blockShortName").o(blockShortName);

	/**	<br/> The entity blockShortName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockShortName">Find the entity blockShortName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockShortName(Wrap<String> c);

	public String getBlockShortName() {
		return blockShortName;
	}
	public void setBlockShortName(String o) {
		this.blockShortName = SchoolBlock.staticSetBlockShortName(siteRequest_, o);
		this.blockShortNameWrap.alreadyInitialized = true;
	}
	public static String staticSetBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock blockShortNameInit() {
		if(!blockShortNameWrap.alreadyInitialized) {
			_blockShortName(blockShortNameWrap);
			if(blockShortName == null)
				setBlockShortName(blockShortNameWrap.o);
		}
		blockShortNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockShortName(siteRequest_, SchoolBlock.staticSolrBlockShortName(siteRequest_, SchoolBlock.staticSetBlockShortName(siteRequest_, o)));
	}

	public String solrBlockShortName() {
		return SchoolBlock.staticSolrBlockShortName(siteRequest_, blockShortName);
	}

	public String strBlockShortName() {
		return blockShortName == null ? "" : blockShortName;
	}

	public String sqlBlockShortName() {
		return blockShortName;
	}

	public String jsonBlockShortName() {
		return blockShortName == null ? "" : blockShortName;
	}

	public String nomAffichageBlockShortName() {
		return null;
	}

	public String htmTooltipBlockShortName() {
		return null;
	}

	public String htmBlockShortName() {
		return blockShortName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockShortName());
	}

	////////////////////
	// blockAdminName //
	////////////////////

	/**	 The entity blockAdminName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockAdminName;
	@JsonIgnore
	public Wrap<String> blockAdminNameWrap = new Wrap<String>().p(this).c(String.class).var("blockAdminName").o(blockAdminName);

	/**	<br/> The entity blockAdminName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockAdminName">Find the entity blockAdminName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockAdminName(Wrap<String> c);

	public String getBlockAdminName() {
		return blockAdminName;
	}
	public void setBlockAdminName(String o) {
		this.blockAdminName = SchoolBlock.staticSetBlockAdminName(siteRequest_, o);
		this.blockAdminNameWrap.alreadyInitialized = true;
	}
	public static String staticSetBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock blockAdminNameInit() {
		if(!blockAdminNameWrap.alreadyInitialized) {
			_blockAdminName(blockAdminNameWrap);
			if(blockAdminName == null)
				setBlockAdminName(blockAdminNameWrap.o);
		}
		blockAdminNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockAdminName(siteRequest_, SchoolBlock.staticSolrBlockAdminName(siteRequest_, SchoolBlock.staticSetBlockAdminName(siteRequest_, o)));
	}

	public String solrBlockAdminName() {
		return SchoolBlock.staticSolrBlockAdminName(siteRequest_, blockAdminName);
	}

	public String strBlockAdminName() {
		return blockAdminName == null ? "" : blockAdminName;
	}

	public String sqlBlockAdminName() {
		return blockAdminName;
	}

	public String jsonBlockAdminName() {
		return blockAdminName == null ? "" : blockAdminName;
	}

	public String nomAffichageBlockAdminName() {
		return null;
	}

	public String htmTooltipBlockAdminName() {
		return null;
	}

	public String htmBlockAdminName() {
		return blockAdminName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockAdminName());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockCompleteName">Find the entity blockCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockCompleteName(Wrap<String> c);

	public String getBlockCompleteName() {
		return blockCompleteName;
	}
	public void setBlockCompleteName(String o) {
		this.blockCompleteName = SchoolBlock.staticSetBlockCompleteName(siteRequest_, o);
		this.blockCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolBlock blockCompleteNameInit() {
		if(!blockCompleteNameWrap.alreadyInitialized) {
			_blockCompleteName(blockCompleteNameWrap);
			if(blockCompleteName == null)
				setBlockCompleteName(blockCompleteNameWrap.o);
		}
		blockCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public static String staticSolrBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolBlock.staticSolrStrBlockCompleteName(siteRequest_, SchoolBlock.staticSolrBlockCompleteName(siteRequest_, SchoolBlock.staticSetBlockCompleteName(siteRequest_, o)));
	}

	public String solrBlockCompleteName() {
		return SchoolBlock.staticSolrBlockCompleteName(siteRequest_, blockCompleteName);
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
		return "name";
	}

	public String htmTooltipBlockCompleteName() {
		return null;
	}

	public String htmBlockCompleteName() {
		return blockCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockCompleteName());
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
		initSchoolBlock();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolBlock() {
		blockKeyInit();
		childKeyInit();
		enrollmentKeysInit();
		enrollmentKeyInit();
		enrollmentAttributeInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		sessionSortInit();
		ageSortInit();
		ageKeyInit();
		ageSearchInit();
		age_Init();
		schoolKeyInit();
		yearKeyInit();
		sessionKeyInit();
		schoolNameInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		schoolAddressInit();
		schoolPhoneNumberInit();
		schoolFormInit();
		schoolNumberInit();
		schoolAdministratorNameInit();
		yearStartInit();
		yearEndInit();
		seasonStartDateInit();
		yearEnrollmentFeeInit();
		sessionStartDateInit();
		sessionEndDateInit();
		ageShortNameInit();
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
		blockTotalPriceInit();
		sessionBlocksInit();
		ageBlocksInit();
		blockBlocksInit();
		blockShortNameInit();
		blockAdminNameInit();
		blockCompleteNameInit();
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
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtainSchoolBlock(String var) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
		switch(var) {
			case "blockKey":
				return oSchoolBlock.blockKey;
			case "childKey":
				return oSchoolBlock.childKey;
			case "enrollmentKeys":
				return oSchoolBlock.enrollmentKeys;
			case "enrollmentKey":
				return oSchoolBlock.enrollmentKey;
			case "enrollmentAttribute":
				return oSchoolBlock.enrollmentAttribute;
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
			case "ageKey":
				return oSchoolBlock.ageKey;
			case "ageSearch":
				return oSchoolBlock.ageSearch;
			case "age_":
				return oSchoolBlock.age_;
			case "schoolKey":
				return oSchoolBlock.schoolKey;
			case "yearKey":
				return oSchoolBlock.yearKey;
			case "sessionKey":
				return oSchoolBlock.sessionKey;
			case "schoolName":
				return oSchoolBlock.schoolName;
			case "schoolCompleteName":
				return oSchoolBlock.schoolCompleteName;
			case "schoolLocation":
				return oSchoolBlock.schoolLocation;
			case "schoolAddress":
				return oSchoolBlock.schoolAddress;
			case "schoolPhoneNumber":
				return oSchoolBlock.schoolPhoneNumber;
			case "schoolForm":
				return oSchoolBlock.schoolForm;
			case "schoolNumber":
				return oSchoolBlock.schoolNumber;
			case "schoolAdministratorName":
				return oSchoolBlock.schoolAdministratorName;
			case "yearStart":
				return oSchoolBlock.yearStart;
			case "yearEnd":
				return oSchoolBlock.yearEnd;
			case "seasonStartDate":
				return oSchoolBlock.seasonStartDate;
			case "yearEnrollmentFee":
				return oSchoolBlock.yearEnrollmentFee;
			case "sessionStartDate":
				return oSchoolBlock.sessionStartDate;
			case "sessionEndDate":
				return oSchoolBlock.sessionEndDate;
			case "ageShortName":
				return oSchoolBlock.ageShortName;
			case "ageCompleteName":
				return oSchoolBlock.ageCompleteName;
			case "ageStart":
				return oSchoolBlock.ageStart;
			case "ageEnd":
				return oSchoolBlock.ageEnd;
			case "blockStartTime":
				return oSchoolBlock.blockStartTime;
			case "blockEndTime":
				return oSchoolBlock.blockEndTime;
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
			case "blockTotalPrice":
				return oSchoolBlock.blockTotalPrice;
			case "sessionBlocks":
				return oSchoolBlock.sessionBlocks;
			case "ageBlocks":
				return oSchoolBlock.ageBlocks;
			case "blockBlocks":
				return oSchoolBlock.blockBlocks;
			case "blockShortName":
				return oSchoolBlock.blockShortName;
			case "blockAdminName":
				return oSchoolBlock.blockAdminName;
			case "blockCompleteName":
				return oSchoolBlock.blockCompleteName;
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
			case "enrollmentKeys":
				oSchoolBlock.addEnrollmentKeys((Long)val);
				if(!saves.contains("enrollmentKeys"))
					saves.add("enrollmentKeys");
				return val;
			case "ageKey":
				if(oSchoolBlock.getAgeKey() == null)
					oSchoolBlock.setAgeKey((Long)val);
				if(!saves.contains("ageKey"))
					saves.add("ageKey");
				return val;
			default:
				return super.attributeCluster(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSchoolBlock(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolBlock(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "blockKey":
			return SchoolBlock.staticSetBlockKey(siteRequest_, o);
		case "childKey":
			return SchoolBlock.staticSetChildKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolBlock.staticSetEnrollmentKeys(siteRequest_, o);
		case "enrollmentKey":
			return SchoolBlock.staticSetEnrollmentKey(siteRequest_, o);
		case "enrollmentAttribute":
			return SchoolBlock.staticSetEnrollmentAttribute(siteRequest_, o);
		case "educationSort":
			return SchoolBlock.staticSetEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolBlock.staticSetSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolBlock.staticSetYearSort(siteRequest_, o);
		case "seasonSort":
			return SchoolBlock.staticSetSeasonSort(siteRequest_, o);
		case "sessionSort":
			return SchoolBlock.staticSetSessionSort(siteRequest_, o);
		case "ageSort":
			return SchoolBlock.staticSetAgeSort(siteRequest_, o);
		case "ageKey":
			return SchoolBlock.staticSetAgeKey(siteRequest_, o);
		case "schoolKey":
			return SchoolBlock.staticSetSchoolKey(siteRequest_, o);
		case "yearKey":
			return SchoolBlock.staticSetYearKey(siteRequest_, o);
		case "sessionKey":
			return SchoolBlock.staticSetSessionKey(siteRequest_, o);
		case "schoolName":
			return SchoolBlock.staticSetSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolBlock.staticSetSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolBlock.staticSetSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolBlock.staticSetSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolBlock.staticSetSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolBlock.staticSetSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolBlock.staticSetSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolBlock.staticSetSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return SchoolBlock.staticSetYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolBlock.staticSetYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return SchoolBlock.staticSetSeasonStartDate(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolBlock.staticSetYearEnrollmentFee(siteRequest_, o);
		case "sessionStartDate":
			return SchoolBlock.staticSetSessionStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolBlock.staticSetSessionEndDate(siteRequest_, o);
		case "ageShortName":
			return SchoolBlock.staticSetAgeShortName(siteRequest_, o);
		case "ageCompleteName":
			return SchoolBlock.staticSetAgeCompleteName(siteRequest_, o);
		case "ageStart":
			return SchoolBlock.staticSetAgeStart(siteRequest_, o);
		case "ageEnd":
			return SchoolBlock.staticSetAgeEnd(siteRequest_, o);
		case "blockStartTime":
			return SchoolBlock.staticSetBlockStartTime(siteRequest_, o);
		case "blockEndTime":
			return SchoolBlock.staticSetBlockEndTime(siteRequest_, o);
		case "blockPricePerMonth":
			return SchoolBlock.staticSetBlockPricePerMonth(siteRequest_, o);
		case "blockSunday":
			return SchoolBlock.staticSetBlockSunday(siteRequest_, o);
		case "blockMonday":
			return SchoolBlock.staticSetBlockMonday(siteRequest_, o);
		case "blockTuesday":
			return SchoolBlock.staticSetBlockTuesday(siteRequest_, o);
		case "blockWednesday":
			return SchoolBlock.staticSetBlockWednesday(siteRequest_, o);
		case "blockThursday":
			return SchoolBlock.staticSetBlockThursday(siteRequest_, o);
		case "blockFriday":
			return SchoolBlock.staticSetBlockFriday(siteRequest_, o);
		case "blockSaturday":
			return SchoolBlock.staticSetBlockSaturday(siteRequest_, o);
		case "blockTotalPrice":
			return SchoolBlock.staticSetBlockTotalPrice(siteRequest_, o);
		case "blockShortName":
			return SchoolBlock.staticSetBlockShortName(siteRequest_, o);
		case "blockAdminName":
			return SchoolBlock.staticSetBlockAdminName(siteRequest_, o);
		case "blockCompleteName":
			return SchoolBlock.staticSetBlockCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolBlock(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolBlock(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "blockKey":
			return SchoolBlock.staticSolrBlockKey(siteRequest_, (Long)o);
		case "childKey":
			return SchoolBlock.staticSolrChildKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolBlock.staticSolrEnrollmentKeys(siteRequest_, (Long)o);
		case "enrollmentKey":
			return SchoolBlock.staticSolrEnrollmentKey(siteRequest_, (Long)o);
		case "enrollmentAttribute":
			return SchoolBlock.staticSolrEnrollmentAttribute(siteRequest_, (Boolean)o);
		case "educationSort":
			return SchoolBlock.staticSolrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolBlock.staticSolrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolBlock.staticSolrYearSort(siteRequest_, (Integer)o);
		case "seasonSort":
			return SchoolBlock.staticSolrSeasonSort(siteRequest_, (Integer)o);
		case "sessionSort":
			return SchoolBlock.staticSolrSessionSort(siteRequest_, (Integer)o);
		case "ageSort":
			return SchoolBlock.staticSolrAgeSort(siteRequest_, (Integer)o);
		case "ageKey":
			return SchoolBlock.staticSolrAgeKey(siteRequest_, (Long)o);
		case "schoolKey":
			return SchoolBlock.staticSolrSchoolKey(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolBlock.staticSolrYearKey(siteRequest_, (Long)o);
		case "sessionKey":
			return SchoolBlock.staticSolrSessionKey(siteRequest_, (Long)o);
		case "schoolName":
			return SchoolBlock.staticSolrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolBlock.staticSolrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolBlock.staticSolrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolBlock.staticSolrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolBlock.staticSolrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolBlock.staticSolrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolBlock.staticSolrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolBlock.staticSolrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return SchoolBlock.staticSolrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolBlock.staticSolrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return SchoolBlock.staticSolrSeasonStartDate(siteRequest_, (LocalDate)o);
		case "yearEnrollmentFee":
			return SchoolBlock.staticSolrYearEnrollmentFee(siteRequest_, (BigDecimal)o);
		case "sessionStartDate":
			return SchoolBlock.staticSolrSessionStartDate(siteRequest_, (LocalDate)o);
		case "sessionEndDate":
			return SchoolBlock.staticSolrSessionEndDate(siteRequest_, (LocalDate)o);
		case "ageShortName":
			return SchoolBlock.staticSolrAgeShortName(siteRequest_, (String)o);
		case "ageCompleteName":
			return SchoolBlock.staticSolrAgeCompleteName(siteRequest_, (String)o);
		case "ageStart":
			return SchoolBlock.staticSolrAgeStart(siteRequest_, (Integer)o);
		case "ageEnd":
			return SchoolBlock.staticSolrAgeEnd(siteRequest_, (Integer)o);
		case "blockStartTime":
			return SchoolBlock.staticSolrBlockStartTime(siteRequest_, (LocalTime)o);
		case "blockEndTime":
			return SchoolBlock.staticSolrBlockEndTime(siteRequest_, (LocalTime)o);
		case "blockPricePerMonth":
			return SchoolBlock.staticSolrBlockPricePerMonth(siteRequest_, (BigDecimal)o);
		case "blockSunday":
			return SchoolBlock.staticSolrBlockSunday(siteRequest_, (Boolean)o);
		case "blockMonday":
			return SchoolBlock.staticSolrBlockMonday(siteRequest_, (Boolean)o);
		case "blockTuesday":
			return SchoolBlock.staticSolrBlockTuesday(siteRequest_, (Boolean)o);
		case "blockWednesday":
			return SchoolBlock.staticSolrBlockWednesday(siteRequest_, (Boolean)o);
		case "blockThursday":
			return SchoolBlock.staticSolrBlockThursday(siteRequest_, (Boolean)o);
		case "blockFriday":
			return SchoolBlock.staticSolrBlockFriday(siteRequest_, (Boolean)o);
		case "blockSaturday":
			return SchoolBlock.staticSolrBlockSaturday(siteRequest_, (Boolean)o);
		case "blockTotalPrice":
			return SchoolBlock.staticSolrBlockTotalPrice(siteRequest_, (BigDecimal)o);
		case "blockShortName":
			return SchoolBlock.staticSolrBlockShortName(siteRequest_, (String)o);
		case "blockAdminName":
			return SchoolBlock.staticSolrBlockAdminName(siteRequest_, (String)o);
		case "blockCompleteName":
			return SchoolBlock.staticSolrBlockCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolBlock(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolBlock(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "blockKey":
			return SchoolBlock.staticSolrStrBlockKey(siteRequest_, (Long)o);
		case "childKey":
			return SchoolBlock.staticSolrStrChildKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolBlock.staticSolrStrEnrollmentKeys(siteRequest_, (Long)o);
		case "enrollmentKey":
			return SchoolBlock.staticSolrStrEnrollmentKey(siteRequest_, (Long)o);
		case "enrollmentAttribute":
			return SchoolBlock.staticSolrStrEnrollmentAttribute(siteRequest_, (Boolean)o);
		case "educationSort":
			return SchoolBlock.staticSolrStrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolBlock.staticSolrStrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolBlock.staticSolrStrYearSort(siteRequest_, (Integer)o);
		case "seasonSort":
			return SchoolBlock.staticSolrStrSeasonSort(siteRequest_, (Integer)o);
		case "sessionSort":
			return SchoolBlock.staticSolrStrSessionSort(siteRequest_, (Integer)o);
		case "ageSort":
			return SchoolBlock.staticSolrStrAgeSort(siteRequest_, (Integer)o);
		case "ageKey":
			return SchoolBlock.staticSolrStrAgeKey(siteRequest_, (Long)o);
		case "schoolKey":
			return SchoolBlock.staticSolrStrSchoolKey(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolBlock.staticSolrStrYearKey(siteRequest_, (Long)o);
		case "sessionKey":
			return SchoolBlock.staticSolrStrSessionKey(siteRequest_, (Long)o);
		case "schoolName":
			return SchoolBlock.staticSolrStrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolBlock.staticSolrStrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolBlock.staticSolrStrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolBlock.staticSolrStrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolBlock.staticSolrStrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolBlock.staticSolrStrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolBlock.staticSolrStrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolBlock.staticSolrStrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return SchoolBlock.staticSolrStrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolBlock.staticSolrStrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return SchoolBlock.staticSolrStrSeasonStartDate(siteRequest_, (Date)o);
		case "yearEnrollmentFee":
			return SchoolBlock.staticSolrStrYearEnrollmentFee(siteRequest_, (Double)o);
		case "sessionStartDate":
			return SchoolBlock.staticSolrStrSessionStartDate(siteRequest_, (Date)o);
		case "sessionEndDate":
			return SchoolBlock.staticSolrStrSessionEndDate(siteRequest_, (Date)o);
		case "ageShortName":
			return SchoolBlock.staticSolrStrAgeShortName(siteRequest_, (String)o);
		case "ageCompleteName":
			return SchoolBlock.staticSolrStrAgeCompleteName(siteRequest_, (String)o);
		case "ageStart":
			return SchoolBlock.staticSolrStrAgeStart(siteRequest_, (Integer)o);
		case "ageEnd":
			return SchoolBlock.staticSolrStrAgeEnd(siteRequest_, (Integer)o);
		case "blockStartTime":
			return SchoolBlock.staticSolrStrBlockStartTime(siteRequest_, (String)o);
		case "blockEndTime":
			return SchoolBlock.staticSolrStrBlockEndTime(siteRequest_, (String)o);
		case "blockPricePerMonth":
			return SchoolBlock.staticSolrStrBlockPricePerMonth(siteRequest_, (Double)o);
		case "blockSunday":
			return SchoolBlock.staticSolrStrBlockSunday(siteRequest_, (Boolean)o);
		case "blockMonday":
			return SchoolBlock.staticSolrStrBlockMonday(siteRequest_, (Boolean)o);
		case "blockTuesday":
			return SchoolBlock.staticSolrStrBlockTuesday(siteRequest_, (Boolean)o);
		case "blockWednesday":
			return SchoolBlock.staticSolrStrBlockWednesday(siteRequest_, (Boolean)o);
		case "blockThursday":
			return SchoolBlock.staticSolrStrBlockThursday(siteRequest_, (Boolean)o);
		case "blockFriday":
			return SchoolBlock.staticSolrStrBlockFriday(siteRequest_, (Boolean)o);
		case "blockSaturday":
			return SchoolBlock.staticSolrStrBlockSaturday(siteRequest_, (Boolean)o);
		case "blockTotalPrice":
			return SchoolBlock.staticSolrStrBlockTotalPrice(siteRequest_, (Double)o);
		case "blockShortName":
			return SchoolBlock.staticSolrStrBlockShortName(siteRequest_, (String)o);
		case "blockAdminName":
			return SchoolBlock.staticSolrStrBlockAdminName(siteRequest_, (String)o);
		case "blockCompleteName":
			return SchoolBlock.staticSolrStrBlockCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolBlock(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolBlock(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "blockKey":
			return SchoolBlock.staticSolrFqBlockKey(siteRequest_, o);
		case "childKey":
			return SchoolBlock.staticSolrFqChildKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolBlock.staticSolrFqEnrollmentKeys(siteRequest_, o);
		case "enrollmentKey":
			return SchoolBlock.staticSolrFqEnrollmentKey(siteRequest_, o);
		case "enrollmentAttribute":
			return SchoolBlock.staticSolrFqEnrollmentAttribute(siteRequest_, o);
		case "educationSort":
			return SchoolBlock.staticSolrFqEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolBlock.staticSolrFqSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolBlock.staticSolrFqYearSort(siteRequest_, o);
		case "seasonSort":
			return SchoolBlock.staticSolrFqSeasonSort(siteRequest_, o);
		case "sessionSort":
			return SchoolBlock.staticSolrFqSessionSort(siteRequest_, o);
		case "ageSort":
			return SchoolBlock.staticSolrFqAgeSort(siteRequest_, o);
		case "ageKey":
			return SchoolBlock.staticSolrFqAgeKey(siteRequest_, o);
		case "schoolKey":
			return SchoolBlock.staticSolrFqSchoolKey(siteRequest_, o);
		case "yearKey":
			return SchoolBlock.staticSolrFqYearKey(siteRequest_, o);
		case "sessionKey":
			return SchoolBlock.staticSolrFqSessionKey(siteRequest_, o);
		case "schoolName":
			return SchoolBlock.staticSolrFqSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolBlock.staticSolrFqSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolBlock.staticSolrFqSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolBlock.staticSolrFqSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolBlock.staticSolrFqSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolBlock.staticSolrFqSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolBlock.staticSolrFqSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolBlock.staticSolrFqSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return SchoolBlock.staticSolrFqYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolBlock.staticSolrFqYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return SchoolBlock.staticSolrFqSeasonStartDate(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolBlock.staticSolrFqYearEnrollmentFee(siteRequest_, o);
		case "sessionStartDate":
			return SchoolBlock.staticSolrFqSessionStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolBlock.staticSolrFqSessionEndDate(siteRequest_, o);
		case "ageShortName":
			return SchoolBlock.staticSolrFqAgeShortName(siteRequest_, o);
		case "ageCompleteName":
			return SchoolBlock.staticSolrFqAgeCompleteName(siteRequest_, o);
		case "ageStart":
			return SchoolBlock.staticSolrFqAgeStart(siteRequest_, o);
		case "ageEnd":
			return SchoolBlock.staticSolrFqAgeEnd(siteRequest_, o);
		case "blockStartTime":
			return SchoolBlock.staticSolrFqBlockStartTime(siteRequest_, o);
		case "blockEndTime":
			return SchoolBlock.staticSolrFqBlockEndTime(siteRequest_, o);
		case "blockPricePerMonth":
			return SchoolBlock.staticSolrFqBlockPricePerMonth(siteRequest_, o);
		case "blockSunday":
			return SchoolBlock.staticSolrFqBlockSunday(siteRequest_, o);
		case "blockMonday":
			return SchoolBlock.staticSolrFqBlockMonday(siteRequest_, o);
		case "blockTuesday":
			return SchoolBlock.staticSolrFqBlockTuesday(siteRequest_, o);
		case "blockWednesday":
			return SchoolBlock.staticSolrFqBlockWednesday(siteRequest_, o);
		case "blockThursday":
			return SchoolBlock.staticSolrFqBlockThursday(siteRequest_, o);
		case "blockFriday":
			return SchoolBlock.staticSolrFqBlockFriday(siteRequest_, o);
		case "blockSaturday":
			return SchoolBlock.staticSolrFqBlockSaturday(siteRequest_, o);
		case "blockTotalPrice":
			return SchoolBlock.staticSolrFqBlockTotalPrice(siteRequest_, o);
		case "blockShortName":
			return SchoolBlock.staticSolrFqBlockShortName(siteRequest_, o);
		case "blockAdminName":
			return SchoolBlock.staticSolrFqBlockAdminName(siteRequest_, o);
		case "blockCompleteName":
			return SchoolBlock.staticSolrFqBlockCompleteName(siteRequest_, o);
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
					o = defineSchoolBlock(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolBlock(String var, String val) {
		switch(var.toLowerCase()) {
			case "agekey":
				if(val != null)
					setAgeKey(val);
				saves.add("ageKey");
				return val;
			case "schooladdress":
				if(val != null)
					setSchoolAddress(val);
				saves.add("schoolAddress");
				return val;
			case "blockstarttime":
				if(val != null)
					setBlockStartTime(val);
				saves.add("blockStartTime");
				return val;
			case "blockendtime":
				if(val != null)
					setBlockEndTime(val);
				saves.add("blockEndTime");
				return val;
			case "blockpricepermonth":
				if(val != null)
					setBlockPricePerMonth(val);
				saves.add("blockPricePerMonth");
				return val;
			case "blockmonday":
				if(val != null)
					setBlockMonday(val);
				saves.add("blockMonday");
				return val;
			case "blocktuesday":
				if(val != null)
					setBlockTuesday(val);
				saves.add("blockTuesday");
				return val;
			case "blockwednesday":
				if(val != null)
					setBlockWednesday(val);
				saves.add("blockWednesday");
				return val;
			case "blockthursday":
				if(val != null)
					setBlockThursday(val);
				saves.add("blockThursday");
				return val;
			case "blockfriday":
				if(val != null)
					setBlockFriday(val);
				saves.add("blockFriday");
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
					o = defineSchoolBlock(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolBlock(String var, Object val) {
		switch(var.toLowerCase()) {
			case "agekey":
				if(val instanceof Long)
					setAgeKey((Long)val);
				saves.add("ageKey");
				return val;
			case "schooladdress":
				if(val instanceof String)
					setSchoolAddress((String)val);
				saves.add("schoolAddress");
				return val;
			case "blockstarttime":
				if(val instanceof LocalTime)
					setBlockStartTime((LocalTime)val);
				saves.add("blockStartTime");
				return val;
			case "blockendtime":
				if(val instanceof LocalTime)
					setBlockEndTime((LocalTime)val);
				saves.add("blockEndTime");
				return val;
			case "blockpricepermonth":
				if(val instanceof BigDecimal)
					setBlockPricePerMonth((BigDecimal)val);
				saves.add("blockPricePerMonth");
				return val;
			case "blockmonday":
				if(val instanceof Boolean)
					setBlockMonday((Boolean)val);
				saves.add("blockMonday");
				return val;
			case "blocktuesday":
				if(val instanceof Boolean)
					setBlockTuesday((Boolean)val);
				saves.add("blockTuesday");
				return val;
			case "blockwednesday":
				if(val instanceof Boolean)
					setBlockWednesday((Boolean)val);
				saves.add("blockWednesday");
				return val;
			case "blockthursday":
				if(val instanceof Boolean)
					setBlockThursday((Boolean)val);
				saves.add("blockThursday");
				return val;
			case "blockfriday":
				if(val instanceof Boolean)
					setBlockFriday((Boolean)val);
				saves.add("blockFriday");
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolBlock(solrDocument);
	}
	public void populateSchoolBlock(SolrDocument solrDocument) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("blockKey")) {
				Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
				if(blockKey != null)
					oSchoolBlock.setBlockKey(blockKey);
			}

			if(saves.contains("childKey")) {
				Long childKey = (Long)solrDocument.get("childKey_stored_long");
				if(childKey != null)
					oSchoolBlock.setChildKey(childKey);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolBlock.enrollmentKeys.addAll(enrollmentKeys);

			if(saves.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolBlock.setEducationSort(educationSort);
			}

			if(saves.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolBlock.setSchoolSort(schoolSort);
			}

			if(saves.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolBlock.setYearSort(yearSort);
			}

			if(saves.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolBlock.setSeasonSort(seasonSort);
			}

			if(saves.contains("sessionSort")) {
				Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
				if(sessionSort != null)
					oSchoolBlock.setSessionSort(sessionSort);
			}

			if(saves.contains("ageSort")) {
				Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
				if(ageSort != null)
					oSchoolBlock.setAgeSort(ageSort);
			}

			Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
			if(ageKey != null)
				oSchoolBlock.setAgeKey(ageKey);

			if(saves.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolBlock.setSchoolKey(schoolKey);
			}

			if(saves.contains("yearKey")) {
				Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
				if(yearKey != null)
					oSchoolBlock.setYearKey(yearKey);
			}

			if(saves.contains("sessionKey")) {
				Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
				if(sessionKey != null)
					oSchoolBlock.setSessionKey(sessionKey);
			}

			if(saves.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolBlock.setSchoolName(schoolName);
			}

			if(saves.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolBlock.setSchoolCompleteName(schoolCompleteName);
			}

			if(saves.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolBlock.setSchoolLocation(schoolLocation);
			}

			if(saves.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolBlock.setSchoolAddress(schoolAddress);
			}

			if(saves.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolBlock.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(saves.contains("schoolForm")) {
				String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
				if(schoolForm != null)
					oSchoolBlock.setSchoolForm(schoolForm);
			}

			if(saves.contains("schoolNumber")) {
				Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
				if(schoolNumber != null)
					oSchoolBlock.setSchoolNumber(schoolNumber);
			}

			if(saves.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchoolBlock.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(saves.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolBlock.setYearStart(yearStart);
			}

			if(saves.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolBlock.setYearEnd(yearEnd);
			}

			if(saves.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolBlock.setSeasonStartDate(seasonStartDate);
			}

			if(saves.contains("yearEnrollmentFee")) {
				Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
				if(yearEnrollmentFee != null)
					oSchoolBlock.setYearEnrollmentFee(yearEnrollmentFee);
			}

			if(saves.contains("sessionStartDate")) {
				Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
				if(sessionStartDate != null)
					oSchoolBlock.setSessionStartDate(sessionStartDate);
			}

			if(saves.contains("sessionEndDate")) {
				Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
				if(sessionEndDate != null)
					oSchoolBlock.setSessionEndDate(sessionEndDate);
			}

			if(saves.contains("ageShortName")) {
				String ageShortName = (String)solrDocument.get("ageShortName_stored_string");
				if(ageShortName != null)
					oSchoolBlock.setAgeShortName(ageShortName);
			}

			if(saves.contains("ageCompleteName")) {
				String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
				if(ageCompleteName != null)
					oSchoolBlock.setAgeCompleteName(ageCompleteName);
			}

			if(saves.contains("ageStart")) {
				Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
				if(ageStart != null)
					oSchoolBlock.setAgeStart(ageStart);
			}

			if(saves.contains("ageEnd")) {
				Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
				if(ageEnd != null)
					oSchoolBlock.setAgeEnd(ageEnd);
			}

			if(saves.contains("blockStartTime")) {
				String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
				if(blockStartTime != null)
					oSchoolBlock.setBlockStartTime(blockStartTime);
			}

			if(saves.contains("blockEndTime")) {
				String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
				if(blockEndTime != null)
					oSchoolBlock.setBlockEndTime(blockEndTime);
			}

			if(saves.contains("blockPricePerMonth")) {
				Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
				if(blockPricePerMonth != null)
					oSchoolBlock.setBlockPricePerMonth(blockPricePerMonth);
			}

			if(saves.contains("blockSunday")) {
				Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
				if(blockSunday != null)
					oSchoolBlock.setBlockSunday(blockSunday);
			}

			if(saves.contains("blockMonday")) {
				Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
				if(blockMonday != null)
					oSchoolBlock.setBlockMonday(blockMonday);
			}

			if(saves.contains("blockTuesday")) {
				Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
				if(blockTuesday != null)
					oSchoolBlock.setBlockTuesday(blockTuesday);
			}

			if(saves.contains("blockWednesday")) {
				Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
				if(blockWednesday != null)
					oSchoolBlock.setBlockWednesday(blockWednesday);
			}

			if(saves.contains("blockThursday")) {
				Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
				if(blockThursday != null)
					oSchoolBlock.setBlockThursday(blockThursday);
			}

			if(saves.contains("blockFriday")) {
				Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
				if(blockFriday != null)
					oSchoolBlock.setBlockFriday(blockFriday);
			}

			if(saves.contains("blockSaturday")) {
				Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
				if(blockSaturday != null)
					oSchoolBlock.setBlockSaturday(blockSaturday);
			}

			if(saves.contains("blockTotalPrice")) {
				Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
				if(blockTotalPrice != null)
					oSchoolBlock.setBlockTotalPrice(blockTotalPrice);
			}

			if(saves.contains("blockShortName")) {
				String blockShortName = (String)solrDocument.get("blockShortName_stored_string");
				if(blockShortName != null)
					oSchoolBlock.setBlockShortName(blockShortName);
			}

			if(saves.contains("blockAdminName")) {
				String blockAdminName = (String)solrDocument.get("blockAdminName_stored_string");
				if(blockAdminName != null)
					oSchoolBlock.setBlockAdminName(blockAdminName);
			}

			if(saves.contains("blockCompleteName")) {
				String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
				if(blockCompleteName != null)
					oSchoolBlock.setBlockCompleteName(blockCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.block.SchoolBlock"));
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
			clientSolr.commit(false, false, true);
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
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolBlock(SolrInputDocument document) {
		if(blockKey != null) {
			document.addField("blockKey_indexed_long", blockKey);
			document.addField("blockKey_stored_long", blockKey);
		}
		if(childKey != null) {
			document.addField("childKey_indexed_long", childKey);
			document.addField("childKey_stored_long", childKey);
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
		if(ageKey != null) {
			document.addField("ageKey_indexed_long", ageKey);
			document.addField("ageKey_stored_long", ageKey);
		}
		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
		}
		if(sessionKey != null) {
			document.addField("sessionKey_indexed_long", sessionKey);
			document.addField("sessionKey_stored_long", sessionKey);
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
		if(schoolAddress != null) {
			document.addField("schoolAddress_indexed_string", schoolAddress);
			document.addField("schoolAddress_stored_string", schoolAddress);
		}
		if(schoolPhoneNumber != null) {
			document.addField("schoolPhoneNumber_indexed_string", schoolPhoneNumber);
			document.addField("schoolPhoneNumber_stored_string", schoolPhoneNumber);
		}
		if(schoolForm != null) {
			document.addField("schoolForm_indexed_string", schoolForm);
			document.addField("schoolForm_stored_string", schoolForm);
		}
		if(schoolNumber != null) {
			document.addField("schoolNumber_indexed_int", schoolNumber);
			document.addField("schoolNumber_stored_int", schoolNumber);
		}
		if(schoolAdministratorName != null) {
			document.addField("schoolAdministratorName_indexed_string", schoolAdministratorName);
			document.addField("schoolAdministratorName_stored_string", schoolAdministratorName);
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
		if(ageShortName != null) {
			document.addField("ageShortName_indexed_string", ageShortName);
			document.addField("ageShortName_stored_string", ageShortName);
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
		if(blockTotalPrice != null) {
			document.addField("blockTotalPrice_indexed_double", blockTotalPrice.doubleValue());
			document.addField("blockTotalPrice_stored_double", blockTotalPrice.doubleValue());
		}
		if(blockShortName != null) {
			document.addField("blockShortName_indexed_string", blockShortName);
			document.addField("blockShortName_stored_string", blockShortName);
		}
		if(blockAdminName != null) {
			document.addField("blockAdminName_indexed_string", blockAdminName);
			document.addField("blockAdminName_stored_string", blockAdminName);
		}
		if(blockCompleteName != null) {
			document.addField("blockCompleteName_indexed_string", blockCompleteName);
			document.addField("blockCompleteName_stored_string", blockCompleteName);
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
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolBlock(String entityVar) {
		switch(entityVar) {
			case "blockKey":
				return "blockKey_indexed_long";
			case "childKey":
				return "childKey_indexed_long";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "educationSort":
				return "educationSort_indexed_int";
			case "schoolSort":
				return "schoolSort_indexed_int";
			case "yearSort":
				return "yearSort_indexed_int";
			case "seasonSort":
				return "seasonSort_indexed_int";
			case "sessionSort":
				return "sessionSort_indexed_int";
			case "ageSort":
				return "ageSort_indexed_int";
			case "ageKey":
				return "ageKey_indexed_long";
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "yearKey":
				return "yearKey_indexed_long";
			case "sessionKey":
				return "sessionKey_indexed_long";
			case "schoolName":
				return "schoolName_indexed_string";
			case "schoolCompleteName":
				return "schoolCompleteName_indexed_string";
			case "schoolLocation":
				return "schoolLocation_indexed_string";
			case "schoolAddress":
				return "schoolAddress_indexed_string";
			case "schoolPhoneNumber":
				return "schoolPhoneNumber_indexed_string";
			case "schoolForm":
				return "schoolForm_indexed_string";
			case "schoolNumber":
				return "schoolNumber_indexed_int";
			case "schoolAdministratorName":
				return "schoolAdministratorName_indexed_string";
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
			case "ageShortName":
				return "ageShortName_indexed_string";
			case "ageCompleteName":
				return "ageCompleteName_indexed_string";
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
			case "blockSunday":
				return "blockSunday_indexed_boolean";
			case "blockMonday":
				return "blockMonday_indexed_boolean";
			case "blockTuesday":
				return "blockTuesday_indexed_boolean";
			case "blockWednesday":
				return "blockWednesday_indexed_boolean";
			case "blockThursday":
				return "blockThursday_indexed_boolean";
			case "blockFriday":
				return "blockFriday_indexed_boolean";
			case "blockSaturday":
				return "blockSaturday_indexed_boolean";
			case "blockTotalPrice":
				return "blockTotalPrice_indexed_double";
			case "blockShortName":
				return "blockShortName_indexed_string";
			case "blockAdminName":
				return "blockAdminName_indexed_string";
			case "blockCompleteName":
				return "blockCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolBlock(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSchoolBlock(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
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

		Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
		if(blockKey != null)
			oSchoolBlock.setBlockKey(blockKey);

		Long childKey = (Long)solrDocument.get("childKey_stored_long");
		if(childKey != null)
			oSchoolBlock.setChildKey(childKey);

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

		Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
		if(ageKey != null)
			oSchoolBlock.setAgeKey(ageKey);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolBlock.setSchoolKey(schoolKey);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolBlock.setYearKey(yearKey);

		Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
		if(sessionKey != null)
			oSchoolBlock.setSessionKey(sessionKey);

		String schoolName = (String)solrDocument.get("schoolName_stored_string");
		if(schoolName != null)
			oSchoolBlock.setSchoolName(schoolName);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oSchoolBlock.setSchoolCompleteName(schoolCompleteName);

		String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
		if(schoolLocation != null)
			oSchoolBlock.setSchoolLocation(schoolLocation);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchoolBlock.setSchoolAddress(schoolAddress);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchoolBlock.setSchoolPhoneNumber(schoolPhoneNumber);

		String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
		if(schoolForm != null)
			oSchoolBlock.setSchoolForm(schoolForm);

		Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
		if(schoolNumber != null)
			oSchoolBlock.setSchoolNumber(schoolNumber);

		String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
		if(schoolAdministratorName != null)
			oSchoolBlock.setSchoolAdministratorName(schoolAdministratorName);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolBlock.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolBlock.setYearEnd(yearEnd);

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolBlock.setSeasonStartDate(seasonStartDate);

		Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
		if(yearEnrollmentFee != null)
			oSchoolBlock.setYearEnrollmentFee(yearEnrollmentFee);

		Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
		if(sessionStartDate != null)
			oSchoolBlock.setSessionStartDate(sessionStartDate);

		Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
		if(sessionEndDate != null)
			oSchoolBlock.setSessionEndDate(sessionEndDate);

		String ageShortName = (String)solrDocument.get("ageShortName_stored_string");
		if(ageShortName != null)
			oSchoolBlock.setAgeShortName(ageShortName);

		String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
		if(ageCompleteName != null)
			oSchoolBlock.setAgeCompleteName(ageCompleteName);

		Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
		if(ageStart != null)
			oSchoolBlock.setAgeStart(ageStart);

		Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
		if(ageEnd != null)
			oSchoolBlock.setAgeEnd(ageEnd);

		String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
		if(blockStartTime != null)
			oSchoolBlock.setBlockStartTime(blockStartTime);

		String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
		if(blockEndTime != null)
			oSchoolBlock.setBlockEndTime(blockEndTime);

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

		Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
		if(blockTotalPrice != null)
			oSchoolBlock.setBlockTotalPrice(blockTotalPrice);

		String blockShortName = (String)solrDocument.get("blockShortName_stored_string");
		if(blockShortName != null)
			oSchoolBlock.setBlockShortName(blockShortName);

		String blockAdminName = (String)solrDocument.get("blockAdminName_stored_string");
		if(blockAdminName != null)
			oSchoolBlock.setBlockAdminName(blockAdminName);

		String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
		if(blockCompleteName != null)
			oSchoolBlock.setBlockCompleteName(blockCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolBlock() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolBlock) {
			SchoolBlock original = (SchoolBlock)o;
			if(!Objects.equals(blockKey, original.getBlockKey()))
				apiRequest.addVars("blockKey");
			if(!Objects.equals(childKey, original.getChildKey()))
				apiRequest.addVars("childKey");
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(educationSort, original.getEducationSort()))
				apiRequest.addVars("educationSort");
			if(!Objects.equals(schoolSort, original.getSchoolSort()))
				apiRequest.addVars("schoolSort");
			if(!Objects.equals(yearSort, original.getYearSort()))
				apiRequest.addVars("yearSort");
			if(!Objects.equals(seasonSort, original.getSeasonSort()))
				apiRequest.addVars("seasonSort");
			if(!Objects.equals(sessionSort, original.getSessionSort()))
				apiRequest.addVars("sessionSort");
			if(!Objects.equals(ageSort, original.getAgeSort()))
				apiRequest.addVars("ageSort");
			if(!Objects.equals(ageKey, original.getAgeKey()))
				apiRequest.addVars("ageKey");
			if(!Objects.equals(schoolKey, original.getSchoolKey()))
				apiRequest.addVars("schoolKey");
			if(!Objects.equals(yearKey, original.getYearKey()))
				apiRequest.addVars("yearKey");
			if(!Objects.equals(sessionKey, original.getSessionKey()))
				apiRequest.addVars("sessionKey");
			if(!Objects.equals(schoolName, original.getSchoolName()))
				apiRequest.addVars("schoolName");
			if(!Objects.equals(schoolCompleteName, original.getSchoolCompleteName()))
				apiRequest.addVars("schoolCompleteName");
			if(!Objects.equals(schoolLocation, original.getSchoolLocation()))
				apiRequest.addVars("schoolLocation");
			if(!Objects.equals(schoolAddress, original.getSchoolAddress()))
				apiRequest.addVars("schoolAddress");
			if(!Objects.equals(schoolPhoneNumber, original.getSchoolPhoneNumber()))
				apiRequest.addVars("schoolPhoneNumber");
			if(!Objects.equals(schoolForm, original.getSchoolForm()))
				apiRequest.addVars("schoolForm");
			if(!Objects.equals(schoolNumber, original.getSchoolNumber()))
				apiRequest.addVars("schoolNumber");
			if(!Objects.equals(schoolAdministratorName, original.getSchoolAdministratorName()))
				apiRequest.addVars("schoolAdministratorName");
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
			if(!Objects.equals(ageShortName, original.getAgeShortName()))
				apiRequest.addVars("ageShortName");
			if(!Objects.equals(ageCompleteName, original.getAgeCompleteName()))
				apiRequest.addVars("ageCompleteName");
			if(!Objects.equals(ageStart, original.getAgeStart()))
				apiRequest.addVars("ageStart");
			if(!Objects.equals(ageEnd, original.getAgeEnd()))
				apiRequest.addVars("ageEnd");
			if(!Objects.equals(blockStartTime, original.getBlockStartTime()))
				apiRequest.addVars("blockStartTime");
			if(!Objects.equals(blockEndTime, original.getBlockEndTime()))
				apiRequest.addVars("blockEndTime");
			if(!Objects.equals(blockPricePerMonth, original.getBlockPricePerMonth()))
				apiRequest.addVars("blockPricePerMonth");
			if(!Objects.equals(blockSunday, original.getBlockSunday()))
				apiRequest.addVars("blockSunday");
			if(!Objects.equals(blockMonday, original.getBlockMonday()))
				apiRequest.addVars("blockMonday");
			if(!Objects.equals(blockTuesday, original.getBlockTuesday()))
				apiRequest.addVars("blockTuesday");
			if(!Objects.equals(blockWednesday, original.getBlockWednesday()))
				apiRequest.addVars("blockWednesday");
			if(!Objects.equals(blockThursday, original.getBlockThursday()))
				apiRequest.addVars("blockThursday");
			if(!Objects.equals(blockFriday, original.getBlockFriday()))
				apiRequest.addVars("blockFriday");
			if(!Objects.equals(blockSaturday, original.getBlockSaturday()))
				apiRequest.addVars("blockSaturday");
			if(!Objects.equals(blockTotalPrice, original.getBlockTotalPrice()))
				apiRequest.addVars("blockTotalPrice");
			if(!Objects.equals(blockShortName, original.getBlockShortName()))
				apiRequest.addVars("blockShortName");
			if(!Objects.equals(blockAdminName, original.getBlockAdminName()))
				apiRequest.addVars("blockAdminName");
			if(!Objects.equals(blockCompleteName, original.getBlockCompleteName()))
				apiRequest.addVars("blockCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), blockKey, childKey, enrollmentKeys, educationSort, schoolSort, yearSort, seasonSort, sessionSort, ageSort, ageKey, schoolKey, yearKey, sessionKey, schoolName, schoolCompleteName, schoolLocation, schoolAddress, schoolPhoneNumber, schoolForm, schoolNumber, schoolAdministratorName, yearStart, yearEnd, seasonStartDate, yearEnrollmentFee, sessionStartDate, sessionEndDate, ageShortName, ageCompleteName, ageStart, ageEnd, blockStartTime, blockEndTime, blockPricePerMonth, blockSunday, blockMonday, blockTuesday, blockWednesday, blockThursday, blockFriday, blockSaturday, blockTotalPrice, blockShortName, blockAdminName, blockCompleteName);
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
				&& Objects.equals( blockKey, that.blockKey )
				&& Objects.equals( childKey, that.childKey )
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( educationSort, that.educationSort )
				&& Objects.equals( schoolSort, that.schoolSort )
				&& Objects.equals( yearSort, that.yearSort )
				&& Objects.equals( seasonSort, that.seasonSort )
				&& Objects.equals( sessionSort, that.sessionSort )
				&& Objects.equals( ageSort, that.ageSort )
				&& Objects.equals( ageKey, that.ageKey )
				&& Objects.equals( schoolKey, that.schoolKey )
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( sessionKey, that.sessionKey )
				&& Objects.equals( schoolName, that.schoolName )
				&& Objects.equals( schoolCompleteName, that.schoolCompleteName )
				&& Objects.equals( schoolLocation, that.schoolLocation )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( schoolPhoneNumber, that.schoolPhoneNumber )
				&& Objects.equals( schoolForm, that.schoolForm )
				&& Objects.equals( schoolNumber, that.schoolNumber )
				&& Objects.equals( schoolAdministratorName, that.schoolAdministratorName )
				&& Objects.equals( yearStart, that.yearStart )
				&& Objects.equals( yearEnd, that.yearEnd )
				&& Objects.equals( seasonStartDate, that.seasonStartDate )
				&& Objects.equals( yearEnrollmentFee, that.yearEnrollmentFee )
				&& Objects.equals( sessionStartDate, that.sessionStartDate )
				&& Objects.equals( sessionEndDate, that.sessionEndDate )
				&& Objects.equals( ageShortName, that.ageShortName )
				&& Objects.equals( ageCompleteName, that.ageCompleteName )
				&& Objects.equals( ageStart, that.ageStart )
				&& Objects.equals( ageEnd, that.ageEnd )
				&& Objects.equals( blockStartTime, that.blockStartTime )
				&& Objects.equals( blockEndTime, that.blockEndTime )
				&& Objects.equals( blockPricePerMonth, that.blockPricePerMonth )
				&& Objects.equals( blockSunday, that.blockSunday )
				&& Objects.equals( blockMonday, that.blockMonday )
				&& Objects.equals( blockTuesday, that.blockTuesday )
				&& Objects.equals( blockWednesday, that.blockWednesday )
				&& Objects.equals( blockThursday, that.blockThursday )
				&& Objects.equals( blockFriday, that.blockFriday )
				&& Objects.equals( blockSaturday, that.blockSaturday )
				&& Objects.equals( blockTotalPrice, that.blockTotalPrice )
				&& Objects.equals( blockShortName, that.blockShortName )
				&& Objects.equals( blockAdminName, that.blockAdminName )
				&& Objects.equals( blockCompleteName, that.blockCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolBlock { ");
		sb.append( "blockKey: " ).append(blockKey);
		sb.append( ", childKey: " ).append(childKey);
		sb.append( ", enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", educationSort: " ).append(educationSort);
		sb.append( ", schoolSort: " ).append(schoolSort);
		sb.append( ", yearSort: " ).append(yearSort);
		sb.append( ", seasonSort: " ).append(seasonSort);
		sb.append( ", sessionSort: " ).append(sessionSort);
		sb.append( ", ageSort: " ).append(ageSort);
		sb.append( ", ageKey: " ).append(ageKey);
		sb.append( ", schoolKey: " ).append(schoolKey);
		sb.append( ", yearKey: " ).append(yearKey);
		sb.append( ", sessionKey: " ).append(sessionKey);
		sb.append( ", schoolName: \"" ).append(schoolName).append( "\"" );
		sb.append( ", schoolCompleteName: \"" ).append(schoolCompleteName).append( "\"" );
		sb.append( ", schoolLocation: \"" ).append(schoolLocation).append( "\"" );
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", schoolPhoneNumber: \"" ).append(schoolPhoneNumber).append( "\"" );
		sb.append( ", schoolForm: \"" ).append(schoolForm).append( "\"" );
		sb.append( ", schoolNumber: " ).append(schoolNumber);
		sb.append( ", schoolAdministratorName: \"" ).append(schoolAdministratorName).append( "\"" );
		sb.append( ", yearStart: " ).append(yearStart);
		sb.append( ", yearEnd: " ).append(yearEnd);
		sb.append( ", seasonStartDate: " ).append(seasonStartDate);
		sb.append( ", yearEnrollmentFee: " ).append(yearEnrollmentFee);
		sb.append( ", sessionStartDate: " ).append(sessionStartDate);
		sb.append( ", sessionEndDate: " ).append(sessionEndDate);
		sb.append( ", ageShortName: \"" ).append(ageShortName).append( "\"" );
		sb.append( ", ageCompleteName: \"" ).append(ageCompleteName).append( "\"" );
		sb.append( ", ageStart: " ).append(ageStart);
		sb.append( ", ageEnd: " ).append(ageEnd);
		sb.append( ", blockStartTime: " ).append(blockStartTime);
		sb.append( ", blockEndTime: " ).append(blockEndTime);
		sb.append( ", blockPricePerMonth: " ).append(blockPricePerMonth);
		sb.append( ", blockSunday: " ).append(blockSunday);
		sb.append( ", blockMonday: " ).append(blockMonday);
		sb.append( ", blockTuesday: " ).append(blockTuesday);
		sb.append( ", blockWednesday: " ).append(blockWednesday);
		sb.append( ", blockThursday: " ).append(blockThursday);
		sb.append( ", blockFriday: " ).append(blockFriday);
		sb.append( ", blockSaturday: " ).append(blockSaturday);
		sb.append( ", blockTotalPrice: " ).append(blockTotalPrice);
		sb.append( ", blockShortName: \"" ).append(blockShortName).append( "\"" );
		sb.append( ", blockAdminName: \"" ).append(blockAdminName).append( "\"" );
		sb.append( ", blockCompleteName: \"" ).append(blockCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
