



package org.computate.scolaire.frFR.saison;

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
public class SaisonGenPage extends SaisonGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeSaisonScolaire(Couverture<ListeRecherche<SaisonScolaire>> c) {
	}

	protected void _saisonScolaire(Couverture<SaisonScolaire> c) {
		if(listeSaisonScolaire != null && listeSaisonScolaire.size() == 1)
			c.o(listeSaisonScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(saisonScolaire != null)
			c.o("une saison");
		else if(listeSaisonScolaire == null || listeSaisonScolaire.size() == 0)
			c.o("aucune saison trouvée");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(saisonScolaire != null)
			c.o("");
		else if(listeSaisonScolaire == null || listeSaisonScolaire.size() == 0)
			c.o("aucune saison trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/frFR/saison");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/frFR/saison-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("duotone");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("sun-o");
	}

	@Override public void initLoinSaisonGenPage() {
		initSaisonGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsSaisonGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/SaisonPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AnneePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/SessionPage.js").f().g("script");
	}

	@Override public void htmlScriptSaisonGenPage() {
		l("$(document).ready(function() {");
		tl(1, "rechercherSaisonScolaireSessionCles($('#formSaisonScolaireSessionCles'), $('#listSaisonScolaireSessionCles')); ");
		tl(1, "rechercherSaisonScolaireAnneeCle($('#formSaisonScolaireAnneeCle'), $('#listSaisonScolaireAnneeCle')); ");
		l("});");
	}

	public void htmlFormPageSaisonScolaire(SaisonScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strSaisonId()).g("span");
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
					{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalDate val = o.getSaisonJourDebut();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_saisonJourDebut").a("class", "").f().sx("début de la saison").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row  ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border datepicker ")
										.a("placeholder", "DD-MM-YYYY")
										.a("data-timeformat", "DD-MM-YYYY")
										.a("id", "Page_saisonJourDebut")
										.a("onclick", "enleverLueur($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
										.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonJourDebut', s, function() { ajouterLueur($('#Page_saisonJourDebut')); }, function() { ajouterErreur($('#Page_saisonJourDebut')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "enleverLueur($('#Page_saisonJourDebut')); $('#Page_saisonJourDebut').val(null); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonJourDebut', null, $('#Page_saisonJourDebut'), function() { ajouterLueur($('#Page_saisonJourDebut')); }, function() { ajouterErreur($('#Page_saisonJourDebut')); }); ")
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
					{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSaisonEte")
										.a("name", "setSaisonEte")
										.a("id", "Page_saisonEte")
										.a("onchange", "patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonEte', $(this).prop('checked'), function() { ajouterLueur($('#Page_saisonEte')); }, function() { ajouterErreur($('#Page_saisonEte')); }); ")
										;
										if(o.getSaisonEte() != null && o.getSaisonEte())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_saisonEte").a("class", "").f().sx("été").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "enleverLueur($('#Page_saisonEte')); $('#Page_saisonEte').val(null); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonEte', null, $('#Page_saisonEte'), function() { ajouterLueur($('#Page_saisonEte')); }, function() { ajouterErreur($('#Page_saisonEte')); }); ")
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
					{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSaisonHiver")
										.a("name", "setSaisonHiver")
										.a("id", "Page_saisonHiver")
										.a("onchange", "patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonHiver', $(this).prop('checked'), function() { ajouterLueur($('#Page_saisonHiver')); }, function() { ajouterErreur($('#Page_saisonHiver')); }); ")
										;
										if(o.getSaisonHiver() != null && o.getSaisonHiver())
											a("checked", "checked");
									fg();

								} g("div");
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_saisonHiver").a("class", "").f().sx("hiver").g("label");
							} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "enleverLueur($('#Page_saisonHiver')); $('#Page_saisonHiver').val(null); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonHiver', null, $('#Page_saisonHiver'), function() { ajouterLueur($('#Page_saisonHiver')); }, function() { ajouterErreur($('#Page_saisonHiver')); }); ")
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
					{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_saisonFraisInscription").a("class", "").f().sx("frais d'inscription").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "frais d'inscription")
										.a("class", "setSaisonFraisInscription w3-input w3-border ")
										.a("name", "setSaisonFraisInscription")
										.a("id", "Page_saisonFraisInscription")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonFraisInscription', $(this).val(), function() { ajouterLueur($('#Page_saisonFraisInscription')); }, function() { ajouterErreur($('#Page_saisonFraisInscription')); }); ")
										.a("value", o.strSaisonFraisInscription())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "enleverLueur($('#Page_saisonFraisInscription')); $('#Page_saisonFraisInscription').val(null); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonFraisInscription', null, $('#Page_saisonFraisInscription'), function() { ajouterLueur($('#Page_saisonFraisInscription')); }, function() { ajouterErreur($('#Page_saisonFraisInscription')); }); ")
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
					{ e("form").a("action", "").a("id", "formSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurSaisonCle")
							.a("class", "valeurSaisonCle ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_sessionCles").a("class", "").f().sx("sessions").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "sessions")
											.a("title", "Les sessions scolaires de la saison scolaire. ")
											.a("class", "valeurObjetSuggere suggereSessionCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setSessionCles")
											.a("id", "Page_sessionCles")
											.a("autocomplete", "off")
											.a("oninput", "rechercherSaisonScolaireSessionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireSessionCles'), $('#listSaisonScolaireSessionCles')); ")
										.fg();

									} g("div");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postSessionScolaireVals({ saisonCle: ", o.getPk(), " }, function() { var $e = $('#Page_sessionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_sessionCles')); }); ")
											.f().sx("add")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireSessionCles").f();
									} g("ul");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurSaisonCles")
							.a("class", "valeurSaisonCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-yellow ").f();
								e("label").a("for", "Page_anneeCle").a("class", "").f().sx("année").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "année")
											.a("title", "L'année scolaire de la saison scolaire. ")
											.a("class", "valeurObjetSuggere suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setAnneeCle")
											.a("id", "Page_anneeCle")
											.a("autocomplete", "off")
											.a("oninput", "rechercherSaisonScolaireAnneeCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireAnneeCle'), $('#listSaisonScolaireAnneeCle')); ")
										.fg();

									} g("div");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postAnneeScolaireVals({ saisonCles: ", o.getPk(), " }, function() { var $e = $('#Page_anneeCle'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_anneeCle')); }); ")
											.f().sx("add")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireAnneeCle").f();
									} g("ul");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTSaisonScolaire(SaisonScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSaisonId()).g("span");
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
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSaisonJourDebut();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_saisonJourDebut").a("class", "").f().sx("début de la saison").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "POST_saisonJourDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonJourDebut', s, function() { ajouterLueur($('#POST_saisonJourDebut')); }, function() { ajouterErreur($('#POST_saisonJourDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSaisonEte")
									.a("name", "saisonEte")
									.a("id", "POST_saisonEte")
									;
									if(o.getSaisonEte() != null && o.getSaisonEte())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_saisonEte").a("class", "").f().sx("été").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSaisonHiver")
									.a("name", "saisonHiver")
									.a("id", "POST_saisonHiver")
									;
									if(o.getSaisonHiver() != null && o.getSaisonHiver())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_saisonHiver").a("class", "").f().sx("hiver").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_saisonFraisInscription").a("class", "").f().sx("frais d'inscription").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "frais d'inscription")
									.a("class", "valeurSaisonFraisInscription w3-input w3-border ")
									.a("name", "saisonFraisInscription")
									.a("id", "POST_saisonFraisInscription")
									.a("value", o.strSaisonFraisInscription())
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
				{ e("form").a("action", "").a("id", "formSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurSaisonCle")
						.a("class", "valeurSaisonCle ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_sessionCles").a("class", "").f().sx("sessions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "sessions")
										.a("title", "Les sessions scolaires de la saison scolaire. ")
										.a("class", "valeurObjetSuggere suggereSessionCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setSessionCles")
										.a("id", "POST_sessionCles")
										.a("autocomplete", "off")
										.a("oninput", "rechercherSaisonScolaireSessionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireSessionCles'), $('#listSaisonScolaireSessionCles')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSessionScolaireVals({ saisonCle: ", o.getPk(), " }, function() { var $e = $('#POST_sessionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_sessionCles')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireSessionCles").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurSaisonCles")
						.a("class", "valeurSaisonCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "POST_anneeCle").a("class", "").f().sx("année").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "année")
										.a("title", "L'année scolaire de la saison scolaire. ")
										.a("class", "valeurObjetSuggere suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setAnneeCle")
										.a("id", "POST_anneeCle")
										.a("autocomplete", "off")
										.a("oninput", "rechercherSaisonScolaireAnneeCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireAnneeCle'), $('#listSaisonScolaireAnneeCle')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postAnneeScolaireVals({ saisonCles: ", o.getPk(), " }, function() { var $e = $('#POST_anneeCle'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_anneeCle')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireAnneeCle").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHSaisonScolaire(SaisonScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSaisonId()).g("span");
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
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSaisonJourDebut();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_saisonJourDebut").a("class", "").f().sx("début de la saison").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "PATCH_saisonJourDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonJourDebut', s, function() { ajouterLueur($('#PATCH_saisonJourDebut')); }, function() { ajouterErreur($('#PATCH_saisonJourDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setSaisonEte")
									.a("name", "setSaisonEte")
									.a("id", "PATCH_saisonEte")
									;
									if(o.getSaisonEte() != null && o.getSaisonEte())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_saisonEte").a("class", "").f().sx("été").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setSaisonHiver")
									.a("name", "setSaisonHiver")
									.a("id", "PATCH_saisonHiver")
									;
									if(o.getSaisonHiver() != null && o.getSaisonHiver())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_saisonHiver").a("class", "").f().sx("hiver").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_saisonFraisInscription").a("class", "").f().sx("frais d'inscription").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "frais d'inscription")
									.a("class", "setSaisonFraisInscription w3-input w3-border ")
									.a("name", "setSaisonFraisInscription")
									.a("id", "PATCH_saisonFraisInscription")
									.a("value", o.strSaisonFraisInscription())
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
				{ e("form").a("action", "").a("id", "formSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurSaisonCle")
						.a("class", "valeurSaisonCle ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_sessionCles").a("class", "").f().sx("sessions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "sessions")
										.a("title", "Les sessions scolaires de la saison scolaire. ")
										.a("class", "valeurObjetSuggere suggereSessionCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setSessionCles")
										.a("id", "PATCH_sessionCles")
										.a("autocomplete", "off")
										.a("oninput", "rechercherSaisonScolaireSessionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireSessionCles'), $('#listSaisonScolaireSessionCles')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSessionScolaireVals({ saisonCle: ", o.getPk(), " }, function() { var $e = $('#PATCH_sessionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_sessionCles')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireSessionCles").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurSaisonCles")
						.a("class", "valeurSaisonCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "PATCH_anneeCle").a("class", "").f().sx("année").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "année")
										.a("title", "L'année scolaire de la saison scolaire. ")
										.a("class", "valeurObjetSuggere suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setAnneeCle")
										.a("id", "PATCH_anneeCle")
										.a("autocomplete", "off")
										.a("oninput", "rechercherSaisonScolaireAnneeCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireAnneeCle'), $('#listSaisonScolaireAnneeCle')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postAnneeScolaireVals({ saisonCles: ", o.getPk(), " }, function() { var $e = $('#PATCH_anneeCle'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_anneeCle')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireAnneeCle").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheSaisonScolaire(SaisonScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
					{ e("div").a("class", "w3-cell-row w3-yellow ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strSaisonId()).g("span");
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
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonJourDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalDate val = o.getSaisonJourDebut();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_saisonJourDebut").a("class", "").f().sx("début de la saison").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border datepicker ")
									.a("placeholder", "DD-MM-YYYY")
									.a("data-timeformat", "DD-MM-YYYY")
									.a("id", "Recherche_saisonJourDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=\"pk\"]').val() }], 'setSaisonJourDebut', s, function() { ajouterLueur($('#Recherche_saisonJourDebut')); }, function() { ajouterErreur($('#Recherche_saisonJourDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonEte").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSaisonEte")
									.a("name", "saisonEte")
									.a("id", "Recherche_saisonEte")
									;
									if(o.getSaisonEte() != null && o.getSaisonEte())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_saisonEte").a("class", "").f().sx("été").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonHiver").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSaisonHiver")
									.a("name", "saisonHiver")
									.a("id", "Recherche_saisonHiver")
									;
									if(o.getSaisonHiver() != null && o.getSaisonHiver())
										a("checked", "checked");
								fg();

							} g("div");
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_saisonHiver").a("class", "").f().sx("hiver").g("label");
						} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSaisonFraisInscription").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_saisonFraisInscription").a("class", "").f().sx("frais d'inscription").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "frais d'inscription")
									.a("class", "valeurSaisonFraisInscription w3-input w3-border ")
									.a("name", "saisonFraisInscription")
									.a("id", "Recherche_saisonFraisInscription")
									.a("value", o.strSaisonFraisInscription())
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
				{ e("form").a("action", "").a("id", "formSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurSaisonCle")
						.a("class", "valeurSaisonCle ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireSessionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_sessionCles").a("class", "").f().sx("sessions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "sessions")
										.a("title", "Les sessions scolaires de la saison scolaire. ")
										.a("class", "valeurObjetSuggere suggereSessionCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setSessionCles")
										.a("id", "Recherche_sessionCles")
										.a("autocomplete", "off")
										.a("oninput", "rechercherSaisonScolaireSessionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireSessionCles'), $('#listSaisonScolaireSessionCles')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSessionScolaireVals({ saisonCle: ", o.getPk(), " }, function() { var $e = $('#Recherche_sessionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_sessionCles')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireSessionCles").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurSaisonCles")
						.a("class", "valeurSaisonCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereSaisonScolaireAnneeCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", "Recherche_anneeCle").a("class", "").f().sx("année").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "année")
										.a("title", "L'année scolaire de la saison scolaire. ")
										.a("class", "valeurObjetSuggere suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setAnneeCle")
										.a("id", "Recherche_anneeCle")
										.a("autocomplete", "off")
										.a("oninput", "rechercherSaisonScolaireAnneeCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'SaisonScolaireAnneeCle'), $('#listSaisonScolaireAnneeCle')); ")
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postAnneeScolaireVals({ saisonCles: ", o.getPk(), " }, function() { var $e = $('#Recherche_anneeCle'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_anneeCle')); }); ")
										.f().sx("add")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireAnneeCle").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodySaisonGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeSaisonScolaire == null || listeSaisonScolaire.size() == 0) {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("aucune saison trouvée").g("span");
			} g("h1");
		} else if(listeSaisonScolaire != null && listeSaisonScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("une saison").g("span");
				} g("h1");
				SaisonScolaire o = listeSaisonScolaire.get(0);
				requeteSite_.setRequetePk(o.getPk());
			}
		} else {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("saisons").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("année").g("th");
						e("th").f().sx("début de la saison").g("th");
						e("th").f().sx("été").g("th");
						e("th").f().sx("hiver").g("th");
						e("th").f().sx("frais d'inscription").g("th");
						e("th").f().sx("sessions").g("th");
						e("th").f().sx("clé primaire").g("th");
						e("th").f().sx("crée").g("th");
						e("th").f().sx("modifié").g("th");
						e("th").f().sx("ID").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeSaisonScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeSaisonScolaire.size(); i++) {
						SaisonScolaire o = listeSaisonScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/frFR/saison/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getAnneeCle());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSaisonJourDebut());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSaisonEte());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSaisonHiver());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSaisonFraisInscription());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSessionCles());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getPk());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getCree());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getModifie());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSaisonId());
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeSaisonScolaire != null && listeSaisonScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SaisonScolaire o = listeSaisonScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SaisonScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageSaisonScolaire(o);
				}

			} g("div");
		}
		htmlBodyFormsSaisonGenPage();
	}

	public void htmlBodyFormsSaisonGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postSaisonScolaireModale').show(); ")
			.f().sx("Créer une saison")
		.g("button");
		{ e("div").a("id", "postSaisonScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSaisonScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer une saison").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SaisonScolaire o = new SaisonScolaire();

					// Form POST
					{ e("form").a("action", "").a("id", "postSaisonScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTSaisonScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postSaisonScolaire($('#postSaisonScolaireForm')); ")
						.f().sx("Créer une saison")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchSaisonScolaireModale').show(); ")
			.f().sx("Modifier des saisons")
		.g("button");
		{ e("div").a("id", "patchSaisonScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSaisonScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des saisons").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SaisonScolaire o = new SaisonScolaire();

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchSaisonScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheSaisonScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "rechercheSaisonScolaire($('#patchSaisonScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des une saison")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchSaisonScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSaisonScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchSaisonScolaire($('#patchSaisonScolaireFormulaireFiltres'), $('#patchSaisonScolaireFormulaireValeurs')); ")
						.f().sx("Modifier des saisons")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#deleteSaisonScolaireModale').show(); ")
			.f().sx("Supprimer des saisons")
		.g("button");
		{ e("div").a("id", "deleteSaisonScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSaisonScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des saisons").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SaisonScolaire o = new SaisonScolaire();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteSaisonScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSaisonScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "deleteSaisonScolaire(); ")
						.f().sx("Supprimer des saisons")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
