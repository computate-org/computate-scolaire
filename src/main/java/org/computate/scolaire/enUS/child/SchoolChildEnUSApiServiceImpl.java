package org.computate.scolaire.enUS.child;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Translate: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.enfant.EnfantScolaireFrFRApiServiceImpl
 **/
public class SchoolChildEnUSApiServiceImpl extends SchoolChildEnUSGenApiServiceImpl {

	public SchoolChildEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}

	@Override public void sqlPATCHSchoolChild(SchoolChild o, Boolean inheritPk, Handler<AsyncResult<SchoolChild>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		ApiRequest apiRequest = siteRequest.getApiRequest_();
		List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
		List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
		for(Long enrollmentKey : o.getEnrollmentKeys()) {
			pks.add(enrollmentKey);
			classes.add("SchoolEnrollment");
		}
		super.sqlPATCHSchoolChild(o, inheritPk, eventHandler);
	}
}
