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

	public void htmlFormPageEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
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
						.a("onchange", "patchEcoleScolaire($('#EcoleScolaireForm'), $('#archiveForm')); ")
						;
						if(o.getArchive() != null && o.getArchive())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_archive").a("class", "").f().sx("Archivé").g("label");
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
						.a("onchange", "patchEcoleScolaire($('#EcoleScolaireForm'), $('#supprimeForm')); ")
						;
						if(o.getSupprime() != null && o.getSupprime())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_supprime").a("class", "").f().sx("Supprimé").g("label");
				} g("form");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "ecoleNomForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_ecoleNom").a("class", "").f().sx("Nom de l'école").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "Nom de l'école")
						.a("title", "Nom de l'école. ")
						.a("class", "setEcoleNom w3-input w3-border ")
						.a("name", "setEcoleNom")
						.a("id", "Page_ecoleNom")
						.a("onchange", "patchEcoleScolaire($('#EcoleScolaireForm'), $('#ecoleNomForm')); ")
						.a("value", o.strEcoleNom())
					.fg();

				} g("form");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "ecoleNumeroTelephoneForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_ecoleNumeroTelephone").a("class", "").f().sx("Numéro de téléphone").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "Numéro de téléphone")
						.a("title", "Numéro de téléphone de l'école. ")
						.a("class", "setEcoleNumeroTelephone w3-input w3-border ")
						.a("name", "setEcoleNumeroTelephone")
						.a("id", "Page_ecoleNumeroTelephone")
						.a("onchange", "patchEcoleScolaire($('#EcoleScolaireForm'), $('#ecoleNumeroTelephoneForm')); ")
						.a("value", o.strEcoleNumeroTelephone())
					.fg();

				} g("form");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "ecoleAdministrateurNomForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_ecoleAdministrateurNom").a("class", "").f().sx("Administrateur de l'école").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "Administrateur de l'école")
						.a("title", "Description.enUS: ")
						.a("class", "setEcoleAdministrateurNom w3-input w3-border ")
						.a("name", "setEcoleAdministrateurNom")
						.a("id", "Page_ecoleAdministrateurNom")
						.a("onchange", "patchEcoleScolaire($('#EcoleScolaireForm'), $('#ecoleAdministrateurNomForm')); ")
						.a("value", o.strEcoleAdministrateurNom())
					.fg();

				} g("form");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "ecoleAddresseForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_ecoleAddresse").a("class", "").f().sx("Addresse").g("label");

					e("textarea")
						.a("placeholder", "Addresse")
						.a("title", "Description.enUS: ")
						.a("class", "setEcoleAddresse w3-input w3-border ")
						.a("name", "setEcoleAddresse")
						.a("id", "Page_ecoleAddresse")
						.a("onchange", "patchEcoleScolaire($('#EcoleScolaireForm'), $('#ecoleAddresseForm')); ")
					.f().sx(o.strEcoleAddresse()).g("textarea");

				} g("form");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
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

				e("label").a("for", "POST_archive").a("class", "").f().sx("Archivé").g("label");
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

				e("label").a("for", "POST_supprime").a("class", "").f().sx("Supprimé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_ecoleNom").a("class", "").f().sx("Nom de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Nom de l'école")
					.a("title", "Nom de l'école. ")
					.a("class", "valeurEcoleNom w3-input w3-border ")
					.a("name", "ecoleNom")
					.a("id", "POST_ecoleNom")
					.a("value", o.strEcoleNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_ecoleNumeroTelephone").a("class", "").f().sx("Numéro de téléphone").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Numéro de téléphone")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("class", "valeurEcoleNumeroTelephone w3-input w3-border ")
					.a("name", "ecoleNumeroTelephone")
					.a("id", "POST_ecoleNumeroTelephone")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_ecoleAdministrateurNom").a("class", "").f().sx("Administrateur de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Administrateur de l'école")
					.a("title", "Description.enUS: ")
					.a("class", "valeurEcoleAdministrateurNom w3-input w3-border ")
					.a("name", "ecoleAdministrateurNom")
					.a("id", "POST_ecoleAdministrateurNom")
					.a("value", o.strEcoleAdministrateurNom())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_ecoleAddresse").a("class", "").f().sx("Addresse").g("label");

				e("textarea")
					.a("placeholder", "Addresse")
					.a("title", "Description.enUS: ")
					.a("class", "valeurEcoleAddresse w3-input w3-border ")
					.a("name", "ecoleAddresse")
					.a("id", "POST_ecoleAddresse")
				.f().sx(o.strEcoleAddresse()).g("textarea");

			} g("div");
		} g("div");
	}

	public void htmlFormPATCHEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
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

				e("label").a("for", "PATCH_archive").a("class", "").f().sx("Archivé").g("label");
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

				e("label").a("for", "PATCH_supprime").a("class", "").f().sx("Supprimé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_ecoleNom").a("class", "").f().sx("Nom de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Nom de l'école")
					.a("title", "Nom de l'école. ")
					.a("class", "setEcoleNom w3-input w3-border ")
					.a("name", "setEcoleNom")
					.a("id", "PATCH_ecoleNom")
					.a("value", o.strEcoleNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_ecoleNumeroTelephone").a("class", "").f().sx("Numéro de téléphone").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Numéro de téléphone")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("class", "setEcoleNumeroTelephone w3-input w3-border ")
					.a("name", "setEcoleNumeroTelephone")
					.a("id", "PATCH_ecoleNumeroTelephone")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_ecoleAdministrateurNom").a("class", "").f().sx("Administrateur de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Administrateur de l'école")
					.a("title", "Description.enUS: ")
					.a("class", "setEcoleAdministrateurNom w3-input w3-border ")
					.a("name", "setEcoleAdministrateurNom")
					.a("id", "PATCH_ecoleAdministrateurNom")
					.a("value", o.strEcoleAdministrateurNom())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_ecoleAddresse").a("class", "").f().sx("Addresse").g("label");

				e("textarea")
					.a("placeholder", "Addresse")
					.a("title", "Description.enUS: ")
					.a("class", "setEcoleAddresse w3-input w3-border ")
					.a("name", "setEcoleAddresse")
					.a("id", "PATCH_ecoleAddresse")
				.f().sx(o.strEcoleAddresse()).g("textarea");

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_ecoleCle").a("class", "").f().sx("Clé").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Clé")
					.a("title", "La clé primaire de l'école dans la base de données. ")
					.a("class", "setEcoleCle w3-input w3-border ")
					.a("name", "setEcoleCle")
					.a("id", "PATCH_ecoleCle")
					.a("value", o.strEcoleCle())
				.fg();

			} g("div");
		} g("div");
	}

	public void htmlFormRechercheEcoleScolaire(EcoleScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
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

				e("label").a("for", "Recherche_archive").a("class", "").f().sx("Archivé").g("label");
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

				e("label").a("for", "Recherche_supprime").a("class", "").f().sx("Supprimé").g("label");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_ecoleNom").a("class", "").f().sx("Nom de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Nom de l'école")
					.a("title", "Nom de l'école. ")
					.a("class", "valeurEcoleNom w3-input w3-border ")
					.a("name", "ecoleNom")
					.a("id", "Recherche_ecoleNom")
					.a("value", o.strEcoleNom())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_ecoleNumeroTelephone").a("class", "").f().sx("Numéro de téléphone").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Numéro de téléphone")
					.a("title", "Numéro de téléphone de l'école. ")
					.a("class", "valeurEcoleNumeroTelephone w3-input w3-border ")
					.a("name", "ecoleNumeroTelephone")
					.a("id", "Recherche_ecoleNumeroTelephone")
					.a("value", o.strEcoleNumeroTelephone())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_ecoleAdministrateurNom").a("class", "").f().sx("Administrateur de l'école").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Administrateur de l'école")
					.a("title", "Description.enUS: ")
					.a("class", "valeurEcoleAdministrateurNom w3-input w3-border ")
					.a("name", "ecoleAdministrateurNom")
					.a("id", "Recherche_ecoleAdministrateurNom")
					.a("value", o.strEcoleAdministrateurNom())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_ecoleAddresse").a("class", "").f().sx("Addresse").g("label");

				e("textarea")
					.a("placeholder", "Addresse")
					.a("title", "Description.enUS: ")
					.a("class", "valeurEcoleAddresse w3-input w3-border ")
					.a("name", "ecoleAddresse")
					.a("id", "Recherche_ecoleAddresse")
				.f().sx(o.strEcoleAddresse()).g("textarea");

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_ecoleCle").a("class", "").f().sx("Clé").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "Clé")
					.a("title", "La clé primaire de l'école dans la base de données. ")
					.a("class", "valeurEcoleCle w3-input w3-border ")
					.a("name", "ecoleCle")
					.a("id", "Recherche_ecoleCle")
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

				if(o.getPk() != null) {
					{ e("form").a("id", "EcoleScolaireForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageEcoleScolaire(o);
				}

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
