package org.computate.scolaire.frFR.inscription.design;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignGenPage
 **/
public class DesignInscriptionGenPage extends DesignInscriptionGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeDesignInscription(Couverture<ListeRecherche<DesignInscription>> c) {
	}

	protected void _designInscription(Couverture<DesignInscription> c) {
		if(listeDesignInscription != null && listeDesignInscription.size() == 1)
			c.o(listeDesignInscription.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("design d'inscriptions");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(designInscription != null && designInscription.getDesignInscriptionNomComplet() != null)
			c.o(designInscription.getDesignInscriptionNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(designInscription != null && designInscription.getDesignInscriptionNomComplet() != null)
			c.o(designInscription.getDesignInscriptionNomComplet());
		else if(designInscription != null)
			c.o("");
		else if(listeDesignInscription == null || listeDesignInscription.size() == 0)
			c.o("aucun design d'inscription trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/design-inscription");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/design-inscription-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("drafting-compass");
	}

	@Override public void initLoinDesignInscriptionGenPage() {
		initDesignInscriptionGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsDesignInscriptionGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/DesignInscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PartHtmlPage.js").f().g("script");
	}

	@Override public void htmlScriptDesignInscriptionGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggereDesignInscriptionPartHtmlCles([{'name':'fq','value':'designInscriptionCle:' + pk}], $('#listDesignInscriptionPartHtmlCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketDesignInscription(websocketDesignInscriptionInner);");
		l("});");
	}

	public void htmlFormPageDesignInscription(DesignInscription o) {
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
			o.htmDesignInscriptionNomComplet("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("Page");
		} g("div");
	}

	public void htmlFormPOSTDesignInscription(DesignInscription o) {
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
			o.htmDesignInscriptionNomComplet("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("POST");
		} g("div");
	}

	public void htmlFormPUTDesignInscription(DesignInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignInscriptionNomComplet("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("PUT");
		} g("div");
	}

	public void htmlFormPATCHDesignInscription(DesignInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignInscriptionNomComplet("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("PATCH");
		} g("div");
	}

	public void htmlFormRechercheDesignInscription(DesignInscription o) {
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
			o.htmDesignInscriptionNomComplet("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyDesignInscriptionGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeDesignInscription == null || listeDesignInscription.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/design-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("design d'inscriptions").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun design d'inscription trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeDesignInscription != null && listeDesignInscription.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			DesignInscription o = listeDesignInscription.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/design-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/design-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeDesignInscription.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeDesignInscription.getQuery(), "_suggested", "");
					Integer rows1 = listeDesignInscription.getRows();
					Integer start1 = listeDesignInscription.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/design-inscription?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/design-inscription?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/design-inscription?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/design-inscription?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-indigo w3-hover-indigo ").f();
					{ e("tr").f();
						e("th").f().sx("crée").g("th");
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeDesignInscription.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeDesignInscription.size(); i++) {
						DesignInscription o = listeDesignInscription.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/design-inscription/" + o.getPk();
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
									e("i").a("class", "far fa-drafting-compass ").f().g("i");
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

		if(listeDesignInscription != null && listeDesignInscription.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			DesignInscription o = listeDesignInscription.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "DesignInscriptionForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageDesignInscription(o);
				}

			} g("div");

		}
		htmlBodyFormsDesignInscriptionGenPage();
		htmlSuggereDesignInscriptionGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsDesignInscriptionGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("id", "rechargerCeDesignInscriptionGenPage")
				.a("onclick", "patchDesignInscriptionVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeDesignInscriptionGenPage')); }, function() { ajouterErreur($('#rechargerCeDesignInscriptionGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("recharger ce design d'inscription");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#postDesignInscriptionModale').show(); ")
			.f().sx("Créer un design d'inscription")
		.g("button");
		{ e("div").a("id", "postDesignInscriptionModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postDesignInscriptionModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Créer un design d'inscription").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						DesignInscription o = new DesignInscription();
						o.setRequeteSite_(requeteSite_);

						// Form POST
						{ e("div").a("id", "postDesignInscriptionForm").f();
							htmlFormPOSTDesignInscription(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "postDesignInscription($('#postDesignInscriptionForm')); ")
							.f().sx("Créer un design d'inscription")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#putDesignInscriptionModale').show(); ")
			.f().sx("Dupliquer des design d'inscriptions")
		.g("button");
		{ e("div").a("id", "putDesignInscriptionModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putDesignInscriptionModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Dupliquer des design d'inscriptions").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						DesignInscription o = new DesignInscription();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PUT
						{ e("form").a("action", "").a("id", "putDesignInscriptionFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTDesignInscription(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "putDesignInscription($('#putDesignInscriptionFormulaireValeurs')); ")
							.f().sx("Dupliquer des design d'inscriptions")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#patchDesignInscriptionModale').show(); ")
			.f().sx("Modifier des design d'inscriptions")
		.g("button");
		{ e("div").a("id", "patchDesignInscriptionModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchDesignInscriptionModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modifier des design d'inscriptions").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						DesignInscription o = new DesignInscription();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PATCH
						{ e("form").a("action", "").a("id", "patchDesignInscriptionFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHDesignInscription(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "patchDesignInscription($('#patchDesignInscriptionFormulaireFiltres'), $('#patchDesignInscriptionFormulaireValeurs'), function() {}, function() {}); ")
							.f().sx("Modifier des design d'inscriptions")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listeDesignInscription != null && listeDesignInscription.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#deleteDesignInscriptionModale').show(); ")
				.f().sx("Supprimer des design d'inscriptions")
			.g("button");
			{ e("div").a("id", "deleteDesignInscriptionModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteDesignInscriptionModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Supprimer des design d'inscriptions").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							DesignInscription o = new DesignInscription();
							o.setRequeteSite_(requeteSite_);

							// Form DELETE
							{ e("div").a("id", "deleteDesignInscriptionForm").f();
								htmlFormPATCHDesignInscription(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "deleteDesignInscription(", o.getPk(), "); ")
								.f().sx("Supprimer des design d'inscriptions")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestEnrollmentDesignGenPage
	 * r: "/design-inscription"
	 * r.enUS: "/enrollment-design"
	 * r: "voir tous les design d'inscriptions"
	 * r.enUS: "see all the enrollment designs"
	 * r: "rechargerDesignInscriptionGenPage"
	 * r.enUS: "refreshEnrollmentDesignGenPage"
	 * r: "recharger tous les design d'inscriptions"
	 * r.enUS: "refresh all the enrollment designs"
	 * r: "rechercher design d'inscriptions : "
	 * r.enUS: "search enrollment designs: "
	 * r: "suggereFormDesignInscription"
	 * r.enUS: "suggestFormEnrollmentDesign"
	 * r: "rechercher design d'inscriptions"
	 * r.enUS: "search enrollment designs"
	 * r: "suggereDesignInscription w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestEnrollmentDesign w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereDesignInscription"
	 * r.enUS: "suggestEnrollmentDesign"
	 * r: patchDesignInscriptionVals
	 * r.enUS: patchEnrollmentDesignVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerDesignInscriptionGenPage
	 * r.enUS: refreshEnrollmentDesignGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereDesignInscriptionObjetSuggere
	 * r.enUS: suggestEnrollmentDesignObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListDesignInscription'
	 * r.enUS: '#suggestListEnrollmentDesign'
	 * r: "suggereListDesignInscription"
	 * r.enUS: "suggestListEnrollmentDesign"
	**/
	public static void htmlSuggereDesignInscriptionGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/design-inscription").a("class", "").f();
					p.e("i").a("class", "far fa-drafting-compass ").f().g("i");
					p.sx("voir tous les design d'inscriptions");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("id", "rechargerTousDesignInscriptionGenPage", id).a("href", "/design-inscription").a("class", "").a("onclick", "patchDesignInscriptionVals([], {}, function() { ajouterLueur($('#rechargerTousDesignInscriptionGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousDesignInscriptionGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("recharger tous les design d'inscriptions");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher design d'inscriptions : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher design d'inscriptions")
				.a("class", "suggereDesignInscription w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereDesignInscription")
				.a("id", "suggereDesignInscription", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereDesignInscriptionObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListDesignInscription", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListDesignInscription", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
