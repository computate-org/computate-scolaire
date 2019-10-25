package org.computate.scolaire.enUS.form.part;

import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;

public class FormPart extends FormPartGen<Cluster> {

	protected void _formPartKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentFormKey(Wrap<Long> c) {
	}

	protected void _htmlOrder(Wrap<Double> c) {
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

	protected void _htmlText(Wrap<String> c) {
	}

	protected void _htmlAfter(Wrap<String> c) {
		if(htmlElement != null) {
			c.o(String.format("<%s>", htmlElement));
		}
	}
}
