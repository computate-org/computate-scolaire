package org.computate.scolaire.frFR.mission;

import java.lang.String;
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
public class MissionScolaireGenPage extends MissionScolaireGenPageGen<MiseEnPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeMissionScolaire(Couverture<ListeRecherche<MissionScolaire>> c) {
	}

	protected void _missionScolaire(Couverture<MissionScolaire> c) {
		if(listeMissionScolaire != null && listeMissionScolaire.size() == 1)
			c.o(listeMissionScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(missionScolaire != null)
			c.o("");
		else if(listeMissionScolaire == null || listeMissionScolaire.size() == 0)
			c.o("");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(missionScolaire != null)
			c.o("");
		else if(listeMissionScolaire == null || listeMissionScolaire.size() == 0)
			c.o("");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/frFR/mission");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/frFR/mission-999.png");
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
		e("script").a("src", statiqueUrlBase, "/js/MissionScolairePage.js").f().g("script");
	}

	protected void _pageUriMissionScolaire(Couverture<String> c) {
			c.o("/frFR/mission");
	}

	@Override public void htmlScriptMissionScolaireGenPage() {
	}

	public void htmlFormPageMissionScolaire(MissionScolaire o) {
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
					e("label").a("class", "").f().sx("nom de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strMissionNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("description de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTMissionScolaire(MissionScolaire o) {
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
					e("label").a("class", "").f().sx("nom de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strMissionNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("description de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHMissionScolaire(MissionScolaire o) {
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
					e("label").a("class", "").f().sx("nom de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strMissionNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("description de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheMissionScolaire(MissionScolaire o) {
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
					e("label").a("class", "").f().sx("nom de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strMissionNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("description de la mission").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyMissionScolaireGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeMissionScolaire == null || listeMissionScolaire.size() == 0) {
			// contexteAucunNomTrouve : 

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("").g("span");
			} g("h1");
		} else if(listeMissionScolaire != null && listeMissionScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			// contexteUnNom : 
			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("").g("span");
				} g("h1");
				MissionScolaire o = listeMissionScolaire.get(0);
			}
		} else {
			// contexteNomPluriel : plusiers 

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("nom de la mission").g("th");
						e("th").f().sx("description de la mission").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeMissionScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeMissionScolaire.size(); i++) {
						MissionScolaire o = listeMissionScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
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

		if(listeMissionScolaire != null && listeMissionScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			MissionScolaire o = listeMissionScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "/api/mission").a("id", "MissionScolaireForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageMissionScolaire(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");
		}
		htmlBodyFormsMissionScolaireGenPage();
	}

	public void htmlBodyFormsMissionScolaireGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postMissionScolaireModale').show(); ")
			.f().sx("Créer null")
		.g("button");
		{ e("div").a("id", "postMissionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postMissionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					MissionScolaire o = new MissionScolaire();

					// Form POST
					{ e("form").a("action", "/api/mission").a("id", "postMissionScolaireForm").f();
						htmlFormPOSTMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "postMissionScolaire($('#postMissionScolaireForm')); ")
						.f().sx("Créer null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchMissionScolaireModale').show(); ")
			.f().sx("Modifier des null")
		.g("button");
		{ e("div").a("id", "patchMissionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchMissionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					MissionScolaire o = new MissionScolaire();

					// FormFiltres PATCH
					{ e("form").a("action", "/api/mission").a("id", "patchMissionScolaireFormFiltres").f();
						htmlFormRechercheMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "rechercheMissionScolaire($('#patchMissionScolaireFormFiltres')); ")
						.f().sx("Rechercher des null")
					.g("button");


					// FormValeurs PATCH
					{ e("form").a("action", "/api/mission").a("id", "patchMissionScolaireFormValeurs").f();
						htmlFormPATCHMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchMissionScolaire($('#patchMissionScolaireFormFiltres'), $('#patchMissionScolaireFormValeurs')); ")
						.f().sx("Modifier des null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#deleteMissionScolaireModale').show(); ")
			.f().sx("Supprimer des null")
		.g("button");
		{ e("div").a("id", "deleteMissionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteMissionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					MissionScolaire o = new MissionScolaire();

					// Form DELETE
					{ e("form").a("action", "/api/mission").a("id", "deleteMissionScolaireForm").f();
						htmlFormPATCHMissionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "deleteMissionScolaire(); ")
						.f().sx("Supprimer des null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
