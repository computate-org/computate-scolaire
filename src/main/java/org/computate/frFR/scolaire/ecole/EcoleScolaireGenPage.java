package org.computate.frFR.scolaire.ecole;

import io.vertx.core.http.HttpServerRequest;
import java.lang.Double;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.time.ZonedDateTime;
import org.computate.frFR.scolaire.config.ConfigSite;
import java.time.LocalDateTime;
import java.io.IOException;
import org.computate.frFR.scolaire.chaine.Chaine;
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

	public void htmlFormEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("clé primaire").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurPk")
					.a("name", "pk")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "clé primaire")
					.a("title", "La clé primaire dans la base de données. ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "supprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("class", "valeurSupprime")
					.a("name", "supprime")
					.a("value", "true")
					.a("onchange", "envoyer(); ");
					if(o.getSupprime() != null && o.getSupprime())
						a("checked", "checked");
				fg();

				e("label").a("class", "").f().sx("Supprimé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				LocalDateTime val = o.getCree();

				e("label").a("class", "").f().sx("Crée").g("label");
				e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker ")
					.a("placeholder", "DD/MM/YYYY")
					.a("data-timeformat", "DD/MM/YYYY")
					.a("onclick", "enleverLueur($(this)); ")
					.a("title", "La date et l'heure créées.  (DD/MM/YYYY)")
					.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("fr-FR")).format(val))
					.a("onchange", "var t = moment(this.value, 'DD/MM/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); $(this).next().val(s); $(this).next().trigger('change'); } ")
					.fg();
				e("input")
					.a("type", "hidden")
					.a("class", "valeurCree")
					.a("name", "cree")
					.a("onchange", "envoyer(); ")
					.a("value", o.strCree())
				.fg();
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				LocalDateTime val = o.getModifie();

				e("label").a("class", "").f().sx("Modifié").g("label");
				e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker ")
					.a("placeholder", "DD/MM/YYYY")
					.a("data-timeformat", "DD/MM/YYYY")
					.a("onclick", "enleverLueur($(this)); ")
					.a("title", "La date et l'heure modifiéés.  (DD/MM/YYYY)")
					.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("fr-FR")).format(val))
					.a("onchange", "var t = moment(this.value, 'DD/MM/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); $(this).next().val(s); $(this).next().trigger('change'); } ")
					.fg();
				e("input")
					.a("type", "hidden")
					.a("class", "valeurModifie")
					.a("name", "modifie")
					.a("onchange", "envoyer(); ")
					.a("value", o.strModifie())
				.fg();
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurEcoleCle")
					.a("name", "ecoleCle")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurEnfantCles")
					.a("name", "enfantCles")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurBlocCles")
					.a("name", "blocCles")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurGroupeAgeCles")
					.a("name", "groupeAgeCles")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurSessionCles")
					.a("name", "sessionCles")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurSaisonCles")
					.a("name", "saisonCles")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurAnneeCles")
					.a("name", "anneeCles")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "supprime")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("class", "valeurSupprime")
					.a("name", "supprime")
					.a("value", "true")
					.a("onchange", "envoyer(); ");
					if(o.getSupprime() != null && o.getSupprime())
						a("checked", "checked");
				fg();

				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("input")
					.a("type", "hidden")
					.a("name", "archive")
					.a("value", "false")
				.fg();

				e("input")
					.a("type", "checkbox")
					.a("class", "valeurArchive")
					.a("name", "archive")
					.a("value", "true")
					.a("onchange", "envoyer(); ");
					if(o.getArchive() != null && o.getArchive())
						a("checked", "checked");
				fg();

				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurScolaireTri")
					.a("name", "scolaireTri")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurEcoleTri")
					.a("name", "ecoleTri")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurObjetSuggerePoids")
					.a("name", "objetSuggerePoids")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurObjetSuggere")
					.a("name", "objetSuggere")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("class", "").f().sx("NomAffichage.enUS: ").g("label");

				e("input")
							.a("type", "text")
					.a("class", "valeurEcoleNomCourt")
					.a("name", "ecoleNomCourt")
					.a("class", "w3-input w3-border ")
					.a("placeholder", "NomAffichage.enUS: ")
					.a("title", "Description.enUS: ")
					.a("onchange", "envoyer(); ")
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
					htmlFormEcoleScolaire(o);

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
						e("th").f().sx("clé primaire").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					for(int i = 0; i < listeEcoleScolaire.size(); i++) {
						EcoleScolaire o = listeEcoleScolaire.getList().get(i);
						{ e("tr").f();
							{ e("td").f();
								sx(o.getPk());
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

					{ e("form").a("id", "postEcoleScolaireFormulaire").f();
						htmlFormEcoleScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "postEcoleScolaire($('#postEcoleScolaireFormulaire')); ")
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

					{ e("form").a("id", "patchEcoleScolaireFormulaire").f();
						htmlFormEcoleScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchEcoleScolaire(); ")
						.f().sx("Modifier des écoles")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeEcoleScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
				.a("onclick", "$('#putEcoleScolaireModale').show(); ")
				.f().sx("Remplacer l'école")
			.g("button");
			{ e("div").a("id", "putEcoleScolaireModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putEcoleScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "").f().sx("Remplacer l'école").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						EcoleScolaire o = new EcoleScolaire();

						{ e("form").a("id", "putEcoleScolaireFormulaire").f();
							htmlFormEcoleScolaire(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-pink ")
							.a("onclick", "putEcoleScolaire(", o.getPk(), ", $('#putEcoleScolaireFormulaire')); ")
							.f().sx("Remplacer l'école")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}

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

					{ e("form").a("id", "deleteEcoleScolaireFormulaire").f();
						htmlFormEcoleScolaire(o);
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
