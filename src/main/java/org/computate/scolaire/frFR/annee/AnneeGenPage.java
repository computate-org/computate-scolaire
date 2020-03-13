package org.computate.scolaire.frFR.annee;

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
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.math.BigDecimal;
import java.math.MathContext;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.year.YearGenPage
 **/
public class AnneeGenPage extends AnneeGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeAnneeScolaire(Couverture<ListeRecherche<AnneeScolaire>> c) {
	}

	protected void _anneeScolaire(Couverture<AnneeScolaire> c) {
		if(listeAnneeScolaire != null && listeAnneeScolaire.size() == 1)
			c.o(listeAnneeScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("années");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(anneeScolaire != null && anneeScolaire.getAnneeNomComplet() != null)
			c.o(anneeScolaire.getAnneeNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(anneeScolaire != null && anneeScolaire.getAnneeNomComplet() != null)
			c.o(anneeScolaire.getAnneeNomComplet());
		else if(anneeScolaire != null)
			c.o("");
		else if(listeAnneeScolaire == null || listeAnneeScolaire.size() == 0)
			c.o("aucune année trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/annee");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/annee-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("calendar-check");
	}

	@Override public void initLoinAnneeGenPage() {
		initAnneeGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsAnneeGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/AnneePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/EcolePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/SaisonPage.js").f().g("script");
	}

	@Override public void htmlScriptAnneeGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggereAnneeScolaireEcoleCle([{'name':'fq','value':'anneeCles:' + pk}], $('#listAnneeScolaireEcoleCle_Page'), pk); ");
		tl(2, "suggereAnneeScolaireSaisonCles([{'name':'fq','value':'anneeCle:' + pk}], $('#listAnneeScolaireSaisonCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketAnneeScolaire(websocketAnneeScolaireInner);");
		l("});");
	}

	public void htmlFormPageAnneeScolaire(AnneeScolaire o) {
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
			o.htmAnneeDebut("Page");
			o.htmAnneeFin("Page");
			o.htmAnneeFraisInscription("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("Page");
			o.htmSaisonCles("Page");
		} g("div");
	}

	public void htmlFormPOSTAnneeScolaire(AnneeScolaire o) {
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
			o.htmAnneeDebut("POST");
			o.htmAnneeFin("POST");
			o.htmAnneeFraisInscription("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("POST");
			o.htmSaisonCles("POST");
		} g("div");
	}

	public void htmlFormPUTAnneeScolaire(AnneeScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeDebut("PUT");
			o.htmAnneeFin("PUT");
			o.htmAnneeFraisInscription("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("PUT");
			o.htmSaisonCles("PUT");
		} g("div");
	}

	public void htmlFormPATCHAnneeScolaire(AnneeScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAnneeDebut("PATCH");
			o.htmAnneeFin("PATCH");
			o.htmAnneeFraisInscription("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("PATCH");
			o.htmSaisonCles("PATCH");
		} g("div");
	}

	public void htmlFormRechercheAnneeScolaire(AnneeScolaire o) {
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
			o.htmAnneeDebut("Recherche");
			o.htmAnneeFin("Recherche");
			o.htmAnneeFraisInscription("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("Recherche");
			o.htmSaisonCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyAnneeGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeAnneeScolaire == null || listeAnneeScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/annee").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("années").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-orange ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucune année trouvée").g("span");
				} g("span");
			} g("h2");
		} else if(listeAnneeScolaire != null && listeAnneeScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			AnneeScolaire o = listeAnneeScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/annee").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-orange ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-orange ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/annee").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeAnneeScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeAnneeScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeAnneeScolaire.getRows();
					Integer start1 = listeAnneeScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/annee?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/annee?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/annee?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/annee?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1AnneeGenPage();
		}

		if(listeAnneeScolaire != null && listeAnneeScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			AnneeScolaire o = listeAnneeScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "AnneeScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageAnneeScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsAnneeGenPage();
		htmlSuggereAnneeGenPage(this, null);
		g("div");
	}

	public void table1AnneeGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2AnneeGenPage();
		} g("table");
	}

	public void table2AnneeGenPage() {
		thead1AnneeGenPage();
		tbody1AnneeGenPage();
		tfoot1AnneeGenPage();
	}

	public void thead1AnneeGenPage() {
		{ e("thead").a("class", "w3-orange w3-hover-orange ").f();
			thead2AnneeGenPage();
		} g("thead");
	}

	public void thead2AnneeGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1AnneeGenPage() {
		{ e("tbody").f();
			tbody2AnneeGenPage();
		} g("tbody");
	}

	public void tbody2AnneeGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeAnneeScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeAnneeScolaire.size(); i++) {
			AnneeScolaire o = listeAnneeScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/annee/" + o.getPk();
			{ e("tr").f();
				if(getColonneCree()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strCree());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColonneObjetTitre()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							e("i").a("class", "far fa-calendar-check ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1AnneeGenPage() {
		{ e("tfoot").a("class", "w3-orange w3-hover-orange ").f();
			tfoot2AnneeGenPage();
		} g("tfoot");
	}

	public void tfoot2AnneeGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeAnneeScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			if(getColonneCree()) {
				e("td").f();
				g("td");
			}
			if(getColonneObjetTitre()) {
				e("td").f();
				g("td");
			}
		} g("tr");
	}

	public Boolean getColonneCree() {
		return true;
	}

	public Boolean getColonneObjetTitre() {
		return true;
	}

	public void htmlBodyFormsAnneeGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		if(listeAnneeScolaire != null && listeAnneeScolaire.size() == 1) {
			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
					.a("id", "rechargerCetteAnneeGenPage")
					.a("onclick", "patchAnneeScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCetteAnneeGenPage')); }, function() { ajouterErreur($('#rechargerCetteAnneeGenPage')); }); return false; ").f();
					e("i").a("class", "fas fa-sync-alt ").f().g("i");
				sx("recharger cette année");
			} g("button");
		}

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
			.a("onclick", "$('#postAnneeScolaireModale').show(); ")
			.f().sx("Créer une année")
		.g("button");
		{ e("div").a("id", "postAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-orange ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postAnneeScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Créer une année").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						AnneeScolaire o = new AnneeScolaire();
						o.setRequeteSite_(requeteSite_);

						// Form POST
						{ e("div").a("id", "postAnneeScolaireForm").f();
							htmlFormPOSTAnneeScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
							.a("onclick", "postAnneeScolaire($('#postAnneeScolaireForm')); ")
							.f().sx("Créer une année")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
			.a("onclick", "$('#putAnneeScolaireModale').show(); ")
			.f().sx("Dupliquer des années")
		.g("button");
		{ e("div").a("id", "putAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-orange ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putAnneeScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Dupliquer des années").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						AnneeScolaire o = new AnneeScolaire();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PUT
						{ e("form").a("action", "").a("id", "putAnneeScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTAnneeScolaire(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
							.a("onclick", "putAnneeScolaire($('#putAnneeScolaireFormulaireValeurs'), ", Optional.ofNullable(anneeScolaire).map(AnneeScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
							.f().sx("Dupliquer des années")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
			.a("onclick", "$('#patchAnneeScolaireModale').show(); ")
			.f().sx("Modifier des années")
		.g("button");
		{ e("div").a("id", "patchAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-orange ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchAnneeScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modifier des années").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						AnneeScolaire o = new AnneeScolaire();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PATCH
						{ e("form").a("action", "").a("id", "patchAnneeScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHAnneeScolaire(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
							.a("onclick", "patchAnneeScolaire($('#patchAnneeScolaireFormulaireFiltres'), $('#patchAnneeScolaireFormulaireValeurs'), ", Optional.ofNullable(anneeScolaire).map(AnneeScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
							.f().sx("Modifier des années")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listeAnneeScolaire != null && listeAnneeScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#deleteAnneeScolaireModale').show(); ")
				.f().sx("Supprimer des années")
			.g("button");
			{ e("div").a("id", "deleteAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteAnneeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Supprimer des années").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							AnneeScolaire o = new AnneeScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form DELETE
							{ e("div").a("id", "deleteAnneeScolaireForm").f();
								htmlFormPATCHAnneeScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "deleteAnneeScolaire(", o.getPk(), "); ")
								.f().sx("Supprimer des années")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestYearGenPage
	 * r: "/annee"
	 * r.enUS: "/year"
	 * r: "voir toutes les années"
	 * r.enUS: "see all the years"
	 * r: "rechargerAnneeGenPage"
	 * r.enUS: "refreshYearGenPage"
	 * r: "recharger toutes les années"
	 * r.enUS: "refresh all the years"
	 * r: "rechercher années : "
	 * r.enUS: "search years: "
	 * r: "suggereFormAnneeScolaire"
	 * r.enUS: "suggestFormSchoolYear"
	 * r: "rechercher années"
	 * r.enUS: "search years"
	 * r: "suggereAnneeScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolYear w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereAnneeScolaire"
	 * r.enUS: "suggestSchoolYear"
	 * r: patchAnneeScolaireVals
	 * r.enUS: patchSchoolYearVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerAnneeGenPage
	 * r.enUS: refreshYearGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereAnneeScolaireObjetSuggere
	 * r.enUS: suggestSchoolYearObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListAnneeScolaire'
	 * r.enUS: '#suggestListSchoolYear'
	 * r: "suggereListAnneeScolaire"
	 * r.enUS: "suggestListSchoolYear"
	**/
	public static void htmlSuggereAnneeGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("id", "rechargerToutesAnneeGenPage", id).a("href", "/annee").a("class", "").a("onclick", "patchAnneeScolaireVals([], {}, function() { ajouterLueur($('#rechargerToutesAnneeGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesAnneeGenPage", id, "')); }); return false; ").f();
				p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
				p.sx("recharger toutes les années");
			} p.g("a");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher années : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher années")
				.a("class", "suggereAnneeScolaire w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereAnneeScolaire")
				.a("id", "suggereAnneeScolaire", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereAnneeScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListAnneeScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListAnneeScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/annee").a("class", "").f();
				p.e("i").a("class", "far fa-calendar-check ").f().g("i");
				p.sx("voir toutes les années");
			} p.g("a");
		} p.g("div");
	}

}
