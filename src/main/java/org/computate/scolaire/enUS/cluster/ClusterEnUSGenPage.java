package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.util.List;
import org.computate.scolaire.enUS.page.part.PagePart;
import java.lang.Long;
import java.lang.String;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.lang.Boolean;
import org.computate.enUS.school.page.PageLayout;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.time.LocalDateTime;
import java.time.LocalDate;
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
public class ClusterEnUSGenPage extends ClusterEnUSGenPageGen<PageLayout> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeCluster(Couverture<ListeRecherche<Cluster>> c) {
	}

	protected void _cluster(Couverture<Cluster> c) {
		if(listeCluster != null && listeCluster.size() == 1)
			c.o(listeCluster.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(cluster != null)
			c.o("");
		else if(listeCluster == null || listeCluster.size() == 0)
			c.o("");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(cluster != null)
			c.o("");
		else if(listeCluster == null || listeCluster.size() == 0)
			c.o("");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/enUS/cluster");
	}

	@Override protected void _pageUriFrFR(Couverture<String> c) {
		c.o("/frFR/cluster");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/enUS/cluster-999.png");
	}

	@Override public void initLoinClusterEnUSGenPage() {
		initClusterEnUSGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsClusterEnUSGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/ClusterEnUSPage.js").f().g("script");
	}

	protected void _pageUriCluster(Couverture<String> c) {
			c.o("/enUS/cluster");
	}

	@Override public void htmlScriptClusterEnUSGenPage() {
	}

	public void htmlFormPageCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyClusterEnUSGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeCluster == null || listeCluster.size() == 0) {
			// contexteAucunNomTrouve : 

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("").g("span");
			} g("h1");
		} else if(listeCluster != null && listeCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			// contexteUnNom : 
			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("").g("span");
				} g("h1");
				Cluster o = listeCluster.get(0);
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
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeCluster.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeCluster.size(); i++) {
						Cluster o = listeCluster.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enUS/cluster/" + o.getPk();
						{ e("tr").f();
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeCluster != null && listeCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			Cluster o = listeCluster.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "/api/cluster").a("id", "ClusterForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageCluster(o);
				}

			} g("div");
		}
		htmlBodyFormsClusterEnUSGenPage();
	}

	public void htmlBodyFormsClusterEnUSGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
			.a("onclick", "$('#postClusterModale').show(); ")
			.f().sx("Créer null")
		.g("button");
		{ e("div").a("id", "postClusterModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3- ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postClusterModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// Form POST
					{ e("form").a("action", "/api/cluster").a("id", "postClusterForm").f();
						htmlFormPOSTCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "postCluster($('#postClusterForm')); ")
						.f().sx("Créer null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
			.a("onclick", "$('#patchClusterModale').show(); ")
			.f().sx("Modifier des null")
		.g("button");
		{ e("div").a("id", "patchClusterModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3- ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchClusterModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// FormFiltres PATCH
					{ e("form").a("action", "/api/cluster").a("id", "patchClusterFormFiltres").f();
						htmlFormRechercheCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "rechercheCluster($('#patchClusterFormFiltres')); ")
						.f().sx("Rechercher des null")
					.g("button");


					// FormValeurs PATCH
					{ e("form").a("action", "/api/cluster").a("id", "patchClusterFormValeurs").f();
						htmlFormPATCHCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "patchCluster($('#patchClusterFormFiltres'), $('#patchClusterFormValeurs')); ")
						.f().sx("Modifier des null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
			.a("onclick", "$('#deleteClusterModale').show(); ")
			.f().sx("Supprimer des null")
		.g("button");
		{ e("div").a("id", "deleteClusterModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3- ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteClusterModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// Form DELETE
					{ e("form").a("action", "/api/cluster").a("id", "deleteClusterForm").f();
						htmlFormPATCHCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "deleteCluster(); ")
						.f().sx("Supprimer des null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
