package org.computate.enUS.education.school;

import java.text.Normalizer;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;

public class School extends SchoolGen<Cluster> {

	protected void _contactInfo(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enfantCles(List<Long> o) {}

	protected void _blocCles(List<Long> o) {}

	protected void _groupeAgeCles(List<Long> o) {}

	protected void _sessionCles(List<Long> o) {}

	protected void _saisonCles(List<Long> o) {}

	protected void _anneeCles(List<Long> o) {}

	protected void _scolaireTri(Wrap<Integer> c) {
		c.o(1);
	}

	protected void _ecoleTri(Wrap<Integer> c) {
		c.o(1);
	}

	protected void _ecoleNom(Wrap<String> c) {
	}

	protected void _ecoleNumeroTelephone(Wrap<String> c) {
	}

	protected void _ecoleAdministrateurNom(Wrap<String> c) {
	}

	protected void _ecoleAddresse(Wrap<String> c) {
	}

	protected void _objetSuggerePoids(Wrap<Double> c) {
		c.o(1D);
	}

	protected void _objetSuggere(Wrap<String> c) { 
		c.o(ecoleNom);
	}

	protected void _ecoleNomCourt(Wrap<String> c) {
		c.o(ecoleNom);
	}

	protected void _ecoleId(Wrap<String> c) {
		if(ecoleNom != null) {
			String s = Normalizer.normalize(ecoleNom, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
			c.o(s);
		}
		else if(pk != null){
			c.o(pk.toString());
		}
	}

	protected void _pageUri(Wrap<String> c) {
		if(ecoleId != null) {
			String o = "/ecole/" + ecoleId;
			c.o(o);
		}
	}

	public void  htmlBody() {
		super.htmlBody();
	}
}
