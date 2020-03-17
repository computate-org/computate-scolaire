package org.computate.scolaire.frFR.age;

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
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.age.AgeGenPage
 **/
public class AgeGenPage extends AgeGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeAgeScolaire(Couverture<ListeRecherche<AgeScolaire>> c) {
	}

	protected void _ageScolaire(Couverture<AgeScolaire> c) {
		if(listeAgeScolaire != null && listeAgeScolaire.size() == 1)
			c.o(listeAgeScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("âges");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(ageScolaire != null && ageScolaire.getAgeNomComplet() != null)
			c.o(ageScolaire.getAgeNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(ageScolaire != null && ageScolaire.getAgeNomComplet() != null)
			c.o(ageScolaire.getAgeNomComplet());
		else if(ageScolaire != null)
			c.o("");
		else if(listeAgeScolaire == null || listeAgeScolaire.size() == 0)
			c.o("aucun âge trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/age");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/age-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("duotone");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("birthday-cake");
	}

	@Override public void initLoinAgeGenPage() {
		initAgeGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsAgeGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/AgePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/BlocPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/SessionPage.js").f().g("script");
	}

	@Override public void htmlScriptAgeGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggereAgeScolaireSessionCle([{'name':'fq','value':'ageCles:' + pk}], $('#listAgeScolaireSessionCle_Page'), pk); ");
		tl(2, "suggereAgeScolaireBlocCles([{'name':'fq','value':'ageCle:' + pk}], $('#listAgeScolaireBlocCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketAgeScolaire(websocketAgeScolaireInner);");
		l("});");
	}

	public void htmlFormPageAgeScolaire(AgeScolaire o) {
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
			o.htmAgeDebut("Page");
			o.htmAgeFin("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionCle("Page");
			o.htmBlocCles("Page");
		} g("div");
	}

	public void htmlFormPOSTAgeScolaire(AgeScolaire o) {
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
			o.htmAgeDebut("POST");
			o.htmAgeFin("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionCle("POST");
			o.htmBlocCles("POST");
		} g("div");
	}

	public void htmlFormPUTAgeScolaire(AgeScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeDebut("PUT");
			o.htmAgeFin("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionCle("PUT");
			o.htmBlocCles("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleAddresse("PUT");
		} g("div");
	}

	public void htmlFormPATCHAgeScolaire(AgeScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeDebut("PATCH");
			o.htmAgeFin("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionCle("PATCH");
			o.htmBlocCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleAddresse("PATCH");
		} g("div");
	}

	public void htmlFormRechercheAgeScolaire(AgeScolaire o) {
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
			o.htmAgeDebut("Recherche");
			o.htmAgeFin("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionCle("Recherche");
			o.htmBlocCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
			o.htmEcoleAddresse("Recherche");
		} g("div");
	}

	@Override public void htmlBodyAgeGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeAgeScolaire == null || listeAgeScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("âges").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun âge trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeAgeScolaire != null && listeAgeScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			AgeScolaire o = listeAgeScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeAgeScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeAgeScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeAgeScolaire.getRows();
					Integer start1 = listeAgeScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/age?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1AgeGenPage();
		}

		if(listeAgeScolaire != null && listeAgeScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			AgeScolaire o = listeAgeScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "AgeScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageAgeScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsAgeGenPage();
		htmlSuggereAgeGenPage(this, null);
		g("div");
	}

	public void table1AgeGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2AgeGenPage();
		} g("table");
	}

	public void table2AgeGenPage() {
		thead1AgeGenPage();
		tbody1AgeGenPage();
		tfoot1AgeGenPage();
	}

	public void thead1AgeGenPage() {
		{ e("thead").a("class", "w3-blue w3-hover-blue ").f();
			thead2AgeGenPage();
		} g("thead");
	}

	public void thead2AgeGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1AgeGenPage() {
		{ e("tbody").f();
			tbody2AgeGenPage();
		} g("tbody");
	}

	public void tbody2AgeGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeAgeScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeAgeScolaire.size(); i++) {
			AgeScolaire o = listeAgeScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/age/" + o.getPk();
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
							e("i").a("class", "fad fa-birthday-cake ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1AgeGenPage() {
		{ e("tfoot").a("class", "w3-blue w3-hover-blue ").f();
			tfoot2AgeGenPage();
		} g("tfoot");
	}

	public void tfoot2AgeGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeAgeScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsAgeGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeAgeScolaire != null && listeAgeScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
						.a("id", "rechargerCeAgeGenPage")
						.a("onclick", "patchAgeScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeAgeGenPage')); }, function() { ajouterErreur($('#rechargerCeAgeGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger cet âge");
				} g("button");
			}

			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
				.a("onclick", "$('#postAgeScolaireModale').show(); ")
				.f().sx("Créer un âge")
			.g("button");
			{ e("div").a("id", "postAgeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postAgeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un âge").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							AgeScolaire o = new AgeScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postAgeScolaireForm").f();
								htmlFormPOSTAgeScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue ")
								.a("onclick", "postAgeScolaire($('#postAgeScolaireForm')); ")
								.f().sx("Créer un âge")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
				.a("onclick", "$('#putAgeScolaireModale').show(); ")
				.f().sx("Dupliquer des âges")
			.g("button");
			{ e("div").a("id", "putAgeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putAgeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer des âges").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							AgeScolaire o = new AgeScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PUT
							{ e("form").a("action", "").a("id", "putAgeScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTAgeScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue ")
								.a("onclick", "putAgeScolaire($('#putAgeScolaireFormulaireValeurs'), ", Optional.ofNullable(ageScolaire).map(AgeScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Dupliquer des âges")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
				.a("onclick", "$('#patchAgeScolaireModale').show(); ")
				.f().sx("Modifier des âges")
			.g("button");
			{ e("div").a("id", "patchAgeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchAgeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier des âges").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							AgeScolaire o = new AgeScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchAgeScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHAgeScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue ")
								.a("onclick", "patchAgeScolaire($('#patchAgeScolaireFormulaireFiltres'), $('#patchAgeScolaireFormulaireValeurs'), ", Optional.ofNullable(ageScolaire).map(AgeScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier des âges")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
	}

	/**
	 * Var.enUS: htmlSuggestAgeGenPage
	 * r: "/age"
	 * r.enUS: "/age"
	 * r: "voir tous les âges"
	 * r.enUS: "see all the ages"
	 * r: "rechargerAgeGenPage"
	 * r.enUS: "refreshAgeGenPage"
	 * r: "recharger tous les âges"
	 * r.enUS: "refresh all the ages"
	 * r: "rechercher âges : "
	 * r.enUS: "search ages: "
	 * r: "suggereFormAgeScolaire"
	 * r.enUS: "suggestFormSchoolAge"
	 * r: "rechercher âges"
	 * r.enUS: "search ages"
	 * r: "suggereAgeScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolAge w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereAgeScolaire"
	 * r.enUS: "suggestSchoolAge"
	 * r: patchAgeScolaireVals
	 * r.enUS: patchSchoolAgeVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerAgeGenPage
	 * r.enUS: refreshAgeGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereAgeScolaireObjetSuggere
	 * r.enUS: suggestSchoolAgeObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListAgeScolaire'
	 * r.enUS: '#suggestListSchoolAge'
	 * r: "suggereListAgeScolaire"
	 * r.enUS: "suggestListSchoolAge"
	**/
	public static void htmlSuggereAgeGenPage(MiseEnPage p, String id) {
		RequeteSiteFrFR requeteSite_ = p.getRequeteSite_();
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), AgeGenPage.ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), AgeGenPage.ROLES)
				) {
			{ p.e("div").a("class", "").f();
				{ p.e("button").a("id", "rechargerTousAgeGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ").a("onclick", "patchAgeScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousAgeGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousAgeGenPage", id, "')); }); ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("recharger tous les âges");
				} p.g("button");
			} p.g("div");
		}
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher âges : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher âges")
				.a("class", "suggereAgeScolaire w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereAgeScolaire")
				.a("id", "suggereAgeScolaire", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereAgeScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListAgeScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListAgeScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/age").a("class", "").f();
				p.e("i").a("class", "fad fa-birthday-cake ").f().g("i");
				p.sx("voir tous les âges");
			} p.g("a");
		} p.g("div");
	}

}
