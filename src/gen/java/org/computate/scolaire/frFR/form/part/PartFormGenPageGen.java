package org.computate.scolaire.frFR.form.part;

import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.form.part.PartForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartFormGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PartFormGenPageGen<DEV> extends ClusterPage {

	///////////////////
	// listePartForm //
	///////////////////

	/**	L'entité « listePartForm »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<PartForm> listePartForm;
	@JsonIgnore
	public Couverture<ListeRecherche<PartForm>> listePartFormCouverture = new Couverture<ListeRecherche<PartForm>>().p(this).c(ListeRecherche.class).var("listePartForm").o(listePartForm);

	/**	<br/>L'entité « listePartForm »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listePartForm">Trouver l'entité listePartForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listePartForm(Couverture<ListeRecherche<PartForm>> c);

	public ListeRecherche<PartForm> getListePartForm() {
		return listePartForm;
	}

	public void setListePartForm(ListeRecherche<PartForm> listePartForm) {
		this.listePartForm = listePartForm;
		this.listePartFormCouverture.dejaInitialise = true;
	}
	protected PartFormGenPage listePartFormInit() {
		if(!listePartFormCouverture.dejaInitialise) {
			_listePartForm(listePartFormCouverture);
			if(listePartForm == null)
				setListePartForm(listePartFormCouverture.o);
		}
		if(listePartForm != null)
			listePartForm.initLoinPourClasse(requeteSite_);
		listePartFormCouverture.dejaInitialise(true);
		return (PartFormGenPage)this;
	}

	//////////////
	// partForm //
	//////////////

	/**	L'entité « partForm »
	 *	 is defined as null before being initialized. 
	 */
	protected PartForm partForm;
	@JsonIgnore
	public Couverture<PartForm> partFormCouverture = new Couverture<PartForm>().p(this).c(PartForm.class).var("partForm").o(partForm);

	/**	<br/>L'entité « partForm »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partForm">Trouver l'entité partForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _partForm(Couverture<PartForm> c);

	public PartForm getPartForm() {
		return partForm;
	}

	public void setPartForm(PartForm partForm) {
		this.partForm = partForm;
		this.partFormCouverture.dejaInitialise = true;
	}
	protected PartFormGenPage partFormInit() {
		if(!partFormCouverture.dejaInitialise) {
			_partForm(partFormCouverture);
			if(partForm == null)
				setPartForm(partFormCouverture.o);
		}
		if(partForm != null)
			partForm.initLoinPourClasse(requeteSite_);
		partFormCouverture.dejaInitialise(true);
		return (PartFormGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePartFormGenPage = false;

	public PartFormGenPage initLoinPartFormGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePartFormGenPage) {
			dejaInitialisePartFormGenPage = true;
			initLoinPartFormGenPage();
		}
		return (PartFormGenPage)this;
	}

	public void initLoinPartFormGenPage() {
		initPartFormGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initPartFormGenPage() {
		listePartFormInit();
		partFormInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPartFormGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePartFormGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listePartForm != null)
			listePartForm.setRequeteSite_(requeteSite_);
		if(partForm != null)
			partForm.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePartFormGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPartFormGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPartFormGenPage(String var) {
		PartFormGenPage oPartFormGenPage = (PartFormGenPage)this;
		switch(var) {
			case "listePartForm":
				return oPartFormGenPage.listePartForm;
			case "partForm":
				return oPartFormGenPage.partForm;
			default:
				return super.obtenirClusterPage(var);
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
				o = attribuerPartFormGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPartFormGenPage(String var, Object val) {
		PartFormGenPage oPartFormGenPage = (PartFormGenPage)this;
		switch(var) {
			default:
				return super.attribuerClusterPage(var, val);
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
					o = definirPartFormGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPartFormGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPartFormGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsPartFormGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPartFormGenPage();
		super.htmlScript();
	}

	public void htmlScriptPartFormGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPartFormGenPage();
		super.htmlBody();
	}

	public void htmlBodyPartFormGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPartFormGenPage();
		super.html();
	}

	public void htmlPartFormGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPartFormGenPage();
		super.htmlMeta();
	}

	public void htmlMetaPartFormGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPartFormGenPage();
		super.htmlStyles();
	}

	public void htmlStylesPartFormGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePartFormGenPage();
		super.htmlStyle();
	}

	public void htmlStylePartFormGenPage() {
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
		if(!(o instanceof PartFormGenPage))
			return false;
		PartFormGenPage that = (PartFormGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PartFormGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
