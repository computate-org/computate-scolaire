package org.computate.scolaire.frFR.mere;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.mom.MomGenPage
 **/
public class MereGenPage extends MereGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeMereScolaire(Couverture<ListeRecherche<MereScolaire>> c) {
	}

	protected void _mereScolaire(Couverture<MereScolaire> c) {
		if(listeMereScolaire != null && listeMereScolaire.size() == 1)
			c.o(listeMereScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("mères");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(mereScolaire != null && mereScolaire.getMereNomComplet() != null)
			c.o(mereScolaire.getMereNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(mereScolaire != null && mereScolaire.getMereNomComplet() != null)
			c.o(mereScolaire.getMereNomComplet());
		else if(mereScolaire != null)
			c.o("");
		else if(listeMereScolaire == null || listeMereScolaire.size() == 0)
			c.o("aucune mère trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/mere");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/mere-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("female");
	}

	@Override public void initLoinMereGenPage() {
		initMereGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsMereGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/MerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptMereGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggereMereScolaireInscriptionCles([{'name':'fq','value':'mereCles:' + pk}], $('#listMereScolaireInscriptionCles_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketMereScolaire(websocketMereScolaireInner);");
		l("});");
	}

	public void htmlFormPageMereScolaire(MereScolaire o) {
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
			o.htmPersonneRecevoirMail("Page");
			o.htmPersonneContactUrgence("Page");
			o.htmPersonneChercher("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Page");
		} g("div");
	}

	public void htmlFormPOSTMereScolaire(MereScolaire o) {
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
			o.htmPersonneRecevoirMail("POST");
			o.htmPersonneContactUrgence("POST");
			o.htmPersonneChercher("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("POST");
		} g("div");
	}

	public void htmlFormPUTMereScolaire(MereScolaire o) {
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
			o.htmPersonneRecevoirMail("PUT");
			o.htmPersonneContactUrgence("PUT");
			o.htmPersonneChercher("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PUT");
		} g("div");
	}

	public void htmlFormPATCHMereScolaire(MereScolaire o) {
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
			o.htmPersonneRecevoirMail("PATCH");
			o.htmPersonneContactUrgence("PATCH");
			o.htmPersonneChercher("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PATCH");
		} g("div");
	}

	public void htmlFormRechercheMereScolaire(MereScolaire o) {
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
			o.htmPersonneRecevoirMail("Recherche");
			o.htmPersonneContactUrgence("Recherche");
			o.htmPersonneChercher("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyMereGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeMereScolaire == null || listeMereScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/mere").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("mères").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucune mère trouvée").g("span");
				} g("span");
			} g("h2");
		} else if(listeMereScolaire != null && listeMereScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			MereScolaire o = listeMereScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/mere").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/mere").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeMereScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeMereScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeMereScolaire.getRows();
					Integer start1 = listeMereScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mere?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mere?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/mere?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mere?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1MereGenPage();
		}

		if(listeMereScolaire != null && listeMereScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			MereScolaire o = listeMereScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "MereScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageMereScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsMereGenPage();
		htmlSuggereMereGenPage(this, null);
		g("div");
	}

	public void table1MereGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2MereGenPage();
		} g("table");
	}

	public void table2MereGenPage() {
		thead1MereGenPage();
		tbody1MereGenPage();
		tfoot1MereGenPage();
	}

	public void thead1MereGenPage() {
		{ e("thead").a("class", "w3-pink w3-hover-pink ").f();
			thead2MereGenPage();
		} g("thead");
	}

	public void thead2MereGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1MereGenPage() {
		{ e("tbody").f();
			tbody2MereGenPage();
		} g("tbody");
	}

	public void tbody2MereGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeMereScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeMereScolaire.size(); i++) {
			MereScolaire o = listeMereScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/mere/" + o.getPk();
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
							e("i").a("class", "far fa-female ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1MereGenPage() {
		{ e("tfoot").a("class", "w3-pink w3-hover-pink ").f();
			tfoot2MereGenPage();
		} g("tfoot");
	}

	public void tfoot2MereGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeMereScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsMereGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeMereScolaire != null && listeMereScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("id", "rechargerCetteMereGenPage")
						.a("onclick", "patchMereScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCetteMereGenPage')); }, function() { ajouterErreur($('#rechargerCetteMereGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger cette mère");
				} g("button");
			}

			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("onclick", "$('#postMereScolaireModale').show(); ")
				.f().sx("Créer une mère")
			.g("button");
			{ e("div").a("id", "postMereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-pink ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postMereScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer une mère").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							MereScolaire o = new MereScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postMereScolaireForm").f();
								htmlFormPOSTMereScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
								.a("onclick", "postMereScolaire($('#postMereScolaireForm')); ")
								.f().sx("Créer une mère")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("onclick", "$('#putMereScolaireModale').show(); ")
				.f().sx("Dupliquer des mères")
			.g("button");
			{ e("div").a("id", "putMereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-pink ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putMereScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer des mères").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							MereScolaire o = new MereScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PUT
							{ e("form").a("action", "").a("id", "putMereScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTMereScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
								.a("onclick", "putMereScolaire($('#putMereScolaireFormulaireValeurs'), ", Optional.ofNullable(mereScolaire).map(MereScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Dupliquer des mères")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("onclick", "$('#patchMereScolaireModale').show(); ")
				.f().sx("Modifier des mères")
			.g("button");
			{ e("div").a("id", "patchMereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-pink ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchMereScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier des mères").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							MereScolaire o = new MereScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchMereScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHMereScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
								.a("onclick", "patchMereScolaire($('#patchMereScolaireFormulaireFiltres'), $('#patchMereScolaireFormulaireValeurs'), ", Optional.ofNullable(mereScolaire).map(MereScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier des mères")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			if(listeMereScolaire != null && listeMereScolaire.size() == 1) {
				e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
					.a("onclick", "$('#deleteMereScolaireModale').show(); ")
					.f().sx("Supprimer des mères")
				.g("button");
				{ e("div").a("id", "deleteMereScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
					{ e("div").a("class", "w3-modal-content ").f();
						{ e("div").a("class", "w3-card-4 ").f();
							{ e("header").a("class", "w3-container w3-pink ").f();
								e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteMereScolaireModale').hide(); ").f().sx("×").g("span");
								e("h2").a("class", "w3-padding ").f().sx("Supprimer des mères").g("h2");
							} g("header");
							{ e("div").a("class", "w3-container ").f();
								MereScolaire o = new MereScolaire();
								o.setRequeteSite_(requeteSite_);

								// Form DELETE
								{ e("div").a("id", "deleteMereScolaireForm").f();
									htmlFormPATCHMereScolaire(o);
								} g("div");
								e("button")
									.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
									.a("onclick", "deleteMereScolaire(", o.getPk(), "); ")
									.f().sx("Supprimer des mères")
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
	 * Var.enUS: htmlSuggestMomGenPage
	 * r: "/mere"
	 * r.enUS: "/mom"
	 * r: "voir toutes les mères"
	 * r.enUS: "see all the moms"
	 * r: "rechargerMereGenPage"
	 * r.enUS: "refreshMomGenPage"
	 * r: "recharger toutes les mères"
	 * r.enUS: "refresh all the moms"
	 * r: "rechercher mères : "
	 * r.enUS: "search moms: "
	 * r: "suggereFormMereScolaire"
	 * r.enUS: "suggestFormSchoolMom"
	 * r: "rechercher mères"
	 * r.enUS: "search moms"
	 * r: "suggereMereScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolMom w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereMereScolaire"
	 * r.enUS: "suggestSchoolMom"
	 * r: patchMereScolaireVals
	 * r.enUS: patchSchoolMomVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerMereGenPage
	 * r.enUS: refreshMomGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereMereScolaireObjetSuggere
	 * r.enUS: suggestSchoolMomObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListMereScolaire'
	 * r.enUS: '#suggestListSchoolMom'
	 * r: "suggereListMereScolaire"
	 * r.enUS: "suggestListSchoolMom"
	**/
	public static void htmlSuggereMereGenPage(MiseEnPage p, String id) {
		RequeteSiteFrFR requeteSite_ = p.getRequeteSite_();
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), MereGenPage.ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), MereGenPage.ROLES)
				) {
			{ p.e("div").a("class", "").f();
				{ p.e("button").a("id", "rechargerToutesMereGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ").a("onclick", "patchMereScolaireVals([], {}, function() { ajouterLueur($('#rechargerToutesMereGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesMereGenPage", id, "')); }); ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("recharger toutes les mères");
				} p.g("button");
			} p.g("div");
		}
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher mères : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher mères")
				.a("class", "suggereMereScolaire w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggereMereScolaire")
				.a("id", "suggereMereScolaire", id)
				.a("autocomplete", "off")
				.a("oninput", "suggereMereScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListMereScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListMereScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/mere").a("class", "").f();
				p.e("i").a("class", "far fa-female ").f().g("i");
				p.sx("voir toutes les mères");
			} p.g("a");
		} p.g("div");
	}

}
