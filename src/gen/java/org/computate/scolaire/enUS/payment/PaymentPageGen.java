package org.computate.scolaire.enUS.payment;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.payment.PaymentGenPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.PaymentPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PaymentPageGen<DEV> extends PaymentGenPage {

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPaymentPage = false;

	public PaymentPage initDeepPaymentPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPaymentPage) {
			alreadyInitializedPaymentPage = true;
			initDeepPaymentPage();
		}
		return (PaymentPage)this;
	}

	public void initDeepPaymentPage() {
		super.initDeepPaymentGenPage(siteRequest_);
		initPaymentPage();
	}

	public void initPaymentPage() {
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPaymentPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPaymentPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestPaymentGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPaymentPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPaymentPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainPaymentPage(String var) {
		PaymentPage oPaymentPage = (PaymentPage)this;
		switch(var) {
			default:
				return super.obtainPaymentGenPage(var);
		}
	}

	///////////////
	// attribute //
	///////////////

	@Override public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributePaymentPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePaymentPage(String var, Object val) {
		PaymentPage oPaymentPage = (PaymentPage)this;
		switch(var) {
			default:
				return super.attributePaymentGenPage(var, val);
		}
	}

	/////////////
	// define //
	/////////////

	@Override public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definePaymentPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePaymentPage(String var, String val) {
		switch(var) {
			default:
				return super.definePaymentGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPaymentPage();
		super.htmlScripts();
	}

	public void htmlScriptsPaymentPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPaymentPage();
		super.htmlScript();
	}

	public void htmlScriptPaymentPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPaymentPage();
		super.htmlBody();
	}

	public void htmlBodyPaymentPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPaymentPage();
		super.html();
	}

	public void htmlPaymentPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPaymentPage();
		super.htmlMeta();
	}

	public void htmlMetaPaymentPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPaymentPage();
		super.htmlStyles();
	}

	public void htmlStylesPaymentPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePaymentPage();
		super.htmlStyle();
	}

	public void htmlStylePaymentPage() {
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
		if(!(o instanceof PaymentPage))
			return false;
		PaymentPage that = (PaymentPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PaymentPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
