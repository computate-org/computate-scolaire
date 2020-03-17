package org.computate.scolaire.frFR.paiement;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.payment.PaymentGenPage
 **/
public class PaiementGenPage extends PaiementGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("User");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listePaiementScolaire(Couverture<ListeRecherche<PaiementScolaire>> c) {
	}

	protected void _paiementScolaire(Couverture<PaiementScolaire> c) {
		if(listePaiementScolaire != null && listePaiementScolaire.size() == 1)
			c.o(listePaiementScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("paiements");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(paiementScolaire != null && paiementScolaire.getPaiementNomComplet() != null)
			c.o(paiementScolaire.getPaiementNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(paiementScolaire != null && paiementScolaire.getPaiementNomComplet() != null)
			c.o(paiementScolaire.getPaiementNomComplet());
		else if(paiementScolaire != null)
			c.o("");
		else if(listePaiementScolaire == null || listePaiementScolaire.size() == 0)
			c.o("aucun paiement trouvé");
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
			c.o("search-dollar");
	}

	@Override public void initLoinPaiementGenPage() {
		initPaiementGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsPaiementGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/PaiementPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptPaiementGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggerePaiementScolaireInscriptionCle([{'name':'fq','value':'paiementCles:' + pk}], $('#listPaiementScolaireInscriptionCle_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketPaiementScolaire(websocketPaiementScolaireInner);");
		l("});");
	}

	public void htmlFormPagePaiementScolaire(PaiementScolaire o) {
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
			o.htmPaiementEspeces("Page");
			o.htmPaiementCheque("Page");
			o.htmPaiementSysteme("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDescription("Page");
			o.htmInscriptionPaimentComplet("Page");
			o.htmPaiementPar("Page");
			o.htmInscriptionPaimentChaqueMois("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("Page");
			o.htmCustomerProfileId("Page");
			o.htmTransactionStatus("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCle("Page");
			o.htmPaiementRecu("Page");
		} g("div");
	}

	public void htmlFormPOSTPaiementScolaire(PaiementScolaire o) {
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
			o.htmPaiementEspeces("POST");
			o.htmPaiementCheque("POST");
			o.htmPaiementSysteme("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDescription("POST");
			o.htmInscriptionPaimentComplet("POST");
			o.htmPaiementPar("POST");
			o.htmInscriptionPaimentChaqueMois("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("POST");
			o.htmCustomerProfileId("POST");
			o.htmTransactionStatus("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCle("POST");
			o.htmPaiementRecu("POST");
		} g("div");
	}

	public void htmlFormPUTPaiementScolaire(PaiementScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDate("PUT");
			o.htmPaiementMontant("PUT");
			o.htmPaiementEspeces("PUT");
			o.htmPaiementCheque("PUT");
			o.htmPaiementSysteme("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDescription("PUT");
			o.htmInscriptionPaimentComplet("PUT");
			o.htmPaiementPar("PUT");
			o.htmInscriptionPaimentChaqueMois("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("PUT");
			o.htmCustomerProfileId("PUT");
			o.htmTransactionStatus("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCle("PUT");
			o.htmPaiementRecu("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantNomCompletPrefere("PUT");
			o.htmEnfantDateNaissance("PUT");
			o.htmMereNomCompletPrefere("PUT");
			o.htmPereNomCompletPrefere("PUT");
			o.htmFraisMontant("PUT");
			o.htmFraisMontantFuture("PUT");
			o.htmFraisInscription("PUT");
			o.htmFraisPremierDernier("PUT");
			o.htmFraisMois("PUT");
			o.htmPaiementNomCourt("PUT");
		} g("div");
	}

	public void htmlFormPATCHPaiementScolaire(PaiementScolaire o) {
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
			o.htmPaiementEspeces("PATCH");
			o.htmPaiementCheque("PATCH");
			o.htmPaiementSysteme("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDescription("PATCH");
			o.htmInscriptionPaimentComplet("PATCH");
			o.htmPaiementPar("PATCH");
			o.htmInscriptionPaimentChaqueMois("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("PATCH");
			o.htmCustomerProfileId("PATCH");
			o.htmTransactionStatus("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCle("PATCH");
			o.htmPaiementRecu("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantNomCompletPrefere("PATCH");
			o.htmEnfantDateNaissance("PATCH");
			o.htmMereNomCompletPrefere("PATCH");
			o.htmPereNomCompletPrefere("PATCH");
			o.htmFraisMontant("PATCH");
			o.htmFraisMontantFuture("PATCH");
			o.htmFraisInscription("PATCH");
			o.htmFraisPremierDernier("PATCH");
			o.htmFraisMois("PATCH");
			o.htmPaiementNomCourt("PATCH");
		} g("div");
	}

	public void htmlFormRecherchePaiementScolaire(PaiementScolaire o) {
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
			o.htmPaiementEspeces("Recherche");
			o.htmPaiementCheque("Recherche");
			o.htmPaiementSysteme("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaiementDescription("Recherche");
			o.htmInscriptionPaimentComplet("Recherche");
			o.htmPaiementPar("Recherche");
			o.htmInscriptionPaimentChaqueMois("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("Recherche");
			o.htmCustomerProfileId("Recherche");
			o.htmTransactionStatus("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCle("Recherche");
			o.htmPaiementRecu("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
			o.htmEnfantNomCompletPrefere("Recherche");
			o.htmEnfantDateNaissance("Recherche");
			o.htmMereNomCompletPrefere("Recherche");
			o.htmPereNomCompletPrefere("Recherche");
			o.htmFraisMontant("Recherche");
			o.htmFraisMontantFuture("Recherche");
			o.htmFraisInscription("Recherche");
			o.htmFraisPremierDernier("Recherche");
			o.htmFraisMois("Recherche");
			o.htmPaiementNomCourt("Recherche");
		} g("div");
	}

	@Override public void htmlBodyPaiementGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listePaiementScolaire == null || listePaiementScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("paiements").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun paiement trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listePaiementScolaire != null && listePaiementScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PaiementScolaire o = listePaiementScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listePaiementScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listePaiementScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listePaiementScolaire.getRows();
					Integer start1 = listePaiementScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/paiement?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/paiement?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/paiement?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/paiement?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1PaiementGenPage();
		}

		if(listePaiementScolaire != null && listePaiementScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PaiementScolaire o = listePaiementScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "PaiementScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPagePaiementScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsPaiementGenPage();
		htmlSuggerePaiementGenPage(this, null);
		g("div");
	}

	public void table1PaiementGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2PaiementGenPage();
		} g("table");
	}

	public void table2PaiementGenPage() {
		thead1PaiementGenPage();
		tbody1PaiementGenPage();
		tfoot1PaiementGenPage();
	}

	public void thead1PaiementGenPage() {
		{ e("thead").a("class", "w3-green w3-hover-green ").f();
			thead2PaiementGenPage();
		} g("thead");
	}

	public void thead2PaiementGenPage() {
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
			if(getColonneFraisMontant()) {
				e("th").f().sx("frais montant").g("th");
			}
			} g("tr");
	}

	public void tbody1PaiementGenPage() {
		{ e("tbody").f();
			tbody2PaiementGenPage();
		} g("tbody");
	}

	public void tbody2PaiementGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listePaiementScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listePaiementScolaire.size(); i++) {
			PaiementScolaire o = listePaiementScolaire.getList().get(i);
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
							e("i").a("class", "fas fa-search-dollar ").f().g("i");
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
				if(getColonneFraisMontant()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strFraisMontant());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1PaiementGenPage() {
		{ e("tfoot").a("class", "w3-green w3-hover-green ").f();
			tfoot2PaiementGenPage();
		} g("tfoot");
	}

	public void tfoot2PaiementGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listePaiementScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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
				BigDecimal sum_paiementMontant = Optional.ofNullable((Double)facets.get("sum_paiementMontant")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
				e("span").a("class", "font-weight-bold ").f().sx(sum_paiementMontant).g("span");
				g("td");
			}
			if(getColonneFraisMontant()) {
				e("td").f();
				BigDecimal sum_fraisMontant = Optional.ofNullable((Double)facets.get("sum_fraisMontant")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
				e("span").a("class", "font-weight-bold ").f().sx(sum_fraisMontant).g("span");
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

	public Boolean getColonneFraisMontant() {
		return true;
	}

	public void htmlBodyFormsPaiementGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listePaiementScolaire != null && listePaiementScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("id", "rechargerCePaiementGenPage")
						.a("onclick", "patchPaiementScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCePaiementGenPage')); }, function() { ajouterErreur($('#rechargerCePaiementGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce paiement");
				} g("button");
			}

			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#postPaiementScolaireModale').show(); ")
				.f().sx("Créer un paiement")
			.g("button");
			{ e("div").a("id", "postPaiementScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postPaiementScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un paiement").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PaiementScolaire o = new PaiementScolaire();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postPaiementScolaireForm").f();
								htmlFormPOSTPaiementScolaire(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "postPaiementScolaire($('#postPaiementScolaireForm')); ")
								.f().sx("Créer un paiement")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#putPaiementScolaireModale').show(); ")
				.f().sx("Dupliquer des paiements")
			.g("button");
			{ e("div").a("id", "putPaiementScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putPaiementScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer des paiements").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PaiementScolaire o = new PaiementScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PUT
							{ e("form").a("action", "").a("id", "putPaiementScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTPaiementScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "putPaiementScolaire($('#putPaiementScolaireFormulaireValeurs'), ", Optional.ofNullable(paiementScolaire).map(PaiementScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Dupliquer des paiements")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#patchPaiementScolaireModale').show(); ")
				.f().sx("Modifier des paiements")
			.g("button");
			{ e("div").a("id", "patchPaiementScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchPaiementScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier des paiements").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PaiementScolaire o = new PaiementScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchPaiementScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHPaiementScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "patchPaiementScolaire($('#patchPaiementScolaireFormulaireFiltres'), $('#patchPaiementScolaireFormulaireValeurs'), ", Optional.ofNullable(paiementScolaire).map(PaiementScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier des paiements")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
	}

	/**
	 * Var.enUS: htmlSuggestPaymentGenPage
	 * r: "/paiement"
	 * r.enUS: "/payment"
	 * r: "voir tous les paiements"
	 * r.enUS: "see all the payments"
	 * r: "rechargerPaiementGenPage"
	 * r.enUS: "refreshPaymentGenPage"
	 * r: "recharger tous les paiements"
	 * r.enUS: "refresh all the payments"
	 * r: "rechercher paiements : "
	 * r.enUS: "search payments: "
	 * r: "suggereFormPaiementScolaire"
	 * r.enUS: "suggestFormSchoolPayment"
	 * r: "rechercher paiements"
	 * r.enUS: "search payments"
	 * r: "suggerePaiementScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolPayment w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggerePaiementScolaire"
	 * r.enUS: "suggestSchoolPayment"
	 * r: patchPaiementScolaireVals
	 * r.enUS: patchSchoolPaymentVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerPaiementGenPage
	 * r.enUS: refreshPaymentGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggerePaiementScolaireObjetSuggere
	 * r.enUS: suggestSchoolPaymentObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListPaiementScolaire'
	 * r.enUS: '#suggestListSchoolPayment'
	 * r: "suggereListPaiementScolaire"
	 * r.enUS: "suggestListSchoolPayment"
	**/
	public static void htmlSuggerePaiementGenPage(MiseEnPage p, String id) {
		RequeteSiteFrFR requeteSite_ = p.getRequeteSite_();
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), PaiementGenPage.ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), PaiementGenPage.ROLES)
				) {
			{ p.e("div").a("class", "").f();
				{ p.e("button").a("id", "rechargerTousPaiementGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ").a("onclick", "patchPaiementScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousPaiementGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousPaiementGenPage", id, "')); }); ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("recharger tous les paiements");
				} p.g("button");
			} p.g("div");
		}
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher paiements : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("placeholder", "rechercher paiements")
				.a("class", "suggerePaiementScolaire w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggerePaiementScolaire")
				.a("id", "suggerePaiementScolaire", id)
				.a("autocomplete", "off")
				.a("oninput", "suggerePaiementScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListPaiementScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListPaiementScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/paiement").a("class", "").f();
				p.e("i").a("class", "fas fa-search-dollar ").f().g("i");
				p.sx("voir tous les paiements");
			} p.g("a");
		} p.g("div");
	}

}
