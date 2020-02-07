package org.computate.scolaire.frFR.ecole;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.school.SchoolGenPage
 **/
public class EcoleGenPage extends EcoleGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeEcole(Couverture<ListeRecherche<Ecole>> c) {
	}

	protected void _ecole(Couverture<Ecole> c) {
		if(listeEcole != null && listeEcole.size() == 1)
			c.o(listeEcole.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("écoles");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(ecole != null && ecole.getEcoleNomComplet() != null)
			c.o(ecole.getEcoleNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(ecole != null && ecole.getEcoleNomComplet() != null)
			c.o(ecole.getEcoleNomComplet());
		else if(ecole != null)
			c.o("");
		else if(listeEcole == null || listeEcole.size() == 0)
			c.o("aucune école trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/ecole");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/ecole-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("school");
	}

	@Override public void initLoinEcoleGenPage() {
		initEcoleGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsEcoleGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/EcolePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AnneePage.js").f().g("script");
	}

	@Override public void htmlScriptEcoleGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggereEcoleAnneeCles([{'name':'fq','value':'ecoleCle:' + pk}], $('#listEcoleAnneeCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketEcole(websocketEcoleInner);");
		l("});");
	}

	public void htmlFormPageEcole(Ecole o) {
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
			o.htmEcoleNom("Page");
			o.htmEcoleAdministrateurNom("Page");
			o.htmEcoleEmplacement("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleNumeroTelephone("Page");
			o.htmEcoleAddresse("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCles("Page");
		} g("div");
	}

	public void htmlFormPOSTEcole(Ecole o) {
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
			o.htmEcoleNom("POST");
			o.htmEcoleAdministrateurNom("POST");
			o.htmEcoleEmplacement("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleNumeroTelephone("POST");
			o.htmEcoleAddresse("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCles("POST");
		} g("div");
	}

	public void htmlFormPUTEcole(Ecole o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleNom("PUT");
			o.htmEcoleAdministrateurNom("PUT");
			o.htmEcoleEmplacement("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleNumeroTelephone("PUT");
			o.htmEcoleAddresse("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCles("PUT");
		} g("div");
	}

	public void htmlFormPATCHEcole(Ecole o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleNom("PATCH");
			o.htmEcoleAdministrateurNom("PATCH");
			o.htmEcoleEmplacement("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleNumeroTelephone("PATCH");
			o.htmEcoleAddresse("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCles("PATCH");
		} g("div");
	}

	public void htmlFormRechercheEcole(Ecole o) {
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
			o.htmEcoleNom("Recherche");
			o.htmEcoleAdministrateurNom("Recherche");
			o.htmEcoleEmplacement("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleNumeroTelephone("Recherche");
			o.htmEcoleAddresse("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyEcoleGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeEcole == null || listeEcole.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/ecole").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("écoles").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucune école trouvée").g("span");
				} g("span");
			} g("h2");
		} else if(listeEcole != null && listeEcole.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Ecole o = listeEcole.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/ecole").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/ecole").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeEcole.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeEcole.getQuery(), "_suggested", "");
					Integer rows1 = listeEcole.getRows();
					Integer start1 = listeEcole.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/ecole?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/ecole?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/ecole?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/ecole?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-pink w3-hover-pink ").f();
					{ e("tr").f();
						e("th").f().sx("crée").g("th");
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeEcole.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeEcole.size(); i++) {
						Ecole o = listeEcole.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/ecole/" + o.getPk();
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
									e("i").a("class", "far fa-school ").f().g("i");
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

		if(listeEcole != null && listeEcole.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Ecole o = listeEcole.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "EcoleForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageEcole(o);
				}

			} g("div");

		}
		htmlBodyFormsEcoleGenPage();
		htmlSuggereEcoleGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsEcoleGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("id", "rechargerCetteEcoleGenPage")
				.a("onclick", "patchEcoleVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCetteEcoleGenPage')); }, function() { ajouterErreur($('#rechargerCetteEcoleGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("recharger cette école");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postEcoleModale').show(); ")
			.f().sx("Créer une école")
		.g("button");
		{ e("div").a("id", "postEcoleModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postEcoleModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Créer une école").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						Ecole o = new Ecole();
						o.setRequeteSite_(requeteSite_);

						// Form POST
						{ e("div").a("id", "postEcoleForm").f();
							htmlFormPOSTEcole(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
							.a("onclick", "postEcole($('#postEcoleForm')); ")
							.f().sx("Créer une école")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#putEcoleModale').show(); ")
			.f().sx("Dupliquer des écoles")
		.g("button");
		{ e("div").a("id", "putEcoleModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putEcoleModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Dupliquer des écoles").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						Ecole o = new Ecole();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PUT
						{ e("form").a("action", "").a("id", "putEcoleFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTEcole(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
							.a("onclick", "putEcole($('#putEcoleFormulaireValeurs'), ", Optional.ofNullable(ecole).map(Ecole::getPk).map(a -> a.toString()).orElse("null"), "); ")
							.f().sx("Dupliquer des écoles")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchEcoleModale').show(); ")
			.f().sx("Modifier des écoles")
		.g("button");
		{ e("div").a("id", "patchEcoleModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEcoleModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modifier des écoles").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						Ecole o = new Ecole();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PATCH
						{ e("form").a("action", "").a("id", "patchEcoleFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHEcole(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
							.a("onclick", "patchEcole($('#patchEcoleFormulaireFiltres'), $('#patchEcoleFormulaireValeurs'), ", Optional.ofNullable(ecole).map(Ecole::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
							.f().sx("Modifier des écoles")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listeEcole != null && listeEcole.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("onclick", "$('#deleteEcoleModale').show(); ")
				.f().sx("Supprimer des écoles")
			.g("button");
			{ e("div").a("id", "deleteEcoleModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-pink ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteEcoleModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Supprimer des écoles").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							Ecole o = new Ecole();
							o.setRequeteSite_(requeteSite_);

							// Form DELETE
							{ e("div").a("id", "deleteEcoleForm").f();
								htmlFormPATCHEcole(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
								.a("onclick", "deleteEcole(", o.getPk(), "); ")
								.f().sx("Supprimer des écoles")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestSchoolGenPage
	 * r: "/ecole"
	 * r.enUS: "/school"
	 * r: "voir toutes les écoles"
	 * r.enUS: "see all the schools"
	 * r: "rechargerEcoleGenPage"
	 * r.enUS: "refreshSchoolGenPage"
	 * r: "recharger toutes les écoles"
	 * r.enUS: "refresh all the schools"
	 * r: "rechercher écoles : "
	 * r.enUS: "search schools: "
	 * r: "suggereFormEcole"
	 * r.enUS: "suggestFormSchool"
	 * r: "rechercher écoles"
	 * r.enUS: "search schools"
	 * r: "suggereEcole w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchool w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereEcole"
	 * r.enUS: "suggestSchool"
	 * r: patchEcoleVals
	 * r.enUS: patchSchoolVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerEcoleGenPage
	 * r.enUS: refreshSchoolGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereEcoleObjetSuggere
	 * r.enUS: suggestSchoolObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListEcole'
	 * r.enUS: '#suggestListSchool'
	 * r: "suggereListEcole"
	 * r.enUS: "suggestListSchool"
	**/
	public static void htmlSuggereEcoleGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("id", "rechargerToutesEcoleGenPage", id).a("href", "/ecole").a("class", "").a("onclick", "patchEcoleVals([], {}, function() { ajouterLueur($('#rechargerToutesEcoleGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesEcoleGenPage", id, "')); }); return false; ").f();
				p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
				p.sx("recharger toutes les écoles");
			} p.g("a");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher écoles : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher écoles")
				.a("class", "suggereEcole w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereEcole")
				.a("id", "suggereEcole", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereEcoleObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListEcole", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListEcole", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/ecole").a("class", "").f();
				p.e("i").a("class", "far fa-school ").f().g("i");
				p.sx("voir toutes les écoles");
			} p.g("a");
		} p.g("div");
	}

}
