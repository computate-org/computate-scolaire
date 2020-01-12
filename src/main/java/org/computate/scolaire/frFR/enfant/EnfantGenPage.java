package org.computate.scolaire.frFR.enfant;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.child.ChildGenPage
 **/
public class EnfantGenPage extends EnfantGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeEnfantScolaire(Couverture<ListeRecherche<EnfantScolaire>> c) {
	}

	protected void _enfantScolaire(Couverture<EnfantScolaire> c) {
		if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1)
			c.o(listeEnfantScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("enfants");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(enfantScolaire != null && enfantScolaire.getEnfantNomComplet() != null)
			c.o(enfantScolaire.getEnfantNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(enfantScolaire != null && enfantScolaire.getEnfantNomComplet() != null)
			c.o(enfantScolaire.getEnfantNomComplet());
		else if(enfantScolaire != null)
			c.o("");
		else if(listeEnfantScolaire == null || listeEnfantScolaire.size() == 0)
			c.o("aucun enfant trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/enfant");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/enfant-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("child");
	}

	@Override public void initLoinEnfantGenPage() {
		initEnfantGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsEnfantGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/EnfantPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptEnfantGenPage() {
		l("$(document).ready(function() {");
		tl(1, "var pk = ", requeteSite_.getRequetePk(), ";");
		tl(1, "suggereEnfantScolaireInscriptionCles([{'name':'fq','value':'enfantCle:' + pk}], $('#listEnfantScolaireInscriptionCles_Page'), pk); ");
		tl(1, "websocketEnfantScolaire(async function(requetePatch) {");
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
		tl(2, "await patchEnfantScolaireVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageEnfantScolaire(EnfantScolaire o) {
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
			o.htmPersonneDateNaissance("Page");
			o.htmPersonneAgeEnSeptembre("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantPropre("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("Page");
			o.htmEnfantEcolesPrecedemmentFrequentees("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("Page");
			o.htmEnfantObjectifs("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Page");
		} g("div");
	}

	public void htmlFormPOSTEnfantScolaire(EnfantScolaire o) {
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
			o.htmPersonneDateNaissance("POST");
			o.htmPersonneAgeEnSeptembre("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantPropre("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("POST");
			o.htmEnfantEcolesPrecedemmentFrequentees("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("POST");
			o.htmEnfantObjectifs("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("POST");
		} g("div");
	}

	public void htmlFormPATCHEnfantScolaire(EnfantScolaire o) {
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
			o.htmPersonneDateNaissance("PATCH");
			o.htmPersonneAgeEnSeptembre("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantPropre("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("PATCH");
			o.htmEnfantEcolesPrecedemmentFrequentees("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("PATCH");
			o.htmEnfantObjectifs("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantNomComplet("PATCH");
		} g("div");
	}

	public void htmlFormRechercheEnfantScolaire(EnfantScolaire o) {
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
			o.htmPersonneDateNaissance("Recherche");
			o.htmPersonneAgeEnSeptembre("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantPropre("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("Recherche");
			o.htmEnfantEcolesPrecedemmentFrequentees("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("Recherche");
			o.htmEnfantObjectifs("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantNomComplet("Recherche");
		} g("div");
	}

	@Override public void htmlBodyEnfantGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeEnfantScolaire == null || listeEnfantScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("enfants").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun enfant trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			EnfantScolaire o = listeEnfantScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
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
					{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeEnfantScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeEnfantScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeEnfantScolaire.getRows();
					Integer start1 = listeEnfantScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/enfant?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, "&start=", start3, "&rows=", rows1).f();
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
					Map<String, Map<String, List<String>>> highlighting = listeEnfantScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeEnfantScolaire.size(); i++) {
						EnfantScolaire o = listeEnfantScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enfant/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strEnfantNomComplet());
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

		if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			EnfantScolaire o = listeEnfantScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "EnfantScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageEnfantScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsEnfantGenPage();
		htmlSuggereEnfantGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsEnfantGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("id", "rechargerCeEnfantGenPage")
				.a("onclick", "patchEnfantScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeEnfantGenPage')); }, function() { ajouterErreur($('#rechargerCeEnfantGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger cet enfant");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#postEnfantScolaireModale').show(); ")
			.f().sx("Créer un enfant")
		.g("button");
		{ e("div").a("id", "postEnfantScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postEnfantScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer un enfant").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EnfantScolaire o = new EnfantScolaire();
					o.setRequeteSite_(requeteSite_);

					// Form POST
					{ e("div").a("id", "postEnfantScolaireForm").f();
						htmlFormPOSTEnfantScolaire(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "postEnfantScolaire($('#postEnfantScolaireForm')); ")
						.f().sx("Créer un enfant")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchEnfantScolaireModale').show(); ")
			.f().sx("Modifier des enfants")
		.g("button");
		{ e("div").a("id", "patchEnfantScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEnfantScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des enfants").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EnfantScolaire o = new EnfantScolaire();
					o.setRequeteSite_(requeteSite_);

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchEnfantScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheEnfantScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "rechercheEnfantScolaire($('#patchEnfantScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des un enfant")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchEnfantScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHEnfantScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchEnfantScolaire($('#patchEnfantScolaireFormulaireFiltres'), $('#patchEnfantScolaireFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des enfants")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#deleteEnfantScolaireModale').show(); ")
				.f().sx("Supprimer des enfants")
			.g("button");
			{ e("div").a("id", "deleteEnfantScolaireModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-green ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteEnfantScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des enfants").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						EnfantScolaire o = new EnfantScolaire();
						o.setRequeteSite_(requeteSite_);

						// Form DELETE
						{ e("div").a("id", "deleteEnfantScolaireForm").f();
							htmlFormPATCHEnfantScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
							.a("onclick", "deleteEnfantScolaire(", o.getPk(), "); ")
							.f().sx("Supprimer des enfants")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestChildGenPage
	 * r: "/enfant"
	 * r.enUS: "/child"
	 * r: "voir tous les enfants"
	 * r.enUS: "see all the children"
	 * r: "rechargerEnfantGenPage"
	 * r.enUS: "refreshChildGenPage"
	 * r: "recharger tous les enfants"
	 * r.enUS: "refresh all the children"
	 * r: "rechercher enfants : "
	 * r.enUS: "search children: "
	 * r: "suggereFormEnfantScolaire"
	 * r.enUS: "suggestFormSchoolChild"
	 * r: "rechercher enfants"
	 * r.enUS: "search children"
	 * r: "suggereEnfantScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolChild w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereEnfantScolaire"
	 * r.enUS: "suggestSchoolChild"
	 * r: patchEnfantScolaireVals
	 * r.enUS: patchSchoolChildVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerEnfantGenPage
	 * r.enUS: refreshChildGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereEnfantScolaireObjetSuggere
	 * r.enUS: suggestSchoolChildObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListEnfantScolaire'
	 * r.enUS: '#suggestListSchoolChild'
	 * r: "suggereListEnfantScolaire"
	 * r.enUS: "suggestListSchoolChild"
	**/
	public static void htmlSuggereEnfantGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/enfant").a("class", "").f();
					p.e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
					p.sx("voir tous les enfants");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerTousEnfantGenPage", id).a("href", "/enfant").a("class", "").a("onclick", "patchEnfantScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousEnfantGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousEnfantGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger tous les enfants");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher enfants : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormEnfantScolaire", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/enfant?q=objetSuggere:' + encodeURIComponent($('#suggereEnfantScolaire", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher enfants")
							.a("class", "suggereEnfantScolaire w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereEnfantScolaire")
							.a("id", "suggereEnfantScolaire", id)
							.a("autocomplete", "off")
							.a("oninput", "suggereEnfantScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListEnfantScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListEnfantScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
