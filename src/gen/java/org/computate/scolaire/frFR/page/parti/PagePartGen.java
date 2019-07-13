package org.computate.scolaire.frFR.page.parti;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.page.parti.PagePart&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PagePartGen<DEV> extends Cluster {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.page.parti.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:page_">Trouver l'entité page_ dans Solr</a>
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
	protected PagePart page_Init() {
		if(!page_Couverture.dejaInitialise) {
			_page_(page_Couverture);
			if(page_ == null)
				setPage_(page_Couverture.o);
		}
		page_Couverture.dejaInitialise(true);
		return (PagePart)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.page.parti.PagePart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partiVar">Trouver l'entité partiVar dans Solr</a>
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

	public PagePart initLoinPagePart(RequeteSiteFrFR requeteSite_) {
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
		page_Init();
		partiVarInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPagePart(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePagePart(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePagePart(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
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
	public Object obtenirPagePart(String var) {
		PagePart oPagePart = (PagePart)this;
		switch(var) {
			case "page_":
				return oPagePart.page_;
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

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyPagePart();
	}

	public void htmlBodyPagePart() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode());
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
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PagePart {");
		sb.append(" }");
		return sb.toString();
	}
}
