package org.computate.scolaire.frFR.html.part;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.html.part.HtmlPartGenPage
 **/
public class PartHtmlGenPage extends PartHtmlGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listePartHtml(Couverture<ListeRecherche<PartHtml>> c) {
	}

	protected void _partHtml(Couverture<PartHtml> c) {
		if(listePartHtml != null && listePartHtml.size() == 1)
			c.o(listePartHtml.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("part de HTMLs");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(partHtml != null && partHtml.getObjetTitre() != null)
			c.o(partHtml.getObjetTitre());
		else if(partHtml != null)
			c.o("");
		else if(listePartHtml == null || listePartHtml.size() == 0)
			c.o("aucun part de HTML trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/part-html");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/part-html-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("sun");
	}

	@Override public void initLoinPartHtmlGenPage() {
		initPartHtmlGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsPartHtmlGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/PartHtmlPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/DesignInscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptPartHtmlGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggerePartHtmlDesignInscriptionCle([{'name':'fq','value':'partHtmlCles:' + pk}], $('#listPartHtmlDesignInscriptionCle_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketPartHtml(websocketPartHtmlInner);");
		l("});");
	}

	public void htmlFormPagePartHtml(PartHtml o) {
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
			o.htmDesignInscriptionCle("Page");
			o.htmHtmlLien("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("Page");
			o.htmHtmlId("Page");
			o.htmHtmlClasses("Page");
			o.htmHtmlStyle("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("Page");
			o.htmHtmlVarSpan("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("Page");
			o.htmHtmlVarInput("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("Page");
			o.htmHtmlExclure("Page");
			o.htmPdfExclure("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("Page");
			o.htmTri2("Page");
			o.htmTri3("Page");
			o.htmTri4("Page");
			o.htmTri5("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("Page");
			o.htmTri7("Page");
			o.htmTri8("Page");
			o.htmTri9("Page");
			o.htmTri10("Page");
		} g("div");
	}

	public void htmlFormPOSTPartHtml(PartHtml o) {
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
			o.htmDesignInscriptionCle("POST");
			o.htmHtmlLien("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("POST");
			o.htmHtmlId("POST");
			o.htmHtmlClasses("POST");
			o.htmHtmlStyle("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("POST");
			o.htmHtmlVarSpan("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("POST");
			o.htmHtmlVarInput("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("POST");
			o.htmHtmlExclure("POST");
			o.htmPdfExclure("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("POST");
			o.htmTri2("POST");
			o.htmTri3("POST");
			o.htmTri4("POST");
			o.htmTri5("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("POST");
			o.htmTri7("POST");
			o.htmTri8("POST");
			o.htmTri9("POST");
			o.htmTri10("POST");
		} g("div");
	}

	public void htmlFormPATCHPartHtml(PartHtml o) {
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
			o.htmDesignInscriptionCle("PATCH");
			o.htmHtmlLien("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("PATCH");
			o.htmHtmlId("PATCH");
			o.htmHtmlClasses("PATCH");
			o.htmHtmlStyle("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("PATCH");
			o.htmHtmlVarSpan("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("PATCH");
			o.htmHtmlVarInput("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("PATCH");
			o.htmHtmlExclure("PATCH");
			o.htmPdfExclure("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("PATCH");
			o.htmTri2("PATCH");
			o.htmTri3("PATCH");
			o.htmTri4("PATCH");
			o.htmTri5("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("PATCH");
			o.htmTri7("PATCH");
			o.htmTri8("PATCH");
			o.htmTri9("PATCH");
			o.htmTri10("PATCH");
		} g("div");
	}

	public void htmlFormRecherchePartHtml(PartHtml o) {
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
			o.htmDesignInscriptionCle("Recherche");
			o.htmHtmlLien("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("Recherche");
			o.htmHtmlId("Recherche");
			o.htmHtmlClasses("Recherche");
			o.htmHtmlStyle("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("Recherche");
			o.htmHtmlVarSpan("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("Recherche");
			o.htmHtmlVarInput("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("Recherche");
			o.htmHtmlExclure("Recherche");
			o.htmPdfExclure("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("Recherche");
			o.htmTri2("Recherche");
			o.htmTri3("Recherche");
			o.htmTri4("Recherche");
			o.htmTri5("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("Recherche");
			o.htmTri7("Recherche");
			o.htmTri8("Recherche");
			o.htmTri9("Recherche");
			o.htmTri10("Recherche");
		} g("div");
	}

	@Override public void htmlBodyPartHtmlGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listePartHtml == null || listePartHtml.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/part-html").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("part de HTMLs").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun part de HTML trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listePartHtml != null && listePartHtml.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PartHtml o = listePartHtml.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/part-html").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
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
					{ e("a").a("href", "/part-html").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listePartHtml.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listePartHtml.getQuery(), "_suggested", "");
					Integer rows1 = listePartHtml.getRows();
					Integer start1 = listePartHtml.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/part-html?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/part-html?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/part-html?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/part-html?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-yellow w3-hover-yellow ").f();
					{ e("tr").f();
						e("th").f().sx("crée").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listePartHtml.getQueryResponse().getHighlighting();
					for(int i = 0; i < listePartHtml.size(); i++) {
						PartHtml o = listePartHtml.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/part-html/" + o.getPk();
						{ e("tr").f();
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

		if(listePartHtml != null && listePartHtml.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PartHtml o = listePartHtml.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "PartHtmlForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPagePartHtml(o);
				}

			} g("div");

		}
		htmlBodyFormsPartHtmlGenPage();
		htmlSuggerePartHtmlGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsPartHtmlGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("id", "rechargerCePartHtmlGenPage")
				.a("onclick", "patchPartHtmlVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCePartHtmlGenPage')); }, function() { ajouterErreur($('#rechargerCePartHtmlGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger ce part de HTML");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postPartHtmlModale').show(); ")
			.f().sx("Créer un part de HTML")
		.g("button");
		{ e("div").a("id", "postPartHtmlModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postPartHtmlModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer un part de HTML").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					PartHtml o = new PartHtml();
					o.setRequeteSite_(requeteSite_);

					// Form POST
					{ e("div").a("id", "postPartHtmlForm").f();
						htmlFormPOSTPartHtml(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postPartHtml($('#postPartHtmlForm')); ")
						.f().sx("Créer un part de HTML")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listePartHtml != null && listePartHtml.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#putPartHtmlModale').show(); ")
				.f().sx("Remplacer le part de HTML")
			.g("button");
			{ e("div").a("id", "putPartHtmlModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putPartHtmlModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Remplacer le part de HTML").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						PartHtml o = new PartHtml();
						o.setRequeteSite_(requeteSite_);

						// Form PUT
						{ e("div").a("id", "putPartHtmlForm").f();
							htmlFormPOSTPartHtml(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
							.a("onclick", "putPartHtml(", o.getPk(), ", $('#putPartHtmlForm')); ")
							.f().sx("Remplacer le part de HTML")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchPartHtmlModale').show(); ")
			.f().sx("Modifier des part de HTMLs")
		.g("button");
		{ e("div").a("id", "patchPartHtmlModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchPartHtmlModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des part de HTMLs").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					PartHtml o = new PartHtml();
					o.setRequeteSite_(requeteSite_);

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchPartHtmlFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRecherchePartHtml(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "recherchePartHtml($('#patchPartHtmlFormulaireFiltres')); ")
						.f().sx("Rechercher des un part de HTML")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchPartHtmlFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHPartHtml(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchPartHtml($('#patchPartHtmlFormulaireFiltres'), $('#patchPartHtmlFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des part de HTMLs")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listePartHtml != null && listePartHtml.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#deletePartHtmlModale').show(); ")
				.f().sx("Supprimer des part de HTMLs")
			.g("button");
			{ e("div").a("id", "deletePartHtmlModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deletePartHtmlModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des part de HTMLs").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						PartHtml o = new PartHtml();
						o.setRequeteSite_(requeteSite_);

						// Form DELETE
						{ e("div").a("id", "deletePartHtmlForm").f();
							htmlFormPATCHPartHtml(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
							.a("onclick", "deletePartHtml(", o.getPk(), "); ")
							.f().sx("Supprimer des part de HTMLs")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestHtmlPartGenPage
	 * r: "/part-html"
	 * r.enUS: "/html-part"
	 * r: "voir tous les part de HTMLs"
	 * r.enUS: "see all the HTML parts"
	 * r: "rechargerPartHtmlGenPage"
	 * r.enUS: "refreshHtmlPartGenPage"
	 * r: "recharger tous les part de HTMLs"
	 * r.enUS: "refresh all the HTML parts"
	 * r: "rechercher part de HTMLs : "
	 * r.enUS: "search HTML parts: "
	 * r: "suggereFormPartHtml"
	 * r.enUS: "suggestFormHtmlPart"
	 * r: "rechercher part de HTMLs"
	 * r.enUS: "search HTML parts"
	 * r: "suggerePartHtml w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestHtmlPart w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggerePartHtml"
	 * r.enUS: "suggestHtmlPart"
	 * r: patchPartHtmlVals
	 * r.enUS: patchHtmlPartVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerPartHtmlGenPage
	 * r.enUS: refreshHtmlPartGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggerePartHtmlObjetSuggere
	 * r.enUS: suggestHtmlPartObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListPartHtml'
	 * r.enUS: '#suggestListHtmlPart'
	 * r: "suggereListPartHtml"
	 * r.enUS: "suggestListHtmlPart"
	**/
	public static void htmlSuggerePartHtmlGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/part-html").a("class", "").f();
					p.e("i").a("class", "far fa-sun w3-padding-small ").f().g("i");
					p.sx("voir tous les part de HTMLs");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerTousPartHtmlGenPage", id).a("href", "/part-html").a("class", "").a("onclick", "patchPartHtmlVals([], {}, function() { ajouterLueur($('#rechargerTousPartHtmlGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousPartHtmlGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger tous les part de HTMLs");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher part de HTMLs : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormPartHtml", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/part-html?q=objetSuggere:' + encodeURIComponent($('#suggerePartHtml", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher part de HTMLs")
							.a("class", "suggerePartHtml w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggerePartHtml")
							.a("id", "suggerePartHtml", id)
							.a("autocomplete", "off")
							.a("oninput", "suggerePartHtmlObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListPartHtml", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListPartHtml", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
