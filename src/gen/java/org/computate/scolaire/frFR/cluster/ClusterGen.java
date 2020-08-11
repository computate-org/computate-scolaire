package org.computate.scolaire.frFR.cluster;

import java.util.Arrays;
import java.util.Date;
import java.time.ZonedDateTime;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import org.computate.scolaire.frFR.page.part.PagePart;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
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
import java.lang.Object;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true">Trouver la classe pageH1 dans Solr. </a>
 * <br/>
 **/
public abstract class ClusterGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(Cluster.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("User");

	public static final String Cluster_UnNom = "un cluster";
	public static final String Cluster_Ce = "ce ";
	public static final String Cluster_CeNom = "ce cluster";
	public static final String Cluster_Un = "un ";
	public static final String Cluster_LeNom = "le cluster";
	public static final String Cluster_NomSingulier = "cluster";
	public static final String Cluster_NomPluriel = "clusters";
	public static final String Cluster_NomActuel = "cluster actuel";
	public static final String Cluster_Tous = "all ";
	public static final String Cluster_TousNom = "tous les clusters";
	public static final String Cluster_RechercherTousNomPar = "rechercher clusters par ";
	public static final String Cluster_RechercherTousNom = "rechercher clusters";
	public static final String Cluster_Titre = "clusters";
	public static final String Cluster_LesNom = "les clusters";
	public static final String Cluster_AucunNomTrouve = "aucun cluster trouvé";
	public static final String Cluster_NomVar = "cluster";
	public static final String Cluster_DeNom = "de cluster";
	public static final String Cluster_NomAdjectifSingulier = "cluster";
	public static final String Cluster_NomAdjectifPluriel = "clusters";
	public static final String Cluster_Couleur = "gray";
	public static final String Cluster_IconeGroupe = "regular";
	public static final String Cluster_IconeNom = "fort-awesome";

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	 L'entité requeteSite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/> L'entité requeteSite_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSiteFrFR> c);

	public RequeteSiteFrFR getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSiteFrFR requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
	}
	protected Cluster requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (Cluster)this;
	}

	///////////////
	// pageParts //
	///////////////

	/**	 L'entité pageParts
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<PagePart> pageParts = new ArrayList<PagePart>();
	@JsonIgnore
	public Couverture<List<PagePart>> pagePartsCouverture = new Couverture<List<PagePart>>().p(this).c(List.class).var("pageParts").o(pageParts);

	/**	<br/> L'entité pageParts
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageParts">Trouver l'entité pageParts dans Solr</a>
	 * <br/>
	 * @param pageParts est l'entité déjà construit. 
	 **/
	protected abstract void _pageParts(List<PagePart> l);

	public List<PagePart> getPageParts() {
		return pageParts;
	}

	public void setPageParts(List<PagePart> pageParts) {
		this.pageParts = pageParts;
		this.pagePartsCouverture.dejaInitialise = true;
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
	public abstract void avantPagePart(PagePart o, String entiteVar);
	protected Cluster pagePartsInit() {
		if(!pagePartsCouverture.dejaInitialise) {
			_pageParts(pageParts);
		}
		pagePartsCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	////////
	// pk //
	////////

	/**	 L'entité pk
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long pk;
	@JsonIgnore
	public Couverture<Long> pkCouverture = new Couverture<Long>().p(this).c(Long.class).var("pk").o(pk);

	/**	<br/> L'entité pk
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pk">Trouver l'entité pk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pk(Couverture<Long> c);

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
		this.pkCouverture.dejaInitialise = true;
	}
	public Cluster setPk(String o) {
		if(NumberUtils.isParsable(o))
			this.pk = Long.parseLong(o);
		this.pkCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	protected Cluster pkInit() {
		if(!pkCouverture.dejaInitialise) {
			_pk(pkCouverture);
			if(pk == null)
				setPk(pkCouverture.o);
		}
		pkCouverture.dejaInitialise(true);
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
		return "clé primaire";
	}

	public String htmTooltipPk() {
		return null;
	}

	public String htmPk() {
		return pk == null ? "" : StringEscapeUtils.escapeHtml4(strPk());
	}

	public void inputPk(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		s.e("span").a("class", "varCluster", pk, "Pk ").f().sx(htmPk()).g("span");
	}

	public void htmPk(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			if("Page".equals(classeApiMethodeMethode)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("class", "").f().sx("clé primaire").g("label");
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

	/**	 L'entité inheritPk
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long inheritPk;
	@JsonIgnore
	public Couverture<Long> inheritPkCouverture = new Couverture<Long>().p(this).c(Long.class).var("inheritPk").o(inheritPk);

	/**	<br/> L'entité inheritPk
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inheritPk">Trouver l'entité inheritPk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inheritPk(Couverture<Long> c);

	public Long getInheritPk() {
		return inheritPk;
	}

	public void setInheritPk(Long inheritPk) {
		this.inheritPk = inheritPk;
		this.inheritPkCouverture.dejaInitialise = true;
	}
	public Cluster setInheritPk(String o) {
		if(NumberUtils.isParsable(o))
			this.inheritPk = Long.parseLong(o);
		this.inheritPkCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	protected Cluster inheritPkInit() {
		if(!inheritPkCouverture.dejaInitialise) {
			_inheritPk(inheritPkCouverture);
			if(inheritPk == null)
				setInheritPk(inheritPkCouverture.o);
		}
		inheritPkCouverture.dejaInitialise(true);
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

	public void inputInheritPk(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			s.e("input")
				.a("type", "text")
				.a("id", classeApiMethodeMethode, "_inheritPk");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					s.a("class", "setInheritPk classCluster inputCluster", pk, "InheritPk w3-input w3-border ");
					s.a("name", "setInheritPk");
				} else {
					s.a("class", "valeurInheritPk w3-input w3-border classCluster inputCluster", pk, "InheritPk w3-input w3-border ");
					s.a("name", "inheritPk");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					s.a("onclick", "enleverLueur($(this)); ");
					s.a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInheritPk', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inheritPk')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inheritPk')); }); ");
				}
				s.a("value", strInheritPk())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLE_READS)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLE_READS)
					) {
				s.e("span").a("class", "varCluster", pk, "InheritPk ").f().sx(htmInheritPk()).g("span");
			}
		}
	}

	public void htmInheritPk(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterInheritPk").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputInheritPk(classeApiMethodeMethode);
							} s.g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ s.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ s.e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inheritPk')); $('#", classeApiMethodeMethode, "_inheritPk').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#ClusterForm :input[name=pk]').val() }], 'setInheritPk', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inheritPk')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inheritPk')); }); ")
											.f();
											s.e("i").a("class", "far fa-eraser ").f().g("i");
										} s.g("button");
									} s.g("div");
								}
							}
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	////////
	// id //
	////////

	/**	 L'entité id
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String id;
	@JsonIgnore
	public Couverture<String> idCouverture = new Couverture<String>().p(this).c(String.class).var("id").o(id);

	/**	<br/> L'entité id
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:id">Trouver l'entité id dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _id(Couverture<String> c);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.idCouverture.dejaInitialise = true;
	}
	protected Cluster idInit() {
		if(!idCouverture.dejaInitialise) {
			_id(idCouverture);
			if(id == null)
				setId(idCouverture.o);
		}
		idCouverture.dejaInitialise(true);
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

	//////////
	// cree //
	//////////

	/**	 L'entité cree
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime cree;
	@JsonIgnore
	public Couverture<ZonedDateTime> creeCouverture = new Couverture<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("cree").o(cree);

	/**	<br/> L'entité cree
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cree">Trouver l'entité cree dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cree(Couverture<ZonedDateTime> c);

	public ZonedDateTime getCree() {
		return cree;
	}

	public void setCree(ZonedDateTime cree) {
		this.cree = cree;
		this.creeCouverture.dejaInitialise = true;
	}
	public Cluster setCree(Instant o) {
		this.cree = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
		this.creeCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public Cluster setCree(String o) {
		this.cree = o == null ? null : ZonedDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone()))).truncatedTo(ChronoUnit.MILLIS);
		this.creeCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	public Cluster setCree(Date o) {
		this.cree = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		this.creeCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	protected Cluster creeInit() {
		if(!creeCouverture.dejaInitialise) {
			_cree(creeCouverture);
			if(cree == null)
				setCree(creeCouverture.o);
		}
		creeCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public Date solrCree() {
		return cree == null ? null : Date.from(cree.toInstant());
	}

	public String strCree() {
		return cree == null ? "" : cree.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy H'h'mm:ss zz VV", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonCree() {
		return cree == null ? "" : cree.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public String nomAffichageCree() {
		return "crée";
	}

	public String htmTooltipCree() {
		return null;
	}

	public String htmCree() {
		return cree == null ? "" : StringEscapeUtils.escapeHtml4(strCree());
	}

	public void inputCree(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		s.e("span").a("class", "varCluster", pk, "Cree ").f().sx(htmCree()).g("span");
	}

	public void htmCree(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterCree").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("for", classeApiMethodeMethode, "_cree").a("class", "").f().sx("crée").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								inputCree(classeApiMethodeMethode);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	/////////////
	// modifie //
	/////////////

	/**	 L'entité modifie
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected ZonedDateTime modifie;
	@JsonIgnore
	public Couverture<ZonedDateTime> modifieCouverture = new Couverture<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("modifie").o(modifie);

	/**	<br/> L'entité modifie
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modifie">Trouver l'entité modifie dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modifie(Couverture<ZonedDateTime> c);

	public ZonedDateTime getModifie() {
		return modifie;
	}

	public void setModifie(ZonedDateTime modifie) {
		this.modifie = modifie;
		this.modifieCouverture.dejaInitialise = true;
	}
	public Cluster setModifie(Instant o) {
		this.modifie = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
		this.modifieCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public Cluster setModifie(String o) {
		this.modifie = o == null ? null : ZonedDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone()))).truncatedTo(ChronoUnit.MILLIS);
		this.modifieCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	public Cluster setModifie(Date o) {
		this.modifie = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		this.modifieCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	protected Cluster modifieInit() {
		if(!modifieCouverture.dejaInitialise) {
			_modifie(modifieCouverture);
			if(modifie == null)
				setModifie(modifieCouverture.o);
		}
		modifieCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public Date solrModifie() {
		return modifie == null ? null : Date.from(modifie.toInstant());
	}

	public String strModifie() {
		return modifie == null ? "" : modifie.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy H'h'mm:ss zz VV", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonModifie() {
		return modifie == null ? "" : modifie.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public String nomAffichageModifie() {
		return "modifié";
	}

	public String htmTooltipModifie() {
		return null;
	}

	public String htmModifie() {
		return modifie == null ? "" : StringEscapeUtils.escapeHtml4(strModifie());
	}

	public void inputModifie(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		s.e("span").a("class", "varCluster", pk, "Modifie ").f().sx(htmModifie()).g("span");
	}

	public void htmModifie(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterModifie").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("for", classeApiMethodeMethode, "_modifie").a("class", "").f().sx("modifié").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								inputModifie(classeApiMethodeMethode);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	/////////////
	// archive //
	/////////////

	/**	 L'entité archive
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean archive;
	@JsonIgnore
	public Couverture<Boolean> archiveCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("archive").o(archive);

	/**	<br/> L'entité archive
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:archive">Trouver l'entité archive dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _archive(Couverture<Boolean> c);

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
		this.archiveCouverture.dejaInitialise = true;
	}
	public Cluster setArchive(String o) {
		this.archive = Boolean.parseBoolean(o);
		this.archiveCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	protected Cluster archiveInit() {
		if(!archiveCouverture.dejaInitialise) {
			_archive(archiveCouverture);
			if(archive == null)
				setArchive(archiveCouverture.o);
		}
		archiveCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public Boolean solrArchive() {
		return archive;
	}

	public String strArchive() {
		return archive == null ? "" : archive.toString();
	}

	public String jsonArchive() {
		return archive == null ? "" : archive.toString();
	}

	public String nomAffichageArchive() {
		return "archivé";
	}

	public String htmTooltipArchive() {
		return null;
	}

	public String htmArchive() {
		return archive == null ? "" : StringEscapeUtils.escapeHtml4(strArchive());
	}

	public void inputArchive(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				s.e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_archive")
					.a("value", "true");
			} else {
				s.e("select")
					.a("id", classeApiMethodeMethode, "_archive");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				s.a("class", "setArchive classCluster inputCluster", pk, "Archive w3-input w3-border ");
				s.a("name", "setArchive");
			} else {
				s.a("class", "valeurArchive classCluster inputCluster", pk, "Archive w3-input w3-border ");
				s.a("name", "archive");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				s.a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setArchive', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_archive')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_archive')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getArchive() != null && getArchive())
					s.a("checked", "checked");
				s.fg();
			} else {
				s.f();
				s.e("option").a("value", "").a("selected", "selected").f().g("option");
				s.e("option").a("value", "true").f().sx("true").g("option");
				s.e("option").a("value", "false").f().sx("false").g("option");
				s.g("select");
			}

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLE_READS)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLE_READS)
					) {
				s.e("span").a("class", "varCluster", pk, "Archive ").f().sx(htmArchive()).g("span");
			}
		}
	}

	public void htmArchive(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterArchive").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("for", classeApiMethodeMethode, "_archive").a("class", "").f().sx("archivé").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputArchive(classeApiMethodeMethode);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	//////////////
	// supprime //
	//////////////

	/**	 L'entité supprime
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean supprime;
	@JsonIgnore
	public Couverture<Boolean> supprimeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("supprime").o(supprime);

	/**	<br/> L'entité supprime
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:supprime">Trouver l'entité supprime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _supprime(Couverture<Boolean> c);

	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
		this.supprimeCouverture.dejaInitialise = true;
	}
	public Cluster setSupprime(String o) {
		this.supprime = Boolean.parseBoolean(o);
		this.supprimeCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	protected Cluster supprimeInit() {
		if(!supprimeCouverture.dejaInitialise) {
			_supprime(supprimeCouverture);
			if(supprime == null)
				setSupprime(supprimeCouverture.o);
		}
		supprimeCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public Boolean solrSupprime() {
		return supprime;
	}

	public String strSupprime() {
		return supprime == null ? "" : supprime.toString();
	}

	public String jsonSupprime() {
		return supprime == null ? "" : supprime.toString();
	}

	public String nomAffichageSupprime() {
		return "supprimé";
	}

	public String htmTooltipSupprime() {
		return null;
	}

	public String htmSupprime() {
		return supprime == null ? "" : StringEscapeUtils.escapeHtml4(strSupprime());
	}

	public void inputSupprime(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				s.e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_supprime")
					.a("value", "true");
			} else {
				s.e("select")
					.a("id", classeApiMethodeMethode, "_supprime");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				s.a("class", "setSupprime classCluster inputCluster", pk, "Supprime w3-input w3-border ");
				s.a("name", "setSupprime");
			} else {
				s.a("class", "valeurSupprime classCluster inputCluster", pk, "Supprime w3-input w3-border ");
				s.a("name", "supprime");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				s.a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSupprime', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_supprime')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_supprime')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getSupprime() != null && getSupprime())
					s.a("checked", "checked");
				s.fg();
			} else {
				s.f();
				s.e("option").a("value", "").a("selected", "selected").f().g("option");
				s.e("option").a("value", "true").f().sx("true").g("option");
				s.e("option").a("value", "false").f().sx("false").g("option");
				s.g("select");
			}

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLE_READS)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLE_READS)
					) {
				s.e("span").a("class", "varCluster", pk, "Supprime ").f().sx(htmSupprime()).g("span");
			}
		}
	}

	public void htmSupprime(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterSupprime").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("for", classeApiMethodeMethode, "_supprime").a("class", "").f().sx("supprimé").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputSupprime(classeApiMethodeMethode);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	////////////////////////
	// classeNomCanonique //
	////////////////////////

	/**	 L'entité classeNomCanonique
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classeNomCanonique;
	@JsonIgnore
	public Couverture<String> classeNomCanoniqueCouverture = new Couverture<String>().p(this).c(String.class).var("classeNomCanonique").o(classeNomCanonique);

	/**	<br/> L'entité classeNomCanonique
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeNomCanonique">Trouver l'entité classeNomCanonique dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeNomCanonique(Couverture<String> c);

	public String getClasseNomCanonique() {
		return classeNomCanonique;
	}

	public void setClasseNomCanonique(String classeNomCanonique) {
		this.classeNomCanonique = classeNomCanonique;
		this.classeNomCanoniqueCouverture.dejaInitialise = true;
	}
	protected Cluster classeNomCanoniqueInit() {
		if(!classeNomCanoniqueCouverture.dejaInitialise) {
			_classeNomCanonique(classeNomCanoniqueCouverture);
			if(classeNomCanonique == null)
				setClasseNomCanonique(classeNomCanoniqueCouverture.o);
		}
		classeNomCanoniqueCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrClasseNomCanonique() {
		return classeNomCanonique;
	}

	public String strClasseNomCanonique() {
		return classeNomCanonique == null ? "" : classeNomCanonique;
	}

	public String jsonClasseNomCanonique() {
		return classeNomCanonique == null ? "" : classeNomCanonique;
	}

	public String nomAffichageClasseNomCanonique() {
		return null;
	}

	public String htmTooltipClasseNomCanonique() {
		return null;
	}

	public String htmClasseNomCanonique() {
		return classeNomCanonique == null ? "" : StringEscapeUtils.escapeHtml4(strClasseNomCanonique());
	}

	/////////////////////
	// classeNomSimple //
	/////////////////////

	/**	 L'entité classeNomSimple
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classeNomSimple;
	@JsonIgnore
	public Couverture<String> classeNomSimpleCouverture = new Couverture<String>().p(this).c(String.class).var("classeNomSimple").o(classeNomSimple);

	/**	<br/> L'entité classeNomSimple
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeNomSimple">Trouver l'entité classeNomSimple dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classeNomSimple(Couverture<String> c);

	public String getClasseNomSimple() {
		return classeNomSimple;
	}

	public void setClasseNomSimple(String classeNomSimple) {
		this.classeNomSimple = classeNomSimple;
		this.classeNomSimpleCouverture.dejaInitialise = true;
	}
	protected Cluster classeNomSimpleInit() {
		if(!classeNomSimpleCouverture.dejaInitialise) {
			_classeNomSimple(classeNomSimpleCouverture);
			if(classeNomSimple == null)
				setClasseNomSimple(classeNomSimpleCouverture.o);
		}
		classeNomSimpleCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrClasseNomSimple() {
		return classeNomSimple;
	}

	public String strClasseNomSimple() {
		return classeNomSimple == null ? "" : classeNomSimple;
	}

	public String jsonClasseNomSimple() {
		return classeNomSimple == null ? "" : classeNomSimple;
	}

	public String nomAffichageClasseNomSimple() {
		return null;
	}

	public String htmTooltipClasseNomSimple() {
		return null;
	}

	public String htmClasseNomSimple() {
		return classeNomSimple == null ? "" : StringEscapeUtils.escapeHtml4(strClasseNomSimple());
	}

	//////////////////////////
	// classeNomsCanoniques //
	//////////////////////////

	/**	 L'entité classeNomsCanoniques
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classeNomsCanoniques = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> classeNomsCanoniquesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classeNomsCanoniques").o(classeNomsCanoniques);

	/**	<br/> L'entité classeNomsCanoniques
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classeNomsCanoniques">Trouver l'entité classeNomsCanoniques dans Solr</a>
	 * <br/>
	 * @param classeNomsCanoniques est l'entité déjà construit. 
	 **/
	protected abstract void _classeNomsCanoniques(List<String> l);

	public List<String> getClasseNomsCanoniques() {
		return classeNomsCanoniques;
	}

	public void setClasseNomsCanoniques(List<String> classeNomsCanoniques) {
		this.classeNomsCanoniques = classeNomsCanoniques;
		this.classeNomsCanoniquesCouverture.dejaInitialise = true;
	}
	public Cluster addClasseNomsCanoniques(String...objets) {
		for(String o : objets) {
			addClasseNomsCanoniques(o);
		}
		return (Cluster)this;
	}
	public Cluster addClasseNomsCanoniques(String o) {
		if(o != null && !classeNomsCanoniques.contains(o))
			this.classeNomsCanoniques.add(o);
		return (Cluster)this;
	}
	public Cluster setClasseNomsCanoniques(JsonArray objets) {
		classeNomsCanoniques.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasseNomsCanoniques(o);
		}
		return (Cluster)this;
	}
	protected Cluster classeNomsCanoniquesInit() {
		if(!classeNomsCanoniquesCouverture.dejaInitialise) {
			_classeNomsCanoniques(classeNomsCanoniques);
		}
		classeNomsCanoniquesCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public List<String> solrClasseNomsCanoniques() {
		return classeNomsCanoniques;
	}

	public String strClasseNomsCanoniques() {
		return classeNomsCanoniques == null ? "" : classeNomsCanoniques.toString();
	}

	public String jsonClasseNomsCanoniques() {
		return classeNomsCanoniques == null ? "" : classeNomsCanoniques.toString();
	}

	public String nomAffichageClasseNomsCanoniques() {
		return null;
	}

	public String htmTooltipClasseNomsCanoniques() {
		return null;
	}

	public String htmClasseNomsCanoniques() {
		return classeNomsCanoniques == null ? "" : StringEscapeUtils.escapeHtml4(strClasseNomsCanoniques());
	}

	///////////////
	// sessionId //
	///////////////

	/**	 L'entité sessionId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sessionId;
	@JsonIgnore
	public Couverture<String> sessionIdCouverture = new Couverture<String>().p(this).c(String.class).var("sessionId").o(sessionId);

	/**	<br/> L'entité sessionId
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionId">Trouver l'entité sessionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionId(Couverture<String> c);

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
		this.sessionIdCouverture.dejaInitialise = true;
	}
	protected Cluster sessionIdInit() {
		if(!sessionIdCouverture.dejaInitialise) {
			_sessionId(sessionIdCouverture);
			if(sessionId == null)
				setSessionId(sessionIdCouverture.o);
		}
		sessionIdCouverture.dejaInitialise(true);
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

	public void inputSessionId(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		s.e("span").a("class", "varCluster", pk, "SessionId ").f().sx(htmSessionId()).g("span");
	}

	public void htmSessionId(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterSessionId").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputSessionId(classeApiMethodeMethode);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	///////////////////
	// utilisateurId //
	///////////////////

	/**	 L'entité utilisateurId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String utilisateurId;
	@JsonIgnore
	public Couverture<String> utilisateurIdCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurId").o(utilisateurId);

	/**	<br/> L'entité utilisateurId
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurId">Trouver l'entité utilisateurId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurId(Couverture<String> c);

	public String getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(String utilisateurId) {
		this.utilisateurId = utilisateurId;
		this.utilisateurIdCouverture.dejaInitialise = true;
	}
	protected Cluster utilisateurIdInit() {
		if(!utilisateurIdCouverture.dejaInitialise) {
			_utilisateurId(utilisateurIdCouverture);
			if(utilisateurId == null)
				setUtilisateurId(utilisateurIdCouverture.o);
		}
		utilisateurIdCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrUtilisateurId() {
		return utilisateurId;
	}

	public String strUtilisateurId() {
		return utilisateurId == null ? "" : utilisateurId;
	}

	public String jsonUtilisateurId() {
		return utilisateurId == null ? "" : utilisateurId;
	}

	public String nomAffichageUtilisateurId() {
		return null;
	}

	public String htmTooltipUtilisateurId() {
		return null;
	}

	public String htmUtilisateurId() {
		return utilisateurId == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurId());
	}

	public void inputUtilisateurId(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		s.e("span").a("class", "varCluster", pk, "UtilisateurId ").f().sx(htmUtilisateurId()).g("span");
	}

	public void htmUtilisateurId(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterUtilisateurId").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputUtilisateurId(classeApiMethodeMethode);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	////////////////////
	// utilisateurCle //
	////////////////////

	/**	 L'entité utilisateurCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long utilisateurCle;
	@JsonIgnore
	public Couverture<Long> utilisateurCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("utilisateurCle").o(utilisateurCle);

	/**	<br/> L'entité utilisateurCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCle">Trouver l'entité utilisateurCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurCle(Couverture<Long> c);

	public Long getUtilisateurCle() {
		return utilisateurCle;
	}

	public void setUtilisateurCle(Long utilisateurCle) {
		this.utilisateurCle = utilisateurCle;
		this.utilisateurCleCouverture.dejaInitialise = true;
	}
	public Cluster setUtilisateurCle(String o) {
		if(NumberUtils.isParsable(o))
			this.utilisateurCle = Long.parseLong(o);
		this.utilisateurCleCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	protected Cluster utilisateurCleInit() {
		if(!utilisateurCleCouverture.dejaInitialise) {
			_utilisateurCle(utilisateurCleCouverture);
			if(utilisateurCle == null)
				setUtilisateurCle(utilisateurCleCouverture.o);
		}
		utilisateurCleCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public Long solrUtilisateurCle() {
		return utilisateurCle;
	}

	public String strUtilisateurCle() {
		return utilisateurCle == null ? "" : utilisateurCle.toString();
	}

	public String jsonUtilisateurCle() {
		return utilisateurCle == null ? "" : utilisateurCle.toString();
	}

	public String nomAffichageUtilisateurCle() {
		return null;
	}

	public String htmTooltipUtilisateurCle() {
		return null;
	}

	public String htmUtilisateurCle() {
		return utilisateurCle == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurCle());
	}

	public void inputUtilisateurCle(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		s.e("span").a("class", "varCluster", pk, "UtilisateurCle ").f().sx(htmUtilisateurCle()).g("span");
	}

	public void htmUtilisateurCle(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ s.e("div").a("class", "w3-padding ").f();
				{ s.e("div").a("id", "suggere", classeApiMethodeMethode, "ClusterUtilisateurCle").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-padding ").f();
							{ s.e("div").a("class", "w3-cell ").f();

								inputUtilisateurCle(classeApiMethodeMethode);
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			} s.g("div");
		} s.g("div");
	}

	/////////////////
	// sauvegardes //
	/////////////////

	/**	 L'entité sauvegardes
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> sauvegardes = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> sauvegardesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("sauvegardes").o(sauvegardes);

	/**	<br/> L'entité sauvegardes
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sauvegardes">Trouver l'entité sauvegardes dans Solr</a>
	 * <br/>
	 * @param sauvegardes est l'entité déjà construit. 
	 **/
	protected abstract void _sauvegardes(List<String> l);

	public List<String> getSauvegardes() {
		return sauvegardes;
	}

	public void setSauvegardes(List<String> sauvegardes) {
		this.sauvegardes = sauvegardes;
		this.sauvegardesCouverture.dejaInitialise = true;
	}
	public Cluster addSauvegardes(String...objets) {
		for(String o : objets) {
			addSauvegardes(o);
		}
		return (Cluster)this;
	}
	public Cluster addSauvegardes(String o) {
		if(o != null && !sauvegardes.contains(o))
			this.sauvegardes.add(o);
		return (Cluster)this;
	}
	public Cluster setSauvegardes(JsonArray objets) {
		sauvegardes.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addSauvegardes(o);
		}
		return (Cluster)this;
	}
	protected Cluster sauvegardesInit() {
		if(!sauvegardesCouverture.dejaInitialise) {
			_sauvegardes(sauvegardes);
		}
		sauvegardesCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public List<String> solrSauvegardes() {
		return sauvegardes;
	}

	public String strSauvegardes() {
		return sauvegardes == null ? "" : sauvegardes.toString();
	}

	public String jsonSauvegardes() {
		return sauvegardes == null ? "" : sauvegardes.toString();
	}

	public String nomAffichageSauvegardes() {
		return null;
	}

	public String htmTooltipSauvegardes() {
		return null;
	}

	public String htmSauvegardes() {
		return sauvegardes == null ? "" : StringEscapeUtils.escapeHtml4(strSauvegardes());
	}

	////////////////
	// objetTitre //
	////////////////

	/**	 L'entité objetTitre
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String objetTitre;
	@JsonIgnore
	public Couverture<String> objetTitreCouverture = new Couverture<String>().p(this).c(String.class).var("objetTitre").o(objetTitre);

	/**	<br/> L'entité objetTitre
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetTitre">Trouver l'entité objetTitre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetTitre(Couverture<String> c);

	public String getObjetTitre() {
		return objetTitre;
	}

	public void setObjetTitre(String objetTitre) {
		this.objetTitre = objetTitre;
		this.objetTitreCouverture.dejaInitialise = true;
	}
	protected Cluster objetTitreInit() {
		if(!objetTitreCouverture.dejaInitialise) {
			_objetTitre(objetTitreCouverture);
			if(objetTitre == null)
				setObjetTitre(objetTitreCouverture.o);
		}
		objetTitreCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrObjetTitre() {
		return objetTitre;
	}

	public String strObjetTitre() {
		return objetTitre == null ? "" : objetTitre;
	}

	public String jsonObjetTitre() {
		return objetTitre == null ? "" : objetTitre;
	}

	public String nomAffichageObjetTitre() {
		return null;
	}

	public String htmTooltipObjetTitre() {
		return null;
	}

	public String htmObjetTitre() {
		return objetTitre == null ? "" : StringEscapeUtils.escapeHtml4(strObjetTitre());
	}

	public void inputObjetTitre(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
	}

	public void htmObjetTitre(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			if("Page".equals(classeApiMethodeMethode)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row  ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								{ s.e("div").a("class", "w3-rest ").f();
									s.e("span").a("class", "varCluster", pk, "ObjetTitre ").f().sx(strObjetTitre()).g("span");
								} s.g("div");
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			}
		} s.g("div");
	}

	/////////////
	// objetId //
	/////////////

	/**	 L'entité objetId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String objetId;
	@JsonIgnore
	public Couverture<String> objetIdCouverture = new Couverture<String>().p(this).c(String.class).var("objetId").o(objetId);

	/**	<br/> L'entité objetId
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetId">Trouver l'entité objetId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetId(Couverture<String> c);

	public String getObjetId() {
		return objetId;
	}

	public void setObjetId(String objetId) {
		this.objetId = objetId;
		this.objetIdCouverture.dejaInitialise = true;
	}
	protected Cluster objetIdInit() {
		if(!objetIdCouverture.dejaInitialise) {
			_objetId(objetIdCouverture);
			if(objetId == null)
				setObjetId(objetIdCouverture.o);
		}
		objetIdCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrObjetId() {
		return objetId;
	}

	public String strObjetId() {
		return objetId == null ? "" : objetId;
	}

	public String jsonObjetId() {
		return objetId == null ? "" : objetId;
	}

	public String nomAffichageObjetId() {
		return "ID";
	}

	public String htmTooltipObjetId() {
		return null;
	}

	public String htmObjetId() {
		return objetId == null ? "" : StringEscapeUtils.escapeHtml4(strObjetId());
	}

	public void inputObjetId(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
	}

	public void htmObjetId(String classeApiMethodeMethode) {
		Cluster s = (Cluster)this;
		{ s.e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			if("Page".equals(classeApiMethodeMethode)) {
				{ s.e("div").a("class", "w3-padding ").f();
					{ s.e("div").a("class", "w3-card ").f();
						{ s.e("div").a("class", "w3-cell-row w3-gray ").f();
							s.e("label").a("class", "").f().sx("ID").g("label");
						} s.g("div");
						{ s.e("div").a("class", "w3-cell-row  ").f();
							{ s.e("div").a("class", "w3-cell ").f();
								{ s.e("div").a("class", "w3-rest ").f();
									s.e("a").a("href", pageUrlId).f().sx(strObjetId()).g("a");
								} s.g("div");
							} s.g("div");
						} s.g("div");
					} s.g("div");
				} s.g("div");
			}
		} s.g("div");
	}

	/////////////////
	// objetNomVar //
	/////////////////

	/**	 L'entité objetNomVar
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String objetNomVar;
	@JsonIgnore
	public Couverture<String> objetNomVarCouverture = new Couverture<String>().p(this).c(String.class).var("objetNomVar").o(objetNomVar);

	/**	<br/> L'entité objetNomVar
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetNomVar">Trouver l'entité objetNomVar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetNomVar(Couverture<String> c);

	public String getObjetNomVar() {
		return objetNomVar;
	}

	public void setObjetNomVar(String objetNomVar) {
		this.objetNomVar = objetNomVar;
		this.objetNomVarCouverture.dejaInitialise = true;
	}
	protected Cluster objetNomVarInit() {
		if(!objetNomVarCouverture.dejaInitialise) {
			_objetNomVar(objetNomVarCouverture);
			if(objetNomVar == null)
				setObjetNomVar(objetNomVarCouverture.o);
		}
		objetNomVarCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrObjetNomVar() {
		return objetNomVar;
	}

	public String strObjetNomVar() {
		return objetNomVar == null ? "" : objetNomVar;
	}

	public String jsonObjetNomVar() {
		return objetNomVar == null ? "" : objetNomVar;
	}

	public String nomAffichageObjetNomVar() {
		return null;
	}

	public String htmTooltipObjetNomVar() {
		return null;
	}

	public String htmObjetNomVar() {
		return objetNomVar == null ? "" : StringEscapeUtils.escapeHtml4(strObjetNomVar());
	}

	//////////////////
	// objetSuggere //
	//////////////////

	/**	 L'entité objetSuggere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String objetSuggere;
	@JsonIgnore
	public Couverture<String> objetSuggereCouverture = new Couverture<String>().p(this).c(String.class).var("objetSuggere").o(objetSuggere);

	/**	<br/> L'entité objetSuggere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetSuggere">Trouver l'entité objetSuggere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetSuggere(Couverture<String> c);

	public String getObjetSuggere() {
		return objetSuggere;
	}

	public void setObjetSuggere(String objetSuggere) {
		this.objetSuggere = objetSuggere;
		this.objetSuggereCouverture.dejaInitialise = true;
	}
	protected Cluster objetSuggereInit() {
		if(!objetSuggereCouverture.dejaInitialise) {
			_objetSuggere(objetSuggereCouverture);
			if(objetSuggere == null)
				setObjetSuggere(objetSuggereCouverture.o);
		}
		objetSuggereCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrObjetSuggere() {
		return objetSuggere;
	}

	public String strObjetSuggere() {
		return objetSuggere == null ? "" : objetSuggere;
	}

	public String jsonObjetSuggere() {
		return objetSuggere == null ? "" : objetSuggere;
	}

	public String nomAffichageObjetSuggere() {
		return null;
	}

	public String htmTooltipObjetSuggere() {
		return null;
	}

	public String htmObjetSuggere() {
		return objetSuggere == null ? "" : StringEscapeUtils.escapeHtml4(strObjetSuggere());
	}

	////////////////
	// objetTexte //
	////////////////

	/**	 L'entité objetTexte
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String objetTexte;
	@JsonIgnore
	public Couverture<String> objetTexteCouverture = new Couverture<String>().p(this).c(String.class).var("objetTexte").o(objetTexte);

	/**	<br/> L'entité objetTexte
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetTexte">Trouver l'entité objetTexte dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetTexte(Couverture<String> c);

	public String getObjetTexte() {
		return objetTexte;
	}

	public void setObjetTexte(String objetTexte) {
		this.objetTexte = objetTexte;
		this.objetTexteCouverture.dejaInitialise = true;
	}
	protected Cluster objetTexteInit() {
		if(!objetTexteCouverture.dejaInitialise) {
			_objetTexte(objetTexteCouverture);
			if(objetTexte == null)
				setObjetTexte(objetTexteCouverture.o);
		}
		objetTexteCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrObjetTexte() {
		return objetTexte;
	}

	public String strObjetTexte() {
		return objetTexte == null ? "" : objetTexte;
	}

	public String jsonObjetTexte() {
		return objetTexte == null ? "" : objetTexte;
	}

	public String nomAffichageObjetTexte() {
		return null;
	}

	public String htmTooltipObjetTexte() {
		return null;
	}

	public String htmObjetTexte() {
		return objetTexte == null ? "" : StringEscapeUtils.escapeHtml4(strObjetTexte());
	}

	///////////////
	// pageUrlId //
	///////////////

	/**	 L'entité pageUrlId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageUrlId;
	@JsonIgnore
	public Couverture<String> pageUrlIdCouverture = new Couverture<String>().p(this).c(String.class).var("pageUrlId").o(pageUrlId);

	/**	<br/> L'entité pageUrlId
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUrlId">Trouver l'entité pageUrlId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrlId(Couverture<String> c);

	public String getPageUrlId() {
		return pageUrlId;
	}

	public void setPageUrlId(String pageUrlId) {
		this.pageUrlId = pageUrlId;
		this.pageUrlIdCouverture.dejaInitialise = true;
	}
	protected Cluster pageUrlIdInit() {
		if(!pageUrlIdCouverture.dejaInitialise) {
			_pageUrlId(pageUrlIdCouverture);
			if(pageUrlId == null)
				setPageUrlId(pageUrlIdCouverture.o);
		}
		pageUrlIdCouverture.dejaInitialise(true);
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

	/**	 L'entité pageUrlPk
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageUrlPk;
	@JsonIgnore
	public Couverture<String> pageUrlPkCouverture = new Couverture<String>().p(this).c(String.class).var("pageUrlPk").o(pageUrlPk);

	/**	<br/> L'entité pageUrlPk
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUrlPk">Trouver l'entité pageUrlPk dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrlPk(Couverture<String> c);

	public String getPageUrlPk() {
		return pageUrlPk;
	}

	public void setPageUrlPk(String pageUrlPk) {
		this.pageUrlPk = pageUrlPk;
		this.pageUrlPkCouverture.dejaInitialise = true;
	}
	protected Cluster pageUrlPkInit() {
		if(!pageUrlPkCouverture.dejaInitialise) {
			_pageUrlPk(pageUrlPkCouverture);
			if(pageUrlPk == null)
				setPageUrlPk(pageUrlPkCouverture.o);
		}
		pageUrlPkCouverture.dejaInitialise(true);
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

	////////////////
	// pageUrlApi //
	////////////////

	/**	 L'entité pageUrlApi
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageUrlApi;
	@JsonIgnore
	public Couverture<String> pageUrlApiCouverture = new Couverture<String>().p(this).c(String.class).var("pageUrlApi").o(pageUrlApi);

	/**	<br/> L'entité pageUrlApi
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUrlApi">Trouver l'entité pageUrlApi dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrlApi(Couverture<String> c);

	public String getPageUrlApi() {
		return pageUrlApi;
	}

	public void setPageUrlApi(String pageUrlApi) {
		this.pageUrlApi = pageUrlApi;
		this.pageUrlApiCouverture.dejaInitialise = true;
	}
	protected Cluster pageUrlApiInit() {
		if(!pageUrlApiCouverture.dejaInitialise) {
			_pageUrlApi(pageUrlApiCouverture);
			if(pageUrlApi == null)
				setPageUrlApi(pageUrlApiCouverture.o);
		}
		pageUrlApiCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrPageUrlApi() {
		return pageUrlApi;
	}

	public String strPageUrlApi() {
		return pageUrlApi == null ? "" : pageUrlApi;
	}

	public String jsonPageUrlApi() {
		return pageUrlApi == null ? "" : pageUrlApi;
	}

	public String nomAffichagePageUrlApi() {
		return null;
	}

	public String htmTooltipPageUrlApi() {
		return null;
	}

	public String htmPageUrlApi() {
		return pageUrlApi == null ? "" : StringEscapeUtils.escapeHtml4(strPageUrlApi());
	}

	////////////
	// pageH1 //
	////////////

	/**	 L'entité pageH1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageH1;
	@JsonIgnore
	public Couverture<String> pageH1Couverture = new Couverture<String>().p(this).c(String.class).var("pageH1").o(pageH1);

	/**	<br/> L'entité pageH1
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageH1">Trouver l'entité pageH1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageH1(Couverture<String> c);

	public String getPageH1() {
		return pageH1;
	}

	public void setPageH1(String pageH1) {
		this.pageH1 = pageH1;
		this.pageH1Couverture.dejaInitialise = true;
	}
	protected Cluster pageH1Init() {
		if(!pageH1Couverture.dejaInitialise) {
			_pageH1(pageH1Couverture);
			if(pageH1 == null)
				setPageH1(pageH1Couverture.o);
		}
		pageH1Couverture.dejaInitialise(true);
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
	// initLoin //
	//////////////

	protected boolean dejaInitialiseCluster = false;

	public Cluster initLoinCluster(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseCluster) {
			dejaInitialiseCluster = true;
			initLoinCluster();
		}
		return (Cluster)this;
	}

	public void initLoinCluster() {
		initCluster();
	}

	public void initCluster() {
		requeteSite_Init();
		pagePartsInit();
		pkInit();
		inheritPkInit();
		idInit();
		creeInit();
		modifieInit();
		archiveInit();
		supprimeInit();
		classeNomCanoniqueInit();
		classeNomSimpleInit();
		classeNomsCanoniquesInit();
		sessionIdInit();
		utilisateurIdInit();
		utilisateurCleInit();
		sauvegardesInit();
		objetTitreInit();
		objetIdInit();
		objetNomVarInit();
		objetSuggereInit();
		objetTexteInit();
		pageUrlIdInit();
		pageUrlPkInit();
		pageUrlApiInit();
		pageH1Init();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinCluster(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteCluster(RequeteSiteFrFR requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteCluster(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirCluster(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirCluster(String var) {
		Cluster oCluster = (Cluster)this;
		switch(var) {
			case "requeteSite_":
				return oCluster.requeteSite_;
			case "pageParts":
				return oCluster.pageParts;
			case "pk":
				return oCluster.pk;
			case "inheritPk":
				return oCluster.inheritPk;
			case "id":
				return oCluster.id;
			case "cree":
				return oCluster.cree;
			case "modifie":
				return oCluster.modifie;
			case "archive":
				return oCluster.archive;
			case "supprime":
				return oCluster.supprime;
			case "classeNomCanonique":
				return oCluster.classeNomCanonique;
			case "classeNomSimple":
				return oCluster.classeNomSimple;
			case "classeNomsCanoniques":
				return oCluster.classeNomsCanoniques;
			case "sessionId":
				return oCluster.sessionId;
			case "utilisateurId":
				return oCluster.utilisateurId;
			case "utilisateurCle":
				return oCluster.utilisateurCle;
			case "sauvegardes":
				return oCluster.sauvegardes;
			case "objetTitre":
				return oCluster.objetTitre;
			case "objetId":
				return oCluster.objetId;
			case "objetNomVar":
				return oCluster.objetNomVar;
			case "objetSuggere":
				return oCluster.objetSuggere;
			case "objetTexte":
				return oCluster.objetTexte;
			case "pageUrlId":
				return oCluster.pageUrlId;
			case "pageUrlPk":
				return oCluster.pageUrlPk;
			case "pageUrlApi":
				return oCluster.pageUrlApi;
			case "pageH1":
				return oCluster.pageH1;
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerCluster(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerCluster(String var, Object val) {
		Cluster oCluster = (Cluster)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirCluster(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirCluster(String var, String val) {
		switch(var) {
			case "inheritPk":
				if(val != null)
					setInheritPk(val);
				sauvegardes.add(var);
				return val;
			case "cree":
				if(val != null)
					setCree(val);
				sauvegardes.add(var);
				return val;
			case "modifie":
				if(val != null)
					setModifie(val);
				sauvegardes.add(var);
				return val;
			case "archive":
				if(val != null)
					setArchive(val);
				sauvegardes.add(var);
				return val;
			case "supprime":
				if(val != null)
					setSupprime(val);
				sauvegardes.add(var);
				return val;
			case "sessionId":
				if(val != null)
					setSessionId(val);
				sauvegardes.add(var);
				return val;
			case "utilisateurId":
				if(val != null)
					setUtilisateurId(val);
				sauvegardes.add(var);
				return val;
			case "utilisateurCle":
				if(val != null)
					setUtilisateurCle(val);
				sauvegardes.add(var);
				return val;
			default:
				return null;
		}
	}

	/////////////
	// peupler //
	/////////////

	public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerCluster(solrDocument);
	}
	public void peuplerCluster(SolrDocument solrDocument) {
		Cluster oCluster = (Cluster)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			Long pk = (Long)solrDocument.get("pk_stored_long");
			oCluster.setPk(pk);

			if(sauvegardes.contains("inheritPk")) {
				Long inheritPk = (Long)solrDocument.get("inheritPk_stored_long");
				if(inheritPk != null)
					oCluster.setInheritPk(inheritPk);
			}

			String id = (String)solrDocument.get("id");
			oCluster.setId(id);

			if(sauvegardes.contains("cree")) {
				Date cree = (Date)solrDocument.get("cree_stored_date");
				if(cree != null)
					oCluster.setCree(cree);
			}

			if(sauvegardes.contains("modifie")) {
				Date modifie = (Date)solrDocument.get("modifie_stored_date");
				if(modifie != null)
					oCluster.setModifie(modifie);
			}

			if(sauvegardes.contains("archive")) {
				Boolean archive = (Boolean)solrDocument.get("archive_stored_boolean");
				if(archive != null)
					oCluster.setArchive(archive);
			}

			if(sauvegardes.contains("supprime")) {
				Boolean supprime = (Boolean)solrDocument.get("supprime_stored_boolean");
				if(supprime != null)
					oCluster.setSupprime(supprime);
			}

			if(sauvegardes.contains("classeNomCanonique")) {
				String classeNomCanonique = (String)solrDocument.get("classeNomCanonique_stored_string");
				if(classeNomCanonique != null)
					oCluster.setClasseNomCanonique(classeNomCanonique);
			}

			if(sauvegardes.contains("classeNomSimple")) {
				String classeNomSimple = (String)solrDocument.get("classeNomSimple_stored_string");
				if(classeNomSimple != null)
					oCluster.setClasseNomSimple(classeNomSimple);
			}

			if(sauvegardes.contains("classeNomsCanoniques")) {
				List<String> classeNomsCanoniques = (List<String>)solrDocument.get("classeNomsCanoniques_stored_strings");
				if(classeNomsCanoniques != null)
					oCluster.classeNomsCanoniques.addAll(classeNomsCanoniques);
			}

			if(sauvegardes.contains("sessionId")) {
				String sessionId = (String)solrDocument.get("sessionId_stored_string");
				if(sessionId != null)
					oCluster.setSessionId(sessionId);
			}

			if(sauvegardes.contains("utilisateurId")) {
				String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
				if(utilisateurId != null)
					oCluster.setUtilisateurId(utilisateurId);
			}

			if(sauvegardes.contains("utilisateurCle")) {
				Long utilisateurCle = (Long)solrDocument.get("utilisateurCle_stored_long");
				if(utilisateurCle != null)
					oCluster.setUtilisateurCle(utilisateurCle);
			}

			if(sauvegardes.contains("sauvegardes")) {
				List<String> sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
				if(sauvegardes != null)
					oCluster.sauvegardes.addAll(sauvegardes);
			}

			if(sauvegardes.contains("objetTitre")) {
				String objetTitre = (String)solrDocument.get("objetTitre_stored_string");
				if(objetTitre != null)
					oCluster.setObjetTitre(objetTitre);
			}

			if(sauvegardes.contains("objetId")) {
				String objetId = (String)solrDocument.get("objetId_stored_string");
				if(objetId != null)
					oCluster.setObjetId(objetId);
			}

			if(sauvegardes.contains("objetSuggere")) {
				String objetSuggere = (String)solrDocument.get("objetSuggere_suggested");
				oCluster.setObjetSuggere(objetSuggere);
			}

			if(sauvegardes.contains("pageUrlId")) {
				String pageUrlId = (String)solrDocument.get("pageUrlId_stored_string");
				if(pageUrlId != null)
					oCluster.setPageUrlId(pageUrlId);
			}

			if(sauvegardes.contains("pageUrlPk")) {
				String pageUrlPk = (String)solrDocument.get("pageUrlPk_stored_string");
				if(pageUrlPk != null)
					oCluster.setPageUrlPk(pageUrlPk);
			}

			if(sauvegardes.contains("pageUrlApi")) {
				String pageUrlApi = (String)solrDocument.get("pageUrlApi_stored_string");
				if(pageUrlApi != null)
					oCluster.setPageUrlApi(pageUrlApi);
			}
		}
	}

	/////////////
	// indexer //
	/////////////

	public static void indexer() {
		try {
			RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.getConfigSite().setConfigChemin("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			SolrQuery rechercheSolr = new SolrQuery();
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.cluster.Cluster"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			Cluster o = new Cluster();
			o.requeteSiteCluster(requeteSite);
			o.initLoinCluster(requeteSite);
			o.indexerCluster();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	public void indexerPourClasse() {
		indexerCluster();
	}

	public void indexerPourClasse(SolrInputDocument document) {
		indexerCluster(document);
	}

	public void indexerCluster(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerCluster(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerCluster() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerCluster(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerCluster(SolrInputDocument document) {
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
		if(cree != null) {
			document.addField("cree_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(cree.toInstant(), ZoneId.of("UTC"))));
			document.addField("cree_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(cree.toInstant(), ZoneId.of("UTC"))));
		}
		if(modifie != null) {
			document.addField("modifie_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(modifie.toInstant(), ZoneId.of("UTC"))));
			document.addField("modifie_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(modifie.toInstant(), ZoneId.of("UTC"))));
		}
		if(archive != null) {
			document.addField("archive_indexed_boolean", archive);
			document.addField("archive_stored_boolean", archive);
		}
		if(supprime != null) {
			document.addField("supprime_indexed_boolean", supprime);
			document.addField("supprime_stored_boolean", supprime);
		}
		if(classeNomCanonique != null) {
			document.addField("classeNomCanonique_indexed_string", classeNomCanonique);
			document.addField("classeNomCanonique_stored_string", classeNomCanonique);
		}
		if(classeNomSimple != null) {
			document.addField("classeNomSimple_indexed_string", classeNomSimple);
			document.addField("classeNomSimple_stored_string", classeNomSimple);
		}
		if(classeNomsCanoniques != null) {
			for(java.lang.String o : classeNomsCanoniques) {
				document.addField("classeNomsCanoniques_indexed_strings", o);
			}
			for(java.lang.String o : classeNomsCanoniques) {
				document.addField("classeNomsCanoniques_stored_strings", o);
			}
		}
		if(sessionId != null) {
			document.addField("sessionId_indexed_string", sessionId);
			document.addField("sessionId_stored_string", sessionId);
		}
		if(utilisateurId != null) {
			document.addField("utilisateurId_indexed_string", utilisateurId);
			document.addField("utilisateurId_stored_string", utilisateurId);
		}
		if(utilisateurCle != null) {
			document.addField("utilisateurCle_indexed_long", utilisateurCle);
			document.addField("utilisateurCle_stored_long", utilisateurCle);
		}
		if(sauvegardes != null) {
			for(java.lang.String o : sauvegardes) {
				document.addField("sauvegardes_indexed_strings", o);
			}
			for(java.lang.String o : sauvegardes) {
				document.addField("sauvegardes_stored_strings", o);
			}
		}
		if(objetTitre != null) {
			document.addField("objetTitre_indexed_string", objetTitre);
			document.addField("objetTitre_stored_string", objetTitre);
		}
		if(objetId != null) {
			document.addField("objetId_indexed_string", objetId);
			document.addField("objetId_stored_string", objetId);
		}
		if(objetSuggere != null) {
			document.addField("objetSuggere_suggested", objetSuggere);
		}
		if(objetTexte != null) {
			document.addField("objetTexte_text_frFR", objetTexte.toString());
			document.addField("objetTexte_indexed_string", objetTexte);
		}
		if(pageUrlId != null) {
			document.addField("pageUrlId_indexed_string", pageUrlId);
			document.addField("pageUrlId_stored_string", pageUrlId);
		}
		if(pageUrlPk != null) {
			document.addField("pageUrlPk_indexed_string", pageUrlPk);
			document.addField("pageUrlPk_stored_string", pageUrlPk);
		}
		if(pageUrlApi != null) {
			document.addField("pageUrlApi_indexed_string", pageUrlApi);
			document.addField("pageUrlApi_stored_string", pageUrlApi);
		}
	}

	public void desindexerCluster() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinCluster(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeCluster(String entiteVar) {
		switch(entiteVar) {
			case "pk":
				return "pk_indexed_long";
			case "inheritPk":
				return "inheritPk_indexed_long";
			case "id":
				return "id_indexed_string";
			case "cree":
				return "cree_indexed_date";
			case "modifie":
				return "modifie_indexed_date";
			case "archive":
				return "archive_indexed_boolean";
			case "supprime":
				return "supprime_indexed_boolean";
			case "classeNomCanonique":
				return "classeNomCanonique_indexed_string";
			case "classeNomSimple":
				return "classeNomSimple_indexed_string";
			case "classeNomsCanoniques":
				return "classeNomsCanoniques_indexed_strings";
			case "sessionId":
				return "sessionId_indexed_string";
			case "utilisateurId":
				return "utilisateurId_indexed_string";
			case "utilisateurCle":
				return "utilisateurCle_indexed_long";
			case "sauvegardes":
				return "sauvegardes_indexed_strings";
			case "objetTitre":
				return "objetTitre_indexed_string";
			case "objetId":
				return "objetId_indexed_string";
			case "objetSuggere":
				return "objetSuggere_indexed_string";
			case "objetTexte":
				return "objetTexte_indexed_string";
			case "pageUrlId":
				return "pageUrlId_indexed_string";
			case "pageUrlPk":
				return "pageUrlPk_indexed_string";
			case "pageUrlApi":
				return "pageUrlApi_indexed_string";
			default:
				return null;
		}
	}

	public static String varRechercheCluster(String entiteVar) {
		switch(entiteVar) {
			case "objetTexte":
				return "objetTexte_text_frFR";
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				return null;
		}
	}

	public static String varSuggereCluster(String entiteVar) {
		switch(entiteVar) {
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				return null;
		}
	}

	/////////////
	// stocker //
	/////////////

	public void stockerPourClasse(SolrDocument solrDocument) {
		stockerCluster(solrDocument);
	}
	public void stockerCluster(SolrDocument solrDocument) {
		Cluster oCluster = (Cluster)this;

		Long pk = (Long)solrDocument.get("pk_stored_long");
		if(pk != null)
			oCluster.setPk(pk);

		Long inheritPk = (Long)solrDocument.get("inheritPk_stored_long");
		if(inheritPk != null)
			oCluster.setInheritPk(inheritPk);

		String id = (String)solrDocument.get("id");
		oCluster.setId(id);

		Date cree = (Date)solrDocument.get("cree_stored_date");
		if(cree != null)
			oCluster.setCree(cree);

		Date modifie = (Date)solrDocument.get("modifie_stored_date");
		if(modifie != null)
			oCluster.setModifie(modifie);

		Boolean archive = (Boolean)solrDocument.get("archive_stored_boolean");
		if(archive != null)
			oCluster.setArchive(archive);

		Boolean supprime = (Boolean)solrDocument.get("supprime_stored_boolean");
		if(supprime != null)
			oCluster.setSupprime(supprime);

		String classeNomCanonique = (String)solrDocument.get("classeNomCanonique_stored_string");
		if(classeNomCanonique != null)
			oCluster.setClasseNomCanonique(classeNomCanonique);

		String classeNomSimple = (String)solrDocument.get("classeNomSimple_stored_string");
		if(classeNomSimple != null)
			oCluster.setClasseNomSimple(classeNomSimple);

		List<String> classeNomsCanoniques = (List<String>)solrDocument.get("classeNomsCanoniques_stored_strings");
		if(classeNomsCanoniques != null)
			oCluster.classeNomsCanoniques.addAll(classeNomsCanoniques);

		String sessionId = (String)solrDocument.get("sessionId_stored_string");
		if(sessionId != null)
			oCluster.setSessionId(sessionId);

		String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
		if(utilisateurId != null)
			oCluster.setUtilisateurId(utilisateurId);

		Long utilisateurCle = (Long)solrDocument.get("utilisateurCle_stored_long");
		if(utilisateurCle != null)
			oCluster.setUtilisateurCle(utilisateurCle);

		List<String> sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null)
			oCluster.sauvegardes.addAll(sauvegardes);

		String objetTitre = (String)solrDocument.get("objetTitre_stored_string");
		if(objetTitre != null)
			oCluster.setObjetTitre(objetTitre);

		String objetId = (String)solrDocument.get("objetId_stored_string");
		if(objetId != null)
			oCluster.setObjetId(objetId);

		String objetSuggere = (String)solrDocument.get("objetSuggere_suggested");
		oCluster.setObjetSuggere(objetSuggere);

		String objetTexte = (String)solrDocument.get("objetTexte_stored_string");
		if(objetTexte != null)
			oCluster.setObjetTexte(objetTexte);

		String pageUrlId = (String)solrDocument.get("pageUrlId_stored_string");
		if(pageUrlId != null)
			oCluster.setPageUrlId(pageUrlId);

		String pageUrlPk = (String)solrDocument.get("pageUrlPk_stored_string");
		if(pageUrlPk != null)
			oCluster.setPageUrlPk(pageUrlPk);

		String pageUrlApi = (String)solrDocument.get("pageUrlApi_stored_string");
		if(pageUrlApi != null)
			oCluster.setPageUrlApi(pageUrlApi);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiCluster() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof Cluster) {
			Cluster original = (Cluster)o;
			if(!Objects.equals(pk, original.getPk()))
				requeteApi.addVars("pk");
			if(!Objects.equals(inheritPk, original.getInheritPk()))
				requeteApi.addVars("inheritPk");
			if(!Objects.equals(id, original.getId()))
				requeteApi.addVars("id");
			if(!Objects.equals(cree, original.getCree()))
				requeteApi.addVars("cree");
			if(!Objects.equals(modifie, original.getModifie()))
				requeteApi.addVars("modifie");
			if(!Objects.equals(archive, original.getArchive()))
				requeteApi.addVars("archive");
			if(!Objects.equals(supprime, original.getSupprime()))
				requeteApi.addVars("supprime");
			if(!Objects.equals(classeNomCanonique, original.getClasseNomCanonique()))
				requeteApi.addVars("classeNomCanonique");
			if(!Objects.equals(classeNomSimple, original.getClasseNomSimple()))
				requeteApi.addVars("classeNomSimple");
			if(!Objects.equals(classeNomsCanoniques, original.getClasseNomsCanoniques()))
				requeteApi.addVars("classeNomsCanoniques");
			if(!Objects.equals(sessionId, original.getSessionId()))
				requeteApi.addVars("sessionId");
			if(!Objects.equals(utilisateurId, original.getUtilisateurId()))
				requeteApi.addVars("utilisateurId");
			if(!Objects.equals(utilisateurCle, original.getUtilisateurCle()))
				requeteApi.addVars("utilisateurCle");
			if(!Objects.equals(sauvegardes, original.getSauvegardes()))
				requeteApi.addVars("sauvegardes");
			if(!Objects.equals(objetTitre, original.getObjetTitre()))
				requeteApi.addVars("objetTitre");
			if(!Objects.equals(objetId, original.getObjetId()))
				requeteApi.addVars("objetId");
			if(!Objects.equals(objetSuggere, original.getObjetSuggere()))
				requeteApi.addVars("objetSuggere");
			if(!Objects.equals(objetTexte, original.getObjetTexte()))
				requeteApi.addVars("objetTexte");
			if(!Objects.equals(pageUrlId, original.getPageUrlId()))
				requeteApi.addVars("pageUrlId");
			if(!Objects.equals(pageUrlPk, original.getPageUrlPk()))
				requeteApi.addVars("pageUrlPk");
			if(!Objects.equals(pageUrlApi, original.getPageUrlApi()))
				requeteApi.addVars("pageUrlApi");
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(pk, inheritPk, id, cree, modifie, archive, supprime, classeNomCanonique, classeNomSimple, classeNomsCanoniques, sessionId, utilisateurId, utilisateurCle, sauvegardes, objetTitre, objetId, objetSuggere, objetTexte, pageUrlId, pageUrlPk, pageUrlApi);
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
		return Objects.equals( pk, that.pk )
				&& Objects.equals( inheritPk, that.inheritPk )
				&& Objects.equals( id, that.id )
				&& Objects.equals( cree, that.cree )
				&& Objects.equals( modifie, that.modifie )
				&& Objects.equals( archive, that.archive )
				&& Objects.equals( supprime, that.supprime )
				&& Objects.equals( classeNomCanonique, that.classeNomCanonique )
				&& Objects.equals( classeNomSimple, that.classeNomSimple )
				&& Objects.equals( classeNomsCanoniques, that.classeNomsCanoniques )
				&& Objects.equals( sessionId, that.sessionId )
				&& Objects.equals( utilisateurId, that.utilisateurId )
				&& Objects.equals( utilisateurCle, that.utilisateurCle )
				&& Objects.equals( sauvegardes, that.sauvegardes )
				&& Objects.equals( objetTitre, that.objetTitre )
				&& Objects.equals( objetId, that.objetId )
				&& Objects.equals( objetSuggere, that.objetSuggere )
				&& Objects.equals( objetTexte, that.objetTexte )
				&& Objects.equals( pageUrlId, that.pageUrlId )
				&& Objects.equals( pageUrlPk, that.pageUrlPk )
				&& Objects.equals( pageUrlApi, that.pageUrlApi );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cluster { ");
		sb.append( "pk: " ).append(pk);
		sb.append( ", inheritPk: " ).append(inheritPk);
		sb.append( ", id: \"" ).append(id).append( "\"" );
		sb.append( ", cree: " ).append(cree);
		sb.append( ", modifie: " ).append(modifie);
		sb.append( ", archive: " ).append(archive);
		sb.append( ", supprime: " ).append(supprime);
		sb.append( ", classeNomCanonique: \"" ).append(classeNomCanonique).append( "\"" );
		sb.append( ", classeNomSimple: \"" ).append(classeNomSimple).append( "\"" );
		sb.append( ", classeNomsCanoniques: " ).append(classeNomsCanoniques);
		sb.append( ", sessionId: \"" ).append(sessionId).append( "\"" );
		sb.append( ", utilisateurId: \"" ).append(utilisateurId).append( "\"" );
		sb.append( ", utilisateurCle: " ).append(utilisateurCle);
		sb.append( ", sauvegardes: " ).append(sauvegardes);
		sb.append( ", objetTitre: \"" ).append(objetTitre).append( "\"" );
		sb.append( ", objetId: \"" ).append(objetId).append( "\"" );
		sb.append( ", objetSuggere: \"" ).append(objetSuggere).append( "\"" );
		sb.append( ", objetTexte: \"" ).append(objetTexte).append( "\"" );
		sb.append( ", pageUrlId: \"" ).append(pageUrlId).append( "\"" );
		sb.append( ", pageUrlPk: \"" ).append(pageUrlPk).append( "\"" );
		sb.append( ", pageUrlApi: \"" ).append(pageUrlApi).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
