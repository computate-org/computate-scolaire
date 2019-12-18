package org.computate.scolaire.frFR.inscription;

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


/**
 * Traduire: false
 **/
public class InscriptionPdfGenPage extends InscriptionPdfGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeInscriptionScolaire(Couverture<ListeRecherche<InscriptionScolaire>> c) {
	}

	protected void _inscriptionScolaire(Couverture<InscriptionScolaire> c) {
		if(listeInscriptionScolaire != null && listeInscriptionScolaire.size() == 1)
			c.o(listeInscriptionScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("inscriptions");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(inscriptionScolaire != null && inscriptionScolaire.getInscriptionNomComplet() != null)
			c.o(inscriptionScolaire.getInscriptionNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(inscriptionScolaire != null && inscriptionScolaire.getInscriptionNomComplet() != null)
			c.o(inscriptionScolaire.getInscriptionNomComplet());
		else if(inscriptionScolaire != null)
			c.o("");
		else if(listeInscriptionScolaire == null || listeInscriptionScolaire.size() == 0)
			c.o("aucune inscription trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/inscription/pdf");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/inscription/pdf-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("solid");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("edit");
	}

	@Override public void initLoinInscriptionPdfGenPage() {
		initInscriptionPdfGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsInscriptionPdfGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPdfPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/BlocPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/EnfantPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/MerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/GardienPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PaiementPage.js").f().g("script");
	}

	@Override public void htmlScriptInscriptionPdfGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggereInscriptionScolaireBlocCles([{'name':'fq','value':'inscriptionCles:", requeteSite_.getRequetePk(), "'}], $('#listInscriptionScolaireBlocCles_Page'), ", requeteSite_.getRequetePk(), "); ");
		tl(1, "suggereInscriptionScolaireEnfantCle([{'name':'fq','value':'inscriptionCles:", requeteSite_.getRequetePk(), "'}], $('#listInscriptionScolaireEnfantCle_Page'), ", requeteSite_.getRequetePk(), "); ");
		tl(1, "suggereInscriptionScolaireMereCles([{'name':'fq','value':'inscriptionCles:", requeteSite_.getRequetePk(), "'}], $('#listInscriptionScolaireMereCles_Page'), ", requeteSite_.getRequetePk(), "); ");
		tl(1, "suggereInscriptionScolairePereCles([{'name':'fq','value':'inscriptionCles:", requeteSite_.getRequetePk(), "'}], $('#listInscriptionScolairePereCles_Page'), ", requeteSite_.getRequetePk(), "); ");
		tl(1, "suggereInscriptionScolaireGardienCles([{'name':'fq','value':'inscriptionCles:", requeteSite_.getRequetePk(), "'}], $('#listInscriptionScolaireGardienCles_Page'), ", requeteSite_.getRequetePk(), "); ");
		tl(1, "suggereInscriptionScolairePaiementCles([{'name':'fq','value':'inscriptionCles:", requeteSite_.getRequetePk(), "'}], $('#listInscriptionScolairePaiementCles_Page'), ", requeteSite_.getRequetePk(), "); ");
		tl(1, "websocketInscriptionScolaire(async function(requetePatch) {");
		tl(2, "var pk = requetePatch['pk'];");
		tl(2, "var pks = requetePatch['pks'];");
		tl(2, "var classes = requetePatch['classes'];");
		tl(2, "if(pks) {");
		tl(3, "for(i=0; i < pks.length; i++) {");
		tl(4, "var pk2 = pks[i];");
		tl(4, "var c = classes[i];");
		tl(4, "await window['patch' + c + 'Vals']( [ {name: 'fq', value: 'pk:' + pk2} ], {});");
		tl(3, "}");
		tl(2, "}");
		tl(2, "await patchInscriptionScolaireVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageInscriptionScolaire(InscriptionScolaire o) {
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
			o.htmInscriptionApprouve("Page");
			o.htmInscriptionImmunisations("Page");
			o.htmInscriptionNomGroupe("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("Page");
			o.htmInscriptionPaimentChaqueMois("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleMarie("Page");
			o.htmFamilleSepare("Page");
			o.htmFamilleDivorce("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleAddresse("Page");
			o.htmInscriptionConsiderationsSpeciales("Page");
			o.htmFamilleCommentVousConnaissezEcole("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocCles("Page");
			o.htmEnfantCle("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMereCles("Page");
			o.htmPereCles("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGardienCles("Page");
			o.htmPaiementCles("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFormInscriptionCle("Page");
		} g("div");
	}

	public void htmlFormPOSTInscriptionScolaire(InscriptionScolaire o) {
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
			o.htmInscriptionApprouve("POST");
			o.htmInscriptionImmunisations("POST");
			o.htmInscriptionNomGroupe("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("POST");
			o.htmInscriptionPaimentChaqueMois("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleMarie("POST");
			o.htmFamilleSepare("POST");
			o.htmFamilleDivorce("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleAddresse("POST");
			o.htmInscriptionConsiderationsSpeciales("POST");
			o.htmFamilleCommentVousConnaissezEcole("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocCles("POST");
			o.htmEnfantCle("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMereCles("POST");
			o.htmPereCles("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGardienCles("POST");
			o.htmPaiementCles("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFormInscriptionCle("POST");
		} g("div");
	}

	public void htmlFormPATCHInscriptionScolaire(InscriptionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("PATCH");
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
			o.htmObjetId("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionApprouve("PATCH");
			o.htmInscriptionImmunisations("PATCH");
			o.htmInscriptionNomGroupe("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("PATCH");
			o.htmInscriptionPaimentChaqueMois("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleMarie("PATCH");
			o.htmFamilleSepare("PATCH");
			o.htmFamilleDivorce("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleAddresse("PATCH");
			o.htmInscriptionConsiderationsSpeciales("PATCH");
			o.htmFamilleCommentVousConnaissezEcole("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocCles("PATCH");
			o.htmEnfantCle("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMereCles("PATCH");
			o.htmPereCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGardienCles("PATCH");
			o.htmPaiementCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFormInscriptionCle("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionNomComplet("PATCH");
		} g("div");
	}

	public void htmlFormRechercheInscriptionScolaire(InscriptionScolaire o) {
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
			o.htmInscriptionApprouve("Recherche");
			o.htmInscriptionImmunisations("Recherche");
			o.htmInscriptionNomGroupe("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("Recherche");
			o.htmInscriptionPaimentChaqueMois("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleMarie("Recherche");
			o.htmFamilleSepare("Recherche");
			o.htmFamilleDivorce("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleAddresse("Recherche");
			o.htmInscriptionConsiderationsSpeciales("Recherche");
			o.htmFamilleCommentVousConnaissezEcole("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocCles("Recherche");
			o.htmEnfantCle("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMereCles("Recherche");
			o.htmPereCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGardienCles("Recherche");
			o.htmPaiementCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFormInscriptionCle("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionNomComplet("Recherche");
		} g("div");
	}

	@Override public void htmlBodyInscriptionPdfGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeInscriptionScolaire == null || listeInscriptionScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/inscription/pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("inscriptions").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-purple ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucune inscription trouvée").g("span");
				} g("span");
			} g("h2");
		} else if(listeInscriptionScolaire != null && listeInscriptionScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			InscriptionScolaire o = listeInscriptionScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/inscription/pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-purple ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-purple ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/inscription/pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeInscriptionScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeInscriptionScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeInscriptionScolaire.getRows();
					Integer start1 = listeInscriptionScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/inscription/pdf?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/inscription/pdf?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/inscription/pdf?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/inscription/pdf?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-purple w3-hover-purple ").f();
					{ e("tr").f();
						e("th").f().sx("nom").g("th");
						e("th").f().sx("crée").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeInscriptionScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeInscriptionScolaire.size(); i++) {
						InscriptionScolaire o = listeInscriptionScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/inscription/pdf/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strInscriptionNomComplet());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCree());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeInscriptionScolaire != null && listeInscriptionScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			InscriptionScolaire o = listeInscriptionScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "InscriptionScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageInscriptionScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsInscriptionPdfGenPage();
		htmlSuggereInscriptionPdfGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsInscriptionPdfGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
				.a("id", "rechargerCetteInscriptionPdfGenPage")
				.a("onclick", "patchInscriptionScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCetteInscriptionPdfGenPage')); }, function() { ajouterErreur($('#rechargerCetteInscriptionPdfGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger cette inscription");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
			.a("onclick", "$('#postInscriptionScolaireModale').show(); ")
			.f().sx("Créer une inscription")
		.g("button");
		{ e("div").a("id", "postInscriptionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postInscriptionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer une inscription").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					InscriptionScolaire o = new InscriptionScolaire();
					o.setRequeteSite_(requeteSite_);

					// Form POST
					{ e("div").a("id", "postInscriptionScolaireForm").f();
						htmlFormPOSTInscriptionScolaire(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
						.a("onclick", "postInscriptionScolaire($('#postInscriptionScolaireForm')); ")
						.f().sx("Créer une inscription")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
			.a("onclick", "$('#patchInscriptionScolaireModale').show(); ")
			.f().sx("Modifier des inscriptions")
		.g("button");
		{ e("div").a("id", "patchInscriptionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchInscriptionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des inscriptions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					InscriptionScolaire o = new InscriptionScolaire();
					o.setRequeteSite_(requeteSite_);

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchInscriptionScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheInscriptionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
						.a("onclick", "rechercheInscriptionScolaire($('#patchInscriptionScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des une inscription")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchInscriptionScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHInscriptionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
						.a("onclick", "patchInscriptionScolaire($('#patchInscriptionScolaireFormulaireFiltres'), $('#patchInscriptionScolaireFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des inscriptions")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeInscriptionScolaire != null && listeInscriptionScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
				.a("onclick", "$('#deleteInscriptionScolaireModale').show(); ")
				.f().sx("Supprimer des inscriptions")
			.g("button");
			{ e("div").a("id", "deleteInscriptionScolaireModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-purple ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteInscriptionScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des inscriptions").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						InscriptionScolaire o = new InscriptionScolaire();
						o.setRequeteSite_(requeteSite_);

						// Form DELETE
						{ e("div").a("id", "deleteInscriptionScolaireForm").f();
							htmlFormPATCHInscriptionScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
							.a("onclick", "deleteInscriptionScolaire(", o.getPk(), "); ")
							.f().sx("Supprimer des inscriptions")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggereInscriptionPdfGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/inscription/pdf").a("class", "").f();
					p.e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
					p.sx("voir toutes les inscriptions");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerToutesInscriptionPdfGenPage", id).a("href", "/inscription/pdf").a("class", "").a("onclick", "patchInscriptionScolaireVals([], {}, function() { ajouterLueur($('#rechargerToutesInscriptionPdfGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesInscriptionPdfGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger toutes les inscriptions");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher inscriptions : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormInscriptionScolaire", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/inscription/pdf?q=objetSuggere:' + encodeURIComponent($('#suggereInscriptionScolaire", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher inscriptions")
							.a("class", "suggereInscriptionScolaire w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereInscriptionScolaire")
							.a("id", "suggereInscriptionScolaire", id)
							.a("autocomplete", "off")
							.a("oninput", "suggereInscriptionScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListInscriptionScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListInscriptionScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
