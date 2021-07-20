package org.computate.scolaire.enUS.page.part;

import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;

/**
 * CanonicalName: org.computate.scolaire.frFR.page.part.PagePart
 **/
public abstract class PagePart extends PagePartGen<Cluster> {

	protected void _page_(Wrap<PageLayout> c) {}

	public void  htmlBody() {
		
	}

	public void  htmlBodyShort() {
		
	}

	protected void _partVar(Wrap<String> c) {}

	public void  htmlBeforePagePart() {}

	public void  htmlAfterPagePart() {}
}
