package org.computate.scolaire.frFR.requete.api;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class RequeteApiGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(RequeteApi.class);

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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
	public static RequeteSiteFrFR staticSetRequeteSite_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected RequeteApi requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (RequeteApi)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cree">Trouver l'entité cree dans Solr</a>
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
	public void setCree(Instant o) {
		this.cree = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
		this.creeCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public void setCree(String o) {
		this.cree = RequeteApi.staticSetCree(requeteSite_, o);
		this.creeCouverture.dejaInitialise = true;
	}
	public static ZonedDateTime staticSetCree(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : ZonedDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone()))).truncatedTo(ChronoUnit.MILLIS);
	}
	public void setCree(Date o) {
		this.cree = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		this.creeCouverture.dejaInitialise = true;
	}
	protected RequeteApi creeInit() {
		if(!creeCouverture.dejaInitialise) {
			_cree(creeCouverture);
			if(cree == null)
				setCree(creeCouverture.o);
		}
		creeCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static Date staticSolrCree(RequeteSiteFrFR requeteSite_, ZonedDateTime o) {
		return o == null ? null : Date.from(o.toInstant());
	}

	public static String staticSolrStrCree(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqCree(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrCree(requeteSite_, RequeteApi.staticSolrCree(requeteSite_, RequeteApi.staticSetCree(requeteSite_, o)));
	}

	public Date solrCree() {
		return RequeteApi.staticSolrCree(requeteSite_, cree);
	}

	public String strCree() {
		return cree == null ? "" : cree.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy H'h'mm:ss zz VV", Locale.forLanguageTag("fr-FR")));
	}

	public OffsetDateTime sqlCree() {
		return cree == null ? null : cree.toOffsetDateTime();
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

	//////////
	// rows //
	//////////

	/**	 L'entité rows
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer rows;
	@JsonIgnore
	public Couverture<Integer> rowsCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("rows").o(rows);

	/**	<br/> L'entité rows
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:rows">Trouver l'entité rows dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _rows(Couverture<Integer> c);

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
		this.rowsCouverture.dejaInitialise = true;
	}
	public void setRows(String o) {
		this.rows = RequeteApi.staticSetRows(requeteSite_, o);
		this.rowsCouverture.dejaInitialise = true;
	}
	public static Integer staticSetRows(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected RequeteApi rowsInit() {
		if(!rowsCouverture.dejaInitialise) {
			_rows(rowsCouverture);
			if(rows == null)
				setRows(rowsCouverture.o);
		}
		rowsCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static Integer staticSolrRows(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrRows(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqRows(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrRows(requeteSite_, RequeteApi.staticSolrRows(requeteSite_, RequeteApi.staticSetRows(requeteSite_, o)));
	}

	public Integer solrRows() {
		return RequeteApi.staticSolrRows(requeteSite_, rows);
	}

	public String strRows() {
		return rows == null ? "" : rows.toString();
	}

	public Integer sqlRows() {
		return rows;
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

	/**	 L'entité numFound
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long numFound;
	@JsonIgnore
	public Couverture<Long> numFoundCouverture = new Couverture<Long>().p(this).c(Long.class).var("numFound").o(numFound);

	/**	<br/> L'entité numFound
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:numFound">Trouver l'entité numFound dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _numFound(Couverture<Long> c);

	public Long getNumFound() {
		return numFound;
	}

	public void setNumFound(Long numFound) {
		this.numFound = numFound;
		this.numFoundCouverture.dejaInitialise = true;
	}
	public void setNumFound(String o) {
		this.numFound = RequeteApi.staticSetNumFound(requeteSite_, o);
		this.numFoundCouverture.dejaInitialise = true;
	}
	public static Long staticSetNumFound(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected RequeteApi numFoundInit() {
		if(!numFoundCouverture.dejaInitialise) {
			_numFound(numFoundCouverture);
			if(numFound == null)
				setNumFound(numFoundCouverture.o);
		}
		numFoundCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static Long staticSolrNumFound(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrNumFound(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqNumFound(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrNumFound(requeteSite_, RequeteApi.staticSolrNumFound(requeteSite_, RequeteApi.staticSetNumFound(requeteSite_, o)));
	}

	public Long solrNumFound() {
		return RequeteApi.staticSolrNumFound(requeteSite_, numFound);
	}

	public String strNumFound() {
		return numFound == null ? "" : numFound.toString();
	}

	public Long sqlNumFound() {
		return numFound;
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

	/**	 L'entité numPATCH
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long numPATCH;
	@JsonIgnore
	public Couverture<Long> numPATCHCouverture = new Couverture<Long>().p(this).c(Long.class).var("numPATCH").o(numPATCH);

	/**	<br/> L'entité numPATCH
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:numPATCH">Trouver l'entité numPATCH dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _numPATCH(Couverture<Long> c);

	public Long getNumPATCH() {
		return numPATCH;
	}

	public void setNumPATCH(Long numPATCH) {
		this.numPATCH = numPATCH;
		this.numPATCHCouverture.dejaInitialise = true;
	}
	public void setNumPATCH(String o) {
		this.numPATCH = RequeteApi.staticSetNumPATCH(requeteSite_, o);
		this.numPATCHCouverture.dejaInitialise = true;
	}
	public static Long staticSetNumPATCH(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected RequeteApi numPATCHInit() {
		if(!numPATCHCouverture.dejaInitialise) {
			_numPATCH(numPATCHCouverture);
			if(numPATCH == null)
				setNumPATCH(numPATCHCouverture.o);
		}
		numPATCHCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static Long staticSolrNumPATCH(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrNumPATCH(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqNumPATCH(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrNumPATCH(requeteSite_, RequeteApi.staticSolrNumPATCH(requeteSite_, RequeteApi.staticSetNumPATCH(requeteSite_, o)));
	}

	public Long solrNumPATCH() {
		return RequeteApi.staticSolrNumPATCH(requeteSite_, numPATCH);
	}

	public String strNumPATCH() {
		return numPATCH == null ? "" : numPATCH.toString();
	}

	public Long sqlNumPATCH() {
		return numPATCH;
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

	/**	 L'entité uuid
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String uuid;
	@JsonIgnore
	public Couverture<String> uuidCouverture = new Couverture<String>().p(this).c(String.class).var("uuid").o(uuid);

	/**	<br/> L'entité uuid
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:uuid">Trouver l'entité uuid dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _uuid(Couverture<String> c);

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String o) {
		this.uuid = RequeteApi.staticSetUuid(requeteSite_, o);
		this.uuidCouverture.dejaInitialise = true;
	}
	public static String staticSetUuid(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected RequeteApi uuidInit() {
		if(!uuidCouverture.dejaInitialise) {
			_uuid(uuidCouverture);
			if(uuid == null)
				setUuid(uuidCouverture.o);
		}
		uuidCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static String staticSolrUuid(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrUuid(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUuid(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrUuid(requeteSite_, RequeteApi.staticSolrUuid(requeteSite_, RequeteApi.staticSetUuid(requeteSite_, o)));
	}

	public String solrUuid() {
		return RequeteApi.staticSolrUuid(requeteSite_, uuid);
	}

	public String strUuid() {
		return uuid == null ? "" : uuid;
	}

	public String sqlUuid() {
		return uuid;
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

	/**	 L'entité id
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String id;
	@JsonIgnore
	public Couverture<String> idCouverture = new Couverture<String>().p(this).c(String.class).var("id").o(id);

	/**	<br/> L'entité id
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:id">Trouver l'entité id dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _id(Couverture<String> c);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = RequeteApi.staticSetId(requeteSite_, o);
		this.idCouverture.dejaInitialise = true;
	}
	public static String staticSetId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected RequeteApi idInit() {
		if(!idCouverture.dejaInitialise) {
			_id(idCouverture);
			if(id == null)
				setId(idCouverture.o);
		}
		idCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static String staticSolrId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrId(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqId(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrId(requeteSite_, RequeteApi.staticSolrId(requeteSite_, RequeteApi.staticSetId(requeteSite_, o)));
	}

	public String solrId() {
		return RequeteApi.staticSolrId(requeteSite_, id);
	}

	public String strId() {
		return id == null ? "" : id;
	}

	public String sqlId() {
		return id;
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

	/**	 L'entité empty
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean empty;
	@JsonIgnore
	public Couverture<Boolean> emptyCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("empty").o(empty);

	/**	<br/> L'entité empty
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:empty">Trouver l'entité empty dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _empty(Couverture<Boolean> c);

	public Boolean getEmpty() {
		return empty;
	}

	public void setEmpty(Boolean empty) {
		this.empty = empty;
		this.emptyCouverture.dejaInitialise = true;
	}
	public void setEmpty(String o) {
		this.empty = RequeteApi.staticSetEmpty(requeteSite_, o);
		this.emptyCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetEmpty(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected RequeteApi emptyInit() {
		if(!emptyCouverture.dejaInitialise) {
			_empty(emptyCouverture);
			if(empty == null)
				setEmpty(emptyCouverture.o);
		}
		emptyCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static Boolean staticSolrEmpty(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEmpty(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmpty(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrEmpty(requeteSite_, RequeteApi.staticSolrEmpty(requeteSite_, RequeteApi.staticSetEmpty(requeteSite_, o)));
	}

	public Boolean solrEmpty() {
		return RequeteApi.staticSolrEmpty(requeteSite_, empty);
	}

	public String strEmpty() {
		return empty == null ? "" : empty.toString();
	}

	public Boolean sqlEmpty() {
		return empty;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pk">Trouver l'entité pk dans Solr</a>
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
	public void setPk(String o) {
		this.pk = RequeteApi.staticSetPk(requeteSite_, o);
		this.pkCouverture.dejaInitialise = true;
	}
	public static Long staticSetPk(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected RequeteApi pkInit() {
		if(!pkCouverture.dejaInitialise) {
			_pk(pkCouverture);
			if(pk == null)
				setPk(pkCouverture.o);
		}
		pkCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static Long staticSolrPk(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrPk(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPk(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrPk(requeteSite_, RequeteApi.staticSolrPk(requeteSite_, RequeteApi.staticSetPk(requeteSite_, o)));
	}

	public Long solrPk() {
		return RequeteApi.staticSolrPk(requeteSite_, pk);
	}

	public String strPk() {
		return pk == null ? "" : pk.toString();
	}

	public Long sqlPk() {
		return pk;
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

	/**	 L'entité original
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected Object original;
	@JsonIgnore
	public Couverture<Object> originalCouverture = new Couverture<Object>().p(this).c(Object.class).var("original").o(original);

	/**	<br/> L'entité original
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:original">Trouver l'entité original dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _original(Couverture<Object> c);

	public Object getOriginal() {
		return original;
	}

	public void setOriginal(Object original) {
		this.original = original;
		this.originalCouverture.dejaInitialise = true;
	}
	public static Object staticSetOriginal(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected RequeteApi originalInit() {
		if(!originalCouverture.dejaInitialise) {
			_original(originalCouverture);
			if(original == null)
				setOriginal(originalCouverture.o);
		}
		originalCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	/////////
	// pks //
	/////////

	/**	 L'entité pks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> pks = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> pksCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("pks").o(pks);

	/**	<br/> L'entité pks
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pks">Trouver l'entité pks dans Solr</a>
	 * <br/>
	 * @param pks est l'entité déjà construit. 
	 **/
	protected abstract void _pks(List<Long> c);

	public List<Long> getPks() {
		return pks;
	}

	public void setPks(List<Long> pks) {
		this.pks = pks;
		this.pksCouverture.dejaInitialise = true;
	}
	public void setPks(String o) {
		Long l = RequeteApi.staticSetPks(requeteSite_, o);
		if(l != null)
			addPks(l);
		this.pksCouverture.dejaInitialise = true;
	}
	public static Long staticSetPks(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public RequeteApi addPks(Long...objets) {
		for(Long o : objets) {
			addPks(o);
		}
		return (RequeteApi)this;
	}
	public RequeteApi addPks(Long o) {
		if(o != null && !pks.contains(o))
			this.pks.add(o);
		return (RequeteApi)this;
	}
	public void setPks(JsonArray objets) {
		pks.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPks(o);
		}
	}
	public RequeteApi addPks(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPks(p);
		}
		return (RequeteApi)this;
	}
	protected RequeteApi pksInit() {
		if(!pksCouverture.dejaInitialise) {
			_pks(pks);
		}
		pksCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static Long staticSolrPks(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrPks(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPks(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrPks(requeteSite_, RequeteApi.staticSolrPks(requeteSite_, RequeteApi.staticSetPks(requeteSite_, o)));
	}

	public List<Long> solrPks() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : pks) {
			l.add(RequeteApi.staticSolrPks(requeteSite_, o));
		}
		return l;
	}

	public String strPks() {
		return pks == null ? "" : pks.toString();
	}

	public List<Long> sqlPks() {
		return pks;
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

	/**	 L'entité classes
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classes = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> classesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("classes").o(classes);

	/**	<br/> L'entité classes
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:classes">Trouver l'entité classes dans Solr</a>
	 * <br/>
	 * @param classes est l'entité déjà construit. 
	 **/
	protected abstract void _classes(List<String> c);

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
		this.classesCouverture.dejaInitialise = true;
	}
	public static String staticSetClasses(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	public RequeteApi addClasses(String...objets) {
		for(String o : objets) {
			addClasses(o);
		}
		return (RequeteApi)this;
	}
	public RequeteApi addClasses(String o) {
		if(o != null && !classes.contains(o))
			this.classes.add(o);
		return (RequeteApi)this;
	}
	public void setClasses(JsonArray objets) {
		classes.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClasses(o);
		}
	}
	protected RequeteApi classesInit() {
		if(!classesCouverture.dejaInitialise) {
			_classes(classes);
		}
		classesCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static String staticSolrClasses(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrClasses(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqClasses(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrClasses(requeteSite_, RequeteApi.staticSolrClasses(requeteSite_, RequeteApi.staticSetClasses(requeteSite_, o)));
	}

	public List<String> solrClasses() {
		List<String> l = new ArrayList<String>();
		for(String o : classes) {
			l.add(RequeteApi.staticSolrClasses(requeteSite_, o));
		}
		return l;
	}

	public String strClasses() {
		return classes == null ? "" : classes.toString();
	}

	public List<String> sqlClasses() {
		return classes;
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

	/**	 L'entité vars
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> vars = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> varsCouverture = new Couverture<List<String>>().p(this).c(List.class).var("vars").o(vars);

	/**	<br/> L'entité vars
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.api.RequeteApi&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vars">Trouver l'entité vars dans Solr</a>
	 * <br/>
	 * @param vars est l'entité déjà construit. 
	 **/
	protected abstract void _vars(List<String> c);

	public List<String> getVars() {
		return vars;
	}

	public void setVars(List<String> vars) {
		this.vars = vars;
		this.varsCouverture.dejaInitialise = true;
	}
	public static String staticSetVars(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	public RequeteApi addVars(String...objets) {
		for(String o : objets) {
			addVars(o);
		}
		return (RequeteApi)this;
	}
	public RequeteApi addVars(String o) {
		if(o != null && !vars.contains(o))
			this.vars.add(o);
		return (RequeteApi)this;
	}
	public void setVars(JsonArray objets) {
		vars.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addVars(o);
		}
	}
	protected RequeteApi varsInit() {
		if(!varsCouverture.dejaInitialise) {
			_vars(vars);
		}
		varsCouverture.dejaInitialise(true);
		return (RequeteApi)this;
	}

	public static String staticSolrVars(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrVars(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqVars(RequeteSiteFrFR requeteSite_, String o) {
		return RequeteApi.staticSolrStrVars(requeteSite_, RequeteApi.staticSolrVars(requeteSite_, RequeteApi.staticSetVars(requeteSite_, o)));
	}

	public List<String> solrVars() {
		List<String> l = new ArrayList<String>();
		for(String o : vars) {
			l.add(RequeteApi.staticSolrVars(requeteSite_, o));
		}
		return l;
	}

	public String strVars() {
		return vars == null ? "" : vars.toString();
	}

	public List<String> sqlVars() {
		return vars;
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
	// initLoin //
	//////////////

	protected boolean dejaInitialiseRequeteApi = false;

	public RequeteApi initLoinRequeteApi(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseRequeteApi) {
			dejaInitialiseRequeteApi = true;
			initLoinRequeteApi();
		}
		return (RequeteApi)this;
	}

	public void initLoinRequeteApi() {
		initRequeteApi();
	}

	public void initRequeteApi() {
		requeteSite_Init();
		creeInit();
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

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinRequeteApi(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteRequeteApi(RequeteSiteFrFR requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteRequeteApi(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirRequeteApi(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirRequeteApi(String var) {
		RequeteApi oRequeteApi = (RequeteApi)this;
		switch(var) {
			case "requeteSite_":
				return oRequeteApi.requeteSite_;
			case "cree":
				return oRequeteApi.cree;
			case "rows":
				return oRequeteApi.rows;
			case "numFound":
				return oRequeteApi.numFound;
			case "numPATCH":
				return oRequeteApi.numPATCH;
			case "uuid":
				return oRequeteApi.uuid;
			case "id":
				return oRequeteApi.id;
			case "empty":
				return oRequeteApi.empty;
			case "pk":
				return oRequeteApi.pk;
			case "original":
				return oRequeteApi.original;
			case "pks":
				return oRequeteApi.pks;
			case "classes":
				return oRequeteApi.classes;
			case "vars":
				return oRequeteApi.vars;
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
				o = attribuerRequeteApi(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerRequeteApi(String var, Object val) {
		RequeteApi oRequeteApi = (RequeteApi)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetRequeteApi(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetRequeteApi(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "cree":
			return RequeteApi.staticSetCree(requeteSite_, o);
		case "rows":
			return RequeteApi.staticSetRows(requeteSite_, o);
		case "numFound":
			return RequeteApi.staticSetNumFound(requeteSite_, o);
		case "numPATCH":
			return RequeteApi.staticSetNumPATCH(requeteSite_, o);
		case "uuid":
			return RequeteApi.staticSetUuid(requeteSite_, o);
		case "id":
			return RequeteApi.staticSetId(requeteSite_, o);
		case "empty":
			return RequeteApi.staticSetEmpty(requeteSite_, o);
		case "pk":
			return RequeteApi.staticSetPk(requeteSite_, o);
		case "pks":
			return RequeteApi.staticSetPks(requeteSite_, o);
		case "classes":
			return RequeteApi.staticSetClasses(requeteSite_, o);
		case "vars":
			return RequeteApi.staticSetVars(requeteSite_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrRequeteApi(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrRequeteApi(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "cree":
			return RequeteApi.staticSolrCree(requeteSite_, (ZonedDateTime)o);
		case "rows":
			return RequeteApi.staticSolrRows(requeteSite_, (Integer)o);
		case "numFound":
			return RequeteApi.staticSolrNumFound(requeteSite_, (Long)o);
		case "numPATCH":
			return RequeteApi.staticSolrNumPATCH(requeteSite_, (Long)o);
		case "uuid":
			return RequeteApi.staticSolrUuid(requeteSite_, (String)o);
		case "id":
			return RequeteApi.staticSolrId(requeteSite_, (String)o);
		case "empty":
			return RequeteApi.staticSolrEmpty(requeteSite_, (Boolean)o);
		case "pk":
			return RequeteApi.staticSolrPk(requeteSite_, (Long)o);
		case "pks":
			return RequeteApi.staticSolrPks(requeteSite_, (Long)o);
		case "classes":
			return RequeteApi.staticSolrClasses(requeteSite_, (String)o);
		case "vars":
			return RequeteApi.staticSolrVars(requeteSite_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrRequeteApi(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrRequeteApi(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "cree":
			return RequeteApi.staticSolrStrCree(requeteSite_, (Date)o);
		case "rows":
			return RequeteApi.staticSolrStrRows(requeteSite_, (Integer)o);
		case "numFound":
			return RequeteApi.staticSolrStrNumFound(requeteSite_, (Long)o);
		case "numPATCH":
			return RequeteApi.staticSolrStrNumPATCH(requeteSite_, (Long)o);
		case "uuid":
			return RequeteApi.staticSolrStrUuid(requeteSite_, (String)o);
		case "id":
			return RequeteApi.staticSolrStrId(requeteSite_, (String)o);
		case "empty":
			return RequeteApi.staticSolrStrEmpty(requeteSite_, (Boolean)o);
		case "pk":
			return RequeteApi.staticSolrStrPk(requeteSite_, (Long)o);
		case "pks":
			return RequeteApi.staticSolrStrPks(requeteSite_, (Long)o);
		case "classes":
			return RequeteApi.staticSolrStrClasses(requeteSite_, (String)o);
		case "vars":
			return RequeteApi.staticSolrStrVars(requeteSite_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqRequeteApi(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqRequeteApi(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "cree":
			return RequeteApi.staticSolrFqCree(requeteSite_, o);
		case "rows":
			return RequeteApi.staticSolrFqRows(requeteSite_, o);
		case "numFound":
			return RequeteApi.staticSolrFqNumFound(requeteSite_, o);
		case "numPATCH":
			return RequeteApi.staticSolrFqNumPATCH(requeteSite_, o);
		case "uuid":
			return RequeteApi.staticSolrFqUuid(requeteSite_, o);
		case "id":
			return RequeteApi.staticSolrFqId(requeteSite_, o);
		case "empty":
			return RequeteApi.staticSolrFqEmpty(requeteSite_, o);
		case "pk":
			return RequeteApi.staticSolrFqPk(requeteSite_, o);
		case "pks":
			return RequeteApi.staticSolrFqPks(requeteSite_, o);
		case "classes":
			return RequeteApi.staticSolrFqClasses(requeteSite_, o);
		case "vars":
			return RequeteApi.staticSolrFqVars(requeteSite_, o);
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
					o = definirRequeteApi(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRequeteApi(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirRequeteApi(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRequeteApi(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiRequeteApi() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof RequeteApi) {
			RequeteApi original = (RequeteApi)o;
			if(!Objects.equals(cree, original.getCree()))
				requeteApi.addVars("cree");
			if(!Objects.equals(rows, original.getRows()))
				requeteApi.addVars("rows");
			if(!Objects.equals(numFound, original.getNumFound()))
				requeteApi.addVars("numFound");
			if(!Objects.equals(numPATCH, original.getNumPATCH()))
				requeteApi.addVars("numPATCH");
			if(!Objects.equals(uuid, original.getUuid()))
				requeteApi.addVars("uuid");
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(cree, rows, numFound, numPATCH, uuid);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof RequeteApi))
			return false;
		RequeteApi that = (RequeteApi)o;
		return Objects.equals( cree, that.cree )
				&& Objects.equals( rows, that.rows )
				&& Objects.equals( numFound, that.numFound )
				&& Objects.equals( numPATCH, that.numPATCH )
				&& Objects.equals( uuid, that.uuid );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RequeteApi { ");
		sb.append( "cree: " ).append(cree);
		sb.append( ", rows: " ).append(rows);
		sb.append( ", numFound: " ).append(numFound);
		sb.append( ", numPATCH: " ).append(numPATCH);
		sb.append( ", uuid: \"" ).append(uuid).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
