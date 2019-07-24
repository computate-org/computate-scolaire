package org.computate.scolaire.frFR.utilisateur;

import java.lang.String;
import java.lang.Boolean;
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


/**
 * Traduire: false
 **/
public class UtilisateurSiteGenPage extends UtilisateurSiteGenPageGen<MiseEnPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeUtilisateurSite(Couverture<ListeRecherche<UtilisateurSite>> c) {
	}

	protected void _utilisateurSite(Couverture<UtilisateurSite> c) {
		if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1)
			c.o(listeUtilisateurSite.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(utilisateurSite != null)
			c.o("");
		else if(listeUtilisateurSite == null || listeUtilisateurSite.size() == 0)
			c.o("");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(utilisateurSite != null)
			c.o("");
		else if(listeUtilisateurSite == null || listeUtilisateurSite.size() == 0)
			c.o("");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/utilisateur");
	}

	@Override protected void _pageUriEnUS(Couverture<String> c) {
		c.o("/utilisateur");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/utilisateur-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteNom(Couverture<String> c) {
			c.o("user");
	}

	@Override public void initLoinUtilisateurSiteGenPage() {
		initUtilisateurSiteGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsUtilisateurSiteGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/UtilisateurSitePage.js").f().g("script");
	}

	protected void _pageUriUtilisateurSite(Couverture<String> c) {
			c.o("/utilisateur");
	}

	@Override public void htmlScriptUtilisateurSiteGenPage() {
	}

	public void htmlFormPageUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("crée").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCree()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modifié").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModifie()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("utilisateur ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUtilisateurId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir archivé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirArchive()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir supprimé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirSupprime()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("crée").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCree()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modifié").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModifie()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("utilisateur ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUtilisateurId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir archivé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirArchive()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir supprimé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirSupprime()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("crée").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCree()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modifié").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModifie()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("utilisateur ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUtilisateurId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir archivé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirArchive()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir supprimé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirSupprime()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("crée").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCree()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modifié").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModifie()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("utilisateur ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUtilisateurId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir archivé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirArchive()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("voir supprimé").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strVoirSupprime()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyUtilisateurSiteGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeUtilisateurSite == null || listeUtilisateurSite.size() == 0) {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("").g("span");
			} g("h1");
		} else if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("").g("span");
				} g("h1");
				UtilisateurSite o = listeUtilisateurSite.get(0);
			}
		} else {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeUtilisateurSite.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeUtilisateurSite.size(); i++) {
						UtilisateurSite o = listeUtilisateurSite.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/utilisateur/" + o.getPk();
						{ e("tr").f();
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeUtilisateurSite != null && listeUtilisateurSite.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			UtilisateurSite o = listeUtilisateurSite.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "/api/utilisateur").a("id", "UtilisateurSiteForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageUtilisateurSite(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");
		}
		htmlBodyFormsUtilisateurSiteGenPage();
	}

	public void htmlBodyFormsUtilisateurSiteGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-deep-purple ")
			.a("onclick", "$('#patchUtilisateurSiteModale').show(); ")
			.f().sx("Modifier des null")
		.g("button");
		{ e("div").a("id", "patchUtilisateurSiteModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-deep-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchUtilisateurSiteModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					UtilisateurSite o = new UtilisateurSite();

					// FormulaireFiltres PATCH
					{ e("form").a("action", "/api/utilisateur").a("id", "patchUtilisateurSiteFormulaireFiltres").f();
						htmlFormRechercheUtilisateurSite(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-deep-purple ")
						.a("onclick", "rechercheUtilisateurSite($('#patchUtilisateurSiteFormulaireFiltres')); ")
						.f().sx("Rechercher des null")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "/api/utilisateur").a("id", "patchUtilisateurSiteFormulaireValeurs").f();
						htmlFormPATCHUtilisateurSite(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-deep-purple ")
						.a("onclick", "patchUtilisateurSite($('#patchUtilisateurSiteFormulaireFiltres'), $('#patchUtilisateurSiteFormulaireValeurs')); ")
						.f().sx("Modifier des null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
