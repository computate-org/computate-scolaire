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
import java.time.LocalTime;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolBlockGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolBlock.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
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

	/**	L'entité « blockKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long blockKey;
	@JsonIgnore
	public Wrap<Long> blockKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("blockKey").o(blockKey);

	/**	<br/>L'entité « blockKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Trouver l'entité blockKey dans Solr</a>
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

	//////////////
	// childKey //
	//////////////

	/**	L'entité « childKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long childKey;
	@JsonIgnore
	public Wrap<Long> childKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("childKey").o(childKey);

	/**	<br/>L'entité « childKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Trouver l'entité childKey dans Solr</a>
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
	public SchoolBlock setChildKey(String o) {
		if(NumberUtils.isParsable(o))
			this.childKey = Long.parseLong(o);
		this.childKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	/**	L'entité « enrollmentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> enrollmentKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/>L'entité « enrollmentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
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

	public void inputEnrollmentKeys(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "enrollments")
					.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setEnrollmentKeys")
					.a("id", classApiMethodMethod, "_enrollmentKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolBlockEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'blockKeys:" + pk + "'}", "], $('#listSchoolBlockEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

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
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("id", classApiMethodMethod, "_enrollmentKeys_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ blockKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
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

	///////////////////
	// enrollmentKey //
	///////////////////

	/**	L'entité « enrollmentKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long enrollmentKey;
	@JsonIgnore
	public Wrap<Long> enrollmentKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentKey").o(enrollmentKey);

	/**	<br/>L'entité « enrollmentKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKey">Trouver l'entité enrollmentKey dans Solr</a>
	 * <br/>
	 * @param o est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentKey(Wrap<Long> o);

	public Long getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(Long enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setEnrollmentKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentKey = Long.parseLong(o);
		this.enrollmentKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	/**	L'entité « enrollmentAttribute »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enrollmentAttribute;
	@JsonIgnore
	public Wrap<Boolean> enrollmentAttributeWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentAttribute").o(enrollmentAttribute);

	/**	<br/>L'entité « enrollmentAttribute »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentAttribute">Trouver l'entité enrollmentAttribute dans Solr</a>
	 * <br/>
	 * @param o est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentAttribute(Wrap<Boolean> o);

	public Boolean getEnrollmentAttribute() {
		return enrollmentAttribute;
	}

	public void setEnrollmentAttribute(Boolean enrollmentAttribute) {
		this.enrollmentAttribute = enrollmentAttribute;
		this.enrollmentAttributeWrap.alreadyInitialized = true;
	}
	public SchoolBlock setEnrollmentAttribute(String o) {
		this.enrollmentAttribute = Boolean.parseBoolean(o);
		this.enrollmentAttributeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	public Boolean solrEnrollmentAttribute() {
		return enrollmentAttribute;
	}

	public String strEnrollmentAttribute() {
		return enrollmentAttribute == null ? "" : enrollmentAttribute.toString();
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

	/**	L'entité « educationSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer educationSort;
	@JsonIgnore
	public Wrap<Integer> educationSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("educationSort").o(educationSort);

	/**	<br/>L'entité « educationSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
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

	////////////////
	// schoolSort //
	////////////////

	/**	L'entité « schoolSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer schoolSort;
	@JsonIgnore
	public Wrap<Integer> schoolSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolSort").o(schoolSort);

	/**	<br/>L'entité « schoolSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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

	//////////////
	// yearSort //
	//////////////

	/**	L'entité « yearSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearSort;
	@JsonIgnore
	public Wrap<Integer> yearSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearSort").o(yearSort);

	/**	<br/>L'entité « yearSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Trouver l'entité yearSort dans Solr</a>
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

	////////////////
	// seasonSort //
	////////////////

	/**	L'entité « seasonSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer seasonSort;
	@JsonIgnore
	public Wrap<Integer> seasonSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("seasonSort").o(seasonSort);

	/**	<br/>L'entité « seasonSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Trouver l'entité seasonSort dans Solr</a>
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

	/////////////////
	// sessionSort //
	/////////////////

	/**	L'entité « sessionSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sessionSort;
	@JsonIgnore
	public Wrap<Integer> sessionSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sessionSort").o(sessionSort);

	/**	<br/>L'entité « sessionSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSort">Trouver l'entité sessionSort dans Solr</a>
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

	/////////////
	// ageSort //
	/////////////

	/**	L'entité « ageSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageSort;
	@JsonIgnore
	public Wrap<Integer> ageSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageSort").o(ageSort);

	/**	<br/>L'entité « ageSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSort">Trouver l'entité ageSort dans Solr</a>
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

	////////////
	// ageKey //
	////////////

	/**	L'entité « ageKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long ageKey;
	@JsonIgnore
	public Wrap<Long> ageKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("ageKey").o(ageKey);

	/**	<br/>L'entité « ageKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Trouver l'entité ageKey dans Solr</a>
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

	public void inputAgeKey(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "age")
					.a("class", "valueObjectSuggest suggestAgeKey w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setAgeKey")
					.a("id", classApiMethodMethod, "_ageKey")
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolBlockAgeKey($(this).val() ? searchSchoolAgeFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'blockKeys:" + pk + "'}", "], $('#listSchoolBlockAgeKey_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

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
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
											.a("id", classApiMethodMethod, "_ageKey_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolAgeVals({ blockKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "ageKey')); });")
											.f().sx("add an age")
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

	///////////////
	// ageSearch //
	///////////////

	/**	L'entité « ageSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolAge>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolAge> ageSearch = new SearchList<SchoolAge>();
	@JsonIgnore
	public Wrap<SearchList<SchoolAge>> ageSearchWrap = new Wrap<SearchList<SchoolAge>>().p(this).c(SearchList.class).var("ageSearch").o(ageSearch);

	/**	<br/>L'entité « ageSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolAge>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSearch">Trouver l'entité ageSearch dans Solr</a>
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

	//////////
	// age_ //
	//////////

	/**	L'entité « age_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SchoolAge age_;
	@JsonIgnore
	public Wrap<SchoolAge> age_Wrap = new Wrap<SchoolAge>().p(this).c(SchoolAge.class).var("age_").o(age_);

	/**	<br/>L'entité « age_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:age_">Trouver l'entité age_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _age_(Wrap<SchoolAge> c);

	public SchoolAge getAge_() {
		return age_;
	}

	public void setAge_(SchoolAge age_) {
		this.age_ = age_;
		this.age_Wrap.alreadyInitialized = true;
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

	/**	L'entité « schoolKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/>L'entité « schoolKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
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

	/////////////
	// yearKey //
	/////////////

	/**	L'entité « yearKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long yearKey;
	@JsonIgnore
	public Wrap<Long> yearKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("yearKey").o(yearKey);

	/**	<br/>L'entité « yearKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Trouver l'entité yearKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearKey(Wrap<Long> c);

	public Long getYearKey() {
		return yearKey;
	}

	public void setYearKey(Long yearKey) {
		this.yearKey = yearKey;
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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
		return "year";
	}

	public String htmTooltipYearKey() {
		return null;
	}

	public String htmYearKey() {
		return yearKey == null ? "" : StringEscapeUtils.escapeHtml4(strYearKey());
	}

	///////////////
	// seasonKey //
	///////////////

	/**	L'entité « seasonKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long seasonKey;
	@JsonIgnore
	public Wrap<Long> seasonKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("seasonKey").o(seasonKey);

	/**	<br/>L'entité « seasonKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKey">Trouver l'entité seasonKey dans Solr</a>
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
		return "year";
	}

	public String htmTooltipSeasonKey() {
		return null;
	}

	public String htmSeasonKey() {
		return seasonKey == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKey());
	}

	////////////////
	// sessionKey //
	////////////////

	/**	L'entité « sessionKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long sessionKey;
	@JsonIgnore
	public Wrap<Long> sessionKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("sessionKey").o(sessionKey);

	/**	<br/>L'entité « sessionKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Trouver l'entité sessionKey dans Solr</a>
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

	/**	L'entité « schoolName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/>L'entité « schoolName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Trouver l'entité schoolName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
		this.schoolNameWrap.alreadyInitialized = true;
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

	/**	L'entité « schoolCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/>L'entité « schoolCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Trouver l'entité schoolCompleteName dans Solr</a>
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
	protected SchoolBlock schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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

	/**	L'entité « schoolLocation »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation;
	@JsonIgnore
	public Wrap<String> schoolLocationWrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation").o(schoolLocation);

	/**	<br/>L'entité « schoolLocation »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Trouver l'entité schoolLocation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}

	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
		this.schoolLocationWrap.alreadyInitialized = true;
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

	///////////////////
	// schoolAddress //
	///////////////////

	/**	L'entité « schoolAddress »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAddress;
	@JsonIgnore
	public Wrap<String> schoolAddressWrap = new Wrap<String>().p(this).c(String.class).var("schoolAddress").o(schoolAddress);

	/**	<br/>L'entité « schoolAddress »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Trouver l'entité schoolAddress dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
		this.schoolAddressWrap.alreadyInitialized = true;
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
		{
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
					a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSchoolAddress', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ");
				}
				a("value", strSchoolAddress())
			.fg();

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
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_schoolAddress')); $('#", classApiMethodMethod, "_schoolAddress').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setSchoolAddress', null, function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ")
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

	/**	L'entité « schoolPhoneNumber »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolPhoneNumber;
	@JsonIgnore
	public Wrap<String> schoolPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("schoolPhoneNumber").o(schoolPhoneNumber);

	/**	<br/>L'entité « schoolPhoneNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Trouver l'entité schoolPhoneNumber dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}

	public void setSchoolPhoneNumber(String schoolPhoneNumber) {
		this.schoolPhoneNumber = schoolPhoneNumber;
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
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
		return "phone number";
	}

	public String htmTooltipSchoolPhoneNumber() {
		return null;
	}

	public String htmSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolPhoneNumber());
	}

	/////////////////////////////
	// schoolAdministratorName //
	/////////////////////////////

	/**	L'entité « schoolAdministratorName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAdministratorName;
	@JsonIgnore
	public Wrap<String> schoolAdministratorNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolAdministratorName").o(schoolAdministratorName);

	/**	<br/>L'entité « schoolAdministratorName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Trouver l'entité schoolAdministratorName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}

	public void setSchoolAdministratorName(String schoolAdministratorName) {
		this.schoolAdministratorName = schoolAdministratorName;
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
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

	public String solrSchoolAdministratorName() {
		return schoolAdministratorName;
	}

	public String strSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
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

	/**	L'entité « yearStart »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
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
	public SchoolBlock setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
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

	/**	L'entité « yearEnd »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
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
	public SchoolBlock setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
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

	/**	L'entité « seasonStartDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate seasonStartDate;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDate").o(seasonStartDate);

	/**	<br/>L'entité « seasonStartDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Trouver l'entité seasonStartDate dans Solr</a>
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
	public SchoolBlock setSeasonStartDate(Instant o) {
		this.seasonStartDate = o == null ? null : LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSeasonStartDate(String o) {
		this.seasonStartDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	//////////////////
	// seasonSummer //
	//////////////////

	/**	L'entité « seasonSummer »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean seasonSummer;
	@JsonIgnore
	public Wrap<Boolean> seasonSummerWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonSummer").o(seasonSummer);

	/**	<br/>L'entité « seasonSummer »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSummer">Trouver l'entité seasonSummer dans Solr</a>
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

	//////////////////
	// seasonWinter //
	//////////////////

	/**	L'entité « seasonWinter »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean seasonWinter;
	@JsonIgnore
	public Wrap<Boolean> seasonWinterWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonWinter").o(seasonWinter);

	/**	<br/>L'entité « seasonWinter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonWinter">Trouver l'entité seasonWinter dans Solr</a>
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

	///////////////////////
	// yearEnrollmentFee //
	///////////////////////

	/**	L'entité « yearEnrollmentFee »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal yearEnrollmentFee;
	@JsonIgnore
	public Wrap<BigDecimal> yearEnrollmentFeeWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("yearEnrollmentFee").o(yearEnrollmentFee);

	/**	<br/>L'entité « yearEnrollmentFee »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Trouver l'entité yearEnrollmentFee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearEnrollmentFee(Wrap<BigDecimal> c);

	public BigDecimal getYearEnrollmentFee() {
		return yearEnrollmentFee;
	}

	public void setYearEnrollmentFee(BigDecimal yearEnrollmentFee) {
		this.yearEnrollmentFee = yearEnrollmentFee;
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public SchoolBlock setYearEnrollmentFee(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setYearEnrollmentFee(Double o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setYearEnrollmentFee(Integer o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	public Double solrYearEnrollmentFee() {
		return yearEnrollmentFee == null ? null : yearEnrollmentFee.doubleValue();
	}

	public String strYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.setScale(2).toString();
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

	/////////////////////
	// seasonShortName //
	/////////////////////

	/**	L'entité « seasonShortName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String seasonShortName;
	@JsonIgnore
	public Wrap<String> seasonShortNameWrap = new Wrap<String>().p(this).c(String.class).var("seasonShortName").o(seasonShortName);

	/**	<br/>L'entité « seasonShortName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonShortName">Trouver l'entité seasonShortName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonShortName(Wrap<String> c);

	public String getSeasonShortName() {
		return seasonShortName;
	}

	public void setSeasonShortName(String seasonShortName) {
		this.seasonShortName = seasonShortName;
		this.seasonShortNameWrap.alreadyInitialized = true;
	}
	protected SchoolBlock seasonShortNameInit() {
		if(!seasonShortNameWrap.alreadyInitialized) {
			_seasonShortName(seasonShortNameWrap);
			if(seasonShortName == null)
				setSeasonShortName(seasonShortNameWrap.o);
		}
		seasonShortNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrSeasonShortName() {
		return seasonShortName;
	}

	public String strSeasonShortName() {
		return seasonShortName == null ? "" : seasonShortName;
	}

	public String jsonSeasonShortName() {
		return seasonShortName == null ? "" : seasonShortName;
	}

	public String nomAffichageSeasonShortName() {
		return null;
	}

	public String htmTooltipSeasonShortName() {
		return null;
	}

	public String htmSeasonShortName() {
		return seasonShortName == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonShortName());
	}

	////////////////////////
	// seasonCompleteName //
	////////////////////////

	/**	L'entité « seasonCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String seasonCompleteName;
	@JsonIgnore
	public Wrap<String> seasonCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("seasonCompleteName").o(seasonCompleteName);

	/**	<br/>L'entité « seasonCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonCompleteName">Trouver l'entité seasonCompleteName dans Solr</a>
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
	protected SchoolBlock seasonCompleteNameInit() {
		if(!seasonCompleteNameWrap.alreadyInitialized) {
			_seasonCompleteName(seasonCompleteNameWrap);
			if(seasonCompleteName == null)
				setSeasonCompleteName(seasonCompleteNameWrap.o);
		}
		seasonCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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

	//////////////////////
	// sessionStartDate //
	//////////////////////

	/**	L'entité « sessionStartDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionStartDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionStartDate").o(sessionStartDate);

	/**	<br/>L'entité « sessionStartDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDate">Trouver l'entité sessionStartDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionStartDate(Wrap<LocalDate> c);

	public LocalDate getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(LocalDate sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionStartDate(Instant o) {
		this.sessionStartDate = o == null ? null : LocalDate.from(o);
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSessionStartDate(String o) {
		this.sessionStartDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSessionStartDate(Date o) {
		this.sessionStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	/**	L'entité « sessionEndDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionEndDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionEndDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionEndDate").o(sessionEndDate);

	/**	<br/>L'entité « sessionEndDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDate">Trouver l'entité sessionEndDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionEndDate(Wrap<LocalDate> c);

	public LocalDate getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(LocalDate sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionEndDate(Instant o) {
		this.sessionEndDate = o == null ? null : LocalDate.from(o);
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSessionEndDate(String o) {
		this.sessionEndDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSessionEndDate(Date o) {
		this.sessionEndDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	//////////////////
	// ageShortName //
	//////////////////

	/**	L'entité « ageShortName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ageShortName;
	@JsonIgnore
	public Wrap<String> ageShortNameWrap = new Wrap<String>().p(this).c(String.class).var("ageShortName").o(ageShortName);

	/**	<br/>L'entité « ageShortName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageShortName">Trouver l'entité ageShortName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageShortName(Wrap<String> c);

	public String getAgeShortName() {
		return ageShortName;
	}

	public void setAgeShortName(String ageShortName) {
		this.ageShortName = ageShortName;
		this.ageShortNameWrap.alreadyInitialized = true;
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

	public String solrAgeShortName() {
		return ageShortName;
	}

	public String strAgeShortName() {
		return ageShortName == null ? "" : ageShortName;
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

	/**	L'entité « ageCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ageCompleteName;
	@JsonIgnore
	public Wrap<String> ageCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("ageCompleteName").o(ageCompleteName);

	/**	<br/>L'entité « ageCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageCompleteName">Trouver l'entité ageCompleteName dans Solr</a>
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
	protected SchoolBlock ageCompleteNameInit() {
		if(!ageCompleteNameWrap.alreadyInitialized) {
			_ageCompleteName(ageCompleteNameWrap);
			if(ageCompleteName == null)
				setAgeCompleteName(ageCompleteNameWrap.o);
		}
		ageCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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

	//////////////
	// ageStart //
	//////////////

	/**	L'entité « ageStart »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageStart;
	@JsonIgnore
	public Wrap<Integer> ageStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageStart").o(ageStart);

	/**	<br/>L'entité « ageStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Trouver l'entité ageStart dans Solr</a>
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

	////////////
	// ageEnd //
	////////////

	/**	L'entité « ageEnd »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageEnd;
	@JsonIgnore
	public Wrap<Integer> ageEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageEnd").o(ageEnd);

	/**	<br/>L'entité « ageEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Trouver l'entité ageEnd dans Solr</a>
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

	////////////////////
	// blockStartTime //
	////////////////////

	/**	L'entité « blockStartTime »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blockStartTime;
	@JsonIgnore
	public Wrap<LocalTime> blockStartTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockStartTime").o(blockStartTime);

	/**	<br/>L'entité « blockStartTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockStartTime">Trouver l'entité blockStartTime dans Solr</a>
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
	public SchoolBlock setBlockStartTime(String o) {
		try {
			this.blockStartTime = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
			this.blockStartTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolBlock)this;
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

	public void inputBlockStartTime(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border timepicker setBlockStartTime classSchoolBlock inputSchoolBlock", pk, "BlockStartTime w3-input w3-border ")
				.a("placeholder", "HH:MM A")
				.a("id", classApiMethodMethod, "_blockStartTime")
				.a("onclick", "removeGlow($(this)); ")
				.a("value", blockStartTime == null ? "" : DateTimeFormatter.ofPattern("h:mm a").format(blockStartTime))
				.a("onchange", "var t = moment(this.value, 'h:mm a'); if(t) { var s = t.format('HH:mm'); patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockStartTime', s, function() { addGlow($('#", classApiMethodMethod, "_blockStartTime')); }, function() { addError($('#", classApiMethodMethod, "_blockStartTime')); }); } ")
				.fg();
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
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockStartTime')); $('#", classApiMethodMethod, "_blockStartTime').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockStartTime', null, function() { addGlow($('#", classApiMethodMethod, "_blockStartTime')); }, function() { addError($('#", classApiMethodMethod, "_blockStartTime')); }); ")
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

	/**	L'entité « blockEndTime »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blockEndTime;
	@JsonIgnore
	public Wrap<LocalTime> blockEndTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockEndTime").o(blockEndTime);

	/**	<br/>L'entité « blockEndTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockEndTime">Trouver l'entité blockEndTime dans Solr</a>
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
	public SchoolBlock setBlockEndTime(String o) {
		try {
			this.blockEndTime = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
			this.blockEndTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolBlock)this;
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

	public void inputBlockEndTime(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border timepicker setBlockEndTime classSchoolBlock inputSchoolBlock", pk, "BlockEndTime w3-input w3-border ")
				.a("placeholder", "HH:MM A")
				.a("id", classApiMethodMethod, "_blockEndTime")
				.a("onclick", "removeGlow($(this)); ")
				.a("value", blockEndTime == null ? "" : DateTimeFormatter.ofPattern("h:mm a").format(blockEndTime))
				.a("onchange", "var t = moment(this.value, 'h:mm a'); if(t) { var s = t.format('HH:mm'); patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockEndTime', s, function() { addGlow($('#", classApiMethodMethod, "_blockEndTime')); }, function() { addError($('#", classApiMethodMethod, "_blockEndTime')); }); } ")
				.fg();
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
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockEndTime')); $('#", classApiMethodMethod, "_blockEndTime').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockEndTime', null, function() { addGlow($('#", classApiMethodMethod, "_blockEndTime')); }, function() { addError($('#", classApiMethodMethod, "_blockEndTime')); }); ")
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

	/**	L'entité « blockPricePerMonth »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blockPricePerMonth;
	@JsonIgnore
	public Wrap<BigDecimal> blockPricePerMonthWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockPricePerMonth").o(blockPricePerMonth);

	/**	<br/>L'entité « blockPricePerMonth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Trouver l'entité blockPricePerMonth dans Solr</a>
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
		return blockPricePerMonth == null ? "" : blockPricePerMonth.setScale(2).toString();
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
		{
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
					a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockPricePerMonth', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); }, function() { addError($('#", classApiMethodMethod, "_blockPricePerMonth')); }); ");
				}
				a("value", strBlockPricePerMonth())
			.fg();

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
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); $('#", classApiMethodMethod, "_blockPricePerMonth').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockPricePerMonth', null, function() { addGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); }, function() { addError($('#", classApiMethodMethod, "_blockPricePerMonth')); }); ")
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

	/**	L'entité « blockSunday »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockSunday;
	@JsonIgnore
	public Wrap<Boolean> blockSundayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSunday").o(blockSunday);

	/**	<br/>L'entité « blockSunday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSunday">Trouver l'entité blockSunday dans Solr</a>
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

	/////////////////
	// blockMonday //
	/////////////////

	/**	L'entité « blockMonday »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockMonday;
	@JsonIgnore
	public Wrap<Boolean> blockMondayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockMonday").o(blockMonday);

	/**	<br/>L'entité « blockMonday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockMonday">Trouver l'entité blockMonday dans Solr</a>
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

	public void inputBlockMonday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
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
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockMonday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockMonday')); }, function() { addError($('#", classApiMethodMethod, "_blockMonday')); }); ");
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

	/**	L'entité « blockTuesday »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockTuesday;
	@JsonIgnore
	public Wrap<Boolean> blockTuesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockTuesday").o(blockTuesday);

	/**	<br/>L'entité « blockTuesday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTuesday">Trouver l'entité blockTuesday dans Solr</a>
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

	public void inputBlockTuesday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
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
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockTuesday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockTuesday')); }, function() { addError($('#", classApiMethodMethod, "_blockTuesday')); }); ");
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

	/**	L'entité « blockWednesday »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockWednesday;
	@JsonIgnore
	public Wrap<Boolean> blockWednesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockWednesday").o(blockWednesday);

	/**	<br/>L'entité « blockWednesday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockWednesday">Trouver l'entité blockWednesday dans Solr</a>
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

	public void inputBlockWednesday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
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
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockWednesday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockWednesday')); }, function() { addError($('#", classApiMethodMethod, "_blockWednesday')); }); ");
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

	/**	L'entité « blockThursday »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockThursday;
	@JsonIgnore
	public Wrap<Boolean> blockThursdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockThursday").o(blockThursday);

	/**	<br/>L'entité « blockThursday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockThursday">Trouver l'entité blockThursday dans Solr</a>
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

	public void inputBlockThursday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
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
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockThursday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockThursday')); }, function() { addError($('#", classApiMethodMethod, "_blockThursday')); }); ");
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

	/**	L'entité « blockFriday »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockFriday;
	@JsonIgnore
	public Wrap<Boolean> blockFridayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockFriday").o(blockFriday);

	/**	<br/>L'entité « blockFriday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockFriday">Trouver l'entité blockFriday dans Solr</a>
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

	public void inputBlockFriday(String classApiMethodMethod) {
		SchoolBlock s = (SchoolBlock)this;
		{
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
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockFriday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockFriday')); }, function() { addError($('#", classApiMethodMethod, "_blockFriday')); }); ");
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

	/**	L'entité « blockSaturday »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockSaturday;
	@JsonIgnore
	public Wrap<Boolean> blockSaturdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSaturday").o(blockSaturday);

	/**	<br/>L'entité « blockSaturday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSaturday">Trouver l'entité blockSaturday dans Solr</a>
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

	/////////////////////
	// blockTotalPrice //
	/////////////////////

	/**	L'entité « blockTotalPrice »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blockTotalPrice;
	@JsonIgnore
	public Wrap<BigDecimal> blockTotalPriceWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockTotalPrice").o(blockTotalPrice);

	/**	<br/>L'entité « blockTotalPrice »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTotalPrice">Trouver l'entité blockTotalPrice dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockTotalPrice(Wrap<BigDecimal> c);

	public BigDecimal getBlockTotalPrice() {
		return blockTotalPrice;
	}

	public void setBlockTotalPrice(BigDecimal blockTotalPrice) {
		this.blockTotalPrice = blockTotalPrice;
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockTotalPrice(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockTotalPrice(Double o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockTotalPrice(Integer o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
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

	public Double solrBlockTotalPrice() {
		return blockTotalPrice == null ? null : blockTotalPrice.doubleValue();
	}

	public String strBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.setScale(2).toString();
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

	/**	L'entité « sessionBlocks »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> sessionBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> sessionBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("sessionBlocks").o(sessionBlocks);

	/**	<br/>L'entité « sessionBlocks »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionBlocks">Trouver l'entité sessionBlocks dans Solr</a>
	 * <br/>
	 * @param sessionBlocks est l'entité déjà construit. 
	 **/
	protected abstract void _sessionBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getSessionBlocks() {
		return sessionBlocks;
	}

	public void setSessionBlocks(List<SchoolBlock> sessionBlocks) {
		this.sessionBlocks = sessionBlocks;
		this.sessionBlocksWrap.alreadyInitialized = true;
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

	/**	L'entité « ageBlocks »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> ageBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> ageBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("ageBlocks").o(ageBlocks);

	/**	<br/>L'entité « ageBlocks »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageBlocks">Trouver l'entité ageBlocks dans Solr</a>
	 * <br/>
	 * @param ageBlocks est l'entité déjà construit. 
	 **/
	protected abstract void _ageBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getAgeBlocks() {
		return ageBlocks;
	}

	public void setAgeBlocks(List<SchoolBlock> ageBlocks) {
		this.ageBlocks = ageBlocks;
		this.ageBlocksWrap.alreadyInitialized = true;
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

	/**	L'entité « blockBlocks »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> blockBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> blockBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("blockBlocks").o(blockBlocks);

	/**	<br/>L'entité « blockBlocks »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockBlocks">Trouver l'entité blockBlocks dans Solr</a>
	 * <br/>
	 * @param blockBlocks est l'entité déjà construit. 
	 **/
	protected abstract void _blockBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getBlockBlocks() {
		return blockBlocks;
	}

	public void setBlockBlocks(List<SchoolBlock> blockBlocks) {
		this.blockBlocks = blockBlocks;
		this.blockBlocksWrap.alreadyInitialized = true;
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

	/**	L'entité « blockShortName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockShortName;
	@JsonIgnore
	public Wrap<String> blockShortNameWrap = new Wrap<String>().p(this).c(String.class).var("blockShortName").o(blockShortName);

	/**	<br/>L'entité « blockShortName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockShortName">Trouver l'entité blockShortName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockShortName(Wrap<String> c);

	public String getBlockShortName() {
		return blockShortName;
	}

	public void setBlockShortName(String blockShortName) {
		this.blockShortName = blockShortName;
		this.blockShortNameWrap.alreadyInitialized = true;
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

	public String solrBlockShortName() {
		return blockShortName;
	}

	public String strBlockShortName() {
		return blockShortName == null ? "" : blockShortName;
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

	/**	L'entité « blockAdminName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockAdminName;
	@JsonIgnore
	public Wrap<String> blockAdminNameWrap = new Wrap<String>().p(this).c(String.class).var("blockAdminName").o(blockAdminName);

	/**	<br/>L'entité « blockAdminName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockAdminName">Trouver l'entité blockAdminName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockAdminName(Wrap<String> c);

	public String getBlockAdminName() {
		return blockAdminName;
	}

	public void setBlockAdminName(String blockAdminName) {
		this.blockAdminName = blockAdminName;
		this.blockAdminNameWrap.alreadyInitialized = true;
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

	public String solrBlockAdminName() {
		return blockAdminName;
	}

	public String strBlockAdminName() {
		return blockAdminName == null ? "" : blockAdminName;
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

	/**	L'entité « blockCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockCompleteName;
	@JsonIgnore
	public Wrap<String> blockCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("blockCompleteName").o(blockCompleteName);

	/**	<br/>L'entité « blockCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockCompleteName">Trouver l'entité blockCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockCompleteName(Wrap<String> c);

	public String getBlockCompleteName() {
		return blockCompleteName;
	}

	public void setBlockCompleteName(String blockCompleteName) {
		this.blockCompleteName = blockCompleteName;
		this.blockCompleteNameWrap.alreadyInitialized = true;
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

	public String solrBlockCompleteName() {
		return blockCompleteName;
	}

	public String strBlockCompleteName() {
		return blockCompleteName == null ? "" : blockCompleteName;
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
		seasonKeyInit();
		sessionKeyInit();
		schoolNameInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		schoolAddressInit();
		schoolPhoneNumberInit();
		schoolAdministratorNameInit();
		yearStartInit();
		yearEndInit();
		seasonStartDateInit();
		seasonSummerInit();
		seasonWinterInit();
		yearEnrollmentFeeInit();
		seasonShortNameInit();
		seasonCompleteNameInit();
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
			case "seasonKey":
				return oSchoolBlock.seasonKey;
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
			case "schoolAdministratorName":
				return oSchoolBlock.schoolAdministratorName;
			case "yearStart":
				return oSchoolBlock.yearStart;
			case "yearEnd":
				return oSchoolBlock.yearEnd;
			case "seasonStartDate":
				return oSchoolBlock.seasonStartDate;
			case "seasonSummer":
				return oSchoolBlock.seasonSummer;
			case "seasonWinter":
				return oSchoolBlock.seasonWinter;
			case "yearEnrollmentFee":
				return oSchoolBlock.yearEnrollmentFee;
			case "seasonShortName":
				return oSchoolBlock.seasonShortName;
			case "seasonCompleteName":
				return oSchoolBlock.seasonCompleteName;
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
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "ageKey":
				oSchoolBlock.setAgeKey((Long)val);
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
			case "schoolAddress":
				if(val != null)
					setSchoolAddress(val);
				saves.add(var);
				return val;
			case "blockStartTime":
				if(val != null)
					setBlockStartTime(val);
				saves.add(var);
				return val;
			case "blockEndTime":
				if(val != null)
					setBlockEndTime(val);
				saves.add(var);
				return val;
			case "blockPricePerMonth":
				if(val != null)
					setBlockPricePerMonth(val);
				saves.add(var);
				return val;
			case "blockMonday":
				if(val != null)
					setBlockMonday(val);
				saves.add(var);
				return val;
			case "blockTuesday":
				if(val != null)
					setBlockTuesday(val);
				saves.add(var);
				return val;
			case "blockWednesday":
				if(val != null)
					setBlockWednesday(val);
				saves.add(var);
				return val;
			case "blockThursday":
				if(val != null)
					setBlockThursday(val);
				saves.add(var);
				return val;
			case "blockFriday":
				if(val != null)
					setBlockFriday(val);
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

			if(saves.contains("seasonKey")) {
				Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
				if(seasonKey != null)
					oSchoolBlock.setSeasonKey(seasonKey);
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

			if(saves.contains("seasonSummer")) {
				Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
				if(seasonSummer != null)
					oSchoolBlock.setSeasonSummer(seasonSummer);
			}

			if(saves.contains("seasonWinter")) {
				Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
				if(seasonWinter != null)
					oSchoolBlock.setSeasonWinter(seasonWinter);
			}

			if(saves.contains("yearEnrollmentFee")) {
				Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
				if(yearEnrollmentFee != null)
					oSchoolBlock.setYearEnrollmentFee(yearEnrollmentFee);
			}

			if(saves.contains("seasonShortName")) {
				String seasonShortName = (String)solrDocument.get("seasonShortName_stored_string");
				if(seasonShortName != null)
					oSchoolBlock.setSeasonShortName(seasonShortName);
			}

			if(saves.contains("seasonCompleteName")) {
				String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
				if(seasonCompleteName != null)
					oSchoolBlock.setSeasonCompleteName(seasonCompleteName);
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
		if(seasonKey != null) {
			document.addField("seasonKey_indexed_long", seasonKey);
			document.addField("seasonKey_stored_long", seasonKey);
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
		if(seasonSummer != null) {
			document.addField("seasonSummer_indexed_boolean", seasonSummer);
			document.addField("seasonSummer_stored_boolean", seasonSummer);
		}
		if(seasonWinter != null) {
			document.addField("seasonWinter_indexed_boolean", seasonWinter);
			document.addField("seasonWinter_stored_boolean", seasonWinter);
		}
		if(yearEnrollmentFee != null) {
			document.addField("yearEnrollmentFee_indexed_double", yearEnrollmentFee.doubleValue());
			document.addField("yearEnrollmentFee_stored_double", yearEnrollmentFee.doubleValue());
		}
		if(seasonShortName != null) {
			document.addField("seasonShortName_indexed_string", seasonShortName);
			document.addField("seasonShortName_stored_string", seasonShortName);
		}
		if(seasonCompleteName != null) {
			document.addField("seasonCompleteName_indexed_string", seasonCompleteName);
			document.addField("seasonCompleteName_stored_string", seasonCompleteName);
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
			case "seasonKey":
				return "seasonKey_indexed_long";
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
			case "schoolAdministratorName":
				return "schoolAdministratorName_indexed_string";
			case "yearStart":
				return "yearStart_indexed_int";
			case "yearEnd":
				return "yearEnd_indexed_int";
			case "seasonStartDate":
				return "seasonStartDate_indexed_date";
			case "seasonSummer":
				return "seasonSummer_indexed_boolean";
			case "seasonWinter":
				return "seasonWinter_indexed_boolean";
			case "yearEnrollmentFee":
				return "yearEnrollmentFee_indexed_double";
			case "seasonShortName":
				return "seasonShortName_indexed_string";
			case "seasonCompleteName":
				return "seasonCompleteName_indexed_string";
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

		Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
		if(seasonKey != null)
			oSchoolBlock.setSeasonKey(seasonKey);

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

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolBlock.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolBlock.setSeasonWinter(seasonWinter);

		Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
		if(yearEnrollmentFee != null)
			oSchoolBlock.setYearEnrollmentFee(yearEnrollmentFee);

		String seasonShortName = (String)solrDocument.get("seasonShortName_stored_string");
		if(seasonShortName != null)
			oSchoolBlock.setSeasonShortName(seasonShortName);

		String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
		if(seasonCompleteName != null)
			oSchoolBlock.setSeasonCompleteName(seasonCompleteName);

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
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(ageKey, original.getAgeKey()))
				apiRequest.addVars("ageKey");
			if(!Objects.equals(schoolAddress, original.getSchoolAddress()))
				apiRequest.addVars("schoolAddress");
			if(!Objects.equals(blockStartTime, original.getBlockStartTime()))
				apiRequest.addVars("blockStartTime");
			if(!Objects.equals(blockEndTime, original.getBlockEndTime()))
				apiRequest.addVars("blockEndTime");
			if(!Objects.equals(blockPricePerMonth, original.getBlockPricePerMonth()))
				apiRequest.addVars("blockPricePerMonth");
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
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentKeys, ageKey, schoolAddress, blockStartTime, blockEndTime, blockPricePerMonth, blockMonday, blockTuesday, blockWednesday, blockThursday, blockFriday);
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
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( ageKey, that.ageKey )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( blockStartTime, that.blockStartTime )
				&& Objects.equals( blockEndTime, that.blockEndTime )
				&& Objects.equals( blockPricePerMonth, that.blockPricePerMonth )
				&& Objects.equals( blockMonday, that.blockMonday )
				&& Objects.equals( blockTuesday, that.blockTuesday )
				&& Objects.equals( blockWednesday, that.blockWednesday )
				&& Objects.equals( blockThursday, that.blockThursday )
				&& Objects.equals( blockFriday, that.blockFriday );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolBlock { ");
		sb.append( "enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", ageKey: " ).append(ageKey);
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", blockStartTime: " ).append(blockStartTime);
		sb.append( ", blockEndTime: " ).append(blockEndTime);
		sb.append( ", blockPricePerMonth: " ).append(blockPricePerMonth);
		sb.append( ", blockMonday: " ).append(blockMonday);
		sb.append( ", blockTuesday: " ).append(blockTuesday);
		sb.append( ", blockWednesday: " ).append(blockWednesday);
		sb.append( ", blockThursday: " ).append(blockThursday);
		sb.append( ", blockFriday: " ).append(blockFriday);
		sb.append(" }");
		return sb.toString();
	}
}
