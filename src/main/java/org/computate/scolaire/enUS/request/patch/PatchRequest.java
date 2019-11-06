package org.computate.scolaire.enUS.request.patch;

import java.time.ZonedDateTime;
import java.util.UUID;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

public class PatchRequest extends PatchRequestGen<Object> {

	protected void _siteRequest_(Wrap<SiteRequestEnUS> c) {}

	protected void _created(Wrap<ZonedDateTime> c) {
		c.o(ZonedDateTime.now());
	}

	protected void _rows(Wrap<Integer> c) {
	}

	protected void _numFound(Wrap<Long> c) {
	}

	protected void _numPATCH(Wrap<Long> c) {
		c.o(0L);
	}

	protected void _uuid(Wrap<String> c) {
		c.o(UUID.randomUUID().toString());
	}

	protected void _id(Wrap<String> c) {
		c.o("PATCH-" + uuid);
	}
}
