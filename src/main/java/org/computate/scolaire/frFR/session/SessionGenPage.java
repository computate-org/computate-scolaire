



package org.computate.scolaire.frFR.session;

import org.computate.scolaire.frFR.cluster.ClusterPage;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;
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
 * Traduire: false
 **/
public class SessionGenPage extends SessionGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeSessionScolaire(Couverture<ListeRecherche<SessionScolaire>> c) {
	}

	protected void _sessionScolaire(Couverture<SessionScolaire> c) {
		if(listeSessionScolaire != null && listeSessionScolaire.size() == 1)
			c.o(listeSessionScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(sessionScolaire != null)
			c.o("une sesson");
		else if(listeSessionScolaire == null || listeSessionScolaire.size() == 0)
			c.o("aucune sesson trouvée");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(sessionScolaire != null)
			c.o("");
		else if(listeSessionScolaire == null || listeSessionScolaire.size() == 0)
			c.o("aucune sesson trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/frFR/sesson");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/frFR/sesson-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("duotone");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("graduation-cap");
	}

	@Override public void initLoinSessionGenPage() {
		initSessionGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsSessionGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/SessionPage.js").f().g("script");
	}

	@Override public void htmlScriptSessionGenPage() {
		l("$(document).ready(function() {");
		l("});");
	}

	public void htmlFormPageSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("clé primaire").g("label");
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
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("crée").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strCree()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("modifié").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strModifie()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strSessonId()).g("span");
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
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strEcoleCle()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("saison").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strSaisonCle()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalDate val = o.getSessonJourDebut();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_sessonJourDebut").a("class", "").f().sx("début de la sesson").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row  ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border datepicker ")
										.a("placeholder", "DD-MM-YYYY")
										.a("data-timeformat", "DD-MM-YYYY")
										.a("id", "Page_sessonJourDebut")
										.a("onclick", "enleverLueur($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
										.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonJourDebut', s, function() { ajouterLueur($('#Page_sessonJourDebut')); }, function() { ajouterErreur($('#Page_sessonJourDebut')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_sessonJourDebut')); $('#Page_sessonJourDebut').val(null); patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonJourDebut', null, $('#Page_sessonJourDebut'), function() { ajouterLueur($('#Page_sessonJourDebut')); }, function() { ajouterErreur($('#Page_sessonJourDebut')); }); ")
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
					{ e("form").a("action", "").a("id", "formSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "sessonEte")
										.a("id", "Page_sessonEte")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSessonEte")
										.a("name", "setSessonEte")
										.a("id", "Page_sessonEte")
										.a("onchange", "patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonEte', $(this).val(), function() { ajouterLueur($('#Page_sessonEte')); }, function() { ajouterErreur($('#Page_sessonEte')); }); ")
										;
										if(o.getSessonEte() != null && o.getSessonEte())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_sessonEte").a("class", "").f().sx("été").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_sessonEte')); $('#Page_sessonEte').val(null); patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonEte', null, $('#Page_sessonEte'), function() { ajouterLueur($('#Page_sessonEte')); }, function() { ajouterErreur($('#Page_sessonEte')); }); ")
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
					{ e("form").a("action", "").a("id", "formSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "hidden")
										.a("name", "sessonHiver")
										.a("id", "Page_sessonHiver")
										.a("value", "false")
									.fg();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSessonHiver")
										.a("name", "setSessonHiver")
										.a("id", "Page_sessonHiver")
										.a("onchange", "patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonHiver', $(this).val(), function() { ajouterLueur($('#Page_sessonHiver')); }, function() { ajouterErreur($('#Page_sessonHiver')); }); ")
										;
										if(o.getSessonHiver() != null && o.getSessonHiver())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_sessonHiver").a("class", "").f().sx("hiver").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_sessonHiver')); $('#Page_sessonHiver').val(null); patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonHiver', null, $('#Page_sessonHiver'), function() { ajouterLueur($('#Page_sessonHiver')); }, function() { ajouterErreur($('#Page_sessonHiver')); }); ")
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

	public void htmlFormPOSTSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSessonId()).g("span");
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("école").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEcoleCle()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("saison").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSaisonCle()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSessonJourDebut();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_sessonJourDebut").a("class", "").f().sx("début de la sesson").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "POST_sessonJourDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonJourDebut', s, function() { ajouterLueur($('#POST_sessonJourDebut')); }, function() { ajouterErreur($('#POST_sessonJourDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "sessonEte")
									.a("id", "POST_sessonEte")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSessonEte")
									.a("name", "sessonEte")
									.a("id", "POST_sessonEte")
									;
									if(o.getSessonEte() != null && o.getSessonEte())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_sessonEte").a("class", "").f().sx("été").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "sessonHiver")
									.a("id", "POST_sessonHiver")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSessonHiver")
									.a("name", "sessonHiver")
									.a("id", "POST_sessonHiver")
									;
									if(o.getSessonHiver() != null && o.getSessonHiver())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_sessonHiver").a("class", "").f().sx("hiver").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSessonId()).g("span");
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("école").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEcoleCle()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("saison").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSaisonCle()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSessonJourDebut();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_sessonJourDebut").a("class", "").f().sx("début de la sesson").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "PATCH_sessonJourDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonJourDebut', s, function() { ajouterLueur($('#PATCH_sessonJourDebut')); }, function() { ajouterErreur($('#PATCH_sessonJourDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "sessonEte")
									.a("id", "PATCH_sessonEte")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setSessonEte")
									.a("name", "setSessonEte")
									.a("id", "PATCH_sessonEte")
									;
									if(o.getSessonEte() != null && o.getSessonEte())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_sessonEte").a("class", "").f().sx("été").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "sessonHiver")
									.a("id", "PATCH_sessonHiver")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setSessonHiver")
									.a("name", "setSessonHiver")
									.a("id", "PATCH_sessonHiver")
									;
									if(o.getSessonHiver() != null && o.getSessonHiver())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_sessonHiver").a("class", "").f().sx("hiver").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSessonId()).g("span");
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("école").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEcoleCle()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("saison").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSaisonCle()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonJourDebut").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSessonJourDebut();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_sessonJourDebut").a("class", "").f().sx("début de la sesson").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "Recherche_sessonJourDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSessionScolaireVal([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=\"pk\"]').val() }], 'setSessonJourDebut', s, function() { ajouterLueur($('#Recherche_sessonJourDebut')); }, function() { ajouterErreur($('#Recherche_sessonJourDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonEte").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "sessonEte")
									.a("id", "Recherche_sessonEte")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSessonEte")
									.a("name", "sessonEte")
									.a("id", "Recherche_sessonEte")
									;
									if(o.getSessonEte() != null && o.getSessonEte())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_sessonEte").a("class", "").f().sx("été").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSessionScolaireSessonHiver").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "hidden")
									.a("name", "sessonHiver")
									.a("id", "Recherche_sessonHiver")
									.a("value", "false")
								.fg();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSessonHiver")
									.a("name", "sessonHiver")
									.a("id", "Recherche_sessonHiver")
									;
									if(o.getSessonHiver() != null && o.getSessonHiver())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_sessonHiver").a("class", "").f().sx("hiver").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodySessionGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeSessionScolaire == null || listeSessionScolaire.size() == 0) {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("aucune sesson trouvée").g("span");
			} g("h1");
		} else if(listeSessionScolaire != null && listeSessionScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("une sesson").g("span");
				} g("h1");
				SessionScolaire o = listeSessionScolaire.get(0);
				requeteSite_.setRequetePk(o.getPk());
			}
		} else {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("sessons").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("début de la sesson").g("th");
						e("th").f().sx("été").g("th");
						e("th").f().sx("hiver").g("th");
						e("th").f().sx("clé primaire").g("th");
						e("th").f().sx("crée").g("th");
						e("th").f().sx("école").g("th");
						e("th").f().sx("modifié").g("th");
						e("th").f().sx("saison").g("th");
						e("th").f().sx("ID").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeSessionScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeSessionScolaire.size(); i++) {
						SessionScolaire o = listeSessionScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/frFR/sesson/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSessonJourDebut());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSessonEte());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSessonHiver());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getPk());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getCree());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleCle());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getModifie());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSaisonCle());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSessonId());
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeSessionScolaire != null && listeSessionScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SessionScolaire o = listeSessionScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SessionScolaireForm").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageSessionScolaire(o);
				}

			} g("div");
		}
		htmlBodyFormsSessionGenPage();
	}

	public void htmlBodyFormsSessionGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#postSessionScolaireModale').show(); ")
			.f().sx("Créer une sesson")
		.g("button");
		{ e("div").a("id", "postSessionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSessionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer une sesson").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SessionScolaire o = new SessionScolaire();

					// Form POST
					{ e("form").a("action", "").a("id", "postSessionScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTSessionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "postSessionScolaire($('#postSessionScolaireForm')); ")
						.f().sx("Créer une sesson")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchSessionScolaireModale').show(); ")
			.f().sx("Modifier des sessons")
		.g("button");
		{ e("div").a("id", "patchSessionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSessionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des sessons").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SessionScolaire o = new SessionScolaire();

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchSessionScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheSessionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "rechercheSessionScolaire($('#patchSessionScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des une sesson")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchSessionScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSessionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchSessionScolaire($('#patchSessionScolaireFormulaireFiltres'), $('#patchSessionScolaireFormulaireValeurs')); ")
						.f().sx("Modifier des sessons")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#deleteSessionScolaireModale').show(); ")
			.f().sx("Supprimer des sessons")
		.g("button");
		{ e("div").a("id", "deleteSessionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSessionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des sessons").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SessionScolaire o = new SessionScolaire();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteSessionScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSessionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "deleteSessionScolaire(); ")
						.f().sx("Supprimer des sessons")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
