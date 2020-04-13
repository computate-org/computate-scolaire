package org.computate.scolaire.frFR.enfant;

import org.computate.scolaire.frFR.cluster.ClusterPage;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.child.ChildGenPage
 **/
public class EnfantGenPage extends EnfantGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

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
		tl(1, "document.onkeydown = function(evt) {");
		tl(2, "evt = evt || window.event;");
		tl(2, "var isEscape = false;");
		tl(2, "if ('key' in evt) {");
		tl(3, "isEscape = (evt.key === 'Escape' || evt.key === 'Esc');");
		tl(2, "} else {");
		tl(3, "isEscape = (evt.keyCode === 27);");
		tl(2, "}");
		tl(2, "if (isEscape) {");
		tl(3, "$('.w3-modal:visible').hide();");
		tl(2, "}");
		tl(1, "};");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereEnfantScolaireInscriptionCles([{'name':'fq','value':'enfantCle:' + pk}], $('#listEnfantScolaireInscriptionCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereEnfantScolaireInscriptionCles([{'name':'fq','value':'enfantCle:' + pk}], $('#listEnfantScolaireInscriptionCles_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketEnfantScolaire(websocketEnfantScolaireInner);");
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
			o.htmInscriptionCles("POST");
		} g("div");
	}

	public void htmlFormPUTImportEnfantScolaire(EnfantScolaire o) {
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
		) {
			{ e("div").a("class", "w3-cell-row ").f();
				e("textarea")
					.a("class", "PUTImport_liste")
					.a("placeholder", "{ \"liste\": [ { \"pk\": ... , \"sauvegardes\": [ ... ] }, ... ] }")
					;
					f();
				g("textarea");
			} g("div");
		}
	}

	public void htmlFormPUTFusionEnfantScolaire(EnfantScolaire o) {
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
		) {
			{ e("div").a("class", "w3-cell-row ").f();
				e("textarea")
					.a("class", "PUTFusion_liste")
					.a("placeholder", "{ \"liste\": [ { \"pk\": ... , \"sauvegardes\": [ ... ] }, ... ] }")
					;
					f();
				g("textarea");
			} g("div");
		}
	}

	public void htmlFormPUTCopieEnfantScolaire(EnfantScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUTCopie");
			o.htmModifie("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUTCopie");
			o.htmSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonnePrenom("PUTCopie");
			o.htmFamilleNom("PUTCopie");
			o.htmPersonnePrenomPrefere("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonneDateNaissance("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PUTCopie");
		} g("div");
	}

	public void htmlFormPATCHEnfantScolaire(EnfantScolaire o) {
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
			o.htmPersonneDateNaissance("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PATCH");
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
			o.htmInscriptionCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyEnfantGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeEnfantScolaire == null || listeEnfantScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("enfants").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-orange ").f();
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
					{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
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
				{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeEnfantScolaire.getQueryResponse().getResults().getNumFound();
					String q = "*:*";
					String query1 = "objetTexte";
					String query2 = "";
					String query = "*:*";
					for(String paramNom : queryParams.fieldNames()) {
						String entiteVar = null;
						String valeurIndexe = null;
						Object paramValeursObjet = queryParams.getValue(paramNom);
						JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);

						try {
							for(Object paramObjet : paramObjets) {
								switch(paramNom) {
									case "q":
										q = (String)paramObjet;
										entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
										valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
										query1 = entiteVar.equals("*") ? query1 : entiteVar;
										query2 = valeurIndexe;
										query = query1 + ":" + query2;
								}
							}
						} catch(Exception e) {
							ExceptionUtils.rethrow(e);
						}
					}

					Integer rows1 = Optional.ofNullable(listeEnfantScolaire).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeEnfantScolaire).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeEnfantScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeEnfantScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/enfant?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1EnfantGenPage();
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
		g("div");
	}

	public void table1EnfantGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2EnfantGenPage();
		} g("table");
	}

	public void table2EnfantGenPage() {
		thead1EnfantGenPage();
		tbody1EnfantGenPage();
		tfoot1EnfantGenPage();
	}

	public void thead1EnfantGenPage() {
		{ e("thead").a("class", "w3-orange w3-hover-orange ").f();
			thead2EnfantGenPage();
		} g("thead");
	}

	public void thead2EnfantGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1EnfantGenPage() {
		{ e("tbody").f();
			tbody2EnfantGenPage();
		} g("tbody");
	}

	public void tbody2EnfantGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeEnfantScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeEnfantScolaire.size(); i++) {
			EnfantScolaire o = listeEnfantScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/enfant/" + o.getPk();
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
							e("i").a("class", "far fa-child ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1EnfantGenPage() {
		{ e("tfoot").a("class", "w3-orange w3-hover-orange ").f();
			tfoot2EnfantGenPage();
		} g("tfoot");
	}

	public void tfoot2EnfantGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeEnfantScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsEnfantGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
						.a("id", "rechargerCeEnfantGenPage")
						.a("onclick", "patchEnfantScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeEnfantGenPage')); }, function() { ajouterErreur($('#rechargerCeEnfantGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger cet enfant");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#postEnfantScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un enfant");
			} g("button");
			{ e("div").a("id", "postEnfantScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
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
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "postEnfantScolaire($('#postEnfantScolaireForm')); ")
								.f().sx("Créer un enfant")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#putimportEnfantScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Importer enfants");
			} g("button");
			{ e("div").a("id", "putimportEnfantScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportEnfantScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Importer enfants").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							EnfantScolaire o = new EnfantScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putimportEnfantScolaireForm").f();
								htmlFormPUTImportEnfantScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "putimportEnfantScolaire($('#putimportEnfantScolaireForm')); ")
								.f().sx("Importer enfants")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#putfusionEnfantScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Fusionner enfants");
			} g("button");
			{ e("div").a("id", "putfusionEnfantScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putfusionEnfantScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Fusionner enfants").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							EnfantScolaire o = new EnfantScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putfusionEnfantScolaireForm").f();
								htmlFormPUTFusionEnfantScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "putfusionEnfantScolaire($('#putfusionEnfantScolaireForm')); ")
								.f().sx("Fusionner enfants")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#putcopieEnfantScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer enfants");
			} g("button");
			{ e("div").a("id", "putcopieEnfantScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopieEnfantScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer enfants").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							EnfantScolaire o = new EnfantScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putcopieEnfantScolaireForm").f();
								htmlFormPUTCopieEnfantScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "putcopieEnfantScolaire(", o.getPk(), ", $('#putcopieEnfantScolaireForm')); ")
								.f().sx("Dupliquer enfants")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#patchEnfantScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier enfants");
			} g("button");
			{ e("div").a("id", "patchEnfantScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEnfantScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier enfants").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							EnfantScolaire o = new EnfantScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchEnfantScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHEnfantScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "patchEnfantScolaire(null, $('#patchEnfantScolaireFormulaireValeurs'), ", Optional.ofNullable(enfantScolaire).map(EnfantScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier enfants")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggereEnfantGenPage(this, null, listeEnfantScolaire);
	}

	/**
	 * Var.enUS: htmlSuggestedChildGenPage
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
	 * r: texteEnfantScolaireObjetTexte
	 * r.enUS: textSchoolChildObjectText
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: 'objetTexte:'
	 * r.enUS: 'objectText:'
	 * r: '#suggereListEnfantScolaire'
	 * r.enUS: '#suggestListSchoolChild'
	 * r: "suggereListEnfantScolaire"
	 * r.enUS: "suggestListSchoolChild"
	**/
	public static void htmlSuggereEnfantGenPage(MiseEnPage p, String id, ListeRecherche<EnfantScolaire> listeEnfantScolaire) {
		RequeteSiteFrFR requeteSite_ = p.getRequeteSite_();
		try {
			OperationRequest operationRequete = requeteSite_.getOperationRequete();
			JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
			String q = "*:*";
			String query1 = "objetTexte";
			String query2 = "";
			for(String paramNom : queryParams.fieldNames()) {
				String entiteVar = null;
				String valeurIndexe = null;
				Object paramValeursObjet = queryParams.getValue(paramNom);
				JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);

				try {
					for(Object paramObjet : paramObjets) {
						switch(paramNom) {
							case "q":
								q = (String)paramObjet;
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								query1 = entiteVar.equals("*") ? query1 : entiteVar;
								query2 = valeurIndexe.equals("*") ? "" : valeurIndexe;
						}
					}
				} catch(Exception e) {
					ExceptionUtils.rethrow(e);
				}
			}

			Integer rows1 = Optional.ofNullable(listeEnfantScolaire).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeEnfantScolaire).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeEnfantScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeEnfantScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), EnfantGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), EnfantGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerTousEnfantGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ").a("onclick", "patchEnfantScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousEnfantGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousEnfantGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger tous les enfants");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher enfants : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher enfants")
					.a("class", "suggereEnfantScolaire w3-input w3-border w3-bar-item ")
					.a("name", "suggereEnfantScolaire")
					.a("id", "suggereEnfantScolaire", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereEnfantScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListEnfantScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/enfant?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeEnfantScolaire != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
					.a("onclick", "window.location.href = '/enfant?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListEnfantScolaire", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/enfant").a("class", "").f();
					p.e("i").a("class", "far fa-child ").f().g("i");
					p.sx("voir tous les enfants");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
