package org.computate.scolaire.frFR.cluster;

import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.couverture.Couverture;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.cluster.ClusterGenPage
 **/
public class ClusterGenPage extends ClusterGenPageGen<MiseEnPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeCluster(Couverture<ListeRecherche<Cluster>> c) {
	}

	protected void _cluster(Couverture<Cluster> c) {
		if(listeCluster != null && listeCluster.size() == 1)
			c.o(listeCluster.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("clusters");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(cluster != null && cluster.getObjetTitre() != null)
			c.o(cluster.getObjetTitre());
		else if(cluster != null)
			c.o("");
		else if(listeCluster == null || listeCluster.size() == 0)
			c.o("aucun cluster trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/cluster");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/cluster-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("fort-awesome");
	}

	@Override public void initLoinClusterGenPage() {
		initClusterGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsClusterGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/ClusterPage.js").f().g("script");
	}

	@Override public void htmlScriptClusterGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(1, "}");
		tl(1, "websocketCluster(websocketClusterInner);");
		l("});");
	}

	public void htmlFormPageCluster(Cluster o) {
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
	}

	public void htmlFormPOSTCluster(Cluster o) {
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
	}

	public void htmlFormPATCHCluster(Cluster o) {
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
	}

	public void htmlFormRechercheCluster(Cluster o) {
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
	}

	@Override public void htmlBodyClusterGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeCluster == null || listeCluster.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/cluster").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("clusters").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun cluster trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeCluster != null && listeCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Cluster o = listeCluster.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/cluster").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/cluster").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeCluster.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeCluster.getQuery(), "_suggested", "");
					Integer rows1 = listeCluster.getRows();
					Integer start1 = listeCluster.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/cluster?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-gray w3-hover-gray ").f();
					{ e("tr").f();
						e("th").f().sx("crée").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeCluster.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeCluster.size(); i++) {
						Cluster o = listeCluster.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/cluster/" + o.getPk();
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

		if(listeCluster != null && listeCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Cluster o = listeCluster.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "ClusterForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageCluster(o);
				}

			} g("div");

		}
		htmlBodyFormsClusterGenPage();
		htmlSuggereClusterGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsClusterGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("id", "rechargerCeClusterGenPage")
				.a("onclick", "patchClusterVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeClusterGenPage')); }, function() { ajouterErreur($('#rechargerCeClusterGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger ce cluster");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
			.a("onclick", "$('#postClusterModale').show(); ")
			.f().sx("Créer un cluster")
		.g("button");
		{ e("div").a("id", "postClusterModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-gray ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postClusterModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer un cluster").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();
					o.setRequeteSite_(requeteSite_);

					// Form POST
					{ e("div").a("id", "postClusterForm").f();
						htmlFormPOSTCluster(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
						.a("onclick", "postCluster($('#postClusterForm')); ")
						.f().sx("Créer un cluster")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
			.a("onclick", "$('#patchClusterModale').show(); ")
			.f().sx("Modifier des clusters")
		.g("button");
		{ e("div").a("id", "patchClusterModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-gray ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchClusterModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des clusters").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();
					o.setRequeteSite_(requeteSite_);

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchClusterFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
						.a("onclick", "rechercheCluster($('#patchClusterFormulaireFiltres')); ")
						.f().sx("Rechercher des un cluster")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchClusterFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
						.a("onclick", "patchCluster($('#patchClusterFormulaireFiltres'), $('#patchClusterFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des clusters")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeCluster != null && listeCluster.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#deleteClusterModale').show(); ")
				.f().sx("Supprimer des clusters")
			.g("button");
			{ e("div").a("id", "deleteClusterModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-gray ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteClusterModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des clusters").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						Cluster o = new Cluster();
						o.setRequeteSite_(requeteSite_);

						// Form DELETE
						{ e("div").a("id", "deleteClusterForm").f();
							htmlFormPATCHCluster(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
							.a("onclick", "deleteCluster(", o.getPk(), "); ")
							.f().sx("Supprimer des clusters")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestClusterGenPage
	 * r: "/cluster"
	 * r.enUS: "/cluster"
	 * r: "voir tous les clusters"
	 * r.enUS: "see all the clusters"
	 * r: "rechargerClusterGenPage"
	 * r.enUS: "refreshClusterGenPage"
	 * r: "recharger tous les clusters"
	 * r.enUS: "refresh all the clusters"
	 * r: "rechercher clusters : "
	 * r.enUS: "search clusters: "
	 * r: "suggereFormCluster"
	 * r.enUS: "suggestFormCluster"
	 * r: "rechercher clusters"
	 * r.enUS: "search clusters"
	 * r: "suggereCluster w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestCluster w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereCluster"
	 * r.enUS: "suggestCluster"
	 * r: patchClusterVals
	 * r.enUS: patchClusterVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerClusterGenPage
	 * r.enUS: refreshClusterGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereClusterObjetSuggere
	 * r.enUS: suggestClusterObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListCluster'
	 * r.enUS: '#suggestListCluster'
	 * r: "suggereListCluster"
	 * r.enUS: "suggestListCluster"
	**/
	public static void htmlSuggereClusterGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/cluster").a("class", "").f();
					p.e("i").a("class", "far fa-fort-awesome w3-padding-small ").f().g("i");
					p.sx("voir tous les clusters");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerTousClusterGenPage", id).a("href", "/cluster").a("class", "").a("onclick", "patchClusterVals([], {}, function() { ajouterLueur($('#rechargerTousClusterGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousClusterGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger tous les clusters");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher clusters : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormCluster", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/cluster?q=objetSuggere:' + encodeURIComponent($('#suggereCluster", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher clusters")
							.a("class", "suggereCluster w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereCluster")
							.a("id", "suggereCluster", id)
							.a("autocomplete", "off")
							.a("oninput", "suggereClusterObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListCluster", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListCluster", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
