package org.computate.scolaire.enUS.enrollment;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;

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
			for(HtmlPart htmlPart : htmlPartList) {
				String htmlVar = htmlPart.getHtmlVar();

				s(htmlPart.getHtmlBefore());
				if(htmlVar != null)
					s("{{", htmlVar, "}}");
				s(htmlPart.getHtmlAfter());
			}
		}
	}
}
