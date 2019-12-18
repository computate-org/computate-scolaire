package org.computate.scolaire.frFR.gardien;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.guardian.GuardianGenPage
 **/
public class GardienGenPage extends GardienGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeGardienScolaire(Couverture<ListeRecherche<GardienScolaire>> c) {
	}

	protected void _gardienScolaire(Couverture<GardienScolaire> c) {
		if(listeGardienScolaire != null && listeGardienScolaire.size() == 1)
			c.o(listeGardienScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("gardiens");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(gardienScolaire != null && gardienScolaire.getGardienNomComplet() != null)
			c.o(gardienScolaire.getGardienNomComplet());
		else if(gardienScolaire != null)
			c.o("");
		else if(listeGardienScolaire == null || listeGardienScolaire.size() == 0)
			c.o("aucun gardien trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/gardien");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/gardien-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("phone");
	}

	@Override public void initLoinGardienGenPage() {
		initGardienGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsGardienGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/GardienPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptGardienGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggereGardienScolaireInscriptionCles([{'name':'fq','value':'gardienCles:", requeteSite_.getRequetePk(), "'}], $('#listGardienScolaireInscriptionCles_Page'), ", requeteSite_.getRequetePk(), "); ");
		tl(1, "websocketGardienScolaire(async function(requetePatch) {");
		tl(2, "var pk = requetePatch['pk'];");
		tl(2, "var pks = requetePatch['pks'];");
		tl(2, "var classes = requetePatch['classes'];");
		tl(2, "if(pks) {");
		tl(3, "for(i=0; i < pks.length; i++) {");
		tl(4, "var pk2 = pks[i];");
		tl(4, "var c = classes[i];");
		tl(4, "await window['patch' + c + 'Vals']( [ {name: 'fq', value: 'pk:' + pk2} ], {});");
		tl(3, "}");
		tl(2, "}");
		tl(2, "await patchGardienScolaireVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageGardienScolaire(GardienScolaire o) {
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
			o.htmPersonnePrenom("Page");
			o.htmFamilleNom("Page");
			o.htmPersonnePrenomPrefere("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneNumeroTelephone("Page");
			o.htmPersonneOccupation("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Page");
		} g("div");
	}

	public void htmlFormPOSTGardienScolaire(GardienScolaire o) {
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
			o.htmPersonnePrenom("POST");
			o.htmFamilleNom("POST");
			o.htmPersonnePrenomPrefere("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneNumeroTelephone("POST");
			o.htmPersonneOccupation("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("POST");
		} g("div");
	}

	public void htmlFormPATCHGardienScolaire(GardienScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("PATCH");
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
			o.htmObjetId("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonnePrenom("PATCH");
			o.htmFamilleNom("PATCH");
			o.htmPersonnePrenomPrefere("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneNumeroTelephone("PATCH");
			o.htmPersonneOccupation("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGardienNomComplet("PATCH");
		} g("div");
	}

	public void htmlFormRechercheGardienScolaire(GardienScolaire o) {
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
			o.htmPersonnePrenom("Recherche");
			o.htmFamilleNom("Recherche");
			o.htmPersonnePrenomPrefere("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneNumeroTelephone("Recherche");
			o.htmPersonneOccupation("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGardienNomComplet("Recherche");
		} g("div");
	}

	@Override public void htmlBodyGardienGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeGardienScolaire == null || listeGardienScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/gardien").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("gardiens").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun gardien trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeGardienScolaire != null && listeGardienScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			GardienScolaire o = listeGardienScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/gardien").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
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
					{ e("a").a("href", "/gardien").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeGardienScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeGardienScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeGardienScolaire.getRows();
					Integer start1 = listeGardienScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/gardien?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/gardien?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/gardien?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/gardien?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-yellow w3-hover-yellow ").f();
					{ e("tr").f();
						e("th").f().sx("").g("th");
						e("th").f().sx("crée").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeGardienScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeGardienScolaire.size(); i++) {
						GardienScolaire o = listeGardienScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/gardien/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-phone w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strGardienNomComplet());
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

		if(listeGardienScolaire != null && listeGardienScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			GardienScolaire o = listeGardienScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "GardienScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageGardienScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsGardienGenPage();
		htmlSuggereGardienGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsGardienGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("id", "rechargerCeGardienGenPage")
				.a("onclick", "patchGardienScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeGardienGenPage')); }, function() { ajouterErreur($('#rechargerCeGardienGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger ce gardien");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postGardienScolaireModale').show(); ")
			.f().sx("Créer un gardien")
		.g("button");
		{ e("div").a("id", "postGardienScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postGardienScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer un gardien").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					GardienScolaire o = new GardienScolaire();
					o.setRequeteSite_(requeteSite_);

					// Form POST
					{ e("div").a("id", "postGardienScolaireForm").f();
						htmlFormPOSTGardienScolaire(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postGardienScolaire($('#postGardienScolaireForm')); ")
						.f().sx("Créer un gardien")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchGardienScolaireModale').show(); ")
			.f().sx("Modifier des gardiens")
		.g("button");
		{ e("div").a("id", "patchGardienScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchGardienScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des gardiens").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					GardienScolaire o = new GardienScolaire();
					o.setRequeteSite_(requeteSite_);

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchGardienScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheGardienScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "rechercheGardienScolaire($('#patchGardienScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des un gardien")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchGardienScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHGardienScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchGardienScolaire($('#patchGardienScolaireFormulaireFiltres'), $('#patchGardienScolaireFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des gardiens")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeGardienScolaire != null && listeGardienScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#deleteGardienScolaireModale').show(); ")
				.f().sx("Supprimer des gardiens")
			.g("button");
			{ e("div").a("id", "deleteGardienScolaireModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteGardienScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des gardiens").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						GardienScolaire o = new GardienScolaire();
						o.setRequeteSite_(requeteSite_);

						// Form DELETE
						{ e("div").a("id", "deleteGardienScolaireForm").f();
							htmlFormPATCHGardienScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
							.a("onclick", "deleteGardienScolaire(", o.getPk(), "); ")
							.f().sx("Supprimer des gardiens")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestGuardianGenPage
	 * r: "/gardien"
	 * r.enUS: "/guardian"
	 * r: "voir tous les gardiens"
	 * r.enUS: "see all the guardians"
	 * r: "rechargerGardienGenPage"
	 * r.enUS: "refreshGuardianGenPage"
	 * r: "recharger tous les gardiens"
	 * r.enUS: "refresh all the guardians"
	 * r: "rechercher gardiens : "
	 * r.enUS: "search guardians: "
	 * r: "suggereFormGardienScolaire"
	 * r.enUS: "suggestFormSchoolGuardian"
	 * r: "rechercher gardiens"
	 * r.enUS: "search guardians"
	 * r: "suggereGardienScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolGuardian w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereGardienScolaire"
	 * r.enUS: "suggestSchoolGuardian"
	 * r: patchGardienScolaireVals
	 * r.enUS: patchSchoolGuardianVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerGardienGenPage
	 * r.enUS: refreshGuardianGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereGardienScolaireObjetSuggere
	 * r.enUS: suggestSchoolGuardianObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListGardienScolaire'
	 * r.enUS: '#suggestListSchoolGuardian'
	 * r: "suggereListGardienScolaire"
	 * r.enUS: "suggestListSchoolGuardian"
	**/
	public static void htmlSuggereGardienGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/gardien").a("class", "").f();
					p.e("i").a("class", "far fa-phone w3-padding-small ").f().g("i");
					p.sx("voir tous les gardiens");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerTousGardienGenPage", id).a("href", "/gardien").a("class", "").a("onclick", "patchGardienScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousGardienGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousGardienGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger tous les gardiens");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher gardiens : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormGardienScolaire", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/gardien?q=objetSuggere:' + encodeURIComponent($('#suggereGardienScolaire", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher gardiens")
							.a("class", "suggereGardienScolaire w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereGardienScolaire")
							.a("id", "suggereGardienScolaire", id)
							.a("autocomplete", "off")
							.a("oninput", "suggereGardienScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListGardienScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListGardienScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
