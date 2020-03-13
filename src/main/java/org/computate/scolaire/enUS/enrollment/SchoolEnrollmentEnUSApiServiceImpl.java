package org.computate.scolaire.enUS.enrollment;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.OperationRequest;

/**
 * Translate: false
 **/
public class SchoolEnrollmentEnUSApiServiceImpl extends SchoolEnrollmentEnUSGenApiServiceImpl {

	public SchoolEnrollmentEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}

	@Override
	public void aSearchSchoolEnrollment(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<SchoolEnrollment>>> eventHandler) {
		super.aSearchSchoolEnrollment(siteRequest, populate, store, classApiUriMethod, eventHandler);
//		try {
//			OperationRequest operationRequest = siteRequest.getOperationRequest();
//			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
//			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
//			SearchList<SchoolEnrollment> listSearch = new SearchList<SchoolEnrollment>();
//			listSearch.setPopulate(populate);
//			listSearch.setStore(store);
//			listSearch.setQuery("*:*");
//			listSearch.setC(SchoolEnrollment.class);
//			if(entityList != null)
//				listSearch.addFields(entityList);
//			listSearch.set("json.facet", "{max_modified:'max(modified_indexed_date)'}");
//
//			String id = operationRequest.getParams().getJsonObject("path").getString("id");
//			if(id != null) {
//				listSearch.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objectId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
//			}
//
//			List<String> roles = Arrays.asList("SiteAdmin");
//			if(
//					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
//					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
//					) {
//				listSearch.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest.getSessionId()).orElse("-----")));
//			}
//
//			operationRequest.getParams().getJsonObject("query").forEach(paramRequest -> {
//				String entityVar = null;
//				String valueIndexed = null;
//				String varIndexed = null;
//				String valueSort = null;
//				Integer aSearchStart = null;
//				Integer aSearchNum = null;
//				String paramName = paramRequest.getKey();
//				Object paramValuesObject = paramRequest.getValue();
//				JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);
//
//				try {
//					for(Object paramObject : paramObjects) {
//						switch(paramName) {
//							case "q":
//								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
//								varIndexed = "*".equals(entityVar) ? entityVar : SchoolEnrollment.varSearchSchoolEnrollment(entityVar);
//								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
//								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
//								listSearch.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
//								if(!"*".equals(entityVar)) {
//									listSearch.setHighlight(true);
//									listSearch.setHighlightSnippets(3);
//									listSearch.addHighlightField(varIndexed);
//									listSearch.setParam("hl.encoder", "html");
//								}
//								break;
//							case "fq":
//								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
//								if(!"design".equals(entityVar)) {
//									valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
//									varIndexed = SchoolEnrollment.varIndexedSchoolEnrollment(entityVar);
//									listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
//								}
//								break;
//							case "sort":
//								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
//								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
//								varIndexed = SchoolEnrollment.varIndexedSchoolEnrollment(entityVar);
//								listSearch.addSort(varIndexed, ORDER.valueOf(valueSort));
//								break;
//							case "start":
//								aSearchStart = (Integer)paramObject;
//								listSearch.setStart(aSearchStart);
//								break;
//							case "rows":
//								aSearchNum = (Integer)paramObject;
//								listSearch.setRows(aSearchNum);
//								break;
//						}
//					}
//				} catch(Exception e) {
//					eventHandler.handle(Future.failedFuture(e));
//				}
//			});
//			if(listSearch.getSorts().size() == 0)
//				listSearch.addSort("created_indexed_date", ORDER.desc);
//			listSearch.initDeepForClass(siteRequest);
//			eventHandler.handle(Future.succeededFuture(listSearch));
//		} catch(Exception e) {
//			eventHandler.handle(Future.failedFuture(e));
//		}
	}
}
