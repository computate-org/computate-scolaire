package org.computate.frFR.scolaire.page.parti; 

import org.computate.frFR.scolaire.chaine.Chaine;
import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.requete.RequeteSite;

public abstract class PagePart extends PagePartGen<Cluster> {  

	/**	Tous les infos importants à propos de la requête actuelle. **/
	protected void _requeteSite_(Couverture<RequeteSite> c) {}  

	public abstract Chaine partiH3();
	public abstract Chaine partiH3Court();

	public abstract Chaine partiH4();
	public abstract Chaine partiH4Court();

	public abstract void html();
	public abstract void htmlCourt();

	protected void _partiVar(Couverture<String> c) {}

	public void shAvantClasseJavaChamp() {
	}

	public void shAvantPageParti() {}
	public void shApresPageParti() {}
	public void shHtmlAvantPageParti() {}
	public void shHtmlApresPageParti() {}
	public void htmlAvantPageParti() {}
	public void htmlApresPageParti() {}
//
//	public void shCmd() {
//		if(requeteSite.pageAchete) {
//			page_.tabLigne(0, "if [ \"$1\" == \"p\" ]; then");
////			page.tabLigne(1, "figlet -f smmono9 '", nomSimple.toString().replace("'", "'\"'\"'"), "'; read");
//			page_.tabLigne(0, "else");
//			page_.tabLigne(1, "if [ \"$1\" == \"p\" ]; then");
//			page_.tabLigne(0, "fi");
//		}
//	}
}
