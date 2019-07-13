package org.computate.scolaire.frFR.ecole;

import java.lang.Long;
import java.util.List;
import java.lang.Integer;
import java.lang.String;
import java.lang.Double;
import org.computate.scolaire.frFR.cluster.ClusterFrFRPage;
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


/**
 * Traduire: false
 **/
public class EcoleFrFRGenPage extends EcoleFrFRGenPageGen<ClusterFrFRPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeEcole(Couverture<ListeRecherche<Ecole>> c) {
	}

	protected void _ecole(Couverture<Ecole> c) {
		if(listeEcole != null && listeEcole.size() == 1)
			c.o(listeEcole.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(ecole != null)
			c.o("une école");
		else if(listeEcole == null || listeEcole.size() == 0)
			c.o("aucune école trouvée");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(ecole != null)
			c.o("");
		else if(listeEcole == null || listeEcole.size() == 0)
			c.o("aucune école trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/frFR/ecole");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/frFR/ecole-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("fort-awesome");
	}

	@Override public void initLoinEcoleFrFRGenPage() {
		initEcoleFrFRGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsEcoleFrFRGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/EcoleFrFRPage.js").f().g("script");
	}

	protected void _pageUriEcole(Couverture<String> c) {
			c.o("/frFR/ecole");
	}

	@Override public void htmlScriptEcoleFrFRGenPage() {
	}

	public void htmlFormPageEcole(Ecole o) {
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
					e("label").a("class", "").f().sx("nom de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("numéro de téléphone").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("administrateur de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAdministrateurNom()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("addresse").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAddresse()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTEcole(Ecole o) {
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
					e("label").a("class", "").f().sx("nom de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("numéro de téléphone").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("administrateur de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAdministrateurNom()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("addresse").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAddresse()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHEcole(Ecole o) {
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
					e("label").a("class", "").f().sx("nom de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("numéro de téléphone").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("administrateur de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAdministrateurNom()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("addresse").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAddresse()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheEcole(Ecole o) {
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
					e("label").a("class", "").f().sx("nom de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNom()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("numéro de téléphone").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleNumeroTelephone()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("administrateur de l'école").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAdministrateurNom()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("addresse").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strEcoleAddresse()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyEcoleFrFRGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeEcole == null || listeEcole.size() == 0) {
			// contexteAucunNomTrouve : aucune école trouvée

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("aucune école trouvée").g("span");
			} g("h1");
		} else if(listeEcole != null && listeEcole.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			// contexteUnNom : une école
			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("une école").g("span");
				} g("h1");
				Ecole o = listeEcole.get(0);
			}
		} else {
			// contexteNomPluriel : plusiers écoles

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("écoles").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("nom de l'école").g("th");
						e("th").f().sx("numéro de téléphone").g("th");
						e("th").f().sx("addresse").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeEcole.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeEcole.size(); i++) {
						Ecole o = listeEcole.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = o.getPageUri();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleNom());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleNumeroTelephone());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleAddresse());
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeEcole != null && listeEcole.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			Ecole o = listeEcole.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "/api/ecole").a("id", "EcoleForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageEcole(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");
		}
		htmlBodyFormsEcoleFrFRGenPage();
	}

	public void htmlBodyFormsEcoleFrFRGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchEcoleModale').show(); ")
			.f().sx("Modifier des écoles")
		.g("button");
		{ e("div").a("id", "patchEcoleModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEcoleModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des écoles").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Ecole o = new Ecole();

					// FormFiltres PATCH
					{ e("form").a("action", "/api/ecole").a("id", "patchEcoleFormFiltres").f();
						htmlFormRechercheEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "rechercheEcole($('#patchEcoleFormFiltres')); ")
						.f().sx("Rechercher des une école")
					.g("button");


					// FormValeurs PATCH
					{ e("form").a("action", "/api/ecole").a("id", "patchEcoleFormValeurs").f();
						htmlFormPATCHEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchEcole($('#patchEcoleFormFiltres'), $('#patchEcoleFormValeurs')); ")
						.f().sx("Modifier des écoles")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postEcoleModale').show(); ")
			.f().sx("Créer une école")
		.g("button");
		{ e("div").a("id", "postEcoleModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postEcoleModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer une école").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Ecole o = new Ecole();

					// Form POST
					{ e("form").a("action", "/api/ecole").a("id", "postEcoleForm").f();
						htmlFormPOSTEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "postEcole($('#postEcoleForm')); ")
						.f().sx("Créer une école")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#deleteEcoleModale').show(); ")
			.f().sx("Supprimer des écoles")
		.g("button");
		{ e("div").a("id", "deleteEcoleModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteEcoleModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des écoles").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Ecole o = new Ecole();

					// Form DELETE
					{ e("form").a("action", "/api/ecole").a("id", "deleteEcoleForm").f();
						htmlFormPATCHEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "deleteEcole(); ")
						.f().sx("Supprimer des écoles")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
