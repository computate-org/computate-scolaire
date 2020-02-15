package org.computate.scolaire.enUS.block;

import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import java.util.Optional;


/**
 * Translate: false
 **/
public class BlockGenPage extends BlockGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolBlock(Wrap<SearchList<SchoolBlock>> c) {
	}

	protected void _schoolBlock(Wrap<SchoolBlock> c) {
		if(listSchoolBlock != null && listSchoolBlock.size() == 1)
			c.o(listSchoolBlock.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("blocks");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolBlock != null && schoolBlock.getBlockCompleteName() != null)
			c.o(schoolBlock.getBlockCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolBlock != null && schoolBlock.getBlockCompleteName() != null)
			c.o(schoolBlock.getBlockCompleteName());
		else if(schoolBlock != null)
			c.o("");
		else if(listSchoolBlock == null || listSchoolBlock.size() == 0)
			c.o("no block found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/block");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/block-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("bell");
	}

	@Override public void initDeepBlockGenPage() {
		initBlockGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsBlockGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/AgePage.js").f().g("script");
	}

	@Override public void htmlScriptBlockGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(siteRequest_.getRequestPk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggestSchoolBlockAgeKey([{'name':'fq','value':'blockKeys:' + pk}], $('#listSchoolBlockAgeKey_Page'), pk); ");
		tl(2, "suggestSchoolBlockEnrollmentKeys([{'name':'fq','value':'blockKeys:' + pk}], $('#listSchoolBlockEnrollmentKeys_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketSchoolBlock(websocketSchoolBlockInner);");
		l("});");
	}

	public void htmlFormPageSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Page");
			o.htmCreated("Page");
			o.htmModified("Page");
			o.htmObjectId("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("Page");
			o.htmDeleted("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockStartTime("Page");
			o.htmBlockEndTime("Page");
			o.htmBlockPricePerMonth("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("Page");
			o.htmBlockTuesday("Page");
			o.htmBlockWednesday("Page");
			o.htmBlockThursday("Page");
			o.htmBlockFriday("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("Page");
			o.htmEnrollmentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("POST");
			o.htmCreated("POST");
			o.htmModified("POST");
			o.htmObjectId("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("POST");
			o.htmDeleted("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockStartTime("POST");
			o.htmBlockEndTime("POST");
			o.htmBlockPricePerMonth("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("POST");
			o.htmBlockTuesday("POST");
			o.htmBlockWednesday("POST");
			o.htmBlockThursday("POST");
			o.htmBlockFriday("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("POST");
			o.htmEnrollmentKeys("POST");
		} g("div");
	}

	public void htmlFormPUTSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUT");
			o.htmModified("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUT");
			o.htmDeleted("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockStartTime("PUT");
			o.htmBlockEndTime("PUT");
			o.htmBlockPricePerMonth("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("PUT");
			o.htmBlockTuesday("PUT");
			o.htmBlockWednesday("PUT");
			o.htmBlockThursday("PUT");
			o.htmBlockFriday("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("PUT");
			o.htmEnrollmentKeys("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("PUT");
		} g("div");
	}

	public void htmlFormPATCHSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockStartTime("PATCH");
			o.htmBlockEndTime("PATCH");
			o.htmBlockPricePerMonth("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("PATCH");
			o.htmBlockTuesday("PATCH");
			o.htmBlockWednesday("PATCH");
			o.htmBlockThursday("PATCH");
			o.htmBlockFriday("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("PATCH");
			o.htmEnrollmentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Recherche");
			o.htmCreated("Recherche");
			o.htmModified("Recherche");
			o.htmObjectId("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("Recherche");
			o.htmDeleted("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockStartTime("Recherche");
			o.htmBlockEndTime("Recherche");
			o.htmBlockPricePerMonth("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("Recherche");
			o.htmBlockTuesday("Recherche");
			o.htmBlockWednesday("Recherche");
			o.htmBlockThursday("Recherche");
			o.htmBlockFriday("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("Recherche");
			o.htmEnrollmentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjectTitle("Recherche");
			o.htmSchoolAddress("Recherche");
		} g("div");
	}

	@Override public void htmlBodyBlockGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolBlock == null || listSchoolBlock.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/block").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("blocks").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no block found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolBlock != null && listSchoolBlock.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolBlock o = listSchoolBlock.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/block").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/block").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolBlock.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolBlock.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolBlock.getRows();
					Integer start1 = listSchoolBlock.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/block?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/block?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/block?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/block?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-indigo w3-hover-indigo ").f();
					{ e("tr").f();
						e("th").f().sx("created").g("th");
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolBlock.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolBlock.size(); i++) {
						SchoolBlock o = listSchoolBlock.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/block/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCreated());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-bell ").f().g("i");
									{ e("span").f();
										sx(o.strObjectTitle());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listSchoolBlock != null && listSchoolBlock.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolBlock o = listSchoolBlock.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolBlockForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
						e("input")
						.a("name", "focusId")
						.a("type", "hidden")
						.fg();
					} g("form");
					htmlFormPageSchoolBlock(o);
				}

			} g("div");

		}
		htmlBodyFormsBlockGenPage();
		htmlSuggestBlockGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsBlockGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("id", "refreshThisBlockGenPage")
				.a("onclick", "patchSchoolBlockVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisBlockGenPage')); }, function() { addError($('#refreshThisBlockGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("refresh this block");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#postSchoolBlockModal').show(); ")
			.f().sx("Create a block")
		.g("button");
		{ e("div").a("id", "postSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolBlockModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Create a block").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolBlock o = new SchoolBlock();
						o.setSiteRequest_(siteRequest_);

						// Form POST
						{ e("div").a("id", "postSchoolBlockForm").f();
							htmlFormPOSTSchoolBlock(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "postSchoolBlock($('#postSchoolBlockForm')); ")
							.f().sx("Create a block")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#putSchoolBlockModal').show(); ")
			.f().sx("Duplicate the blocks")
		.g("button");
		{ e("div").a("id", "putSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putSchoolBlockModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Duplicate the blocks").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolBlock o = new SchoolBlock();
						o.setSiteRequest_(siteRequest_);

						// FormValues PUT
						{ e("form").a("action", "").a("id", "putSchoolBlockFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTSchoolBlock(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "putSchoolBlock($('#putSchoolBlockFormValues'), ", Optional.ofNullable(schoolBlock).map(SchoolBlock::getPk).map(a -> a.toString()).orElse("null"), "); ")
							.f().sx("Duplicate the blocks")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#patchSchoolBlockModal').show(); ")
			.f().sx("Modify the blocks")
		.g("button");
		{ e("div").a("id", "patchSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolBlockModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modify the blocks").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolBlock o = new SchoolBlock();
						o.setSiteRequest_(siteRequest_);

						// FormValues PATCH
						{ e("form").a("action", "").a("id", "patchSchoolBlockFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHSchoolBlock(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "patchSchoolBlock($('#patchSchoolBlockFormFilters'), $('#patchSchoolBlockFormValues'), ", Optional.ofNullable(schoolBlock).map(SchoolBlock::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
							.f().sx("Modify the blocks")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listSchoolBlock != null && listSchoolBlock.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#deleteSchoolBlockModal').show(); ")
				.f().sx("Delete the blocks")
			.g("button");
			{ e("div").a("id", "deleteSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolBlockModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Delete the blocks").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolBlock o = new SchoolBlock();
							o.setSiteRequest_(siteRequest_);

							// Form DELETE
							{ e("div").a("id", "deleteSchoolBlockForm").f();
								htmlFormPATCHSchoolBlock(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "deleteSchoolBlock(", o.getPk(), "); ")
								.f().sx("Delete the blocks")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestBlockGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("id", "refreshAllBlockGenPage", id).a("href", "/block").a("class", "").a("onclick", "patchSchoolBlockVals([], {}, function() { addGlow($('#refreshAllBlockGenPage", id, "')); }, function() { addError($('#refreshAllBlockGenPage", id, "')); }); return false; ").f();
				p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
				p.sx("refresh all the blocks");
			} p.g("a");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search blocks: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("class", "suggestSchoolBlock w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggestSchoolBlock")
				.a("id", "suggestSchoolBlock", id)
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolBlockObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolBlock", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolBlock", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/block").a("class", "").f();
				p.e("i").a("class", "far fa-bell ").f().g("i");
				p.sx("see all the blocks");
			} p.g("a");
		} p.g("div");
	}

}
