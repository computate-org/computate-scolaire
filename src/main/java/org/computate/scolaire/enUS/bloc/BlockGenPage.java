package org.computate.scolaire.enUS.bloc;

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
		if(schoolBlock != null)
			c.o("a block");
		else if(listSchoolBlock == null || listSchoolBlock.size() == 0)
			c.o("no block found");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolBlock != null)
			c.o("");
		else if(listSchoolBlock == null || listSchoolBlock.size() == 0)
			c.o("no block found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enUS/block");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enUS/block-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("duotone");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("bell-o");
	}

	@Override public void initDeepBlockGenPage() {
		initBlockGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsBlockGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
	}

	@Override public void htmlScriptBlockGenPage() {
		l("$(document).ready(function() {");
		l("});");
	}

	public void htmlFormPageSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strAgeId()).g("span");
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
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("class", "").f().sx("school").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strSchoolKey()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("class", "").f().sx("key").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strAgeKey()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setAgeStart', $(this).val(), function() { addGlow($('#Page_ageStart')); }, function() { ajouterErreur($('#Page_ageStart')); }); ")
										.a("value", o.strAgeStart())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_ageStart')); $('#Page_ageStart').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setAgeStart', null, $('#Page_ageStart'), function() { addGlow($('#Page_ageStart')); }, function() { ajouterErreur($('#Page_ageStart')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setAgeEnd', $(this).val(), function() { addGlow($('#Page_ageEnd')); }, function() { ajouterErreur($('#Page_ageEnd')); }); ")
										.a("value", o.strAgeEnd())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_ageEnd')); $('#Page_ageEnd').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setAgeEnd', null, $('#Page_ageEnd'), function() { addGlow($('#Page_ageEnd')); }, function() { ajouterErreur($('#Page_ageEnd')); }); ")
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
	}

	public void htmlFormPOSTSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeId()).g("span");
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("school").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSchoolKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("key").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
				{ e("form").a("action", "").a("id", "formSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
	}

	public void htmlFormPATCHSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeId()).g("span");
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("school").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSchoolKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("key").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
				{ e("form").a("action", "").a("id", "formSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
	}

	public void htmlFormSearchSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeId()).g("span");
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("school").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSchoolKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("key").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strAgeKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeStart").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
				{ e("form").a("action", "").a("id", "formSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockAgeEnd").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
	}

	@Override public void htmlBodyBlockGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolBlock == null || listSchoolBlock.size() == 0) {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("no block found").g("span");
			} g("h1");
		} else if(listSchoolBlock != null && listSchoolBlock.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("a block").g("span");
				} g("h1");
				SchoolBlock o = listSchoolBlock.get(0);
				siteRequest_.setRequestPk(o.getPk());
			}
		} else {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("blocks").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("start of the age group").g("th");
						e("th").f().sx("end of the age group").g("th");
						e("th").f().sx("primary key").g("th");
						e("th").f().sx("created").g("th");
						e("th").f().sx("school").g("th");
						e("th").f().sx("modified").g("th");
						e("th").f().sx("key").g("th");
						e("th").f().sx("ID").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolBlock.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolBlock.size(); i++) {
						SchoolBlock o = listSchoolBlock.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enUS/block/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getAgeStart());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getAgeEnd());
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
									sx(o.getSchoolKey());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getModified());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getAgeKey());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getAgeId());
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
					{ e("form").a("action", "").a("id", "SchoolBlockForm").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageSchoolBlock(o);
				}

			} g("div");
		}
		htmlBodyFormsBlockGenPage();
	}

	public void htmlBodyFormsBlockGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#postSchoolBlockModal').show(); ")
			.f().sx("Create a block")
		.g("button");
		{ e("div").a("id", "postSchoolBlockModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-indigo ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolBlockModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Create a block").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolBlock o = new SchoolBlock();

					// Form POST
					{ e("form").a("action", "").a("id", "postSchoolBlockForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTSchoolBlock(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "postSchoolBlock($('#postSchoolBlockForm')); ")
						.f().sx("Create a block")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#patchSchoolBlockModal').show(); ")
			.f().sx("Modify the blocks")
		.g("button");
		{ e("div").a("id", "patchSchoolBlockModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-indigo ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolBlockModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modify the blocks").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolBlock o = new SchoolBlock();

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolBlockFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolBlock(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "aSearchSchoolBlock($('#patchSchoolBlockFormFilters')); ")
						.f().sx("Search the a block")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolBlockFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolBlock(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "patchSchoolBlock($('#patchSchoolBlockFormFilters'), $('#patchSchoolBlockFormValues')); ")
						.f().sx("Modify the blocks")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#deleteSchoolBlockModal').show(); ")
			.f().sx("Delete the blocks")
		.g("button");
		{ e("div").a("id", "deleteSchoolBlockModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-indigo ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolBlockModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Delete the blocks").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolBlock o = new SchoolBlock();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteSchoolBlockForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolBlock(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "deleteSchoolBlock(); ")
						.f().sx("Delete the blocks")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
