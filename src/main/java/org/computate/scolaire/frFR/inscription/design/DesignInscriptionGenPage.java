package org.computate.scolaire.frFR.inscription.design;

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
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignGenPage
 **/
public class DesignInscriptionGenPage extends DesignInscriptionGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeDesignInscription(Couverture<ListeRecherche<DesignInscription>> c) {
	}

	protected void _designInscription(Couverture<DesignInscription> c) {
		if(listeDesignInscription != null && listeDesignInscription.size() == 1)
			c.o(listeDesignInscription.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("design d'inscriptions");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(designInscription != null && designInscription.getDesignInscriptionNomComplet() != null)
			c.o(designInscription.getDesignInscriptionNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(designInscription != null && designInscription.getDesignInscriptionNomComplet() != null)
			c.o(designInscription.getDesignInscriptionNomComplet());
		else if(designInscription != null)
			c.o("");
		else if(listeDesignInscription == null || listeDesignInscription.size() == 0)
			c.o("aucun design d'inscription trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/design-inscription");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/design-inscription-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("drafting-compass");
	}

	@Override public void initLoinDesignInscriptionGenPage() {
		initDesignInscriptionGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsDesignInscriptionGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/DesignInscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PartHtmlPage.js").f().g("script");
	}

	@Override public void htmlScriptDesignInscriptionGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereDesignInscriptionPartHtmlCles([{'name':'fq','value':'designInscriptionCle:' + pk}], $('#listDesignInscriptionPartHtmlCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereDesignInscriptionPartHtmlCles([{'name':'fq','value':'designInscriptionCle:' + pk}], $('#listDesignInscriptionPartHtmlCles_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketDesignInscription(websocketDesignInscriptionInner);");
		l("});");
	}

	public void htmlFormPageDesignInscription(DesignInscription o) {
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
			o.htmDesignInscriptionNomComplet("Page");
			o.htmDesignCache("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("Page");
		} g("div");
	}

	public void htmlFormPOSTDesignInscription(DesignInscription o) {
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
			o.htmDesignInscriptionNomComplet("POST");
			o.htmDesignCache("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("POST");
		} g("div");
	}

	public void htmlFormPUTDesignInscription(DesignInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignInscriptionNomComplet("PUT");
			o.htmDesignCache("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("PUT");
		} g("div");
	}

	public void htmlFormPATCHDesignInscription(DesignInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignInscriptionNomComplet("PATCH");
			o.htmDesignCache("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("PATCH");
		} g("div");
	}

	public void htmlFormRechercheDesignInscription(DesignInscription o) {
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
			o.htmDesignInscriptionNomComplet("Recherche");
			o.htmDesignCache("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyDesignInscriptionGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeDesignInscription == null || listeDesignInscription.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/design-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("design d'inscriptions").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun design d'inscription trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeDesignInscription != null && listeDesignInscription.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			DesignInscription o = listeDesignInscription.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/design-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-khaki ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-khaki ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/design-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeDesignInscription.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listeDesignInscription).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeDesignInscription).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeDesignInscription).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeDesignInscription).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/design-inscription?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/design-inscription?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/design-inscription?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/design-inscription?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1DesignInscriptionGenPage();
		}

		if(listeDesignInscription != null && listeDesignInscription.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			DesignInscription o = listeDesignInscription.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "DesignInscriptionForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageDesignInscription(o);
				}

			} g("div");

		}
		htmlBodyFormsDesignInscriptionGenPage();
		g("div");
	}

	public void table1DesignInscriptionGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2DesignInscriptionGenPage();
		} g("table");
	}

	public void table2DesignInscriptionGenPage() {
		thead1DesignInscriptionGenPage();
		tbody1DesignInscriptionGenPage();
		tfoot1DesignInscriptionGenPage();
	}

	public void thead1DesignInscriptionGenPage() {
		{ e("thead").a("class", "w3-khaki w3-hover-khaki ").f();
			thead2DesignInscriptionGenPage();
		} g("thead");
	}

	public void thead2DesignInscriptionGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1DesignInscriptionGenPage() {
		{ e("tbody").f();
			tbody2DesignInscriptionGenPage();
		} g("tbody");
	}

	public void tbody2DesignInscriptionGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeDesignInscription.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeDesignInscription.size(); i++) {
			DesignInscription o = listeDesignInscription.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/design-inscription/" + o.getPk();
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
							e("i").a("class", "far fa-drafting-compass ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1DesignInscriptionGenPage() {
		{ e("tfoot").a("class", "w3-khaki w3-hover-khaki ").f();
			tfoot2DesignInscriptionGenPage();
		} g("tfoot");
	}

	public void tfoot2DesignInscriptionGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeDesignInscription.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsDesignInscriptionGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeDesignInscription != null && listeDesignInscription.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
						.a("id", "rechargerCeDesignInscriptionGenPage")
						.a("onclick", "patchDesignInscriptionVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeDesignInscriptionGenPage')); }, function() { ajouterErreur($('#rechargerCeDesignInscriptionGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce design d'inscription");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#postDesignInscriptionModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un design d'inscription");
			} g("button");
			{ e("div").a("id", "postDesignInscriptionModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postDesignInscriptionModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un design d'inscription").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							DesignInscription o = new DesignInscription();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postDesignInscriptionForm").f();
								htmlFormPOSTDesignInscription(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "postDesignInscription($('#postDesignInscriptionForm')); ")
								.f().sx("Créer un design d'inscription")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#putDesignInscriptionModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer des design d'inscriptions");
			} g("button");
			{ e("div").a("id", "putDesignInscriptionModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putDesignInscriptionModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer des design d'inscriptions").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							DesignInscription o = new DesignInscription();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PUT
							{ e("form").a("action", "").a("id", "putDesignInscriptionFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTDesignInscription(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "putDesignInscription($('#putDesignInscriptionFormulaireValeurs'), ", Optional.ofNullable(designInscription).map(DesignInscription::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Dupliquer des design d'inscriptions")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#patchDesignInscriptionModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier des design d'inscriptions");
			} g("button");
			{ e("div").a("id", "patchDesignInscriptionModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchDesignInscriptionModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier des design d'inscriptions").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							DesignInscription o = new DesignInscription();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchDesignInscriptionFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHDesignInscription(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "patchDesignInscription($('#patchDesignInscriptionFormulaireFiltres'), $('#patchDesignInscriptionFormulaireValeurs'), ", Optional.ofNullable(designInscription).map(DesignInscription::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier des design d'inscriptions")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggereDesignInscriptionGenPage(this, null, listeDesignInscription);
	}

	/**
	 * Var.enUS: htmlSuggestEnrollmentDesignGenPage
	 * r: "/design-inscription"
	 * r.enUS: "/enrollment-design"
	 * r: "voir tous les design d'inscriptions"
	 * r.enUS: "see all the enrollment designs"
	 * r: "rechargerDesignInscriptionGenPage"
	 * r.enUS: "refreshEnrollmentDesignGenPage"
	 * r: "recharger tous les design d'inscriptions"
	 * r.enUS: "refresh all the enrollment designs"
	 * r: "rechercher design d'inscriptions : "
	 * r.enUS: "search enrollment designs: "
	 * r: "suggereFormDesignInscription"
	 * r.enUS: "suggestFormEnrollmentDesign"
	 * r: "rechercher design d'inscriptions"
	 * r.enUS: "search enrollment designs"
	 * r: "suggereDesignInscription w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestEnrollmentDesign w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereDesignInscription"
	 * r.enUS: "suggestEnrollmentDesign"
	 * r: patchDesignInscriptionVals
	 * r.enUS: patchEnrollmentDesignVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerDesignInscriptionGenPage
	 * r.enUS: refreshEnrollmentDesignGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereDesignInscriptionObjetSuggere
	 * r.enUS: suggestEnrollmentDesignObjectSuggest
	 * r: texteDesignInscriptionObjetTexte
	 * r.enUS: textEnrollmentDesignObjectText
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: 'objetTexte:'
	 * r.enUS: 'objectText:'
	 * r: '#suggereListDesignInscription'
	 * r.enUS: '#suggestListEnrollmentDesign'
	 * r: "suggereListDesignInscription"
	 * r.enUS: "suggestListEnrollmentDesign"
	**/
	public static void htmlSuggereDesignInscriptionGenPage(MiseEnPage p, String id, ListeRecherche<DesignInscription> listeDesignInscription) {
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

			Integer rows1 = Optional.ofNullable(listeDesignInscription).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeDesignInscription).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeDesignInscription).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeDesignInscription).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), DesignInscriptionGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), DesignInscriptionGenPage.ROLES)
					) {
				if(listeDesignInscription == null) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerTousDesignInscriptionGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ").a("onclick", "patchDesignInscriptionVals([], {}, function() { ajouterLueur($('#rechargerTousDesignInscriptionGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousDesignInscriptionGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger tous les design d'inscriptions");
						} p.g("button");
					} p.g("div");
				}
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher design d'inscriptions : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher design d'inscriptions")
					.a("class", "suggereDesignInscription w3-input w3-border w3-bar-item ")
					.a("name", "suggereDesignInscription")
					.a("id", "suggereDesignInscription", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereDesignInscriptionObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListDesignInscription", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/design-inscription?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeDesignInscription != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-khaki ")
					.a("onclick", "window.location.href = '/design-inscription?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListDesignInscription", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/design-inscription").a("class", "").f();
					p.e("i").a("class", "far fa-drafting-compass ").f().g("i");
					p.sx("voir tous les design d'inscriptions");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
