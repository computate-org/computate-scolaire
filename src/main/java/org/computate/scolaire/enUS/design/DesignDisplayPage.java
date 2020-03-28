package org.computate.scolaire.enUS.design;

import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.year.SchoolYear;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.OperationRequest;

/**
 * Translate: false
 **/ 
public class DesignDisplayPage extends DesignDisplayPageGen<DesignDisplayGenPage> {

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
	protected void _designId(Wrap<String> c) {
		if(pageDesign != null)
			c.o(pageDesign.getId());
	}

	protected void _enrollmentSearch(SearchList<SchoolEnrollment> l) {
		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		l.setStore(true);
		l.setQuery("*:*");
		l.setC(SchoolEnrollment.class);
		l.setRows(1000);

		List<String> roles = Arrays.asList("SiteAdmin");
		if(
				!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
				&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
				) {
			l.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----")));
		}

		l.addSort("seasonStartDate_indexed_date", ORDER.asc);
		l.addSort("sessionEndDate_indexed_date", ORDER.asc);
		l.addSort("ageStart_indexed_int", ORDER.asc);
		l.addSort("blockPricePerMonth_indexed_double", ORDER.asc);
		l.addSort("blockStartTime_indexed_string", ORDER.asc);

		if("name-roster".equals(designId)) {
			l.addSort("childCompleteNamePreferred_indexed_string", ORDER.asc);
		}
		else if("birthday-roster".equals(designId)) {
			l.addSort("childBirthMonth_indexed_int", ORDER.asc);
			l.addSort("childBirthDay_indexed_int", ORDER.asc);
		}

		String id = operationRequest.getParams().getJsonObject("path").getString("id");
		if(id != null) {
			l.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objectId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
		}

		operationRequest.getParams().getJsonObject("query").forEach(paramRequest -> {
			String entityVar = null;
			String valueIndexed = null;
			String varIndexed = null;
			String valueSort = null;
			Integer aSearchStart = null;
			Integer aSearchNum = null;
			String paramName = paramRequest.getKey();
			Object paramValuesObject = paramRequest.getValue();
			JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

			try {
				for(Object paramObject : paramObjects) {
					switch(paramName) {
						case "q":
							entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
							varIndexed = "*".equals(entityVar) ? entityVar : SchoolYear.varSearchSchoolYear(entityVar);
							valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
							valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
							l.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
							if(!"*".equals(entityVar)) {
								l.setHighlight(true);
								l.setHighlightSnippets(3);
								l.addHighlightField(varIndexed);
								l.setParam("hl.encoder", "html");
							}
							break;
						case "fq":
							entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
							if(!"design".equals(entityVar)) {
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolYear.varIndexedSchoolYear(entityVar);
								l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
							}
							break;
					}
				}
			} catch(Exception e) {
				ExceptionUtils.rethrow(e);
			}
		});
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
				if(ObjectUtils.compare(blockKeyCurrent, blockKeyBefore) != 0) {
					blockKeyBefore = enrollment.getBlockKey();
					enrollmentEnrollments = enrollment.getEnrollmentEnrollments();
					enrollmentBlocks.add(enrollment);
					enrollmentNumber = 1;
				}
				if((i + 2) > size)
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

	protected void _enrollmentBlocks(List<SchoolEnrollment> c) {
	}

	protected void _enrollmentBlock(Wrap<SchoolEnrollment> c) {
	}

	protected void _enrollmentEnrollment(Wrap<SchoolEnrollment> c) {
	}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		l.setStore(true);
		l.setQuery("*:*");
		l.setC(SchoolYear.class);

		Long yearKey = Optional.ofNullable(enrollmentSearch.first()).map(SchoolEnrollment::getYearKey).orElse(null);
		if(yearKey != null)
			l.addFilterQuery("pk_indexed_long:" + yearKey);

		operationRequest.getParams().getJsonObject("query").forEach(paramRequest -> {
			String entityVar = null;
			String valueIndexed = null;
			String varIndexed = null;
			String valueSort = null;
			Integer aSearchStart = null;
			Integer aSearchNum = null;
			String paramName = paramRequest.getKey();
			Object paramValuesObject = paramRequest.getValue();
			JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

			try {
				for(Object paramObject : paramObjects) {
					switch(paramName) {
//						case "q":
//							entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
//							varIndexed = "*".equals(entityVar) ? entityVar : SchoolYear.varSearchSchoolYear(entityVar);
//							valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
//							valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
//							l.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
//							if(!"*".equals(entityVar)) {
//								l.setHighlight(true);
//								l.setHighlightSnippets(3);
//								l.addHighlightField(varIndexed);
//								l.setParam("hl.encoder", "html");
//							}
//							break;
						case "fq":
							entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
							valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
							varIndexed = SchoolYear.varIndexedSchoolYear(entityVar);
							l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
							break;
//						case "sort":
//							entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
//							valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
//							varIndexed = SchoolYear.varIndexedSchoolYear(entityVar);
//							l.addSort(varIndexed, ORDER.valueOf(valueSort));
//							break;
//						case "start":
//							aSearchStart = (Integer)paramObject;
//							l.setStart(aSearchStart);
//							break;
//						case "rows":
//							aSearchNum = (Integer)paramObject;
//							l.setRows(aSearchNum);
//							break;
					}
				}
			} catch(Exception e) {
				ExceptionUtils.rethrow(e);
			}
		});
	}

	protected void _year_(Wrap<SchoolYear> c) {
		if("main-enrollment-form".equals(designId)) {
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
		l.setQuery("*:*");
//		l.addFilterQuery("yearKey_indexed_long:" + year_.getPk());
		l.setC(SchoolBlock.class);
		l.setStore(true);
		l.addSort("seasonStartDate_indexed_date", ORDER.asc);
		l.addSort("sessionEndDate_indexed_date", ORDER.asc);
		l.addSort("ageStart_indexed_int", ORDER.asc);
		l.addSort("blockPricePerMonth_indexed_double", ORDER.asc);
		l.addSort("blockStartTime_indexed_string", ORDER.asc);

		OperationRequest operationRequest = siteRequest_.getOperationRequest();

		operationRequest.getParams().getJsonObject("query").forEach(paramRequest -> {
			String entityVar = null;
			String valueIndexed = null;
			String varIndexed = null;
			String paramName = paramRequest.getKey();
			Object paramValuesObject = paramRequest.getValue();
			JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);
	
			try {
				for(Object paramObject : paramObjects) {
					switch(paramName) {
						case "fq":
							entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
							valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
							varIndexed = SchoolYear.varIndexedSchoolYear(entityVar);
							l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
							break;
					}
				}
			} catch(Exception e) {
				ExceptionUtils.rethrow(e);
			}
		});
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
						if(ObjectUtils.compare(ageStartCurrent, ageStartBefore) != 0) {
							ageStartBefore = block.getAgeStart();
							blockBlocks = block.getBlockBlocks();
							ageBlocks.add(block);
						}
						if((i + 2) > size)
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

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _htmlPartSearch(SearchList<HtmlPart> l) {
		if(pageDesign != null) {
			l.setQuery("*:*");
			l.addFilterQuery("pageDesignKeys_indexed_longs:" + pageDesign.getPk());
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
		if(htmlPartList != null) {
			htmlPageLayout2(htmlPartList, null, 0, htmlPartList.size());
		}
	}
}
