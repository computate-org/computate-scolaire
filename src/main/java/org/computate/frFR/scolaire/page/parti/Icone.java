package org.computate.frFR.scolaire.page.parti;   

import org.computate.frFR.scolaire.chaine.Chaine;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.page.MiseEnPage;

public class Icone extends IconeGen<PagePart> {   

	protected void _type(Chaine o) {
	}
	protected void _nom(Chaine o) {
	}

	protected void _page_(Couverture<MiseEnPage> c) {}

	public void htmlAvant() {
//		page_.e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
//			page_.e("use").a("xlink:href", "/sprites/", type, ".svg#", nom).f().g("use");
	}
	public void htmlMilieu() {
	}
	public void htmlApres() {
//		page_.g("svg");
	}
	public void html() {
		htmlAvant();
		htmlMilieu();
		htmlApres();
	}

		@Override public Chaine partiH3() {
			return null;
		}
		@Override public Chaine partiH3Court() {
			return null;
		}

		@Override public Chaine partiH4() {
			return null;
		}
		@Override public Chaine partiH4Court() {
			return null;
		}

		@Override
		public void htmlCourt() {
			// TODO Auto-generated method stub
			
		}

	//////////
	// code //
	//////////

	public void codeAvant() { 
	}
	public void codeMilieu() {
	}
	public void codeApres() {
	}
	public void code() {
		codeAvant();
		codeMilieu();
		codeApres();
	}
}
