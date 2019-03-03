package org.computate.frFR.scolaire.page.parti;

import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Objects;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.apache.commons.text.StringEscapeUtils;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.page.parti.PagePart&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PagePartGen<DEV> extends Cluster {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.page.parti.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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

	//////////////
	// partiVar //
	//////////////

	/**	L'entité « partiVar »
	 *	 is defined as null before being initialized. 
	 */
	protected String partiVar;
	public Couverture<String> partiVarCouverture = new Couverture<String>().p(this).c(String.class).var("partiVar").o(partiVar);

	/**	<br/>L'entité « partiVar »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.page.parti.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partiVar">Trouver l'entité partiVar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _partiVar(Couverture<String> c);

	public String getPartiVar() {
		return partiVar;
	}

	public void setPartiVar(String partiVar) {
		this.partiVar = partiVar;
		this.partiVarCouverture.dejaInitialise = true;
	}
	protected PagePart partiVarInit() {
		if(!partiVarCouverture.dejaInitialise) {
			_partiVar(partiVarCouverture);
			if(partiVar == null)
				setPartiVar(partiVarCouverture.o);
		}
		partiVarCouverture.dejaInitialise(true);
		return (PagePart)this;
	}

	public String solrPartiVar() {
		return partiVar;
	}

	public String strPartiVar() {
		return partiVar == null ? "" : partiVar;
	}

	public String nomAffichagePartiVar() {
		return null;
	}

	public String htmTooltipPartiVar() {
		return null;
	}

	public String htmPartiVar() {
		return partiVar == null ? "" : StringEscapeUtils.escapeHtml4(strPartiVar());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePagePart = false;

	public PagePart initLoinPagePart(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePagePart) {
			dejaInitialisePagePart = true;
			initLoinPagePart();
		}
		return (PagePart)this;
	}

	public void initLoinPagePart() {
		super.initLoinCluster(requeteSite_);
		initPagePart();
	}

	public void initPagePart() {
		partiVarInit();
	}

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinPagePart(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePagePart(RequeteSite requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSitePagePart(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPagePart(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPagePart(String var) throws Exception {
		PagePart oPagePart = (PagePart)this;
		switch(var) {
			case "requeteSite_":
				return oPagePart.requeteSite_;
			case "partiVar":
				return oPagePart.partiVar;
			default:
				return super.obtenirCluster(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerPagePart(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPagePart(String var, Object val) {
		PagePart oPagePart = (PagePart)this;
		switch(var) {
			default:
				return super.attribuerCluster(var, val);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirPagePart(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPagePart(String var, String val) {
		switch(var) {
			default:
				return super.definirCluster(var, val);
		}
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPagePart();
		super.htmlCluster();
	}

	public void htmlPagePart() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), partiVar);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PagePart))
			return false;
		PagePart that = (PagePart)o;
		return super.equals(o)
				&& Objects.equals( partiVar, that.partiVar );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PagePart {");
		sb.append( "partiVar: \"" ).append(partiVar).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
