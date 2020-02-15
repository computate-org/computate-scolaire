package org.computate.scolaire.frFR.saison;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.season.SeasonGenPage
 **/
public class SaisonGenPage extends SaisonGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeSaisonScolaire(Couverture<ListeRecherche<SaisonScolaire>> c) {
	}

	protected void _saisonScolaire(Couverture<SaisonScolaire> c) {
		if(listeSaisonScolaire != null && listeSaisonScolaire.size() == 1)
			c.o(listeSaisonScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(saisonScolaire != null && saisonScolaire.getSaisonNomComplet() != null)
			c.o(saisonScolaire.getSaisonNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(saisonScolaire != null && saisonScolaire.getSaisonNomComplet() != null)
			c.o(saisonScolaire.getSaisonNomComplet());
		else if(saisonScolaire != null)
			c.o("");
		else if(listeSaisonScolaire == null || listeSaisonScolaire.size() == 0)
			c.o("aucune saison trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/saison");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/saison-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("sun");
	}

	@Override public void initLoinSaisonGenPage() {
		initSaisonGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsSaisonGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/SaisonPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AnneePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/SessionPage.js").f().g("script");
	}

	@Override public void htmlScriptSaisonGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggereSaisonScolaireAnneeCle([{'name':'fq','value':'saisonCles:' + pk}], $('#listSaisonScolaireAnneeCle_Page'), pk); ");
		tl(2, "suggereSaisonScolaireSessionCles([{'name':'fq','value':'saisonCle:' + pk}], $('#listSaisonScolaireSessionCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketSaisonScolaire(websocketSaisonScolaireInner);");
		l("});");
	}

	public void htmlFormPageSaisonScolaire(SaisonScolaire o) {
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
			o.htmSaisonJourDebut("Page");
			o.htmSaisonFuture("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCle("Page");
			o.htmSessionCles("Page");
		} g("div");
	}

	public void htmlFormPOSTSaisonScolaire(SaisonScolaire o) {
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
			o.htmSaisonJourDebut("POST");
			o.htmSaisonFuture("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCle("POST");
			o.htmSessionCles("POST");
		} g("div");
	}

	public void htmlFormPUTSaisonScolaire(SaisonScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonJourDebut("PUT");
			o.htmSaisonFuture("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCle("PUT");
			o.htmSessionCles("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonEte("PUT");
			o.htmSaisonHiver("PUT");
		} g("div");
	}

	public void htmlFormPATCHSaisonScolaire(SaisonScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonJourDebut("PATCH");
			o.htmSaisonFuture("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCle("PATCH");
			o.htmSessionCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSaisonEte("PATCH");
			o.htmSaisonHiver("PATCH");
		} g("div");
	}

	public void htmlFormRechercheSaisonScolaire(SaisonScolaire o) {
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
			o.htmSaisonJourDebut("Recherche");
			o.htmSaisonFuture("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCle("Recherche");
			o.htmSessionCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
			o.htmSaisonEte("Recherche");
			o.htmSaisonHiver("Recherche");
		} g("div");
	}

	@Override public void htmlBodySaisonGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeSaisonScolaire == null || listeSaisonScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/saison").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucune saison trouvée").g("span");
				} g("span");
			} g("h2");
		} else if(listeSaisonScolaire != null && listeSaisonScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SaisonScolaire o = listeSaisonScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/saison").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
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
				{ e("a").a("href", "/saison").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeSaisonScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeSaisonScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeSaisonScolaire.getRows();
					Integer start1 = listeSaisonScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/saison?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/saison?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/saison?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/saison?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-yellow w3-hover-yellow ").f();
					{ e("tr").f();
						e("th").f().sx("crée").g("th");
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeSaisonScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeSaisonScolaire.size(); i++) {
						SaisonScolaire o = listeSaisonScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/saison/" + o.getPk();
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
									e("i").a("class", "far fa-sun ").f().g("i");
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

		if(listeSaisonScolaire != null && listeSaisonScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SaisonScolaire o = listeSaisonScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SaisonScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSaisonScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsSaisonGenPage();
		htmlSuggereSaisonGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsSaisonGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("id", "rechargerCetteSaisonGenPage")
				.a("onclick", "patchSaisonScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCetteSaisonGenPage')); }, function() { ajouterErreur($('#rechargerCetteSaisonGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("recharger cette saison");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postSaisonScolaireModale').show(); ")
			.f().sx("Créer une saison")
		.g("button");
		{ e("div").a("id", "postSaisonScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSaisonScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Créer une saison").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SaisonScolaire o = new SaisonScolaire();
						o.setRequeteSite_(requeteSite_);

						// Form POST
						{ e("div").a("id", "postSaisonScolaireForm").f();
							htmlFormPOSTSaisonScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-yellow ")
							.a("onclick", "postSaisonScolaire($('#postSaisonScolaireForm')); ")
							.f().sx("Créer une saison")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#putSaisonScolaireModale').show(); ")
			.f().sx("Dupliquer des saisons")
		.g("button");
		{ e("div").a("id", "putSaisonScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putSaisonScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Dupliquer des saisons").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SaisonScolaire o = new SaisonScolaire();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PUT
						{ e("form").a("action", "").a("id", "putSaisonScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTSaisonScolaire(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-yellow ")
							.a("onclick", "putSaisonScolaire($('#putSaisonScolaireFormulaireValeurs'), ", Optional.ofNullable(saisonScolaire).map(SaisonScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
							.f().sx("Dupliquer des saisons")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchSaisonScolaireModale').show(); ")
			.f().sx("Modifier des saisons")
		.g("button");
		{ e("div").a("id", "patchSaisonScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSaisonScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modifier des saisons").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SaisonScolaire o = new SaisonScolaire();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PATCH
						{ e("form").a("action", "").a("id", "patchSaisonScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHSaisonScolaire(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-yellow ")
							.a("onclick", "patchSaisonScolaire($('#patchSaisonScolaireFormulaireFiltres'), $('#patchSaisonScolaireFormulaireValeurs'), ", Optional.ofNullable(saisonScolaire).map(SaisonScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
							.f().sx("Modifier des saisons")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listeSaisonScolaire != null && listeSaisonScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#deleteSaisonScolaireModale').show(); ")
				.f().sx("Supprimer des saisons")
			.g("button");
			{ e("div").a("id", "deleteSaisonScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-yellow ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSaisonScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Supprimer des saisons").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SaisonScolaire o = new SaisonScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form DELETE
							{ e("div").a("id", "deleteSaisonScolaireForm").f();
								htmlFormPATCHSaisonScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-yellow ")
								.a("onclick", "deleteSaisonScolaire(", o.getPk(), "); ")
								.f().sx("Supprimer des saisons")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestSeasonGenPage
	 * r: "/saison"
	 * r.enUS: "/season"
	 * r: "voir toutes les saisons"
	 * r.enUS: "see all the seasons"
	 * r: "rechargerSaisonGenPage"
	 * r.enUS: "refreshSeasonGenPage"
	 * r: "recharger toutes les saisons"
	 * r.enUS: "refresh all the seasons"
	 * r: "rechercher  : "
	 * r.enUS: "search school seasons: "
	 * r: "suggereFormSaisonScolaire"
	 * r.enUS: "suggestFormSchoolSeason"
	 * r: "rechercher "
	 * r.enUS: "search school seasons"
	 * r: "suggereSaisonScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolSeason w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereSaisonScolaire"
	 * r.enUS: "suggestSchoolSeason"
	 * r: patchSaisonScolaireVals
	 * r.enUS: patchSchoolSeasonVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerSaisonGenPage
	 * r.enUS: refreshSeasonGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereSaisonScolaireObjetSuggere
	 * r.enUS: suggestSchoolSeasonObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListSaisonScolaire'
	 * r.enUS: '#suggestListSchoolSeason'
	 * r: "suggereListSaisonScolaire"
	 * r.enUS: "suggestListSchoolSeason"
	**/
	public static void htmlSuggereSaisonGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("id", "rechargerToutesSaisonGenPage", id).a("href", "/saison").a("class", "").a("onclick", "patchSaisonScolaireVals([], {}, function() { ajouterLueur($('#rechargerToutesSaisonGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesSaisonGenPage", id, "')); }); return false; ").f();
				p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
				p.sx("recharger toutes les saisons");
			} p.g("a");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher  : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher saisons")
				.a("class", "suggereSaisonScolaire w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereSaisonScolaire")
				.a("id", "suggereSaisonScolaire", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereSaisonScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListSaisonScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListSaisonScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/saison").a("class", "").f();
				p.e("i").a("class", "far fa-sun ").f().g("i");
				p.sx("voir toutes les saisons");
			} p.g("a");
		} p.g("div");
	}

}
