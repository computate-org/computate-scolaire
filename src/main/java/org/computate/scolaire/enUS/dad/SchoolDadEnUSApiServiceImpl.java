package org.computate.scolaire.enUS.dad;

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
 * CanonicalName.frFR: org.computate.scolaire.frFR.pere.PereScolaireFrFRApiServiceImpl
 **/
public class SchoolDadEnUSApiServiceImpl extends SchoolDadEnUSGenApiServiceImpl {

	public SchoolDadEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}

	@Override public void sqlPATCHSchoolDad(SchoolDad o, Boolean inheritPk, Handler<AsyncResult<SchoolDad>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		ApiRequest apiRequest = siteRequest.getApiRequest_();
		List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
		List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
		for(Long enrollmentKey : o.getEnrollmentKeys()) {
			pks.add(enrollmentKey);
			classes.add("SchoolEnrollment");
		}
		super.sqlPATCHSchoolDad(o, inheritPk, eventHandler);
	}
}
