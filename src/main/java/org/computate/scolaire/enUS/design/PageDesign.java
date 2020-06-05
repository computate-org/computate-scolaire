package org.computate.scolaire.enUS.design;

import java.util.List;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.search.SearchList;

/**	
 *	classCanonicalName: org.computate.scolaire.frFR.design.DesignPage
 *	Model: true
 **/
public class PageDesign extends PageDesignGen<Cluster> {

	protected void _pageDesignKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _childDesignKeys(List<Long> c) {
	}

	protected void _parentDesignKeys(List<Long> c) {
	}

	protected void _htmlPartKeys(List<Long> o) {}

	protected void _pageDesignCompleteName(Wrap<String> c) {
		String o;
//		if(yearCompleteName == null)
			o = String.format("enrollment design");
//		else
//			o = String.format("enrollment design for the %s", yearCompleteName);
		c.o(o);
	}

	protected void _designHidden(Wrap<Boolean> c) {
		c.o(false);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(pageDesignCompleteName);
	}
}
