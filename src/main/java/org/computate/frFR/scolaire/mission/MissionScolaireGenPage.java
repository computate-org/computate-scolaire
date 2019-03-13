package org.computate.frFR.scolaire.mission;

import io.vertx.core.http.HttpServerRequest;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.time.ZonedDateTime;
import org.computate.frFR.scolaire.config.ConfigSite;
import java.time.LocalDateTime;
import java.io.IOException;
import org.computate.frFR.scolaire.page.MiseEnPage;
import org.computate.frFR.scolaire.recherche.ListeRecherche;
import org.computate.frFR.scolaire.couverture.Couverture;
import io.vertx.core.http.HttpServerResponse;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.String;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSite;
import org.computate.frFR.scolaire.requete.RequeteSite;


public class MissionScolaireGenPage extends MissionScolaireGenPageGen<MiseEnPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeMissionScolaire(Couverture<ListeRecherche<MissionScolaire>> c) {
	}

	protected void _missionScolaire(Couverture<MissionScolaire> c) {
		if(listeMissionScolaire.size() == 1)
			c.o(listeMissionScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(missionScolaire != null)
			c.o("une mission");
		else if(listeMissionScolaire.size() == 0)
			c.o("aucune mission trouvée");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(missionScolaire != null)
			c.o("une mission");
		else if(listeMissionScolaire.size() == 0)
			c.o("aucune mission trouvée");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("fort-awesome");
	}

	@Override public void initLoinMissionScolaireGenPage() {
		initMissionScolaireGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsMissionScolaireGenPage() {
		e("script").a("src", "/static/js/MissionScolairePage.js").f().g("script");
	}

	protected void _pageUriMissionScolaire(Couverture<String> c) {
			c.o("/mission");
	}

	@Override public void htmlScriptMissionScolaireGenPage() {
	}

	public void htmlFormPageMissionScolaire(MissionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("clé primaire").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strPk()).g("span");
				} g("div");
			} g("div");
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
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "archiveForm").a("style", "display: inline-block; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "archive")
						.a("id", "Page_archive")
						.a("value", "false")
					.fg();

					e("input")
						.a("type", "checkbox")
						.a("value", "true")
						.a("class", "setArchive")
						.a("name", "setArchive")
						.a("id", "Page_archive")
						.a("onchange", "patchMissionScolaire($('#MissionScolaireForm'), $('#archiveForm')); ")
						;
						if(o.getArchive() != null && o.getArchive())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_archive").a("class", "").f().sx("archivé").g("label");
				} g("form");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "supprimeForm").a("style", "display: inline-block; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "supprime")
						.a("id", "Page_supprime")
						.a("value", "false")
					.fg();

					e("input")
						.a("type", "checkbox")
						.a("value", "true")
						.a("class", "setSupprime")
						.a("name", "setSupprime")
						.a("id", "Page_supprime")
						.a("onchange", "patchMissionScolaire($('#MissionScolaireForm'), $('#supprimeForm')); ")
						;
						if(o.getSupprime() != null && o.getSupprime())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_supprime").a("class", "").f().sx("supprimé").g("label");
				} g("form");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "missionNomForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_missionNom").a("class", "").f().sx("nom de la mission").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "nom de la mission")
						.a("title", "Nom de la mission. ")
						.a("class", "setMissionNom w3-input w3-border ")
						.a("name", "setMissionNom")
						.a("id", "Page_missionNom")
						.a("onchange", "patchMissionScolaire($('#MissionScolaireForm'), $('#missionNomForm')); ")
						.a("value", o.strMissionNom())
					.fg();

				} g("form");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "ecoleNumeroTelephoneForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_ecoleNumeroTelephone").a("class", "").f().sx("description de la mission").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "description de la mission")
						.a("title", "Numéro de téléphone de l'école. ")
						.a("class", "setEcoleNumeroTelephone w3-input w3-border ")
						.a("name", "setEcoleNumeroTelephone")
						.a("id", "Page_ecoleNumeroTelephone")
						.a("onchange", "patchMissionScolaire($('#MissionScolaireForm'), $('#ecoleNumeroTelephoneForm')); ")
						.a("value", o.strEcoleNumeroTelephone())
					.fg();

				} g("form");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTMissionScolaire(MissionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("clé primaire").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strPk()).g("span");
				} g("div");
			} g("div");
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
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "archive")
					.a("id", "POST_archive")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurArchive")
					.a("name", "archive")
					.a("id", "POST_archive")
					;
					if(o.getArchive() != null && o.getArchive())
						a("checked", "checked");
				fg();

				e("label").a("for", "POST_archive").a("class", "").f().sx("archivé").g("label");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "supprime")
					.a("id", "POST_supprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurSupprime")
					.a("name", "supprime")
					.a("id", "POST_supprime")
					;
					if(o.getSupprime() != null && o.getSupprime())
						a("checked", "checked");
				fg();

				e("label").a("for", "POST_supprime").a("class", "").f().sx("supprimé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_missionNom").a("class", "").f().sx("nom de la mission").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "nom de la mission")
					.a("title", "Nom de la mission. ")
					.a("class", "valeurMissionNom w3-input w3-border ")
					.a("name", "missionNom")
					.a("id", "POST_missionNom")
					.a("value", o.strMissionNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_ecoleNumeroTelephone").a("class", "").f().sx("description de la mission").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "description de la mission")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("class", "valeurEcoleNumeroTelephone w3-input w3-border ")
					.a("name", "ecoleNumeroTelephone")
					.a("id", "POST_ecoleNumeroTelephone")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
		} g("div");
	}

	public void htmlFormPATCHMissionScolaire(MissionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("clé primaire").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strPk()).g("span");
				} g("div");
			} g("div");
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
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "archive")
					.a("id", "PATCH_archive")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "setArchive")
					.a("name", "setArchive")
					.a("id", "PATCH_archive")
					;
					if(o.getArchive() != null && o.getArchive())
						a("checked", "checked");
				fg();

				e("label").a("for", "PATCH_archive").a("class", "").f().sx("archivé").g("label");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "supprime")
					.a("id", "PATCH_supprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "setSupprime")
					.a("name", "setSupprime")
					.a("id", "PATCH_supprime")
					;
					if(o.getSupprime() != null && o.getSupprime())
						a("checked", "checked");
				fg();

				e("label").a("for", "PATCH_supprime").a("class", "").f().sx("supprimé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_missionNom").a("class", "").f().sx("nom de la mission").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "nom de la mission")
					.a("title", "Nom de la mission. ")
					.a("class", "setMissionNom w3-input w3-border ")
					.a("name", "setMissionNom")
					.a("id", "PATCH_missionNom")
					.a("value", o.strMissionNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_ecoleNumeroTelephone").a("class", "").f().sx("description de la mission").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "description de la mission")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("class", "setEcoleNumeroTelephone w3-input w3-border ")
					.a("name", "setEcoleNumeroTelephone")
					.a("id", "PATCH_ecoleNumeroTelephone")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
		} g("div");
	}

	public void htmlFormRechercheMissionScolaire(MissionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("clé primaire").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strPk()).g("span");
				} g("div");
			} g("div");
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
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "archive")
					.a("id", "Recherche_archive")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurArchive")
					.a("name", "archive")
					.a("id", "Recherche_archive")
					;
					if(o.getArchive() != null && o.getArchive())
						a("checked", "checked");
				fg();

				e("label").a("for", "Recherche_archive").a("class", "").f().sx("archivé").g("label");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "supprime")
					.a("id", "Recherche_supprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("value", "true")
					.a("class", "valeurSupprime")
					.a("name", "supprime")
					.a("id", "Recherche_supprime")
					;
					if(o.getSupprime() != null && o.getSupprime())
						a("checked", "checked");
				fg();

				e("label").a("for", "Recherche_supprime").a("class", "").f().sx("supprimé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_missionNom").a("class", "").f().sx("nom de la mission").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "nom de la mission")
					.a("title", "Nom de la mission. ")
					.a("class", "valeurMissionNom w3-input w3-border ")
					.a("name", "missionNom")
					.a("id", "Recherche_missionNom")
					.a("value", o.strMissionNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_ecoleNumeroTelephone").a("class", "").f().sx("description de la mission").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "description de la mission")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("class", "valeurEcoleNumeroTelephone w3-input w3-border ")
					.a("name", "ecoleNumeroTelephone")
					.a("id", "Recherche_ecoleNumeroTelephone")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
		} g("div");
	}

	@Override public void htmlBodyMissionScolaireGenPage() {

		if(listeMissionScolaire.size() == 0) {
			//aucune mission trouvée

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
				e("span").a("class", " ").f().sx("aucune mission trouvée").g("span");
			} g("h1");
		} else if(listeMissionScolaire.size() == 1) {
			// une mission
			MissionScolaire o = listeMissionScolaire.first();

			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
					e("span").a("class", " ").f().sx("une mission").g("span");
				} g("h1");
			}
			{ e("div").a("class", "w3-card w3-margin w3-padding w3-margin-top w3-show ").f();

				if(o.getPk() != null) {
					{ e("form").a("id", "MissionScolaireForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageMissionScolaire(o);
				}

				o.htmlBody();

			} g("div");
		} else {
			// plusiers missions

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
				e("span").a("class", " ").f().sx("missions").g("i");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("nom de la mission").g("th");
						e("th").f().sx("description de la mission").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					for(int i = 0; i < listeMissionScolaire.size(); i++) {
						MissionScolaire o = listeMissionScolaire.getList().get(i);
						String uri = o.getPageUri();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getMissionNom());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleNumeroTelephone());
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postMissionScolaireModale').show(); ")
			.f().sx("Créer une mission")
		.g("button");
		{ e("div").a("id", "postMissionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postMissionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer une mission").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					MissionScolaire o = new MissionScolaire();

					{ e("form").a("id", "postMissionScolaireForm").f();
						htmlFormPOSTMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "postMissionScolaire($('#postMissionScolaireForm')); ")
						.f().sx("Créer une mission")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchMissionScolaireModale').show(); ")
			.f().sx("Modifier des missions")
		.g("button");
		{ e("div").a("id", "patchMissionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchMissionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des missions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					MissionScolaire o = new MissionScolaire();

					{ e("form").a("id", "patchMissionScolaireFormFiltres").f();
						htmlFormRechercheMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "rechercheMissionScolaire($('#patchMissionScolaireFormFiltres')); ")
						.f().sx("Modifier des missions")
					.g("button");


					{ e("form").a("id", "patchMissionScolaireFormValeurs").f();
						htmlFormPATCHMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchMissionScolaire($('#patchMissionScolaireFormFiltres'), $('#patchMissionScolaireFormValeurs')); ")
						.f().sx("Modifier des missions")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#deleteMissionScolaireModale').show(); ")
			.f().sx("Supprimer des missions")
		.g("button");
		{ e("div").a("id", "deleteMissionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteMissionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des missions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					MissionScolaire o = new MissionScolaire();

					{ e("form").a("id", "deleteMissionScolaireForm").f();
						htmlFormPATCHMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "deleteMissionScolaire(); ")
						.f().sx("Supprimer des missions")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}
}
