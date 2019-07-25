package org.computate.scolaire.enUS.cluster;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.page.part.PagePart;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.xml.UtilXml;

public class Cluster extends ClusterGen<Object> {

	protected void _siteRequest_(Wrap<SiteRequestEnUS> c) {}

	protected void _pageParts(List<PagePart> l) {
	}

	public void  beforePagePart(PagePart o, String var) {
	}

	protected void _pk(Wrap<Long> c) {}

	protected void _id(Wrap<String> c) {
		if(pk != null)
			c.o(pk.toString());
	}

	protected void _created(Wrap<ZonedDateTime> c) {}

	protected void _modified(Wrap<ZonedDateTime> c) {}

	protected void _archived(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _deleted(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _classCanonicalName(Wrap<String> c) {
		String o = getClass().getCanonicalName();
		c.o(o);
	}

	protected void _classSimpleName(Wrap<String> c) {
		String o = getClass().getSimpleName();
		c.o(o);
	}

	protected void _classCanonicalNames(List<String> l) { 
		l.add(Cluster.class.getCanonicalName());
	}

	public Cluster e(String localName) {
		AllWriter w = siteRequest_.getW();
		String localNameParent = siteRequest_.getXmlStack().isEmpty() ? null : siteRequest_.getXmlStack().peek();

		boolean eNoWrapParent = localNameParent == null || PageLayout.HTML_ELEMENTS_NO_WRAP.contains(localNameParent);
		String tabs = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\t"));
		String tabsEscaped = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\\t"));

		siteRequest_.getXmlStack().push(localName);
		if(StringUtils.equals(localName, "html"))
			w.s("<!DOCTYPE html>\n");
		if(!eNoWrapParent && !tabsEscaped.isEmpty()) {
			w.l();
			w.s(tabs);
		}
		w.s("<");
		w.s(localName);
		
		return this;
	}

	public Cluster a1(String attributeName, Object...objects) {
		AllWriter w = siteRequest_.getW();
		w.s(" ");
		w.s(attributeName);
		w.s("=\"");
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(UtilXml.escapeInQuotes(s));
			}
		}
		
		return this;
	}

	public Cluster a(String attributeName, Object...objects) {
		AllWriter w = siteRequest_.getW();
		w.s(" ");
		w.s(attributeName);
		w.s("=\"");
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(UtilXml.escapeInQuotes(s));
			}
		}
		w.s("\"");
		
		return this;
	}

	public Cluster a2() {
		AllWriter w = siteRequest_.getW();
		w.s("\"");
		
		return this;
	}

	public Cluster f() {
		AllWriter w = siteRequest_.getW();
		w.s(">");
		
		return this;
	}

	public Cluster s(Object...objects) {
		AllWriter w = siteRequest_.getW();
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(s);
			}
		}
		
		return this;
	}

	public Cluster t(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			s("\t");
		s(objects);
		return this;
	}

	public Cluster tl(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			s("\t");
		s(objects);
		s("\n");
		return this;
	}

	public Cluster l(Object...objects) {
		s(objects);
		s("\n");
		return this;
	}

	public Cluster lx(Object...objects) {
		s(objects);
		sx("\n");
		return this;
	}

	public Cluster sx(Object...objects) {
		AllWriter w = siteRequest_.getW();
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(UtilXml.escape(s));
			}
		}
		
		return this;
	}

	public Cluster tx(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			sx("\t");
		sx(objects);
		return this;
	}

	public Cluster tlx(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			sx("\t");
		sx(objects);
		sx("\n");
		return this;
	}

	public Cluster fg() {
		AllWriter w = siteRequest_.getW();
		w.s("/>");
		siteRequest_.getXmlStack().pop();
		
		return this;
	}

	public Cluster g(String localName) {
		AllWriter w = siteRequest_.getW();
		String localNameParent = siteRequest_.getXmlStack().peek();
		boolean eNoWrap = localNameParent == null || PageLayout.HTML_ELEMENTS_NO_WRAP.contains(localName);

		siteRequest_.getXmlStack().pop();
		String tabs = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\t"));
		String tabsEscaped = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\\t"));

		if(!eNoWrap && !tabsEscaped.isEmpty()) {
			w.l();
			w.s(tabs);
		}
		w.s("</");
		w.s(localName);
		w.s(">");
		
		return this;
	}
}
