package org.computate.scolaire.enUS.design;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.payment.SchoolPayment;
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

import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.mail.MailAttachment;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailMessage;

/**
 * Translate: false
 **/
public class DesignEmailPage extends DesignEmailPageGen<DesignEmailGenPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _w1(Wrap<AllWriter> c) {
		c.o(siteRequest_.getW());
	}

	//////////
	// Page //
	//////////

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _wPage(Wrap<AllWriter> c) {
		AllWriter o = AllWriter.create(siteRequest_);
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _pageDesign(Wrap<PageDesign> c) {
		if(listPageDesign.size() == 1)
			c.o(listPageDesign.get(0));
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _pageDesignId(Wrap<String> c) {
		if(pageDesign != null)
			c.o(pageDesign.getObjectId());
		else
			throw new RuntimeException("No page design found. ");
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _pageHtmlPartSearch(SearchList<HtmlPart> l) {
		if(pageDesign != null) {
			l.setQuery("*:*");

			StringBuilder fq = new StringBuilder();
			fq.append("pageDesignKeys_indexed_longs:").append(pageDesign.getPk());
			for(Long k : pageDesign.getParentDesignKeys())
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

	protected void _pageHtmlPartList(Wrap<List<HtmlPart>> c) {
		if(pageHtmlPartSearch.size() > 0)
			c.o(pageHtmlPartSearch.getList());
	}

	///////////
	// Email //
	///////////

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _wEmail(Wrap<AllWriter> c) {
		AllWriter o = AllWriter.create(siteRequest_);
		c.o(o);
	}

	protected void _emailContentType(Wrap<String> c) {
		c.o("text/html");
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailDesignId(Wrap<String> c) {
		if("enrollment-sent".equals(pageDesignId))
			c.o("email-enrollment");
		else
			c.o(siteRequest_.getRequestVars().get("emailDesignId"));
	}

	protected void _emailDesignSearch(SearchList<PageDesign> l) {
		if(emailDesignId != null) {
			l.setStore(true);
			l.setQuery("*:*");
			l.setC(PageDesign.class);
			l.setRows(1);
			l.addFilterQuery("objectId_indexed_string:" + ClientUtils.escapeQueryChars(emailDesignId));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailDesign(Wrap<PageDesign> c) {
		if(emailDesignSearch.size() == 1)
			c.o(emailDesignSearch.get(0));
		else
			throw new RuntimeException("No email design found. ");
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailHtmlPartSearch(SearchList<HtmlPart> l) {
		if(emailDesign != null) {
			l.setQuery("*:*");

			StringBuilder fq = new StringBuilder();
			fq.append("pageDesignKeys_indexed_longs:").append(emailDesign.getPk());
			for(Long k : emailDesign.getParentDesignKeys())
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

	protected void _emailHtmlPartList(Wrap<List<HtmlPart>> c) {
		if(emailHtmlPartSearch.size() > 0)
			c.o(emailHtmlPartSearch.getList());
	}

	////////////////
	// Attachment //
	////////////////

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _wAttachment(Wrap<AllWriter> c) {
		AllWriter o = AllWriter.create(siteRequest_);
		c.o(o);
	}

	protected void _attachmentContentType(Wrap<String> c) {
		c.o("application/pdf");
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _attachmentDesignId(Wrap<String> c) {
		if("enrollment-sent".equals(pageDesignId))
			c.o("main-enrollment-form");
		else
		c.o(siteRequest_.getRequestVars().get("attachmentDesignId"));
	}

	protected void _attachmentDesignSearch(SearchList<PageDesign> l) {
		if(attachmentDesignId != null) {
			l.setStore(true);
			l.setQuery("*:*");
			l.setC(PageDesign.class);
			l.setRows(1);
			l.addFilterQuery("objectId_indexed_string:" + ClientUtils.escapeQueryChars(attachmentDesignId));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _attachmentDesign(Wrap<PageDesign> c) {
		if(attachmentDesignSearch.size() == 1)
			c.o(attachmentDesignSearch.get(0));
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _attachmentHtmlPartSearch(SearchList<HtmlPart> l) {
		if(attachmentDesign != null) {
			l.setQuery("*:*");

			StringBuilder fq = new StringBuilder();
			fq.append("pageDesignKeys_indexed_longs:").append(attachmentDesign.getPk());
			for(Long k : attachmentDesign.getParentDesignKeys())
				fq.append(" OR pageDesignKeys_indexed_longs:").append(k);

			l.addFilterQuery(fq.toString());
			l.addFilterQuery("pdfExclude_indexed_boolean:false");
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

	protected void _attachmentHtmlPartList(Wrap<List<HtmlPart>> c) {
		if(attachmentHtmlPartSearch.size() > 0)
			c.o(attachmentHtmlPartSearch.getList());
	}

	/////////////////
	// Enrollments //
	/////////////////

	protected void _enrollmentSearch(SearchList<SchoolEnrollment> l) {
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
		if(!pageDesignId.endsWith("-enrollment-form")) {
			l.addFilterQuery("childFirstName_indexed_string:[* TO *]");
		}

		l.addSort("seasonStartDate_indexed_date", ORDER.asc);
		l.addSort("sessionEndDate_indexed_date", ORDER.asc);
		l.addSort("ageStart_indexed_int", ORDER.asc);
		l.addSort("blockPricePerMonth_indexed_double", ORDER.asc);
		l.addSort("blockStartTime_indexed_string", ORDER.asc);

		if("name-roster".equals(pageDesignId)) {
			l.addSort("childCompleteNamePreferred_indexed_string", ORDER.asc);
		}
		else if("birthday-roster".equals(pageDesignId)) {
			l.addSort("childBirthMonth_indexed_int", ORDER.asc);
			l.addSort("childBirthDay_indexed_int", ORDER.asc);
		}
		else if("email-roster".equals(pageDesignId)) {
			l.addSort("enrollmentGroupName_indexed_string", ORDER.asc);
		}
		else if(StringUtils.equalsAny(pageDesignId, "group-names-roster", "group-details-roster")) {
			l.addSort("enrollmentGroupName_indexed_string", ORDER.asc);
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
				if(StringUtils.equalsAny(pageDesignId, "group-names-roster", "group-details-roster")) {
					if(blockKeyCurrent == null || ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0) {
						blockKeyBefore = enrollment.getBlockKey();
						enrollmentGroups = enrollment.getEnrollmentGroups();
						enrollmentBlocks.add(enrollment);
					}
					while(i < size) {
						enrollment = enrollments.get(i);
						groupCurrent = enrollment.getEnrollmentGroupName();
						if(StringUtils.isBlank(groupCurrent)) {
							groupCurrent = "none";
							enrollment.setEnrollmentGroupName(groupCurrent);
						}
						blockKeyCurrent = enrollment.getBlockKey();
						if(groupBefore == null || ObjectUtils.compare(groupCurrent, groupBefore) != 0) {
							groupBefore = enrollment.getEnrollmentGroupName();
							enrollmentEnrollments = enrollment.getEnrollmentEnrollments();
							enrollmentGroups.add(enrollment);
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
						if(ObjectUtils.compare(groupCurrent, groupBefore) != 0)
							break;
						i++;
					}
					enrollment.setEnrollmentKey(enrollment.getPk());
					enrollment.setEnrollmentNumber(enrollmentNumber);
					enrollmentEnrollments.add(enrollment);
					enrollmentNumber++;
					i++;
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

	///////////
	// Years //
	///////////

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setStore(true);
		l.setQuery("*:*");
		l.setC(SchoolYear.class);

		Long yearKey = Optional.ofNullable(enrollmentSearch.first()).map(SchoolEnrollment::getYearKey).orElse(null);
		if(attachmentDesignId.endsWith("-enrollment-form") && yearKey != null) {
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
		if(pageDesignId.endsWith("-enrollment-form") || "enrollment-sent".equals(pageDesignId)) {
			if(yearSearch.size() == 0) {
				throw new RuntimeException("No year was found for the query: " + siteRequest_.getOperationRequest().getParams().getJsonObject("query").encode());
			}
			else if(yearSearch.size() == 1) {
				c.o(yearSearch.get(0));
			}
			else  {
				throw new RuntimeException("More than one year was found for the query: " + siteRequest_.getOperationRequest().getParams().getJsonObject("query").encode());
			}
		}
	}

	protected void _yearKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getPk());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearEnd());
	}

	/////////////
	// Schools //
	/////////////

	protected void _schoolSearch(SearchList<School> l) {
		l.setStore(true);
		l.setQuery("*:*");
		l.setC(School.class);

		Long schoolKey = Optional.ofNullable(enrollmentSearch.first()).map(SchoolEnrollment::getSchoolKey).orElse(null);
		if(attachmentDesignId.endsWith("-enrollment-form") && schoolKey != null) {
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
		if(pageDesignId.endsWith("-enrollment-form") || "enrollment-sent".equals(pageDesignId)) {
			if(schoolSearch.size() == 0) {
				throw new RuntimeException("No school was found for the query: " + siteRequest_.getOperationRequest().getParams().getJsonObject("query").encode());
			}
			else if(schoolSearch.size() == 1) {
				c.o(schoolSearch.get(0));
			}
			else  {
				throw new RuntimeException("More than one school was found for the query: " + siteRequest_.getOperationRequest().getParams().getJsonObject("query").encode());
			}
		}
		else if(schoolSearch.size() == 1) {
			c.o(schoolSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(school_ != null)
			c.o(school_.getSchoolKey());
	}

	protected void _schoolName(Wrap<String> c) {
		if(school_ != null)
			c.o(school_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(school_ != null)
			c.o(school_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolPhoneNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolAdministratorName());
	}

	/////////////
	// Seasons //
	/////////////

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
						if((i + 1) > size)
							break;
						seasonStartDateCurrent = block.getSeasonStartDate();
						sessionStartDateCurrent = block.getSessionStartDate();
						ageStartCurrent = block.getAgeStart();
						block.setEnrollmentKey(schoolEnrollment.getPk());
						block.setEnrollmentAttribute(schoolEnrollment.getBlockKeys().contains(block.getPk()));
						blockBlocks.add(block);
						if(ObjectUtils.compare(seasonStartDateCurrent, seasonStartDateBefore) != 0)
							break;
						i++;
					}
					seasonStartDateCurrent = block.getSeasonStartDate();
					sessionStartDateCurrent = block.getSessionStartDate();
					ageStartCurrent = block.getAgeStart();
					if(ObjectUtils.compare(seasonStartDateCurrent, seasonStartDateBefore) != 0)
						break;
					i++;
				}
				seasonStartDateCurrent = block.getSeasonStartDate();
				sessionStartDateCurrent = block.getSessionStartDate();
				ageStartCurrent = block.getAgeStart();
				i++;
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

	protected void _paymentSearch(SearchList<SchoolPayment> l) {
		if(pageDesignId.equals("receipts")) {
			l.setStore(true);
			l.setQuery("*:*");
			l.setC(SchoolPayment.class);
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
	
			for(String var : siteRequest_.getRequestVars().keySet()) {
				String val = siteRequest_.getRequestVars().get(var);
				if(!"design".equals(var)) {
					String varIndexed = SchoolPayment.varIndexedSchoolPayment(var);
					if(varIndexed != null)
						l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(val));
				}
			}
		}
	}

	protected void _payments_(Wrap<List<SchoolPayment>> c) {
		c.o(paymentSearch.getList());
	}

	protected void _payment_(Wrap<SchoolPayment> c) {
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
		if("enrollment-sent".equals(pageDesignId)) {
			StringBuilder b = new StringBuilder();
			b.append(schoolEnrollment.getEnrollmentParentEmails());
			c.o(b.toString());
		}
		else {
			c.o(siteRequest_.getRequestVars().get("emailToAddress"));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailToName(Wrap<String> c) {
		if("enrollment-sent".equals(pageDesignId)) {
			c.o(schoolEnrollment.getEnrollmentParentNames());
		}
		else {
			c.o(siteRequest_.getRequestVars().get("emailToName"));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailMessage(Wrap<String> c) {
		c.o(siteRequest_.getRequestVars().get("emailMessage"));
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _emailSubject(Wrap<String> c) {
		if("enrollment-sent".equals(pageDesignId)) {
			StringBuilder b = new StringBuilder();
			b.append("Enrollment form for ").append(schoolEnrollment.getChildCompleteNamePreferred()).append(" for ").append(schoolEnrollment.getBlockShortName());
			c.o(b.toString());
		}
		else {
			String format = siteRequest_.getRequestVars().get("emailSubject");
			if(format == null)
				throw new RuntimeException("The email subject field was blank. ");
			Matcher matcher = Pattern.compile("\\$\\{(\\w+)}", Pattern.MULTILINE).matcher(format);
			boolean found = matcher.find();
			StringBuffer sb = new StringBuffer();
			
			while(found) {
				String var = matcher.group(1);
				Object o = obtainDesignEmailPage(var);
				matcher.appendReplacement(sb, o == null ? "" : o.toString());
				found = matcher.find();
			}
			matcher.appendTail(sb);
			c.o(sb.toString());
		}
	}

	@Override public void htmlPageLayout() {

		SiteContextEnUS siteContext = siteRequest_.getSiteContext_();
		MailClient mailClient = siteContext.getMailClient();
		MailMessage message = new MailMessage();
		message.setFrom(emailFrom);
		if(StringUtils.isBlank(emailToAddress))
			throw new RuntimeException("The email to field was blank. ");
		if(StringUtils.isBlank(emailToSchool))
			throw new RuntimeException("The request was not matched to a school. ");
		ArrayList<String> tos = new ArrayList<>();
		tos.addAll(Arrays.asList(emailToSchool.trim().split("\\s*,\\s*")));
		tos.addAll(Arrays.asList(emailToAddress.trim().split("\\s*,\\s*")));
		message.setTo(tos);

		if(pageDesign != null) {
			siteRequest_.setW(wPage);
			setW(wPage);
			if(pageHtmlPartList != null) {
				htmlPageLayout2(pageContentType, pageHtmlPartList, null, 0, pageHtmlPartList.size());
			}
		}

		if(emailDesign != null) {
			siteRequest_.setW(wEmail);
			setW(wEmail);
			if(emailHtmlPartList != null) {
				htmlPageLayout2(emailContentType, emailHtmlPartList, null, 0, emailHtmlPartList.size());
			}
		}

		if(attachmentDesign != null) {
			siteRequest_.setW(wAttachment);
			setW(wAttachment);
			if(attachmentHtmlPartList != null) {
				htmlPageLayout2(attachmentContentType, attachmentHtmlPartList, null, 0, attachmentHtmlPartList.size());
			}
	
			try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
	//			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	//			renderer.setTimeouted(true);
	//			for(File fichier : FileUtils.listFiles(new File(siteConfig.docroot.chaîne() + "/ttf"), new String[] { "ttf" }, false)) {
	//				FontResolver resolver = renderer.getFontResolver();
	//				String chemin = fichier.getAbsolutePath();
	//				renderer.getFontResolver().addFont(chemin, true);
	//			}
		
				DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
				fac.setNamespaceAware(false);
				fac.setValidating(false);
				fac.setFeature("http://xml.org/sax/features/namespaces", false);
				fac.setFeature("http://xml.org/sax/features/validation", false);
				fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
				fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	
				DocumentBuilder builder = fac.newDocumentBuilder();
				builder.setEntityResolver(FSEntityResolver.instance());
				Document doc = builder.parse(new ByteArrayInputStream(wAttachment.toString().getBytes()));
	
				if("application/pdf".equals(attachmentContentType)) {
					ITextRenderer renderer = new ITextRenderer();
					renderer.setDocument(doc, null);
					renderer.layout();
					renderer.createPDF(os);
					renderer.finishPDF();
					MailAttachment attachment = new MailAttachment();
					attachment.setContentType("application/pdf");
					attachment.setData(Buffer.buffer(os.toByteArray()));
					attachment.setName(schoolEnrollment.getObjectId());
					message.setAttachment(attachment);
				}
			} catch (IOException | ParserConfigurationException | SAXException | DocumentException e) {
				ExceptionUtils.rethrow(e);
			}
		}

		message.setHtml(wEmail.toString());
//		message.setSubject(String.format("Enrollment of %s for the %s", schoolEnrollment.getChildCompleteName(), schoolEnrollment.getSeasonCompleteName()));
		message.setSubject(emailSubject);

		LOGGER.info("Email from: " + message.getFrom() + " to " + String.join("; ", message.getTo()));
		WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
		workerExecutor.executeBlocking(
			blockingCodeHandler -> {
				mailClient.sendMail(message, result -> {
					if (result.succeeded()) {
						System.out.println(result.result());
					} else {
						result.cause().printStackTrace();
					}
				});
			}, resultHandler -> {
			}
		);

		w1.getBuffer().appendString(wPage.toString());
	}
}
