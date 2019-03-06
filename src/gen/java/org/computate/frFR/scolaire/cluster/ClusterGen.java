package org.computate.frFR.scolaire.cluster;

import org.computate.frFR.scolaire.cluster.Cluster;
import java.util.Date;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import org.computate.frFR.scolaire.page.MiseEnPage;
import java.util.ArrayList;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import org.computate.frFR.scolaire.couverture.Couverture;
import java.lang.Long;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import java.time.Instant;
import java.time.ZoneId;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import java.time.format.DateTimeFormatter;
import io.vertx.ext.sql.SQLConnection;
import java.lang.Object;
import io.vertx.ext.sql.SQLClient;
import org.computate.frFR.scolaire.requete.RequeteSite;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ClusterGen<DEV> extends Object {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cluster.class);

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSite requeteSite_;
	public Couverture<RequeteSite> requeteSite_Couverture = new Couverture<RequeteSite>().p(this).c(RequeteSite.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSite> c);

	public RequeteSite getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSite requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
	}

	///////////
	// page_ //
	///////////

	/**	L'entité « page_ »
	 *	 is defined as null before being initialized. 
	 */
	protected MiseEnPage page_;
	public Couverture<MiseEnPage> page_Couverture = new Couverture<MiseEnPage>().p(this).c(MiseEnPage.class).var("page_").o(page_);

	/**	<br/>L'entité « page_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:page_">Trouver l'entité page_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _page_(Couverture<MiseEnPage> c);

	public MiseEnPage getPage_() {
		return page_;
	}

	public void setPage_(MiseEnPage page_) {
		this.page_ = page_;
		this.page_Couverture.dejaInitialise = true;
	}

	////////
	// pk //
	////////

	/**	L'entité « pk »
	 *	 is defined as null before being initialized. 
	 */
	protected Long pk;
	public Couverture<Long> pkCouverture = new Couverture<Long>().p(this).c(Long.class).var("pk").o(pk);

	/**	<br/>L'entité « pk »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pk">Trouver l'entité pk dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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

	public String nomAffichagePk() {
		return "cl\u00E9 primaire";
	}

	public String htmTooltipPk() {
		return null;
	}

	public String htmPk() {
		return pk == null ? "" : StringEscapeUtils.escapeHtml4(strPk());
	}

	public void htmPk(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "Pk\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "Pk() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setPk\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePk()), "</span>");
				r.s("			<input");
							r.s(" name=\"pk\"");
							r.s(" value=\"", htmPk(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPk());
			}
			r.l("</div>");
		}
	}

	////////
	// id //
	////////

	/**	L'entité « id »
	 *	 is defined as null before being initialized. 
	 */
	protected String id;
	public Couverture<String> idCouverture = new Couverture<String>().p(this).c(String.class).var("id").o(id);

	/**	<br/>L'entité « id »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:id">Trouver l'entité id dans Solr</a>
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

	public String nomAffichageId() {
		return null;
	}

	public String htmTooltipId() {
		return null;
	}

	public String htmId() {
		return id == null ? "" : StringEscapeUtils.escapeHtml4(strId());
	}

	public void htmId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "Id\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "Id() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageId()), "</span>");
				r.s("			<input");
							r.s(" name=\"id\"");
							r.s(" value=\"", htmId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmId());
			}
			r.l("</div>");
		}
	}

	//////////////
	// supprime //
	//////////////

	/**	L'entité « supprime »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean supprime;
	public Couverture<Boolean> supprimeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("supprime").o(supprime);

	/**	<br/>L'entité « supprime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:supprime">Trouver l'entité supprime dans Solr</a>
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
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
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

	public String nomAffichageSupprime() {
		return "Supprim\u00E9";
	}

	public String htmTooltipSupprime() {
		return null;
	}

	public String htmSupprime() {
		return supprime == null ? "" : StringEscapeUtils.escapeHtml4(strSupprime());
	}

	public void htmSupprime(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "Supprime\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "Supprime() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSupprime\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSupprime()), "</span>");
				r.s("			<input");
							r.s(" name=\"supprime\"");
							r.s(" value=\"", htmSupprime(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSupprime());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// utilisateurId //
	///////////////////

	/**	L'entité « utilisateurId »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurId;
	public Couverture<String> utilisateurIdCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurId").o(utilisateurId);

	/**	<br/>L'entité « utilisateurId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurId">Trouver l'entité utilisateurId dans Solr</a>
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

	public String nomAffichageUtilisateurId() {
		return null;
	}

	public String htmTooltipUtilisateurId() {
		return null;
	}

	public String htmUtilisateurId() {
		return utilisateurId == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurId());
	}

	public void htmUtilisateurId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "UtilisateurId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "UtilisateurId() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setUtilisateurId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurId()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurId\"");
							r.s(" value=\"", htmUtilisateurId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurId());
			}
			r.l("</div>");
		}
	}

	//////////
	// cree //
	//////////

	/**	L'entité « cree »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDateTime cree;
	public Couverture<LocalDateTime> creeCouverture = new Couverture<LocalDateTime>().p(this).c(LocalDateTime.class).var("cree").o(cree);

	/**	<br/>L'entité « cree »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cree">Trouver l'entité cree dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cree(Couverture<LocalDateTime> c);

	public LocalDateTime getCree() {
		return cree;
	}

	public void setCree(LocalDateTime cree) {
		this.cree = cree;
		this.creeCouverture.dejaInitialise = true;
	}
	public Cluster setCree(Instant o) {
		this.cree = LocalDateTime.from(o);
		this.creeCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public Cluster setCree(String o) {
		this.cree = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this.creeCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	public Cluster setCree(Date o) {
		this.cree = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());
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
		return cree == null ? null : Date.from(cree.atZone(ZoneId.systemDefault()).toInstant());
	}

	public String strCree() {
		return cree == null ? "" : cree.toString();
	}

	public String nomAffichageCree() {
		return "Cr\u00E9e";
	}

	public String htmTooltipCree() {
		return null;
	}

	public String htmCree() {
		return cree == null ? "" : StringEscapeUtils.escapeHtml4(strCree());
	}

	public void htmCree(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "Cree\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "Cree() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setCree\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageCree()), "</span>");
				r.s("			<input");
							r.s(" name=\"cree\"");
							r.s(" value=\"", htmCree(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmCree());
			}
			r.l("</div>");
		}
	}

	/////////////
	// modifie //
	/////////////

	/**	L'entité « modifie »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDateTime modifie;
	public Couverture<LocalDateTime> modifieCouverture = new Couverture<LocalDateTime>().p(this).c(LocalDateTime.class).var("modifie").o(modifie);

	/**	<br/>L'entité « modifie »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modifie">Trouver l'entité modifie dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modifie(Couverture<LocalDateTime> c);

	public LocalDateTime getModifie() {
		return modifie;
	}

	public void setModifie(LocalDateTime modifie) {
		this.modifie = modifie;
		this.modifieCouverture.dejaInitialise = true;
	}
	public Cluster setModifie(Instant o) {
		this.modifie = LocalDateTime.from(o);
		this.modifieCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public Cluster setModifie(String o) {
		this.modifie = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this.modifieCouverture.dejaInitialise = true;
		return (Cluster)this;
	}
	public Cluster setModifie(Date o) {
		this.modifie = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());
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
		return modifie == null ? null : Date.from(modifie.atZone(ZoneId.systemDefault()).toInstant());
	}

	public String strModifie() {
		return modifie == null ? "" : modifie.toString();
	}

	public String nomAffichageModifie() {
		return "Modifi\u00E9";
	}

	public String htmTooltipModifie() {
		return null;
	}

	public String htmModifie() {
		return modifie == null ? "" : StringEscapeUtils.escapeHtml4(strModifie());
	}

	public void htmModifie(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "Modifie\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "Modifie() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setModifie\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModifie()), "</span>");
				r.s("			<input");
							r.s(" name=\"modifie\"");
							r.s(" value=\"", htmModifie(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModifie());
			}
			r.l("</div>");
		}
	}

	/////////////////////////
	// clusterNomCanonique //
	/////////////////////////

	/**	L'entité « clusterNomCanonique »
	 *	 is defined as null before being initialized. 
	 */
	protected String clusterNomCanonique;
	public Couverture<String> clusterNomCanoniqueCouverture = new Couverture<String>().p(this).c(String.class).var("clusterNomCanonique").o(clusterNomCanonique);

	/**	<br/>L'entité « clusterNomCanonique »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clusterNomCanonique">Trouver l'entité clusterNomCanonique dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clusterNomCanonique(Couverture<String> c);

	public String getClusterNomCanonique() {
		return clusterNomCanonique;
	}

	public void setClusterNomCanonique(String clusterNomCanonique) {
		this.clusterNomCanonique = clusterNomCanonique;
		this.clusterNomCanoniqueCouverture.dejaInitialise = true;
	}
	protected Cluster clusterNomCanoniqueInit() {
		if(!clusterNomCanoniqueCouverture.dejaInitialise) {
			_clusterNomCanonique(clusterNomCanoniqueCouverture);
			if(clusterNomCanonique == null)
				setClusterNomCanonique(clusterNomCanoniqueCouverture.o);
		}
		clusterNomCanoniqueCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrClusterNomCanonique() {
		return clusterNomCanonique;
	}

	public String strClusterNomCanonique() {
		return clusterNomCanonique == null ? "" : clusterNomCanonique;
	}

	public String nomAffichageClusterNomCanonique() {
		return null;
	}

	public String htmTooltipClusterNomCanonique() {
		return null;
	}

	public String htmClusterNomCanonique() {
		return clusterNomCanonique == null ? "" : StringEscapeUtils.escapeHtml4(strClusterNomCanonique());
	}

	public void htmClusterNomCanonique(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "ClusterNomCanonique\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "ClusterNomCanonique() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setClusterNomCanonique\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageClusterNomCanonique()), "</span>");
				r.s("			<input");
							r.s(" name=\"clusterNomCanonique\"");
							r.s(" value=\"", htmClusterNomCanonique(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmClusterNomCanonique());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// clusterNomSimple //
	//////////////////////

	/**	L'entité « clusterNomSimple »
	 *	 is defined as null before being initialized. 
	 */
	protected String clusterNomSimple;
	public Couverture<String> clusterNomSimpleCouverture = new Couverture<String>().p(this).c(String.class).var("clusterNomSimple").o(clusterNomSimple);

	/**	<br/>L'entité « clusterNomSimple »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.cluster.Cluster&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clusterNomSimple">Trouver l'entité clusterNomSimple dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clusterNomSimple(Couverture<String> c);

	public String getClusterNomSimple() {
		return clusterNomSimple;
	}

	public void setClusterNomSimple(String clusterNomSimple) {
		this.clusterNomSimple = clusterNomSimple;
		this.clusterNomSimpleCouverture.dejaInitialise = true;
	}
	protected Cluster clusterNomSimpleInit() {
		if(!clusterNomSimpleCouverture.dejaInitialise) {
			_clusterNomSimple(clusterNomSimpleCouverture);
			if(clusterNomSimple == null)
				setClusterNomSimple(clusterNomSimpleCouverture.o);
		}
		clusterNomSimpleCouverture.dejaInitialise(true);
		return (Cluster)this;
	}

	public String solrClusterNomSimple() {
		return clusterNomSimple;
	}

	public String strClusterNomSimple() {
		return clusterNomSimple == null ? "" : clusterNomSimple;
	}

	public String nomAffichageClusterNomSimple() {
		return null;
	}

	public String htmTooltipClusterNomSimple() {
		return null;
	}

	public String htmClusterNomSimple() {
		return clusterNomSimple == null ? "" : StringEscapeUtils.escapeHtml4(strClusterNomSimple());
	}

	public void htmClusterNomSimple(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchCluster", strPk(), "ClusterNomSimple\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchCluster", strPk(), "ClusterNomSimple() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/warfarin/cluster?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setClusterNomSimple\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageClusterNomSimple()), "</span>");
				r.s("			<input");
							r.s(" name=\"clusterNomSimple\"");
							r.s(" value=\"", htmClusterNomSimple(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmClusterNomSimple());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseCluster = false;

	public Cluster initLoinCluster(RequeteSite requeteSite_) {
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
		pkInit();
		idInit();
		supprimeInit();
		utilisateurIdInit();
		creeInit();
		modifieInit();
		clusterNomCanoniqueInit();
		clusterNomSimpleInit();
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinCluster(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteCluster(RequeteSite requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteCluster(requeteSite_);
	}

	/////////////
	// indexer //
	/////////////

	public static void indexer() {
		try {
			RequeteSite requeteSite = new RequeteSite();
			requeteSite.initLoinRequeteSite();
			SiteContexte siteContexte = new SiteContexte();
			siteContexte.getConfigSite().setConfigChemin("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContexte.initLoinSiteContexte();
			siteContexte.setRequeteSite_(requeteSite);
			requeteSite.setSiteContexte_(siteContexte);
			Cluster o = new Cluster();
			o.requeteSiteCluster(requeteSite);
			o.initLoinCluster(requeteSite);
			o.indexerCluster();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	public void indexerPourClasse() throws Exception {
		indexerCluster();
	}

	public void indexerPourClasse(SolrInputDocument document) throws Exception {
		indexerCluster(document);
	}

	public void indexerCluster(SolrClient clientSolr) throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerCluster(document);
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerCluster() throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerCluster(document);
		SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerCluster(SolrInputDocument document) throws Exception {
		if(sauvegardesCluster != null)
			document.addField("sauvegardesCluster_stored_strings", sauvegardesCluster);

		if(pk != null) {
			document.addField("pk_indexed_long", pk);
			document.addField("pk_stored_long", pk);
		}
		if(id != null) {
			document.addField("id", id);
		}
		if(utilisateurId != null) {
			document.addField("utilisateurId_indexed_string", utilisateurId);
			document.addField("utilisateurId_stored_string", utilisateurId);
		}
		if(cree != null) {
			document.addField("cree_indexed_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(cree));
			document.addField("cree_stored_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(cree));
		}
		if(modifie != null) {
			document.addField("modifie_indexed_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(modifie));
			document.addField("modifie_stored_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(modifie));
		}
		if(clusterNomCanonique != null) {
			document.addField("clusterNomCanonique_indexed_string", clusterNomCanonique);
			document.addField("clusterNomCanonique_stored_string", clusterNomCanonique);
		}
		if(clusterNomSimple != null) {
			document.addField("clusterNomSimple_indexed_string", clusterNomSimple);
			document.addField("clusterNomSimple_stored_string", clusterNomSimple);
		}
	}

	public void desindexerCluster() throws Exception {
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.initLoinRequeteSite();
		SiteContexte siteContexte = new SiteContexte();
		siteContexte.initLoinSiteContexte();
		siteContexte.setRequeteSite_(requeteSite);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		initLoinCluster(siteContexte.getRequeteSite_());
		SolrClient clientSolr = siteContexte.getClientSolr();
		clientSolr.deleteById(id.toString());
		clientSolr.commit();
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
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
	public Object obtenirCluster(String var) throws Exception {
		Cluster oCluster = (Cluster)this;
		switch(var) {
			case "requeteSite_":
				return oCluster.requeteSite_;
			case "page_":
				return oCluster.page_;
			case "pk":
				return oCluster.pk;
			case "id":
				return oCluster.id;
			case "supprime":
				return oCluster.supprime;
			case "utilisateurId":
				return oCluster.utilisateurId;
			case "cree":
				return oCluster.cree;
			case "modifie":
				return oCluster.modifie;
			case "clusterNomCanonique":
				return oCluster.clusterNomCanonique;
			case "clusterNomSimple":
				return oCluster.clusterNomSimple;
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
			case "pk":
				setPk(val);
				sauvegardesCluster.add(var);
				return val;
			case "id":
				setId(val);
				sauvegardesCluster.add(var);
				return val;
			case "supprime":
				setSupprime(val);
				sauvegardesCluster.add(var);
				return val;
			case "utilisateurId":
				setUtilisateurId(val);
				sauvegardesCluster.add(var);
				return val;
			case "cree":
				setCree(val);
				sauvegardesCluster.add(var);
				return val;
			case "modifie":
				setModifie(val);
				sauvegardesCluster.add(var);
				return val;
			case "clusterNomCanonique":
				setClusterNomCanonique(val);
				sauvegardesCluster.add(var);
				return val;
			case "clusterNomSimple":
				setClusterNomSimple(val);
				sauvegardesCluster.add(var);
				return val;
			default:
				return null;
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesCluster = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerCluster(solrDocument);
	}
	public void peuplerCluster(SolrDocument solrDocument) {
		Cluster oCluster = (Cluster)this;
		sauvegardesCluster = (List<String>)solrDocument.get("sauvegardesCluster_stored_strings");
		if(sauvegardesCluster != null) {

			Long pk = (Long)solrDocument.get("pk_stored_long");
			oCluster.setPk(pk);

			String id = (String)solrDocument.get("id");
			oCluster.setId(id);

			if(sauvegardesCluster.contains("utilisateurId")) {
				String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
				if(utilisateurId != null)
					oCluster.setUtilisateurId(utilisateurId);
			}

			if(sauvegardesCluster.contains("cree")) {
				Date cree = (Date)solrDocument.get("cree_stored_date");
				if(cree != null)
					oCluster.setCree(cree);
			}

			if(sauvegardesCluster.contains("modifie")) {
				Date modifie = (Date)solrDocument.get("modifie_stored_date");
				if(modifie != null)
					oCluster.setModifie(modifie);
			}

			if(sauvegardesCluster.contains("clusterNomCanonique")) {
				String clusterNomCanonique = (String)solrDocument.get("clusterNomCanonique_stored_string");
				if(clusterNomCanonique != null)
					oCluster.setClusterNomCanonique(clusterNomCanonique);
			}

			if(sauvegardesCluster.contains("clusterNomSimple")) {
				String clusterNomSimple = (String)solrDocument.get("clusterNomSimple_stored_string");
				if(clusterNomSimple != null)
					oCluster.setClusterNomSimple(clusterNomSimple);
			}
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

		String id = (String)solrDocument.get("id");
		oCluster.setId(id);

		String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
		if(utilisateurId != null)
			oCluster.setUtilisateurId(utilisateurId);

		Date cree = (Date)solrDocument.get("cree_stored_date");
		if(cree != null)
			oCluster.setCree(cree);

		Date modifie = (Date)solrDocument.get("modifie_stored_date");
		if(modifie != null)
			oCluster.setModifie(modifie);

		String clusterNomCanonique = (String)solrDocument.get("clusterNomCanonique_stored_string");
		if(clusterNomCanonique != null)
			oCluster.setClusterNomCanonique(clusterNomCanonique);

		String clusterNomSimple = (String)solrDocument.get("clusterNomSimple_stored_string");
		if(clusterNomSimple != null)
			oCluster.setClusterNomSimple(clusterNomSimple);
	}

	//////////
	// html //
	//////////

	public void html() {
		htmlCluster();
	}

	public void htmlCluster() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(pk, id, supprime, utilisateurId, cree, modifie, clusterNomCanonique, clusterNomSimple);
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
				&& Objects.equals( id, that.id )
				&& Objects.equals( supprime, that.supprime )
				&& Objects.equals( utilisateurId, that.utilisateurId )
				&& Objects.equals( cree, that.cree )
				&& Objects.equals( modifie, that.modifie )
				&& Objects.equals( clusterNomCanonique, that.clusterNomCanonique )
				&& Objects.equals( clusterNomSimple, that.clusterNomSimple );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cluster {");
		sb.append( "pk: " ).append(pk);
		sb.append( ", id: \"" ).append(id).append( "\"" );
		sb.append( ", supprime: " ).append(supprime);
		sb.append( ", utilisateurId: \"" ).append(utilisateurId).append( "\"" );
		sb.append( ", cree: " ).append(cree);
		sb.append( ", modifie: " ).append(modifie);
		sb.append( ", clusterNomCanonique: \"" ).append(clusterNomCanonique).append( "\"" );
		sb.append( ", clusterNomSimple: \"" ).append(clusterNomSimple).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
