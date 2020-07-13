package org.computate.scolaire.frFR.ecrivain;

import java.io.PrintWriter;
import java.math.MathContext;
import java.io.StringWriter;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.io.File;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import io.vertx.core.buffer.Buffer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.lang.Boolean;
import java.lang.Object;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ToutEcrivainGen<DEV> extends Object {

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
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

	/**	L'entité « tabStr »
	 *	 is defined as null before being initialized. 
	 */
	protected String tabStr;
	@JsonIgnore
	public Couverture<String> tabStrCouverture = new Couverture<String>().p(this).c(String.class).var("tabStr").o(tabStr);

	/**	<br/>L'entité « tabStr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecrivain.ToutEcrivain&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tabStr">Trouver l'entité tabStr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabStr(Couverture<String> c);

	public String getTabStr() {
		return tabStr;
	}

	public void setTabStr(String tabStr) {
		this.tabStr = tabStr;
		this.tabStrCouverture.dejaInitialise = true;
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

	public String solrTabStr() {
		return tabStr;
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

	/**	L'entité « fichier »
	 *	 is defined as null before being initialized. 
	 */
	protected File fichier;
	@JsonIgnore
	public Couverture<File> fichierCouverture = new Couverture<File>().p(this).c(File.class).var("fichier").o(fichier);

	/**	<br/>L'entité « fichier »
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

	/**	L'entité « ecrivainString »
	 *	 is defined as null before being initialized. 
	 */
	protected StringWriter ecrivainString;
	@JsonIgnore
	public Couverture<StringWriter> ecrivainStringCouverture = new Couverture<StringWriter>().p(this).c(StringWriter.class).var("ecrivainString").o(ecrivainString);

	/**	<br/>L'entité « ecrivainString »
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

	/**	L'entité « buffer »
	 *	 is defined as null before being initialized. 
	 */
	protected Buffer buffer;
	@JsonIgnore
	public Couverture<Buffer> bufferCouverture = new Couverture<Buffer>().p(this).c(Buffer.class).var("buffer").o(buffer);

	/**	<br/>L'entité « buffer »
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

	/**	L'entité « ecrivainImpression »
	 *	 is defined as null before being initialized. 
	 */
	protected PrintWriter ecrivainImpression;
	@JsonIgnore
	public Couverture<PrintWriter> ecrivainImpressionCouverture = new Couverture<PrintWriter>().p(this).c(PrintWriter.class).var("ecrivainImpression").o(ecrivainImpression);

	/**	<br/>L'entité « ecrivainImpression »
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

	/**	L'entité « vide »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean vide;
	@JsonIgnore
	public Couverture<Boolean> videCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("vide").o(vide);

	/**	<br/>L'entité « vide »
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
	public ToutEcrivain setVide(String o) {
		this.vide = Boolean.parseBoolean(o);
		this.videCouverture.dejaInitialise = true;
		return (ToutEcrivain)this;
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

	public Boolean solrVide() {
		return vide;
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
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
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
