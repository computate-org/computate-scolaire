package org.computate.scolaire.frFR.utilisateur;

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
import java.util.Optional;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.user.SiteUserGenPage
 **/
public class UtilisateurSiteGenPage extends UtilisateurSiteGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeUtilisateurSite(Couverture<ListeRecherche<UtilisateurSite>> c) {
	}

	protected void _utilisateurSite(Couverture<UtilisateurSite> c) {
		if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1)
			c.o(listeUtilisateurSite.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("utilisateurs du site");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(utilisateurSite != null && utilisateurSite.getObjetTitre() != null)
			c.o(utilisateurSite.getObjetTitre());
		else if(utilisateurSite != null)
			c.o("");
		else if(listeUtilisateurSite == null || listeUtilisateurSite.size() == 0)
			c.o("aucun utilisateur du site trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/utilisateur");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/utilisateur-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("user-cog");
	}

	@Override public void initLoinUtilisateurSiteGenPage() {
		initUtilisateurSiteGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsUtilisateurSiteGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/UtilisateurSitePage.js").f().g("script");
	}

	@Override public void htmlScriptUtilisateurSiteGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(1, "}");
		tl(1, "websocketUtilisateurSite(websocketUtilisateurSiteInner);");
		l("});");
	}

	public void htmlFormPageUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Page");
			o.htmCree("Page");
			o.htmModifie("Page");
			o.htmObjetId("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("Page");
			o.htmSupprime("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurRecevoirCourriels("Page");
			o.htmVoirArchive("Page");
			o.htmVoirSupprime("Page");
		} g("div");
	}

	public void htmlFormPOSTUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("POST");
			o.htmCree("POST");
			o.htmModifie("POST");
			o.htmObjetId("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("POST");
			o.htmSupprime("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurRecevoirCourriels("POST");
			o.htmVoirArchive("POST");
			o.htmVoirSupprime("POST");
		} g("div");
	}

	public void htmlFormPUTUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurRecevoirCourriels("PUT");
			o.htmVoirArchive("PUT");
			o.htmVoirSupprime("PUT");
		} g("div");
	}

	public void htmlFormPATCHUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurRecevoirCourriels("PATCH");
			o.htmVoirArchive("PATCH");
			o.htmVoirSupprime("PATCH");
		} g("div");
	}

	public void htmlFormRechercheUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Recherche");
			o.htmCree("Recherche");
			o.htmModifie("Recherche");
			o.htmObjetId("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("Recherche");
			o.htmSupprime("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurRecevoirCourriels("Recherche");
			o.htmVoirArchive("Recherche");
			o.htmVoirSupprime("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyUtilisateurSiteGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeUtilisateurSite == null || listeUtilisateurSite.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/utilisateur").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("utilisateurs du site").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun utilisateur du site trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			UtilisateurSite o = listeUtilisateurSite.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/utilisateur").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/utilisateur").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeUtilisateurSite.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeUtilisateurSite.getQuery(), "_suggested", "");
					Integer rows1 = listeUtilisateurSite.getRows();
					Integer start1 = listeUtilisateurSite.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/utilisateur?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/utilisateur?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/utilisateur?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/utilisateur?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-gray w3-hover-gray ").f();
					{ e("tr").f();
						e("th").f().sx("crée").g("th");
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeUtilisateurSite.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeUtilisateurSite.size(); i++) {
						UtilisateurSite o = listeUtilisateurSite.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/utilisateur/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCree());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-user-cog ").f().g("i");
									{ e("span").f();
										sx(o.strObjetTitre());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			UtilisateurSite o = listeUtilisateurSite.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "UtilisateurSiteForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageUtilisateurSite(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");

		}
		htmlBodyFormsUtilisateurSiteGenPage();
		htmlSuggereUtilisateurSiteGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsUtilisateurSiteGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("id", "rechargerCeUtilisateurSiteGenPage")
				.a("onclick", "patchUtilisateurSiteVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeUtilisateurSiteGenPage')); }, function() { ajouterErreur($('#rechargerCeUtilisateurSiteGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("recharger cet utilisateur du site");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
			.a("onclick", "$('#patchUtilisateurSiteModale').show(); ")
			.f().sx("Modifier des utilisateurs du site")
		.g("button");
		{ e("div").a("id", "patchUtilisateurSiteModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-gray ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchUtilisateurSiteModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des utilisateurs du site").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					UtilisateurSite o = new UtilisateurSite();
					o.setRequeteSite_(requeteSite_);

					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchUtilisateurSiteFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHUtilisateurSite(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
						.a("onclick", "patchUtilisateurSite($('#patchUtilisateurSiteFormulaireFiltres'), $('#patchUtilisateurSiteFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des utilisateurs du site")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestSiteUserGenPage
	 * r: "/utilisateur"
	 * r.enUS: "/user"
	 * r: "voir tous les utilisateurs du site"
	 * r.enUS: "see all the site users"
	 * r: "rechargerUtilisateurSiteGenPage"
	 * r.enUS: "refreshSiteUserGenPage"
	 * r: "recharger tous les utilisateurs du site"
	 * r.enUS: "refresh all the site users"
	 * r: "rechercher utilisateurs du site : "
	 * r.enUS: "search site users: "
	 * r: "suggereFormUtilisateurSite"
	 * r.enUS: "suggestFormSiteUser"
	 * r: "rechercher utilisateurs du site"
	 * r.enUS: "search site users"
	 * r: "suggereUtilisateurSite w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSiteUser w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereUtilisateurSite"
	 * r.enUS: "suggestSiteUser"
	 * r: patchUtilisateurSiteVals
	 * r.enUS: patchSiteUserVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerUtilisateurSiteGenPage
	 * r.enUS: refreshSiteUserGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereUtilisateurSiteObjetSuggere
	 * r.enUS: suggestSiteUserObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListUtilisateurSite'
	 * r.enUS: '#suggestListSiteUser'
	 * r: "suggereListUtilisateurSite"
	 * r.enUS: "suggestListSiteUser"
	**/
	public static void htmlSuggereUtilisateurSiteGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/utilisateur").a("class", "").f();
					p.e("i").a("class", "far fa-user-cog ").f().g("i");
					p.sx("voir tous les utilisateurs du site");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher utilisateurs du site : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher utilisateurs du site")
				.a("class", "suggereUtilisateurSite w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereUtilisateurSite")
				.a("id", "suggereUtilisateurSite", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereUtilisateurSiteObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListUtilisateurSite", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListUtilisateurSite", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
