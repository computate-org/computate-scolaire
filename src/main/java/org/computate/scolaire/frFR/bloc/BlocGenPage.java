package org.computate.scolaire.frFR.bloc;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.block.BlockGenPage
 **/
public class BlocGenPage extends BlocGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeBlocScolaire(Couverture<ListeRecherche<BlocScolaire>> c) {
	}

	protected void _blocScolaire(Couverture<BlocScolaire> c) {
		if(listeBlocScolaire != null && listeBlocScolaire.size() == 1)
			c.o(listeBlocScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("blocs");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(blocScolaire != null && blocScolaire.getBlocNomComplet() != null)
			c.o(blocScolaire.getBlocNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(blocScolaire != null && blocScolaire.getBlocNomComplet() != null)
			c.o(blocScolaire.getBlocNomComplet());
		else if(blocScolaire != null)
			c.o("");
		else if(listeBlocScolaire == null || listeBlocScolaire.size() == 0)
			c.o("aucun bloc trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/bloc");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/bloc-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("bell");
	}

	@Override public void initLoinBlocGenPage() {
		initBlocGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsBlocGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/BlocPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AgePage.js").f().g("script");
	}

	@Override public void htmlScriptBlocGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggereBlocScolaireAgeCle([{'name':'fq','value':'blocCles:' + pk}], $('#listBlocScolaireAgeCle_Page'), pk); ");
		tl(2, "suggereBlocScolaireInscriptionCles([{'name':'fq','value':'blocCles:' + pk}], $('#listBlocScolaireInscriptionCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketBlocScolaire(websocketBlocScolaireInner);");
		l("});");
	}

	public void htmlFormPageBlocScolaire(BlocScolaire o) {
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
			o.htmBlocHeureDebut("Page");
			o.htmBlocHeureFin("Page");
			o.htmBlocPrixParMois("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocLundi("Page");
			o.htmBlocMardi("Page");
			o.htmBlocMercredi("Page");
			o.htmBlocJeudi("Page");
			o.htmBlocVendredi("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeCle("Page");
			o.htmInscriptionCles("Page");
		} g("div");
	}

	public void htmlFormPOSTBlocScolaire(BlocScolaire o) {
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
			o.htmBlocHeureDebut("POST");
			o.htmBlocHeureFin("POST");
			o.htmBlocPrixParMois("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocLundi("POST");
			o.htmBlocMardi("POST");
			o.htmBlocMercredi("POST");
			o.htmBlocJeudi("POST");
			o.htmBlocVendredi("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeCle("POST");
			o.htmInscriptionCles("POST");
		} g("div");
	}

	public void htmlFormPUTBlocScolaire(BlocScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocHeureDebut("PUT");
			o.htmBlocHeureFin("PUT");
			o.htmBlocPrixParMois("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocLundi("PUT");
			o.htmBlocMardi("PUT");
			o.htmBlocMercredi("PUT");
			o.htmBlocJeudi("PUT");
			o.htmBlocVendredi("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeCle("PUT");
			o.htmInscriptionCles("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleAddresse("PUT");
		} g("div");
	}

	public void htmlFormPATCHBlocScolaire(BlocScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocHeureDebut("PATCH");
			o.htmBlocHeureFin("PATCH");
			o.htmBlocPrixParMois("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocLundi("PATCH");
			o.htmBlocMardi("PATCH");
			o.htmBlocMercredi("PATCH");
			o.htmBlocJeudi("PATCH");
			o.htmBlocVendredi("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeCle("PATCH");
			o.htmInscriptionCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleAddresse("PATCH");
		} g("div");
	}

	public void htmlFormRechercheBlocScolaire(BlocScolaire o) {
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
			o.htmBlocHeureDebut("Recherche");
			o.htmBlocHeureFin("Recherche");
			o.htmBlocPrixParMois("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocLundi("Recherche");
			o.htmBlocMardi("Recherche");
			o.htmBlocMercredi("Recherche");
			o.htmBlocJeudi("Recherche");
			o.htmBlocVendredi("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeCle("Recherche");
			o.htmInscriptionCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
			o.htmEcoleAddresse("Recherche");
		} g("div");
	}

	@Override public void htmlBodyBlocGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeBlocScolaire == null || listeBlocScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/bloc").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("blocs").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun bloc trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeBlocScolaire != null && listeBlocScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			BlocScolaire o = listeBlocScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/bloc").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
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
				{ e("a").a("href", "/bloc").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeBlocScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeBlocScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeBlocScolaire.getRows();
					Integer start1 = listeBlocScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/bloc?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/bloc?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/bloc?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/bloc?q=", query, "&start=", start3, "&rows=", rows1).f();
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
					Map<String, Map<String, List<String>>> highlighting = listeBlocScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeBlocScolaire.size(); i++) {
						BlocScolaire o = listeBlocScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/bloc/" + o.getPk();
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
									e("i").a("class", "far fa-bell ").f().g("i");
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

		if(listeBlocScolaire != null && listeBlocScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			BlocScolaire o = listeBlocScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "BlocScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageBlocScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsBlocGenPage();
		htmlSuggereBlocGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsBlocGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("id", "rechargerCeBlocGenPage")
				.a("onclick", "patchBlocScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeBlocGenPage')); }, function() { ajouterErreur($('#rechargerCeBlocGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("recharger ce bloc");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#postBlocScolaireModale').show(); ")
			.f().sx("Créer un bloc")
		.g("button");
		{ e("div").a("id", "postBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postBlocScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Créer un bloc").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						BlocScolaire o = new BlocScolaire();
						o.setRequeteSite_(requeteSite_);

						// Form POST
						{ e("div").a("id", "postBlocScolaireForm").f();
							htmlFormPOSTBlocScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "postBlocScolaire($('#postBlocScolaireForm')); ")
							.f().sx("Créer un bloc")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#putBlocScolaireModale').show(); ")
			.f().sx("Dupliquer des blocs")
		.g("button");
		{ e("div").a("id", "putBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putBlocScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Dupliquer des blocs").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						BlocScolaire o = new BlocScolaire();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PUT
						{ e("form").a("action", "").a("id", "putBlocScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTBlocScolaire(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "putBlocScolaire($('#putBlocScolaireFormulaireValeurs'), ", Optional.ofNullable(blocScolaire).map(BlocScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
							.f().sx("Dupliquer des blocs")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#patchBlocScolaireModale').show(); ")
			.f().sx("Modifier des blocs")
		.g("button");
		{ e("div").a("id", "patchBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchBlocScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modifier des blocs").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						BlocScolaire o = new BlocScolaire();
						o.setRequeteSite_(requeteSite_);

						// FormulaireValeurs PATCH
						{ e("form").a("action", "").a("id", "patchBlocScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHBlocScolaire(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
							.a("onclick", "patchBlocScolaire($('#patchBlocScolaireFormulaireFiltres'), $('#patchBlocScolaireFormulaireValeurs'), ", Optional.ofNullable(blocScolaire).map(BlocScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
							.f().sx("Modifier des blocs")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listeBlocScolaire != null && listeBlocScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#deleteBlocScolaireModale').show(); ")
				.f().sx("Supprimer des blocs")
			.g("button");
			{ e("div").a("id", "deleteBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteBlocScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Supprimer des blocs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							BlocScolaire o = new BlocScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form DELETE
							{ e("div").a("id", "deleteBlocScolaireForm").f();
								htmlFormPATCHBlocScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "deleteBlocScolaire(", o.getPk(), "); ")
								.f().sx("Supprimer des blocs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestBlockGenPage
	 * r: "/bloc"
	 * r.enUS: "/block"
	 * r: "voir tous les blocs"
	 * r.enUS: "see all the blocks"
	 * r: "rechargerBlocGenPage"
	 * r.enUS: "refreshBlockGenPage"
	 * r: "recharger tous les blocs"
	 * r.enUS: "refresh all the blocks"
	 * r: "rechercher blocs : "
	 * r.enUS: "search blocks: "
	 * r: "suggereFormBlocScolaire"
	 * r.enUS: "suggestFormSchoolBlock"
	 * r: "rechercher blocs"
	 * r.enUS: "search blocks"
	 * r: "suggereBlocScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolBlock w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereBlocScolaire"
	 * r.enUS: "suggestSchoolBlock"
	 * r: patchBlocScolaireVals
	 * r.enUS: patchSchoolBlockVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerBlocGenPage
	 * r.enUS: refreshBlockGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereBlocScolaireObjetSuggere
	 * r.enUS: suggestSchoolBlockObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListBlocScolaire'
	 * r.enUS: '#suggestListSchoolBlock'
	 * r: "suggereListBlocScolaire"
	 * r.enUS: "suggestListSchoolBlock"
	**/
	public static void htmlSuggereBlocGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("id", "rechargerTousBlocGenPage", id).a("href", "/bloc").a("class", "").a("onclick", "patchBlocScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousBlocGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousBlocGenPage", id, "')); }); return false; ").f();
				p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
				p.sx("recharger tous les blocs");
			} p.g("a");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher blocs : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher blocs")
				.a("class", "suggereBlocScolaire w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereBlocScolaire")
				.a("id", "suggereBlocScolaire", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereBlocScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListBlocScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListBlocScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/bloc").a("class", "").f();
				p.e("i").a("class", "far fa-bell ").f().g("i");
				p.sx("voir tous les blocs");
			} p.g("a");
		} p.g("div");
	}

}
