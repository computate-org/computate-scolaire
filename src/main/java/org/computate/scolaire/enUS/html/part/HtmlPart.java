package org.computate.scolaire.enUS.html.part;

import java.util.List;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;

public class HtmlPart extends HtmlPartGen<Cluster> {

	protected void _htmlPartKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _pageDesignKeys(List<Long> c) {
	}

	protected void _htmlLink(Wrap<String> c) {
	}

	protected void _htmlElement(Wrap<String> c) {
		if(htmlLink != null)
			c.o("a");
	}

	protected void _htmlId(Wrap<String> c) {
	}

	protected void _htmlClasses(Wrap<String> c) {
	}

	protected void _htmlStyle(Wrap<String> c) {
	}

	protected void _htmlBefore(Wrap<String> c) {
		if(htmlElement != null) {
			String id = htmlId == null ? "" : String.format(" id=\"%s\"", htmlId);
			String classes = htmlClasses == null ? "" : String.format(" class=\"%s\"", htmlClasses);
			String style = htmlStyle == null ? "" : String.format(" style=\"%s\"", htmlStyle);
			c.o(String.format("<%s%s%s%s>", htmlElement, id, classes, style));
		}
	}

	protected void _htmlAfter(Wrap<String> c) {
		if(htmlElement != null) {
			c.o(String.format("<%s>", htmlElement));
		}
	}

	protected void _htmlText(Wrap<String> c) {
	}

	protected void _htmlVar(Wrap<String> c) {
	}

	protected void _htmlVarSpan(Wrap<String> c) {
	}

	protected void _htmlVarForm(Wrap<String> c) {
	}

	protected void _htmlVarInput(Wrap<String> c) {
	}

	protected void _htmlVarForEach(Wrap<String> c) {
	}

	protected void _htmlExclude(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _pdfExclude(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _loginLogout(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _sort1(Wrap<Double> c) {
	}

	protected void _sort2(Wrap<Double> c) {
	}

	protected void _sort3(Wrap<Double> c) {
	}

	protected void _sort4(Wrap<Double> c) {
	}

	protected void _sort5(Wrap<Double> c) {
	}

	protected void _sort6(Wrap<Double> c) {
	}

	protected void _sort7(Wrap<Double> c) {
	}

	protected void _sort8(Wrap<Double> c) {
	}

	protected void _sort9(Wrap<Double> c) {
	}

	protected void _sort10(Wrap<Double> c) {
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		if(htmlBefore != null)
			b.append(htmlBefore);

		if(htmlVarForEach != null)
			b.append("for each {").append(htmlVarForEach).append("}").append(" into {").append(htmlVar).append("}");
		else {
			if(htmlVarSpan != null)
				b.append("<span>").append(htmlVar).append("</span>");
			else if(htmlVar != null)
				b.append("{").append(htmlVar).append("}");
		}

		if(htmlVarInput != null)
			b.append("[").append(htmlVarInput).append("]");
		if(htmlVarForm != null)
			b.append("[[").append(htmlVarForm).append("]]");
		if(htmlText != null)
			b.append(htmlText);
		if(htmlAfter != null)
			b.append(htmlAfter);
		if(b.length() == 0)
			b.append(pk);
		c.o(b.toString());
	}

	@Override()
	protected void  _objectId(Wrap<String> c) {
		if(pk != null){
			c.o(pk.toString());
		}
	}
}
