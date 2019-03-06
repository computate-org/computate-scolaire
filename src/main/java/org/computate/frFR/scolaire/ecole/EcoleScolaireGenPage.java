package org.computate.frFR.scolaire.ecole;

import io.vertx.core.http.HttpServerRequest;
import java.lang.Double;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.time.ZonedDateTime;
import org.computate.frFR.scolaire.config.ConfigSite;
import java.time.LocalDateTime;
import java.io.IOException;
import java.lang.Integer;
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


public class EcoleScolaireGenPage extends EcoleScolaireGenPageGen<MiseEnPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeEcoleScolaire(Couverture<ListeRecherche<EcoleScolaire>> c) {
	}

	protected void _ecoleScolaire(Couverture<EcoleScolaire> c) {
		if(listeEcoleScolaire.size() == 1)
			c.o(listeEcoleScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(ecoleScolaire != null)
			c.o("une école");
		else if(listeEcoleScolaire.size() == 0)
			c.o("aucune école trouvée");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(ecoleScolaire != null)
			c.o("une école");
		else if(listeEcoleScolaire.size() == 0)
			c.o("aucune école trouvée");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("fort-awesome");
	}

	@Override public void initLoinEcoleScolaireGenPage() {
		initEcoleScolaireGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsEcoleScolaireGenPage() {
		e("script").a("src", "/static/js/EcoleScolairePage.js").f().g("script");
	}

	protected void _pageUriEcoleScolaire(Couverture<String> c) {
			c.o("/ecole");
	}

	@Override public void htmlScriptEcoleScolaireGenPage() {
	}

	public void htmlFormPOSTEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Nom de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleNom")
					.a("name", "ecoleNom")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Nom de l'école")
					.a("title", "Nom de l'école. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Numéro de téléphone").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleNumeroTelephone")
					.a("name", "ecoleNumeroTelephone")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Numéro de téléphone")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Addresse").g("label");

				e("textarea")
					.a("class", "valeurEcoleAddresse")
					.a("name", "ecoleAddresse")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Addresse")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.f().sx(o.strEcoleAddresse()).g("textarea");

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Administrateur de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleAdministrateurNom")
					.a("name", "ecoleAdministrateurNom")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Administrateur de l'école")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleAdministrateurNom())
				.fg();

			} g("div");
		} g("div");
	}

	public void htmlFormPATCHEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Nom de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleNom")
					.a("name", "ecoleNom")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Nom de l'école")
					.a("title", "Nom de l'école. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Numéro de téléphone").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleNumeroTelephone")
					.a("name", "ecoleNumeroTelephone")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Numéro de téléphone")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Addresse").g("label");

				e("textarea")
					.a("class", "valeurEcoleAddresse")
					.a("name", "ecoleAddresse")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Addresse")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.f().sx(o.strEcoleAddresse()).g("textarea");

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Administrateur de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleAdministrateurNom")
					.a("name", "ecoleAdministrateurNom")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Administrateur de l'école")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleAdministrateurNom())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Clé").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleCle")
					.a("name", "ecoleCle")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Clé")
					.a("title", "La clé primaire de l'école dans la base de données. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleCle())
				.fg();

			} g("div");
		} g("div");
	}

	public void htmlFormRechercheEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Nom de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleNom")
					.a("name", "ecoleNom")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Nom de l'école")
					.a("title", "Nom de l'école. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Numéro de téléphone").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleNumeroTelephone")
					.a("name", "ecoleNumeroTelephone")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Numéro de téléphone")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Addresse").g("label");

				e("textarea")
					.a("class", "valeurEcoleAddresse")
					.a("name", "ecoleAddresse")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Addresse")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.f().sx(o.strEcoleAddresse()).g("textarea");

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Administrateur de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleAdministrateurNom")
					.a("name", "ecoleAdministrateurNom")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Administrateur de l'école")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleAdministrateurNom())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("Clé").g("label");

				e("input")
					.a("type", "text")
					.a("class", "valeurEcoleCle")
					.a("name", "ecoleCle")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "Clé")
					.a("title", "La clé primaire de l'école dans la base de données. ")
					.a("onchange", "envoyer(); ")
					.a("value", o.strEcoleCle())
				.fg();

			} g("div");
		} g("div");
	}

	@Override public void htmlBodyEcoleScolaireGenPage() {

		if(listeEcoleScolaire.size() == 0) {
			//aucune école trouvée

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
				e("span").a("class", " ").f().sx("aucune école trouvée").g("span");
			} g("h1");
		} else if(listeEcoleScolaire.size() == 1) {
			// une école
			EcoleScolaire o = listeEcoleScolaire.first();

			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
					e("span").a("class", " ").f().sx("une école").g("span");
				} g("h1");
			}
			{ e("div").a("class", "w3-card w3-margin w3-padding w3-margin-top w3-show ").f();

				if(o.getPk() != null)
					htmlFormPATCHEcoleScolaire(o);

				o.htmlBody();

			} g("div");
		} else {
			// plusiers écoles

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " w3-margin-right-4 ").f().g("i");
				e("span").a("class", " ").f().sx("écoles").g("i");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("Clé").g("th");
						e("th").f().sx("Nom de l'école").g("th");
						e("th").f().sx("Numéro de téléphone").g("th");
						e("th").f().sx("Addresse").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					for(int i = 0; i < listeEcoleScolaire.size(); i++) {
						EcoleScolaire o = listeEcoleScolaire.getList().get(i);
						String uri = o.getPageUri();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleCle());
								} g("a");
							} g("td");
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
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postEcoleScolaireModale').show(); ")
			.f().sx("Créer une école")
		.g("button");
		{ e("div").a("id", "postEcoleScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postEcoleScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer une école").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EcoleScolaire o = new EcoleScolaire();

					{ e("form").a("id", "postEcoleScolaireForm").f();
						htmlFormPOSTEcoleScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "postEcoleScolaire($('#postEcoleScolaireForm')); ")
						.f().sx("Créer une école")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchEcoleScolaireModale').show(); ")
			.f().sx("Modifier des écoles")
		.g("button");
		{ e("div").a("id", "patchEcoleScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEcoleScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des écoles").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EcoleScolaire o = new EcoleScolaire();

					{ e("form").a("id", "patchEcoleScolaireFormFiltres").f();
						htmlFormRechercheEcoleScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "rechercheEcoleScolaire($('#patchEcoleScolaireFormFiltres')); ")
						.f().sx("Modifier des écoles")
					.g("button");


					{ e("form").a("id", "patchEcoleScolaireFormValeurs").f();
						htmlFormPATCHEcoleScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchEcoleScolaire($('#patchEcoleScolaireFormFiltres'), $('#patchEcoleScolaireFormValeurs')); ")
						.f().sx("Modifier des écoles")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#deleteEcoleScolaireModale').show(); ")
			.f().sx("Supprimer des écoles")
		.g("button");
		{ e("div").a("id", "deleteEcoleScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteEcoleScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des écoles").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EcoleScolaire o = new EcoleScolaire();

					{ e("form").a("id", "deleteEcoleScolaireForm").f();
						htmlFormPATCHEcoleScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "deleteEcoleScolaire(); ")
						.f().sx("Supprimer des écoles")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}
}
