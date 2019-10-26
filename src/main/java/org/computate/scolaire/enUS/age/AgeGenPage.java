package org.computate.scolaire.enUS.age;

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
public class AgeGenPage extends AgeGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolAge(Wrap<SearchList<SchoolAge>> c) {
	}

	protected void _schoolAge(Wrap<SchoolAge> c) {
		if(listSchoolAge != null && listSchoolAge.size() == 1)
			c.o(listSchoolAge.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("ages");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolAge != null && schoolAge.getAgeCompleteName() != null)
			c.o(schoolAge.getAgeCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolAge != null && schoolAge.getAgeCompleteName() != null)
			c.o(schoolAge.getAgeCompleteName());
		else if(schoolAge != null)
			c.o("");
		else if(listSchoolAge == null || listSchoolAge.size() == 0)
			c.o("no age found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/age");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/age-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("duotone");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("birthday-cake");
	}

	@Override public void initDeepAgeGenPage() {
		initAgeGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsAgeGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/AgePage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
	}

	@Override public void htmlScriptAgeGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggestSchoolAgeBlockKeys($('#formSchoolAgeBlockKeys'), $('#listSchoolAgeBlockKeys_Page')); ");
		l("});");
	}

	public void htmlFormPageSchoolAge(SchoolAge o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strObjectId()).g("span");
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
					{ e("form").a("action", "").a("id", "formSchoolAgeArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolAgeArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-blue ").f();
								e("label").a("for", "Page_archived").a("class", "").f().sx("archived").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setArchived")
										.a("name", "setArchived")
										.a("id", "Page_archived")
										.a("onchange", "patchSchoolAgeVal([{ name: 'fq', value: 'pk:' + $('#SchoolAgeForm :input[name=\"pk\"]').val() }], 'setArchived', $(this).prop('checked'), function() { addGlow($('#Page_archived')); }, function() { addError($('#Page_archived')); }); ")
										;
										if(o.getArchived() != null && o.getArchived())
											a("checked", "checked");
									fg();

								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolAgeDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolAgeDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-blue ").f();
								e("label").a("for", "Page_deleted").a("class", "").f().sx("deleted").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setDeleted")
										.a("name", "setDeleted")
										.a("id", "Page_deleted")
										.a("onchange", "patchSchoolAgeVal([{ name: 'fq', value: 'pk:' + $('#SchoolAgeForm :input[name=\"pk\"]').val() }], 'setDeleted', $(this).prop('checked'), function() { addGlow($('#Page_deleted')); }, function() { addError($('#Page_deleted')); }); ")
										;
										if(o.getDeleted() != null && o.getDeleted())
											a("checked", "checked");
									fg();

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
					{ e("form").a("action", "").a("id", "formSchoolAgeAgeStart").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-blue ").f();
								e("label").a("for", "Page_ageStart").a("class", "").f().sx("start of the age group").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "start of the age group")
										.a("class", "setAgeStart w3-input w3-border ")
										.a("name", "setAgeStart")
										.a("id", "Page_ageStart")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchSchoolAgeVal([{ name: 'fq', value: 'pk:' + $('#SchoolAgeForm :input[name=\"pk\"]').val() }], 'setAgeStart', $(this).val(), function() { addGlow($('#Page_ageStart')); }, function() { addError($('#Page_ageStart')); }); ")
										.a("value", o.strAgeStart())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue ")
									.a("onclick", "removeGlow($('#Page_ageStart')); $('#Page_ageStart').val(null); patchSchoolAgeVal([{ name: 'fq', value: 'pk:' + $('#SchoolAgeForm :input[name=\"pk\"]').val() }], 'setAgeStart', null, function() { addGlow($('#Page_ageStart')); }, function() { addError($('#Page_ageStart')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolAgeAgeEnd").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-blue ").f();
								e("label").a("for", "Page_ageEnd").a("class", "").f().sx("end of the age group").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "end of the age group")
										.a("class", "setAgeEnd w3-input w3-border ")
										.a("name", "setAgeEnd")
										.a("id", "Page_ageEnd")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchSchoolAgeVal([{ name: 'fq', value: 'pk:' + $('#SchoolAgeForm :input[name=\"pk\"]').val() }], 'setAgeEnd', $(this).val(), function() { addGlow($('#Page_ageEnd')); }, function() { addError($('#Page_ageEnd')); }); ")
										.a("value", o.strAgeEnd())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue ")
									.a("onclick", "removeGlow($('#Page_ageEnd')); $('#Page_ageEnd').val(null); patchSchoolAgeVal([{ name: 'fq', value: 'pk:' + $('#SchoolAgeForm :input[name=\"pk\"]').val() }], 'setAgeEnd', null, function() { addGlow($('#Page_ageEnd')); }, function() { addError($('#Page_ageEnd')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolAgeBlockKeys").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valueAgeKey")
							.a("class", "valueAgeKey ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolAgeBlockKeys").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "/block").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
									e("i").a("class", "far fa-bell w3-padding-small ").f().g("i");
									sx("blocks");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relate blocks to this age");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "blocks")
											.a("class", "valueObjectSuggest suggestBlockKeys w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setBlockKeys")
											.a("id", "Page_blockKeys")
											.a("autocomplete", "off")
											.a("oninput", "suggestSchoolAgeBlockKeys($('#' + ($(this).val() ? 'suggest' : 'form') + 'SchoolAgeBlockKeys'), $('#listSchoolAgeBlockKeys_Page')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolAgeBlockKeys_Page").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
											.a("onclick", "postSchoolBlockVals({ ageKey: \"", o.getPk(), "\" }, function() { patchSchoolAgeVals([{ name: 'fq', value: 'pk:", o.getPk(), "' }], {}, function() { suggestSchoolAgeBlockKeys($('#' + ($('#Page_blockKeys').val() ? 'suggest' : 'form') + 'SchoolAgeBlockKeys'), $('#listSchoolAgeBlockKeys_Page')); var $e = $('#Page_blockKeys'); $e.html($e.val()); }, function() { addError($('#Page_blockKeys')); }); }, function() { addError($('#Page_blockKeys')); });")
											.f().sx("add a block")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTSchoolAge(SchoolAge o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjectId()).g("span");
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
				{ e("form").a("action", "").a("id", "formSchoolAgeArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "POST_archived").a("class", "").f().sx("archived").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueArchived")
									.a("name", "archived")
									.a("id", "POST_archived")
									;
									if(o.getArchived() != null && o.getArchived())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolAgeDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "POST_deleted").a("class", "").f().sx("deleted").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueDeleted")
									.a("name", "deleted")
									.a("id", "POST_deleted")
									;
									if(o.getDeleted() != null && o.getDeleted())
										a("checked", "checked");
								fg();

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
				{ e("form").a("action", "").a("id", "formSchoolAgeAgeStart").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "POST_ageStart").a("class", "").f().sx("start of the age group").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "start of the age group")
									.a("class", "valueAgeStart w3-input w3-border ")
									.a("name", "ageStart")
									.a("id", "POST_ageStart")
									.a("value", o.strAgeStart())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolAgeAgeEnd").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "POST_ageEnd").a("class", "").f().sx("end of the age group").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "end of the age group")
									.a("class", "valueAgeEnd w3-input w3-border ")
									.a("name", "ageEnd")
									.a("id", "POST_ageEnd")
									.a("value", o.strAgeEnd())
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("blocks").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strBlockKeys()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHSchoolAge(SchoolAge o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjectId()).g("span");
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
				{ e("form").a("action", "").a("id", "formSchoolAgeArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "PATCH_archived").a("class", "").f().sx("archived").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setArchived")
									.a("name", "setArchived")
									.a("id", "PATCH_archived")
									;
									if(o.getArchived() != null && o.getArchived())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolAgeDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "PATCH_deleted").a("class", "").f().sx("deleted").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setDeleted")
									.a("name", "setDeleted")
									.a("id", "PATCH_deleted")
									;
									if(o.getDeleted() != null && o.getDeleted())
										a("checked", "checked");
								fg();

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
				{ e("form").a("action", "").a("id", "formSchoolAgeAgeStart").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "PATCH_ageStart").a("class", "").f().sx("start of the age group").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "start of the age group")
									.a("class", "setAgeStart w3-input w3-border ")
									.a("name", "setAgeStart")
									.a("id", "PATCH_ageStart")
									.a("value", o.strAgeStart())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolAgeAgeEnd").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "PATCH_ageEnd").a("class", "").f().sx("end of the age group").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "end of the age group")
									.a("class", "setAgeEnd w3-input w3-border ")
									.a("name", "setAgeEnd")
									.a("id", "PATCH_ageEnd")
									.a("value", o.strAgeEnd())
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("blocks").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strBlockKeys()).g("span");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("name").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeCompleteName()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormSearchSchoolAge(SchoolAge o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
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
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjectId()).g("span");
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
				{ e("form").a("action", "").a("id", "formSchoolAgeArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "Recherche_archived").a("class", "").f().sx("archived").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueArchived")
									.a("name", "archived")
									.a("id", "Recherche_archived")
									;
									if(o.getArchived() != null && o.getArchived())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolAgeDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "Recherche_deleted").a("class", "").f().sx("deleted").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueDeleted")
									.a("name", "deleted")
									.a("id", "Recherche_deleted")
									;
									if(o.getDeleted() != null && o.getDeleted())
										a("checked", "checked");
								fg();

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
				{ e("form").a("action", "").a("id", "formSchoolAgeAgeStart").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "Recherche_ageStart").a("class", "").f().sx("start of the age group").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "start of the age group")
									.a("class", "valueAgeStart w3-input w3-border ")
									.a("name", "ageStart")
									.a("id", "Recherche_ageStart")
									.a("value", o.strAgeStart())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolAgeAgeEnd").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolAgeAgeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", "Recherche_ageEnd").a("class", "").f().sx("end of the age group").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "end of the age group")
									.a("class", "valueAgeEnd w3-input w3-border ")
									.a("name", "ageEnd")
									.a("id", "Recherche_ageEnd")
									.a("value", o.strAgeEnd())
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("blocks").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strBlockKeys()).g("span");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-blue ").f();
						e("label").a("class", "").f().sx("name").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeCompleteName()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyAgeGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolAge == null || listSchoolAge.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("ages").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no age found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolAge != null && listSchoolAge.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolAge o = listSchoolAge.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolAge.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolAge.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolAge.getRows();
					Integer start1 = listSchoolAge.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/age?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-blue w3-hover-blue ").f();
					{ e("tr").f();
						e("th").f().sx("name").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolAge.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolAge.size(); i++) {
						SchoolAge o = listSchoolAge.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/age/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fad fa-birthday-cake w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strAgeCompleteName());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCreated());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listSchoolAge != null && listSchoolAge.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolAge o = listSchoolAge.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolAgeForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageSchoolAge(o);
				}

			} g("div");

		}
		htmlBodyFormsAgeGenPage();
		htmlSuggestAgeGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsAgeGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
				.a("id", "refreshThisAgeGenPage")
				.a("onclick", "patchSchoolAgeVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisAgeGenPage')); }, function() { addError($('#refreshThisAgeGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this age");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
			.a("onclick", "$('#postSchoolAgeModal').show(); ")
			.f().sx("Create an age")
		.g("button");
		{ e("div").a("id", "postSchoolAgeModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-blue ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolAgeModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create an age").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolAge o = new SchoolAge();

					// Form POST
					{ e("div").a("id", "postSchoolAgeForm").f();
						htmlFormPOSTSchoolAge(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
						.a("onclick", "postSchoolAge($('#postSchoolAgeForm')); ")
						.f().sx("Create an age")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
			.a("onclick", "$('#patchSchoolAgeModal').show(); ")
			.f().sx("Modify the ages")
		.g("button");
		{ e("div").a("id", "patchSchoolAgeModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-blue ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolAgeModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the ages").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolAge o = new SchoolAge();

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolAgeFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolAge(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
						.a("onclick", "aSearchSchoolAge($('#patchSchoolAgeFormFilters')); ")
						.f().sx("Search the an age")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolAgeFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolAge(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
						.a("onclick", "patchSchoolAge($('#patchSchoolAgeFormFilters'), $('#patchSchoolAgeFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the ages")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchoolAge != null && listSchoolAge.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
				.a("onclick", "$('#deleteSchoolAgeModal').show(); ")
				.f().sx("Delete the ages")
			.g("button");
			{ e("div").a("id", "deleteSchoolAgeModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-blue ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolAgeModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the ages").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolAge o = new SchoolAge();

						// Form DELETE
						{ e("div").a("id", "deleteSchoolAgeForm").f();
							htmlFormPATCHSchoolAge(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
							.a("onclick", "deleteSchoolAge(", o.getPk(), "); ")
							.f().sx("Delete the ages")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestAgeGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/age").a("class", "").f();
					p.e("i").a("class", "fad fa-birthday-cake w3-padding-small ").f().g("i");
					p.sx("see all the ages");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllAgeGenPage", id).a("href", "/age").a("class", "").a("onclick", "patchSchoolAgeVals([], {}, function() { addGlow($('#refreshAllAgeGenPage", id, "')); }, function() { addError($('#refreshAllAgeGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the ages");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search ages: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolAge", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/age?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolAge", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchoolAge w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchoolAge")
							.a("id", "suggestSchoolAge", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolAgeObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolAge", id, "')); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolAge", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
