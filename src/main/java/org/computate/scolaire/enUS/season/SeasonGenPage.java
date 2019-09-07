



package org.computate.scolaire.enUS.season;

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


/**
 * Translate: false
 **/
public class SeasonGenPage extends SeasonGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolSeason(Wrap<SearchList<SchoolSeason>> c) {
	}

	protected void _schoolSeason(Wrap<SchoolSeason> c) {
		if(listSchoolSeason != null && listSchoolSeason.size() == 1)
			c.o(listSchoolSeason.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
		if(schoolSeason != null)
			c.o("a season");
		else if(listSchoolSeason == null || listSchoolSeason.size() == 0)
			c.o("no season found");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolSeason != null)
			c.o("");
		else if(listSchoolSeason == null || listSchoolSeason.size() == 0)
			c.o("no season found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enUS/season");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enUS/season-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("duotone");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("sun-o");
	}

	@Override public void initDeepSeasonGenPage() {
		initSeasonGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsSeasonGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/SeasonPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/YearPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SessionPage.js").f().g("script");
	}

	@Override public void htmlScriptSeasonGenPage() {
		l("$(document).ready(function() {");
		tl(1, "searchSchoolSeasonSessionKeys($('#formSchoolSeasonSessionKeys'), $('#listSchoolSeasonSessionKeys')); ");
		tl(1, "searchSchoolSeasonYearKey($('#formSchoolSeasonYearKey'), $('#listSchoolSeasonYearKey')); ");
		l("});");
	}

	public void htmlFormPageSchoolSeason(SchoolSeason o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("class", "").f().sx("primary key").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strPk()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("class", "").f().sx("created").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strCreated()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("class", "").f().sx("modified").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strModified()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strSeasonId()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalDate val = o.getSeasonStartDay();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_seasonStartDay").a("class", "").f().sx("start of the season").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row  ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border datepicker ")
										.a("placeholder", "MM/DD/YYYY")
										.a("data-timeformat", "MM/DD/YYYY")
										.a("id", "Page_seasonStartDay")
										.a("onclick", "removeGlow($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(val))
										.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonStartDay', s, function() { addGlow($('#Page_seasonStartDay')); }, function() { addError($('#Page_seasonStartDay')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_seasonStartDay')); $('#Page_seasonStartDay').val(null); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonStartDay', null, $('#Page_seasonStartDay'), function() { addGlow($('#Page_seasonStartDay')); }, function() { addError($('#Page_seasonStartDay')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSeasonSummer")
										.a("name", "setSeasonSummer")
										.a("id", "Page_seasonSummer")
										.a("onchange", "patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonSummer', $(this).prop('checked'), function() { addGlow($('#Page_seasonSummer')); }, function() { addError($('#Page_seasonSummer')); }); ")
										;
										if(o.getSeasonSummer() != null && o.getSeasonSummer())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_seasonSummer").a("class", "").f().sx("summer").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_seasonSummer')); $('#Page_seasonSummer').val(null); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonSummer', null, $('#Page_seasonSummer'), function() { addGlow($('#Page_seasonSummer')); }, function() { addError($('#Page_seasonSummer')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSeasonWinter")
										.a("name", "setSeasonWinter")
										.a("id", "Page_seasonWinter")
										.a("onchange", "patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonWinter', $(this).prop('checked'), function() { addGlow($('#Page_seasonWinter')); }, function() { addError($('#Page_seasonWinter')); }); ")
										;
										if(o.getSeasonWinter() != null && o.getSeasonWinter())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_seasonWinter").a("class", "").f().sx("winter").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_seasonWinter')); $('#Page_seasonWinter').val(null); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonWinter', null, $('#Page_seasonWinter'), function() { addGlow($('#Page_seasonWinter')); }, function() { addError($('#Page_seasonWinter')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_seasonEnrollmentFee").a("class", "").f().sx("enrollment fee").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "enrollment fee")
										.a("class", "setSeasonEnrollmentFee w3-input w3-border ")
										.a("name", "setSeasonEnrollmentFee")
										.a("id", "Page_seasonEnrollmentFee")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonEnrollmentFee', $(this).val(), function() { addGlow($('#Page_seasonEnrollmentFee')); }, function() { addError($('#Page_seasonEnrollmentFee')); }); ")
										.a("value", o.strSeasonEnrollmentFee())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_seasonEnrollmentFee')); $('#Page_seasonEnrollmentFee').val(null); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonEnrollmentFee', null, $('#Page_seasonEnrollmentFee'), function() { addGlow($('#Page_seasonEnrollmentFee')); }, function() { addError($('#Page_seasonEnrollmentFee')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valueSeasonKey")
							.a("class", "valueSeasonKey ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_sessionKeys").a("class", "").f().sx("sessions").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "sessions")
											.a("class", "valueObjectSuggest suggereSessionKeys w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setSessionKeys")
											.a("id", "Page_sessionKeys")
											.a("autocomplete", "off")
											.a("oninput", "searchSchoolSeasonSessionKeys($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonSessionKeys'), $('#listSchoolSeasonSessionKeys')); ")
										.fg();

									} g("div");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postSchoolSessionVals({ seasonKey: ", o.getPk(), " }, function() { var $e = $('#Page_sessionKeys'); $e.html($e.val()); }, function() { addError($('#Page_sessionKeys')); }); ")
											.f().sx("add")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonSessionKeys").f();
									} g("ul");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valueSeasonKeys")
							.a("class", "valueSeasonKeys ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_yearKey").a("class", "").f().sx("year").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "year")
											.a("class", "valueObjectSuggest suggereYearKey w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setYearKey")
											.a("id", "Page_yearKey")
											.a("autocomplete", "off")
											.a("oninput", "searchSchoolSeasonYearKey($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonYearKey'), $('#listSchoolSeasonYearKey')); ")
										.fg();

									} g("div");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postSchoolYearVals({ seasonKeys: ", o.getPk(), " }, function() { var $e = $('#Page_yearKey'); $e.html($e.val()); }, function() { addError($('#Page_yearKey')); }); ")
											.f().sx("add")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonYearKey").f();
									} g("ul");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTSchoolSeason(SchoolSeason o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("primary key").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("created").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCreated()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("modified").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModified()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSeasonId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSeasonStartDay();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_seasonStartDay").a("class", "").f().sx("start of the season").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "MM/DD/YYYY")
									.a("data-timeformat", "MM/DD/YYYY")
									.a("id", "POST_seasonStartDay")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonStartDay', s, function() { addGlow($('#POST_seasonStartDay')); }, function() { addError($('#POST_seasonStartDay')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueSeasonSummer")
									.a("name", "seasonSummer")
									.a("id", "POST_seasonSummer")
									;
									if(o.getSeasonSummer() != null && o.getSeasonSummer())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_seasonSummer").a("class", "").f().sx("summer").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueSeasonWinter")
									.a("name", "seasonWinter")
									.a("id", "POST_seasonWinter")
									;
									if(o.getSeasonWinter() != null && o.getSeasonWinter())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_seasonWinter").a("class", "").f().sx("winter").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_seasonEnrollmentFee").a("class", "").f().sx("enrollment fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "enrollment fee")
									.a("class", "valueSeasonEnrollmentFee w3-input w3-border ")
									.a("name", "seasonEnrollmentFee")
									.a("id", "POST_seasonEnrollmentFee")
									.a("value", o.strSeasonEnrollmentFee())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valueSeasonKey")
						.a("class", "valueSeasonKey ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_sessionKeys").a("class", "").f().sx("sessions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "sessions")
										.a("class", "valueObjectSuggest suggereSessionKeys w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setSessionKeys")
										.a("id", "POST_sessionKeys")
										.a("autocomplete", "off")
										.a("oninput", "searchSchoolSeasonSessionKeys($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonSessionKeys'), $('#listSchoolSeasonSessionKeys')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSchoolSessionVals({ seasonKey: ", o.getPk(), " }, function() { var $e = $('#POST_sessionKeys'); $e.html($e.val()); }, function() { addError($('#POST_sessionKeys')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonSessionKeys").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valueSeasonKeys")
						.a("class", "valueSeasonKeys ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_yearKey").a("class", "").f().sx("year").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "year")
										.a("class", "valueObjectSuggest suggereYearKey w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setYearKey")
										.a("id", "POST_yearKey")
										.a("autocomplete", "off")
										.a("oninput", "searchSchoolSeasonYearKey($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonYearKey'), $('#listSchoolSeasonYearKey')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSchoolYearVals({ seasonKeys: ", o.getPk(), " }, function() { var $e = $('#POST_yearKey'); $e.html($e.val()); }, function() { addError($('#POST_yearKey')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonYearKey").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHSchoolSeason(SchoolSeason o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("primary key").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("created").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCreated()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("modified").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModified()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSeasonId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSeasonStartDay();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_seasonStartDay").a("class", "").f().sx("start of the season").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "MM/DD/YYYY")
									.a("data-timeformat", "MM/DD/YYYY")
									.a("id", "PATCH_seasonStartDay")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonStartDay', s, function() { addGlow($('#PATCH_seasonStartDay')); }, function() { addError($('#PATCH_seasonStartDay')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setSeasonSummer")
									.a("name", "setSeasonSummer")
									.a("id", "PATCH_seasonSummer")
									;
									if(o.getSeasonSummer() != null && o.getSeasonSummer())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_seasonSummer").a("class", "").f().sx("summer").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setSeasonWinter")
									.a("name", "setSeasonWinter")
									.a("id", "PATCH_seasonWinter")
									;
									if(o.getSeasonWinter() != null && o.getSeasonWinter())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_seasonWinter").a("class", "").f().sx("winter").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_seasonEnrollmentFee").a("class", "").f().sx("enrollment fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "enrollment fee")
									.a("class", "setSeasonEnrollmentFee w3-input w3-border ")
									.a("name", "setSeasonEnrollmentFee")
									.a("id", "PATCH_seasonEnrollmentFee")
									.a("value", o.strSeasonEnrollmentFee())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valueSeasonKey")
						.a("class", "valueSeasonKey ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_sessionKeys").a("class", "").f().sx("sessions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "sessions")
										.a("class", "valueObjectSuggest suggereSessionKeys w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setSessionKeys")
										.a("id", "PATCH_sessionKeys")
										.a("autocomplete", "off")
										.a("oninput", "searchSchoolSeasonSessionKeys($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonSessionKeys'), $('#listSchoolSeasonSessionKeys')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSchoolSessionVals({ seasonKey: ", o.getPk(), " }, function() { var $e = $('#PATCH_sessionKeys'); $e.html($e.val()); }, function() { addError($('#PATCH_sessionKeys')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonSessionKeys").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valueSeasonKeys")
						.a("class", "valueSeasonKeys ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_yearKey").a("class", "").f().sx("year").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "year")
										.a("class", "valueObjectSuggest suggereYearKey w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setYearKey")
										.a("id", "PATCH_yearKey")
										.a("autocomplete", "off")
										.a("oninput", "searchSchoolSeasonYearKey($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonYearKey'), $('#listSchoolSeasonYearKey')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSchoolYearVals({ seasonKeys: ", o.getPk(), " }, function() { var $e = $('#PATCH_yearKey'); $e.html($e.val()); }, function() { addError($('#PATCH_yearKey')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonYearKey").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormSearchSchoolSeason(SchoolSeason o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("primary key").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("created").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCreated()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("modified").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModified()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSeasonId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonStartDay").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSeasonStartDay();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_seasonStartDay").a("class", "").f().sx("start of the season").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "MM/DD/YYYY")
									.a("data-timeformat", "MM/DD/YYYY")
									.a("id", "Recherche_seasonStartDay")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=\"pk\"]').val() }], 'setSeasonStartDay', s, function() { addGlow($('#Recherche_seasonStartDay')); }, function() { addError($('#Recherche_seasonStartDay')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonSummer").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueSeasonSummer")
									.a("name", "seasonSummer")
									.a("id", "Recherche_seasonSummer")
									;
									if(o.getSeasonSummer() != null && o.getSeasonSummer())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_seasonSummer").a("class", "").f().sx("summer").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonWinter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueSeasonWinter")
									.a("name", "seasonWinter")
									.a("id", "Recherche_seasonWinter")
									;
									if(o.getSeasonWinter() != null && o.getSeasonWinter())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_seasonWinter").a("class", "").f().sx("winter").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSeasonEnrollmentFee").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_seasonEnrollmentFee").a("class", "").f().sx("enrollment fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "enrollment fee")
									.a("class", "valueSeasonEnrollmentFee w3-input w3-border ")
									.a("name", "seasonEnrollmentFee")
									.a("id", "Recherche_seasonEnrollmentFee")
									.a("value", o.strSeasonEnrollmentFee())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valueSeasonKey")
						.a("class", "valueSeasonKey ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonSessionKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_sessionKeys").a("class", "").f().sx("sessions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "sessions")
										.a("class", "valueObjectSuggest suggereSessionKeys w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setSessionKeys")
										.a("id", "Recherche_sessionKeys")
										.a("autocomplete", "off")
										.a("oninput", "searchSchoolSeasonSessionKeys($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonSessionKeys'), $('#listSchoolSeasonSessionKeys')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSchoolSessionVals({ seasonKey: ", o.getPk(), " }, function() { var $e = $('#Recherche_sessionKeys'); $e.html($e.val()); }, function() { addError($('#Recherche_sessionKeys')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonSessionKeys").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valueSeasonKeys")
						.a("class", "valueSeasonKeys ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolSeasonYearKey").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_yearKey").a("class", "").f().sx("year").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "year")
										.a("class", "valueObjectSuggest suggereYearKey w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setYearKey")
										.a("id", "Recherche_yearKey")
										.a("autocomplete", "off")
										.a("oninput", "searchSchoolSeasonYearKey($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolSeasonYearKey'), $('#listSchoolSeasonYearKey')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSchoolYearVals({ seasonKeys: ", o.getPk(), " }, function() { var $e = $('#Recherche_yearKey'); $e.html($e.val()); }, function() { addError($('#Recherche_yearKey')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonYearKey").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodySeasonGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolSeason == null || listSchoolSeason.size() == 0) {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("no season found").g("span");
			} g("h1");
		} else if(listSchoolSeason != null && listSchoolSeason.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("a season").g("span");
				} g("h1");
				SchoolSeason o = listSchoolSeason.get(0);
				siteRequest_.setRequestPk(o.getPk());
			}
		} else {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("seasons").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("year").g("th");
						e("th").f().sx("start of the season").g("th");
						e("th").f().sx("summer").g("th");
						e("th").f().sx("winter").g("th");
						e("th").f().sx("enrollment fee").g("th");
						e("th").f().sx("sessions").g("th");
						e("th").f().sx("primary key").g("th");
						e("th").f().sx("created").g("th");
						e("th").f().sx("modified").g("th");
						e("th").f().sx("ID").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolSeason.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolSeason.size(); i++) {
						SchoolSeason o = listSchoolSeason.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enUS/season/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getYearKey());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSeasonStartDay());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSeasonSummer());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSeasonWinter());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSeasonEnrollmentFee());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSessionKeys());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getPk());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getCreated());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getModified());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSeasonId());
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listSchoolSeason != null && listSchoolSeason.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolSeason o = listSchoolSeason.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolSeasonForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageSchoolSeason(o);
				}

			} g("div");
		}
		htmlBodyFormsSeasonGenPage();
	}

	public void htmlBodyFormsSeasonGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postSchoolSeasonModal').show(); ")
			.f().sx("Create a season")
		.g("button");
		{ e("div").a("id", "postSchoolSeasonModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolSeasonModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Create a season").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolSeason o = new SchoolSeason();

					// Form POST
					{ e("form").a("action", "").a("id", "postSchoolSeasonForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTSchoolSeason(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postSchoolSeason($('#postSchoolSeasonForm')); ")
						.f().sx("Create a season")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchSchoolSeasonModal').show(); ")
			.f().sx("Modify the seasons")
		.g("button");
		{ e("div").a("id", "patchSchoolSeasonModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolSeasonModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modify the seasons").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolSeason o = new SchoolSeason();

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolSeasonFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolSeason(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "aSearchSchoolSeason($('#patchSchoolSeasonFormFilters')); ")
						.f().sx("Search the a season")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolSeasonFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolSeason(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchSchoolSeason($('#patchSchoolSeasonFormFilters'), $('#patchSchoolSeasonFormValues')); ")
						.f().sx("Modify the seasons")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#deleteSchoolSeasonModal').show(); ")
			.f().sx("Delete the seasons")
		.g("button");
		{ e("div").a("id", "deleteSchoolSeasonModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolSeasonModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Delete the seasons").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolSeason o = new SchoolSeason();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteSchoolSeasonForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolSeason(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "deleteSchoolSeason(); ")
						.f().sx("Delete the seasons")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
