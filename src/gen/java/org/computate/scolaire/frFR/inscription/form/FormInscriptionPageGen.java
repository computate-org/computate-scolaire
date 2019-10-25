package org.computate.scolaire.frFR.inscription.form;

import org.computate.scolaire.frFR.inscription.form.FormInscriptionGenPage;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
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

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.form.FormInscriptionPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class FormInscriptionPageGen<DEV> extends FormInscriptionGenPage {

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseFormInscriptionPage = false;

	public FormInscriptionPage initLoinFormInscriptionPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseFormInscriptionPage) {
			dejaInitialiseFormInscriptionPage = true;
			initLoinFormInscriptionPage();
		}
		return (FormInscriptionPage)this;
	}

	public void initLoinFormInscriptionPage() {
		super.initLoinFormInscriptionGenPage(requeteSite_);
		initFormInscriptionPage();
	}

	public void initFormInscriptionPage() {
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinFormInscriptionPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteFormInscriptionPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteFormInscriptionGenPage(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteFormInscriptionPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirFormInscriptionPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirFormInscriptionPage(String var) {
		FormInscriptionPage oFormInscriptionPage = (FormInscriptionPage)this;
		switch(var) {
			default:
				return super.obtenirFormInscriptionGenPage(var);
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
				o = attribuerFormInscriptionPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerFormInscriptionPage(String var, Object val) {
		FormInscriptionPage oFormInscriptionPage = (FormInscriptionPage)this;
		switch(var) {
			default:
				return super.attribuerFormInscriptionGenPage(var, val);
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
					o = definirFormInscriptionPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirFormInscriptionPage(String var, String val) {
		switch(var) {
			default:
				return super.definirFormInscriptionGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsFormInscriptionPage();
		super.htmlScripts();
	}

	public void htmlScriptsFormInscriptionPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptFormInscriptionPage();
		super.htmlScript();
	}

	public void htmlScriptFormInscriptionPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyFormInscriptionPage();
		super.htmlBody();
	}

	public void htmlBodyFormInscriptionPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlFormInscriptionPage();
		super.html();
	}

	public void htmlFormInscriptionPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaFormInscriptionPage();
		super.htmlMeta();
	}

	public void htmlMetaFormInscriptionPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesFormInscriptionPage();
		super.htmlStyles();
	}

	public void htmlStylesFormInscriptionPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleFormInscriptionPage();
		super.htmlStyle();
	}

	public void htmlStyleFormInscriptionPage() {
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
		if(!(o instanceof FormInscriptionPage))
			return false;
		FormInscriptionPage that = (FormInscriptionPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("FormInscriptionPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
