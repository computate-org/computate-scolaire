package org.computate.scolaire.enUS.form.part;

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
public class FormPartGenPage extends FormPartGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listFormPart(Wrap<SearchList<FormPart>> c) {
	}

	protected void _formPart(Wrap<FormPart> c) {
		if(listFormPart != null && listFormPart.size() == 1)
			c.o(listFormPart.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("form parts");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(formPart != null)
			c.o("");
		else if(listFormPart == null || listFormPart.size() == 0)
			c.o("no form part found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/form-part");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/form-part-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("sun");
	}

	@Override public void initDeepFormPartGenPage() {
		initFormPartGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsFormPartGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/FormPartPage.js").f().g("script");
	}

	@Override public void htmlScriptFormPartGenPage() {
		l("$(document).ready(function() {");
		l("});");
	}

	public void htmlFormPageFormPart(FormPart o) {
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
					{ e("form").a("action", "").a("id", "formFormPartArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setArchived', $(this).prop('checked'), function() { addGlow($('#Page_archived')); }, function() { addError($('#Page_archived')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setDeleted', $(this).prop('checked'), function() { addGlow($('#Page_deleted')); }, function() { addError($('#Page_deleted')); }); ")
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
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("class", "").f().sx("enrollment form").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strEnrollmentFormKey()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formFormPartHtmlOrder").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlOrder").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlOrder").a("class", "").f().sx("ordre").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "ordre")
										.a("class", "setHtmlOrder w3-input w3-border ")
										.a("name", "setHtmlOrder")
										.a("id", "Page_htmlOrder")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlOrder', $(this).val(), function() { addGlow($('#Page_htmlOrder')); }, function() { addError($('#Page_htmlOrder')); }); ")
										.a("value", o.strHtmlOrder())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlOrder')); $('#Page_htmlOrder').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlOrder', null, function() { addGlow($('#Page_htmlOrder')); }, function() { addError($('#Page_htmlOrder')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlLink").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlLink").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlLink").a("class", "").f().sx("HTML après").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "HTML après")
										.a("class", "setHtmlLink w3-input w3-border ")
										.a("name", "setHtmlLink")
										.a("id", "Page_htmlLink")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlLink', $(this).val(), function() { addGlow($('#Page_htmlLink')); }, function() { addError($('#Page_htmlLink')); }); ")
										.a("value", o.strHtmlLink())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlLink')); $('#Page_htmlLink').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlLink', null, function() { addGlow($('#Page_htmlLink')); }, function() { addError($('#Page_htmlLink')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlElement").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlElement").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlElement").a("class", "").f().sx("HTML element").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "HTML element")
										.a("class", "setHtmlElement w3-input w3-border ")
										.a("name", "setHtmlElement")
										.a("id", "Page_htmlElement")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlElement', $(this).val(), function() { addGlow($('#Page_htmlElement')); }, function() { addError($('#Page_htmlElement')); }); ")
										.a("value", o.strHtmlElement())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlElement')); $('#Page_htmlElement').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlElement', null, function() { addGlow($('#Page_htmlElement')); }, function() { addError($('#Page_htmlElement')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlId").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlId").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlId").a("class", "").f().sx("HTML ID").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "HTML ID")
										.a("class", "setHtmlId w3-input w3-border ")
										.a("name", "setHtmlId")
										.a("id", "Page_htmlId")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlId', $(this).val(), function() { addGlow($('#Page_htmlId')); }, function() { addError($('#Page_htmlId')); }); ")
										.a("value", o.strHtmlId())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlId')); $('#Page_htmlId').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlId', null, function() { addGlow($('#Page_htmlId')); }, function() { addError($('#Page_htmlId')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlClasses").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlClasses").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlClasses").a("class", "").f().sx("HTML classes").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "HTML classes")
										.a("class", "setHtmlClasses w3-input w3-border ")
										.a("name", "setHtmlClasses")
										.a("id", "Page_htmlClasses")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlClasses', $(this).val(), function() { addGlow($('#Page_htmlClasses')); }, function() { addError($('#Page_htmlClasses')); }); ")
										.a("value", o.strHtmlClasses())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlClasses')); $('#Page_htmlClasses').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlClasses', null, function() { addGlow($('#Page_htmlClasses')); }, function() { addError($('#Page_htmlClasses')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlStyle").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlStyle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlStyle").a("class", "").f().sx("HTML element").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "HTML element")
										.a("class", "setHtmlStyle w3-input w3-border ")
										.a("name", "setHtmlStyle")
										.a("id", "Page_htmlStyle")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlStyle', $(this).val(), function() { addGlow($('#Page_htmlStyle')); }, function() { addError($('#Page_htmlStyle')); }); ")
										.a("value", o.strHtmlStyle())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlStyle')); $('#Page_htmlStyle').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlStyle', null, function() { addGlow($('#Page_htmlStyle')); }, function() { addError($('#Page_htmlStyle')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlBefore").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlBefore").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlBefore").a("class", "").f().sx("HTML avant").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "HTML avant")
										.a("class", "setHtmlBefore w3-input w3-border ")
										.a("name", "setHtmlBefore")
										.a("id", "Page_htmlBefore")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlBefore', $(this).val(), function() { addGlow($('#Page_htmlBefore')); }, function() { addError($('#Page_htmlBefore')); }); ")
										.a("value", o.strHtmlBefore())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlBefore')); $('#Page_htmlBefore').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlBefore', null, function() { addGlow($('#Page_htmlBefore')); }, function() { addError($('#Page_htmlBefore')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlText").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlText").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlText").a("class", "").f().sx("texte").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "texte")
										.a("class", "setHtmlText w3-input w3-border ")
										.a("name", "setHtmlText")
										.a("id", "Page_htmlText")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlText', $(this).val(), function() { addGlow($('#Page_htmlText')); }, function() { addError($('#Page_htmlText')); }); ")
										.a("value", o.strHtmlText())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlText')); $('#Page_htmlText').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlText', null, function() { addGlow($('#Page_htmlText')); }, function() { addError($('#Page_htmlText')); }); ")
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
					{ e("form").a("action", "").a("id", "formFormPartHtmlAfter").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "value")
							.a("class", "value ")
							.a("value", siteRequest_.getRequestPk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggestFormPartHtmlAfter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_htmlAfter").a("class", "").f().sx("HTML après").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "HTML après")
										.a("class", "setHtmlAfter w3-input w3-border ")
										.a("name", "setHtmlAfter")
										.a("id", "Page_htmlAfter")
										.a("onclick", "removeGlow($(this)); ")
										.a("onchange", "patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlAfter', $(this).val(), function() { addGlow($('#Page_htmlAfter')); }, function() { addError($('#Page_htmlAfter')); }); ")
										.a("value", o.strHtmlAfter())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#Page_htmlAfter')); $('#Page_htmlAfter').val(null); patchFormPartVal([{ name: 'fq', value: 'pk:' + $('#FormPartForm :input[name=\"pk\"]').val() }], 'setHtmlAfter', null, function() { addGlow($('#Page_htmlAfter')); }, function() { addError($('#Page_htmlAfter')); }); ")
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

	public void htmlFormPOSTFormPart(FormPart o) {
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
				{ e("form").a("action", "").a("id", "formFormPartArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
				{ e("form").a("action", "").a("id", "formFormPartDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("enrollment form").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnrollmentFormKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlOrder").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlOrder").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlOrder").a("class", "").f().sx("ordre").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "ordre")
									.a("class", "valueHtmlOrder w3-input w3-border ")
									.a("name", "htmlOrder")
									.a("id", "POST_htmlOrder")
									.a("value", o.strHtmlOrder())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlLink").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlLink").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlLink").a("class", "").f().sx("HTML après").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML après")
									.a("class", "valueHtmlLink w3-input w3-border ")
									.a("name", "htmlLink")
									.a("id", "POST_htmlLink")
									.a("value", o.strHtmlLink())
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
				{ e("form").a("action", "").a("id", "formFormPartHtmlElement").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlElement").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlElement").a("class", "").f().sx("HTML element").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML element")
									.a("class", "valueHtmlElement w3-input w3-border ")
									.a("name", "htmlElement")
									.a("id", "POST_htmlElement")
									.a("value", o.strHtmlElement())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlId").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlId").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlId").a("class", "").f().sx("HTML ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML ID")
									.a("class", "valueHtmlId w3-input w3-border ")
									.a("name", "htmlId")
									.a("id", "POST_htmlId")
									.a("value", o.strHtmlId())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlClasses").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlClasses").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlClasses").a("class", "").f().sx("HTML classes").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML classes")
									.a("class", "valueHtmlClasses w3-input w3-border ")
									.a("name", "htmlClasses")
									.a("id", "POST_htmlClasses")
									.a("value", o.strHtmlClasses())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlStyle").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlStyle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlStyle").a("class", "").f().sx("HTML element").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML element")
									.a("class", "valueHtmlStyle w3-input w3-border ")
									.a("name", "htmlStyle")
									.a("id", "POST_htmlStyle")
									.a("value", o.strHtmlStyle())
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
				{ e("form").a("action", "").a("id", "formFormPartHtmlBefore").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlBefore").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlBefore").a("class", "").f().sx("HTML avant").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML avant")
									.a("class", "valueHtmlBefore w3-input w3-border ")
									.a("name", "htmlBefore")
									.a("id", "POST_htmlBefore")
									.a("value", o.strHtmlBefore())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlText").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlText").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlText").a("class", "").f().sx("texte").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "texte")
									.a("class", "valueHtmlText w3-input w3-border ")
									.a("name", "htmlText")
									.a("id", "POST_htmlText")
									.a("value", o.strHtmlText())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlAfter").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlAfter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_htmlAfter").a("class", "").f().sx("HTML après").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML après")
									.a("class", "valueHtmlAfter w3-input w3-border ")
									.a("name", "htmlAfter")
									.a("id", "POST_htmlAfter")
									.a("value", o.strHtmlAfter())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHFormPart(FormPart o) {
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
				{ e("form").a("action", "").a("id", "formFormPartArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
				{ e("form").a("action", "").a("id", "formFormPartDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("enrollment form").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnrollmentFormKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlOrder").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlOrder").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlOrder").a("class", "").f().sx("ordre").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "ordre")
									.a("class", "setHtmlOrder w3-input w3-border ")
									.a("name", "setHtmlOrder")
									.a("id", "PATCH_htmlOrder")
									.a("value", o.strHtmlOrder())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlLink").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlLink").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlLink").a("class", "").f().sx("HTML après").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML après")
									.a("class", "setHtmlLink w3-input w3-border ")
									.a("name", "setHtmlLink")
									.a("id", "PATCH_htmlLink")
									.a("value", o.strHtmlLink())
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
				{ e("form").a("action", "").a("id", "formFormPartHtmlElement").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlElement").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlElement").a("class", "").f().sx("HTML element").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML element")
									.a("class", "setHtmlElement w3-input w3-border ")
									.a("name", "setHtmlElement")
									.a("id", "PATCH_htmlElement")
									.a("value", o.strHtmlElement())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlId").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlId").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlId").a("class", "").f().sx("HTML ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML ID")
									.a("class", "setHtmlId w3-input w3-border ")
									.a("name", "setHtmlId")
									.a("id", "PATCH_htmlId")
									.a("value", o.strHtmlId())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlClasses").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlClasses").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlClasses").a("class", "").f().sx("HTML classes").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML classes")
									.a("class", "setHtmlClasses w3-input w3-border ")
									.a("name", "setHtmlClasses")
									.a("id", "PATCH_htmlClasses")
									.a("value", o.strHtmlClasses())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlStyle").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlStyle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlStyle").a("class", "").f().sx("HTML element").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML element")
									.a("class", "setHtmlStyle w3-input w3-border ")
									.a("name", "setHtmlStyle")
									.a("id", "PATCH_htmlStyle")
									.a("value", o.strHtmlStyle())
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
				{ e("form").a("action", "").a("id", "formFormPartHtmlBefore").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlBefore").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlBefore").a("class", "").f().sx("HTML avant").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML avant")
									.a("class", "setHtmlBefore w3-input w3-border ")
									.a("name", "setHtmlBefore")
									.a("id", "PATCH_htmlBefore")
									.a("value", o.strHtmlBefore())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlText").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlText").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlText").a("class", "").f().sx("texte").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "texte")
									.a("class", "setHtmlText w3-input w3-border ")
									.a("name", "setHtmlText")
									.a("id", "PATCH_htmlText")
									.a("value", o.strHtmlText())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormPartHtmlAfter").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartHtmlAfter").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_htmlAfter").a("class", "").f().sx("HTML après").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "HTML après")
									.a("class", "setHtmlAfter w3-input w3-border ")
									.a("name", "setHtmlAfter")
									.a("id", "PATCH_htmlAfter")
									.a("value", o.strHtmlAfter())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormSearchFormPart(FormPart o) {
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
				{ e("form").a("action", "").a("id", "formFormPartArchived").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartArchived").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
				{ e("form").a("action", "").a("id", "formFormPartDeleted").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "value")
						.a("class", "value ")
						.a("value", siteRequest_.getRequestPk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggestFormPartDeleted").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("enrollment form").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnrollmentFormKey()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyFormPartGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listFormPart == null || listFormPart.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/form-part").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("form parts").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no form part found").g("span");
				} g("span");
			} g("h2");
		} else if(listFormPart != null && listFormPart.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			FormPart o = listFormPart.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/form-part").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/form-part").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listFormPart.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listFormPart.getQuery(), "_suggested", "");
					Integer rows1 = listFormPart.getRows();
					Integer start1 = listFormPart.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/form-part?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/form-part?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/form-part?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/form-part?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-yellow w3-hover-yellow ").f();
					{ e("tr").f();
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listFormPart.getQueryResponse().getHighlighting();
					for(int i = 0; i < listFormPart.size(); i++) {
						FormPart o = listFormPart.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/form-part/" + o.getPk();
						{ e("tr").f();
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

		if(listFormPart != null && listFormPart.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			FormPart o = listFormPart.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "FormPartForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageFormPart(o);
				}

			} g("div");

		}
		htmlBodyFormsFormPartGenPage();
		htmlSuggestFormPartGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsFormPartGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("id", "refreshThisFormPartGenPage")
				.a("onclick", "patchFormPartVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisFormPartGenPage')); }, function() { addError($('#refreshThisFormPartGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this form part");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postFormPartModal').show(); ")
			.f().sx("Create a form part")
		.g("button");
		{ e("div").a("id", "postFormPartModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postFormPartModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create a form part").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					FormPart o = new FormPart();

					// Form POST
					{ e("div").a("id", "postFormPartForm").f();
						htmlFormPOSTFormPart(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postFormPart($('#postFormPartForm')); ")
						.f().sx("Create a form part")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchFormPartModal').show(); ")
			.f().sx("Modify the form parts")
		.g("button");
		{ e("div").a("id", "patchFormPartModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchFormPartModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the form parts").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					FormPart o = new FormPart();

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchFormPartFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchFormPart(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "aSearchFormPart($('#patchFormPartFormFilters')); ")
						.f().sx("Search the a form part")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchFormPartFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHFormPart(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchFormPart($('#patchFormPartFormFilters'), $('#patchFormPartFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the form parts")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listFormPart != null && listFormPart.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#deleteFormPartModal').show(); ")
				.f().sx("Delete the form parts")
			.g("button");
			{ e("div").a("id", "deleteFormPartModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteFormPartModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the form parts").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						FormPart o = new FormPart();

						// Form DELETE
						{ e("div").a("id", "deleteFormPartForm").f();
							htmlFormPATCHFormPart(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
							.a("onclick", "deleteFormPart(", o.getPk(), "); ")
							.f().sx("Delete the form parts")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestFormPartGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/form-part").a("class", "").f();
					p.e("i").a("class", "far fa-sun w3-padding-small ").f().g("i");
					p.sx("see all the form parts");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllFormPartGenPage", id).a("href", "/form-part").a("class", "").a("onclick", "patchFormPartVals([], {}, function() { addGlow($('#refreshAllFormPartGenPage", id, "')); }, function() { addError($('#refreshAllFormPartGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the form parts");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search form parts: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormFormPart", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/form-part?q=objectSuggest:' + encodeURIComponent($('#suggestFormPart", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestFormPart w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestFormPart")
							.a("id", "suggestFormPart", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestFormPartObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListFormPart", id, "')); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListFormPart", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
