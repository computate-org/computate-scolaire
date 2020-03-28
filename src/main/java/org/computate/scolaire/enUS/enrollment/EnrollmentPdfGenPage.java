package org.computate.scolaire.enUS.enrollment;

import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Translate: false
 **/
public class EnrollmentPdfGenPage extends EnrollmentPdfGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolEnrollment(Wrap<SearchList<SchoolEnrollment>> c) {
	}

	protected void _schoolEnrollment(Wrap<SchoolEnrollment> c) {
		if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1)
			c.o(listSchoolEnrollment.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("enrollments");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolEnrollment != null && schoolEnrollment.getEnrollmentCompleteName() != null)
			c.o(schoolEnrollment.getEnrollmentCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolEnrollment != null && schoolEnrollment.getEnrollmentCompleteName() != null)
			c.o(schoolEnrollment.getEnrollmentCompleteName());
		else if(schoolEnrollment != null)
			c.o("");
		else if(listSchoolEnrollment == null || listSchoolEnrollment.size() == 0)
			c.o("no enrollment found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enrollment-pdf");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enrollment-pdf-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("solid");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("edit");
	}

	@Override public void initDeepEnrollmentPdfGenPage() {
		initEnrollmentPdfGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsEnrollmentPdfGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPdfPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/YearPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/ChildPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/MomPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/DadPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/GuardianPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/PaymentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SiteUserPage.js").f().g("script");
	}

	@Override public void htmlScriptEnrollmentPdfGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(siteRequest_.getRequestPk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentBlockKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentBlockKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentBlockKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentBlockKeys_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentChildKey([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentChildKey_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentChildKey([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentChildKey_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentMomKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentMomKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentMomKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentMomKeys_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentDadKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentDadKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentDadKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentDadKeys_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentGuardianKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentGuardianKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentGuardianKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentGuardianKeys_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentPaymentKeys([{'name':'fq','value':'enrollmentKey:' + pk}], $('#listSchoolEnrollmentPaymentKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentPaymentKeys([{'name':'fq','value':'enrollmentKey:' + pk}], $('#listSchoolEnrollmentPaymentKeys_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentUserKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentUserKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentUserKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentUserKeys_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolEnrollmentYearKey([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentYearKey_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolEnrollmentYearKey([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentYearKey_Page'), pk, false); ");
		}
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature1').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature2').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature3').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature4').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature5').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature6').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature7').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature8').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature9').jSignature({'height':200}); ");
		tl(2, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature10').jSignature({'height':200}); ");
		tl(1, "}");
		tl(1, "websocketSchoolEnrollment(websocketSchoolEnrollmentInner);");
		l("});");
	}

	public void htmlFormPageSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Page");
			o.htmCreated("Page");
			o.htmModified("Page");
			o.htmObjectId("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("Page");
			o.htmDeleted("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("Page");
			o.htmEnrollmentImmunizations("Page");
			o.htmEnrollmentGroupName("Page");
			o.htmCustomerProfileId("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("Page");
			o.htmChildPottyTrained("Page");
			o.htmEnrollmentPaymentEachMonth("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("Page");
			o.htmFamilySeparated("Page");
			o.htmFamilyDivorced("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("Page");
			o.htmEnrollmentSpecialConsiderations("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildMedicalConditions("Page");
			o.htmChildPreviousSchoolsAttended("Page");
			o.htmFamilyHowDoYouKnowTheSchool("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("Page");
			o.htmChildObjectives("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("Page");
			o.htmChildKey("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("Page");
			o.htmDadKeys("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("Page");
			o.htmPaymentKeys("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("POST");
			o.htmCreated("POST");
			o.htmModified("POST");
			o.htmObjectId("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("POST");
			o.htmDeleted("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("POST");
			o.htmEnrollmentImmunizations("POST");
			o.htmEnrollmentGroupName("POST");
			o.htmCustomerProfileId("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("POST");
			o.htmChildPottyTrained("POST");
			o.htmEnrollmentPaymentEachMonth("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("POST");
			o.htmFamilySeparated("POST");
			o.htmFamilyDivorced("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("POST");
			o.htmEnrollmentSpecialConsiderations("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildMedicalConditions("POST");
			o.htmChildPreviousSchoolsAttended("POST");
			o.htmFamilyHowDoYouKnowTheSchool("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("POST");
			o.htmChildObjectives("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("POST");
			o.htmChildKey("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("POST");
			o.htmDadKeys("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("POST");
			o.htmPaymentKeys("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserKeys("POST");
		} g("div");
	}

	public void htmlFormPUTSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUT");
			o.htmModified("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUT");
			o.htmDeleted("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("PUT");
			o.htmEnrollmentImmunizations("PUT");
			o.htmEnrollmentGroupName("PUT");
			o.htmCustomerProfileId("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("PUT");
			o.htmChildPottyTrained("PUT");
			o.htmEnrollmentPaymentEachMonth("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("PUT");
			o.htmFamilySeparated("PUT");
			o.htmFamilyDivorced("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("PUT");
			o.htmEnrollmentSpecialConsiderations("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildMedicalConditions("PUT");
			o.htmChildPreviousSchoolsAttended("PUT");
			o.htmFamilyHowDoYouKnowTheSchool("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("PUT");
			o.htmChildObjectives("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("PUT");
			o.htmChildKey("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("PUT");
			o.htmDadKeys("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("PUT");
			o.htmPaymentKeys("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserKeys("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildCompleteName("PUT");
			o.htmChildCompleteNamePreferred("PUT");
			o.htmChildBirthDate("PUT");
			o.htmSchoolAddress("PUT");
			o.htmEnrollmentChargeDate("PUT");
			o.htmEnrollmentParentNames("PUT");
			o.htmEnrollmentSignature1("PUT");
			o.htmEnrollmentSignature2("PUT");
			o.htmEnrollmentSignature3("PUT");
			o.htmEnrollmentSignature4("PUT");
			o.htmEnrollmentSignature5("PUT");
			o.htmEnrollmentSignature6("PUT");
			o.htmEnrollmentSignature7("PUT");
			o.htmEnrollmentSignature8("PUT");
			o.htmEnrollmentSignature9("PUT");
			o.htmEnrollmentSignature10("PUT");
			o.htmEnrollmentDate1("PUT");
			o.htmEnrollmentDate2("PUT");
			o.htmEnrollmentDate3("PUT");
			o.htmEnrollmentDate4("PUT");
			o.htmEnrollmentDate5("PUT");
			o.htmEnrollmentDate6("PUT");
			o.htmEnrollmentDate7("PUT");
			o.htmEnrollmentDate8("PUT");
			o.htmEnrollmentDate9("PUT");
			o.htmEnrollmentDate10("PUT");
		} g("div");
	}

	public void htmlFormPATCHSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("PATCH");
			o.htmEnrollmentImmunizations("PATCH");
			o.htmEnrollmentGroupName("PATCH");
			o.htmCustomerProfileId("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("PATCH");
			o.htmChildPottyTrained("PATCH");
			o.htmEnrollmentPaymentEachMonth("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("PATCH");
			o.htmFamilySeparated("PATCH");
			o.htmFamilyDivorced("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("PATCH");
			o.htmEnrollmentSpecialConsiderations("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildMedicalConditions("PATCH");
			o.htmChildPreviousSchoolsAttended("PATCH");
			o.htmFamilyHowDoYouKnowTheSchool("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("PATCH");
			o.htmChildObjectives("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("PATCH");
			o.htmChildKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("PATCH");
			o.htmDadKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("PATCH");
			o.htmPaymentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildCompleteName("PATCH");
			o.htmChildCompleteNamePreferred("PATCH");
			o.htmChildBirthDate("PATCH");
			o.htmSchoolAddress("PATCH");
			o.htmEnrollmentChargeDate("PATCH");
			o.htmEnrollmentParentNames("PATCH");
			o.htmEnrollmentSignature1("PATCH");
			o.htmEnrollmentSignature2("PATCH");
			o.htmEnrollmentSignature3("PATCH");
			o.htmEnrollmentSignature4("PATCH");
			o.htmEnrollmentSignature5("PATCH");
			o.htmEnrollmentSignature6("PATCH");
			o.htmEnrollmentSignature7("PATCH");
			o.htmEnrollmentSignature8("PATCH");
			o.htmEnrollmentSignature9("PATCH");
			o.htmEnrollmentSignature10("PATCH");
			o.htmEnrollmentDate1("PATCH");
			o.htmEnrollmentDate2("PATCH");
			o.htmEnrollmentDate3("PATCH");
			o.htmEnrollmentDate4("PATCH");
			o.htmEnrollmentDate5("PATCH");
			o.htmEnrollmentDate6("PATCH");
			o.htmEnrollmentDate7("PATCH");
			o.htmEnrollmentDate8("PATCH");
			o.htmEnrollmentDate9("PATCH");
			o.htmEnrollmentDate10("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Recherche");
			o.htmCreated("Recherche");
			o.htmModified("Recherche");
			o.htmObjectId("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("Recherche");
			o.htmDeleted("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("Recherche");
			o.htmEnrollmentImmunizations("Recherche");
			o.htmEnrollmentGroupName("Recherche");
			o.htmCustomerProfileId("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("Recherche");
			o.htmChildPottyTrained("Recherche");
			o.htmEnrollmentPaymentEachMonth("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("Recherche");
			o.htmFamilySeparated("Recherche");
			o.htmFamilyDivorced("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("Recherche");
			o.htmEnrollmentSpecialConsiderations("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildMedicalConditions("Recherche");
			o.htmChildPreviousSchoolsAttended("Recherche");
			o.htmFamilyHowDoYouKnowTheSchool("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("Recherche");
			o.htmChildObjectives("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("Recherche");
			o.htmChildKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("Recherche");
			o.htmDadKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("Recherche");
			o.htmPaymentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjectTitle("Recherche");
			o.htmChildCompleteName("Recherche");
			o.htmChildCompleteNamePreferred("Recherche");
			o.htmChildBirthDate("Recherche");
			o.htmSchoolAddress("Recherche");
			o.htmEnrollmentChargeDate("Recherche");
			o.htmEnrollmentParentNames("Recherche");
			o.htmEnrollmentDate1("Recherche");
			o.htmEnrollmentDate2("Recherche");
			o.htmEnrollmentDate3("Recherche");
			o.htmEnrollmentDate4("Recherche");
			o.htmEnrollmentDate5("Recherche");
			o.htmEnrollmentDate6("Recherche");
			o.htmEnrollmentDate7("Recherche");
			o.htmEnrollmentDate8("Recherche");
			o.htmEnrollmentDate9("Recherche");
			o.htmEnrollmentDate10("Recherche");
		} g("div");
	}

	@Override public void htmlBodyEnrollmentPdfGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolEnrollment == null || listSchoolEnrollment.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/enrollment-pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue-gray w3-hover-blue-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("enrollments").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no enrollment found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolEnrollment o = listSchoolEnrollment.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/enrollment-pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue-gray w3-hover-blue-gray ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue-gray ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue-gray ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/enrollment-pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue-gray w3-hover-blue-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listSchoolEnrollment.getQueryResponse().getResults().getNumFound();
					String q = "*:*";
					String query1 = "objectText";
					String query2 = "";
					String query = "*:*";
					for(String paramName : queryParams.fieldNames()) {
						String entityVar = null;
						String valueIndexed = null;
						Object paramObjectValues = queryParams.getValue(paramName);
						JsonArray paramObjects = paramObjectValues instanceof JsonArray ? (JsonArray)paramObjectValues : new JsonArray().add(paramObjectValues);

						try {
							for(Object paramObject : paramObjects) {
								switch(paramName) {
									case "q":
										q = (String)paramObject;
										entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
										valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
										query1 = entityVar.equals("*") ? query1 : entityVar;
										query2 = valueIndexed;
										query = query1 + ":" + query2;
								}
							}
						} catch(Exception e) {
							ExceptionUtils.rethrow(e);
						}
					}

					Integer rows1 = Optional.ofNullable(listSchoolEnrollment).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listSchoolEnrollment).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listSchoolEnrollment).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listSchoolEnrollment).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment-pdf?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment-pdf?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/enrollment-pdf?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment-pdf?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
				table1EnrollmentPdfGenPage();
		}

		if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolEnrollment o = listSchoolEnrollment.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolEnrollmentForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
						e("input")
						.a("name", "focusId")
						.a("type", "hidden")
						.fg();
					} g("form");
					htmlFormPageSchoolEnrollment(o);
				}

			} g("div");

		}
		htmlBodyFormsEnrollmentPdfGenPage();
		g("div");
	}

	public void table1EnrollmentPdfGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2EnrollmentPdfGenPage();
		} g("table");
	}

	public void table2EnrollmentPdfGenPage() {
		thead1EnrollmentPdfGenPage();
		tbody1EnrollmentPdfGenPage();
		tfoot1EnrollmentPdfGenPage();
	}

	public void thead1EnrollmentPdfGenPage() {
		{ e("thead").a("class", "w3-blue-gray w3-hover-blue-gray ").f();
			thead2EnrollmentPdfGenPage();
		} g("thead");
	}

	public void thead2EnrollmentPdfGenPage() {
			{ e("tr").f();
			if(getColumnCreated()) {
				e("th").f().sx("created").g("th");
			}
			if(getColumnObjectTitle()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1EnrollmentPdfGenPage() {
		{ e("tbody").f();
			tbody2EnrollmentPdfGenPage();
		} g("tbody");
	}

	public void tbody2EnrollmentPdfGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listSchoolEnrollment.getQueryResponse().getHighlighting();
		for(int i = 0; i < listSchoolEnrollment.size(); i++) {
			SchoolEnrollment o = listSchoolEnrollment.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/enrollment-pdf/" + o.getPk();
			{ e("tr").f();
				if(getColumnCreated()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strCreated());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColumnObjectTitle()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							e("i").a("class", "fas fa-edit ").f().g("i");
							{ e("span").f();
								sx(o.strObjectTitle());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1EnrollmentPdfGenPage() {
		{ e("tfoot").a("class", "w3-blue-gray w3-hover-blue-gray ").f();
			tfoot2EnrollmentPdfGenPage();
		} g("tfoot");
	}

	public void tfoot2EnrollmentPdfGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolEnrollment.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			if(getColumnCreated()) {
				e("td").f();
				g("td");
			}
			if(getColumnObjectTitle()) {
				e("td").f();
				g("td");
			}
		} g("tr");
	}

	public Boolean getColumnCreated() {
		return true;
	}

	public Boolean getColumnObjectTitle() {
		return true;
	}

	public void htmlBodyFormsEnrollmentPdfGenPage() {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
						.a("id", "refreshThisEnrollmentPdfGenPage")
						.a("onclick", "patchSchoolEnrollmentVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisEnrollmentPdfGenPage')); }, function() { addError($('#refreshThisEnrollmentPdfGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("refresh this enrollment");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#postSchoolEnrollmentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Create an enrollment");
			} g("button");
			{ e("div").a("id", "postSchoolEnrollmentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Create an enrollment").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolEnrollment o = new SchoolEnrollment();
							o.setSiteRequest_(siteRequest_);

							// Form POST
							{ e("div").a("id", "postSchoolEnrollmentForm").f();
								htmlFormPOSTSchoolEnrollment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "postSchoolEnrollment($('#postSchoolEnrollmentForm')); ")
								.f().sx("Create an enrollment")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#putSchoolEnrollmentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Duplicate the enrollments");
			} g("button");
			{ e("div").a("id", "putSchoolEnrollmentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Duplicate the enrollments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolEnrollment o = new SchoolEnrollment();
							o.setSiteRequest_(siteRequest_);

							// FormValues PUT
							{ e("form").a("action", "").a("id", "putSchoolEnrollmentFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTSchoolEnrollment(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "putSchoolEnrollment($('#putSchoolEnrollmentFormValues'), ", Optional.ofNullable(schoolEnrollment).map(SchoolEnrollment::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Duplicate the enrollments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#patchSchoolEnrollmentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modify the enrollments");
			} g("button");
			{ e("div").a("id", "patchSchoolEnrollmentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify the enrollments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolEnrollment o = new SchoolEnrollment();
							o.setSiteRequest_(siteRequest_);

							// FormValues PATCH
							{ e("form").a("action", "").a("id", "patchSchoolEnrollmentFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHSchoolEnrollment(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "patchSchoolEnrollment(null, $('#patchSchoolEnrollmentFormValues'), ", Optional.ofNullable(schoolEnrollment).map(SchoolEnrollment::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify the enrollments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggestedEnrollmentPdfGenPage(this, null, listSchoolEnrollment);
	}

	/**
	**/
	public static void htmlSuggestedEnrollmentPdfGenPage(PageLayout p, String id, SearchList<SchoolEnrollment> listSchoolEnrollment) {
		SiteRequestEnUS siteRequest_ = p.getSiteRequest_();
		try {
			OperationRequest operationRequest = siteRequest_.getOperationRequest();
			JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
			String q = "*:*";
			String query1 = "objectText";
			String query2 = "";
			for(String paramName : queryParams.fieldNames()) {
				String entityVar = null;
				String valueIndexed = null;
				Object paramObjectValues = queryParams.getValue(paramName);
				JsonArray paramObjects = paramObjectValues instanceof JsonArray ? (JsonArray)paramObjectValues : new JsonArray().add(paramObjectValues);

				try {
					for(Object paramObject : paramObjects) {
						switch(paramName) {
							case "q":
								q = (String)paramObject;
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								query1 = entityVar.equals("*") ? query1 : entityVar;
								query2 = valueIndexed.equals("*") ? "" : valueIndexed;
						}
					}
				} catch(Exception e) {
					ExceptionUtils.rethrow(e);
				}
			}

			Integer rows1 = Optional.ofNullable(listSchoolEnrollment).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listSchoolEnrollment).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listSchoolEnrollment).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listSchoolEnrollment).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), EnrollmentPdfGenPage.ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), EnrollmentPdfGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "refreshAllEnrollmentPdfGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("onclick", "patchSchoolEnrollmentVals([], {}, function() { addGlow($('#refreshAllEnrollmentPdfGenPage", id, "')); }, function() { addError($('#refreshAllEnrollmentPdfGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("refresh all the enrollments");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("search enrollments: ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("class", "suggestSchoolEnrollment w3-input w3-border w3-bar-item ")
					.a("name", "suggestSchoolEnrollment")
					.a("id", "suggestSchoolEnrollment", id)
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolEnrollmentObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolEnrollment", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/enrollment-pdf?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listSchoolEnrollment != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
					.a("onclick", "window.location.href = '/enrollment-pdf?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolEnrollment", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/enrollment-pdf").a("class", "").f();
					p.e("i").a("class", "fas fa-edit ").f().g("i");
					p.sx("see all the enrollments");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}