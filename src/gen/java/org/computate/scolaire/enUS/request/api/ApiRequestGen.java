package org.computate.scolaire.enUS.request.api;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.java.ZonedDateTimeSerializer;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.wrap.Wrap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class ApiRequestGen<DEV> extends Object {
	protected static final Logger LOG = LoggerFactory.getLogger(ApiRequest.class);

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	 The entity siteRequest_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SiteRequestEnUS siteRequest_;

	/**	<br> The entity siteRequest_
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Find the entity siteRequest_ in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequestEnUS> c);

	public SiteRequestEnUS getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequestEnUS siteRequest_) {
		this.siteRequest_ = siteRequest_;
	}
	public static SiteRequestEnUS staticSetSiteRequest_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected ApiRequest siteRequest_Init() {
		Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().var("siteRequest_");
		if(siteRequest_ == null) {
			_siteRequest_(siteRequest_Wrap);
			setSiteRequest_(siteRequest_Wrap.o);
		}
		return (ApiRequest)this;
	}

	/////////////
	// created //
	/////////////

	/**	 The entity created
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'")
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime created;

	/**	<br> The entity created
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:created">Find the entity created in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _created(Wrap<ZonedDateTime> c);

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}
	@JsonIgnore
	public void setCreated(Instant o) {
		this.created = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	@JsonIgnore
	public void setCreated(String o) {
		this.created = ApiRequest.staticSetCreated(siteRequest_, o);
	}
	public static ZonedDateTime staticSetCreated(SiteRequestEnUS siteRequest_, String o) {
		if(StringUtils.endsWith(o, "Z"))
			return o == null ? null : Instant.parse(o).atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		else
			return o == null ? null : ZonedDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).truncatedTo(ChronoUnit.MILLIS);
	}
	@JsonIgnore
	public void setCreated(Date o) {
		this.created = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
	}
	protected ApiRequest createdInit() {
		Wrap<ZonedDateTime> createdWrap = new Wrap<ZonedDateTime>().var("created");
		if(created == null) {
			_created(createdWrap);
			setCreated(createdWrap.o);
		}
		return (ApiRequest)this;
	}

	public static Date staticSearchCreated(SiteRequestEnUS siteRequest_, ZonedDateTime o) {
		return o == null ? null : Date.from(o.toInstant());
	}

	public static String staticSearchStrCreated(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSearchFqCreated(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrCreated(siteRequest_, ApiRequest.staticSearchCreated(siteRequest_, ApiRequest.staticSetCreated(siteRequest_, o)));
	}

	//////////
	// rows //
	//////////

	/**	 The entity rows
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer rows;

	/**	<br> The entity rows
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:rows">Find the entity rows in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _rows(Wrap<Integer> c);

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	@JsonIgnore
	public void setRows(String o) {
		this.rows = ApiRequest.staticSetRows(siteRequest_, o);
	}
	public static Integer staticSetRows(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ApiRequest rowsInit() {
		Wrap<Integer> rowsWrap = new Wrap<Integer>().var("rows");
		if(rows == null) {
			_rows(rowsWrap);
			setRows(rowsWrap.o);
		}
		return (ApiRequest)this;
	}

	public static Integer staticSearchRows(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrRows(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRows(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrRows(siteRequest_, ApiRequest.staticSearchRows(siteRequest_, ApiRequest.staticSetRows(siteRequest_, o)));
	}

	//////////////
	// numFound //
	//////////////

	/**	 The entity numFound
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long numFound;

	/**	<br> The entity numFound
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:numFound">Find the entity numFound in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numFound(Wrap<Long> c);

	public Long getNumFound() {
		return numFound;
	}

	public void setNumFound(Long numFound) {
		this.numFound = numFound;
	}
	@JsonIgnore
	public void setNumFound(String o) {
		this.numFound = ApiRequest.staticSetNumFound(siteRequest_, o);
	}
	public static Long staticSetNumFound(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ApiRequest numFoundInit() {
		Wrap<Long> numFoundWrap = new Wrap<Long>().var("numFound");
		if(numFound == null) {
			_numFound(numFoundWrap);
			setNumFound(numFoundWrap.o);
		}
		return (ApiRequest)this;
	}

	public static Long staticSearchNumFound(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrNumFound(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumFound(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrNumFound(siteRequest_, ApiRequest.staticSearchNumFound(siteRequest_, ApiRequest.staticSetNumFound(siteRequest_, o)));
	}

	//////////////
	// numPATCH //
	//////////////

	/**	 The entity numPATCH
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long numPATCH;

	/**	<br> The entity numPATCH
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:numPATCH">Find the entity numPATCH in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _numPATCH(Wrap<Long> c);

	public Long getNumPATCH() {
		return numPATCH;
	}

	public void setNumPATCH(Long numPATCH) {
		this.numPATCH = numPATCH;
	}
	@JsonIgnore
	public void setNumPATCH(String o) {
		this.numPATCH = ApiRequest.staticSetNumPATCH(siteRequest_, o);
	}
	public static Long staticSetNumPATCH(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ApiRequest numPATCHInit() {
		Wrap<Long> numPATCHWrap = new Wrap<Long>().var("numPATCH");
		if(numPATCH == null) {
			_numPATCH(numPATCHWrap);
			setNumPATCH(numPATCHWrap.o);
		}
		return (ApiRequest)this;
	}

	public static Long staticSearchNumPATCH(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrNumPATCH(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNumPATCH(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrNumPATCH(siteRequest_, ApiRequest.staticSearchNumPATCH(siteRequest_, ApiRequest.staticSetNumPATCH(siteRequest_, o)));
	}

	//////////
	// uuid //
	//////////

	/**	 The entity uuid
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String uuid;

	/**	<br> The entity uuid
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:uuid">Find the entity uuid in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _uuid(Wrap<String> c);

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String o) {
		this.uuid = ApiRequest.staticSetUuid(siteRequest_, o);
	}
	public static String staticSetUuid(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected ApiRequest uuidInit() {
		Wrap<String> uuidWrap = new Wrap<String>().var("uuid");
		if(uuid == null) {
			_uuid(uuidWrap);
			setUuid(uuidWrap.o);
		}
		return (ApiRequest)this;
	}

	public static String staticSearchUuid(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUuid(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUuid(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrUuid(siteRequest_, ApiRequest.staticSearchUuid(siteRequest_, ApiRequest.staticSetUuid(siteRequest_, o)));
	}

	////////
	// id //
	////////

	/**	 The entity id
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String id;

	/**	<br> The entity id
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> c);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ApiRequest.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected ApiRequest idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			setId(idWrap.o);
		}
		return (ApiRequest)this;
	}

	public static String staticSearchId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrId(siteRequest_, ApiRequest.staticSearchId(siteRequest_, ApiRequest.staticSetId(siteRequest_, o)));
	}

	////////
	// pk //
	////////

	/**	 The entity pk
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long pk;

	/**	<br> The entity pk
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pk(Wrap<Long> c);

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	@JsonIgnore
	public void setPk(String o) {
		this.pk = ApiRequest.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ApiRequest pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			setPk(pkWrap.o);
		}
		return (ApiRequest)this;
	}

	public static Long staticSearchPk(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrPk(siteRequest_, ApiRequest.staticSearchPk(siteRequest_, ApiRequest.staticSetPk(siteRequest_, o)));
	}

	//////////////
	// original //
	//////////////

	/**	 The entity original
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Object original;

	/**	<br> The entity original
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:original">Find the entity original in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _original(Wrap<Object> c);

	public Object getOriginal() {
		return original;
	}

	public void setOriginal(Object original) {
		this.original = original;
	}
	public static Object staticSetOriginal(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected ApiRequest originalInit() {
		Wrap<Object> originalWrap = new Wrap<Object>().var("original");
		if(original == null) {
			_original(originalWrap);
			setOriginal(originalWrap.o);
		}
		return (ApiRequest)this;
	}

	/////////
	// pks //
	/////////

	/**	 The entity pks
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> pks = new ArrayList<Long>();

	/**	<br> The entity pks
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pks">Find the entity pks in Solr</a>
	 * <br>
	 * @param c is the entity already constructed. 
	 **/
	protected abstract void _pks(List<Long> c);

	public List<Long> getPks() {
		return pks;
	}

	public void setPks(List<Long> pks) {
		this.pks = pks;
	}
	@JsonIgnore
	public void setPks(String o) {
		Long l = ApiRequest.staticSetPks(siteRequest_, o);
		if(l != null)
			addPks(l);
	}
	public static Long staticSetPks(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public ApiRequest addPks(Long...objets) {
		for(Long o : objets) {
			addPks(o);
		}
		return (ApiRequest)this;
	}
	public ApiRequest addPks(Long o) {
		if(o != null)
			this.pks.add(o);
		return (ApiRequest)this;
	}
	@JsonIgnore
	public void setPks(JsonArray objets) {
		pks.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPks(o);
		}
	}
	public ApiRequest addPks(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPks(p);
		}
		return (ApiRequest)this;
	}
	protected ApiRequest pksInit() {
		_pks(pks);
		return (ApiRequest)this;
	}

	public static Long staticSearchPks(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPks(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPks(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrPks(siteRequest_, ApiRequest.staticSearchPks(siteRequest_, ApiRequest.staticSetPks(siteRequest_, o)));
	}

	/////////////
	// classes //
	/////////////

	/**	 The entity classes
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> classes = new ArrayList<String>();

	/**	<br> The entity classes
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classes">Find the entity classes in Solr</a>
	 * <br>
	 * @param c is the entity already constructed. 
	 **/
	protected abstract void _classes(List<String> c);

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
	public static String staticSetClasses(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	public ApiRequest addClasses(String...objets) {
		for(String o : objets) {
			addClasses(o);
		}
		return (ApiRequest)this;
	}
	public ApiRequest addClasses(String o) {
		if(o != null)
			this.classes.add(o);
		return (ApiRequest)this;
	}
	@JsonIgnore
	public void setClasses(JsonArray objets) {
		classes.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasses(o);
		}
	}
	protected ApiRequest classesInit() {
		_classes(classes);
		return (ApiRequest)this;
	}

	public static String staticSearchClasses(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClasses(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClasses(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrClasses(siteRequest_, ApiRequest.staticSearchClasses(siteRequest_, ApiRequest.staticSetClasses(siteRequest_, o)));
	}

	//////////
	// vars //
	//////////

	/**	 The entity vars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> vars = new ArrayList<String>();

	/**	<br> The entity vars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:vars">Find the entity vars in Solr</a>
	 * <br>
	 * @param c is the entity already constructed. 
	 **/
	protected abstract void _vars(List<String> c);

	public List<String> getVars() {
		return vars;
	}

	public void setVars(List<String> vars) {
		this.vars = vars;
	}
	public static String staticSetVars(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	public ApiRequest addVars(String...objets) {
		for(String o : objets) {
			addVars(o);
		}
		return (ApiRequest)this;
	}
	public ApiRequest addVars(String o) {
		if(o != null)
			this.vars.add(o);
		return (ApiRequest)this;
	}
	@JsonIgnore
	public void setVars(JsonArray objets) {
		vars.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addVars(o);
		}
	}
	protected ApiRequest varsInit() {
		_vars(vars);
		return (ApiRequest)this;
	}

	public static String staticSearchVars(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrVars(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVars(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrVars(siteRequest_, ApiRequest.staticSearchVars(siteRequest_, ApiRequest.staticSetVars(siteRequest_, o)));
	}

	///////////////////
	// timeRemaining //
	///////////////////

	/**	 The entity timeRemaining
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String timeRemaining;

	/**	<br> The entity timeRemaining
	 *  is defined as null before being initialized. 
	 * <br><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.vertx.api.ApiRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:timeRemaining">Find the entity timeRemaining in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _timeRemaining(Wrap<String> w);

	public String getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(String o) {
		this.timeRemaining = ApiRequest.staticSetTimeRemaining(siteRequest_, o);
	}
	public static String staticSetTimeRemaining(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected ApiRequest timeRemainingInit() {
		Wrap<String> timeRemainingWrap = new Wrap<String>().var("timeRemaining");
		if(timeRemaining == null) {
			_timeRemaining(timeRemainingWrap);
			setTimeRemaining(timeRemainingWrap.o);
		}
		return (ApiRequest)this;
	}

	public static String staticSearchTimeRemaining(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTimeRemaining(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTimeRemaining(SiteRequestEnUS siteRequest_, String o) {
		return ApiRequest.staticSearchStrTimeRemaining(siteRequest_, ApiRequest.staticSearchTimeRemaining(siteRequest_, ApiRequest.staticSetTimeRemaining(siteRequest_, o)));
	}

	//////////////
	// initDeep //
	//////////////

	public ApiRequest initDeepApiRequest(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		initDeepApiRequest();
		return (ApiRequest)this;
	}

	public void initDeepApiRequest() {
		initApiRequest();
	}

	public void initApiRequest() {
				siteRequest_Init();
				createdInit();
				rowsInit();
				numFoundInit();
				numPATCHInit();
				uuidInit();
				idInit();
				pkInit();
				originalInit();
				pksInit();
				classesInit();
				varsInit();
				timeRemainingInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepApiRequest(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestApiRequest(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestApiRequest(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainApiRequest(v);
			else if(o instanceof Cluster) {
				Cluster computateVertxCluster = (Cluster)o;
				o = computateVertxCluster.obtainForClass(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtainApiRequest(String var) {
		ApiRequest oApiRequest = (ApiRequest)this;
		switch(var) {
			case "siteRequest_":
				return oApiRequest.siteRequest_;
			case "created":
				return oApiRequest.created;
			case "rows":
				return oApiRequest.rows;
			case "numFound":
				return oApiRequest.numFound;
			case "numPATCH":
				return oApiRequest.numPATCH;
			case "uuid":
				return oApiRequest.uuid;
			case "id":
				return oApiRequest.id;
			case "pk":
				return oApiRequest.pk;
			case "original":
				return oApiRequest.original;
			case "pks":
				return oApiRequest.pks;
			case "classes":
				return oApiRequest.classes;
			case "vars":
				return oApiRequest.vars;
			case "timeRemaining":
				return oApiRequest.timeRemaining;
			default:
				return null;
		}
	}

	///////////////
	// relate //
	///////////////

	public boolean relateForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = relateApiRequest(v, val);
			else if(o instanceof Cluster) {
				Cluster computateVertxCluster = (Cluster)o;
				o = computateVertxCluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateApiRequest(String var, Object val) {
		ApiRequest oApiRequest = (ApiRequest)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetApiRequest(entityVar,  siteRequest_, o);
	}
	public static Object staticSetApiRequest(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "created":
			return ApiRequest.staticSetCreated(siteRequest_, o);
		case "rows":
			return ApiRequest.staticSetRows(siteRequest_, o);
		case "numFound":
			return ApiRequest.staticSetNumFound(siteRequest_, o);
		case "numPATCH":
			return ApiRequest.staticSetNumPATCH(siteRequest_, o);
		case "uuid":
			return ApiRequest.staticSetUuid(siteRequest_, o);
		case "id":
			return ApiRequest.staticSetId(siteRequest_, o);
		case "pk":
			return ApiRequest.staticSetPk(siteRequest_, o);
		case "pks":
			return ApiRequest.staticSetPks(siteRequest_, o);
		case "classes":
			return ApiRequest.staticSetClasses(siteRequest_, o);
		case "vars":
			return ApiRequest.staticSetVars(siteRequest_, o);
		case "timeRemaining":
			return ApiRequest.staticSetTimeRemaining(siteRequest_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSearchApiRequest(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchApiRequest(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "created":
			return ApiRequest.staticSearchCreated(siteRequest_, (ZonedDateTime)o);
		case "rows":
			return ApiRequest.staticSearchRows(siteRequest_, (Integer)o);
		case "numFound":
			return ApiRequest.staticSearchNumFound(siteRequest_, (Long)o);
		case "numPATCH":
			return ApiRequest.staticSearchNumPATCH(siteRequest_, (Long)o);
		case "uuid":
			return ApiRequest.staticSearchUuid(siteRequest_, (String)o);
		case "id":
			return ApiRequest.staticSearchId(siteRequest_, (String)o);
		case "pk":
			return ApiRequest.staticSearchPk(siteRequest_, (Long)o);
		case "pks":
			return ApiRequest.staticSearchPks(siteRequest_, (Long)o);
		case "classes":
			return ApiRequest.staticSearchClasses(siteRequest_, (String)o);
		case "vars":
			return ApiRequest.staticSearchVars(siteRequest_, (String)o);
		case "timeRemaining":
			return ApiRequest.staticSearchTimeRemaining(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSearchStrApiRequest(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrApiRequest(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "created":
			return ApiRequest.staticSearchStrCreated(siteRequest_, (Date)o);
		case "rows":
			return ApiRequest.staticSearchStrRows(siteRequest_, (Integer)o);
		case "numFound":
			return ApiRequest.staticSearchStrNumFound(siteRequest_, (Long)o);
		case "numPATCH":
			return ApiRequest.staticSearchStrNumPATCH(siteRequest_, (Long)o);
		case "uuid":
			return ApiRequest.staticSearchStrUuid(siteRequest_, (String)o);
		case "id":
			return ApiRequest.staticSearchStrId(siteRequest_, (String)o);
		case "pk":
			return ApiRequest.staticSearchStrPk(siteRequest_, (Long)o);
		case "pks":
			return ApiRequest.staticSearchStrPks(siteRequest_, (Long)o);
		case "classes":
			return ApiRequest.staticSearchStrClasses(siteRequest_, (String)o);
		case "vars":
			return ApiRequest.staticSearchStrVars(siteRequest_, (String)o);
		case "timeRemaining":
			return ApiRequest.staticSearchStrTimeRemaining(siteRequest_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSearchFqApiRequest(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqApiRequest(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "created":
			return ApiRequest.staticSearchFqCreated(siteRequest_, o);
		case "rows":
			return ApiRequest.staticSearchFqRows(siteRequest_, o);
		case "numFound":
			return ApiRequest.staticSearchFqNumFound(siteRequest_, o);
		case "numPATCH":
			return ApiRequest.staticSearchFqNumPATCH(siteRequest_, o);
		case "uuid":
			return ApiRequest.staticSearchFqUuid(siteRequest_, o);
		case "id":
			return ApiRequest.staticSearchFqId(siteRequest_, o);
		case "pk":
			return ApiRequest.staticSearchFqPk(siteRequest_, o);
		case "pks":
			return ApiRequest.staticSearchFqPks(siteRequest_, o);
		case "classes":
			return ApiRequest.staticSearchFqClasses(siteRequest_, o);
		case "vars":
			return ApiRequest.staticSearchFqVars(siteRequest_, o);
		case "timeRemaining":
			return ApiRequest.staticSearchFqTimeRemaining(siteRequest_, o);
			default:
				return null;
		}
	}

	/////////////
	// define //
	/////////////

	public boolean defineForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineApiRequest(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineApiRequest(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public static final String VAR_siteRequest_ = "siteRequest_";
	public static final String VAR_created = "created";
	public static final String VAR_rows = "rows";
	public static final String VAR_numFound = "numFound";
	public static final String VAR_numPATCH = "numPATCH";
	public static final String VAR_uuid = "uuid";
	public static final String VAR_id = "id";
	public static final String VAR_pk = "pk";
	public static final String VAR_original = "original";
	public static final String VAR_pks = "pks";
	public static final String VAR_classes = "classes";
	public static final String VAR_vars = "vars";
	public static final String VAR_timeRemaining = "timeRemaining";

	public static final String DISPLAY_NAME_siteRequest_ = "";
	public static final String DISPLAY_NAME_created = "";
	public static final String DISPLAY_NAME_rows = "";
	public static final String DISPLAY_NAME_numFound = "";
	public static final String DISPLAY_NAME_numPATCH = "";
	public static final String DISPLAY_NAME_uuid = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_original = "";
	public static final String DISPLAY_NAME_pks = "";
	public static final String DISPLAY_NAME_classes = "";
	public static final String DISPLAY_NAME_vars = "";
	public static final String DISPLAY_NAME_timeRemaining = "";

	public static String displayNameForClass(String var) {
		return ApiRequest.displayNameApiRequest(var);
	}
	public static String displayNameApiRequest(String var) {
		switch(var) {
		case VAR_siteRequest_:
			return DISPLAY_NAME_siteRequest_;
		case VAR_created:
			return DISPLAY_NAME_created;
		case VAR_rows:
			return DISPLAY_NAME_rows;
		case VAR_numFound:
			return DISPLAY_NAME_numFound;
		case VAR_numPATCH:
			return DISPLAY_NAME_numPATCH;
		case VAR_uuid:
			return DISPLAY_NAME_uuid;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_original:
			return DISPLAY_NAME_original;
		case VAR_pks:
			return DISPLAY_NAME_pks;
		case VAR_classes:
			return DISPLAY_NAME_classes;
		case VAR_vars:
			return DISPLAY_NAME_vars;
		case VAR_timeRemaining:
			return DISPLAY_NAME_timeRemaining;
		default:
			return null;
		}
	}
}
