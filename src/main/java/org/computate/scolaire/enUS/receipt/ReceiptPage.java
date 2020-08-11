package org.computate.scolaire.enUS.receipt;

/**
 * Translate: false
 **/
public class ReceiptPage extends ReceiptPageGen<ReceiptGenPage> {

	@Override public void htmlFormPageSchoolReceipt(SchoolReceipt o) {
		if(schoolReceipt_ != null) {
			{ e("div").a("class", "").f();
				{ e("a").a("href", "/pdf/custom-receipt?var=receiptKey:", schoolReceipt_.getPk()).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ").f();
					sx("Print a receipt");
				} g("a");
			} g("div");
		}
		super.htmlFormPageSchoolReceipt(o);
	}
}
