package org.computate.scolaire.enUS.request.patch;

import java.util.Date;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import java.lang.String;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.util.List;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import java.lang.Object;

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

	///////////
	// empty //
	///////////

	/**	L'entité « empty »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean empty;
	@JsonIgnore
	public Wrap<Boolean> emptyWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("empty").o(empty);

	/**	<br/>L'entité « empty »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:empty">Trouver l'entité empty dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _empty(Wrap<Boolean> c);

	public Boolean getEmpty() {
		return empty;
	}

	public void setEmpty(Boolean empty) {
		this.empty = empty;
		this.emptyWrap.alreadyInitialized = true;
	}
	public PatchRequest setEmpty(String o) {
		this.empty = Boolean.parseBoolean(o);
		this.emptyWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	protected PatchRequest emptyInit() {
		if(!emptyWrap.alreadyInitialized) {
			_empty(emptyWrap);
			if(empty == null)
				setEmpty(emptyWrap.o);
		}
		emptyWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public Boolean solrEmpty() {
		return empty;
	}

	public String strEmpty() {
		return empty == null ? "" : empty.toString();
	}

	public String jsonEmpty() {
		return empty == null ? "" : empty.toString();
	}

	public String nomAffichageEmpty() {
		return null;
	}

	public String htmTooltipEmpty() {
		return null;
	}

	public String htmEmpty() {
		return empty == null ? "" : StringEscapeUtils.escapeHtml4(strEmpty());
	}

	////////
	// pk //
	////////

	/**	L'entité « pk »
	 *	 is defined as null before being initialized. 
	 */
	protected Long pk;
	@JsonIgnore
	public Wrap<Long> pkWrap = new Wrap<Long>().p(this).c(Long.class).var("pk").o(pk);

	/**	<br/>L'entité « pk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pk">Trouver l'entité pk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pk(Wrap<Long> c);

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
		this.pkWrap.alreadyInitialized = true;
	}
	public PatchRequest setPk(String o) {
		if(NumberUtils.isParsable(o))
			this.pk = Long.parseLong(o);
		this.pkWrap.alreadyInitialized = true;
		return (PatchRequest)this;
	}
	protected PatchRequest pkInit() {
		if(!pkWrap.alreadyInitialized) {
			_pk(pkWrap);
			if(pk == null)
				setPk(pkWrap.o);
		}
		pkWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public Long solrPk() {
		return pk;
	}

	public String strPk() {
		return pk == null ? "" : pk.toString();
	}

	public String jsonPk() {
		return pk == null ? "" : pk.toString();
	}

	public String nomAffichagePk() {
		return null;
	}

	public String htmTooltipPk() {
		return null;
	}

	public String htmPk() {
		return pk == null ? "" : StringEscapeUtils.escapeHtml4(strPk());
	}

	//////////////
	// original //
	//////////////

	/**	L'entité « original »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected Object original;
	@JsonIgnore
	public Wrap<Object> originalWrap = new Wrap<Object>().p(this).c(Object.class).var("original").o(original);

	/**	<br/>L'entité « original »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:original">Trouver l'entité original dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _original(Wrap<Object> c);

	public Object getOriginal() {
		return original;
	}

	public void setOriginal(Object original) {
		this.original = original;
		this.originalWrap.alreadyInitialized = true;
	}
	protected PatchRequest originalInit() {
		if(!originalWrap.alreadyInitialized) {
			_original(originalWrap);
			if(original == null)
				setOriginal(originalWrap.o);
		}
		originalWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	/////////
	// pks //
	/////////

	/**	L'entité « pks »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> pks = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> pksWrap = new Wrap<List<Long>>().p(this).c(List.class).var("pks").o(pks);

	/**	<br/>L'entité « pks »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pks">Trouver l'entité pks dans Solr</a>
	 * <br/>
	 * @param pks est l'entité déjà construit. 
	 **/
	protected abstract void _pks(List<Long> c);

	public List<Long> getPks() {
		return pks;
	}

	public void setPks(List<Long> pks) {
		this.pks = pks;
		this.pksWrap.alreadyInitialized = true;
	}
	public PatchRequest addPks(Long...objets) {
		for(Long o : objets) {
			addPks(o);
		}
		return (PatchRequest)this;
	}
	public PatchRequest addPks(Long o) {
		if(o != null && !pks.contains(o))
			this.pks.add(o);
		return (PatchRequest)this;
	}
	public PatchRequest setPks(JsonArray objets) {
		pks.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPks(o);
		}
		return (PatchRequest)this;
	}
	public PatchRequest addPks(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPks(p);
		}
		return (PatchRequest)this;
	}
	protected PatchRequest pksInit() {
		if(!pksWrap.alreadyInitialized) {
			_pks(pks);
		}
		pksWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public List<Long> solrPks() {
		return pks;
	}

	public String strPks() {
		return pks == null ? "" : pks.toString();
	}

	public String jsonPks() {
		return pks == null ? "" : pks.toString();
	}

	public String nomAffichagePks() {
		return null;
	}

	public String htmTooltipPks() {
		return null;
	}

	public String htmPks() {
		return pks == null ? "" : StringEscapeUtils.escapeHtml4(strPks());
	}

	/////////////
	// classes //
	/////////////

	/**	L'entité « classes »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	protected List<String> classes = new java.util.ArrayList<java.lang.String>();
	@JsonIgnore
	public Wrap<List<String>> classesWrap = new Wrap<List<String>>().p(this).c(List.class).var("classes").o(classes);

	/**	<br/>L'entité « classes »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classes">Trouver l'entité classes dans Solr</a>
	 * <br/>
	 * @param classes est l'entité déjà construit. 
	 **/
	protected abstract void _classes(List<String> c);

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
		this.classesWrap.alreadyInitialized = true;
	}
	public PatchRequest addClasses(String...objets) {
		for(String o : objets) {
			addClasses(o);
		}
		return (PatchRequest)this;
	}
	public PatchRequest addClasses(String o) {
		if(o != null && !classes.contains(o))
			this.classes.add(o);
		return (PatchRequest)this;
	}
	public PatchRequest setClasses(JsonArray objets) {
		classes.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasses(o);
		}
		return (PatchRequest)this;
	}
	protected PatchRequest classesInit() {
		if(!classesWrap.alreadyInitialized) {
			_classes(classes);
		}
		classesWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public List<String> solrClasses() {
		return classes;
	}

	public String strClasses() {
		return classes == null ? "" : classes.toString();
	}

	public String jsonClasses() {
		return classes == null ? "" : classes.toString();
	}

	public String nomAffichageClasses() {
		return null;
	}

	public String htmTooltipClasses() {
		return null;
	}

	public String htmClasses() {
		return classes == null ? "" : StringEscapeUtils.escapeHtml4(strClasses());
	}

	//////////
	// vars //
	//////////

	/**	L'entité « vars »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	protected List<String> vars = new java.util.ArrayList<java.lang.String>();
	@JsonIgnore
	public Wrap<List<String>> varsWrap = new Wrap<List<String>>().p(this).c(List.class).var("vars").o(vars);

	/**	<br/>L'entité « vars »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.request.patch.PatchRequest&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:vars">Trouver l'entité vars dans Solr</a>
	 * <br/>
	 * @param vars est l'entité déjà construit. 
	 **/
	protected abstract void _vars(List<String> c);

	public List<String> getVars() {
		return vars;
	}

	public void setVars(List<String> vars) {
		this.vars = vars;
		this.varsWrap.alreadyInitialized = true;
	}
	public PatchRequest addVars(String...objets) {
		for(String o : objets) {
			addVars(o);
		}
		return (PatchRequest)this;
	}
	public PatchRequest addVars(String o) {
		if(o != null && !vars.contains(o))
			this.vars.add(o);
		return (PatchRequest)this;
	}
	public PatchRequest setVars(JsonArray objets) {
		vars.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addVars(o);
		}
		return (PatchRequest)this;
	}
	protected PatchRequest varsInit() {
		if(!varsWrap.alreadyInitialized) {
			_vars(vars);
		}
		varsWrap.alreadyInitialized(true);
		return (PatchRequest)this;
	}

	public List<String> solrVars() {
		return vars;
	}

	public String strVars() {
		return vars == null ? "" : vars.toString();
	}

	public String jsonVars() {
		return vars == null ? "" : vars.toString();
	}

	public String nomAffichageVars() {
		return null;
	}

	public String htmTooltipVars() {
		return null;
	}

	public String htmVars() {
		return vars == null ? "" : StringEscapeUtils.escapeHtml4(strVars());
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
		emptyInit();
		pkInit();
		originalInit();
		pksInit();
		classesInit();
		varsInit();
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
			case "empty":
				return oPatchRequest.empty;
			case "pk":
				return oPatchRequest.pk;
			case "original":
				return oPatchRequest.original;
			case "pks":
				return oPatchRequest.pks;
			case "classes":
				return oPatchRequest.classes;
			case "vars":
				return oPatchRequest.vars;
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
