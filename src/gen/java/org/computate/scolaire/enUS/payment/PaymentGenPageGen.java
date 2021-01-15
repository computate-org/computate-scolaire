package org.computate.scolaire.enUS.payment;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.PaymentGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class PaymentGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PaymentGenPage.class);

	///////////////////////
	// listSchoolPayment //
	///////////////////////

	/**	 The entity listSchoolPayment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolPayment> listSchoolPayment;
	@JsonIgnore
	public Wrap<SearchList<SchoolPayment>> listSchoolPaymentWrap = new Wrap<SearchList<SchoolPayment>>().p(this).c(SearchList.class).var("listSchoolPayment").o(listSchoolPayment);

	/**	<br/> The entity listSchoolPayment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.PaymentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolPayment">Find the entity listSchoolPayment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolPayment(Wrap<SearchList<SchoolPayment>> c);

	public SearchList<SchoolPayment> getListSchoolPayment() {
		return listSchoolPayment;
	}

	public void setListSchoolPayment(SearchList<SchoolPayment> listSchoolPayment) {
		this.listSchoolPayment = listSchoolPayment;
		this.listSchoolPaymentWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolPayment> staticSetListSchoolPayment(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	////////////////////
	// schoolPayment_ //
	////////////////////

	/**	 The entity schoolPayment_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolPayment schoolPayment_;
	@JsonIgnore
	public Wrap<SchoolPayment> schoolPayment_Wrap = new Wrap<SchoolPayment>().p(this).c(SchoolPayment.class).var("schoolPayment_").o(schoolPayment_);

	/**	<br/> The entity schoolPayment_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.payment.PaymentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPayment_">Find the entity schoolPayment_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPayment_(Wrap<SchoolPayment> c);

	public SchoolPayment getSchoolPayment_() {
		return schoolPayment_;
	}

	public void setSchoolPayment_(SchoolPayment schoolPayment_) {
		this.schoolPayment_ = schoolPayment_;
		this.schoolPayment_Wrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetSchoolPayment_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected PaymentGenPage schoolPayment_Init() {
		if(!schoolPayment_Wrap.alreadyInitialized) {
			_schoolPayment_(schoolPayment_Wrap);
			if(schoolPayment_ == null)
				setSchoolPayment_(schoolPayment_Wrap.o);
		}
		schoolPayment_Wrap.alreadyInitialized(true);
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
		schoolPayment_Init();
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
			case "schoolPayment_":
				return oPaymentGenPage.schoolPayment_;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetPaymentGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetPaymentGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrPaymentGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrPaymentGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrPaymentGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrPaymentGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqPaymentGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqPaymentGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrFqClusterPage(entityVar,  siteRequest_, o);
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
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
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
