package org.computate.scolaire.enUS.design;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.receipt.SchoolReceipt;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;
import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;

import io.vertx.ext.web.api.OperationRequest;

/**
 * Translate: false
 **/
public class DesignPdfPage extends DesignPdfPageGen<DesignPdfGenPage> {

	@Override protected void _pageContentType(Wrap<String> c) {
		c.o("application/pdf");
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _w1(Wrap<AllWriter> c) {
		c.o(siteRequest_.getW());
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _w2(Wrap<AllWriter> c) {
		AllWriter o = AllWriter.create(siteRequest_);
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _pageDesignId(Wrap<String> c) {
		if(listPageDesign != null && listPageDesign.size() == 1)
			setPageDesign_(listPageDesign.first());
		if(pageDesign_ != null)
			c.o(pageDesign_.getObjectId());
	}

	protected void _enrollmentSearch(SearchList<SchoolEnrollment> l) {
		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		l.setStore(true);
		l.setQuery("*:*");
		l.setC(SchoolEnrollment.class);
		l.setRows(1000);

		List<String> roles = Arrays.asList("SiteManager");
		if(
				!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
				&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
				) {
			l.addFilterQuery(
				"sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----"))
						+ " OR userKeys_indexed_longs:" + Optional.ofNullable(siteRequest_.getUserKey()).orElse(0L)
			);
		}
		if(!pageDesignId.endsWith("-enrollment-form") || pageDesign_ != null && pageDesign_.getDesignIgnoreEmptyChildName()) {
			l.addFilterQuery("childFirstName_indexed_string:[* TO *]");
		}
		if(StringUtils.equalsAny(pageDesignId, "paid-roster") || pageDesign_ != null && pageDesign_.getDesignIgnorePaymentsPastDue()) {
			l.addFilterQuery("paymentsPastDue_indexed_boolean:false");
		}
		if(StringUtils.equalsAny(pageDesignId, "not-paid-roster") || pageDesign_ != null && pageDesign_.getDesignIgnorePaymentsNotPastDue()) {
			l.addFilterQuery("paymentsPastDue_indexed_boolean:true");
		}
		if(pageDesignId.endsWith("-enrollment-form") || pageDesign_ != null && pageDesign_.getDesignFilterEnrollmentKey()) {
			if(!siteRequest_.getRequestVars().containsKey("enrollmentKey"))
				l.addFilterQuery("enrollmentKey_indexed_long:0");
		}

		l.addSort("seasonStartDate_indexed_date", ORDER.asc);
		l.addSort("sessionEndDate_indexed_date", ORDER.asc);
		l.addSort("ageStart_indexed_int", ORDER.asc);
		l.addSort("blockPricePerMonth_indexed_double", ORDER.asc);
		l.addSort("blockStartTime_indexed_string", ORDER.asc);

		if(StringUtils.equalsAny(pageDesignId, "paid-roster", "not-paid-roster", "email-roster", "group-names-roster", "group-details-roster") 
				|| pageDesign_ != null && pageDesign_.getDesignEnrollmentSortGroupName()) {
			l.addSort("enrollmentGroupName_indexed_string", ORDER.asc);
		}
		if(StringUtils.equalsAny(pageDesignId, "paid-roster", "not-paid-roster", "email-roster", "group-names-roster", "group-details-roster") 
				|| pageDesign_ != null && pageDesign_.getDesignEnrollmentSortChildName()) {
			l.addSort("childCompleteNamePreferred_indexed_string", ORDER.asc);
		}
		if("birthday-roster".equals(pageDesignId) || pageDesign_ != null && pageDesign_.getDesignEnrollmentSortMonthDayOfBirth()) {
			l.addSort("childBirthMonth_indexed_int", ORDER.asc);
			l.addSort("childBirthDay_indexed_int", ORDER.asc);
		}

		for(String var : siteRequest_.getRequestVars().keySet()) {
			String val = siteRequest_.getRequestVars().get(var);
			if(!"design".equals(var)) {
				String varIndexed = SchoolEnrollment.varIndexedSchoolEnrollment(var);
				if(varIndexed != null)
					l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(val));
			}
		}
	}

	protected void _schoolEnrollment(Wrap<SchoolEnrollment> c) {
		if(enrollmentSearch.size() == 1) {
			c.o(enrollmentSearch.get(0));
		}
		else {
			SchoolEnrollment o = new SchoolEnrollment();
			c.o(o);
			o.setPk(0L);
			o.setSiteRequest_(siteRequest_);
			SchoolChild child = new SchoolChild();
			child.setSiteRequest_(siteRequest_);
			o.setChild_(child);
		}
	}

	protected void _enrollments(Wrap<List<SchoolEnrollment>> c) {
		Integer i = 0;
		Integer size = enrollmentSearch.size();
		Long blockKeyBefore = null;
		Long blockKeyCurrent = null;
		String groupBefore = null;
		String groupCurrent = null;
		SchoolEnrollment enrollment = null;
		List<SchoolEnrollment> enrollmentEnrollments = null;
		Integer enrollmentNumber = null;

		enrollments = enrollmentSearch.getList();
		c.o(enrollments);
		if(size > 0) {
			enrollment = enrollments.get(i);
			blockKeyCurrent = enrollment.getBlockKey();
			while(i < size) {
				enrollment = enrollments.get(i);
				blockKeyCurrent = enrollment.getBlockKey();
				groupCurrent = enrollment.getEnrollmentGroupName();
				if(StringUtils.equalsAny(pageDesignId, "paid-roster", "not-paid-roster", "group-names-roster", "group-details-roster")
						|| pageDesign_ != null && pageDesign_.getDesignEnrollmentSortGroupName()) {
					if(blockKeyCurrent == null || ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0) {
						blockKeyBefore = enrollment.getBlockKey();
						enrollmentGroups = enrollment.getEnrollmentGroups();
						enrollmentBlocks.add(enrollment);
					}
					while(i < size) {
						enrollment = enrollments.get(i);
						blockKeyCurrent = enrollment.getBlockKey();
						groupCurrent = enrollment.getEnrollmentGroupName();
						if(StringUtils.isBlank(groupCurrent)) {
							groupCurrent = "none";
							enrollment.setEnrollmentGroupName(groupCurrent);
						}
						if(groupBefore == null || ObjectUtils.compare(groupCurrent, groupBefore) != 0) {
							groupBefore = enrollment.getEnrollmentGroupName();
							enrollmentEnrollments = enrollment.getEnrollmentEnrollments();
							enrollmentGroups.add(enrollment);
							enrollmentNumber = 1;
						}
						enrollment.setEnrollmentKey(enrollment.getPk());
						enrollment.setEnrollmentNumber(enrollmentNumber);
						enrollmentEnrollments.add(enrollment);
						enrollmentNumber++;
						i++;
						if((i + 1) > size)
							break;
						enrollment = enrollments.get(i);
						blockKeyCurrent = enrollment.getBlockKey();
						groupCurrent = enrollment.getEnrollmentGroupName();
						if(ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0)
							break;
						if(ObjectUtils.compare(groupCurrent, groupBefore) != 0)
							break;
					}
					enrollment.setEnrollmentKey(enrollment.getPk());
					enrollment.setEnrollmentNumber(enrollmentNumber);
					enrollmentNumber++;
				}
				else {
					if(blockKeyCurrent == null || ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0) {
						blockKeyBefore = enrollment.getBlockKey();
						enrollmentEnrollments = enrollment.getEnrollmentEnrollments();
						enrollmentBlocks.add(enrollment);
						enrollmentNumber = 1;
					}
					if((i + 1) > size)
						break;
					enrollment.setEnrollmentKey(enrollment.getPk());
					enrollment.setEnrollmentNumber(enrollmentNumber);
					enrollmentEnrollments.add(enrollment);
					enrollmentNumber++;
					if(ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0)
						break;
					i++;
				}
			}
		}
	}

	protected void _enrollmentBlocks(List<SchoolEnrollment> c) {
	}

	protected void _enrollmentGroups(Wrap<List<SchoolEnrollment>> c) {
	}

	protected void _enrollmentBlock(Wrap<SchoolEnrollment> c) {
	}

	protected void _enrollmentGroup(Wrap<SchoolEnrollment> c) {
	}

	protected void _enrollmentEnrollment(Wrap<SchoolEnrollment> c) {
	}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setStore(true);
		l.setQuery("*:*");
		l.setC(SchoolYear.class);

		Long yearKey = Optional.ofNullable(enrollmentSearch.first()).map(SchoolEnrollment::getYearKey).orElse(null);
		if(StringUtils.equalsAny(pageDesignId, "main-enrollment-form", "tax-year") && yearKey != null) {
			l.addFilterQuery("pk_indexed_long:" + yearKey);
		} else {
			for(String var : siteRequest_.getRequestVars().keySet()) {
				String val = siteRequest_.getRequestVars().get(var);
				if(!"design".equals(var)) {
					String varIndexed = SchoolYear.varIndexedSchoolYear(var);
					if(varIndexed != null)
						l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(val));
				}
			}
		}
	}

	protected void _year_(Wrap<SchoolYear> c) {
		if(pageDesignId.endsWith("-enrollment-form") || BooleanUtils.isTrue(pageDesign_.getSearchYears())) {
			if(yearSearch.size() == 1) {
				c.o(yearSearch.get(0));
			}
		}
	}

	protected void _yearKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getPk());
	}

	protected void _yearVar(Wrap<String> c) {
		String o = siteRequest_.getRequestVars().get("year");
		if(o != null)
			c.o(o);
	}

	protected void _schoolSearch(SearchList<School> l) {
		l.setStore(true);
		l.setQuery("*:*");
		l.setC(School.class);

		Long schoolKey = Optional.ofNullable(enrollmentSearch.first()).map(SchoolEnrollment::getSchoolKey).orElse(null);
		if(pageDesignId.endsWith("-enrollment-form") && schoolKey != null) {
			l.addFilterQuery("pk_indexed_long:" + schoolKey);
		} else {
			for(String var : siteRequest_.getRequestVars().keySet()) {
				String val = siteRequest_.getRequestVars().get(var);
				if(!"design".equals(var)) {
					String varIndexed = School.varIndexedSchool(var);
					if(varIndexed != null)
						l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(val));
				}
			}
		}
	}

	protected void _school_(Wrap<School> c) {
		if(pageDesignId.endsWith("-enrollment-form")) {
			if(schoolSearch.size() == 1) {
				c.o(schoolSearch.get(0));
			}
		}
	}

	protected void _paymentSearch(SearchList<SchoolPayment> l) {
		if(BooleanUtils.isTrue(pageDesign_.getSearchPayments())) {
			l.setStore(true);
			l.setQuery("*:*");
			l.setC(SchoolPayment.class);
			if(pageDesignId.equals("payment-receipt"))
				l.setRows(1);
			else 
				l.setRows(1000);
	
			List<String> roles = Arrays.asList("SiteManager");
			if(
					!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
					) {
				l.addFilterQuery(
					"sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----"))
							+ " OR userKeys_indexed_longs:" + Optional.ofNullable(siteRequest_.getUserKey()).orElse(0L)
				);
			}
			if(pageDesignId.equals("payment-receipt") || StringUtils.equalsAny(pageDesignId, "tax-year")) {
				l.add("json.facet", "{sum_paymentAmount:'sum(paymentAmount_indexed_double)'}");
				l.add("json.facet", "{sum_chargeAmount:'sum(chargeAmount_indexed_double)'}");
				l.add("json.facet", "{sum_chargeAmountDue:'sum(chargeAmountDue_indexed_double)'}");
				l.add("json.facet", "{sum_chargeAmountFuture:'sum(chargeAmountFuture_indexed_double)'}");
				l.addFilterQuery("paymentAmount_indexed_double:[* TO *]");
			}

			if(StringUtils.equalsAny(pageDesignId, "tax-year")) {
				Integer year = Integer.parseInt(siteRequest_.getRequestVars().get("year"));
				ZoneId zoneId = ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone());
				String start = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.of(year, 1, 1, 0, 0, 0, 0, zoneId).withZoneSameInstant(ZoneId.of("UTC")));
				String end = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.of(year, 12, 31, 23, 59, 59, 999, zoneId).withZoneSameInstant(ZoneId.of("UTC")));
				l.addFilterQuery("paymentDate_indexed_date:[" + start + " TO " + end + "]");
			}
	
			for(String var : siteRequest_.getRequestVars().keySet()) {
				String val = siteRequest_.getRequestVars().get(var);
				if(!"design".equals(var)) {
					String varIndexed = SchoolPayment.varIndexedSchoolPayment(var);
					if(varIndexed != null) {
						if(StringUtils.startsWith(val, "[")) {
							String[] fqs = StringUtils.substringBefore(StringUtils.substringAfter(val, "["), "]").split(" TO ");
							if(fqs.length != 2)
								throw new RuntimeException(String.format("\"%s\" invalid range query. ", val));
							String fq1 = fqs[0].equals("*") ? fqs[0] : SchoolPayment.staticSolrFqForClass(var, siteRequest_, fqs[0]);
							String fq2 = fqs[1].equals("*") ? fqs[1] : SchoolPayment.staticSolrFqForClass(var, siteRequest_, fqs[1]);
							l.addFilterQuery(varIndexed + ":[" + fq1 + " TO " + fq2 + "]");
						} else {
							l.addFilterQuery(varIndexed + ":" + SchoolPayment.staticSolrFqForClass(var, siteRequest_, val));
						}
					}
				}
			}

			if(pageDesign_ != null && pageDesign_.getDesignEnrollmentSortGroupName()) {
				LocalDate now = LocalDate.now(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone()));
				l.addFilterQuery("blockPricePerMonth_indexed_double:[* TO *]");
				l.addFilterQuery("childCompleteNamePreferred_indexed_string:[* TO *]");
				l.addFilterQuery("enrollmentGroupName_indexed_string:[* TO *]");
				l.addFilterQuery("paymentDate_indexed_date:[" + SchoolPayment.staticSolrFqPaymentDate(siteRequest_, now.minusDays(15).toString()) + " TO " + SchoolPayment.staticSolrFqPaymentDate(siteRequest_, now.plusDays(15).toString()) + "]");
				l.addSort("seasonStartDate_indexed_date", ORDER.asc);
				l.addSort("sessionEndDate_indexed_date", ORDER.asc);
				l.addSort("ageStart_indexed_int", ORDER.asc);
				l.addSort("blockPricePerMonth_indexed_double", ORDER.asc);
				l.addSort("blockStartTime_indexed_string", ORDER.asc);
				l.addSort("enrollmentGroupName_indexed_string", ORDER.asc);
			}
			if(pageDesign_ != null && pageDesign_.getDesignEnrollmentSortChildName()) {
				l.addSort("childCompleteNamePreferred_indexed_string", ORDER.asc);
			}
		}
	}

	protected void _payments_(Wrap<List<SchoolPayment>> c) {
		Integer i = 0;
		Integer size = paymentSearch.size();
		Long blockKeyBefore = null;
		Long blockKeyCurrent = null;
		String groupBefore = null;
		String groupCurrent = null;
		SchoolPayment payment = null;
		List<SchoolPayment> paymentPayments = null;
		Integer paymentNumber = null;

		payments_ = paymentSearch.getList();
		c.o(payments_);
		if(size > 0) {
			payment = payments_.get(i);
			blockKeyCurrent = payment.getBlockKey();
			while(i < size) {
				payment = payments_.get(i);
				blockKeyCurrent = payment.getBlockKey();
				groupCurrent = payment.getEnrollmentGroupName();
				if(StringUtils.equalsAny(pageDesignId, "paid-roster", "not-paid-roster", "group-names-roster", "group-details-roster")
						|| pageDesign_ != null && pageDesign_.getDesignEnrollmentSortGroupName()) {
					if(blockKeyCurrent == null || ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0) {
						blockKeyBefore = payment.getBlockKey();
						paymentGroups = payment.getPaymentGroups();
						paymentBlocks.add(payment);
					}
					while(i < size) {
						payment = payments_.get(i);
						blockKeyCurrent = payment.getBlockKey();
						groupCurrent = payment.getEnrollmentGroupName();
						if(StringUtils.isBlank(groupCurrent)) {
							groupCurrent = "none";
							payment.setEnrollmentGroupName(groupCurrent);
						}
						if(groupBefore == null || ObjectUtils.compare(groupCurrent, groupBefore) != 0) {
							groupBefore = payment.getEnrollmentGroupName();
							paymentPayments = payment.getPaymentPayments();
							paymentGroups.add(payment);
							paymentNumber = 1;
						}
						payment.setPaymentKey(payment.getPk());
						payment.setPaymentNumber(paymentNumber);
						paymentPayments.add(payment);
						paymentNumber++;
						i++;
						if((i + 1) > size)
							break;
						payment = payments_.get(i);
						blockKeyCurrent = payment.getBlockKey();
						groupCurrent = payment.getEnrollmentGroupName();
						if(ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0)
							break;
						if(ObjectUtils.compare(groupCurrent, groupBefore) != 0)
							break;
					}
					payment.setPaymentKey(payment.getPk());
					payment.setPaymentNumber(paymentNumber);
					paymentNumber++;
				}
				else {
					if(blockKeyCurrent == null || ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0) {
						blockKeyBefore = payment.getBlockKey();
						paymentPayments = payment.getPaymentPayments();
						paymentBlocks.add(payment);
						paymentNumber = 1;
					}
					if((i + 1) > size)
						break;
					payment.setPaymentKey(payment.getPk());
					payment.setPaymentNumber(paymentNumber);
					paymentPayments.add(payment);
					paymentNumber++;
					if(ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0)
						break;
					i++;
				}
			}
		}
	}

	protected void _payment_(Wrap<SchoolPayment> c) {
	}

	protected void _paymentBlocks(List<SchoolPayment> c) {
	}

	protected void _paymentGroups(Wrap<List<SchoolPayment>> c) {
	}

	protected void _paymentBlock(Wrap<SchoolPayment> c) {
	}

	protected void _paymentGroup(Wrap<SchoolPayment> c) {
	}

	protected void _paymentPayment(Wrap<SchoolPayment> c) {
	}

	protected void _paymentFacets(Wrap<SimpleOrderedMap> c) {
		if(payments_.size() > 0)
			c.o((SimpleOrderedMap)Optional.ofNullable(paymentSearch.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap()));
	}

	protected void _paymentLastStr(Wrap<String> c) {
		if(payments_.size() > 0) {
			for(SchoolPayment p : paymentSearch.getList()) {
				if(p.getPaymentAmount() != null) {
					c.o(p.getPaymentShortName());
					return;
				}
			}
			c.o("none");
		}
	}

	protected void _paymentAmount(Wrap<BigDecimal> c) {
		if(payments_.size() > 0)
			c.o(Optional.ofNullable((Double)paymentFacets.get("sum_paymentAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargeAmount(Wrap<BigDecimal> c) {
		if(payments_.size() > 0)
			c.o(Optional.ofNullable((Double)paymentFacets.get("sum_chargeAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargeAmountFuture(Wrap<BigDecimal> c) {
		if(payments_.size() > 0)
			c.o(Optional.ofNullable((Double)paymentFacets.get("sum_chargeAmountFuture")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargeAmountDue(Wrap<BigDecimal> c) {
		if(payments_.size() > 0)
			c.o(Optional.ofNullable((Double)paymentFacets.get("sum_chargeAmountDue")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargesNow(Wrap<BigDecimal> c) {
		if(payments_.size() > 0)
			c.o(chargeAmount.subtract(paymentAmount).subtract(chargeAmountFuture));
	}

	protected void _paymentsCurrent(Wrap<Boolean> c) {
		if(payments_.size() > 0)
			c.o(chargesNow.compareTo(BigDecimal.ZERO) <= 0);
	}

	protected void _paymentsLate(Wrap<Boolean> c) {
		if(payments_.size() > 0)
			c.o(chargesNow.subtract(chargeAmountDue).compareTo(BigDecimal.ZERO) > 0);
	}

	protected void _paymentsLateAmount(Wrap<BigDecimal> c) {
		if(payments_.size() > 0) {
			if(paymentsLate)
				c.o(chargesNow.subtract(chargeAmountDue));
			else
				c.o(BigDecimal.ZERO);
		}
	}

	protected void _paymentsAhead(Wrap<Boolean> c) {
		if(payments_.size() > 0)
			c.o(chargesNow.compareTo(BigDecimal.ZERO) < 0);
	}

	protected void _receiptSearch(SearchList<SchoolReceipt> l) {
		if(pageDesignId.equals("custom-receipt")) {
			l.setStore(true);
			l.setQuery("*:*");
			l.setC(SchoolReceipt.class);
			l.setRows(1);
	
			List<String> roles = Arrays.asList("SiteManager");
			if(
					!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
					) {
				l.addFilterQuery(
					"sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----"))
							+ " OR userKeys_indexed_longs:" + Optional.ofNullable(siteRequest_.getUserKey()).orElse(0L)
				);
			}
			l.addFilterQuery("paymentAmount_indexed_double:[* TO *]");
	
			for(String var : siteRequest_.getRequestVars().keySet()) {
				String val = siteRequest_.getRequestVars().get(var);
				if(!"design".equals(var)) {
					String varIndexed = SchoolReceipt.varIndexedSchoolReceipt(var);
					if(varIndexed != null)
						l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(val));
				}
			}
		}
	}

	protected void _receipts_(Wrap<List<SchoolReceipt>> c) {
		c.o(receiptSearch.getList());
	}

	protected void _receipt_(Wrap<SchoolReceipt> c) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailFrom(Wrap<String> c) {
		if(school_ != null)
			c.o(school_.getSchoolEmailFrom());
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailToSchool(Wrap<String> c) {
		if(school_ != null)
			c.o(school_.getSchoolEmailTo());
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailToAddress(Wrap<String> c) {
		c.o(siteRequest_.getRequestVars().get("emailToAddress"));
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailToName(Wrap<String> c) {
		c.o(siteRequest_.getRequestVars().get("emailToName"));
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailMessage(Wrap<String> c) {
		c.o(siteRequest_.getRequestVars().get("emailMessage"));
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getSchoolKey());
	}

	protected void _schoolName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolPhoneNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolAdministratorName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearEnd());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _mom_(Wrap<SchoolMom> c) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _dad_(Wrap<SchoolDad> c) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _guardian_(Wrap<SchoolGuardian> c) {
	}

	protected void _blockSearch(SearchList<SchoolBlock> l) {
		if(year_ != null) {
			l.setQuery("*:*");
			l.addFilterQuery("yearKey_indexed_long:" + year_.getPk());
			l.setC(SchoolBlock.class);
			l.setStore(true);
			l.setRows(100);
			l.addSort("seasonStartDate_indexed_date", ORDER.asc);
			l.addSort("sessionEndDate_indexed_date", ORDER.asc);
			l.addSort("ageStart_indexed_int", ORDER.asc);
			l.addSort("blockPricePerMonth_indexed_double", ORDER.asc);
			l.addSort("blockStartTime_indexed_string", ORDER.asc);
	
			for(String var : siteRequest_.getRequestVars().keySet()) {
				String val = siteRequest_.getRequestVars().get(var);
				if(!"design".equals(var)) {
					String varIndexed = SchoolYear.varIndexedSchoolYear(var);
					if(varIndexed != null)
						l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(val));
				}
			}
		}
	}

	protected void _blocks(Wrap<List<SchoolBlock>> c) {
		Integer i = 0;
		Integer size = blockSearch.size();
		LocalDate seasonStartDateBefore = null;
		LocalDate seasonStartDateCurrent = null;
		LocalDate sessionStartDateBefore = null;
		LocalDate sessionStartDateCurrent = null;
		Integer ageStartBefore = null;
		Integer ageStartCurrent = null;
		SchoolBlock block = null;
		List<SchoolBlock> sessionBlocks = null;
		List<SchoolBlock> ageBlocks = null;
		List<SchoolBlock> blockBlocks = null;
		SchoolEnrollment schoolEnrollment = enrollmentSearch.size() == 1 ? enrollmentSearch.first() : null;

		blocks = blockSearch.getList();
		c.o(blocks);
		if(schoolEnrollment != null && size > 0) {
			block = blocks.get(i);
			seasonStartDateCurrent = block.getSeasonStartDate();
			sessionStartDateCurrent = block.getSessionStartDate();
			ageStartCurrent = block.getAgeStart();
			while(i < size) {
				block = blocks.get(i);
				if(ObjectUtils.compare(seasonStartDateCurrent, seasonStartDateBefore) != 0) {
					seasonStartDateBefore = block.getSeasonStartDate();
					sessionBlocks = block.getSessionBlocks();
					seasonBlocks.add(block);
				}
				while(i < size) {
					block = blocks.get(i);
					if(ObjectUtils.compare(sessionStartDateCurrent, sessionStartDateBefore) != 0) {
						sessionStartDateBefore = block.getSessionStartDate();
						ageBlocks = block.getAgeBlocks();
						sessionBlocks.add(block);
					}
					while(i < size) {
						block = blocks.get(i);
						seasonStartDateCurrent = block.getSeasonStartDate();
						sessionStartDateCurrent = block.getSessionStartDate();
						ageStartCurrent = block.getAgeStart();
						if(ObjectUtils.compare(ageStartCurrent, ageStartBefore) != 0) {
							ageStartBefore = block.getAgeStart();
							blockBlocks = block.getBlockBlocks();
							ageBlocks.add(block);
						}
						blockBlocks.add(block);
						block.setEnrollmentKey(schoolEnrollment.getPk());
						block.setEnrollmentAttribute(schoolEnrollment.getBlockKeys().contains(block.getPk()));
						i++;
						if((i + 1) > size)
							break;
						block = blocks.get(i);
						seasonStartDateCurrent = block.getSeasonStartDate();
						sessionStartDateCurrent = block.getSessionStartDate();
						ageStartCurrent = block.getAgeStart();
						if(ObjectUtils.compare(seasonStartDateCurrent, seasonStartDateBefore) != 0)
							break;
						if(ObjectUtils.compare(seasonStartDateCurrent, seasonStartDateBefore) != 0)
							break;
					}
					seasonStartDateCurrent = block.getSeasonStartDate();
					sessionStartDateCurrent = block.getSessionStartDate();
					ageStartCurrent = block.getAgeStart();
					if(ObjectUtils.compare(seasonStartDateCurrent, seasonStartDateBefore) != 0)
						break;
				}
				seasonStartDateCurrent = block.getSeasonStartDate();
				sessionStartDateCurrent = block.getSessionStartDate();
				ageStartCurrent = block.getAgeStart();
			}
		}
	}

	protected void _seasonBlocks(List<SchoolBlock> l) {
	}

	protected void _seasonBlock(Wrap<SchoolBlock> c) {
	}

	protected void _sessionBlock(Wrap<SchoolBlock> c) {
	}

	protected void _ageBlock(Wrap<SchoolBlock> c) {
	}

	protected void _blockBlock(Wrap<SchoolBlock> c) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _htmlPartSearch(SearchList<HtmlPart> l) {
		if(pageDesign_ != null) {
			l.setQuery("*:*");

			StringBuilder fq = new StringBuilder();
			fq.append("pageDesignKeys_indexed_longs:").append(pageDesign_.getPk());
			for(Long k : pageDesign_.getParentDesignKeys())
				fq.append(" OR pageDesignKeys_indexed_longs:").append(k);

			l.addFilterQuery(fq.toString());
			l.setC(HtmlPart.class);
			l.setStore(true);
			l.addSort("sort1_indexed_double", ORDER.asc);
			l.addSort("sort2_indexed_double", ORDER.asc);
			l.addSort("sort3_indexed_double", ORDER.asc);
			l.addSort("sort4_indexed_double", ORDER.asc);
			l.addSort("sort5_indexed_double", ORDER.asc);
			l.addSort("sort6_indexed_double", ORDER.asc);
			l.addSort("sort7_indexed_double", ORDER.asc);
			l.addSort("sort8_indexed_double", ORDER.asc);
			l.addSort("sort9_indexed_double", ORDER.asc);
			l.addSort("sort10_indexed_double", ORDER.asc);
			l.setRows(100000);
		}
	}

	protected void _htmlPartList(Wrap<List<HtmlPart>> c) {
		if(htmlPartSearch.size() > 0)
			c.o(htmlPartSearch.getList());
	}

	@Override public void htmlPageLayout() {
		siteRequest_.setW(w2);
		setW(w2);
		if(htmlPartList != null) {
			htmlPageLayout2(pageContentType, htmlPartList, null, 0, htmlPartList.size());
		}

		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
//			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//			renderer.setTimeouted(true);
//			for(File fichier : FileUtils.listFiles(new File(siteConfig.docroot.chaîne() + "/ttf"), new String[] { "ttf" }, false)) {
//				FontResolver resolver = renderer.getFontResolver();
//				String chemin = fichier.getAbsolutePath();
//				renderer.getFontResolver().addFont(chemin, true);
//			}
	
			String str = w2.toString();
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			fac.setNamespaceAware(false);
			fac.setValidating(false);
			fac.setFeature("http://xml.org/sax/features/namespaces", false);
			fac.setFeature("http://xml.org/sax/features/validation", false);
			fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			DocumentBuilder builder = fac.newDocumentBuilder();
			builder.setEntityResolver(FSEntityResolver.instance());
			Document doc = builder.parse(new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8"))));

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			renderer.layout();
			renderer.createPDF(os);
			renderer.finishPDF();

			String filename = schoolEnrollment.getObjectId();
			if(pageDesign_ != null) {
				String designName = pageDesign_.getPageDesignCompleteName();
				String schoolName = siteRequest_.getRequestVars().get("schoolName");
				String schoolLocation = siteRequest_.getRequestVars().get("schoolLocation");
				String yearStart = siteRequest_.getRequestVars().get("yearStart");
				Integer yearEnd = yearStart == null ? null : Integer.parseInt(yearStart) + 1;
				if(designName.contains(" roster"))
					filename = toId(String.format(" %s %s %s-%s", designName, schoolName, schoolLocation, yearStart, yearEnd).toLowerCase());
			}

			siteRequest_.getRequestHeaders()
					.add("Content-Disposition", "inline; filename=\"" + filename + ".pdf\"")
					.add("Content-Transfer-Encoding", "binary")
					.add("Accept-Ranges", "bytes")
					;
			w1.getBuffer().appendBytes(os.toByteArray());
		} catch (IOException | ParserConfigurationException | SAXException | DocumentException e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public String toId(String s) {
		if(s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
		}

		return s;
	}
}
