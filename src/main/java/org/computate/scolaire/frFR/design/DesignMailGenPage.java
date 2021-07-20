package org.computate.scolaire.frFR.design;

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
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
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
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
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
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
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
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
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
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Traduire: false
 **/
public class DesignMailGenPage extends DesignMailGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeDesignPage(Couverture<ListeRecherche<DesignPage>> c) {
	}

	protected void _designPage_(Couverture<DesignPage> c) {
		if(listeDesignPage != null && listeDesignPage.size() == 1)
			c.o(listeDesignPage.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("design de pages");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(designPage_ != null && designPage_.getDesignPageNomComplet() != null)
			c.o(designPage_.getDesignPageNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(designPage_ != null && designPage_.getDesignPageNomComplet() != null)
			c.o(designPage_.getDesignPageNomComplet());
		else if(designPage_ != null)
			c.o("design de pages");
		else if(listeDesignPage == null || listeDesignPage.size() == 0)
			c.o("aucun design de page trouvé");
		else
			c.o("design de pages");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/mail");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/mail-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("drafting-compass");
	}

	@Override public void initLoinDesignMailGenPage() {
		initDesignMailGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsDesignMailGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/DesignMailPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/DesignPagePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/DesignPagePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PartHtmlPage.js").f().g("script");
	}

	@Override public void htmlScriptDesignMailGenPage() {
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
			tl(2, "suggereDesignPageDesignParentCles([{'name':'fq','value':'designEnfantCles:' + pk}], $('#listDesignPageDesignParentCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereDesignPageDesignParentCles([{'name':'fq','value':'designEnfantCles:' + pk}], $('#listDesignPageDesignParentCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereDesignPagePartHtmlCles([{'name':'fq','value':'designPageCles:' + pk}], $('#listDesignPagePartHtmlCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereDesignPagePartHtmlCles([{'name':'fq','value':'designPageCles:' + pk}], $('#listDesignPagePartHtmlCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereDesignPageDesignEnfantCles([{'name':'fq','value':'designParentCles:' + pk}], $('#listDesignPageDesignEnfantCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereDesignPageDesignEnfantCles([{'name':'fq','value':'designParentCles:' + pk}], $('#listDesignPageDesignEnfantCles_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketDesignPage(websocketDesignPageInner);");
		l("});");
	}

	public void htmlFormPageDesignPage(DesignPage o) {
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
			o.htmDesignPageNomComplet("Page");
			o.htmDesignCache("Page");
			o.htmDesignAdmin("Page");
			o.htmDesignIgnorerNomEnfantVide("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignIgnorerPaiementsPasEnSouffrance("Page");
			o.htmDesignIgnorerPaiementsEnSouffrance("Page");
			o.htmDesignFiltrerInscriptionCle("Page");
			o.htmDesignInscriptionTriMoisJourDeNaissance("Page");
			o.htmDesignInscriptionTriNomGroupe("Page");
			o.htmDesignInscriptionTriNomEnfant("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmRechercherAnnees("Page");
			o.htmRechercherPaiements("Page");
			o.htmRechercherPaiementsActuel("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignParentCles("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("Page");
		} g("div");
	}

	public void htmlFormPOSTDesignPage(DesignPage o) {
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
			o.htmDesignPageNomComplet("POST");
			o.htmDesignCache("POST");
			o.htmDesignAdmin("POST");
			o.htmDesignIgnorerNomEnfantVide("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignIgnorerPaiementsPasEnSouffrance("POST");
			o.htmDesignIgnorerPaiementsEnSouffrance("POST");
			o.htmDesignFiltrerInscriptionCle("POST");
			o.htmDesignInscriptionTriMoisJourDeNaissance("POST");
			o.htmDesignInscriptionTriNomGroupe("POST");
			o.htmDesignInscriptionTriNomEnfant("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmRechercherAnnees("POST");
			o.htmRechercherPaiements("POST");
			o.htmRechercherPaiementsActuel("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignParentCles("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("POST");
		} g("div");
	}

	public void htmlFormPUTImportDesignPage(DesignPage o) {
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

	public void htmlFormPUTFusionDesignPage(DesignPage o) {
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

	public void htmlFormPUTCopieDesignPage(DesignPage o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUTCopie");
			o.htmModifie("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUTCopie");
			o.htmSupprime("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignPageNomComplet("PUTCopie");
			o.htmDesignCache("PUTCopie");
			o.htmDesignAdmin("PUTCopie");
			o.htmDesignIgnorerNomEnfantVide("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignIgnorerPaiementsPasEnSouffrance("PUTCopie");
			o.htmDesignIgnorerPaiementsEnSouffrance("PUTCopie");
			o.htmDesignFiltrerInscriptionCle("PUTCopie");
			o.htmDesignInscriptionTriMoisJourDeNaissance("PUTCopie");
			o.htmDesignInscriptionTriNomGroupe("PUTCopie");
			o.htmDesignInscriptionTriNomEnfant("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmRechercherAnnees("PUTCopie");
			o.htmRechercherPaiements("PUTCopie");
			o.htmRechercherPaiementsActuel("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignParentCles("PUTCopie");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("PUTCopie");
		} g("div");
	}

	public void htmlFormPATCHDesignPage(DesignPage o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignPageNomComplet("PATCH");
			o.htmDesignCache("PATCH");
			o.htmDesignAdmin("PATCH");
			o.htmDesignIgnorerNomEnfantVide("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignIgnorerPaiementsPasEnSouffrance("PATCH");
			o.htmDesignIgnorerPaiementsEnSouffrance("PATCH");
			o.htmDesignFiltrerInscriptionCle("PATCH");
			o.htmDesignInscriptionTriMoisJourDeNaissance("PATCH");
			o.htmDesignInscriptionTriNomGroupe("PATCH");
			o.htmDesignInscriptionTriNomEnfant("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmRechercherAnnees("PATCH");
			o.htmRechercherPaiements("PATCH");
			o.htmRechercherPaiementsActuel("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignParentCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("PATCH");
		} g("div");
	}

	public void htmlFormRechercheDesignPage(DesignPage o) {
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
			o.htmDesignPageNomComplet("Recherche");
			o.htmDesignCache("Recherche");
			o.htmDesignAdmin("Recherche");
			o.htmDesignIgnorerNomEnfantVide("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignIgnorerPaiementsPasEnSouffrance("Recherche");
			o.htmDesignIgnorerPaiementsEnSouffrance("Recherche");
			o.htmDesignFiltrerInscriptionCle("Recherche");
			o.htmDesignInscriptionTriMoisJourDeNaissance("Recherche");
			o.htmDesignInscriptionTriNomGroupe("Recherche");
			o.htmDesignInscriptionTriNomEnfant("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmRechercherAnnees("Recherche");
			o.htmRechercherPaiements("Recherche");
			o.htmRechercherPaiementsActuel("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmDesignParentCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPartHtmlCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Recherche");
			o.htmSessionId("Recherche");
			o.htmUtilisateurId("Recherche");
			o.htmUtilisateurCle("Recherche");
			o.htmObjetTitre("Recherche");
		} g("div");
	}

	@Override public void htmlBodyDesignMailGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeDesignPage == null || listeDesignPage.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/mail").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("design de pages").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun design de page trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeDesignPage != null && listeDesignPage.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			DesignPage o = listeDesignPage.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/mail").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
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
				{ e("a").a("href", "/mail").a("class", "w3-bar-item w3-btn w3-center w3-block w3-khaki w3-hover-khaki ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			{ e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeDesignPage.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listeDesignPage).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeDesignPage).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeDesignPage).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeDesignPage).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mail?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mail?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/mail?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mail?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1DesignMailGenPage();
			} g("div");
		}

		if(listeDesignPage != null && listeDesignPage.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			DesignPage o = listeDesignPage.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "DesignPageForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageDesignPage(o);
				}

			} g("div");

		}
		htmlBodyFormsDesignMailGenPage();
	}

	public void table1DesignMailGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2DesignMailGenPage();
		} g("table");
	}

	public void table2DesignMailGenPage() {
		thead1DesignMailGenPage();
		tbody1DesignMailGenPage();
		tfoot1DesignMailGenPage();
	}

	public void thead1DesignMailGenPage() {
		{ e("thead").a("class", "w3-khaki w3-hover-khaki ").f();
			thead2DesignMailGenPage();
		} g("thead");
	}

	public void thead2DesignMailGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1DesignMailGenPage() {
		{ e("tbody").f();
			tbody2DesignMailGenPage();
		} g("tbody");
	}

	public void tbody2DesignMailGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeDesignPage.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeDesignPage.size(); i++) {
			DesignPage o = listeDesignPage.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/mail/" + o.getPk();
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
							e("i").a("class", "far fa-drafting-compass ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1DesignMailGenPage() {
		{ e("tfoot").a("class", "w3-khaki w3-hover-khaki ").f();
			tfoot2DesignMailGenPage();
		} g("tfoot");
	}

	public void tfoot2DesignMailGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeDesignPage.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsDesignMailGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {

			if(listeDesignPage != null && listeDesignPage.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
						.a("id", "rechargerCeDesignMailGenPage")
						.a("onclick", "patchDesignPageVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeDesignMailGenPage')); }, function() { ajouterErreur($('#rechargerCeDesignMailGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger ce design de page");
				} g("button");
			}
		}
		if(
				requeteSite_.getUtilisateurRolesRessource().contains("SiteAdmin")
				|| requeteSite_.getUtilisateurRolesRoyaume().contains("SiteAdmin")
				) {

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#postDesignPageModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer un design de page");
			} g("button");
			{ e("div").a("id", "postDesignPageModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postDesignPageModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Créer un design de page").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "postDesignPageFormulaireValeurs").f();
							DesignPage o = new DesignPage();
							o.setRequeteSite_(requeteSite_);

							// Form POST
							{ e("div").a("id", "postDesignPageForm").f();
								htmlFormPOSTDesignPage(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "postDesignPage($('#postDesignPageForm')); ")
								.f().sx("Créer un design de page")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#putimportDesignPageModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Importer design de pages");
			} g("button");
			{ e("div").a("id", "putimportDesignPageModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportDesignPageModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Importer design de pages").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putimportDesignPageFormulaireValeurs").f();
							DesignPage o = new DesignPage();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putimportDesignPageForm").f();
								htmlFormPUTImportDesignPage(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "putimportDesignPage($('#putimportDesignPageForm')); ")
								.f().sx("Importer design de pages")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#putfusionDesignPageModale').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Fusionner design de pages");
			} g("button");
			{ e("div").a("id", "putfusionDesignPageModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putfusionDesignPageModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Fusionner design de pages").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putfusionDesignPageFormulaireValeurs").f();
							DesignPage o = new DesignPage();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putfusionDesignPageForm").f();
								htmlFormPUTFusionDesignPage(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "putfusionDesignPage($('#putfusionDesignPageForm')); ")
								.f().sx("Fusionner design de pages")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#putcopieDesignPageModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer design de pages");
			} g("button");
			{ e("div").a("id", "putcopieDesignPageModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopieDesignPageModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer design de pages").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putcopieDesignPageFormulaireValeurs").f();
							DesignPage o = new DesignPage();
							o.setRequeteSite_(requeteSite_);

							// Form PUT
							{ e("div").a("id", "putcopieDesignPageForm").f();
								htmlFormPUTCopieDesignPage(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "putcopieDesignPage($('#putcopieDesignPageForm'), ", designPage_ == null ? "null" : designPage_.getPk(), "); ")
								.f().sx("Dupliquer design de pages")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
				.a("onclick", "$('#patchDesignPageModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier design de pages");
			} g("button");
			{ e("div").a("id", "patchDesignPageModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-khaki ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchDesignPageModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier design de pages").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "patchDesignPageFormulaireValeurs").f();
							DesignPage o = new DesignPage();
							o.setRequeteSite_(requeteSite_);

							htmlFormPATCHDesignPage(o);
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-khaki ")
								.a("onclick", "patchDesignPage(null, $('#patchDesignPageFormulaireValeurs'), ", Optional.ofNullable(designPage_).map(DesignPage::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier design de pages")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		htmlSuggereDesignMailGenPage(this, null, listeDesignPage);
	}

	/**
	**/
	public static void htmlSuggereDesignMailGenPage(MiseEnPage p, String id, ListeRecherche<DesignPage> listeDesignPage) {
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

			Integer rows1 = Optional.ofNullable(listeDesignPage).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeDesignPage).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeDesignPage).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeDesignPage).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), DesignMailGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), DesignMailGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerTousDesignMailGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ").a("onclick", "patchDesignPageVals([], {}, function() { ajouterLueur($('#rechargerTousDesignMailGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousDesignMailGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger tous les design de pages");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher design de pages : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher design de pages")
					.a("class", "suggereDesignPage w3-input w3-border w3-bar-item ")
					.a("name", "suggereDesignPage")
					.a("id", "suggereDesignPage", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereDesignPageObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,designPageNomComplet' } ], $('#suggereListDesignPage", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/mail?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeDesignPage != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-khaki ")
					.a("onclick", "window.location.href = '/mail?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListDesignPage", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/mail").a("class", "").f();
					p.e("i").a("class", "far fa-drafting-compass ").f().g("i");
					p.sx("voir tous les design de pages");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
