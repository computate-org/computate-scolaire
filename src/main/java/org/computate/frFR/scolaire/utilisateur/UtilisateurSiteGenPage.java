package org.computate.frFR.scolaire.utilisateur;

import io.vertx.core.http.HttpServerRequest;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.time.ZonedDateTime;
import org.computate.frFR.scolaire.config.ConfigSite;
import java.time.LocalDateTime;
import java.io.IOException;
import org.computate.frFR.scolaire.page.MiseEnPage;
import org.computate.frFR.scolaire.recherche.ListeRecherche;
import java.util.List;
import org.computate.frFR.scolaire.couverture.Couverture;
import java.lang.Long;
import io.vertx.core.http.HttpServerResponse;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.Boolean;
import java.lang.String;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSite;
import org.computate.frFR.scolaire.requete.RequeteSite;


public class UtilisateurSiteGenPage extends UtilisateurSiteGenPageGen<MiseEnPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeUtilisateurSite(Couverture<ListeRecherche<UtilisateurSite>> c) {
	}

	protected void _utilisateurSite(Couverture<UtilisateurSite> c) {
		if(listeUtilisateurSite.size() == 1)
			c.o(listeUtilisateurSite.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(utilisateurSite != null)
			c.o("un utilisateur");
		else if(listeUtilisateurSite.size() == 0)
			c.o("aucun utilisateur trouvé");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(utilisateurSite != null)
			c.o("un utilisateur");
		else if(listeUtilisateurSite.size() == 0)
			c.o("aucun utilisateur trouvé");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("user");
	}

	@Override public void initLoinUtilisateurSiteGenPage() {
		initUtilisateurSiteGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsUtilisateurSiteGenPage() {
		e("script").a("src", "/static/js/UtilisateurSitePage.js").f().g("script");
	}

	protected void _pageUriUtilisateurSite(Couverture<String> c) {
			c.o("/utilisateur");
	}

	@Override public void htmlScriptUtilisateurSiteGenPage() {
	}

	public void htmlFormPageUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "voirArchiveForm").a("style", "display: inline-block; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "voirArchive")
						.a("id", "Page_voirArchive")
						.a("value", "false")
					.fg();

					e("input")
						.a("type", "checkbox")
						.a("value", "true")
						.a("class", "setVoirArchive")
						.a("name", "setVoirArchive")
						.a("id", "Page_voirArchive")
						.a("onchange", "patchUtilisateurSite($('#UtilisateurSiteForm'), $('#voirArchiveForm')); ")
						;
						if(o.getVoirArchive() != null && o.getVoirArchive())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_voirArchive").a("class", "").f().sx("voir archivé").g("label");
				} g("form");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "voirSupprimeForm").a("style", "display: inline-block; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "voirSupprime")
						.a("id", "Page_voirSupprime")
						.a("value", "false")
					.fg();

					e("input")
						.a("type", "checkbox")
						.a("value", "true")
						.a("class", "setVoirSupprime")
						.a("name", "setVoirSupprime")
						.a("id", "Page_voirSupprime")
						.a("onchange", "patchUtilisateurSite($('#UtilisateurSiteForm'), $('#voirSupprimeForm')); ")
						;
						if(o.getVoirSupprime() != null && o.getVoirSupprime())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
				} g("form");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "voirArchive")
					.a("id", "POST_voirArchive")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurVoirArchive")
					.a("name", "voirArchive")
					.a("id", "POST_voirArchive")
					;
					if(o.getVoirArchive() != null && o.getVoirArchive())
						a("checked", "checked");
				fg();

				e("label").a("for", "POST_voirArchive").a("class", "").f().sx("voir archivé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "voirSupprime")
					.a("id", "POST_voirSupprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurVoirSupprime")
					.a("name", "voirSupprime")
					.a("id", "POST_voirSupprime")
					;
					if(o.getVoirSupprime() != null && o.getVoirSupprime())
						a("checked", "checked");
				fg();

				e("label").a("for", "POST_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "voirArchive")
					.a("id", "PATCH_voirArchive")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "setVoirArchive")
					.a("name", "setVoirArchive")
					.a("id", "PATCH_voirArchive")
					;
					if(o.getVoirArchive() != null && o.getVoirArchive())
						a("checked", "checked");
				fg();

				e("label").a("for", "PATCH_voirArchive").a("class", "").f().sx("voir archivé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "voirSupprime")
					.a("id", "PATCH_voirSupprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "setVoirSupprime")
					.a("name", "setVoirSupprime")
					.a("id", "PATCH_voirSupprime")
					;
					if(o.getVoirSupprime() != null && o.getVoirSupprime())
						a("checked", "checked");
				fg();

				e("label").a("for", "PATCH_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "voirArchive")
					.a("id", "Recherche_voirArchive")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurVoirArchive")
					.a("name", "voirArchive")
					.a("id", "Recherche_voirArchive")
					;
					if(o.getVoirArchive() != null && o.getVoirArchive())
						a("checked", "checked");
				fg();

				e("label").a("for", "Recherche_voirArchive").a("class", "").f().sx("voir archivé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "voirSupprime")
					.a("id", "Recherche_voirSupprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurVoirSupprime")
					.a("name", "voirSupprime")
					.a("id", "Recherche_voirSupprime")
					;
					if(o.getVoirSupprime() != null && o.getVoirSupprime())
						a("checked", "checked");
				fg();

				e("label").a("for", "Recherche_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyUtilisateurSiteGenPage() {

		if(listeUtilisateurSite.size() == 0) {
			//aucun utilisateur trouvé

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
				e("span").a("class", " ").f().sx("aucun utilisateur trouvé").g("span");
			} g("h1");
		} else if(listeUtilisateurSite.size() == 1) {
			// un utilisateur
			UtilisateurSite o = listeUtilisateurSite.first();

			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
					e("span").a("class", " ").f().sx("un utilisateur").g("span");
				} g("h1");
			}
			{ e("div").a("class", "w3-card w3-margin w3-padding w3-margin-top w3-show ").f();

				if(o.getPk() != null) {
					{ e("form").a("id", "UtilisateurSiteForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageUtilisateurSite(o);
				}

				o.htmlBody();

			} g("div");
		} else {
			// plusiers utilisateurs

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
				e("span").a("class", " ").f().sx("utilisateurs").g("i");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					for(int i = 0; i < listeUtilisateurSite.size(); i++) {
						UtilisateurSite o = listeUtilisateurSite.getList().get(i);
						String uri = "/utilisateur/" + o.getPk();
						{ e("tr").f();
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-deep-purple ")
			.a("onclick", "$('#patchUtilisateurSiteModale').show(); ")
			.f().sx("Modifier des utilisateurs")
		.g("button");
		{ e("div").a("id", "patchUtilisateurSiteModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-deep-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchUtilisateurSiteModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des utilisateurs").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					UtilisateurSite o = new UtilisateurSite();

					{ e("form").a("id", "patchUtilisateurSiteFormFiltres").f();
						htmlFormRechercheUtilisateurSite(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-deep-purple ")
						.a("onclick", "rechercheUtilisateurSite($('#patchUtilisateurSiteFormFiltres')); ")
						.f().sx("Modifier des utilisateurs")
					.g("button");


					{ e("form").a("id", "patchUtilisateurSiteFormValeurs").f();
						htmlFormPATCHUtilisateurSite(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-deep-purple ")
						.a("onclick", "patchUtilisateurSite($('#patchUtilisateurSiteFormFiltres'), $('#patchUtilisateurSiteFormValeurs')); ")
						.f().sx("Modifier des utilisateurs")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}
}
