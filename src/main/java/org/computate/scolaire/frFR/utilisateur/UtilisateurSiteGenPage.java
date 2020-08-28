package org.computate.scolaire.frFR.utilisateur;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.user.SiteUserGenPage
 **/
public class UtilisateurSiteGenPage extends UtilisateurSiteGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin", "SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeUtilisateurSite(Couverture<ListeRecherche<UtilisateurSite>> c) {
	}

	protected void _utilisateurSite_(Couverture<UtilisateurSite> c) {
		if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1)
			c.o(listeUtilisateurSite.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("utilisateurs du site");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(utilisateurSite_ != null && utilisateurSite_.getObjetTitre() != null)
			c.o(utilisateurSite_.getObjetTitre());
		else if(utilisateurSite_ != null)
			c.o("utilisateurs du site");
		else if(listeUtilisateurSite == null || listeUtilisateurSite.size() == 0)
			c.o("aucun utilisateur du site trouvé");
		else
			c.o("utilisateurs du site");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/utilisateur");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/utilisateur-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("user-cog");
	}

	@Override public void initLoinUtilisateurSiteGenPage() {
		initUtilisateurSiteGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsUtilisateurSiteGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/UtilisateurSitePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PaiementPage.js").f().g("script");
	}

	@Override public void htmlScriptUtilisateurSiteGenPage() {
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
			tl(2, "suggereUtilisateurSiteInscriptionCles([{'name':'fq','value':'utilisateurCles:' + pk}], $('#listUtilisateurSiteInscriptionCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereUtilisateurSiteInscriptionCles([{'name':'fq','value':'utilisateurCles:' + pk}], $('#listUtilisateurSiteInscriptionCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereUtilisateurSitePaiementCles([{'name':'fq','value':'utilisateurCles:' + pk}], $('#listUtilisateurSitePaiementCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereUtilisateurSitePaiementCles([{'name':'fq','value':'utilisateurCles:' + pk}], $('#listUtilisateurSitePaiementCles_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketUtilisateurSite(websocketUtilisateurSiteInner);");
		l("});");
	}

	public void htmlFormPageUtilisateurSite(UtilisateurSite o) {
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
			o.htmUtilisateurRecevoirCourriels("Page");
			o.htmVoirArchive("Page");
			o.htmVoirSupprime("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("Page");
			o.htmCustomerProfileId2("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Page");
			o.htmPaiementCles("Page");
		} g("div");
	}

	public void htmlFormPOSTUtilisateurSite(UtilisateurSite o) {
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
			o.htmUtilisateurRecevoirCourriels("POST");
			o.htmVoirArchive("POST");
			o.htmVoirSupprime("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("POST");
			o.htmCustomerProfileId2("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("POST");
			o.htmPaiementCles("POST");
		} g("div");
	}

	public void htmlFormPUTImportUtilisateurSite(UtilisateurSite o) {
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

	public void htmlFormPUTFusionUtilisateurSite(UtilisateurSite o) {
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

	public void htmlFormPUTCopieUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUTCopie");
			o.htmModifie("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUTCopie");
			o.htmSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurRecevoirCourriels("PUTCopie");
			o.htmVoirArchive("PUTCopie");
			o.htmVoirSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("PUTCopie");
			o.htmCustomerProfileId2("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PUTCopie");
			o.htmPaiementCles("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopie");
			o.htmSessionId("PUTCopie");
			o.htmUtilisateurId("PUTCopie");
			o.htmUtilisateurCle("PUTCopie");
			o.htmUtilisateurNom("PUTCopie");
			o.htmUtilisateurMail("PUTCopie");
		} g("div");
	}

	public void htmlFormPATCHUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurRecevoirCourriels("PATCH");
			o.htmVoirArchive("PATCH");
			o.htmVoirSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("PATCH");
			o.htmCustomerProfileId2("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("PATCH");
			o.htmPaiementCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUtilisateurId("PATCH");
			o.htmUtilisateurCle("PATCH");
			o.htmUtilisateurNom("PATCH");
			o.htmUtilisateurMail("PATCH");
		} g("div");
	}

	public void htmlFormRechercheUtilisateurSite(UtilisateurSite o) {
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
			o.htmUtilisateurRecevoirCourriels("Recherche");
			o.htmVoirArchive("Recherche");
			o.htmVoirSupprime("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("Recherche");
			o.htmCustomerProfileId2("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionCles("Recherche");
			o.htmPaiementCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Recherche");
			o.htmSessionId("Recherche");
			o.htmUtilisateurId("Recherche");
			o.htmUtilisateurCle("Recherche");
			o.htmObjetTitre("Recherche");
			o.htmUtilisateurNom("Recherche");
			o.htmUtilisateurMail("Recherche");
		} g("div");
	}

	@Override public void htmlBodyUtilisateurSiteGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeUtilisateurSite == null || listeUtilisateurSite.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/utilisateur").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("utilisateurs du site").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun utilisateur du site trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			UtilisateurSite o = listeUtilisateurSite.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/utilisateur").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
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
				{ e("a").a("href", "/utilisateur").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeUtilisateurSite.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listeUtilisateurSite).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeUtilisateurSite).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeUtilisateurSite).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeUtilisateurSite).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/utilisateur?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/utilisateur?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/utilisateur?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/utilisateur?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1UtilisateurSiteGenPage();
		}

		if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			UtilisateurSite o = listeUtilisateurSite.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "UtilisateurSiteForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageUtilisateurSite(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");

		}
		htmlBodyFormsUtilisateurSiteGenPage();
		g("div");
	}

	public void table1UtilisateurSiteGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2UtilisateurSiteGenPage();
		} g("table");
	}

	public void table2UtilisateurSiteGenPage() {
		thead1UtilisateurSiteGenPage();
		tbody1UtilisateurSiteGenPage();
		tfoot1UtilisateurSiteGenPage();
	}

	public void thead1UtilisateurSiteGenPage() {
		{ e("thead").a("class", "w3-gray w3-hover-gray ").f();
			thead2UtilisateurSiteGenPage();
		} g("thead");
	}

	public void thead2UtilisateurSiteGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1UtilisateurSiteGenPage() {
		{ e("tbody").f();
			tbody2UtilisateurSiteGenPage();
		} g("tbody");
	}

	public void tbody2UtilisateurSiteGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeUtilisateurSite.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeUtilisateurSite.size(); i++) {
			UtilisateurSite o = listeUtilisateurSite.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/utilisateur/" + o.getPk();
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
							e("i").a("class", "far fa-user-cog ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1UtilisateurSiteGenPage() {
		{ e("tfoot").a("class", "w3-gray w3-hover-gray ").f();
			tfoot2UtilisateurSiteGenPage();
		} g("tfoot");
	}

	public void tfoot2UtilisateurSiteGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeUtilisateurSite.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsUtilisateurSiteGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
						.a("id", "rechargerCeUtilisateurSiteGenPage")
						.a("onclick", "patchUtilisateurSiteVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeUtilisateurSiteGenPage')); }, function() { ajouterErreur($('#rechargerCeUtilisateurSiteGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger cet utilisateur du site");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#patchUtilisateurSiteModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier utilisateurs du site");
			} g("button");
			{ e("div").a("id", "patchUtilisateurSiteModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchUtilisateurSiteModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier utilisateurs du site").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "patchUtilisateurSiteFormulaireValeurs").f();
							UtilisateurSite o = new UtilisateurSite();
							o.setRequeteSite_(requeteSite_);

							htmlFormPATCHUtilisateurSite(o);
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "patchUtilisateurSite(null, $('#patchUtilisateurSiteFormulaireValeurs'), ", Optional.ofNullable(utilisateurSite_).map(UtilisateurSite::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier utilisateurs du site")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#postUtilisateurSiteModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un utilisateur du site");
			} g("button");
			{ e("div").a("id", "postUtilisateurSiteModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postUtilisateurSiteModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un utilisateur du site").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "postUtilisateurSiteFormulaireValeurs").f();
							UtilisateurSite o = new UtilisateurSite();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postUtilisateurSiteForm").f();
								htmlFormPOSTUtilisateurSite(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "postUtilisateurSite($('#postUtilisateurSiteForm')); ")
								.f().sx("Créer un utilisateur du site")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggereUtilisateurSiteGenPage(this, null, listeUtilisateurSite);
	}

	/**
	 * Var.enUS: htmlSuggestedSiteUserGenPage
	 * r: "/utilisateur"
	 * r.enUS: "/user"
	 * r: "voir tous les utilisateurs du site"
	 * r.enUS: "see all the site users"
	 * r: "rechargerUtilisateurSiteGenPage"
	 * r.enUS: "refreshSiteUserGenPage"
	 * r: "recharger tous les utilisateurs du site"
	 * r.enUS: "refresh all the site users"
	 * r: "rechercher utilisateurs du site : "
	 * r.enUS: "search site users: "
	 * r: "suggereFormUtilisateurSite"
	 * r.enUS: "suggestFormSiteUser"
	 * r: "rechercher utilisateurs du site"
	 * r.enUS: "search site users"
	 * r: "suggereUtilisateurSite w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSiteUser w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereUtilisateurSite"
	 * r.enUS: "suggestSiteUser"
	 * r: patchUtilisateurSiteVals
	 * r.enUS: patchSiteUserVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerUtilisateurSiteGenPage
	 * r.enUS: refreshSiteUserGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereUtilisateurSiteObjetSuggere
	 * r.enUS: suggestSiteUserObjectSuggest
	 * r: texteUtilisateurSiteObjetTexte
	 * r.enUS: textSiteUserObjectText
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: 'objetTexte:'
	 * r.enUS: 'objectText:'
	 * r: '#suggereListUtilisateurSite'
	 * r.enUS: '#suggestListSiteUser'
	 * r: "suggereListUtilisateurSite"
	 * r.enUS: "suggestListSiteUser"
	**/
	public static void htmlSuggereUtilisateurSiteGenPage(MiseEnPage p, String id, ListeRecherche<UtilisateurSite> listeUtilisateurSite) {
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

			Integer rows1 = Optional.ofNullable(listeUtilisateurSite).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeUtilisateurSite).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeUtilisateurSite).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeUtilisateurSite).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), UtilisateurSiteGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), UtilisateurSiteGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerTousUtilisateurSiteGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ").a("onclick", "patchUtilisateurSiteVals([], {}, function() { ajouterLueur($('#rechargerTousUtilisateurSiteGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousUtilisateurSiteGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger tous les utilisateurs du site");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher utilisateurs du site : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher utilisateurs du site")
					.a("class", "suggereUtilisateurSite w3-input w3-border w3-bar-item ")
					.a("name", "suggereUtilisateurSite")
					.a("id", "suggereUtilisateurSite", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereUtilisateurSiteObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,objetTitre' } ], $('#suggereListUtilisateurSite", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/utilisateur?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeUtilisateurSite != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
					.a("onclick", "window.location.href = '/utilisateur?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListUtilisateurSite", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/utilisateur").a("class", "").f();
					p.e("i").a("class", "far fa-user-cog ").f().g("i");
					p.sx("voir tous les utilisateurs du site");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
