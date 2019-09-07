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
									e("span").f().sx(o.strBlocId()).g("span");
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
									e("span").f().sx(o.strBlockKey()).g("span");
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
							e("label").a("class", "").f().sx("enrollments").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strEnrollmentKeys()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalTime val = o.getBlockTimeStart();

							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockTimeStart").a("class", "").f().sx("start time").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border timepicker ")
										.a("placeholder", "HH:MM AM")
										.a("id", "Page_blockTimeStart")
										.a("onclick", "removeGlow($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
										.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeStart', s, function() { addGlow($('#Page_blockTimeStart')); }, function() { ajouterErreur($('#Page_blockTimeStart')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockTimeStart')); $('#Page_blockTimeStart').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeStart', null, $('#Page_blockTimeStart'), function() { addGlow($('#Page_blockTimeStart')); }, function() { ajouterErreur($('#Page_blockTimeStart')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalTime val = o.getBlockTimeEnd();

							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockTimeEnd").a("class", "").f().sx("end time").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border timepicker ")
										.a("placeholder", "HH:MM AM")
										.a("id", "Page_blockTimeEnd")
										.a("onclick", "removeGlow($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
										.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeEnd', s, function() { addGlow($('#Page_blockTimeEnd')); }, function() { ajouterErreur($('#Page_blockTimeEnd')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockTimeEnd')); $('#Page_blockTimeEnd').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeEnd', null, $('#Page_blockTimeEnd'), function() { addGlow($('#Page_blockTimeEnd')); }, function() { ajouterErreur($('#Page_blockTimeEnd')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "blockSunday")
										.a("id", "Page_blockSunday")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setBlockSunday")
										.a("name", "setBlockSunday")
										.a("id", "Page_blockSunday")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockSunday', $(this).val(), function() { addGlow($('#Page_blockSunday')); }, function() { ajouterErreur($('#Page_blockSunday')); }); ")
										;
										if(o.getBlockSunday() != null && o.getBlockSunday())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockSunday").a("class", "").f().sx("sunday").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockSunday')); $('#Page_blockSunday').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockSunday', null, $('#Page_blockSunday'), function() { addGlow($('#Page_blockSunday')); }, function() { ajouterErreur($('#Page_blockSunday')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "blockMonday")
										.a("id", "Page_blockMonday")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setBlockMonday")
										.a("name", "setBlockMonday")
										.a("id", "Page_blockMonday")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockMonday', $(this).val(), function() { addGlow($('#Page_blockMonday')); }, function() { ajouterErreur($('#Page_blockMonday')); }); ")
										;
										if(o.getBlockMonday() != null && o.getBlockMonday())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockMonday").a("class", "").f().sx("monday").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockMonday')); $('#Page_blockMonday').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockMonday', null, $('#Page_blockMonday'), function() { addGlow($('#Page_blockMonday')); }, function() { ajouterErreur($('#Page_blockMonday')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "blockTuesday")
										.a("id", "Page_blockTuesday")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setBlockTuesday")
										.a("name", "setBlockTuesday")
										.a("id", "Page_blockTuesday")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTuesday', $(this).val(), function() { addGlow($('#Page_blockTuesday')); }, function() { ajouterErreur($('#Page_blockTuesday')); }); ")
										;
										if(o.getBlockTuesday() != null && o.getBlockTuesday())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockTuesday").a("class", "").f().sx("tuesday").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockTuesday')); $('#Page_blockTuesday').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTuesday', null, $('#Page_blockTuesday'), function() { addGlow($('#Page_blockTuesday')); }, function() { ajouterErreur($('#Page_blockTuesday')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "blockWednesday")
										.a("id", "Page_blockWednesday")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setBlockWednesday")
										.a("name", "setBlockWednesday")
										.a("id", "Page_blockWednesday")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockWednesday', $(this).val(), function() { addGlow($('#Page_blockWednesday')); }, function() { ajouterErreur($('#Page_blockWednesday')); }); ")
										;
										if(o.getBlockWednesday() != null && o.getBlockWednesday())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockWednesday").a("class", "").f().sx("wednesday").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockWednesday')); $('#Page_blockWednesday').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockWednesday', null, $('#Page_blockWednesday'), function() { addGlow($('#Page_blockWednesday')); }, function() { ajouterErreur($('#Page_blockWednesday')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "blockThursday")
										.a("id", "Page_blockThursday")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setBlockThursday")
										.a("name", "setBlockThursday")
										.a("id", "Page_blockThursday")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockThursday', $(this).val(), function() { addGlow($('#Page_blockThursday')); }, function() { ajouterErreur($('#Page_blockThursday')); }); ")
										;
										if(o.getBlockThursday() != null && o.getBlockThursday())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockThursday").a("class", "").f().sx("thursday").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockThursday')); $('#Page_blockThursday').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockThursday', null, $('#Page_blockThursday'), function() { addGlow($('#Page_blockThursday')); }, function() { ajouterErreur($('#Page_blockThursday')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "blockFriday")
										.a("id", "Page_blockFriday")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setBlockFriday")
										.a("name", "setBlockFriday")
										.a("id", "Page_blockFriday")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockFriday', $(this).val(), function() { addGlow($('#Page_blockFriday')); }, function() { ajouterErreur($('#Page_blockFriday')); }); ")
										;
										if(o.getBlockFriday() != null && o.getBlockFriday())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockFriday").a("class", "").f().sx("friday").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockFriday')); $('#Page_blockFriday').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockFriday', null, $('#Page_blockFriday'), function() { addGlow($('#Page_blockFriday')); }, function() { ajouterErreur($('#Page_blockFriday')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "blockSaturday")
										.a("id", "Page_blockSaturday")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setBlockSaturday")
										.a("name", "setBlockSaturday")
										.a("id", "Page_blockSaturday")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockSaturday', $(this).val(), function() { addGlow($('#Page_blockSaturday')); }, function() { ajouterErreur($('#Page_blockSaturday')); }); ")
										;
										if(o.getBlockSaturday() != null && o.getBlockSaturday())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockSaturday").a("class", "").f().sx("saturday").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockSaturday')); $('#Page_blockSaturday').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockSaturday', null, $('#Page_blockSaturday'), function() { addGlow($('#Page_blockSaturday')); }, function() { ajouterErreur($('#Page_blockSaturday')); }); ")
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
					{ e("form").a("action", "").a("id", "formSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_blockPricePerMonth").a("class", "").f().sx("price per month").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "price per month")
										.a("class", "setBlockPricePerMonth w3-input w3-border ")
										.a("name", "setBlockPricePerMonth")
										.a("id", "Page_blockPricePerMonth")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockPricePerMonth', $(this).val(), function() { addGlow($('#Page_blockPricePerMonth')); }, function() { ajouterErreur($('#Page_blockPricePerMonth')); }); ")
										.a("value", o.strBlockPricePerMonth())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#Page_blockPricePerMonth')); $('#Page_blockPricePerMonth').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockPricePerMonth', null, $('#Page_blockPricePerMonth'), function() { addGlow($('#Page_blockPricePerMonth')); }, function() { ajouterErreur($('#Page_blockPricePerMonth')); }); ")
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
								e("span").f().sx(o.strBlocId()).g("span");
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
								e("span").f().sx(o.strBlockKey()).g("span");
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
						e("label").a("class", "").f().sx("enrollments").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnrollmentKeys()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlockTimeStart();

						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockTimeStart").a("class", "").f().sx("start time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "POST_blockTimeStart")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeStart', s, function() { addGlow($('#POST_blockTimeStart')); }, function() { ajouterErreur($('#POST_blockTimeStart')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlockTimeEnd();

						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockTimeEnd").a("class", "").f().sx("end time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "POST_blockTimeEnd")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeEnd', s, function() { addGlow($('#POST_blockTimeEnd')); }, function() { ajouterErreur($('#POST_blockTimeEnd')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockSunday")
									.a("id", "POST_blockSunday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockSunday")
									.a("name", "blockSunday")
									.a("id", "POST_blockSunday")
									;
									if(o.getBlockSunday() != null && o.getBlockSunday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockSunday").a("class", "").f().sx("sunday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockMonday")
									.a("id", "POST_blockMonday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockMonday")
									.a("name", "blockMonday")
									.a("id", "POST_blockMonday")
									;
									if(o.getBlockMonday() != null && o.getBlockMonday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockMonday").a("class", "").f().sx("monday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockTuesday")
									.a("id", "POST_blockTuesday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockTuesday")
									.a("name", "blockTuesday")
									.a("id", "POST_blockTuesday")
									;
									if(o.getBlockTuesday() != null && o.getBlockTuesday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockTuesday").a("class", "").f().sx("tuesday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockWednesday")
									.a("id", "POST_blockWednesday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockWednesday")
									.a("name", "blockWednesday")
									.a("id", "POST_blockWednesday")
									;
									if(o.getBlockWednesday() != null && o.getBlockWednesday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockWednesday").a("class", "").f().sx("wednesday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockThursday")
									.a("id", "POST_blockThursday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockThursday")
									.a("name", "blockThursday")
									.a("id", "POST_blockThursday")
									;
									if(o.getBlockThursday() != null && o.getBlockThursday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockThursday").a("class", "").f().sx("thursday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockFriday")
									.a("id", "POST_blockFriday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockFriday")
									.a("name", "blockFriday")
									.a("id", "POST_blockFriday")
									;
									if(o.getBlockFriday() != null && o.getBlockFriday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockFriday").a("class", "").f().sx("friday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockSaturday")
									.a("id", "POST_blockSaturday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockSaturday")
									.a("name", "blockSaturday")
									.a("id", "POST_blockSaturday")
									;
									if(o.getBlockSaturday() != null && o.getBlockSaturday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockSaturday").a("class", "").f().sx("saturday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_blockPricePerMonth").a("class", "").f().sx("price per month").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "price per month")
									.a("class", "valueBlockPricePerMonth w3-input w3-border ")
									.a("name", "blockPricePerMonth")
									.a("id", "POST_blockPricePerMonth")
									.a("value", o.strBlockPricePerMonth())
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
								e("span").f().sx(o.strBlocId()).g("span");
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
								e("span").f().sx(o.strBlockKey()).g("span");
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
						e("label").a("class", "").f().sx("enrollments").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnrollmentKeys()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlockTimeStart();

						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockTimeStart").a("class", "").f().sx("start time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "PATCH_blockTimeStart")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeStart', s, function() { addGlow($('#PATCH_blockTimeStart')); }, function() { ajouterErreur($('#PATCH_blockTimeStart')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlockTimeEnd();

						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockTimeEnd").a("class", "").f().sx("end time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "PATCH_blockTimeEnd")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeEnd', s, function() { addGlow($('#PATCH_blockTimeEnd')); }, function() { ajouterErreur($('#PATCH_blockTimeEnd')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockSunday")
									.a("id", "PATCH_blockSunday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setBlockSunday")
									.a("name", "setBlockSunday")
									.a("id", "PATCH_blockSunday")
									;
									if(o.getBlockSunday() != null && o.getBlockSunday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockSunday").a("class", "").f().sx("sunday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockMonday")
									.a("id", "PATCH_blockMonday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setBlockMonday")
									.a("name", "setBlockMonday")
									.a("id", "PATCH_blockMonday")
									;
									if(o.getBlockMonday() != null && o.getBlockMonday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockMonday").a("class", "").f().sx("monday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockTuesday")
									.a("id", "PATCH_blockTuesday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setBlockTuesday")
									.a("name", "setBlockTuesday")
									.a("id", "PATCH_blockTuesday")
									;
									if(o.getBlockTuesday() != null && o.getBlockTuesday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockTuesday").a("class", "").f().sx("tuesday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockWednesday")
									.a("id", "PATCH_blockWednesday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setBlockWednesday")
									.a("name", "setBlockWednesday")
									.a("id", "PATCH_blockWednesday")
									;
									if(o.getBlockWednesday() != null && o.getBlockWednesday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockWednesday").a("class", "").f().sx("wednesday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockThursday")
									.a("id", "PATCH_blockThursday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setBlockThursday")
									.a("name", "setBlockThursday")
									.a("id", "PATCH_blockThursday")
									;
									if(o.getBlockThursday() != null && o.getBlockThursday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockThursday").a("class", "").f().sx("thursday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockFriday")
									.a("id", "PATCH_blockFriday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setBlockFriday")
									.a("name", "setBlockFriday")
									.a("id", "PATCH_blockFriday")
									;
									if(o.getBlockFriday() != null && o.getBlockFriday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockFriday").a("class", "").f().sx("friday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockSaturday")
									.a("id", "PATCH_blockSaturday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setBlockSaturday")
									.a("name", "setBlockSaturday")
									.a("id", "PATCH_blockSaturday")
									;
									if(o.getBlockSaturday() != null && o.getBlockSaturday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockSaturday").a("class", "").f().sx("saturday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_blockPricePerMonth").a("class", "").f().sx("price per month").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "price per month")
									.a("class", "setBlockPricePerMonth w3-input w3-border ")
									.a("name", "setBlockPricePerMonth")
									.a("id", "PATCH_blockPricePerMonth")
									.a("value", o.strBlockPricePerMonth())
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
								e("span").f().sx(o.strBlocId()).g("span");
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
								e("span").f().sx(o.strBlockKey()).g("span");
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
						e("label").a("class", "").f().sx("enrollments").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnrollmentKeys()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeStart").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlockTimeStart();

						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockTimeStart").a("class", "").f().sx("start time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "Recherche_blockTimeStart")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeStart', s, function() { addGlow($('#Recherche_blockTimeStart')); }, function() { ajouterErreur($('#Recherche_blockTimeStart')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTimeEnd").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlockTimeEnd();

						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockTimeEnd").a("class", "").f().sx("end time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "Recherche_blockTimeEnd")
									.a("onclick", "removeGlow($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("en-US")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, \"'h'MM\"); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=\"pk\"]').val() }], 'setBlockTimeEnd', s, function() { addGlow($('#Recherche_blockTimeEnd')); }, function() { ajouterErreur($('#Recherche_blockTimeEnd')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSunday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockSunday")
									.a("id", "Recherche_blockSunday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockSunday")
									.a("name", "blockSunday")
									.a("id", "Recherche_blockSunday")
									;
									if(o.getBlockSunday() != null && o.getBlockSunday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockSunday").a("class", "").f().sx("sunday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockMonday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockMonday")
									.a("id", "Recherche_blockMonday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockMonday")
									.a("name", "blockMonday")
									.a("id", "Recherche_blockMonday")
									;
									if(o.getBlockMonday() != null && o.getBlockMonday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockMonday").a("class", "").f().sx("monday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockTuesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockTuesday")
									.a("id", "Recherche_blockTuesday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockTuesday")
									.a("name", "blockTuesday")
									.a("id", "Recherche_blockTuesday")
									;
									if(o.getBlockTuesday() != null && o.getBlockTuesday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockTuesday").a("class", "").f().sx("tuesday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockWednesday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockWednesday")
									.a("id", "Recherche_blockWednesday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockWednesday")
									.a("name", "blockWednesday")
									.a("id", "Recherche_blockWednesday")
									;
									if(o.getBlockWednesday() != null && o.getBlockWednesday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockWednesday").a("class", "").f().sx("wednesday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockThursday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockThursday")
									.a("id", "Recherche_blockThursday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockThursday")
									.a("name", "blockThursday")
									.a("id", "Recherche_blockThursday")
									;
									if(o.getBlockThursday() != null && o.getBlockThursday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockThursday").a("class", "").f().sx("thursday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockFriday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockFriday")
									.a("id", "Recherche_blockFriday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockFriday")
									.a("name", "blockFriday")
									.a("id", "Recherche_blockFriday")
									;
									if(o.getBlockFriday() != null && o.getBlockFriday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockFriday").a("class", "").f().sx("friday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockSaturday").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "blockSaturday")
									.a("id", "Recherche_blockSaturday")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valueBlockSaturday")
									.a("name", "blockSaturday")
									.a("id", "Recherche_blockSaturday")
									;
									if(o.getBlockSaturday() != null && o.getBlockSaturday())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockSaturday").a("class", "").f().sx("saturday").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestSchoolBlockBlockPricePerMonth").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_blockPricePerMonth").a("class", "").f().sx("price per month").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "price per month")
									.a("class", "valueBlockPricePerMonth w3-input w3-border ")
									.a("name", "blockPricePerMonth")
									.a("id", "Recherche_blockPricePerMonth")
									.a("value", o.strBlockPricePerMonth())
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
						e("th").f().sx("start time").g("th");
						e("th").f().sx("end time").g("th");
						e("th").f().sx("sunday").g("th");
						e("th").f().sx("monday").g("th");
						e("th").f().sx("tuesday").g("th");
						e("th").f().sx("wednesday").g("th");
						e("th").f().sx("thursday").g("th");
						e("th").f().sx("friday").g("th");
						e("th").f().sx("saturday").g("th");
						e("th").f().sx("price per month").g("th");
						e("th").f().sx("primary key").g("th");
						e("th").f().sx("created").g("th");
						e("th").f().sx("school").g("th");
						e("th").f().sx("modified").g("th");
						e("th").f().sx("key").g("th");
						e("th").f().sx("enrollments").g("th");
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
									sx(o.getBlockTimeStart());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockTimeEnd());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockSunday());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockMonday());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockTuesday());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockWednesday());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockThursday());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockFriday());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockSaturday());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlockPricePerMonth());
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
									sx(o.getBlockKey());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEnrollmentKeys());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getBlocId());
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
