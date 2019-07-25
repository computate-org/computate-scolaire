package org.computate.scolaire.enUS.mission;

import java.text.Normalizer;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;

public class Mission extends MissionGen<Cluster> {

	protected void _missionNom(Wrap<String> c) {
	}

	protected void _ecoleNumeroTelephone(Wrap<String> c) {
	}

	protected void _missionId(Wrap<String> c) {
		if(missionNom != null) {
			String s = Normalizer.normalize(missionNom, Normalizer.Form.NFD);
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
		if(missionId != null) {
			String o = "/mission/" + missionId;
			c.o(o);
		}
	}

	public void  htmlBody() {
		super.htmlBody();
	}
}
