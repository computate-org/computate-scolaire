package org.computate.scolaire.enUS.cluster;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.enUS.school.page.PageLayout;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

public class Cluster extends ClusterGen<Object> {

	protected void _requeteSite_(Wrap<SiteRequestEnUS> c) {}

	protected void _pageParts(List<PagePart> l) {
	}

	public void  avantPagePart(PagePart o, String var) {
	}

	protected void _pk(Wrap<Long> c) {}

	protected void _id(Wrap<String> c) {
		if(pk != null)
			c.o(pk.toString());
	}

	protected void _cree(Wrap<ZonedDateTime> c) {}

	protected void _modifie(Wrap<ZonedDateTime> c) {}

	protected void _archive(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _supprime(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _classeNomCanonique(Wrap<String> c) {
		String o = getClass().getCanonicalName();
		c.o(o);
	}

	protected void _classeNomSimple(Wrap<String> c) {
		String o = getClass().getSimpleName();
		c.o(o);
	}

	protected void _classeNomsCanoniques(List<String> l) { 
		l.add(Cluster.class.getCanonicalName());
	}

	public Cluster e(String nomLocal) {
		ToutEcrivain w = requeteSite_.getW();
		String nomLocalParent = requeteSite_.getXmlPile().isEmpty() ? null : requeteSite_.getXmlPile().peek();

		boolean eNoWrapParent = nomLocalParent == null || MiseEnPage.HTML_ELEMENTS_NO_WRAP.contains(nomLocalParent);
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\t"));
		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\\t"));

		requeteSite_.getXmlPile().push(nomLocal);
		if(StringUtils.equals(nomLocal, "html"))
			w.s("<!DOCTYPE html>\n");
		if(!eNoWrapParent && !tabulationsEchappes.isEmpty()) {
			w.l();
			w.s(tabulations);
		}
		w.s("<");
		w.s(nomLocal);
		
		return this;
	}

	public Cluster a1(String nomAttribut, Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		w.s(" ");
		w.s(nomAttribut);
		w.s("=\"");
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapperDansCitatations(s));
			}
		}
		
		return this;
	}

	public Cluster a(String nomAttribut, Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		w.s(" ");
		w.s(nomAttribut);
		w.s("=\"");
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapperDansCitatations(s));
			}
		}
		w.s("\"");
		
		return this;
	}

	public Cluster a2() {
		ToutEcrivain w = requeteSite_.getW();
		w.s("\"");
		
		return this;
	}

	public Cluster f() {
		ToutEcrivain w = requeteSite_.getW();
		w.s(">");
		
		return this;
	}

	public Cluster s(Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(s);
			}
		}
		
		return this;
	}

	public Cluster t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		return this;
	}

	public Cluster tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		s("\n");
		return this;
	}

	public Cluster l(Object...objets) {
		s(objets);
		s("\n");
		return this;
	}

	public Cluster lx(Object...objets) {
		s(objets);
		sx("\n");
		return this;
	}

	public Cluster sx(Object...objets) {
		ToutEcrivain w = requeteSite_.getW();
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapper(s));
			}
		}
		
		return this;
	}

	public Cluster tx(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("\t");
		sx(objets);
		return this;
	}

	public Cluster tlx(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("\t");
		sx(objets);
		sx("\n");
		return this;
	}

	public Cluster fg() {
		ToutEcrivain w = requeteSite_.getW();
		w.s("/>");
		requeteSite_.getXmlPile().pop();
		
		return this;
	}

	public Cluster g(String nomLocal) {
		ToutEcrivain w = requeteSite_.getW();
		String nomLocalParent = requeteSite_.getXmlPile().peek();
		boolean eNoWrap = nomLocalParent == null || MiseEnPage.HTML_ELEMENTS_NO_WRAP.contains(nomLocal);

		requeteSite_.getXmlPile().pop();
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\t"));
		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\\t"));

		if(!eNoWrap && !tabulationsEchappes.isEmpty()) {
			w.l();
			w.s(tabulations);
		}
		w.s("</");
		w.s(nomLocal);
		w.s(">");
		
		return this;
	}
}
