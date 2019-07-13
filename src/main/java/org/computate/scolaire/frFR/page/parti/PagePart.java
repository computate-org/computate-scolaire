package org.computate.scolaire.frFR.page.parti;  


import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;

/**
 * MotCle: classeNomSimplePagePart
 */ 
public abstract class PagePart extends PagePartGen<Cluster> {  

	protected void _page_(Couverture<MiseEnPage> c) {}

	public void  htmlBody() {
		
	}

	public void  htmlBodyCourt() {
		
	}

	protected void _partiVar(Couverture<String> c) {}

	public void  shAvantClasseJavaChamp() {
	}

	public void  shAvantPageParti() {}

	public void  shApresPageParti() {}

	public void  shHtmlAvantPageParti() {}

	public void  shHtmlApresPageParti() {}

	public void  htmlAvantPageParti() {}

	public void  htmlApresPageParti() {}
}
