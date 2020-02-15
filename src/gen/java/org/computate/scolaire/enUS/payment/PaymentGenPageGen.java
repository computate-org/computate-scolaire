package org.computate.scolaire.enUS.payment;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.PaymentGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PaymentGenPageGen<DEV> extends ClusterPage {

	///////////////////////
	// listSchoolPayment //
	///////////////////////

	/**	L'entité « listSchoolPayment »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SchoolPayment> listSchoolPayment;
	@JsonIgnore
	public Wrap<SearchList<SchoolPayment>> listSchoolPaymentWrap = new Wrap<SearchList<SchoolPayment>>().p(this).c(SearchList.class).var("listSchoolPayment").o(listSchoolPayment);

	/**	<br/>L'entité « listSchoolPayment »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.PaymentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolPayment">Trouver l'entité listSchoolPayment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolPayment(Wrap<SearchList<SchoolPayment>> c);

	public SearchList<SchoolPayment> getListSchoolPayment() {
		return listSchoolPayment;
	}

	public void setListSchoolPayment(SearchList<SchoolPayment> listSchoolPayment) {
		this.listSchoolPayment = listSchoolPayment;
		this.listSchoolPaymentWrap.alreadyInitialized = true;
	}
	protected PaymentGenPage listSchoolPaymentInit() {
		if(!listSchoolPaymentWrap.alreadyInitialized) {
			_listSchoolPayment(listSchoolPaymentWrap);
			if(listSchoolPayment == null)
				setListSchoolPayment(listSchoolPaymentWrap.o);
		}
		if(listSchoolPayment != null)
			listSchoolPayment.initDeepForClass(siteRequest_);
		listSchoolPaymentWrap.alreadyInitialized(true);
		return (PaymentGenPage)this;
	}

	///////////////////
	// schoolPayment //
	///////////////////

	/**	L'entité « schoolPayment »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolPayment schoolPayment;
	@JsonIgnore
	public Wrap<SchoolPayment> schoolPaymentWrap = new Wrap<SchoolPayment>().p(this).c(SchoolPayment.class).var("schoolPayment").o(schoolPayment);

	/**	<br/>L'entité « schoolPayment »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.PaymentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPayment">Trouver l'entité schoolPayment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolPayment(Wrap<SchoolPayment> c);

	public SchoolPayment getSchoolPayment() {
		return schoolPayment;
	}

	public void setSchoolPayment(SchoolPayment schoolPayment) {
		this.schoolPayment = schoolPayment;
		this.schoolPaymentWrap.alreadyInitialized = true;
	}
	protected PaymentGenPage schoolPaymentInit() {
		if(!schoolPaymentWrap.alreadyInitialized) {
			_schoolPayment(schoolPaymentWrap);
			if(schoolPayment == null)
				setSchoolPayment(schoolPaymentWrap.o);
		}
		if(schoolPayment != null)
			schoolPayment.initDeepForClass(siteRequest_);
		schoolPaymentWrap.alreadyInitialized(true);
		return (PaymentGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPaymentGenPage = false;

	public PaymentGenPage initDeepPaymentGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPaymentGenPage) {
			alreadyInitializedPaymentGenPage = true;
			initDeepPaymentGenPage();
		}
		return (PaymentGenPage)this;
	}

	public void initDeepPaymentGenPage() {
		initPaymentGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initPaymentGenPage() {
		listSchoolPaymentInit();
		schoolPaymentInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPaymentGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPaymentGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolPayment != null)
			listSchoolPayment.setSiteRequest_(siteRequest_);
		if(schoolPayment != null)
			schoolPayment.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPaymentGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPaymentGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainPaymentGenPage(String var) {
		PaymentGenPage oPaymentGenPage = (PaymentGenPage)this;
		switch(var) {
			case "listSchoolPayment":
				return oPaymentGenPage.listSchoolPayment;
			case "schoolPayment":
				return oPaymentGenPage.schoolPayment;
			default:
				return super.obtainClusterPage(var);
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
				o = attributePaymentGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePaymentGenPage(String var, Object val) {
		PaymentGenPage oPaymentGenPage = (PaymentGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
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
					o = definePaymentGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePaymentGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPaymentGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsPaymentGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPaymentGenPage();
		super.htmlScript();
	}

	public void htmlScriptPaymentGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPaymentGenPage();
		super.htmlBody();
	}

	public void htmlBodyPaymentGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPaymentGenPage();
		super.html();
	}

	public void htmlPaymentGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPaymentGenPage();
		super.htmlMeta();
	}

	public void htmlMetaPaymentGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPaymentGenPage();
		super.htmlStyles();
	}

	public void htmlStylesPaymentGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePaymentGenPage();
		super.htmlStyle();
	}

	public void htmlStylePaymentGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestPaymentGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof PaymentGenPage) {
			PaymentGenPage original = (PaymentGenPage)o;
			super.apiRequestClusterPage();
		}
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
		if(!(o instanceof PaymentGenPage))
			return false;
		PaymentGenPage that = (PaymentGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PaymentGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
