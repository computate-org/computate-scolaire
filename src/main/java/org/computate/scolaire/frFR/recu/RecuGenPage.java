package org.computate.scolaire.frFR.recu;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.receipt.ReceiptGenPage
 **/
public class RecuGenPage extends RecuGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeRecuScolaire(Couverture<ListeRecherche<RecuScolaire>> c) {
	}

	protected void _recuScolaire_(Couverture<RecuScolaire> c) {
		if(listeRecuScolaire != null && listeRecuScolaire.size() == 1)
			c.o(listeRecuScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("reçus");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(recuScolaire_ != null && recuScolaire_.getPaiementNomComplet() != null)
			c.o(recuScolaire_.getPaiementNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(recuScolaire_ != null && recuScolaire_.getPaiementNomComplet() != null)
			c.o(recuScolaire_.getPaiementNomComplet());
		else if(recuScolaire_ != null)
			c.o("reçus");
		else if(listeRecuScolaire == null || listeRecuScolaire.size() == 0)
			c.o("aucun reçu trouvé");
		else
			c.o("reçus");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/paiement");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/paiement-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("solid");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("file-invoice-dollar");
	}

	@Override public void initLoinRecuGenPage() {
		initRecuGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsRecuGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/RecuPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/EcolePage.js").f().g("script");
	}

	@Override public void htmlScriptRecuGenPage() {
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
			tl(2, "suggereRecuScolaireEcoleCle([{'name':'fq','value':'recuCles:' + pk}], $('#listRecuScolaireEcoleCle_Page'), pk, true); ");
		} else {
			tl(2, "suggereRecuScolaireEcoleCle([{'name':'fq','value':'recuCles:' + pk}], $('#listRecuScolaireEcoleCle_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketRecuScolaire(websocketRecuScolaireInner);");
		l("});");
	}

	public void htmlFormPageRecuScolaire(RecuScolaire o) {
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
			o.htmPaiementDate("Page");
			o.htmPaiementMontant("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("Page");
			o.htmPaiementDescription("Page");
		} g("div");
	}

	public void htmlFormPOSTRecuScolaire(RecuScolaire o) {
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
			o.htmPaiementDate("POST");
			o.htmPaiementMontant("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("POST");
			o.htmPaiementDescription("POST");
		} g("div");
	}

	public void htmlFormPUTImportRecuScolaire(RecuScolaire o) {
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

	public void htmlFormPUTFusionRecuScolaire(RecuScolaire o) {
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

	public void htmlFormPUTCopieRecuScolaire(RecuScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUTCopie");
			o.htmModifie("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUTCopie");
			o.htmSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDate("PUTCopie");
			o.htmPaiementMontant("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("PUTCopie");
			o.htmPaiementDescription("PUTCopie");
		} g("div");
	}

	public void htmlFormPATCHRecuScolaire(RecuScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDate("PATCH");
			o.htmPaiementMontant("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("PATCH");
			o.htmPaiementDescription("PATCH");
		} g("div");
	}

	public void htmlFormRechercheRecuScolaire(RecuScolaire o) {
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
			o.htmPaiementDate("Recherche");
			o.htmPaiementMontant("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEcoleCle("Recherche");
			o.htmPaiementDescription("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Recherche");
			o.htmSessionId("Recherche");
			o.htmUtilisateurId("Recherche");
			o.htmUtilisateurCle("Recherche");
			o.htmObjetTitre("Recherche");
			o.htmPaiementNomCourt("Recherche");
		} g("div");
	}

	@Override public void htmlBodyRecuGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeRecuScolaire == null || listeRecuScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-green w3-hover-light-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("reçus").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun reçu trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeRecuScolaire != null && listeRecuScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			RecuScolaire o = listeRecuScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-green w3-hover-light-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-green w3-hover-light-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeRecuScolaire.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listeRecuScolaire).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeRecuScolaire).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeRecuScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeRecuScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/paiement?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/paiement?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/paiement?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/paiement?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1RecuGenPage();
		}

		if(listeRecuScolaire != null && listeRecuScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			RecuScolaire o = listeRecuScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "RecuScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageRecuScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsRecuGenPage();
		g("div");
	}

	public void table1RecuGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2RecuGenPage();
		} g("table");
	}

	public void table2RecuGenPage() {
		thead1RecuGenPage();
		tbody1RecuGenPage();
		tfoot1RecuGenPage();
	}

	public void thead1RecuGenPage() {
		{ e("thead").a("class", "w3-light-green w3-hover-light-green ").f();
			thead2RecuGenPage();
		} g("thead");
	}

	public void thead2RecuGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			if(getColonnePaiementNomCourt()) {
				e("th").f().sx("nom").g("th");
			}
			if(getColonnePaiementDate()) {
				e("th").f().sx("date de paiement").g("th");
			}
			if(getColonnePaiementMontant()) {
				e("th").f().sx("paiement montant").g("th");
			}
			} g("tr");
	}

	public void tbody1RecuGenPage() {
		{ e("tbody").f();
			tbody2RecuGenPage();
		} g("tbody");
	}

	public void tbody2RecuGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeRecuScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeRecuScolaire.size(); i++) {
			RecuScolaire o = listeRecuScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/paiement/" + o.getPk();
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
							e("i").a("class", "fas fa-file-invoice-dollar ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColonnePaiementNomCourt()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strPaiementNomCourt());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColonnePaiementDate()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strPaiementDate());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColonnePaiementMontant()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strPaiementMontant());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1RecuGenPage() {
		{ e("tfoot").a("class", "w3-light-green w3-hover-light-green ").f();
			tfoot2RecuGenPage();
		} g("tfoot");
	}

	public void tfoot2RecuGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeRecuScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			if(getColonneCree()) {
				e("td").f();
				g("td");
			}
			if(getColonneObjetTitre()) {
				e("td").f();
				g("td");
			}
			if(getColonnePaiementNomCourt()) {
				e("td").f();
				g("td");
			}
			if(getColonnePaiementDate()) {
				e("td").f();
				g("td");
			}
			if(getColonnePaiementMontant()) {
				e("td").f();
				BigDecimal sum_paiementMontant = Optional.ofNullable((Double)facets.get("sum_paiementMontant")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
				e("span").a("class", "font-weight-bold ").f().sx(sum_paiementMontant).g("span");
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

	public Boolean getColonnePaiementNomCourt() {
		return true;
	}

	public Boolean getColonnePaiementDate() {
		return true;
	}

	public Boolean getColonnePaiementMontant() {
		return true;
	}

	public void htmlBodyFormsRecuGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeRecuScolaire != null && listeRecuScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
						.a("id", "rechargerCeRecuGenPage")
						.a("onclick", "patchRecuScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeRecuGenPage')); }, function() { ajouterErreur($('#rechargerCeRecuGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce reçu");
				} g("button");
			}
		}
		if(
				requeteSite_.getUtilisateurRolesRessource().contains("SiteAdmin")
				|| requeteSite_.getUtilisateurRolesRoyaume().contains("SiteAdmin")
				) {

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#postRecuScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un reçu");
			} g("button");
			{ e("div").a("id", "postRecuScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postRecuScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un reçu").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "postRecuScolaireFormulaireValeurs").f();
							RecuScolaire o = new RecuScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postRecuScolaireForm").f();
								htmlFormPOSTRecuScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "postRecuScolaire($('#postRecuScolaireForm')); ")
								.f().sx("Créer un reçu")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#putimportRecuScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Importer reçus");
			} g("button");
			{ e("div").a("id", "putimportRecuScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportRecuScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Importer reçus").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putimportRecuScolaireFormulaireValeurs").f();
							RecuScolaire o = new RecuScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putimportRecuScolaireForm").f();
								htmlFormPUTImportRecuScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "putimportRecuScolaire($('#putimportRecuScolaireForm')); ")
								.f().sx("Importer reçus")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#putfusionRecuScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Fusionner reçus");
			} g("button");
			{ e("div").a("id", "putfusionRecuScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putfusionRecuScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Fusionner reçus").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putfusionRecuScolaireFormulaireValeurs").f();
							RecuScolaire o = new RecuScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putfusionRecuScolaireForm").f();
								htmlFormPUTFusionRecuScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "putfusionRecuScolaire($('#putfusionRecuScolaireForm')); ")
								.f().sx("Fusionner reçus")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#putcopieRecuScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer reçus");
			} g("button");
			{ e("div").a("id", "putcopieRecuScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopieRecuScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer reçus").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putcopieRecuScolaireFormulaireValeurs").f();
							RecuScolaire o = new RecuScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putcopieRecuScolaireForm").f();
								htmlFormPUTCopieRecuScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "putcopieRecuScolaire($('#putcopieRecuScolaireForm'), ", recuScolaire_ == null ? "null" : recuScolaire_.getPk(), "); ")
								.f().sx("Dupliquer reçus")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#patchRecuScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier reçus");
			} g("button");
			{ e("div").a("id", "patchRecuScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchRecuScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier reçus").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "patchRecuScolaireFormulaireValeurs").f();
							RecuScolaire o = new RecuScolaire();
							o.setRequeteSite_(requeteSite_);

							htmlFormPATCHRecuScolaire(o);
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "patchRecuScolaire(null, $('#patchRecuScolaireFormulaireValeurs'), ", Optional.ofNullable(recuScolaire_).map(RecuScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier reçus")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggereRecuGenPage(this, null, listeRecuScolaire);
	}

	/**
	 * Var.enUS: htmlSuggestedReceiptGenPage
	 * r: "/paiement"
	 * r.enUS: "/receipt"
	 * r: "voir tous les reçus"
	 * r.enUS: "see all the receipts"
	 * r: "rechargerRecuGenPage"
	 * r.enUS: "refreshReceiptGenPage"
	 * r: "recharger tous les reçus"
	 * r.enUS: "refresh all the receipts"
	 * r: "rechercher reçus : "
	 * r.enUS: "search receipts: "
	 * r: "suggereFormRecuScolaire"
	 * r.enUS: "suggestFormSchoolReceipt"
	 * r: "rechercher reçus"
	 * r.enUS: "search receipts"
	 * r: "suggereRecuScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolReceipt w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereRecuScolaire"
	 * r.enUS: "suggestSchoolReceipt"
	 * r: patchRecuScolaireVals
	 * r.enUS: patchSchoolReceiptVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerRecuGenPage
	 * r.enUS: refreshReceiptGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereRecuScolaireObjetSuggere
	 * r.enUS: suggestSchoolReceiptObjectSuggest
	 * r: texteRecuScolaireObjetTexte
	 * r.enUS: textSchoolReceiptObjectText
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: 'objetTexte:'
	 * r.enUS: 'objectText:'
	 * r: '#suggereListRecuScolaire'
	 * r.enUS: '#suggestListSchoolReceipt'
	 * r: "suggereListRecuScolaire"
	 * r.enUS: "suggestListSchoolReceipt"
	**/
	public static void htmlSuggereRecuGenPage(MiseEnPage p, String id, ListeRecherche<RecuScolaire> listeRecuScolaire) {
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

			Integer rows1 = Optional.ofNullable(listeRecuScolaire).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeRecuScolaire).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeRecuScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeRecuScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), RecuGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), RecuGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerTousRecuGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ").a("onclick", "patchRecuScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousRecuGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousRecuGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger tous les reçus");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher reçus : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher reçus")
					.a("class", "suggereRecuScolaire w3-input w3-border w3-bar-item ")
					.a("name", "suggereRecuScolaire")
					.a("id", "suggereRecuScolaire", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereRecuScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,paiementNomComplet' } ], $('#suggereListRecuScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/paiement?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeRecuScolaire != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
					.a("onclick", "window.location.href = '/paiement?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListRecuScolaire", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/paiement").a("class", "").f();
					p.e("i").a("class", "fas fa-file-invoice-dollar ").f().g("i");
					p.sx("voir tous les reçus");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
