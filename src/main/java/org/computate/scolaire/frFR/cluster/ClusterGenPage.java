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
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.cluster.ClusterGenPage
 **/
public class ClusterGenPage extends ClusterGenPageGen<MiseEnPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("User");

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

	public void htmlFormPUTCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
	}

	public void htmlFormPATCHCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
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
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
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
					StringBuilder fqs = new StringBuilder();
					for(String fq : listeCluster.getFilterQueries()) {
						if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_"))
							fqs.append("&fq=").append(StringUtils.substringBefore(fq, "_indexed_")).append(":").append(StringUtils.substringAfter(fq, ":"));
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : listeCluster.getSorts()) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_indexed_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/cluster?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1ClusterGenPage();
		}

		{ e("div").a("class", "").f();
			{ e("form").a("id", "ClusterForm").a("style", "display: inline-block; width: 100%; ").a("method", "GET").a("action", "/cluster").a("onsubmit", "event.preventDefault(); rechercher($('#rechercheObjetTexte')); return false; ").f();
				{ e("div").a("class", "w3-bar ").f();
					e("input").a("type", "text")
						.a("placeholder", "rechercher clusters")
						.a("title", "")
						.a("class", "rechercheObjetTexte w3-input w3-border w3-bar-item ")
						.a("name", "objetTexte")
						.a("id", "rechercheObjetTexte");
					operationRequete.getParams().getJsonObject("query").forEach(paramRequete -> {
						String entiteVar = null;
						String valeurIndexe = null;
						String paramNom = paramRequete.getKey();
						Object paramValeursObjet = paramRequete.getValue();
						JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);

						try {
							for(Object paramObjet : paramObjets) {
								switch(paramNom) {
									case "q":
										entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
										valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
										if("objetTexte".equals(entiteVar))
											a("value", URLDecoder.decode(valeurIndexe, "UTF-8"));
								}
							}
						} catch(Exception e) {
							ExceptionUtils.rethrow(e);
						}
					});
					fg();
					{ e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
						.f();
						e("i").a("class", "fas fa-search ").f().g("i");
					} g("button");
				} g("div");
			} g("form");
		} g("div");

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

	public void table1ClusterGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2ClusterGenPage();
		} g("table");
	}

	public void table2ClusterGenPage() {
		thead1ClusterGenPage();
		tbody1ClusterGenPage();
		tfoot1ClusterGenPage();
	}

	public void thead1ClusterGenPage() {
		{ e("thead").a("class", "w3-gray w3-hover-gray ").f();
			thead2ClusterGenPage();
		} g("thead");
	}

	public void thead2ClusterGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1ClusterGenPage() {
		{ e("tbody").f();
			tbody2ClusterGenPage();
		} g("tbody");
	}

	public void tbody2ClusterGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeCluster.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeCluster.size(); i++) {
			Cluster o = listeCluster.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/cluster/" + o.getPk();
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
							e("i").a("class", "far fa-fort-awesome ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1ClusterGenPage() {
		{ e("tfoot").a("class", "w3-gray w3-hover-gray ").f();
			tfoot2ClusterGenPage();
		} g("tfoot");
	}

	public void tfoot2ClusterGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeCluster.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsClusterGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeCluster != null && listeCluster.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
						.a("id", "rechargerCeClusterGenPage")
						.a("onclick", "patchClusterVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeClusterGenPage')); }, function() { ajouterErreur($('#rechargerCeClusterGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce cluster");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#postClusterModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un cluster");
			} g("button");
			{ e("div").a("id", "postClusterModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
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
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "postCluster($('#postClusterForm')); ")
								.f().sx("Créer un cluster")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#putClusterModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer des clusters");
			} g("button");
			{ e("div").a("id", "putClusterModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putClusterModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer des clusters").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							Cluster o = new Cluster();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PUT
							{ e("form").a("action", "").a("id", "putClusterFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTCluster(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "putCluster($('#putClusterFormulaireValeurs'), ", Optional.ofNullable(cluster).map(Cluster::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Dupliquer des clusters")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#patchClusterModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier des clusters");
			} g("button");
			{ e("div").a("id", "patchClusterModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchClusterModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier des clusters").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							Cluster o = new Cluster();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchClusterFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHCluster(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "patchCluster($('#patchClusterFormulaireFiltres'), $('#patchClusterFormulaireValeurs'), ", Optional.ofNullable(cluster).map(Cluster::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier des clusters")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
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
		RequeteSiteFrFR requeteSite_ = p.getRequeteSite_();
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ClusterGenPage.ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ClusterGenPage.ROLES)
				) {
			{ p.e("div").a("class", "").f();
				{ p.e("button").a("id", "rechargerTousClusterGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ").a("onclick", "patchClusterVals([], {}, function() { ajouterLueur($('#rechargerTousClusterGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousClusterGenPage", id, "')); }); ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("recharger tous les clusters");
				} p.g("button");
			} p.g("div");
		}
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher clusters : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher clusters")
				.a("class", "suggereCluster w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereCluster")
				.a("id", "suggereCluster", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereClusterObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListCluster", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListCluster", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/cluster").a("class", "").f();
				p.e("i").a("class", "far fa-fort-awesome ").f().g("i");
				p.sx("voir tous les clusters");
			} p.g("a");
		} p.g("div");
	}

}
