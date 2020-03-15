package org.computate.scolaire.frFR.pere;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.dad.DadGenPage
 **/
public class PereGenPage extends PereGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listePereScolaire(Couverture<ListeRecherche<PereScolaire>> c) {
	}

	protected void _pereScolaire(Couverture<PereScolaire> c) {
		if(listePereScolaire != null && listePereScolaire.size() == 1)
			c.o(listePereScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("pères");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(pereScolaire != null && pereScolaire.getPereNomComplet() != null)
			c.o(pereScolaire.getPereNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(pereScolaire != null && pereScolaire.getPereNomComplet() != null)
			c.o(pereScolaire.getPereNomComplet());
		else if(pereScolaire != null)
			c.o("");
		else if(listePereScolaire == null || listePereScolaire.size() == 0)
			c.o("aucun père trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/pere");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/pere-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("male");
	}

	@Override public void initLoinPereGenPage() {
		initPereGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsPereGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/PerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptPereGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggerePereScolaireInscriptionCles([{'name':'fq','value':'pereCles:' + pk}], $('#listPereScolaireInscriptionCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketPereScolaire(websocketPereScolaireInner);");
		l("});");
	}

	public void htmlFormPagePereScolaire(PereScolaire o) {
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
			o.htmPersonneMail("Page");
			o.htmPersonneNumeroTelephone("Page");
			o.htmPersonneOccupation("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneSms("Page");
			o.htmPersonneContactUrgence("Page");
			o.htmPersonneRecevoirMail("Page");
			o.htmPersonneChercher("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Page");
		} g("div");
	}

	public void htmlFormPOSTPereScolaire(PereScolaire o) {
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
			o.htmPersonneMail("POST");
			o.htmPersonneNumeroTelephone("POST");
			o.htmPersonneOccupation("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneSms("POST");
			o.htmPersonneContactUrgence("POST");
			o.htmPersonneRecevoirMail("POST");
			o.htmPersonneChercher("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("POST");
		} g("div");
	}

	public void htmlFormPUTPereScolaire(PereScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonnePrenom("PUT");
			o.htmFamilleNom("PUT");
			o.htmPersonnePrenomPrefere("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneMail("PUT");
			o.htmPersonneNumeroTelephone("PUT");
			o.htmPersonneOccupation("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneSms("PUT");
			o.htmPersonneContactUrgence("PUT");
			o.htmPersonneRecevoirMail("PUT");
			o.htmPersonneChercher("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PUT");
		} g("div");
	}

	public void htmlFormPATCHPereScolaire(PereScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
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
			o.htmPersonneMail("PATCH");
			o.htmPersonneNumeroTelephone("PATCH");
			o.htmPersonneOccupation("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneSms("PATCH");
			o.htmPersonneContactUrgence("PATCH");
			o.htmPersonneRecevoirMail("PATCH");
			o.htmPersonneChercher("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PATCH");
		} g("div");
	}

	public void htmlFormRecherchePereScolaire(PereScolaire o) {
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
			o.htmPersonneMail("Recherche");
			o.htmPersonneNumeroTelephone("Recherche");
			o.htmPersonneOccupation("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneSms("Recherche");
			o.htmPersonneContactUrgence("Recherche");
			o.htmPersonneRecevoirMail("Recherche");
			o.htmPersonneChercher("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyPereGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listePereScolaire == null || listePereScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/pere").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-blue w3-hover-light-blue ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("pères").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-blue ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun père trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listePereScolaire != null && listePereScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PereScolaire o = listePereScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/pere").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-blue w3-hover-light-blue ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-blue ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-blue ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/pere").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-blue w3-hover-light-blue ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listePereScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listePereScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listePereScolaire.getRows();
					Integer start1 = listePereScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/pere?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/pere?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/pere?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/pere?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1PereGenPage();
		}

		if(listePereScolaire != null && listePereScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PereScolaire o = listePereScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "PereScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPagePereScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsPereGenPage();
		htmlSuggerePereGenPage(this, null);
		g("div");
	}

	public void table1PereGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2PereGenPage();
		} g("table");
	}

	public void table2PereGenPage() {
		thead1PereGenPage();
		tbody1PereGenPage();
		tfoot1PereGenPage();
	}

	public void thead1PereGenPage() {
		{ e("thead").a("class", "w3-light-blue w3-hover-light-blue ").f();
			thead2PereGenPage();
		} g("thead");
	}

	public void thead2PereGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1PereGenPage() {
		{ e("tbody").f();
			tbody2PereGenPage();
		} g("tbody");
	}

	public void tbody2PereGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listePereScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listePereScolaire.size(); i++) {
			PereScolaire o = listePereScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/pere/" + o.getPk();
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
							e("i").a("class", "far fa-male ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1PereGenPage() {
		{ e("tfoot").a("class", "w3-light-blue w3-hover-light-blue ").f();
			tfoot2PereGenPage();
		} g("tfoot");
	}

	public void tfoot2PereGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listePereScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsPereGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listePereScolaire != null && listePereScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
						.a("id", "rechargerCePereGenPage")
						.a("onclick", "patchPereScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCePereGenPage')); }, function() { ajouterErreur($('#rechargerCePereGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce père");
				} g("button");
			}

			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#postPereScolaireModale').show(); ")
				.f().sx("Créer un père")
			.g("button");
			{ e("div").a("id", "postPereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postPereScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un père").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PereScolaire o = new PereScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postPereScolaireForm").f();
								htmlFormPOSTPereScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
								.a("onclick", "postPereScolaire($('#postPereScolaireForm')); ")
								.f().sx("Créer un père")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#putPereScolaireModale').show(); ")
				.f().sx("Dupliquer des pères")
			.g("button");
			{ e("div").a("id", "putPereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putPereScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer des pères").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PereScolaire o = new PereScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PUT
							{ e("form").a("action", "").a("id", "putPereScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTPereScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
								.a("onclick", "putPereScolaire($('#putPereScolaireFormulaireValeurs'), ", Optional.ofNullable(pereScolaire).map(PereScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Dupliquer des pères")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#patchPereScolaireModale').show(); ")
				.f().sx("Modifier des pères")
			.g("button");
			{ e("div").a("id", "patchPereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchPereScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier des pères").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PereScolaire o = new PereScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchPereScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHPereScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
								.a("onclick", "patchPereScolaire($('#patchPereScolaireFormulaireFiltres'), $('#patchPereScolaireFormulaireValeurs'), ", Optional.ofNullable(pereScolaire).map(PereScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier des pères")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			if(listePereScolaire != null && listePereScolaire.size() == 1) {
				e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
					.a("onclick", "$('#deletePereScolaireModale').show(); ")
					.f().sx("Supprimer des pères")
				.g("button");
				{ e("div").a("id", "deletePereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
					{ e("div").a("class", "w3-modal-content ").f();
						{ e("div").a("class", "w3-card-4 ").f();
							{ e("header").a("class", "w3-container w3-light-blue ").f();
								e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deletePereScolaireModale').hide(); ").f().sx("×").g("span");
								e("h2").a("class", "w3-padding ").f().sx("Supprimer des pères").g("h2");
							} g("header");
							{ e("div").a("class", "w3-container ").f();
								PereScolaire o = new PereScolaire();
								o.setRequeteSite_(requeteSite_);

								// Form DELETE
								{ e("div").a("id", "deletePereScolaireForm").f();
									htmlFormPATCHPereScolaire(o);
								} g("div");
								e("button")
									.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
									.a("onclick", "deletePereScolaire(", o.getPk(), "); ")
									.f().sx("Supprimer des pères")
								.g("button");

							} g("div");
						} g("div");
					} g("div");
				} g("div");

			}
			g("div");
		}
	}

	/**
	 * Var.enUS: htmlSuggestDadGenPage
	 * r: "/pere"
	 * r.enUS: "/dad"
	 * r: "voir tous les pères"
	 * r.enUS: "see all the dads"
	 * r: "rechargerPereGenPage"
	 * r.enUS: "refreshDadGenPage"
	 * r: "recharger tous les pères"
	 * r.enUS: "refresh all the dads"
	 * r: "rechercher pères : "
	 * r.enUS: "search dads: "
	 * r: "suggereFormPereScolaire"
	 * r.enUS: "suggestFormSchoolDad"
	 * r: "rechercher pères"
	 * r.enUS: "search dads"
	 * r: "suggerePereScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolDad w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggerePereScolaire"
	 * r.enUS: "suggestSchoolDad"
	 * r: patchPereScolaireVals
	 * r.enUS: patchSchoolDadVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerPereGenPage
	 * r.enUS: refreshDadGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggerePereScolaireObjetSuggere
	 * r.enUS: suggestSchoolDadObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListPereScolaire'
	 * r.enUS: '#suggestListSchoolDad'
	 * r: "suggereListPereScolaire"
	 * r.enUS: "suggestListSchoolDad"
	**/
	public static void htmlSuggerePereGenPage(MiseEnPage p, String id) {
		RequeteSiteFrFR requeteSite_ = p.getRequeteSite_();
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), PereGenPage.ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), PereGenPage.ROLES)
				) {
			{ p.e("div").a("class", "").f();
				{ p.e("button").a("id", "rechargerTousPereGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ").a("onclick", "patchPereScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousPereGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousPereGenPage", id, "')); }); ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("recharger tous les pères");
				} p.g("button");
			} p.g("div");
		}
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher pères : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher pères")
				.a("class", "suggerePereScolaire w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggerePereScolaire")
				.a("id", "suggerePereScolaire", id)
				.a("autocomplete", "off")
				.a("oninput", "suggerePereScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListPereScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListPereScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/pere").a("class", "").f();
				p.e("i").a("class", "far fa-male ").f().g("i");
				p.sx("voir tous les pères");
			} p.g("a");
		} p.g("div");
	}

}
