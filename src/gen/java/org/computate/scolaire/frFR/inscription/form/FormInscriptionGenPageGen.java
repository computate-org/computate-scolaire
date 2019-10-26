package org.computate.scolaire.frFR.inscription.form;

import org.computate.scolaire.frFR.inscription.form.FormInscription;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.cluster.ClusterPage;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.form.FormInscriptionGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class FormInscriptionGenPageGen<DEV> extends ClusterPage {

	//////////////////////////
	// listeFormInscription //
	//////////////////////////

	/**	L'entité « listeFormInscription »
	 *	 is defined as null before being initialized. 
	 */
	protected ListeRecherche<FormInscription> listeFormInscription;
	@JsonIgnore
	public Couverture<ListeRecherche<FormInscription>> listeFormInscriptionCouverture = new Couverture<ListeRecherche<FormInscription>>().p(this).c(ListeRecherche.class).var("listeFormInscription").o(listeFormInscription);

	/**	<br/>L'entité « listeFormInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.form.FormInscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listeFormInscription">Trouver l'entité listeFormInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listeFormInscription(Couverture<ListeRecherche<FormInscription>> c);

	public ListeRecherche<FormInscription> getListeFormInscription() {
		return listeFormInscription;
	}

	public void setListeFormInscription(ListeRecherche<FormInscription> listeFormInscription) {
		this.listeFormInscription = listeFormInscription;
		this.listeFormInscriptionCouverture.dejaInitialise = true;
	}
	protected FormInscriptionGenPage listeFormInscriptionInit() {
		if(!listeFormInscriptionCouverture.dejaInitialise) {
			_listeFormInscription(listeFormInscriptionCouverture);
			if(listeFormInscription == null)
				setListeFormInscription(listeFormInscriptionCouverture.o);
		}
		if(listeFormInscription != null)
			listeFormInscription.initLoinPourClasse(requeteSite_);
		listeFormInscriptionCouverture.dejaInitialise(true);
		return (FormInscriptionGenPage)this;
	}

	/////////////////////
	// formInscription //
	/////////////////////

	/**	L'entité « formInscription »
	 *	 is defined as null before being initialized. 
	 */
	protected FormInscription formInscription;
	@JsonIgnore
	public Couverture<FormInscription> formInscriptionCouverture = new Couverture<FormInscription>().p(this).c(FormInscription.class).var("formInscription").o(formInscription);

	/**	<br/>L'entité « formInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.form.FormInscriptionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:formInscription">Trouver l'entité formInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _formInscription(Couverture<FormInscription> c);

	public FormInscription getFormInscription() {
		return formInscription;
	}

	public void setFormInscription(FormInscription formInscription) {
		this.formInscription = formInscription;
		this.formInscriptionCouverture.dejaInitialise = true;
	}
	protected FormInscriptionGenPage formInscriptionInit() {
		if(!formInscriptionCouverture.dejaInitialise) {
			_formInscription(formInscriptionCouverture);
			if(formInscription == null)
				setFormInscription(formInscriptionCouverture.o);
		}
		if(formInscription != null)
			formInscription.initLoinPourClasse(requeteSite_);
		formInscriptionCouverture.dejaInitialise(true);
		return (FormInscriptionGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseFormInscriptionGenPage = false;

	public FormInscriptionGenPage initLoinFormInscriptionGenPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseFormInscriptionGenPage) {
			dejaInitialiseFormInscriptionGenPage = true;
			initLoinFormInscriptionGenPage();
		}
		return (FormInscriptionGenPage)this;
	}

	public void initLoinFormInscriptionGenPage() {
		initFormInscriptionGenPage();
		super.initLoinClusterPage(requeteSite_);
	}

	public void initFormInscriptionGenPage() {
		listeFormInscriptionInit();
		formInscriptionInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinFormInscriptionGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteFormInscriptionGenPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
		if(listeFormInscription != null)
			listeFormInscription.setRequeteSite_(requeteSite_);
		if(formInscription != null)
			formInscription.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteFormInscriptionGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirFormInscriptionGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirFormInscriptionGenPage(String var) {
		FormInscriptionGenPage oFormInscriptionGenPage = (FormInscriptionGenPage)this;
		switch(var) {
			case "listeFormInscription":
				return oFormInscriptionGenPage.listeFormInscription;
			case "formInscription":
				return oFormInscriptionGenPage.formInscription;
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
				o = attribuerFormInscriptionGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerFormInscriptionGenPage(String var, Object val) {
		FormInscriptionGenPage oFormInscriptionGenPage = (FormInscriptionGenPage)this;
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
					o = definirFormInscriptionGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirFormInscriptionGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsFormInscriptionGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsFormInscriptionGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptFormInscriptionGenPage();
		super.htmlScript();
	}

	public void htmlScriptFormInscriptionGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyFormInscriptionGenPage();
		super.htmlBody();
	}

	public void htmlBodyFormInscriptionGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlFormInscriptionGenPage();
		super.html();
	}

	public void htmlFormInscriptionGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaFormInscriptionGenPage();
		super.htmlMeta();
	}

	public void htmlMetaFormInscriptionGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesFormInscriptionGenPage();
		super.htmlStyles();
	}

	public void htmlStylesFormInscriptionGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleFormInscriptionGenPage();
		super.htmlStyle();
	}

	public void htmlStyleFormInscriptionGenPage() {
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
		if(!(o instanceof FormInscriptionGenPage))
			return false;
		FormInscriptionGenPage that = (FormInscriptionGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("FormInscriptionGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
