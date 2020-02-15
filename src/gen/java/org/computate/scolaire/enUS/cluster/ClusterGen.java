package org.computate.scolaire.enUS.cluster;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import java.time.Instant;
import org.computate.scolaire.enUS.page.part.PagePart;
import java.time.ZoneId;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import java.lang.Object;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ClusterGen<DEV> extends Object {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cluster.class);

	public static final String Cluster_UnNom = "a cluster";
	public static final String Cluster_Ce = "this ";
	public static final String Cluster_CeNom = "this cluster";
	public static final String Cluster_Un = "a ";
	public static final String Cluster_LeNom = "the cluster";
	public static final String Cluster_NomSingulier = "cluster";
	public static final String Cluster_NomPluriel = "clusters";
	public static final String Cluster_NomActuel = "current cluster";
	public static final String Cluster_TousNom = "all the clusters";
	public static final String Cluster_RechercherTousNomPar = "search clusters by ";
	public static final String Cluster_LesNoms = "the clusters";
	public static final String Cluster_AucunNomTrouve = "no cluster found";
	public static final String Cluster_NomVar = "cluster";
	public static final String Cluster_DeNom = "of cluster";
	public static final String Cluster_UnNomAdjectif = "a cluster";
	public static final String Cluster_NomAdjectifSingulier = "cluster";
	public static final String Cluster_NomAdjectifPluriel = "clusters";
	public static final String Cluster_Couleur = "gray";
	public static final String Cluster_IconeGroupe = "regular";
	public static final String Cluster_IconeNom = "fort-awesome";

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
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
	protected Cluster siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	///////////////
	// pageParts //
	///////////////

	/**	L'entité « pageParts »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 */
	protected List<PagePart> pageParts = new java.util.ArrayList<org.computate.scolaire.enUS.page.part.PagePart>();
	@JsonIgnore
	public Wrap<List<PagePart>> pagePartsWrap = new Wrap<List<PagePart>>().p(this).c(List.class).var("pageParts").o(pageParts);

	/**	<br/>L'entité « pageParts »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageParts">Trouver l'entité pageParts dans Solr</a>
	 * <br/>
	 * @param pageParts est l'entité déjà construit. 
	 **/
	protected abstract void _pageParts(List<PagePart> l);

	public List<PagePart> getPageParts() {
		return pageParts;
	}

	public void setPageParts(List<PagePart> pageParts) {
		this.pageParts = pageParts;
		this.pagePartsWrap.alreadyInitialized = true;
	}
	public Cluster addPageParts(PagePart...objets) {
		for(PagePart o : objets) {
			addPageParts(o);
		}
		return (Cluster)this;
	}
	public Cluster addPageParts(PagePart o) {
		if(o != null && !pageParts.contains(o))
			this.pageParts.add(o);
		return (Cluster)this;
	}
	public abstract void beforePagePart(PagePart o, String entiteVar);
	protected Cluster pagePartsInit() {
		if(!pagePartsWrap.alreadyInitialized) {
			_pageParts(pageParts);
		}
		pagePartsWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	////////
	// pk //
	////////

	/**	L'entité « pk »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long pk;
	@JsonIgnore
	public Wrap<Long> pkWrap = new Wrap<Long>().p(this).c(Long.class).var("pk").o(pk);

	/**	<br/>L'entité « pk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pk">Trouver l'entité pk dans Solr</a>
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
	public Cluster setPk(String o) {
		if(NumberUtils.isParsable(o))
			this.pk = Long.parseLong(o);
		this.pkWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	protected Cluster pkInit() {
		if(!pkWrap.alreadyInitialized) {
			_pk(pkWrap);
			if(pk == null)
				setPk(pkWrap.o);
		}
		pkWrap.alreadyInitialized(true);
		return (Cluster)this;
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
		return "primary key";
	}

	public String htmTooltipPk() {
		return null;
	}

	public String htmPk() {
		return pk == null ? "" : StringEscapeUtils.escapeHtml4(strPk());
	}

	public void inputPk(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
	}

	public void htmPk(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("class", "").f().sx("primary key").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row  ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								{ s.e("div").a("class", "w3-rest ").f();
									s.e("a").a("href", pageUrlPk).f().sx(strPk()).g("a");
								} s.g("div");
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			}
		} s.g("div");
	}

	///////////////
	// inheritPk //
	///////////////

	/**	L'entité « inheritPk »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long inheritPk;
	@JsonIgnore
	public Wrap<Long> inheritPkWrap = new Wrap<Long>().p(this).c(Long.class).var("inheritPk").o(inheritPk);

	/**	<br/>L'entité « inheritPk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:inheritPk">Trouver l'entité inheritPk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inheritPk(Wrap<Long> c);

	public Long getInheritPk() {
		return inheritPk;
	}

	public void setInheritPk(Long inheritPk) {
		this.inheritPk = inheritPk;
		this.inheritPkWrap.alreadyInitialized = true;
	}
	public Cluster setInheritPk(String o) {
		if(NumberUtils.isParsable(o))
			this.inheritPk = Long.parseLong(o);
		this.inheritPkWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	protected Cluster inheritPkInit() {
		if(!inheritPkWrap.alreadyInitialized) {
			_inheritPk(inheritPkWrap);
			if(inheritPk == null)
				setInheritPk(inheritPkWrap.o);
		}
		inheritPkWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public Long solrInheritPk() {
		return inheritPk;
	}

	public String strInheritPk() {
		return inheritPk == null ? "" : inheritPk.toString();
	}

	public String jsonInheritPk() {
		return inheritPk == null ? "" : inheritPk.toString();
	}

	public String nomAffichageInheritPk() {
		return null;
	}

	public String htmTooltipInheritPk() {
		return null;
	}

	public String htmInheritPk() {
		return inheritPk == null ? "" : StringEscapeUtils.escapeHtml4(strInheritPk());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:id">Trouver l'entité id dans Solr</a>
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
	protected Cluster idInit() {
		if(!idWrap.alreadyInitialized) {
			_id(idWrap);
			if(id == null)
				setId(idWrap.o);
		}
		idWrap.alreadyInitialized(true);
		return (Cluster)this;
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

	/////////////
	// created //
	/////////////

	/**	L'entité « created »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected ZonedDateTime created;
	@JsonIgnore
	public Wrap<ZonedDateTime> createdWrap = new Wrap<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("created").o(created);

	/**	<br/>L'entité « created »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:created">Trouver l'entité created dans Solr</a>
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
	public Cluster setCreated(Instant o) {
		this.created = ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
		this.createdWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public Cluster setCreated(String o) {
		this.created = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone()))).truncatedTo(ChronoUnit.MILLIS);
		this.createdWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	public Cluster setCreated(Date o) {
		this.created = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		this.createdWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	protected Cluster createdInit() {
		if(!createdWrap.alreadyInitialized) {
			_created(createdWrap);
			if(created == null)
				setCreated(createdWrap.o);
		}
		createdWrap.alreadyInitialized(true);
		return (Cluster)this;
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

	public void inputCreated(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
	}

	public void htmCreated(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("class", "").f().sx("created").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row  ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								{ s.e("div").a("class", "w3-rest ").f();
									s.e("span").f().sx(strCreated()).g("span");
								} s.g("div");
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			}
		} s.g("div");
	}

	//////////////
	// modified //
	//////////////

	/**	L'entité « modified »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected ZonedDateTime modified;
	@JsonIgnore
	public Wrap<ZonedDateTime> modifiedWrap = new Wrap<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("modified").o(modified);

	/**	<br/>L'entité « modified »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:modified">Trouver l'entité modified dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modified(Wrap<ZonedDateTime> c);

	public ZonedDateTime getModified() {
		return modified;
	}

	public void setModified(ZonedDateTime modified) {
		this.modified = modified;
		this.modifiedWrap.alreadyInitialized = true;
	}
	public Cluster setModified(Instant o) {
		this.modified = ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
		this.modifiedWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public Cluster setModified(String o) {
		this.modified = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone()))).truncatedTo(ChronoUnit.MILLIS);
		this.modifiedWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	public Cluster setModified(Date o) {
		this.modified = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		this.modifiedWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	protected Cluster modifiedInit() {
		if(!modifiedWrap.alreadyInitialized) {
			_modified(modifiedWrap);
			if(modified == null)
				setModified(modifiedWrap.o);
		}
		modifiedWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public Date solrModified() {
		return modified == null ? null : Date.from(modified.toInstant());
	}

	public String strModified() {
		return modified == null ? "" : modified.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy h:mm:ss a zz", Locale.US));
	}

	public String jsonModified() {
		return modified == null ? "" : modified.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy h:mm:ss.SSS a zz VV", Locale.US));
	}

	public String nomAffichageModified() {
		return "modified";
	}

	public String htmTooltipModified() {
		return null;
	}

	public String htmModified() {
		return modified == null ? "" : StringEscapeUtils.escapeHtml4(strModified());
	}

	public void inputModified(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
	}

	public void htmModified(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("class", "").f().sx("modified").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row  ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								{ s.e("div").a("class", "w3-rest ").f();
									s.e("span").f().sx(strModified()).g("span");
								} s.g("div");
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			}
		} s.g("div");
	}

	//////////////
	// archived //
	//////////////

	/**	L'entité « archived »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean archived;
	@JsonIgnore
	public Wrap<Boolean> archivedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("archived").o(archived);

	/**	<br/>L'entité « archived »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:archived">Trouver l'entité archived dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _archived(Wrap<Boolean> c);

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
		this.archivedWrap.alreadyInitialized = true;
	}
	public Cluster setArchived(String o) {
		this.archived = Boolean.parseBoolean(o);
		this.archivedWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	protected Cluster archivedInit() {
		if(!archivedWrap.alreadyInitialized) {
			_archived(archivedWrap);
			if(archived == null)
				setArchived(archivedWrap.o);
		}
		archivedWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public Boolean solrArchived() {
		return archived;
	}

	public String strArchived() {
		return archived == null ? "" : archived.toString();
	}

	public String jsonArchived() {
		return archived == null ? "" : archived.toString();
	}

	public String nomAffichageArchived() {
		return "archived";
	}

	public String htmTooltipArchived() {
		return null;
	}

	public String htmArchived() {
		return archived == null ? "" : StringEscapeUtils.escapeHtml4(strArchived());
	}

	public void inputArchived(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		if("Page".equals(classApiMethodMethod)) {
			s.e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_archived")
				.a("value", "true");
		} else {
			s.e("select")
				.a("id", classApiMethodMethod, "_archived");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			s.a("class", "setArchived inputCluster", pk, "Archived w3-input w3-border ");
			s.a("name", "setArchived");
		} else {
			s.a("class", "valueArchived inputCluster", pk, "Archived w3-input w3-border ");
			s.a("name", "archived");
		}
		if("Page".equals(classApiMethodMethod)) {
			s.a("onchange", "patchClusterVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setArchived', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_archived')); }, function() { addError($('#", classApiMethodMethod, "_archived')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getArchived() != null && getArchived())
				s.a("checked", "checked");
			s.fg();
		} else {
			s.f();
			s.e("option").a("value", "").a("selected", "selected").f().g("option");
			s.e("option").a("value", "true").f().sx("true").g("option");
			s.e("option").a("value", "false").f().sx("false").g("option");
			s.g("select");
		}

	}

	public void htmArchived(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggest", classApiMethodMethod, "ClusterArchived").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("for", classApiMethodMethod, "_archived").a("class", "").f().sx("archived").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputArchived(classApiMethodMethod);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	/////////////
	// deleted //
	/////////////

	/**	L'entité « deleted »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean deleted;
	@JsonIgnore
	public Wrap<Boolean> deletedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("deleted").o(deleted);

	/**	<br/>L'entité « deleted »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:deleted">Trouver l'entité deleted dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _deleted(Wrap<Boolean> c);

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
		this.deletedWrap.alreadyInitialized = true;
	}
	public Cluster setDeleted(String o) {
		this.deleted = Boolean.parseBoolean(o);
		this.deletedWrap.alreadyInitialized = true;
		return (Cluster)this;
	}
	protected Cluster deletedInit() {
		if(!deletedWrap.alreadyInitialized) {
			_deleted(deletedWrap);
			if(deleted == null)
				setDeleted(deletedWrap.o);
		}
		deletedWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public Boolean solrDeleted() {
		return deleted;
	}

	public String strDeleted() {
		return deleted == null ? "" : deleted.toString();
	}

	public String jsonDeleted() {
		return deleted == null ? "" : deleted.toString();
	}

	public String nomAffichageDeleted() {
		return "deleted";
	}

	public String htmTooltipDeleted() {
		return null;
	}

	public String htmDeleted() {
		return deleted == null ? "" : StringEscapeUtils.escapeHtml4(strDeleted());
	}

	public void inputDeleted(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		if("Page".equals(classApiMethodMethod)) {
			s.e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_deleted")
				.a("value", "true");
		} else {
			s.e("select")
				.a("id", classApiMethodMethod, "_deleted");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			s.a("class", "setDeleted inputCluster", pk, "Deleted w3-input w3-border ");
			s.a("name", "setDeleted");
		} else {
			s.a("class", "valueDeleted inputCluster", pk, "Deleted w3-input w3-border ");
			s.a("name", "deleted");
		}
		if("Page".equals(classApiMethodMethod)) {
			s.a("onchange", "patchClusterVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setDeleted', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_deleted')); }, function() { addError($('#", classApiMethodMethod, "_deleted')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getDeleted() != null && getDeleted())
				s.a("checked", "checked");
			s.fg();
		} else {
			s.f();
			s.e("option").a("value", "").a("selected", "selected").f().g("option");
			s.e("option").a("value", "true").f().sx("true").g("option");
			s.e("option").a("value", "false").f().sx("false").g("option");
			s.g("select");
		}

	}

	public void htmDeleted(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggest", classApiMethodMethod, "ClusterDeleted").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("for", classApiMethodMethod, "_deleted").a("class", "").f().sx("deleted").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputDeleted(classApiMethodMethod);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	////////////////////////
	// classCanonicalName //
	////////////////////////

	/**	L'entité « classCanonicalName »
	 *	 is defined as null before being initialized. 
	 */
	protected String classCanonicalName;
	@JsonIgnore
	public Wrap<String> classCanonicalNameWrap = new Wrap<String>().p(this).c(String.class).var("classCanonicalName").o(classCanonicalName);

	/**	<br/>L'entité « classCanonicalName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classCanonicalName">Trouver l'entité classCanonicalName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classCanonicalName(Wrap<String> c);

	public String getClassCanonicalName() {
		return classCanonicalName;
	}

	public void setClassCanonicalName(String classCanonicalName) {
		this.classCanonicalName = classCanonicalName;
		this.classCanonicalNameWrap.alreadyInitialized = true;
	}
	protected Cluster classCanonicalNameInit() {
		if(!classCanonicalNameWrap.alreadyInitialized) {
			_classCanonicalName(classCanonicalNameWrap);
			if(classCanonicalName == null)
				setClassCanonicalName(classCanonicalNameWrap.o);
		}
		classCanonicalNameWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrClassCanonicalName() {
		return classCanonicalName;
	}

	public String strClassCanonicalName() {
		return classCanonicalName == null ? "" : classCanonicalName;
	}

	public String jsonClassCanonicalName() {
		return classCanonicalName == null ? "" : classCanonicalName;
	}

	public String nomAffichageClassCanonicalName() {
		return null;
	}

	public String htmTooltipClassCanonicalName() {
		return null;
	}

	public String htmClassCanonicalName() {
		return classCanonicalName == null ? "" : StringEscapeUtils.escapeHtml4(strClassCanonicalName());
	}

	/////////////////////
	// classSimpleName //
	/////////////////////

	/**	L'entité « classSimpleName »
	 *	 is defined as null before being initialized. 
	 */
	protected String classSimpleName;
	@JsonIgnore
	public Wrap<String> classSimpleNameWrap = new Wrap<String>().p(this).c(String.class).var("classSimpleName").o(classSimpleName);

	/**	<br/>L'entité « classSimpleName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classSimpleName">Trouver l'entité classSimpleName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classSimpleName(Wrap<String> c);

	public String getClassSimpleName() {
		return classSimpleName;
	}

	public void setClassSimpleName(String classSimpleName) {
		this.classSimpleName = classSimpleName;
		this.classSimpleNameWrap.alreadyInitialized = true;
	}
	protected Cluster classSimpleNameInit() {
		if(!classSimpleNameWrap.alreadyInitialized) {
			_classSimpleName(classSimpleNameWrap);
			if(classSimpleName == null)
				setClassSimpleName(classSimpleNameWrap.o);
		}
		classSimpleNameWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrClassSimpleName() {
		return classSimpleName;
	}

	public String strClassSimpleName() {
		return classSimpleName == null ? "" : classSimpleName;
	}

	public String jsonClassSimpleName() {
		return classSimpleName == null ? "" : classSimpleName;
	}

	public String nomAffichageClassSimpleName() {
		return null;
	}

	public String htmTooltipClassSimpleName() {
		return null;
	}

	public String htmClassSimpleName() {
		return classSimpleName == null ? "" : StringEscapeUtils.escapeHtml4(strClassSimpleName());
	}

	/////////////////////////
	// classCanonicalNames //
	/////////////////////////

	/**	L'entité « classCanonicalNames »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	protected List<String> classCanonicalNames = new java.util.ArrayList<java.lang.String>();
	@JsonIgnore
	public Wrap<List<String>> classCanonicalNamesWrap = new Wrap<List<String>>().p(this).c(List.class).var("classCanonicalNames").o(classCanonicalNames);

	/**	<br/>L'entité « classCanonicalNames »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classCanonicalNames">Trouver l'entité classCanonicalNames dans Solr</a>
	 * <br/>
	 * @param classCanonicalNames est l'entité déjà construit. 
	 **/
	protected abstract void _classCanonicalNames(List<String> l);

	public List<String> getClassCanonicalNames() {
		return classCanonicalNames;
	}

	public void setClassCanonicalNames(List<String> classCanonicalNames) {
		this.classCanonicalNames = classCanonicalNames;
		this.classCanonicalNamesWrap.alreadyInitialized = true;
	}
	public Cluster addClassCanonicalNames(String...objets) {
		for(String o : objets) {
			addClassCanonicalNames(o);
		}
		return (Cluster)this;
	}
	public Cluster addClassCanonicalNames(String o) {
		if(o != null && !classCanonicalNames.contains(o))
			this.classCanonicalNames.add(o);
		return (Cluster)this;
	}
	public Cluster setClassCanonicalNames(JsonArray objets) {
		classCanonicalNames.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClassCanonicalNames(o);
		}
		return (Cluster)this;
	}
	protected Cluster classCanonicalNamesInit() {
		if(!classCanonicalNamesWrap.alreadyInitialized) {
			_classCanonicalNames(classCanonicalNames);
		}
		classCanonicalNamesWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public List<String> solrClassCanonicalNames() {
		return classCanonicalNames;
	}

	public String strClassCanonicalNames() {
		return classCanonicalNames == null ? "" : classCanonicalNames.toString();
	}

	public String jsonClassCanonicalNames() {
		return classCanonicalNames == null ? "" : classCanonicalNames.toString();
	}

	public String nomAffichageClassCanonicalNames() {
		return null;
	}

	public String htmTooltipClassCanonicalNames() {
		return null;
	}

	public String htmClassCanonicalNames() {
		return classCanonicalNames == null ? "" : StringEscapeUtils.escapeHtml4(strClassCanonicalNames());
	}

	///////////////
	// sessionId //
	///////////////

	/**	L'entité « sessionId »
	 *	 is defined as null before being initialized. 
	 */
	protected String sessionId;
	@JsonIgnore
	public Wrap<String> sessionIdWrap = new Wrap<String>().p(this).c(String.class).var("sessionId").o(sessionId);

	/**	<br/>L'entité « sessionId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionId">Trouver l'entité sessionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionId(Wrap<String> c);

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
		this.sessionIdWrap.alreadyInitialized = true;
	}
	protected Cluster sessionIdInit() {
		if(!sessionIdWrap.alreadyInitialized) {
			_sessionId(sessionIdWrap);
			if(sessionId == null)
				setSessionId(sessionIdWrap.o);
		}
		sessionIdWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrSessionId() {
		return sessionId;
	}

	public String strSessionId() {
		return sessionId == null ? "" : sessionId;
	}

	public String jsonSessionId() {
		return sessionId == null ? "" : sessionId;
	}

	public String nomAffichageSessionId() {
		return null;
	}

	public String htmTooltipSessionId() {
		return null;
	}

	public String htmSessionId() {
		return sessionId == null ? "" : StringEscapeUtils.escapeHtml4(strSessionId());
	}

	///////////
	// saves //
	///////////

	/**	L'entité « saves »
	 *	 is defined as null before being initialized. 
	 */
	protected List<String> saves;
	@JsonIgnore
	public Wrap<List<String>> savesWrap = new Wrap<List<String>>().p(this).c(List.class).var("saves").o(saves);

	/**	<br/>L'entité « saves »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:saves">Trouver l'entité saves dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saves(Wrap<List<String>> c);

	public List<String> getSaves() {
		return saves;
	}

	public void setSaves(List<String> saves) {
		this.saves = saves;
		this.savesWrap.alreadyInitialized = true;
	}
	public Cluster addSaves(String...objets) {
		for(String o : objets) {
			addSaves(o);
		}
		return (Cluster)this;
	}
	public Cluster addSaves(String o) {
		if(o != null && !saves.contains(o))
			this.saves.add(o);
		return (Cluster)this;
	}
	public Cluster setSaves(JsonArray objets) {
		saves.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addSaves(o);
		}
		return (Cluster)this;
	}
	protected Cluster savesInit() {
		if(!savesWrap.alreadyInitialized) {
			_saves(savesWrap);
			if(saves == null)
				setSaves(savesWrap.o);
		}
		savesWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public List<String> solrSaves() {
		return saves;
	}

	public String strSaves() {
		return saves == null ? "" : saves.toString();
	}

	public String jsonSaves() {
		return saves == null ? "" : saves.toString();
	}

	public String nomAffichageSaves() {
		return null;
	}

	public String htmTooltipSaves() {
		return null;
	}

	public String htmSaves() {
		return saves == null ? "" : StringEscapeUtils.escapeHtml4(strSaves());
	}

	/////////////////
	// objectTitle //
	/////////////////

	/**	L'entité « objectTitle »
	 *	 is defined as null before being initialized. 
	 */
	protected String objectTitle;
	@JsonIgnore
	public Wrap<String> objectTitleWrap = new Wrap<String>().p(this).c(String.class).var("objectTitle").o(objectTitle);

	/**	<br/>L'entité « objectTitle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectTitle">Trouver l'entité objectTitle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objectTitle(Wrap<String> c);

	public String getObjectTitle() {
		return objectTitle;
	}

	public void setObjectTitle(String objectTitle) {
		this.objectTitle = objectTitle;
		this.objectTitleWrap.alreadyInitialized = true;
	}
	protected Cluster objectTitleInit() {
		if(!objectTitleWrap.alreadyInitialized) {
			_objectTitle(objectTitleWrap);
			if(objectTitle == null)
				setObjectTitle(objectTitleWrap.o);
		}
		objectTitleWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrObjectTitle() {
		return objectTitle;
	}

	public String strObjectTitle() {
		return objectTitle == null ? "" : objectTitle;
	}

	public String jsonObjectTitle() {
		return objectTitle == null ? "" : objectTitle;
	}

	public String nomAffichageObjectTitle() {
		return null;
	}

	public String htmTooltipObjectTitle() {
		return null;
	}

	public String htmObjectTitle() {
		return objectTitle == null ? "" : StringEscapeUtils.escapeHtml4(strObjectTitle());
	}

	public void inputObjectTitle(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
	}

	public void htmObjectTitle(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row  ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								{ s.e("div").a("class", "w3-rest ").f();
									s.e("span").f().sx(strObjectTitle()).g("span");
								} s.g("div");
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			}
		} s.g("div");
	}

	//////////////
	// objectId //
	//////////////

	/**	L'entité « objectId »
	 *	 is defined as null before being initialized. 
	 */
	protected String objectId;
	@JsonIgnore
	public Wrap<String> objectIdWrap = new Wrap<String>().p(this).c(String.class).var("objectId").o(objectId);

	/**	<br/>L'entité « objectId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectId">Trouver l'entité objectId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objectId(Wrap<String> c);

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
		this.objectIdWrap.alreadyInitialized = true;
	}
	protected Cluster objectIdInit() {
		if(!objectIdWrap.alreadyInitialized) {
			_objectId(objectIdWrap);
			if(objectId == null)
				setObjectId(objectIdWrap.o);
		}
		objectIdWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrObjectId() {
		return objectId;
	}

	public String strObjectId() {
		return objectId == null ? "" : objectId;
	}

	public String jsonObjectId() {
		return objectId == null ? "" : objectId;
	}

	public String nomAffichageObjectId() {
		return "ID";
	}

	public String htmTooltipObjectId() {
		return null;
	}

	public String htmObjectId() {
		return objectId == null ? "" : StringEscapeUtils.escapeHtml4(strObjectId());
	}

	public void inputObjectId(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
	}

	public void htmObjectId(String classApiMethodMethod) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("class", "").f().sx("ID").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row  ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								{ s.e("div").a("class", "w3-rest ").f();
									s.e("a").a("href", pageUrlId).f().sx(strObjectId()).g("a");
								} s.g("div");
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			}
		} s.g("div");
	}

	///////////////////
	// objectNameVar //
	///////////////////

	/**	L'entité « objectNameVar »
	 *	 is defined as null before being initialized. 
	 */
	protected String objectNameVar;
	@JsonIgnore
	public Wrap<String> objectNameVarWrap = new Wrap<String>().p(this).c(String.class).var("objectNameVar").o(objectNameVar);

	/**	<br/>L'entité « objectNameVar »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectNameVar">Trouver l'entité objectNameVar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objectNameVar(Wrap<String> c);

	public String getObjectNameVar() {
		return objectNameVar;
	}

	public void setObjectNameVar(String objectNameVar) {
		this.objectNameVar = objectNameVar;
		this.objectNameVarWrap.alreadyInitialized = true;
	}
	protected Cluster objectNameVarInit() {
		if(!objectNameVarWrap.alreadyInitialized) {
			_objectNameVar(objectNameVarWrap);
			if(objectNameVar == null)
				setObjectNameVar(objectNameVarWrap.o);
		}
		objectNameVarWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrObjectNameVar() {
		return objectNameVar;
	}

	public String strObjectNameVar() {
		return objectNameVar == null ? "" : objectNameVar;
	}

	public String jsonObjectNameVar() {
		return objectNameVar == null ? "" : objectNameVar;
	}

	public String nomAffichageObjectNameVar() {
		return null;
	}

	public String htmTooltipObjectNameVar() {
		return null;
	}

	public String htmObjectNameVar() {
		return objectNameVar == null ? "" : StringEscapeUtils.escapeHtml4(strObjectNameVar());
	}

	///////////////////
	// objectSuggest //
	///////////////////

	/**	L'entité « objectSuggest »
	 *	 is defined as null before being initialized. 
	 */
	protected String objectSuggest;
	@JsonIgnore
	public Wrap<String> objectSuggestWrap = new Wrap<String>().p(this).c(String.class).var("objectSuggest").o(objectSuggest);

	/**	<br/>L'entité « objectSuggest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggest">Trouver l'entité objectSuggest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objectSuggest(Wrap<String> c);

	public String getObjectSuggest() {
		return objectSuggest;
	}

	public void setObjectSuggest(String objectSuggest) {
		this.objectSuggest = objectSuggest;
		this.objectSuggestWrap.alreadyInitialized = true;
	}
	protected Cluster objectSuggestInit() {
		if(!objectSuggestWrap.alreadyInitialized) {
			_objectSuggest(objectSuggestWrap);
			if(objectSuggest == null)
				setObjectSuggest(objectSuggestWrap.o);
		}
		objectSuggestWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrObjectSuggest() {
		return objectSuggest;
	}

	public String strObjectSuggest() {
		return objectSuggest == null ? "" : objectSuggest;
	}

	public String jsonObjectSuggest() {
		return objectSuggest == null ? "" : objectSuggest;
	}

	public String nomAffichageObjectSuggest() {
		return null;
	}

	public String htmTooltipObjectSuggest() {
		return null;
	}

	public String htmObjectSuggest() {
		return objectSuggest == null ? "" : StringEscapeUtils.escapeHtml4(strObjectSuggest());
	}

	///////////////
	// pageUrlId //
	///////////////

	/**	L'entité « pageUrlId »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUrlId;
	@JsonIgnore
	public Wrap<String> pageUrlIdWrap = new Wrap<String>().p(this).c(String.class).var("pageUrlId").o(pageUrlId);

	/**	<br/>L'entité « pageUrlId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrlId">Trouver l'entité pageUrlId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrlId(Wrap<String> c);

	public String getPageUrlId() {
		return pageUrlId;
	}

	public void setPageUrlId(String pageUrlId) {
		this.pageUrlId = pageUrlId;
		this.pageUrlIdWrap.alreadyInitialized = true;
	}
	protected Cluster pageUrlIdInit() {
		if(!pageUrlIdWrap.alreadyInitialized) {
			_pageUrlId(pageUrlIdWrap);
			if(pageUrlId == null)
				setPageUrlId(pageUrlIdWrap.o);
		}
		pageUrlIdWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrPageUrlId() {
		return pageUrlId;
	}

	public String strPageUrlId() {
		return pageUrlId == null ? "" : pageUrlId;
	}

	public String jsonPageUrlId() {
		return pageUrlId == null ? "" : pageUrlId;
	}

	public String nomAffichagePageUrlId() {
		return null;
	}

	public String htmTooltipPageUrlId() {
		return null;
	}

	public String htmPageUrlId() {
		return pageUrlId == null ? "" : StringEscapeUtils.escapeHtml4(strPageUrlId());
	}

	///////////////
	// pageUrlPk //
	///////////////

	/**	L'entité « pageUrlPk »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUrlPk;
	@JsonIgnore
	public Wrap<String> pageUrlPkWrap = new Wrap<String>().p(this).c(String.class).var("pageUrlPk").o(pageUrlPk);

	/**	<br/>L'entité « pageUrlPk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrlPk">Trouver l'entité pageUrlPk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrlPk(Wrap<String> c);

	public String getPageUrlPk() {
		return pageUrlPk;
	}

	public void setPageUrlPk(String pageUrlPk) {
		this.pageUrlPk = pageUrlPk;
		this.pageUrlPkWrap.alreadyInitialized = true;
	}
	protected Cluster pageUrlPkInit() {
		if(!pageUrlPkWrap.alreadyInitialized) {
			_pageUrlPk(pageUrlPkWrap);
			if(pageUrlPk == null)
				setPageUrlPk(pageUrlPkWrap.o);
		}
		pageUrlPkWrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrPageUrlPk() {
		return pageUrlPk;
	}

	public String strPageUrlPk() {
		return pageUrlPk == null ? "" : pageUrlPk;
	}

	public String jsonPageUrlPk() {
		return pageUrlPk == null ? "" : pageUrlPk;
	}

	public String nomAffichagePageUrlPk() {
		return null;
	}

	public String htmTooltipPageUrlPk() {
		return null;
	}

	public String htmPageUrlPk() {
		return pageUrlPk == null ? "" : StringEscapeUtils.escapeHtml4(strPageUrlPk());
	}

	////////////
	// pageH1 //
	////////////

	/**	L'entité « pageH1 »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageH1;
	@JsonIgnore
	public Wrap<String> pageH1Wrap = new Wrap<String>().p(this).c(String.class).var("pageH1").o(pageH1);

	/**	<br/>L'entité « pageH1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH1">Trouver l'entité pageH1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageH1(Wrap<String> c);

	public String getPageH1() {
		return pageH1;
	}

	public void setPageH1(String pageH1) {
		this.pageH1 = pageH1;
		this.pageH1Wrap.alreadyInitialized = true;
	}
	protected Cluster pageH1Init() {
		if(!pageH1Wrap.alreadyInitialized) {
			_pageH1(pageH1Wrap);
			if(pageH1 == null)
				setPageH1(pageH1Wrap.o);
		}
		pageH1Wrap.alreadyInitialized(true);
		return (Cluster)this;
	}

	public String solrPageH1() {
		return pageH1;
	}

	public String strPageH1() {
		return pageH1 == null ? "" : pageH1;
	}

	public String jsonPageH1() {
		return pageH1 == null ? "" : pageH1;
	}

	public String nomAffichagePageH1() {
		return null;
	}

	public String htmTooltipPageH1() {
		return null;
	}

	public String htmPageH1() {
		return pageH1 == null ? "" : StringEscapeUtils.escapeHtml4(strPageH1());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedCluster = false;

	public Cluster initDeepCluster(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedCluster) {
			alreadyInitializedCluster = true;
			initDeepCluster();
		}
		return (Cluster)this;
	}

	public void initDeepCluster() {
		initCluster();
	}

	public void initCluster() {
		siteRequest_Init();
		pagePartsInit();
		pkInit();
		inheritPkInit();
		idInit();
		createdInit();
		modifiedInit();
		archivedInit();
		deletedInit();
		classCanonicalNameInit();
		classSimpleNameInit();
		classCanonicalNamesInit();
		sessionIdInit();
		savesInit();
		objectTitleInit();
		objectIdInit();
		objectNameVarInit();
		objectSuggestInit();
		pageUrlIdInit();
		pageUrlPkInit();
		pageH1Init();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepCluster(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCluster(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestCluster(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCluster(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainCluster(String var) {
		Cluster oCluster = (Cluster)this;
		switch(var) {
			case "siteRequest_":
				return oCluster.siteRequest_;
			case "pageParts":
				return oCluster.pageParts;
			case "pk":
				return oCluster.pk;
			case "inheritPk":
				return oCluster.inheritPk;
			case "id":
				return oCluster.id;
			case "created":
				return oCluster.created;
			case "modified":
				return oCluster.modified;
			case "archived":
				return oCluster.archived;
			case "deleted":
				return oCluster.deleted;
			case "classCanonicalName":
				return oCluster.classCanonicalName;
			case "classSimpleName":
				return oCluster.classSimpleName;
			case "classCanonicalNames":
				return oCluster.classCanonicalNames;
			case "sessionId":
				return oCluster.sessionId;
			case "saves":
				return oCluster.saves;
			case "objectTitle":
				return oCluster.objectTitle;
			case "objectId":
				return oCluster.objectId;
			case "objectNameVar":
				return oCluster.objectNameVar;
			case "objectSuggest":
				return oCluster.objectSuggest;
			case "pageUrlId":
				return oCluster.pageUrlId;
			case "pageUrlPk":
				return oCluster.pageUrlPk;
			case "pageH1":
				return oCluster.pageH1;
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
				o = attributeCluster(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeCluster(String var, Object val) {
		Cluster oCluster = (Cluster)this;
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
					o = defineCluster(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineCluster(String var, String val) {
		switch(var) {
			case "created":
				setCreated(val);
				savesCluster.add(var);
				return val;
			case "modified":
				setModified(val);
				savesCluster.add(var);
				return val;
			case "archived":
				setArchived(val);
				savesCluster.add(var);
				return val;
			case "deleted":
				setDeleted(val);
				savesCluster.add(var);
				return val;
			default:
				return null;
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesCluster = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	public void populateForClass(SolrDocument solrDocument) {
		populateCluster(solrDocument);
	}
	public void populateCluster(SolrDocument solrDocument) {
		Cluster oCluster = (Cluster)this;
		savesCluster = (List<String>)solrDocument.get("savesCluster_stored_strings");
		if(savesCluster != null) {

			Long pk = (Long)solrDocument.get("pk_stored_long");
			oCluster.setPk(pk);

			if(savesCluster.contains("inheritPk")) {
				Long inheritPk = (Long)solrDocument.get("inheritPk_stored_long");
				if(inheritPk != null)
					oCluster.setInheritPk(inheritPk);
			}

			String id = (String)solrDocument.get("id");
			oCluster.setId(id);

			if(savesCluster.contains("created")) {
				Date created = (Date)solrDocument.get("created_stored_date");
				if(created != null)
					oCluster.setCreated(created);
			}

			if(savesCluster.contains("modified")) {
				Date modified = (Date)solrDocument.get("modified_stored_date");
				if(modified != null)
					oCluster.setModified(modified);
			}

			if(savesCluster.contains("archived")) {
				Boolean archived = (Boolean)solrDocument.get("archived_stored_boolean");
				if(archived != null)
					oCluster.setArchived(archived);
			}

			if(savesCluster.contains("deleted")) {
				Boolean deleted = (Boolean)solrDocument.get("deleted_stored_boolean");
				if(deleted != null)
					oCluster.setDeleted(deleted);
			}

			if(savesCluster.contains("classCanonicalName")) {
				String classCanonicalName = (String)solrDocument.get("classCanonicalName_stored_string");
				if(classCanonicalName != null)
					oCluster.setClassCanonicalName(classCanonicalName);
			}

			if(savesCluster.contains("classSimpleName")) {
				String classSimpleName = (String)solrDocument.get("classSimpleName_stored_string");
				if(classSimpleName != null)
					oCluster.setClassSimpleName(classSimpleName);
			}

			if(savesCluster.contains("classCanonicalNames")) {
				List<String> classCanonicalNames = (List<String>)solrDocument.get("classCanonicalNames_stored_strings");
				if(classCanonicalNames != null)
					oCluster.classCanonicalNames.addAll(classCanonicalNames);
			}

			if(savesCluster.contains("sessionId")) {
				String sessionId = (String)solrDocument.get("sessionId_stored_string");
				if(sessionId != null)
					oCluster.setSessionId(sessionId);
			}

			if(savesCluster.contains("saves")) {
				List<String> saves = (List<String>)solrDocument.get("saves_stored_strings");
				if(saves != null)
					oCluster.setSaves(saves);
			}

			if(savesCluster.contains("objectTitle")) {
				String objectTitle = (String)solrDocument.get("objectTitle_stored_string");
				if(objectTitle != null)
					oCluster.setObjectTitle(objectTitle);
			}

			if(savesCluster.contains("objectId")) {
				String objectId = (String)solrDocument.get("objectId_stored_string");
				if(objectId != null)
					oCluster.setObjectId(objectId);
			}

			if(savesCluster.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
				oCluster.setObjectSuggest(objectSuggest);
			}

			if(savesCluster.contains("pageUrlId")) {
				String pageUrlId = (String)solrDocument.get("pageUrlId_stored_string");
				if(pageUrlId != null)
					oCluster.setPageUrlId(pageUrlId);
			}

			if(savesCluster.contains("pageUrlPk")) {
				String pageUrlPk = (String)solrDocument.get("pageUrlPk_stored_string");
				if(pageUrlPk != null)
					oCluster.setPageUrlPk(pageUrlPk);
			}
		}
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.cluster.Cluster"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			Cluster o = new Cluster();
			o.siteRequestCluster(siteRequest);
			o.initDeepCluster(siteRequest);
			o.indexCluster();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	public void indexForClass() {
		indexCluster();
	}

	public void indexForClass(SolrInputDocument document) {
		indexCluster(document);
	}

	public void indexCluster(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexCluster(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexCluster() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexCluster(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexCluster(SolrInputDocument document) {
		if(savesCluster != null)
			document.addField("savesCluster_stored_strings", savesCluster);

		if(pk != null) {
			document.addField("pk_indexed_long", pk);
			document.addField("pk_stored_long", pk);
		}
		if(inheritPk != null) {
			document.addField("inheritPk_indexed_long", inheritPk);
			document.addField("inheritPk_stored_long", inheritPk);
		}
		if(id != null) {
			document.addField("id", id);
			document.addField("id_indexed_string", id);
		}
		if(created != null) {
			document.addField("created_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(created.toInstant(), ZoneId.of("UTC"))));
			document.addField("created_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(created.toInstant(), ZoneId.of("UTC"))));
		}
		if(modified != null) {
			document.addField("modified_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(modified.toInstant(), ZoneId.of("UTC"))));
			document.addField("modified_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(modified.toInstant(), ZoneId.of("UTC"))));
		}
		if(archived != null) {
			document.addField("archived_indexed_boolean", archived);
			document.addField("archived_stored_boolean", archived);
		}
		if(deleted != null) {
			document.addField("deleted_indexed_boolean", deleted);
			document.addField("deleted_stored_boolean", deleted);
		}
		if(classCanonicalName != null) {
			document.addField("classCanonicalName_indexed_string", classCanonicalName);
			document.addField("classCanonicalName_stored_string", classCanonicalName);
		}
		if(classSimpleName != null) {
			document.addField("classSimpleName_indexed_string", classSimpleName);
			document.addField("classSimpleName_stored_string", classSimpleName);
		}
		if(classCanonicalNames != null) {
			for(java.lang.String o : classCanonicalNames) {
				document.addField("classCanonicalNames_indexed_strings", o);
			}
			for(java.lang.String o : classCanonicalNames) {
				document.addField("classCanonicalNames_stored_strings", o);
			}
		}
		if(sessionId != null) {
			document.addField("sessionId_indexed_string", sessionId);
			document.addField("sessionId_stored_string", sessionId);
		}
		if(saves != null) {
			for(java.lang.String o : saves) {
				document.addField("saves_indexed_strings", o);
			}
			for(java.lang.String o : saves) {
				document.addField("saves_stored_strings", o);
			}
		}
		if(objectTitle != null) {
			document.addField("objectTitle_indexed_string", objectTitle);
			document.addField("objectTitle_stored_string", objectTitle);
		}
		if(objectId != null) {
			document.addField("objectId_indexed_string", objectId);
			document.addField("objectId_stored_string", objectId);
		}
		if(objectSuggest != null) {
			document.addField("objectSuggest_suggested", objectSuggest);
		}
		if(pageUrlId != null) {
			document.addField("pageUrlId_indexed_string", pageUrlId);
			document.addField("pageUrlId_stored_string", pageUrlId);
		}
		if(pageUrlPk != null) {
			document.addField("pageUrlPk_indexed_string", pageUrlPk);
			document.addField("pageUrlPk_stored_string", pageUrlPk);
		}
	}

	public void unindexCluster() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepCluster(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedCluster(String entityVar) {
		switch(entityVar) {
			case "pk":
				return "pk_indexed_long";
			case "inheritPk":
				return "inheritPk_indexed_long";
			case "id":
				return "id_indexed_string";
			case "created":
				return "created_indexed_date";
			case "modified":
				return "modified_indexed_date";
			case "archived":
				return "archived_indexed_boolean";
			case "deleted":
				return "deleted_indexed_boolean";
			case "classCanonicalName":
				return "classCanonicalName_indexed_string";
			case "classSimpleName":
				return "classSimpleName_indexed_string";
			case "classCanonicalNames":
				return "classCanonicalNames_indexed_strings";
			case "sessionId":
				return "sessionId_indexed_string";
			case "saves":
				return "saves_indexed_strings";
			case "objectTitle":
				return "objectTitle_indexed_string";
			case "objectId":
				return "objectId_indexed_string";
			case "objectSuggest":
				return "objectSuggest_indexed_string";
			case "pageUrlId":
				return "pageUrlId_indexed_string";
			case "pageUrlPk":
				return "pageUrlPk_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public static String varSearchCluster(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public static String varSuggestCluster(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	/////////////
	// store //
	/////////////

	public void storeForClass(SolrDocument solrDocument) {
		storeCluster(solrDocument);
	}
	public void storeCluster(SolrDocument solrDocument) {
		Cluster oCluster = (Cluster)this;

		Long pk = (Long)solrDocument.get("pk_stored_long");
		if(pk != null)
			oCluster.setPk(pk);

		Long inheritPk = (Long)solrDocument.get("inheritPk_stored_long");
		if(inheritPk != null)
			oCluster.setInheritPk(inheritPk);

		String id = (String)solrDocument.get("id");
		oCluster.setId(id);

		Date created = (Date)solrDocument.get("created_stored_date");
		if(created != null)
			oCluster.setCreated(created);

		Date modified = (Date)solrDocument.get("modified_stored_date");
		if(modified != null)
			oCluster.setModified(modified);

		Boolean archived = (Boolean)solrDocument.get("archived_stored_boolean");
		if(archived != null)
			oCluster.setArchived(archived);

		Boolean deleted = (Boolean)solrDocument.get("deleted_stored_boolean");
		if(deleted != null)
			oCluster.setDeleted(deleted);

		String classCanonicalName = (String)solrDocument.get("classCanonicalName_stored_string");
		if(classCanonicalName != null)
			oCluster.setClassCanonicalName(classCanonicalName);

		String classSimpleName = (String)solrDocument.get("classSimpleName_stored_string");
		if(classSimpleName != null)
			oCluster.setClassSimpleName(classSimpleName);

		List<String> classCanonicalNames = (List<String>)solrDocument.get("classCanonicalNames_stored_strings");
		if(classCanonicalNames != null)
			oCluster.classCanonicalNames.addAll(classCanonicalNames);

		String sessionId = (String)solrDocument.get("sessionId_stored_string");
		if(sessionId != null)
			oCluster.setSessionId(sessionId);

		List<String> saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null)
			oCluster.setSaves(saves);

		String objectTitle = (String)solrDocument.get("objectTitle_stored_string");
		if(objectTitle != null)
			oCluster.setObjectTitle(objectTitle);

		String objectId = (String)solrDocument.get("objectId_stored_string");
		if(objectId != null)
			oCluster.setObjectId(objectId);

		String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
		oCluster.setObjectSuggest(objectSuggest);

		String pageUrlId = (String)solrDocument.get("pageUrlId_stored_string");
		if(pageUrlId != null)
			oCluster.setPageUrlId(pageUrlId);

		String pageUrlPk = (String)solrDocument.get("pageUrlPk_stored_string");
		if(pageUrlPk != null)
			oCluster.setPageUrlPk(pageUrlPk);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestCluster() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof Cluster) {
			Cluster original = (Cluster)o;
			if(!Objects.equals(created, original.getCreated()))
				apiRequest.addVars("created");
			if(!Objects.equals(modified, original.getModified()))
				apiRequest.addVars("modified");
			if(!Objects.equals(archived, original.getArchived()))
				apiRequest.addVars("archived");
			if(!Objects.equals(deleted, original.getDeleted()))
				apiRequest.addVars("deleted");
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(created, modified, archived, deleted);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Cluster))
			return false;
		Cluster that = (Cluster)o;
		return Objects.equals( created, that.created )
				&& Objects.equals( modified, that.modified )
				&& Objects.equals( archived, that.archived )
				&& Objects.equals( deleted, that.deleted );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cluster { ");
		sb.append( "created: " ).append(created);
		sb.append( ", modified: " ).append(modified);
		sb.append( ", archived: " ).append(archived);
		sb.append( ", deleted: " ).append(deleted);
		sb.append(" }");
		return sb.toString();
	}
}
