package org.computate.scolaire.frFR.annee;

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
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.year.YearGenPage
 **/
public class AnneeGenPage extends AnneeGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeAnneeScolaire(Couverture<ListeRecherche<AnneeScolaire>> c) {
	}

	protected void _anneeScolaire_(Couverture<AnneeScolaire> c) {
		if(listeAnneeScolaire != null && listeAnneeScolaire.size() == 1)
			c.o(listeAnneeScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("années");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(anneeScolaire_ != null && anneeScolaire_.getAnneeNomComplet() != null)
			c.o(anneeScolaire_.getAnneeNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(anneeScolaire_ != null && anneeScolaire_.getAnneeNomComplet() != null)
			c.o(anneeScolaire_.getAnneeNomComplet());
		else if(anneeScolaire_ != null)
			c.o("années");
		else if(listeAnneeScolaire == null || listeAnneeScolaire.size() == 0)
			c.o("aucune année trouvée");
		else
			c.o("années");
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
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/SaisonPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AgePage.js").f().g("script");
	}

	@Override public void htmlScriptAnneeGenPage() {
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
			tl(2, "suggereAnneeScolaireEcoleCle([{'name':'fq','value':'anneeCles:' + pk}], $('#listAnneeScolaireEcoleCle_Page'), pk, true); ");
		} else {
			tl(2, "suggereAnneeScolaireEcoleCle([{'name':'fq','value':'anneeCles:' + pk}], $('#listAnneeScolaireEcoleCle_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereAnneeScolaireAgeCles([{'name':'fq','value':'anneeCle:' + pk}], $('#listAnneeScolaireAgeCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereAnneeScolaireAgeCles([{'name':'fq','value':'anneeCle:' + pk}], $('#listAnneeScolaireAgeCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereAnneeScolaireInscriptionCles([{'name':'fq','value':'anneeCle:' + pk}], $('#listAnneeScolaireInscriptionCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereAnneeScolaireInscriptionCles([{'name':'fq','value':'anneeCle:' + pk}], $('#listAnneeScolaireInscriptionCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereAnneeScolaireSaisonCles([{'name':'fq','value':'anneeCle:' + pk}], $('#listAnneeScolaireSaisonCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereAnneeScolaireSaisonCles([{'name':'fq','value':'anneeCle:' + pk}], $('#listAnneeScolaireSaisonCles_Page'), pk, false); ");
		}
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
			o.htmSessionDateDebut("Page");
			o.htmSessionDateFin("Page");
			o.htmAnneeFraisInscription("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("Page");
			o.htmAgeCles("Page");
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
			o.htmSessionDateDebut("POST");
			o.htmSessionDateFin("POST");
			o.htmAnneeFraisInscription("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("POST");
			o.htmAgeCles("POST");
		} g("div");
	}

	public void htmlFormPUTImportAnneeScolaire(AnneeScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			e("textarea")
				.a("class", "PUTImport_liste w3-input w3-border ")
				.a("style", "height: 400px; ")
				.a("placeholder", "{ \"liste\": [ { \"pk\": ... , \"sauvegardes\": [ ... ] }, ... ] }")
				;
				f();
			g("textarea");
		} g("div");
	}

	public void htmlFormPUTFusionAnneeScolaire(AnneeScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			e("textarea")
				.a("class", "PUTFusion_liste w3-input w3-border ")
				.a("style", "height: 400px; ")
				.a("placeholder", "{ \"liste\": [ { \"pk\": ... , \"sauvegardes\": [ ... ] }, ... ] }")
				;
				f();
			g("textarea");
		} g("div");
	}

	public void htmlFormPUTCopieAnneeScolaire(AnneeScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUTCopie");
			o.htmModifie("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUTCopie");
			o.htmSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionDateDebut("PUTCopie");
			o.htmSessionDateFin("PUTCopie");
			o.htmAnneeFraisInscription("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("PUTCopie");
			o.htmAgeCles("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopie");
			o.htmSessionId("PUTCopie");
			o.htmUtilisateurId("PUTCopie");
			o.htmUtilisateurCle("PUTCopie");
			o.htmSaisonDateDebut("PUTCopie");
			o.htmAnneeDebut("PUTCopie");
			o.htmAnneeFin("PUTCopie");
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
			o.htmSessionDateDebut("PATCH");
			o.htmSessionDateFin("PATCH");
			o.htmAnneeFraisInscription("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("PATCH");
			o.htmAgeCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUtilisateurId("PATCH");
			o.htmUtilisateurCle("PATCH");
			o.htmSaisonDateDebut("PATCH");
			o.htmAnneeDebut("PATCH");
			o.htmAnneeFin("PATCH");
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
			o.htmSessionDateDebut("Recherche");
			o.htmSessionDateFin("Recherche");
			o.htmAnneeFraisInscription("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("Recherche");
			o.htmAgeCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Recherche");
			o.htmSessionId("Recherche");
			o.htmUtilisateurId("Recherche");
			o.htmUtilisateurCle("Recherche");
			o.htmObjetTitre("Recherche");
			o.htmSaisonDateDebut("Recherche");
			o.htmAnneeDebut("Recherche");
			o.htmAnneeFin("Recherche");
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
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeAnneeScolaire.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listeAnneeScolaire).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeAnneeScolaire).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeAnneeScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeAnneeScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/annee?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/annee?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/annee?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/annee?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#postAnneeScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer une année");
			} g("button");
			{ e("div").a("id", "postAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postAnneeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer une année").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "postAnneeScolaireFormulaireValeurs").f();
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


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#putimportAnneeScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Importer années");
			} g("button");
			{ e("div").a("id", "putimportAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportAnneeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Importer années").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putimportAnneeScolaireFormulaireValeurs").f();
							AnneeScolaire o = new AnneeScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putimportAnneeScolaireForm").f();
								htmlFormPUTImportAnneeScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "putimportAnneeScolaire($('#putimportAnneeScolaireForm')); ")
								.f().sx("Importer années")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#putfusionAnneeScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Fusionner années");
			} g("button");
			{ e("div").a("id", "putfusionAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putfusionAnneeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Fusionner années").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putfusionAnneeScolaireFormulaireValeurs").f();
							AnneeScolaire o = new AnneeScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putfusionAnneeScolaireForm").f();
								htmlFormPUTFusionAnneeScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "putfusionAnneeScolaire($('#putfusionAnneeScolaireForm')); ")
								.f().sx("Fusionner années")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#putcopieAnneeScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer années");
			} g("button");
			{ e("div").a("id", "putcopieAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopieAnneeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer années").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putcopieAnneeScolaireFormulaireValeurs").f();
							AnneeScolaire o = new AnneeScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putcopieAnneeScolaireForm").f();
								htmlFormPUTCopieAnneeScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "putcopieAnneeScolaire($('#putcopieAnneeScolaireForm'), ", anneeScolaire_ == null ? "null" : anneeScolaire_.getPk(), "); ")
								.f().sx("Dupliquer années")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#patchAnneeScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier années");
			} g("button");
			{ e("div").a("id", "patchAnneeScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-orange ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchAnneeScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier années").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "patchAnneeScolaireFormulaireValeurs").f();
							AnneeScolaire o = new AnneeScolaire();
							o.setRequeteSite_(requeteSite_);

							htmlFormPATCHAnneeScolaire(o);
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-orange ")
								.a("onclick", "patchAnneeScolaire(null, $('#patchAnneeScolaireFormulaireValeurs'), ", Optional.ofNullable(anneeScolaire_).map(AnneeScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier années")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggereAnneeGenPage(this, null, listeAnneeScolaire);
	}

	/**
	 * Var.enUS: htmlSuggestedYearGenPage
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
	 * r: texteAnneeScolaireObjetTexte
	 * r.enUS: textSchoolYearObjectText
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: 'objetTexte:'
	 * r.enUS: 'objectText:'
	 * r: '#suggereListAnneeScolaire'
	 * r.enUS: '#suggestListSchoolYear'
	 * r: "suggereListAnneeScolaire"
	 * r.enUS: "suggestListSchoolYear"
	**/
	public static void htmlSuggereAnneeGenPage(MiseEnPage p, String id, ListeRecherche<AnneeScolaire> listeAnneeScolaire) {
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

			Integer rows1 = Optional.ofNullable(listeAnneeScolaire).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeAnneeScolaire).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeAnneeScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeAnneeScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), AnneeGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), AnneeGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerToutesAnneeGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ").a("onclick", "patchAnneeScolaireVals([], {}, function() { ajouterLueur($('#rechargerToutesAnneeGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesAnneeGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger toutes les années");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher années : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher années")
					.a("class", "suggereAnneeScolaire w3-input w3-border w3-bar-item ")
					.a("name", "suggereAnneeScolaire")
					.a("id", "suggereAnneeScolaire", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereAnneeScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,anneeNomComplet' } ], $('#suggereListAnneeScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/annee?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeAnneeScolaire != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
					.a("onclick", "window.location.href = '/annee?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

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
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
