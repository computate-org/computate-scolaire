package org.computate.scolaire.enUS.enrollment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;
import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;

/**
 * Translate: false
 **/
public class EnrollmentPdfPage extends EnrollmentPdfPageGen<EnrollmentPdfGenPage> {

	public void initDeepEnrollmentPdfPage() {
		super.initDeepEnrollmentPdfGenPage(siteRequest_);
		initEnrollmentPdfPage();
	}

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
		siteRequest_.setW(w2);
		setW(w2);
		if(htmlPartList != null) {
			htmlPageLayout2(htmlPartList, null, 0, htmlPartList.size());
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
			Document doc = builder.parse(new ByteArrayInputStream(str.getBytes()));

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			renderer.layout();
			renderer.createPDF(os);
			renderer.finishPDF();
			siteRequest_.getRequestHeaders()
					.add("Content-Disposition", "inline; filename=\"" + schoolEnrollment.getObjectId() + ".pdf\"")
					.add("Content-Transfer-Encoding", "binary")
					.add("Accept-Ranges", "bytes")
					;
			w1.getBuffer().appendBytes(os.toByteArray());
		} catch (IOException | ParserConfigurationException | SAXException | DocumentException e) {
			ExceptionUtils.rethrow(e);
		}
	}
}
