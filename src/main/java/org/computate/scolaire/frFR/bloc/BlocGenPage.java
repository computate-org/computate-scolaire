package org.computate.scolaire.frFR.bloc;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.block.BlockGenPage
 **/
public class BlocGenPage extends BlocGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

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
			tl(2, "suggereBlocScolaireAgeCle([{'name':'fq','value':'blocCles:' + pk}], $('#listBlocScolaireAgeCle_Page'), pk, true); ");
		} else {
			tl(2, "suggereBlocScolaireAgeCle([{'name':'fq','value':'blocCles:' + pk}], $('#listBlocScolaireAgeCle_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereBlocScolaireInscriptionCles([{'name':'fq','value':'blocCles:' + pk}], $('#listBlocScolaireInscriptionCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereBlocScolaireInscriptionCles([{'name':'fq','value':'blocCles:' + pk}], $('#listBlocScolaireInscriptionCles_Page'), pk, false); ");
		}
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

	public void htmlFormPUTImportBlocScolaire(BlocScolaire o) {
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

	public void htmlFormPUTFusionBlocScolaire(BlocScolaire o) {
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

	public void htmlFormPUTCopieBlocScolaire(BlocScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUTCopie");
			o.htmModifie("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUTCopie");
			o.htmSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocHeureDebut("PUTCopie");
			o.htmBlocHeureFin("PUTCopie");
			o.htmBlocPrixParMois("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocLundi("PUTCopie");
			o.htmBlocMardi("PUTCopie");
			o.htmBlocMercredi("PUTCopie");
			o.htmBlocJeudi("PUTCopie");
			o.htmBlocVendredi("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeCle("PUTCopie");
			o.htmInscriptionCles("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopie");
			o.htmSessionId("PUTCopie");
			o.htmUtilisateurId("PUTCopie");
			o.htmUtilisateurCle("PUTCopie");
			o.htmEcoleAddresse("PUTCopie");
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
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUtilisateurId("PATCH");
			o.htmUtilisateurCle("PATCH");
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
			o.htmInheritPk("Recherche");
			o.htmSessionId("Recherche");
			o.htmUtilisateurId("Recherche");
			o.htmUtilisateurCle("Recherche");
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
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeBlocScolaire.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listeBlocScolaire).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeBlocScolaire).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeBlocScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeBlocScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/bloc?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/bloc?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/bloc?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/bloc?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1BlocGenPage();
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
		g("div");
	}

	public void table1BlocGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2BlocGenPage();
		} g("table");
	}

	public void table2BlocGenPage() {
		thead1BlocGenPage();
		tbody1BlocGenPage();
		tfoot1BlocGenPage();
	}

	public void thead1BlocGenPage() {
		{ e("thead").a("class", "w3-indigo w3-hover-indigo ").f();
			thead2BlocGenPage();
		} g("thead");
	}

	public void thead2BlocGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1BlocGenPage() {
		{ e("tbody").f();
			tbody2BlocGenPage();
		} g("tbody");
	}

	public void tbody2BlocGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeBlocScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeBlocScolaire.size(); i++) {
			BlocScolaire o = listeBlocScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/bloc/" + o.getPk();
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
							e("i").a("class", "far fa-bell ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1BlocGenPage() {
		{ e("tfoot").a("class", "w3-indigo w3-hover-indigo ").f();
			tfoot2BlocGenPage();
		} g("tfoot");
	}

	public void tfoot2BlocGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeBlocScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsBlocGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeBlocScolaire != null && listeBlocScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("id", "rechargerCeBlocGenPage")
						.a("onclick", "patchBlocScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeBlocGenPage')); }, function() { ajouterErreur($('#rechargerCeBlocGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce bloc");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#postBlocScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un bloc");
			} g("button");
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


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#putimportBlocScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Importer blocs");
			} g("button");
			{ e("div").a("id", "putimportBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportBlocScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Importer blocs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							BlocScolaire o = new BlocScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putimportBlocScolaireForm").f();
								htmlFormPUTImportBlocScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "putimportBlocScolaire($('#putimportBlocScolaireForm')); ")
								.f().sx("Importer blocs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#putfusionBlocScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Fusionner blocs");
			} g("button");
			{ e("div").a("id", "putfusionBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putfusionBlocScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Fusionner blocs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							BlocScolaire o = new BlocScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putfusionBlocScolaireForm").f();
								htmlFormPUTFusionBlocScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "putfusionBlocScolaire($('#putfusionBlocScolaireForm')); ")
								.f().sx("Fusionner blocs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#putcopieBlocScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer blocs");
			} g("button");
			{ e("div").a("id", "putcopieBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopieBlocScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer blocs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							BlocScolaire o = new BlocScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putcopieBlocScolaireForm").f();
								htmlFormPUTCopieBlocScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "putcopieBlocScolaire($('#putcopieBlocScolaireForm'), ", blocScolaire == null ? "null" : blocScolaire.getPk(), "); ")
								.f().sx("Dupliquer blocs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#patchBlocScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier blocs");
			} g("button");
			{ e("div").a("id", "patchBlocScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchBlocScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier blocs").g("h2");
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
								.a("onclick", "patchBlocScolaire(null, $('#patchBlocScolaireFormulaireValeurs'), ", Optional.ofNullable(blocScolaire).map(BlocScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier blocs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggereBlocGenPage(this, null, listeBlocScolaire);
	}

	/**
	 * Var.enUS: htmlSuggestedBlockGenPage
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
	 * r: texteBlocScolaireObjetTexte
	 * r.enUS: textSchoolBlockObjectText
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: 'objetTexte:'
	 * r.enUS: 'objectText:'
	 * r: '#suggereListBlocScolaire'
	 * r.enUS: '#suggestListSchoolBlock'
	 * r: "suggereListBlocScolaire"
	 * r.enUS: "suggestListSchoolBlock"
	**/
	public static void htmlSuggereBlocGenPage(MiseEnPage p, String id, ListeRecherche<BlocScolaire> listeBlocScolaire) {
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

			Integer rows1 = Optional.ofNullable(listeBlocScolaire).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeBlocScolaire).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeBlocScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeBlocScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), BlocGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), BlocGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerTousBlocGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ").a("onclick", "patchBlocScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousBlocGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousBlocGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger tous les blocs");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher blocs : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher blocs")
					.a("class", "suggereBlocScolaire w3-input w3-border w3-bar-item ")
					.a("name", "suggereBlocScolaire")
					.a("id", "suggereBlocScolaire", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereBlocScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListBlocScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/bloc?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeBlocScolaire != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
					.a("onclick", "window.location.href = '/bloc?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

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
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
