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
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.session.SessionGenPage
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
			c.o("sessions");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(sessionScolaire != null && sessionScolaire.getSessionNomComplet() != null)
			c.o(sessionScolaire.getSessionNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(sessionScolaire != null && sessionScolaire.getSessionNomComplet() != null)
			c.o(sessionScolaire.getSessionNomComplet());
		else if(sessionScolaire != null)
			c.o("");
		else if(listeSessionScolaire == null || listeSessionScolaire.size() == 0)
			c.o("aucune session trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/session");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/session-999.png");
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
		e("script").a("src", statiqueUrlBase, "/js/frFR/AgePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/SaisonPage.js").f().g("script");
	}

	@Override public void htmlScriptSessionGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", requeteSite_.getRequetePk(), ";");
		tl(1, "suggereSessionScolaireSaisonCle([{'name':'fq','value':'sessionCles:' + pk}], $('#listSessionScolaireSaisonCle_Page'), pk); ");
		tl(1, "suggereSessionScolaireAgeCles([{'name':'fq','value':'sessionCle:' + pk}], $('#listSessionScolaireAgeCles_Page'), pk); ");
		tl(1, "websocketSessionScolaire(websocketSessionScolaireInner);");
		l("});");
	}

	public void htmlFormPageSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("Page");
			o.htmPk("Page");
			o.htmObjetId("Page");
			o.htmModifie("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("Page");
			o.htmSupprime("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionJourDebut("Page");
			o.htmSessionJourFin("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonCle("Page");
			o.htmAgeCles("Page");
		} g("div");
	}

	public void htmlFormPOSTSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("POST");
			o.htmPk("POST");
			o.htmObjetId("POST");
			o.htmModifie("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("POST");
			o.htmSupprime("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionJourDebut("POST");
			o.htmSessionJourFin("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonCle("POST");
			o.htmAgeCles("POST");
		} g("div");
	}

	public void htmlFormPATCHSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmPk("PATCH");
			o.htmObjetId("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionJourDebut("PATCH");
			o.htmSessionJourFin("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonCle("PATCH");
			o.htmAgeCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleAddresse("PATCH");
			o.htmSessionNomComplet("PATCH");
		} g("div");
	}

	public void htmlFormRechercheSessionScolaire(SessionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("Recherche");
			o.htmPk("Recherche");
			o.htmObjetId("Recherche");
			o.htmModifie("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("Recherche");
			o.htmSupprime("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionJourDebut("Recherche");
			o.htmSessionJourFin("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonCle("Recherche");
			o.htmAgeCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleAddresse("Recherche");
			o.htmSessionNomComplet("Recherche");
		} g("div");
	}

	@Override public void htmlBodySessionGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeSessionScolaire == null || listeSessionScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/session").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("sessions").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucune session trouvée").g("span");
				} g("span");
			} g("h2");
		} else if(listeSessionScolaire != null && listeSessionScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SessionScolaire o = listeSessionScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/session").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/session").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeSessionScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeSessionScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeSessionScolaire.getRows();
					Integer start1 = listeSessionScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/session?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/session?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/session?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/session?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-green w3-hover-green ").f();
					{ e("tr").f();
						e("th").f().sx("nom").g("th");
						e("th").f().sx("crée").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeSessionScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeSessionScolaire.size(); i++) {
						SessionScolaire o = listeSessionScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/session/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fad fa-graduation-cap w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strSessionNomComplet());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCree());
									} g("span");
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
					{ e("form").a("action", "").a("id", "SessionScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
						e("input")
						.a("name", "focusId")
						.a("type", "hidden")
						.fg();
					} g("form");
					htmlFormPageSessionScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsSessionGenPage();
		htmlSuggereSessionGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsSessionGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("id", "rechargerCetteSessionGenPage")
				.a("onclick", "patchSessionScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCetteSessionGenPage')); }, function() { ajouterErreur($('#rechargerCetteSessionGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger cette session");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#postSessionScolaireModale').show(); ")
			.f().sx("Créer une session")
		.g("button");
		{ e("div").a("id", "postSessionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSessionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer une session").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SessionScolaire o = new SessionScolaire();
					o.setRequeteSite_(requeteSite_);

					// Form POST
					{ e("div").a("id", "postSessionScolaireForm").f();
						htmlFormPOSTSessionScolaire(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "postSessionScolaire($('#postSessionScolaireForm')); ")
						.f().sx("Créer une session")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchSessionScolaireModale').show(); ")
			.f().sx("Modifier des sessions")
		.g("button");
		{ e("div").a("id", "patchSessionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSessionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des sessions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SessionScolaire o = new SessionScolaire();
					o.setRequeteSite_(requeteSite_);

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchSessionScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheSessionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "rechercheSessionScolaire($('#patchSessionScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des une session")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchSessionScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSessionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchSessionScolaire($('#patchSessionScolaireFormulaireFiltres'), $('#patchSessionScolaireFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des sessions")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeSessionScolaire != null && listeSessionScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#deleteSessionScolaireModale').show(); ")
				.f().sx("Supprimer des sessions")
			.g("button");
			{ e("div").a("id", "deleteSessionScolaireModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-green ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSessionScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des sessions").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SessionScolaire o = new SessionScolaire();
						o.setRequeteSite_(requeteSite_);

						// Form DELETE
						{ e("div").a("id", "deleteSessionScolaireForm").f();
							htmlFormPATCHSessionScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
							.a("onclick", "deleteSessionScolaire(", o.getPk(), "); ")
							.f().sx("Supprimer des sessions")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestSessionGenPage
	 * r: "/session"
	 * r.enUS: "/session"
	 * r: "voir toutes les sessions"
	 * r.enUS: "see all the sessions"
	 * r: "rechargerSessionGenPage"
	 * r.enUS: "refreshSessionGenPage"
	 * r: "recharger toutes les sessions"
	 * r.enUS: "refresh all the sessions"
	 * r: "rechercher sessions : "
	 * r.enUS: "search sessions: "
	 * r: "suggereFormSessionScolaire"
	 * r.enUS: "suggestFormSchoolSession"
	 * r: "rechercher sessions"
	 * r.enUS: "search sessions"
	 * r: "suggereSessionScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolSession w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereSessionScolaire"
	 * r.enUS: "suggestSchoolSession"
	 * r: patchSessionScolaireVals
	 * r.enUS: patchSchoolSessionVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerSessionGenPage
	 * r.enUS: refreshSessionGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereSessionScolaireObjetSuggere
	 * r.enUS: suggestSchoolSessionObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListSessionScolaire'
	 * r.enUS: '#suggestListSchoolSession'
	 * r: "suggereListSessionScolaire"
	 * r.enUS: "suggestListSchoolSession"
	**/
	public static void htmlSuggereSessionGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/session").a("class", "").f();
					p.e("i").a("class", "fad fa-graduation-cap w3-padding-small ").f().g("i");
					p.sx("voir toutes les sessions");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerToutesSessionGenPage", id).a("href", "/session").a("class", "").a("onclick", "patchSessionScolaireVals([], {}, function() { ajouterLueur($('#rechargerToutesSessionGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesSessionGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger toutes les sessions");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher sessions : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormSessionScolaire", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/session?q=objetSuggere:' + encodeURIComponent($('#suggereSessionScolaire", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher sessions")
							.a("class", "suggereSessionScolaire w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereSessionScolaire")
							.a("id", "suggereSessionScolaire", id)
							.a("autocomplete", "off")
							.a("oninput", "suggereSessionScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListSessionScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListSessionScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
