package org.computate.scolaire.frFR.inscription;

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
 **/
public class InscriptionPdfGenPage extends InscriptionPdfGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

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
		c.o("/inscription-pdf");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/inscription-pdf-999.png");
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
		e("script").a("src", statiqueUrlBase, "/js/frFR/AnneePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/BlocPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/EnfantPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/MerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/GardienPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PaiementPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/UtilisateurSitePage.js").f().g("script");
	}

	@Override public void htmlScriptInscriptionPdfGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(requeteSite_.getRequetePk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolaireBlocCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireBlocCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolaireBlocCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireBlocCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolaireEnfantCle([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireEnfantCle_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolaireEnfantCle([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireEnfantCle_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolaireMereCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireMereCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolaireMereCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireMereCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolairePereCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolairePereCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolairePereCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolairePereCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolaireGardienCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireGardienCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolaireGardienCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireGardienCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolairePaiementCles([{'name':'fq','value':'inscriptionCle:' + pk}], $('#listInscriptionScolairePaiementCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolairePaiementCles([{'name':'fq','value':'inscriptionCle:' + pk}], $('#listInscriptionScolairePaiementCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolaireUtilisateurCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireUtilisateurCles_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolaireUtilisateurCles([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireUtilisateurCles_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			tl(2, "suggereInscriptionScolaireAnneeCle([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireAnneeCle_Page'), pk, true); ");
		} else {
			tl(2, "suggereInscriptionScolaireAnneeCle([{'name':'fq','value':'inscriptionCles:' + pk}], $('#listInscriptionScolaireAnneeCle_Page'), pk, false); ");
		}
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature1').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature2').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature3').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature4').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature5').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature6').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature7').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature8').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature9').jSignature({'height':200}); ");
		tl(2, "$('#inputInscriptionScolaire' + pk + 'inscriptionSignature10').jSignature({'height':200}); ");
		tl(1, "}");
		tl(1, "websocketInscriptionScolaire(websocketInscriptionScolaireInner);");
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
			o.htmCustomerProfileId("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("Page");
			o.htmEnfantPropre("Page");
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
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("Page");
			o.htmEnfantEcolesPrecedemmentFrequentees("Page");
			o.htmFamilleCommentVousConnaissezEcole("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("Page");
			o.htmEnfantObjectifs("Page");
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
			o.htmUtilisateurCles("Page");
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
			o.htmCustomerProfileId("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("POST");
			o.htmEnfantPropre("POST");
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
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("POST");
			o.htmEnfantEcolesPrecedemmentFrequentees("POST");
			o.htmFamilleCommentVousConnaissezEcole("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("POST");
			o.htmEnfantObjectifs("POST");
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
			o.htmUtilisateurCles("POST");
		} g("div");
	}

	public void htmlFormPUTInscriptionScolaire(InscriptionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PUT");
			o.htmModifie("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PUT");
			o.htmSupprime("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionApprouve("PUT");
			o.htmInscriptionImmunisations("PUT");
			o.htmInscriptionNomGroupe("PUT");
			o.htmCustomerProfileId("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("PUT");
			o.htmEnfantPropre("PUT");
			o.htmInscriptionPaimentChaqueMois("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleMarie("PUT");
			o.htmFamilleSepare("PUT");
			o.htmFamilleDivorce("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilleAddresse("PUT");
			o.htmInscriptionConsiderationsSpeciales("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("PUT");
			o.htmEnfantEcolesPrecedemmentFrequentees("PUT");
			o.htmFamilleCommentVousConnaissezEcole("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("PUT");
			o.htmEnfantObjectifs("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlocCles("PUT");
			o.htmEnfantCle("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMereCles("PUT");
			o.htmPereCles("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGardienCles("PUT");
			o.htmPaiementCles("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUtilisateurCles("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantNomComplet("PUT");
			o.htmEnfantNomCompletPrefere("PUT");
			o.htmEnfantDateNaissance("PUT");
			o.htmEcoleAddresse("PUT");
			o.htmInscriptionDateFrais("PUT");
			o.htmInscriptionNomsParents("PUT");
			o.htmInscriptionSignature1("PUT");
			o.htmInscriptionSignature2("PUT");
			o.htmInscriptionSignature3("PUT");
			o.htmInscriptionSignature4("PUT");
			o.htmInscriptionSignature5("PUT");
			o.htmInscriptionSignature6("PUT");
			o.htmInscriptionSignature7("PUT");
			o.htmInscriptionSignature8("PUT");
			o.htmInscriptionSignature9("PUT");
			o.htmInscriptionSignature10("PUT");
			o.htmInscriptionDate1("PUT");
			o.htmInscriptionDate2("PUT");
			o.htmInscriptionDate3("PUT");
			o.htmInscriptionDate4("PUT");
			o.htmInscriptionDate5("PUT");
			o.htmInscriptionDate6("PUT");
			o.htmInscriptionDate7("PUT");
			o.htmInscriptionDate8("PUT");
			o.htmInscriptionDate9("PUT");
			o.htmInscriptionDate10("PUT");
		} g("div");
	}

	public void htmlFormPATCHInscriptionScolaire(InscriptionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCree("PATCH");
			o.htmModifie("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchive("PATCH");
			o.htmSupprime("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionApprouve("PATCH");
			o.htmInscriptionImmunisations("PATCH");
			o.htmInscriptionNomGroupe("PATCH");
			o.htmCustomerProfileId("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("PATCH");
			o.htmEnfantPropre("PATCH");
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
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("PATCH");
			o.htmEnfantEcolesPrecedemmentFrequentees("PATCH");
			o.htmFamilleCommentVousConnaissezEcole("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("PATCH");
			o.htmEnfantObjectifs("PATCH");
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
			o.htmUtilisateurCles("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantNomComplet("PATCH");
			o.htmEnfantNomCompletPrefere("PATCH");
			o.htmEnfantDateNaissance("PATCH");
			o.htmEcoleAddresse("PATCH");
			o.htmInscriptionDateFrais("PATCH");
			o.htmInscriptionNomsParents("PATCH");
			o.htmInscriptionSignature1("PATCH");
			o.htmInscriptionSignature2("PATCH");
			o.htmInscriptionSignature3("PATCH");
			o.htmInscriptionSignature4("PATCH");
			o.htmInscriptionSignature5("PATCH");
			o.htmInscriptionSignature6("PATCH");
			o.htmInscriptionSignature7("PATCH");
			o.htmInscriptionSignature8("PATCH");
			o.htmInscriptionSignature9("PATCH");
			o.htmInscriptionSignature10("PATCH");
			o.htmInscriptionDate1("PATCH");
			o.htmInscriptionDate2("PATCH");
			o.htmInscriptionDate3("PATCH");
			o.htmInscriptionDate4("PATCH");
			o.htmInscriptionDate5("PATCH");
			o.htmInscriptionDate6("PATCH");
			o.htmInscriptionDate7("PATCH");
			o.htmInscriptionDate8("PATCH");
			o.htmInscriptionDate9("PATCH");
			o.htmInscriptionDate10("PATCH");
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
			o.htmCustomerProfileId("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInscriptionPaimentComplet("Recherche");
			o.htmEnfantPropre("Recherche");
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
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantConditionsMedicales("Recherche");
			o.htmEnfantEcolesPrecedemmentFrequentees("Recherche");
			o.htmFamilleCommentVousConnaissezEcole("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnfantDescription("Recherche");
			o.htmEnfantObjectifs("Recherche");
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
			o.htmUtilisateurCles("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjetTitre("Recherche");
			o.htmEnfantNomComplet("Recherche");
			o.htmEnfantNomCompletPrefere("Recherche");
			o.htmEnfantDateNaissance("Recherche");
			o.htmEcoleAddresse("Recherche");
			o.htmInscriptionDateFrais("Recherche");
			o.htmInscriptionNomsParents("Recherche");
			o.htmInscriptionDate1("Recherche");
			o.htmInscriptionDate2("Recherche");
			o.htmInscriptionDate3("Recherche");
			o.htmInscriptionDate4("Recherche");
			o.htmInscriptionDate5("Recherche");
			o.htmInscriptionDate6("Recherche");
			o.htmInscriptionDate7("Recherche");
			o.htmInscriptionDate8("Recherche");
			o.htmInscriptionDate9("Recherche");
			o.htmInscriptionDate10("Recherche");
		} g("div");
	}

	@Override public void htmlBodyInscriptionPdfGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeInscriptionScolaire == null || listeInscriptionScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/inscription-pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue-gray w3-hover-blue-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("inscriptions").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue-gray ").f();
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
					{ e("a").a("href", "/inscription-pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue-gray w3-hover-blue-gray ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue-gray ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue-gray ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/inscription-pdf").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue-gray w3-hover-blue-gray ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequete).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listeInscriptionScolaire.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/inscription-pdf?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/inscription-pdf?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/inscription-pdf?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/inscription-pdf?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
				table1InscriptionPdfGenPage();
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
		g("div");
	}

	public void table1InscriptionPdfGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2InscriptionPdfGenPage();
		} g("table");
	}

	public void table2InscriptionPdfGenPage() {
		thead1InscriptionPdfGenPage();
		tbody1InscriptionPdfGenPage();
		tfoot1InscriptionPdfGenPage();
	}

	public void thead1InscriptionPdfGenPage() {
		{ e("thead").a("class", "w3-blue-gray w3-hover-blue-gray ").f();
			thead2InscriptionPdfGenPage();
		} g("thead");
	}

	public void thead2InscriptionPdfGenPage() {
			{ e("tr").f();
			if(getColonneCree()) {
				e("th").f().sx("crée").g("th");
			}
			if(getColonneObjetTitre()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1InscriptionPdfGenPage() {
		{ e("tbody").f();
			tbody2InscriptionPdfGenPage();
		} g("tbody");
	}

	public void tbody2InscriptionPdfGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listeInscriptionScolaire.getQueryResponse().getHighlighting();
		for(int i = 0; i < listeInscriptionScolaire.size(); i++) {
			InscriptionScolaire o = listeInscriptionScolaire.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/inscription-pdf/" + o.getPk();
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
							e("i").a("class", "fas fa-edit ").f().g("i");
							{ e("span").f();
								sx(o.strObjetTitre());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1InscriptionPdfGenPage() {
		{ e("tfoot").a("class", "w3-blue-gray w3-hover-blue-gray ").f();
			tfoot2InscriptionPdfGenPage();
		} g("tfoot");
	}

	public void tfoot2InscriptionPdfGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeInscriptionScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsInscriptionPdfGenPage() {
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listeInscriptionScolaire != null && listeInscriptionScolaire.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
						.a("id", "rechargerCetteInscriptionPdfGenPage")
						.a("onclick", "patchInscriptionScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCetteInscriptionPdfGenPage')); }, function() { ajouterErreur($('#rechargerCetteInscriptionPdfGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("recharger cette inscription");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#postInscriptionScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Créer une inscription");
			} g("button");
			{ e("div").a("id", "postInscriptionScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
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
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "postInscriptionScolaire($('#postInscriptionScolaireForm')); ")
								.f().sx("Créer une inscription")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#putInscriptionScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Dupliquer des inscriptions");
			} g("button");
			{ e("div").a("id", "putInscriptionScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putInscriptionScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Dupliquer des inscriptions").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							InscriptionScolaire o = new InscriptionScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PUT
							{ e("form").a("action", "").a("id", "putInscriptionScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTInscriptionScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "putInscriptionScolaire($('#putInscriptionScolaireFormulaireValeurs'), ", Optional.ofNullable(inscriptionScolaire).map(InscriptionScolaire::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Dupliquer des inscriptions")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#patchInscriptionScolaireModale').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modifier des inscriptions");
			} g("button");
			{ e("div").a("id", "patchInscriptionScolaireModale").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchInscriptionScolaireModale').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modifier des inscriptions").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							InscriptionScolaire o = new InscriptionScolaire();
							o.setRequeteSite_(requeteSite_);

							// FormulaireValeurs PATCH
							{ e("form").a("action", "").a("id", "patchInscriptionScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHInscriptionScolaire(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "patchInscriptionScolaire($('#patchInscriptionScolaireFormulaireFiltres'), $('#patchInscriptionScolaireFormulaireValeurs'), ", Optional.ofNullable(inscriptionScolaire).map(InscriptionScolaire::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modifier des inscriptions")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggereInscriptionPdfGenPage(this, null, listeInscriptionScolaire);
	}

	/**
	**/
	public static void htmlSuggereInscriptionPdfGenPage(MiseEnPage p, String id, ListeRecherche<InscriptionScolaire> listeInscriptionScolaire) {
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

			Integer rows1 = Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classeNomsCanoniques_", "archive_", "supprime_", "sessionId", "utilisateurCles"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listeInscriptionScolaire).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), InscriptionPdfGenPage.ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), InscriptionPdfGenPage.ROLES)
					) {
				if(listeInscriptionScolaire == null) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "rechargerToutesInscriptionPdfGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("onclick", "patchInscriptionScolaireVals([], {}, function() { ajouterLueur($('#rechargerToutesInscriptionPdfGenPage", id, "')); }, function() { ajouterErreur($('#rechargerToutesInscriptionPdfGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("recharger toutes les inscriptions");
						} p.g("button");
					} p.g("div");
				}
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("rechercher inscriptions : ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("placeholder", "rechercher inscriptions")
					.a("class", "suggereInscriptionScolaire w3-input w3-border w3-bar-item ")
					.a("name", "suggereInscriptionScolaire")
					.a("id", "suggereInscriptionScolaire", id)
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListInscriptionScolaire", id, "'), ", p.getRequeteSite_().getRequetePk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/inscription-pdf?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listeInscriptionScolaire != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
					.a("onclick", "window.location.href = '/inscription-pdf?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListInscriptionScolaire", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/inscription-pdf").a("class", "").f();
					p.e("i").a("class", "fas fa-edit ").f().g("i");
					p.sx("voir toutes les inscriptions");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
