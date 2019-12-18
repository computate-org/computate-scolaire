package org.computate.scolaire.enUS.enrollment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.xhtmlrenderer.extend.FontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 * Translate: false
 **/  
public class EnrollmentFormPage extends EnrollmentFormPageGen<EnrollmentFormGenPage> {

	public void initDeepEnrollmentFormPage() {
		super.initDeepEnrollmentFormGenPage(siteRequest_);
		initEnrollmentFormPage();
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listEnrollmentDesign(SearchList<EnrollmentDesign> l) {
		if(schoolEnrollment != null && schoolEnrollment.getPk() != null) {
			l.setQuery("*:*");
//			l.addFilterQuery("enrollmentKeys_indexed_longs:" + schoolEnrollment.getPk());
			l.addFilterQuery("pk_indexed_long:12697");
			l.setC(EnrollmentDesign.class);
			l.setStore(true);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _enrollmentDesign(Wrap<EnrollmentDesign> c) {
		if(listEnrollmentDesign.size() == 1)
			c.o(listEnrollmentDesign.get(0));
	}

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

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _htmlPartSearch(SearchList<HtmlPart> l) {
		if(enrollmentDesign != null) {
			l.setQuery("*:*");
			l.addFilterQuery("enrollmentDesignKey_indexed_long:" + enrollmentDesign.getPk());
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
