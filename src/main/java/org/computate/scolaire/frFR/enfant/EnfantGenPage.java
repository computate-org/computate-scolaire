



package org.computate.scolaire.frFR.enfant;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.child.ChildGenPage
 **/
public class EnfantGenPage extends EnfantGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeEnfantScolaire(Couverture<ListeRecherche<EnfantScolaire>> c) {
	}

	protected void _enfantScolaire(Couverture<EnfantScolaire> c) {
		if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1)
			c.o(listeEnfantScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("enfants");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(enfantScolaire != null && enfantScolaire.getEnfantNomComplet() != null)
			c.o(enfantScolaire.getEnfantNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(enfantScolaire != null && enfantScolaire.getEnfantNomComplet() != null)
			c.o(enfantScolaire.getEnfantNomComplet());
		else if(enfantScolaire != null)
			c.o("");
		else if(listeEnfantScolaire == null || listeEnfantScolaire.size() == 0)
			c.o("aucun enfant trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/enfant");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/enfant-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("child");
	}

	@Override public void initLoinEnfantGenPage() {
		initEnfantGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsEnfantGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/EnfantPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptEnfantGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggereEnfantScolaireInscriptionCles($('#formEnfantScolaireInscriptionCles'), $('#listEnfantScolaireInscriptionCles_Page')); ");
		l("});");
	}

	public void htmlFormPageEnfantScolaire(EnfantScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("clé primaire").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strPk()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("crée").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strCree()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("modifié").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strModifie()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strObjetId()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_archive").a("class", "").f().sx("archivé").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setArchive")
										.a("name", "setArchive")
										.a("id", "Page_archive")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setArchive', $(this).prop('checked'), function() { ajouterLueur($('#Page_archive')); }, function() { ajouterErreur($('#Page_archive')); }); ")
										;
										if(o.getArchive() != null && o.getArchive())
											a("checked", "checked");
									fg();

								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_supprime").a("class", "").f().sx("supprimé").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSupprime")
										.a("name", "setSupprime")
										.a("id", "Page_supprime")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setSupprime', $(this).prop('checked'), function() { ajouterLueur($('#Page_supprime')); }, function() { ajouterErreur($('#Page_supprime')); }); ")
										;
										if(o.getSupprime() != null && o.getSupprime())
											a("checked", "checked");
									fg();

								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_personnePrenom").a("class", "").f().sx("prénom").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "prénom")
										.a("class", "setPersonnePrenom w3-input w3-border ")
										.a("name", "setPersonnePrenom")
										.a("id", "Page_personnePrenom")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonnePrenom', $(this).val(), function() { ajouterLueur($('#Page_personnePrenom')); }, function() { ajouterErreur($('#Page_personnePrenom')); }); ")
										.a("value", o.strPersonnePrenom())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_personnePrenom')); $('#Page_personnePrenom').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonnePrenom', null, function() { ajouterLueur($('#Page_personnePrenom')); }, function() { ajouterErreur($('#Page_personnePrenom')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireFamilleNom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireFamilleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_familleNom").a("class", "").f().sx("nom de famille").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "nom de famille")
										.a("class", "setFamilleNom w3-input w3-border ")
										.a("name", "setFamilleNom")
										.a("id", "Page_familleNom")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setFamilleNom', $(this).val(), function() { ajouterLueur($('#Page_familleNom')); }, function() { ajouterErreur($('#Page_familleNom')); }); ")
										.a("value", o.strFamilleNom())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_familleNom')); $('#Page_familleNom').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setFamilleNom', null, function() { ajouterLueur($('#Page_familleNom')); }, function() { ajouterErreur($('#Page_familleNom')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenomPrefere").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenomPrefere").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_personnePrenomPrefere").a("class", "").f().sx("prénom préferé").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "prénom préferé")
										.a("class", "setPersonnePrenomPrefere w3-input w3-border ")
										.a("name", "setPersonnePrenomPrefere")
										.a("id", "Page_personnePrenomPrefere")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonnePrenomPrefere', $(this).val(), function() { ajouterLueur($('#Page_personnePrenomPrefere')); }, function() { ajouterErreur($('#Page_personnePrenomPrefere')); }); ")
										.a("value", o.strPersonnePrenomPrefere())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_personnePrenomPrefere')); $('#Page_personnePrenomPrefere').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonnePrenomPrefere', null, function() { ajouterLueur($('#Page_personnePrenomPrefere')); }, function() { ajouterErreur($('#Page_personnePrenomPrefere')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolairePersonneDateNaissance").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonneDateNaissance").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalDate val = o.getPersonneDateNaissance();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_personneDateNaissance").a("class", "").f().sx("date de naissance").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row  ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border datepicker ")
										.a("placeholder", "DD-MM-YYYY")
										.a("data-timeformat", "DD-MM-YYYY")
										.a("id", "Page_personneDateNaissance")
										.a("onclick", "enleverLueur($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
										.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonneDateNaissance', s, function() { ajouterLueur($('#Page_personneDateNaissance')); }, function() { ajouterErreur($('#Page_personneDateNaissance')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_personneDateNaissance')); $('#Page_personneDateNaissance').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonneDateNaissance', null, function() { ajouterLueur($('#Page_personneDateNaissance')); }, function() { ajouterErreur($('#Page_personneDateNaissance')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("âge").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strPersonneAgeEnSeptembre()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_enfantVaccinsAJour").a("class", "").f().sx("vaccins à jour").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setEnfantVaccinsAJour")
										.a("name", "setEnfantVaccinsAJour")
										.a("id", "Page_enfantVaccinsAJour")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantVaccinsAJour', $(this).prop('checked'), function() { ajouterLueur($('#Page_enfantVaccinsAJour')); }, function() { ajouterErreur($('#Page_enfantVaccinsAJour')); }); ")
										;
										if(o.getEnfantVaccinsAJour() != null && o.getEnfantVaccinsAJour())
											a("checked", "checked");
									fg();

								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantPropre").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantPropre").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_enfantPropre").a("class", "").f().sx("propre").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setEnfantPropre")
										.a("name", "setEnfantPropre")
										.a("id", "Page_enfantPropre")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantPropre', $(this).prop('checked'), function() { ajouterLueur($('#Page_enfantPropre')); }, function() { ajouterErreur($('#Page_enfantPropre')); }); ")
										;
										if(o.getEnfantPropre() != null && o.getEnfantPropre())
											a("checked", "checked");
									fg();

								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_enfantConditionsMedicales").a("class", "").f().sx("conditions médicales").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "conditions médicales")
										.a("class", "setEnfantConditionsMedicales w3-input w3-border ")
										.a("name", "setEnfantConditionsMedicales")
										.a("id", "Page_enfantConditionsMedicales")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantConditionsMedicales', $(this).val(), function() { ajouterLueur($('#Page_enfantConditionsMedicales')); }, function() { ajouterErreur($('#Page_enfantConditionsMedicales')); }); ")
										.a("value", o.strEnfantConditionsMedicales())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_enfantConditionsMedicales')); $('#Page_enfantConditionsMedicales').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantConditionsMedicales', null, function() { ajouterLueur($('#Page_enfantConditionsMedicales')); }, function() { ajouterErreur($('#Page_enfantConditionsMedicales')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_enfantEcolesPrecedemmentFrequentees").a("class", "").f().sx("écoles précedemment fréqentées").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "écoles précedemment fréqentées")
										.a("class", "setEnfantEcolesPrecedemmentFrequentees w3-input w3-border ")
										.a("name", "setEnfantEcolesPrecedemmentFrequentees")
										.a("id", "Page_enfantEcolesPrecedemmentFrequentees")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantEcolesPrecedemmentFrequentees', $(this).val(), function() { ajouterLueur($('#Page_enfantEcolesPrecedemmentFrequentees')); }, function() { ajouterErreur($('#Page_enfantEcolesPrecedemmentFrequentees')); }); ")
										.a("value", o.strEnfantEcolesPrecedemmentFrequentees())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_enfantEcolesPrecedemmentFrequentees')); $('#Page_enfantEcolesPrecedemmentFrequentees').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantEcolesPrecedemmentFrequentees', null, function() { ajouterLueur($('#Page_enfantEcolesPrecedemmentFrequentees')); }, function() { ajouterErreur($('#Page_enfantEcolesPrecedemmentFrequentees')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantDescription").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantDescription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_enfantDescription").a("class", "").f().sx("description").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("textarea")
										.a("placeholder", "description")
										.a("class", "setEnfantDescription w3-input w3-border ")
										.a("name", "setEnfantDescription")
										.a("id", "Page_enfantDescription")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantDescription', $(this).val(), function() { ajouterLueur($('#Page_enfantDescription')); }, function() { ajouterErreur($('#Page_enfantDescription')); }); ")
									.f().sx(o.strEnfantDescription()).g("textarea");

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_enfantDescription')); $('#Page_enfantDescription').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantDescription', null, function() { ajouterLueur($('#Page_enfantDescription')); }, function() { ajouterErreur($('#Page_enfantDescription')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantObjectifs").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantObjectifs").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-green ").f();
								e("label").a("for", "Page_enfantObjectifs").a("class", "").f().sx("objectifs").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("textarea")
										.a("placeholder", "objectifs")
										.a("class", "setEnfantObjectifs w3-input w3-border ")
										.a("name", "setEnfantObjectifs")
										.a("id", "Page_enfantObjectifs")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantObjectifs', $(this).val(), function() { ajouterLueur($('#Page_enfantObjectifs')); }, function() { ajouterErreur($('#Page_enfantObjectifs')); }); ")
									.f().sx(o.strEnfantObjectifs()).g("textarea");

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#Page_enfantObjectifs')); $('#Page_enfantObjectifs').val(null); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setEnfantObjectifs', null, function() { ajouterLueur($('#Page_enfantObjectifs')); }, function() { ajouterErreur($('#Page_enfantObjectifs')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEnfantScolaireInscriptionCles").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurEnfantCle")
							.a("class", "valeurEnfantCle ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEnfantScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
									e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
									sx("inscriptions");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a cet enfant");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "inscriptions")
											.a("class", "valeurObjetSuggere suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setInscriptionCles")
											.a("id", "Page_inscriptionCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereEnfantScolaireInscriptionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'EnfantScolaireInscriptionCles'), $('#listEnfantScolaireInscriptionCles_Page')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listEnfantScolaireInscriptionCles_Page").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
											.a("onclick", "postInscriptionScolaireVals({ enfantCle: \"", o.getPk(), "\" }, function() { patchEnfantScolaireVals([{ name: 'fq', value: 'pk:", o.getPk(), "' }], {}, function() { suggereEnfantScolaireInscriptionCles($('#' + ($('#Page_inscriptionCles').val() ? 'suggere' : 'form') + 'EnfantScolaireInscriptionCles'), $('#listEnfantScolaireInscriptionCles_Page')); var $e = $('#Page_inscriptionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_inscriptionCles')); }); }, function() { ajouterErreur($('#Page_inscriptionCles')); });")
											.f().sx("ajouter une inscription")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTEnfantScolaire(EnfantScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjetId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_archive").a("class", "").f().sx("archivé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

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

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_supprime").a("class", "").f().sx("supprimé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

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

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_personnePrenom").a("class", "").f().sx("prénom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prénom")
									.a("class", "valeurPersonnePrenom w3-input w3-border ")
									.a("name", "personnePrenom")
									.a("id", "POST_personnePrenom")
									.a("value", o.strPersonnePrenom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireFamilleNom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireFamilleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_familleNom").a("class", "").f().sx("nom de famille").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "nom de famille")
									.a("class", "valeurFamilleNom w3-input w3-border ")
									.a("name", "familleNom")
									.a("id", "POST_familleNom")
									.a("value", o.strFamilleNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenomPrefere").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenomPrefere").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_personnePrenomPrefere").a("class", "").f().sx("prénom préferé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prénom préferé")
									.a("class", "valeurPersonnePrenomPrefere w3-input w3-border ")
									.a("name", "personnePrenomPrefere")
									.a("id", "POST_personnePrenomPrefere")
									.a("value", o.strPersonnePrenomPrefere())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonneDateNaissance").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonneDateNaissance").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getPersonneDateNaissance();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_personneDateNaissance").a("class", "").f().sx("date de naissance").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "POST_personneDateNaissance")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonneDateNaissance', s, function() { ajouterLueur($('#POST_personneDateNaissance')); }, function() { ajouterErreur($('#POST_personneDateNaissance')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("âge").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPersonneAgeEnSeptembre()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_enfantVaccinsAJour").a("class", "").f().sx("vaccins à jour").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurEnfantVaccinsAJour")
									.a("name", "enfantVaccinsAJour")
									.a("id", "POST_enfantVaccinsAJour")
									;
									if(o.getEnfantVaccinsAJour() != null && o.getEnfantVaccinsAJour())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantPropre").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantPropre").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_enfantPropre").a("class", "").f().sx("propre").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurEnfantPropre")
									.a("name", "enfantPropre")
									.a("id", "POST_enfantPropre")
									;
									if(o.getEnfantPropre() != null && o.getEnfantPropre())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_enfantConditionsMedicales").a("class", "").f().sx("conditions médicales").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "conditions médicales")
									.a("class", "valeurEnfantConditionsMedicales w3-input w3-border ")
									.a("name", "enfantConditionsMedicales")
									.a("id", "POST_enfantConditionsMedicales")
									.a("value", o.strEnfantConditionsMedicales())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_enfantEcolesPrecedemmentFrequentees").a("class", "").f().sx("écoles précedemment fréqentées").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "écoles précedemment fréqentées")
									.a("class", "valeurEnfantEcolesPrecedemmentFrequentees w3-input w3-border ")
									.a("name", "enfantEcolesPrecedemmentFrequentees")
									.a("id", "POST_enfantEcolesPrecedemmentFrequentees")
									.a("value", o.strEnfantEcolesPrecedemmentFrequentees())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantDescription").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantDescription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_enfantDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "description")
									.a("class", "valeurEnfantDescription w3-input w3-border ")
									.a("name", "enfantDescription")
									.a("id", "POST_enfantDescription")
								.f().sx(o.strEnfantDescription()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantObjectifs").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantObjectifs").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "POST_enfantObjectifs").a("class", "").f().sx("objectifs").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "objectifs")
									.a("class", "valeurEnfantObjectifs w3-input w3-border ")
									.a("name", "enfantObjectifs")
									.a("id", "POST_enfantObjectifs")
								.f().sx(o.strEnfantObjectifs()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("inscriptions").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionCles()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHEnfantScolaire(EnfantScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjetId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_archive").a("class", "").f().sx("archivé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

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

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_supprime").a("class", "").f().sx("supprimé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

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

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_personnePrenom").a("class", "").f().sx("prénom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prénom")
									.a("class", "setPersonnePrenom w3-input w3-border ")
									.a("name", "setPersonnePrenom")
									.a("id", "PATCH_personnePrenom")
									.a("value", o.strPersonnePrenom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireFamilleNom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireFamilleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_familleNom").a("class", "").f().sx("nom de famille").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "nom de famille")
									.a("class", "setFamilleNom w3-input w3-border ")
									.a("name", "setFamilleNom")
									.a("id", "PATCH_familleNom")
									.a("value", o.strFamilleNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenomPrefere").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenomPrefere").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_personnePrenomPrefere").a("class", "").f().sx("prénom préferé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prénom préferé")
									.a("class", "setPersonnePrenomPrefere w3-input w3-border ")
									.a("name", "setPersonnePrenomPrefere")
									.a("id", "PATCH_personnePrenomPrefere")
									.a("value", o.strPersonnePrenomPrefere())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonneDateNaissance").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonneDateNaissance").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getPersonneDateNaissance();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_personneDateNaissance").a("class", "").f().sx("date de naissance").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "PATCH_personneDateNaissance")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonneDateNaissance', s, function() { ajouterLueur($('#PATCH_personneDateNaissance')); }, function() { ajouterErreur($('#PATCH_personneDateNaissance')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("âge").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPersonneAgeEnSeptembre()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_enfantVaccinsAJour").a("class", "").f().sx("vaccins à jour").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setEnfantVaccinsAJour")
									.a("name", "setEnfantVaccinsAJour")
									.a("id", "PATCH_enfantVaccinsAJour")
									;
									if(o.getEnfantVaccinsAJour() != null && o.getEnfantVaccinsAJour())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantPropre").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantPropre").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_enfantPropre").a("class", "").f().sx("propre").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setEnfantPropre")
									.a("name", "setEnfantPropre")
									.a("id", "PATCH_enfantPropre")
									;
									if(o.getEnfantPropre() != null && o.getEnfantPropre())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_enfantConditionsMedicales").a("class", "").f().sx("conditions médicales").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "conditions médicales")
									.a("class", "setEnfantConditionsMedicales w3-input w3-border ")
									.a("name", "setEnfantConditionsMedicales")
									.a("id", "PATCH_enfantConditionsMedicales")
									.a("value", o.strEnfantConditionsMedicales())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_enfantEcolesPrecedemmentFrequentees").a("class", "").f().sx("écoles précedemment fréqentées").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "écoles précedemment fréqentées")
									.a("class", "setEnfantEcolesPrecedemmentFrequentees w3-input w3-border ")
									.a("name", "setEnfantEcolesPrecedemmentFrequentees")
									.a("id", "PATCH_enfantEcolesPrecedemmentFrequentees")
									.a("value", o.strEnfantEcolesPrecedemmentFrequentees())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantDescription").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantDescription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_enfantDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "description")
									.a("class", "setEnfantDescription w3-input w3-border ")
									.a("name", "setEnfantDescription")
									.a("id", "PATCH_enfantDescription")
								.f().sx(o.strEnfantDescription()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantObjectifs").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantObjectifs").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "PATCH_enfantObjectifs").a("class", "").f().sx("objectifs").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "objectifs")
									.a("class", "setEnfantObjectifs w3-input w3-border ")
									.a("name", "setEnfantObjectifs")
									.a("id", "PATCH_enfantObjectifs")
								.f().sx(o.strEnfantObjectifs()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("inscriptions").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionCles()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("nom").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnfantNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheEnfantScolaire(EnfantScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjetId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_archive").a("class", "").f().sx("archivé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

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

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_supprime").a("class", "").f().sx("supprimé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

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

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_personnePrenom").a("class", "").f().sx("prénom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prénom")
									.a("class", "valeurPersonnePrenom w3-input w3-border ")
									.a("name", "personnePrenom")
									.a("id", "Recherche_personnePrenom")
									.a("value", o.strPersonnePrenom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireFamilleNom").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireFamilleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_familleNom").a("class", "").f().sx("nom de famille").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "nom de famille")
									.a("class", "valeurFamilleNom w3-input w3-border ")
									.a("name", "familleNom")
									.a("id", "Recherche_familleNom")
									.a("value", o.strFamilleNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonnePrenomPrefere").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonnePrenomPrefere").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_personnePrenomPrefere").a("class", "").f().sx("prénom préferé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prénom préferé")
									.a("class", "valeurPersonnePrenomPrefere w3-input w3-border ")
									.a("name", "personnePrenomPrefere")
									.a("id", "Recherche_personnePrenomPrefere")
									.a("value", o.strPersonnePrenomPrefere())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolairePersonneDateNaissance").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolairePersonneDateNaissance").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getPersonneDateNaissance();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_personneDateNaissance").a("class", "").f().sx("date de naissance").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "Recherche_personneDateNaissance")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchEnfantScolaireVal([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=\"pk\"]').val() }], 'setPersonneDateNaissance', s, function() { ajouterLueur($('#Recherche_personneDateNaissance')); }, function() { ajouterErreur($('#Recherche_personneDateNaissance')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("âge").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPersonneAgeEnSeptembre()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantVaccinsAJour").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_enfantVaccinsAJour").a("class", "").f().sx("vaccins à jour").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurEnfantVaccinsAJour")
									.a("name", "enfantVaccinsAJour")
									.a("id", "Recherche_enfantVaccinsAJour")
									;
									if(o.getEnfantVaccinsAJour() != null && o.getEnfantVaccinsAJour())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantPropre").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantPropre").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_enfantPropre").a("class", "").f().sx("propre").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurEnfantPropre")
									.a("name", "enfantPropre")
									.a("id", "Recherche_enfantPropre")
									;
									if(o.getEnfantPropre() != null && o.getEnfantPropre())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantConditionsMedicales").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_enfantConditionsMedicales").a("class", "").f().sx("conditions médicales").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "conditions médicales")
									.a("class", "valeurEnfantConditionsMedicales w3-input w3-border ")
									.a("name", "enfantConditionsMedicales")
									.a("id", "Recherche_enfantConditionsMedicales")
									.a("value", o.strEnfantConditionsMedicales())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantEcolesPrecedemmentFrequentees").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_enfantEcolesPrecedemmentFrequentees").a("class", "").f().sx("écoles précedemment fréqentées").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "écoles précedemment fréqentées")
									.a("class", "valeurEnfantEcolesPrecedemmentFrequentees w3-input w3-border ")
									.a("name", "enfantEcolesPrecedemmentFrequentees")
									.a("id", "Recherche_enfantEcolesPrecedemmentFrequentees")
									.a("value", o.strEnfantEcolesPrecedemmentFrequentees())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantDescription").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantDescription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_enfantDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "description")
									.a("class", "valeurEnfantDescription w3-input w3-border ")
									.a("name", "enfantDescription")
									.a("id", "Recherche_enfantDescription")
								.f().sx(o.strEnfantDescription()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEnfantScolaireEnfantObjectifs").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEnfantScolaireEnfantObjectifs").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", "Recherche_enfantObjectifs").a("class", "").f().sx("objectifs").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "objectifs")
									.a("class", "valeurEnfantObjectifs w3-input w3-border ")
									.a("name", "enfantObjectifs")
									.a("id", "Recherche_enfantObjectifs")
								.f().sx(o.strEnfantObjectifs()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("inscriptions").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionCles()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("nom").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEnfantNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyEnfantGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeEnfantScolaire == null || listeEnfantScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("enfants").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun enfant trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			EnfantScolaire o = listeEnfantScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/enfant").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeEnfantScolaire.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeEnfantScolaire.getQuery(), "_suggested", "");
					Integer rows1 = listeEnfantScolaire.getRows();
					Integer start1 = listeEnfantScolaire.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/enfant?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enfant?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-green w3-hover-green ").f();
					{ e("tr").f();
						e("th").f().sx("nom").g("th");
						e("th").f().sx("crée").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeEnfantScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeEnfantScolaire.size(); i++) {
						EnfantScolaire o = listeEnfantScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enfant/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strEnfantNomComplet());
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

		if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			EnfantScolaire o = listeEnfantScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "EnfantScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageEnfantScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsEnfantGenPage();
		htmlSuggereEnfantGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsEnfantGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("id", "rechargerCeEnfantGenPage")
				.a("onclick", "patchEnfantScolaireVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeEnfantGenPage')); }, function() { ajouterErreur($('#rechargerCeEnfantGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger cet enfant");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#postEnfantScolaireModale').show(); ")
			.f().sx("Créer un enfant")
		.g("button");
		{ e("div").a("id", "postEnfantScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postEnfantScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer un enfant").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EnfantScolaire o = new EnfantScolaire();

					// Form POST
					{ e("div").a("id", "postEnfantScolaireForm").f();
						htmlFormPOSTEnfantScolaire(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "postEnfantScolaire($('#postEnfantScolaireForm')); ")
						.f().sx("Créer un enfant")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchEnfantScolaireModale').show(); ")
			.f().sx("Modifier des enfants")
		.g("button");
		{ e("div").a("id", "patchEnfantScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEnfantScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des enfants").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EnfantScolaire o = new EnfantScolaire();

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchEnfantScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheEnfantScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "rechercheEnfantScolaire($('#patchEnfantScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des un enfant")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchEnfantScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHEnfantScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchEnfantScolaire($('#patchEnfantScolaireFormulaireFiltres'), $('#patchEnfantScolaireFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des enfants")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeEnfantScolaire != null && listeEnfantScolaire.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#deleteEnfantScolaireModale').show(); ")
				.f().sx("Supprimer des enfants")
			.g("button");
			{ e("div").a("id", "deleteEnfantScolaireModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-green ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteEnfantScolaireModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des enfants").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						EnfantScolaire o = new EnfantScolaire();

						// Form DELETE
						{ e("div").a("id", "deleteEnfantScolaireForm").f();
							htmlFormPATCHEnfantScolaire(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
							.a("onclick", "deleteEnfantScolaire(", o.getPk(), "); ")
							.f().sx("Supprimer des enfants")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestChildGenPage
	 * r: "/enfant"
	 * r.enUS: "/child"
	 * r: "voir tous les enfants"
	 * r.enUS: "see all the children"
	 * r: "rechargerEnfantGenPage"
	 * r.enUS: "refreshChildGenPage"
	 * r: "recharger tous les enfants"
	 * r.enUS: "refresh all the children"
	 * r: "rechercher enfants : "
	 * r.enUS: "search children: "
	 * r: "suggereFormEnfantScolaire"
	 * r.enUS: "suggestFormSchoolChild"
	 * r: "rechercher enfants"
	 * r.enUS: "search children"
	 * r: "suggereEnfantScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolChild w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereEnfantScolaire"
	 * r.enUS: "suggestSchoolChild"
	 * r: patchEnfantScolaireVals
	 * r.enUS: patchSchoolChildVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerEnfantGenPage
	 * r.enUS: refreshChildGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereEnfantScolaireObjetSuggere
	 * r.enUS: suggestSchoolChildObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListEnfantScolaire'
	 * r.enUS: '#suggestListSchoolChild'
	 * r: "suggereListEnfantScolaire"
	 * r.enUS: "suggestListSchoolChild"
	**/
	public static void htmlSuggereEnfantGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/enfant").a("class", "").f();
					p.e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
					p.sx("voir tous les enfants");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerTousEnfantGenPage", id).a("href", "/enfant").a("class", "").a("onclick", "patchEnfantScolaireVals([], {}, function() { ajouterLueur($('#rechargerTousEnfantGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousEnfantGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger tous les enfants");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher enfants : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormEnfantScolaire", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/enfant?q=objetSuggere:' + encodeURIComponent($('#suggereEnfantScolaire", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher enfants")
							.a("class", "suggereEnfantScolaire w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereEnfantScolaire")
							.a("id", "suggereEnfantScolaire", id)
							.a("autocomplete", "off")
							.a("oninput", "suggereEnfantScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListEnfantScolaire", id, "')); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListEnfantScolaire", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
