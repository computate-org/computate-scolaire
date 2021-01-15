package org.computate.scolaire.frFR.ecrivain;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.io.PrintWriter;
import java.math.MathContext;
import java.io.StringWriter;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.File;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import io.vertx.core.buffer.Buffer;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class ToutEcrivainGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ToutEcrivain.class);

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	 L'entité requeteSite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/> L'entité requeteSite_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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
	protected ToutEcrivain requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (ToutEcrivain)this;
	}

	////////////
	// tabStr //
	////////////

	/**	 L'entité tabStr
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String tabStr;
	@JsonIgnore
	public Couverture<String> tabStrCouverture = new Couverture<String>().p(this).c(String.class).var("tabStr").o(tabStr);

	/**	<br/> L'entité tabStr
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tabStr">Trouver l'entité tabStr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabStr(Couverture<String> c);

	public String getTabStr() {
		return tabStr;
	}
	public void setTabStr(String o) {
		this.tabStr = ToutEcrivain.staticSetTabStr(requeteSite_, o);
		this.tabStrCouverture.dejaInitialise = true;
	}
	public static String staticSetTabStr(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected ToutEcrivain tabStrInit() {
		if(!tabStrCouverture.dejaInitialise) {
			_tabStr(tabStrCouverture);
			if(tabStr == null)
				setTabStr(tabStrCouverture.o);
		}
		tabStrCouverture.dejaInitialise(true);
		return (ToutEcrivain)this;
	}

	public static String staticSolrTabStr(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrTabStr(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqTabStr(RequeteSiteFrFR requeteSite_, String o) {
		return ToutEcrivain.staticSolrStrTabStr(requeteSite_, ToutEcrivain.staticSolrTabStr(requeteSite_, ToutEcrivain.staticSetTabStr(requeteSite_, o)));
	}

	public String solrTabStr() {
		return ToutEcrivain.staticSolrTabStr(requeteSite_, tabStr);
	}

	public String strTabStr() {
		return tabStr == null ? "" : tabStr;
	}

	public String jsonTabStr() {
		return tabStr == null ? "" : tabStr;
	}

	public String nomAffichageTabStr() {
		return null;
	}

	public String htmTooltipTabStr() {
		return null;
	}

	public String htmTabStr() {
		return tabStr == null ? "" : StringEscapeUtils.escapeHtml4(strTabStr());
	}

	/////////////
	// fichier //
	/////////////

	/**	 L'entité fichier
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected File fichier;
	@JsonIgnore
	public Couverture<File> fichierCouverture = new Couverture<File>().p(this).c(File.class).var("fichier").o(fichier);

	/**	<br/> L'entité fichier
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fichier">Trouver l'entité fichier dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fichier(Couverture<File> c);

	public File getFichier() {
		return fichier;
	}

	public void setFichier(File fichier) {
		this.fichier = fichier;
		this.fichierCouverture.dejaInitialise = true;
	}
	public static File staticSetFichier(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ToutEcrivain fichierInit() {
		if(!fichierCouverture.dejaInitialise) {
			_fichier(fichierCouverture);
			if(fichier == null)
				setFichier(fichierCouverture.o);
		}
		fichierCouverture.dejaInitialise(true);
		return (ToutEcrivain)this;
	}

	////////////////////
	// ecrivainString //
	////////////////////

	/**	 L'entité ecrivainString
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected StringWriter ecrivainString;
	@JsonIgnore
	public Couverture<StringWriter> ecrivainStringCouverture = new Couverture<StringWriter>().p(this).c(StringWriter.class).var("ecrivainString").o(ecrivainString);

	/**	<br/> L'entité ecrivainString
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecrivainString">Trouver l'entité ecrivainString dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecrivainString(Couverture<StringWriter> c);

	public StringWriter getEcrivainString() {
		return ecrivainString;
	}

	public void setEcrivainString(StringWriter ecrivainString) {
		this.ecrivainString = ecrivainString;
		this.ecrivainStringCouverture.dejaInitialise = true;
	}
	public static StringWriter staticSetEcrivainString(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ToutEcrivain ecrivainStringInit() {
		if(!ecrivainStringCouverture.dejaInitialise) {
			_ecrivainString(ecrivainStringCouverture);
			if(ecrivainString == null)
				setEcrivainString(ecrivainStringCouverture.o);
		}
		ecrivainStringCouverture.dejaInitialise(true);
		return (ToutEcrivain)this;
	}

	////////////
	// buffer //
	////////////

	/**	 L'entité buffer
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Buffer buffer;
	@JsonIgnore
	public Couverture<Buffer> bufferCouverture = new Couverture<Buffer>().p(this).c(Buffer.class).var("buffer").o(buffer);

	/**	<br/> L'entité buffer
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:buffer">Trouver l'entité buffer dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _buffer(Couverture<Buffer> c);

	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
		this.bufferCouverture.dejaInitialise = true;
	}
	public static Buffer staticSetBuffer(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ToutEcrivain bufferInit() {
		if(!bufferCouverture.dejaInitialise) {
			_buffer(bufferCouverture);
			if(buffer == null)
				setBuffer(bufferCouverture.o);
		}
		bufferCouverture.dejaInitialise(true);
		return (ToutEcrivain)this;
	}

	////////////////////////
	// ecrivainImpression //
	////////////////////////

	/**	 L'entité ecrivainImpression
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PrintWriter ecrivainImpression;
	@JsonIgnore
	public Couverture<PrintWriter> ecrivainImpressionCouverture = new Couverture<PrintWriter>().p(this).c(PrintWriter.class).var("ecrivainImpression").o(ecrivainImpression);

	/**	<br/> L'entité ecrivainImpression
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecrivainImpression">Trouver l'entité ecrivainImpression dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecrivainImpression(Couverture<PrintWriter> c);

	public PrintWriter getEcrivainImpression() {
		return ecrivainImpression;
	}

	public void setEcrivainImpression(PrintWriter ecrivainImpression) {
		this.ecrivainImpression = ecrivainImpression;
		this.ecrivainImpressionCouverture.dejaInitialise = true;
	}
	public static PrintWriter staticSetEcrivainImpression(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected ToutEcrivain ecrivainImpressionInit() {
		if(!ecrivainImpressionCouverture.dejaInitialise) {
			_ecrivainImpression(ecrivainImpressionCouverture);
			if(ecrivainImpression == null)
				setEcrivainImpression(ecrivainImpressionCouverture.o);
		}
		ecrivainImpressionCouverture.dejaInitialise(true);
		return (ToutEcrivain)this;
	}

	//////////
	// vide //
	//////////

	/**	 L'entité vide
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean vide;
	@JsonIgnore
	public Couverture<Boolean> videCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("vide").o(vide);

	/**	<br/> L'entité vide
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vide">Trouver l'entité vide dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _vide(Couverture<Boolean> c);

	public Boolean getVide() {
		return vide;
	}

	public void setVide(Boolean vide) {
		this.vide = vide;
		this.videCouverture.dejaInitialise = true;
	}
	public void setVide(String o) {
		this.vide = ToutEcrivain.staticSetVide(requeteSite_, o);
		this.videCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetVide(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected ToutEcrivain videInit() {
		if(!videCouverture.dejaInitialise) {
			_vide(videCouverture);
			if(vide == null)
				setVide(videCouverture.o);
		}
		videCouverture.dejaInitialise(true);
		return (ToutEcrivain)this;
	}

	public static Boolean staticSolrVide(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrVide(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqVide(RequeteSiteFrFR requeteSite_, String o) {
		return ToutEcrivain.staticSolrStrVide(requeteSite_, ToutEcrivain.staticSolrVide(requeteSite_, ToutEcrivain.staticSetVide(requeteSite_, o)));
	}

	public Boolean solrVide() {
		return ToutEcrivain.staticSolrVide(requeteSite_, vide);
	}

	public String strVide() {
		return vide == null ? "" : vide.toString();
	}

	public String jsonVide() {
		return vide == null ? "" : vide.toString();
	}

	public String nomAffichageVide() {
		return null;
	}

	public String htmTooltipVide() {
		return null;
	}

	public String htmVide() {
		return vide == null ? "" : StringEscapeUtils.escapeHtml4(strVide());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseToutEcrivain = false;

	public ToutEcrivain initLoinToutEcrivain(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseToutEcrivain) {
			dejaInitialiseToutEcrivain = true;
			initLoinToutEcrivain();
		}
		return (ToutEcrivain)this;
	}

	public void initLoinToutEcrivain() {
		initToutEcrivain();
	}

	public void initToutEcrivain() {
		requeteSite_Init();
		tabStrInit();
		fichierInit();
		ecrivainStringInit();
		bufferInit();
		ecrivainImpressionInit();
		videInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinToutEcrivain(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteToutEcrivain(RequeteSiteFrFR requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteToutEcrivain(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirToutEcrivain(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirToutEcrivain(String var) {
		ToutEcrivain oToutEcrivain = (ToutEcrivain)this;
		switch(var) {
			case "requeteSite_":
				return oToutEcrivain.requeteSite_;
			case "tabStr":
				return oToutEcrivain.tabStr;
			case "fichier":
				return oToutEcrivain.fichier;
			case "ecrivainString":
				return oToutEcrivain.ecrivainString;
			case "buffer":
				return oToutEcrivain.buffer;
			case "ecrivainImpression":
				return oToutEcrivain.ecrivainImpression;
			case "vide":
				return oToutEcrivain.vide;
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
				o = attribuerToutEcrivain(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerToutEcrivain(String var, Object val) {
		ToutEcrivain oToutEcrivain = (ToutEcrivain)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetToutEcrivain(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetToutEcrivain(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "tabStr":
			return ToutEcrivain.staticSetTabStr(requeteSite_, o);
		case "vide":
			return ToutEcrivain.staticSetVide(requeteSite_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrToutEcrivain(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrToutEcrivain(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "tabStr":
			return ToutEcrivain.staticSolrTabStr(requeteSite_, (String)o);
		case "vide":
			return ToutEcrivain.staticSolrVide(requeteSite_, (Boolean)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrToutEcrivain(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrToutEcrivain(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "tabStr":
			return ToutEcrivain.staticSolrStrTabStr(requeteSite_, (String)o);
		case "vide":
			return ToutEcrivain.staticSolrStrVide(requeteSite_, (Boolean)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqToutEcrivain(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqToutEcrivain(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "tabStr":
			return ToutEcrivain.staticSolrFqTabStr(requeteSite_, o);
		case "vide":
			return ToutEcrivain.staticSolrFqVide(requeteSite_, o);
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
					o = definirToutEcrivain(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirToutEcrivain(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiToutEcrivain() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof ToutEcrivain) {
			ToutEcrivain original = (ToutEcrivain)o;
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
		if(!(o instanceof ToutEcrivain))
			return false;
		ToutEcrivain that = (ToutEcrivain)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ToutEcrivain { ");
		sb.append(" }");
		return sb.toString();
	}
}
