package org.computate.scolaire.enUS.request.patch;

import java.math.MathContext;
import java.util.Date;
import java.time.ZonedDateTime;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.time.LocalDateTime;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.writer.AllWriter;
import java.time.Instant;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.lang.Object;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PatchRequestGen<DEV> extends Object {

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	L'entité « siteRequest_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SiteRequestEnUS siteRequest_;
	@JsonIgnore
	public Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("siteRequest_").o(siteRequest_);

	/**	<br/>L'entité « siteRequest_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequestEnUS> c);

	public SiteRequestEnUS getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequestEnUS siteRequest_) {
		this.siteRequest_ = siteRequest_;
		this.siteRequest_Wrap.alreadyInitialized = true;
	}
	protected PatchRequest siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	/////////////
	// created //
	/////////////

	/**	L'entité « created »
	 *	 is defined as null before being initialized. 
	 */
	protected ZonedDateTime created;
	@JsonIgnore
	public Wrap<ZonedDateTime> createdWrap = new Wrap<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("created").o(created);

	/**	<br/>L'entité « created »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:created">Trouver l'entité created dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _created(Wrap<ZonedDateTime> c);

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
		this.createdWrap.alreadyInitialized = true;
	}
	public PatchRequest setCreated(Instant o) {
		this.created = ZonedDateTime.from(o);
		this.createdWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public PatchRequest setCreated(String o) {
		this.created = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this.createdWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	public PatchRequest setCreated(Date o) {
		this.created = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone()));
		this.createdWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	protected PatchRequest createdInit() {
		if(!createdWrap.alreadyInitialized) {
			_created(createdWrap);
			if(created == null)
				setCreated(createdWrap.o);
		}
		createdWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public Date solrCreated() {
		return created == null ? null : Date.from(created.toInstant());
	}

	public String strCreated() {
		return created == null ? "" : created.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy h:mm:ss a zz", Locale.US));
	}

	public String jsonCreated() {
		return created == null ? "" : created.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy h:mm:ss.SSS a zz VV", Locale.US));
	}

	public String nomAffichageCreated() {
		return "created";
	}

	public String htmTooltipCreated() {
		return null;
	}

	public String htmCreated() {
		return created == null ? "" : StringEscapeUtils.escapeHtml4(strCreated());
	}

	//////////
	// rows //
	//////////

	/**	L'entité « rows »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer rows;
	@JsonIgnore
	public Wrap<Integer> rowsWrap = new Wrap<Integer>().p(this).c(Integer.class).var("rows").o(rows);

	/**	<br/>L'entité « rows »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:rows">Trouver l'entité rows dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _rows(Wrap<Integer> c);

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
		this.rowsWrap.alreadyInitialized = true;
	}
	public PatchRequest setRows(String o) {
		if(NumberUtils.isParsable(o))
			this.rows = Integer.parseInt(o);
		this.rowsWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	protected PatchRequest rowsInit() {
		if(!rowsWrap.alreadyInitialized) {
			_rows(rowsWrap);
			if(rows == null)
				setRows(rowsWrap.o);
		}
		rowsWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public Integer solrRows() {
		return rows;
	}

	public String strRows() {
		return rows == null ? "" : rows.toString();
	}

	public String jsonRows() {
		return rows == null ? "" : rows.toString();
	}

	public String nomAffichageRows() {
		return null;
	}

	public String htmTooltipRows() {
		return null;
	}

	public String htmRows() {
		return rows == null ? "" : StringEscapeUtils.escapeHtml4(strRows());
	}

	//////////////
	// numFound //
	//////////////

	/**	L'entité « numFound »
	 *	 is defined as null before being initialized. 
	 */
	protected Long numFound;
	@JsonIgnore
	public Wrap<Long> numFoundWrap = new Wrap<Long>().p(this).c(Long.class).var("numFound").o(numFound);

	/**	<br/>L'entité « numFound »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:numFound">Trouver l'entité numFound dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _numFound(Wrap<Long> c);

	public Long getNumFound() {
		return numFound;
	}

	public void setNumFound(Long numFound) {
		this.numFound = numFound;
		this.numFoundWrap.alreadyInitialized = true;
	}
	public PatchRequest setNumFound(String o) {
		if(NumberUtils.isParsable(o))
			this.numFound = Long.parseLong(o);
		this.numFoundWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	protected PatchRequest numFoundInit() {
		if(!numFoundWrap.alreadyInitialized) {
			_numFound(numFoundWrap);
			if(numFound == null)
				setNumFound(numFoundWrap.o);
		}
		numFoundWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public Long solrNumFound() {
		return numFound;
	}

	public String strNumFound() {
		return numFound == null ? "" : numFound.toString();
	}

	public String jsonNumFound() {
		return numFound == null ? "" : numFound.toString();
	}

	public String nomAffichageNumFound() {
		return null;
	}

	public String htmTooltipNumFound() {
		return null;
	}

	public String htmNumFound() {
		return numFound == null ? "" : StringEscapeUtils.escapeHtml4(strNumFound());
	}

	//////////////
	// numPATCH //
	//////////////

	/**	L'entité « numPATCH »
	 *	 is defined as null before being initialized. 
	 */
	protected Long numPATCH;
	@JsonIgnore
	public Wrap<Long> numPATCHWrap = new Wrap<Long>().p(this).c(Long.class).var("numPATCH").o(numPATCH);

	/**	<br/>L'entité « numPATCH »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:numPATCH">Trouver l'entité numPATCH dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _numPATCH(Wrap<Long> c);

	public Long getNumPATCH() {
		return numPATCH;
	}

	public void setNumPATCH(Long numPATCH) {
		this.numPATCH = numPATCH;
		this.numPATCHWrap.alreadyInitialized = true;
	}
	public PatchRequest setNumPATCH(String o) {
		if(NumberUtils.isParsable(o))
			this.numPATCH = Long.parseLong(o);
		this.numPATCHWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	protected PatchRequest numPATCHInit() {
		if(!numPATCHWrap.alreadyInitialized) {
			_numPATCH(numPATCHWrap);
			if(numPATCH == null)
				setNumPATCH(numPATCHWrap.o);
		}
		numPATCHWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public Long solrNumPATCH() {
		return numPATCH;
	}

	public String strNumPATCH() {
		return numPATCH == null ? "" : numPATCH.toString();
	}

	public String jsonNumPATCH() {
		return numPATCH == null ? "" : numPATCH.toString();
	}

	public String nomAffichageNumPATCH() {
		return null;
	}

	public String htmTooltipNumPATCH() {
		return null;
	}

	public String htmNumPATCH() {
		return numPATCH == null ? "" : StringEscapeUtils.escapeHtml4(strNumPATCH());
	}

	//////////
	// uuid //
	//////////

	/**	L'entité « uuid »
	 *	 is defined as null before being initialized. 
	 */
	protected String uuid;
	@JsonIgnore
	public Wrap<String> uuidWrap = new Wrap<String>().p(this).c(String.class).var("uuid").o(uuid);

	/**	<br/>L'entité « uuid »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:uuid">Trouver l'entité uuid dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _uuid(Wrap<String> c);

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
		this.uuidWrap.alreadyInitialized = true;
	}
	protected PatchRequest uuidInit() {
		if(!uuidWrap.alreadyInitialized) {
			_uuid(uuidWrap);
			if(uuid == null)
				setUuid(uuidWrap.o);
		}
		uuidWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public String solrUuid() {
		return uuid;
	}

	public String strUuid() {
		return uuid == null ? "" : uuid;
	}

	public String jsonUuid() {
		return uuid == null ? "" : uuid;
	}

	public String nomAffichageUuid() {
		return "UUID";
	}

	public String htmTooltipUuid() {
		return null;
	}

	public String htmUuid() {
		return uuid == null ? "" : StringEscapeUtils.escapeHtml4(strUuid());
	}

	////////
	// id //
	////////

	/**	L'entité « id »
	 *	 is defined as null before being initialized. 
	 */
	protected String id;
	@JsonIgnore
	public Wrap<String> idWrap = new Wrap<String>().p(this).c(String.class).var("id").o(id);

	/**	<br/>L'entité « id »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:id">Trouver l'entité id dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _id(Wrap<String> c);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.idWrap.alreadyInitialized = true;
	}
	protected PatchRequest idInit() {
		if(!idWrap.alreadyInitialized) {
			_id(idWrap);
			if(id == null)
				setId(idWrap.o);
		}
		idWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public String solrId() {
		return id;
	}

	public String strId() {
		return id == null ? "" : id;
	}

	public String jsonId() {
		return id == null ? "" : id;
	}

	public String nomAffichageId() {
		return null;
	}

	public String htmTooltipId() {
		return null;
	}

	public String htmId() {
		return id == null ? "" : StringEscapeUtils.escapeHtml4(strId());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPatchRequest = false;

	public PatchRequest initDeepPatchRequest(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPatchRequest) {
			alreadyInitializedPatchRequest = true;
			initDeepPatchRequest();
		}
		return (PatchRequest)this;
	}

	public void initDeepPatchRequest() {
		initPatchRequest();
	}

	public void initPatchRequest() {
		siteRequest_Init();
		createdInit();
		rowsInit();
		numFoundInit();
		numPATCHInit();
		uuidInit();
		idInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPatchRequest(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPatchRequest(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPatchRequest(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPatchRequest(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainPatchRequest(String var) {
		PatchRequest oPatchRequest = (PatchRequest)this;
		switch(var) {
			case "siteRequest_":
				return oPatchRequest.siteRequest_;
			case "created":
				return oPatchRequest.created;
			case "rows":
				return oPatchRequest.rows;
			case "numFound":
				return oPatchRequest.numFound;
			case "numPATCH":
				return oPatchRequest.numPATCH;
			case "uuid":
				return oPatchRequest.uuid;
			case "id":
				return oPatchRequest.id;
			default:
				return null;
		}
	}

	///////////////
	// attribute //
	///////////////

	public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributePatchRequest(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePatchRequest(String var, Object val) {
		PatchRequest oPatchRequest = (PatchRequest)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// define //
	/////////////

	public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definePatchRequest(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePatchRequest(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash();
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PatchRequest))
			return false;
		PatchRequest that = (PatchRequest)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PatchRequest { ");
		sb.append(" }");
		return sb.toString();
	}
}
