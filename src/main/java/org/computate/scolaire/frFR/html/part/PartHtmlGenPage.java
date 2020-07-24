package org.computate.scolaire.frFR.html.part;

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
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.html.part.HtmlPartGenPage
 **/
public class PartHtmlGenPage extends PartHtmlGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listePartHtml(Couverture<ListeRecherche<PartHtml>> c) {
	}

	protected void _partHtml(Couverture<PartHtml> c) {
		if(listePartHtml != null && listePartHtml.size() == 1)
			c.o(listePartHtml.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("part de HTMLs");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(partHtml != null && partHtml.getObjetTitre() != null)
			c.o(partHtml.getObjetTitre());
		else if(partHtml != null)
			c.o("part de HTMLs");
		else if(listePartHtml == null || listePartHtml.size() == 0)
			c.o("aucun part de HTML trouvé");
		else
			c.o("part de HTMLs");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/part-html");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/part-html-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("puzzle-piece");
	}

	@Override public void initLoinPartHtmlGenPage() {
		initPartHtmlGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsPartHtmlGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/PartHtmlPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/DesignPagePage.js").f().g("script");
	}

	@Override public void htmlScriptPartHtmlGenPage() {
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
			tl(2, "suggerePartHtmlDesignPageCles([{'name':'fq','value':'partHtmlCles:' + pk}], $('#listPartHtmlDesignPageCles_Page'), pk, true); ");
		} else {
			tl(2, "suggerePartHtmlDesignPageCles([{'name':'fq','value':'partHtmlCles:' + pk}], $('#listPartHtmlDesignPageCles_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketPartHtml(websocketPartHtmlInner);");
		l("});");
	}

	public void htmlFormPagePartHtml(PartHtml o) {
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
			o.htmDesignPageCles("Page");
			o.htmHtmlLien("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("Page");
			o.htmHtmlId("Page");
			o.htmHtmlClasses("Page");
			o.htmHtmlStyle("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("Page");
			o.htmHtmlVarSpan("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("Page");
			o.htmHtmlVarInput("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("Page");
			o.htmHtmlExclure("Page");
			o.htmPdfExclure("Page");
			o.htmConnecterDeconnecter("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("Page");
			o.htmTri2("Page");
			o.htmTri3("Page");
			o.htmTri4("Page");
			o.htmTri5("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("Page");
			o.htmTri7("Page");
			o.htmTri8("Page");
			o.htmTri9("Page");
			o.htmTri10("Page");
		} g("div");
	}

	public void htmlFormPOSTPartHtml(PartHtml o) {
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
			o.htmDesignPageCles("POST");
			o.htmHtmlLien("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("POST");
			o.htmHtmlId("POST");
			o.htmHtmlClasses("POST");
			o.htmHtmlStyle("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("POST");
			o.htmHtmlVarSpan("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("POST");
			o.htmHtmlVarInput("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("POST");
			o.htmHtmlExclure("POST");
			o.htmPdfExclure("POST");
			o.htmConnecterDeconnecter("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("POST");
			o.htmTri2("POST");
			o.htmTri3("POST");
			o.htmTri4("POST");
			o.htmTri5("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("POST");
			o.htmTri7("POST");
			o.htmTri8("POST");
			o.htmTri9("POST");
			o.htmTri10("POST");
		} g("div");
	}

	public void htmlFormPUTImportPartHtml(PartHtml o) {
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

	public void htmlFormPUTFusionPartHtml(PartHtml o) {
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

	public void htmlFormPUTCopiePartHtml(PartHtml o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUTCopie");
			o.htmModifie("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUTCopie");
			o.htmSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignPageCles("PUTCopie");
			o.htmHtmlLien("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("PUTCopie");
			o.htmHtmlId("PUTCopie");
			o.htmHtmlClasses("PUTCopie");
			o.htmHtmlStyle("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("PUTCopie");
			o.htmHtmlVarSpan("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("PUTCopie");
			o.htmHtmlVarInput("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("PUTCopie");
			o.htmHtmlExclure("PUTCopie");
			o.htmPdfExclure("PUTCopie");
			o.htmConnecterDeconnecter("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("PUTCopie");
			o.htmTri2("PUTCopie");
			o.htmTri3("PUTCopie");
			o.htmTri4("PUTCopie");
			o.htmTri5("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("PUTCopie");
			o.htmTri7("PUTCopie");
			o.htmTri8("PUTCopie");
			o.htmTri9("PUTCopie");
			o.htmTri10("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopie");
			o.htmSessionId("PUTCopie");
			o.htmUtilisateurId("PUTCopie");
			o.htmUtilisateurCle("PUTCopie");
		} g("div");
	}

	public void htmlFormPATCHPartHtml(PartHtml o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignPageCles("PATCH");
			o.htmHtmlLien("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("PATCH");
			o.htmHtmlId("PATCH");
			o.htmHtmlClasses("PATCH");
			o.htmHtmlStyle("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAvant("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlApres("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlTexte("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("PATCH");
			o.htmHtmlVarSpan("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("PATCH");
			o.htmHtmlVarInput("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("PATCH");
			o.htmHtmlExclure("PATCH");
			o.htmPdfExclure("PATCH");
			o.htmConnecterDeconnecter("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("PATCH");
			o.htmTri2("PATCH");
			o.htmTri3("PATCH");
			o.htmTri4("PATCH");
			o.htmTri5("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("PATCH");
			o.htmTri7("PATCH");
			o.htmTri8("PATCH");
			o.htmTri9("PATCH");
			o.htmTri10("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUtilisateurId("PATCH");
			o.htmUtilisateurCle("PATCH");
		} g("div");
	}

	public void htmlFormRecherchePartHtml(PartHtml o) {
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
			o.htmDesignPageCles("Recherche");
			o.htmHtmlLien("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("Recherche");
			o.htmHtmlId("Recherche");
			o.htmHtmlClasses("Recherche");
			o.htmHtmlStyle("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("Recherche");
			o.htmHtmlVarSpan("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForm("Recherche");
			o.htmHtmlVarInput("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVarForEach("Recherche");
			o.htmHtmlExclure("Recherche");
			o.htmPdfExclure("Recherche");
			o.htmConnecterDeconnecter("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri1("Recherche");
			o.htmTri2("Recherche");
			o.htmTri3("Recherche");
			o.htmTri4("Recherche");
			o.htmTri5("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTri6("Recherche");
			o.htmTri7("Recherche");
			o.htmTri8("Recherche");
			o.htmTri9("Recherche");
			o.htmTri10("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Recherche");
			o.htmSessionId("Recherche");
			o.htmUtilisateurId("Recherche");
			o.htmUtilisateurCle("Recherche");
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyPartHtmlGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listePartHtml == null || listePartHtml.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/part-html").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("part de HTMLs").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun part de HTML trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listePartHtml != null && listePartHtml.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PartHtml o = listePartHtml.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/part-html").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-khaki ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-khaki ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/part-html").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listePartHtml.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listePartHtml).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listePartHtml).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listePartHtml).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listePartHtml).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/part-html?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/part-html?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/part-html?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/part-html?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1PartHtmlGenPage();
		}

		if(listePartHtml != null && listePartHtml.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PartHtml o = listePartHtml.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "PartHtmlForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPagePartHtml(o);
				}

			} g("div");

		}
		htmlBodyFormsPartHtmlGenPage();
		g("div");
	}

	public void table1PartHtmlGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2PartHtmlGenPage();
		} g("table");
	}

	public void table2PartHtmlGenPage() {
		thead1PartHtmlGenPage();
		tbody1PartHtmlGenPage();
		tfoot1PartHtmlGenPage();
	}

	public void thead1PartHtmlGenPage() {
		{ e("thead").a("class", "w3-khaki w3-hover-khaki ").f();
			thead2PartHtmlGenPage();
		} g("thead");
	}

	public void thead2PartHtmlGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1PartHtmlGenPage() {
		{ e("tbody").f();
			tbody2PartHtmlGenPage();
		} g("tbody");
	}

	public void tbody2PartHtmlGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listePartHtml.getQueryResponse().getHighlighting();
		for(int i = 0; i < listePartHtml.size(); i++) {
			PartHtml o = listePartHtml.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/part-html/" + o.getPk();
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
							e("i").a("class", "far fa-puzzle-piece ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1PartHtmlGenPage() {
		{ e("tfoot").a("class", "w3-khaki w3-hover-khaki ").f();
			tfoot2PartHtmlGenPage();
		} g("tfoot");
	}

	public void tfoot2PartHtmlGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listePartHtml.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsPartHtmlGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listePartHtml != null && listePartHtml.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
						.a("id", "rechargerCePartHtmlGenPage")
						.a("onclick", "patchPartHtmlVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCePartHtmlGenPage')); }, function() { ajouterErreur($('#rechargerCePartHtmlGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce part de HTML");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#postPartHtmlModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un part de HTML");
			} g("button");
			{ e("div").a("id", "postPartHtmlModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postPartHtmlModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un part de HTML").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PartHtml o = new PartHtml();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postPartHtmlForm").f();
								htmlFormPOSTPartHtml(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "postPartHtml($('#postPartHtmlForm')); ")
								.f().sx("Créer un part de HTML")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#putimportPartHtmlModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Importer part de HTMLs");
			} g("button");
			{ e("div").a("id", "putimportPartHtmlModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportPartHtmlModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Importer part de HTMLs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PartHtml o = new PartHtml();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putimportPartHtmlForm").f();
								htmlFormPUTImportPartHtml(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "putimportPartHtml($('#putimportPartHtmlForm')); ")
								.f().sx("Importer part de HTMLs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#putfusionPartHtmlModale').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Fusionner part de HTMLs");
			} g("button");
			{ e("div").a("id", "putfusionPartHtmlModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putfusionPartHtmlModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Fusionner part de HTMLs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PartHtml o = new PartHtml();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putfusionPartHtmlForm").f();
								htmlFormPUTFusionPartHtml(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "putfusionPartHtml($('#putfusionPartHtmlForm')); ")
								.f().sx("Fusionner part de HTMLs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#putcopiePartHtmlModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer part de HTMLs");
			} g("button");
			{ e("div").a("id", "putcopiePartHtmlModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopiePartHtmlModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer part de HTMLs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PartHtml o = new PartHtml();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putcopiePartHtmlForm").f();
								htmlFormPUTCopiePartHtml(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "putcopiePartHtml($('#putcopiePartHtmlForm'), ", partHtml == null ? "null" : partHtml.getPk(), "); ")
								.f().sx("Dupliquer part de HTMLs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#patchPartHtmlModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier part de HTMLs");
			} g("button");
			{ e("div").a("id", "patchPartHtmlModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchPartHtmlModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier part de HTMLs").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							PartHtml o = new PartHtml();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchPartHtmlFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHPartHtml(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "patchPartHtml(null, $('#patchPartHtmlFormulaireValeurs'), ", Optional.ofNullable(partHtml).map(PartHtml::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier part de HTMLs")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggerePartHtmlGenPage(this, null, listePartHtml);
	}

	/**
	 * Var.enUS: htmlSuggestedHtmlPartGenPage
	 * r: "/part-html"
	 * r.enUS: "/html-part"
	 * r: "voir tous les part de HTMLs"
	 * r.enUS: "see all the HTML parts"
	 * r: "rechargerPartHtmlGenPage"
	 * r.enUS: "refreshHtmlPartGenPage"
	 * r: "recharger tous les part de HTMLs"
	 * r.enUS: "refresh all the HTML parts"
	 * r: "rechercher part de HTMLs : "
	 * r.enUS: "search HTML parts: "
	 * r: "suggereFormPartHtml"
	 * r.enUS: "suggestFormHtmlPart"
	 * r: "rechercher part de HTMLs"
	 * r.enUS: "search HTML parts"
	 * r: "suggerePartHtml w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestHtmlPart w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggerePartHtml"
	 * r.enUS: "suggestHtmlPart"
	 * r: patchPartHtmlVals
	 * r.enUS: patchHtmlPartVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerPartHtmlGenPage
	 * r.enUS: refreshHtmlPartGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggerePartHtmlObjetSuggere
	 * r.enUS: suggestHtmlPart
	 * r: textePartHtmlObjetTexte
	 * r.enUS: textHtmlPart
	 * r: 'objetSuggere:'
	 * r.enUS: ':'
	 * r: 'objetTexte:'
	 * r.enUS: ':'
	 * r: '#suggereListPartHtml'
	 * r.enUS: '#suggestListHtmlPart'
	 * r: "suggereListPartHtml"
	 * r.enUS: "suggestListHtmlPart"
	**/
	public static void htmlSuggerePartHtmlGenPage(MiseEnPage p, String id, ListeRecherche<PartHtml> listePartHtml) {
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

			Integer rows1 = Optional.ofNullable(listePartHtml).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listePartHtml).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listePartHtml).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listePartHtml).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), PartHtmlGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), PartHtmlGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerTousPartHtmlGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ").a("onclick", "patchPartHtmlVals([], {}, function() { ajouterLueur($('#rechargerTousPartHtmlGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousPartHtmlGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger tous les part de HTMLs");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher part de HTMLs : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher part de HTMLs")
					.a("class", "suggerePartHtml w3-input w3-border w3-bar-item ")
					.a("name", "suggerePartHtml")
					.a("id", "suggerePartHtml", id)
					.a("autocomplete", "off")
					.a("oninput", "suggerePartHtmlObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListPartHtml", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/part-html?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listePartHtml != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-khaki ")
					.a("onclick", "window.location.href = '/part-html?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListPartHtml", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/part-html").a("class", "").f();
					p.e("i").a("class", "far fa-puzzle-piece ").f().g("i");
					p.sx("voir tous les part de HTMLs");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
