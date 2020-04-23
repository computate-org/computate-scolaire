package org.computate.scolaire.enUS.cluster;

import java.text.Normalizer;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
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

	protected void _inheritPk(Wrap<Long> c) {}

	protected void _id(Wrap<String> c) {
		if(pk != null)
			c.o(pk.toString());
	}

	protected void _created(Wrap<ZonedDateTime> c) {}

	protected void _modified(Wrap<ZonedDateTime> c) {
	}

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
		Class<?> cl = getClass();
		if(!cl.equals(Cluster.class))
			l.add(cl.getCanonicalName());
		l.add(Cluster.class.getCanonicalName());
	}

	protected void _sessionId(Wrap<String> c) {
		c.o(siteRequest_.getSessionId());
	}

	protected void _saves(Wrap<List<String>> c) {
		try {
			c.o((List<String>)FieldUtils.getField(getClass(), "saves" + getClass().getSimpleName(), true).get(this));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			ExceptionUtils.rethrow(e);
		}
	}

	protected void _objectTitle(Wrap<String> c) {
	}

	protected void _objectId(Wrap<String> c) {
		if(objectTitle != null) {
			c.o(toId(objectTitle));
		}
		else if(pk != null){
			c.o(pk.toString());
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

	protected void _objectNameVar(Wrap<String> c) {
		if(objectId != null) {
			Class<?> cl = getClass();

			try {
				String o = toId((String)FieldUtils.getField(cl, cl.getSimpleName() + "_NameVar").get(this));
				c.o(o);
			} catch (Exception e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		StringBuilder b = new StringBuilder();
		if(pk != null)
			b.append(" ").append(pk);
		if(objectNameVar != null)
			b.append(" ").append(objectNameVar);
		if(objectId != null)
			b.append(" ").append(objectId);
		if(objectTitle != null)
			b.append(" ").append(objectTitle);
		c.o(b.toString());
	}

	protected void _objectText(Wrap<String> c) { 
		StringBuilder b = new StringBuilder();
		if(pk != null)
			b.append(" ").append(pk);
		if(objectNameVar != null)
			b.append(" ").append(objectNameVar);
		if(objectId != null)
			b.append(" ").append(objectId);
		if(objectTitle != null)
			b.append(" ").append(objectTitle);
		c.o(b.toString());
	}

	protected void _pageUrlId(Wrap<String> c) {
		if(objectId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/" + objectNameVar + "/" + objectId;
			c.o(o);
		}
	}

	protected void _pageUrlPk(Wrap<String> c) {
		if(pk != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/" + objectNameVar + "/" + pk;
			c.o(o);
		}
	}

	protected void _pageUrlApi(Wrap<String> c) {
		if(pk != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/api/" + objectNameVar + "/" + pk;
			c.o(o);
		}
	}

	protected void _pageH1(Wrap<String> c) {
		try {
			Class<?> cl = getClass();
			c.o((String)FieldUtils.getField(cl, cl.getSimpleName() + "_NameSingular").get(this) + ": " + objectTitle);
		} catch (Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public Cluster e(String localName) {
		AllWriter w = siteRequest_.getW();
		String localNameParent = siteRequest_.getXmlStack().isEmpty() ? null : siteRequest_.getXmlStack().peek();

		boolean eNoWrapParent = localNameParent == null || PageLayout.HTML_ELEMENTS_NO_WRAP.contains(localNameParent);
		String tabs = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "  "));

		siteRequest_.getXmlStack().push(localName);
		if(StringUtils.equals(localName, "html"))
			w.s("<!DOCTYPE html>\n");
		if(!eNoWrapParent && !tabs.isEmpty()) {
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
			s("  ");
		s(objects);
		return this;
	}

	public Cluster tl(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			s("  ");
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
			sx("  ");
		sx(objects);
		return this;
	}

	public Cluster tlx(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			sx("  ");
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
		String tabs = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "  "));

		if(!eNoWrap || localNameParent == null)
			w.l();
		if(!eNoWrap && !tabs.isEmpty())
			w.s(tabs);
		w.s("</");
		w.s(localName);
		w.s(">");
		
		return this;
	}
}
