package org.computate.scolaire.frFR.requete.patch;

import java.math.MathContext;
import java.util.Date;
import java.time.ZonedDateTime;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.time.LocalDateTime;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.time.Instant;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import java.lang.Long;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.lang.Object;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class RequetePatchGen<DEV> extends Object {

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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
	protected RequetePatch requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (RequetePatch)this;
	}

	//////////
	// cree //
	//////////

	/**	L'entité « cree »
	 *	 is defined as null before being initialized. 
	 */
	protected ZonedDateTime cree;
	@JsonIgnore
	public Couverture<ZonedDateTime> creeCouverture = new Couverture<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("cree").o(cree);

	/**	<br/>L'entité « cree »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cree">Trouver l'entité cree dans Solr</a>
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
	public RequetePatch setCree(Instant o) {
		this.cree = ZonedDateTime.from(o);
		this.creeCouverture.dejaInitialise = true;
		return (RequetePatch)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public RequetePatch setCree(String o) {
		this.cree = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this.creeCouverture.dejaInitialise = true;
		return (RequetePatch)this;
	}
	public RequetePatch setCree(Date o) {
		this.cree = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(requeteSite_.getConfigSite_().getSiteZone()));
		this.creeCouverture.dejaInitialise = true;
		return (RequetePatch)this;
	}
	protected RequetePatch creeInit() {
		if(!creeCouverture.dejaInitialise) {
			_cree(creeCouverture);
			if(cree == null)
				setCree(creeCouverture.o);
		}
		creeCouverture.dejaInitialise(true);
		return (RequetePatch)this;
	}

	public Date solrCree() {
		return cree == null ? null : Date.from(cree.toInstant());
	}

	public String strCree() {
		return cree == null ? "" : cree.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy H'h'mm:ss zz", Locale.FRANCE));
	}

	public String jsonCree() {
		return cree == null ? "" : cree.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy H'h'mm:ss.SSS zz VV", Locale.FRANCE));
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

	/**	L'entité « rows »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer rows;
	@JsonIgnore
	public Couverture<Integer> rowsCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("rows").o(rows);

	/**	<br/>L'entité « rows »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:rows">Trouver l'entité rows dans Solr</a>
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
	public RequetePatch setRows(String o) {
		if(NumberUtils.isParsable(o))
			this.rows = Integer.parseInt(o);
		this.rowsCouverture.dejaInitialise = true;
		return (RequetePatch)this;
	}
	protected RequetePatch rowsInit() {
		if(!rowsCouverture.dejaInitialise) {
			_rows(rowsCouverture);
			if(rows == null)
				setRows(rowsCouverture.o);
		}
		rowsCouverture.dejaInitialise(true);
		return (RequetePatch)this;
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
	public Couverture<Long> numFoundCouverture = new Couverture<Long>().p(this).c(Long.class).var("numFound").o(numFound);

	/**	<br/>L'entité « numFound »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:numFound">Trouver l'entité numFound dans Solr</a>
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
	public RequetePatch setNumFound(String o) {
		if(NumberUtils.isParsable(o))
			this.numFound = Long.parseLong(o);
		this.numFoundCouverture.dejaInitialise = true;
		return (RequetePatch)this;
	}
	protected RequetePatch numFoundInit() {
		if(!numFoundCouverture.dejaInitialise) {
			_numFound(numFoundCouverture);
			if(numFound == null)
				setNumFound(numFoundCouverture.o);
		}
		numFoundCouverture.dejaInitialise(true);
		return (RequetePatch)this;
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
	public Couverture<Long> numPATCHCouverture = new Couverture<Long>().p(this).c(Long.class).var("numPATCH").o(numPATCH);

	/**	<br/>L'entité « numPATCH »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:numPATCH">Trouver l'entité numPATCH dans Solr</a>
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
	public RequetePatch setNumPATCH(String o) {
		if(NumberUtils.isParsable(o))
			this.numPATCH = Long.parseLong(o);
		this.numPATCHCouverture.dejaInitialise = true;
		return (RequetePatch)this;
	}
	protected RequetePatch numPATCHInit() {
		if(!numPATCHCouverture.dejaInitialise) {
			_numPATCH(numPATCHCouverture);
			if(numPATCH == null)
				setNumPATCH(numPATCHCouverture.o);
		}
		numPATCHCouverture.dejaInitialise(true);
		return (RequetePatch)this;
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
	public Couverture<String> uuidCouverture = new Couverture<String>().p(this).c(String.class).var("uuid").o(uuid);

	/**	<br/>L'entité « uuid »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:uuid">Trouver l'entité uuid dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _uuid(Couverture<String> c);

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
		this.uuidCouverture.dejaInitialise = true;
	}
	protected RequetePatch uuidInit() {
		if(!uuidCouverture.dejaInitialise) {
			_uuid(uuidCouverture);
			if(uuid == null)
				setUuid(uuidCouverture.o);
		}
		uuidCouverture.dejaInitialise(true);
		return (RequetePatch)this;
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
	public Couverture<String> idCouverture = new Couverture<String>().p(this).c(String.class).var("id").o(id);

	/**	<br/>L'entité « id »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.requete.patch.RequetePatch&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:id">Trouver l'entité id dans Solr</a>
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
	protected RequetePatch idInit() {
		if(!idCouverture.dejaInitialise) {
			_id(idCouverture);
			if(id == null)
				setId(idCouverture.o);
		}
		idCouverture.dejaInitialise(true);
		return (RequetePatch)this;
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
	// initLoin //
	//////////////

	protected boolean dejaInitialiseRequetePatch = false;

	public RequetePatch initLoinRequetePatch(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseRequetePatch) {
			dejaInitialiseRequetePatch = true;
			initLoinRequetePatch();
		}
		return (RequetePatch)this;
	}

	public void initLoinRequetePatch() {
		initRequetePatch();
	}

	public void initRequetePatch() {
		requeteSite_Init();
		creeInit();
		rowsInit();
		numFoundInit();
		numPATCHInit();
		uuidInit();
		idInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinRequetePatch(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteRequetePatch(RequeteSiteFrFR requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteRequetePatch(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirRequetePatch(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirRequetePatch(String var) {
		RequetePatch oRequetePatch = (RequetePatch)this;
		switch(var) {
			case "requeteSite_":
				return oRequetePatch.requeteSite_;
			case "cree":
				return oRequetePatch.cree;
			case "rows":
				return oRequetePatch.rows;
			case "numFound":
				return oRequetePatch.numFound;
			case "numPATCH":
				return oRequetePatch.numPATCH;
			case "uuid":
				return oRequetePatch.uuid;
			case "id":
				return oRequetePatch.id;
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
				o = attribuerRequetePatch(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerRequetePatch(String var, Object val) {
		RequetePatch oRequetePatch = (RequetePatch)this;
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
					o = definirRequetePatch(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRequetePatch(String var, String val) {
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
		if(!(o instanceof RequetePatch))
			return false;
		RequetePatch that = (RequetePatch)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RequetePatch { ");
		sb.append(" }");
		return sb.toString();
	}
}
